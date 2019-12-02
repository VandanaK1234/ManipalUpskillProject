package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.Assert;

public class NewUserPOM 
{
	private WebDriver driver;
	public  NewUserPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user_login")
	WebElement userName;
	
	@FindBy(id="email")
	WebElement eMail;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="url")
	WebElement website;
	
	@FindBy(xpath="//button[@class='button wp-generate-pw hide-if-no-js']")
	WebElement showPwdBtn;
	
	@FindBy(id="pass1-text")
	WebElement pwdTxt;
	
	@FindBy(xpath="//span[contains(text(),'Hide')]")
	WebElement hideBtn;
	
	@FindBy(xpath="//span[contains(text(),'Cancel')]")
	WebElement cancelBtn;
	
	@FindBy(id="role")
	WebElement roleSelect;
	
	@FindBy(id="createusersub")
	WebElement createUserBtn;
	
	public void addNewUser(String userNm,String email,String firstNm,String lastNm,String url,String pwd)
	{
		this.userName.clear();
		this.userName.sendKeys(userNm);
		this.eMail.clear();
		this.eMail.sendKeys(email);
		this.firstName.clear();
		this.firstName.sendKeys(firstNm);
		this.lastName.clear();
		this.lastName.sendKeys(lastNm);
		this.website.clear();
		this.website.sendKeys(url);
		this.showPwdBtn.click();
		this.pwdTxt.clear();
		this.pwdTxt.sendKeys(pwd);
		Assert.verify(this.hideBtn.isDisplayed());
		Assert.verify(this.cancelBtn.isDisplayed());
		Select select1= new Select(this.roleSelect);
		select1.selectByVisibleText("Agent");
		this.createUserBtn.click();
		
	}

}
