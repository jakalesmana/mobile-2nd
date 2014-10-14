package com.dyned.generalenglish1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;

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
		ImageView imgLogo = (ImageView) v.findViewById(R.id.imgGuideIcon);
		TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
		TextView txtContent = (TextView) v.findViewById(R.id.txtContent);
		
		if (pos == 0) {
			imgLogo.setImageResource(R.drawable.welcome_icn);
			txtTitle.setText("Welcome");
			txtContent.setText("Thank You for downloading " + GEApplication.appName + " Application.");
		} else if (pos == 1) {
			imgLogo.setImageResource(R.drawable.practice_icn);
			txtTitle.setText("Practice");
			txtContent.setText("Practice these lessons for a few minutes every day. Your English will improve!");
		} else if (pos == 2) {
			imgLogo.setImageResource(R.drawable.enjoy_icn);
			txtTitle.setText("Enjoy");
			txtContent.setText("Free bonus lessons at the end of units!");
		} else if (pos == 3) {
			imgLogo.setImageResource(R.drawable.track_icn);
			txtTitle.setText("Track");
			txtContent.setText("Visit your personal study dashboard at mobile.dyned.com to see your progress!");
		}
		return v;
	}
}
