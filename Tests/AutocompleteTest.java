package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AutocompleteTest {
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
	public void clickAutocompleteTest() throws InterruptedException {
		WebElement autocompleteLink = driver.findElement(By.partialLinkText("Auto"));
		autocompleteLink.click();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "clickAutocompleteTest")
	public void addressTest() throws InterruptedException {

		WebElement autocomplete = driver.findElement(By.id("autocomplete"));
		autocomplete.sendKeys("601 Almarida Drive, Campbell, CA");
		Thread.sleep(3000);
		
		WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
		autocompleteResult.click();
		Thread.sleep(5000);
	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}
}
