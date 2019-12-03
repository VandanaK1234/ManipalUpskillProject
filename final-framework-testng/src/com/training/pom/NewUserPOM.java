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
	//UserLogin in new user screen
	@FindBy(id="user_login")
	WebElement userName;
	
	//email in new user screen
	@FindBy(id="email")
	WebElement eMail;
	
	//firstName Login in new user screen
	@FindBy(id="first_name")
	WebElement firstName;
	
	//lastName in new user screen
	@FindBy(id="last_name")
	WebElement lastName;
	
	//url in new user screen
	@FindBy(id="url")
	WebElement website;
	
	//button to show in new user screen
	@FindBy(xpath="//button[@class='button wp-generate-pw hide-if-no-js']")
	WebElement showPwdBtn;
	
	//password text  in new user screen
	@FindBy(id="pass1-text")
	WebElement pwdTxt;
	
	//hide button new user screen
	@FindBy(xpath="//span[contains(text(),'Hide')]")
	WebElement hideBtn;
	
	//cancel button in new user screen
	@FindBy(xpath="//span[contains(text(),'Cancel')]")
	WebElement cancelBtn;
	
	//Role selectbox in new user screen
	@FindBy(id="role")
	WebElement roleSelect;
	
	//submit button to create new user
	@FindBy(id="createusersub")
	WebElement createUserBtn;
	
	//adding details for new user
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
