package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_checkbox extends Environment_proprties_Read{
	  public String checkbox(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String checkbox_Xapth=Environment("checkbox_Xapth");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+checkbox_Xapth).isEnabled())
			{
				return "Check Box";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
