package com.dyned.generalenglish.component;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyned.generalenglish5.R;

public class AnswerItemListView extends LinearLayout {

	public interface AnswerHandler{
		void onAnswer(String answer);
	}
	
	private AnswerHandler answerHandler;
	private List<String> options;
	private List<View> items;
	private float density;
	
	public AnswerItemListView(Context context, int spaceLeft, List<String> list, AnswerHandler onAnswer) {
		super(context);
		options = list;
		items = new ArrayList<View>();
		this.answerHandler = onAnswer;
		setOrientation(LinearLayout.VERTICAL);
		density = getContext().getResources().getDisplayMetrics().density;
		
		for (int i = 0; i < options.size(); i++) {
			OptionItem item = new OptionItem(context, options.get(i), i);
			item.setTag(options.get(i));
			item.setOnClickListener(clickHandler);
			items.add(item);
			addView(item);
			
			item.getLayoutParams().height = (int) ((spaceLeft / 3) - (10 * density));
			
			addDivider();
		}		
	}
	
	private void addDivider() {
		FrameLayout divider = new FrameLayout(getContext());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) (1 * density));
		params.setMargins((int) (10 * density), 0, (int) (10 * density), 0);
		divider.setLayoutParams(params);
		divider.setBackgroundColor(getContext().getResources().getColor(R.color.GEGrey));
		addView(divider);
	}
	
	private OnClickListener clickHandler = new OnClickListener() {		
		public void onClick(View v) {
			for (int i = 0; i < items.size(); i++) {
				items.get(i).setSelected(false);
			}
			
			v.setSelected(true);
			if(answerHandler!=null){
				answerHandler.onAnswer((String)v.getTag());
			}
		}
	};

	private class OptionItem extends FrameLayout {
		public OptionItem(Context context, String option, int pos) {
			super(context);
			
			View v = LayoutInflater.from(context).inflate(R.layout.answer_item, this, false);
			TextView index = (TextView) v.findViewById(R.id.txtIndex);
			TextView answer = (TextView) v.findViewById(R.id.txtAnswer);
			
			index.setText(getIndex(pos));
			answer.setText(option);
			
			addView(v);
		}
	}
	
	private String getIndex(int pos){
		switch (pos) {
			case 0: return "A";
			case 1: return "B";
			case 2: return "C";
			case 3: return "D";
			case 4: return "E";
			case 5: return "F";
			case 6: return "G";
			case 7: return "H";
			case 8: return "I";
			case 9: return "J";
			case 10: return "K";
			default: return "";
		}
	}
}
