package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExecuteJavascriptTest {
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
	public void clickModalTest() throws InterruptedException {
		WebElement modalLink = driver.findElement(By.linkText("Modal"));
		modalLink.click();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "clickModalTest")
	public void clickOpenModalTest() throws InterruptedException {

		WebElement modalButton = driver.findElement(By.id("modal-button"));
		modalButton.click();
		Thread.sleep(3000);

	}

	@Test(dependsOnMethods = "clickOpenModalTest")
	public void clickOpenAlertTest() throws InterruptedException {

		WebElement closeButton = driver.findElement(By.id("close-button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", closeButton);
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
