package com.sp.mailru.di;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.driver.WebDriverFactory;

public class DriverModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(WebDriver.class).toInstance(WebDriverFactory.getInstance(System.getProperty(ProjectConstants.DRIVER_TYPE_JVM_PARAMETER)));
	}
}
