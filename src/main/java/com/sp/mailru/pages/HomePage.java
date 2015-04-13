package com.sp.mailru.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import com.sp.mailru.elements.AuthorizationForm;

public class HomePage {

	private static final Logger LOG = Logger.getLogger(HomePage.class);
	
	private WebDriver driver;

	private AuthorizationForm authorizationForm;

	@FindBy(id = "PH_authView")
	private HtmlElement logoutDisplay;

	@FindBy(id = "PH_logoutLink")
	private HtmlElement logoutLink;

	public HomePage(WebDriver driver) throws IllegalStateException{
		this.driver = driver;
		driver.get(ProjectConstants.HOME_URL);
		if(! driver.getTitle().contains(ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE)){
			LOG.error("Loaded page couldn't be identified by title: " + ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE);
			LOG.error("Page: " + driver.getCurrentUrl());
			throw new IllegalStateException("page couldn't be identified");
		}
		PageFactory.initElements(new HtmlElementDecorator(driver), this);
	}

	public boolean isLogged() {
		return ! logoutDisplay.getAttribute("style").trim().equals("display: none;");
	}

	public void logout() {
		if (! isLogged()) return;
		logoutLink.click();
	}

	public MailPage login(String login, String password, boolean saveLogin) {
		authorizationForm.login(login, password, saveLogin);
		return new MailPage(driver);
	}

}
