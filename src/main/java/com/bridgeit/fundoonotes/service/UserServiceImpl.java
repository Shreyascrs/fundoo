package com.bridgeit.fundoonotes.service;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgeit.fundoonotes.Response.*;
import com.bridgeit.fundoonotes.dto.DtoforgetPassword;
import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.dto.Dtouser;
import com.bridgeit.fundoonotes.exception.UserExceptionHandler;
import com.bridgeit.fundoonotes.model.Email;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.PasswordEncryptUtility;
import com.bridgeit.fundoonotes.utility.TokenUtility;
import com.bridgeit.fundoonotes.utility.Utility;
import com.bridgeit.fundoonotes.utility.UtilityMail;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private IRepository repository;
	@Autowired
	UtilityMail emailsender;
	@Autowired
	private PasswordEncryptUtility passwordEncryptUtility;
	@Autowired
	Response response;

	public Response registerUser(Dtouser dtouser, HttpServletRequest request) {

		User user = mapper.map(dtouser, User.class);
		Email email = new Email();

		String token = TokenUtility.generateToken(user.getUserId());
		user.setPassword(passwordEncryptUtility.encryptPassword(dtouser.getPassword()));
		user.setToken(token);
		user.setCreatedTime(Utility.todayDate());
		user.setUpdatedTime(Utility.todayDate());
		User status = repository.save(user);
		System.err.println(status);
		email.setEmail(dtouser.getEmail());
		email.setTo(email.getEmail());
		email.setSubject("verification");
		email.setBody("body");

		try {

			email.setBody(emailsender.getlink("http://localhost:9090/user/mailactivation/", status.getUserId()));
			emailsender.send(email);
			return response.sendresponse(201, "email sent successfully", "");
		} catch (Exception e) {
			throw new UserExceptionHandler("email not sent");
		}

	}

	public Response validateEmail(String token) {
		String id = TokenUtility.verifyToken(token);
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			user.get().setVerified(true);
			user.get().setUpdatedTime(Utility.todayDate());
			repository.save(user.get());
			return response.sendresponse(200, "Email verified", token);
		}
		return response.sendresponse(204, "Email not verified", "");

	}

	@Override
	public Response loginUser(Dtologin dtologin) {

		boolean isEmailPresent = repository.findUserByEmail(dtologin.getEmail()).isPresent();
		if (!isEmailPresent) {

			return response.sendresponse(204, "email not registered", "");
		}
		User user = repository.findUserByEmail(dtologin.getEmail()).get();
		String token=TokenUtility.generateToken(user.getUserId());
		if (user.getisVerified() == false) {
			return response.sendresponse(204, "Email not verified verify your email", "");
		}
		boolean verifyPassword = passwordEncryptUtility.isPassword(dtologin, user);
		if (!verifyPassword) {

			return response.sendresponse(204, "Password not mattching", "");
		}
		user.setUpdatedTime(Utility.todayDate());
		repository.save(user);

		return response.sendresponse(200, "login successfull", token);
	}

	@Override
	public Response forgetPassword(Dtologin dtologin) {
		Email email = new Email();
		Optional<User> user = repository.findUserByEmail(dtologin.getEmail());
		if (user.isPresent()) {
			User use = user.get();
			email.setEmail("fundooapp6@gmail.com");
			email.setTo(dtologin.getEmail());
			email.setSubject("reset password link");
			try {
				email.setBody(emailsender.getlink("http://localhost:9090/user/resetpassword/", use.getUserId()));
				emailsender.send(email);

			} catch (Exception e) {
//				throw new UserExceptionHandler("internal server error");

			}
			return response.sendresponse(205, "Password reset successfull", "");

		} else {
			return response.sendresponse(204, "password reset not sent", "");
		}

	}

	public Response resetPassword(String token, DtoforgetPassword dtoforgetPassword) {

		String id = TokenUtility.verifyToken(token);
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			System.err.println(dtoforgetPassword.getPassword());
			user.get().setPassword(passwordEncryptUtility.encryptPassword(dtoforgetPassword.getPassword()));
			user.get().setUpdatedTime(Utility.todayDate());
			repository.save(user.get());
			return response.sendresponse(200, "password changed successfull", "");
		} else {
			return response.sendresponse(204, "password not changed", "");
		}

	}

}
