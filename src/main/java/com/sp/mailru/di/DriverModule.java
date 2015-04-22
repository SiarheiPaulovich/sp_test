package com.sp.mailru.di;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.sp.mailru.constants.CommonConstants;
import com.sp.mailru.constants.TestEnvConstants;
import com.sp.mailru.driver.LocalDriverFactory;
import com.sp.mailru.pages.HomePage;

public class DriverModule extends AbstractModule{

	@Override
	protected void configure() {
		WebDriver wd = LocalDriverFactory.getInstance(System.getProperty(TestEnvConstants.DRIVER_TYPE_JVM_PARAMETER));
		wd.manage().window().maximize();
		wd.manage().timeouts().pageLoadTimeout(CommonConstants.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
		wd.manage().timeouts().implicitlyWait(CommonConstants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		bind(WebDriver.class).toInstance(wd);
		bind(HomePage.class).toInstance(new HomePage(wd));
	}
}
