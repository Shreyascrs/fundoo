package com.bridgeit.fundoonotes.dto;

public class Dtonote {

	private String title;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Dtonote(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	public Dtonote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
