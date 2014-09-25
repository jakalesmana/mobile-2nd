package com.dyned.generalenglish1.composite;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyned.generalenglish1.R;
import com.dyned.generalenglish1.model.GEQuestion;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class ResultAdapter extends BaseAdapter {

	private LayoutInflater li;
	private List<GEQuestion> listQuestion;
	private List<SerializedNameValuePair> listAnswer;
	private boolean needParse;
	
	public ResultAdapter(Context context, List<GEQuestion> listQuestion, List<SerializedNameValuePair> listAnswer) {
		this(context, listQuestion, listAnswer, false);
	}
	public ResultAdapter(Context context, List<GEQuestion> listQuestion, List<SerializedNameValuePair> listAnswer, boolean titleNeedParse) {
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listQuestion = listQuestion;
		this.listAnswer = listAnswer;
		this.needParse = titleNeedParse;
	}
	
	@Override
	public int getCount() {
		return listQuestion.size();
	}

	@Override
	public Object getItem(int position) {
		return listQuestion.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView status, question, answer;
		int position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = li.inflate(R.layout.result_item, null);
			holder.question = (TextView) convertView.findViewById(R.id.txtQuestion);
			holder.answer = (TextView) convertView.findViewById(R.id.txtAnswer);
			holder.status = (TextView) convertView.findViewById(R.id.txtStatus);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		

		String question;
		if (needParse) {
			question = ((GEQuestion)listQuestion.get(position)).getQuestion().split("\\|")[1];
		} else {
			question = ((GEQuestion)listQuestion.get(position)).getQuestion();
		}
		
		holder.question.setText(question);
		holder.answer.setText(((SerializedNameValuePair)listAnswer.get(position)).getValue());
		
		String status;
		int color;
		if (((SerializedNameValuePair)listAnswer.get(position)).getValue()
				.equals(((SerializedNameValuePair)listAnswer.get(position)).getName())) {
			status = "correct";
			color = Color.BLUE;
		} else {
			status = "wrong";
			color = Color.RED;
		}
		
		holder.status.setText(status);
		holder.status.setTextColor(color);
		
		return convertView;
	}
}
