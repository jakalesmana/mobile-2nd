package com.dyned.generalenglish1.component;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.util.AppUtil;

@SuppressLint("ViewConstructor")
public class HashtagItemListView extends LinearLayout {

	public interface OptionsChoosenHandler {
		void onChoosen(String word);
	}
	
	private OptionsChoosenHandler chooseHandler;
	private Context mContext;
	
	public HashtagItemListView(final Context context, List<String> aTags, OptionsChoosenHandler handler) {
		super(context);
		mContext = context;
		chooseHandler = handler;
		float density = context.getResources().getDisplayMetrics().density;
		int padding = (int) (5 * density);
		int screenWidth = AppUtil.GetScreenWidth(mContext);
		int maxWidth = screenWidth - 2 * padding; 
		
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		LinearLayout row = new LinearLayout(mContext);
		LayoutParams rowParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int rowMargin = (int) (5 * density);
		rowParams.setMargins(0, rowMargin, 0, 0);
		row.setOrientation(LinearLayout.HORIZONTAL);
		row.setLayoutParams(rowParams);
		
		for (int i = 0; i < aTags.size(); i++) {
			final String tag = aTags.get(i);
			if(!tag.trim().equals("")){
				Button hashtag = new Button(context);
				LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				buttonParams.setMargins(0, 0, (int) (5 * density), 0);
				int btnPadding = (int) (5 * density);
				hashtag.setPadding(btnPadding, btnPadding, btnPadding, btnPadding);
				
				hashtag.setText(tag);
				hashtag.setTextColor(Color.WHITE);
				hashtag.setLayoutParams(buttonParams);
				hashtag.setBackgroundResource(R.drawable.bg_btn_green_noborder);
				
				hashtag.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						chooseHandler.onChoosen(tag);
						v.setVisibility(View.INVISIBLE);
					}
				});
				
				row.addView(hashtag);
				hashtag.measure(0, 0);
				row.measure(0, 0);
				
				if(row.getMeasuredWidth() > maxWidth){
					row.removeViewAt(row.getChildCount()-1);
					addView(row);
					row = new LinearLayout(mContext);
					row.setOrientation(LinearLayout.HORIZONTAL);
					row.setLayoutParams(rowParams);
					row.addView(hashtag);
				}
			}
		}
		
		if(row.getChildCount() > 0){
			addView(row);	
		}
		
		if(getChildCount() == 1 && ((LinearLayout)getChildAt(0)).getChildCount() ==1){
			setVisibility(View.GONE);
		}
	}
}
