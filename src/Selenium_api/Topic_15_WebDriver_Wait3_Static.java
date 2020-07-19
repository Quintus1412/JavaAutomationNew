package Selenium_api;

import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

import org.testng.annotations.BeforeClass;


import java.awt.SecondaryLoop;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_WebDriver_Wait3_Static {

	// khai bao driver
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
		System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// //Khoi tao Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		//
		// khoi tao IE
		// System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe")
		// driver = new InternetExplorerDriver();
		//
		// cho page load thanh cong
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// maximize browser len
		driver.manage().window().maximize();

	}

	
	@Test
	public void TC_01_Not_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
		
	
		
	}
	@Test
	public void TC_01_Static_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
		
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
