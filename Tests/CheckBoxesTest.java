package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CheckBoxesTest {

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
	public void clickCheckBoxTest() throws InterruptedException {
		WebElement checkBoxLink = driver.findElement(By.partialLinkText("Check"));
		checkBoxLink.click();
	}

	@Test(dependsOnMethods = "clickCheckBoxTest")
	public void checkBoxTest() throws InterruptedException {

		WebElement checkBox1 = driver.findElement(By.id("checkbox-1"));
		checkBox1.click();
		Thread.sleep(3000);

		WebElement checkBox2 = driver.findElement(By.id("checkbox-2"));
		checkBox2.click();
		Thread.sleep(3000);

		WebElement checkBox3 = driver.findElement(By.id("checkbox-3"));
		checkBox3.click();
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
