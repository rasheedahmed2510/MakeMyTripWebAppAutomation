package com.mmt.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.JavascriptExecutor;
import com.mmt.qa.base.BaseTest;

public class TestUtils extends BaseTest {
	
	//fetch current date
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd MMM yy");
		String currentDate = formatter1.format(date);
		//String currentTodaysDate[] =currentDate.split(" ");
		//return currentTodaysDate[0];
		return currentDate;
	}
	
	public static void scrollFromTopToBottomPage(){
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		try {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*try{
			int height =Integer.parseInt(js.executeScript("window.scrollTo(0, document.body.scrollHeight)").toString());
			while(true){
				js.executeScript("window.scrollTo(0, document.body.scrollHeight");
				Thread.sleep(500);
				
				 int newHeight = Integer.parseInt(js.executeScript("return document.body.scrollHeight").toString());
			        if (newHeight == height) {
			            break;
			        }
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
	}
	
	public static void scrollTo(){
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		try {
			js.executeScript("window.scrollTo(0, 250)");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
