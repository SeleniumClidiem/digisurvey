package BatchExecution_Digi;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BrowserConfiguration_Digi.Browser_Setup;
import Businessfunctions_Digi.Functional_Cases_propread;
import FunctionalLibraries_Digi.Functional_Libraries;
import Utilities_Digi.Excel_Utils;
import Utilities_Digi.copiedText_Robo;
import Utilities_Digi.newTab_robot;
import Utilities_Digi.newWindowRobo;
import Utilities_Digi.pasteLinkinchildRobo;

class Hybrid_Framework_DigiSurvey extends Browser_Setup
{
	Functional_Cases_propread func_cases = new Functional_Cases_propread();
	sheetRead read = new sheetRead();
	String SurveyLink;
	int share;
	String currentUsername;
	String currentPassword;
	List<String> AnsFromResponse;
	
	@DataProvider
	public Object[][] custData() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile(Environment("Sheet_Control"));
		return testData;
	}
	
	@SuppressWarnings("null")
	@Test(dataProvider = "custData")
	public void Frame_Digi(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		Functional_Libraries fl = new Functional_Libraries();
		
		if (S1.equals("Y")) 
		{
			//Login Company
			if(S3!="")
			{
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int j = 1; j < RC.getLastrowno(Login); j++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(j, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
					  } 
				  }
				func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
				//func_cases.companyLogout(driver, Environment("Company_LogoXPATH"), Environment("Company_LogoutXPATH"));
				
					
			}
			//create survey
			if(S4!="")
			{
				if(S3!="")
				{
				  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
				  int create_suvey_row=RC.getLastrowno(create_suvey); 
				  int create_suvey_col=RC.getLastcolmno(create_suvey); 
				  String[] create_suvey_ele=new String[create_suvey_col]; 
				  for (int j = 1; j < RC.getLastrowno(create_suvey); j++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S4);
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
					  if (S4.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
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
						  
						//create survey questions 
						  
						  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]!="")
							{
							  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
							  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
							  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
							  String[] create_Que_ele=new String[create_Que_col]; 
							  int Que_No=1;
							  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
							  { 
								 
								  System.out.println("QueNO: "+Que_No);
								  System.out.println("for Loop" );
								  System.out.println(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]);
								  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
								  if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]
										  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  Que_No++;
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
									  //based on j value get the row data and do Adding Users
									  
									  //int Que_No=0; 
									  for(int create_que=0;create_que<create_Que_col;create_que++) 
									  {
										  
										  create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
										  System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
										 /* if(create_que==create_Que_col)
										  {
											  Que_No++;
											  
										  }*/
							  
									  } 
									  func_cases.AddQuestionto_CreateSurvey(driver, create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "Tags")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "NumberOfOptions")],
											  Que_No-1,create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion1")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion2")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion3")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion4")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion5")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion6")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion7")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion8")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion9")],
											  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "OPtion10")]);
									  
									 
										jse.executeScript("window.scrollBy(0,1000)", "");//scroll down 
										
								  } 
							  }
			
							}
						  jse.executeScript("window.scrollBy(0,-1000)", "");//scroll up to save
						  
						//call "save" for  created survey
							func_cases.save_CreatedSurvey(driver);
							
						//click drafts > more options > publish
							func_cases.publishSavedSurveyQue(driver,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
							
							
						 /* //without saving publish , after creating the survey
						  func_cases.after_surveyCreationClick_Publish(driver);*/
						
						  //validate the published issue matches with input or not
			
			
						  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Validate_Survey")]!="")
						  {
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
								  
						  			}
						  		}
						  
						  		if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Validate_Survey")]!="")
						  		{
						  			func_cases.validatePublishedSurveyData(driver, 
						  					create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Name")], 
						  					create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Name")], 
						  					create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")], 
						  					create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Description")], 
						  					Question_NUmber);
						  		}
						  }
					  }
		
				  	}
				}
			}
			//update surveyname
			if(S5!="")
			{
				// survey > surveys and select already created survey and edit
				if(S3!=""&&S4.equals(""))   //S4-createSurvey
				{
				  	String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
				  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
				  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
				  	String[] update_suvey_ele=new String[update_suveyname_col]; 
				  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
				  	{ 
				  		System.out.println("for Loop" );
				  		System.out.println(S5);
				  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
				  		if (S5.equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
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
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")]);
						  
				  		}
				  	}
				}
				//create survey and check udation required in create survey fields, if need below will do that 
				if(S3!=""&&S4!="")
				{
					
					  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
					  int create_suvey_row=RC.getLastrowno(create_suvey); 
					  int create_suvey_col=RC.getLastcolmno(create_suvey); 
					  String[] create_suvey_ele=new String[create_suvey_col]; 
					  for (int j = 1; j < RC.getLastrowno(create_suvey); j++) 
					  { 
						  System.out.println("for Loop" );
						  System.out.println(S4);
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
						  if (S4.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
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
									  System.out.println(S5);
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
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")]);
									  }
								  }
							  }
							  
						  }
					  }
				}
				  
			}
			//share survey
			if(S6!="")
			{
				if(S3!=""&&S4.equals(""))
				{
				
					share = 1;
					System.out.println("ShareSurvey : "+share);
				  String share_survey=Environment("Sheet_ShareSurvey"); 
				  int share_survey_row=RC.getLastrowno(share_survey); 
				  int share_survey_col=RC.getLastcolmno(share_survey); 
				  String[] share_survey_ele=new String[share_survey_col]; 
				  for (int j = 1; j < RC.getLastrowno(share_survey); j++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S6);
					  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"), share_survey)); 
					  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int share_survey_ind=0;share_survey_ind<share_survey_col;share_survey_ind++) 
						  {
							  share_survey_ele[share_survey_ind]=RC.getStringCellData(j, share_survey_ind, share_survey);
							  System.out.println(share_survey_ele[share_survey_ind]); //call login as company method, pass array values
			  
				  
						  }
						  
						  //call share survey
						  if(share_survey_ele[RC.Current_Coulumn_Number(share_survey, "SurveyName")]!="")
						  func_cases.share_survey(driver, share_survey_ele[RC.Current_Coulumn_Number(share_survey, "SurveyName")],
								  share_survey_ele[RC.Current_Coulumn_Number(share_survey, "Recipient_Email_IDs")],share);
						  if (S6.equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)))
						  {
							  share++;
						  }
						  
					  }
				  }
				}
				  
				  if(S3!=""&&S4!="")//create survey for surveyname
				  {
					  share =1;
					  System.out.println("ShareSurvey : "+share);
					  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
					  String share_survey= Environment("Sheet_ShareSurvey");
					  
					 // int create_suvey_row=RC.getLastrowno(create_suvey); 
					  int create_suvey_col=RC.getLastcolmno(create_suvey); 
					  String[] create_suvey_ele=new String[create_suvey_col]; 
					  
					 // int share_survey_row=RC.getLastrowno(share_survey); 
					  int share_survey_col=RC.getLastcolmno(share_survey); 
					  String[] share_survey_ele=new String[share_survey_col];
					  
					  for (int create = 1; create < RC.getLastrowno(create_suvey); create++) 
					  { 
						  System.out.println("for Loop" );
						  System.out.println(S4);
						  System.out.println(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
						  if (S4.equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
							  // Adduser contains company email_id at 1st column  for validation
						  { 
							  System.out.println("Matches ID to Register");
							  System.out.println(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
							  //based on j value get the row data and do Adding Users
							   
							  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
							  {
								  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(create, create_suvey_Login, create_suvey);
								  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
				  
					  
							  }
							  
							  for (int j = 1; j < RC.getLastrowno(share_survey); j++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S6);
								  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"), share_survey)); 
								  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int share_survey_ind=0;share_survey_ind<share_survey_col;share_survey_ind++) 
									  {
										  share_survey_ele[share_survey_ind]=RC.getStringCellData(j, share_survey_ind, share_survey);
										  System.out.println(share_survey_ele[share_survey_ind]); //call login as company method, pass array values
						  
							  
									  }
									  
									  //call share survey
									 func_cases.share_survey(driver, create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")],
											  share_survey_ele[RC.Current_Coulumn_Number(share_survey, "Recipient_Email_IDs")],share);
									 if (S6.equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey))||
											 S4.equals(RC.getStringCellData(create+1, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
									 {
										 share++;
									 }
								  }
							  }
							  
							  
						  }
					  }
					  if(S7!="")
					  {
						  func_cases.CompanyLogout(driver);
					  }
				  }
			
			}
			//end user giving respose
			if(S7!="")
			{
				
				  //String parentWindow= driver.getWindowHandle();
				  int enduserno=1;
				  String EndUser=Environment("Sheet_EndUser"); 
				  int EndUser_row=RC.getLastrowno(EndUser); 
				  int EndUser_col=RC.getLastcolmno(EndUser); 
				  String[] EndUser_ele=new String[EndUser_col]; 
				  for (int enduser_index = 1; enduser_index < RC.getLastrowno(EndUser); enduser_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S7);
					  System.out.println(RC.getStringCellData(enduser_index, RC.Current_Coulumn_Number(EndUser, "EndUserID"), EndUser)); 
					  if (S7.equals(RC.getStringCellData(enduser_index, RC.Current_Coulumn_Number(EndUser, "EndUserID"),EndUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(enduser_index, RC.Current_Coulumn_Number(EndUser, "EndUserID"),EndUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int EndUser_ind=0;EndUser_ind<EndUser_col;EndUser_ind++) 
						  {
							  EndUser_ele[EndUser_ind]=RC.getStringCellData(enduser_index, EndUser_ind, EndUser);
							  System.out.println(EndUser_ele[EndUser_ind]); //call login as company method, pass array values
			  
				  
						  }
					  
				  
						  //call Enduser method to login and fill the survey
						  
						  /*INCASE OF USING GMAIL
                    		 func_cases.enduser_Login(driver, EndUser_ele[RC.Current_Coulumn_Number(EndUser, "WebLink")], 
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "EndUserEmail")], 
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "password")], 
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "searchtext")],enduserno);*/
						  //loginas digi user or company inorder to give reponse to shared survey
						  func_cases.enduser_Login(driver, EndUser_ele[RC.Current_Coulumn_Number(EndUser, "WebLink")],
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "EndUserEmail")],
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "password")]);
						  
						// if Direct share survey happend, 
						  if(S4==""&&S6!="")
						  {
							    /*get que types  based on surveyname , in share survey sheet search with survey name , 
							   and click view and get no of que & get answer types */
							  String oldTab = driver.getWindowHandle();
							  String sharesurvey=Environment("Sheet_ShareSurvey"); 
							  int share_survey_row=RC.getLastrowno(sharesurvey); 
							  int share_survey_col=RC.getLastcolmno(sharesurvey); 
							  String[] share_survey_ele=new String[share_survey_col]; 
							  for (int j = 1; j < RC.getLastrowno(sharesurvey); j++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S6);
								  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "ShareID"), sharesurvey)); 
								  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "ShareID"),sharesurvey)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "ShareID"),sharesurvey)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int share_survey_ind=0;share_survey_ind<share_survey_col;share_survey_ind++) 
									  {
										  share_survey_ele[share_survey_ind]=RC.getStringCellData(j, share_survey_ind, sharesurvey);
										  System.out.println(share_survey_ele[share_survey_ind]); //call login as company method, pass array values
						  
							  
									  }
									  if(share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")]!="")
									  {
										  //view survey and get details
										  String[] view_details = func_cases.viewSurvey(driver, 
												  share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")]);
										  int Total_Que = 0;
										  Total_Que = Integer.parseInt(view_details[1]);
										  String[] queType_ele = new String[Total_Que];
										  System.out.println("converted to integer : "+Total_Que);
										  /*if(view_details[1]=="1")  {  Total_Que=1; }*/
										  
										 //based on no of que, get que type 
										  for(int i=1; i <= Total_Que ; i++)
										  {
											  queType_ele[i-1] = func_cases.viewsurvey_QueType(driver,i);
											  System.out.println( queType_ele[i-1]);
											  
											  
										  }
										  for(int que_no=1; que_no <= Total_Que;que_no++)
										  {
											  if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "FillAnswer")]!="")
											  {
												  /*func_cases.answerSurveyByEndUser(driver,EndUser_ele[RC.Current_Coulumn_Number(EndUser, "SaveResponse")],
												  	EndUser_ele[RC.Current_Coulumn_Number(EndUser, "SubmitResponse")]);*/
												  if(que_no==1)
												  {
													  ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
													  System.out.println(newTab.size());
													  newTab.remove(oldTab);
													  System.out.println(newTab.get(0));
													  // change focus to new tab
													  driver.switchTo().window(newTab.get(0));
												  }
													
												  func_cases.answer_basedonQueType(driver, que_no, queType_ele[que_no-1],
														  share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")]);
											  }
											  //based on survey name S6  //or based on create survey S4
										  }
										  func_cases.saveSurveySubmit(driver,Total_Que);
										  driver.close();
										  Thread.sleep(3000);
										  driver.switchTo().window(oldTab);
									  }
						  
								  }
							  }
						  }	  
						  else              //if created and shared survey fill
						  {
							  if(S4!=""&&S6!="")//create survey & share for surveyname
							  {
								 System.out.println(share+" no of shared survey links "); 
								  
								  String oldTab  = driver.getWindowHandle(); 
								  int i=1;
								  //open a new window and get surveylink from excel and give response
								  
								  	String create_suvey=Environment("Sheet_Survey_createSurvey"); 
								  	String share_survey= Environment("Sheet_ShareSurvey");
								  
								  	int create_suvey_row=RC.getLastrowno(create_suvey); 
								  	int create_suvey_col=RC.getLastcolmno(create_suvey); 
								  	String[] create_suvey_ele=new String[create_suvey_col]; 
								  
								  	// int share_survey_row=RC.getLastrowno(share_survey); 
								  	int share_survey_col=RC.getLastcolmno(share_survey); 
								  	String[] share_survey_ele=new String[share_survey_col];
								  	
									
								  
								  	for (int create = 1; create < RC.getLastrowno(create_suvey); create++) 
								  	{ 
								  		
								  		System.out.println("for Loop" );
								  		System.out.println(S4);
								  		System.out.println(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
								  		if (S4.equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
								  			// Adduser contains company email_id at 1st column  for validation
								  		{ 
								  			
								  			System.out.println("Matches ID to Register");
								  			System.out.println(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
								  			//based on j value get the row data and do Adding Users
										   
								  			for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
								  			{
								  				create_suvey_ele[create_suvey_Login]=RC.getStringCellData(create, create_suvey_Login, create_suvey);
								  				System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
								  				
								  			}
								  			  
										  
								  			//read queId from created survey elements ,compare those with createquestions sheet ID
								  			  Thread.sleep(3000);
											  newTab_robot newTab = new newTab_robot();
											  ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
											  System.out.println(Tabs.size());
											  Tabs.remove(oldTab);
											  System.out.println(Tabs.get(0));
											  // change focus to new tab
											  driver.switchTo().window(Tabs.get(0));
											  
											  //switch to new tab to answer survey, now get the link from excel
											  
											  File src = new File("SurveyLink_Excel\\Survey_Links.xlsx");
											  FileInputStream fis;
											  fis = new FileInputStream(src);
											  XSSFWorkbook wb = new XSSFWorkbook(fis);
											  XSSFSheet sheet1 = wb.getSheetAt(0);
											  String data0 ;
											  if(i<=share)
											  {
										  		data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
										  		System.out.println("Open Survey Link :"+i+data0);
										  		wb.close();
										  		driver.get(data0);//surveylink opened , now you have to fill that response as an end user
											  }
									  String surveylinkURL = driver.getCurrentUrl();
									  if(!surveylinkURL.contains("Error"))
									  {
								  				String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
								  				int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
								  				int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
								  				String[] create_Que_ele=new String[create_Que_col]; 
								  				int Que_No=0;
								  				for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
								  				{ 
											 
								  					System.out.println("QueNO: "+Que_No);
								  					System.out.println("for Loop" );
								  					System.out.println(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]);
								  					System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
								  					if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]
													  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
								  					// Adduser contains company email_id at 1st column  for validation
								  					{ 
								  						Que_No++;
								  						System.out.println("Matches ID to Register");
								  						System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
								  						//based on j value get the row data and do Adding Users
												  
								  						
								  						for(int create_que=0;create_que<create_Que_col;create_que++) 
								  						{
													  
								  							create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
								  							System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
													 
										  
								  						} 
								  						func_cases.answer_basedonQueType(driver, Que_No, 
														  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
								  					}   
								  				}
								  				i++;
								  				func_cases.saveSurveySubmit(driver,Que_No);
								  				driver.close();
								  				Thread.sleep(3000);
								  				driver.switchTo().window(oldTab);
								  			}
								  		}
								  	}
							 
							 
								
								  
								  
							  }
						  }
						  func_cases.CompanyLogout(driver);
						  
						
						}
					  
					}
				
						  
						  
					  
					  //driver.switchTo().window(parentWindow);
				  
			}
			if(S8!="")
			{
				//Response View
				
				String currentURL=driver.getCurrentUrl();
				if(currentURL.contains("Account/Login"))
				{
					System.out.println("SURVEY RESPONSE");
					System.out.println("username :"+currentUsername);
					System.out.println("password :"+currentPassword);
					func_cases.companyLogin(driver, currentUsername, currentPassword);
				}
				if(S3!=""&&S4==""&&S6=="")
				{
					
				  String Response_Survey=Environment("Sheet_Response_Survey"); 
				  int Response_Survey_row=RC.getLastrowno(Response_Survey); 
				  int Response_Survey_col=RC.getLastcolmno(Response_Survey); 
				  String[] Response_Survey_ele=new String[Response_Survey_col]; 
				  for (int Response_Survey_index = 1; Response_Survey_index < RC.getLastrowno(Response_Survey); Response_Survey_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S8);
					  System.out.println(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "ResponseID"), Response_Survey)); 
					  if (S8.equals(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "ResponseID"),Response_Survey)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "ResponseID"),Response_Survey)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Response_Survey_ind=0;Response_Survey_ind<Response_Survey_col;Response_Survey_ind++) 
						  {
							  Response_Survey_ele[Response_Survey_ind]=RC.getStringCellData(Response_Survey_index, Response_Survey_ind, Response_Survey);
							  System.out.println(Response_Survey_ele[Response_Survey_ind]); //call login as company method, pass array values
			  
				  
						  }
						  //call surveyResponse Method
						  AnsFromResponse =func_cases.surveyresponse_ofEndUsers(driver, 
								  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")]);
					  }
				  }
				}
				else
					if(S3!=""&&S4!=""&&S6!="")
					{
						
						  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
						  int create_suvey_row=RC.getLastrowno(create_suvey); 
						  int create_suvey_col=RC.getLastcolmno(create_suvey); 
						  String[] create_suvey_ele=new String[create_suvey_col]; 
						  for (int create_ind = 1; create_ind < RC.getLastrowno(create_suvey); create_ind++) 
						  { 
							  System.out.println("for Loop" );
							  System.out.println(S4);
							  System.out.println(RC.getStringCellData(create_ind, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
							  if (S4.equals(RC.getStringCellData(create_ind, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)))
								  // Adduser contains company email_id at 1st column  for validation
							  { 
								  System.out.println("Matches ID to Register");
								  System.out.println(RC.getStringCellData(create_ind, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
								  //based on j value get the row data and do Adding Users
								   
								  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
								  {
									  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(create_ind, create_suvey_Login, create_suvey);
									  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
					  
						  
								  }
								  /*String share_survey=Environment("Sheet_ShareSurvey"); 
								  int share_survey_row=RC.getLastrowno(share_survey); 
								  int share_survey_col=RC.getLastcolmno(share_survey); 
								  String[] share_survey_ele=new String[share_survey_col]; 
								  for (int j = 1; j < RC.getLastrowno(share_survey); j++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(S6);
									  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"), share_survey)); 
									  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int share_survey_ind=0;share_survey_ind<share_survey_col;share_survey_ind++) 
										  {
											  share_survey_ele[share_survey_ind]=RC.getStringCellData(j, share_survey_ind, share_survey);
											  System.out.println(share_survey_ele[share_survey_ind]); //call login as company method, pass array values
										  }
										  //if u add any control  in between create survey sheet and share survey , call the surveyrepose() here
										  func_cases.surveyresponse_ofEndUsers(driver, 
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
									  }
								  }*/
//answers from surveyresponse view								  
								  AnsFromResponse= func_cases.surveyresponse_ofEndUsers(driver, 
										  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
								  
//validate the answers in List with Ans entered by enduser matched or not ...."list.get(i);"
								  
								  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]!="")
									{
									  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
									  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
									  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
									  String[] create_Que_ele=new String[create_Que_col]; 
									  int Que_No=1;
									  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
									  { 
										 
										  System.out.println("QueNO: "+Que_No);
										  System.out.println("for Loop" );
										  System.out.println(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]);
										  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
										  if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]
												  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
											  // Adduser contains company email_id at 1st column  for validation
										  { 
											  Que_No++;
											  System.out.println("Matches ID to Register");
											  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
											  //based on j value get the row data and do Adding Users
											 
											  for(int create_que=0;create_que<create_Que_col;create_que++) 
											  { 
												  create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
												  System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
												
											  } 
											  String Que_type = create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")];
											  
											  String Survey_Answers=Environment("Sheet_Survey_Answers"); 
											  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
											  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
											  String[] Survey_Answers_ele=new String[Survey_Answers_col]; 
											  for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
											  { 
												  System.out.println("for Loop" );
												  System.out.println(Que_type);
												  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers)); 
												  if (Que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)))
													  // Adduser contains company email_id at 1st column  for validation
												  { 
													  System.out.println("Matches ID to Register");
													  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)); 
													  //based on j value get the row data and do Adding Users
													   
													  for(int Survey_Answers_Ind=0;Survey_Answers_Ind<Survey_Answers_col;Survey_Answers_Ind++) 
													  {
														  Survey_Answers_ele[Survey_Answers_Ind]=RC.getStringCellData(Survey_Answers_Index, Survey_Answers_Ind, Survey_Answers);
														  System.out.println(Survey_Answers_ele[Survey_Answers_Ind]);
													  }
													  
													  List<String> list = new ArrayList<>();
													  String[] options = new String[]{Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option1")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option2")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option3")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option4")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option5")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option6")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option7")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option8")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option9")],
															  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Option10")]};
													  switch(Que_type)
													  {
													  		case "Check Box": System.out.println("checkbox to be clicked");
													  		for(int i=0;i<10;i++)
													  		{
													  			int cht_tot=0;
													  			
													  			if(options[i]!="")
													  			{
													  				list.add(options[i]);
													  				cht_tot++;
													  			}
													  			
													  			if(i==9)
													  			{
													  				String[] str = new String[cht_tot-1];
													  				/*String str1 = list.get(i); 
													  				String str2 = list.get(i+ 1);
													  				str1= str1.concat(str2);
													  				list.set(i,str1);
													  				list.remove(i+ 1);*/
													  				
													  				int tot_chkEle =list.size();
													  				
													  			}
													  		}
													  		
													  		break;
													  		
													  		case "Date": System.out.println("Date to be enetered");
													  		list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")]);
													  		break;
													  		
													  		case "File Upload":System.out.println("file to be uploaded");
													  		break;
													  		
													  		case "Dropdown":System.out.println("dropdown to be clicked");
													  		break;
													  		
													  		case "Number":System.out.println("Number to be clicked");
													  		break;
													  		
													  		case "Radio Button":System.out.println("radio button to be clicked");
													  		break;
													  		
													  		case "Scale / Rate":System.out.println("rating to be entered");
													  		break;
													  		
													  		case "Single Line Text":System.out.println("single line text to be eneterd");
													  		break;
													  		
													  		case "Text Area":System.out.println("text area to be entered");
													  		break;
													  		
													  		case "Yes or No":System.out.println("Y/N to be selected");
													  		break;
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
			if(S9!="")
			{
				//Add User
				
				if(S3!="")
				{
				  String AddUsers=Environment("Sheet_AddUsers"); 
				  int AddUsers_row=RC.getLastrowno(AddUsers); 
				  int AddUsers_col=RC.getLastcolmno(AddUsers); 
				  String[] AddUsers_ele=new String[AddUsers_col]; 
				  for (int Addusers_index = 1; Addusers_index < RC.getLastrowno(AddUsers); Addusers_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S9);
					  System.out.println(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "AddUserID"), AddUsers)); 
					  if (S9.equals(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "AddUserID"),AddUsers)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "AddUserID"),AddUsers)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int AddUsers_ind=0;AddUsers_ind<AddUsers_col;AddUsers_ind++) 
						  {
							  AddUsers_ele[AddUsers_ind]=RC.getStringCellData(Addusers_index, AddUsers_ind, AddUsers);
							  System.out.println(AddUsers_ele[AddUsers_ind]); //call login as company method, pass array values
			  
				  
						  }
						  //addusers method
						  
						  
						  func_cases.AddUsers_inCompany(driver, 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "UserName")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Employee ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Email ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Contact No")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Designation")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Parent_Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Reporting Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Hr Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Street")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "City")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Country ")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "State")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Zip Code")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "ChooseFilePath")]);
					  }
				  }
				}
				
			}
			if(S10!="")
			{
				//call create quiz method
				if(S3!="")
				{
				  String create_quiz=Environment("Sheet_Quiz_Create"); 
				  int create_quiz_row=RC.getLastrowno(create_quiz); 
				  int create_quiz_col=RC.getLastcolmno(create_quiz); 
				  String[] create_quiz_ele=new String[create_quiz_col]; 
				  for (int create_quiz_index = 1; create_quiz_index < RC.getLastrowno(create_quiz); create_quiz_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S10);
					  System.out.println(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"), create_quiz)); 
					  if (S10.equals(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"),create_quiz)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"),create_quiz)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int create_quiz_ind=0;create_quiz_ind<create_quiz_col;create_quiz_ind++) 
						  {
							  create_quiz_ele[create_quiz_ind]=RC.getStringCellData(create_quiz_index, create_quiz_ind, create_quiz);
							  System.out.println(create_quiz_ele[create_quiz_ind]); //call login as company method, pass array values
			  
				  
						  }
						  //call create quiz method
						  func_cases.createQuizTempalte(driver, 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "TemplateName")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizType")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizName")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Description")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "SaveAsTemplate")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "NewTemplateName")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Individual_Or_TotalDuration")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "TotalDur_in Minutes")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "EqualWeight")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Score")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Equal_Duration")], 
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "DurationInSeconds")],
								  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "ExpiresInDays(Incaseof W/O duration)")]);
						  
						  //create quiz questions
						  if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Quiz_que")]!="")
							{
							  String create_quiz_que=Environment("Sheet_Quiz_Questions"); 
							  int quiz_que_row=RC.getLastrowno(create_quiz_que); 
							  int quiz_que_col=RC.getLastcolmno(create_quiz_que); 
							  String[] quiz_que_ele=new String[quiz_que_col]; 
							  int Que_No=1;
							  for (int k = 1; k < RC.getLastrowno(create_quiz_que); k++) 
							  { 
								 
								  System.out.println("QueNO: "+Que_No);
								  System.out.println("for Loop" );
								  System.out.println(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Quiz_que")]);
								  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(create_quiz_que, "Quiz_QueID"), create_quiz_que)); 
								  if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Quiz_que")]
										  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(create_quiz_que, "Quiz_QueID"), create_quiz_que)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  Que_No++;
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(create_quiz_que, "Quiz_QueID"), create_quiz_que)); 
									  //based on j value get the row data and do Adding Users
									  
									  //int Que_No=0; 
									  for(int create_que=0;create_que < quiz_que_col;create_que++) 
									  {
										  
										  quiz_que_ele[create_que]=RC.getStringCellData(k, create_que, create_quiz_que);
										  System.out.println(quiz_que_ele[create_que]); //call login as company method, pass array values
										 /* if(create_que==create_Que_col)
										  {
											  Que_No++;
											  
										  }*/
							  
									  } 
									  func_cases.Quiz_Questions(driver,
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "EnterUrQue")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "Weightage_Score")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "DurationInSeconds")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "AnswerType")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "NumberOfOptions")],
											  Que_No-1,
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion1")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion2")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion3")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion4")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion5")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion6")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion7")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion8")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion9")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "OPtion10")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "LabelText(Optional)")],
											  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "Validate_Que")]);
									  
										jse.executeScript("window.scrollBy(0,1000)", "");//scroll down 
										
										
								  } 
							  }
			
							}
						  	//save created quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "saveQuiz")]!="")
							{
								func_cases.saveCreatedQuiz(driver);
							}
							
							//cancel created Quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "cancelQuiz")]!="")
							{
								func_cases.cancelCreatedQuiz(driver);
							}
							
							//publish created Quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "publishQuiz")]!="")
							{
								func_cases.publish_SavedSurvey(driver);
							}
						  
						  
					  }
				  }
				}
			}
			
			if(S11!="")
			{
				//publish Quiz
				if(S3!=""&&S10=="")                 //login and create quiz
				{
				  String publish_saved_quiz=Environment("Sheet_PublishSavedQuiz"); 
				  int publish_saved_quiz_row=RC.getLastrowno(publish_saved_quiz); 
				  int publish_saved_quiz_col=RC.getLastcolmno(publish_saved_quiz); 
				  String[] publish_saved_quiz_ele=new String[publish_saved_quiz_col]; 
				  for (int publish_saved_quiz_index = 1; publish_saved_quiz_index < RC.getLastrowno(publish_saved_quiz); publish_saved_quiz_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S11);
					  System.out.println(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"), publish_saved_quiz)); 
					  if (S11.equals(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int publish_saved_quiz_ind=0;publish_saved_quiz_ind<publish_saved_quiz_col;publish_saved_quiz_ind++) 
						  {
							  publish_saved_quiz_ele[publish_saved_quiz_ind]=RC.getStringCellData(publish_saved_quiz_index, publish_saved_quiz_ind, publish_saved_quiz);
							  System.out.println(publish_saved_quiz_ele[publish_saved_quiz_ind]); //call login as company method, pass array values
			  
				  
						  }
						  func_cases.publishExistedQuiz(driver, 
								  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "QuizName")], 
								  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "Proceed")]);
					  }
				  }
				}
				if(S3!=""&&S10!="")
				{
					  String create_quiz=Environment("Sheet_Quiz_Create"); 
					  int create_quiz_row=RC.getLastrowno(create_quiz); 
					  int create_quiz_col=RC.getLastcolmno(create_quiz); 
					  String[] create_quiz_ele=new String[create_quiz_col]; 
					  for (int create_quiz_index = 1; create_quiz_index < RC.getLastrowno(create_quiz); create_quiz_index++) 
					  { 
						  System.out.println("for Loop" );
						  System.out.println(S10);
						  System.out.println(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"), create_quiz)); 
						  if (S10.equals(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"),create_quiz)))
							  // Adduser contains company email_id at 1st column  for validation
						  { 
							  System.out.println("Matches ID to Register");
							  System.out.println(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"),create_quiz)); 
							  //based on j value get the row data and do Adding Users
							   
							  for(int create_quiz_ind=0;create_quiz_ind<create_quiz_col;create_quiz_ind++) 
							  {
								  create_quiz_ele[create_quiz_ind]=RC.getStringCellData(create_quiz_index, create_quiz_ind, create_quiz);
								  System.out.println(create_quiz_ele[create_quiz_ind]); //call login as company method, pass array values
				  
					  
							  }
							  //quizname search in quizzes and get the status if it is a draft , publish it
							  
							  
							  String publish_saved_quiz=Environment("Sheet_PublishSavedQuiz"); 
							  int publish_saved_quiz_row=RC.getLastrowno(publish_saved_quiz); 
							  int publish_saved_quiz_col=RC.getLastcolmno(publish_saved_quiz); 
							  String[] publish_saved_quiz_ele=new String[publish_saved_quiz_col]; 
							  for (int publish_saved_quiz_index = 1; publish_saved_quiz_index < RC.getLastrowno(publish_saved_quiz); publish_saved_quiz_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S11);
								  System.out.println(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"), publish_saved_quiz)); 
								  if (S11.equals(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int publish_saved_quiz_ind=0;publish_saved_quiz_ind<publish_saved_quiz_col;publish_saved_quiz_ind++) 
									  {
										  publish_saved_quiz_ele[publish_saved_quiz_ind]=RC.getStringCellData(publish_saved_quiz_index, publish_saved_quiz_ind, publish_saved_quiz);
										  System.out.println(publish_saved_quiz_ele[publish_saved_quiz_ind]); //call login as company method, pass array values
						  
							  
									  }
									  func_cases.publishExistedQuiz(driver, 
											  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizName")], 
											  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "Proceed")]);
								  }
							  }
							 
						  }
					  }
				}
				
			}
			if(S13!="")
			{
				func_cases.CompanyLogout(driver);
			}
			if(S14!="")
			{
				//Individual Registration
				
				  String Indiv_Reg=Environment("Sheet_IndividualRegister"); 
				  int Indiv_Reg_row=RC.getLastrowno(Indiv_Reg); 
				  int Indiv_Reg_col=RC.getLastcolmno(Indiv_Reg); 
				  String[] Indiv_Reg_ele=new String[Indiv_Reg_col]; 
				  for (int Indiv_Reg_index = 1; Indiv_Reg_index < RC.getLastrowno(Indiv_Reg); Indiv_Reg_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S14);
					  System.out.println(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Indiv_Reg, "Indiv_RegID"), Indiv_Reg)); 
					  if (S14.equals(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Indiv_Reg, "Indiv_RegID"),Indiv_Reg)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Indiv_Reg, "Indiv_RegID"),Indiv_Reg)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Indiv_Reg_ind=0;Indiv_Reg_ind<Indiv_Reg_col;Indiv_Reg_ind++) 
						  {
							  Indiv_Reg_ele[Indiv_Reg_ind]=RC.getStringCellData(Indiv_Reg_index, Indiv_Reg_ind, Indiv_Reg);
							  System.out.println(Indiv_Reg_ele[Indiv_Reg_ind]); //call login as company method, pass array values
			  
				  
						  }
				
						  func_cases.candidateRegistration(driver, 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "FirstName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "LastName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "EmailID")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ContactNumber")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Password")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ConfirmPassword")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Captcha")]);
					  }
				  }
			}
			if(S15!="")
			{
				//company Registration
				
				  String Org_Reg=Environment("Sheet_OrganizationRegister"); 
				  int Org_Reg_row=RC.getLastrowno(Org_Reg); 
				  int Org_Reg_col=RC.getLastcolmno(Org_Reg); 
				  String[] Org_Reg_ele=new String[Org_Reg_col]; 
				  for (int Org_Reg_index = 1; Org_Reg_index < RC.getLastrowno(Org_Reg); Org_Reg_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S15);
					  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"), Org_Reg)); 
					  if (S15.equals(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"),Org_Reg)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"),Org_Reg)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Org_Reg_ind=0;Org_Reg_ind<Org_Reg_col;Org_Reg_ind++) 
						  {
							  Org_Reg_ele[Org_Reg_ind]=RC.getStringCellData(Org_Reg_index, Org_Reg_ind, Org_Reg);
							  System.out.println(Org_Reg_ele[Org_Reg_ind]); //call login as company method, pass array values
			  
				  
						  }
						  func_cases.companyRegistration(driver, 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
								  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
				
						  
					  }
				  }
			}
			
		}
	}
}
	
