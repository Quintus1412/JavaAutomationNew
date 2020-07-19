package Selenium_api;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_ButtonRadioCheckboxAlert {

	// khai bao driver
	private WebDriver driver;
	private Select select;
	private WebDriverWait explicitWait;
	private FluentWait fluentWait;
	private JavascriptExecutor javacriptEx;
	// private JavascriptExecutor javacriptEx;
	public Actions action;
	Alert alert;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		action = new Actions(driver);

		javacriptEx = (JavascriptExecutor) driver;
		
		
		explicitWait = new WebDriverWait(driver, 5);

		// chờ load page
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//
//	@Test
	public void TC_01_Button() throws InterruptedException {
		driver.get("https://www.bhphotovideo.com/");

		// Hover to displayed (Camcoder)
//		action.moveToElement(driver.findElement(By.xpath("//a [@data-selenium='computersLink']/span"))).perform();
//		Thread.sleep(2000);
		// Chon 1 thằng con
		javacriptEx.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button [@class='login']")));
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lf-section lf-login-section']")).isDisplayed());
	
		
		// click (selenium

	}
//@Test
public void TC_02_RadioButton() {
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	By dualZoneAir = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	
	//check vào checkbox
	driver.findElement(dualZoneAir).click();
	Assert.assertTrue(driver.findElement(dualZoneAir).isSelected());
	sleepInSecond(2);
	
	//uncheck
	driver.findElement(dualZoneAir).click();
	Assert.assertFalse(driver.findElement(dualZoneAir).isSelected());
	sleepInSecond(2);
	
}


	public void TC_03_CustomRadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		
		By radioButton =By.xpath("//input[@value='Summer']");
		
		javacriptEx.executeScript("arguments[0].click()", driver.findElement(radioButton));
		
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
	}

	public void TC_03_Checkbox() {
		driver.get("https://www.bhphotovideo.com/");

	}
	
	
	//@Test
	public void TC_04_Alert() {
		//driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.get("https://www.google.com/");
		
		//Create alert Accept?
		javacriptEx.executeScript("alert('You will be redirected...')");
		sleepInSecond(3);
		//Switch qua alert
		alert = driver.switchTo().alert();
		alert.accept();
		
		//Create alert confirm?
		javacriptEx.executeScript("confirm('are you sure?')");
		sleepInSecond(3);
		//Switch qua alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"are you sure?");
		
		alert.dismiss();
		
		
		//Create alert prompt?
		javacriptEx.executeScript("prompt('Please fill')");
		sleepInSecond(3);
		//Switch qua alert
		alert = driver.switchTo().alert();
		alert.sendKeys("Quintus test");
		sleepInSecond(2);
		Assert.assertEquals(alert.getText(),"Please fill");
		alert.accept();
		
	}
	
//	@Test
	public void TC_05_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		String name = "Quintus test";
		alert.sendKeys(name);
		sleepInSecond(2);
		alert.accept();
		// Verify result
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: "+name);
		
	}
	
	public void TC_06_AuthenAlert() {
		//driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		String username= "admin";
		String password = "admin";
		
		driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).isDisplayed());
	
	}
	 
	 @Test
		public void TC_07_byPassAuthenAlert() throws Exception {
			
			
			String username= "admin";
			String password = "admin";
			driver.get("http://the-internet.herokuapp.com/");
			
			WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
			String url = basicAuthenLink.getAttribute("href");
			
			driver.get(byPassAuthenAlert(url, username, password));
			
			sleepInSecond(2);
			Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).isDisplayed());
		
		}

		
		public String byPassAuthenAlert( String url, String username, String password) {
			
			
			
			System.out.println("Old URL= "+url);
			String[] slitUrl = url.split("//");
			url = slitUrl[0] +"//"+ username+":"+password+"@"+slitUrl[1];
			
			System.out.println("New Url= "+url);
			return url;
		
		}
	 
	 

	public String getTextByJS(String cssLocator) {
		return (String) javacriptEx.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// random number
	public int randNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public boolean isElementDisplayed(String xpathLocator) {
		if (driver.findElement(By.xpath(xpathLocator)).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementContained(String xpathLocator, String content) {
		if (driver.findElement(By.xpath(xpathLocator)).getText().contains(content)) {
			return true;
		} else {
			return false;
		}
	}

	public int selectedItem(String ParentXpath, String childXpath) {

		driver.findElement(By.xpath(ParentXpath)).click();
		sleepInSecond(1);

		// lấy hết tất cả các iteam đưa vào List <WebElement>
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		System.out.println("Number of item: " + allItems.size());

		// wait cho tơi khi tất cả các item xuất hiện
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		int selectedItem = 0;
		for (int i = 0; i <= allItems.size(); i++) {

			if (allItems.get(i).isSelected()) {
				selectedItem = selectedItem + 1;
				return selectedItem;
			}

		}
		return selectedItem;

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
