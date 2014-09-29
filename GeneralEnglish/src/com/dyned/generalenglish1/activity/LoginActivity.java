package com.dyned.generalenglish1.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.GERecordHistory;
import com.dyned.generalenglish1.model.Profile;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.PostInternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class LoginActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private EditText txtEmail;
	private EditText txtPassword;
	private Button btnSubmit;
	private UserPreference pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		pref = UserPreference.getInstance(LoginActivity.this);
		
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		
		txtEmail.setText("jakaputralesmana@gmail.com");
		txtPassword.setText("jaka");
		
		btnSubmit.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				doLogin(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim());
			}
		});
		
		if (pref.isLoggedIn()) {
			goToHome();
		}
	}
	
	private void goToHome() {
		startActivity(new Intent(LoginActivity.this, HomeFragmentActivity.class));
		finish();
	}

	private void doLogin(String email, String password) {
		if (email.isEmpty()) {
			Toast.makeText(this, "Your email is empty.", Toast.LENGTH_SHORT).show();
		}
		if (password.isEmpty()) {
			Toast.makeText(this, "Your password is empty.", Toast.LENGTH_SHORT).show();
		}
		
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {		
				handler.post(new Runnable() {					
					public void run() {
						dialog = ProgressDialog.show(LoginActivity.this, "", "Signing in..");
					}
				});	
			}
			
			public void onDone(String str) {
				try {
					System.out.println("response login: " + str);
					JSONObject result = new JSONObject(str);
					boolean status = result.getBoolean("status");
					if (status) {
						Profile me = Profile.parseProfile(str);
						pref.setLoggedIn(true);
						pref.setAppKey(me.getAppKey());
						pref.setName(me.getName());
						pref.setAvatar(me.getAvatar());
						
						loadLatestHistory();
					} else {
						dialog.dismiss();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(LoginActivity.this, "Failed to sign in.", Toast.LENGTH_SHORT).show();
				}
			}
			
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LoginActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("email", email);
		task.addPair("password", password);
		
		task.execute(URLAddress.URL_LOGIN);
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
						dialog.dismiss();
						goToHome();
					} else {
						dialog.dismiss();
					}
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
				}
			}
			
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LoginActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("app_key", pref.getAppKey());
		task.addPair("conversation", GEApplication.app);
		
		task.execute(URLAddress.URL_CONVERSATION_HISTORY);
	}
}
