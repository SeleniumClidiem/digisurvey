package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_radiooptions extends Environment_proprties_Read{
	public String radiooptions(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String radiooptions_Xpath=Environment("radiooptions_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+radiooptions_Xpath).isEnabled())
			{
				return "Radion Button";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
