package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class KeyAndMousePressTest {
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
	public void clicKeyAndMousePressTest() throws InterruptedException {
		WebElement keyandmousepresslink = driver.findElement(By.partialLinkText("Key"));
		keyandmousepresslink.click();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "clicKeyAndMousePressTest")
	public void submitValueTest() throws InterruptedException {

		WebElement textBox = driver.findElement(By.id("name"));
		textBox.sendKeys("aryan");
		textBox.submit();
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = "submitValueTest")
	public void clickButtonTest() throws InterruptedException {

		WebElement button = driver.findElement(By.id("button"));
		button.click();
		Thread.sleep(3000);
	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
