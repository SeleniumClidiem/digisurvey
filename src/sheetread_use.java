import java.io.IOException;

import org.testng.annotations.Test;

import BatchExecution_Digi.sheetRead;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;

public class sheetread_use extends Environment_proprties_Read {
	@Test
	public void sheetread_use() throws IOException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		sheetRead read = new sheetRead();
		try 
		{
			String[] elements = read.sheet_reading("Sheet_ShareSurvey", "Share_006", "ShareID");
			System.out.println(elements.length);
			
			System.out.println(elements[RC.Current_Coulumn_Number(Environment("Sheet_ShareSurvey"), "SurveyName")]);
			System.out.println(elements[RC.Current_Coulumn_Number(Environment("Sheet_ShareSurvey"), "Recipient_Email_IDs")]);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
