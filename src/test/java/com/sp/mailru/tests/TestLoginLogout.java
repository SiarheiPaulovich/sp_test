package com.sp.mailru.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sp.mailru.pages.HomePage;
import com.sp.mailru.pages.ProjectConstants;
import com.sp.mailru.pages.SingletonFirefoxDriver;

public class TestLoginLogout{

	private static final Logger LOG = Logger.getLogger(TestLoginLogout.class);
	private WebDriver driver;
	HomePage homePage;

	@DataProvider(name = "loginsTestProvider")
	public Object[][] loadTestData() {
		return new Object[][] { { "bad_mail", "bad_password", false }, { "TrueTestMail", "TruePassword", true } };
	}

	@BeforeTest
	public void init() {
		driver = SingletonFirefoxDriver.getInstance(5);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void openHomePage() {
		driver.get(ProjectConstants.HOME_URL);
	}

	@Test(dataProvider = "loginsTestProvider")
	public void testLogin(String username, String password, boolean expectedToBeBeLogged) {
		LOG.info("Login test started [" + username + "," + password + "," + expectedToBeBeLogged + "]");
		Assert.assertTrue(driver.getTitle().contains("Mail.Ru:"));
		if (homePage.isLogged())
			homePage.logout();
		homePage.login(username, password, false);
		// use assertFalse;
		// use softAssert
		Assert.assertEquals(homePage.isLogged(), expectedToBeBeLogged);
		LOG.info("Login test finished");
	}

	@Test(dependsOnMethods = { "testLogin" })
	public void testLogout() {
		LOG.info("Logout test started");

		// fake assertion - if previous test method will be passed, so user is
		// logged in, else this test will be skipped due to dependents
		Assert.assertTrue(homePage.isLogged(), "User is not logged to perform logout");
		homePage.logout();
		Assert.assertFalse(homePage.isLogged(), "User is still logged after logout");
		LOG.info("Logout test finished");
	}
}
