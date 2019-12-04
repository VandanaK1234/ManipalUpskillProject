package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(xpath="//li[@id='menu-item-354']//a[contains(text(),'New Launch')]")
	 WebElement newLaunchLnk;
	
	@FindBy(xpath="//div[@id='wpmm-megamenu']//div[contains(@class,'wpmm-posts wpmm-4-posts')]//div[@class='wpmm-post post-681']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")
	WebElement donecQuisImg1;
	
	@FindBy(xpath="//a[contains(text(),'Plots')]")
	WebElement plotsLnk;
	
	@FindBy(xpath="//div[@id='wpmm-megamenu']//div[contains(@class,'wpmm-posts wpmm-3-posts')]//div[@class='wpmm-post post-681']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")
	WebElement donecQuisImg2;
	//Validate the Login/Register link on home page of application and click that link
	public void loginOrRegisterlnktest() 
		{
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='responsive']/li[7]/a")));
			
		Assert.verify((loginRegsterLnk).isDisplayed());
		this.loginRegsterLnk.click();
		}
	
	public void newLaunchLnktest()
	{
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-item-354']//a[contains(text(),'New Launch')]")));
		Actions act= new Actions(driver);
		act.moveToElement(element).build().perform();
		
		element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='wpmm-megamenu']//div[contains(@class,'wpmm-posts wpmm-4-posts')]//div[@class='wpmm-post post-681']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")));
		Assert.verify(this.donecQuisImg1.isDisplayed());
		this.donecQuisImg1.click();
	}
	
	public void plotsLnktest()
	{
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Plots')]")));
		Actions act= new Actions(driver);
		act.moveToElement(this.plotsLnk).build().perform();
		element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='wpmm-megamenu']//div[contains(@class,'wpmm-posts wpmm-3-posts')]//div[@class='wpmm-post post-681']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")));
		Assert.verify(this.donecQuisImg2.isDisplayed());
		this.donecQuisImg2.click();
	}
}

