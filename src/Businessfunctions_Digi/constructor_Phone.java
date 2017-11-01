package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_Phone extends Environment_proprties_Read{
	public String Phone(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String Phone_Xpath=Environment("Phone_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+Phone_Xpath).isEnabled())
			{
				return "Number";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
