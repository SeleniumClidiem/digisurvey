import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;

public class getAnswersRandomly extends Environment_proprties_Read
{
	Functional_Libraries fl = new Functional_Libraries();
	WebDriver driver= new ChromeDriver();
	int queno;
	@DataProvider
	public Object[][] Answers() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile(Environment("Sheet_Survey_Answers"));
		return testData;
	}
	@Test(dataProvider = "Answers")
	public void getAnswer(String AnsID, String opt1, String opt2, String opt3, String opt4, String opt5, String opt6, 
			String opt7, String opt8, String opt9, String opt10, String date, String fileupload, 
			String number, String scaleorrate, String textarea, String singleline, String yorn) throws IOException, InterruptedException
	{
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
		
		
//newly added xpaths		
		String noofcheckboxes_Xpath=Environment("noofcheckboxes_Xpath");
		String checkboxtext_Xapth=Environment("checkboxtext_Xapth");
		String checkboxtextspan_Xapth=Environment("checkboxtextspan_Xapth");
		
		String chk1_Xpath=Environment("chk1_Xpath");
		String chk2_Xpath=Environment("chk2_Xpath");
		
		String noofdropdownoptions_Xpath=Environment("noofdropdownoptions_Xpath");
		
		String noofradiooptions_Xpath=Environment("noofradiooptions_Xpath");
		String radiooption1_Xpath=Environment("radiooption1_Xpath");
		String radiooptionclick_Xpath=Environment("radiooptionclick_Xpath");
		String radiooptiontext_Xpath=Environment("radiooptiontext_Xpath");
		
		String ratedynamic_Xpath=Environment("ratedynamic_Xpath");
		
		String radioNo_Xpath=Environment("radioNo_Xpath");
		
		
		if(AnsID!="")
		{
			if(AnsID.equals("Check Box"))
			{
				String responseoptions = null;
				String[] options = new String[]{opt1,opt2,opt3,opt4,opt5,opt6,opt7,opt8,opt9,opt10};
				int attempt=0;
				
				List<WebElement> chkoptions = driver.findElements(By.xpath(Que_Xpath+"["+queno+"]"+noofcheckboxes_Xpath));
				int chksize = chkoptions.size();
				for(int i=1;i<=chksize;i++)//to get each option text
				{
					//get the option text from survey link
					if(fl.elementEnabled(driver, Que_Xpath+"["+queno+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth,"").equals("true"))
					{
						//getting 1st option value from survey link
						responseoptions=driver.findElement(By.xpath(Que_Xpath+"["+queno+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth)).getText();
						for(int j=0;j<10;j++)
						{
							
							if(responseoptions.equals(options[j]))//compare 1st option from survey link with each option from ans sheet
							{
								//matches get the column number from excel, now click on the respected i value
								fl.ClickByXpath(driver, Que_Xpath+"["+queno+"]"+chk1_Xpath+"["+i+"]"+chk2_Xpath, "", "", "", "", "");
								attempt++;
							}
							
						}
					}
					if(i==chksize&&attempt==0)
					{
						System.out.println("You are not Answered this question");
					}
					else
					{
						if(i==chksize&&attempt!=0)
						{
							System.out.println("You are selected "+attempt+" checkboxes");
						}
					}
				}
			}
			if(AnsID.equals("Dropdown"))
			{
				String[] options = new String[]{opt1,opt2,opt3,opt4,opt5,opt6,opt7,opt8,opt9,opt10};
				int attempt=0;
				List<WebElement> dropOptions = driver.findElements(By.xpath(Que_Xpath+"["+queno+"]"+noofdropdownoptions_Xpath));
				int optionsize = dropOptions.size();
				
				for(int i=1;i<=optionsize;i++)//to get option text from surveylink
				{
					String drop = fl.getTextXPATH(driver, Que_Xpath+"["+queno+"]"+noofdropdownoptions_Xpath+"["+i+"]", "", "", "", "", "");
					for(int j=0;j<10;j++)//to get excel options
					{
						if(drop.equals(options[j]))
						{
							fl.selectDropdownByxpath(driver, Que_Xpath+"["+queno+"]"+dropdown_Xpath, options[j], "", "", "", "", "");
							attempt++;
						}
					}
					if(i==optionsize&&attempt==0)
					{
						System.out.println("You are not Answered this question");
					}
					else
					{
						if(i==optionsize&&attempt!=0)
						{
							System.out.println("You are selected "+attempt+" dropboxes, but last string in excel is selected");
						}
					}
				}
			}
			if(AnsID.equals("Radio Button"))
			{
				String[] options = new String[]{opt1,opt2,opt3,opt4,opt5,opt6,opt7,opt8,opt9,opt10};
				int attempt=0;
				List<WebElement> radioOptions = driver.findElements(By.xpath(Que_Xpath+"["+queno+"]"+noofradiooptions_Xpath));
				int radiosize = radioOptions.size();
				
				for(int i=1;i<=radiosize;i++)
				{
					String Radio = fl.getTextXPATH(driver, Que_Xpath+"["+queno+"]"+radiooption1_Xpath+"["+i+"]"+radiooptiontext_Xpath, "", "", "", "", "");
					for(int j=0;j<10;j++)
					{
						if(Radio.equals(options[j]))
						{
							fl.ClickByXpath(driver, Que_Xpath+"["+queno+"]"+radiooption1_Xpath+"["+i+"]"+radiooptionclick_Xpath, "", "", "", "", "");
							attempt++;
						}
					}
					if(i==radiosize&&attempt==0)
					{
						System.out.println("You are not Answered this question");
					}
					else
					{
						if(i==radiosize&&attempt!=0)
						{
							System.out.println("You are selected "+attempt+" radiobutton, but last string in excel is selected");
						}
					}
				}
				
			}
			if(AnsID.equals("Date"))
			{
				fl.entervalueByXpath(driver, Que_Xpath+"["+queno+"]"+date_Xpath, date, "", "", "", "", "");
			}
			
			if(AnsID.equals("File Upload"))
			{
				fl.entervalueByXpath(driver, Que_Xpath+"["+queno+"]"+File_Xpath, fileupload, "", "", "", "", "");
			}
			if(AnsID.equals("Number"))
			{
				fl.entervalueByXpath(driver, Que_Xpath+"["+queno+"]"+Phone_Xpath, number, "", "", "", "", "");
			}
			
			if(AnsID.equals("Scale / Rate"))
			{
				int rate = Integer.parseInt(scaleorrate);
				fl.ClickByXpath(driver, Que_Xpath+"["+queno+"]"+ratedynamic_Xpath+"["+rate+"]", "", "", "", "", "");
			}
			if(AnsID.equals("Single Line Text"))
			{
				fl.entervalueByXpath(driver, Que_Xpath+"["+queno+"]"+feedback_Xpath, singleline, "", "", "", "", "");
			}
			if(AnsID.equals("Text Area"))
			{
				fl.entervalueByXpath(driver, Que_Xpath+"["+queno+"]"+brief_Xpath, textarea, "", "", "", "", "");
			}
			if(AnsID.equals("Yes or No"))
			{
				if(yorn.equals("Yes"))
				{
					fl.ClickByXpath(driver, Que_Xpath+"["+queno+"]"+radioYesorNo_Xpath, "", "", "", "", "");
				}
				else
				{
					if(yorn.equals("No"))
					{
						fl.ClickByXpath(driver, Que_Xpath+"["+queno+"]"+radioNo_Xpath, "", "", "", "", "");
					}
				}
				
			}
		}
	}

}
