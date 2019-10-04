package com.mmt.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.mmt.qa.generic.AutoConstants;

public abstract class BaseTest implements AutoConstants{
	
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;
	public static Logger log;
	
	public BaseTest(){
		prop = new Properties();
		log =Logger.getLogger(this.getClass());
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\mmt\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	static{
		System.setProperty(chromeKey, chromeValue);
		System.setProperty(firefoxKey, firefoxValue);
	}
	
	public static void initialization(){
		String browserName= prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else{
			log.info("The browser name is incorrect or not initialized in the config file");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
}
