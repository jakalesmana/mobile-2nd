package com.dyned.generalenglish1.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.composite.ResultAdapter;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.model.GEAnswerPacket;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.model.GEMainMenu;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.PostInternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class GrammarResultActivity extends BaseActivity {

	private LessonManager lessonMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grammar_result);
		lessonMgr = LessonManager.getInstance();
		
		GEMainMenu unit = lessonMgr.getCurrentUnit();
		GELesson lesson = lessonMgr.getCurrentLesson();
		
		TextView txtUnit = (TextView) findViewById(R.id.txtUnit);
		TextView txtLesson = (TextView) findViewById(R.id.txtLesson);
		Button btnTry = (Button) findViewById(R.id.btnTry);
		Button btnMenu = (Button) findViewById(R.id.btnMenu);
		ListView lvResult = (ListView) findViewById(R.id.lvResult);
		
		txtUnit.setText(unit.getTitle());
		txtLesson.setText(lesson.getTitle());
		lvResult.setAdapter(new ResultAdapter(this, lesson.getGrammar().getListQuestion(), lessonMgr.getCurrentGrammarAnswer()));
		
		btnTry.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lessonMgr.repeatGrammar(GrammarResultActivity.this);
				finish();
			}
		});
		
		btnMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lessonMgr.finishLesson();
				UserPreference.getInstance(GrammarResultActivity.this).clearCurrentAnswerPacket();
				finish();
			}
		});
		
		submitLessonResultToServer();
	}
	
	private void submitLessonResultToServer() {
		GEAnswerPacket answers = UserPreference.getInstance(this).getCurrentAnswerPacket();
		System.out.println("answers to submit: ");
		System.out.println("completed time: " + answers.getCompletedTime());
		System.out.println("listening total: " + answers.getListeningTotal());
		System.out.println("comp attemp: " + answers.getComprehentionAttempted());
		System.out.println("comp correct: " + answers.getComprehentionCorrect());
		System.out.println("grammar attemp: " + answers.getGrammarAttempted());
		System.out.println("grammar correct: " + answers.getGrammarCorrect());
		
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {				
			}
			
			public void onDone(String str) {
				System.out.println("response update answer: " + str);
				try {
					JSONObject result = new JSONObject(str);
					if (result.getBoolean("status")) {
//						loadLatestHistory();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			public void onConnectionError(String message) {
				
			}
		});
		task.addPair("app_key", URLAddress.DUMMY_APP_KEY);
		task.addPair("conversation", answers.getCoversation());
		task.addPair("unit", answers.getUnit());
		task.addPair("lesson", answers.getLesson());
		task.addPair("completed_time", "" + answers.getCompletedTime());
		task.addPair("listening_total", "" + answers.getListeningTotal());
		task.addPair("content_attempted", "" + answers.getComprehentionAttempted());
		task.addPair("content_correct", "" + answers.getComprehentionCorrect());
		task.addPair("grammar_attempted", "" + answers.getGrammarAttempted());
		task.addPair("grammar_correct", "" + answers.getGrammarCorrect());
		task.execute(URLAddress.URL_CONVERSATION_UPDATE);
		
	}
	
	@Override
	public void onBackPressed() {}
}
