package com.sp.mailru.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
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

public class TestLogin {

	private static final Logger LOG = Logger.getLogger(TestLogin.class);

	private WebDriver driver;

	private HomePage homePage;

	@DataProvider(name = "loginsTestProvider")
	public Object[][] loadTestData() {
		return new Object[][] { { "bad_mail", "bad_password", false }, { "TrueTestMail", "TruePassword", true } };
	}

	@Parameters({ "pageTimeout" })
	@BeforeTest
	public void init(@Optional("10") int timeoutInSeconds) {
		driver = SingletonFirefoxDriver.getInstance();
		driver.manage().timeouts()
				.implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void openHomePage() {
		driver.get(ProjectConstants.HOME_URL);
	}

	@Test(dataProvider = "loginsTestProvider")
	public void testLogin(String username, String password,
			boolean expectedToBeBeLogged) {
		LOG.info("Login test started [" + username + "," + password + ","
				+ expectedToBeBeLogged + "]");
		SoftAssert softAssert = new SoftAssert();
		if (homePage.isLogged())
			homePage.logout();
		softAssert.assertFalse(homePage.isLogged());
		
		try{
			homePage.login(username, password, false);
		}catch(IllegalStateException e){
			if(expectedToBeBeLogged) throw e;
		}
		softAssert.assertEquals(homePage.isLogged(), expectedToBeBeLogged);
		softAssert.assertAll();
		LOG.info("Login test finished");
	}

}
