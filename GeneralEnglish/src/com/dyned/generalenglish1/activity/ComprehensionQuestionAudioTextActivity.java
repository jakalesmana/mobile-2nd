package com.dyned.generalenglish1.activity;

import java.util.List;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.AnswerItemListView;
import com.dyned.generalenglish1.component.AnswerItemListView.AnswerHandler;
import com.dyned.generalenglish1.component.AudioPlayer;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;
import com.dyned.generalenglish1.util.AppUtil;
import com.dyned.generalenglish1.util.StringUtil;

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
		AppUtil.AddActivityHistory(this);
		
		lessonMgr = LessonManager.getInstance();
		
		question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		last = getIntent().getBooleanExtra("lastQuestion", false);
				
		int appHeight = AppUtil.GetAppScreenHeight(this);
		int audioHeight = appHeight / 9 * 4;
		int optionsHeight = appHeight / 9 * 5;
		
		FrameLayout layoutAudio = (FrameLayout) findViewById(R.id.layoutAudio);
		FrameLayout layoutOption = (FrameLayout)findViewById(R.id.layoutOption);
		List<String> randomList = StringUtil.randomList(question.getOptions());
		layoutOption.addView(new AnswerItemListView(this, optionsHeight, randomList, clickListener));
		
		layoutAudio.getLayoutParams().height = audioHeight;
		
		ap = new AudioPlayer(this, question.getQuestion().split("\\|")[0].split("\\.")[0], null);
		layoutAudio.addView(ap);
	}
	
	private AnswerHandler clickListener = new AnswerHandler() {		
		public void onAnswer(String ans) {
			SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), ans);
			
			System.out.println("answer: " + answer.getName() + " - " + answer.getValue());
			
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
