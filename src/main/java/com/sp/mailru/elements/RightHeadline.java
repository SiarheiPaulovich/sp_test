package com.sp.mailru.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Block(@FindBy(xpath = "//*[contains(@class, 'w-x-ph__col_right')]"))
public class RightHeadline extends HtmlElement {
	
	@FindBy(id = "mailbox__login")
	WebElement authLink;
	
	@FindBy(id = "PH_logoutLink")
	WebElement logoutLink;
	
	@FindBy(id = "PH_authView")
	HtmlElement logoutDisplay;
	
	public boolean isLogged() {
		return ! logoutDisplay.getAttribute("style").trim().equals("display: none;");
	}
	
	public void logout() {
		if (! isLogged()) return;
		logoutLink.click();
	}
	
}
