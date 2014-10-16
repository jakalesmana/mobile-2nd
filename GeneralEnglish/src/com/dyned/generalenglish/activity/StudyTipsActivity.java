package com.dyned.generalenglish.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.dyned.generalenglish.composite.StudyTipsAdapter;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.InternetTask;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish1.R;

public class StudyTipsActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_tips);
		setHeaderTitle("Study Tips");
		disableHomeButton();
		
		retrieveTips();
	}

	private void retrieveTips() {
		InternetTask getTask = new InternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(StudyTipsActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response load study tips: " + str);
				
				try {
					List<String> listTitle = new ArrayList<String>();
					List<String> listContent = new ArrayList<String>();
					JSONObject obj = new JSONObject(str);
					JSONArray arr = obj.getJSONArray("data");
					for (int i = 0; i < arr.length(); i++) {
						String title = arr.getJSONObject(i).getString("title");
						System.out.println("title count: val: " + title);
						String content = arr.getJSONObject(i).getString("content");
						listTitle.add(title);
						listContent.add(content);
					}
					
					System.out.println("title count: " + listTitle.size());
					
					StudyTipsAdapter adapter = new StudyTipsAdapter(StudyTipsActivity.this, listTitle, listContent);
					WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(adapter);
					((FloatingGroupExpandableListView)findViewById(R.id.lvStudy)).setAdapter(wrapperAdapter);
					dialog.dismiss();
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
					Toast.makeText(StudyTipsActivity.this, "Loading data failed, try again later.", Toast.LENGTH_SHORT).show();
				}

			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(StudyTipsActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		getTask.execute(URLAddress.STUDY_TIPS_URL);
	}
}
