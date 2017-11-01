package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_brief extends Environment_proprties_Read {
	public String brief(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String brief_Xpath=Environment("brief_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+brief_Xpath).isEnabled())
			{
				return "Text Area";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
