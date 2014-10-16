package com.dyned.generalenglish.composite;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dyned.generalenglish6.R;

public class StudyTipsAdapter extends BaseExpandableListAdapter {

	private final Context mContext;
	private final LayoutInflater mLayoutInflater;

	private List<String> groupTitle;
	private List<String> groupContent;
	
	public StudyTipsAdapter(Context context, List<String> groupTitle, List<String> groupContent) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.groupTitle = groupTitle;
		this.groupContent = groupContent;
	}
	
	@Override
	public int getGroupCount() {
		return groupTitle.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupTitle.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_tips_title, parent, false);
		}

		TextView text = (TextView) convertView.findViewById(R.id.txtTitle);
		text.setText(groupTitle.get(groupPosition));
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groupContent.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_tips_content, parent, false);
		}
		
		final TextView text = (TextView) convertView.findViewById(R.id.txtContent);
		text.setText(Html.fromHtml(groupContent.get(groupPosition)));
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
