package com.dyned.generalenglish.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.composite.ResultAdapter;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GEAnswerPacket;
import com.dyned.generalenglish.model.GELesson;
import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish.model.GERecordHistory;
import com.dyned.generalenglish.tools.InternetConnectionListener;
import com.dyned.generalenglish.tools.PostInternetTask;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish.util.URLAddress;
import com.dyned.generalenglish4.R;

public class GrammarResultActivity extends BaseActivity {
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private LessonManager lessonMgr;
	private UserPreference pref;
	private GELesson lesson;
	private GEMainMenu unit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grammar_result);
		AppUtil.AddActivityHistory(this);
		disableMenu();
		setHeaderTitle("Feedback");
		
		lessonMgr = LessonManager.getInstance();
		pref = UserPreference.getInstance(this);
		
		unit = lessonMgr.getCurrentUnit();
		lesson = lessonMgr.getCurrentLesson();
		
		TextView txtUnit = (TextView) findViewById(R.id.txtUnit);
		TextView txtLesson = (TextView) findViewById(R.id.txtLesson);
		Button btnTry = (Button) findViewById(R.id.btnTry);
		Button btnMenu = (Button) findViewById(R.id.btnMenu);
		ListView lvResult = (ListView) findViewById(R.id.lvResult);
		
		txtUnit.setText(unit.getTitle());
		txtLesson.setText(lesson.getTitle() + " - Grammar");
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
		
		if (LessonManager.isAllAnswerCorrect(lessonMgr.getCurrentGrammarAnswer())) {
			submitLessonResultToServer();
		}
		
	}
	
	private void submitLessonResultToServer() {
		GEAnswerPacket answers = UserPreference.getInstance(this).getCurrentAnswerPacket();
		System.out.println("ggg answers to submit: ");
		System.out.println("gggconversation: " + answers.getConversation());
		System.out.println("gggunit: " + answers.getUnit());
		System.out.println("ggglesson: " + answers.getLesson());
		System.out.println("gggcompleted time: " + answers.getCompletedTime());
		System.out.println("ggglistening total: " + answers.getListeningTotal());
		System.out.println("gggcomp attemp: " + answers.getComprehentionAttempted());
		System.out.println("gggcomp correct: " + answers.getComprehentionCorrect());
		System.out.println("ggggrammar attemp: " + answers.getGrammarAttempted());
		System.out.println("ggggrammar correct: " + answers.getGrammarCorrect());
		
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {	
				handler.post(new Runnable() {
					public void run() {
				    	dialog = ProgressDialog.show(GrammarResultActivity.this, "", "Loading..");				
					}
				});
			}
			
			public void onDone(String str) {
				System.out.println("response update answer: " + str);
				try {
					JSONObject result = new JSONObject(str);
					if (result.getBoolean("status")) {
						loadLatestHistory();
					}
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
				}
			}
			
			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(GrammarResultActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("app_key", pref.getAppKey());
		task.addPair("conversation", answers.getConversation());
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
	
	private void loadLatestHistory() {
		PostInternetTask task = new PostInternetTask(this, new InternetConnectionListener() {			
			public void onStart() {				
			}
			
			public void onDone(String str) {
				try {
					JSONObject result = new JSONObject(str);
					boolean status = result.getBoolean("status");
					if (status) {
						System.out.println("response update histroy: " + str);
						List<GERecordHistory> historyList = GERecordHistory.parseHistory(str);

						if (lesson.getCode().equalsIgnoreCase("LC")) {
							showUnitShareToUser();
						} else {
							if (!unit.getCode().equalsIgnoreCase("U1") && !lesson.getCode().equalsIgnoreCase("LBNS")) {
								if (!pref.isCompletedLesson(unit.getCode(), lesson.getCode())) {
									showWaitToUser();
								}
							}
						}
						
						pref.setHistory(historyList);
					}
					dialog.dismiss();
				} catch (JSONException e) {
					e.printStackTrace();
					dialog.dismiss();
				}
			}

			public void onConnectionError(String message) {
				dialog.dismiss();
				Toast.makeText(GrammarResultActivity.this, message + ", try again later.", Toast.LENGTH_SHORT).show();
			}
		});
		task.addPair("app_key", pref.getAppKey());
		task.addPair("conversation", GEApplication.app);
		
		task.execute(URLAddress.URL_CONVERSATION_HISTORY);
	}
	
	private void showWaitToUser() {
		Intent i = new Intent(this, WaitActivity.class);
		startActivityForResult(i, 0);
	}
	
	private void showUnitShareToUser() {
		Intent i = new Intent(this, UnitShareActivity.class);
		startActivityForResult(i, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	
	@Override
	public void onBackPressed() {}
}
