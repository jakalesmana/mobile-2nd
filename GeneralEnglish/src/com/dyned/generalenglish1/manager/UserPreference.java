package com.dyned.generalenglish1.manager;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.model.GEAnswerPacket;
import com.dyned.generalenglish1.model.GERecordHistory;
import com.dyned.generalenglish1.model.GERecordLesson;
import com.dyned.generalenglish1.util.GsonExclusion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class UserPreference {

	private static UserPreference instance;
	private static SharedPreferences myPref;
	
	private final String ANSWER_PACKET = "answer_packet";
	private final String HISTORIES = "history";
	
	private final String APP_KEY = "app_key";
	private final String GCM_ID = "gcm_id";
	private final String LOGGEN_IN = "logged_in";
	private final String NAME = "name";
	private final String AVATAR = "avatar";
	private final String COMPLETE_UNIT = "completed_unit";
	
	public static UserPreference getInstance(Context c){
		if (instance == null) {
			instance = new UserPreference();
			myPref = c.getSharedPreferences(GEApplication.app, 0);
		}
		return instance;
	}
	
	public void addToCurrentAnswerPacket(GEAnswerPacket newPacket){
		
		GEAnswerPacket currentPacket = getCurrentAnswerPacket();
		GEAnswerPacket totalPacket = new GEAnswerPacket();
		//merge new packet with current packet
		if (currentPacket.getUnit().equals(newPacket.getUnit()) && 
				currentPacket.getLesson().equals(newPacket.getLesson())) {

			totalPacket.setCoversation(newPacket.getConversation());
			totalPacket.setUnit(newPacket.getUnit());
			totalPacket.setLesson(newPacket.getLesson());
			totalPacket.setListeningTotal(currentPacket.getListeningTotal() + newPacket.getListeningTotal());
			if(newPacket.getCompletedTime() > 0) totalPacket.setCompletedTime(newPacket.getCompletedTime());
			totalPacket.setComprehentionAttempted(currentPacket.getComprehentionAttempted() + newPacket.getComprehentionAttempted());
			totalPacket.setComprehentionCorrect(currentPacket.getComprehentionCorrect() + newPacket.getComprehentionCorrect());
			totalPacket.setGrammarAttempted(currentPacket.getGrammarAttempted() + newPacket.getGrammarAttempted());
			totalPacket.setGrammarCorrect(currentPacket.getGrammarCorrect() + newPacket.getGrammarCorrect());
		} else {
			totalPacket = newPacket;
		}
		
		
		GsonExclusion ex = new GsonExclusion();
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
		
		String serialPacket = gson.toJson(totalPacket);
		
		System.out.println("packet answer saved: " + serialPacket);
		
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(ANSWER_PACKET, serialPacket);
		editor.commit();
	}
	
	public GEAnswerPacket getCurrentAnswerPacket(){
		GsonExclusion ex = new GsonExclusion();
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
		
		String packet = myPref.getString(ANSWER_PACKET, "");
		
		GEAnswerPacket packetObj = gson.fromJson(packet, GEAnswerPacket.class);
		
		if (packetObj == null) {
			return new GEAnswerPacket();
		} else {
			return packetObj;
		}
	}
	
	public void clearCurrentAnswerPacket(){
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(ANSWER_PACKET, "");
		editor.commit();
	}

	public void setHistory(List<GERecordHistory> historyList) {
		GsonExclusion ex = new GsonExclusion();
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
		
		JsonArray histories = gson.toJsonTree(historyList).getAsJsonArray();
		System.out.println("history to save: " + histories.toString());
		
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(HISTORIES, histories.toString());
		editor.commit();
	}
	
	public List<GERecordHistory> getHistory(){
		GsonExclusion ex = new GsonExclusion();
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
		
		Type listType = new TypeToken<List<GERecordHistory>>(){}.getType();
		return gson.fromJson(myPref.getString(HISTORIES, ""), listType);
	}
	
	public void setLoggedIn(boolean isLoggedIn) {
		SharedPreferences.Editor editor = myPref.edit();
		editor.putBoolean(LOGGEN_IN, isLoggedIn);
		editor.commit();
	}

	public boolean isLoggedIn() {
		return myPref.getBoolean(LOGGEN_IN, false);
	}
	
	public void setAppKey(String appkey) {
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(APP_KEY, appkey);
		editor.commit();
	}
	
	public void setGCMID(String id) {
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(GCM_ID, id);
		editor.commit();
	}

	public String getAppKey() {
		return myPref.getString(APP_KEY, "");
	}
	
	public String getGCMID() {
		return myPref.getString(GCM_ID, "");
	}
	
	public void setName(String name) {
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(NAME, name);
		editor.commit();
	}

	public String getName() { 
		return myPref.getString(NAME, "");
	}
	
	public void setAvatar(String image_url) {
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(AVATAR, image_url);
		editor.commit();
	}

	public String getAvatar() {
		return myPref.getString(AVATAR, "");
	}

	public void addCompletedUnit(String unitCode) {
		String comp = myPref.getString(COMPLETE_UNIT, "");
		StringBuilder sb = new StringBuilder(comp);
		if (comp.isEmpty()) {
			sb.append(unitCode);
		} else {
			sb.append("," + unitCode);
		}
		
		SharedPreferences.Editor editor = myPref.edit();
		editor.putString(COMPLETE_UNIT, unitCode);
		editor.commit();
	}

	public boolean isCompletedUnit(String unitCode) {
		String comp = myPref.getString(COMPLETE_UNIT, "");
		String[] completes = comp.split(",");
		for (int i = 0; i < completes.length; i++) {
			if (unitCode.equalsIgnoreCase(completes[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean isCompletedLesson(String unitCode, String lessonCode) {
		List<GERecordHistory> histories = getHistory();
		for (int i = 0; i < histories.size(); i++) {
			if (histories.get(i).getUnit().equalsIgnoreCase(unitCode)) {
				List<GERecordLesson> lessons = histories.get(i).getRecords();
				for (int j = 0; j < lessons.size(); j++) {
					if (lessons.get(j).getStatus().equals("completed")) {
						if (lessons.get(j).getLessonCode().equalsIgnoreCase(lessonCode)) {
							return true;
						}
					}
				}
			}
			
		}
		return false;
	}
	
	public void logout() {
		setLoggedIn(false);
		setAppKey("");
		setName("");
	}
}
