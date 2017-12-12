package BatchExecution_Digi;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BrowserConfiguration_Digi.Browser_Setup;
import Businessfunctions_Digi.Functional_Cases_propread;
import FunctionalLibraries_Digi.Functional_Libraries;
import Loggings_Digi.Logs_DigiSurvey;
import Utilities_Digi.DB_QueType;
import Utilities_Digi.Excel_Utils;
import Utilities_Digi.copiedText_Robo;
import Utilities_Digi.newTab_robot;
import Utilities_Digi.newWindowRobo;
import Utilities_Digi.pasteLinkinchildRobo;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	  MethodListener.class })
class Hybrid_Framework_DigiSurvey extends Browser_Setup
{
	
	{
		System.setProperty("atu.reporter.config", "lib//atu.properties");
	}
	Functional_Cases_propread func_cases = new Functional_Cases_propread();
	sheetRead read = new sheetRead();
	String SurveyLink;
	int share;
	int share_quiz;
	String currentUsername;
	String currentPassword;
	List<String> AnsFromResponse;
	List<String> QuizResponse;
	List<String> refer_friends = new ArrayList<>();
	List<String> company_Profile = new ArrayList<>();
	String[] que_type_IndividualShare;
	
	
	@DataProvider
	public Object[][] custData() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),1);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData")
	public void Frame_Digi(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
//added lines for Log4j==============
		
		
		DOMConfigurator.configure("log4j.xml");
		Logs_DigiSurvey.startTestCase(S2);
//=================================		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		Functional_Libraries fl = new Functional_Libraries();
		
		if (S1.equals("Y")) 
		{
			ATUReports.currentRunDescription = S2;
			if(S27!="")
			{
				String clidiem_Admin=Environment("Sheet_clidiem_Admin"); 
				  int clidiem_Admin_row=RC.getLastrowno(clidiem_Admin); 
				  int clidiem_Admin_col=RC.getLastcolmno(clidiem_Admin); 
				  String[] clidiem_Admin_ele=new String[clidiem_Admin_col]; 
				  for (int clidiem_Admin_index = 1; clidiem_Admin_index < RC.getLastrowno(clidiem_Admin); clidiem_Admin_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S27);
					  System.out.println(RC.getStringCellData(clidiem_Admin_index, RC.Current_Coulumn_Number(clidiem_Admin, "AdminID"), clidiem_Admin)); 
					  if (S27.equals(RC.getStringCellData(clidiem_Admin_index, RC.Current_Coulumn_Number(clidiem_Admin, "AdminID"),clidiem_Admin)))
						  // Adduser contains company email_id at 1st column  for validation
					  {
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(clidiem_Admin_index, RC.Current_Coulumn_Number(clidiem_Admin, "AdminID"),clidiem_Admin)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int clidiem_Admin_ind=0;clidiem_Admin_ind<clidiem_Admin_col;clidiem_Admin_ind++) 
						  {
							  clidiem_Admin_ele[clidiem_Admin_ind]=RC.getStringCellData(clidiem_Admin_index, clidiem_Admin_ind, clidiem_Admin);
							  System.out.println(clidiem_Admin_ele[clidiem_Admin_ind]); //call login as company method, pass array values
						  }
					  }
				  }
				  func_cases.adminLogin(driver, 
						  clidiem_Admin_ele[RC.Current_Coulumn_Number(clidiem_Admin, "EmailID")], 
						  clidiem_Admin_ele[RC.Current_Coulumn_Number(clidiem_Admin, "Password")]);
				  
			}
			if(S29!="")
			{
				String AdminUsers=Environment("Sheet_AdminUsers"); 
				  int AdminUsers_row=RC.getLastrowno(AdminUsers); 
				  int AdminUsers_col=RC.getLastcolmno(AdminUsers); 
				  String[] AdminUsers_ele=new String[AdminUsers_col]; 
				  for (int AdminUsers_index = 1; AdminUsers_index < RC.getLastrowno(AdminUsers); AdminUsers_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S29);
					  System.out.println(RC.getStringCellData(AdminUsers_index, RC.Current_Coulumn_Number(AdminUsers, "Adm_UserID"), AdminUsers)); 
					  if (S29.equals(RC.getStringCellData(AdminUsers_index, RC.Current_Coulumn_Number(AdminUsers, "Adm_UserID"),AdminUsers)))
						  // Adduser contains company email_id at 1st column  for validation
					  {
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(AdminUsers_index, RC.Current_Coulumn_Number(AdminUsers, "Adm_UserID"),AdminUsers)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int AdminUsers_ind=0;AdminUsers_ind<AdminUsers_col;AdminUsers_ind++) 
						  {
							  AdminUsers_ele[AdminUsers_ind]=RC.getStringCellData(AdminUsers_index, AdminUsers_ind, AdminUsers);
							  System.out.println(AdminUsers_ele[AdminUsers_ind]); //call login as company method, pass array values
						  }
						  func_cases.adminUsers(driver, 
								  AdminUsers_ele[RC.Current_Coulumn_Number(AdminUsers, "UserName")], 
								  AdminUsers_ele[RC.Current_Coulumn_Number(AdminUsers, "EmailId")], 
								  AdminUsers_ele[RC.Current_Coulumn_Number(AdminUsers, "Contact")], 
								  AdminUsers_ele[RC.Current_Coulumn_Number(AdminUsers, "Role")], 
								  AdminUsers_ele[RC.Current_Coulumn_Number(AdminUsers, "Password")]);
					  }
				  }
			}
			if(S28!="")
			{
				func_cases.adminLogout(driver);
			}
			int firsttimeRegister=1;
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
						  if(S26=="")
						  {
							  func_cases.companyRegistration(driver, firsttimeRegister, 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
							  func_cases.CompanyLogout(driver);
						  }
						  if(firsttimeRegister==1&&S26!="")
						  {
							  func_cases.companyRegistration(driver, firsttimeRegister, 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
							  if(S26!="")
							  	func_cases.referFriends(driver, Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ReferFrnds")]);
							  	func_cases.CompanyLogout(driver);
							  	firsttimeRegister++;
						  }
					  }
				  }
			}
			//if(S14!=""||S26!="")
			if(S14!="")
			{
				//Individual Registration
				
				  String Org_Reg=Environment("Sheet_OrganizationRegister"); 
				  int Org_Reg_row=RC.getLastrowno(Org_Reg); 
				  int Org_Reg_col=RC.getLastcolmno(Org_Reg); 
				  String[] Org_Reg_ele=new String[Org_Reg_col]; 
				  
				  String Indiv_Reg=Environment("Sheet_IndividualRegister"); 
				  int Indiv_Reg_row=RC.getLastrowno(Indiv_Reg); 
				  int Indiv_Reg_col=RC.getLastcolmno(Indiv_Reg); 
				  String[] Indiv_Reg_ele=new String[Indiv_Reg_col]; 
				  for (int Indiv_Reg_index = 1; Indiv_Reg_index < RC.getLastrowno(Indiv_Reg); Indiv_Reg_index++) 
				  { 
					  System.out.println("for Loop");
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
						  if(S26=="")
						  {
							  func_cases.candidateRegistration(driver, firsttimeRegister, 
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "FirstName")], 
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "LastName")], 
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "EmailID")], 
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ContactNumber")],
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Password")],
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ConfirmPassword")],
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Captcha")]);
							  func_cases.CompanyLogout(driver);
						  }
						  if(firsttimeRegister==1&&S26!="")
						  {
							  func_cases.candidateRegistration(driver, firsttimeRegister, 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "FirstName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "LastName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "EmailID")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ContactNumber")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Password")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ConfirmPassword")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Captcha")]);
						  
							  if(S26!="")
						  		func_cases.referFriends(driver, Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ReferFrnds")]);
								func_cases.CompanyLogout(driver);
								firsttimeRegister++;
						  }
					  }
				  } 
			}
			if(S26!="")
			{
				  String Org_Reg=Environment("Sheet_OrganizationRegister"); 
				  int Org_Reg_row=RC.getLastrowno(Org_Reg); 
				  int Org_Reg_col=RC.getLastcolmno(Org_Reg); 
				  String[] Org_Reg_ele=new String[Org_Reg_col]; 
				  
				  String Indiv_Reg=Environment("Sheet_IndividualRegister"); 
				  int Indiv_Reg_row=RC.getLastrowno(Indiv_Reg); 
				  int Indiv_Reg_col=RC.getLastcolmno(Indiv_Reg); 
				  String[] Indiv_Reg_ele=new String[Indiv_Reg_col]; 
				  for (int Indiv_Reg_index = 1; Indiv_Reg_index < RC.getLastrowno(Indiv_Reg); Indiv_Reg_index++) 
				  {
					  if(firsttimeRegister!=1)
					  {
						  //after getting referral link register as individual
						  if(S26.equals(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Indiv_Reg, "RF_ID"),Indiv_Reg)))
						  {
							  System.out.println("Matches ID to Register");
							  System.out.println(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Indiv_Reg, "RF_ID"),Indiv_Reg)); 
							  //based on j value get the row data and do Adding Users
						   
							  for(int Indiv_Reg_ind=0;Indiv_Reg_ind<Indiv_Reg_col;Indiv_Reg_ind++) 
							  {
								  Indiv_Reg_ele[Indiv_Reg_ind]=RC.getStringCellData(Indiv_Reg_index, Indiv_Reg_ind, Indiv_Reg);
								  System.out.println(Indiv_Reg_ele[Indiv_Reg_ind]); //call login as company method, pass array values
			  
				  
							  }
							  refer_friends.add(Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "FirstName")]+" "+
									  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "LastName")]);
						  	  func_cases.candidateRegistration(driver, firsttimeRegister, 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "FirstName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "LastName")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "EmailID")], 
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ContactNumber")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Password")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "ConfirmPassword")],
								  Indiv_Reg_ele[RC.Current_Coulumn_Number(Indiv_Reg, "Captcha")]);
						  	  func_cases.CompanyLogout(driver);
						  
						  }
					  }
				  }
				  
				  if(firsttimeRegister!=1)
				  {
				  	for (int Org_Reg_index = 1; Org_Reg_index < RC.getLastrowno(Org_Reg); Org_Reg_index++) 
				  	{ 
					  System.out.println("for Loop" );
					  System.out.println(S15);
					  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "RF_ID"), Org_Reg)); 
					  if(S26.equals(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "RF_ID"),Org_Reg)))
					  {
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "RF_ID"),Org_Reg)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Org_Reg_ind=0;Org_Reg_ind<Org_Reg_col;Org_Reg_ind++) 
						  {
							  Org_Reg_ele[Org_Reg_ind]=RC.getStringCellData(Org_Reg_index, Org_Reg_ind, Org_Reg);
							  System.out.println(Org_Reg_ele[Org_Reg_ind]); //call login as company method, pass array values
						  }
						  refer_friends.add(Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")]);
							  func_cases.companyRegistration(driver, firsttimeRegister, 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
									  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
							  func_cases.CompanyLogout(driver);
						  
					  }
				  	}
				  }
			}
//==================start			
			//Login Company
			/*if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
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
				  //Validating Users registered with refer link in MyReferral List of user who are sharing refer link
				  if(S26!="")
				  {
				  	  int refered_size = refer_friends.size();
					  String[] refered=new String[refered_size];
				  
					   
					  for(int i=0;i<refered_size;i++)
					  {
						  func_cases.validateReferedFriends(driver, refer_friends.get(i));
					  }
				  }
				      List<String> list = new ArrayList<>();
				      list.add("Name");
				      list.add("Last");
				      String[] refered=new String[list.size()];
				      
					  for(int i=0;i<list.size();i++)
					  {
						  refered[i]=list.get(i);
						  System.out.println(refered[i]);
						  func_cases.validateReferedFriends(driver, refered[i]);
					  }
			}*/
