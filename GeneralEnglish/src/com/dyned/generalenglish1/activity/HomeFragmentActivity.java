package com.dyned.generalenglish1.activity;

import java.util.List;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.model.GE;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.model.GEMainMenu;
import com.dyned.generalenglish1.model.GEQuestion;

public class HomeFragmentActivity extends BaseActivity {

	private float density;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		enabeBackMenu();
		
		density = getResources().getDisplayMetrics().density;
		
//		initLeftMenu();

		GE ge = GEApplication.getGEContent();
		System.out.println("GE app name: " + ge.getAppName());
		final List<GEMainMenu> menus = ge.getListMenu();
		for (GEMainMenu geMainMenu : menus) {
			System.out.println("GE app menu: " + geMainMenu.getTitle());
			List<GELesson> lessons = geMainMenu.getListLesson();
			for (GELesson geLesson : lessons) {
				System.out.println("GE app lesson: " + geLesson.getTitle());
				
				System.out.println("GE app comprehension " + geLesson.getComprehension().getTitle());
				List<GEQuestion> questions = geLesson.getComprehension().getListQuestion();
				for (GEQuestion geQuestion : questions) {
					System.out.println("GE app comp question: " + geQuestion.getQuestion());
				}
				
				System.out.println("GE app grammar " + geLesson.getGrammar().getType());
				List<GEQuestion> questionss = geLesson.getGrammar().getListQuestion();
				for (GEQuestion geQuestion : questionss) {
					System.out.println("GE app grammar question: " + geQuestion.getQuestion());
				}
			}
		}
		
//		ListView lvHome = (ListView) findViewById(R.id.lvHome);
//		lvHome.setAdapter(new UnitAdapter(this, menus));
//		lvHome.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
//				Intent i = new Intent(HomeFragmentActivity.this, UnitActivity.class);
//				i.putExtra("GEunit", menus.get(pos));
//				startActivity(i);
//			}
//		});
		
//		GEAnswerPacket p = new GEAnswerPacket();
//		p.setUnit("U1");
//		p.setLesson("L2");
//		p.setCoversation("GE1");
//		p.setComprehentionAttempted(3);
//		p.setComprehentionCorrect(3);
//		
//		UserPreference.getInstance(this).addToCurrentAnswerPacket(p);
//		
//		GEAnswerPacket p1 = UserPreference.getInstance(this).getCurrentAnswerPacket();
//		System.out.println("packet answer: " + p1.getComprehentionAttempted() + " - " + p1.getComprehentionCorrect());
	
	
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		int var = 10;
		if(density == 0.75){ //ldpi
			var = 9;
		} else if(density == 1.0){ //mdpi
			var = 10;
		} else if (density == 1.5) { //hdpi
			var = 11;
		} else if(density == 2.0){ //xhdpi
			var = 12;
		} else if(density == 3.0){ //xxhdpi
			var = 13;
		}
		handleLayout(var);
	}

	private void handleLayout(int var) {
		FrameLayout layoutPipe1 = (FrameLayout) findViewById(R.id.layoutPipe1);
		FrameLayout layoutImgUnit1 = (FrameLayout) findViewById(R.id.layoutImgUnit1);
		FrameLayout layoutImgUnit2 = (FrameLayout) findViewById(R.id.layoutImgUnit2);
		ImageView imgUnit1 = (ImageView) findViewById(R.id.imgUnit1);
		ImageView imgUnit2 = (ImageView) findViewById(R.id.imgUnit2);
		
		int imageWidth = imgUnit1.getWidth();
		
		int x1 = imageWidth + imgUnit1.getLeft() + layoutImgUnit1.getLeft() - (imageWidth / 8);		
		int yLine1 = imageWidth + layoutImgUnit1.getTop() - (imageWidth / 4);
				
		System.out.println("xxx right: " + imgUnit2.getRight());
		System.out.println("xxx layout left: " + layoutImgUnit2.getLeft());
		
		int x2 = imgUnit2.getRight() + layoutImgUnit2.getLeft() - (imageWidth * 3 / 4);
//		int x2 = imageWidth + imgUnit2.getRight() + layoutImgUnit1.getLeft() - (imageWidth / 4);
		int y2 = yLine1 + (imageWidth / 2);
		
		int lineWidth = x2 - x1;
		int lineHeight = y2 - yLine1;
		
		LayoutParams params = new LayoutParams(lineWidth, lineHeight);
		params.leftMargin = x1;
		params.topMargin = yLine1;
		layoutPipe1.setLayoutParams(params);
		
		
		int yLine2 = (int) (yLine1 + (imageWidth) + (var * density * 2));
		FrameLayout layoutPipe2 = (FrameLayout) findViewById(R.id.layoutPipe2);
		LayoutParams params2 = new LayoutParams(lineWidth, lineHeight);
		params2.leftMargin = x1;
		params2.topMargin = yLine2;
		layoutPipe2.setLayoutParams(params2);
		
		int yLine3 = (int) (yLine2 + (imageWidth) + (0 * density));
		FrameLayout layoutPipe3 = (FrameLayout) findViewById(R.id.layoutPipe3);
		LayoutParams params3 = new LayoutParams(lineWidth, lineHeight);
		params3.leftMargin = x1;
		params3.topMargin = yLine3;
		layoutPipe3.setLayoutParams(params3);
		
		int yLine4 = (int) (yLine3 + (imageWidth) + (var * density * 2));
		FrameLayout layoutPipe4 = (FrameLayout) findViewById(R.id.layoutPipe4);
		LayoutParams params4 = new LayoutParams(lineWidth, lineHeight);
		params4.leftMargin = x1;
		params4.topMargin = yLine4;
		layoutPipe4.setLayoutParams(params4);
		
		int yLine5 = (int) (yLine4 + (imageWidth) + (0 * density));
		FrameLayout layoutPipe5 = (FrameLayout) findViewById(R.id.layoutPipe5);
		LayoutParams params5 = new LayoutParams(lineWidth, lineHeight);
		params5.leftMargin = x1;
		params5.topMargin = yLine5;
		layoutPipe5.setLayoutParams(params5);
		
		int yLine6 = (int) (yLine5 + (imageWidth) + (var * density * 2));
		FrameLayout layoutPipe6 = (FrameLayout) findViewById(R.id.layoutPipe6);
		LayoutParams params6 = new LayoutParams(lineWidth, lineHeight);
		params6.leftMargin = x1;
		params6.topMargin = yLine6;
		layoutPipe6.setLayoutParams(params6);
				
		int yLine7 = (int) (yLine6 + (imageWidth) + (0 * density));
		FrameLayout layoutPipe7 = (FrameLayout) findViewById(R.id.layoutPipe7);
		LayoutParams params7 = new LayoutParams(lineWidth, lineHeight);
		params7.leftMargin = x1;
		params7.topMargin = yLine7;
		layoutPipe7.setLayoutParams(params7);
		
		int yLine8 = (int) (yLine7 + (imageWidth) + (var * density * 2));
		FrameLayout layoutPipe8 = (FrameLayout) findViewById(R.id.layoutPipe8);
		LayoutParams params8 = new LayoutParams(lineWidth, lineHeight);
		params8.leftMargin = x1;
		params8.topMargin = yLine8;
		layoutPipe8.setLayoutParams(params8);
		
		int yLine9 = (int) (yLine8 + (imageWidth) + (0 * density));
		FrameLayout layoutPipe9 = (FrameLayout) findViewById(R.id.layoutPipe9);
		LayoutParams params9 = new LayoutParams(lineWidth, lineHeight);
		params9.leftMargin = x1;
		params9.topMargin = yLine9;
		layoutPipe9.setLayoutParams(params9);
		
		int yLine10 = (int) (yLine9 + (imageWidth) + (var * density * 2));
		FrameLayout layoutPipe10 = (FrameLayout) findViewById(R.id.layoutPipe10);
		LayoutParams params10 = new LayoutParams(lineWidth, lineHeight);
		params10.leftMargin = x1;
		params10.topMargin = yLine10;
		layoutPipe10.setLayoutParams(params10);
		
		int yLine11 = (int) (yLine10 + (imageWidth) + (0 * density));
		FrameLayout layoutPipe11 = (FrameLayout) findViewById(R.id.layoutPipe11);
		LayoutParams params11 = new LayoutParams(lineWidth, lineHeight);
		params11.leftMargin = x1;
		params11.topMargin = yLine11;
		layoutPipe11.setLayoutParams(params11);
		
	}
}
