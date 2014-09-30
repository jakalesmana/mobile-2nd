package com.dyned.generalenglish1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dyned.generalenglish1.R;

public class LeftMenuActivity extends Activity {

	private FrameLayout layoutMenu;
	private Animation animationSlideInLeft;
	private Animation animationSlideOutLeft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_left_menu);
		
		layoutMenu = (FrameLayout) findViewById(R.id.layoutMenu);
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
		
		animationSlideInLeft.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {}
			public void onAnimationRepeat(Animation animation) {}
	
			public void onAnimationEnd(Animation animation) {
				showAllMenu();
			}
		});
	
		new Handler().postDelayed(new Runnable() {
			public void run() {
				layoutMenu.startAnimation(animationSlideInLeft);
				layoutMenu.setVisibility(View.VISIBLE);
			}
		}, 0);
	}
	
	private void showAllMenu() {
		final ScaleAnimation scale1 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale1.setDuration(300);
		scale1.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale2 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale2.setDuration(300);
		scale2.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale3 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale3.setDuration(300);
		scale3.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale4 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale4.setDuration(300);
		scale4.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale5 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale5.setDuration(300);
		scale5.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale6 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale6.setDuration(300);
		scale6.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale7 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale7.setDuration(300);
		scale7.setInterpolator(new OvershootInterpolator());
		
		final ScaleAnimation scale8 = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
		scale8.setDuration(300);
		scale8.setInterpolator(new OvershootInterpolator());
		
		final ImageView imgMenu1 = (ImageView) findViewById(R.id.imgMenu1);
		final ImageView imgMenu2 = (ImageView) findViewById(R.id.imgMenu2);
		final ImageView imgMenu3 = (ImageView) findViewById(R.id.imgMenu3);
		final ImageView imgMenu4 = (ImageView) findViewById(R.id.imgMenu4);
		final ImageView imgMenu5 = (ImageView) findViewById(R.id.imgMenu5);
		final ImageView imgMenu6 = (ImageView) findViewById(R.id.imgMenu6);
		final ImageView imgMenu7 = (ImageView) findViewById(R.id.imgMenu7);
		final ImageView imgMenu8 = (ImageView) findViewById(R.id.imgMenu8);
		imgMenu1.setOnClickListener(onMenuClick);
		imgMenu2.setOnClickListener(onMenuClick);
		imgMenu3.setOnClickListener(onMenuClick);
		imgMenu4.setOnClickListener(onMenuClick);
		imgMenu5.setOnClickListener(onMenuClick);
		imgMenu6.setOnClickListener(onMenuClick);
		imgMenu7.setOnClickListener(onMenuClick);
		imgMenu8.setOnClickListener(onMenuClick);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu1.setVisibility(View.VISIBLE);
				imgMenu1.startAnimation(scale1);
			}
		}, 50);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu2.setVisibility(View.VISIBLE);
				imgMenu2.startAnimation(scale2);
			}
		}, 100);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {				
				imgMenu3.setVisibility(View.VISIBLE);
				imgMenu3.startAnimation(scale3);
			}
		}, 150);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {	
				imgMenu4.setVisibility(View.VISIBLE);
				imgMenu4.startAnimation(scale4);
			}
		}, 200);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu5.setVisibility(View.VISIBLE);
				imgMenu5.startAnimation(scale5);
			}
		}, 250);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu6.setVisibility(View.VISIBLE);
				imgMenu6.startAnimation(scale6);
			}
		}, 300);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu7.setVisibility(View.VISIBLE);
				imgMenu7.startAnimation(scale7);
			}
		}, 350);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {		
				imgMenu8.setVisibility(View.VISIBLE);
				imgMenu8.startAnimation(scale8);
			}
		}, 400);
	}
	
	private OnClickListener onMenuClick = new OnClickListener() {
		public void onClick(View v) {
			ScaleAnimation clickAnim = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
			clickAnim.setDuration(300);
			clickAnim.setInterpolator(new OvershootInterpolator());
			v.startAnimation(clickAnim);
		}
	};
}
