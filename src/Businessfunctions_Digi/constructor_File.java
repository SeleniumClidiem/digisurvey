package Businessfunctions_Digi;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class constructor_File extends Environment_proprties_Read{
	public String File(WebDriver driver, int queNo)
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		try
		{	
			String Que_Xpath=Environment("Que_Xpath");
			String File_Xpath=Environment("File_Xpath");
			if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+File_Xpath).isEnabled())
			{
				return "File Upload";
			}
		}
		catch(Exception e)
		{
		
		}
		return null;
	}
}
