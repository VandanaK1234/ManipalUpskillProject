package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class NewPropertyPOM {
  private WebDriver driver;
	public NewPropertyPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//Header in the screen
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	WebElement header;
	
	//Enter title text box.
	@FindBy(name="post_title")
	WebElement enterTitleTxt;
	
	//Text area for title
	@FindBy(xpath="//textarea[@id='content']")
	WebElement textArea;
	
	@FindBy(xpath="//textarea[@id='_price']")
	WebElement priceTxt;
	
	@FindBy(id="_price_per")
	WebElement pricePerSquareTxt;
	
	@FindBy(linkText="Main Details")
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
	
	@FindBy(id="ui-id-4")
	WebElement detailsTab;
	
	@FindBy(id="_storage_room")
	WebElement storageRoomTxt;
	
	@FindBy (xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[3]/label[1]/input[1]")
	WebElement regionChkBox;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[2]")
	WebElement publishBtn;
	
	//Validating the header
	public void validateHeader()
	{
		String actual="Add Property";
		Assert.verify(header.isDisplayed());
		 Assert.verify(this.header.getText().contains(actual));
		 
	}
	
	//Validating fields in different tabs
	public void validateTabsDetails()
	{
		//Fields in price tab
		Assert.verify((this.priceTxt.isDisplayed()) && (this.priceTxt.isEnabled()));
		Assert.verify((this.pricePerSquareTxt.isDisplayed()) && (this.pricePerSquareTxt.isEnabled()));
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(("Main Details"))));
		//Fields validation in MainDetails tab
		this.mainDetailsTab.click();
		Assert.verify((this.statusTxt.isDisplayed()) && (this.statusTxt.isEnabled()));
		Assert.verify((this.locationTxt.isDisplayed()) && (this.locationTxt.isEnabled()));
		Assert.verify((this.possesionTxt.isDisplayed()) && (this.possesionTxt.isEnabled()));
		
		 element  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(("Location"))));
		
		 //Fields validation in Location tab
		this.locationTab.click();
		Assert.verify((this.addressTxt.isDisplayed()) && (this.addressTxt.isEnabled()));
		Assert.verify((this.googleMapaddressTxt.isDisplayed()) && (this.googleMapaddressTxt.isEnabled()));
		Assert.verify((this.latitudeTxt.isDisplayed()) && (this.latitudeTxt.isEnabled()));
		Assert.verify((this.longitudeTxt.isDisplayed()) && (this.longitudeTxt.isEnabled()));
		
		 element  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(("Details"))));
		//Fields validation in details tab
		this.detailsTab.click();
		Assert.verify((this.storageRoomTxt.isDisplayed()) && (this.storageRoomTxt.isEnabled()));
		
	}
	
	//Adding property details in price tab
	public void addpropertyPriceDetails(String title,String price,String pricePersquare)
	{
		this.enterTitleTxt.clear();
		this.enterTitleTxt.sendKeys(title,Keys.TAB);
		//this.textArea.clear();
		this.textArea.sendKeys(title,Keys.TAB);
		this.regionChkBox.click();
		this.priceTxt.sendKeys(price,Keys.TAB);
		this.pricePerSquareTxt.sendKeys(pricePersquare);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.id("publish")));
		
		
		
				
	}
	
	//Adding property details in Main Details tab
	public void addpropertyMainDetails(String status,String location,String possession)
	{   
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Main Details")));
		this.mainDetailsTab.click();
		this.statusTxt.clear();
		this.statusTxt.sendKeys(status);
		this.locationTxt.clear();
		this.locationTxt.sendKeys(location);
		this.possesionTxt.clear();
		this.possesionTxt.sendKeys(possession);
		
	}
	
	//Adding property details in Location tab
	public void addpropertyLocationDetails(String address,String googleaddress,String longitude,String latitude)
	{   
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Location")));
		element.click();
		this.addressTxt.clear();
		this.addressTxt.sendKeys(address);
		this.googleMapaddressTxt.clear();
		this.googleMapaddressTxt.sendKeys(googleaddress);
		this.latitudeTxt.clear();
		this.latitudeTxt.sendKeys(longitude);
		this.longitudeTxt.clear();
		this.longitudeTxt.sendKeys(latitude);
		
	}
	
	//Adding property details in Details tab
	public void addpropertyDetailstab(String storageRoom)
	{
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-4")));
		element.click();
		this.storageRoomTxt.clear();
		this.storageRoomTxt.sendKeys(storageRoom);
		
		
		
	}
	public void publishproperty() throws InterruptedException
	{
		this.publishBtn.submit();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		
		//driver.findElement(By.id("publish")).submit();
		
		
	}
}