//=============end			
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
								  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyImage")],
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
						  		func_cases.validatePublishedSurveyData(driver, 
						  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Name")], 
						  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Name")], 
						  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")], 
						  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Description")], 
						  				Question_NUmber);
						  		func_cases.checkSurveyTemplates(driver,
						  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "TemplateName")]);
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
				  			String update =func_cases.updateSurveyName(driver, update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SearchWithSurveyName")],
				  				  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Name")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Notes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Name")], 
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Notes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_SubNotes")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SurveyName")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")],
								  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]);
				  			if(update.equals("true"))
				  			{
				  				if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
				  				{
				  					String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
								  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
								  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
								  String[] create_Que_ele=new String[create_Que_col]; 
								  int Que_No=func_cases.createdque(driver);
								  System.out.println("Previously Created Question : "+Que_No);
								  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
								  { 
									 
									  System.out.println("QueNO: "+Que_No);
									  System.out.println("for Loop" );
									  System.out.println(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]);
									  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
									  if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
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
										  func_cases.AddQuestionto_CreateSurvey(driver, 
												  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
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
										  
										  
									  }
								  }
								  func_cases.update_SurveyAftrAdingQue(driver);
				  				}
				  			}
						  
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
										  String update=func_cases.updateSurveyName(driver,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Name")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Categeory_Notes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Name")], 
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_Notes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Group_SubNotes")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "SurveyName")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "Description")],
												  update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]);
										  if(update.equals("true"))
										  {
											  if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
								  				{
								  				
												  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
												  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
												  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
												  String[] create_Que_ele=new String[create_Que_col]; 
												  int Que_No=func_cases.createdque(driver);
												  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
												  { 
													 
													  System.out.println("QueNO: "+Que_No);
													  System.out.println("for Loop" );
													  System.out.println(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]);
													  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
													  if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
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
														  func_cases.AddQuestionto_CreateSurvey(driver, 
																  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
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
														  
													  }
												  }
												  func_cases.update_SurveyAftrAdingQue(driver);
								  				}
										  }
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
				  /*if(S7!="")
				  {
					  func_cases.CompanyLogout(driver);
				  }*/
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
//=========================start	
				if(S4!=""&&S6!="")//create survey & share for surveyname
				  {
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
						  func_cases.enduser_Login(driver, EndUser_ele[RC.Current_Coulumn_Number(EndUser, "WebLink")],
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "EndUserEmail")],
								  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "password")]);
//added from else block , after check delete else block===============						  
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
						  				
						  				System.out.println("Based on this value need to stop filling the response by enduser"+
						  						create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AnswerByEndUser")]);
						  				if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AnswerByEndUser")]!="")
						  				{
						  					if(!surveylinkURL.contains("Error"))
						  					{
						  						String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
						  						int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
						  						int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
						  						String[] create_Que_ele=new String[create_Que_col];
						  						int Que_No=0;
						  						int k_val=0;
						  						for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
						  						{ 
						  							k_val=k;
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
						  								if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "FillAnswer")]!="")
						  								{
						  									func_cases.answer_basedonQueType(driver, Que_No,
						  										create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")]);
						  								}
						  							}
						  							
						  						}
//if update enabled , and added que , then answering by end user also consider the que types of updated que		
						  						/*System.out.println("CreateSurvey_Questions :"+k_val);
						  						System.out.println("CreateSurvey_Questions :"+create_Que_row);*/
						  						if(S5!="")
						  						{
						  							String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
						  						  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
						  						  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
						  						  	String[] update_suvey_ele=new String[update_suveyname_col]; 
						  						  	
						  						  	String CreateSur_Questions=Environment("Sheet_CreateSurvey_Questions");
							  						int create_Q_row=RC.getLastrowno(CreateSurvey_Questions); 
							  						int create_Q_col=RC.getLastcolmno(CreateSurvey_Questions); 
							  						String[] create_Q_ele=new String[create_Que_col];
							  						
						  						  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
						  						  	{ 
						  						  		System.out.println("for Loop" );
						  						  		System.out.println(S5);
						  						  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
						  						  		if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")].equals(
						  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
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
						  						  			if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
						  						  			{
						  						  				for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
						  						  				{
						  						  					if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
									  								.equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
						  						  					{
						  						  						System.out.println("answer to Que in create list : "+Que_No);
						  						  						Que_No++;	
						  						  						System.out.println("answer to Que in Update list : "+Que_No);
						  						  						System.out.println("Matches ID to Register");
						  						  						System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions));
						  						  						for(int create_que=0;create_que<create_Que_col;create_que++) 
						  						  						{
														  
						  						  							create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
						  						  							System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
														 
											  
						  						  						} 
						  						  						if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "FillAnswer")]!="")
						  						  						{
						  						  							func_cases.answer_basedonQueType(driver, Que_No, 
									  										create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")]);
						  						  						}
						  						  					}
						  						  				}
						  						  			}
						  						  		}
						  						  	}
						  						}
//==========================================================END								  						
						  						i++;
						  							if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "SubmitResponse")]!="")
						  						{
						  							func_cases.saveSurveySubmit(driver,Que_No);
						  						}
						  						Thread.sleep(3000);
						  						driver.close();
						  						Thread.sleep(3000);
						  						driver.switchTo().window(oldTab);
						  					}
						  				}
						  				else
						  				{
						  					i++;
						  					driver.close();
					  						Thread.sleep(3000);
					  						driver.switchTo().window(oldTab);
						  				}
						  					
					  				
						  			}
						  		}
					  		if(S4!=""&&S6!="")
								func_cases.CompanyLogout(driver);
					  		}
					  
				  		}
				  	}
					
////added from else block , after check delete else block===============	END**********************
					  		
//=========================end						  
						// if Direct share survey happend, 
					else
					{	  
						if(S4==""&&S6!="")
						  {
							    /*get que types  based on surveyname , in share survey sheet search with survey name , 
							   and click view and get no of que & get answer types */
							  int share_no=1;
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
									  String currentURL = driver.getCurrentUrl();
									  if(currentURL.contains("/Account/Login"))
									  {
										  func_cases.companyLogin(driver, currentUsername, currentPassword);
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
										  String[] que_Type = new String[Total_Que];
										  que_type_IndividualShare=new String[Total_Que];
										  /*if(view_details[1]=="1")  {  Total_Que=1; }*/
										 //based on no of que, get que type 
										  for(int i=1; i <= Total_Que ; i++)
										  {
											  /*queType_ele[i-1] = func_cases.viewsurvey_QueType(driver,i);
											  System.out.println( queType_ele[i-1]);*/
											  
											  queType_ele[i-1]=func_cases.viewSurvey_GetQueText(driver, i);
											  String quetext=queType_ele[i-1];
											  System.out.println("Question "+i+":"+quetext);
											  String questiontype = DB_QueType.Db_qtype_Survey(share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")], quetext);
											  System.out.println( "Que Type "+questiontype+" and i value "+i);
											  int k=i-1;
											  que_Type[k]=questiontype;
											  que_type_IndividualShare[k]=questiontype;
										  }
										  
										  func_cases.CompanyLogout(driver);
										  
										  String EndUser1=Environment("Sheet_EndUser"); 
										  int EndUser_row1=RC.getLastrowno(EndUser1); 
										  int EndUser_col1=RC.getLastcolmno(EndUser1); 
										  String[] EndUser_ele1=new String[EndUser_col1]; 
										  for (int enduser_index1 = 1; enduser_index1 < RC.getLastrowno(EndUser1); enduser_index1++) 
										  { 
											  System.out.println("for Loop" );
											  System.out.println(S7);
											  System.out.println(RC.getStringCellData(enduser_index1, RC.Current_Coulumn_Number(EndUser1, "EndUserID"), EndUser1)); 
											  if (S7.equals(RC.getStringCellData(enduser_index1, RC.Current_Coulumn_Number(EndUser1, "EndUserID"),EndUser1)))
												  // Adduser contains company email_id at 1st column  for validation
											  { 
												  System.out.println("Matches ID to Register");
												  System.out.println(RC.getStringCellData(enduser_index1, RC.Current_Coulumn_Number(EndUser1, "EndUserID"),EndUser1)); 
												  //based on j value get the row data and do Adding Users
												   
												  for(int EndUser_ind1=0;EndUser_ind1<EndUser_col1;EndUser_ind1++) 
												  {
													  EndUser_ele1[EndUser_ind1]=RC.getStringCellData(enduser_index1, EndUser_ind1, EndUser1);
													  System.out.println(EndUser_ele1[EndUser_ind1]); //call login as company method, pass array values
									  
										  
												  }
												  func_cases.enduser_Login(driver, EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "WebLink")],
														  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "EndUserEmail")],
														  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "password")]);
												  //==quizpart start
												  String  oldTab1 = driver.getWindowHandle();
												  Thread.sleep(3000);
									  			  newTab_robot newTab = new newTab_robot();
									  			  Thread.sleep(3000);
									  			  
									  			  Set<String> set = new HashSet<String>(driver.getWindowHandles());
									  			  for(String tab : set)
									  			  {
													System.out.println("window :"+tab);
									  			  }
									  			  set.remove(oldTab1);
									  			  driver.switchTo().window(set.iterator().next());
									  			  
												  
									  			  //switch to new tab to answer survey, now get the link from excel
									  			  
									  			  File src = new File("SurveyLink_Excel\\Survey_Links.xlsx");
									  			  FileInputStream fis;
									  			  fis = new FileInputStream(src);
									  			  XSSFWorkbook wb = new XSSFWorkbook(fis);
									  			  XSSFSheet sheet1 = wb.getSheetAt(0);
									  			  String data0 ;
									  			  if(share_no<=share)
									  			  {
									  				  data0 = sheet1.getRow(share_no).getCell(0).getStringCellValue();
									  				  System.out.println("Open Survey Link :"+share_no+data0);
									  				  wb.close();
									  				  driver.get(data0);//surveylink opened , now you have to fill that response as an end user
									  			  }
									  			  //String surveylinkURL = driver.getCurrentUrl();
												  
												  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "FillAnswer")]!="")
												  {
													  for(int num=0;num<Total_Que;num++)
													  {
													  	/*func_cases.answerQuiz(driver,que_Type[num],num+1,Total_Que);
													  	func_cases.answerQuiz(driver, que_type, curentque, totque);*/
													  	func_cases.answer_basedonQueType(driver, num+1, que_Type[num]);
													  }
												  }
												  
												  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SaveResponse")]!="")
												  {
													  
												  }
												  
												  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SubmitResponse")]!="")
												  {
													  func_cases.saveSurveySubmit(driver,Total_Que);
												  }
												  driver.close();
												  driver.switchTo().window(oldTab);
										  
												  func_cases.CompanyLogout(driver);
											  }
											  if(enduser_index1+1 == RC.getLastrowno(EndUser1))
											  {
												  share_no++;
											  }
											  //====quiz end part
												  
												  /*if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SubmitResponse")]!="")
												  {
													  func_cases.saveSurveySubmit(driver,Total_Que);
												  }
												  driver.close();
												  Thread.sleep(3000);
												  driver.switchTo().window(oldTab);*/
											  }
										  }
									  }
						  
								  }
							  }
						  }	  
						  /*else              //if created and shared survey fill
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
								  				
								  				System.out.println("Based on this value need to stop filling the response by enduser"+
								  						create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AnswerByEndUser")]);
								  				if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AnswerByEndUser")]!="")
								  				{
								  					if(!surveylinkURL.contains("Error"))
								  					{
								  						String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
								  						int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
								  						int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
								  						String[] create_Que_ele=new String[create_Que_col];
								  						int Que_No=0;
								  						int k_val=0;
								  						for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
								  						{ 
								  							k_val=k;
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
								  								if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "FillAnswer")]!="")
								  								{
								  									func_cases.answer_basedonQueType(driver, Que_No,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")], 
								  										create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")],
								  										create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
								  								}
								  							}
								  							
								  						}
//if update enabled , and added que , then answering by end user also consider the que types of updated que		
								  						System.out.println("CreateSurvey_Questions :"+k_val);
								  						System.out.println("CreateSurvey_Questions :"+create_Que_row);
								  						if(S5!="")
								  						{
								  							String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
								  						  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
								  						  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
								  						  	String[] update_suvey_ele=new String[update_suveyname_col]; 
								  						  	
								  						  	String CreateSur_Questions=Environment("Sheet_CreateSurvey_Questions");
									  						int create_Q_row=RC.getLastrowno(CreateSurvey_Questions); 
									  						int create_Q_col=RC.getLastcolmno(CreateSurvey_Questions); 
									  						String[] create_Q_ele=new String[create_Que_col];
									  						
								  						  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
								  						  	{ 
								  						  		System.out.println("for Loop" );
								  						  		System.out.println(S5);
								  						  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
								  						  		if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")].equals(
								  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
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
								  						  			if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
								  						  			{
								  						  				for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
								  						  				{
								  						  					if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
											  								.equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)))
								  						  					{
								  						  						System.out.println("answer to Que in create list : "+Que_No);
								  						  						Que_No++;	
								  						  						System.out.println("answer to Que in Update list : "+Que_No);
								  						  						System.out.println("Matches ID to Register");
								  						  						System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions));
								  						  						for(int create_que=0;create_que<create_Que_col;create_que++) 
								  						  						{
																  
								  						  							create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
								  						  							System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
																 
													  
								  						  						} 
								  						  						if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "FillAnswer")]!="")
								  						  						{
								  						  							func_cases.answer_basedonQueType(driver, Que_No,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")], 
											  										create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")],
											  										create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
								  						  						}
								  						  					}
								  						  				}
								  						  			}
								  						  		}
								  						  	}
								  						}
//==========================================================END								  						
								  						i++;
								  							if(EndUser_ele[RC.Current_Coulumn_Number(EndUser, "SubmitResponse")]!="")
								  						{
								  							func_cases.saveSurveySubmit(driver,Que_No);
								  						}
								  						Thread.sleep(3000);
								  						driver.close();
								  						Thread.sleep(3000);
								  						driver.switchTo().window(oldTab);
								  					}
								  				}
								  				else
								  				{
								  					i++;
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
					  
					}*/
				
						  
						  
					  
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
				if(S3!=""&&S4==""&&S6==""&&S7=="")
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
						  //
						  //call surveyResponse Method
						  AnsFromResponse =func_cases.surveyresponse_ofEndUsers(driver, 
								  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")],
								  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "Enduser_Name")]);
						  
						  for(int response=0;response<AnsFromResponse.size();response++)
						  {
							  System.out.println("Response Answers "+AnsFromResponse.get(response));
						  }
						  List<String> list = new ArrayList<>();
						  list.add(Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")]);
						  int Tot_Que=0;
						  for(int i=1;i<=que_type_IndividualShare.length;i++)
						  {
							  Tot_Que++;
						  }
						  String strI = String.valueOf(Tot_Que);
						  list.add(strI);
						  for(int que_typ=1;que_typ<=que_type_IndividualShare.length;que_typ++)
						  {
							  String Que_type=que_type_IndividualShare[que_typ];
							  System.out.println("Validating Ans:Question type while you sharing:");
						  String Survey_Answers=Environment("Sheet_Survey_Answers"); 
						  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
						  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
						  String[] Survey_Answers_ele=new String[Survey_Answers_col];
						  
						  for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
						  { 
							  System.out.println("for Loop" );
							  System.out.println(Que_type);
							  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers));
							  int times=0;
							  if (Que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)))
							  			// Adduser contains company email_id at 1st column  for validation
							  {
								  
								  
								  		System.out.println("Matches ID to Register");
								  		System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)); 
								  //based on j value get the row data and do Adding Users
								   
								  		for(int Survey_Answers_Ind=0;Survey_Answers_Ind<Survey_Answers_col;Survey_Answers_Ind++) 
								  		{
								  			System.out.println(times);
								  			times++;
									  		Survey_Answers_ele[Survey_Answers_Ind]=RC.getStringCellData(Survey_Answers_Index, Survey_Answers_Ind, Survey_Answers);
									  		System.out.println("QueId matches with Createsurvey, and the values are"+Survey_Answers_ele[Survey_Answers_Ind]);
								  		}
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
								  			int cht_tot=0;
								  		
