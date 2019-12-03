package com.training.MediumTestCases.tests;

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
import com.training.pom.AdminPropertiesPOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.EditPropertyPOM;
import com.training.pom.NewPropertyPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC01RealEstate_AddAllPropertiesTest 
{
	private WebDriver driver;
	private String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM dashboardadminpg;
	private AdminPropertiesPOM adminpropertiespg;
	private NewPropertyPOM newpropertypg;
	private EditPropertyPOM editpropertypg;
	
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
		dashboardadminpg= new DashboardAdminPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		adminpropertiespg= new AdminPropertiesPOM(driver);
		newpropertypg= new NewPropertyPOM(driver);
		editpropertypg= new EditPropertyPOM(driver);
		
		//Reading admin url from properties file
		String adminurl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver); 
		//  Opening admin login page for Retail application in browser
		driver.get(adminurl);
		
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		//Searching and deleting the added property by admin
				adminpropertiespg.searchAndDeleteProperty(properties.getProperty("title"));
				driver.quit();
	}

	
	@Test (dataProvider = "xlsx-input-sheet4", dataProviderClass = LoginDataProviders.class)
	public void addAllProperties(String title,String price,String pricepersq,String status,String location,String possession,String address,String googleaddress,String longitude,String latitude,String storageRoom) throws InterruptedException, IOException
	{
		//admin login
		String adminid=properties.getProperty("adminID");
		String adminpwd=properties.getProperty("adminpwd");
		String sheet=properties.getProperty("adminID");
		adminloginpg.adminLoginTest(adminid,adminpwd);
		
		//Validating the dashboard of admin
		dashboardadminpg.validateScreen();
		//Clicking properties link
		dashboardadminpg.propertyLnkclick();
		//Clicking addproperty link to add new property
		adminpropertiespg.addProperties();
		newpropertypg.validateHeader();
		//newpropertypg.validateTabsDetails();
		//Adding details in different tabs
		newpropertypg.addpropertyPriceDetails(title,price,pricepersq);
		newpropertypg.addpropertyMainDetails(status, location, possession);
		newpropertypg.addpropertyLocationDetails(address, googleaddress, longitude, latitude);
		newpropertypg.addpropertyDetailstab(storageRoom);
		
		//publishing the property
		newpropertypg.publishproperty();
		
			
	}
}

