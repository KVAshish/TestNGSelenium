package com.pack.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	WebDriver driver = null;
	String filePath = "/home/ruchi/Videos" + File.separator;
    public static int m_count = 0;
	public static String workDir;
	public static String tcName;
	public static ArrayList al;

	    public TestListener() {
	        workDir = System.getProperty("user.dir");
	        System.out.println("Work Dir is :" +workDir);
	        al = new ArrayList();
	    }
    
	/*
	 * Overridden method onTestFailure to take screen shots of the Failed Test Cases.
	 * 
	 */
	@Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    	createFile(tcName + ".dif", "Test " + tcName + " was a FAILURE");
         al.add(tcName + "|FAILURE");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName);
    }
    
	/*
	 * takeScreenShot Method is used for taking the screen shots using Webdriver.
	 * 
	 * 
	 */
    public void takeScreenShot(String methodName) {
    	//get the driver
    	driver=SeleniumLibs.getDriver();
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
				FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
    
    /* 
     * Default implementation for
     * org.testng.ISuiteListener#onFinish(org.testng.ISuite)
     */
    @Override
	public void onFinish(ITestContext context) {
		createFileFromAL(al, "testExecutionStatus.log");
	}
  
    public void onTestStart(ITestResult result) {   }
  

    public void onTestSuccess(ITestResult result) { 
//    	System.out.println("***** SUCESS "+result.getName()+" test has passed *****");
//    	createFile(tcName + ".suc", "Test " + tcName + " was a SUCESS");
//         al.add(tcName + "|FAILURE");
    }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }
    
    @Override
    public void onStart(ITestContext context) {  
    	 tcName = context.getCurrentXmlTest().getName();
    }
    
    /**
     * Creates a file for the string input
     * @param fileName
     * @param contents
     */
    private void createFile(String fileName, String contents) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(contents);
            out.close();
        } catch (Exception ex) {
        }
    }
    
    /**
     * Creates a file with the contents of the arraylist passed
     * @param alTC
     * @param fileName
     */
    private void createFileFromAL(ArrayList alTC, String fileName) {
        StringBuffer buff = new StringBuffer();
        try {
            if (alTC != null && alTC.size() >= 0) {
                for (int ii = 0; ii < al.size(); ii++) {
                    buff.append((String) alTC.get(ii)).append("\n");
                }
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(fileName));
                out.write(buff.toString());
                out.close();
            }

        } catch (Exception ex) {
        }
    }
} 