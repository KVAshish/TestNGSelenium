package com.pack.tests;

import org.testng.annotations.Test;

import com.pack.common.QLogger;
import com.pack.common.SeleniumLibs;
import com.pack.common.VideoRecord;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.testng.annotations.AfterClass;

public class helloTest {

	 protected static String strCD = System.getProperty("user.dir");
	 protected static String fileseparator = System.getProperty("file.separator");
	 protected static String logLevel;
	VideoRecord vd = new VideoRecord();
//	QLogger qlogger = new QLogger(strCD, "test"  + ".log", logLevel);
	//ResourceBundle rb = ResourceBundle.getBundle("helloTest", Locale.US);

	@BeforeClass
	public void beforeClass() throws Exception {

		System.out.println("Before test");
		System.out.println(strCD);

	}

	@Test()
	public void test12()throws Exception {
//		qlogger.log(Level.INFO, "Entring Test case", ": = Test");
		//rb.getString(url);
		System.out.println("sad");
		vd.startRecording("testName13");
		SeleniumLibs.startSelenium("http://flipkart.com");
		String x = SeleniumLibs.getPageSource();
		vd.stopRecording();
		if (x.contains("Flipkart")) {
			System.out.println("Passed");
			assert true;
		} else {
			System.out.println("Failed");
//			qlogger.log(Level.INFO, "Test case failed", ": = Test");
			assert false;
		}

	}

	@AfterClass
	public void afterClass() {
		System.out.println("After");
		SeleniumLibs.stopSelenium();
	}

}
