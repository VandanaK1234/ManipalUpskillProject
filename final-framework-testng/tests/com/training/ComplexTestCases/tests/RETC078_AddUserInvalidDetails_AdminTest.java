package com.training.ComplexTestCases.tests;

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
import com.training.pom.AdminLoginPOM;
import com.training.pom.BaseurlHomePOM;
import com.training.pom.DashboardAdminPOM;
import com.training.pom.NewUserPOM;
import com.training.pom.UsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC078_AddUserInvalidDetails_AdminTest {
	private static WebDriver driver;
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
				driver = DriverFactory.getDriver(DriverNames.CHROME);
				//Initializing home page
							
				String adminurl = properties.getProperty("adminURL");
		//  Opening admin login page for Retail application in browser
		driver.get(adminurl);
		
		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		//Initializing all web pages
		dashboardadminpg= new DashboardAdminPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		admin_userspg= new UsersPOM(driver);
		newuserpg= new NewUserPOM(driver);
				
		
		
		
	}
	
	@AfterClass
	public void tearDown() throws Exception 
	{
		driver.quit();
	}
		
	@Test(priority=1)
	public void adminLogin()
	{
		//Admin login
		String adminid=properties.getProperty("adminID");
		String adminpwd=properties.getProperty("adminpwd");
		String sheet=properties.getProperty("adminID");
		adminloginpg.adminLoginTest(adminid,adminpwd);
		//Validating admin dashboard
		dashboardadminpg.validateScreen();
	}
	
		@Test (priority=2, dataProvider = "xlsx-input-sheet8", dataProviderClass = LoginDataProviders.class)
		public void addAndViewUsers(String userNm,String email,String firstNm,String lastNm,String url,String pwd,String role)
		{
			
			
			//Clicking users link
			dashboardadminpg.userLnkclick();
				admin_userspg.validateScreen();
				//Clicking add user to add new user
			admin_userspg.addUser();
			//Entering details for new user
			newuserpg.addNewUser(userNm, email, firstNm, lastNm, url, pwd,role);
			//Validating if user is created
			newuserpg.IncorrectEmailidMsg();
					
			
		}
	


}
