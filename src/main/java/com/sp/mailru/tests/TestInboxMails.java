package com.sp.mailru.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.di.DriverModule;
import com.sp.mailru.entities.InboxMailListItem;
import com.sp.mailru.pages.HomePage;
import com.sp.mailru.pages.MailPage;

@Guice(modules = {DriverModule.class})
public class TestInboxMails{

	@Inject
	private WebDriver driver;

	@Inject
	private HomePage homePage;
		
	@Parameters({"pageTimeout"})
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds){
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage.openPage();
		Assert.assertTrue(homePage.checkPage(ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE));
		homePage.init();
	}
	
	@AfterSuite
	public void shutdown(){
		driver.quit();
	}
		
	@Parameters({"username","password"})
	@Test
	public void testWelcomeMail(String username, String password){
		if(homePage.isLogged()) homePage.logout();
		Assert.assertFalse(homePage.isLogged());
		MailPage mailPage = homePage.login(username, password, false);
		Assert.assertTrue(mailPage.checkPage(ProjectConstants.MAIL_PAGE_IDENTIFIER_BY_TITLE));
		mailPage.init();
		Assert.assertTrue(mailPage.isLogged());
		
		List<WebElement> inboxMailLinks = mailPage.getInboxMailLinksList();
		boolean receivedWelcomeMail = false;
		for(WebElement link : inboxMailLinks){
			InboxMailListItem mail = mailPage.parseInboxMailLinkItem(link);
			if(mail.getFrom().contains("welcome@corp.mail.ru") && mail.getTitle().contains("Добро пожаловать в Mail.Ru")){
				receivedWelcomeMail = true;
			}
		}
		Assert.assertTrue(receivedWelcomeMail,"Didn't receive a Welcome-mail");
	}
}
