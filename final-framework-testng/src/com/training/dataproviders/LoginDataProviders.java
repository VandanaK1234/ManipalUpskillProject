package com.training.dataproviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {
	
	

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "xlsx-input-sheet1")
	public Object[][] getExcelData1stsheet() throws IOException
	{ 
		Properties  properties = new Properties();
		FileInputStream inStream = new FileInputStream("C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\others.properties");
		properties.load(inStream);
		String path= properties.getProperty("dataFilePath");
		return new ApachePOIExcelRead().getExcelContentsheet(path,0); 
	}
	
	@DataProvider(name = "xlsx-input-sheet2")
	public Object[][] getExcelData2ndsheet() throws IOException
	{Properties  properties = new Properties();
	FileInputStream inStream = new FileInputStream("C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\others.properties");
	properties.load(inStream);
	
		String path= properties.getProperty("dataFilePath");
		return new ApachePOIExcelRead().getExcelContentsheet(path,1); 
	}
	@DataProvider(name = "xlsx-input-sheet3")
	public Object[][] getExcelData3rdsheet() throws IOException
	{Properties  properties = new Properties();
	FileInputStream inStream = new FileInputStream("C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\others.properties");
	properties.load(inStream);
	
		String path= properties.getProperty("dataFilePath");
		return new ApachePOIExcelRead().getExcelContentsheet(path,2); 
	}
	@DataProvider(name = "xlsx-input-sheet4")
	public Object[][] getExcelData4thsheet() throws IOException
	{
		Properties  properties = new Properties();
		FileInputStream inStream = new FileInputStream("C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\others.properties");
		properties.load(inStream);
		String path= properties.getProperty("dataFilePath");
		return new ApachePOIExcelRead().getExcelContentsheet(path,3); 
	}
		
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
