package com.tutorialsNinja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tutorials.Ninja.qa.utils.Utilities;
public class RegisterTest {
	
	
	@Test (priority =1)
	public void VerifyRegisteringAccountWithMandatoryFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Abdul");
		driver.findElement(By.id("input-lastname")).sendKeys("Shaikh");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualMessage, "Your Account Has Been Created!", "Account Success Page is not displayed!");
		driver.quit();
		
	}
	
	
	@Test (priority =2)
	public void VerifyRegisteringAccountByProvidingAllFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Abdul");
		driver.findElement(By.id("input-lastname")).sendKeys("Shaikh");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualMessage, "Your Account Has Been Created!", "Account Success Page is not displayed!");
		driver.quit();
		
	}

	@Test (priority =3)
	public void VerifyRegisteringAccountByExistingEmailAddress() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Abdul");
		driver.findElement(By.id("input-lastname")).sendKeys("Shaikh");
		driver.findElement(By.id("input-email")).sendKeys("uha24278@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		
		Assert.assertTrue(actualMessage.contains("Warning: E-Mail Address is already registered!"),"No Warning message for registered email address");

		driver.quit();
		
	}
	
	
	@Test (priority =4)
	public void VerifyRegisteringAccountWithoutFillingAnyDetails() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyWarning.contains("Warning: You must agree to the Privacy Policy!"),"No Warning message for Privacy Policy");
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		//Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"),"No Warning message for First Name");
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!","No Warning message for First Name");
		
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertTrue(actualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"),"No Warning message for Last Name");
		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertTrue(actualEmailWarning.contains("E-Mail Address does not appear to be valid!"),"No Warning message for Email Address");
		
		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertTrue(actualTelephoneWarning.contains("Telephone must be between 3 and 32 characters!"),"No Warning message for Telephone Address");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(actualPasswordWarning.contains("Password must be between 4 and 20 characters!"),"No Warning message for Password Field");
		
		//driver.quit();
		
	}
}
