package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_feedback extends Environment_proprties_Read{
	public String feedback(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String feedback_Xpath=Environment("feedback_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+feedback_Xpath).isEnabled())
			{
				return "Single Line Text";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
