package BatchExecution_Digi;



import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BrowserConfiguration_Digi.Browser_Setup;
import Businessfunctions_Digi.Functional_Cases_propread;
import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Excel_Utils;

class Hybrid_Framework_DigiSurvey extends Browser_Setup
{
	Functional_Cases_propread func_cases = new Functional_Cases_propread();
	
	
	@DataProvider
	public Object[][] custData() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile(Environment("Sheet_Control"));
		return testData;
	}
	
	@Test(dataProvider = "custData")
	public void Frame_Digi(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,String S11, String S12) throws IOException, InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		Functional_Libraries fl = new Functional_Libraries();
		
		if (S1.equals("Y")) 
		{
			if(S5!="")
			{
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int j = 1; j < RC.getLastrowno(Login); j++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S5);
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S5.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(j, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
			  
				  
						  } 
					  } 
				  }
				func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
				//func_cases.companyLogout(driver, Environment("Company_LogoXPATH"), Environment("Company_LogoutXPATH"));
				
					
			}
			if(S7!="")
			{
				  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
				  int create_suvey_row=RC.getLastrowno(create_suvey); 
				  int create_suvey_col=RC.getLastcolmno(create_suvey); 
				  String[] create_suvey_ele=new String[create_suvey_col]; 
				  for (int j = 1; j < RC.getLastrowno(create_suvey); j++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S7);
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
					  if (S7.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
						  {
							  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(j, create_suvey_Login, create_suvey);
							  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
			  
				  
						  }
						 
						 
						
							  func_cases.create_Survey(driver, create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Name")],
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Notes")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Name")],
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Notes")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_SubNotes")],
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Description")],
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "TemplateName")],
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]);
							  //skip last column in function call
						  
						 
						  
						  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]!="")
							{
							  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
							  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
							  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
							  String[] create_Que_ele=new String[create_Que_col]; 
							  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
							  { 
								  
								  System.out.println("for Loop" );
								  System.out.println(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]);
								  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
								  if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]
										  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
									  //based on j value get the row data and do Adding Users
									  
									  int Que_No=0; 
									  for(int create_que=0;create_que<create_Que_col;create_que++) 
									  {
										  
										  create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
										  System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
										  if(create_que==create_Que_col)
										  {
											  Que_No++;
											  
										  }
							  
									  } 
									  func_cases.AddQuestionto_CreateSurvey(driver, create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "Tags")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "NumberOfOptions")],
											  Que_No,create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion1")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion2")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion3")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion4")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion5")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion6")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion7")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion8")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion9")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion10")]);
									  
									 
										jse.executeScript("window.scrollBy(0,1000)", "");//scroll up
										
										//call "save" for  created survey
										func_cases.save_CreatedSurvey(driver);
										
										//click drafts > more options > publish
										func_cases.publishSavedSurveyQue(driver,
												create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
										
									 
										
										
										
								  } 
							  }
			
							}
						  //without saving publish , after creating the survey
						  func_cases.after_surveyCreationClick_Publish(driver);
						
						  //validate the published issue matches with input or not
			
			
						  //String create_suvey_1=Environment("Sheet_Survey_createSurvey"); 
						 // int Question_NUmber=RC.getLastrowno(create_suvey_1); 
						  String Que_ID=create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")];
						  String CreateSurvey_Questions_1=Environment("Sheet_CreateSurvey_Questions");
						  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions_1); 
						  String[] create_Que_ele=new String[create_Que_col]; 
						  
						  int Question_NUmber=0;
						  
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
						  
						  
						  func_cases.validatePublishedSurveyData(driver, 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Name")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Name")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")], 
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Description")], 
								  Question_NUmber);
					  }
		
				  	}
			}
			//update surveyname
			if(S8!="")
			{
				// survey > surveys and select already created survey and edit
				if(S7.equals(""))
				{
				  	String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
				  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
				  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
				  	String[] update_suvey_ele=new String[update_suveyname_col]; 
				  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
				  	{ 
				  		System.out.println("for Loop" );
				  		System.out.println(S8);
				  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
				  		if (S8.equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
						  // Adduser contains company email_id at 1st column  for validation
				  		{ 
				  			System.out.println("Matches ID to Register");
				  			System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)); 
				  			//based on j value get the row data and do Adding Users
						   
				  			for(int update_suveyname_col_Index=0;update_suveyname_col_Index<update_suveyname_col;update_suveyname_col_Index++) 
				  			{
							 	update_suvey_ele[update_suveyname_col_Index]=RC.getStringCellData(update_surveyname_index, update_suveyname_col_Index, Update_suveyName);
							 	System.out.println(update_suvey_ele[update_suveyname_col_Index]); //call login as company method, pass array values
			  
				  
				  			}
				  			//call update method
				  			func_cases.updateSurveyName(driver, update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SearchWithSurveyName")],
				  				  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Name")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Notes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Name")], 
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Notes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_SubNotes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SurveyName")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")], 
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "TemplateName")]);
						  
				  		}
				  	}
				}
				//create survey and check udation required in create survey fields, if need below will do that 
				if(S7!="")
				{
					
					  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
					  int create_suvey_row=RC.getLastrowno(create_suvey); 
					  int create_suvey_col=RC.getLastcolmno(create_suvey); 
					  String[] create_suvey_ele=new String[create_suvey_col]; 
					  for (int j = 1; j < RC.getLastrowno(create_suvey); j++) 
					  { 
						  System.out.println("for Loop" );
						  System.out.println(S7);
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
						  if (S7.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
							  // Adduser contains company email_id at 1st column  for validation
						  { 
							  System.out.println("Matches ID to Register");
							  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
							  //based on j value get the row data and do Adding Users
							   
							  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
							  {
								  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(j, create_suvey_Login, create_suvey);
								  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
				  
					  
							  }
							  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")]!="")
							  {
								  String Update_suveyName=Environment("Sheet_UpdateSurveyName");
								  int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
								  int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
								  String[] update_suvey_ele=new String[update_suveyname_col]; 
								  //call updatesheet and update method
								  for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(S8);
									  System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
									  if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")].equals
											  (RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int update_suveyname_col_Index=0;update_suveyname_col_Index<update_suveyname_col;update_suveyname_col_Index++) 
										  {
											  update_suvey_ele[update_suveyname_col_Index]=RC.getStringCellData(update_surveyname_index, update_suveyname_col_Index, Update_suveyName);
											  System.out.println(update_suvey_ele[update_suveyname_col_Index]); //call login as company method, pass array values
							  
								  
										  }
										  //call update method
										  func_cases.updateSurveyName(driver,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Name")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Notes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Name")], 
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Notes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_SubNotes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SurveyName")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")], 
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "TemplateName")]);
									  }
								  }
							  }
							  
						  }
					  }
				}
				  
			}
		}
	}
}
	
	
	

