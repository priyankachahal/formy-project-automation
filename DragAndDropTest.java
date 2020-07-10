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

public class DragAndDropTest {
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
	public void clickDragAndDropTest() throws InterruptedException {
		WebElement dragAndDropLink = driver.findElement(By.partialLinkText("Drag"));
		dragAndDropLink.click();
	}

	@Test(dependsOnMethods = "clickDragAndDropTest")
	public void dragAndDropTest() throws InterruptedException {

		WebElement imagelogo = driver.findElement(By.id("image"));
	
		WebElement dropArea = driver.findElement(By.id("box"));
		actions.dragAndDrop(imagelogo,dropArea).build().perform();	

	}

	@AfterSuite()
	public void teardown() {
		driver.close();
	}

}
