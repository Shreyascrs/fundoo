package com.bridgeit.fundoonotes.dto;

public class DtoresetPassword {

	private String password;

	public DtoresetPassword() {

		// TODO Auto-generated constructor stub
	}

	public DtoresetPassword(String password) {
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
