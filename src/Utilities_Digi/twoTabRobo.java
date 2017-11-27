package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class twoTabRobo {
	public twoTabRobo() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }

}
