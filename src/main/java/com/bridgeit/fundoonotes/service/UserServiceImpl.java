package com.bridgeit.fundoonotes.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.model.Email;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.PasswordEncryptUtility;
import com.bridgeit.fundoonotes.utility.TokenUtility;
import com.bridgeit.fundoonotes.utility.Utility;
import com.bridgeit.fundoonotes.utility.UtilityMail;

@Service
public class UserServiceImpl implements IUserService {

	ModelMapper mapper = new ModelMapper();
	@Autowired
	private IRepository repository;
	@Autowired
	UtilityMail emailsender;
	@Autowired
	private PasswordEncryptUtility passwordEncryptUtility;

	public String registerUser(Dtouser dtouser, HttpServletRequest request) {

		User user = mapper.map(dtouser, User.class);
		Email email = new Email();

		String token = TokenUtility.generateToken(user.getUserId());
		user.setPassword(passwordEncryptUtility.encryptPassword(dtouser.getPassword()));
		user.setToken(token);
		user.setCreatedTime(Utility.todayDate());
		user.setUpdatedTime(Utility.todayDate());
		User status = repository.save(user);
		System.err.println(status);
		email.setEmail("fundooapp6@gmail.com");
		email.setTo(email.getEmail());
		email.setSubject("verification");
		email.setBody("body");
		StringBuffer url;
	System.err.println(	 url= request.getRequestURL());
		
		try {
			
				email.setBody(emailsender.getlink("http://localhost:9090/user/mailactivation/",status.getUserId()));
		} catch (Exception e) {
			System.out.println("error in email snding");
		}
		emailsender.send(email);
		return "success";
	}

	
	public String validateEmail(String token)
	{
		String id=TokenUtility.verifyToken(token);
		Optional<User> user=repository.findById(id);
		if(user.isPresent())
		{
			user.get().setVerified(true);
			user.get().setUpdatedTime(Utility.todayDate());
			repository.save(user.get());
		}
		else
		{
			System.out.println("error");
		}
		
		
		
		return null;
	}
	
	
	@Override
	public String loginUser(Dtologin dtologin) {

		return null;
	}

}
