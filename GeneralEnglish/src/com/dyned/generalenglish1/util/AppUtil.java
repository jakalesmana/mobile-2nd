package com.dyned.generalenglish1.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class AppUtil {

	private static List<Activity> activities = new ArrayList<Activity>();
	
	public static int GetActionBarHeight(Context c){
		TypedValue tv = new TypedValue();
		if (c.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
		{
			int height = TypedValue.complexToDimensionPixelSize(tv.data, c.getResources().getDisplayMetrics());
		    return height;
		}
		return 0;
	}
	
	public static int GetScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public static int GetScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
	public static int GetAppScreenHeight(Context context) {
		return GetScreenHeight(context) - GetActionBarHeight(context);
	}
	
	public static boolean IsSDCARDMounted(){
		String status = Environment.getExternalStorageState();
	    if (status.equals(Environment.MEDIA_MOUNTED)) return true;
	    return false;
	}
	
	public static void HideKeyboard(IBinder windowToken, Context context){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(windowToken, 0);
	}
	
	public static void ShowKeyboard(View view, Context context){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);		
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}
	
	public static boolean IsNetworkConnected(Context context){
		ConnectivityManager conMgr =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		  if (i == null)
		    return false;
		  if (!i.isConnected())
		    return false;
		  if (!i.isAvailable())
		    return false;
		  
		  return true;
	}
	
	public static String getLocalIpv4Address(){
	    try {
	        String ipv4;
	        List<NetworkInterface>  nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
	        if(nilist.size() > 0){
	            for (NetworkInterface ni: nilist){
	                List<InetAddress>  ialist = Collections.list(ni.getInetAddresses());
	                if(ialist.size()>0){
	                    for (InetAddress address: ialist){
	                        if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4=address.getHostAddress())){ 
	                            return ipv4;
	                        }
	                    }
	                }
	            }
	        }

	    } catch (SocketException ex) {
	    	return "";
	    }
	    return "";
	}
	
	public static boolean IsGooglePlayServiceInstalled(Context context) {
		try {
			context.getPackageManager().getApplicationInfo("com.google.android.gms", 0);
			return true;
		} catch(PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	
	public static boolean IsAppInstalled(Context context, String packageName) {
		try {
			context.getPackageManager().getApplicationInfo(packageName, 0);
			return true;
		} catch(PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	
	public static void AddActivityHistory(Activity activity){
		activities.add(activity);
	}
	
	public static void ClearActivityHistory(){
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i) != null) {
				activities.get(i).finish();
			}
		}
		activities.clear();
	}
	
	public static String getVersionName(Context ctx) {
		try {
			return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static int getVersionCode(Context ctx) {
		try {
			return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	
	public static String getApplicationName(Context context){
		Resources appR = context.getResources(); 
		String txt = (String) appR.getText(appR.getIdentifier("app_name",  "string", context.getPackageName()));
		return txt;
    }
	
	public static String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  String model = Build.MODEL;
		  if (model.startsWith(manufacturer)) {
		    return capitalize(model);
		  } else {
		    return capitalize(manufacturer) + " " + model;
		  }
		}

	private static String capitalize(String s) {
	  if (s == null || s.length() == 0) {
	    return "";
	  }
	  char first = s.charAt(0);
	  if (Character.isUpperCase(first)) {
	    return s;
	  } else {
	    return Character.toUpperCase(first) + s.substring(1);
	  }
	} 
	
	public static String getOsversion(){
		return Build.VERSION.RELEASE;
	}
	
	public static boolean hasCallPhoneFeature(Context c){
		PackageManager pm = c.getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
		    return true;
		}
		return false;
	}
	
	public static boolean hasLocationFeature(Context c){
		PackageManager pm = c.getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION)) {
		    return true;
		}
		return false;
	}
	
	public static boolean hasGPSFeature(Context c){
		PackageManager pm = c.getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
		    return true;
		}
		return false;
	}

	public static int getImageResId(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "drawable", context.getPackageName());
	}

	public static int getResId(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "raw", context.getPackageName());
	}

	public static String ReadTextFileFromAssets(Context context, String fileName) {
		InputStream is;
		try {
			is = context.getAssets().open(fileName);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
	
			// byte buffer into a string
			return new String(buffer);
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void showChooserIntent(Context aContext, String aSubject,
			String aContent) {
		List<Intent> targetedShareIntents = findShareClients(aContext,
				aSubject, aContent);
		Intent chooserIntent = Intent.createChooser(
				targetedShareIntents.remove(0), "Select app to share");
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
				targetedShareIntents.toArray(new Parcelable[] {}));
		aContext.startActivity(chooserIntent);
	}
	
	private static List<Intent> findShareClients(Context aContext,
			String subject, String content) {
		List<Intent> targetIntents = new ArrayList<Intent>();
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");

		final PackageManager packageManager = aContext.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(
				shareIntent, PackageManager.MATCH_DEFAULT_ONLY);
		if (null != list) {
			for (ResolveInfo resolveInfo : list) {
				String p = resolveInfo.activityInfo.packageName;
				if (p != null) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.setPackage(p);
					// intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					// subject);
					intent.putExtra(Intent.EXTRA_TEXT, subject + ":\r\n"
							+ content);
					targetIntents.add(intent);
				}
			}
		}
		return targetIntents;
	}
	
}