//listaddremove class=====================================================================													  		
								  			System.out.println("Before adding elements to list "+list.size());
								  		
								  			int old_list =list.size();
										 	int temp_size;
								//checkbox elements adding to the list		 
										 	for(int i=0;i<10;i++)
									  		{
											 	
											 	if(options[i]!="")
									  			{
											 		list.add(options[i]);
											 		for(int tem=0;tem<list.size();tem++)
											 		{
											 			System.out.println("Adding checkAns to list "+list.get(tem));
											 		}
											 		if(cht_tot>=1)
											 		{
											 			temp_size=list.size();
											 			System.out.println(temp_size);
											 			String temp = list.get(temp_size-1);
											 			System.out.println(temp);
											 			list.remove(temp_size-1);
											 			list.add(","+temp);
											 			for(int tem=0;tem<list.size();tem++)
												 		{
												 			System.out.println("Adding , to second checkAns onwards  to list "+list.get(tem));
												 		}
											 		}
									  				cht_tot++;
									  			}
									  		}
										 	int new_list = list.size();
								//after adding check elements get the size		 
										 	System.out.println(new_list);
										 
										 	int list_size =  new_list-old_list;
										 
										 	String[] arr = new String[list_size];
										 	System.out.println("string array size : "+arr.length);
										 	for(int ind=arr.length-1,j=new_list-1;ind>=0;ind--,j--)
										 	{
											 	arr[ind]=list.get(j);
//added comment on 3-Nov-2017																			 
											 	/*if(ind!=0)
											 	{
												 	arr[ind]=","+arr[ind];
											 	}*/
//End added comment on 3-Nov-2017																				
										 	}
										 
										 	for(int arr_ind = 0,list_rem=new_list-1;arr_ind<arr.length;arr_ind++,list_rem--)
										 	{
											 	System.out.println(arr[arr_ind]);
											 //removing newly added list elements			 
											 	list.remove(list_rem);
										 	}
										 	System.out.println("After concatenation removing list elements "+list.size());
										 
										 	if(old_list==list.size())
										 	{
											 	for(int arr_siz=1;arr_siz<arr.length;arr_siz++)
											 	{
												 	//int arr_temp=arr_siz+1;
												 	arr[0]=arr[0].concat(arr[arr_siz]);
											 	}
											 	System.out.println("the elemnet to be added to the list is "+arr[0]);
											 	list.add(arr[0]);
											 
											 	System.out.println(list.size());
										 	}
										 	for(int list_disp = 0;list_disp<list.size();list_disp++)
										 	{
											 	System.out.println(list.get(list_disp));
										 	}
//================================================================															 
								  			break;
								  		
								  			case "Date": System.out.println("Date to be enetered");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")]);
								  			break;
								  		
								  			case "File Upload":System.out.println("file to be uploaded");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "File Upload")]);
								  			break;
								  			
								  			case "Dropdown":System.out.println("dropdown to be clicked");
								  			System.out.println("Before adding elements to list "+list.size());
								  			int drop_tot=0;
								  			int beforDroplist =list.size();
								  			int list_withdrop = list.size()+1;
											for(int i=0;i<10;i++)
								  			{
										 	
										 		if(options[i]!="")
								  				{
										 			list.add(options[i]);
										 			drop_tot++;
								  				}
								  			}
											int newDrop_list = list.size();
											System.out.println(newDrop_list);
											list.add(beforDroplist,list.get(newDrop_list-1));
											list.remove(newDrop_list);
											System.out.println("after adding drop elem List size "+list.size());
											int list_incl_drop =list.size();
										
											System.out.println(list.get(beforDroplist));//list.get(5)
										
											System.out.println(list_incl_drop);
										
											for(int i=0;i<list_incl_drop;i++)//i=0;i<11;i++
											{
												System.out.println(list.get(i));
												//list.remove(i);
											}
											for(int i=list_incl_drop-1;i>=list_withdrop;i--)
											{
												System.out.println(list.get(i));
												list.remove(i);
											}
											System.out.println(list.size());
										 
											for(int i=0;i<list.size();i++)
											{
												System.out.println(list.get(i));
											}
								  			break;
								  		
								  			case "Number":System.out.println("Number to be clicked");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Number")]);
								  			break;
								  		
								  				case "Radio Button":System.out.println("radio button to be clicked");
								  				int radio_tot=0;
								  				int beforRadiolist =list.size();
								  				int list_withRadio = list.size()+1;
										 		for(int i=0;i<10;i++)
									  			{
											 	
											 		if(options[i]!="")
									  				{
											 			list.add(options[i]);
											 			radio_tot++;
									  				}
									  			}
										 	int newRadio_list = list.size();
										 	System.out.println(newRadio_list);
										 	list.add(beforRadiolist,list.get(newRadio_list-1));
										 	list.remove(newRadio_list);
										 	System.out.println("after adding drop elem List size "+list.size());
										 	int list_incl_radio =list.size();
											
										 	System.out.println(list.get(beforRadiolist));//list.get(5)
											
										 	System.out.println(list_incl_radio);
											
										 	for(int i=0;i<list_incl_radio;i++)//i=0;i<11;i++
										 	{
											 	System.out.println(list.get(i));
											 	//list.remove(i);
										 	}
										 	for(int i=list_incl_radio-1;i>=list_withRadio;i--)
										 	{
											 System.out.println(list.get(i));
												list.remove(i);
										 	}
										 	System.out.println(list.size());
											 
										 	for(int i=0;i<list.size();i++)
										 	{
											 	System.out.println(list.get(i));
										 	}
										 	break;
								  		
								  			case "Scale / Rate":System.out.println("rating to be entered");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Scale / Rate")]);
								  			break;
								  		
								  			case "Single Line Text":System.out.println("single line text to be eneterd");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Single Line Text")]);
								  			break;
								  		
								  			case "Text Area":System.out.println("text area to be entered");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "TextArea")]);
								  			break;
								  		
								  			case "Yes or No":System.out.println("Y/N to be selected");
								  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")]);
								  			break;
								  	}
							  	
							  }
							  else
							  {
								  /*if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")].equals(
										  RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueID"),Survey_Answers)))
								  {
									  System.out.println("QuestionID in createSurvey Sheet Is not matching with SUrveyAns QUEID");
									  Assert.fail("Add Answers For the QuestionID "+create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]+" in SurveyAnswers sheet");
									  
									  
								  }*/
							  }
						  	  
						  }
					    }
						  
					  }
				  }
				}
				else
					if(S3!=""&&S4!=""&&S6!="")
					{
						String Tot_Que;
						  String create_suvey=Environment("Sheet_Survey_createSurvey"); 
						  int create_suvey_row=RC.getLastrowno(create_suvey); 
						  int create_suvey_col=RC.getLastcolmno(create_suvey); 
						  String[] create_suvey_ele=new String[create_suvey_col]; 
						  int Question_NUmber;
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
								  Tot_Que = null;
								  Question_NUmber=0;
								  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]!="")
								  {
								  		String Que_ID=create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")];
								  		String CreateSurvey_Questions_1=Environment("Sheet_CreateSurvey_Questions");
								  		int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions_1); 
								  		String[] create_Que_ele=new String[create_Que_col]; 
								  
								  		
								  		for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions_1); k++) 
								  		{
								  			if (Que_ID
											  .equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions_1, "QuestioID"), CreateSurvey_Questions_1)))
								  			{
										  
								  				Question_NUmber++;
										  
								  			}
								  		}
								  		if(S5=="")
								  		Tot_Que=String.valueOf(Question_NUmber);
								  		else
								  		{
								  			if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")]=="")
								  				Tot_Que=String.valueOf(Question_NUmber);
								  		}
										 
								  }
								  if(S5!="")
			  						{
			  							String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
			  						  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
			  						  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
			  						  	String[] update_suvey_ele=new String[update_suveyname_col]; 
			  						  	
			  						  	String CreateSur_Questions=Environment("Sheet_CreateSurvey_Questions");
				  						int create_Q_row=RC.getLastrowno(CreateSur_Questions); 
				  						int create_Q_col=RC.getLastcolmno(CreateSur_Questions); 
				  						String[] create_Q_ele=new String[create_Q_col];
				  						
			  						  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
			  						  	{ 
			  						  		System.out.println("for Loop" );
			  						  		System.out.println(S5);
			  						  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
			  						  		if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")].equals(
			  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
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
			  						  			if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
			  						  			{
			  						  				for (int k = 1; k < RC.getLastrowno(CreateSur_Questions); k++) 
			  						  				{
			  						  					if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
						  								.equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSur_Questions, "QuestioID"), CreateSur_Questions)))
			  						  					{
			  						  						System.out.println("answer to Que in create list : "+Question_NUmber);
			  						  						Question_NUmber++;
			  						  					}
			  						  				}
			  						  				Tot_Que=String.valueOf(Question_NUmber);
			  						  			}
			  						  			
			  						  		}
			  						  	}
			  						}
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
										  if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "ValidateEndUser_Response")].equals(
												  RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "createSurveyControl"),Response_Survey)))
										  {
											  System.out.println("Matches ID to Register");
											  System.out.println(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "ResponseID"),Response_Survey)); 
											  //based on j value get the row data and do Adding Users
										   
											  for(int Response_Survey_ind=0;Response_Survey_ind<Response_Survey_col;Response_Survey_ind++) 
											  {
												  Response_Survey_ele[Response_Survey_ind]=RC.getStringCellData(Response_Survey_index, Response_Survey_ind, Response_Survey);
												  System.out.println(Response_Survey_ele[Response_Survey_ind]); //call login as company method, pass array values
							  
								  
											  }
