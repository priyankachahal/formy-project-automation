package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FormTest {
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
	public void clickFormTest() throws InterruptedException {
		WebElement formLink = driver.findElement(By.linkText("Form"));
		formLink.click();
	}

	@Test(dependsOnMethods = "clickFormTest")
	public void submitFormTest() throws InterruptedException {

		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys("aaa");

		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys("bbb");

		WebElement jobTitle = driver.findElement(By.id("job-title"));
		jobTitle.sendKeys("QA");

		WebElement education = driver.findElement(By.id("radio-button-3"));
		education.click();

		WebElement sex = driver.findElement(By.id("checkbox-2"));
		sex.click();

		WebElement yoe = driver.findElement(By.id("select-menu"));
		yoe.click();

		WebElement zeroToOne = driver.findElement(By.xpath("//option[contains(text(),'0-1')]"));
		zeroToOne.click();

		// datepicker
		WebElement datepicker = driver.findElement(By.id("datepicker"));
		datepicker.sendKeys("01/01/2020");
		datepicker.sendKeys(Keys.RETURN);

		WebElement submitButton = driver.findElement(By.xpath("//a[contains(text(),'Submit')]"));
		Thread.sleep(3000);
		submitButton.click();
		Thread.sleep(10000);
		
		WebElement alert = driver.findElement(By.xpath("//div[contains(text(),'successfully')]"));
		String actualAlert = "The form was successfully submitted!";
		String expectedAlert = alert.getText();
//		Assert.assertEquals(actualAlert,expectedAlert);
		Assert.assertTrue(actualAlert.equals(expectedAlert), "Successfully submitted");
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
