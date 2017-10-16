package IndividualFunc_Check;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class more_buttonClick extends Environment_proprties_Read{
	WebDriver driver = new ChromeDriver();
	Functional_Libraries fl = new Functional_Libraries();
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
		

		fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_EmailIDxpath,"karteek@clidiem.com", "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_Passwordxpath, "Test@123", "", "", "", "", "");

		Thread.sleep(3000);
		// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
		// "", "", "", "");
		fl.ClickByXpath(driver, Company_LoginButtonXPATH, "", "", "", "", "");
		
		fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
		
		fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
		
		//fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, "surveyName10", "", "", "", "", "");
		
		/*JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
		
		fl.ClickByXpath(driver, "(//*[@id='divactionsquestionnaire']/button)[5]", "", "", "", "", "");*/
		/*JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");*/
		
		/*fl.ClickByXpath(driver, "//*[@id='divactionsquestionnaire']/button", "", "", "", "", "");
		
		fl.ClickByXpath(driver, Environment("publishonGrid_Xpath"), "", "", "", "", "");
		
		fl.ClickByXpath(driver, Environment("Proceed_Xpath"), "", "", "", "", "");*/
		
		//get surveyname
		/*fl.ClickByXpath(driver, "(//*[@id='divactionsquestionnaire']/button)[1]", "", "", "", "", "");
		
		fl.ClickByXpath(driver, Environment("EditonGrid_Xpath"), "", "", "", "", "");*/
		
		fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
		
		fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
		
		//search with surveyname
		fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, "survey10", "", "", "", "", "");
		
		//click on more button
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		
		//fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
		fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
		
		/*//click on surveyname
		fl.ClickByXpath(driver, surveyNamein_SurveyList_Xpath, "", "", "", "", "");
		fl.ClickByXpath(driver, surveyNamein_SurveyList_Xpath, "", "", "", "", "");
		*/
		
		
		
		
		
	}
}
