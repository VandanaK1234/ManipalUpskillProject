package com.training.dataproviders;

import java.util.List;

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
	public Object[][] getExcelData1stsheet()
	{
		String fileName ="C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\RetailTestcasesData.xlsx"; 
		return new ApachePOIExcelRead().getExcelContentsheet1(fileName); 
	}
		@DataProvider(name = "xlsx-input-sheet2")
		public Object[][] getExcelData2ndsheet(){
			String fileName ="C:\\Users\\VandanaKapoor\\git\\ManipalUpskillProject\\final-framework-testng\\resources\\RetailTestcasesData.xlsx"; 
			return new ApachePOIExcelRead().getExcelContentsheet2(fileName);
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
