package com.framework.Package.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestNGListners implements ITestListener{

	/*String name="";
	int index=0;
	String packageName="";
	String className="";*/
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		/*name=result.getInstanceName();
		index=name.indexOf('.');
		packageName=name.substring(0,index);
		className=name.substring(index+1);*/
		System.out.println("Test "+result.getName()+" from package= "+(result.getInstanceName().substring(0,result.getInstanceName().lastIndexOf('.')))+" & class="+(result.getInstanceName().substring(result.getInstanceName().lastIndexOf('.')+1))+" Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test "+result.getName()+" from package= "+(result.getInstanceName().substring(0,result.getInstanceName().lastIndexOf('.')))+" & class="+(result.getInstanceName().substring(result.getInstanceName().lastIndexOf('.')+1))+" Passed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test "+result.getName()+" from package= "+(result.getInstanceName().substring(0,result.getInstanceName().lastIndexOf('.')))+" & class="+(result.getInstanceName().substring(result.getInstanceName().lastIndexOf('.')+1))+" Failed");
		System.out.println("Code for Screenshot to be written here");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test "+result.getName()+" from package= "+(result.getInstanceName().substring(0,result.getInstanceName().lastIndexOf('.')))+" & class="+(result.getInstanceName().substring(result.getInstanceName().lastIndexOf('.')+1))+" Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
