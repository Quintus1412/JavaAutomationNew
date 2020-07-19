package Selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
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

public class Topic_11_User_Interaction {

	// khai bao driver
	private WebDriver driver;
	Actions action;
	WebElement element;
	

	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		 driver = new FirefoxDriver();
		 action = new Actions(driver);
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		
		
		 
	  }

	
	public void TC_01_Hover() {
		
		driver.get("https://www.myntra.com/");
		
		element = driver.findElement(By.xpath("//a[@class='desktop-main'and contains(text(),'Home & Living')]"));
		action.moveToElement(element).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Kitchen & Table']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']/span[text()='Table Covers Collection']")).isDisplayed());
		
		

	}

//	@Test
	public void TC_02_ClickAndHover() {
		
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int itemSize= allItems.size();
		System.out.println(itemSize);
		
		//|0|1|2|3|4|
		
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
		sleepInSecond(3);
		
		List <WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println(selectedItems);
		Assert.assertEquals(selectedItems.size(),4);
		
		
	}
	//@Test
	public void TC_03_ClickAndHoverBlock() {
		
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int itemSize= allItems.size();
		System.out.println(itemSize);
		
		//|0|1|2|3|4|
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(allItems.get(0));
		action.click(allItems.get(5));
		action.click(allItems.get(8));
		action.click(allItems.get(11));
		
		action.keyUp(Keys.CONTROL).perform();
		
		sleepInSecond(3);
		
		List <WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println(selectedItems);
		Assert.assertEquals(selectedItems.size(),4);
		
		
	}
	
	
	public void TC_04_doubleClick() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	
		
		
	}
	
	public void TC_05_rightClick() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		element= driver.findElement(By.xpath("//span[contains(@class,'context-menu-one btn')]"));
		action.contextClick(element).perform();
		sleepInSecond(2);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
		action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]"))).perform();
		sleepInSecond(2);
		driver.switchTo().alert().accept();
		//li[contains(@class,'context-menu-icon-quit')]
		
	}
	
	
	public void TC_06_dragAndDrop() {
		
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		element= driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement elementDesti= driver.findElement(By.xpath(".//*[@id='droptarget']"));
		action.dragAndDrop(element, elementDesti).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		sleepInSecond(2);
		
		
	}
	@Test
	public void TC_06_dragAndDropCss() {
		
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		element= driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement elementDesti= driver.findElement(By.xpath(".//*[@id='droptarget']"));
		action.dragAndDrop(element, elementDesti).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		sleepInSecond(2);
		

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
