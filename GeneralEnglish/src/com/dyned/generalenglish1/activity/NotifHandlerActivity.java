package com.dyned.generalenglish1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.GEPushNotification;
import com.dyned.generalenglish1.util.NotificationUtil;

public class NotifHandlerActivity extends SherlockActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NotificationUtil.getInstance().clear();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("xxx on resume");
		
		GEPushNotification notif = UserPreference.getPushBridgeData();
		
		if (notif != null) {
			Intent i = new Intent(this, HomeFragmentActivity.class);
			i.putExtra("GEData", notif);
			startActivity(i);
		} else {
			Toast.makeText(this, "notif null", Toast.LENGTH_SHORT).show();
		}
		
		finish();
	}
}