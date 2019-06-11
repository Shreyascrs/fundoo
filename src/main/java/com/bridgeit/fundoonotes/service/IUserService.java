package com.bridgeit.fundoonotes.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.DtoforgetPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;

public interface IUserService {

	Response registerUser(Dtouser dtouser,HttpServletRequest request);
	Response loginUser(Dtologin dtologin);
	Response forgetPassword(Dtologin dtologin);
	Response resetPassword(String token,DtoforgetPassword dtoforgetPassword);
}
