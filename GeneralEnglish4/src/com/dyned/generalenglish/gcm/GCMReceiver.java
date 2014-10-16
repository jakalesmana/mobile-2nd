package com.dyned.generalenglish.gcm;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dyned.generalenglish.activity.NotifHandlerActivity;
import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GEPushNotification;
import com.dyned.generalenglish.model.GERecordHistory;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.PostInternetTask;
import com.dyned.generalenglish.util.NotificationUtil;
import com.dyned.generalenglish.util.URLAddress;

public class GCMReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
	        handleRegistration(context, intent);
	    } else if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
	    	if (UserPreference.getInstance(context).isLoggedIn()) {
	    		handleMessage(context, intent);
			}
	    }
	}

	private void handleRegistration(Context context, Intent intent) {
	    String registration = intent.getStringExtra("registration_id");
	    if (intent.getStringExtra("error") != null) {
	        // Registration failed, should try again later.
		    Log.d("C2DM", "registration failed");
		    String error = intent.getStringExtra("error");
		    if(error == "SERVICE_NOT_AVAILABLE"){
		    	Log.d("C2DM", "SERVICE_NOT_AVAILABLE");
		    }else if(error == "ACCOUNT_MISSING"){
		    	Log.d("C2DM", "ACCOUNT_MISSING");
		    }else if(error == "AUTHENTICATION_FAILED"){
		    	Log.d("C2DM", "AUTHENTICATION_FAILED");
		    }else if(error == "TOO_MANY_REGISTRATIONS"){
		    	Log.d("C2DM", "TOO_MANY_REGISTRATIONS");
		    }else if(error == "INVALID_SENDER"){
		    	Log.d("C2DM", "INVALID_SENDER");
		    }else if(error == "PHONE_REGISTRATION_ERROR"){
		    	Log.d("C2DM", "PHONE_REGISTRATION_ERROR");
		    } else {
		    	Log.d("C2DM", "C2DM: " + error);
		    }
	    } else if (intent.getStringExtra("unregistered") != null) {
	        // unregistration done, new messages from the authorized sender will be rejected
	    	Log.i("C2DM", "unregistered");
	    } else if (registration != null) {
	    	System.out.println("gcm id: " + registration);
	    	UserPreference.getInstance(context).setGCMID(registration);
	    }
	}
	
	private void handleMessage(Context context, Intent intent)
	{
		System.out.println("data pushed");
		
		String conv = intent.getExtras().getString("conversation");
		if(conv == null) conv = "";
		String type = intent.getExtras().getString("type");
		String data = intent.getExtras().getString("data");
		
		if (conv.equals(GEApplication.app) && type.equals("ge_unit_open")) {
			try {
				GEPushNotification notif = new GEPushNotification();
				JSONObject obj = new JSONObject(data);
				notif.setConverstaion(conv);
				notif.setType(type);
				notif.setMessage(obj.getString("message"));
				notif.setConversationId(obj.getInt("conversation_id"));
				notif.setUnitId(obj.getInt("unit_id") - 36);
				notif.setLessonId(obj.getInt("lesson_id"));
				loadLatestHistory(notif, context);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void loadLatestHistory(final GEPushNotification notif, final Context c) {
		PostInternetTask task = new PostInternetTask(c, new InternetConnectionListener() {			
			public void onStart() {				
			}
			
			public void onDone(String str) {
				try {
					JSONObject result = new JSONObject(str);
					boolean status = result.getBoolean("status");
					if (status) {
						System.out.println("response update histroy on notif: " + str);
						List<GERecordHistory> historyList = GERecordHistory.parseHistory(str);
						UserPreference.getInstance(c).setHistory(historyList);
						NotificationUtil.getInstance().show(c, notif, NotifHandlerActivity.class);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			public void onConnectionError(String message) {
			}
		});
		task.addPair("app_key", UserPreference.getInstance(c).getAppKey());
		task.addPair("conversation", GEApplication.app);
		
		task.execute(URLAddress.URL_CONVERSATION_HISTORY);
	}
}
