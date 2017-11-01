package Businessfunctions_Digi;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

class contructor_dropdown extends Environment_proprties_Read {
	
	public String dropdown(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String dropdown_Xpath=Environment("dropdown_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+dropdown_Xpath).isEnabled())
			{
				return "Dropdown";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
	
	

}
