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
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.NewUserPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.UsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC04RealEstate_ViewUsersTest {
	private static final String fileName = null;
	private static final String sheetNm = null;
	private WebDriver driver;
	private String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private AdminLoginPOM adminloginpg;
	private DashboardAdminPOM dashboardadminpg;
	private UsersPOM admin_userspg;
	private NewUserPOM newuserpg;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException
	{
		//Loading the property file to be referred later in test case
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Initializing all web pages
				dashboardadminpg= new DashboardAdminPOM(driver);
				adminloginpg=new AdminLoginPOM(driver);
				admin_userspg= new UsersPOM(driver);
				newuserpg= new NewUserPOM(driver);
				
				//Reading admin url from properties file
		String adminurl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver); 
		//  Opening admin login page for Retail application in browser
		driver.get(adminurl);
		
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		//Deleting the user added by admin from  new users screen
		admin_userspg.deleteUser(properties.getProperty("userNm"));
		driver.quit();
	}
		
		@Test (dataProvider = "xlsx-input-sheet3", dataProviderClass = LoginDataProviders.class)
				public void addAndViewUsers(String userNm,String email,String firstNm,String lastNm,String url,String pwd)
		{
			
			//Admin login
			String adminid=properties.getProperty("adminID");
			String adminpwd=properties.getProperty("adminpwd");
			String sheet=properties.getProperty("adminID");
			adminloginpg.adminLoginTest(adminid,adminpwd);
			//Validating admin dashboard
			dashboardadminpg.validateScreen();
			//Clicking users link
			dashboardadminpg.userLnkclick();
				admin_userspg.validateScreen();
				//Clicking add user to add new user
			admin_userspg.addUser();
			//Entering details for new user
			newuserpg.addNewUser(userNm, email, firstNm, lastNm, url, pwd);
			//Validating if user is created
			admin_userspg.validateuserCreation();
					
			
		}
	

}
