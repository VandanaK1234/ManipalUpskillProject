package com.training.SimpleTestCases.tests;

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
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseLoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.UserProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC01RealEstate_RegisterUserTest {
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
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		homepgpom = new BaseurlHomePOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
		//adding some wait time to load the page
		registerationpg= new RegistrationPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		admindprofilepg= new DashboardAdminPOM(driver);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		//Read userId for deletion from repository
				String userid=properties.getProperty("userID");
		//Login to admin page and  delete the registered user.
		driver.get(properties.getProperty("adminURL"));
	String	adminUserName=properties.getProperty("adminID");
	String adminPwd=properties.getProperty("adminpwd");
		adminloginpg.adminLoginTest(adminUserName, adminPwd);
			//deleting the user from admin profile page
		admindprofilepg.userDeletion(userid);
		//cleaning up the browser
		driver.quit();
	}
	@Test (dataProvider = "xlsx-input-sheet1", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String emailid,String firstName,String lastName) throws InterruptedException 
	{   
		
		//Verify if Login/Register Link is being displayed on home page and click on that link
		homepgpom.loginOrRegisterlnktest();
		//Navigating to Registration tab
		registerationpg.registerTabClick();
		// Verifying field details on Registration screen
		registerationpg.validateRegistrationScreen();
		//Registering new user
		registerationpg.RegisterUser(emailid,firstName,lastName);
		//Validating successful registration message on screen
		registerationpg.validLoginDetails();
		registerationpg.validateRegistrationScreen();
		//Registering again the same user.
		
		registerationpg.RegisterUser(emailid,firstName,lastName);
		//validate that user is not allowed to register again
		registerationpg.InvalidRegistrationDetails();
		
	}
}
