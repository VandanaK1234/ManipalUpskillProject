package com.training.SimpleTestCases.tests;

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
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseLoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.UserProfilePOM;
import com.training.pom.UsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC01RealEstate_RegisterUserTest {
	private static WebDriver driver;
	private static String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private static BaseurlHomePOM homepgpom;
	private static RegistrationPOM registerationpg;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM admindprofilepg;
	private UsersPOM userspg;
	
	
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
			
		//Reading base url from properties file
	final String	baseUrl = properties.getProperty("baseURL");
		
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
		//Verify if Login/Register Link is being displayed on home page and click on that link
				homepgpom.loginOrRegisterlnktest();
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		//Initializing all web pages
		registerationpg= new RegistrationPOM(driver);
		adminloginpg= new AdminLoginPOM(driver);
		admindprofilepg= new DashboardAdminPOM(driver);
		userspg= new UsersPOM(driver);
		
		
	}
	
	@AfterClass
	public void tearDown() throws Exception
	{
			
driver.quit();
	
	}
	@Test (priority=1, dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String emailid,String firstName,String lastName) throws InterruptedException 
	{   
		
		
		//Navigating to Registration tab
		registerationpg.registerTabClick();
		// Verifying field details on Registration screen
		registerationpg.validateRegistrationScreen();
		//Registering new user
		registerationpg.RegisterUser(emailid,firstName,lastName);
		//Validating successful registration message on screen
		registerationpg.validLoginDetails();
				
	}
	
	@Test(priority=2)
	public void adminLogin()
	{
		//Read userId for deletion from repository
		String userid=properties.getProperty("userID");
//Login to admin page and  delete the registered user.
driver.get(properties.getProperty("adminURL"));
String	adminUserName=properties.getProperty("adminID");
String adminPwd=properties.getProperty("adminpwd");
adminloginpg.adminLoginTest(adminUserName, adminPwd);
	
	}
	

	@Test (priority=3, dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class )
	public void viewUserDetails(String emailid,String firstName,String lastName) throws IOException
	{
		
		admindprofilepg.userLnkclick();
		userspg.searchUser(emailid);
		userspg.deleteUser(emailid);
	}
}
