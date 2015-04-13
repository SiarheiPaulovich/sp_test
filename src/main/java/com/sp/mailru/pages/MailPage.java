package com.sp.mailru.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sp.mailru.entities.InboxMailListItem;

public class MailPage {

	private static final Logger LOG = Logger.getLogger(MailPage.class);
	
	@FindBy(how = How.CLASS_NAME, using = "b-datalist__body")
	private WebElement datalistBody;
	
	public MailPage(WebDriver driver) throws IllegalStateException{
		if(! driver.getTitle().contains(ProjectConstants.MAIL_PAGE_IDENTIFIER_BY_TITLE)){
			LOG.error("Loaded page couldn't be identified by title: " + ProjectConstants.MAIL_PAGE_IDENTIFIER_BY_TITLE);
			LOG.error("Page: " + driver.getCurrentUrl());
			throw new IllegalStateException("page couldn't be identified");
		}
		PageFactory.initElements(driver, this);
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
