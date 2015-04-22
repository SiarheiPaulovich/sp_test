package com.sp.mailru.driver.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sp.mailru.driver.WebDriverType;

public class FirefoxDriverFactory implements DriverFactory{

	private WebDriver driver;
	
	private static final WebDriverType driverType = WebDriverType.FIREFOX;

	public WebDriver getInstance() {
		if(driver == null){
			synchronized(this){
				if(driver == null){
					driver = new FirefoxDriver();
				}
			}
		}
		return driver;
	}

	public WebDriverType getDriverType() {
		return driverType;
	}
}
