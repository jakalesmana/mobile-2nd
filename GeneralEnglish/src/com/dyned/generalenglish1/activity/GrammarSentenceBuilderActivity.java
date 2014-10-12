package com.dyned.generalenglish1.activity;

import java.util.List;

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
import com.dyned.generalenglish1.util.StringUtil;

public class GrammarSentenceBuilderActivity extends BaseActivity {
	
	private TextView txtAnswer;
	private FrameLayout layoutItem;
	private GEQuestion question;
	private List<String> optionsToBuild;
	private LessonManager lessonMgr;
	private String defaultAnswer = "____________________";

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
		
		optionsToBuild = StringUtil.randomList(question.getOptions());
		initBuilderItem();
	}

	private void initBuilderItem() {
		txtAnswer.setText(defaultAnswer);
		layoutItem.removeAllViews();
		HashtagItemListView itemList = new HashtagItemListView(this, optionsToBuild, mhandler);
		layoutItem.addView(itemList);
	}
	
	private OptionsChoosenHandler mhandler = new OptionsChoosenHandler() {
		public void onChoosen(String word) {
			if (txtAnswer.getText().toString().equals(defaultAnswer)) {
				txtAnswer.setText("");
			}
			txtAnswer.append(word + " ");
		}
	};
	
	@Override
	public void finish() {
		lessonMgr.backGrammar();
		super.finish();
	}
}
