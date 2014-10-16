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
import com.dyned.generalenglish.composite.ApplicationAdapter;
import com.dyned.generalenglish.model.GEApplicationObject;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.InternetTask;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish4.R;

public class ApplicationActivity extends BaseActivity {

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_application);
		setHeaderTitle("DynEd Applications");
		disableHomeButton();
		
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
				
				try {
					List<GEApplicationObject> apps = new ArrayList<GEApplicationObject>();
					JSONObject obj = new JSONObject(str);
					JSONArray arr = obj.getJSONArray("data");
					for (int i = 0; i < arr.length(); i++) {
						int id = arr.getJSONObject(i).getInt("id");
						String name = arr.getJSONObject(i).getString("name");
						String pckg = arr.getJSONObject(i).getString("package");
						String images = arr.getJSONObject(i).getString("images");
						String icon = arr.getJSONObject(i).getString("thumbnail");
						String link = arr.getJSONObject(i).getString("direct_link");
						
						GEApplicationObject app = new GEApplicationObject();
						app.setId(id);
						app.setAppName(name);
						app.setAppIcon(icon);
						app.setAppImage(images);
						app.setDirectLink(link);
						app.setPackageName(pckg);
						apps.add(app);
					}
				
					ListView lvApp = (ListView) findViewById(R.id.lvApplication);
					lvApp.setAdapter(new ApplicationAdapter(ApplicationActivity.this, apps));
					
					dialog.dismiss();
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
					Toast.makeText(ApplicationActivity.this, "Loading data failed, try again later.", Toast.LENGTH_SHORT).show();
				}
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
