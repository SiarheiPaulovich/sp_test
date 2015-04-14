package com.sp.mailru.pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

	protected WebDriver driver;
	
	public abstract void init();
	
	public boolean checkPage(String partOfPageTitle){
		return driver.getTitle().contains(partOfPageTitle);
	}
}
