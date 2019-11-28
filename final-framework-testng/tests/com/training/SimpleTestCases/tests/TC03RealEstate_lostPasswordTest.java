package com.training.SimpleTestCases.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC03RealEstate_lostPasswordTest {
	private WebDriver driver;
	private String baseUrl;
		private static Properties properties;
	private ScreenShot screenShot;
	private BaseurlHomePOM homepgpom;
	private BaseLoginPOM loginpgpom;
	private LostPasswordPOM lostpwdpom;
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
		loginpgpom= new BaseLoginPOM(driver);
		lostpwdpom=new LostPasswordPOM(driver);
		adminloginpg=new AdminLoginPOM(driver);
		admindprofilepg= new DashboardAdminPOM(driver);
		registerationpg = new RegistrationPOM(driver);
		
		screenShot = new ScreenShot(driver); 
		baseUrl = properties.getProperty("baseURL");
		//  Opening home page for Retail application in browser
		driver.get(baseUrl);
		//adding some wait time to load the page
						   
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
	
	}
		
	
	
	@AfterMethod
	public void tearDown() throws Exception {
		
		//Read the regsitered userID from properties file to delete by admin user
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
	
	@Test (dataProvider = "xlsx-input-sheet2", dataProviderClass = LoginDataProviders.class)
	public void resetPassword(String emailId,String password) throws InterruptedException, IOException
	{
		
    //Clicking the Lost pwd link on login page
    loginpgpom.lostPwdLnkTest();
   
    //Entering mail id and resetting the pwd.
    lostpwdpom.resetPwdTest(emailId);
   
    
    TakesScreenshot scrShot =((TakesScreenshot)driver);
    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    File DestFile=new File("C:\\User\\ResetPwdError.jpg");
    	FileUtils.copyFile(SrcFile, DestFile);	
	}

}
