package com.bridgeit.fundoonotes.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class PasswordEncryptUtility {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}

//	public boolean isPassword(User user,Login)
}
