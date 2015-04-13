package com.sp.mailru.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@SuppressWarnings("deprecation")
@Block(@FindBy(how = How.CLASS_NAME, using = "mailbox__class"))
public class AuthorizationForm extends HtmlElement{

	@FindBy(how = How.ID, using = "mailbox__login")
    private WebElement loginField;

    @FindBy(how = How.ID, using = "mailbox__password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "ph_saveLogin")
    private WebElement saveLoginCheckbox;
    
    @FindBy(how = How.ID, using = "mailbox__auth__button")
    private WebElement submitButton;

    public void login(String login, String password, boolean saveLogin) {
    	loginField.clear();
        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
        if(saveLogin) saveLoginCheckbox.click();
        submitButton.click();
    }
}
