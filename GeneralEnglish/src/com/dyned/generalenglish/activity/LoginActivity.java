package com.dyned.generalenglish.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.gcm.MDAGCMRegistrar;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GERecordHistory;
import com.dyned.generalenglish.model.Profile;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.PostInternetTask;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish1.R;

public class LoginActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private EditText txtEmail;
	private EditText txtPassword;
	private Button btnSubmit;
	private UserPreference pref;
	private TextView txtSignUp;
	private TextView txtForgotPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		disableMenu();
		disableHomeButton();
		enableTopLogo();
		
		pref = UserPreference.getInstance(LoginActivity.this);
		
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		txtSignUp = (TextView) findViewById(R.id.txtSignUp);
		txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
		
		txtEmail.setText("jakaputralesmana@gmail.com");
		txtPassword.setText("jaka");
		
		btnSubmit.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				doLogin(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim());
			}
		});
		
		txtSignUp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(i);
			}
		});
		
		txtForgotPassword.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				showForgotPopup();
			}
		});
		
		if (pref.isLoggedIn()) {
			goToHome();
		}
	}
	
	@SuppressLint("InflateParams") 
	private void showForgotPopup() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.popup_forgot_password, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(promptsView);

		final EditText txtEmail = (EditText) promptsView.findViewById(R.id.txtEmail);

		alertDialogBuilder.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
			    doForgotPassword(txtEmail.getText().toString());
			}})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				dialog.dismiss();
			}});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	private void goToHome() {
		startActivity(new Intent(LoginActivity.this, HomeFragmentActivity.class));
		finish();
	}
	
	private void doForgotPassword(String email) {
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(LoginActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response forgot password: " + str);
				
				try {
					JSONObject obj = new JSONObject(str);
					boolean status = obj.getBoolean("status");
					
					if (status) {
						if (obj.has("message")) {
							String message = obj.getString("message");
							Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
						}
					} else {
						if (obj.has("error")) {
							String error = obj.getString("error");
							Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
						}
					}
					
					dialog.dismiss();
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
					Toast.makeText(LoginActivity.this, "Loading data failed, try again later.", Toast.LENGTH_SHORT).show();
				}

			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LoginActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("email", email);
		task.execute(URLAddress.URL_FORGOT_PASSWORD);
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
						MDAGCMRegistrar.sendDeviceIdToServer(LoginActivity.this, pref.getGCMID());
					} else {
						dialog.dismiss();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(LoginActivity.this, "Failed to sign in.", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
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
						
						if (!pref.isGuideOpened()) {
							startActivity(new Intent(LoginActivity.this, GuideActivity.class));
							finish();
						} else {
							goToHome();
						}
						
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
