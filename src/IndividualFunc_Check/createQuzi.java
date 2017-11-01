package IndividualFunc_Check;

import org.openqa.selenium.WebDriver;

import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Environment_proprties_Read;

public class createQuzi extends Environment_proprties_Read{
	public void CreateQuiz (WebDriver driver) throws InterruptedException
	{
    	try
    	{
    		Functional_Libraries fl = new Functional_Libraries();
    		
    		fl.ClickByXpath(driver, Environment("Quizxpath"), "", "", "", "", "");
    		
    		fl.ClickByXpath(driver, Environment("CreatenewQuizxpath"), "", "", "", "", "");
    		
    		fl.selectDropdownByxpath(driver, Environment("Selectquiztemplatexpath"),"", "", "", "", "","");
    		
    		fl.selectDropdownByxpath(driver, Environment("SelectQuiztypexpath"),"", "", "", "", "","");
    		
    		fl.entervalueByXpath(driver, Environment("QuizNamexpath"), "", "", "", "", "","");
    		
    		fl.entervalueByXpath(driver, Environment("QuizDescriptionxpath"), "", "", "", "", "","");
    		
    		fl.ClickByXpath(driver, Environment("SaveQuizastemplatexpath"), "", "", "", "", "");
    		
    		fl.entervalueByXpath(driver, Environment("Templatexpath"), "", "", "", "", "","");
    		
    		fl.entervalueByXpath(driver, Environment("QuestionTextxpath"), "", "", "", "", "","");
    		
    		fl.entervalueByXpath(driver, Environment("QuestionWeightagexpath"), "", "", "", "", "","");
    		
    		fl.selectDropdownByxpath(driver, Environment("SelectAnswertypexpath"),"", "", "", "", "","");
    		
    		fl.selectDropdownByxpath(driver, Environment("SelectNoOfQuestionsxpath"),"", "", "", "", "","");
    		
    		fl.ClickByXpath(driver, Environment("Showresultcheckboxxpath"), "", "", "", "", "");
    		
    		fl.ClickByXpath(driver, Environment("AddQuestionxpath"), "", "", "", "", "");
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

}
