import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Utilities_Digi.newTab_robot;

public class newtabopen {
	@Test
	public void check() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		
		driver.manage().window().maximize();
		Thread.sleep(3000);
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,100)");
		
		WebElement newwindow = driver.findElement(By.xpath("//*[@id='content']/p[4]/button"));
		newwindow.click();
		
		String oldTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(newTab.size());
		newTab.remove(oldTab);
		System.out.println(newTab.get(0));
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		
		//newTab_robot robo = new newTab_robot();
		driver.get("https://www.gmail.com");
		
		driver.close();
		
		 driver.switchTo().window(oldTab);
		 
		 System.out.println(driver.getTitle());
		 
		 
		 
		 String oldTab_1 = driver.getWindowHandle();
		 
		 //reopen the same tab from parent window
		 
		 WebElement newwindow_1 = driver.findElement(By.xpath("//*[@id='content']/p[4]/button"));
		 newwindow_1.click();
			
			
			ArrayList<String> newTab_1 = new ArrayList<String>(driver.getWindowHandles());
			System.out.println(newTab_1.size());
			newTab_1.remove(oldTab_1);
			System.out.println(newTab_1.get(0));
			// change focus to new tab
			driver.switchTo().window(newTab_1.get(0));
			
			//newTab_robot robo = new newTab_robot();
			driver.get("https://www.facebook.com");
			
		
		
		
		
	}

}
