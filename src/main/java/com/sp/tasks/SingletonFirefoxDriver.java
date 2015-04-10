package com.sp.tasks;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonFirefoxDriver {

	private static final Logger log = Logger.getLogger(SingletonFirefoxDriver.class);
	private static WebDriver driver;
	
	static{
		driver = new FirefoxDriver();
		log.info("FirefoxDriver has been initialized");
	}
	private SingletonFirefoxDriver(){
	}
	
	public static synchronized WebDriver getInstance(int implicitTimeoutInSeconds){
		driver.manage().timeouts().implicitlyWait(implicitTimeoutInSeconds, TimeUnit.SECONDS);
		return driver;
	}
}
