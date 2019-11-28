package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LostPasswordPOM {
	private WebDriver driver;
	
	public LostPasswordPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//User login/mail id for user
	@FindBy(id="user_login")
	private WebElement user_login;
	
	//Reset pwd button
    @FindBy(name="submit")
    private WebElement resetPwdBtn;
    
    public void resetPwdTest(String emailId)
    {
    	this.user_login.clear();
    	this.user_login.sendKeys(emailId);
    	this.resetPwdBtn.click();
    }
}
