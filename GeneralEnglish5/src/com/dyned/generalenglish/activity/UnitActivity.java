package com.dyned.generalenglish.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dyned.generalenglish.component.LessonItemListView;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.model.GELesson;
import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish5.R;

public class UnitActivity extends BaseActivity {

	private FrameLayout layoutOption;
	private GEMainMenu unit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit);
		AppUtil.AddActivityHistory(this);
		
		unit = (GEMainMenu) getIntent().getSerializableExtra("GEunit");
		
		ScrollView svUnit = (ScrollView) findViewById(R.id.svUnit);
		ImageView imgUnit = (ImageView) findViewById(R.id.imgUnit);
		FrameLayout layoutTitle = (FrameLayout) findViewById(R.id.layoutTitle);
		TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
		TextView txtSubtitle = (TextView) findViewById(R.id.txtSubtitle);
		
		updateImageUnit(unit.getCode(), imgUnit);
		txtTitle.setText(unit.getTitle().split(":")[0]);
		txtSubtitle.setText(unit.getDesc());
		
		int appHeight = AppUtil.GetAppScreenHeight(this);
		int titleHeight = appHeight / 9 * 4;
		
		layoutTitle.getLayoutParams().height = titleHeight;
		
		layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		updateOptions();
		
		doSmoothScroll(svUnit);
	}
	
	private void updateOptions() {
		int appHeight = AppUtil.GetAppScreenHeight(this);
		int optionsHeight = appHeight / 9 * 5;
		
		layoutOption.removeAllViews();
		layoutOption.addView(new LessonItemListView(this, unit, optionsHeight, unit.getListLesson(), new OnClickListener() {
			public void onClick(View v) {
				GELesson lesson = (GELesson) v.getTag();
				LessonManager.getInstance().startLesson(UnitActivity.this, unit, lesson);
			}
		}));
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateOptions();
	}

	private void updateImageUnit(String unitCode, ImageView imgUnit) {
		String unitPos = unitCode.substring(1);
		int resId = AppUtil.getImageResId(this, "unit_" + unitPos);
		imgUnit.setImageResource(resId);
	}

	private void doSmoothScroll(final ScrollView svUnit) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				new CountDownTimer(1000, 10) {
					public void onTick(long millisUntilFinished) {             
						svUnit.smoothScrollBy(0, 5);   
					}          

					public void onFinish() {}      
				}.start(); 
			}
		}, 600);
	}
	
}
