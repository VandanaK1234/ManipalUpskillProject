package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminLoginPOM {
private WebDriver driver; 
	
	public AdminLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//admin loginn id text box
	@FindBy(id="user_login")
	private WebElement userName; 
	
	//admin pwd text box
	@FindBy(id="user_pass")
	private WebElement password;
	
	//login button
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	
	
	//admin login in application
	public void adminLoginTest(String userName,String password)
	{
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear(); 
		this.password.sendKeys(password); 
		this.loginBtn.click(); 
	}
}
