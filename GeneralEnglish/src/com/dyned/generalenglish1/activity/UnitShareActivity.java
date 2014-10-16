package com.dyned.generalenglish1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEMainMenu;
import com.dyned.generalenglish1.util.AppUtil;

public class UnitShareActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit_share);
		
		GEMainMenu currentUnit = LessonManager.getInstance().getCurrentUnit();
		final int unitNo = Integer.parseInt(currentUnit.getCode().substring(1));
		
		ImageView imgUnit = (ImageView) findViewById(R.id.imgUnitNo);
		TextView txtUnit = (TextView) findViewById(R.id.txtUnitNo);
		FrameLayout layoutShare = (FrameLayout) findViewById(R.id.layoutShare);
		RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
		container.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		txtUnit.setText("Unit " + unitNo);
		
		int resId = AppUtil.getImageResId(this, "unit_" + unitNo);
		imgUnit.setImageResource(resId);
		
		layoutShare.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showShare(unitNo);
			}
		});
	}
	
	private void showShare(int unitNo) {
		AppUtil.showChooserIntent(this, "Unit Completed!", "Yayy! I just completed Unit " + unitNo + " of " + GEApplication.getGEContent().getAppName() + "!");
	}
}
