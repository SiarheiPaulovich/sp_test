package com.sp.mailru.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sp.mailru.pages.HomePage;
import com.sp.mailru.pages.ProjectConstants;
import com.sp.mailru.pages.SingletonFirefoxDriver;

public class TestLoginLogout{

	private static final Logger LOG = Logger.getLogger(TestLoginLogout.class);
	private WebDriver driver;
	HomePage homePage;

	@DataProvider(name = "loginsTestProvider")
	public Object[][] loadTestData() {
		return new Object[][] { { "bad_mail", "bad_password", true }, { "TrueTestMail", "TruePassword", true } };
	}

	@Parameters({"pageTimeout"})
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds) {
		driver = SingletonFirefoxDriver.getInstance();
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
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
		//SoftAssert softAssert = new SoftAssert();
		Assert.assertEquals(homePage.isLogged(), expectedToBeBeLogged);
		LOG.info("Login test finished");
	}

	@Test(dependsOnMethods = { "testLogin" })
	public void testLogout() {
		LOG.info("Logout test started");

		homePage.logout();
		Assert.assertFalse(homePage.isLogged(), "User is still logged after logout");
		LOG.info("Logout test finished");
	}
}
