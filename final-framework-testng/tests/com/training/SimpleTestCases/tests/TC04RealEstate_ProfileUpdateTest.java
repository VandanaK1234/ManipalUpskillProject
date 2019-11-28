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
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseLoginPOM;
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.LostPasswordPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.UserProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC04RealEstate_ProfileUpdateTest {
	private WebDriver driver;
	private String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private BaseurlHomePOM homepgpom;
	private BaseLoginPOM loginpgpom;
	private UserProfilePOM userprofilepom;
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
		// open the base url of Retail application in browser
		driver.get(baseUrl);
		loginpgpom= new BaseLoginPOM(driver);
		userprofilepom= new UserProfilePOM(driver);
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
						admindprofilepg.pwdGenerate(emailid);
				//cleaning up the browser
				driver.navigate().to(baseUrl);
				
	}
		
	
	
	@AfterMethod
	public void tearDown() throws Exception {
			
		   driver.navigate().to("http://realestatem1.upskills.in/admin");
			String	adminUserName=properties.getProperty("adminID");
			String adminPwd=properties.getProperty("adminpwd");
			adminloginpg.adminLoginTest(adminUserName, adminPwd);
	//deleting the user from admin profile page
			String userid=properties.getProperty("userID");
admindprofilepg.userDeletion(userid);
	//cleaning up the browser
		//cleaning up the browser
		driver.quit();
	}
		@Test(dataProvider = "xlsx-input-sheet2", dataProviderClass = LoginDataProviders.class)
	     public void updateProfiledetails (String emailId,String pwd) throws InterruptedException, IOException
		      {
			homepgpom.loginOrRegisterlnktest();
			//Enter user details for Logging in to application
			loginpgpom.loginTest(emailId,pwd);
			userprofilepom.validateScreen();
			userprofilepom.updateDetails("Testing","123456");
			
			
	
			
		      }
	
	
}
