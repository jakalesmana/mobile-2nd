package com.dyned.generalenglish.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.composite.NotifAdapter;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GENotification;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.PostInternetTask;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish4.R;

public class NotificationActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		setHeaderTitle("Notifications");
		disableHomeButton();
		
		loadNotif();
	}

	private void loadNotif() {
		PostInternetTask postTask = new PostInternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(NotificationActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response load notif: " + str);
				
				
				
				try {
					List<GENotification> listNotif = new ArrayList<GENotification>();
					JSONObject obj = new JSONObject(str);
					JSONArray arr = obj.getJSONArray("data");
					for (int i = 0; i < arr.length(); i++) {
						String title = arr.getJSONObject(i).getString("message");
						long date = arr.getJSONObject(i).getLong("time");
						GENotification notif = new GENotification(title, date);
						listNotif.add(notif);
					}
					
					ListView lvNotif = (ListView) findViewById(R.id.lvNotification);
					lvNotif.setAdapter(new NotifAdapter(NotificationActivity.this, listNotif));
					
					dialog.dismiss();
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
					Toast.makeText(NotificationActivity.this, "Loading data failed, try again later.", Toast.LENGTH_SHORT).show();
				}

			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(NotificationActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		postTask.addPair("conversation", GEApplication.app);
		postTask.addPair("app_key", UserPreference.getInstance(this).getAppKey());
		postTask.execute(URLAddress.NOTIFICATION_URL);
	}
}
