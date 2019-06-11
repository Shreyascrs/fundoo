package com.bridgeit.fundoonotes.Response;

public class Response {

	private int statusCode;
	private String message;
	private String data;

	public Response(int statusCode, String message, String data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	public Response sendresponse(int statusCode, String message, String data)
	{
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
		return this;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMssage() {
		return message;
	}

	public void setMssage(String mssage) {
		this.message = mssage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", mssage=" + message + ", data=" + data + "]";
	}

	public Response() {
		
	}
	
}
