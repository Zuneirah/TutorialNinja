package com.tutorialsNinja.qa.testcases;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.Ninja.qa.utils.Utilities;

public class LoginTest {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		  
		String browserName = "chrome";
		  
		if(browserName.equals("chrome")) {
			
			    driver = new ChromeDriver();	   
			    
		   } else if(browserName.equals("firefox"))  {
			   
			   driver = new FirefoxDriver();
			   
		   } else if (browserName.equals("edge"))  {
			   
			   driver=new EdgeDriver();
		   } 
			   
		   
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.get("https://tutorialsninja.com/demo/");
			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Login")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys("uha24278@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Pass@2023");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit Your Information is not Displayed in Account Page");
	}
	
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Pass@20231");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	   
	}
	
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAddressAndValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Pass@2023");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	    driver.quit();
	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAddressAndInvalidPassword()
	{
		driver.findElement(By.id("input-email")).sendKeys("uha242781");
    	driver.findElement(By.id("input-password")).sendKeys("Pass@202311");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	  
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	  
		
	}
	
	
	
	
	
	

}
