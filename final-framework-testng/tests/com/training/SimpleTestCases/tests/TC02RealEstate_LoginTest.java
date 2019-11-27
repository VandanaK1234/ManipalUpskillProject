package com.training.SimpleTestCases.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseLoginPOM;
import com.training.pom.UserProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import common.Assert;

public class TC02RealEstate_LoginTest {
	private WebDriver driver;
	private String baseUrl;
	private BaseLoginPOM baseloginpom;
	private static Properties properties;
	private ScreenShot screenShot;
	private BaseurlHomePOM homepgpom;
	private UserProfilePOM userprofile;
	private RegistrationPOM registerationpg;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM admindprofilepg;
	private  String password;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
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
		// open the base url of Retail application in browser
		driver.get(baseUrl);
		Thread.sleep(5000);
		baseloginpom= new BaseLoginPOM(driver);
		userprofile= new UserProfilePOM(driver);
		registerationpg= new RegistrationPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		admindprofilepg= new DashboardAdminPOM(driver);
		
		//Verify if Login/Register Link is being displayed on home page and click on that link
				homepgpom.loginOrRegisterlnktest();
				// Verifying field details on Registration screen
				registerationpg.validateRegistrationScreen();
				//Registering new user
				String emailid=properties.getProperty("userID");
				String firstName=properties.getProperty("firstNm");
				String lastName=properties.getProperty("lastNm");
				registerationpg.RegisterUser(emailid,firstName,lastName);
				//Validating successful registration message on screen
				registerationpg.validLoginDetails();
				//pwd generation from admin
				driver.get(properties.getProperty("adminURL"));
				String	adminUserName=properties.getProperty("adminID");
				String adminPwd=properties.getProperty("adminpwd");
					adminloginpg.adminLoginTest(adminUserName, adminPwd);
						//deleting the user from admin profile page
				admindprofilepg.pwdGenerate(emailid);
				Thread.sleep(2000);
					//cleaning up the browser
				driver.navigate().to(baseUrl);
				Thread.sleep(2000);
				
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//Read userId for deletion from repository
				String userid=properties.getProperty("userID");
		//Login to admin page and  delete the registered user.
				String	adminUserName=properties.getProperty("adminID");
				String adminPwd=properties.getProperty("adminpwd");
		driver.get(properties.getProperty("adminURL"));
	
		adminloginpg.adminLoginTest(adminUserName, adminPwd);
			//deleting the user from admin profile page
		admindprofilepg.userDeletion(userid);
		//cleaning up the browser
		driver.quit();
				
	}
	@Test (dataProvider = "xlsx-input-sheet2", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String emailId,String password) throws InterruptedException {
		//Validating if Login/Register link is displaying on home page and clicking that
		homepgpom.loginOrRegisterlnktest();
		Thread.sleep(5000);
		//Enter user details for Logging in to application
		baseloginpom.loginTest(emailId,password);
		//Validate if user is successfully logged and can view his Profile page
		userprofile.validateScreen();
		
	}
}
