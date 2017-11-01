

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class brief_surveysaveopen {
	public static void main(String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		WebDriver driver = new ChromeDriver();
		int saved = 0;
		for(int i=0;i<2;i++)//open surveylink
		{
			driver.get("https://www.google.com");
			String surveylinkurl = driver.getCurrentUrl();
			System.out.println("save survey Link :"+surveylinkurl);
			try{
				FileInputStream fis = new FileInputStream(new File("SurveyLink_Excel\\Survey_Links.xlsx"));
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet1 = wb.getSheet("SurveyLinks");
			
				sheet1.getRow(i).createCell(0).setCellValue(surveylinkurl);
			
				FileOutputStream fout = new FileOutputStream("SurveyLink_Excel\\Survey_Links.xlsx");
				wb.write(fout);
				wb.close();
				saved++;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		for(int i=0;i<saved;i++)
		{	
			String Parent = driver.getWindowHandle();
			if(i==0)
			{
				
				Robot newTab = new Robot();
				newTab.keyPress(KeyEvent.VK_CONTROL);
				newTab.keyPress(KeyEvent.VK_N);
				newTab.keyRelease(KeyEvent.VK_N);
				newTab.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(3000);
				ArrayList<String> Al = new ArrayList<String> (driver.getWindowHandles());
				System.out.println(Al.size());
				Al.remove(Parent);
				driver.switchTo().window(Al.get(0));
			}
			
			
			
			
			File src = new File("SurveyLink_Excel\\Survey_Links.xlsx");
			FileInputStream fis;
			fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			String data0 ;
			//System.out.println(data0);
				
			int rowcount = sheet1.getLastRowNum();
				
			data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
			System.out.println("Open Survey Link :"+data0);
			wb.close();
			driver.get(data0);
		}
		
	}

}
