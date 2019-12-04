package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class DonecQuisPOM 
{
	
	private WebDriver driver; 
			
			public DonecQuisPOM(WebDriver driver) 
			{
				this.driver = driver; 
				PageFactory.initElements(driver, this);
			}
    @FindBy (xpath="//h2[contains(text(),'Donec quis')]")	
    WebElement header;
			
	@FindBy(name="your-name")	
	WebElement yourNmTxt;
	
	@FindBy(name="your-email")	
	WebElement yourEmailTxt;
	
	@FindBy(name="your-subject")	
	WebElement subjectTxt;
	
	@FindBy(name="your-message")	
	WebElement messageTxt;
	
	@FindBy(xpath="//input[@class='wpcf7-form-control wpcf7-submit']")
	WebElement sendBtn;
	
	@FindBy(xpath="//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")
	WebElement confirmationText1;
	
	@FindBy(xpath="//a[contains(text(),'Plots')]")
	WebElement plotsLnk;
	
	public void validateHeader()
	{
		Assert.verify(this.header.isDisplayed());
	}
	
	public void sendInquiry(String name,String email,String subject,String message)
	{
		this.yourNmTxt.clear();
		this.yourNmTxt.sendKeys(name);
		this.yourEmailTxt.clear();
		this.yourEmailTxt.sendKeys(email);
		this.subjectTxt.clear();
		this.subjectTxt.sendKeys(subject);
		this.messageTxt.clear();
		this.messageTxt.sendKeys(message);
		this.sendBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3000);
		WebElement element  = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")));
	String actualMsg=this.confirmationText1.getText();
	Assert.verify(actualMsg.contains("There was an error trying to send your message."));
		
	}
	

}
