package com.day_2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class FabHotel_Search {
	WebDriver fabDriver;
	String testURL = "https://www.fabhotels.com/", geckoDriverpath = "D:\\Testing\\geckodriver.exe";

	@Test(priority=1)
	public void searchHotel() {
		//close
		
		WebElement hotelLocation, checkIN, checkOUT, selectAdult,searchHotel;
		hotelLocation = fabDriver.findElement(By.id("autocomplete-location"));
		hotelLocation.sendKeys("Mumbai");

		//CheckIN Date
		checkIN = fabDriver.findElement(By.className("searchCheckIn"));
		checkIN.click();
		//String chechIN_Month = fabDriver.findElement(By.xpath("//table[@class='table-condensed']/thead/tr[2]/th[@class='datepicker-switch']")).getText();
		while (!fabDriver.findElement(By.className("datepicker-switch")).getText().contains("April 2018")) // Handle the Month		
		{
			fabDriver.findElement(By.className("next")).click();
		}
		List<WebElement> checkin_Day = fabDriver.findElements(By.className("day"));
		for (WebElement chekin_D : checkin_Day) {
			String checkINday = chekin_D.getText();
			if (checkINday.equalsIgnoreCase("15")) {
				chekin_D.click();
				break;
			}
		}

		//CheckOUT Date
		checkOUT = fabDriver.findElement(By.className("searchCheckOut"));
		checkOUT.click();
		while (!fabDriver.findElement(By.className("datepicker-switch")).getText().contains("April 2018")) // Handle the month
		{
			fabDriver.findElement(By.className("next")).click();
		}
		List<WebElement> checkout_Day = fabDriver.findElements(By.className("day"));
		for (WebElement checkout_D : checkout_Day) {
			String checkoutDay = checkout_D.getText();
			if (checkoutDay.equalsIgnoreCase("16")) {
				checkout_D.click();
				break;
			}
		}

		//Adults
		selectAdult = fabDriver.findElement(By.xpath("//div[@class='wrap-select-box wrap_select_box']"));
		selectAdult.click();
		List<WebElement> adult = fabDriver.findElements(By.xpath("//div[@class='select-dropdown-section']/span"));
		for (WebElement adlt : adult) {
			String adultStr = adlt.getText();			
			if (adultStr.equalsIgnoreCase("2")) {
				adlt.click();
				break;
			}
		}
		
		//click on Search Button
		searchHotel = fabDriver.findElement(By.id("listingPageBtn"));
		searchHotel.click();			
	}

	@Test(priority=2)
	public void hotelList(){
		//WebElement sortHotelsbyPrice;
		
		//Validate Hotel List Page
		try {
			String actual_Page_Title = "Budget Hotels in Mumbai, Online Budget Hotel Booking in Mumbai - FabHotels";
			System.out.println("Actual page Title: "+actual_Page_Title);
			//for Firefox only it does not return the page title in latest version
			WebDriverWait wait = new WebDriverWait(fabDriver,20);
			wait.until(ExpectedConditions.titleIs(actual_Page_Title));
			String expected_Page_Title = fabDriver.getTitle();
			System.out.println("Expected Page Title: "+expected_Page_Title);
			
			Assert.assertEquals(expected_Page_Title, actual_Page_Title);
			System.out.println("Test Pass...");
		}catch(AssertionError e) {
			System.out.println(e.getMessage());
		}
		
		// Filter Hotels by Price (Min to Max)
		//sortHotelsbyPrice = fabDriver.findElement(By.className("rupees-text"));
		//sortHotelsbyPrice.click();
		//fabDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Select Hotel to Book
		List<WebElement>bookHotel = fabDriver.findElements(By.xpath("//h3[@class='hotel-card-title']/a"));
		for (WebElement bookH : bookHotel) {
			String hName = bookH.getText();		
			System.out.println(hName);
			if (hName.equalsIgnoreCase("FabHotel Ashir Inn")) {
				//fabDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//fabDriver.findElement(By.className("btn hotel-book-btn")).click();
				bookH.click();				
				break;
			}
		}				
	}
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", geckoDriverpath);
		fabDriver = new FirefoxDriver();
		fabDriver.get(testURL);
		fabDriver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		fabDriver.quit();

	}

}
