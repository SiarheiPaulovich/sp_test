package com.sp.mailru.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.inject.Inject;
import com.sp.mailru.constants.ProjectConstants;
import com.sp.mailru.di.DriverModule;
import com.sp.mailru.pages.HomePage;

@Guice(modules = {DriverModule.class})
public class TestLogin {

	@Inject
	WebDriver driver;

	@Inject
	HomePage homePage;
	
	@DataProvider(name = "loginsTestProvider")
	public Object[][] loadTestData() {
		return new Object[][] { { "bad_mail", "bad_password", false }, { "TrueTestMail", "TruePassword", true } };
	}

	@BeforeMethod
	public void openHomePage() {
		homePage.openPage();
		Assert.assertTrue(homePage.checkPage(ProjectConstants.HOME_PAGE_IDENTIFIER_BY_TITLE));
	}

	@Test(dataProvider = "loginsTestProvider")
	public void testLogin(String username, String password,	boolean expectedToBeBeLogged) {
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
	}

}
