package com.training.MediumTestCases.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
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
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC03RealEstate_ViewPropertyDetailsTest {
	
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
		newpropertypg= new NewPropertyPOM(driver);
		editpropertypg= new EditPropertyPOM(driver);
		adminpropertiespg= new AdminPropertiesPOM(driver);
		screenShot = new ScreenShot(driver); 
		//Reading admin url from properties file
		String adminurl = properties.getProperty("adminURL");
		//  Opening home page for Retail application in browser
		driver.get(adminurl);
		
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		//Cleaning up the browser
		//Searching and deleting the added property by admin
		adminpropertiespg.searchAndDeleteProperty(properties.getProperty("title"));
		driver.quit();
	}

	
	@Test (dataProvider = "xlsx-input-sheet4", dataProviderClass = LoginDataProviders.class)
	public void ViewPropertyDetails(String title,String price,String pricepersq,String status,String location,String possession,String address,String googleaddress,String longitude,String latitude,String storageRoom) throws InterruptedException, IOException
	{

		String adminid=properties.getProperty("adminID");
		String adminpwd=properties.getProperty("adminpwd");
		String sheet=properties.getProperty("adminID");
		//Admin login
		adminloginpg.adminLoginTest(adminid,adminpwd);
		//validating the admin dashboard
		dashboardadminpg.validateScreen();
		
		dashboardadminpg.propertyLnkclick();
		//Clicking addproperty link to add new property
		adminpropertiespg.addProperties();
		newpropertypg.validateHeader();
		
		//Add property details
		
		newpropertypg.addpropertyPriceDetails(title,price,pricepersq);
		//Publishing the property
		newpropertypg.publishproperty();
		
	
	
		
	}
}
