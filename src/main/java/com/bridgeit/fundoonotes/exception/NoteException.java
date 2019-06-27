package com.bridgeit.fundoonotes.exception;

public class NoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NoteException(String message) {
		super(message);
		this.message = message;

	}

}
