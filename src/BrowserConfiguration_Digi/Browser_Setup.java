package BrowserConfiguration_Digi;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import Businessfunctions_Digi.Functional_Cases_propread;
import Utilities_Digi.Environment_proprties_Read;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;



public class Browser_Setup extends Environment_proprties_Read {
	
public static WebDriver driver;
Functional_Cases_propread func_cases = new Functional_Cases_propread();
	
	//public static InputStream input1=null;


	
	@BeforeTest
	public WebDriver Setup()throws IOException{
		
		
		if(Environment("Browser").equalsIgnoreCase("FF")){
			
			File file = new File("drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver",file.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			ATUReports.setWebDriver(driver);
			/*System.setProperty("atu.reporter.config", "lib\\atu.properties");
			ATUReports.setWebDriver(driver);*/
			
			
		}else if(Environment("Browser").equalsIgnoreCase("Chrome")){
			
			File file = new File("drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			ChromeOptions o = new ChromeOptions();
			o.addArguments("disable-extensions");
			o.addArguments("--start-maximized");
			driver = new ChromeDriver(o);
//=====================	
			ATUReports.setWebDriver(driver);
			/*System.setProperty("atu.reporter.config", "Digi_Survey\\lib\\atu.properties");
			ATUReports.setWebDriver(driver);*/
			
			
		}else if(Environment("Browser").equalsIgnoreCase("IE")){
			
			File file = new File("drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
			driver = new InternetExplorerDriver();
			ATUReports.setWebDriver(driver);
			/*ATUReports.setWebDriver(driver);
			System.setProperty("atu.reporter.config", "lib\\atu.properties");*/
			
		}else {
			System.out.println("Browser is not configure properly");
		}
		
		//driver.manage().window().maximize();
		
		return driver;
	}
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		
		/*System.out.println("CompanyLogout");
		try 
		{
			func_cases.CompanyLogout(driver);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 // System.out.println("CompanyLoggedout");
		driver.quit();
	}

}
