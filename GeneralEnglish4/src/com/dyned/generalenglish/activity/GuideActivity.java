package com.dyned.generalenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dyned.generalenglish.composite.GuideAdapter;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish4.R;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.viewpagerindicator.CirclePageIndicator;

public class GuideActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		getSupportActionBar().hide();
		
		JazzyViewPager pager = (JazzyViewPager) findViewById(R.id.container);
		pager.setTransitionEffect(TransitionEffect.Standard);
		pager.setFadeEnabled(true);
		pager.setAdapter(new GuideAdapter(getSupportFragmentManager(), pager));
		pager.setPageMargin(0);
		
		CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.vpi);
		indicator.setViewPager(pager);
		indicator.setFillColor(0xff30d5c7);
		indicator.setStrokeColor(0xff30d5c7);
		
		Button btnSkip = (Button) findViewById(R.id.btnSkip);
		btnSkip.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				UserPreference.getInstance(GuideActivity.this).setGuideOpened(true);
				Intent i = new Intent(GuideActivity.this, HomeFragmentActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
