package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_07_TextBox_TextArea {

	// khai bao driver
	private WebDriver driver;
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
		// 1 - Mở browser
		driver = new FirefoxDriver();
		// 2- nhập vào URL
		driver.get("http://demo.guru99.com/v4/");
		// chờ load page
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email= "quintus"+randNumber()+"@gmail.com";
		name="Quintus";
		gender = "male";
		dob="2000-02-02";
		address="1 Red hill";
		city="Melbourne";
		state="Vic";
		phone="314156462";
		pin="124699";
		
		editAdd = "123 spring vale";
		editCity = "Sydney";
		editState = "NSW";
		editPhone = "87636654";
		editPin = "555555";
		editEmail = "quintusdang"+randNumber()+"@hotmail.com";

	}

	//*
//	Login page Register new
//	account page
//	Home page
//	New Customer
//	page
//	New
//	Customer success
//	page Edit
//	Customer page
//	Edit customer success page
	
	//

	@Test
	public void TC_01_RegisterToSystem() {
		
		// lưu URLcua page login
		loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//lấy userID sau khi register
		userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println("User ID is "+ userID);
		
		password= driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("Password is "+ password);
		
		
	}

	@Test
	public void TC_02_LoginToSystem() {
		
		// đi tới URL trang Log in
		driver.get(loginPageURL);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+userID+"']")).isDisplayed());
		
		String WelcomeMsg = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(WelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");

	}
	
	@Test
	public void TC_03_NewCustomer() {
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	
		// Điền thông tin New User
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passTexboxBy).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(submitButtonBy).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Verify create new user thành công
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
		
		//customerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_04_EditCustomer() {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(genderBy).getAttribute("value"),gender);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"),dob);
		Assert.assertEquals(driver.findElement(addressTextareaBy).getText(),address);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"),phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),email);
//		
		
		
		
		// check 3 field bịdisabled
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderBy).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		
		
		
		// truyen gia tri can thay doi
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAdd);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		
		
		driver.findElement(submitButtonBy).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//gia1 trị giữ nguyên sau khi edit
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(),dob);
		
		// gia tri thay doi sau khi edit
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),editAdd);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(),editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),editEmail);
		
		
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
