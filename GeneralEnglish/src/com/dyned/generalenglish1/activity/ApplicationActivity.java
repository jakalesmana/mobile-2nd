package com.dyned.generalenglish1.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.InternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class ApplicationActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_application);
		
		loadApplication();
	}

	private void loadApplication() {
		InternetTask getTask = new InternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(ApplicationActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response load applications: " + str);
				
//				try {
					dialog.dismiss();
//				} catch (JSONException e) {
//					e.printStackTrace();
//					dialog.dismiss();
//					Toast.makeText(ApplicationActivity.this, "Loading data failed, try again later.", Toast.LENGTH_SHORT).show();
//				}
			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(ApplicationActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		getTask.execute(URLAddress.APPLICATION_URL + "?id=2&type=" + GEApplication.app);
	}
}
