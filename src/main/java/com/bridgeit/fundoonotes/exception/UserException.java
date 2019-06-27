package com.bridgeit.fundoonotes.exception;

public class UserException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserException(String message)
	{
		super(message);
		this.message=message;
	}

	
}
