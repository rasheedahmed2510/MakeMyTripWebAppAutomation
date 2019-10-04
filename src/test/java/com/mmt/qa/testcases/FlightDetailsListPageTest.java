package com.mmt.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
	
	@Test(priority=1)
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
	
	@Test(priority=2)
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
	
	@Test(priority=3)
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
	public void validateToSelectDepartureAndReturnFlightAlongWithPriceAndGrandTotalTest(String departureFlightVal, String returnFlightVal) throws InterruptedException{
		System.out.println("******************Start Test to select Departure and Return flight along with price validation********************************");
		
		SoftAssert sa= new SoftAssert();
		
		int departFlight= Integer.parseInt(departureFlightVal);
		int returnFlight= Integer.parseInt(returnFlightVal);
		
		flightdetailslist = new FlightDetailsListPage();
		//TestUtils.scrollTo();
		//flightdetailslist.selectDepartureAndReturnFlightAlongWithPrice(1, 2);
		
		//Select the departure and return flight and return the price
		String deptFlightFare= flightdetailslist.selectAndGetDepartureFlightSelectedFare(departFlight);
		String returnFlightFare= flightdetailslist.selectAndGetReturnFlightSelectedFare(returnFlight);
		
		//Get Departure and Return flight fare from footer
		String deptFlightFareInFooter= flightdetailslist.getDepartureFlightSelectedFareInFooter();
		String returnFlightFareInFooter=flightdetailslist.getReturnFlightSelectedFareInFooter();
		
		sa.assertEquals(deptFlightFare, deptFlightFareInFooter, "The departure flight fare selected has the same fare in the footer");
		sa.assertEquals(returnFlightFare, returnFlightFareInFooter, "The return flight fare selected has the same fare in the footer");
		
		//Validate the grand total fare for departure and return flight
		String departFare= deptFlightFareInFooter.replace("₹", "");
		String returnFare= returnFlightFareInFooter.replace("₹", "");
		String depart1= departFare.replaceFirst(" ", "");
		String return1= returnFare.replaceFirst(" ", "");
		int actualTotalFlightFare=0;
		try {
			//Adding both the departure and return flight fare
			actualTotalFlightFare = Integer.parseInt(depart1)+Integer.parseInt(return1);
			System.out.println("The actual total flight fare for departure and return is: "+actualTotalFlightFare);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String expTotalFlightFare= flightdetailslist.getGrandTotalFlightFare();
		String expTotalFare= expTotalFlightFare.replace("₹", "");
		String expTotFlightFare= expTotalFare.replaceFirst(" ", "");
		int expectedTotalFlightFare = 0;
		try {
			expectedTotalFlightFare = Integer.parseInt(expTotFlightFare);
			System.out.println("The expected total flight fare for departure and return is: "+expectedTotalFlightFare);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		sa.assertEquals(actualTotalFlightFare, expectedTotalFlightFare, "The total flight fare displayed is valid");
		
		sa.assertAll();
		
		System.out.println("*****************End Test to select Departure and Return flight along with price validation ***********************************");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
