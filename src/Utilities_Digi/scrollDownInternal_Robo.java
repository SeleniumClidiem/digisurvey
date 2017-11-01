package Utilities_Digi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class scrollDownInternal_Robo {
	public scrollDownInternal_Robo() throws AWTException, InterruptedException 
	{
        
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_CONTROL);
           
         
    }

}
