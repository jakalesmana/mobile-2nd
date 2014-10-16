package com.dyned.generalenglish.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class GERecordHistory {

	@SerializedName("unit")
	private String unit;
	
	@SerializedName("lessons")
	private List<GERecordLesson> records;
	
	public GERecordHistory(){
		unit = "";
		records = new ArrayList<GERecordLesson>();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<GERecordLesson> getRecords() {
		return records;
	}

	public void setRecords(List<GERecordLesson> records) {
		this.records = records;
	}
	
	public static List<GERecordHistory> parseHistory(String data){
		try {
			List<GERecordHistory> list = new ArrayList<GERecordHistory>();
			
			JSONObject obj = new JSONObject(data);
			JSONObject dataObj = obj.getJSONObject("data");
			JSONArray arr = dataObj.getJSONArray("records");
			for (int i = 0; i < arr.length(); i++) {
				GERecordHistory grh = new GERecordHistory();
				grh.setUnit(arr.getJSONObject(i).getString("unit"));
				
				List<GERecordLesson> lessons = new ArrayList<GERecordLesson>();
				JSONArray arrLesson = arr.getJSONObject(i).getJSONArray("lessons");
				for (int j = 0; j < arrLesson.length(); j++) {
					GERecordLesson lesson = GERecordLesson.parseRecordLesson(arrLesson.getJSONObject(j).toString());
					lessons.add(lesson);
				}
				grh.setRecords(lessons);
				list.add(grh);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
