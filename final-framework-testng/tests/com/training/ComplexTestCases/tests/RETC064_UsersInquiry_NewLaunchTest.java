package com.training.ComplexTestCases.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
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
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private BaseurlHomePOM homepgpom;
	private DonecQuisPOM donecquispg;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException
	{
		//Loading the property file to be referred later in test case
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Initializing all web pages
		homepgpom= new BaseurlHomePOM(driver);
		donecquispg= new DonecQuisPOM(driver);
		
		
		//Reading base url from properties file
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
			
driver.quit();
	
	}
//Login to aplication url and register multiple users.User data is read from excel file via data provider	
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
