package com.dyned.generalenglish.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class GE {

	private String appName;
	private List<GEMainMenu> listMenu;
	
	public GE() {
		appName = "";
		listMenu = new ArrayList<GEMainMenu>();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<GEMainMenu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<GEMainMenu> listMenu) {
		this.listMenu = listMenu;
	}
	
	public static GE parseGE(String appData) {
		try {
			GE ge = new GE();
			JSONObject obj = new JSONObject(appData);
			JSONObject main = obj.getJSONObject("mainlist");
			ge.setAppName(main.getString("appname"));
			ge.setListMenu(GEMainMenu.parseListMenu(main.getJSONArray("mainlistitem")));
			return ge;
		} catch (JSONException e) {
			e.printStackTrace();
			return new GE();
		}
	}
}
