package Utilities_Digi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;

public class pasteLinkinchildRobo {
	public void pasteLinkinchildRobo() throws InterruptedException, AWTException 
	{
		Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_T);
    robot.keyRelease(KeyEvent.VK_T);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(3000);*/
		Thread.sleep(3000);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(3000);
    
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
