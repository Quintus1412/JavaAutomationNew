package Selenium_api;

import org.testng.annotations.Test;



import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Topic_13_JavaExcutor {

	// khai bao driver
	private WebDriver driver;
	Actions action;
	private WebElement element;
	private JavascriptExecutor jsExecutor;
	
	private String email,userID, password, loginPageURL;
	private String name, dob, address, city, state, phone, pin, gender, customerID;
	
	private String editAdd, editCity, editState, editPhone, editPin, editEmail;
	private By nameTextboxBy = By.name("name");
	private By genderBy = By.name("gender");
	private By dobTextboxBy = By.name("dob");
	private By addressTextareaBy = By.name("addr");
	private By cityTextboxBy = By.name("city");
	private By stateTextboxBy = By.name("state");
	private By pinTextboxBy = By.name("pinno");
	private By phoneTextboxBy = By.name("telephoneno");
	
	private By emailTextboxBy = By.name("emailid");
	private By passTexboxBy = By.name("password");
	private By submitButtonBy = By.name("sub");
	
	

	@BeforeClass
	  public void beforeClass() {
		
		 //Khoi tao Chrome
		 System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
		
		 jsExecutor = (JavascriptExecutor) driver;
		 
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 
		 email= "quintus"+randNumber()+"@gmail.com";
			name="Quintus";
			password="123456";
			gender = "male";
			dob="2000-02-02";
			address="1 Red hill";
			city="Melbourne";
			state="Vic";
			phone="9999462";
			pin="888888";
		
		
		 
	  }
	
	
	public void TC_01() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		//Get domain
		String liveGuruDomain= (String) executeForBrowser("return document.domain");
		System.out.println("Live Guru Domain: " + liveGuruDomain);
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruURL= (String) executeForBrowser("return document.URL");
		System.out.println("Live Guru Domain: " + liveGuruURL);
		Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(isTextInInnerHTML("Sony Xperia was added to your shopping cart."));
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		String customerTitle= (String) executeForBrowser("return document.title");
		System.out.println("Customer page title is: " + customerTitle);
		Assert.assertEquals(customerTitle, "Customer Service");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		
		Assert.assertTrue(isTextInInnerHTML("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String demoGuruDomain= (String) executeForBrowser("return document.domain");
		System.out.println("Demo Guru Domain: " + demoGuruDomain);
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
		
		
		
		
		
		
		
		
	}

	@Test
	public void TC_02_RemoveAttribute() {
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		//Log in
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr272837");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rabEmEd");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String WelcomeMsg = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(WelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		
		
		//New customer page
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Ä�iá»�n thÃ´ng tin New User
		driver.findElement(nameTextboxBy).sendKeys(name);
		
		//Run on Chrome (Firefox: textbox)
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(2);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passTexboxBy).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.findElement(submitButtonBy).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		// Verify create new user thÃ nh cÃ´ng
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		customerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),email);
		

	}

	
	public void TC_02_CheckURL() {

	}
	

	// Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean isTextInInnerHTML(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		
		//window.location='https://tiki.vn'
		jsExecutor.executeScript("window.location = '" + url + "'");
		
		
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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
