package com.framework.Package.reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyFileReader {
	
	//String path=System.getProperty("user.dir")+"/src/main/resources/configFiles/Environments.properties";
	public String path;
	public Properties prop;
	public FileInputStream fis;
	public FileOutputStream fos;
	
	public PropertyFileReader(String path){
		this.path=path;
	}
	
	
	public String getPropData(String key) throws Exception{
		
		prop=new Properties();
		fis=new FileInputStream(path);
		prop.load(fis);
		
		String value=prop.getProperty(key);
		return value;
		//System.out.println(value);
	}
	
	public void setPropData(String key,String value) throws Exception{
		
		prop=new Properties();
		fis=new FileInputStream(path);
		prop.load(fis);
		prop.setProperty(key, value);
		fos=new FileOutputStream(path);
		prop.store(fos, "Prop file updated successfully");
	}
	
	/*public static void main(String[] args) throws Exception{
		
		PropertyFileReader pfr=new PropertyFileReader();
		pfr.getPropData("QA");
		pfr.setPropData("Check", "Pass");
		
	}*/

}
