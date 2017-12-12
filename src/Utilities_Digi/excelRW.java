package Utilities_Digi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelRW {
	public static void writeExcel(String filepath, int noOfshares, String SurveyURL) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(filepath));//"SurveyLink_Excel\\Survey_Links.xlsx"
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
	
		System.out.println("exceel row "+noOfshares+" is set by link :"+SurveyURL);
	
		sheet1.getRow(noOfshares).createCell(0).setCellValue(SurveyURL);
	
		FileOutputStream fout = new FileOutputStream(filepath);
		wb.write(fout);
		wb.close();
	}
	public static String readExcel(String filepath, int i) throws IOException
	{
		File src = new File(filepath);
		FileInputStream fis;
		fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		String data0 ;
		data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
		System.out.println("Open Link :"+i+" "+data0);
		wb.close();
		return data0;
}

}
