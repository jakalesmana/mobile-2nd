package com.dyned.generalenglish.model;

public class GENotification {

	private String message;
	private long date;
	
	public GENotification(String message, long date){
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	
}
