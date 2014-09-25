package com.dyned.generalenglish1.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * The object packet to be sent to Middleware
 */
@SuppressWarnings("serial")
public class GEAnswerPacket implements Serializable {

	@SerializedName("coversation")
	private String coversation;
	
	@SerializedName("unit")
	private String unit;
	
	@SerializedName("lesson")
	private String lesson;
	
	@SerializedName("completed_time")
	private long completedTime;
	
	@SerializedName("listening_total")
	private int listeningTotal;
	
	@SerializedName("content_correct")
	private int comprehentionCorrect;
	
	@SerializedName("content_attempted")
	private int comprehentionAttempted;
	
	@SerializedName("grammar_correct")
	private int grammarCorrect;
	
	@SerializedName("grammar_attempted")
	private int grammarAttempted;
	
	public GEAnswerPacket() {
		coversation = "";
		unit = "";
		lesson = "";
		completedTime = 0;
		listeningTotal = 0;
		comprehentionCorrect = 0;
		comprehentionAttempted = 0;
		grammarCorrect = 0;
		grammarAttempted = 0;
	}

	public String getCoversation() {
		return coversation;
	}

	public void setCoversation(String coversation) {
		this.coversation = coversation;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public long getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(long completedTime) {
		this.completedTime = completedTime;
	}

	public int getListeningTotal() {
		return listeningTotal;
	}

	public void setListeningTotal(int listeningTotal) {
		this.listeningTotal = listeningTotal;
	}

	public int getComprehentionCorrect() {
		return comprehentionCorrect;
	}

	public void setComprehentionCorrect(int comprehentionCorrect) {
		this.comprehentionCorrect = comprehentionCorrect;
	}

	public int getComprehentionAttempted() {
		return comprehentionAttempted;
	}

	public void setComprehentionAttempted(int comprehentionAttempted) {
		this.comprehentionAttempted = comprehentionAttempted;
	}

	public int getGrammarCorrect() {
		return grammarCorrect;
	}

	public void setGrammarCorrect(int grammarCorrect) {
		this.grammarCorrect = grammarCorrect;
	}

	public int getGrammarAttempted() {
		return grammarAttempted;
	}

	public void setGrammarAttempted(int grammarAttempted) {
		this.grammarAttempted = grammarAttempted;
	}
	
}
