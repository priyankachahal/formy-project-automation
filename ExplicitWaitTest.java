package com.formy.automation.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExplicitWaitTest {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeSuite
	public void init() {
		/*
		 * Preconditions: Contact the geckodriver,geckodriver finds firefox and play
		 * with it
		 */
		System.setProperty("webdriver.gecko.driver", "/Users/priyankakumar/Desktop/geckodriver");

		// Launch firefox
		driver = new FirefoxDriver();
		
		//Explicit wait
		wait = new WebDriverWait(driver, 10);

		// Implicit wait using Set timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void formyLaunchTest() {
		driver.get("http://formy-project.herokuapp.com/");
	}

	@Test(dependsOnMethods = "formyLaunchTest")
	public void clickFileUploadTest() throws InterruptedException {
		WebElement fileUploadLink = driver.findElement(By.partialLinkText("Upload"));
		fileUploadLink.click();
	}

	@Test(dependsOnMethods = "clickFileUploadTest")
	public void fileUploadTestTest() throws InterruptedException {

		WebElement fileUploadBox = driver.findElement(By.id("file-upload-field"));
		wait.until(ExpectedConditions.visibilityOf(fileUploadBox));
		fileUploadBox.sendKeys("PriyankaKumar_resume");
		
		WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
		resetButton.click();
		Thread.sleep(3000);

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}
}
