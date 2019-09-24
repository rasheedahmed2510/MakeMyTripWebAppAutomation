package com.mmt.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mmt.qa.base.BaseTest;
import com.mmt.qa.pages.FlightDetailsListPage;
import com.mmt.qa.pages.HomePage;
import com.mmt.qa.utils.TestUtils;

public class FlightDetailsListPageTest extends BaseTest {
		
	HomePage homepage;
	FlightDetailsListPage flightdetailslist;
	
	@BeforeMethod
	public void preCondition(){
		initialization();
		homepage = new HomePage();
		homepage.searchForFlightsRoundTripFor7DaysFromCurrentDate(prop.getProperty("fromCity"), prop.getProperty("toCity"));
	}
	
	@Test(priority=1, enabled = false)
	public void validateTheDepartureAndReturnFlightCountWithoutSelectingStopsTest() throws Exception{
		System.out.println("******************Start Test without selecting stops********************************");
		flightdetailslist = new FlightDetailsListPage();
		TestUtils.scrollFromTopToBottomPage();
		int totalDepartureFlightCount = flightdetailslist.getTotalDepartureFlightCount();
		System.out.println("The total departure flight count is: "+totalDepartureFlightCount);
		int totalReturnFlightCount = flightdetailslist.getTotalReturnFlightCount();
		System.out.println("The total return flight count is: "+totalReturnFlightCount);
		System.out.println("*****************End Test without selecting stops***********************************");
	}
	
	@Test(priority=2, enabled= false)
	public void validateTheDepartureAndReturnFlightCountBySelectingNonStopChkBoxTest() throws Exception{
		System.out.println("******************Start Test with selecting Non stop checkbox********************************");
		flightdetailslist = new FlightDetailsListPage();
		flightdetailslist.clickToSelectNonStopChkBox();
		TestUtils.scrollFromTopToBottomPage();
		int totalDepartureFlightCount = flightdetailslist.getTotalDepartureFlightCount();
		System.out.println("The total departure flight count is: "+totalDepartureFlightCount);
		int totalReturnFlightCount = flightdetailslist.getTotalReturnFlightCount();
		System.out.println("The total return flight count is: "+totalReturnFlightCount);
		System.out.println("*****************End Test with selecting Non stop checkbox***********************************");
	}
	
	@Test(priority=3, enabled = false)
	public void validateTheDepartureAndReturnFlightCountBySelectingOneStopChkBoxTest() throws Exception{
		System.out.println("******************Start Test with selecting One stop checkbox ********************************");
		flightdetailslist = new FlightDetailsListPage();
		flightdetailslist.clickToSelectOneStopChkBox();
		TestUtils.scrollFromTopToBottomPage();
		int totalDepartureFlightCount = flightdetailslist.getTotalDepartureFlightCount();
		System.out.println("The total departure flight count is: "+totalDepartureFlightCount);
		int totalReturnFlightCount = flightdetailslist.getTotalReturnFlightCount();
		System.out.println("The total return flight count is: "+totalReturnFlightCount);
		System.out.println("*****************End Test with selecting One stop checkbox ***********************************");
	}
	
	@Parameters({"departFlight","returnFlight"})
	@Test(priority= 4)
	public void validateToSelectDepartureAndReturnFlightTest() throws InterruptedException{
		System.out.println("******************Start Test to select Departure and Return flight ********************************");
		/*int departFlightVal= Integer.parseInt(departFlight);
		int returnFlightVal= Integer.parseInt(returnFlight);*/
		flightdetailslist = new FlightDetailsListPage();
		//TestUtils.scrollTo();
		flightdetailslist.selectDepartureAndReturnFlight(1, 2);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
