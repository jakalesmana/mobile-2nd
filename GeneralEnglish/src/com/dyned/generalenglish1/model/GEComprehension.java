package com.dyned.generalenglish1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class GEComprehension implements Serializable {
	
	private String title;
	private String type;
	private List<GEQuestion> listQuestion;
	
	public GEComprehension() {
		title = "";
		type = "";
		listQuestion = new ArrayList<GEQuestion>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<GEQuestion> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<GEQuestion> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public static GEComprehension parseComprehension(String data) {
		try {
			GEComprehension comp = new GEComprehension();
			JSONObject obj = new JSONObject(data);
			comp.setTitle(obj.has("title") ? obj.getString("title") : "");
			comp.setType(obj.getString("type"));
			comp.setListQuestion(GEQuestion.parseListQuestion(obj.getJSONArray("comprehensionitem")));
			return comp;
		} catch (JSONException e) {
			e.printStackTrace();
			return new GEComprehension();
		}
	}
	
}
