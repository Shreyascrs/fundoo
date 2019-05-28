package com.bridgeit.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.dto.Dtolabel;
import com.bridgeit.fundoonotes.service.LabelServiceimpl;

@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LabelServiceimpl labelServiceimpl;
	
	@PostMapping("/createlabel")
	public String createLabel(@RequestHeader String token, @RequestBody Dtolabel dtolabel)
	{
		String responce=labelServiceimpl.createLabel(token, dtolabel);
		return responce;
	}
	
	@PostMapping("/updatelabel")
	public String updateLabel(@RequestHeader String token,@RequestBody Dtolabel dtolabel,@RequestParam String labelId)
	{
		String responce=labelServiceimpl.updateLabel(dtolabel, labelId, token);
		return responce;
	}
	@DeleteMapping("/deletelabel")
	public String deletelabel(@RequestHeader String token,@RequestParam String labelId)
	{
		String responce=labelServiceimpl.deleteLabel(token, labelId);
		return responce;
	}
	@PostMapping("/readall")
	public List<Dtolabel> readAll(@RequestHeader String token)
	{
		List<Dtolabel> label=labelServiceimpl.readAll(token);
		return label;
	}
}
