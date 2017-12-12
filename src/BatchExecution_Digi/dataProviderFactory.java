package BatchExecution_Digi;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;

public class dataProviderFactory extends Environment_proprties_Read{
	/*@Factory(dataProvider="custData")
    public Object[] createInstances(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29)
	{
        return new Object[] {new Hybrid_Framework_DigiSurvey( S1, S2,  S3,  S4,  S5,  S6,  S7,  S8,  S9,  S10,
    			 S11,  S12,  S13,  S14,  S15,  S16,  S17,  S18,  S19,  S20,
    			 S21,  S22,  S23,  S24,  S25,  S26,  S27,  S28,  S29)};
    }
	@DataProvider(name="custData")
	public Object[][] custData(Method method) throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData =E_utils.readXLSXFile(Environment("Sheet_Control"));
		System.out.println(testData.length);
		return testData;
	}*/

}
