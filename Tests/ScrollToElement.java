package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollToElement {
	WebDriver driver;
	Actions actions;

	@BeforeSuite
	public void init() {
		/*
		 * Preconditions: Contact the geckodriver,geckodriver finds firefox and play
		 * with it
		 */
		System.setProperty("webdriver.gecko.driver", "/Users/priyankakumar/Desktop/geckodriver");

		// Launch firefox
		driver = new FirefoxDriver();

		actions = new Actions(driver);

		// Set timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void formyLaunchTest() {
		driver.get("http://formy-project.herokuapp.com/");
	}

	@Test(dependsOnMethods = "formyLaunchTest")
	public void clickPageScrollTest() throws InterruptedException {
		WebElement pagescrollLink = driver.findElement(By.partialLinkText("Scroll"));
		pagescrollLink.click();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "clickPageScrollTest")
	public void scrollElementsToViewTest() throws InterruptedException {

		WebElement nameBox = driver.findElement(By.id("name"));
		actions.moveToElement(nameBox);
		nameBox.sendKeys("aman");
		Thread.sleep(3000);

		WebElement dateBox = driver.findElement(By.id("date"));
		dateBox.sendKeys("01/01/2020");
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}
}
