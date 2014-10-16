package com.dyned.generalenglish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class GELesson implements Serializable {

	private String id;
	private String code;
	private String name;
	private String title;
	private String image;
	private String audio;
	private List<String> listScript;
	private GEComprehension comprehension;
	private GEGrammar grammar;
	
	public GELesson() {
		id = "";
		name = "";
		code = "";
		title = "";
		audio = "";
		image = "";
		listScript = new ArrayList<String>();
		comprehension = new GEComprehension();
		grammar = new GEGrammar();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public List<String> getListScript() {
		return listScript;
	}

	public void setListScript(List<String> listScript) {
		this.listScript = listScript;
	}

	public GEComprehension getComprehension() {
		return comprehension;
	}

	public void setComprehension(GEComprehension comprehension) {
		this.comprehension = comprehension;
	}

	public GEGrammar getGrammar() {
		return grammar;
	}

	public void setGrammar(GEGrammar grammar) {
		this.grammar = grammar;
	}

	public static List<GELesson> parseListLesson(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			JSONArray jsonArray = obj.getJSONArray("lessonitem");
			
			List<GELesson> listLesson = new ArrayList<GELesson>();
			for (int i = 0; i < jsonArray.length(); i++) {
				GELesson lesson = new GELesson();
				lesson.setId(jsonArray.getJSONObject(i).getString("id"));
				lesson.setCode(jsonArray.getJSONObject(i).getString("code"));
				lesson.setName(jsonArray.getJSONObject(i).getString("name"));
				lesson.setTitle(jsonArray.getJSONObject(i).getString("title"));
				lesson.setImage(jsonArray.getJSONObject(i).getString("image"));
				lesson.setAudio(jsonArray.getJSONObject(i).getString("audio"));
				lesson.setListScript(parseScript(jsonArray.getJSONObject(i).getJSONArray("viewscript")));
				lesson.setComprehension(GEComprehension.parseComprehension(jsonArray.getJSONObject(i).getString("comprehension")));
				lesson.setGrammar(GEGrammar.parseGrammar(jsonArray.getJSONObject(i).getString("grammar")));
				listLesson.add(lesson);
			}
			return listLesson;
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<GELesson>();
		}
	}

	private static List<String> parseScript(JSONArray jsonArray) {
		try {
			List<String> scripts = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				String script = jsonArray.getString(i);
				scripts.add(script);
			}
			return scripts;
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
		
	}
	
}
