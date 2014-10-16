package com.dyned.generalenglish.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GELesson;
import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish.model.GERecordHistory;
import com.dyned.generalenglish.model.GERecordLesson;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish6.R;

public class LessonItemListView extends LinearLayout {
	
	private List<GELesson> options;
	private List<View> items;
	private float density; 
	private GEMainMenu currentUnit;
	
	public LessonItemListView(Context context, GEMainMenu unit, int spaceLeft, List<GELesson> list, OnClickListener clickHandler) {
		super(context);
		options = list;
		items = new ArrayList<View>();
		setOrientation(LinearLayout.VERTICAL);
		density = getContext().getResources().getDisplayMetrics().density;
		currentUnit = unit;
		
		for (int i = 0; i < options.size(); i++) {
			
			UnitLessonItem item;
			if (isOpen(options.get(i))) {
				item = new UnitLessonItem(context, options.get(i), i, true);
				item.setTag(options.get(i));
				item.setOnClickListener(clickHandler);
			} else {
				item = new UnitLessonItem(context, options.get(i), i, false);
			}
			
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


	private class UnitLessonItem extends FrameLayout {
		public UnitLessonItem(Context context, GELesson lesson, int pos, boolean available) {
			super(context);
			
			View v = LayoutInflater.from(context).inflate(R.layout.unit_item, this, false);
			CircularImageView imgLesson = (CircularImageView) v.findViewById(R.id.imgLesson);
			TextView txtLesson = (TextView) v.findViewById(R.id.txtLesson);

			imgLesson.setImageResource(AppUtil.getImageResId(getContext(), lesson.getImage().toLowerCase(Locale.getDefault()).split("\\.")[0] + "_img"));
			txtLesson.setText(lesson.getTitle());
			
			if (!available) {
				v.setBackgroundColor(Color.LTGRAY);
				txtLesson.setTextColor(Color.parseColor("#989898"));
			}
			
			addView(v);
			
			imgLesson.getLayoutParams().width = AppUtil.GetScreenWidth(getContext()) / 5;
			imgLesson.getLayoutParams().height = AppUtil.GetScreenWidth(getContext()) / 5;
		}
	}
	
	private boolean isOpen(GELesson lesson){
		List<GERecordHistory> histories = UserPreference.getInstance(getContext()).getHistory();
		for (int i = 0; i < histories.size(); i++) {
			String unitCode = histories.get(i).getUnit();
			List<GERecordLesson> listRecordLesson = histories.get(i).getRecords();
			for (int j = 0; j < listRecordLesson.size(); j++) {
				if (currentUnit.getCode().equals(unitCode) && 
						lesson.getCode().equals(listRecordLesson.get(j).getLessonCode())) {
					if (!listRecordLesson.get(j).getStatus().equals("waiting")) {
						return true;
					}
					
				}
			}
		}
		return false;
	}

}
