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
import com.training.pom.AdminPropertiesPOM;
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.EditPropertyPOM;
import com.training.pom.NewPropertyPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC061andRETC062_RegisterViewMultipleUsersTest {
	
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private BaseurlHomePOM homepgpom;
	private RegistrationPOM registerationpg;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM admindprofilepg;
	
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
		registerationpg= new RegistrationPOM(driver);
		adminloginpg= new AdminLoginPOM(driver);
		admindprofilepg= new DashboardAdminPOM(driver);
		
		//Reading base url from properties file
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
		//Verify if Login/Register Link is being displayed on home page and click on that link
				homepgpom.loginOrRegisterlnktest();
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
			
driver.quit();
	
	}
//Login to aplication url and register multiple users.User data is read from excel file via data provider	
	@Test (priority=1 , dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class )
	
	public void registermultipleUsers(String emailid,String firstName,String lastName) throws InterruptedException
	{
		//Navigating to Registration tab
				registerationpg.registerTabClick();
		// Verifying field details on Registration screen
				registerationpg.validateRegistrationScreen();
				//Registering new user
				registerationpg.RegisterUser(emailid,firstName,lastName);
				//Validating successful registration message on screen
				registerationpg.validLoginDetails();
				//Navigating to Registration tab
				registerationpg.registerTabClick();
				//Validating that user already registered is not allowed to register again
				registerationpg.RegisterUser(emailid,firstName,lastName);
				registerationpg.InvalidRegistrationDetails();
				
	}
	
	//Admin login to application
	@Test(priority=3)
	public void AdminLogin()
	{
		driver.get(properties.getProperty("adminURL"));
		String	adminUserName=properties.getProperty("adminID");
		String adminPwd=properties.getProperty("adminpwd");
		adminloginpg.adminLoginTest(adminUserName, adminPwd);
	}
	
//Admin deletes the users which are registered.User details are read from excel file via Data providers
	@Test (priority=4, dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class )
		public void DeleteUsers(String emailid,String firstName,String lastName) throws InterruptedException, IOException
	{
		//deleting the user from admin profile page
		admindprofilepg.userDeletion(emailid);
		//cleaning up the browser
    }
	
	//Admin verify the user details .
		@Test (priority=2, dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class )
		public void viewUserDetails()
		{
			
		}
}
