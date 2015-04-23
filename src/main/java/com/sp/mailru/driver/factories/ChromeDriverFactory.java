package com.sp.mailru.driver.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sp.mailru.constants.TestEnvConstants;
import com.sp.mailru.driver.OsType;
import com.sp.mailru.driver.WebDriverType;

public class ChromeDriverFactory implements DriverFactory{

	private static final String CHROME_SYS_PROP_NAME = "webdriver.chrome.driver";
	
	private WebDriver driver;
	
	private static final WebDriverType driverType = WebDriverType.CHROME;

	public WebDriver getInstance() {
		if(driver == null){
			synchronized(this){
				if(driver == null){
					switch(OsType.getOsType()){
					case WIN:
						System.setProperty(CHROME_SYS_PROP_NAME, TestEnvConstants.CHROME_DRIVER_LOCAL_PATH_WIN);
						break;
					
					case MAC:
						System.setProperty(CHROME_SYS_PROP_NAME, TestEnvConstants.CHROME_DRIVER_LOCAL_PATH_MAC);
						break;
						
					default:
						throw new RuntimeException("Unknown CHROME driver installation path for OS: " + OsType.getOsType());
					}
					
					driver = new ChromeDriver();
				}
			}
		}
		return driver;
	}

	public WebDriverType getDriverType() {
		return driverType;
	}
}