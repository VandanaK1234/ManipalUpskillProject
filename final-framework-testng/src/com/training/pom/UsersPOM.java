package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class UsersPOM {
	private WebDriver driver;
	public  UsersPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='wp-first-item current']")
	WebElement allUsersLnk;
	
   @FindBy(xpath="//li[@id='menu-users']//ul[@class='wp-submenu wp-submenu-wrap']//li//a[contains(text(),'Add New')]")
   WebElement addNewUserLnk;
   
   @FindBy(xpath="//h1[@class='wp-heading-inline']")
   WebElement header1;
   
   @FindBy(xpath="//p[contains(text(),'New user created.')]")
   WebElement header2;
   
   public void validateScreen()
   {
	   Assert.verify(this.header1.isDisplayed());
	   
	   Assert.verify(this.allUsersLnk.isDisplayed());
	   Assert.verify(this.addNewUserLnk.isDisplayed());
	   
   }
}
