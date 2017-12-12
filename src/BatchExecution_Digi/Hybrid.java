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
class Hybrid extends Browser_Setup
{
	
	{
		System.setProperty("atu.reporter.config", "lib//atu.properties");
	}
	Functional_Cases_propread func_cases = new Functional_Cases_propread();
	Functional_Libraries fl = new Functional_Libraries();
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
	public void AdminLogin_CreatingUsers_AdminLogout(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
//added lines for Log4j==============
		
		
		DOMConfigurator.configure("log4j.xml");
		Logs_DigiSurvey.startTestCase(S2);
//=================================		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		//Functional_Libraries fl = new Functional_Libraries();
		
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
						  func_cases.adminLogin(driver, 
								  clidiem_Admin_ele[RC.Current_Coulumn_Number(clidiem_Admin, "EmailID")], 
								  clidiem_Admin_ele[RC.Current_Coulumn_Number(clidiem_Admin, "Password")]);
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
					  }
				  }
				  
				  
			}
		}
		else
			fl.disp_Message(driver, "", "", "Testcase Skipped", "", "");
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
	public void CompanyLogin_CreatingUsers_FillingCompanyProfile_FillingMYProfile_CompanyLogout(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29) throws IOException, InterruptedException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		System.out.println("Output Test2");
		if(S3!="")
		{
			ATUReports.currentRunDescription = S2;
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
					  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
					//Adding Users
					  if(S9!="")
						{
						  //Add User
							  String AddUsers=Environment("Sheet_AddUsers"); 
							  int AddUsers_row=RC.getLastrowno(AddUsers); 
							  int AddUsers_col=RC.getLastcolmno(AddUsers); 
							  String[] AddUsers_ele=new String[AddUsers_col]; 
							  for (int Addusers_index = 1; Addusers_index < RC.getLastrowno(AddUsers); Addusers_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S9);
								  System.out.println(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "AddUserID"), AddUsers)); 
								  if (S9.equals(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "AddUserID"),AddUsers))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Addusers_index, RC.Current_Coulumn_Number(AddUsers, "LoginWith"),AddUsers)) )
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
								  if (S23.equals(RC.getStringCellData(CompanyProfile_index, RC.Current_Coulumn_Number(CompanyProfile, "CompanyProfID"),CompanyProfile))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(CompanyProfile_index, RC.Current_Coulumn_Number(CompanyProfile, "LoginWith"),CompanyProfile)))
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
								  if (S24.equals(RC.getStringCellData(MyProfile_index, RC.Current_Coulumn_Number(MyProfile, "MyProfileID"),MyProfile))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(MyProfile_index, RC.Current_Coulumn_Number(MyProfile, "LoginWith"),MyProfile)) )
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
						if(S13!="")
						{
							func_cases.CompanyLogout(driver);
						}
				  } 
			  }
		}
		else
			fl.disp_Message(driver, "", "", "Testcase Skipped", "", "");
			
	}
	
		
	@DataProvider
	public Object[][] custData3() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),3);
		return testData;
	}
	@Test(dataProvider = "custData3")
	public void CompanyLogin_CreateSurvey_EditUpdateSurvey_ShareSurvey_EndUSerResponse_ResponseValidationByCompany(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29) throws IOException, InterruptedException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println("Output Test3");
		if(S3!="")
		{
			ATUReports.currentRunDescription = S2;
			  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
			  String Login=Environment("Sheet_Login"); 
			  int Login_row=RC.getLastrowno(Login); 
			  int Login_col=RC.getLastcolmno(Login); 
			  String[] Login_ele=new String[Login_col]; 
			  for (int log_index = 1; log_index < RC.getLastrowno(Login); log_index++) 
			  { 
				  System.out.println("for Loop" );
				  System.out.println(S3);
				  System.out.println(RC.getStringCellData(log_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
				  if (S3.equals(RC.getStringCellData(log_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
					  // Adduser contains company email_id at 1st column  for validation
				  { 
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(log_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int col_Login=0;col_Login<Login_col;col_Login++) 
					  {
						  Login_ele[col_Login]=RC.getStringCellData(log_index, col_Login, Login);
						  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
						  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
						  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
		  
			  
					  } 
					  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
					  //create survey
					  if(S4!="")
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
								  if (S4.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)) )
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
					  if(S5!="")
					  {
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
									  if (S4.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)))
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
														  (RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName))&&
														  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "LoginWith"),Update_suveyName)))
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
					  if(S6!="")
					  {
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
								  if (S4.equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)))
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
										  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey))&&
												  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(share_survey, "LoginWith"),share_survey)))
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
					  if(S7!="")
					  {
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
							  			if (S4.equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
							  					Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(create, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)))
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
								  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName))&&
								  						  			Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "LoginWith"),Update_suveyName)))
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
					  }
					  //Survey Response Validation
					  if(S8!="")
					  {
						  String currentURL=driver.getCurrentUrl();
							if(currentURL.contains("Account/Login"))
							{
								System.out.println("SURVEY RESPONSE");
								System.out.println("username :"+currentUsername);
								System.out.println("password :"+currentPassword);
								func_cases.companyLogin(driver, currentUsername, currentPassword);
							}
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
									  if (S4.equals(RC.getStringCellData(create_ind, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(create_ind, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)))
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
					  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName))&&
					  						  			Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "LoginWith"),Update_suveyName)))
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
														  RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "createSurveyControl"),Response_Survey))&&
														  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "LoginWith"),Response_Survey)))
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
								  						  				RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName))&&
								  						  			Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "LoginWith"),Update_suveyName)))
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
					  if(S13!="")
					  {
						  func_cases.CompanyLogout(driver);
					  }
				  }
			  }
		}
		else
			fl.disp_Message(driver, "", "", "Testcase Skipped", "", "");
	}
}
	

