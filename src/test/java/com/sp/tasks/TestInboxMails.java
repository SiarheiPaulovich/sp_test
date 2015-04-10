package com.sp.tasks;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestInboxMails extends Assert{

	private static final Logger log = Logger.getLogger(TestInboxMails.class);
	private WebDriver driver;
	HomePage homePage;
		
	@BeforeTest
	public void init(){
		driver = SingletonFirefoxDriver.getInstance(5);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}
	
	@BeforeMethod
	public void openHomePage(){
		homePage.openPage();
	}
	
	@AfterSuite
	public void shutdown(){
		driver.quit();
	}
		
	@Parameters({"username","password"})
	@Test
	public void testWelcomeMail(String username, String password){
		log.info("Welcome mail test started");
		assertTrue(driver.getTitle().contains("Mail.Ru:"));
		if(homePage.isLogged()) homePage.logout();
		MailPage mailPage = homePage.login(username, password, false);
		assertEquals(homePage.isLogged(),true);
		
		List<WebElement> inboxMailLinks = mailPage.getInboxMailLinksList();
		boolean receivedWelcomeMail = false;
		for(WebElement link : inboxMailLinks){
			MailPage.InboxMail mail = mailPage.new InboxMail(link);
			
			if(mail.getFrom().contains("welcome@corp.mail.ru") && mail.getTitle().contains("Добро пожаловать в Mail.Ru")){
				receivedWelcomeMail = true;
			}
		}
		assertTrue(receivedWelcomeMail,"Didn't receive a Welcome-mail");
		log.info("Welcome mail test finished");
	}
}
