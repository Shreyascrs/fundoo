package com.bridgeit.fundoonotes.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.ElasticSearch.ElasticsearchService;



@RestController
@RequestMapping("/elasticsearchNote")
public class ElasticController {
	
	@Autowired
	ElasticsearchService elastic;
	
	@PostMapping("/createnote")
	public String createNote(@RequestBody com.bridgeit.fundoonotes.model.Note note) throws IOException
	{
		elastic.createNote(note);
		return "created";
	}
	

}
