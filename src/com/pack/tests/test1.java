package com.pack.tests;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class test1 {
	public WebDriver driver = null;

	@org.testng.annotations.BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
	}

	@Test
	public void test() {
		driver.get("http://google.com");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
