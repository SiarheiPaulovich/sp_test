package com.sp.mailru.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.sp.mailru.entities.InboxMailListItem;

//Use page object pattern (you can start reading of yandex elements http://habrahabr.ru/company/yandex/blog/158787/).
//Page object should include repeated parts of page and other elements
public class MailPage {

	private static final Logger LOG = Logger.getLogger(MailPage.class); 
	
	private WebDriver driver;

	@FindBy(how = How.CLASS_NAME, using = "datalist__body")
	private WebElement datalistBody;
	
	public MailPage(WebDriver driver) {
		this.driver = driver;
	}

	// Using @FindBy
	public List<WebElement> getInboxMailLinksList() {
/*
		WebElement datalistBody = null;
		try {
			datalistBody = driver.findElement(By.className("b-datalist__body"));
		} catch (NoSuchElementException e) {
			LOG.error("Could not find datalistBody by class=b-datalist__body");
			return null;
		}
*/
		List<WebElement> elementLinks = datalistBody
				.findElements(By
						.xpath("//div[contains(@class, 'b-datalist__item')]/div[@class='b-datalist__item__body']/a[@class='js-href b-datalist__item__link']"));

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
