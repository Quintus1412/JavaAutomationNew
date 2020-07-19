package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic05_Web_Browser_API_Excersices {

	// Thao tác của user
	// 1 - Mở browser
	// 2- nhập vào URL
	// 3- User tải lại trang
	// 4 - User click vào 1 đường link (Web Element
	// 5- User back lại trang trước đó
	// 6 -User forward tới trang
	// 7 - User get title của trang web
	// 8 - User get ra URL của trang
	// 9 - User get toàn bộ source code của trang
	// 10- User đóng application/ browser

	// driver là 1 cái instance (đại diện) cho 1 thư viện/ class/interface nào đó
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// 1 - Mở browser
		driver = new FirefoxDriver();
		//2- nhập vào URL
		driver.get("http://live.demoguru99.com/");
		// chờ load page
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		
//		// 3- User tải lại trang
//		driver.navigate().refresh();
//		
//		// 4 - User click vào 1 đường link (Web Element)
//		
//		// 5- User back lại trang trước đó
//		driver.navigate().back();
//		
//		// 6 -User forward tới trang
//		driver.navigate().forward(); 
//		
//		// Alert/ Window/ Tab/ Iframe
//		driver.switchTo().alert();
//		driver.switchTo().frame("")	;
//		
//		// 7 - User get title của trang web
//		String homePageTitle = driver.getTitle();
//		Assert.assertEquals(homePageTitle, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
//		
//		// 8 - User get ra URL của trang
//		Assert.assertEquals(driver.getCurrentUrl(),"https://tiki.vn/");
//		
//		// 9 - User get toàn bộ source code của trang
//		driver.getPageSource();
//		Assert.assertTrue(driver.getPageSource().contains("Thỏa sức mua sắm qua mạng hàng ngàn mặt hàng sách, điện tử, đồ gia dụng"));
//		
//		//10- lấy ra ID của tab/window hiện tại
//		driver.getTitle();
//			//chờ cho element xuất hiện để thao tác - WebDriverWaits
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			// chờ page được load xing
//		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//			// chờ cho script được thực thi xong
//		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
//			// phóng to screen lên hết cỡ
//		driver.manage().window().maximize();
//			// test giao dien
//		driver.manage().window().getPosition();
//		driver.manage().window().getSize();
//		
//		
//		// 10- User đóng application/ browser
//			//Đóng tab đang làm việc
//		driver.close();
//			// Đóng cả trình duyệt
//		driver.quit();
		

	}

	@Test
	public void TC_01_CheckPageURL() {
		// vào trang My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account') and @title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
		
		// Vào trang create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_CheckPageTitle() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account') and @title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		//Click vào Create new account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	

	@Test
	public void TC_03() {
		//Click vao Acount link o footer
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account') and @title='My Account']")).click();
		//Click vào Create new account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Verify URL cua register page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		//back lai trang Log in page
		driver.navigate().back();
		//verify URL cua log in page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		//forward toi trang Register
		driver.navigate().forward();
		//verify title của register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
@Test	
	public void TC_04() {
		//Vao trang My account
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account') and @title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		//Click vào Create new account button vao trang create new account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		//verify source code chứa text Create New Account
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Create New Customer Account"));


	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
