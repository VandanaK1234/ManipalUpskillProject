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
import com.training.pom.EditPostPOM;
import com.training.pom.EditPropertyPOM;
import com.training.pom.NewLaunchPOM;
import com.training.pom.NewPropertyPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC02RealEstate_ViewEnquireNewLaunchTest
{
	private WebDriver driver;
	private String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM dashboardadminpg;
	private NewLaunchPOM newlaunchpg;
	private EditPostPOM editpostpg;
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
		newlaunchpg= new NewLaunchPOM(driver);
		editpostpg= new EditPostPOM(driver);
		
		//Reading admin url from property file
		
		String adminurl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver); 
		//  Opening admin home page for Retail application in browser
		driver.get(adminurl);
		
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		//Cleaning up the browser
		driver.quit();
	}

	
	@Test (dataProvider = "xlsx-input-sheet5", dataProviderClass = LoginDataProviders.class)
	public void viewEnquireNewLaunch(String name,String email,String subject,String message,String saleprice,String downpayment,String loanTerm,String rate)
	{
		//Admin login
		String adminid=properties.getProperty("adminID");
		String adminpwd=properties.getProperty("adminpwd");
		String sheet=properties.getProperty("adminID");
		adminloginpg.adminLoginTest(adminid,adminpwd);
		//validating the admin dashboard
		dashboardadminpg.validateScreen();
		
		//Clicking new launch link in admin screen
		dashboardadminpg.newLaunchLnk();
		editpostpg.newLaunch();
		//Validating Nullam hendrerit apartment in new launches
		newlaunchpg.NewLaunchVerify();
		
		//Mortgage calculation
		newlaunchpg.mortgageCalculation(saleprice, downpayment, loanTerm, rate);
		//Sending inquiry
		newlaunchpg.sendEnquiry(name, email, subject, message);
		
	}

}
