package com.dyned.generalenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dyned.generalenglish6.R;

public class GrammarResultStatusActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(0, 0);
		
		boolean pass = getIntent().getBooleanExtra("isPass", false);
		if (pass) {
			setContentView(R.layout.popup_true);
		} else {
			setContentView(R.layout.popup_false);
		}
		
		Button btnCheck = (Button) findViewById(R.id.btnCheck);
		btnCheck.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(GrammarResultStatusActivity.this, GrammarResultActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		Animation zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
		findViewById(R.id.layoutStatus).startAnimation(zoom);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
				scale.setDuration(500);
				scale.setInterpolator(new OvershootInterpolator());
				findViewById(R.id.imgStatus).startAnimation(scale);
			}
		}, 400);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		float density = getResources().getDisplayMetrics().density;
		ImageView imgStatus = (ImageView) findViewById(R.id.imgStatus);
		
		LinearLayout layoutStatus = (LinearLayout) findViewById(R.id.layoutStatus);
		layoutStatus.setPadding(0, (int)((imgStatus.getHeight() / 2) - (20 * density)), 0, 0);
		
		((FrameLayout.LayoutParams)layoutStatus.getLayoutParams()).topMargin = (imgStatus.getHeight() / 2);
	}
	
//	@SuppressWarnings("deprecation")
//	@SuppressLint("InflateParams")
//	private void showAnswerStatus(View parent, boolean pass) {
//		View v = LayoutInflater.from(this).inflate(R.layout.popup_true, null);
//		PopupWindow mPopupWindow = new PopupWindow(v, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
//		mPopupWindow.setOutsideTouchable(false);
//		mPopupWindow.setFocusable(true);
//		mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
//	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(0, R.anim.zoom_out);
	}
	
	@Override
	public void onBackPressed() {}
}
