package com.sp.mailru.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// It should be reimplemented. If you have desire I will tell you about correct implementations of Singleton.
// You attempted to create thread safe Singleton but it isn't so.
// First of all, driver is a static field - just one instance will be created during test execution
// Use lazy initialization to create instance with double check and synchronization by Object
public class SingletonFirefoxDriver {

	private static final Logger log = Logger.getLogger(SingletonFirefoxDriver.class);
	private static WebDriver driver;

	static {
		driver = new FirefoxDriver();
		log.info("FirefoxDriver has been initialized");
	}

	private SingletonFirefoxDriver() {
	}

	public static synchronized WebDriver getInstance(int implicitTimeoutInSeconds) {
		driver.manage().timeouts().implicitlyWait(implicitTimeoutInSeconds, TimeUnit.SECONDS);
		return driver;
	}
}
