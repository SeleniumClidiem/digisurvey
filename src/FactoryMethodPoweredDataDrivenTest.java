import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryMethodPoweredDataDrivenTest {
	private String name;
    private int age;
 
    public FactoryMethodPoweredDataDrivenTest(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    @Test
    public void testValidName() {
        System.out.println(name+" "+age);
    }
 
    @Test
    public void testValidAge() {
        System.out.println(name+" "+age);
    }
 
    @Factory(dataProvider = "get-data")
    public static Object[] produceTestClasses(String name, int age) {
        return new Object[] { new FactoryMethodPoweredDataDrivenTest(name, age) };
    }
 
    @DataProvider(name = "get-data")
    public static Object[][] getData() {
        return new Object[][] { { "Tom", 10 }, { "Jerry", 20 } };
    }
}
