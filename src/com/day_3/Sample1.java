package com.day_3;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class Sample1 {
	WebDriver driver;
	String geckoDriverpath = "D:\\Testing\\geckodriver.exe";
	@Parameters({"username","uassword"})
	@Test
	 public void Test1(String username, String password) {
	  Testmain(username,password);
	 }

	
	@BeforeMethod
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", geckoDriverpath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://ct.uccpreconfig.fastbooking.ch");
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		driver.quit();
		Thread.sleep(5000);
	}

	public void Testmain(String username, String password) {

		driver.findElement(By.name("txtLoginID")).sendKeys(username);

		// Find Password and Enter Value
		driver.findElement(By.name("pwdKey")).sendKeys(password);

		// Selecting dropdown Value
		Select drpTool = new Select(driver.findElement(By.id("tool")));
		drpTool.selectByVisibleText("Updater");

		// Find Submit button and click
		driver.findElement(By.id("btnLogin")).click();

		// Validation Check
		try {
			Boolean Valid = driver.findElement(By.className("blackLink")).isDisplayed();
			if (Valid) {
				System.out.println("Success");
				// return conditional;
			}
		} catch (Exception e) {
			System.out.println("Invalid Username or passowrd");
		}
	}

}
