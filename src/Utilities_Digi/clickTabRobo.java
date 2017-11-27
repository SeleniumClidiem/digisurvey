package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class clickTabRobo {
	
	public clickTabRobo() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }

}
