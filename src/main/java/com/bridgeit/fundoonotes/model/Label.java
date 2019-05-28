package com.bridgeit.fundoonotes.model;

import org.springframework.data.annotation.Id;

public class Label {

	@Id
	private String labelId;
	private String labelName;
	private String userId;

	public String getLabelid() {
		return labelId;
	}

	public void setLabelid(String labelid) {
		this.labelId = labelid;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getUserid() {
		return userId;
	}

	public void setUserid(String userid) {
		this.userId = userid;
	}

	@Override
	public String toString() {
		return "Label [labelid=" + labelId + ", labelName=" + labelName + ", userid=" + userId + "]";
	}

	public Label(String labelid, String labelName, String userid) {
		super();
		this.labelId = labelid;
		this.labelName = labelName;
		this.userId = userid;
	}

	public Label() {

		// TODO Auto-generated constructor stub
	}

}
