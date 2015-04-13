package com.sp.mailru.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.sp.mailru.elements.AuthorizationForm;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class HomePage {

	private static final Logger LOG = Logger.getLogger(HomePage.class);
	
	private WebDriver driver;
	
	private AuthorizationForm authorizationForm;
	
	@FindBy(how = How.ID, using = "PH_authView")
	private WebElement logoutDisplay;
	
	@FindBy(how = How.ID, using = "PH_logoutLink")
	private WebElement logoutLink;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		HtmlElementLoader.populatePageObject(this, driver);
	}

	public boolean isLogged() {
		String display = logoutDisplay.getAttribute("style");
		return ! display.trim().equals("display: none;");
	}

	public void logout() {
		if (!isLogged()) return;
		logoutLink.click();
	}

	// All elements via @FindBy. This method should a combination of another
	// three: setUsername, setPassword, clickLogin(). It provides you
	// flexibility for another tests: check error message if username is empty, etc.
	// Bad idea to return null - the better way is to create your custom exceptions, and log error.
	public MailPage login(String login, String password, boolean saveLogin) {
		/*
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

		*/
		authorizationForm.login(login, password, saveLogin);
		
		if (isLogged())
			return new MailPage(driver);
		else
			return null;
	}

}
