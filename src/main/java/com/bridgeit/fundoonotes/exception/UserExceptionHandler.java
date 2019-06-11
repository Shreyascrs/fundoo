package com.bridgeit.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends RuntimeException{
	
	public UserExceptionHandler(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExceptionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserExceptionHandler(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExceptionHandler(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> RuntimeException(String message) {
		return new ResponseEntity<String>("Something is not right", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoteException.class)
	public ResponseEntity<String> NoteException(String message) {
		return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
