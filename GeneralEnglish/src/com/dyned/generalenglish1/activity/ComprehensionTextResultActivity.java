package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.composite.ResultAdapter;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.model.GEMainMenu;

public class ComprehensionTextResultActivity extends BaseActivity {

	private LessonManager lessonMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comprehension_result);
		lessonMgr = LessonManager.getInstance();
		
		GEMainMenu unit = lessonMgr.getCurrentUnit();
		GELesson lesson = lessonMgr.getCurrentLesson();
		
		TextView txtUnit = (TextView) findViewById(R.id.txtUnit);
		TextView txtLesson = (TextView) findViewById(R.id.txtLesson);
		Button btnTry = (Button) findViewById(R.id.btnTry);
		Button btnGrammar = (Button) findViewById(R.id.btnGrammar);
		ListView lvResult = (ListView) findViewById(R.id.lvResult);
		
		txtUnit.setText(unit.getTitle());
		txtLesson.setText(lesson.getComprehension().getTitle());
		lvResult.setAdapter(new ResultAdapter(this, lesson.getComprehension().getListQuestion(), lessonMgr.getCurrentComprehensionAnswer()));
		
		btnTry.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lessonMgr.repeatComprehension(ComprehensionTextResultActivity.this);
				finish();
			}
		});
		
		btnGrammar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lessonMgr.prepareGrammar(ComprehensionTextResultActivity.this);
			}
		});
	}
	
	@Override
	public void onBackPressed() {}
}
