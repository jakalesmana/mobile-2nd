package com.dyned.generalenglish.composite;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyned.generalenglish.model.GEApplicationObject;
import com.dyned.generalenglish.util.AnimateFirstDisplayListener;
import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish5.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ApplicationAdapter extends BaseAdapter {	
	
	private List<GEApplicationObject> mListApp;
	private LayoutInflater mInflater;
	private DisplayImageOptions optionsApp;
	private Context context;
	
	public ApplicationAdapter(Activity aContext, List<GEApplicationObject> aList) {
		mListApp = aList;
		mInflater = LayoutInflater.from(aContext);
		this.context = aContext;
		
		optionsApp = new DisplayImageOptions.Builder().showStubImage(R.drawable.transparent).
				showImageForEmptyUri(R.drawable.transparent)
				.cacheOnDisc().cacheInMemory().build();
		
	}
	@Override
	public int getCount() {
		return mListApp.size();
	}

	@Override
	public GEApplicationObject getItem(int position) {
		return mListApp.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ItemViewHolder
	{
		TextView txtApp;
		Button btnAction;
		ImageView imgApp;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemViewHolder holder = null;
		final GEApplicationObject app = getItem(position);

		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item_application, parent, false);
			holder = new ItemViewHolder();
			holder.txtApp = (TextView) convertView.findViewById(R.id.txtApp);
			holder.btnAction = (Button) convertView.findViewById(R.id.btnAction);
			holder.imgApp = (ImageView) convertView.findViewById(R.id.imgApp);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ItemViewHolder) convertView.getTag();
		}
		
		holder.txtApp.setText(app.getAppName());

		holder.imgApp.getLayoutParams().height = AppUtil.GetScreenWidth(context) * 2 / 3;
		ImageLoader imageLoader = ImageLoader.getInstance();
	 
		imageLoader.displayImage(app.getAppIcon(), holder.imgApp, 
				optionsApp, new AnimateFirstDisplayListener(app.getAppIcon(), holder.imgApp, false, 0, 0));
		 
		if (AppUtil.IsAppInstalled(context, app.getPackageName())) {
			holder.btnAction.setText("   OPEN   ");
		} else {
			holder.btnAction.setText("   INSTALL   ");
		}
		
		holder.btnAction.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(app, AppUtil.IsAppInstalled(context, app.getPackageName()));
			}
		});
		
		return convertView;
	}
	
	private void showDialog(final GEApplicationObject app, boolean installed) {
		if (installed) {
			Intent i = context.getPackageManager().getLaunchIntentForPackage(app.getPackageName());
			context.startActivity(i);
		} else {
			final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
			alertDialog.setTitle("");
			alertDialog.setMessage("Download " + app.getAppName() + "?");
					
			alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.dismiss();
				}
			});
			
			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {				
					Intent i = new Intent(android.content.Intent.ACTION_VIEW);
					i.setData(Uri.parse(app.getDirectLink()));
					context.startActivity(i);
				}
			});
			
			alertDialog.show();
		}
	}
}
