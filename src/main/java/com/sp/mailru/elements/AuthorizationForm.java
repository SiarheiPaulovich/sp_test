package com.sp.mailru.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Block(@FindBy(xpath = "//*[contains(@class, 'mailbox__auth')]"))
public class AuthorizationForm extends HtmlElement {

	@FindBy(id = "mailbox__login")
	private WebElement loginField;

	@FindBy(id = "mailbox__password")
	private WebElement passwordField;

	@FindBy(id = "mailbox__auth__remember__checkbox")
	private WebElement saveLoginCheckbox;

	@FindBy(id = "mailbox__auth__button")
	private WebElement submitButton;

	public void login(String login, String password, boolean saveLogin) {
		loginField.clear();
		loginField.sendKeys(login);
		passwordField.clear();
		passwordField.sendKeys(password);
		if (saveLogin)
			saveLoginCheckbox.click();
		submitButton.click();
	}
}
