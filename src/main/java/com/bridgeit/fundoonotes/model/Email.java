package com.bridgeit.fundoonotes.model;

public class Email {

	private String email;
	private String to;
	private String subject;
	private String body;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Email(String email, String to, String subject, String body) {
		super();
		this.email = email;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	public Email()
	{
		
	}
	@Override
	public String toString() {
		return "Email [email=" + email + ", to=" + to + ", subject=" + subject + ", body=" + body + "]";
	}
	
}