//answers from surveyresponse view	
											  AnsFromResponse= func_cases.surveyresponse_ofEndUsers(driver, 
													  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")],
													  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "Enduser_Name")]);
											  for(int response=0;response<AnsFromResponse.size();response++)
											  {
												  System.out.println("Response Answers "+AnsFromResponse.get(response));
											  }
//validate the answers in List with Ans entered by enduser matched or not ...."list.get(i);"
											  List<String> list = new ArrayList<>();
											  list.add(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")]);
											  list.add(Tot_Que);
//questions at the time of created survey ====================================================================================															  
											  if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]!="")
												{
												  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
												  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
												  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
												  String[] create_Que_ele=new String[create_Que_col]; 
												  int Que_No=1;
								  
												  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
												  { 
//adding here 													  
													  String Survey_Answers=Environment("Sheet_Survey_Answers"); 
													  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
													  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
													  String[] Survey_Answers_ele=new String[Survey_Answers_col]; 
													 
													  System.out.println("QueNO: "+Que_No);
													  System.out.println("for Loop");
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
														 
														  /*String Survey_Answers=Environment("Sheet_Survey_Answers"); 
														  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
														  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
														  String[] Survey_Answers_ele=new String[Survey_Answers_col];*/
														  for(int create_que=0;create_que<create_Que_col;create_que++) 
														  { 
															  create_Que_ele[create_que]=RC.getStringCellData(k, create_que, CreateSurvey_Questions);
															  System.out.println(create_Que_ele[create_que]); //call login as company method, pass array values
															
														  } 
														  String Que_type = create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "AnswerType")];
														  
														  
														  for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
														  { 
															  System.out.println("for Loop" );
															  System.out.println(Que_type);
															  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers));
															  int times=0;
															  if (Que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)))
															  			// Adduser contains company email_id at 1st column  for validation
															  {
																  
																  
																  		System.out.println("Matches ID to Register");
																  		System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)); 
																  //based on j value get the row data and do Adding Users
																   
																  		for(int Survey_Answers_Ind=0;Survey_Answers_Ind<Survey_Answers_col;Survey_Answers_Ind++) 
																  		{
																  			System.out.println(times);
																  			times++;
																	  		Survey_Answers_ele[Survey_Answers_Ind]=RC.getStringCellData(Survey_Answers_Index, Survey_Answers_Ind, Survey_Answers);
																	  		System.out.println("QueId matches with Createsurvey, and the values are"+Survey_Answers_ele[Survey_Answers_Ind]);
																  		}
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
																  			int cht_tot=0;
																  		
			//listaddremove class=====================================================================													  		
																  			System.out.println("Before adding elements to list "+list.size());
																  		
																  			int old_list =list.size();
																		 	int temp_size;
																//checkbox elements adding to the list		 
																		 	for(int i=0;i<10;i++)
																	  		{
																			 	
																			 	if(options[i]!="")
																	  			{
																			 		list.add(options[i]);
																			 		for(int tem=0;tem<list.size();tem++)
																			 		{
																			 			System.out.println("Adding checkAns to list "+list.get(tem));
																			 		}
																			 		if(cht_tot>=1)
																			 		{
																			 			temp_size=list.size();
																			 			System.out.println(temp_size);
																			 			String temp = list.get(temp_size-1);
																			 			System.out.println(temp);
																			 			list.remove(temp_size-1);
																			 			list.add(","+temp);
																			 			for(int tem=0;tem<list.size();tem++)
																				 		{
																				 			System.out.println("Adding , to second checkAns onwards  to list "+list.get(tem));
																				 		}
																			 		}
																	  				cht_tot++;
																	  			}
																	  		}
																		 	int new_list = list.size();
																//after adding check elements get the size		 
																		 	System.out.println(new_list);
																		 
																		 	int list_size =  new_list-old_list;
																		 
																		 	String[] arr = new String[list_size];
																		 	System.out.println("string array size : "+arr.length);
																		 	for(int ind=arr.length-1,j=new_list-1;ind>=0;ind--,j--)
																		 	{
																			 	arr[ind]=list.get(j);
//added comment on 3-Nov-2017																			 
																			 	/*if(ind!=0)
																			 	{
																				 	arr[ind]=","+arr[ind];
																			 	}*/
//End added comment on 3-Nov-2017																				
																		 	}
																		 
																		 	for(int arr_ind = 0,list_rem=new_list-1;arr_ind<arr.length;arr_ind++,list_rem--)
																		 	{
																			 	System.out.println(arr[arr_ind]);
																			 //removing newly added list elements			 
																			 	list.remove(list_rem);
																		 	}
																		 	System.out.println("After concatenation removing list elements "+list.size());
																		 
																		 	if(old_list==list.size())
																		 	{
																			 	for(int arr_siz=1;arr_siz<arr.length;arr_siz++)
																			 	{
																				 	//int arr_temp=arr_siz+1;
																				 	arr[0]=arr[0].concat(arr[arr_siz]);
																			 	}
																			 	System.out.println("the elemnet to be added to the list is "+arr[0]);
																			 	list.add(arr[0]);
																			 
																			 	System.out.println(list.size());
																		 	}
																		 	for(int list_disp = 0;list_disp<list.size();list_disp++)
																		 	{
																			 	System.out.println(list.get(list_disp));
																		 	}
			//================================================================															 
																  			break;
																  		
																  			case "Date": System.out.println("Date to be enetered");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")]);
																  			break;
																  		
																  			case "File Upload":System.out.println("file to be uploaded");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "File Upload")]);
																  			break;
																  			
																  			case "Dropdown":System.out.println("dropdown to be clicked");
																  			System.out.println("Before adding elements to list "+list.size());
																  			int drop_tot=0;
																  			int beforDroplist =list.size();
																  			int list_withdrop = list.size()+1;
																			for(int i=0;i<10;i++)
																  			{
																		 	
																		 		if(options[i]!="")
																  				{
																		 			list.add(options[i]);
																		 			drop_tot++;
																  				}
																  			}
																			int newDrop_list = list.size();
																			System.out.println(newDrop_list);
																			list.add(beforDroplist,list.get(newDrop_list-1));
																			list.remove(newDrop_list);
																			System.out.println("after adding drop elem List size "+list.size());
																			int list_incl_drop =list.size();
																		
																			System.out.println(list.get(beforDroplist));//list.get(5)
																		
																			System.out.println(list_incl_drop);
																		
																			for(int i=0;i<list_incl_drop;i++)//i=0;i<11;i++
																			{
																				System.out.println(list.get(i));
																				//list.remove(i);
																			}
																			for(int i=list_incl_drop-1;i>=list_withdrop;i--)
																			{
																				System.out.println(list.get(i));
																				list.remove(i);
																			}
																			System.out.println(list.size());
																		 
																			for(int i=0;i<list.size();i++)
																			{
																				System.out.println(list.get(i));
																			}
																  			break;
																  		
																  			case "Number":System.out.println("Number to be clicked");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Number")]);
																  			break;
																  		
																  				case "Radio Button":System.out.println("radio button to be clicked");
																  				int radio_tot=0;
																  				int beforRadiolist =list.size();
																  				int list_withRadio = list.size()+1;
																		 		for(int i=0;i<10;i++)
																	  			{
																			 	
																			 		if(options[i]!="")
																	  				{
																			 			list.add(options[i]);
																			 			radio_tot++;
																	  				}
																	  			}
																		 	int newRadio_list = list.size();
																		 	System.out.println(newRadio_list);
																		 	list.add(beforRadiolist,list.get(newRadio_list-1));
																		 	list.remove(newRadio_list);
																		 	System.out.println("after adding drop elem List size "+list.size());
																		 	int list_incl_radio =list.size();
																			
																		 	System.out.println(list.get(beforRadiolist));//list.get(5)
																			
																		 	System.out.println(list_incl_radio);
																			
																		 	for(int i=0;i<list_incl_radio;i++)//i=0;i<11;i++
																		 	{
																			 	System.out.println(list.get(i));
																			 	//list.remove(i);
																		 	}
																		 	for(int i=list_incl_radio-1;i>=list_withRadio;i--)
																		 	{
																			 System.out.println(list.get(i));
																				list.remove(i);
																		 	}
																		 	System.out.println(list.size());
																			 
																		 	for(int i=0;i<list.size();i++)
																		 	{
																			 	System.out.println(list.get(i));
																		 	}
																		 	break;
																  		
																  			case "Scale / Rate":System.out.println("rating to be entered");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Scale / Rate")]);
																  			break;
																  		
																  			case "Single Line Text":System.out.println("single line text to be eneterd");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Single Line Text")]);
																  			break;
																  		
																  			case "Text Area":System.out.println("text area to be entered");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "TextArea")]);
																  			break;
																  		
																  			case "Yes or No":System.out.println("Y/N to be selected");
																  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")]);
																  			break;
																  	}
															  	
															  }
															  else
															  {
																  /*if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")].equals(
																		  RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueID"),Survey_Answers)))
																  {
																	  System.out.println("QuestionID in createSurvey Sheet Is not matching with SUrveyAns QUEID");
																	  Assert.fail("Add Answers For the QuestionID "+create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]+" in SurveyAnswers sheet");
																	  
																	  
																  }*/
															  }
														  	  
														  }
															
													  } 
												  }
												  
								
												}
