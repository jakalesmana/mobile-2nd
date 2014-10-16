package com.dyned.generalenglish.component;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.dyned.generalenglish.util.AppUtil;
import com.dyned.generalenglish6.R;

@SuppressLint("ViewConstructor")
public class AudioPlayer extends FrameLayout {

	public interface AudioPlayerListener {
		void onPlay();
		void onPause();
		void onStop();
		void onComplete();
	}
	
	private View v;
	private String filename;
	private MediaPlayer player;
	private Handler seekHandler = new Handler();
	private SeekBar bar;
	private boolean playing, shouldUpdate;
	private ImageView btnMedia;
	private TextView txtCurrent, txtTotal;
	private int durationTotalSecond;
	private AudioPlayerListener listener;
	
	@SuppressLint("InflateParams")
	public AudioPlayer(Context context, String filename, AudioPlayerListener listener) {
		super(context);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		this.listener = listener;
		
		LayoutInflater i = LayoutInflater.from(context);
		v = i.inflate(R.layout.layout_audio, null);
		this.filename = filename;
		
		init();
		seekUpdation();
		addView(v);
	}
	
	public void init() {
		System.out.println("audio player init");
		txtCurrent = (TextView)v.findViewById(R.id.txtCurrent);
		txtTotal = (TextView)v.findViewById(R.id.txtTotal);
		
		btnMedia = (ImageView)v.findViewById(R.id.imgPlay);
		bar = (SeekBar)v.findViewById(R.id.barAudio);
		player = MediaPlayer.create(getContext(), AppUtil.getResId(getContext(), filename.toLowerCase(Locale.getDefault()) + "_audio"));
		try {
			player.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		durationTotalSecond = Integer.parseInt(getTimeString(player.getDuration()).split(":")[1]);
		player.setOnCompletionListener(new OnCompletionListener() {			
			public void onCompletion(MediaPlayer mp) {
				playing = false;
				btnMedia.setImageResource(R.drawable.play);
				shouldUpdate = false;
				bar.setProgress(0);
				if(listener != null) listener.onStop();
				if(listener != null) listener.onComplete();
			}
		});
		
		bar.setMax(player.getDuration());
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar bar) {
				player.seekTo(bar.getProgress());
				if (playing) {
					player.start();
					if(listener != null) listener.onPlay();
				} else {
					player.pause();
					if(listener != null) listener.onPause();
				}
				shouldUpdate = true;
				seekUpdation();
			}
			
			public void onStartTrackingTouch(SeekBar bar) {
				player.pause();
				if(listener != null) listener.onPause();
				shouldUpdate = false;
			}
			
			public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
				System.out.println("progress: " + progress + " and " + (player.getDuration() - progress));
				txtCurrent.setText(getTimeString(progress));
				txtTotal.setText("-" + getTimeString(player.getDuration() - progress));
			}
		});
		
		btnMedia.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				playPause();
			}
		});
	}
	
	public void setDraggable(boolean draggable){
		bar.setEnabled(draggable);
		
		if (draggable == false) {
			AlphaAnimation alpha = new AlphaAnimation(0.2f, 0.2f);
			alpha.setDuration(0);
			alpha.setFillAfter(true); 
			bar.startAnimation(alpha);
		} else {
			AlphaAnimation alpha = new AlphaAnimation(0.2f, 1.0f);
			alpha.setDuration(0);
			alpha.setFillAfter(true); 
			bar.startAnimation(alpha);
		}
	}
	
	public void playPause(){
		if (playing) {
			System.out.println("audio player pause");
			player.pause();
			if(listener != null) listener.onPause();
			playing = false;
			btnMedia.setImageResource(R.drawable.play);
		} else {
			System.out.println("audio player play");
			shouldUpdate = true;
			player.start();
			if(listener != null) listener.onPlay();
			playing = true;
			btnMedia.setImageResource(R.drawable.pause_icon);
			seekUpdation();
		}
	}
	
	Runnable run = new Runnable() {
		public void run() {
			if (shouldUpdate) {
				seekUpdation();
			}
		}
	};

	public void seekUpdation() {
		int pos = 0;
//		int dur = 0;
		try {
			pos = player.getCurrentPosition();
//			dur = player.getDuration();
		} catch (Exception e) {
			pos = 0;
//			dur = 0;
		}
		bar.setProgress(pos);
		txtCurrent.setText(getTimeString(pos));
		try {
			txtTotal.setText("-" + getTimeString(player.getDuration() - pos).split(":")[0] + ":" + getToGoSecondString(txtCurrent.getText().toString()));
		} catch (Exception e) {
			txtTotal.setText("");
		}
		seekHandler.postDelayed(run, 100);
	}

	private String getToGoSecondString(String current) {
		int togo = durationTotalSecond - ((Integer.parseInt(current.split(":")[1])));
		String res = "" + togo;
		if (("" + togo).length() < 2) {
			res = "0" + togo;
		}
		return res;
	}

	public void forceStop(){
		seekHandler.removeCallbacksAndMessages(run);
		bar.setProgress(0);
		playing = false;
		btnMedia.setImageResource(R.drawable.play);
		if (player != null) {
			player.stop();
			player.reset();
			player.release();
			player = null;
			if(listener != null) listener.onStop();
		}
	}
	
	private String getTimeString(long millis) {
	    StringBuffer buf = new StringBuffer();
	    int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
	    int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

	    buf
	        .append(String.format("%02d", minutes))
	        .append(":")
	        .append(String.format("%02d", seconds));

	    return buf.toString();
	}

	public void reset() {
		init();
	}
}
