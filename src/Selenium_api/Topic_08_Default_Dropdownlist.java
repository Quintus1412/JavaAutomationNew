package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_08_Default_Dropdownlist {

	// khai bao driver
	private WebDriver driver;
	private Select select;
	private WebDriverWait wait;
	private JavascriptExecutor javacriptEx;
	
	


	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		wait= new WebDriverWait(driver, 5);
		
		javacriptEx = (JavascriptExecutor) driver;
		
		// chờ load page
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		

	}


	//

	
	public void TC_01_Defaul_DropDown() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Thao tác với dropdown job1
		// DÙng thư viện select chỉ thao tác với thẻ select
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		boolean status = select.isMultiple();
		System.out.println("Status is: "+status);
		Assert.assertFalse(status);
		
		
		// chọn 1 giá trị
		// Select và kiểm tra gia trị được select
		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		
		
		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		
		select.selectByIndex(2);
		Thread.sleep(3000);
		
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Adhoc Testing");
		
		// lấy ra số lượng giá tri của dropdown
		int job1DropdownItems = select.getOptions().size();
		Assert.assertEquals( job1DropdownItems, 10);
		
		
		//Thao tác với dropdown job2
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		boolean status2 = select.isMultiple();
		System.out.println("Status is: "+status2);
		Assert.assertTrue(status2);
		
		
		select.selectByVisibleText("Mobile");
		Thread.sleep(1000);
		select.selectByVisibleText("Desktop");
		Thread.sleep(1000);
		select.selectByVisibleText("Automation");
		Thread.sleep(1000);
		
		
		List <WebElement> job2SelectedIteam =  select.getAllSelectedOptions();
		for (WebElement select: job2SelectedIteam) {
			System.out.println(select.getText());
		}
		
		Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
		
		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
		
	}

	@Test
	public void TC_02_Defaul_DropDown() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector(".ico-register")).click();
		
		
		driver.findElement(By.cssSelector("#gender-male")).click();
		
		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Jonh");
		
		
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Kick");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("14");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("December");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1991");
		
		driver.findElement(By.cssSelector("#Email")).sendKeys("emailtest"+randNumber()+"@gmail.com");
		
		driver.findElement(By.cssSelector("#Company")).sendKeys("FS");
		
		driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals((driver.findElement(By.cssSelector(".result")).getText()),"Your registration completed");
		Assert.assertTrue(driver.findElement(By.cssSelector(".ico-account")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".ico-logout")).isDisplayed());
		
	
	}
	
	@Test
	public void TC_03_NewCustomer() {
		
	}

	@Test
	public void TC_04_EditCustomer() {
		
		
	}
	
	// random number
	public int randNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	
	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
