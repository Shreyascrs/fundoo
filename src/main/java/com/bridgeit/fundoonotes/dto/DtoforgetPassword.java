package com.bridgeit.fundoonotes.dto;

public class DtoforgetPassword {

	private String password;

	public DtoforgetPassword() {

		// TODO Auto-generated constructor stub
	}

	public DtoforgetPassword(String password) {
		super();
		this.password = password;
	}

	@Override
	public String toString() {
		return "DtoforgetPassword [password=" + password + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}
}
