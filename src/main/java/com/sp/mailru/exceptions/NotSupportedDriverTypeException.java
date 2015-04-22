package com.sp.mailru.exceptions;

import com.sp.mailru.driver.WebDriverType;


@SuppressWarnings("serial")
public class NotSupportedDriverTypeException extends RuntimeException{
	
	private WebDriverType wrongType;
	
	public NotSupportedDriverTypeException(WebDriverType type){
		wrongType = type;
	}
	
	public String toString(){
		return "Type '" + wrongType + "' is not currently supported";
	}
}