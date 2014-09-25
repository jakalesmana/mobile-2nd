package com.dyned.generalenglish1.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dyned.generalenglish1.R;

public class BaseActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private TextView txtHeaderTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
	}
	
	private void setupActionBar(){
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.header, null);
		
		actionBar = getSupportActionBar();
//		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayShowHomeEnabled(true);
//		actionBar.setHomeButtonEnabled(true);
		actionBar.setCustomView(v);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		txtHeaderTitle = (TextView) v.findViewById(R.id.txtHeaderTitle);
	}
	
	protected void setHeaderTitle(String title){
		txtHeaderTitle.setText(title);
	}
}
