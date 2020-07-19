package Selenium_api;

import org.testng.annotations.Test;





import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_14_UploadFile {

	// khai bao driver
	private WebDriver driver;
	Actions action;
	private WebElement element;
	private JavascriptExecutor jsExecutor;
	
	private String rootFolderPath = System.getProperty("user.dir");
	
	String image1Name ="28471893_1729427590461252_2115047759216450861_n.jpg";
	String image2Name="Untitled.png";
	String image3Name="123.jpg";
	
	String images1Path = rootFolderPath +"\\Images\\"+ image1Name;
	String images2Path = rootFolderPath +"\\Images\\"+  image2Name;
	String images3Path = rootFolderPath +"\\Images\\"+  image3Name;
	
	String firefoxscript = rootFolderPath+ "\\AutoIT_Scripts\\firefoxUploadMultiple.exe";


	@BeforeClass
	  public void beforeClass() {
		
		 //Khoi tao Chrome
		 System.setProperty("webdriver.gecko.driver", rootFolderPath+ "\\Drivers\\geckodriver.exe");
		 driver = new FirefoxDriver();
		
		 jsExecutor = (JavascriptExecutor) driver;
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 
		 
	  }
	
	
	public void TC_01() {
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement fileUpload = driver.findElement(By.xpath("//input[@name='files[]']"));
		fileUpload.sendKeys(images1Path+"\n"+images2Path+"\n"+images3Path);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image1Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2Name+"']")).isDisplayed());
		
		//Click vào Start button tại từng file
		List <WebElement> startButtons = driver.findElements(By.cssSelector("table  .start"));
		for (WebElement button:startButtons) {
			button.click();
			sleepInSecond(1);
		}
		
		// 3 file dc upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image1Name+ "' and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image2Name+ "' and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image3Name+ "' and @href]")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_02_AutoIT() throws IOException {
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement fileUpload = driver.findElement(By.cssSelector(".btn-success"));
		fileUpload.click();
		
		sleepInSecond(3);
		
		Runtime.getRuntime().exec(new String[] {firefoxscript, images1Path});
		sleepInSecond(3);
		
		
		
		
		
	}

	
	public void TC_02_RemoveAttribute() {
	
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
