import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class queno_check extends Environment_proprties_Read{

	@Test
	public void que_noCheck() throws IOException
	{
		Functional_Libraries fl = new Functional_Libraries();
		
		for(int i=0;i<3; i++)
		{
			if(i==1)
			{
				System.out.println("1");
			
			}
			if(i==2)
			{
				System.out.println("2");
				
			}
		}
		
		String Que_Xpath=Environment("Que_Xpath");
		String checkbox_Xapth=Environment("checkbox_Xapth");
		String dropdown_Xpath=Environment("dropdown_Xpath");
		String radiooptions_Xpath=Environment("radiooptions_Xpath");
		String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
		String rate_Xpath=Environment("rate_Xpath");
		
		//WebElement radiooptions = fl.findByXpath(driver, Que_Xpath+"["+10+"]"+radiooptions_Xpath);
		System.out.println(Que_Xpath+"["+1+"]"+checkbox_Xapth);
		System.out.println(Que_Xpath+"["+1+"]"+dropdown_Xpath);
		System.out.println(Que_Xpath+"["+1+"]"+radiooptions_Xpath);
		System.out.println(Que_Xpath+"["+1+"]"+radioYesorNo_Xpath);
		System.out.println(Que_Xpath+"["+1+"]"+rate_Xpath);
		
		System.out.println("==========================================");
		System.out.println(Que_Xpath+"["+6+"]"+radiooptions_Xpath+"/following-sibling::span");
		System.out.println(Que_Xpath+"["+10+"]"+radiooptions_Xpath);
	}
}
