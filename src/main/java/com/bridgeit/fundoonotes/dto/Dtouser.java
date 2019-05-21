package com.bridgeit.fundoonotes.dto;

public class Dtouser {

	private String name;
	private String phonenumber;
	private String email;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Dtouser [name=" + name + ", phonenumber=" + phonenumber + ", email=" + email + ", password=" + password
				+ "]";
	}

	public Dtouser() {
		
	}

	public Dtouser(String name, String phonenumber, String email, String password) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
	}

}
