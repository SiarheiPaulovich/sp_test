package com.sp.tasks;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailPage {
	
	private static final Logger log = Logger.getLogger(MailPage.class);
	private WebDriver driver;
	
	public MailPage(WebDriver driver){
		this.driver = driver;
	}
	
	public List<WebElement> getInboxMailLinksList(){
		
		WebElement datalistBody = null;
		try{
			datalistBody = driver.findElement(By.className("b-datalist__body"));
		}catch(NoSuchElementException e){
			log.error("Could not find datalistBody by class=b-datalist__body");
			return null;
		}
		
		List<WebElement> elementLinks = datalistBody.findElements(
				By.xpath("//div[contains(@class, 'b-datalist__item')]/div[@class='b-datalist__item__body']/a[@class='js-href b-datalist__item__link']"));
		
		
		return elementLinks;
	}
	
	class InboxMail{
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
		
		public InboxMail(WebElement inboxMailLink){
			title = inboxMailLink.getAttribute("data-subject");
			from = inboxMailLink.getAttribute("title");
			url = inboxMailLink.getAttribute("href");
		}
	}
}
