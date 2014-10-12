package com.dyned.generalenglish1.activity;

import android.os.Bundle;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.composite.GuideAdapter;
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
		pager.setPageMargin(15);
		
		CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.vpi);
		indicator.setViewPager(pager);
	}
}