//====================End Questions at the time of created survey
//Questions at the time of Survey Update========================================											  
											  if(S5!="")
						  						{
						  							String Update_suveyName=Environment("Sheet_UpdateSurveyName"); 
						  						  	int update_suveyname_row=RC.getLastrowno(Update_suveyName); 
						  						  	int update_suveyname_col=RC.getLastcolmno(Update_suveyName); 
						  						  	String[] update_suvey_ele=new String[update_suveyname_col]; 
						  						  	
						  						  	String CreateSur_Questions=Environment("Sheet_CreateSurvey_Questions");
							  						int create_Q_row=RC.getLastrowno(CreateSur_Questions); 
							  						int create_Q_col=RC.getLastcolmno(CreateSur_Questions); 
							  						String[] create_Q_ele=new String[create_Q_col];
							  						
							  						String Survey_Answers=Environment("Sheet_Survey_Answers"); 
													  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
													  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
													  String[] Survey_Answers_ele=new String[Survey_Answers_col];
							  						
						  						  	for (int update_surveyname_index = 1; update_surveyname_index < RC.getLastrowno(Update_suveyName); update_surveyname_index++) 
						  						  	{ 
						  						  		System.out.println("for Loop" );
						  						  		System.out.println(S5);
						  						  		System.out.println(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"), Update_suveyName)); 
						  						  		if (create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "update_surveydetails")].equals(
						  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName)))
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
						  						  			if(update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]!="")
						  						  			{
						  						  				for (int k = 1; k < RC.getLastrowno(CreateSur_Questions); k++) 
						  						  				{
						  						  					if (update_suvey_ele[RC.Current_Coulumn_Number(Update_suveyName, "AddQuestions")]
									  								.equals(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSur_Questions, "QuestioID"), CreateSur_Questions)))
						  						  					{
						  						  						for(int create_que=0;create_que<create_Q_col;create_que++) 
						  						  						{ 
						  						  						create_Q_ele[create_que]=RC.getStringCellData(k, create_que, CreateSur_Questions);
																		  System.out.println(create_Q_ele[create_que]); //call login as company method, pass array values
																		
						  						  						} 
						  						  						String Que_type = create_Q_ele[RC.Current_Coulumn_Number(CreateSur_Questions, "AnswerType")];
						  						  					for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
																	  { 
																		  System.out.println("for Loop" );
																		  System.out.println(Que_type);
																		  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers));
																		  int times=0;
																		  if (Que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)))
																		  			// Adduser contains company email_id at 1st column  for validation
																		  {
																			  
																			  
																			  		System.out.println("Matches ID to Register");
																			  		System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)); 
																			  //based on j value get the row data and do Adding Users
																			   
																			  		for(int Survey_Answers_Ind=0;Survey_Answers_Ind<Survey_Answers_col;Survey_Answers_Ind++) 
																			  		{
																			  			System.out.println(times);
																			  			times++;
																				  		Survey_Answers_ele[Survey_Answers_Ind]=RC.getStringCellData(Survey_Answers_Index, Survey_Answers_Ind, Survey_Answers);
																				  		System.out.println("QueId matches with Createsurvey, and the values are"+Survey_Answers_ele[Survey_Answers_Ind]);
																			  		}
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
																			  			int cht_tot=0;
																			  		
						//listaddremove class=====================================================================													  		
																			  			System.out.println("Before adding elements to list "+list.size());
																			  		
																			  			int old_list =list.size();
																					 	int temp_size;
																			//checkbox elements adding to the list		 
																					 	for(int i=0;i<10;i++)
																				  		{
																						 	
																						 	if(options[i]!="")
																				  			{
																						 		list.add(options[i]);
																						 		for(int tem=0;tem<list.size();tem++)
																						 		{
																						 			System.out.println("Adding checkAns to list "+list.get(tem));
																						 		}
																						 		if(cht_tot>=1)
																						 		{
																						 			temp_size=list.size();
																						 			System.out.println(temp_size);
																						 			String temp = list.get(temp_size-1);
																						 			System.out.println(temp);
																						 			list.remove(temp_size-1);
																						 			list.add(","+temp);
																						 			for(int tem=0;tem<list.size();tem++)
																							 		{
																							 			System.out.println("Adding , to second checkAns onwards  to list "+list.get(tem));
																							 		}
																						 		}
																				  				cht_tot++;
																				  			}
																				  		}
																					 	int new_list = list.size();
																			//after adding check elements get the size		 
																					 	System.out.println(new_list);
																					 
																					 	int list_size =  new_list-old_list;
																					 
																					 	String[] arr = new String[list_size];
																					 	System.out.println("string array size : "+arr.length);
																					 	for(int ind=arr.length-1,j=new_list-1;ind>=0;ind--,j--)
																					 	{
																						 	arr[ind]=list.get(j);
			//added comment on 3-Nov-2017																			 
																						 	/*if(ind!=0)
																						 	{
																							 	arr[ind]=","+arr[ind];
																						 	}*/
			//End added comment on 3-Nov-2017																				
																					 	}
																					 
																					 	for(int arr_ind = 0,list_rem=new_list-1;arr_ind<arr.length;arr_ind++,list_rem--)
																					 	{
																						 	System.out.println(arr[arr_ind]);
																						 //removing newly added list elements			 
																						 	list.remove(list_rem);
																					 	}
																					 	System.out.println("After concatenation removing list elements "+list.size());
																					 
																					 	if(old_list==list.size())
																					 	{
																						 	for(int arr_siz=1;arr_siz<arr.length;arr_siz++)
																						 	{
																							 	//int arr_temp=arr_siz+1;
																							 	arr[0]=arr[0].concat(arr[arr_siz]);
																						 	}
																						 	System.out.println("the elemnet to be added to the list is "+arr[0]);
																						 	list.add(arr[0]);
																						 
																						 	System.out.println(list.size());
																					 	}
																					 	for(int list_disp = 0;list_disp<list.size();list_disp++)
																					 	{
																						 	System.out.println(list.get(list_disp));
																					 	}
						//================================================================															 
																			  			break;
																			  		
																			  			case "Date": System.out.println("Date to be enetered");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")]);
																			  			break;
																			  		
																			  			case "File Upload":System.out.println("file to be uploaded");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "File Upload")]);
																			  			break;
																			  			
																			  			case "Dropdown":System.out.println("dropdown to be clicked");
																			  			System.out.println("Before adding elements to list "+list.size());
																			  			int drop_tot=0;
																			  			int beforDroplist =list.size();
																			  			int list_withdrop = list.size()+1;
																						for(int i=0;i<10;i++)
																			  			{
																					 	
																					 		if(options[i]!="")
																			  				{
																					 			list.add(options[i]);
																					 			drop_tot++;
																			  				}
																			  			}
																						int newDrop_list = list.size();
																						System.out.println(newDrop_list);
																						list.add(beforDroplist,list.get(newDrop_list-1));
																						list.remove(newDrop_list);
																						System.out.println("after adding drop elem List size "+list.size());
																						int list_incl_drop =list.size();
																					
																						System.out.println(list.get(beforDroplist));//list.get(5)
																					
																						System.out.println(list_incl_drop);
																					
																						for(int i=0;i<list_incl_drop;i++)//i=0;i<11;i++
																						{
																							System.out.println(list.get(i));
																							//list.remove(i);
																						}
																						for(int i=list_incl_drop-1;i>=list_withdrop;i--)
																						{
																							System.out.println(list.get(i));
																							list.remove(i);
																						}
																						System.out.println(list.size());
																					 
																						for(int i=0;i<list.size();i++)
																						{
																							System.out.println(list.get(i));
																						}
																			  			break;
																			  		
																			  			case "Number":System.out.println("Number to be clicked");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Number")]);
																			  			break;
																			  		
																			  				case "Radio Button":System.out.println("radio button to be clicked");
																			  				int radio_tot=0;
																			  				int beforRadiolist =list.size();
																			  				int list_withRadio = list.size()+1;
																					 		for(int i=0;i<10;i++)
																				  			{
																						 	
																						 		if(options[i]!="")
																				  				{
																						 			list.add(options[i]);
																						 			radio_tot++;
																				  				}
																				  			}
																					 	int newRadio_list = list.size();
																					 	System.out.println(newRadio_list);
																					 	list.add(beforRadiolist,list.get(newRadio_list-1));
																					 	list.remove(newRadio_list);
																					 	System.out.println("after adding drop elem List size "+list.size());
																					 	int list_incl_radio =list.size();
																						
																					 	System.out.println(list.get(beforRadiolist));//list.get(5)
																						
																					 	System.out.println(list_incl_radio);
																						
																					 	for(int i=0;i<list_incl_radio;i++)//i=0;i<11;i++
																					 	{
																						 	System.out.println(list.get(i));
																						 	//list.remove(i);
																					 	}
																					 	for(int i=list_incl_radio-1;i>=list_withRadio;i--)
																					 	{
																						 System.out.println(list.get(i));
																							list.remove(i);
																					 	}
																					 	System.out.println(list.size());
																						 
																					 	for(int i=0;i<list.size();i++)
																					 	{
																						 	System.out.println(list.get(i));
																					 	}
																					 	break;
																			  		
																			  			case "Scale / Rate":System.out.println("rating to be entered");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Scale / Rate")]);
																			  			break;
																			  		
																			  			case "Single Line Text":System.out.println("single line text to be eneterd");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Single Line Text")]);
																			  			break;
																			  		
																			  			case "Text Area":System.out.println("text area to be entered");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "TextArea")]);
																			  			break;
																			  		
																			  			case "Yes or No":System.out.println("Y/N to be selected");
																			  			list.add(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")]);
																			  			break;
																			  	}
																		  	
																		  }
																		  else
																		  {
																			  /*if(create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")].equals(
																					  RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueID"),Survey_Answers)))
																			  {
																				  System.out.println("QuestionID in createSurvey Sheet Is not matching with SUrveyAns QUEID");
																				  Assert.fail("Add Answers For the QuestionID "+create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "QuestioID")]+" in SurveyAnswers sheet");
																				  
																				  
																			  }*/
																		  }
																	  	  
																	  }
						  						  					}
						  						  				}
						  						  			}
						  						  		}
						  						  	}
						  						}
											  for(int excelAns=0;excelAns<list.size();excelAns++)
											  {
												  System.out.println("Excel Ans "+list.get(excelAns));
												  if(list.get(excelAns).equals("Yes"))
												  {
													  String str1=list.get(excelAns);
													  String upperYes = str1.toUpperCase();
													  list.set(excelAns, upperYes);
												  }
												  if(AnsFromResponse.get(excelAns).contains("Download"))
												  {
													  AnsFromResponse.set(excelAns, list.get(excelAns));
												  }
												  System.out.println("Response Ans "+AnsFromResponse.get(excelAns));
												  if(!list.get(excelAns).equals(AnsFromResponse.get(excelAns)))
												  {
													  Assert.fail("Answers in Excel Not Matched With Answers From Response");
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
						  
						  
						  String status=func_cases.AddUsers_inCompany(driver, 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "UserName")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Employee ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Email ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Contact No")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Designation")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Parent_Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Reporting Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Hr Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Street1")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Street2")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "City")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Country ")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "State")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Zip Code")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "ChooseFilePath")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Description")]);//Description
						  if(status=="true")
						  func_cases.AddUsers_inCompanyValidation(driver, 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "UserName")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Employee ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Email ID")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Contact No")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Designation")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Parent_Role")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Reporting Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Hr Manager")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Street1")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Street2")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "City")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Country ")], 
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "State")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Zip Code")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "ChooseFilePath")],
								  AddUsers_ele[RC.Current_Coulumn_Number(AddUsers, "Description")]);//Description
					  }
				  }
				}
				
			}
			//company profile fill
			if(S23!="")//Sheet_CompanyProfile
			{
				  String CompanyProfile=Environment("Sheet_CompanyProfile"); 
				  int CompanyProfile_row=RC.getLastrowno(CompanyProfile); 
				  int CompanyProfile_col=RC.getLastcolmno(CompanyProfile); 
				  String[] CompanyProfile_ele=new String[CompanyProfile_col]; 
				  for (int CompanyProfile_index = 1; CompanyProfile_index < RC.getLastrowno(CompanyProfile); CompanyProfile_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S23);
					  System.out.println(RC.getStringCellData(CompanyProfile_index, RC.Current_Coulumn_Number(CompanyProfile, "CompanyProfID"), CompanyProfile)); 
					  if (S23.equals(RC.getStringCellData(CompanyProfile_index, RC.Current_Coulumn_Number(CompanyProfile, "CompanyProfID"),CompanyProfile)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(CompanyProfile_index, RC.Current_Coulumn_Number(CompanyProfile, "CompanyProfID"),CompanyProfile)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int CompanyProfile_ind=0;CompanyProfile_ind<CompanyProfile_col;CompanyProfile_ind++) 
						  {
							  CompanyProfile_ele[CompanyProfile_ind]=RC.getStringCellData(CompanyProfile_index, CompanyProfile_ind, CompanyProfile);
							  System.out.println(CompanyProfile_ele[CompanyProfile_ind]);
							  if(CompanyProfile_ele[CompanyProfile_ind]!="")
							  {
								  company_Profile.add(CompanyProfile_ele[CompanyProfile_ind]);
							  }
						  }
						  
						  func_cases.CompanyProfile(driver, 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "LegalName")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Website")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "CompanyType")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "BusinessStructure")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Contact No")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DUNS No")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Industry")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "SubIndustry")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "FoundedYear")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Compay Size")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "ParentCompany")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "FB_URL")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Twitter_Url")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Linkedin_Url")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Google+Url")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Currency")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Yearly_CurrencyType")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "YearlyCurrency")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Yearly")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "TimeZone")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Street1")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_City")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Country")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_State")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Zipcode")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "MailingSameAsReg")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Street1")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_City")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Country")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_State")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Zipcode")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "President")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Campaign_Manager")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Account Manager")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "RoutingNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "BankName")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "PhoneNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "AccNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "AccType")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Street1")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_City")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Country")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_State")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Zipcode")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DocumentType")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DocumentNotes")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Document_Name")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Document_FilePath")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DocumentDescription")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image1")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image3")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Overview")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Background")]);
						  func_cases.companyInfoValidation(driver, 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "LegalName")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Website")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "CompanyType")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "BusinessStructure")],
								  "",
								  "",
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Contact No")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DUNS No")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "BusinessStructure")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Industry")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "SubIndustry")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "FoundedYear")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Compay Size")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "ParentCompany")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "FB_URL")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Twitter_Url")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Linkedin_Url")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Google+Url")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Currency")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "YearlyCurrency")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Yearly")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_City")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_State")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Country")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Registered_Zipcode")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "MailingSameAsReg")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_City")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_State")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Country")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Mail_Zipcode")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "TimeZone")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "President")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Campaign_Manager")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Account Manager")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "BankName")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "RoutingNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "AccNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "AccType")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "PhoneNo")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Street2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_City")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Country")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_State")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Bank_Zipcode")], 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DocumentType")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Document_Name")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "DocumentDescription")],
								  "", 
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image1")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image2")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Gallery_Image3")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Overview")],
								  CompanyProfile_ele[RC.Current_Coulumn_Number(CompanyProfile, "Background")]);
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
						  Logs_DigiSurvey.info("creating New Quiz");
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
						  Logs_DigiSurvey.info("Creating Quiz Template Completed, going to add questions");
						  
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
									  Logs_DigiSurvey.info("Added questions to created Quiz");
									  
										jse.executeScript("window.scrollBy(0,1000)", "");//scroll down 
										
										
								  } 
							  }
			
							}
						  	//save created quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "saveQuiz")]!="")
							{
								
								func_cases.saveCreatedQuiz(driver);
								func_cases.checkQuizTemplates(driver,
										create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "TemplateName")]);
								
							}
							
							//cancel created Quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "cancelQuiz")]!="")
							{
								func_cases.cancelCreatedQuiz(driver);
							}
							
							//publish created Quiz
							if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "publishQuiz")]!="")
							{
								func_cases.publish_SavedQuiz(driver,
										create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizName")]);
								func_cases.checkQuizTemplates(driver,
										create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "TemplateName")]);
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
					//check company profile filled or not
				
				  int check=0;
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
						  check++;
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int publish_saved_quiz_ind=0;publish_saved_quiz_ind<publish_saved_quiz_col;publish_saved_quiz_ind++) 
						  {
							  publish_saved_quiz_ele[publish_saved_quiz_ind]=RC.getStringCellData(publish_saved_quiz_index, publish_saved_quiz_ind, publish_saved_quiz);
							  System.out.println(publish_saved_quiz_ele[publish_saved_quiz_ind]); //call login as company method, pass array values
			  
				  
						  }
						  if(check==1)
						  {
							  func_cases.check_CP_Filled(driver, 
								  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "filepath")],
								  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "overview")],
								  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "background")]);
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
									  if(create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "publishQuiz")].equals(""))
									  {
									  	func_cases.check_CP_Filled(driver, 
											  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "filepath")],
											  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "overview")],
											  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "background")]);
									  	func_cases.publishExistedQuiz(driver, 
											  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizName")], 
											  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "Proceed")]);
									  }
								  }
							  }
							 
						  }
					  }
				}
				
			}
			if(S12!="")
			{
				String EditUpdateQuiz=Environment("Sheet_EditUpdateQuiz"); 
				  int EditUpdateQuiz_row=RC.getLastrowno(EditUpdateQuiz); 
				  int EditUpdateQuiz_col=RC.getLastcolmno(EditUpdateQuiz); 
				  String[] EditUpdateQuiz_ele=new String[EditUpdateQuiz_col]; 
				  for (int EditUpdateQuiz_index = 1; EditUpdateQuiz_index < RC.getLastrowno(EditUpdateQuiz); EditUpdateQuiz_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S12);
					  System.out.println(RC.getStringCellData(EditUpdateQuiz_index, RC.Current_Coulumn_Number(EditUpdateQuiz, "UpdateID"), EditUpdateQuiz)); 
					  if (S12.equals(RC.getStringCellData(EditUpdateQuiz_index, RC.Current_Coulumn_Number(EditUpdateQuiz, "UpdateID"),EditUpdateQuiz)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(EditUpdateQuiz_index, RC.Current_Coulumn_Number(EditUpdateQuiz, "UpdateID"),EditUpdateQuiz)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int EditUpdateQuiz_ind=0;EditUpdateQuiz_ind<EditUpdateQuiz_col;EditUpdateQuiz_ind++) 
						  {
							  EditUpdateQuiz_ele[EditUpdateQuiz_ind]=RC.getStringCellData(EditUpdateQuiz_index, EditUpdateQuiz_ind, EditUpdateQuiz);
							  System.out.println(EditUpdateQuiz_ele[EditUpdateQuiz_ind]); //call login as company method, pass array values
						  }
						  func_cases.editUpdateQuiz(driver, 
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "SearchWithQuizName")], 
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "Description")], 
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "ExpiresIn(Days)")],
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "EqualWeight")],
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "EqualDuration(In Sec)")],
								  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "TotalDuration(Min)")]);
						  if(EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "AddQue")]!="")
						  {
							  String create_quiz_que=Environment("Sheet_Quiz_Questions"); 
							  int quiz_que_row=RC.getLastrowno(create_quiz_que); 
							  int quiz_que_col=RC.getLastcolmno(create_quiz_que); 
							  String[] quiz_que_ele=new String[quiz_que_col]; 
							  //call a function tell that how many existing questions are there currently
							  int Que_No=func_cases.existedQueInQuiz(driver);
							  System.out.println("Existed Questions in Quiz:"+Que_No);
							  for (int k = 1; k < RC.getLastrowno(create_quiz_que); k++) 
							  { 
								 
								  System.out.println("QueNO: "+Que_No);
								  System.out.println("for Loop" );
								  System.out.println(EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "AddQue")]);
								  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(create_quiz_que, "Quiz_QueID"), create_quiz_que)); 
								  if(EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "AddQue")]
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
									  System.out.println("Current question number is:"+Que_No);
									  func_cases.Quiz_Questions(driver,
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "EnterUrQue")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "Weightage_Score")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "DurationInSeconds")], 
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "AnswerType")],
											  quiz_que_ele[RC.Current_Coulumn_Number(create_quiz_que, "NumberOfOptions")],
											  Que_No,
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
											  EditUpdateQuiz_ele[RC.Current_Coulumn_Number(EditUpdateQuiz, "ValidateQue")]);
									  
										jse.executeScript("window.scrollBy(0,1000)", "");//scroll down
										
										
								  }
							  }
							  //save updated quiz
							  func_cases.saveCreatedQuiz(driver);
						  }
					  }
				  }
			}
			if(S21!="")
			{
				//share quiz
				share_quiz=1;
				String Quiz_Share=Environment("Sheet_Quiz_Share"); 
				  int Quiz_Share_row=RC.getLastrowno(Quiz_Share); 
				  int Quiz_Share_col=RC.getLastcolmno(Quiz_Share); 
				  String[] Quiz_Share_ele=new String[Quiz_Share_col]; 
				  for (int Quiz_Share_index = 1; Quiz_Share_index < RC.getLastrowno(Quiz_Share); Quiz_Share_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S21);
					  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"), Quiz_Share)); 
					  if (S21.equals(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Quiz_Share_ind=0;Quiz_Share_ind<Quiz_Share_col;Quiz_Share_ind++) 
						  {
							  Quiz_Share_ele[Quiz_Share_ind]=RC.getStringCellData(Quiz_Share_index, Quiz_Share_ind, Quiz_Share);
							  System.out.println(Quiz_Share_ele[Quiz_Share_ind]); //call login as company method, pass array values
						  }
						  func_cases.shareQuiz(driver, 
								  Quiz_Share_ele[RC.Current_Coulumn_Number(Quiz_Share, "QuizName")], 
								  share_quiz);
						  if(S21.equals(
								  RC.getStringCellData(Quiz_Share_index+1, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)))
						  {
							  share_quiz++;
						  }
					  }
				  }
				  //func_cases.CompanyLogout(driver);
			}
			if(S22!="")
			{
				if(S21!="")//from share get quizname, for each quiz end user login ,give response and logout
				{
				  //func_cases.companyLogin(driver, currentUsername, currentPassword);
				  int share_no=1;
				  String Quiz_Share=Environment("Sheet_Quiz_Share"); 
				  int Quiz_Share_row=RC.getLastrowno(Quiz_Share); 
				  int Quiz_Share_col=RC.getLastcolmno(Quiz_Share); 
				  String[] Quiz_Share_ele=new String[Quiz_Share_col]; 
				  for (int Quiz_Share_index = 1; Quiz_Share_index < RC.getLastrowno(Quiz_Share); Quiz_Share_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S21);
					  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"), Quiz_Share)); 
					  if (S21.equals(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Quiz_Share_ind=0;Quiz_Share_ind<Quiz_Share_col;Quiz_Share_ind++) 
						  {
							  Quiz_Share_ele[Quiz_Share_ind]=RC.getStringCellData(Quiz_Share_index, Quiz_Share_ind, Quiz_Share);
							  System.out.println(Quiz_Share_ele[Quiz_Share_ind]); //call login as company method, pass array values
						  }
						  String currentURL = driver.getCurrentUrl();
						  if(currentURL.contains("/Account/Login"))
						  {
							  func_cases.companyLogin(driver, currentUsername, currentPassword);
						  }
						  String[] view_details=func_cases.viewQuiz(driver, Quiz_Share_ele[RC.Current_Coulumn_Number(Quiz_Share, "QuizName")]);
						  int Total_Que = 0;
						  Total_Que = Integer.parseInt(view_details[3]);
						  String[] queType_ele = new String[Total_Que];
						  String[] que_Type = new String[Total_Que];
						  System.out.println("converted Question No to integer : "+Total_Que);
						  /*if(view_details[1]=="1")  {  Total_Que=1; }*/
					  
						  //based on no of que, GET QUESTION TYPE
						  
						  for(int i=1; i <= Total_Que ; i++)
						  {
							  //queType_ele[i-1] = func_cases.viewQuiz_QueType(driver,i);
							  queType_ele[i-1]=func_cases.viewQuiz_GetQueText(driver, i);
							  String quetext=queType_ele[i-1];
							  System.out.println("Question "+i+":"+quetext);
							  String questiontype = DB_QueType.Db_qtype(Quiz_Share_ele[RC.Current_Coulumn_Number(Quiz_Share, "QuizName")], quetext);
							  System.out.println( "Que Type "+questiontype+" and i value "+i);
							  int k=i-1;
							  que_Type[k]=questiontype;
						  }
					  
						  func_cases.CompanyLogout(driver);
						  
						  
						  String Quiz_EndUser=Environment("Sheet_Quiz_EndUser"); 
						  int Quiz_EndUser_row=RC.getLastrowno(Quiz_EndUser); 
						  int Quiz_EndUser_col=RC.getLastcolmno(Quiz_EndUser); 
						  String[] Quiz_EndUser_ele=new String[Quiz_EndUser_col]; 
						  for (int Quiz_EndUser_index = 1; Quiz_EndUser_index < RC.getLastrowno(Quiz_EndUser); Quiz_EndUser_index++) 
						  { 
							  System.out.println("for Loop" );
							  System.out.println(S22);
							  System.out.println(RC.getStringCellData(Quiz_EndUser_index, RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserQuizID"), Quiz_EndUser)); 
							  if (S22.equals(RC.getStringCellData(Quiz_EndUser_index, RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserQuizID"),Quiz_EndUser)))
								  // Adduser contains company email_id at 1st column  for validation
							  { 
								  System.out.println("Matches ID to Register");
								  System.out.println(RC.getStringCellData(Quiz_EndUser_index, RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserQuizID"),Quiz_EndUser)); 
								  //based on j value get the row data and do Adding Users
						   
								  for(int Quiz_EndUser_ind=0;Quiz_EndUser_ind<Quiz_EndUser_col;Quiz_EndUser_ind++) 
								  {
									  Quiz_EndUser_ele[Quiz_EndUser_ind]=RC.getStringCellData(Quiz_EndUser_index, Quiz_EndUser_ind, Quiz_EndUser);
									  System.out.println(Quiz_EndUser_ele[Quiz_EndUser_ind]); //call login as company method, pass array values
			  
				  
								  }
								  //loginas digi user or company inorder to give reponse to shared Quiz
								  func_cases.enduser_Login(driver, Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "WebLink")],
								  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserEmail")],
								  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "password")]);
//after login as end user, open new tab, get the quiz link from excel								  
								  String oldTab = driver.getWindowHandle();
								  Thread.sleep(3000);
					  			  newTab_robot newTab = new newTab_robot();
					  			  Thread.sleep(3000);
					  			  /*ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
					  			  System.out.println(Tabs.size());
					  			  Tabs.remove(oldTab);
					  			  System.out.println(Tabs.get(0));
					  			  // change focus to new tab
					  			  driver.switchTo().window(Tabs.get(0));
					  			  */
					  			  Set<String> set = new HashSet<String>(driver.getWindowHandles());
					  			  for(String tab : set)
					  			  {
									System.out.println("window :"+tab);
					  			  }
					  			  set.remove(oldTab);
					  			  driver.switchTo().window(set.iterator().next());
					  			  
								  
					  			  //switch to new tab to answer survey, now get the link from excel
					  			  
					  			  File src = new File("QuizLink_Excel\\Quiz_Links.xlsx");
					  			  FileInputStream fis;
					  			  fis = new FileInputStream(src);
					  			  XSSFWorkbook wb = new XSSFWorkbook(fis);
					  			  XSSFSheet sheet1 = wb.getSheetAt(0);
					  			  String data0 ;
					  			  if(share_no<=share_quiz)
					  			  {
					  				  data0 = sheet1.getRow(share_no).getCell(0).getStringCellValue();
					  				  System.out.println("Open Quiz Link :"+share_no+data0);
					  				  wb.close();
					  				  driver.get(data0);//surveylink opened , now you have to fill that response as an end user
					  			  }
					  			  String surveylinkURL = driver.getCurrentUrl();
								  
								  if(Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "FillAnswer")]!="")
								  {
									  for(int num=0;num<Total_Que;num++)
									  {
									  	func_cases.answerQuiz(driver,que_Type[num],num+1,Total_Que);
									  }
								  }
								  
								  if(Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "SaveResponse")]!="")
								  {
									  
								  }
								  
								  if(Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "SubmitResponse")]!="")
								  {
									  
								  }
								  driver.close();
								  driver.switchTo().window(oldTab);
						  
								  func_cases.CompanyLogout(driver);
							  }
							  if(Quiz_EndUser_index+1 == RC.getLastrowno(Quiz_EndUser))
							  {
								  share_no++;
							  }
						  }
						  
					  }
				  }
				}		
			}
			if(S25!="")
			{
				String Quiz_RespView=Environment("Sheet_Quiz_RespView"); 
				  int Quiz_RespView_row=RC.getLastrowno(Quiz_RespView); 
				  int Quiz_RespView_col=RC.getLastcolmno(Quiz_RespView); 
				  String[] Quiz_RespView_ele=new String[Quiz_RespView_col]; 
				  for (int Quiz_RespView_index = 1; Quiz_RespView_index < RC.getLastrowno(Quiz_RespView); Quiz_RespView_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S25);
					  System.out.println(RC.getStringCellData(Quiz_RespView_index, RC.Current_Coulumn_Number(Quiz_RespView, "QR_ID"), Quiz_RespView)); 
					  if (S25.equals(RC.getStringCellData(Quiz_RespView_index, RC.Current_Coulumn_Number(Quiz_RespView, "QR_ID"),Quiz_RespView)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Quiz_RespView_index, RC.Current_Coulumn_Number(Quiz_RespView, "QR_ID"),Quiz_RespView)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Quiz_RespView_ind=0;Quiz_RespView_ind<Quiz_RespView_col;Quiz_RespView_ind++) 
						  {
							  Quiz_RespView_ele[Quiz_RespView_ind]=RC.getStringCellData(Quiz_RespView_index, Quiz_RespView_ind, Quiz_RespView);
							  System.out.println(Quiz_RespView_ele[Quiz_RespView_ind]); //call login as company method, pass array values
			  
				  
						  }
						  QuizResponse=func_cases.quiz_ResponseView(driver,
								  Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")],
								  Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "Enduser_Name")]);
						  for(int response=0;response<QuizResponse.size();response++)
						  {
							  System.out.println("QuizResponse Answers "+QuizResponse.get(response));
						  }
					  }
				  }
			}
			if(S16!="")
			{
				//create petition
				String Create_Petition=Environment("Sheet_Create_Petition"); 
				  int Create_Petition_row=RC.getLastrowno(Create_Petition); 
				  int Create_Petition_col=RC.getLastcolmno(Create_Petition); 
				  String[] Create_Petition_ele=new String[Create_Petition_col]; 
				  for (int Indiv_Reg_index = 1; Indiv_Reg_index < RC.getLastrowno(Create_Petition); Indiv_Reg_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S16);
					  System.out.println(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"), Create_Petition)); 
					  if (S16.equals(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"),Create_Petition)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"),Create_Petition)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Create_Petition_ind=0;Create_Petition_ind<Create_Petition_col;Create_Petition_ind++) 
						  {
							  Create_Petition_ele[Create_Petition_ind]=RC.getStringCellData(Indiv_Reg_index, Create_Petition_ind, Create_Petition);
							  System.out.println(Create_Petition_ele[Create_Petition_ind]); //call login as company method, pass array values
			  
				  
						  }
						  String existedpetition=func_cases.beforeCreation_checkExistedPetitions(driver, 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
						  if(existedpetition.equals("false"))
						  {
							  
						  	func_cases.createPetition(driver, 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Campaign Type")], 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Category")], 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")], 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Tags")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "EndDate")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Indefinite")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "VideoLink")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CurrencyType")], 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FundGoal")], 
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SignatureRequired")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Name")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Designation")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Overview")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Overview_Image")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Background")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "BackgroundImage")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Solution")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SolutionImage")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "RiskChallenge")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "RiskChallengeImage")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SupportingDocumentsPath")],
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FAQ")]);
								  
