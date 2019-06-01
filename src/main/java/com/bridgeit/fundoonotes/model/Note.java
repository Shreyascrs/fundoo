package com.bridgeit.fundoonotes.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


public class Note {

	@Id
	private String noteid;
	private String userid;
	private String title;
	private String description;
	private String createdTime;
	private String updatedTime;
	private boolean archive;
	private boolean trash;
	private boolean isPin;
	
	@DBRef
	private List<Label> labels;
	
	
	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public String getNoteid() {
		return noteid;
	}

	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	@Override
	public String toString() {
		return "Note [noteid=" + noteid + ", userid=" + userid + ", title=" + title + ", description=" + description
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", archive=" + archive + ", trash="
				+ trash + ", isPin=" + isPin + "]";
	}

	public Note(String noteid, String userid, String title, String description, String createdTime, String updatedTime,
			boolean archive, boolean trash, boolean isPin) {
		super();
		this.noteid = noteid;
		this.userid = userid;
		this.title = title;
		this.description = description;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.archive = archive;
		this.trash = trash;
		this.isPin = isPin;
	}

	public Note() {
		 
		// TODO Auto-generated constructor stub
	}


}
