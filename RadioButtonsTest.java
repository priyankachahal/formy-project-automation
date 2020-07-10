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

public class RadioButtonsTest {
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
	public void clickRadioButtonTest() throws InterruptedException {
		WebElement radioButtonLink = driver.findElement(By.partialLinkText("Radio"));
		radioButtonLink.click();
	}

	@Test(dependsOnMethods = "clickRadioButtonTest")
	public void radioButtonTest() throws InterruptedException {

		WebElement radioButton1 = driver.findElement(By.id("radio-button-1"));
		radioButton1.click();
		Thread.sleep(3000);

		WebElement radioButton2 = driver.findElement(By.xpath("//input[@value='option2']"));
		radioButton2.click();
		Thread.sleep(3000);

		WebElement radioButton3 = driver.findElement(By.xpath("//input[@value='option3']"));
		radioButton3.click();
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}
}
