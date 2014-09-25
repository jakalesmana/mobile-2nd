package com.dyned.generalenglish1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.composite.UnitLessonAdapter;
import com.dyned.generalenglish1.manager.LessonManager;
import com.dyned.generalenglish1.model.GEMainMenu;

public class UnitActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit);
		
		final GEMainMenu unit = (GEMainMenu) getIntent().getSerializableExtra("GEunit");
		
		TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
		ListView lvUnit = (ListView) findViewById(R.id.lvUnit);
		
		txtTitle.setText(unit.getTitle());
		lvUnit.setAdapter(new UnitLessonAdapter(this, unit.getListLesson()));
		
		lvUnit.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				LessonManager.getInstance().startLesson(UnitActivity.this, unit, unit.getListLesson().get(pos));
			}
		});
	}
}
