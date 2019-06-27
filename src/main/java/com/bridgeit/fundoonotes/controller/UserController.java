package com.bridgeit.fundoonotes.controller;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.DtoresetPassword;
import com.bridgeit.fundoonotes.dto.DtoforgotPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.service.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = { "jwtTokens" })
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public Response userRegister(@RequestBody Dtouser dtouser, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Response status = userServiceImpl.registerUser(dtouser, request);
		return status;
	}

	@GetMapping("/mailactivation/{token}")
	public Response emailactivation(@PathVariable String token, HttpServletRequest request)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
			IllegalArgumentException, UnsupportedEncodingException {
		System.out.println("hitting to controller");
		Response activationMessage = userServiceImpl.validateEmail(token);
		return activationMessage;
	}

	@PostMapping("/login")
	public Response login(@RequestBody Dtologin dtologin) throws UnsupportedEncodingException {
		Response response = userServiceImpl.loginUser(dtologin);
		return response;
	}

	@PostMapping("/forgetpassword")
	public Response forgetPassword(@RequestBody DtoforgotPassword dtologin) {
		Response res = userServiceImpl.forgotPassword(dtologin);
		return res;
	}

	@PutMapping("/resetpassword/{token}")
	public Response resetpassword(@PathVariable String token, @RequestBody DtoresetPassword dtoforgetPassword){
		System.out.println("hitting to reset controller");
		Response res = userServiceImpl.resetPassword(token, dtoforgetPassword);
		return res;
	}
}

