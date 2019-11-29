package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class NewPropertyPOM {
  private WebDriver driver;
	public NewPropertyPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	WebElement header;
	
	@FindBy(name="post_title")
	WebElement enterTitleTxt;
	
	@FindBy(xpath="//textarea[@id='content']")
	WebElement textArea;
	
	@FindBy(xpath="//textarea[@id='_price']")
	WebElement priceTxt;
	
	@FindBy(id="_price_per")
	WebElement pricePerSquareTxt;
	
	@FindBy(partialLinkText="Main Details")
	WebElement mainDetailsTab;
	
	@FindBy(id="_status")
	WebElement statusTxt;
	
	@FindBy(id="_location")
	WebElement locationTxt;
	
	@FindBy(id="_possession")
	WebElement possesionTxt;
	
	@FindBy(partialLinkText="Location")
	WebElement locationTab;
	
	@FindBy(id="_friendly_address")
	WebElement addressTxt;
	
	@FindBy(id="_address")
	WebElement googleMapaddressTxt;
	
	@FindBy(id="_geolocation_lat")
	WebElement latitudeTxt;
	
	@FindBy(id="_geolocation_long")
	WebElement longitudeTxt;
	
	@FindBy(partialLinkText="Details")
	WebElement detailsTab;
	
	@FindBy(id="_storage_room")
	WebElement storageRoomTxt;
	
	@FindBy (id="in-region-57")
	WebElement regionChkBox;
	
	@FindBy(id="publish")
	WebElement publishBtn;
	
	public void validateHeader()
	{
		String actual="Add Property";
		Assert.verify(header.isDisplayed());
		 Assert.verify(this.header.getText().contains(actual));
		 
	}
	public void validateTabsDetails()
	{
		
		Assert.verify((this.priceTxt.isDisplayed()) && (this.priceTxt.isEnabled()));
		Assert.verify((this.pricePerSquareTxt.isDisplayed()) && (this.pricePerSquareTxt.isEnabled()));
		
		this.mainDetailsTab.click();
		Assert.verify((this.statusTxt.isDisplayed()) && (this.statusTxt.isEnabled()));
		Assert.verify((this.locationTxt.isDisplayed()) && (this.locationTxt.isEnabled()));
		Assert.verify((this.possesionTxt.isDisplayed()) && (this.possesionTxt.isEnabled()));
		
		this.locationTab.click();
		Assert.verify((this.addressTxt.isDisplayed()) && (this.addressTxt.isEnabled()));
		Assert.verify((this.googleMapaddressTxt.isDisplayed()) && (this.googleMapaddressTxt.isEnabled()));
		Assert.verify((this.latitudeTxt.isDisplayed()) && (this.latitudeTxt.isEnabled()));
		Assert.verify((this.longitudeTxt.isDisplayed()) && (this.longitudeTxt.isEnabled()));
		this.detailsTab.click();
		Assert.verify((this.storageRoomTxt.isDisplayed()) && (this.storageRoomTxt.isEnabled()));
		
	}
	public void addpropertyPriceDetails(String title,String price,String pricePersquare)
	{
		this.enterTitleTxt.clear();
		this.enterTitleTxt.sendKeys(title);
		this.textArea.clear();
		this.textArea.sendKeys(price);
		this.priceTxt.clear();
		this.pricePerSquareTxt.sendKeys(pricePersquare);
		
				
	}
	public void addpropertyMainDetails(String status,String location,String possession)
	{   
		this.mainDetailsTab.click();
		this.statusTxt.clear();
		this.statusTxt.sendKeys(status);
		this.locationTxt.clear();
		this.locationTxt.sendKeys(status);
		this.possesionTxt.clear();
		this.possesionTxt.sendKeys(status);
		
	}
	public void addpropertyLocationDetails(String address,String googleaddress,String longitude,String latitude)
	{   
		this.locationTab.click();
		this.addressTxt.clear();
		this.addressTxt.sendKeys(address);
		this.googleMapaddressTxt.clear();
		this.googleMapaddressTxt.sendKeys(googleaddress);
		this.latitudeTxt.clear();
		this.latitudeTxt.sendKeys(longitude);
		this.longitudeTxt.clear();
		this.longitudeTxt.sendKeys(latitude);
		
	}
	public void addpropertyDetailstab(String storageRoom)
	{
		this.detailsTab.click();
		this.storageRoomTxt.clear();
		this.storageRoomTxt.sendKeys(storageRoom);
		
	}
}
