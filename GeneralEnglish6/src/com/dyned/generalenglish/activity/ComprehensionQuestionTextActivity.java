package com.dyned.generalenglish.activity;

import java.util.List;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dyned.generalenglish.component.AnswerItemListView;
import com.dyned.generalenglish.component.AnswerItemListView.AnswerHandler;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.model.GEQuestion;
import com.dyned.generalenglish.model.SerializedNameValuePair;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish.util.StringUtil;
import com.dyned.generalenglish6.R;

public class ComprehensionQuestionTextActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	private GEQuestion question;
	private boolean last;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_text);
		AppUtil.AddActivityHistory(this);
		
		lessonMgr = LessonManager.getInstance();
		
		question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		last = getIntent().getBooleanExtra("lastQuestion", false);
				
		FrameLayout layoutTitle = (FrameLayout) findViewById(R.id.layoutTitle);
		TextView txtQuestion = (TextView) findViewById(R.id.txtQuestion);
		
		txtQuestion.setText(question.getQuestion());
		
		int appHeight = AppUtil.GetAppScreenHeight(this);
		int titleHeight = appHeight / 9 * 4;
		int optionsHeight = appHeight / 9 * 5;
		
		layoutTitle.getLayoutParams().height = titleHeight;
		
		List<String> randomList = StringUtil.randomList(question.getOptions());
		FrameLayout layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		layoutOption.addView(new AnswerItemListView(this, optionsHeight, randomList, clickListener));
	}
	
	private AnswerHandler clickListener = new AnswerHandler() {		
		public void onAnswer(String ans) {
			SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), ans);
			if (last) {
				lessonMgr.doneComprehension(ComprehensionQuestionTextActivity.this, answer);
			} else {
				lessonMgr.doneAnswerComprehension(ComprehensionQuestionTextActivity.this, answer);
			}
		}
	};
	
	@Override
	public void finish() {
		lessonMgr.backComprehension();
		super.finish();
	}
}
