package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic05_Web_Browser_API {

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
		driver.get("https://tiki.vn/");
		
		// 3- User tải lại trang
		driver.navigate().refresh();
		
		// 4 - User click vào 1 đường link (Web Element)
		
		// 5- User back lại trang trước đó
		driver.navigate().back();
		
		// 6 -User forward tới trang
		driver.navigate().forward(); 
		
		// Alert/ Window/ Tab/ Iframe
		driver.switchTo().alert();
		driver.switchTo().frame("")	;
		
		// 7 - User get title của trang web
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		// 8 - User get ra URL của trang
		Assert.assertEquals(driver.getCurrentUrl(),"https://tiki.vn/");
		
		// 9 - User get toàn bộ source code của trang
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Thỏa sức mua sắm qua mạng hàng ngàn mặt hàng sách, điện tử, đồ gia dụng"));
		
		//10- lấy ra ID của tab/window hiện tại
		driver.getTitle();
			//chờ cho element xuất hiện để thao tác - WebDriverWaits
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// chờ page được load xing
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			// chờ cho script được thực thi xong
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
			// phóng to screen lên hết cỡ
		driver.manage().window().maximize();
			// test giao dien
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		
		
		// 10- User đóng application/ browser
			//Đóng tab đang làm việc
		driver.close();
			// Đóng cả trình duyệt
		driver.quit();
		

	}

	@Test
	public void TC_01_CheckTitle() {

	}

	@Test
	public void TC_02_CheckURL() {
		// check URL cua page

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

	@Test
	public void TC_01_CheckTitle() {

	}

	@Test
	public void TC_02_CheckURL() {

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
