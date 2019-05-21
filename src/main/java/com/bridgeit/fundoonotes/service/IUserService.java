package com.bridgeit.fundoonotes.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;

public interface IUserService {

	String registerUser(Dtouser dtouser,HttpServletRequest request);
	String loginUser(Dtologin dtologin);
}
