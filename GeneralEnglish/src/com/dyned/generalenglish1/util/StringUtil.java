package com.dyned.generalenglish1.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	
	public static List<String> randomList(List<String> options){
		int min = 1;
		int max = options.size();
		
		List<String> list = new ArrayList<String>(options);
		
		String temp2 = list.get(max - 1);
		list.remove(max - 1);
		list.add(1, temp2);
		
		Random r = new Random();
		for (int i = 0; i < max; i++) {
			int rand = r.nextInt(max - min) + min;
			String temp = list.get(0);
			list.add(rand, temp);
			list.remove(0);
		}
		
		return list;
	}
}
