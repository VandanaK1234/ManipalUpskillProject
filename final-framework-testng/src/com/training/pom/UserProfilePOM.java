package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class UserProfilePOM {
	private WebDriver driver;
	public UserProfilePOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this) ;
	}
@FindBy(linkText="My Profile")
private WebElement userProfileLnk;

@FindBy(linkText="Bookmarked Listings")
private WebElement bookmarksLnk;

@FindBy(linkText="Change Password")
private WebElement changepwdLnk;

@FindBy(linkText="Log Out")
private WebElement logoutLnk;

@FindBy(id="first-name")
private WebElement firstNm;

@FindBy(id="last-name")
private WebElement lastNm;

@FindBy(id="agent_title")
private WebElement agentTitle;

@FindBy(id="phone")
private WebElement phone;

@FindBy(xpath="//*[@id='edit_user']/button")
private WebElement saveChgbtn;

@FindBy(xpath="//a[@class='current']")
private WebElement myProfile;

@FindBy(xpath="//*[@id=\'header\']/div[2]/div/div/div/div")
private WebElement logout;


public void updateDetails(String title, String phone)
{  
	this.agentTitle.clear();
	this.agentTitle.sendKeys(title);
	this.phone.clear();
	this.phone.sendKeys(phone);
	this.saveChgbtn.click();	
	WebElement element=driver.findElement(By.xpath("//p[contains(text(),'Your profile has been updated.')]"));
	String expected="Your profile has been updated.";
	String actual=element.getText();
	assertEquals(actual, expected);
	this.logoutLnk.click();
	
}
public void validateScreen()
{
	
	this.myProfile.click();
	Assert.verify(this.userProfileLnk.isDisplayed());
	Assert.verify(this.bookmarksLnk.isDisplayed());
	Assert.verify(this.changepwdLnk.isDisplayed());
	Assert.verify(this.logoutLnk.isDisplayed());
}
public void logout()
{
	this.logoutLnk.click();
	}

}
