package com.sp.mailru.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Use page object pattern (you can start reading of yandex elements http://habrahabr.ru/company/yandex/blog/158787/).
// Page object should include repeated parts of page and other elements
public class HomePage {

	private static final Logger LOG = Logger.getLogger(HomePage.class);
	
	private WebDriver driver;
	
	private String baseUrl; 

	public HomePage(WebDriver driver) {
		this.driver = driver;
		baseUrl = ProjectConstants.HOME_URL;
	}

	// driver.find... should be replaced with element that will be initialized
	// using @FindBy
	public boolean isLogged() {
		String display = driver.findElement(By.id("PH_authView")).getAttribute("style");
		return ! display.trim().equals("display: none;");
	}

	public void logout() {
		if (!isLogged())
			return;
		driver.findElement(By.id("PH_logoutLink")).click();
	}

	// All elements via @FindBy. This method should a combination of another
	// three: setUsername, setPassword, clickLogin(). It provides you
	// flexibility for another tests: check error message if username is empty, etc.
	// Bad idea to return null - the better way is to create your custom exceptions, and log error.
	public MailPage login(String username, String password, boolean saveLogin) {
		WebElement loginTextField = null;
		try {
			loginTextField = driver.findElement(By.id("mailbox__login"));
		} catch (NoSuchElementException e) {
			LOG.error("Could not find loginTextField by id=mailbox__login");
			return null;
		}
		loginTextField.clear();
		loginTextField.sendKeys(username);

		WebElement passwordTextField = null;
		try {
			passwordTextField = driver.findElement(By.id("mailbox__password"));
		} catch (NoSuchElementException e) {
			LOG.error("Could not find passwordTextField by id=mailbox__password");
			return null;
		}
		passwordTextField.clear();
		passwordTextField.sendKeys(password);

		if (saveLogin)
			driver.findElement(By.id("ph_saveLogin")).click();
		try {
			driver.findElement(By.id("mailbox__auth__button")).click();
		} catch (NoSuchElementException e) {
			LOG.error("Could not find submit button by id=mailbox__auth__button");
			return null;
		}

		if (isLogged())
			return new MailPage(driver);
		else
			return null;
	}

}
