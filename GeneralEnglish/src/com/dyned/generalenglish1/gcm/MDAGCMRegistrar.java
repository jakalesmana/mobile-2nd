package com.dyned.generalenglish1.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.dyned.generalenglish1.manager.UserPreference;
import com.dyned.generalenglish1.tools.InternetConnectionListener;
import com.dyned.generalenglish1.tools.PostInternetTask;
import com.dyned.generalenglish1.util.URLAddress;

public class MDAGCMRegistrar {
	private static final String GCM_PROJECT_ID = "51356884072";
	
    public static void registerGCM(Context context) {
    	System.out.println("register for gcm id");
    	
//    	new Thread(new Runnable() {			
//			public void run() {
//				GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
//		    	try {
//					String registrationId = gcm.register(ApplicationProperties.GCM_PROJECT_ID);
//					System.out.println("registrationId: " + registrationId);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
    	
//    	GCMRegistrar.checkDevice(context);
//    	GCMRegistrar.checkManifest(context);
    	
        Intent registrationIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
		registrationIntent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
		registrationIntent.putExtra("sender", GCM_PROJECT_ID);
		context.startService(registrationIntent);
    }
    
    public static void sendDeviceIdToServer(final Context context, final String deviceId){
    	PostInternetTask internetTask = new PostInternetTask(context, new InternetConnectionListener() {			
			public void onStart() {
			}
			
			public void onDone(String str) {
				System.out.println("device id registrar: " + str);
				
			}

			@Override
			public void onConnectionError(String message) {
			}
		});
    	internetTask.addPair("token", deviceId);
    	internetTask.addPair("app_key", UserPreference.getInstance(context).getAppKey());
    	internetTask.execute(URLAddress.SET_DEVICE_TOKEN);
    }
}