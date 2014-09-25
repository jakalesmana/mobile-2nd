package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.composite.AnswerAdapter;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class ComprehensionQuestionTextActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_text);
		lessonMgr = LessonManager.getInstance();
		
		final GEQuestion question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		final boolean last = getIntent().getBooleanExtra("lastQuestion", false);
				
		TextView txtQuestion = (TextView) findViewById(R.id.txtQuestion);
		ListView lvAnswer = (ListView) findViewById(R.id.lvAnswer);
		
		txtQuestion.setText(question.getQuestion());
		lvAnswer.setAdapter(new AnswerAdapter(this, question.getOptions()));
		
		lvAnswer.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), question.getOptions().get(pos));
				if (last) {
					lessonMgr.doneComprehension(ComprehensionQuestionTextActivity.this, answer);
				} else {
					lessonMgr.doneAnswerComprehension(ComprehensionQuestionTextActivity.this, answer);
				}
			}
		});
	}
	
	@Override
	public void finish() {
		lessonMgr.backComprehension();
		super.finish();
	}
}
