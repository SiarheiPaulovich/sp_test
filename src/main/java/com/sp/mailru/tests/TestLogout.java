package com.sp.mailru.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.driver.SingletonFirefoxDriver;
import com.sp.mailru.pages.HomePage;

public class TestLogout {

	private WebDriver driver;
	
	private HomePage homePage;
	
	@Parameters({"pageTimeout"})
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds) {
		driver = SingletonFirefoxDriver.getInstance();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.checkPage(ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE));
		homePage.init();
	}
	
	@Parameters({"username","password"})
	@Test
	public void testLogout(String username, String password) {
		if (! homePage.isLogged()) homePage.login(username, password, false);
		Assert.assertTrue(homePage.isLogged(), "User is still not logged to perform logout");
		homePage.logout();
		Assert.assertFalse(homePage.isLogged());
	}
}
