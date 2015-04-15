package com.sp.mailru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import com.google.inject.Inject;
import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.elements.AuthorizationForm;
import com.sp.mailru.elements.RightHeadline;

public class HomePage extends AbstractPage{

	private AuthorizationForm authorizationForm;
	
	private RightHeadline rightHeadline;

	@Inject
	public HomePage(WebDriver driver){
		this.driver = driver;
	}

	public void openPage(){
		driver.get(ProjectConstants.HOME_URL);
	}
	
	public void init(){
		PageFactory.initElements(new HtmlElementDecorator(driver), this);
	}
	
	public boolean isLogged() {
		return rightHeadline.isLogged();
	}

	public void logout() {
		rightHeadline.logout();
	}

	public MailPage login(String login, String password, boolean saveLogin) {
		authorizationForm.login(login, password, saveLogin);
		return new MailPage(driver);
	}

}
