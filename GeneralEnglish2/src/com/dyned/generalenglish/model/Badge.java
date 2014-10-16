package com.dyned.generalenglish.model;

public class Badge {

	private int unit;
	private boolean open;
	
	public Badge(int unit, boolean open){
		this.unit = unit;
		this.open = open;
	}

	public int getUnit() {
		return unit;
	}
	
	public boolean isOpen() {
		return open;
	}
}
