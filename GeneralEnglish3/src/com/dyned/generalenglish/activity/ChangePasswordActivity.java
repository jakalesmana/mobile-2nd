package com.dyned.generalenglish.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.PostInternetTask;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish3.R;

public class ChangePasswordActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private EditText txtOldPassword;
	private EditText txtNewPassword;
	private EditText txtConfirmPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		setHeaderTitle("Change Password");
		disableHomeButton();
		
		txtOldPassword = (EditText) findViewById(R.id.txtOldPassword);
		txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
		txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (txtOldPassword.getText().toString().equals("")) {
					Toast.makeText(ChangePasswordActivity.this, "Your old password is empty.", Toast.LENGTH_SHORT).show();
					return;
				} 
				
				if (txtNewPassword.getText().toString().equals("")) {
					Toast.makeText(ChangePasswordActivity.this, "Your new password is empty.", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (!txtConfirmPassword.getText().toString().equals(txtNewPassword.getText().toString())) {
					Toast.makeText(ChangePasswordActivity.this, "Your new password doesn't match.", Toast.LENGTH_SHORT).show();
					return;
				}
				
				doChangePassword();
			}
		});
	}
	
	private void doChangePassword() {
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {	
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(ChangePasswordActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				try {
					JSONObject result = new JSONObject(str);
					boolean status = result.getBoolean("status");
					if (status) {
						String message = result.getString("message");
						Toast.makeText(ChangePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
						dialog.dismiss();
						finish();
					} else {
						String message = result.getString("error");
						Toast.makeText(ChangePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
					Toast.makeText(ChangePasswordActivity.this, "Change password failed, try again later.", Toast.LENGTH_SHORT).show();
				}
			}

			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(ChangePasswordActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("app_key", UserPreference.getInstance(this).getAppKey());
		task.addPair("old_pass", txtOldPassword.getText().toString());
		task.addPair("new_pass", txtNewPassword.getText().toString());
		
		task.execute(URLAddress.URL_CHANGE_PASSWORD);
	}
}
