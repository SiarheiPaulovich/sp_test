package com.sp.tasks;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Use page object pattern (you can start reading of yandex elements http://habrahabr.ru/company/yandex/blog/158787/).
//Page object should include repeated parts of page and other elements
public class MailPage {

	// Constant - UPPERCASE
	private static final Logger log = Logger.getLogger(MailPage.class); // Empty
																		// string
																		// between
																		// variables
	private WebDriver driver;

	public MailPage(WebDriver driver) {
		this.driver = driver;
	}

	// Using @FindBy
	public List<WebElement> getInboxMailLinksList() {

		WebElement datalistBody = null;
		try {
			datalistBody = driver.findElement(By.className("b-datalist__body"));
		} catch (NoSuchElementException e) {
			log.error("Could not find datalistBody by class=b-datalist__body");
			return null;
		}

		List<WebElement> elementLinks = datalistBody
				.findElements(By
						.xpath("//div[contains(@class, 'b-datalist__item')]/div[@class='b-datalist__item__body']/a[@class='js-href b-datalist__item__link']"));

		return elementLinks;
	}

	// If I understood you correctly - it should be business object, if yes move
	// it in another package and class. Don't create inner class here. MailPage
	// just can have method to create this BO
	class InboxMail {
		private String title;
		private String from;
		private String url;

		public String getUrl() {
			return url;
		}

		public String getTitle() {
			return title;
		}

		public String getFrom() {
			return from;
		}

		public InboxMail(WebElement inboxMailLink) {
			title = inboxMailLink.getAttribute("data-subject");
			from = inboxMailLink.getAttribute("title");
			url = inboxMailLink.getAttribute("href");
		}
	}
}
