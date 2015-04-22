package com.sp.mailru.driver.factories;

import org.openqa.selenium.WebDriver;

import com.sp.mailru.driver.WebDriverType;

public interface DriverFactory {

	public WebDriver getInstance();
	
	public WebDriverType getDriverType();
}
