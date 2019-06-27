package com.bridgeit.fundoonotes.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtolabel;
import com.bridgeit.fundoonotes.service.LabelServiceimpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LabelServiceimpl labelServiceimpl;

	@PostMapping("/createlabel")
	public Response createLabel(@PathVariable String token, @RequestBody Dtolabel dtolabel) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Response response = labelServiceimpl.createLabel(token, dtolabel);
		return response;
	}

	@PutMapping("/updatelabel")
	public Response updateLabel(@PathVariable String token, @RequestBody Dtolabel dtolabel,
			@RequestParam String labelId) throws  UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Response response = labelServiceimpl.updateLabel(dtolabel, labelId, token);
		return response;
	}

	@DeleteMapping("/deletelabel")
	public Response deletelabel(@RequestHeader String token, @RequestParam String labelId) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Response responce = labelServiceimpl.deleteLabel(token, labelId);
		return responce;
	}

	@GetMapping("/readall")
	public List<Dtolabel> readAll(@PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		List<Dtolabel> label = labelServiceimpl.readAll(token);
		return label;
	}
}
