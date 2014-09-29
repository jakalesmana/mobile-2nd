package com.dyned.generalenglish1.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dyned.generalenglish1.R;

public class BaseActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private TextView txtHeaderTitle;
	private boolean backMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.activity_enter, R.anim.activity_leave);
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
		
		ImageView imgMenu = (ImageView) findViewById(R.id.imgBack);
		imgMenu.setOnClickListener(clickHandler);
	}
	
	protected void setHeaderTitle(String title){
		txtHeaderTitle.setText(title);
	}
	
	protected void enabeBackMenu(){
		backMenu = true;
	}
	
	private OnClickListener clickHandler = new OnClickListener() {		
		public void onClick(View v) {
			if (backMenu) {
				Intent i = new Intent(BaseActivity.this, LeftMenuActivity.class);
				startActivity(i);
			} else {
				
			}
		}
	};
	
	@Override
	public void finish() {
		super.finish();
//		overridePendingTransition(R.anim.activity_leave, R.anim.activity_enter);
	};
}
