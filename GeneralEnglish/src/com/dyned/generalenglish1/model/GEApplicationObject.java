package com.dyned.generalenglish1.model;

import android.graphics.drawable.Drawable;

public class GEApplicationObject {

	private int id;
	private String packageName;
	private String appName;
	private String appIcon;
	private String appImage;
	private Drawable icon;
	private String directLink;
	
	public GEApplicationObject(){
	}
	
	public GEApplicationObject(String pckg, String name, String icon) {
		packageName = pckg;
		appName = name;
		appIcon = icon;
	}
	
	public GEApplicationObject(String pckg, String name, Drawable icon) {
		packageName = pckg;
		appName = name;
		this.icon = icon;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	
	public Drawable getDrawableIcon() {
		return icon;
	}

	public void setAppIcon(Drawable appIcon) {
		this.icon = appIcon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppImage() {
		return appImage;
	}

	public void setAppImage(String appImage) {
		this.appImage = appImage;
	}

	public Drawable getIc() {
		return icon;
	}

	public void setIc(Drawable ic) {
		this.icon = ic;
	}

	public String getDirectLink() {
		return directLink;
	}

	public void setDirectLink(String directLink) {
		this.directLink = directLink;
	}
	
	
}
