package com.training.pom;


import static org.testng.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class DashboardAdminPOM {
	private WebDriver driver;
	private Actions action;
	public DashboardAdminPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this) ;
	}
	@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
	WebElement header;

	@FindBy(xpath="//*[@id='menu-users']/a/div[3]")
	WebElement usersLnk;
	
	@FindBy(xpath="//input[@id='user-search-input']")
	WebElement searchTxt;
	
	@FindBy(xpath="//a[@class='submitdelete']")
	WebElement deleteBtn;
	
	@FindBy(xpath="//span[@class='edit']//a[contains(text(),'Edit')]")
	WebElement editBtn;
	
	@FindBy(xpath="//*[@id=\'password\']/td/button")
	WebElement pwdGenerateBtn;
	
	@FindBy(id="submit")
	WebElement confirmDelete;
	
	@FindBy(id="pass1-text")
	WebElement password;
	
	@FindBy(name="submit")
	WebElement updateUser;
	
	@FindBy(xpath="//*[@id='wp-admin-bar-logout']/a")
	WebElement logout;
	
	
	public void validateScreen()
		{
		//validating if admin is successfully logged and able to view dasboard
		Assert.verify(this.header.isDisplayed());
		
	}
	public void userDeletion (String userId) throws IOException
	{
		//Clicking the Users link to view all registered users for application
		this.usersLnk.click();
		//Searching the particular user 
		this.searchTxt.clear();
		this.searchTxt.sendKeys(userId);
		this.searchTxt.sendKeys(Keys.ENTER);
		TakesScreenshot scr1= (TakesScreenshot)driver;
		File srcFile=scr1.getScreenshotAs(OutputType.FILE);
		File DestFile=new File("C:\\User\\BeforeUserDeletion.jpg");
    	FileUtils.copyFile(srcFile, DestFile);
    	Actions action = new Actions(driver);
    	WebElement element= driver.findElement(By.xpath("//td[@class='username column-username has-row-actions column-primary']"));
    	action.moveToElement(element).build().perform();
		//Deleting that user
		this.deleteBtn.click();
		this.confirmDelete.click();
	//String expected="User deleted";
	//	String actual=(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[3]/div[2]/p"))).getText();
	//	assertEquals(expected,actual);
		
		//Below code checks if that user still exists in user list
		this.searchTxt.clear();
		this.searchTxt.sendKeys(userId);
		this.searchTxt.sendKeys(Keys.ENTER);
	   srcFile=scr1.getScreenshotAs(OutputType.FILE);
       DestFile=new File("C:\\User\\AfterUserDeletion.jpg");
    	FileUtils.copyFile(srcFile, DestFile);
		
	}
	public void pwdGenerate(String userId) throws InterruptedException
	{
		this.usersLnk.click();
		//Searching the particular user 
		this.searchTxt.clear();
		this.searchTxt.sendKeys(userId);
		this.searchTxt.sendKeys(Keys.ENTER);
		Actions action = new Actions(driver);
    	WebElement element= driver.findElement(By.xpath("//td[@class='username column-username has-row-actions column-primary']"));
    	action.moveToElement(element).build().perform();
    	this.editBtn.click();
    	this.pwdGenerateBtn.click();	
    	this.password.clear();
    	this.password.sendKeys("Chikchik@123");
    	this.updateUser.click();
    	element= driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a/img"));
    	action.moveToElement(element).build().perform();
    	this.logout.click();
    	Thread.sleep(2000);
    	//String expected="User updated";
		//String actual=(driver.findElement(By.xpath("//strong[contains(text(),'User updated.')]"))).getText();
	//	assertEquals(expected,actual);
		
		}
	
}