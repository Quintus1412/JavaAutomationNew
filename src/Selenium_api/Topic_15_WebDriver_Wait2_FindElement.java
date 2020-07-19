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

public class Topic_15_WebDriver_Wait2_FindElement {

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

	
	
	public void TC_findElemt() {
		driver.get("");
		driver.findElement(By.xpath("")).sendKeys("");
		
		WebElement emailtetbox = driver.findElement(By.xpath(""));
		emailtetbox.clear();
		emailtetbox.sendKeys("abcxyz");
		//1. Nếu tìm thấy 1 elemen thì sẽ làm gì
		// Thao tác với Element này
		
		//2. Nếu tìm thấy hơn 1 element thì sẽ làm gì
		// Thao tác với element đầu tiên
		
		//3. Nếu ko tìm thấy element nào thì sẽ làm gì
		// Chờ cho đến khi hết timeout của implicit wait
		//Trong thời gian chờ cứ mỗi 0.5sec nó sẽ tìm lại 1 lần:
		// --Nếu như có tìm thấy element thì nó sẽ thao tác vơi element đó ->Passed step và ko cần chờ hêt timeout
		//-- Nếu như ko tìm thấy mà hết timeout
		////+ Đánh failed test case
		////+ Throw ra 1 exception: No such element
		
		
	}
	public void TC_findElements() {
		driver.get("https://accounts.spotify.com/en/login?continue=https:%2F%2Fwww.spotify.com%2Fvn-en%2Faccount%2Foverview%2F");
		driver.findElements(By.xpath("")).get(0).click();
		
		
		//Khai báo biến dùng lại nhiều lần
		List <WebElement> textboxes = driver.findElements(By.xpath("//input"));
		textboxes.get(0).click();
		
		//1. Nếu tìm thấy 1 elemen thì sẽ làm gì
		// Sẽ gán element đó vào list
		
		//2. Nếu tìm thấy hơn 1 element thì sẽ làm gì
		//Gán danh sách element vào list
				
		//3. Nếu ko tìm thấy element nào thì sẽ làm gì
		// Chờ cho đến khi hết timeout của implicit wait
		//Trong thời gian chờ cứ mỗi 0.5sec nó sẽ tìm lại 1 lần:
		// --Nếu như có tìm thấy element thì nó sẽ thao tác vơi element đó ->Passed step và ko cần chờ hêt timeout
		//-- Nếu như ko tìm thấy mà hết timeout
		////+ ko có element nào để nhét vô List nên List Empty= 0
		////+ KO đánh failed test case
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
