package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class EditPropertyPOM {
	private WebDriver driver;
	public  EditPropertyPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//p[contains(text(),'Post published.')]")
	WebElement header2;
	
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	WebElement header;
	
	public void validateScreen()
	{
		String actual="Users";
		Assert.verify(header.isDisplayed());
		 Assert.verify(this.header.getText().contains(actual));
		 Assert.verify(header2.isDisplayed()); 
		 
	}

}

