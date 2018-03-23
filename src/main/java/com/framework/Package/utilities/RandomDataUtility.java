package com.framework.Package.utilities;

import java.sql.Timestamp;
import java.util.Date;

public class RandomDataUtility {
	
	public static String getCurrentDateAndTime(){
		Date date=new Date();
		Timestamp time=new Timestamp(date.getTime());
		String timestamp=time.toString();
		return timestamp;
	}

}
