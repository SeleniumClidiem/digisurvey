package Utilities_Digi;

import java.awt.Robot;


import java.awt.event.KeyEvent;

public class select_delete {
	
	public select_delete() 
	{
        try {
        	//Setting clipboard with file location
            
           
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            
            
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }

}