//want to save created petition, check the save column is not null in create petition sheet						  
						  	/*if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Save")]!="")
						  	{
						  		func_cases.saveCreatedPetition(driver);
						  	}
						  	else
						  	{
						  		if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Save")]==""&&
									  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Publish")]!="")
						  		{
						  			func_cases.publishCreatedPetition(driver);
						  		}
						  	}
						  	if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Save")]!=""&&
								  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Publish")]!="")
						  	{
						  		func_cases.publishSavedPetition(driver,
									  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
						  	}*/
						  }
						  else
						  {
							  System.out.println("Petition already existed i drafts "+Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
							  func_cases.displayMessage_Report(driver,Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
						  }
					  	}
				  }
				  
			}
			if(S24!="")
			{
				String MyProfile=Environment("Sheet_MyProfile"); 
				  int MyProfile_row=RC.getLastrowno(MyProfile); 
				  int MyProfile_col=RC.getLastcolmno(MyProfile); 
				  String[] MyProfile_ele=new String[MyProfile_col]; 
				  for (int MyProfile_index = 1; MyProfile_index < RC.getLastrowno(MyProfile); MyProfile_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S24);
					  System.out.println(RC.getStringCellData(MyProfile_index, RC.Current_Coulumn_Number(MyProfile, "MyProfileID"), MyProfile)); 
					  if (S24.equals(RC.getStringCellData(MyProfile_index, RC.Current_Coulumn_Number(MyProfile, "MyProfileID"),MyProfile)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(MyProfile_index, RC.Current_Coulumn_Number(MyProfile, "MyProfileID"),MyProfile)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int MyProfile_ind=0;MyProfile_ind<MyProfile_col;MyProfile_ind++) 
						  {
							  MyProfile_ele[MyProfile_ind]=RC.getStringCellData(MyProfile_index, MyProfile_ind, MyProfile);
							  System.out.println(MyProfile_ele[MyProfile_ind]); //call login as company method, pass array values  
						  }
						  func_cases.myProfile(driver, 
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Name")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Contact")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Occupation")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "VisaStatus")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "BirthMMYYYY")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "SalaryType")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "SalaryVal")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Street1")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Street2")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "City")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Country")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "State")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Zipcode")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Timezone")]);
						  func_cases.myProfile_Validation(driver, 
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Name")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Contact")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Occupation")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "VisaStatus")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "BirthMMYYYY")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "SalaryType")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "SalaryVal")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Street1")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Street2")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "City")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Country")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "State")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Zipcode")],
								  MyProfile_ele[RC.Current_Coulumn_Number(MyProfile, "Timezone")]);
						  
					  }
				  }
			}
