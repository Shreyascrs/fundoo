package com.bridgeit.fundoonotes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.fundoonotes.ElasticSearch.IElesticservice;
import com.bridgeit.fundoonotes.model.Note;

@RestController
@RequestMapping("/elasticsearchnote")
public class ElasticController {

	@Autowired
	IElesticservice elastic;

	@PostMapping("/createnote")
	public String createNote(@RequestBody com.bridgeit.fundoonotes.model.Note note) throws IOException {
		elastic.createNote(note);
		return "created";
	}

	@GetMapping("/findbyid/{noteid}")
	public Note findById(@PathVariable String noteid) throws IOException {
		return elastic.findById(noteid);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/updatenote")
	public ResponseEntity updateNote(@RequestBody Note note) throws IOException {
		return new ResponseEntity(elastic.updateNote(note), HttpStatus.CREATED);
	}
	
	@GetMapping("/readall")
	public List<Note> findAll() throws IOException
	{
		return elastic.readAll();
	}

	@GetMapping("/searchbytitle")
	public List<Note> searchTitle(@RequestParam String title,@RequestParam String userid) throws IOException
	{
		return elastic.findByTitle(title, userid);
	}
	@GetMapping("/deletenote")
	public String deletenote(@RequestParam String noteid) throws IOException
	{
		return elastic.delete(noteid);
	}
}
