package com.sp.mailru.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;


/**
 * 
 * Currently is not used
 *
 */
public class TestInjector {

	private static Injector injector;
	
	private static Module [] modules;
	
	public static Injector setupInjector(Module... modules) {
		
		if(modules.equals(TestInjector.modules)) 
			injector = Guice.createInjector(modules);
		
		return injector;
	}
	
	public static <T> T prepareObject(Class<T> _class, Module... modules) {
		if(modules != null) setupInjector(modules);
		return injector.getInstance(_class);
	}
	
}
