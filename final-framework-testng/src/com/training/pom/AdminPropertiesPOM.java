package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	//Link to view All properties 
	@FindBy(partialLinkText="All Properties")
	WebElement allPropertiesLnk;
	
	//Link to add new property
	@FindBy(xpath="//li[@id='menu-posts-property']//ul[@class='wp-submenu wp-submenu-wrap']//li//a[contains(text(),'Add New')]")
	WebElement addNewLnk;
	
	
	//Header of page
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	WebElement header;
	
	//Search text for searching any property
	@FindBy(id="post-search-input")
	WebElement searchTxt;
	
	//Title of searched property 
	@FindBy(xpath="//a[contains(text(),'Test launch1234')]")
	WebElement propertyTitleLnk;
	
	//Link to delete the searched property
	@FindBy(xpath="//tr[@id='post-7579']//a[@class='submitdelete'][contains(text(),'Trash')]")
	WebElement deletepropertyLnk;
	
	//deletion confirmation text
	@FindBy(xpath="//td[@class='colspanchange']")
	WebElement propertyDeletionconfirmation;
	
	public void validateScreen()
	{
	   String actual="Properties";
	   //validating that if header is displaying
	   
	   Assert.verify(this.header.isDisplayed());
	   Assert.verify(this.header.getText().contains(actual));
	   //validating the display of links
	   
	   Assert.verify(this.allPropertiesLnk.isDisplayed());
	   Assert.verify(this.addNewLnk.isDisplayed());
	   	   
	}
	public void searchAndDeleteProperty(String title) throws IOException
	{
		
		//Searching any particular property by its title.
		this.allPropertiesLnk.click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		this.searchTxt.sendKeys(title,Keys.ENTER);
		
		//String expectedtitle=this.propertyTitleLnk.getText();
	//	assertEquals(expectedtitle,title);
		//Mouse hover on title of searched property and deleting that property
		Actions action= new Actions(driver);
		action.moveToElement(propertyTitleLnk).build().perform();

	    TakesScreenshot scrShot =((TakesScreenshot)driver);
	    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	    File DestFile=new File("C:\\User\\SearchedProperty.jpg");
	    	FileUtils.copyFile(SrcFile, DestFile);	
		
	}
	//clicking link to add new property
	public void addProperties()
	{
		this.addNewLnk.click();
	}
	
}

