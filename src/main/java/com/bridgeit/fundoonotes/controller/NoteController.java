package com.bridgeit.fundoonotes.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.service.Noteserviceimpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@interface validateNote{
	
}

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	Noteserviceimpl noteserviceimpl;

	@PostMapping("/createnote")
	public Response createNote(@RequestBody Dtonote dtonote, @RequestHeader String token) throws IOException {
		Response response = noteserviceimpl.createNote(dtonote, token);
		return response;
	}

	@PostMapping("/updatenote")
	public Response updateNote(@RequestBody Dtonote dtonote, @RequestHeader String token,
			@RequestParam(value = "noteid") String noteid) throws IOException {
		Response response = noteserviceimpl.updateNote(dtonote, token, noteid);
		return response;
	}

	@PostMapping("/deletenote")
	public Response deleteNote(@RequestHeader String token, @RequestParam String noteId) throws IOException {
		Response response = noteserviceimpl.deleteNote(token, noteId);
		return response;
	}

	@GetMapping("/readnotes")
	public List<Dtonote> readNote(@RequestParam String token) throws IOException {
		List<Dtonote> notelist = noteserviceimpl.readNotes(token);
		return notelist;
	}

	@GetMapping("/archive")
	public String archive(@RequestParam String noteId, @RequestHeader String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		String response = noteserviceimpl.isArchive(token, noteId);
		return response;
	}

	@GetMapping("/trash")
	public String trash(@RequestParam String noteId, @RequestHeader String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		String response = noteserviceimpl.isTrash(token, noteId);
		return response;
	}

	@GetMapping("/pin")
	public String pin(@RequestParam String noteId, @RequestHeader String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		String response = noteserviceimpl.isPin(token, noteId);
		return response;
	}

	@PostMapping("/addlabeltonote")
	public Response addlabeltonote(@RequestParam String noteId, @RequestHeader String token,
			@RequestParam String labelId) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Response response = noteserviceimpl.addLabelToNote(noteId, token, labelId);
		return response;
	}

	@PostMapping("/removelabelfromnote")
	public Response removeLabelFromNote(@RequestParam String noteId, @RequestHeader String token, @RequestParam String labelId) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Response response = noteserviceimpl.removeLabelFromNote(noteId, token, labelId);
		return response;
	}

}
