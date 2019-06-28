package com.bridgeit.fundoonotes.service;

import javax.servlet.http.HttpServletRequest;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.DtoresetPassword;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.dto.DtoforgotPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.model.User;

public interface IUserService {

	Response registerUser(Dtouser dtouser, HttpServletRequest request);

	Response loginUser(Dtologin dtologin);

	Response forgotPassword(DtoforgotPassword dtologin);

	Response resetPassword(String token, DtoresetPassword dtoforgetPassword);
	
	boolean isUserPresent(String token);
}
