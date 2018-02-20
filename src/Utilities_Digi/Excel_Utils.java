package Utilities_Digi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utils extends Environment_proprties_Read
{
	private XSSFWorkbook Workbook = null;
	private XSSFSheet Worksheet = null;
	private XSSFCell Cell=null;
	Excel_Utils E_Utils;
	

	public Excel_Utils(String FilePath)   
	{
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(FilePath);
			Workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public int getNumberOfRows(String SheetName) 
	{
		Worksheet = Workbook.getSheet(SheetName);
		if(Worksheet != null) {
			return Worksheet.getLastRowNum();
		}
		return 0;
	}
	
	public int getNumberOfColumns(String SheetName, int rownum) 
	{
		Worksheet = Workbook.getSheet(SheetName);
		if(Worksheet != null) {
			return Worksheet.getRow(rownum).getLastCellNum();
		}
		return 0;
	}
	
	public String getStringCellData(int RowNum, int ColNum, String SheetName) 
	{
		Worksheet = Workbook.getSheet(SheetName);
		
		try {
			return Worksheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
		} catch (Exception e) {
			return "Blank";
		}
	}
	
	public String getNumericalCellData(int RowNum, int ColNum, String SheetName) 
	{
		Worksheet = Workbook.getSheet(SheetName);
		try {
			 XSSFCell cell =  Worksheet.getRow(RowNum).getCell(ColNum);
			 String str; 
			return str = NumberToTextConverter.toText(cell.getNumericCellValue());
		} catch (Exception e) {
			return null;
		}
	}
	public int getLastrowno(String SheetName)
	{
		Worksheet = Workbook.getSheet(SheetName);
		int rowcount = Worksheet.getLastRowNum();
		return rowcount+1;
	}
	public int getLastcolmno(String SheetName)
	{
		Worksheet = Workbook.getSheet(SheetName);
	//	int colmcount = Worksheet.getLastRowNum();              //DOUBT
		Row r = Worksheet.getRow(1);              //any one row is enough to know how many columns are there
		int maxCell=  r.getLastCellNum();
		
		return maxCell;
	}
	public Object[][] readXLSXFile(String Sheet) throws IOException //String[][]
	{
		

		Worksheet = Workbook.getSheet(Sheet);
		int Total_Rows =Worksheet.getLastRowNum()+1;
		Row r = Worksheet.getRow(1);              //any one row is enough to know how many columns are there
		int Total_Column=  r.getLastCellNum();
		System.out.println(Total_Rows);
		System.out.println(Total_Column);
		Object[][] excelData = new Object[Total_Rows-1][Total_Column];
		System.out.println("excel rows and columns");
		for(int i=1;i<Total_Rows;i++)
		{
			//XSSFRow row = Worksheet.getRow(i);
			for(int j=0;j<Total_Column;j++)
			{
				try{
				//System.out.println(Worksheet.getRow(i).getCell(j).getStringCellValue());
				if(Worksheet.getRow(i).getCell(j).getStringCellValue()!=null)
				{
					excelData[i-1][j]=Worksheet.getRow(i).getCell(j).getStringCellValue();
				}
				else
					excelData[i-1][j]="Blank";
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
				System.out.println(excelData[i-1][j]);
			}
		}
		return excelData;
		
	}
	
	public Object[][] readXLSXFile1(String Sheet, int row) throws IOException //String[][]
	{
		Worksheet = Workbook.getSheet(Sheet);
		Row r = Worksheet.getRow(0);              
		int Total_Column=  r.getLastCellNum();
		System.out.println(Total_Column);
		Object[][] excelData = new Object[1][Total_Column];
		System.out.println("return data size:"+excelData.length);
		System.out.println("excel rows and columns");
			for(int j=0;j<Total_Column;j++)
			{
					try
					{
							if(Worksheet.getRow(row).getCell(j).getStringCellValue()!=null)//!=""
							{
								excelData[0][j]=Worksheet.getRow(row).getCell(j).getStringCellValue();
								
							}
							else
								excelData[0][j]=null;
						}
					catch (NullPointerException e) 
					{
						System.out.println(e.getMessage());
					}
					System.out.println("Row:"+row+" Data:"+excelData[0][j]);
			}
			return excelData;
	}
	public String[] readXLSXRunDesc(String Sheet) throws IOException //String[][]
	{
		

		Worksheet = Workbook.getSheet(Sheet);
		int Total_Rows =Worksheet.getLastRowNum()+1;
		Row r = Worksheet.getRow(1);              //any one row is enough to know how many columns are there
		int Total_Column=  r.getLastCellNum();
		System.out.println(Total_Rows);
		System.out.println(Total_Column);
		String[] excelData = new String[Total_Column];
		System.out.println("excel rows and columns");
		for(int i=1;i<Total_Rows;i++)
		{
			//XSSFRow row = Worksheet.getRow(i);
			for(int j=0;j<Total_Column;j++)
			{
				try
				{
					//System.out.println(Worksheet.getRow(i).getCell(j).getStringCellValue());
					if(Worksheet.getRow(i).getCell(j).getStringCellValue()!=null)
					{
	//					excelData[i-1][j]=Worksheet.getRow(i).getCell(j).getStringCellValue();
					}
		//			else
	//					excelData[i-1][j]="Blank";
					}
				catch (NullPointerException e)
				{
					System.out.println(e.getMessage());
				}
	//			System.out.println(excelData[i-1][j]);
			}
		}
		return excelData;
		
	}
	public  String getTestCaseName(String sTestCase)throws Exception{
		 
		String value = sTestCase;

		return value;

		}
	public Object[][] readXLSXFile_Sample(String Sheet) throws IOException //String[][]
	{

		Worksheet = Workbook.getSheet(Sheet);
		int Total_Rows =Worksheet.getLastRowNum()+1;
		Row r = Worksheet.getRow(1);              //any one row is enough to know how many columns are there
		int Total_Column=  r.getLastCellNum();
		
		System.out.println(Total_Rows);
		System.out.println(Total_Column);
		Object[][] excelData = new Object[Total_Rows][Total_Column];
		System.out.println("excel rows and columns");
		
		for(int i=0;i<Total_Rows-1;i++)
		{
			for(int j=0;j<Total_Column;j++)
			{
				excelData[i][j]=Worksheet.getRow(i+1).getCell(j).getStringCellValue();
				System.out.println(excelData[i][j]);
			}
			
		}
		return excelData;
		
	}
	public int Current_Coulumn_Number(String Sheet,String header_name) throws IOException
	{
		/*int i=MATCH("RegisterCompany",1:1,0);
		return i-1;*/
		//int header_col_no =row.getCellNumber(header_name);
		Excel_Utils Excel = new Excel_Utils(Environment("Excel"));
		Worksheet = Workbook.getSheet(Sheet);
		int Total_Rows =Worksheet.getLastRowNum()+1;
		//System.out.println("Method Call"+Total_Rows);
		Row r = Worksheet.getRow(0);              //any one row is enough to know how many columns are there
		int Total_Column=  r.getLastCellNum();
		//System.out.println("Method Call"+Total_Column);
		String[] header = new String[Total_Column];
		for(int col=0;col<Total_Column;col++)
		{
			header[col]=Excel.getStringCellData(0, col, Sheet);
			//System.out.println(header[col]);//==========================
			if(header_name.equals(header[col]))
			{
				return col;
			}
		}
		return (Integer) null;
	
	

		//return Total_Column;
	}
	

}
