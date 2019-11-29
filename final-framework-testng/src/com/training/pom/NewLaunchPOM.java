package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Assert;

public class NewLaunchPOM {

	private WebDriver driver;
	public  NewLaunchPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//li[@class='menu-item menu-item-type-taxonomy menu-item-object-region menu-item-354 wpmm-light-scheme menu-item-wpmm-megamenu menu-item-wpmm-taxonomy wpmm-region']//a[contains(text(),'New Launch')]")
	WebElement newLaunchLnk;
	
	@FindBy(xpath="//div[@class='wpmm-light-scheme wpmm-megamenu-container menu-item-354-megamenu wpmm-visible']//div[@class='wpmm-post post-156']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")
	WebElement NullamAptImg;
	
	@FindBy(xpath="//div[contains(@class,'col-md-12')]//button[@class='slick-next slick-arrow'][contains(text(),'Next')]")
	WebElement nextBtn;
	
	@FindBy(name="your-name")
	WebElement yourNameTxt;
	
	@FindBy(name="your-email")
	WebElement yourEmailTxt;
	
	@FindBy(name="your-subject")
	WebElement subjectTxt;
	
	@FindBy(xpath="//textarea[contains(@name,'your-message')]")
	WebElement MessageTxt;
	
	@FindBy(xpath="//input[contains(@class,'wpcf7-form-control wpcf7-submit')]")
	WebElement sendBtn;
	
	@FindBy(xpath="//div[contains(@class,'wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng')]")
	WebElement confirmationText1;
	
	
	@FindBy(name="amount")
	WebElement salePriceTxt;
	
	@FindBy(id="downpayment")
	WebElement downpaymentTxt;
	
	@FindBy(id="years")
	WebElement loanTermtxt;
	
	@FindBy(id="interest")
	WebElement interestRateTxt;
	
	@FindBy(xpath="//button[contains(@class,'button calc-button')]")
	WebElement calculateBtn;
	
	@FindBy(xpath="//strong[contains(@class,'calc-output')]")
	WebElement confirmationTxt2;
	public void sendEnquiry(String name,String email,String subject,String message)
	{

		this.yourNameTxt.clear();
		this.yourNameTxt.sendKeys(name);
		this.yourEmailTxt.clear();
		this.yourEmailTxt.sendKeys(email);
		this.subjectTxt.clear();
		this.subjectTxt.sendKeys(subject);
		this.MessageTxt.clear();
		this.MessageTxt.sendKeys(message);
		this.sendBtn.click();
		String actualMsg=this.confirmationTxt2.getText();
		Assert.verify(actualMsg.contains("There was an error trying to send your message."));
	}
	public void mortgageCalculation(String saleprice,String downpayment,String loanTerm,String rate)
	{
		this.salePriceTxt.clear();
		this.salePriceTxt.sendKeys(saleprice);
		this.downpaymentTxt.clear();
		this.downpaymentTxt.sendKeys(downpayment);
		this.loanTermtxt.clear();
		this.loanTermtxt.sendKeys(loanTerm);
		this.interestRateTxt.clear();
		this.interestRateTxt.sendKeys(rate);
		this.calculateBtn.click();
		String actualMsg=this.confirmationTxt2.getText();
		Assert.verify(actualMsg.contains("Monthly Payment"));
				
	}
	
	
	
}

