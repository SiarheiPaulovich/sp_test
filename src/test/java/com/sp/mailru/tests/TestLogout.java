package com.sp.mailru.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sp.mailru.pages.HomePage;
import com.sp.mailru.pages.SingletonFirefoxDriver;

public class TestLogout {

	private static final Logger LOG = Logger.getLogger(TestLogin.class);
	
	private WebDriver driver;
	
	private HomePage homePage;
	
	@Parameters({"pageTimeout"})
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds) {
		driver = SingletonFirefoxDriver.getInstance();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}
	
	@Parameters({"username","password"})
	@Test
	public void testLogout(String username, String password) {
		LOG.info("Logout test started");
		if (! homePage.isLogged()) homePage.login(username, password, false);
		Assert.assertTrue(homePage.isLogged(), "User is still not logged to perform logout");
		homePage.logout();
		Assert.assertFalse(homePage.isLogged());
		LOG.info("Logout test finished");
	}
}
