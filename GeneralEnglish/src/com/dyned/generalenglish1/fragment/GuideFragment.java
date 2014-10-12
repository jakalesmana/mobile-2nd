package com.dyned.generalenglish1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyned.generalenglish1.R;

public class GuideFragment extends SherlockFragment {

	private int pos;
	
	public static GuideFragment getInstance(int pos){
		GuideFragment f = new GuideFragment();
		f.setPos(pos);
		return f;
	}
	
	public void setPos(int pos){
		this.pos = pos;
	}
	
	@SuppressLint("InflateParams") 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = LayoutInflater.from(getSherlockActivity()).inflate(R.layout.fragment_guide, null);
		if (pos == 0) {
			
		} else if (pos == 1) {
			
		} else if (pos == 2) {
			
		} else if (pos == 3) {
			
		}
		return v;
	}
}
