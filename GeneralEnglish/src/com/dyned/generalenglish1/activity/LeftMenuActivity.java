package com.dyned.generalenglish1.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.Country;
import com.dyned.generalenglish1.model.Language;
import com.dyned.generalenglish1.model.Profile;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.InternetTask;
import com.dyned.generalenglish1.tools.PostInternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class LeftMenuActivity extends Activity {

	private FrameLayout layoutMenu;
	private Animation animationSlideInLeft;
	private Animation animationSlideOutLeft;
	
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private ArrayList<Country> listCountry;
	private ArrayList<Language> listLanguage;
	
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
				finishing();
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
		public void onClick(final View v) {
			ScaleAnimation clickAnim = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
			clickAnim.setDuration(300);
			clickAnim.setInterpolator(new OvershootInterpolator());
			
			clickAnim.setAnimationListener(new AnimationListener() {
				public void onAnimationStart(Animation animation) {}
				public void onAnimationRepeat(Animation animation) {}
				public void onAnimationEnd(Animation animation) {
					handleClick(v);
				}
			});
			
			v.startAnimation(clickAnim);

		}
	};
	
	private void handleClick(View v) {
		if (v.getId() == R.id.imgMenu1) { //profile
			loadCountryAndLanguage();
		} else if (v.getId() == R.id.imgMenu2) { //study tips
			Intent i = new Intent(this, StudyTipsActivity.class);
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu3) { // badges
			Intent i = new Intent(this, BadgeActivity.class);
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu4) { // apps
			Intent i = new Intent(this, ApplicationActivity.class);
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu5) { // notification
			Intent i = new Intent(this, NotificationActivity.class);
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu6) { // support
			Intent i = new Intent(this, WebViewerActivity.class);
			i.putExtra("url_menu", URLAddress.SUPPORT_URL);
			i.putExtra("title", "Contact Us");
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu7) { // track record
			Intent i = new Intent(this, WebViewerActivity.class);
			i.putExtra("url_menu", URLAddress.ACCOUNT_URL + "?app_key=" + UserPreference.getInstance(this).getAppKey());
			i.putExtra("title", "Study Records");
			startActivity(i);
			finishing();
		} else if (v.getId() == R.id.imgMenu8) { //logout
			showConfirmation();
		}
	}
	
	private void showConfirmation() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("");
		alertDialog.setMessage("Are you sure you want to logout?");
				
		alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
			}
		});
		
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {				
				doLogout();
			}
		});
		
		alertDialog.show();
	}
	
	private void doLogout() {
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(LeftMenuActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response logout: " + str);
				
				UserPreference.getInstance(LeftMenuActivity.this).logout();
				
				dialog.dismiss();
				
				Intent i = new Intent();
				i.putExtra("logout", true);
				setResult(RESULT_OK, i);
				finish();
			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LeftMenuActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("app_key", UserPreference.getInstance(this).getAppKey());
		task.execute(URLAddress.URL_LOGOUT);
	}

	private void loadCountryAndLanguage() {
		InternetTask getTask = new InternetTask(this, new InternetConnectionListener() {
			public void onStart() {
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(LeftMenuActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response load country: " + str);
				listCountry = (ArrayList<Country>) Country.parseCountries(str);
				loadLanguage();
			}
			
			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LeftMenuActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		getTask.execute(URLAddress.COUNTRY_URL);
	}
	
	private void loadLanguage() {
		InternetTask getTask = new InternetTask(this, new InternetConnectionListener() {
			public void onStart() {
			 
			}
			
			public void onDone(String str) {
				System.out.println("response load language: " + str);
				listLanguage = (ArrayList<Language>) Language.parseLanguages(str);
				loadProfile();
			}

			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LeftMenuActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		getTask.execute(URLAddress.LANGUAGE_URL);
	}
	
	private void loadProfile() {
		PostInternetTask postTask = new PostInternetTask(this, new InternetConnectionListener() {
			public void onStart() {
			}
			
			public void onDone(String str) {
				System.out.println("response load profile: " + str);
				Profile p = Profile.parseProfile(str);
				dialog.dismiss();
				
				if (p!=null) {
					Intent i = new Intent(LeftMenuActivity.this, ProfileActivity.class);
					i.putExtra("countries", listCountry);
					i.putExtra("languages", listLanguage);
					i.putExtra("profile", p);
					startActivity(i);
					finishing();
				} else {
					Toast.makeText(LeftMenuActivity.this, "Can not profile" + ", try again later.", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(LeftMenuActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		postTask.addPair("app_key", UserPreference.getInstance(this).getAppKey());
		postTask.execute(URLAddress.PROFILE_URL);
	}
	
	private void finishing() {
		layoutMenu.startAnimation(animationSlideOutLeft);
		layoutMenu.setVisibility(View.VISIBLE);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				finish();
			}
		}, 500);
	}
}
