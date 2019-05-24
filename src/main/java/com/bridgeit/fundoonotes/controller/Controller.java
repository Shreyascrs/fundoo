package com.bridgeit.fundoonotes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonotes.dto.DtoforgetPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class Controller {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public String userRegister(@RequestBody Dtouser dtouser, HttpServletRequest request) {
		String status = userServiceImpl.registerUser(dtouser, request);
		return status;
	}

	@GetMapping("/mailactivation/{token}")
	public String emailactivation(@PathVariable String token, HttpServletRequest request) {
		userServiceImpl.validateEmail(token);

		return null;
	}

	@PostMapping("/login")
	public String login(@RequestBody Dtologin dtologin)
	{
		userServiceImpl.loginUser(dtologin);
		return "success";
	}
	
	@PostMapping("/forgetpassword")
	public String forgetPassword(@RequestBody Dtologin dtologin)
	{
		String res=userServiceImpl.forgetPassword(dtologin);
		return res;
	}
	
	@GetMapping("/resetpassword/{token}")
	public String resetpassword(@PathVariable String token,@RequestBody DtoforgetPassword dtoforgetPassword )
	{
		String res=userServiceImpl.resetPassword(token, dtoforgetPassword);
		return res;
	}
}
