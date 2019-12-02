package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class AdminPropertiesPOM {
      private WebDriver driver;
	public AdminPropertiesPOM(WebDriver driver  )
	{     
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(partialLinkText="All Properties")
	WebElement allPropertiesLnk;
	
	@FindBy(xpath="//li[@id='menu-posts-property']//ul[@class='wp-submenu wp-submenu-wrap']//li//a[contains(text(),'Add New')]")
	WebElement addNewLnk;
	
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	WebElement header;
	
	@FindBy(id="post-search-input")
	WebElement searchTxt;
	
	@FindBy(partialLinkText="Test Launch")
	WebElement propertyTitleLnk;
	
	@FindBy(xpath="//a[@class='submitdelete']")
	WebElement deletepropertyLnk;
	
	@FindBy(xpath="//td[@class='colspanchange']")
	WebElement propertyDeletionconfirmation;
	public void validateScreen()
	{
	   String actual="Properties";
	   Assert.verify(this.header.isDisplayed());
	   Assert.verify(this.header.getText().contains(actual));
	   Assert.verify(this.allPropertiesLnk.isDisplayed());
	   Assert.verify(this.addNewLnk.isDisplayed());
	   	   
	}
	public void searchProperty(String title)
	{
		this.allPropertiesLnk.click();
		this.searchTxt.sendKeys(title);
		String expectedtitle=this.propertyTitleLnk.getText();
		assertEquals(expectedtitle,title);
		Actions action= new Actions(driver);
		action.moveToElement(propertyTitleLnk).build().perform();
		this.deletepropertyLnk.click();
		String expected="No Properties found";
		String actual= this.propertyDeletionconfirmation.getText();
		Assert.verify(actual.contains(expected));
	}
	public void addProperties()
	{
		this.addNewLnk.click();
	}
	
}

