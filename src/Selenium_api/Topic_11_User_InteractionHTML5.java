package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Topic_11_User_InteractionHTML5 {

	// khai bao driver
	private WebDriver driver;
	Actions action;
	private JavascriptExecutor javascriptExecutor;
	private String ROOT_FOLDER = System.getProperty("user.dir");
	WebElement element;
	String javascriptPath = ROOT_FOLDER + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = ROOT_FOLDER + "\\dragAndDrop\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Drag_And_Drop_JS_Jquery() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		// Get the content of Javascript file
		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to application . Web đà dùng jquery rồi
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
	}

	
	// Unable to work
	public void TC_02_Drag_And_Drop_UInteraction() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		WebElement source = driver.findElement(By.xpath("//div[@id='column-a'"));
		WebElement target = driver.findElement(By.xpath("//div[@id='column-b'"));

		action.dragAndDrop(source, target).perform();
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

	}

	@Test

	public void TC_03_Drag_And_Drop_OffSet() throws AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");


		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		

	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public boolean isElementDisplayed(String xpathLocator) {
		if (driver.findElement(By.xpath(xpathLocator)).isDisplayed()) {
			return true;
		} else {
			return false;
		}
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
