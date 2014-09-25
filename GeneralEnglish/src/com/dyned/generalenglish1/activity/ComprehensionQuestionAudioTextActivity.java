package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.AudioPlayer;
import com.dyned.generalenglish1.composite.AnswerAdapter;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class ComprehensionQuestionAudioTextActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	private AudioPlayer ap;
	private boolean firstTime = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_audio_text);
		lessonMgr = LessonManager.getInstance();
		
		final GEQuestion question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		final boolean last = getIntent().getBooleanExtra("lastQuestion", false);
				
		FrameLayout layoutAudio = (FrameLayout) findViewById(R.id.layoutAudio);
		ListView lvAnswer = (ListView) findViewById(R.id.lvAnswer);
		
		lvAnswer.setAdapter(new AnswerAdapter(this, question.getOptions()));
		lvAnswer.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				SerializedNameValuePair answer = new SerializedNameValuePair(question.getAnswer(), question.getOptions().get(pos));
				if (last) {
					lessonMgr.doneComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
				} else {
					lessonMgr.doneAnswerComprehension(ComprehensionQuestionAudioTextActivity.this, answer);
				}
			}
		});
		
		ap = new AudioPlayer(this, question.getQuestion().split("\\|")[0].split("\\.")[0], null);
		layoutAudio.addView(ap);
	}
	
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
