package Selenium_api;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_03_XPATHCSS_Part2 {
	
	//khai bao driver
	WebDriver driver;
	
	 @BeforeClass
	  public void beforeClass() {
		 //khoi tao trinh duyet
		
		 System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
		 driver = new FirefoxDriver();
		 
		 Assert.assertTrue(1==1);
		 //Khoi tao Chrome
//		 System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
//		 driver = new ChromeDriver();
//		 
		 //khoi tao IE
//		 System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe")
//		 driver = new  InternetExplorerDriver();
//		 
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
		// check URL cua page
		 String homePageURL = driver.getCurrentUrl();
		 Assert.assertEquals("http://live.demoguru99.com/", homePageURL);
	  }
	
	
	  @AfterClass
	  public void afterClass() {
		  //quit browser
		  driver.quit();
	  }



	

	
	
	

	
	
}
