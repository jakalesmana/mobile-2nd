package com.dyned.generalenglish1.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	@SuppressLint("SimpleDateFormat") 
	public static String getDate(long timeStamp){
	    try{
	    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
	        Date netDate = (new Date(timeStamp));
	        return sdf.format(netDate);
	    }
	    catch(Exception ex){
	        return "";
	    }
	}
}
