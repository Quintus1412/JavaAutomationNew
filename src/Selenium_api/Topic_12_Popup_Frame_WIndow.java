package Selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_Popup_Frame_WIndow {

	// khai bao driver
	private WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		action = new Actions(driver);

	}

	// @Test
	public void TC_01_PopUp() {
		driver.get("https://voso.vn/#");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Đăng Nhập']")).click();
		sleepInSecond(3);

		// nếu popup hiển thị thì close popup
		if (isElementDisplayed("//div[@class='modal-content']")) {
			driver.findElement(By.xpath("//button[@class='close']")).click();
			sleepInSecond(3);

		}
		// nếu popup ko hiển thị thì qua step tiếp theo
		Assert.assertFalse(isElementDisplayed("//div[@class='modal-content']"));
		driver.findElement(By.xpath("//input[@class='in_control']")).sendKeys("test@testing.com");
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@class='btn btn-register']")).click();

	}
	// @Test

	public void TC_02_Iframe() {
		driver.get("https://kyna.vn");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		sleepInSecond(2);

		// switch qua iframe facebook và thao tac trên element của iframe đó
		String texttitle = driver.findElement(By.xpath("//a[text()='Kyna.vn']")).getText();
		String like = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();

		System.out.println(texttitle);
		System.out.println(like);

		driver.switchTo().defaultContent();
		/// switch qua Chat
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")).sendKeys("Testing");

		action.sendKeys(driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")), Keys.ENTER).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//form[@ng-submit='editUserInfo()']")).isDisplayed());

		// Switch qua trang parent

		driver.switchTo().defaultContent();
		// Close popup to click search button
		driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close' and @title='Close']")).click();
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("java");
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@class='btn btn-secondary search-button']/parent::span")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='active']")).getText(), "Danh sách khóa học");

		///

		// nếu popup hiển thị thì close popup

	}

	@Test
	public void TC_03_Windown_Tab() {
		
		driver.get("https://kyna.vn");
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close' and @title='Close']")).click();
		
		// Lấy ra ID của window cha hiện hành
		String parentWindowID = driver.getWindowHandle();
		System.out.println("ID cha: "+parentWindowID);
		
		driver.findElement(By.xpath("//img[@alt='android-app-icon']")).click();
		
		
		// kiểm tra ID con khác với ID cha hiện hành thì switch qua con
		switchToWindowByTitle("Kyna 2 - Apps on Google Play");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@aria-label='Install']")).isDisplayed());
		
		// switch về cha
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
		// Từ cha switch qua Facebook
		driver.findElement(By.xpath("//img [@alt='facebook']")).click();
		// String childWindowID2 = driver.getWindowHandle();
		switchToWindowByTitle("Kyna.vn - Trang chủ");
		sleepInSecond(2);
		
		// Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		closeWindowExcepParent(parentWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		sleepInSecond(3);
		
		
	}


	public void TC_03_Windown_Tab2() {

		driver.get("http://live.demoguru99.com/index.php/mobile.html");
		String parentWindow = driver.getWindowHandle();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().equals("The product Sony Xperia has been added to comparison list."));
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().equals("The product Samsung Galaxy has been added to comparison list."));
		
	
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(4);
		//System.out.println("ID cha: "+parentWindowID);
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Close Window']")).isDisplayed());
		
		switchToWindowByTitle("Mobile");
		
		//http://live.demoguru99.com/index.php/catalog/product_compare/index/
		
		sleepInSecond(1);
		closeWindowExcepParent(parentWindow);
		
		

	}

	public void closeWindowExcepParent(String windownParent) {
		
		// Lấy ra tất cả ID của window
		Set<String> allIDs = driver.getWindowHandles();
		System.out.print(allIDs);
		 
		for (String id : allIDs) {
			
			if (!id.equals(windownParent)) {
				
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(windownParent);
		}
		
	}
	public void switchToWindowByTitle(String windownTitle) {
		
		// Lấy ra tất cả ID của window
		Set<String> allIDs = driver.getWindowHandles();
		
			for (String id : allIDs) {
				driver.switchTo().window(id);
				String title = driver.getTitle();
				if (title.equals(windownTitle)) {
				break;
			}
			
		}
		
	}
	public void switchToParent(String parentID) {

		// Lấy ra tất cả ID của window
		Set<String> allIDs = driver.getWindowHandles();

		for (String idItem : allIDs) {
			System.out.println("ID: " + idItem);
			// Nếu khác ID của tham số truyền vào
			if (!idItem.equals(parentID)) {
				// thì switch qua window đó
				driver.switchTo().window(idItem);
				break;

			}

		}

	}

	public boolean isElementDisplayed(String locator) {
		try {
			// 1.element có hiển thị - có trong DOM
			// 2. Element không hiển thị - có trong DOM
			// 3. Element không hiển thị - Ko có trong DOM
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void TC_02_CheckURL() {

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
