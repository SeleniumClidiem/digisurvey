import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class firefox 
{
	@Test
	public void firefox()
	{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:4034/Account/Login");
	}
	

}
