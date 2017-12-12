import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class multipleTest extends beforetest{
	
	/*@DataProvider
	public Object[][] cus() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),15);
		System.out.println("data provider object length"+testData.length);
		for(int i=0;i<29;i++)
		{
			System.out.println(testData[0][i]);
		}
		
		return testData;
	}*/
	@Test
	public void test1()
	{
		
		driver.get("https://www.google.co.in");
	}
	@Test
	public void test2()
	{
		System.out.println("testcase : 2");
		driver.get("https://www.gmail.com");
	}

}
