package com.dyned.generalenglish1.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.component.HashtagItemListView;
import com.dyned.generalenglish1.component.HashtagItemListView.OptionsChoosenHandler;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class GrammarSentenceBuilderActivity extends BaseActivity {
	
	private TextView txtAnswer;
	private FrameLayout layoutItem;
	private GEQuestion question;
	private List<String> optionsToBuild;
	private LessonManager lessonMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sentence_builder);
		
		lessonMgr = LessonManager.getInstance();
		
		question = (GEQuestion) getIntent().getSerializableExtra("GEquestion");
		final boolean last = getIntent().getBooleanExtra("lastQuestion", false);
		
		txtAnswer = (TextView) findViewById(R.id.txtAnswer);
		layoutItem = (FrameLayout) findViewById(R.id.layoutBuilder);
		
		Button btnReset = (Button) findViewById(R.id.btnReset);
		Button btnOk = (Button) findViewById(R.id.btnOk);
		
		btnReset.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				initBuilderItem();
			}
		});
		
		btnOk.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				String myAnswer = txtAnswer.getText().toString().trim();
				if (last) {
					lessonMgr.doneGrammar(GrammarSentenceBuilderActivity.this, new SerializedNameValuePair(question.getAnswer(), myAnswer));
				} else {
					lessonMgr.doneAnswerGrammar(GrammarSentenceBuilderActivity.this, new SerializedNameValuePair(question.getAnswer(), myAnswer));
				}
			}
		});
		
		optionsToBuild = randomList(question.getOptions());
		initBuilderItem();
	}

	private void initBuilderItem() {
		txtAnswer.setText("");
		layoutItem.removeAllViews();
		HashtagItemListView itemList = new HashtagItemListView(this, optionsToBuild, mhandler);
		layoutItem.addView(itemList);
	}
	
	private OptionsChoosenHandler mhandler = new OptionsChoosenHandler() {
		public void onChoosen(String word) {
			txtAnswer.append(word + " ");
		}
	};
	
	private List<String> randomList(List<String> options){
		int min = 1;
		int max = question.getOptions().size();
		
		List<String> list = new ArrayList<String>(options);
		
		String temp2 = list.get(max - 1);
		list.remove(max - 1);
		list.add(1, temp2);
		
		Random r = new Random();
		for (int i = 0; i < max; i++) {
			int rand = r.nextInt(max - min) + min;
			String temp = list.get(0);
			list.add(rand, temp);
			list.remove(0);
		}
		
		return list;
	}
	
	@Override
	public void finish() {
		lessonMgr.backGrammar();
		super.finish();
	}
}
