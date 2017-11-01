package IndividualFunc_Check;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.UploadFile_Robot;
import Utilities_Digi.select_delete;

public class more_buttonClick extends Environment_proprties_Read{
	WebDriver driver = new ChromeDriver();
	Functional_Libraries fl = new Functional_Libraries();
	UploadFile_Robot robo = new UploadFile_Robot();
	@Test
	public void buttonClick() throws InterruptedException, IOException
	{
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		String Digi_CompanyLoginxpath=Environment("Digi_CompanyLoginXPATH");
		String Company_EmailIDxpath=Environment("Company_EmailIDXPATH");
		String Company_Passwordxpath=Environment("Company_PasswordXPATH");
		String Company_LoginButtonXPATH=Environment("Company_LoginButtonXPATH");
		fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");
		
		//===survey_Surveys===
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String publishonGrid_Xpath=Environment("publishonGrid_Xpath");
		String Proceed_Xpath=Environment("Proceed_Xpath");
		String success_Publish_Xpath=Environment("success_Publish_Xpath");
		String surveyNamein_SurveyList_Xpath=Environment("surveyNamein_SurveyList_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String EditonGrid_Xpath=Environment("EditonGrid_Xpath");
		String SurveyName_Xapth=Environment("SurveyNameXPATH");
		
		String Que_Xpath=Environment("Que_Xpath");
		String checkbox_Xapth=Environment("checkbox_Xapth");
		String dropdown_Xpath=Environment("dropdown_Xpath");
		String radiooptions_Xpath=Environment("radiooptions_Xpath");
		String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
		String rate_Xpath=Environment("rate_Xpath");
		String date_Xpath=Environment("date_Xpath");
		String File_Xpath=Environment("File_Xpath");
		String Phone_Xpath=Environment("Phone_Xpath");
		String feedback_Xpath=Environment("feedback_Xpath");
		String brief_Xpath=Environment("brief_Xpath");
		
		String No_Que_Xpath=Environment("No_Que_Xpath");
		

		fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_EmailIDxpath,"karteek@clidiem.com", "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_Passwordxpath, "Test@123", "", "", "", "", "");

		Thread.sleep(3000);
		
		fl.ClickByXpath(driver, Company_LoginButtonXPATH, "", "", "", "", "");
		
		driver.get("http://localhost:4034/Survey/Index/NTg=/M0FFMzMwODY=");
		
		driver.get("http://localhost:4034/Survey/Index/NTg=/M0FFMzMwODY=");
		
		
		List<WebElement> no_que=driver.findElements(By.xpath(No_Que_Xpath));
		
		for(int i=1;i<=no_que.size();i++)
		{
			System.out.println(i);
			if(i>5)
			{
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,200)", "");
			}
			
				
			
			
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+dropdown_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.selectDropdownByIndexxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, 1, "", "", "", "", "");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+checkbox_Xapth).isEnabled())
				{
					System.out.println("checkbox");
					fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+checkbox_Xapth, "", "", "", "", "");
				
					continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try{
				
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+radiooptions_Xpath);
						String text=fl.getTextXPATH(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath+"/following-sibling::span", "", "", "", "", "");
						
						if(text.contains("Yes")||text.contains("No"))
						{
							//fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath, "", "", "", "", "");
						}
						
						{
							fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath, "", "", "", "", "");
							continue;
						}
						
						
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath).isEnabled())
				{
							System.out.println(Que_Xpath+"["+i+"]"+radioYesorNo_Xpath);
							fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath, "", "", "", "", "");
							continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				
			
						if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+rate_Xpath).isEnabled())
						{
								System.out.println(Que_Xpath+"["+i+"]"+rate_Xpath);
								fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+rate_Xpath, "", "", "", "", "");
								continue;
						}
			}
		
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+date_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+date_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+date_Xpath, "08/21/1991", "", "", "", "", "");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+File_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+File_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+File_Xpath, "", "", "", "", "");
						robo.uploadFile("D:\\Sailaja\\ScreenshotOuputConsole\\10Company_Creation.png");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+Phone_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+Phone_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+Phone_Xpath, "9533676851", "", "", "", "", "");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+feedback_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+feedback_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+feedback_Xpath, "Single line comment", "", "", "", "", "");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+brief_Xpath).isEnabled())
				{
						System.out.println(Que_Xpath+"["+i+"]"+brief_Xpath);
						//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
						fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+brief_Xpath, "Multi line comment", "", "", "", "", "");
						continue;
				}
			}
			catch(Exception e)
			{
				
				//System.out.println(e.getMessage());
			}
				
			
			
			
		}
		
		/*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
		
		fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
		
		//search with surveyname
		fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, "survys_10", "", "", "", "", "");
		
		//click on more button
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		
		
		//fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
		
		fl.ClickByXpath(driver, EditonGrid_Xpath, "", "", "", "", "");
		
		Thread.sleep(3000);
		fl.entervalueByXpath(driver, SurveyName_Xapth, "", "", "", "", "", "");
		
		select_delete sel_del = new select_delete();
		//select_delete sel_del_1 = new select_delete();
		
		
		
		//driver.findElement(By.xpath(SurveyName_Xapth)).clear();
		
		fl.entervalueByXpath(driver, SurveyName_Xapth, "survey_10", "", "", "", "", "");*/
		
		
		
		
		
		
		
	}
}
