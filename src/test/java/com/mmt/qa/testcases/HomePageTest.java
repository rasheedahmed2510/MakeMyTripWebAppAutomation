package com.mmt.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mmt.qa.base.BaseTest;
import com.mmt.qa.pages.HomePage;

public class HomePageTest extends BaseTest {
	
	HomePage homepage;
	
	@BeforeMethod
	public void preCondition(){
		initialization();
		homepage = new HomePage();
	}
	
	@Test(priority = 1, enabled= false)
	public void SearchForFlights(){
		System.out.println("******************Start Test********************************");
		homepage.searchForFlightsRoundTripFor7DaysFromCurrentDate(prop.getProperty("fromCity"), prop.getProperty("toCity"));
		System.out.println("*****************End Test***********************************");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
}
