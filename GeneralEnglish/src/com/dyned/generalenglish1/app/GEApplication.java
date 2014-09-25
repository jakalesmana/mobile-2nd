package com.dyned.generalenglish1.app;

import android.app.Application;

import com.dyned.generalenglish1.model.GE;
import com.dyned.generalenglish1.util.AppUtil;

public class GEApplication extends Application {
	
	public static final String app = "GE1";
//	public static final String sample_app_key = "9cc8bd9f879ce0233ccc5fada1ac4d54f5f05893";
	
	private static GE mainAppContent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		String appData = AppUtil.ReadTextFileFromAssets(this, "app_content.json");
		mainAppContent = GE.parseGE(appData);
	}
	
	public static GE getGEContent(){
		return mainAppContent;
	}
}
