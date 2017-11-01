package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_date extends Environment_proprties_Read{
	public String date(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String date_Xpath=Environment("date_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+date_Xpath).isEnabled())
			{
				return "Date";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
