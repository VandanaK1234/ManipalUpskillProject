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
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		dashboardadminpg= new DashboardAdminPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		String adminurl = properties.getProperty("adminURL");
		newpropertypg= new NewPropertyPOM(driver);
		editpropertypg= new EditPropertyPOM(driver);
		adminpropertiespg= new AdminPropertiesPOM(driver);
		screenShot = new ScreenShot(driver); 
		//  Opening home page for Retail application in browser
		driver.get(adminurl);
		
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		
		//driver.quit();
	}

	
	@Test (dataProvider = "xlsx-input-sheet4", dataProviderClass = LoginDataProviders.class)
	public void ViewPropertyDetails(String title,String price,String pricepersq,String status,String location,String possession,String address,String googleaddress,String longitude,String latitude,String storageRoom)
	{

		String adminid=properties.getProperty("adminID");
		String adminpwd=properties.getProperty("adminpwd");
		String sheet=properties.getProperty("adminID");
		adminloginpg.adminLoginTest(adminid,adminpwd);
		dashboardadminpg.validateScreen();
		dashboardadminpg.propertyLnkclick();
		
		adminpropertiespg.addProperties();
		newpropertypg.validateHeader();
		//newpropertypg.validateTabsDetails();
		newpropertypg.addpropertyPriceDetails(title,null,null);
		
		newpropertypg.publishproperty();
		newpropertypg.publishproperty();
	editpropertypg.validateScreen();
	adminpropertiespg.searchProperty(title);
		
	}
}
