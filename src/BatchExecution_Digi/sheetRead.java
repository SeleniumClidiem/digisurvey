package BatchExecution_Digi;

import java.io.IOException;
import java.util.List;

import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;

public class sheetRead extends Environment_proprties_Read
{
	

	public String[] sheet_reading(String Sheet_nameEnvironment, String S, String sheet_Colname) throws IOException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		String create_suvey=Environment(Sheet_nameEnvironment); 
		  int create_suvey_row=RC.getLastrowno(create_suvey); 
		  int create_suvey_col=RC.getLastcolmno(create_suvey); 
		  String[] create_suvey_ele=new String[create_suvey_col]; 
		  for (int j = 1; j < RC.getLastrowno(create_suvey); j++) 
		  { 
			  System.out.println("for Loop" );
			  System.out.println(S);
			  try
			  {
				System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, sheet_Colname), create_suvey));
				 if (S.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, sheet_Colname),create_suvey)))
					  // Adduser contains company email_id at 1st column  for validation
				  { 
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, sheet_Colname),create_suvey)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
					  {
						  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(j, create_suvey_Login, create_suvey);
						  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
		  
			  
					  }
					  return create_suvey_ele;
				  }
			  }
			
		  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
		 
		  }
		  return null;  
	}
	
	
}
