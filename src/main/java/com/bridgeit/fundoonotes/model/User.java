package com.bridgeit.fundoonotes.model;

import javax.validation.constraints.NotNull;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class User {
	@Id
	private String id;
	private String name;
	private String phoneNumber;
	@Indexed(unique=true)
	private String email;
	private String password;
	private String createdTime;
	private String updatedTime;
	private boolean isVerified;
	@NotNull
	private String token;

	public String getUserId() {
		return id;
	}

	public void setUserId(String userId) {
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ ", isVerified=" + isVerified + ", token=" + token + "]";
	}

	public User() {

	}

	public User(String userId, String name, String phoneNumber, String email, String password, String createdTime,
			String updatedTime, boolean isVerified, @NotNull String token) {
		super();
		this.id = userId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.isVerified = isVerified;
		this.token = token;
	}

}
