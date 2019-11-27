package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPOM {
private WebDriver driver; 
	
	public AdminLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	public void adminLoginTest(String userName,String password)
	{
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear(); 
		this.password.sendKeys(password); 
		this.loginBtn.click(); 
	}
}
