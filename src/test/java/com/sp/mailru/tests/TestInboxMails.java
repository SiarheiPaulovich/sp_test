package com.sp.mailru.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sp.mailru.entities.InboxMailListItem;
import com.sp.mailru.pages.HomePage;
import com.sp.mailru.pages.MailPage;
import com.sp.mailru.pages.SingletonFirefoxDriver;


public class TestInboxMails{

	private static final Logger LOG = Logger.getLogger(TestInboxMails.class);

	private WebDriver driver;

	private HomePage homePage;
		
	@Parameters({"pageTimeout"})
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds){
		driver = SingletonFirefoxDriver.getInstance();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}
	
	@AfterSuite
	public void shutdown(){
		driver.quit();
	}
		
	@Parameters({"username","password"})
	@Test
	public void testWelcomeMail(String username, String password){
		LOG.info("Welcome mail test started");
		if(homePage.isLogged()) homePage.logout();
		Assert.assertFalse(homePage.isLogged());
		MailPage mailPage = homePage.login(username, password, false);
		Assert.assertTrue(homePage.isLogged());
		
		List<WebElement> inboxMailLinks = mailPage.getInboxMailLinksList();
		boolean receivedWelcomeMail = false;
		for(WebElement link : inboxMailLinks){
			InboxMailListItem mail = mailPage.parseInboxMailLinkItem(link);
			if(mail.getFrom().contains("welcome@corp.mail.ru") && mail.getTitle().contains("Добро пожаловать в Mail.Ru")){
				receivedWelcomeMail = true;
			}
		}
		Assert.assertTrue(receivedWelcomeMail,"Didn't receive a Welcome-mail");
		LOG.info("Welcome mail test finished");
	}
}
