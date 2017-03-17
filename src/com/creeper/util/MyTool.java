package com.creeper.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTool {

	public static String getGoodTime(Date date) {
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return time.format(date);
	}
	
}
