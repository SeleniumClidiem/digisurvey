package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class closeopenedtab_robo {
	public closeopenedtab_robo() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
}
