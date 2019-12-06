package com.training.ComplexTestCases.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.DonecQuisPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC064_UsersInquiry_NewLaunchTest 
{
	private static WebDriver driver;
	private static String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private static BaseurlHomePOM homepgpom;
	private DonecQuisPOM donecquispg;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException
	{
		//Loading the property file to be referred later in test case
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Initializing home page
		homepgpom= new BaseurlHomePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		
		donecquispg= new DonecQuisPOM(driver);
							
	}
	
	@AfterClass
	public void tearDown() throws Exception
	{
			
        driver.quit();
	
	}

       //Multiple users inquire for Donec Quis apartment via Plots or New Launch section in home page of application.	
	   @Test (priority=1 , dataProvider = "xlsx-input-sheet7", dataProviderClass = LoginDataProviders.class )
	   public void NewLaunchInquiry(String name,String email,String subject, String message)
	   {
		
		homepgpom.newLaunchLnktest();
		donecquispg.validateHeader();
		donecquispg.sendInquiry(name, email, subject, message);
		homepgpom.plotsLnktest();
		donecquispg.validateHeader();
		donecquispg.sendInquiry(name, email, subject, message);
		
	  }
}
