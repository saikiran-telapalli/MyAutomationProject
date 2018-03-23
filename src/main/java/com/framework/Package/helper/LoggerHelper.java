package com.framework.Package.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean root=false;

	public static Logger getLogger(Class<?> clazz){

		if(root){
			//System.out.println("Root value is root="+root);
			return Logger.getLogger(clazz);			
		}

		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/configFiles/log4j.properties");
		root=true;
		//System.out.println("Root value is true,root="+root);
		return Logger.getLogger(clazz);

	}

}
