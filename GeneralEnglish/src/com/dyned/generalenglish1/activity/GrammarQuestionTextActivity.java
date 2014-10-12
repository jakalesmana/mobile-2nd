package com.dyned.generalenglish1.activity;

import java.util.List;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.AnswerItemListView;
import com.dyned.generalenglish1.component.AnswerItemListView.AnswerHandler;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;
import com.dyned.generalenglish1.util.AppUtil;
import com.dyned.generalenglish1.util.StringUtil;

public class GrammarQuestionTextActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	private GEQuestion question;
	private boolean last;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_text);
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
		
		FrameLayout layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		
		List<String> randomList = StringUtil.randomList(question.getOptions());
		layoutOption.addView(new AnswerItemListView(this, optionsHeight, randomList, clickListener));
		
	}
	
	private AnswerHandler clickListener = new AnswerHandler() {		
		public void onAnswer(String ans) {
			SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), ans);
			if (last) {
				lessonMgr.doneGrammar(GrammarQuestionTextActivity.this, answer);
			} else {
				lessonMgr.doneAnswerGrammar(GrammarQuestionTextActivity.this, answer);
			}
		}
	};
	
	@Override
	public void finish() {
		lessonMgr.backGrammar();
		super.finish();
	}
}
