package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.AnswerItemListView;
import com.dyned.generalenglish1.component.AnswerItemListView.AnswerHandler;
import com.dyned.generalenglish1.component.AudioPlayer;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class ComprehensionQuestionAudioTextActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	private AudioPlayer ap;
	private boolean firstTime = true;
	private boolean last;
	private GEQuestion question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_audio_text);
		lessonMgr = LessonManager.getInstance();
		
		question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		last = getIntent().getBooleanExtra("lastQuestion", false);
				
		FrameLayout layoutAudio = (FrameLayout) findViewById(R.id.layoutAudio);
		
//		ListView lvAnswer = (ListView) findViewById(R.id.lvAnswer);
//		
//		lvAnswer.setAdapter(new AnswerAdapter(this, question.getOptions()));
//		lvAnswer.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
//				SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), question.getOptions().get(pos));
//				if (last) {
//					lessonMgr.doneComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
//				} else {
//					lessonMgr.doneAnswerComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
//				}
//			}
//		});
		
		FrameLayout layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		layoutOption.addView(new AnswerItemListView(this, question.getOptions(), clickListener));
		
		ap = new AudioPlayer(this, question.getQuestion().split("\\|")[0].split("\\.")[0], null);
		layoutAudio.addView(ap);
	}
	
	private AnswerHandler clickListener = new AnswerHandler() {		
		public void onAnswer(String ans) {
			SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), ans);
			if (last) {
				lessonMgr.doneComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
			} else {
				lessonMgr.doneAnswerComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
			}
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		if (ap != null) {
			ap.init();
			if (firstTime) {
				firstTime = false;
				ap.playPause();
			}
		}
	}
	
	public void onPause() {
		if (ap != null) {
			ap.forceStop();
		}
		super.onPause();
	};
	
	@Override
	public void finish() {
		lessonMgr.backComprehension();
		super.finish();
	}
	
}
