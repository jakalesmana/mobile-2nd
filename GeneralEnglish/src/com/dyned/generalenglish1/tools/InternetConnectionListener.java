package com.dyned.generalenglish1.tools;

public interface InternetConnectionListener {

	public void onStart();
	public void onConnectionError(String message);
	public void onDone(String str);
	
}
