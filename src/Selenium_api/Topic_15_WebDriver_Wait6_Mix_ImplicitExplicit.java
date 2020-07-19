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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_WebDriver_Wait6_Mix_ImplicitExplicit {

	// khai bao driver
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
//		System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		// //Khoi tao Chrome
		 System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
		 explicitWait = new WebDriverWait(driver, 12);
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

	
	
	public void TC_01_Visible() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// cần tới 5s để loading icon biến mất
		
		//Chờ cho Helloword hiển thị trong 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
	

		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
}
	

	public void TC_02_Invisibile() {
		//Implicit dc set nó sẽ áp dụng cho tất cả step Findelement về sau cho đến khi hết tất cả cac test cases
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// cần tới 5s để loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(" //div[@id='loading']//img")));
		//Đếm 3s KO thấy Hello text xuất hiện và hết timeout 
		//-- Đánh failed case
		//--Throw exception
		Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText(), "Hello World!");
		
	}
	@Test
	public void TC_03_Implicit() {
		//Implicit dc set nó sẽ áp dụng cho tất cả step Findelement về sau cho đến khi hết tất cả cac test cases
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.findElement(By.xpath("//td[@title='Thursday, July 16, 2020']")).click();
		
		// cần tới 5s để loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		// chờ cho ngày dc chọn visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected' and @title='Thursday, July 16, 2020']")));
	
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Thursday, July 15, 2020']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "Wednesday, July 16, 2020");
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
