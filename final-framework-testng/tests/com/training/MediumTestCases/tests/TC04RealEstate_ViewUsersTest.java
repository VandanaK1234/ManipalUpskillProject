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
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		String adminurl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver); 
		//  Opening home page for Retail application in browser
		driver.get(adminurl);
		//adding some wait time to load the page
		dashboardadminpg= new DashboardAdminPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		admin_userspg= new UsersPOM(driver);
		newuserpg= new NewUserPOM(driver);
		String fileName= properties.getProperty("dataFilePath");
		String sheetNm= properties.getProperty("SheetTC04MediumTst");
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		//Read userId for deletion from repository
				String userid=properties.getProperty("userID");
		//  delete the registered user.
		
		driver.quit();
	}
		
		@Test (dataProvider = "xlsx-input-sheet3", dataProviderClass = LoginDataProviders.class)
				public void addAndViewUsers(String userNm,String email,String firstNm,String lastNm,String url,String pwd)
		{
			String adminid=properties.getProperty("adminID");
			String adminpwd=properties.getProperty("adminpwd");
			String sheet=properties.getProperty("adminID");
			adminloginpg.adminLoginTest(adminid,adminpwd);
			dashboardadminpg.validateScreen();
			dashboardadminpg.userLnkclick();
			admin_userspg.validateScreen();
			admin_userspg.addUser();
			newuserpg.addNewUser(userNm, email, firstNm, lastNm, url, pwd);
			admin_userspg.validateuserCreation();
			
			admin_userspg.deleteUser(userNm);
			
		}
	

}
