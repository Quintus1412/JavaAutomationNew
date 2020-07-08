package Selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_01_Check_Evironment {
	
	//khai bao driver
	WebDriver driver;
	
	 @BeforeClass
	  public void beforeClass() {
		 //khoi tao trinh duyet
//		 System.setProperties(");
//		 driver = new FirefoxDriver("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
//		 
		 //Khoi tao Chrome
		 System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
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
		 
	  
	  
	  }
 
  
	 
	@Test
	  public void TC_02_CheckURL() {
		 
	  }
	
	  @AfterClass
	  public void afterClass() {
		  //quit browser
		  driver.quit();
	  }

}
