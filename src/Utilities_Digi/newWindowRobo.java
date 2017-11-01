package Utilities_Digi;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;

public class newWindowRobo {
	public void newWindowRobo() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
           
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
}
