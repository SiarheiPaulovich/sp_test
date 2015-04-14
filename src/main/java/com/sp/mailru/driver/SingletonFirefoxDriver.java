package com.sp.mailru.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonFirefoxDriver {

	private static WebDriver driver;

	private SingletonFirefoxDriver() {}

	public static WebDriver getInstance() {
		
		if(driver == null){
			synchronized(SingletonFirefoxDriver.class){
				if(driver == null){
					driver = new FirefoxDriver();
				}
			}
		}
		
		return driver;
	}
}
