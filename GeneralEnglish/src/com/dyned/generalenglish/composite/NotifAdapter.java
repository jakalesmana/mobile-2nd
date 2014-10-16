package com.dyned.generalenglish.composite;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyned.generalenglish.model.GENotification;
import com.dyned.generalenglish.util.StringUtil;
import com.dyned.generalenglish1.R;

public class NotifAdapter extends BaseAdapter {	
	
	private List<GENotification> mListBadge;
	private LayoutInflater mInflater;

	
	public NotifAdapter(Activity aContext, List<GENotification> aList) {
		mListBadge = aList;
		mInflater = LayoutInflater.from(aContext);
		
	}
	@Override
	public int getCount() {
		return mListBadge.size();
	}

	@Override
	public GENotification getItem(int position) {
		return mListBadge.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ItemViewHolder
	{
		TextView txtMessage;
		TextView txtTime;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemViewHolder holder = null;
		GENotification notif = getItem(position);

		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item_notification, parent, false);
			holder = new ItemViewHolder();
			holder.txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
			holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ItemViewHolder) convertView.getTag();
		}

		holder.txtMessage.setText(notif.getMessage());
		holder.txtTime.setText(StringUtil.getDate(notif.getDate() * 1000));

		return convertView;
	}
}
