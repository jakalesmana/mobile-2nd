package com.dyned.generalenglish.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.manager.UserPreference;
import com.dyned.generalenglish.model.GE;
import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish.model.GEPushNotification;
import com.dyned.generalenglish.model.GERecordHistory;
import com.dyned.generalenglish.util.NotificationUtil;
import com.dyned.generalenglish5.R;

public class HomeFragmentActivity extends BaseActivity {

	private PopupWindow mPopupWindow;
	
	private float density;
	private ImageView imgUnit1;
	private ImageView imgUnit2;
	private ImageView imgUnit3;
	private ImageView imgUnit4;
	private ImageView imgUnit5;
	private ImageView imgUnit6;
	private ImageView imgUnit7;
	private ImageView imgUnit8;
	private ImageView imgUnit9;
	private ImageView imgUnit10;
	private ImageView imgUnit11;
	private ImageView imgUnit12;
	private List<GEMainMenu> menus;
	private List<Integer> openedIds;

	private ImageView imgTime1;

	private ImageView imgTime2;

	private ImageView imgTime3;

	private ImageView imgTime4;

	private ImageView imgTime5;

	private ImageView imgTime6;

	private ImageView imgTime7;

	private ImageView imgTime8;

	private ImageView imgTime9;

	private ImageView imgTime10;

	private ImageView imgTime11;

	private ImageView imgTime12; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		enableBackMenu();
		disableHomeButton();
		NotificationUtil.getInstance().clear();
		
		openedIds = new ArrayList<Integer>();
		
		density = getResources().getDisplayMetrics().density;
		
//		initLeftMenu();

		GE ge = GEApplication.getGEContent();
		menus = ge.getListMenu();
		
//		for (GEMainMenu geMainMenu : menus) {
//			System.out.println("GE app menu: " + geMainMenu.getTitle());
//			List<GELesson> lessons = geMainMenu.getListLesson();
//			for (GELesson geLesson : lessons) {
//				System.out.println("GE app lesson: " + geLesson.getTitle());
//				
//				System.out.println("GE app comprehension " + geLesson.getComprehension().getTitle());
//				List<GEQuestion> questions = geLesson.getComprehension().getListQuestion();
//				for (GEQuestion geQuestion : questions) {
//					System.out.println("GE app comp question: " + geQuestion.getQuestion());
//				}
//				
//				System.out.println("GE app grammar " + geLesson.getGrammar().getType());
//				List<GEQuestion> questionss = geLesson.getGrammar().getListQuestion();
//				for (GEQuestion geQuestion : questionss) {
//					System.out.println("GE app grammar question: " + geQuestion.getQuestion());
//				}
//			}
//		}
		
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
	
	
		imgUnit1 = (ImageView) findViewById(R.id.imgUnit1);
		imgUnit2 = (ImageView) findViewById(R.id.imgUnit2);
		imgUnit3 = (ImageView) findViewById(R.id.imgUnit3);
		imgUnit4 = (ImageView) findViewById(R.id.imgUnit4);
		imgUnit5 = (ImageView) findViewById(R.id.imgUnit5);
		imgUnit6 = (ImageView) findViewById(R.id.imgUnit6);
		imgUnit7 = (ImageView) findViewById(R.id.imgUnit7);
		imgUnit8 = (ImageView) findViewById(R.id.imgUnit8);
		imgUnit9 = (ImageView) findViewById(R.id.imgUnit9);
		imgUnit10 = (ImageView) findViewById(R.id.imgUnit10);
		imgUnit11= (ImageView) findViewById(R.id.imgUnit11);
		imgUnit12 = (ImageView) findViewById(R.id.imgUnit12);
		
		imgTime1 = (ImageView) findViewById(R.id.imgTime1);
		imgTime2 = (ImageView) findViewById(R.id.imgTime2);
		imgTime3 = (ImageView) findViewById(R.id.imgTime3);
		imgTime4 = (ImageView) findViewById(R.id.imgTime4);
		imgTime5 = (ImageView) findViewById(R.id.imgTime5);
		imgTime6 = (ImageView) findViewById(R.id.imgTime6);
		imgTime7 = (ImageView) findViewById(R.id.imgTime7);
		imgTime8 = (ImageView) findViewById(R.id.imgTime8);
		imgTime9 = (ImageView) findViewById(R.id.imgTime9);
		imgTime10 = (ImageView) findViewById(R.id.imgTime10);
		imgTime11 = (ImageView) findViewById(R.id.imgTime11);
		imgTime12 = (ImageView) findViewById(R.id.imgTime12);
		
