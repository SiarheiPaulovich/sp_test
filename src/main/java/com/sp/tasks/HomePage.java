package com.sp.tasks;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private static final Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	String baseUrl;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		baseUrl = "https://mail.ru/";
	}
	
	public void openPage(){
		driver.get(baseUrl);
	}
		
	public boolean isLogged(){
		
		String display = driver.findElement(By.id("PH_authView")).getAttribute("style");
		if(display.trim().equals("display: none;")) return false;
		return true;
	}
	
	public void logout(){
		if(! isLogged()) return;
		driver.findElement(By.id("PH_logoutLink")).click();
	}
	
	public MailPage login(String username, String password, boolean saveLogin){
		WebElement loginTextField = null;
		try{
			loginTextField = driver.findElement(By.id("mailbox__login"));
		}catch(NoSuchElementException e){
			log.error("Could not find loginTextField by id=mailbox__login");
			return null;
		}
		loginTextField.clear();
		loginTextField.sendKeys(username);
		
		WebElement passwordTextField = null; 
		try{
			passwordTextField = driver.findElement(By.id("mailbox__password"));
		}catch(NoSuchElementException e){
			log.error("Could not find passwordTextField by id=mailbox__password");
			return null;
		}
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		
		if(saveLogin) driver.findElement(By.id("ph_saveLogin")).click();
		try{
			driver.findElement(By.id("mailbox__auth__button")).click();
		}catch(NoSuchElementException e){
			log.error("Could not find submit button by id=mailbox__auth__button");
			return null;
		}
		
		if(isLogged()) return new MailPage(driver);
		else return null;
	}
	
	
}
