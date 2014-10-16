package com.dyned.generalenglish.composite;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dyned.generalenglish.model.Badge;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish2.R;

public class BadgeAdapter extends BaseAdapter {	
	
	private Activity mContext;
	private List<Badge> mListBadge;
	private LayoutInflater mInflater;

	
	public BadgeAdapter(Activity aContext, List<Badge> aList) {
		mContext = aContext;
		mListBadge = aList;
		mInflater = LayoutInflater.from(aContext);
		
	}
	@Override
	public int getCount() {
		return mListBadge.size();
	}

	@Override
	public Badge getItem(int position) {
		return mListBadge.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ItemViewHolder
	{
		ImageView imgIcon;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemViewHolder holder = null;
		Badge badge = getItem(position);

		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item_badge, parent, false);
			holder = new ItemViewHolder();
			holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgBadge);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ItemViewHolder) convertView.getTag();
		}

		if (badge.isOpen()) {
			int resource = AppUtil.getImageResId(mContext, "unit_" + badge.getUnit());
			holder.imgIcon.setImageResource(resource);
		} else {
			int resource = AppUtil.getImageResId(mContext, "unit_" + badge.getUnit() + "_grey");
			holder.imgIcon.setImageResource(resource);
		}

		return convertView;
	}

}
