package IndividualFunc_Check;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.UploadFile_Robot;

public class finbyxpathcheck  extends Environment_proprties_Read {
	WebDriver driver= new ChromeDriver();

	@Test
	public void check() throws IOException, InterruptedException
	{
		/*driver.get("http://localhost:4034/Account/Login");
		Functional_Libraries fl = new Functional_Libraries();
		if(fl.findByXpath(driver, Environment("Company_EmailIDXPATH")).isEnabled())
		{
			System.out.println("true");
		}
		String Question_Xpath=Environment("QuestionTextPreview_Xpath");
		String Q1=Question_Xpath+"[1]";
		System.out.println(Q1);*/
		WebDriver driver = new ChromeDriver();
		Functional_Libraries fl = new Functional_Libraries();
		UploadFile_Robot robo = new UploadFile_Robot();
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		String Digi_CompanyLoginxpath=Environment("Digi_CompanyLoginXPATH");
		String Company_EmailIDxpath=Environment("Company_EmailIDXPATH");
		String Company_Passwordxpath=Environment("Company_PasswordXPATH");
		String Company_LoginButtonXPATH=Environment("Company_LoginButtonXPATH");
		fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");
		
		fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_EmailIDxpath,"karteek@clidiem.com", "", "", "", "", "");

		fl.entervalueByXpath(driver, Company_Passwordxpath, "Test@123", "", "", "", "", "");

		Thread.sleep(3000);
		
		fl.ClickByXpath(driver, Company_LoginButtonXPATH, "", "", "", "", "");
		
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		
		fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
		
		fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
		
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		
		fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, "surveyName19", "", "", "", "", "");
		
		//click on more button
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		
		
		fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
		
		List<WebElement> span_ele = driver.findElements(By.xpath("//h2/following-sibling::span"));
		int size = span_ele.size();
		System.out.println(size);
		
		
		
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript(document.getElementById("answertype").innerText", "");
*/		
		for(int i=0;i<size; i++)
		{
			System.out.println(span_ele.get(i));
			System.out.println(span_ele.get(i).getText().toCharArray());
		}
		
	}

}
