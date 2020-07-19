package Selenium_api;

import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

import org.testng.annotations.BeforeClass;


import java.awt.SecondaryLoop;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_WebDriver_Wait5_Explicit {

	// khai bao driver
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
//		System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		// //Khoi tao Chrome
		 System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
		 waitExplicit = new WebDriverWait(driver, 12);
	//implicitWait = new WebDriverWait(driver, 10);
		//
		// khoi tao IE
		// System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe")
		// driver = new InternetExplorerDriver();
		//
		// cho page load thanh cong
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// maximize browser len
		driver.manage().window().maximize();

	}

	
	
	 public void TC_01_Element_Found() {
		// Implicit Wait 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit. SECONDS);
		
		// Explicit Wait 
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println(" ------- STEP 01 - Start TC_01_Element_Found: " + new Date() + " ---------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
			Assert.assertTrue (emailTextbox.isDisplayed()); 
		} catch (Exception ex) {
			System.out.println("Switch to catch exception!");
		}
		
		System.out.println(" --- STEP 01 - End TC_01_Element_Found: " + new Date() + " ------ ");
		
		System.out.println(" .. ... STEP 02 - Start TC_01_Element_Found: " + new Date() + "");
		try { waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='lastname']")));
		
		} catch (Exception ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println(" --------- STEP 02 - End TC_01_Element_Found: " + new Date() + " -------");
	}
	 
	@Test public void TC_02_Element_NotFound_TimeOut() {
		// Implicit Wait 
		driver.manage().timeouts().implicitlyWait(8, TimeUnit. SECONDS);
		
		// Explicit Wait 
		waitExplicit = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println(" ------- STEP 01 - Start TC_02_ElementNotFound: " + new Date() + " ---------");
		try {
		waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='email111']"))));
		
		} catch (Exception ex) {
		System.out.println(ex.getMessage());
		}
		
		System.out.println(" --- STEP 01 - Start TC_02_ElementNotFound: " + new Date() + " ------ ");
		
		System.out.println(" .. ... STEP 02 - Start TC_02_ElementNotFound: " + new Date() + "");
		
		try { 
			waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='lastnkame']")));
				
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
				System.out.println(" --------- STEP 02 - End TC_02_ElementNotFound:: " + new Date() + " -------");
	}
	

	

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
