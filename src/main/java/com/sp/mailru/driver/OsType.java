package com.sp.mailru.driver;

import com.sp.mailru.exceptions.UnknownOsException;

public enum OsType {
	WIN, MAC;
	
private	static OsType os;

static{
	String osType = System.getProperty("os.name").toLowerCase();
	if(osType.contains("win")) os = WIN;
	else if(osType.contains("mac")) os = MAC;
	else throw new UnknownOsException(osType);
}

	public static OsType getOsType(){
		return os;
	}
}
