package Selenium_api;

import org.testng.annotations.Test;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.thoughtworks.selenium.webdriven.commands.IsChecked;
import com.thoughtworks.selenium.webdriven.commands.IsEditable;

import org.bouncycastle.asn1.x509.qualified.BiometricData;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic06_Web_Elements_Excercises {

	
	WebDriver driver;

	By EmailTextBox = By.xpath(".//*[@id='mail']");
	By EducationTextBox = By.xpath(".//*[@id='edu']");
	By Under18 = By.xpath(".//*[@id='under_18']");
	By Job1 = By.xpath(".//*[@id='job1']");
	By PasswordTXB = By.xpath("//*[@id='password' and @name='user_pass']");
	By DevelopmentChxB= By.xpath(".//*[@id='development']");
	By Slider1= By.xpath(".//*[@id='slider-1']");
	By Slider2= By.xpath(".//*[@id='slider-2']");
	By AgeDisable =By.xpath(".//*[@id='radio-disabled']");
	By Job3 = By.xpath(".//*[@id='job3']");
	By Bio = By.xpath(".//*[@id='bio']");
	
	By InterestDisable = By.xpath(".//*[@id='check-disbaled']");
	
	
	

	@BeforeClass
	public void beforeClass() {
		// 1 - Mở browser
		driver = new FirefoxDriver();
		// //2- nhập vào URL
		// driver.get("http://live.demoguru99.com/");
		// // chờ load page
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");

	}

	// Check element có hiển thị
	public boolean IsElementDislayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element  " + by + " is displayed");
			return true;
		} else {
			System.out.println("Element  " + by + " is Un-displayed");
			return false;
		}
	}

	// Check element có Enabled
	public boolean IsElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element  " + by + " is enabled");
			return true;
		} else {
			System.out.println("Element  " + by + " is disable");
			return false;
		}
	}

	// Check element có Selected
	public boolean IsElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element  " + by + " is selected");
			return true;
		} else {
			System.out.println("Element  " + by + " is un-selected");
			return false;
		}
	}

	// truyên dữ liệu cho element
	public void SendKeysToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	// click vào element
	public void ClickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	
	@Test
	public void TC_01_CheckDisplayed() {
		// Email is displayed
		if (IsElementDislayed(EmailTextBox)) {
			SendKeysToElement(EmailTextBox, "Automation Testing");
			
		}
		
		// Education is dispalyed
		if (IsElementDislayed(EducationTextBox)) {
			SendKeysToElement(EducationTextBox, "Automation Testing University");
		}
		
		// checkbox under18 is displayed
		if (IsElementDislayed(Under18)) {
			ClickElement(Under18);
		}
		
		
		
	}
	@Test
	public void TC_02_CheckEnabled() {
		
		//Enabled
		Assert.assertTrue(IsElementEnabled(EmailTextBox));
			
		
		//Enable Education
		Assert.assertTrue(IsElementEnabled(EducationTextBox));
		
		
		
		//Enable Age <18
		Assert.assertTrue(IsElementEnabled(Under18));
		
		Assert.assertTrue(IsElementEnabled(Job1));
		
		Assert.assertTrue(IsElementEnabled(DevelopmentChxB));
		
		Assert.assertTrue(IsElementEnabled(Slider1));
		
		
		
		
		
		//Disable elements
		Assert.assertFalse(IsElementEnabled(PasswordTXB));
		Assert.assertFalse(IsElementEnabled(AgeDisable));
		Assert.assertFalse(IsElementEnabled(Bio));
		Assert.assertFalse(IsElementEnabled(Job3));
		Assert.assertFalse(IsElementEnabled(Slider2));
		Assert.assertFalse(IsElementEnabled(InterestDisable));
		
		
				
	}

	
	@Test
	public void TC_03_CheckSelected() {
		//click chon vao check box
		ClickElement(DevelopmentChxB);
		//kiem tra checkbox dc chon
		Assert.assertTrue(IsElementSelected(DevelopmentChxB));
		
		//bỏ chon vao check box
		ClickElement(DevelopmentChxB);
		//kiem tra checkbox đã bỏ chon
		Assert.assertFalse(IsElementSelected(DevelopmentChxB));
	}

	public void TC_04() {

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
