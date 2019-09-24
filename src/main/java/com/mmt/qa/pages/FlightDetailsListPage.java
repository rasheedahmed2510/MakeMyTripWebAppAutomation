package com.mmt.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmt.qa.base.BaseTest;

public class FlightDetailsListPage extends BaseTest {
	
	//Obj Repo
	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//div[contains(@class,'fli-list splitVw-listing')]")
	private List<WebElement> departureFlightList;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//div[contains(@class,'fli-list splitVw-listing')]")
	private List<WebElement> returnFlightList;
	
	@FindBy(xpath="(//span[@class='checkbox-group  append_bottom5 fli-filter-items ']//span[@class='box'])[1]")
	private WebElement NonStopChkbox;
	
	@FindBy(xpath="(//span[@class='checkbox-group  append_bottom5 fli-filter-items ']//span[@class='box'])[2]")
	private WebElement OneStopChkbox;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//div[contains(@class,'fli-list splitVw-listing')]//span[@class='splitVw-outer append_right9']")
	private WebElement departureFlightRadioBtn;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//div[contains(@class,'fli-list splitVw-listing')]//span[@class='splitVw-outer append_right9']")
	private WebElement returnFlightRadioBtn;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//span[@class='actual-price']")
	private WebElement departureFlightPriceList;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//span[@class='actual-price']")
	private WebElement returnFlightPriceList;
	
	@FindBy(xpath="//div[@class='splitVw-footer-left']//div[@class='timing-option pull-left']//div[@class='pull-right marL5 text-right']//p[@class='actual-price']")
	private WebElement departureFlightSelectedFare;
	
	@FindBy(xpath="//div[@class='splitVw-footer-right']//div[@class='timing-option pull-left']//div[@class='pull-right marL5 text-right']//p[@class='actual-price']")
	private WebElement returnFlightSelectedFare;
	
	@FindBy(xpath="//div[@class='splitVw-footer-total make_relative make_flex alC']//span[@class='splitVw-total-fare']")
	private WebElement totalFlightFare;
	
	//create a constructor to initialize the current class members
	public FlightDetailsListPage(){
		PageFactory.initElements(driver, FlightDetailsListPage.this);	
	}
	
	//Action Methods
	public int getTotalDepartureFlightCount() throws Exception{
		if(departureFlightList.size()<1){
			log.assertLog(false, "No flights found");
			throw new Exception("No Flight availablee");
		}
		return departureFlightList.size();
	}
	
	public int getTotalReturnFlightCount() throws Exception{
		if(returnFlightList.size()<1){
			log.assertLog(false, "No flights found");
			throw new Exception("No Flight availablee");
		}
		return returnFlightList.size();
	}
	
	public void clickToSelectNonStopChkBox(){
		if(!NonStopChkbox.isSelected()){
			NonStopChkbox.click();
		} else {
			return;
		}
	}
	
	public void clickToSelectOneStopChkBox(){
		if(!OneStopChkbox.isSelected()){
			OneStopChkbox.click();
		} else {
			return;
		}
	}
	
	public void selectDepartureAndReturnFlight(int departFlight, int returnFlight) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		String departureFlightXpath = "(//div[@class='splitVw-sctn pull-left']//div[contains(@class,'fli-list splitVw-listing')]//span[@class='splitVw-outer append_right9'])"+"["+departFlight+"]";
		String returnFlightXpath= "(//div[@class='splitVw-sctn pull-right']//div[contains(@class,'fli-list splitVw-listing')]//span[@class='splitVw-outer append_right9'])"+"["+returnFlight+"]";
		
		WebElement departFlightWebElement= driver.findElement(By.xpath(departureFlightXpath));
		WebElement returnFlightWebElement= driver.findElement(By.xpath(returnFlightXpath));
		wait.until(ExpectedConditions.visibilityOf(departFlightWebElement)).click();
		wait.until(ExpectedConditions.visibilityOf(returnFlightWebElement)).click();
	}
	
	
}
