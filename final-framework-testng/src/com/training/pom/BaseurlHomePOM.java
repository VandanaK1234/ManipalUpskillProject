package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class BaseurlHomePOM {
private WebDriver driver; 
	
	public BaseurlHomePOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\'responsive\']/li[7]/a")
	private WebElement loginRegsterLnk; 
	
	//Validate the Login/Register link on home page of application
	public void loginOrRegisterlnktest() 
		{
		Assert.verify((loginRegsterLnk).isDisplayed());
		this.loginRegsterLnk.click();
		}
}
