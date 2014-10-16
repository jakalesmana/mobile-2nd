package com.dyned.generalenglish.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish3.R;

public class BaseActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private TextView txtHeaderTitle;
	private boolean backMenu;
	private FrameLayout imgMenu, layoutHome;
	private ImageView imgTopLogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
		
		setupActionBar();
	}
	
	@SuppressLint("InflateParams")
	private void setupActionBar(){
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.header, null);
		
		actionBar = getSupportActionBar();
		actionBar.setCustomView(v);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		txtHeaderTitle = (TextView) v.findViewById(R.id.txtHeaderTitle);
		imgTopLogo = (ImageView) v.findViewById(R.id.imgTopLogo);
		
		imgMenu = (FrameLayout) findViewById(R.id.layoutBack);
		imgMenu.setOnClickListener(clickHandler);
		
		layoutHome = (FrameLayout) findViewById(R.id.layoutHome);
		layoutHome.setOnClickListener(clickHandler);
	}
	
	protected void setHeaderTitle(String title){
		txtHeaderTitle.setText(title);
	}
	
	protected void enableTopLogo(){
		imgTopLogo.setVisibility(View.VISIBLE);
	}
	
	protected void enableBackMenu(){
		backMenu = true;
		((ImageView)imgMenu.getChildAt(0)).setImageResource(R.drawable.slide_menu_icn);
	}
	
	protected void disableMenu(){
		imgMenu.setVisibility(View.GONE);
	}
	
	protected void disableHomeButton(){
		layoutHome.setVisibility(View.GONE);
	}
	
	private OnClickListener clickHandler = new OnClickListener() {		
		public void onClick(View v) {
			if (v == imgMenu) {
				if (backMenu) {
					Intent i = new Intent(BaseActivity.this, LeftMenuActivity.class);
					startActivityForResult(i, 0);
				} else {
					finish();
				}
			} else if (v == layoutHome) {
				AppUtil.ClearActivityHistory();
			}
		}
	};
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 0 && resultCode == RESULT_OK) {
			
			if(data == null) return;
				
			if (data.getBooleanExtra("logout", false)) {
				Intent intent = new Intent(this, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); 
				startActivity(intent);
				finish();
			}
		}
		
		
	}
}
