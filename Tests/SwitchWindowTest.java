package com.formy.automation.basics;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SwitchWindowTest {
	WebDriver driver;

	@BeforeSuite
	public void init() {
		/*
		 * Preconditions: Contact the geckodriver,geckodriver finds firefox and play
		 * with it
		 */
		System.setProperty("webdriver.gecko.driver", "/Users/priyankakumar/Desktop/geckodriver");

		// Launch firefox
		driver = new FirefoxDriver();

		// Set timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void formyLaunchTest() {
		driver.get("http://formy-project.herokuapp.com/");
	}

	@Test(dependsOnMethods = "formyLaunchTest")
	public void clickSwitchWindowsTest() throws InterruptedException {
		WebElement switchWindowsLink = driver.findElement(By.partialLinkText("Switch"));
		switchWindowsLink.click();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "clickSwitchWindowsTest")
	public void clickOpenNewTabTest() throws InterruptedException {

		WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
		newTabButton.click();

		String mainWindow = driver.getWindowHandle();

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String window : allWindowHandles) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				Thread.sleep(3000);
				driver.close();
			}
		}

		driver.switchTo().window(mainWindow);
		Thread.sleep(3000);

	}

	@Test(dependsOnMethods = "clickOpenNewTabTest")
	public void clickOpenAlertTest() throws InterruptedException {

		WebElement alertButton = driver.findElement(By.id("alert-button"));
		alertButton.click();
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();

		alert.accept();

		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
