package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Assert;

public class NewLaunchPOM {

	private WebDriver driver;
	public  NewLaunchPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//New Lauch Menu
	@FindBy(xpath="//li[@class='menu-item menu-item-type-taxonomy menu-item-object-region menu-item-354 wpmm-light-scheme menu-item-wpmm-megamenu menu-item-wpmm-taxonomy wpmm-region']//a[contains(text(),'New Launch')]")
	WebElement newLaunchLnk;
	
	@FindBy(xpath="//div[@class='wpmm-light-scheme wpmm-megamenu-container menu-item-354-megamenu wpmm-visible']//div[@class='wpmm-post post-156']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")
	WebElement NullamAptImg;
	
	@FindBy(xpath="//a[@class='item mfp-gallery slick-slide slick-current slick-active']")
	WebElement image;
	
	//Next button to navigate to new image 
	@FindBy(xpath="//div[contains(@class,'col-md-12')]//button[@class='slick-next slick-arrow'][contains(text(),'Next')]")
	WebElement nextBtn;
	
	//Name text for inquiry form
	@FindBy(name="your-name")
	WebElement yourNameTxt;
	
	//Email text for inquiry form
	@FindBy(name="your-email")
	WebElement yourEmailTxt;
	
	//Subject text for inquiry form
	@FindBy(name="your-subject")
	WebElement subjectTxt;
	
	//Message text for inquiry form
	@FindBy(xpath="//textarea[contains(@name,'your-message')]")
	WebElement MessageTxt;
	
	//Submit button to send any inquiry	
	@FindBy(xpath="//input[contains(@class,'wpcf7-form-control wpcf7-submit')]")
	WebElement sendBtn;
	
	//Confirmation text after sending inquiry
	@FindBy(xpath="//div[contains(@class,'wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng')]")
	           
	WebElement confirmationText1;
	
	//amount text for mortgage calculation
	@FindBy(name="amount")
	WebElement salePriceTxt;
	
	//downpayment text for mortgage calculation
	@FindBy(id="downpayment")
	WebElement downpaymentTxt;
	
	//years text for mortgage calculation
	@FindBy(id="years")
	WebElement loanTermtxt;
	
	//interest text for mortgage calculation
	@FindBy(id="interest")
	WebElement interestRateTxt;
	
	//Calculate button for mortgage calculation
	@FindBy(xpath="//button[contains(@class,'button calc-button')]")
	WebElement calculateBtn;
	
	//Calculated mortgage amount
	@FindBy(xpath="//strong[contains(@class,'calc-output')]")
	WebElement confirmationTxt2;
	
	//Sending Inquiry
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
	     WebDriverWait wait = new WebDriverWait(driver,3000);
			WebElement element  = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")));
		String actualMsg=this.confirmationText1.getText();
		//assertEquals(actualMsg,"There was an error trying to send your message.");
		Assert.verify(actualMsg.contains("There was an error trying to send your message."));
	}
	
	// Mortgage 
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
		 WebDriverWait wait = new WebDriverWait(driver,3000);
			WebElement element  = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='notification success']")));
		String actualMsg=this.confirmationTxt2.getText();
		Assert.verify(actualMsg.contains("1667.11 Rs"));
				
	}
	public void NewLaunchVerify()
	{
		Actions action= new Actions(driver);
		action.moveToElement(this.newLaunchLnk).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element  = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='wpmm-light-scheme wpmm-megamenu-container menu-item-354-megamenu wpmm-visible']//div[@class='wpmm-post post-156']//img[@class='attachment-wpmm_thumb size-wpmm_thumb wp-post-image']")));
		//Assert.verify(this.NullamAptImg.isDisplayed());
		this.NullamAptImg.click();
		action.moveToElement(this.image).build().perform();
		this.nextBtn.click();
	}
	
	
	
}

