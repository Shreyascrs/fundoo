package com.bridgeit.fundoonotes.dto;

public class DtoforgotPassword {

	String email;

	public DtoforgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtoforgotPassword(String email) {
		
		this.email = email;
	}

	@Override
	public String toString() {
		return "DtoforgotPassword [email=" + email + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
