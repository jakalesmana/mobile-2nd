package com.dyned.generalenglish1.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.composite.UnitAdapter;
import com.dyned.generalenglish1.model.GE;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.model.GEMainMenu;
import com.dyned.generalenglish1.model.GEQuestion;

public class HomeFragmentActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

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
		
		ListView lvHome = (ListView) findViewById(R.id.lvHome);
		lvHome.setAdapter(new UnitAdapter(this, menus));
		lvHome.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				Intent i = new Intent(HomeFragmentActivity.this, UnitActivity.class);
				i.putExtra("GEunit", menus.get(pos));
				startActivity(i);
			}
		});
		
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

//	private void initLeftMenu() {
//		final LinearLayout layoutMenu = (LinearLayout) findViewById(R.id.layoutMenu);
//
//		final Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
//		final Animation animationSlideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
//		animationSlideOutLeft.setAnimationListener(new AnimationListener() {
//			public void onAnimationStart(Animation animation) {}
//			public void onAnimationRepeat(Animation animation) {}
//
//			public void onAnimationEnd(Animation animation) {
//				layoutMenu.setVisibility(View.GONE);
//			}
//		});
//
//		new Handler().postDelayed(new Runnable() {
//			public void run() {
//				layoutMenu.startAnimation(animationSlideInLeft);
//				layoutMenu.setVisibility(View.VISIBLE);
//
//				new Handler().postDelayed(new Runnable() {
//					public void run() {
//						layoutMenu.startAnimation(animationSlideOutLeft);
//						layoutMenu.setVisibility(View.VISIBLE);
//					}
//				}, 2000);
//			}
//		}, 1000);
//	}
}
