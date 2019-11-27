package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class BaseLoginPOM {
private WebDriver driver; 
private Actions action;
	
	public BaseLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(linkText=" Lost Your Password?")
	private WebElement lostPwdLnk; 
	
	
	//Logging into application as normal user
	public void loginTest(String userName,String password)
	{
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear(); 
		this.password.sendKeys(password); 
		this.loginBtn.click(); 
	}
	
	//Clicking the Lost password link to reset password
	public void lostPwdLnkTest()
	{   
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.partialLinkText("Lost Your Password?")));
		action=  new Actions(driver);
		action.moveToElement(element).build().perform();
		
		element.click();
		
		
	}
	
	}
