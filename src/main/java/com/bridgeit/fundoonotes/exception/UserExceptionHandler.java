package com.bridgeit.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserExceptionHandler extends RuntimeException {
	
	

//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<String> RuntimeException(String message) {
//		return new ResponseEntity<String>("Something is not right", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@ExceptionHandler(NoteException.class)
//	public ResponseEntity<String> NoteException(String message) {
//		return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	public UserExceptionHandler(String message) {
		super(message);
		
	}

	public UserExceptionHandler()
	{
		
	}
}
