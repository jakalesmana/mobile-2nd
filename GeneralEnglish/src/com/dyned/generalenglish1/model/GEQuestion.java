package com.dyned.generalenglish1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class GEQuestion implements Serializable {
	private String id;
	private String question;
	private List<String> options;
	private String answer;
	
	public GEQuestion() {
		id = "";
		question = "";
		options = new ArrayList<String>();
		answer = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public static List<GEQuestion> parseListQuestion(JSONArray jsonArray) {
		try {
			List<GEQuestion> list = new ArrayList<GEQuestion>();
			for (int i = 0; i < jsonArray.length(); i++) {
				GEQuestion question = new GEQuestion();
				JSONObject obj = jsonArray.getJSONObject(i);
				question.setId(obj.getString("id"));
				question.setQuestion(obj.has("question") ? obj.getString("question") : "");
				question.setAnswer(obj.getString("answer"));
				question.setOptions(parseOptions(obj.getJSONArray("option")));
				list.add(question);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<GEQuestion>();
		}
	}

	private static List<String> parseOptions(JSONArray jsonArray) {
		try {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(jsonArray.getString(i));
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
}
