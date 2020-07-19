package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_08_Custom_Dropdownlist {

	// khai bao driver
	private WebDriver driver;
	private Select select;
	private WebDriverWait explicitWait;
	private FluentWait fluentWait;
	private JavascriptExecutor javacriptEx;
	// private JavascriptExecutor javacriptEx;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 5);

		javacriptEx = (JavascriptExecutor) driver;

		// chờ load page
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//

	public void TC_01_Defaul_DropDown_JQuery() throws InterruptedException {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectIteamInDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()=5]"));

		selectIteamInDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "7");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()=7]"));

		selectIteamInDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "18");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()=18]"));

	}

	// @Test
	public void TC_02_Defaul_DropDown_React() {
		// driver.get("https://demo.nopcommerce.com/");
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectIteamInDropDownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Christian']"));

		selectIteamInDropDownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Elliot Fu");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Elliot Fu']"));

		selectIteamInDropDownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Justen Kitsune");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Justen Kitsune']"));

	}

	// @Test
	public void TC_03_Defaul_DropDown_JS() {
		// driver.get("https://demo.nopcommerce.com/");
		driver.get("https://ej2.syncfusion.com/angular/demos/#/material/drop-down-list/data-binding");

		selectIteamInDropDownList("//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Football");

		selectIteamInDropDownList("//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Golf");

		selectIteamInDropDownList("//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Rugby");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Rugby");

	}

	@Test
	public void TC_04_Defaul_DropDown_Frame() {

		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");

		WebElement iframeElement = driver.findElement(By.xpath("//iframe[@src='templates/template.html?v=189&url=basic.html']"));
		driver.switchTo().frame(iframeElement);

		openDropdownList("(//button[@class='ms-choice'])[1]");
		sleepInSecond(2);
		selectChildItem("(//button[@class='ms-choice'])[1]/following-sibling::div[@class='ms-drop bottom']/ul/li//span", "May");
		selectChildItem("(//button[@class='ms-choice'])[1]/following-sibling::div[@class='ms-drop bottom']/ul/li//span", "September");
		selectChildItem("(//button[@class='ms-choice'])[1]/following-sibling::div[@class='ms-drop bottom']/ul/li//span", "January");
		sleepInSecond(2);
		// Kiểm tra có 3 tháng thì hiện text mỗi tháng
		Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "May"));
		Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "September"));
		Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "January"));

		sleepInSecond(2);
		String selectediteams = driver.findElement(By.xpath("(//div[@class='ms-parent multiple-select'])[1]/button/span")).getText();
		System.out.println("user has selected: " + selectediteams);
		
		// Kiểm tra có 4 tháng thì hiện of 12 selected
		selectChildItem("(//button[@class='ms-choice'])[1]/following-sibling::div[@class='ms-drop bottom']/ul/li//span", "June");
		sleepInSecond(2);
		Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "of 12 selected"));
		
		
		

		// Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "September"));
		// Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "February"));
		// Assert.assertTrue(isElementContained("(//div[@class='ms-parent multiple-select'])[1]/button/span", "February"));

	}

	// public void selectIteamInDropDownList(String ParentXpath, String childXpath, String expectedItem) {
	//
	// // click vào dropdiwn để xúat hiện tất cả các item con
	// driver.findElement(By.xpath(ParentXpath)).click();
	// sleepInSecond(2);
	//
	// // lấy hết tất cả các iteam đưa vào List <WebElement>
	// List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
	//
	// System.out.println("Number of item: " + allItems.size());
	//
	// // wait cho tơi khi tất cả các item xuất hiện
	// explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
	//
	// // Dùng vòng lặp để duyệt qua các item
	// for (WebElement webElement : allItems) {
	// String itemText = webElement.getText();
	//
	// // Kiểm tra gettext() và so sánh với expected text mình mong muốn
	// // Nếu thỏa mãn thì click vào item đó
	// if (itemText.equals(expectedItem)) {
	//
	// webElement.click();
	//
	// break;
	// }
	//
	// }
	//
	// }

	public void openDropdownList(String ParentXpath) {

		// click vào dropdiwn để xúat hiện tất cả các item con
		driver.findElement(By.xpath(ParentXpath)).click();
		sleepInSecond(2);

		// lấy hết tất cả các iteam đưa vào List <WebElement>

	}

	public void selectChildItem(String childXpath, String expectedItem) {

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		System.out.println("Number of item: " + allItems.size());

		// wait cho tơi khi tất cả các item xuất hiện
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Dùng vòng lặp để duyệt qua các item
		for (WebElement webElement : allItems) {
			String itemText = webElement.getText();

			// Kiểm tra gettext() và so sánh với expected text mình mong muốn
			// Nếu thỏa mãn thì click vào item đó
			if (itemText.equals(expectedItem)) {

				webElement.click();

				break;
			}

		}
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
		

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
