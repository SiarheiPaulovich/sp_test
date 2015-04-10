package com.sp.tasks;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLoginLogout extends Assert{

	private static final Logger log = Logger.getLogger(TestLoginLogout.class);
	private WebDriver driver;
	HomePage homePage;
	
	@DataProvider(name = "loginsTestProvider")
	public Object[][] loadTestData(){
		return new Object[][]{
				{"bad_mail","bad_password",false},
				{"TrueTestMail","TruePassword",true}	
		};
	}
	
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
		
	
	@Test(dataProvider = "loginsTestProvider")
	public void testLogin(String username, String password, boolean expectedToBeBeLogged){
		log.info("Login test started [" + username + "," + password + "," + expectedToBeBeLogged + "]");
		assertTrue(driver.getTitle().contains("Mail.Ru:"));
		if(homePage.isLogged()) homePage.logout();
		homePage.login(username, password, false);
		assertEquals(homePage.isLogged(),expectedToBeBeLogged);
		log.info("Login test finished");
	}
	
	@Test(dependsOnMethods = {"testLogin"})
	public void testLogout(){
		log.info("Logout test started");
		assertTrue(homePage.isLogged(), "User is not logged to perform logout");
		homePage.logout();
		assertFalse(homePage.isLogged(), "User is still logged after logout");
		log.info("Logout test finished");
	}
}
