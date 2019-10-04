package com.mmt.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.qa.base.BaseTest;
import com.mmt.qa.utils.TestUtils;

public class HomePage extends BaseTest {
	
	//Obj repo
	@FindBy(xpath="//a[@class='active makeFlex hrtlCenter column']")
	private WebElement flightsOption;
	
	@FindBy(xpath="//ul[@class='fswTabs latoBlack greyText']//li[2]//span[@class='tabsCircle appendRight5']")
	private WebElement roundTripRadioBtn;
	
	@FindBy(xpath="//input[@id='fromCity']")
	private WebElement fromCityInput;
	
	@FindBy(xpath="//input[@id='toCity']")
	private WebElement toCityInput;
	
	@FindBy(xpath="//label[@for='departure']/span[@class='lbl_input latoBold appendBottom10']")
	private WebElement departureDateCalendar;
	
	String todaysDate= TestUtils.getCurrentDate();
	
	String todaysDateXpath = "//div[contains(@aria-label,'"+todaysDate+"')]";
	
	private WebElement selectCurrentDateFromDepartureCalendar;
	
	@FindBy(xpath="//label[@for='return']/span[@class='lbl_input latoBold appendBottom10']")
	private WebElement returnDateCalendar;
	
	@FindBy(xpath="(//div[@class='DayPicker-Body']//div[@class='DayPicker-Week']//div[@class='DayPicker-Day'])[7]")
	private WebElement selectSevethDayFromCurrentDateInReturnDateCalendar;
	
	@FindBy(xpath="//a[contains(text(),'Search')]")
	private WebElement searchBtn;
	
	@FindBy(xpath="//p[@class='font14 appendBottom5 blackText']")
	private List<WebElement> cityList;
	
	//Constructor to initialize the current class members
	public HomePage(){
		PageFactory.initElements(driver, HomePage.this);
	}
	
	//Action methods
	public FlightDetailsListPage searchForFlightsRoundTripFor7DaysFromCurrentDate(String fromCityVal, String toCityVal){
		//click on flight option
		flightsOption.click();
		//check if round trip option is selected or not
		if(!roundTripRadioBtn.isSelected()){
			roundTripRadioBtn.click();
		}
		//input from & to city
		fromCityInput.sendKeys(fromCityVal);
		int fromCities= cityList.size();
		for(int i=0; i< fromCities; i++){
			if(cityList.get(i).getText().contains(fromCityVal)){
				System.out.println(cityList.get(i).getText());
				cityList.get(i).click();
			}
			break;
		}
		
		toCityInput.sendKeys(toCityVal);
		int ToCities= cityList.size();
		for(int i=0; i< ToCities; i++){
			if(cityList.get(i).getText().contains(toCityVal)){
				System.out.println(cityList.get(i).getText());
				cityList.get(i).click();
			}
			break;
		}
		
		//select the departure and return date for 7 days
		departureDateCalendar.click();
		String todaysDate= TestUtils.getCurrentDate();
		System.out.println("Today's current date is: "+todaysDate);
		String currentDateInCal[]= todaysDate.split(" ");
		WebElement selectCurrentDate= driver.findElement(By.xpath("//div[contains(@aria-label,'"+currentDateInCal[0]+"')]"));
		selectCurrentDate.click();
		
		//select the return date
		returnDateCalendar.click();
		String returnDate= selectSevethDayFromCurrentDateInReturnDateCalendar.getText();
		System.out.println("Selected return date after 7 days is: "+returnDate);
		selectSevethDayFromCurrentDateInReturnDateCalendar.click();
		searchBtn.click();
		return new FlightDetailsListPage();
	}
	
	
	
	
	
	
	
}
