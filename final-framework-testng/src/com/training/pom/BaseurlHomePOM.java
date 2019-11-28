package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class BaseurlHomePOM {
private WebDriver driver; 
	
	public BaseurlHomePOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Login/Register link in home screen
	@FindBy(xpath="//*[@id=\'responsive\']/li[7]/a")
	private WebElement loginRegsterLnk; 

	
	//Validate the Login/Register link on home page of application and click that link
	public void loginOrRegisterlnktest() 
		{
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='responsive']/li[7]/a")));
			
		Assert.verify((loginRegsterLnk).isDisplayed());
		this.loginRegsterLnk.click();
		}
}
