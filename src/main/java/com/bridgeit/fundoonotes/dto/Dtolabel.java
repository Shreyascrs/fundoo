package com.bridgeit.fundoonotes.dto;

public class Dtolabel {

	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelname) {
		this.labelName = labelname;
	}

	@Override
	public String toString() {
		return "Dtolabel [labelname=" + labelName + "]";
	}

	public Dtolabel(String labelname) {
		super();
		this.labelName = labelname;
	}

	public Dtolabel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
