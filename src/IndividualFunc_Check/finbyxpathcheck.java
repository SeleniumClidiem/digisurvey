package IndividualFunc_Check;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class finbyxpathcheck  extends Environment_proprties_Read {
	WebDriver driver= new ChromeDriver();

	@Test
	public void check() throws IOException
	{
		driver.get("http://localhost:4034/Account/Login");
		Functional_Libraries fl = new Functional_Libraries();
		if(fl.findByXpath(driver, Environment("Company_EmailIDXPATH")).isEnabled())
		{
			System.out.println("true");
		}
		String Question_Xpath=Environment("QuestionTextPreview_Xpath");
		String Q1=Question_Xpath+"[1]";
		System.out.println(Q1);
		
	}

}
