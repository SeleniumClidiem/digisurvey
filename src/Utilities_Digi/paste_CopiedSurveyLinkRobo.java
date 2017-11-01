package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class paste_CopiedSurveyLinkRobo {
	public paste_CopiedSurveyLinkRobo() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
}
