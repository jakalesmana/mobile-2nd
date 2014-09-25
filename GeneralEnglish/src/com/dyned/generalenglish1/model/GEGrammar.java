package com.dyned.generalenglish1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class GEGrammar implements Serializable {

	private String type;
	private List<GEQuestion> listQuestion;
	
	public GEGrammar() {
		type = "";
		listQuestion = new ArrayList<GEQuestion>();
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

	public static GEGrammar parseGrammar(String data) {
		try {
			GEGrammar grammar = new GEGrammar();
			JSONObject obj = new JSONObject(data);
			grammar.setType(obj.getString("type"));
			grammar.setListQuestion(GEQuestion.parseListQuestion(obj.getJSONArray("grammaritem")));
			return grammar;
		} catch (JSONException e) {
			e.printStackTrace();
			return new GEGrammar();
		}
	}
}
