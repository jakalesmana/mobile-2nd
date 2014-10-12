package com.dyned.generalenglish1.composite;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dyned.generalenglish1.fragment.GuideFragment;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;

public class GuideAdapter extends FragmentStatePagerAdapter {

	private JazzyViewPager mJazzy;
	public GuideAdapter(FragmentManager fm, JazzyViewPager pager) {
		super(fm);
		mJazzy = pager;
	}

	@Override
	public GuideFragment getItem(int pos) {
		GuideFragment f = GuideFragment.getInstance(pos);
		mJazzy.setObjectForPosition(f, pos);
		return f;
	}

	@Override
	public int getCount() {
		return 4;
	}

}