		imgUnit1.setOnClickListener(menuClickHandler);
		imgUnit2.setOnClickListener(menuClickHandler);
		imgUnit3.setOnClickListener(menuClickHandler);
		imgUnit4.setOnClickListener(menuClickHandler);
		imgUnit5.setOnClickListener(menuClickHandler);
		imgUnit6.setOnClickListener(menuClickHandler);
		imgUnit7.setOnClickListener(menuClickHandler);
		imgUnit8.setOnClickListener(menuClickHandler);
		imgUnit9.setOnClickListener(menuClickHandler);
		imgUnit10.setOnClickListener(menuClickHandler);
		imgUnit11.setOnClickListener(menuClickHandler);
		imgUnit12.setOnClickListener(menuClickHandler);
		
		imgUnit1.setTag("0");
		imgUnit2.setTag("1");
		imgUnit3.setTag("2");
		imgUnit4.setTag("3");
		imgUnit5.setTag("4");
		imgUnit6.setTag("5");
		imgUnit7.setTag("6");
		imgUnit8.setTag("7");
		imgUnit9.setTag("8");
		imgUnit10.setTag("9");
		imgUnit11.setTag("10");
		imgUnit12.setTag("11");
		
		ScrollView svHome = (ScrollView) findViewById(R.id.svHome);
		svHome.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener() {
			public void onScrollChanged() {
				if (mPopupWindow != null) {
					mPopupWindow.dismiss();
				}
			}
		});
		
		handlePushNotif();
	}
	
	private void handlePushNotif() {
		GEPushNotification notif = (GEPushNotification) getIntent().getSerializableExtra("GEData");
		
		if (notif != null) {			
			for (int i = 0; i < menus.size(); i++) {
				GEMainMenu unit = menus.get(i);
				System.out.println("yyy notif z: " + unit.getId());
				if (Integer.parseInt(unit.getId()) == notif.getUnitId()) {
					Intent intent = new Intent(HomeFragmentActivity.this, UnitActivity.class);
					intent.putExtra("GEunit", unit);
					startActivity(intent);
					return;
				}
			}
			
		}
	}

	private OnClickListener menuClickHandler = new OnClickListener() {
		public void onClick(View v) {
			int pos = Integer.parseInt((String) v.getTag());
			
			if (isAvailable(pos)) {
				showMenuTitle(v, menus.get(pos), pos);
			}
			
		}
	};
	
	private boolean isAvailable(int pos){
		boolean available = false;
		for (int i = 0; i < openedIds.size(); i++) {
			if (pos == (openedIds.get(i) - 1)) {
				return true;
			}
		}
		return available;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		int var = 10;
		if(density == 0.75){ //ldpi
			System.out.println("density: ldpi");
			var = 9;
		} else if(density == 1.0){ //mdpi
			System.out.println("density: mdpi");
			var = 10;
		} else if (density == 1.5) { //hdpi
			System.out.println("density: hdpi");
			var = 11;
		} else if(density == 2.0){ //xhdpi
			System.out.println("density: xhdpi");
			var = 10;
		} else if(density == 3.0){ //xxhdpi
			System.out.println("density: xxhdpi");
			var = 10;
		}
		handleLayout(var);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		handleStatus();
	}

	private void handleStatus() {
		openedIds.clear();
		List<GERecordHistory> histories = UserPreference.getInstance(this).getHistory();
		
		for (int i = 0; i < histories.size(); i++) {
			String historyUnit = histories.get(i).getUnit();
			
			System.out.println("xxx unit: " + historyUnit);
			
			for (int j = 1; j < histories.size() + 1; j++) {
				if (historyUnit.substring(1).equals("" + j)) {
					if (j == 1) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime1.setVisibility(View.VISIBLE);
						} else {
							imgUnit1.setImageResource(R.drawable.unit_1);
							openedIds.add(1);
						}
					}
					if (j == 2) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime2.setVisibility(View.VISIBLE);
						} else {
							imgUnit2.setImageResource(R.drawable.unit_2);
							openedIds.add(2);
						}
					}
					if (j == 3) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime3.setVisibility(View.VISIBLE);
						} else {
							imgUnit3.setImageResource(R.drawable.unit_3);
							openedIds.add(3);
						}
					}
					if (j == 4) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime4.setVisibility(View.VISIBLE);
						} else {
							imgUnit4.setImageResource(R.drawable.unit_4);
							openedIds.add(4);
						}
					}
					if (j == 5) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime5.setVisibility(View.VISIBLE);
						} else {
							imgUnit5.setImageResource(R.drawable.unit_5);
							openedIds.add(5);
						}
					}
					if (j == 6) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime6.setVisibility(View.VISIBLE);
						} else {
							imgUnit6.setImageResource(R.drawable.unit_6);
							openedIds.add(6);
						}
					}
					if (j == 7) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime7.setVisibility(View.VISIBLE);
						} else {
							imgUnit7.setImageResource(R.drawable.unit_7);
							openedIds.add(7);
						}
					}
					if (j == 8) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime8.setVisibility(View.VISIBLE);
						} else {
							imgUnit8.setImageResource(R.drawable.unit_8);
							openedIds.add(8);
						}
					}
					if (j == 9) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime9.setVisibility(View.VISIBLE);
						} else {
							imgUnit9.setImageResource(R.drawable.unit_9);
							openedIds.add(9);
						}
					}
					if (j == 10) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime10.setVisibility(View.VISIBLE);
						} else {
							imgUnit10.setImageResource(R.drawable.unit_10);
							openedIds.add(10);
						}
					}
					if (j == 11) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime11.setVisibility(View.VISIBLE);
						} else {
							imgUnit11.setImageResource(R.drawable.unit_11);
							openedIds.add(11);
						}
					}
					if (j == 12) {
						if (histories.get(j-1).getRecords().get(0).getStatus().equals("waiting")) {
							imgTime12.setVisibility(View.VISIBLE);
						} else {
							imgUnit12.setImageResource(R.drawable.unit_12);
							openedIds.add(12);
						}
					}
					
					try {
						GERecordHistory geh = histories.get(j);
						if (geh != null && geh.getRecords().get(0).getStatus().equals("waiting")) {
							UserPreference.getInstance(this).addCompletedUnit(histories.get(j-1).getUnit());
						}
					} catch (IndexOutOfBoundsException e) {
					}
					
				}
			}
		}
		
		LessonManager.getInstance().setOpenedUnits(openedIds);
	}

	private void handleLayout(int var) {
		FrameLayout layoutPipe1 = (FrameLayout) findViewById(R.id.layoutPipe1);
		FrameLayout layoutImgUnit1 = (FrameLayout) findViewById(R.id.layoutImgUnit1);
		FrameLayout layoutImgUnit2 = (FrameLayout) findViewById(R.id.layoutImgUnit2);
		ImageView imgUnit1 = (ImageView) findViewById(R.id.imgUnit1);
		ImageView imgUnit2 = (ImageView) findViewById(R.id.imgUnit2);
		
		int imageOddWidth = imgUnit1.getWidth();
		int imageEvenWidth = imgUnit2.getWidth();
		
		int x1 = (int) (imageOddWidth + imgUnit1.getLeft() + layoutImgUnit1.getLeft() - (imageOddWidth / 8) - (2 * density));		
		int yLine1 = imageOddWidth + layoutImgUnit1.getTop() - (imageOddWidth / 4);
						
		int x2 = imgUnit2.getRight() + layoutImgUnit2.getLeft() - (imageOddWidth * 3 / 4);
		int y2 = yLine1 + (imageOddWidth / 2);
		
		int lineWidth = x2 - x1;
		int lineHeight = y2 - yLine1;
		
		LayoutParams params = new LayoutParams(lineWidth, lineHeight);
		params.leftMargin = x1;
		params.topMargin = yLine1;
		layoutPipe1.setLayoutParams(params);
		
		
		int yLine2 = (int) (yLine1 + (imageEvenWidth) + (var * density * 2));
		FrameLayout layoutPipe2 = (FrameLayout) findViewById(R.id.layoutPipe2);
		LayoutParams params2 = new LayoutParams(lineWidth, lineHeight);
		params2.leftMargin = x1;
		params2.topMargin = yLine2;
		layoutPipe2.setLayoutParams(params2);
		
		int yLine3 = (int) (yLine2 + (imageOddWidth) + (0 * density));
		FrameLayout layoutPipe3 = (FrameLayout) findViewById(R.id.layoutPipe3);
		LayoutParams params3 = new LayoutParams(lineWidth, lineHeight);
		params3.leftMargin = x1;
		params3.topMargin = yLine3;
		layoutPipe3.setLayoutParams(params3);
		
		int yLine4 = (int) (yLine3 + (imageEvenWidth) + (var * density * 2));
		FrameLayout layoutPipe4 = (FrameLayout) findViewById(R.id.layoutPipe4);
		LayoutParams params4 = new LayoutParams(lineWidth, lineHeight);
		params4.leftMargin = x1;
		params4.topMargin = yLine4;
		layoutPipe4.setLayoutParams(params4);
		
		int yLine5 = (int) (yLine4 + (imageOddWidth) + (0 * density));
		FrameLayout layoutPipe5 = (FrameLayout) findViewById(R.id.layoutPipe5);
		LayoutParams params5 = new LayoutParams(lineWidth, lineHeight);
		params5.leftMargin = x1;
		params5.topMargin = yLine5;
		layoutPipe5.setLayoutParams(params5);
		
		int yLine6 = (int) (yLine5 + (imageEvenWidth) + (var * density * 2));
		FrameLayout layoutPipe6 = (FrameLayout) findViewById(R.id.layoutPipe6);
		LayoutParams params6 = new LayoutParams(lineWidth, lineHeight);
		params6.leftMargin = x1;
		params6.topMargin = yLine6;
		layoutPipe6.setLayoutParams(params6);
				
		int yLine7 = (int) (yLine6 + (imageOddWidth) + (0 * density));
		FrameLayout layoutPipe7 = (FrameLayout) findViewById(R.id.layoutPipe7);
		LayoutParams params7 = new LayoutParams(lineWidth, lineHeight);
		params7.leftMargin = x1;
		params7.topMargin = yLine7;
		layoutPipe7.setLayoutParams(params7);
		
		int yLine8 = (int) (yLine7 + (imageEvenWidth) + (var * density * 2));
		FrameLayout layoutPipe8 = (FrameLayout) findViewById(R.id.layoutPipe8);
		LayoutParams params8 = new LayoutParams(lineWidth, lineHeight);
		params8.leftMargin = x1;
		params8.topMargin = yLine8;
		layoutPipe8.setLayoutParams(params8);
		
		int yLine9 = (int) (yLine8 + (imageOddWidth) + (0 * density));
		FrameLayout layoutPipe9 = (FrameLayout) findViewById(R.id.layoutPipe9);
		LayoutParams params9 = new LayoutParams(lineWidth, lineHeight);
		params9.leftMargin = x1;
		params9.topMargin = yLine9;
		layoutPipe9.setLayoutParams(params9);
		
		int yLine10 = (int) (yLine9 + (imageEvenWidth) + (var * density * 2));
		FrameLayout layoutPipe10 = (FrameLayout) findViewById(R.id.layoutPipe10);
		LayoutParams params10 = new LayoutParams(lineWidth, lineHeight);
		params10.leftMargin = x1;
		params10.topMargin = yLine10;
		layoutPipe10.setLayoutParams(params10);
		
		int yLine11 = (int) (yLine10 + (imageOddWidth) + (0 * density));
		FrameLayout layoutPipe11 = (FrameLayout) findViewById(R.id.layoutPipe11);
		LayoutParams params11 = new LayoutParams(lineWidth, lineHeight);
		params11.leftMargin = x1;
		params11.topMargin = yLine11;
		layoutPipe11.setLayoutParams(params11);
		
	}
	
	@SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	private void showMenuTitle(View imageMenu, final GEMainMenu menu, int pos)
	{
		
		if(mPopupWindow != null) mPopupWindow.dismiss();
		
		View v = LayoutInflater.from(this).inflate(R.layout.popup_menu, null);
		mPopupWindow = new PopupWindow(v, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
		mPopupWindow.setOutsideTouchable(false);
		TextView unit = (TextView) v.findViewById(R.id.txtUnit);
		TextView title = (TextView) v.findViewById(R.id.txtTitle);		
		unit.setText(menu.getTitle().split(":")[0]);
		title.setText(menu.getDesc());
		
		Rect result = new Rect();
		Paint paint = new Paint();
		paint.getTextBounds(menu.getDesc(), 0, menu.getDesc().length(), result);
		int height = (int) (result.height() + (density * 20));
		int width = (int) ((result.width() * 3 / 2) * density);
				
		int xOffset = 0;
		if(((pos + 1) % 2) == 0){
			//popup left of image
			xOffset = 0 - width;
		} else {
			//popup right of image
			xOffset = imageMenu.getWidth() + (int)(5 * density);
		}
		
		if (!mPopupWindow.isShowing())
		{
			mPopupWindow.showAsDropDown(imageMenu, xOffset, 0 - (imageMenu.getWidth() / 2) - (height));
		}
		else
		{
			mPopupWindow.dismiss();
			mPopupWindow.showAsDropDown(imageMenu, xOffset, 0 - (imageMenu.getWidth() / 2) - (height));
		}
		
		v.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(HomeFragmentActivity.this, UnitActivity.class);
				i.putExtra("GEunit", menu);
				startActivity(i);
				mPopupWindow.dismiss();
			}
		});

	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(0, R.anim.activity_close_scale);
	}
}
