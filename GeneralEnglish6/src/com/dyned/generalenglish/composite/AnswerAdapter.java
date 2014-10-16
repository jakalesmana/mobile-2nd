package com.dyned.generalenglish.composite;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyned.generalenglish6.R;

public class AnswerAdapter extends BaseAdapter {

	private LayoutInflater li;
	private List<String> list;
	
	public AnswerAdapter(Context context, List<String> list) {
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
		TextView index, answer;
		int position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = li.inflate(R.layout.answer_item, null);
			holder.index = (TextView) convertView.findViewById(R.id.txtIndex);
			holder.answer = (TextView) convertView.findViewById(R.id.txtAnswer);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.index.setText(getIndex(position));
		holder.answer.setText((String)list.get(position));
		
		return convertView;
	}
	
	private String getIndex(int pos){
		switch (pos) {
			case 0: return "A";
			case 1: return "B";
			case 2: return "C";
			case 3: return "D";
			case 4: return "E";
			case 5: return "F";
			case 6: return "G";
			case 7: return "H";
			case 8: return "I";
			case 9: return "J";
			case 10: return "K";
			default: return "";
		}
	}
}
