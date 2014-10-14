package com.dyned.generalenglish1.activity;

import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.component.AudioPlayer;
import com.dyned.generalenglish1.component.AudioPlayer.AudioPlayerListener;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.GEAnswerPacket;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.util.AppUtil;

public class ListeningActivity extends BaseActivity {
	
	private LessonManager lessonMgr;
	private AudioPlayer ap;
	private TextView toggleScript;
	private TextView txtScript;
	private boolean viewScript;
	private boolean audioPlaying;
	private long playTime;
	private ImageView imgLesson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lesson);
		AppUtil.AddActivityHistory(this);
		
		lessonMgr = LessonManager.getInstance();
		
		GELesson lesson = (GELesson) getIntent().getSerializableExtra("GElesson");
		
		imgLesson = (ImageView) findViewById(R.id.imgLesson);
		imgLesson.setImageResource(AppUtil.getImageResId(this, lesson.getImage().toLowerCase(Locale.getDefault()).split("\\.")[0] + "_img"));
		
		FrameLayout layoutAudio = (FrameLayout) findViewById(R.id.layoutAudio);
		ap = new AudioPlayer(this, lesson.getAudio().split("\\.")[0], audioListener);
		layoutAudio.addView(ap);
		
		Button btnQuestions = (Button) findViewById(R.id.btnQuestion);
		btnQuestions.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lessonMgr.doneListening(ListeningActivity.this);
			}
		});
		
		toggleScript = (TextView) findViewById(R.id.txtViewScript);
		
		if (!UserPreference.getInstance(this).isCompletedLesson(lessonMgr.getCurrentUnit().getCode(), lesson.getCode())) {
			toggleScript.setEnabled(false);
			AlphaAnimation alpha = new AlphaAnimation(0.2f, 0.2f);
			alpha.setDuration(0);
			alpha.setFillAfter(true); 
			toggleScript.startAnimation(alpha);
			imgLesson.startAnimation(alpha);
			ap.setDraggable(false);
		}
		
		txtScript = (TextView) findViewById(R.id.txtScript);
		for (int i = 0; i < lesson.getListScript().size(); i++) {
			txtScript.append(lesson.getListScript().get(i) + "\n\n");
		}
		toggleScript.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				toggleScript();
			}
		});
	}
	
	private AudioPlayerListener audioListener = new AudioPlayerListener() {		
		public void onStop() {
			//compare to on play time
			if (audioPlaying) {
				audioPlaying = false;
				countTime();
			}
		}
		public void onPlay() {
			if (!audioPlaying) {
				audioPlaying = true;
				playTime = System.currentTimeMillis();
			}
		}		
		public void onPause() {
			//compare to on play time
			if (audioPlaying) {
				audioPlaying = false;
				countTime();
			}
		}
		public void onComplete(){
			toggleScript.setEnabled(true);
			AlphaAnimation alpha = new AlphaAnimation(0.2f, 1.0f);
			alpha.setDuration(0);
			alpha.setFillAfter(true); 
			toggleScript.startAnimation(alpha);
			imgLesson.startAnimation(alpha);
			ap.setDraggable(true);
		}
	};
	
	private void countTime() {
		long listeningSeconds = (System.currentTimeMillis() - playTime) / 1000;
		
		GEAnswerPacket p = new GEAnswerPacket();
		p.setCoversation(GEApplication.app);
		p.setUnit(LessonManager.getInstance().getCurrentUnit().getCode());
		p.setLesson(LessonManager.getInstance().getCurrentLesson().getCode());
		p.setListeningTotal((int)listeningSeconds);
		UserPreference.getInstance(this).addToCurrentAnswerPacket(p);
	}
	
	private void toggleScript() {
		if (!viewScript) {
			toggleScript.setText("- Hide Script");
			txtScript.setVisibility(View.VISIBLE);
		} else {
			toggleScript.setText("+ View Script");
			txtScript.setVisibility(View.GONE);
		}
		viewScript = ! viewScript;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (ap != null) {
			ap.init();
		}
	}
	
	public void onPause() {
		if (ap != null) {
			ap.forceStop();
		}
		super.onPause();
	};
}
