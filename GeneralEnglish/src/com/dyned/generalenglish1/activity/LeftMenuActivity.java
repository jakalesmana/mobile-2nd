package com.dyned.generalenglish1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dyned.generalenglish1.R;

public class LeftMenuActivity extends Activity {

	private LinearLayout layoutMenu;
	private Animation animationSlideInLeft;
	private Animation animationSlideOutLeft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_left_menu);
		
		layoutMenu = (LinearLayout) findViewById(R.id.layoutMenu);
		animationSlideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		animationSlideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
		
		initLeftMenu();
		
		FrameLayout layoutBg = (FrameLayout) findViewById(R.id.layoutBgMenu);
		layoutBg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				layoutMenu.startAnimation(animationSlideOutLeft);
				layoutMenu.setVisibility(View.VISIBLE);
				
				new Handler().postDelayed(new Runnable() {
					public void run() {
						finish();
					}
				}, 500);
				
			}
		});
	}
	
	private void initLeftMenu() {
	animationSlideOutLeft.setAnimationListener(new AnimationListener() {
		public void onAnimationStart(Animation animation) {}
		public void onAnimationRepeat(Animation animation) {}

		public void onAnimationEnd(Animation animation) {
			layoutMenu.setVisibility(View.GONE);
		}
	});

	new Handler().postDelayed(new Runnable() {
		public void run() {
			layoutMenu.startAnimation(animationSlideInLeft);
			layoutMenu.setVisibility(View.VISIBLE);
		}
	}, 0);
}
}
