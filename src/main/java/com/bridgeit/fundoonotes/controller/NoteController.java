package com.bridgeit.fundoonotes.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.service.Noteserviceimpl;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	Noteserviceimpl noteserviceimpl;

	@PostMapping("/createnote")
	public String createNote(@RequestBody Dtonote dtonote, @RequestHeader String token) {
		String response = noteserviceimpl.createNote(dtonote, token);
		return response;
	}

	@PostMapping("/updatenote")
	public String updateNote(@RequestBody Dtonote dtonote, @RequestHeader String token,
			@RequestParam(value = "noteid") String noteid) {
		String response = noteserviceimpl.updateNote(dtonote, token, noteid);
		return response;
	}
	@PostMapping("/deletenote")
	public String deleteNote(@RequestHeader String token,@RequestParam String noteId)
	{
		String responce=noteserviceimpl.deleteNote(token, noteId);
		return responce;
	}
	
	@GetMapping("/readnotes")
	public List<Dtonote> readNote(@RequestParam String token)
	{
		List<Dtonote> notelist=noteserviceimpl.readNotes(token);
		return notelist;
	}
	
	@GetMapping("/archive")
	public String archive(@RequestParam String noteId,@RequestHeader String token)
	{
		String responce=noteserviceimpl.isArchive(token, noteId);
		return responce;
	}
	@GetMapping("/trash")
	public String trash(@RequestParam String noteId,@RequestHeader String token)
	{
		String responce=noteserviceimpl.isTrash(token, noteId);
		return responce;
	}
	@GetMapping("/pin")
	public String pin(@RequestParam String noteId,@RequestHeader String token)
	{
		String responce=noteserviceimpl.isPin(token, noteId);
		return responce;
	}
	
}
