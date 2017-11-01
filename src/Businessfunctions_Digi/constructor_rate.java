package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_rate extends Environment_proprties_Read{
	public String rate(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String rate_Xpath=Environment("rate_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+rate_Xpath).isEnabled())
			{
				return "Scale/Rate";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
