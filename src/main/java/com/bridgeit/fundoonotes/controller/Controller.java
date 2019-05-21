package com.bridgeit.fundoonotes.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.service.UserServiceImpl;



@RestController
@RequestMapping("/user")
public class Controller {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@PostMapping("/register")
	public String userRegister(@RequestBody Dtouser dtouser ,HttpServletRequest request) {
		String status =  userServiceImpl.registerUser(dtouser, request);
		return status;
	}
	
	
	
	
}
