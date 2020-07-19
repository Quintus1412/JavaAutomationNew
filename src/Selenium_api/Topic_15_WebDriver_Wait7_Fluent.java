package Selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

import org.testng.annotations.BeforeClass;

import java.awt.SecondaryLoop;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_WebDriver_Wait7_Fluent {

	// khai bao driver
	WebDriver driver;
	WebElement element;
	FluentWait wait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
		// System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// //Khoi tao Chrome
		System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// implicitWait = new WebDriverWait(driver, 10);
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

	public void TC_01_Fluent_WebDriver() {
		driver.get("https://www.facebook.com/");
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian check là 15s
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(300, TimeUnit.MILLISECONDS)
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class);
		WebElement feelingluckyButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {

			// Function<T,R>
			// T là tham số của hàm apply
			// R là kiểu giá trị trả về
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@id='selenium_14']"));
			}
		});
		feelingluckyButton.click();
	}

	
	public void TC_02_Fluent_WebElement() {
		
		driver.get("https://automationfc.github.io/fluent-wait/");
		element = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement>(element);
		
		// Tổng thời gian là 15s
		fluentElement.withTimeout(15, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		// Tần số mỗi 1s check 1 lần
		
		// Nếu gặp exception là find ko thấy element sẽ bỏ qua
		
		// Kiểm tra điều kiện
		boolean status = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				
				// Kiểm tra điều kiện countdount = 00
				boolean flag = element.getText().endsWith("00");
				System.out.println("Time = " + element.getText());
				// return giá trị cho function apply
				return flag;
			}
		});
		System.out.println("Status= " + status);
	}
	
	
	@Test
	public void TC_03_Fluent_WebElement() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		wait = new FluentWait(driver)
			    .withTimeout(10, TimeUnit.SECONDS)
			    .pollingEvery(200, TimeUnit.MILLISECONDS)
			    .ignoring(NoSuchElementException.class);
			WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() 
			{
			  public WebElement apply(WebDriver driver)
			  {
			     return driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
			  }
			});
			Assert.assertEquals(element.getText(),"Hello World!");
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
