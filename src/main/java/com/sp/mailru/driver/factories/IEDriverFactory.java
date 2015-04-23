package com.sp.mailru.driver.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sp.mailru.constants.TestEnvConstants;
import com.sp.mailru.driver.WebDriverType;

public class IEDriverFactory implements DriverFactory{
	
	private static final String IE_SYS_PROP_NAME = "webdriver.ie.driver";

	private WebDriver driver;
	
	private static final WebDriverType driverType = WebDriverType.IE;

	public WebDriver getInstance() {
		if(driver == null){
			synchronized(this){
				if(driver == null){
					System.setProperty(IE_SYS_PROP_NAME, TestEnvConstants.IE_DRIVER_LOCAL_PATH);
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					driver = new InternetExplorerDriver(caps);
				}
			}
		}
		return driver;
	}

	public WebDriverType getDriverType() {
		return driverType;
	}
}
