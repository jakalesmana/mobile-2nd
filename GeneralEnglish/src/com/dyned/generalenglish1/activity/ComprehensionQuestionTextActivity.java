package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.AnswerItemListView;
import com.dyned.generalenglish1.component.AnswerItemListView.AnswerHandler;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class ComprehensionQuestionTextActivity extends BaseActivity {
	
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
				
		TextView txtQuestion = (TextView) findViewById(R.id.txtQuestion);
//		ListView lvAnswer = (ListView) findViewById(R.id.lvAnswer);
		
		txtQuestion.setText(question.getQuestion());
		
		FrameLayout layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		layoutOption.addView(new AnswerItemListView(this, question.getOptions(), clickListener));
		
//		lvAnswer.setAdapter(new AnswerAdapter(this, question.getOptions()));
//		
//		lvAnswer.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
//				SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), question.getOptions().get(pos));
//				if (last) {
//					lessonMgr.doneComprehension(ComprehensionQuestionTextActivity.this, answer);
//				} else {
//					lessonMgr.doneAnswerComprehension(ComprehensionQuestionTextActivity.this, answer);
//				}
//			}
//		});
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
