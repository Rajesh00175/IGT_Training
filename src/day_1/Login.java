package day_1;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login {
	String baseUrl = "http://ct.uccpreconfig.fastbooking.ch";
	String driverPath = "D:\\Testing\\geckodriver.exe";
	String actualUser, expectedUser;
	WebDriver driver ;
	WebElement userName,password,ctTool,loginButton,expectedUserHP,validProject;
 
@Test
  public void loginCT() {
	  driver.get(baseUrl);
	  userName = driver.findElement(By.id("txtLoginID"));
	  userName.sendKeys("rthakur");
	  password = driver.findElement(By.id("pwdKey"));
	  password.sendKeys("rthakur");
	  ctTool = driver.findElement(By.id("tool"));
	  Select selectTool = new Select(ctTool);
	  selectTool.selectByValue("UD");
	  loginButton = driver.findElement(By.id("btnLogin"));
	  loginButton.click();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  //Vaidate Home page with user
	  expectedUserHP = driver.findElement(By.xpath("//table[@class='sectionTitle2']/tbody/tr/th/a"));
	  expectedUser = expectedUserHP.getText(); 
	  actualUser = "Rajesh Thakur";
	  Assert.assertEquals(expectedUser, actualUser);
	  System.out.println("Test Pass");
	  validProject = driver.findElement(By.id("ValidProject"));
	  validProject.click();
	  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
  }
  @BeforeTest
  public void browserinit() {
	  System.setProperty("webdriver.gecko.driver", driverPath);		
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void closeBrowser() {
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.quit();
  }

}
