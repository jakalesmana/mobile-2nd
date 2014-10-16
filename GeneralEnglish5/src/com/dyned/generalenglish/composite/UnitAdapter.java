package com.dyned.generalenglish.composite;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyned.generalenglish.model.GEMainMenu;
import com.dyned.generalenglish5.R;

public class UnitAdapter extends BaseAdapter {

	private LayoutInflater li;
	private List<GEMainMenu> list;
	
	public UnitAdapter(Context context, List<GEMainMenu> list) {
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView title, subtitle;
		int position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = li.inflate(R.layout.ge_main_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
			holder.subtitle = (TextView) convertView.findViewById(R.id.txtSubtitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.title.setText(((GEMainMenu)list.get(position)).getTitle());
		holder.subtitle.setVisibility(View.GONE);
		
		convertView.setEnabled(false);
		
		return convertView;
	}
}
