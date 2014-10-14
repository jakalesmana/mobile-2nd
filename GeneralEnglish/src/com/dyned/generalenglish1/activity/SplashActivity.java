package com.dyned.generalenglish1.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.gcm.MDAGCMRegistrar;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.GERecordHistory;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.PostInternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class SplashActivity extends SherlockActivity {

	private UserPreference pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		pref = UserPreference.getInstance(this);
		
		if (UserPreference.getInstance(this).getGCMID().equals("")) {
			MDAGCMRegistrar.registerGCM(this);
		}
		
		if (pref.isLoggedIn()) {
			loadLatestHistory();
		} else {
			startActivity(new Intent(SplashActivity.this, LoginActivity.class));
			finish();
		}
		
//		startActivity(new Intent(SplashActivity.this, GuideActivity.class));
	}
	
	private void loadLatestHistory() {
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {				
			}
			
			public void onDone(String str) {
				try {
					JSONObject result = new JSONObject(str);
					boolean status = result.getBoolean("status");
					if (status) {
						System.out.println("response update histroy: " + str);
						List<GERecordHistory> historyList = GERecordHistory.parseHistory(str);
						
						pref.setHistory(historyList);
						startActivity(new Intent(SplashActivity.this, HomeFragmentActivity.class));
						finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			public void onConnectionError(String message) {
			}
		});
		task.addPair("app_key", pref.getAppKey());
		task.addPair("conversation", GEApplication.app);
		
		task.execute(URLAddress.URL_CONVERSATION_HISTORY);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		finish();
	}

}
