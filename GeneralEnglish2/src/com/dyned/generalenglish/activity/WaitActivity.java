package com.dyned.generalenglish.activity;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.model.GE;
import com.dyned.generalenglish.model.GELesson;
import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish2.R;

public class WaitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait);
		
		GEMainMenu currentUnit = LessonManager.getInstance().getCurrentUnit();
		GELesson currentLesson = LessonManager.getInstance().getCurrentLesson();
		
		ImageView imgWait = (ImageView) findViewById(R.id.imgLesson);
		TextView txtWait = (TextView) findViewById(R.id.txtLesson);
		
		GEMainMenu nextUnit = null;
		GELesson nextLesson = null;
		GE content = GEApplication.getGEContent();
		List<GEMainMenu> menus = content.getListMenu();
		for (int i = 0; i < menus.size(); i++) {
			GEMainMenu geunit = menus.get(i);
			if (geunit.getCode().equals(currentUnit.getCode())) {
				List<GELesson> lessons = geunit.getListLesson();
				for (int j = 0; j < lessons.size(); j++) {
					GELesson gelesson = lessons.get(j);
					if (gelesson.getCode().equals(currentLesson.getCode())) {
						try {
							if (lessons.get(j + 1).getCode().equalsIgnoreCase("LBNS")) {
								//if lesson bonus, got to next lesson (first lesson of next unit)
								nextUnit = menus.get(i + 1);
								nextLesson = menus.get(i + 1).getListLesson().get(0);
							} else {
								//next lesson of unit
								nextUnit = geunit;
								nextLesson = lessons.get(j + 1);
							}
						} catch (IndexOutOfBoundsException e) {
							//reach last lesson of unit
							try {
								//first lesson of next unit
								nextUnit = menus.get(i + 1);
								nextLesson = menus.get(i + 1).getListLesson().get(0);
							} catch (IndexOutOfBoundsException e2) {
								//last unit
								finish();
							}
							
						}
					}
				}
			}
		}
		
		txtWait.setText(nextUnit.getTitle().split(":")[0] + " " + nextLesson.getTitle() + " in");
		imgWait.setImageResource(AppUtil.getImageResId(this, nextLesson.getImage().toLowerCase(Locale.getDefault()).split("\\.")[0] + "_img"));
		
		imgWait.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
