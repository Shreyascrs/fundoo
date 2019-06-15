package com.bridgeit.fundoonotes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.DtoforgetPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public Response userRegister(@RequestBody Dtouser dtouser, HttpServletRequest request) {
		Response status = userServiceImpl.registerUser(dtouser, request);
		return status;
	}

	@GetMapping("/mailactivation/{token}")
	public Response emailactivation(@PathVariable String token, HttpServletRequest request) {
		System.out.println("hitting to controller");
		Response activationMessage = userServiceImpl.validateEmail(token);
		return activationMessage;
	}

	@GetMapping("/login")
	public Response login(@RequestBody Dtologin dtologin) {
		Response response = userServiceImpl.loginUser(dtologin);
		return response;
	}

	@PostMapping("/forgetpassword")
	public Response forgetPassword(@RequestBody Dtologin dtologin) {
		Response res = userServiceImpl.forgetPassword(dtologin);
		return res;
	}

	@GetMapping("/resetpassword/{token}")
	public Response resetpassword(@PathVariable String token, @RequestBody DtoforgetPassword dtoforgetPassword) {
		Response res = userServiceImpl.resetPassword(token, dtoforgetPassword);
		return res;
	}
}
