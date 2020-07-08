package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;


import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;


public class Topic_02_CheckBox_Drodownlist {
	
	//khai bao driver
	WebDriver driver;
	
	 @BeforeClass
	  public void beforeClass() {
		 //khoi tao trinh duyet
		 driver = new FirefoxDriver();
		 //cho page load thanh cong
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 //maximize browser len
		 driver.manage().window().maximize();
		 //get URL cua web
		 driver.get("http://live.demoguru99.com/");
		 
	  }	
	 
	 
  
	 
	@Test
	 public void TC_01_CheckTitle() {
		 //lay title cua page
		 String homePageTitle = driver.getTitle();
		 //so sanh title co giong nhau khong?
		 Assert.assertEquals("Home page", homePageTitle);
	  
	  
	  }
 
  
	 
	@Test
	  public void TC_02_CheckURL() {
		 String homePageURL = driver.getCurrentUrl();
		 Assert.assertEquals("http://live.demoguru99.com/", homePageURL);
	  }
	
	  @AfterClass
	  public void afterClass() {
		  //quit browser
		  driver.quit();
	  }

}
