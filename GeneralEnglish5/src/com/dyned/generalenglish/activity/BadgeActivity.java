package com.dyned.generalenglish.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.dyned.generalenglish.app.GEApplication;
import com.dyned.generalenglish.composite.BadgeAdapter;
import com.dyned.generalenglish.manager.LessonManager;
import com.dyned.generalenglish.model.Badge;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish5.R;

public class BadgeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_badge);
		setHeaderTitle("Badges");
		disableHomeButton();
		
		List<Badge> badges = new ArrayList<Badge>();
		for (int i = 1; i <= 12; i++) {
			badges.add(new Badge(i, isOpen(i)));
		}
		
		GridView gvBadge = (GridView) findViewById(R.id.gvBadge);
		gvBadge.setAdapter(new BadgeAdapter(this, badges));
		gvBadge.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				Badge badge = (Badge) parent.getAdapter().getItem(pos);
				if (badge.isOpen()) {
					showShare(badge);
				}
			}
		});
	}
	
	private boolean isOpen(int unit){
		List<Integer> listOpened = LessonManager.getInstance().getOpenedUnits();
		for (int i = 0; i < listOpened.size(); i++) {
			if (unit == listOpened.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	private void showShare(Badge badge) {
		AppUtil.showChooserIntent(this, "Unit Completed!", "Yayy! I just completed Unit " + badge.getUnit() + " of " + GEApplication.getGEContent().getAppName() + "!");
	}
}
