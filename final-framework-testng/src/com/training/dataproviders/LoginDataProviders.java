package com.training.dataproviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.ITestContext;
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
	public Object[][] getExcelData1stsheet(ITestContext context) throws IOException
	{ 
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname1");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname);
		 
	}
	
	@DataProvider(name = "xlsx-input-sheet2")
	public Object[][] getExcelData2ndsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
	String sheetname=context.getCurrentXmlTest().getParameter("sheetname2");
	return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	
	@DataProvider(name = "xlsx-input-sheet3")
	public Object[][] getExcelData3rdsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
	String sheetname=context.getCurrentXmlTest().getParameter("sheetname3");
	return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xlsx-input-sheet4")
	public Object[][] getExcelData4thsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname4");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xlsx-input-sheet5")
	public Object[][] getExcelData5thsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname5");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xlsx-input-sheet6")
	public Object[][] getExcelData6thsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname6");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xlsx-input-sheet7")
	public Object[][] getExcelData7thsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname7");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xlsx-input-sheet8")
	public Object[][] getExcelData8thsheet(ITestContext context) throws IOException
	{
		String path=context.getCurrentXmlTest().getParameter("path");
		String sheetname=context.getCurrentXmlTest().getParameter("sheetname8");
		return new ApachePOIExcelRead().getExcelContentsheet(path,sheetname); 
	}
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
