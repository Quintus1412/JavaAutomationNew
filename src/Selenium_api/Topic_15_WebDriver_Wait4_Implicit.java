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

public class Topic_15_WebDriver_Wait4_Implicit {

	// khai bao driver
	WebDriver driver;
	WebDriverWait implicitWait;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
		System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// //Khoi tao Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		// driver = new ChromeDriver();
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

	
	@Test
	public void TC_01_Pass() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		// cần tới 5s để loading icon biến mất
		
		//Đếm 5.5s = thấy Hello text xuất hiện (vẫn còn timeout 10s)
		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
		
	
		
	}
	
	@Test	
	public void TC_01_Fail() {
		//Implicit dc set nó sẽ áp dụng cho tất cả step Findelement về sau cho đến khi hết tất cả cac test cases
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		// cần tới 5s để loading icon biến mất
		
		//Đếm 3s KO thấy Hello text xuất hiện và hết timeout 
		//-- Đánh failed case
		//--Throw exception
		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
		
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}