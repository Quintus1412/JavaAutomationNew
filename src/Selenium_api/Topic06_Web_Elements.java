package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic06_Web_Elements {

	// Thao tác của user
	WebDriver driver;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		// 1 - Mở browser
		driver = new FirefoxDriver();
//		//2- nhập vào URL
//		driver.get("http://live.demoguru99.com/");
//		// chờ load page
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		
		
	}

	@Test
	public void TC_01() {
		
		element = driver.findElement(By.xpath(".//*[@id='search']"));
		element.sendKeys("Dell computer");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		element.clear();
		element.sendKeys("Pro");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		element.sendKeys("Max");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		// lấy giá trị attribute
		String searchValue = element.getAttribute("placeholder");
		System.out.println("Attribute value is: "+searchValue);
		
		
		
		
		///Get css value
		
		// Gán lại biến element
		element = driver.findElement(By.xpath("//div[@class='actions']/button[@title='Subscribe']"));
		String CssValue = element.getCssValue(" font-family");
		System.out.println("font style is: "+CssValue);
		
		
		//lấy tagname
		String Gettagname = element.getTagName();
		System.out.println("Tag name is: "+Gettagname);
		
		//kiểm tra 1 element có hiển thị để tương tác ko
		Assert.assertTrue(element.isDisplayed());
		Assert.assertTrue(element.isEnabled());
		//element.isSelected();
		
		
		//Nếu element đang tương tác nằm trong form thì dùng sybmit
		element = driver.findElement(By.xpath(".//*[@id='search']"));
		element.submit();
	
		
		
		
		// Dùng 1 lần thì tương tác trự tiếp
//		driver.findElement(By.xpath("")).click();
//		
//		//tạo biến để dùng nhiều lần
//		WebElement emailTextbox = driver.findElement(By.xpath(""));
//		emailTextbox.clear();
//		emailTextbox.click();
//		
//		// xóa dữ lệu trong text field
//		element.clear();
//		//nhập dữ liệu vào text field
//		element.sendKeys("");
//		
//		//tìm 1 element - nếu hơn 1 thì tuon7ng tác với cái đầu tiên
//		element.findElement(By.cssSelector(""));
//		// Tìm nhiều element
//		element.findElements(By.cssSelector(""));
//		// tìm elemtn trả về duy nhất 1
//		element.findElements(By.cssSelector("")).get(0);
//		// tìm attribute
//		element.getAttribute("");
		
	}

	
	public void TC_02_CheckPageTitle() {
		

	}

	

	
	public void TC_03() {
	}
	
	
	

	public void TC_04() {
	


	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
