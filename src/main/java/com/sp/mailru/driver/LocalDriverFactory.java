package com.sp.mailru.driver;

import org.openqa.selenium.WebDriver;

import com.sp.mailru.driver.factories.ChromeDriverFactory;
import com.sp.mailru.driver.factories.DriverFactory;
import com.sp.mailru.driver.factories.FirefoxDriverFactory;
import com.sp.mailru.driver.factories.IEDriverFactory;
import com.sp.mailru.exceptions.NotSupportedDriverTypeException;
import com.sp.mailru.exceptions.WrongWebDriverTypeException;

public class LocalDriverFactory {

	private static ThreadLocal<DriverFactory> driverFactory = new ThreadLocal<DriverFactory>();
	
	public static WebDriver getInstance(WebDriverType type){
		
		if (driverFactory.get() == null) {
			driverFactory.set(LocalDriverFactory.createDriverFactory(type));
		}else{
			if(type.equals(driverFactory.get().getDriverType())){
				return driverFactory.get().getInstance();
			}else{
				driverFactory.get().getInstance().quit();
				driverFactory.set(LocalDriverFactory.createDriverFactory(type));
			}
		}
		return driverFactory.get().getInstance();
	}
	
	public static WebDriver getInstance(String type){
		
		WebDriver driver = null;
		try{
			driver = getInstance(WebDriverType.valueOf(type));
		}catch(IllegalArgumentException e){
			throw new WrongWebDriverTypeException(type);
		}
		
		return driver;
	}
	
	protected static DriverFactory createDriverFactory(WebDriverType type){
		switch(type){		
			case FIREFOX: return new FirefoxDriverFactory();
			case CHROME: return new ChromeDriverFactory();
			case IE: return new IEDriverFactory();
			default: throw new NotSupportedDriverTypeException(type);
		}
	}

}
