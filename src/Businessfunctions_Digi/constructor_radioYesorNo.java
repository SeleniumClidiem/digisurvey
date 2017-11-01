package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_radioYesorNo extends Environment_proprties_Read {
	public String radioYesorNo(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+radioYesorNo_Xpath).isEnabled())
			{
				return "Yes or No";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
