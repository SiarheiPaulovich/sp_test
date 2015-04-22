package com.sp.mailru.exceptions;

import com.sp.mailru.driver.WebDriverType;

@SuppressWarnings("serial")
public class WrongWebDriverTypeException extends RuntimeException{
	
	private String wrongType;
	
	public WrongWebDriverTypeException(String enteredType){
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
