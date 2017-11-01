package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class newTab_robot {

	public newTab_robot() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
}
