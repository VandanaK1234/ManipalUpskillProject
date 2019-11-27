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
@FindBy(id="email")
private WebElement emailid;

@FindBy(id="first-name")
private WebElement firstNm;

@FindBy(id="last-name")
private WebElement lastNm;

@FindBy(xpath="//input[@name='submit']")
private WebElement registerBtn;

 @FindBy(xpath="/html[1]/body[1]/div[1]/div[4]/div[1]/article[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]")
 private WebElement registerLnk;

public void RegisterUser(String emailid,String firstNM,String lastNM) throws InterruptedException
{ 
	
	this.emailid.clear();
	this.emailid.sendKeys(emailid);
	this.firstNm.clear();
	this.firstNm.sendKeys(firstNM);
	this.lastNm.clear();
	this.lastNm.sendKeys(lastNM);
	this.registerBtn.click();
	Thread.sleep(3000);
}
public void validLoginDetails()
{
	WebElement element=driver.findElement(By.xpath("//*[@id='post-133']/div/div/div/div[1]/p"));
	String expected="You have successfully registered to Real Estate. We have emailed your password to the email address you entered.";
	String actual=element.getText();
	assertEquals(actual, expected);
		
}
public void InvalidLoginDetails()
{
	WebElement element=driver.findElement(By.xpath("//*[@id='post-133']/div/div/div/div[1]/p"));
	String expected="An account exists with this email address.";
			      
	String actual=element.getText();
	assertEquals(actual, expected);
		
}
	public void validateRegistrationScreen()
	{    this.registerLnk.click();
		Assert.verify((this.emailid.isDisplayed()&&(this.emailid.isEnabled())));
		Assert.verify((this.firstNm.isDisplayed()&&(this.firstNm.isEnabled())));
		Assert.verify((this.lastNm.isDisplayed()&&(this.lastNm.isEnabled())));
		Assert.verify((this.registerBtn.isDisplayed()&&(this.registerBtn.isEnabled())));
	}
	
}
