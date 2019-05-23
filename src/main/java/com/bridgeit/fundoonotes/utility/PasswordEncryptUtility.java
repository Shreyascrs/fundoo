package com.bridgeit.fundoonotes.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.model.User;
@Component
public class PasswordEncryptUtility {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public Boolean isPassword(Dtologin dtologin,User user)
	{
		return passwordEncoder.matches(dtologin.getPassword(),user.getPassword());
	}

//	public boolean isPassword(User user,Login)
}
