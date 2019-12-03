package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class UsersPOM {
	private WebDriver driver;
	public  UsersPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//All users link
	@FindBy(xpath="//a[@class='wp-first-item current']")
	WebElement allUsersLnk;
	
	//Link for adding new user
	
   @FindBy(xpath="//li[@id='menu-users']//ul[@class='wp-submenu wp-submenu-wrap']//li//a[contains(text(),'Add New')]")
   WebElement addNewUserLnk;
   
   //Header title
   @FindBy(xpath="//h1[@class='wp-heading-inline']")
   WebElement header1;
   
   //second header after adding new user
   @FindBy(xpath="//p[contains(text(),'New user created.')]")
   WebElement header2;

	//Search text box to search any user
	@FindBy(xpath="//input[@id='user-search-input']")
	WebElement searchTxt;
	
	//Delete button to delete any user
	@FindBy(xpath="//a[@class='submitdelete']")
	WebElement deleteBtn;
	
	//logout button
	
	@FindBy(xpath="//*[@id='wp-admin-bar-logout']/a")
	WebElement logout;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/table[1]/tbody[1]/tr[1]/td[1]/strong[1]/a[1]")
	WebElement user;
	
	@FindBy(id="submit")
	WebElement confirmDelete;
   
   public void validateScreen()
   {
	   Assert.verify(this.header1.isDisplayed());
	   
	   Assert.verify(this.allUsersLnk.isDisplayed());
	   Assert.verify(this.addNewUserLnk.isDisplayed());
	   
   }
   public void addUser()
   {
	   this.addNewUserLnk.click();
   }
   public void validateuserCreation()
   {
	   Assert.verify(this.header2.isDisplayed());
	  
	   	   
   }
   public void searchUser(String userNm)
   {
	   this.searchTxt.clear();
	   this.searchTxt.sendKeys(userNm,Keys.ENTER);
	   Assert.verify(this.user.isDisplayed());
   } 
   public void deleteUser(String userNm)
   {
	  this.searchUser(userNm);
	  Actions action = new Actions(driver);
  	WebElement element= driver.findElement(By.xpath("//td[@class='username column-username has-row-actions column-primary']"));
  	action.moveToElement(element).build().perform();
		//Deleting that user
		this.deleteBtn.click();
		this.confirmDelete.click();
	    String expected="User deleted";
		String actual=(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[3]/div[2]/p"))).getText();
		Assert.verify(actual.contains(expected));
	 
		element= driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a/img"));
    	action.moveToElement(element).build().perform();
    	this.logout.click();
   }
   
}
