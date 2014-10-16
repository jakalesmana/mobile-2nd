package com.dyned.generalenglish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

@SuppressWarnings("serial")
public class GEMainMenu implements Serializable {
	
	private String id;
	private String code;
	private String desc;
	private String title;
	private String imageId;
	private List<GELesson> listLesson;
	
	public GEMainMenu() {
		id = "";
		code = "";
		desc = "";
		title = "";
		imageId = "";
		listLesson = new ArrayList<GELesson>();
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<GELesson> getListLesson() {
		return listLesson;
	}

	public void setListLesson(List<GELesson> listLesson) {
		this.listLesson = listLesson;
	}

	public static List<GEMainMenu> parseListMenu(JSONArray jsonArray) {
		try {
			List<GEMainMenu> listMenu = new ArrayList<GEMainMenu>();
			for (int i = 0; i < jsonArray.length(); i++) {
				GEMainMenu menu = new GEMainMenu();
				menu.setId(jsonArray.getJSONObject(i).getString("id"));
				menu.setCode(jsonArray.getJSONObject(i).getString("code"));
				menu.setDesc(jsonArray.getJSONObject(i).getString("desc"));
				menu.setTitle(jsonArray.getJSONObject(i).getString("title"));
				menu.setImageId("unit" + (i + 1));
				menu.setListLesson(GELesson.parseListLesson(jsonArray.getJSONObject(i).getString("lesson")));
				listMenu.add(menu);
			}
			return listMenu;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<GEMainMenu>();
		}
	}

}
