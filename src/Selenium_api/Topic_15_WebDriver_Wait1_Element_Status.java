package Selenium_api;

import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

import org.testng.annotations.BeforeClass;

import java.awt.SecondaryLoop;
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

public class Topic_15_WebDriver_Wait1_Element_Status {

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

	
	public void TC_01_Visible_Passed() {
		driver.get("https://www.facebook.com/");
		// Email displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}


	public void TC_01_Visible_Failed() {
		driver.get("https://www.facebook.com/");
		// Email displayed UI- No i
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());

	}
@Test
	public void TC_02_Invisible_Pass() {
		
	// ko có trên UI- có trong DOM
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		
		
	}

public void TC_02_Invisible_Failed() {
// Ko có trên UI- Ko có trong DOM
	driver.get("https://www.facebook.com/");
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_1111email_confirmation__']")));
	
}

	public void TC_02_Invisible_Failed1() {
// Có trên UI- > Wait invisible -> Fail
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
	
	}

@Test
	public void TC_03_Presence() {
		
		driver.get("https://www.facebook.com/");
		// có trên UI- có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		// Không có trên UI- có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		// Ko có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='address']")));
	}

	
	public void TC_04_Staless() {
		driver.get("https://accounts.spotify.com/en/login?continue=https:%2F%2Fwww.spotify.com%2Fvn-en%2Faccount%2Foverview%2F");
		// Phải bắt dc Element
		
		driver.findElement(By.xpath("//button[@id='login-button']")).click();
		WebElement errormess= driver.findElement(By.xpath("//label[text()='Please enter your Spotify username or email address.']"));
		driver.navigate().refresh();
	
		// Không có trên UI- KO có trong DOM
		explicitWait.until(ExpectedConditions.stalenessOf(errormess));

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
