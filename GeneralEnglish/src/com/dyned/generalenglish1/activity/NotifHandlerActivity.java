package com.dyned.generalenglish1.activity;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.dyned.generalenglish1.model.GEPushNotification;
import com.dyned.generalenglish1.util.NotificationUtil;

public class NotifHandlerActivity extends SherlockActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NotificationUtil.getInstance().clear();
		
		GEPushNotification notif = (GEPushNotification) getIntent().getSerializableExtra("GEData");
		
		if (notif != null) {
			Intent i = new Intent(this, HomeFragmentActivity.class);
			i.putExtra("GEData", notif);
			startActivity(i);
		}
		
		finish();
	}
}