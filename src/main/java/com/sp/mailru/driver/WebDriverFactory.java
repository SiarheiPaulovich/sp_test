package com.sp.mailru.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverFactory {

	public static WebDriver getInstance(WebDriverType type){
		
		switch(type){		
			case HTML_UNIT:
				return new HtmlUnitDriver();
				
			case FIREFOX:
				return SingletonFirefoxDriver.getInstance();
				
			case CHROME:
				return new ChromeDriver();
				
			default:
				return null;
		}
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
	
	@SuppressWarnings("serial")
	public static class WrongWebDriverTypeException extends RuntimeException{
		
		private String wrongType;
		
		WrongWebDriverTypeException(String enteredType){
			wrongType = enteredType;
		}
		
		public String toString(){
			StringBuffer sb = new StringBuffer("Entered=[" + wrongType + "], correct_types=[");
			for(WebDriverType type : WebDriverType.values()){
				sb.append(type.name());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			return sb.toString();
		}
	}
}
