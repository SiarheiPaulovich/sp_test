package com.sp.mailru.exceptions;


@SuppressWarnings("serial")
public class UnknownOsException extends RuntimeException{
	
	private String wrongType;
	
	public UnknownOsException(String type){
		wrongType = type;
	}
	
	public String toString(){
		return "Type '" + wrongType + "' is not currently supported";
	}
}
