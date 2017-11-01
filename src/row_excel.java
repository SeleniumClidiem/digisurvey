import java.io.IOException;

import org.testng.annotations.Test;

import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;

public class row_excel extends Environment_proprties_Read{

	@Test
	public void readExcel() throws IOException
	{
		Excel_Utils RC= new Excel_Utils("TestData_Digi\\ExcelFramework_Digi.xlsx");
		
		int col_no =RC.Current_Coulumn_Number("AddUsers", "UserName");
		System.out.println(col_no);
		
		
		/*System.out.println(RC.getStringCellData(1, 1, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 2, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 3, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 4, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 5, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 6, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 7, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 8, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 9, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 10, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 11, "Controlsheet"));
		System.out.println(RC.getStringCellData(1, 12, "Controlsheet"));*/
		
		String CreateSurvey_Questions_1=Environment("Sheet_CreateSurvey_Questions");
		
		int Question_NUmber=0;
		  String  Que_ID="Q_2";
		  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions_1); k++) 
		  {
			  if (Que_ID
					  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions_1, "QuestioID"), CreateSurvey_Questions_1)))
			  {
				  
				  Question_NUmber++;
				  /*int Que_No=0; 
				  for(int create_que=0;create_que<create_Que_col;create_que++) 
				  {
					  
					  create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions_1);
					  System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
					  if(create_que==create_Que_col)
					  {
						  Que_No++;
						  
					  }
		  
				  }*/ 
			  }
		  }
		  System.out.println(Question_NUmber);
	}
}
