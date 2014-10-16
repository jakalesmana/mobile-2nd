package com.dyned.generalenglish.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GEPushNotification implements Serializable {

	private String converstaion;
	private String type;
	private String message;
	private int conversationId;
	private int unitId;
	private int lessonId;
	
	public GEPushNotification() {
	}

	public String getConverstaion() {
		return converstaion;
	}

	public void setConverstaion(String converstaion) {
		this.converstaion = converstaion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