//========start			
			/*if(S13!="")
			{
				func_cases.CompanyLogout(driver);
			}*/
//=================end			
//the company is not logged in while doing sign petition or funding 			
			if(S18!="")
			{
				
				
				String SignPetition_ExistedUser=Environment("Sheet_SignPetition_ExistedUser"); 
				  int SignPetition_Exist_row=RC.getLastrowno(SignPetition_ExistedUser); 
				  int SignPetition_Exist_col=RC.getLastcolmno(SignPetition_ExistedUser); 
				  String[] SignPetition_Exist_ele=new String[SignPetition_Exist_col]; 
				  for (int SignPetition_Exist_index = 1; SignPetition_Exist_index < RC.getLastrowno(SignPetition_ExistedUser); SignPetition_Exist_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S18);
					  System.out.println(RC.getStringCellData(SignPetition_Exist_index, RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Sign_ExistedID"), SignPetition_ExistedUser)); 
					  if (S18.equals(RC.getStringCellData(SignPetition_Exist_index, RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Sign_ExistedID"),SignPetition_ExistedUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SignPetition_Exist_index, RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Sign_ExistedID"),SignPetition_ExistedUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SignPetition_Exist_ind=0;SignPetition_Exist_ind<SignPetition_Exist_col;SignPetition_Exist_ind++) 
						  {
							  SignPetition_Exist_ele[SignPetition_Exist_ind]=RC.getStringCellData(SignPetition_Exist_index, SignPetition_Exist_ind, SignPetition_ExistedUser);
							  System.out.println(SignPetition_Exist_ele[SignPetition_Exist_ind]); //call login as company method, pass array values
			  
				  
						  }
						  String petitiontitle_check=func_cases.companyBase(driver,
									SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "PetitionTitle")]);
				
						  func_cases.petitionSign_ExistedUser(driver, petitiontitle_check,
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "emailID")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "password")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Address")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "OrgFor")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Donate?")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "ReadTerms")]);
						  
						  if(SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Donate?")]!="")
						  {
							  String Donate_ExistedUser=Environment("Sheet_Donate_ExistedUser"); 
							  int Donate_ExistedUser_row=RC.getLastrowno(Donate_ExistedUser); 
							  int Donate_ExistedUser_col=RC.getLastcolmno(Donate_ExistedUser); 
							  String[] Donate_ExistedUser_ele=new String[Donate_ExistedUser_col]; 
							  for (int Donate_ExistedUser_index = 1; Donate_ExistedUser_index < RC.getLastrowno(Donate_ExistedUser); Donate_ExistedUser_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S20);
								  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"), Donate_ExistedUser)); 
								  if (SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Donate?")].equals(
										  RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int Donate_ExistedUser_ind=0;Donate_ExistedUser_ind<Donate_ExistedUser_col;Donate_ExistedUser_ind++) 
									  {
										  Donate_ExistedUser_ele[Donate_ExistedUser_ind]=RC.getStringCellData(Donate_ExistedUser_index, Donate_ExistedUser_ind, Donate_ExistedUser);
										  System.out.println(Donate_ExistedUser_ele[Donate_ExistedUser_ind]); //call login as company method, pass array values
						  
							  
									  }
									  func_cases.donateFund(driver,
											  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Donate?")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Amount")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "CardNumber")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "ExpDate")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "cvv")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "firstname")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "lastname")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "phone")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "email")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress1")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress2")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "city")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "state")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "country")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "postcode")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "makePayment")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Reset")]);
									  
								  }
							  }
						  }
					  }
				  }
				  
			}
			
			if(S17!="")
			{
				
//New user wants to sign petition and creating profile
				String SignPetition_NewUser=Environment("Sheet_SignPetition_NewUser"); 
				  int SignPetition_New_row=RC.getLastrowno(SignPetition_NewUser); 
				  int SignPetition_New_col=RC.getLastcolmno(SignPetition_NewUser); 
				  String[] SignPetition_New_ele=new String[SignPetition_New_col]; 
				  for (int SignPetition_New_index = 1; SignPetition_New_index < RC.getLastrowno(SignPetition_NewUser); SignPetition_New_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S17);
					  System.out.println(RC.getStringCellData(SignPetition_New_index, RC.Current_Coulumn_Number(SignPetition_NewUser, "Sign_NewID"), SignPetition_NewUser)); 
					  if (S17.equals(RC.getStringCellData(SignPetition_New_index, RC.Current_Coulumn_Number(SignPetition_NewUser, "Sign_NewID"),SignPetition_NewUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SignPetition_New_index, RC.Current_Coulumn_Number(SignPetition_NewUser, "Sign_NewID"),SignPetition_NewUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SignPetition_New_ind=0;SignPetition_New_ind<SignPetition_New_col;SignPetition_New_ind++) 
						  {
							  SignPetition_New_ele[SignPetition_New_ind]=RC.getStringCellData(SignPetition_New_index, SignPetition_New_ind, SignPetition_NewUser);
							  System.out.println(SignPetition_New_ele[SignPetition_New_ind]); //call login as company method, pass array values
			  
				  
						  }
						  String petitiontitle_check=func_cases.companyBase(driver,
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "PetitionTitle")]);
						  
						  func_cases.petitionSign_NewUser(driver,
								  petitiontitle_check,
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "FirstName")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "LastName")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "EmailID")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "CountryCode")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "PhoneNumber")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Address")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Occupation")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "OrganizationFor")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Visa Status")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Signature")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "CreateProfile")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Password")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Donate?")],
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Read_Policy")]);
						  
						  if(SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Donate?")]!="")
						  {
							  String Donate_ExistedUser=Environment("Sheet_Donate_ExistedUser"); 
							  int Donate_ExistedUser_row=RC.getLastrowno(Donate_ExistedUser); 
							  int Donate_ExistedUser_col=RC.getLastcolmno(Donate_ExistedUser); 
							  String[] Donate_ExistedUser_ele=new String[Donate_ExistedUser_col]; 
							  for (int Donate_ExistedUser_index = 1; Donate_ExistedUser_index < RC.getLastrowno(Donate_ExistedUser); Donate_ExistedUser_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S20);
								  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"), Donate_ExistedUser)); 
								  if (SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Donate?")].equals(
										  RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int Donate_ExistedUser_ind=0;Donate_ExistedUser_ind<Donate_ExistedUser_col;Donate_ExistedUser_ind++) 
									  {
										  Donate_ExistedUser_ele[Donate_ExistedUser_ind]=RC.getStringCellData(Donate_ExistedUser_index, Donate_ExistedUser_ind, Donate_ExistedUser);
										  System.out.println(Donate_ExistedUser_ele[Donate_ExistedUser_ind]); //call login as company method, pass array values
						  
							  
									  }
									  func_cases.donateFund(driver,
											  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Donate?")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Amount")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "CardNumber")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "ExpDate")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "cvv")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "firstname")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "lastname")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "phone")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "email")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress1")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress2")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "city")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "state")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "country")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "postcode")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "makePayment")],
											  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Reset")]);
								  }
							  }
						  }
					  }
				  }
				
			}
			if(S20!="")
			{
				String Donate_ExistedUser=Environment("Sheet_Donate_ExistedUser"); 
				  int Donate_ExistedUser_row=RC.getLastrowno(Donate_ExistedUser); 
				  int Donate_ExistedUser_col=RC.getLastcolmno(Donate_ExistedUser); 
				  String[] Donate_ExistedUser_ele=new String[Donate_ExistedUser_col]; 
				  for (int Donate_ExistedUser_index = 1; Donate_ExistedUser_index < RC.getLastrowno(Donate_ExistedUser); Donate_ExistedUser_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S20);
					  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"), Donate_ExistedUser)); 
					  if (S20.equals(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Donate_ExistedUser_index, RC.Current_Coulumn_Number(Donate_ExistedUser, "Existed_DonateID"),Donate_ExistedUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int Donate_ExistedUser_ind=0;Donate_ExistedUser_ind<Donate_ExistedUser_col;Donate_ExistedUser_ind++) 
						  {
							  Donate_ExistedUser_ele[Donate_ExistedUser_ind]=RC.getStringCellData(Donate_ExistedUser_index, Donate_ExistedUser_ind, Donate_ExistedUser);
							  System.out.println(Donate_ExistedUser_ele[Donate_ExistedUser_ind]); //call login as company method, pass array values
			  
				  
						  }
						  String petitiontitle_check=func_cases.companyBase(driver,
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "PetitionTitle")]);
						  func_cases.donateFund(driver,petitiontitle_check,
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Amount")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "CardNumber")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "ExpDate")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "cvv")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "firstname")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "lastname")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "phone")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "email")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress1")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "billingAddress2")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "city")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "state")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "country")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "postcode")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "makePayment")],
								  Donate_ExistedUser_ele[RC.Current_Coulumn_Number(Donate_ExistedUser, "Reset")]);
					  }
				  }
			}
			
			
			
		}
		Logs_DigiSurvey.endTestCase(S2);
	}
	@DataProvider
	public Object[][] custData2() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),2);
		return testData;
	}
	@Test(dataProvider = "custData2")
	public void test2(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29) throws IOException, InterruptedException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		System.out.println("Output Test2");
		if(S3!="")
		{
			  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
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
			  //Validating Users registered with refer link in MyReferral List of user who are sharing refer link
			  if(S26!="")
			  {
			  	  int refered_size = refer_friends.size();
				  String[] refered=new String[refered_size];
			  
				   
				  for(int i=0;i<refered_size;i++)
				  {
					  func_cases.validateReferedFriends(driver, refer_friends.get(i));
				  }
			  }
			      /*List<String> list = new ArrayList<>();
			      list.add("Name");
			      list.add("Last");
			      String[] refered=new String[list.size()];
			      
				  for(int i=0;i<list.size();i++)
				  {
					  refered[i]=list.get(i);
					  System.out.println(refered[i]);
					  func_cases.validateReferedFriends(driver, refered[i]);
				  }*/
		}
		if(S13!="")
		{
			func_cases.CompanyLogout(driver);
		}
	}
}
	
