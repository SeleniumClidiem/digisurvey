package Utilities_Digi;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RefreshRobot {
	
	public RefreshRobot() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(3000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }

}
