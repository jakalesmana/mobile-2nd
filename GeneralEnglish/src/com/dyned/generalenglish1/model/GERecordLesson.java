package com.dyned.generalenglish1.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class GERecordLesson {

	@SerializedName("lesson")
	private String lessonCode;
	
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
	
	@SerializedName("status")
	private String status;
	
	public GERecordLesson(){
	}

	public String getLessonCode() {
		return lessonCode;
	}

	public void setLessonCode(String lessonCode) {
		this.lessonCode = lessonCode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static GERecordLesson parseRecordLesson(String data){
		try {
			GERecordLesson grl = new GERecordLesson();
			JSONObject obj = new JSONObject(data);
			grl.setCompletedTime(obj.getLong("completed_time"));
			grl.setStatus(obj.getString("status"));
			grl.setLessonCode(obj.getString("lesson"));
			grl.setComprehentionAttempted(obj.getInt("content_attempted"));
			grl.setComprehentionCorrect(obj.getInt("content_correct"));
			grl.setGrammarAttempted(obj.getInt("grammar_attempted"));
			grl.setGrammarCorrect(obj.getInt("grammar_correct"));
			grl.setListeningTotal(obj.getInt("listening_total"));
			return grl;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
