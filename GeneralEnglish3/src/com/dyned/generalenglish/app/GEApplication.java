package com.dyned.generalenglish.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.dyned.generalenglish.model.GE;
import com.dyned.generalenglish.util.AppUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class GEApplication extends Application {
	
	public static final String app = "GE3";
	public static final String appName = "GE 3";
//	public static final String sample_app_key = "9cc8bd9f879ce0233ccc5fada1ac4d54f5f05893";
	
	private static GE mainAppContent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		String appData = AppUtil.ReadTextFileFromAssets(this, "app_content.json");
		mainAppContent = GE.parseGE(appData);
		
		initImageLoader(getApplicationContext());
	}
	
	public static GE getGEContent(){
		return mainAppContent;
	}
	
	public static void initImageLoader(Context context) {
		int memoryCacheSize;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
			int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
			memoryCacheSize = (memClass / 10) * 1024 * 1024; // 1/8 of app memory limit 
		} else {
			memoryCacheSize = 1 * 1024 * 1024;
		}

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(memoryCacheSize)
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.tasksProcessingOrder(QueueProcessingType.FIFO)
			.enableLogging()
			.build();
		ImageLoader.getInstance().init(config);
	}
}
