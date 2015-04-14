package com.sp.mailru.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import com.sp.mailru.elements.RightHeadline;
import com.sp.mailru.entities.InboxMailListItem;

public class MailPage extends AbstractPage{

	private RightHeadline rightHeadline;
	
	@FindBy(how = How.CLASS_NAME, using = "b-datalist__body")
	private WebElement datalistBody;
	
	public MailPage(WebDriver driver){
		this.driver = driver;
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
	
	public List<WebElement> getInboxMailLinksList() {
		List<WebElement> elementLinks = datalistBody.findElements(
						By.xpath("//div[contains(@class, 'b-datalist__item')]/div[@class='b-datalist__item__body']/a[@class='js-href b-datalist__item__link']"));
		return elementLinks;
	}

	public InboxMailListItem parseInboxMailLinkItem(WebElement inboxMailLink) {
		return new InboxMailListItem(
				inboxMailLink.getAttribute("data-subject"),
				inboxMailLink.getAttribute("title"),
				inboxMailLink.getAttribute("href")
				);
	}
}
