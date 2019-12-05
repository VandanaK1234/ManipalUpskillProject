package com.training.pom;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import common.Assert;

public class RegistrationPOM {
private WebDriver driver;
public RegistrationPOM(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this) ;
}

//Email id of user to be registered
@FindBy(id="email")
private WebElement emailid;

//First name of user
@FindBy(id="first-name")
private WebElement firstNm;

//Last name of user
@FindBy(id="last-name")
private WebElement lastNm;

//Sumit button to register the user details
@FindBy(xpath="//input[@name='submit']")
private WebElement registerBtn;

//Registration tab link
 @FindBy(linkText="Register")
 private WebElement registertabLnk;

 //Invalid emailId message
 
 @FindBy(xpath="//p[contains(text(),'The email address you entered is not valid.')]")
 WebElement invalidEmailmessage;
 
 //Registering the new user
public void RegisterUser(String emailid,String firstNM,String lastNM) throws InterruptedException
{ 
	
	this.emailid.clear();
	this.emailid.sendKeys(emailid);
	this.firstNm.clear();
	this.firstNm.sendKeys(firstNM);
	this.lastNm.clear();
	this.lastNm.sendKeys(lastNM);
	this.registerBtn.click();
	  //  true && true
	if(!(emailid.contains("@")) && !(emailid.contains(".")))
			{
		WebElement element=driver.findElement(By.xpath("//p[contains(text(),'The email address you entered is not valid.')]"));
		String expected="The email address you entered is not valid.";
		String actual=element.getText();
		assertEquals(actual, expected);
		Assert.verify(this.invalidEmailmessage.isDisplayed());
			}
	
}

//Validating that users details are correct and is registered successfully
public void validLoginDetails()
{
	WebElement element=driver.findElement(By.xpath("//*[@id='post-133']/div/div/div/div[1]/p"));
	String expected="You have successfully registered to Real Estate. We have emailed your password to the email address you entered.";
	String actual=element.getText();
	assertEquals(actual, expected);
		
}
//Validating that already registered user is not able to register again
public void InvalidRegistrationDetails()
{
	WebElement element=driver.findElement(By.xpath("//*[@id='post-133']/div/div/div/div[1]/p"));
	String expected="An account exists with this email address.";
			      
	String actual=element.getText();
	assertEquals(actual, expected);
		
}

//Verifying all the elements on Registration screen 
	public void validateRegistrationScreen()
	{   
		Assert.verify((this.emailid.isDisplayed()&&(this.emailid.isEnabled())));
		Assert.verify((this.firstNm.isDisplayed()&&(this.firstNm.isEnabled())));
		Assert.verify((this.lastNm.isDisplayed()&&(this.lastNm.isEnabled())));
		Assert.verify((this.registerBtn.isDisplayed()&&(this.registerBtn.isEnabled())));
	}
	public void registerTabClick()
	{
		 this.registertabLnk.click();
	}
	
}
