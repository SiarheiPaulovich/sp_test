package com.sp.mailru.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.di.DriverModule;
import com.sp.mailru.pages.HomePage;

@Guice(modules = {DriverModule.class})
public class TestLogout {

	@Inject
	private WebDriver driver;
	
	@Inject
	private HomePage homePage;
	
	@BeforeMethod
	public void openHomePage() {
		homePage.openPage();
		Assert.assertTrue(homePage.checkPage(ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE));
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
