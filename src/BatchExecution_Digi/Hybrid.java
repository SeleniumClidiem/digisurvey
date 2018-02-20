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
import org.apache.poi.xssf.usermodel.XSSFCell;
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

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
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
	int surveyValidation_queNo;
	List<String> Tcase = new ArrayList<>();
	
	/*@DataProvider
	public Object[][] runDescription() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile(Environment("Sheet_Control"));
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "runDescription",priority=0)
	public void currentRunDescription(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
		}
	}
	int TcaseLength=Tcase.size();
	
	@Test(dependsOnMethods={"currentRunDescription"})
	public void RunDescription()
	{
		String[] str = new String[TcaseLength];
		for(int i=0;i<TcaseLength;i++)
		{
			str[i]=Tcase.get(i);
		}
		String Description=Tcase.toString();
		ATUReports.currentRunDescription = Description;
		
	}*/
	
//================================	
	@DataProvider
	public Object[][] custData() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),1);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData",priority=1)
	public void AdminLogin_CreatingUsers_AdminLogout(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
//added lines for Log4j==============
		
		
		DOMConfigurator.configure("log4j.xml");
		Logs_DigiSurvey.startTestCase(S2);
//========================================		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		//Functional_Libraries fl = new Functional_Libraries();
		
		if (S1.equals("Y")) 
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Admin Login , Creating Users And Admin Logout");
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
	@Test(dataProvider = "custData2",priority=2)
	public void Company_Profiles(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException
	{
		System.out.println("second testcase"+S1);
		fl.disp_Message(driver, "", "", "", "", "");
		DOMConfigurator.configure("log4j.xml");
		Logs_DigiSurvey.startTestCase(S2);
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		System.out.println("Output Test2");
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Adding Users in Company and validating, Editing , Validating Company Profile And My Profile");
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
			
		}
		else
			fl.disp_Message(driver, "", "", "Testcase Skipped", "", "");
		
		Logs_DigiSurvey.endTestCase(S2);
		
			
	}
	
		
	@DataProvider
	public Object[][] custData3() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),3);
		return testData;
	}
	@Test(dataProvider = "custData3",priority=3)
	public void SurveyFlow(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException
	{
		DOMConfigurator.configure("log4j.xml");
		Logs_DigiSurvey.startTestCase(S2);
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println("Output Test3");
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to creating a survey, Update, Share, EndUser Giving Response and Response Validation On Company Side");
			if(S3!="")
			{
				
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
							  int check=0;
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
										  check++;
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
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Languages")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AllowAnonymous")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "TemplateName")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "LandingPage")],
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
															  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")],
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
											if(check==1)
											  {
												  func_cases.check_CP_Filled(driver, 
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath1")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath2")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "overview")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "background")]);
											  }
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
										  				Question_NUmber,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Languages")],
										  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AllowAnonymous")],
										  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "LandingPage")]);
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
																			 
																  
																		  } 
																		  func_cases.AddQuestionto_CreateSurvey(driver, 
																				  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
																				  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "Tags")],
																				  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")],
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
								  share =0;
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
										  share++;
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
	//added in Hybrid.java(for loop)											 
												
												 
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
//(PENDING_LEAST PRIOIRITY) Need to add LANDING PAGE from create survey sheet Add At end of METHOD									  
									  func_cases.enduser_Login(driver, EndUser_ele[RC.Current_Coulumn_Number(EndUser, "WebLink")],
											  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "EndUserEmail(NewUser&Existed)")],
											  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "password")],
											  EndUser_ele[RC.Current_Coulumn_Number(EndUser, "Anonymous?")],"");
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
															  fl.disp_Message(driver, "", "", list.get(excelAns), AnsFromResponse.get(excelAns), "");
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
			
		}
		else
			fl.disp_Message(driver, "", "", "Testcase Skipped", "", "");
		Logs_DigiSurvey.endTestCase(S2);
	}
	
	@DataProvider
	public Object[][] custData4() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),4);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData4",priority=4)
	public void Create_Survey(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Create Survey");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
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
									  int check=0;
									  System.out.println("for Loop" );
									  System.out.println(S4);
									  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"), create_suvey)); 
									  if (S4.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "LoginWith"),create_suvey)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  check++;
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(create_suvey, "Create_SurveyID"),create_suvey)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int create_suvey_Login=0;create_suvey_Login<create_suvey_col;create_suvey_Login++) 
										  {
											  create_suvey_ele[create_suvey_Login]=RC.getStringCellData(j, create_suvey_Login, create_suvey);
											  System.out.println(create_suvey_ele[create_suvey_Login]); //call login as company method, pass array values
							  
								  
										  }
	//temporary commenting create survey
											  func_cases.create_Survey(driver, create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Name")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Categeory_Notes")], 
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Name")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_Notes")], 
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Group_SubNotes")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyName")], 
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Description")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "SurveyImage")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Languages")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AllowAnonymous")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "TemplateName")],
												  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "LandingPage")],
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
//Temporary commenting add questions													  
													  func_cases.AddQuestionto_CreateSurvey(driver, create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
															  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "Tags")],
															  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")],
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
//temporary commenting											
										  func_cases.save_CreatedSurvey(driver);
											Thread.sleep(4000);
											
										//click drafts > more options > publish
//temporary commenting											
											if(check==1)
											  {
												  func_cases.check_CP_Filled(driver, 
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath1")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "filepath2")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "overview")],
														  create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "background")]);
											  }
											
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
										  				Question_NUmber,create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "Languages")],
										  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "AllowAnonymous")],
										  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "LandingPage")]);
										  		
										  		//Templates Option Removed From Masters
										  		
										  		/*func_cases.checkSurveyTemplates(driver,
										  				create_suvey_ele[RC.Current_Coulumn_Number(create_suvey, "TemplateName")]);*/
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
						
		}
		else
			fl.disp_Message(driver, "", "", "Create survey Skipped", "", "");
	}
	@DataProvider
	public Object[][] custData5() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),5);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData5",priority=5)
	public void Update_Survey(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Update Survey");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
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
								  		if (S5.equals(RC.getStringCellData(update_surveyname_index, RC.Current_Coulumn_Number(Update_suveyName, "UpdateID"),Update_suveyName))&&
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
															
														  } 
														  func_cases.AddQuestionto_CreateSurvey(driver, 
																  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "EnterUrQue")],
																  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "Tags")],
																  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")],
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
						  if(S13!="")
						  {
							  func_cases.CompanyLogout(driver);
						  }
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "", "Update survey Skipped", "", "");
	}
	/*@DataProvider
	public Object[][] custData6() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),6);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData6",priority=6)
	public void Share_Survey(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Share Survey");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
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
										  if(share_survey_ele[RC.Current_Coulumn_Number(share_survey, "SurveyName")]!="")
										  func_cases.share_survey(driver, share_survey_ele[RC.Current_Coulumn_Number(share_survey, "SurveyName")],
												  share_survey_ele[RC.Current_Coulumn_Number(share_survey, "Recipient_Email_IDs")],share);
										  if (S6.equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(share_survey, "ShareID"),share_survey))&&
												  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(share_survey, "LoginWith"),share_survey)))
										  {
											  share++;
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
		}
	}*/
	@DataProvider
	public Object[][] custData7() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),7);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData7",priority=7)
	public void EndUser_SurveyResponse(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		System.out.println("S2 Value Is: "+S2);
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to EndUser Response And Submiting the response");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S7!="")
						  {
							  if(S4==""&&S6!="")
							  {
								    /*get que types  based on surveyname , in share survey sheet search with survey name , 
								   and click view and get no of que & get answer types */
//changed below from 1 to 0			
								  int share_link=0;					  
								  int share_no=0;
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
									  if (S6.equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "ShareID"),sharesurvey))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "LoginWith"),sharesurvey)))
										  // Adduser contains company email_id at 1st column  for validation
									  {
										  share_link++;
										  
										  if(share_link>=2)
										  {
											  System.out.println("share link >=2:"+share_link);
											  System.out.println("Login Company after checking S21");
											  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
										  }
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(j, RC.Current_Coulumn_Number(sharesurvey, "ShareID"),sharesurvey)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int share_survey_ind=0;share_survey_ind<share_survey_col;share_survey_ind++) 
										  {
											  share_survey_ele[share_survey_ind]=RC.getStringCellData(j, share_survey_ind, sharesurvey);
											  System.out.println(share_survey_ele[share_survey_ind]); //call login as company method, pass array values
							  
								  
										  }
										  
										  if(share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")]!="")
											  func_cases.share_survey(driver, share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "SurveyName")],
													  share_survey_ele[RC.Current_Coulumn_Number(sharesurvey, "Recipient_Email_IDs")],share);
											  if (S6.equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(sharesurvey, "ShareID"),sharesurvey))&&
													  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(j+1, RC.Current_Coulumn_Number(sharesurvey, "LoginWith"),sharesurvey)))
											  {
												  share++;
											  }
					//TEMPORARY LOGOUT
										  func_cases.CompanyLogout(driver);
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
											  surveyValidation_queNo=que_type_IndividualShare.length;
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
//===================START
													 /* String  oldTab1 = driver.getWindowHandle();
													  Thread.sleep(3000);
										  			  newTab_robot newTab = new newTab_robot();
										  			  Thread.sleep(3000);
										  			  
										  			  Set<String> set = new HashSet<String>(driver.getWindowHandles());
										  			  for(String tab : set)
										  			  {
														System.out.println("window :"+tab);
										  			  }
										  			  set.remove(oldTab1);
										  			  driver.switchTo().window(set.iterator().next());*/
//================START END										  			  
													  
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
										  				  
										  				  //select Language
										  				  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "Language")]!="")
										  					  func_cases.selectSurveyLanguageByEndUser(driver,
										  						EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "Language")]);
										  			  }
										  			  //String surveylinkURL = driver.getCurrentUrl();
													  
													  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "FillAnswer")]!="")
													  {
														  int count=0;
														  for(int num=0;num<Total_Que;num++)
														  {
															  count++;
														  	func_cases.answer_basedonQueType(driver, num+1, que_Type[num]);
														  }
							//save asks you to Either give anonymous or login Details(Once you logged in you can save or submit your complete survey)
																				  
														  if(count==Total_Que)
														  {
															  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SaveResponse")]!="")
																  func_cases.saveSurvey(driver);
															  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SubmitResponse")]!="")
															  {
																  func_cases.saveSurveySubmit(driver,Total_Que);
															  }
															  //REGISTERD USER SURVEY SUBMIT
															  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "NewUser/Registered")].equals("R"))
															  	func_cases.enduser_Login(driver, EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "WebLink")],
																	  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "EndUserEmail(NewUser&Existed)")],
																	  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "password")],
																	  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "Anonymous?")],view_details[3]);
															  
															  //NEW ANONYMOUS USER SURVEY SUBMIT
															  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "NewUser/Registered")].equals("A"))
																  func_cases.anonymousSurveySubmit(driver,
																		  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "FirstName(Anonymous)")], 
																		  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "LastName(Anonymous)")],
																		  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "EndUserEmail(NewUser&Existed)")], 
																		  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "ContactNumber(Anonymous)")],
																		  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "Anonymous?")],view_details[3]);
														  }
													  }
													  
													  if(EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "SaveResponse")]!="")
													  {
														  
													  }
													  
													  
													  System.out.println("Submit button clicked");
											
//==============START													  
													  /*driver.close();
													  driver.switchTo().window(oldTab);*/
													  driver.get(Environment("Comapany_BaseURL_Digi"));
													  String pageURL=driver.getCurrentUrl();
													  if(!pageURL.contains("/Account/Login")&&
															  EndUser_ele1[RC.Current_Coulumn_Number(EndUser1, "NewUser/Registered")]=="")
														  func_cases.CompanyLogout(driver);
//==============START END													  
												  }
												  if(enduser_index1+1 == RC.getLastrowno(EndUser1))
												  {
													  share_no++;
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
		}
		else
		{
			fl.disp_Message(driver, "", "End-User Survey Response	is Skipped", "", "", "");
		}
	}
	@DataProvider
	public Object[][] custData8() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),8);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData8",priority=8)
	public void ResponseValidation(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Validating the response of EndUser on CompanySide");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  
						  
						  if(S8!="")
						  {
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
									  if (S8.equals(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "ResponseID"),Response_Survey))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Response_Survey_index, RC.Current_Coulumn_Number(Response_Survey, "LoginWith"),Response_Survey)))
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
						//=============START
										  int Total_Que = 0;
										  if(Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")]!="")
										  {
											  //view survey and get details
											  String[] view_details = func_cases.viewSurvey(driver, 
													  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")]);
											  
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
												  String questiontype = DB_QueType.Db_qtype_Survey(Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")], quetext);
												  System.out.println( "Que Type "+questiontype+" and i value "+i);
												  int k=i-1;
												  que_Type[k]=questiontype;
												  que_type_IndividualShare[k]=questiontype;
											  }
											  surveyValidation_queNo=que_type_IndividualShare.length;
										  }
						//=============END
										  //call surveyResponse Method
										  AnsFromResponse =func_cases.surveyresponse_ofEndUsers(driver, 
												  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")],
												  Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "Enduser_Name")]);
										  int AnsFromResponse_Size=AnsFromResponse.size();
										  for(int response=0;response<AnsFromResponse.size();response++)
										  {
											  System.out.println("Response Answers "+AnsFromResponse.get(response));
										  }
										  List<String> list = new ArrayList<>();
										  list.add(Response_Survey_ele[RC.Current_Coulumn_Number(Response_Survey, "SurveyName")]);
										  System.out.println("Total Questions to validate:"+AnsFromResponse.get(1));
										  String strI =String.valueOf(Total_Que);
										  list.add(strI);
										  int Questi = Integer.parseInt(strI);
										  
										  for(int que_typ=1;que_typ<=Questi;que_typ++)
										  {
											  int q_typ=que_typ-1;
											  String Que_type=que_type_IndividualShare[q_typ];
											  System.out.println("Validating Ans:Question type while you sharing:"+Que_type);
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
												  
											  }
										  	  
										  }
									    }
										  int samp=AnsFromResponse_Size-1;
										if(AnsFromResponse.get(samp).equals("English"))
										{
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
											  fl.disp_Message(driver, "", "", list.get(excelAns), AnsFromResponse.get(excelAns), "");
											  if(!list.get(excelAns).equals(AnsFromResponse.get(excelAns)))
											  {
												  Assert.fail("Answers in Excel Not Matched With Answers From Response");
											  }
										  }
										}
										else
										{
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
												  fl.disp_Message(driver, "", "", list.get(excelAns), AnsFromResponse.get(excelAns), "");
												  if(!list.get(excelAns).contains(AnsFromResponse.get(excelAns)))
												  {
													  Assert.fail("Answers in Excel Not Matched With Answers From Response");
												  }
											  }
										}
										  
									  }
								  }
								}
						  }
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Survey Response Validation is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData9() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),9);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData9",priority=9)
	public void createQuiz(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Create Quiz");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S10!="")
							{
								//call create quiz method
								if(S3!="")
								{
									int check=0;
								  String create_quiz=Environment("Sheet_Quiz_Create"); 
								  int create_quiz_row=RC.getLastrowno(create_quiz); 
								  int create_quiz_col=RC.getLastcolmno(create_quiz); 
								  String[] create_quiz_ele=new String[create_quiz_col]; 
								  for (int create_quiz_index = 1; create_quiz_index < RC.getLastrowno(create_quiz); create_quiz_index++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(S10);
									  System.out.println(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"), create_quiz)); 
									  if (S10.equals(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "quizID"),create_quiz))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(create_quiz_index, RC.Current_Coulumn_Number(create_quiz, "LoginWith"),create_quiz)))
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
										  check++;
											if(check==1)
											  {
												  func_cases.check_CP_Filled(driver, 
														  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "filepath")],
														  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "filepath1")],
														  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "filepath2")],
														  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "overview")],
														  create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "background")]);
											  }
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
												
												/*func_cases.publishExistedQuiz(driver,
														create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "QuizName")],"T");*/
												func_cases.checkQuizTemplates(driver,
														create_quiz_ele[RC.Current_Coulumn_Number(create_quiz, "TemplateName")]);
											}
										  
										  
									  }
								  }
								}
							}
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Create Quiz is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData10() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),10);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData10",priority=10)
	public void publishCreatedQuiz(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Publish Quiz");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  
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
									  if (S11.equals(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "publishQuizID"),publish_saved_quiz))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(publish_saved_quiz_index, RC.Current_Coulumn_Number(publish_saved_quiz, "LoginWith"),publish_saved_quiz)))
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
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "filepath1")],
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "filepath2")],
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "overview")],
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "background")]);
										  }
										  func_cases.publishExistedQuiz(driver, 
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "QuizName")], 
												  publish_saved_quiz_ele[RC.Current_Coulumn_Number(publish_saved_quiz, "Proceed")]);
										  
									  }
								  }
								}
						  }
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Publish quiz is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData11() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),11);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData11",priority=11)
	public void UpdateQuiz(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Edit & Update Quiz");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  
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
									  if (S12.equals(RC.getStringCellData(EditUpdateQuiz_index, RC.Current_Coulumn_Number(EditUpdateQuiz, "UpdateID"),EditUpdateQuiz))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(EditUpdateQuiz_index, RC.Current_Coulumn_Number(EditUpdateQuiz, "LoginWith"),EditUpdateQuiz)))
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
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Update Quiz is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData12() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),12);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData12",priority=12)
	public void shareQuizEndUserResponse(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Share Quiz And EndUser Response");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  System.out.println("Login Company using S3");
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  
						  if(S22!="")
						  {
							  if(S21!="")//from share get quizname, for each quiz end user login ,give response and logout
								{
								  //func_cases.companyLogin(driver, currentUsername, currentPassword);
								  int share_link=0;
								  share_quiz=1;
								  int share_no=1;
								  String Quiz_Share=Environment("Sheet_Quiz_Share"); 
								  int Quiz_Share_row=RC.getLastrowno(Quiz_Share); 
								  int Quiz_Share_col=RC.getLastcolmno(Quiz_Share); 
								  String[] Quiz_Share_ele=new String[Quiz_Share_col]; 
								  for (int Quiz_Share_index = 1; Quiz_Share_index < RC.getLastrowno(Quiz_Share); Quiz_Share_index++) 
								  { 
									  //share_quiz=1;
									 // int share_no=1;
									  String url= driver.getCurrentUrl();
									  System.out.println("share login_current driver url:"+url);
									  
									  System.out.println("for Loop" );
									  System.out.println(S21);
									  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"), Quiz_Share)); 
									  if (S21.equals(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "LoginWith"),Quiz_Share)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  share_link++;
										  
										  if(share_link>=2)
										  {
											  System.out.println("share link >=2:"+share_link);
											  System.out.println("Login Company after checking S21");
											  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
										  }
										  
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(Quiz_Share_index, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int Quiz_Share_ind=0;Quiz_Share_ind<Quiz_Share_col;Quiz_Share_ind++) 
										  {
											  Quiz_Share_ele[Quiz_Share_ind]=RC.getStringCellData(Quiz_Share_index, Quiz_Share_ind, Quiz_Share);
											  System.out.println(Quiz_Share_ele[Quiz_Share_ind]); //call login as company method, pass array values
										  }
										  System.out.println("writing quiz link to excel");
										  func_cases.shareQuiz(driver, 
												  Quiz_Share_ele[RC.Current_Coulumn_Number(Quiz_Share, "QuizName")], 
												  share_quiz);
										  if(S21.equals(
												  RC.getStringCellData(Quiz_Share_index+1, RC.Current_Coulumn_Number(Quiz_Share, "Quiz_ShareID"),Quiz_Share))&&
												  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Quiz_Share_index+1, RC.Current_Coulumn_Number(Quiz_Share, "LoginWith"),Quiz_Share)))
										  {
											  share_quiz++;
											  System.out.println("next share quiz existed :"+share_quiz);
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
//QUIZ LANDING PAGE
												  /*func_cases.enduser_Login(driver, Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "WebLink")],
												  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserEmail")],
												  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "password")],
												  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "Anonymous?")],"");*/
												  func_cases.companyLogin(driver, 
														  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "EndUserEmail")],
														  Quiz_EndUser_ele[RC.Current_Coulumn_Number(Quiz_EndUser, "password")]);
				//after login as end user, open new tab, get the quiz link from excel								  
												  String oldTab = driver.getWindowHandle();
												  Thread.sleep(3000);
									  			  newTab_robot newTab = new newTab_robot();
									  			  Thread.sleep(3000);
									  			 
									  			  Set<String> set = new HashSet<String>(driver.getWindowHandles());
									  			  for(String tab : set)
									  			  {
													System.out.println("window :"+tab);
									  			  }
									  			  set.remove(oldTab);
									  			  driver.switchTo().window(set.iterator().next());
									  			  
									  			  //switch to new tab to answer survey, now get the link from excel
								//==========start	  			  
									  			  File src = new File("QuizLink_Excel\\Quiz_Links.xlsx");
									  			  FileInputStream fis;
									  			  fis = new FileInputStream(src);
									  			  XSSFWorkbook wb = new XSSFWorkbook(fis);
									  			  XSSFSheet sheet1 = wb.getSheetAt(0);
									  			  String data0 ;
									  			  for(share_no=1;share_no<=share_quiz;share_no++)
									  			  {
									  			  	if(share_no<=share_quiz)
									  			  	{
									  				  data0 = sheet1.getRow(share_no).getCell(0).getStringCellValue();
									  				  System.out.println("shared quiz:"+share_quiz);
									  				  System.out.println("Open Quiz Link :"+share_no+data0);
									  				  wb.close();
									  				  driver.get(data0);//surveylink opened , now you have to fill that response as an end user
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
									  			  	}
									  			  }
									  			  /*String surveylinkURL = driver.getCurrentUrl();
												  
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
												  driver.switchTo().window(oldTab);*/
										  
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
						  /*if(S13!="")
							  func_cases.CompanyLogout(driver);*/
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Quiz enduser response giving is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData13() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),13);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData13",priority=13)
	public void QuizResponseValidation(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to QuizResponse Validation On Company Side");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  
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
									  if (S25.equals(RC.getStringCellData(Quiz_RespView_index, RC.Current_Coulumn_Number(Quiz_RespView, "QR_ID"),Quiz_RespView))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Quiz_RespView_index, RC.Current_Coulumn_Number(Quiz_RespView, "LoginWith"),Quiz_RespView)))
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
										  
										  
										  /*String[] view_details=func_cases.viewQuiz(driver, Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")]);
										  int Total_Que = 0;
										  Total_Que = Integer.parseInt(view_details[3]);
										  List<String> list = new ArrayList<>();
										  list.add(Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")]);
										  System.out.println("Total Questions to validate:"+Total_Que);
										  String strI =String.valueOf(Total_Que);
										  list.add(strI);
										  int Questi = Integer.parseInt(strI);
										  que_type_IndividualShare=new String[Total_Que];*/
										  
										  String[] view_details=func_cases.viewQuiz(driver, Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")]);
										  int Total_Que = 0;
										  Total_Que = Integer.parseInt(view_details[3]);
										  String[] queType_ele = new String[Total_Que];
										  String[] que_Type = new String[Total_Que];
										  System.out.println("converted Question No to integer : "+Total_Que);
										  List<String> list = new ArrayList<>();
										  list.add(Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")]);
										  String strI =String.valueOf(Total_Que);
										  list.add(strI);
										  que_type_IndividualShare=new String[Total_Que];
										  for(int i=1; i <= Total_Que ; i++)
										  {
											  //queType_ele[i-1] = func_cases.viewQuiz_QueType(driver,i);
											  queType_ele[i-1]=func_cases.viewQuiz_GetQueText(driver, i);
											  String quetext=queType_ele[i-1];
											  System.out.println("Question "+i+":"+quetext);
											  String questiontype = DB_QueType.Db_qtype(Quiz_RespView_ele[RC.Current_Coulumn_Number(Quiz_RespView, "QuizName")], quetext);
											  System.out.println( "Que Type "+questiontype+" and i value "+i);
											  int k=i-1;
											  que_Type[k]=questiontype;
											  que_type_IndividualShare[k]=questiontype;
										  }
										  for(int que_typ=1;que_typ<=Total_Que;que_typ++)
										  {
											  int q_typ=que_typ-1;
											  String Que_type=que_type_IndividualShare[q_typ];
											  System.out.println("Validating Ans:Question type while you sharing:"+Que_type);
											  String Quiz_Answers=Environment("Sheet_Quiz_Answers");
											  int Quiz_Answers_row=RC.getLastrowno(Quiz_Answers); 
											  int Quiz_Answers_col=RC.getLastcolmno(Quiz_Answers); 
											  String[] Quiz_Answers_ele=new String[Quiz_Answers_col];
										  
											  for (int Quiz_Answers_Index = 1; Quiz_Answers_Index < RC.getLastrowno(Quiz_Answers); Quiz_Answers_Index++) 
											  { 
												  System.out.println("for Loop" );
												  System.out.println(Que_type);
												  System.out.println(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"), Quiz_Answers));
												  int times=0;
												  if (Que_type.equals(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"),Quiz_Answers)))
											  			// Adduser contains company email_id at 1st column  for validation
												  {
												  
												  
												  		System.out.println("Matches ID to Register");
												  		System.out.println(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"),Quiz_Answers)); 
												  		//based on j value get the row data and do Adding Users
												   
												  		for(int Quiz_Answers_Ind=0;Quiz_Answers_Ind<Quiz_Answers_col;Quiz_Answers_Ind++) 
												  		{
												  			System.out.println(times);
												  			times++;
												  			Quiz_Answers_ele[Quiz_Answers_Ind]=RC.getStringCellData(Quiz_Answers_Index, Quiz_Answers_Ind, Quiz_Answers);
													  		System.out.println("QueId matches with Createsurvey, and the values are"+Quiz_Answers_ele[Quiz_Answers_Ind]);
												  		}
												  		String[] options = new String[]{Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option1")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option2")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option3")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option4")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option5")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option6")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option7")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option8")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option9")],
												  				Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Option10")]};
												  
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
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Date")]);
												  			break;
												  		
												  			case "File Upload":System.out.println("file to be uploaded");
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "File Upload")]);
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
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Number")]);
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
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Scale / Rate")]);
												  			break;
												  		
												  			case "Single Line Text":System.out.println("single line text to be eneterd");
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Single Line Text")]);
												  			break;
												  		
												  			case "Text Area":System.out.println("text area to be entered");
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "TextArea")]);
												  			break;
												  		
												  			case "Yes or No":System.out.println("Y/N to be selected");
												  			list.add(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Yes or No")]);
												  			break;
												  	}
											  	
											  }
											  else
											  {
												  
											  }
										  	  
										  }
									    }
										  for(int excelAns=0;excelAns<QuizResponse.size();excelAns++)
										  {
											  System.out.println("Excel Ans "+list.get(excelAns));
											  if(list.get(excelAns).equals("Yes"))
											  {
												  String str1=list.get(excelAns);
												  String upperYes = str1.toUpperCase();
												  list.set(excelAns, upperYes);
											  }
											  if(QuizResponse.get(excelAns).contains("Download"))
											  {
												  QuizResponse.set(excelAns, list.get(excelAns));
											  }
											  System.out.println("Response Ans "+QuizResponse.get(excelAns));
											  //fl.disp_Message(driver, "", "", list.get(excelAns), QuizResponse.get(excelAns), "Y");
											  if(list.get(excelAns).equals(QuizResponse.get(excelAns)))
												  fl.disp_Message(driver, "", "", list.get(excelAns), QuizResponse.get(excelAns), "Y");
											  if(!list.get(excelAns).equals(QuizResponse.get(excelAns)))
											  {
												  fl.disp_MessageFailed(driver, "", "", list.get(excelAns), QuizResponse.get(excelAns), "Y");
												 // Assert.fail("Answers in Excel Not Matched With Answers From Response");
											  }
										  }
										  
									  }
								  }
							}
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Quiz enduser response validation is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData14() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),14);
		return testData;
	}
	
	//@SuppressWarnings("null")
	
	@Test(dataProvider = "custData14",priority=14)
	public void SavecreatePetition(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Create and Save Petition ");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S16!="")
							{
								//create petition
								String Create_Petition=Environment("Sheet_Create_Petition"); 
								  int Create_Petition_row=RC.getLastrowno(Create_Petition); 
								  int Create_Petition_col=RC.getLastcolmno(Create_Petition); 
								  String[] Create_Petition_ele=new String[Create_Petition_col]; 
								  for (int Create_Petition_index = 1; Create_Petition_index < RC.getLastrowno(Create_Petition); Create_Petition_index++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(S16);
									  System.out.println(RC.getStringCellData(Create_Petition_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"), Create_Petition)); 
									  if (S16.equals(RC.getStringCellData(Create_Petition_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"),Create_Petition))&&
											  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Create_Petition_index, RC.Current_Coulumn_Number(Create_Petition, "LoginWith"),Create_Petition)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(Create_Petition_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"),Create_Petition)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int Create_Petition_ind=0;Create_Petition_ind<Create_Petition_col;Create_Petition_ind++) 
										  {
											  Create_Petition_ele[Create_Petition_ind]=RC.getStringCellData(Create_Petition_index, Create_Petition_ind, Create_Petition);
											  System.out.println(Create_Petition_ele[Create_Petition_ind]); //call login as company method, pass array values
							  
								  
										  }
										  String existedpetition=func_cases.beforeCreation_checkExistedPetitions(driver, 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CampaignTitle")]);
										  if(existedpetition.equals("false"))
										  {
											  
										  	func_cases.createPetition(driver, 
										  		  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CampaignTitle")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Campaign Type")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Category")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Tags")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "EndDate")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Indefinite")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "VideoLink")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CurrencyType")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FundGoal")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CollectFundLater")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SignatureRequired")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SocialImage")],//
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img1")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img2")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img3")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img4")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Name")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Designation")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img1")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img2")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img3")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img4")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img5")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FAQ")],//PetitionDescription
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "PetitionDescription")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "LinkText")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Link")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "NewWindow")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "BrowseImage")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "ImageLink")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "occupation")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "VisaStatus")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Age")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Salary")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Textbox_Q")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "DropDown_Q")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "No_Options")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options1")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options2")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options3")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options4")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options5")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options6")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options7")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options8")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options9")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Options10")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "submitForApproval")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SaveAsDraft")]);
										  	
										  	//VALIDATE CREATED PETITION
										  	if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Validate")]!="")
										  	{
										  		func_cases.viewPetitionValidation(driver, 
										  			Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CampaignTitle")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Campaign Type")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Category")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Tags")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "EndDate")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Indefinite")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "VideoLink")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CurrencyType")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FundGoal")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SignatureRequired")],
												  
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SocialImage")],//
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img1")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img2")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img3")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Gal_Img4")],
												  
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Name")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Dmaker_Designation")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img1")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img2")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img3")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img4")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Sup_Img5")], 
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FAQ")],//PetitionDescription
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "PetitionDescription")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "LinkText")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Link")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "NewWindow")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "BrowseImage")],
												  Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "ImageLink")]);
										  	}
										  	if(S13!="")
												  func_cases.CompanyLogout(driver);
										  	/*//VALIDATE CREATED PETITION IN WEBSITE
										  	if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Valid_Website")]!="")
										  	{
										  		 String petitiontitle_check=func_cases.companyBase(driver,
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CampaignTitle")]);
										  		 func_cases.ValidatePetiInWebsite(driver, petitiontitle_check, 
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FundGoal")], 
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SignatureRequired")], 
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "PetitionDescription")], 
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "BrowseImage")], 
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "LinkText")],
										  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FAQ")]);
										  		 
										  	}*/
										  }
										  else
										  {
											  System.out.println("Petition already existed in drafts "+Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
											  func_cases.displayMessage_Report(driver,Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Petition_Title")]);
										  }
									  	}
								  }
								  
							}
						  /*if(S13!="")
							  func_cases.CompanyLogout(driver);*/
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Create Petition is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData15() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),15);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData15",priority=15)
	public void publishSavedPetition(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Publish Saved Petition");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S30!="")
						  {
							  String publishSavedPeti=Environment("Sheet_publishSavedPeti"); 
							  int publishSavedPeti_row=RC.getLastrowno(publishSavedPeti); 
							  int publishSavedPeti_col=RC.getLastcolmno(publishSavedPeti); 
							  String[] publishSavedPeti_ele=new String[publishSavedPeti_col]; 
							  for (int publishSavedPeti_index = 1; publishSavedPeti_index < RC.getLastrowno(publishSavedPeti); publishSavedPeti_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S30);
								  System.out.println(RC.getStringCellData(publishSavedPeti_index, RC.Current_Coulumn_Number(publishSavedPeti, "PUB_ID"), publishSavedPeti)); 
								  if (S30.equals(RC.getStringCellData(publishSavedPeti_index, RC.Current_Coulumn_Number(publishSavedPeti, "PUB_ID"),publishSavedPeti))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(publishSavedPeti_index, RC.Current_Coulumn_Number(publishSavedPeti, "LoginWith"),publishSavedPeti)) )
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(publishSavedPeti_index, RC.Current_Coulumn_Number(publishSavedPeti, "PUB_ID"),publishSavedPeti)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int publishSavedPeti_ind=0;publishSavedPeti_ind<publishSavedPeti_col;publishSavedPeti_ind++) 
									  {
										  publishSavedPeti_ele[publishSavedPeti_ind]=RC.getStringCellData(publishSavedPeti_index, publishSavedPeti_ind, publishSavedPeti);
										  System.out.println(publishSavedPeti_ele[publishSavedPeti_ind]); //call login as company method, pass array values  
									  }
									  if(publishSavedPeti_ele[RC.Current_Coulumn_Number(publishSavedPeti, "Publish")]!="")
									  func_cases.publishSavedPetition(driver, 
											  publishSavedPeti_ele[RC.Current_Coulumn_Number(publishSavedPeti, "CampaignTitle")]);
								  }
							  }
							  
						  }
						  if(S13!="")
							  func_cases.CompanyLogout(driver);
					  }
				  }
			}
		}
		else
			fl.disp_MessageFailed(driver, "", "Publish saved petition is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData16() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),16);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods={"SavecreatePetition","publishSavedPetition"}
	
	@Test(dataProvider = "custData16",priority=16,dependsOnMethods={"publishSavedPetition"})
	public void Peti_AprvRrejectByAdmin(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Petition Approved or Rejected by Admin");
			if(S27!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
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
						  // Add user contains company email_id at 1st column  for validation
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
						  if(S31!="")
						  {
							  int first=0;
							  String ApprovePeti=Environment("Sheet_ApprovePeti"); 
							  int ApprovePeti_row=RC.getLastrowno(ApprovePeti); 
							  int ApprovePeti_col=RC.getLastcolmno(ApprovePeti); 
							  String[] ApprovePeti_ele=new String[ApprovePeti_col]; 
							  for (int ApprovePeti_index = 1; ApprovePeti_index < RC.getLastrowno(ApprovePeti); ApprovePeti_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S30);
								  System.out.println(RC.getStringCellData(ApprovePeti_index, RC.Current_Coulumn_Number(ApprovePeti, "AP_ID"), ApprovePeti)); 
								  if (S31.equals(RC.getStringCellData(ApprovePeti_index, RC.Current_Coulumn_Number(ApprovePeti, "AP_ID"),ApprovePeti)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  first++;
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(ApprovePeti_index, RC.Current_Coulumn_Number(ApprovePeti, "AP_ID"),ApprovePeti)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int ApprovePeti_ind=0;ApprovePeti_ind<ApprovePeti_col;ApprovePeti_ind++) 
									  {
										  ApprovePeti_ele[ApprovePeti_ind]=RC.getStringCellData(ApprovePeti_index, ApprovePeti_ind, ApprovePeti);
										  System.out.println(ApprovePeti_ele[ApprovePeti_ind]); //call login as company method, pass array values  
									  }
									  //approve or reject petition By admin
									  func_cases.AprvRejctPetition(driver, 
											  ApprovePeti_ele[RC.Current_Coulumn_Number(ApprovePeti, "CampaignTitle")],
											  ApprovePeti_ele[RC.Current_Coulumn_Number(ApprovePeti, "Approve")], 
											  ApprovePeti_ele[RC.Current_Coulumn_Number(ApprovePeti, "Reject")],
											  ApprovePeti_ele[RC.Current_Coulumn_Number(ApprovePeti, "comments")],first);
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
		{
			fl.disp_MessageFailed(driver, "", "Testcase Skipped", "", "", "");
			//Assert.fail("Testcase Skipped");
		}
	}
	
	
	//@SuppressWarnings("null")//,dependsOnMethods={"SavecreatePetition","publishSavedPetition"}
	
	@Test(dataProvider = "custData14",dependsOnMethods={"Peti_AprvRrejectByAdmin"},priority=17)
	public void validatePetiWebSite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Approved Petition Validation On Website");
			String Login=Environment("Sheet_Login"); 
			  int Login_row=RC.getLastrowno(Login); 
			  int Login_col=RC.getLastcolmno(Login); 
			  String[] Login_ele=new String[Login_col]; 
			  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
			  { 
				  System.out.println("for Loop" );
				  System.out.println(S3);
				  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
				  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
					  // Adduser contains company email_id at 1st column  for validation
				  { 
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int col_Login=0;col_Login<Login_col;col_Login++) 
					  {
						  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
						  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
						  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
						  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
		  
			  
					  } 
					 // func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
					  
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
								  if (S16.equals(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Create_Petition, "Create_PetiID"),Create_Petition))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Indiv_Reg_index, RC.Current_Coulumn_Number(Create_Petition, "LoginWith"),Create_Petition)))
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
									//VALIDATE CREATED PETITION IN WEBSITE
									  	if(Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "Valid_Website")]!="")
									  	{
									  		 String petitiontitle_check=func_cases.companyBase_Petitions(driver,
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "CampaignTitle")]);
									  		 func_cases.ValidatePetiInWebsite(driver, petitiontitle_check, 
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FundGoal")], 
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "SignatureRequired")], 
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "PetitionDescription")], 
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "BrowseImage")], 
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "LinkText")],
									  				Create_Petition_ele[RC.Current_Coulumn_Number(Create_Petition, "FAQ")]);
									  		 
									  	}
								  }
							  }
						}
				  }
			  }
		}
		else
			fl.disp_Message(driver, "", "Validate Petition In website is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData17() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),17);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData17",priority=18)
	public void NewUserSignPetition(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to New User Sign Petition And Donating Fund , validating New User on Company Side");
			if(S17!="")
			{
				List<String> beforeSign = new ArrayList<>();				
			//New user wants to sign petition and creating profile
				String SignPetition_NewUser=Environment("Sheet_SignPetition_NewUser"); 
			    int SignPetition_New_row=RC.getLastrowno(SignPetition_NewUser); 
				int SignPetition_New_col=RC.getLastcolmno(SignPetition_NewUser); 
				String[] SignPetition_New_ele=new String[SignPetition_New_col]; 
				for (int SignPetition_New_index = 1; SignPetition_New_index < RC.getLastrowno(SignPetition_NewUser); SignPetition_New_index++) 
				{ 
					System.out.println("for Loop");
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
						String petitiontitle_check=func_cases.companyBase_Petitions(driver,
						SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "PetitionTitle")]);
						if(SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Validate")]!="")
						{
							beforeSign=func_cases.beforeSignNewUserValidation(driver);
						}
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
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "DOB")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Salary")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Signature")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "CreateProfile")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Password")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Donate?")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "TextAns")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "DropdownAns")],
								SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Amt_Donate")],
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
						  if(SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Validate")]!="")
						  {  
							  	func_cases.afterSignNewUserValidation(driver,beforeSign);
						  	
						  		func_cases.companyLogin(driver, 
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "CreatedBy")], 
								  SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Password")]);
						  		func_cases.viewPetition(driver, SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "PetitionTitle")]);
						  		func_cases.signFundValidInCompany(driver, SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "FirstName")],
										SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "LastName")],
										SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "OrganizationFor")],
										SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Occupation")],
										SignPetition_New_ele[RC.Current_Coulumn_Number(SignPetition_NewUser, "Address")]);
						  		func_cases.CompanyLogout(driver);
						  }
						  
					}
				}
								
			}
		}
		else
			fl.disp_Message(driver, "", "New user Sign petition is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData18() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),18);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData18",priority=19)
	public void ExistedUserSignPetition(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Existed User Sign Petition And Donating Fund , validating New User on Company Side");
			if(S18!="")
			{
				List<String> beforeSign = new ArrayList<>();	
				List<String> signedUserDetails = new ArrayList<>();	
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
						  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
									SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "PetitionTitle")]);
						  if(SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Validate")]!="")
						  		  beforeSign=func_cases.beforeSignNewUserValidation(driver);
						  System.out.println("beforeSign siz: "+beforeSign);
						  func_cases.petitionSign_ExistedUser(driver, petitiontitle_check,
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "emailID")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "password")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Address")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "OrgFor")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Occupation")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Visa")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "DOB")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Salary")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Sign")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Donate?")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "TextAns")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "DropdownAns")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Amt_Donate")],
								  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "ReadTerms")]);
						  signedUserDetails=func_cases.getFirstNameAndLastName(driver);
						  
						  String name=signedUserDetails.get(0);
						  String ocupat=signedUserDetails.get(1);
						  
						  signedUserDetails.remove(1);signedUserDetails.remove(0);
						  System.out.println("Existed USer Sign petition completed, going to validate");
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
						  if(SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Validate")]!="")
						  {
							  System.out.println("After sign by existed User , validating existed user details on company side");
							  func_cases.afterSignNewUserValidation(driver,beforeSign);
							  
							  func_cases.companyLogin(driver, 
									 SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "CreatedBy")], 
									 SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Password")]);
							  func_cases.viewPetition(driver, SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "PetitionTitle")]);
							  func_cases.signFundValidInCompany(driver, name, name, 
									  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "OrgFor")], 
									  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Occupation")],
									  SignPetition_Exist_ele[RC.Current_Coulumn_Number(SignPetition_ExistedUser, "Address")]);
							  func_cases.CompanyLogout(driver);
						  }
					  }
				  }
				  
			}
		}
		else
		{
			fl.disp_Message(driver, "", "existed user sign petition is skipped", "", "", "");
		}
	}
	@DataProvider
	public Object[][] custData19() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),19);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData19",priority=20)
	public void supportTheCause(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Petition Support the cause By a registered Company and "
					+ "supported company validation on Company side");
			if(S32!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				List<String> comp_Info=new ArrayList<>();
				String SupportCause=Environment("Sheet_SupportCause"); 
				  int SupportCause_row=RC.getLastrowno(SupportCause); 
				  int SupportCause_col=RC.getLastcolmno(SupportCause); 
				  String[] SupportCause_ele=new String[SupportCause_col]; 
				  for (int SupportCause_index = 1; SupportCause_index < RC.getLastrowno(SupportCause); SupportCause_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S18);
					  System.out.println(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"), SupportCause)); 
					  if (S32.equals(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"),SupportCause)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"),SupportCause)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SupportCause_ind=0;SupportCause_ind<SupportCause_col;SupportCause_ind++) 
						  {
							  SupportCause_ele[SupportCause_ind]=RC.getStringCellData(SupportCause_index, SupportCause_ind, SupportCause);
							  System.out.println(SupportCause_ele[SupportCause_ind]); //call login as company method, pass array values
						  }
						  
						  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  //below holds company name , comments, name at logout image
						  comp_Info=func_cases.supportCauseExistedCompany(driver, "",
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "EmailID")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Password")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")]);
						  /*System.out.println("After inserting list elements:size is "+compInfo.size());
						  System.out.println("0th element:CompanyName:"+compInfo.get(0));
						  System.out.println("1st element:Comments:"+compInfo.get(1));
						  System.out.println("2nd element:UserName:"+compInfo.get(2));*/
						  func_cases.companyLogin(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "PetiCreatedIn")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comp_Pwd")]);
						  func_cases.viewPetition(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  String beforeSuportCount=comp_Info.get(0);
						  String userName=comp_Info.get(1);
						  String legalName=comp_Info.get(2);
						  System.out.println("beforeSuportCount:"+beforeSuportCount);
						  System.out.println("userName:"+userName);		
						  System.out.println("legalName:"+legalName);
						  
				//add method to accept the supported company
						  
						  
						 int ind=comp_Info.size();
						  int second=ind-1;int first=ind-2;int zero=ind-3;
						  comp_Info.remove(second);comp_Info.remove(first);comp_Info.remove(zero);
						  //below statement return 
						 func_cases.validatingSuportedOrgOnCompSide(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "AcceptByComp")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "RejectByComp")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "RejectComments")],legalName,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")],userName);
						  
						 
						 // System.out.println("After removing list elements:size is "+compInfo.size());
						  func_cases.CompanyLogout(driver);
						  
						  String petitiontitle_check_1=func_cases.companyBase_Petitions(driver,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  
						  func_cases.AfterSupportedCompanyApprovedBy_CompanyWhoCreatedPetitionValidatingInNWebsite(driver, beforeSuportCount, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "EmailID")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")]);
						  
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Support the cause by company is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData20() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),20);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData20",priority=21)
	public void saveCreatedCrowdFund(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Save Created Petition, go to Detailed View And Validate Fields");
			if(S3!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
			  
				  
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S33!="")
						  {
							  String Create_CrowdFund=Environment("Sheet_Create_CrowdFund"); 
							  int Create_CrowdFund_row=RC.getLastrowno(Create_CrowdFund); 
							  int Create_CrowdFund_col=RC.getLastcolmno(Create_CrowdFund); 
							  String[] Create_CrowdFund_ele=new String[Create_CrowdFund_col]; 
							  for (int Create_CrowdFund_index = 1; Create_CrowdFund_index < RC.getLastrowno(Create_CrowdFund); Create_CrowdFund_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S33);
								  System.out.println(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"), Create_CrowdFund)); 
								  if (S33.equals(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"),Create_CrowdFund))&&
										  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "LoginWith"),Create_CrowdFund)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"),Create_CrowdFund)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int Create_CrowdFund_ind=0;Create_CrowdFund_ind<Create_CrowdFund_col;Create_CrowdFund_ind++) 
									  {
										  Create_CrowdFund_ele[Create_CrowdFund_ind]=RC.getStringCellData(Create_CrowdFund_index, Create_CrowdFund_ind, Create_CrowdFund);
										  System.out.println(Create_CrowdFund_ele[Create_CrowdFund_ind]); //call login as company method, pass array values
									  }
									  func_cases.saveCreatedCrowdFund(driver,
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Beneficiary")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BeneficiaryName")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "RelationType")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BeneficiaryImage")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "CampaignTitle")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "CampaignType")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Categeory")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Indefinite")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "EndDate")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "VideoLink")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Tags")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "FundGoal")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Fund")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "SocialMedia")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider1")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider2")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider3")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider4")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc1")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc2")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc3")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc4")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc5")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "FAQ_ID")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Message")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "LinkText")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Link")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "NewWindow")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BrowseImage")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "ImageLink")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Save")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "SubmitForApproval")]);
									  if(Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Validate")]!="")
									  {
									  	String status=func_cases.viewCrowdFund(driver, Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "CampaignTitle")]);
									  	if(status.equals("true"))
									  		func_cases.validateCrowdFund(driver, 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "CampaignType")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Categeory")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Fund")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "EndDate")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BeneficiaryName")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "RelationType")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BeneficiaryImage")],											  
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Tags")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "VideoLink")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Message")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "LinkText")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Link")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "NewWindow")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BrowseImage")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "ImageLink")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "SocialMedia")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider1")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider2")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider3")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Slider4")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc1")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc2")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc3")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc4")],
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Sup_Doc5")], 
											  Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "FAQ_ID")]);
									  }
									  
								  }
							  }
						  }
					  }
				  }
			}
		}
		else
		{
			fl.disp_Message(driver, "", "Create Crowdfunding is skipped", "", "", "");
		}
	}
	@DataProvider
	public Object[][] custData21() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),21);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData21",priority=22)
	public void publishSavedCrowdFund(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32,
			String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Publish saved CrowdFunding");
			if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						 if(S34!="")
						 {
							
							 String PublishCrowdFund=Environment("Sheet_PublishCrowdFund"); 
							 int PublishCrowdFund_row=RC.getLastrowno(PublishCrowdFund); 
							 int PublishCrowdFund_col=RC.getLastcolmno(PublishCrowdFund); 
							 String[] PublishCrowdFund_ele=new String[PublishCrowdFund_col]; 
							 for (int PublishCrowdFund_index = 1; PublishCrowdFund_index < RC.getLastrowno(PublishCrowdFund); PublishCrowdFund_index++) 
							 { 
								 System.out.println("for Loop" );
								 System.out.println(S34);
								 System.out.println(RC.getStringCellData(PublishCrowdFund_index, RC.Current_Coulumn_Number(PublishCrowdFund, "Pub_CFID"), PublishCrowdFund)); 
								 if (S34.equals(RC.getStringCellData(PublishCrowdFund_index, RC.Current_Coulumn_Number(PublishCrowdFund, "Pub_CFID"),PublishCrowdFund)))
									 // Adduser contains company email_id at 1st column  for validation
								 { 
									 System.out.println("Matches ID to Register");
									 System.out.println(RC.getStringCellData(PublishCrowdFund_index, RC.Current_Coulumn_Number(PublishCrowdFund, "Pub_CFID"),PublishCrowdFund)); 
									 //based on j value get the row data and do Adding Users
								   
									 for(int PublishCrowdFund_ind=0;PublishCrowdFund_ind<PublishCrowdFund_col;PublishCrowdFund_ind++) 
									 {
										 PublishCrowdFund_ele[PublishCrowdFund_ind]=RC.getStringCellData(PublishCrowdFund_index, PublishCrowdFund_ind, PublishCrowdFund);
										 System.out.println(PublishCrowdFund_ele[PublishCrowdFund_ind]); //call login as company method, pass array values
									 }
									 List<String> status=func_cases.moreOptionsCrowdFund(driver, PublishCrowdFund_ele[RC.Current_Coulumn_Number(PublishCrowdFund, "CampaignTitle")]);
									 if(status.get(1).equals("true"))
									 {
										 func_cases.publishSavedCrowdFund(driver, status.get(0));
									 }
								 }
							 }
						 }
					  }
				  }
			}
		}
		else
			fl.disp_MessageFailed(driver, "", "Publish Saved CrowdFund is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData22() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),22);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods={"SavecreatePetition","publishSavedPetition"}
	
	@Test(dataProvider = "custData22",dependsOnMethods={"publishSavedCrowdFund"},priority=23)
	public void cf_AprvRrejectByAdmin(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to CrowdFund Approved or Rejected by Admin");
			if(S27!="")
			{
				
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
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
						  // Add user contains company email_id at 1st column  for validation
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
						  if(S35!="")
						  {
							  int first=0;
							  String ApproveCFByAdmin=Environment("Sheet_ApproveCFByAdmin"); 
							  int ApproveCFByAdmin_row=RC.getLastrowno(ApproveCFByAdmin); 
							  int ApproveCFByAdmin_col=RC.getLastcolmno(ApproveCFByAdmin); 
							  String[] ApproveCFByAdmin_ele=new String[ApproveCFByAdmin_col]; 
							  for (int ApproveCFByAdmin_index = 1; ApproveCFByAdmin_index < RC.getLastrowno(ApproveCFByAdmin); ApproveCFByAdmin_index++) 
							  { 
								  System.out.println("for Loop" );
								  System.out.println(S35);
								  System.out.println(RC.getStringCellData(ApproveCFByAdmin_index, RC.Current_Coulumn_Number(ApproveCFByAdmin, "APRV_CFID"), ApproveCFByAdmin)); 
								  if (S35.equals(RC.getStringCellData(ApproveCFByAdmin_index, RC.Current_Coulumn_Number(ApproveCFByAdmin, "APRV_CFID"),ApproveCFByAdmin)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  first++;
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(ApproveCFByAdmin_index, RC.Current_Coulumn_Number(ApproveCFByAdmin, "APRV_CFID"),ApproveCFByAdmin)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int ApproveCFByAdmin_ind=0;ApproveCFByAdmin_ind<ApproveCFByAdmin_col;ApproveCFByAdmin_ind++) 
									  {
										  ApproveCFByAdmin_ele[ApproveCFByAdmin_ind]=RC.getStringCellData(ApproveCFByAdmin_index, ApproveCFByAdmin_ind, ApproveCFByAdmin);
										  System.out.println(ApproveCFByAdmin_ele[ApproveCFByAdmin_ind]); //call login as company method, pass array values  
									  }
									  //approve or reject petition By admin
									  func_cases.AprvRejctCrowdFund(driver, 
											  ApproveCFByAdmin_ele[RC.Current_Coulumn_Number(ApproveCFByAdmin, "CrowdFundTitle")],
											  ApproveCFByAdmin_ele[RC.Current_Coulumn_Number(ApproveCFByAdmin, "Approve")], 
											  ApproveCFByAdmin_ele[RC.Current_Coulumn_Number(ApproveCFByAdmin, "Reject")],
											  ApproveCFByAdmin_ele[RC.Current_Coulumn_Number(ApproveCFByAdmin, "comments")],first);
								 }
							  }
						  }
					  }
				  }
			}
		}
		else
			fl.disp_MessageFailed(driver, "", "Clidiem admin approving or rejecting crowdfund is skipped", "", "", "");
	}
	@Test(dataProvider = "custData20",dependsOnMethods={"cf_AprvRrejectByAdmin"},priority=24)
	public void validateCF_WebSite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Validate Approved CrowdFund In WebSite Side");
			String Login=Environment("Sheet_Login"); 
			  int Login_row=RC.getLastrowno(Login); 
			  int Login_col=RC.getLastcolmno(Login); 
			  String[] Login_ele=new String[Login_col]; 
			  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
			  { 
				  System.out.println("for Loop" );
				  System.out.println(S3);
				  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
				  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
					  // Adduser contains company email_id at 1st column  for validation
				  { 
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int col_Login=0;col_Login<Login_col;col_Login++) 
					  {
						  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
						  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
						  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
						  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
					  } 
					 // func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
					  
					  if(S33!="")
						{
							//Validate info in created crowdFund with Website Info
						  String Create_CrowdFund=Environment("Sheet_Create_CrowdFund"); 
						  int Create_CrowdFund_row=RC.getLastrowno(Create_CrowdFund); 
						  int Create_CrowdFund_col=RC.getLastcolmno(Create_CrowdFund); 
						  String[] Create_CrowdFund_ele=new String[Create_CrowdFund_col]; 
						  for (int Create_CrowdFund_index = 1; Create_CrowdFund_index < RC.getLastrowno(Create_CrowdFund); Create_CrowdFund_index++) 
						  { 
							  System.out.println("for Loop" );
							  System.out.println(S33);
							  System.out.println(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"), Create_CrowdFund)); 
							  if (S33.equals(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"),Create_CrowdFund))&&
									  Login_ele[RC.Current_Coulumn_Number(Login, "Username")].equals(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "LoginWith"),Create_CrowdFund)))
								  // Adduser contains company email_id at 1st column  for validation
							  { 
								  System.out.println("Matches ID to Register");
								  System.out.println(RC.getStringCellData(Create_CrowdFund_index, RC.Current_Coulumn_Number(Create_CrowdFund, "CF_ID"),Create_CrowdFund)); 
								  //based on j value get the row data and do Adding Users
								   
								  for(int Create_CrowdFund_ind=0;Create_CrowdFund_ind<Create_CrowdFund_col;Create_CrowdFund_ind++) 
								  {
									  Create_CrowdFund_ele[Create_CrowdFund_ind]=RC.getStringCellData(Create_CrowdFund_index, Create_CrowdFund_ind, Create_CrowdFund);
									  System.out.println(Create_CrowdFund_ele[Create_CrowdFund_ind]); //call login as company method, pass array values
								  }
									//VALIDATE CREATED CROWD FUND IN WEBSITE
									  	if(Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Valid_Website")]!="")
									  	{
									  		 String CFtitle_check=func_cases.companyBase_CrowdFund(driver,
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "CampaignTitle")]);
									  		 func_cases.ValidateCFInWebsite(driver, CFtitle_check, 
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Fund")], 
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "Message")], 
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "BrowseImage")], 
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "LinkText")],
									  				Create_CrowdFund_ele[RC.Current_Coulumn_Number(Create_CrowdFund, "FAQ_ID")]);
									  	}
								  }
							  }
						}
				  }
			  }
		}
		else
			fl.disp_Message(driver, "", "Validate Crowdfund in website is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData23() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),23);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData23",priority=25)
	public void supportTheCauseCF(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Crowd Fund Support the cause By a registered Company and "
					+ "supported company validation on Company side");
			if(S32!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String SupportCause=Environment("Sheet_SupportCause"); 
				  int SupportCause_row=RC.getLastrowno(SupportCause); 
				  int SupportCause_col=RC.getLastcolmno(SupportCause); 
				  String[] SupportCause_ele=new String[SupportCause_col]; 
				  for (int SupportCause_index = 1; SupportCause_index < RC.getLastrowno(SupportCause); SupportCause_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S18);
					  System.out.println(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"), SupportCause)); 
					  if (S32.equals(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"),SupportCause)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SupportCause_index, RC.Current_Coulumn_Number(SupportCause, "SC_ID"),SupportCause)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SupportCause_ind=0;SupportCause_ind<SupportCause_col;SupportCause_ind++) 
						  {
							  SupportCause_ele[SupportCause_ind]=RC.getStringCellData(SupportCause_index, SupportCause_ind, SupportCause);
							  System.out.println(SupportCause_ele[SupportCause_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  //below holds company name , comments, name at logout image
						  compInfo=func_cases.supportCauseExistedCompany(driver,"" ,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "EmailID")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Password")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")]);
						  System.out.println("After inserting list elements:size is "+compInfo.size());
						  System.out.println("0th element:CompanyName:"+compInfo.get(0));
						  System.out.println("1st element:Comments:"+compInfo.get(1));
						  System.out.println("2nd element:UserName:"+compInfo.get(2));
						  func_cases.companyLogin(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "PetiCreatedIn")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comp_Pwd")]);
						  func_cases.viewCrowdFund(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  
						  String beforeSuportCount=compInfo.get(0);
						  String userName=compInfo.get(1);
						  String legalName=compInfo.get(2);
						  System.out.println("beforeSuportCount:"+beforeSuportCount);
						  System.out.println("userName:"+userName);		
						  System.out.println("legalName:"+legalName);
						  
				//add method to accept the supported company
						  
						  
						 int ind=compInfo.size();
						  int second=ind-1;int first=ind-2;int zero=ind-3;
						  compInfo.remove(second);compInfo.remove(first);compInfo.remove(zero);
						  /*int ind=compInfo.size();
						  int second=ind-1;int first=ind-2;int zero=ind-3;*/
						  func_cases.validatingSuportedOrgOnCompSide(driver, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "AcceptByComp")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "RejectByComp")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "RejectComments")], legalName,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")],userName);
						  
						//  compInfo.remove(second);compInfo.remove(first);compInfo.remove(zero);
						  System.out.println("After removing list elements:size is "+compInfo.size());
						  func_cases.CompanyLogout(driver);
						  
						  String petitiontitle_check_1=func_cases.companyBase_CrowdFund(driver,
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Title")]);
						  func_cases.AfterSupportedCompanyApprovedBy_CompanyWhoCreatedPetitionValidatingInNWebsite(driver, beforeSuportCount, 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "EmailID")], 
								  SupportCause_ele[RC.Current_Coulumn_Number(SupportCause, "Comments")]);
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Crowdfund Support the cause is Skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData24() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),24);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData24",priority=26)
	public void supportTheCauseNewUserCF(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Support the cause By a New Company and "
					+ "supported NewCompany validation on Company side");
			if(S36!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String SupportCause_NewUser=Environment("Sheet_SupportCause_NewUser"); 
				  int SupportCause_NewUser_row=RC.getLastrowno(SupportCause_NewUser); 
				  int SupportCause_NewUser_col=RC.getLastcolmno(SupportCause_NewUser); 
				  String[] SupportCause_NewUser_ele=new String[SupportCause_NewUser_col]; 
				  for (int SupportCause_NewUser_index = 1; SupportCause_NewUser_index < RC.getLastrowno(SupportCause_NewUser); SupportCause_NewUser_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S36);
					  System.out.println(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"), SupportCause_NewUser)); 
					  if (S36.equals(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"),SupportCause_NewUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"),SupportCause_NewUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SupportCause_NewUser_ind=0;SupportCause_NewUser_ind<SupportCause_NewUser_col;SupportCause_NewUser_ind++) 
						  {
							  SupportCause_NewUser_ele[SupportCause_NewUser_ind]=RC.getStringCellData(SupportCause_NewUser_index, SupportCause_NewUser_ind, SupportCause_NewUser);
							  System.out.println(SupportCause_NewUser_ele[SupportCause_NewUser_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver,
								  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
						  //below holds company name , comments, name at logout image
						  
						  int firsttimeRegister=1;
							if(SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")]!="")
							{
								//company Registration
								  String Org_Reg=Environment("Sheet_OrganizationRegister"); 
								  int Org_Reg_row=RC.getLastrowno(Org_Reg); 
								  int Org_Reg_col=RC.getLastcolmno(Org_Reg); 
								  String[] Org_Reg_ele=new String[Org_Reg_col]; 
								  for (int Org_Reg_index = 1; Org_Reg_index < RC.getLastrowno(Org_Reg); Org_Reg_index++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")]);
									  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"), Org_Reg)); 
									  if (SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")].equals(
											  RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"),Org_Reg)))
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
										  if(S26=="")//Only Company Registration, Not Refering Anyone
										  {
											  func_cases.supportCauseNewCompany(driver, firsttimeRegister, 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
											  //func_cases.CompanyLogout(driver);
										  }
										  if(firsttimeRegister==1&&S26!="")//Registered Company Sharing Reference Link with Friends
										  {
											  func_cases.supportCauseNewCompany(driver, firsttimeRegister, 
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
											  	//func_cases.CompanyLogout(driver);
											  	firsttimeRegister++;
										  }
				//After Registering a new company, Again you need to Click on Support cause login with which you have registered
										  
										  compInfo=func_cases.supportCauseExistedCompany(driver, "status",
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")]);
										  System.out.println("After inserting list elements:size is "+compInfo.size());
										  System.out.println("0th element:CompanyName:"+compInfo.get(0));
										  System.out.println("1st element:Comments:"+compInfo.get(1));
										  System.out.println("2nd element:UserName:"+compInfo.get(2));
										  func_cases.companyLogin(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "PetiCreatedIn")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comp_Pwd")]);
										  func_cases.viewCrowdFund(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
										  String beforeSuportCount=compInfo.get(0);
										  String userName=compInfo.get(1);
										  String legalName=compInfo.get(2);
										  
										  int ind=compInfo.size();
										  int second=ind-1;int first=ind-2;int zero=ind-3;
										  compInfo.remove(second);compInfo.remove(first);compInfo.remove(zero);
										  
										  /*int ind=compInfo.size();
										  int second=ind-1;int first=ind-2;int zero=ind-3;*/
										  func_cases.validatingSuportedOrgOnCompSide(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "AcceptByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectComments")], legalName, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")], userName);
										  
										  //compInfo.remove(second);compInfo.remove(first);compInfo.remove(zero);
										  System.out.println("After removing list elements:size is "+compInfo.size());
										  func_cases.CompanyLogout(driver);
										  
										  String petitiontitle_check_1=func_cases.companyBase_CrowdFund(driver,
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
										  func_cases.AfterSupportedCompanyApprovedBy_CompanyWhoCreatedPetitionValidatingInNWebsite(driver, beforeSuportCount, 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")],  
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")]);
									  }
								  }
							}
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Support the cause new user crowdfund is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData25() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),25);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData25",priority=27)
	public void supportTheCauseNewUserPeti(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32,
			String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Support the cause By a New Company and "
					+ "supported NewCompany validation on Company side");
			if(S36!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String SupportCause_NewUser=Environment("Sheet_SupportCause_NewUser"); 
				  int SupportCause_NewUser_row=RC.getLastrowno(SupportCause_NewUser); 
				  int SupportCause_NewUser_col=RC.getLastcolmno(SupportCause_NewUser); 
				  String[] SupportCause_NewUser_ele=new String[SupportCause_NewUser_col]; 
				  for (int SupportCause_NewUser_index = 1; SupportCause_NewUser_index < RC.getLastrowno(SupportCause_NewUser); SupportCause_NewUser_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S36);
					  System.out.println(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"), SupportCause_NewUser)); 
					  if (S36.equals(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"),SupportCause_NewUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(SupportCause_NewUser_index, RC.Current_Coulumn_Number(SupportCause_NewUser, "Sup_NewID"),SupportCause_NewUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int SupportCause_NewUser_ind=0;SupportCause_NewUser_ind<SupportCause_NewUser_col;SupportCause_NewUser_ind++) 
						  {
							  SupportCause_NewUser_ele[SupportCause_NewUser_ind]=RC.getStringCellData(SupportCause_NewUser_index, SupportCause_NewUser_ind, SupportCause_NewUser);
							  System.out.println(SupportCause_NewUser_ele[SupportCause_NewUser_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
								  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
						  //below holds company name , comments, name at logout image
						  
						  int firsttimeRegister=1;
							if(SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")]!="")
							{
								//company Registration
								  String Org_Reg=Environment("Sheet_OrganizationRegister"); 
								  int Org_Reg_row=RC.getLastrowno(Org_Reg); 
								  int Org_Reg_col=RC.getLastcolmno(Org_Reg); 
								  String[] Org_Reg_ele=new String[Org_Reg_col]; 
								  for (int Org_Reg_index = 1; Org_Reg_index < RC.getLastrowno(Org_Reg); Org_Reg_index++) 
								  { 
									  System.out.println("for Loop" );
									  System.out.println(SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")]);
									  System.out.println(RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"), Org_Reg)); 
									  if (SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "OrgRegID")].equals(
											  RC.getStringCellData(Org_Reg_index, RC.Current_Coulumn_Number(Org_Reg, "OrgRegID"),Org_Reg)))
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
										  if(S26=="")//Only Company Registration, Not Refering Anyone
										  {
											  func_cases.supportCauseNewCompany(driver, firsttimeRegister, 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "YourName")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ContactNo")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "FEIN")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Website")],
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "ConfirmPassword")], 
													  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Code")]);
											  //func_cases.CompanyLogout(driver);
										  }
										  if(firsttimeRegister==1&&S26!="")//Registered Company Sharing Reference Link with Friends
										  {
											  func_cases.supportCauseNewCompany(driver, firsttimeRegister, 
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
											  	//func_cases.CompanyLogout(driver);
											  	firsttimeRegister++;
										  }
				//After Registering a new company, Again you need to Click on Support cause login with which you have registered
										  
										  /*compInfo=func_cases.supportCauseExistedCompany(driver, "status",
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")]);*/
										  
										  String totOrgBeforSuport=func_cases.supportCauseNewUserAfterRegister(driver, "status",
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")]);
										  func_cases.approveSupportCauseRegisterCompany(driver,
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "PetiCreatedIn")],
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comp_Pwd")],
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")],
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "AcceptByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectComments")],
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgName")]);
										  String petitiontitle_check_1=func_cases.companyBase_Petitions(driver,
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
										  compInfo=func_cases.supportCauseNewUserAfterRegisterValidation(driver, 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "OrgEmailID")], 
												  Org_Reg_ele[RC.Current_Coulumn_Number(Org_Reg, "Password")],
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comments")], totOrgBeforSuport);
										  int ind=compInfo.size();
										  System.out.println("compInfo Size::"+ind);
										  int second=ind-1;int first=ind-2;int zero=ind-3;
										  System.out.println("compInfo index 0 value is:"+compInfo.get(zero));
										  System.out.println("compInfo index 1 value is:"+compInfo.get(first));
										  System.out.println("compInfo index 2 value is:"+compInfo.get(second));
										  
										  func_cases.companyLogin(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "PetiCreatedIn")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Comp_Pwd")]);
										  func_cases.viewPetition(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "Title")]);
										  
										  /* func_cases.validatingSuportedOrgOnCompSide(driver, 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "AcceptByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectByComp")], 
												  SupportCause_NewUser_ele[RC.Current_Coulumn_Number(SupportCause_NewUser, "RejectComments")], 
												  compInfo.get(zero), compInfo.get(first), compInfo.get(second));*/
										  
										  func_cases.validatingNewSuportedOrgOnCompSide(driver, compInfo.get(zero), compInfo.get(first), compInfo.get(second));
										  
										  compInfo.remove(second);compInfo.remove(first);compInfo.remove(zero);
										  System.out.println("After removing list elements:size is "+compInfo.size());
										  func_cases.CompanyLogout(driver);
									  }
								  }
							}
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Support the cause By New User is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData26() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),26);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData26",priority=28)
	public void commentOnCompanySide(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Commenting a petition by Company");
			if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S37!="")
							 {
								
								 String PetiComm_CompSide=Environment("Sheet_PetiComm_CompSide"); 
								 int PetiComm_CompSide_row=RC.getLastrowno(PetiComm_CompSide); 
								 int PetiComm_CompSide_col=RC.getLastcolmno(PetiComm_CompSide); 
								 String[] PetiComm_CompSide_ele=new String[PetiComm_CompSide_col]; 
								 for (int PetiComm_CompSide_index = 1; PetiComm_CompSide_index < RC.getLastrowno(PetiComm_CompSide); PetiComm_CompSide_index++) 
								 { 
									 System.out.println("for Loop" );
									 System.out.println(S34);
									 System.out.println(RC.getStringCellData(PetiComm_CompSide_index, RC.Current_Coulumn_Number(PetiComm_CompSide, "PC_CompID"), PetiComm_CompSide)); 
									 if (S37.equals(RC.getStringCellData(PetiComm_CompSide_index, RC.Current_Coulumn_Number(PetiComm_CompSide, "PC_CompID"),PetiComm_CompSide)))
										 // Adduser contains company email_id at 1st column  for validation
									 { 
										 System.out.println("Matches ID to Register");
										 System.out.println(RC.getStringCellData(PetiComm_CompSide_index, RC.Current_Coulumn_Number(PetiComm_CompSide, "PC_CompID"),PetiComm_CompSide)); 
										 //based on j value get the row data and do Adding Users
									   
										 for(int PetiComm_CompSide_ind=0;PetiComm_CompSide_ind<PetiComm_CompSide_col;PetiComm_CompSide_ind++) 
										 {
											 PetiComm_CompSide_ele[PetiComm_CompSide_ind]=RC.getStringCellData(PetiComm_CompSide_index, PetiComm_CompSide_ind, PetiComm_CompSide);
											 System.out.println(PetiComm_CompSide_ele[PetiComm_CompSide_ind]); //call login as company method, pass array values
										 }
										 func_cases.viewPetition(driver, PetiComm_CompSide_ele[RC.Current_Coulumn_Number(PetiComm_CompSide, "Petition")]);
										 List<String> status=func_cases.petitionNewCommentsOnCompanySide(driver, 
												 PetiComm_CompSide_ele[RC.Current_Coulumn_Number(PetiComm_CompSide, "NewComment")]);
										 func_cases.CompanyLogout(driver);
										 func_cases.companyBase_Petitions(driver, PetiComm_CompSide_ele[RC.Current_Coulumn_Number(PetiComm_CompSide, "Petition")]);
										 func_cases.petition_NewCommentByCompValidatingOnWebSite(driver, status.get(1), status.get(2));
									 }
								 }
							 }
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Comment on Company Side is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData27() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),27);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData27",priority=29)
	public void peti_replyCommentByExistedUserInWebsite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a Reply Comment To a Petition In WebSite By Existed User");
			if(S38!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String replyComent_InWebByExisted=Environment("Sheet_replyComent_InWebByExisted"); 
				  int replyComent_InWebByExisted_row=RC.getLastrowno(replyComent_InWebByExisted); 
				  int replyComent_InWebByExisted_col=RC.getLastcolmno(replyComent_InWebByExisted); 
				  String[] replyComent_InWebByExisted_ele=new String[replyComent_InWebByExisted_col]; 
				  for (int replyComent_InWebByExisted_index = 1; replyComent_InWebByExisted_index < RC.getLastrowno(replyComent_InWebByExisted); replyComent_InWebByExisted_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S36);
					  System.out.println(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(replyComent_InWebByExisted, "RC_ExiID"), replyComent_InWebByExisted)); 
					  if (S38.equals(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(replyComent_InWebByExisted, "RC_ExiID"),replyComent_InWebByExisted)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(replyComent_InWebByExisted, "RC_ExiID"),replyComent_InWebByExisted)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int replyComent_InWebByExisted_ind=0;replyComent_InWebByExisted_ind<replyComent_InWebByExisted_col;replyComent_InWebByExisted_ind++) 
						  {
							  replyComent_InWebByExisted_ele[replyComent_InWebByExisted_ind]=RC.getStringCellData(replyComent_InWebByExisted_index, replyComent_InWebByExisted_ind, replyComent_InWebByExisted);
							  System.out.println(replyComent_InWebByExisted_ele[replyComent_InWebByExisted_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
								  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(replyComent_InWebByExisted, "Petition")]);
						  if(petitiontitle_check.equals("true"))
						  {
							  func_cases.petitionReplyCommentToARecentCommentInWebSite_ByExistedUser(driver, 
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(replyComent_InWebByExisted, "ReplyComment")], 
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(replyComent_InWebByExisted, "Email")],
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(replyComent_InWebByExisted, "Password")]);
						  }
						  
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Reply Comment to petition in Website by existed user is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData28() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),28);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData28",priority=30)
	public void peti_newCommentInWebsite_ByExistedUser(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a New Comment To a Petition In WebSite By Existed User");
			if(S39!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String NewCommentInWeb_Existed=Environment("Sheet_NewCommentInWeb_Existed"); 
				  int NewCommentInWeb_Existed_row=RC.getLastrowno(NewCommentInWeb_Existed); 
				  int NewCommentInWeb_Existed_col=RC.getLastcolmno(NewCommentInWeb_Existed); 
				  String[] NewCommentInWeb_Existed_ele=new String[NewCommentInWeb_Existed_col]; 
				  for (int NewCommentInWeb_Existed_index = 1; NewCommentInWeb_Existed_index < RC.getLastrowno(NewCommentInWeb_Existed); NewCommentInWeb_Existed_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S39);
					  System.out.println(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComInWebExistedID"), NewCommentInWeb_Existed)); 
					  if (S39.equals(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComInWebExistedID"),NewCommentInWeb_Existed)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComInWebExistedID"),NewCommentInWeb_Existed)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int NewCommentInWeb_Existed_ind=0;NewCommentInWeb_Existed_ind<NewCommentInWeb_Existed_col;NewCommentInWeb_Existed_ind++) 
						  {
							  NewCommentInWeb_Existed_ele[NewCommentInWeb_Existed_ind]=RC.getStringCellData(NewCommentInWeb_Existed_index, NewCommentInWeb_Existed_ind, NewCommentInWeb_Existed);
							  System.out.println(NewCommentInWeb_Existed_ele[NewCommentInWeb_Existed_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_Petitions(driver, 
								  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "Petition")]);
						  if(petitiontitle_check.equals("true"))
						  {
							  func_cases.newCommentPostInWebSite(driver, 
									  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComment")]);
							  String name=func_cases.newCommentByExisterUserSignIn(driver, 
									  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "ExistedUserEmail")], 
									  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "ExistedUserPassword")],
									  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "Petition")]);
							 // func_cases.companyBase_Petitions(driver, NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "Petition")]);
							  //Thread.sleep(10000);
							  func_cases.petition_NewCommentByCompValidatingOnWebSite(driver,
									  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComment")],name);
							  if(S3!="")
							  {
								  func_cases.companyLogin(driver, 
										  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "PetiCreatedIn")], 
										  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "PetiCreatePwd")]);
								  func_cases.viewPetition(driver, 
										  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "Petition")]);
								  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
										  NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(NewCommentInWeb_Existed, "NewComment")],
										  name);
								  func_cases.CompanyLogout(driver);
							  }
						  }
					  }
				  }
			}
		}
	}
	@DataProvider
	public Object[][] custData29() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),29);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData29",priority=31)
	public void peti_newCommentInWebsite_ByNewUser(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a New Comment To a Petition In WebSite By New(Individual or Oranization) User");
			if(S40!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String NewCOmmentInWeb_NewUser=Environment("Sheet_NewCOmmentInWeb_NewUser"); 
				  int NewCOmmentInWeb_NewUser_row=RC.getLastrowno(NewCOmmentInWeb_NewUser); 
				  int NewCOmmentInWeb_NewUser_col=RC.getLastcolmno(NewCOmmentInWeb_NewUser); 
				  String[] NewCOmmentInWeb_NewUser_ele=new String[NewCOmmentInWeb_NewUser_col]; 
				  for (int NewCOmmentInWeb_NewUser_index = 1; NewCOmmentInWeb_NewUser_index < RC.getLastrowno(NewCOmmentInWeb_NewUser); NewCOmmentInWeb_NewUser_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S40);
					  System.out.println(RC.getStringCellData(NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NC_NewUserID"), NewCOmmentInWeb_NewUser)); 
					  if (S40.equals(RC.getStringCellData(NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NC_NewUserID"),NewCOmmentInWeb_NewUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NC_NewUserID"),NewCOmmentInWeb_NewUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int NewCOmmentInWeb_NewUser_ind=0;NewCOmmentInWeb_NewUser_ind<NewCOmmentInWeb_NewUser_col;NewCOmmentInWeb_NewUser_ind++) 
						  {
							  NewCOmmentInWeb_NewUser_ele[NewCOmmentInWeb_NewUser_ind]=RC.getStringCellData(NewCOmmentInWeb_NewUser_index, NewCOmmentInWeb_NewUser_ind, NewCOmmentInWeb_NewUser);
							  System.out.println(NewCOmmentInWeb_NewUser_ele[NewCOmmentInWeb_NewUser_ind]); //call login as company method, pass array values
						  }
						   
							  if(NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndiv")].equals("Org"))
							  {
								  int firsttimeRegister=1;
								  String OrganizationRegister=Environment("Sheet_OrganizationRegister"); 
								  int OrganizationRegister_row=RC.getLastrowno(OrganizationRegister); 
								  int OrganizationRegister_col=RC.getLastcolmno(OrganizationRegister); 
								  String[] OrganizationRegister_ele=new String[OrganizationRegister_col]; 
								  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(OrganizationRegister); OrganizationRegister_index++) 
								  { 
									  System.out.println("for Loop");
									  System.out.println(NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndivID")]);
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"), OrganizationRegister)); 
									  if (NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndivID")].equals(
											  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<OrganizationRegister_col;OrganizationRegister_ind++) 
										  {
											  OrganizationRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, OrganizationRegister);
											  System.out.println(OrganizationRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
										  }
										  
										  String petitiontitle_check=func_cases.companyBase_Petitions(driver, 
												  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
										  if(petitiontitle_check.equals("true"))
										  {
											  func_cases.newCommentPostInWebSite(driver, 
													  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")]);
										  
											  String name=func_cases.newCommentByNewCompanyRegisterSignIn(driver, 
												  firsttimeRegister,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "YourName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ContactNo")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "FEIN")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Website")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ConfirmPassword")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Code")], 
												  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
											  
											  func_cases.petition_NewCommentByCompValidatingOnWebSite(driver,
													  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")],name);
											  if(S3!="")
											  {
												  func_cases.companyLogin(driver, 
														  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "PetiCreatedIn")], 
														  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "PetiCreatePwd")]);
												  func_cases.viewPetition(driver, 
														  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
												  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
														  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")],
														  name);
												  func_cases.CompanyLogout(driver);
											  }
										  }
										  
									  }
								  }
							  }
							  else
							  {
								  if(NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndiv")].equals("Indiv"))
								  {
									  int firsttimeRegister=1;
									  String IndividualRegister=Environment("Sheet_IndividualRegister"); 
										 int IndividualRegister_row=RC.getLastrowno(IndividualRegister); 
										 int IndividualRegister_col=RC.getLastcolmno(IndividualRegister); 
										 String[] IndividualRegister_ele=new String[IndividualRegister_col]; 
										 for (int IndividualRegister_index = 1; IndividualRegister_index < RC.getLastrowno(IndividualRegister); IndividualRegister_index++) 
										 { 
											 System.out.println("for Loop" );
											 System.out.println(NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndivID")]);
											 System.out.println(RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"), IndividualRegister)); 
											 if (NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "OrgORIndivID")].equals(
													 RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)))
												 // Adduser contains company email_id at 1st column  for validation
											 { 
												 System.out.println("Matches ID to Register");
												 System.out.println(RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)); 
												 //based on j value get the row data and do Adding Users
											   
												 for(int IndividualRegister_ind=0;IndividualRegister_ind<IndividualRegister_col;IndividualRegister_ind++) 
												 {
													 IndividualRegister_ele[IndividualRegister_ind]=RC.getStringCellData(IndividualRegister_index, IndividualRegister_ind, IndividualRegister);
													 System.out.println(IndividualRegister_ele[IndividualRegister_ind]); //call login as company method, pass array values
												 }
												 
												 String petitiontitle_check=func_cases.companyBase_Petitions(driver, 
														  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
												  if(petitiontitle_check.equals("true"))
												  {
													  func_cases.newCommentPostInWebSite(driver, 
															  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")]);
												  
													  String name=func_cases.peti_newCommentByNewIndividualRegisterSignIn(driver, 
														 firsttimeRegister, 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "FirstName")], 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "LastName")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ContactNumber")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ConfirmPassword")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Captcha")], 
														 NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
													 /* func_cases.petitionNewCommentByCompValidatingOnWebSite(driver,
															  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")],name);*/
													  func_cases.petition_NewCommentByCompValidatingOnWebSite(driver,
															  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")],name);
													  if(S3!="")
													  {
														  func_cases.companyLogin(driver, 
																  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "PetiCreatedIn")], 
																  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "PetiCreatePwd")]);
														  func_cases.viewPetition(driver, 
																  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "Petition")]);
														  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
																  NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(NewCOmmentInWeb_NewUser, "NewComment")],
																  name);
														  func_cases.CompanyLogout(driver);
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
	}
	@DataProvider
	public Object[][] custData30() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),30);
		return testData;
	}
	@Test(dataProvider = "custData30",priority=31)
	public void peti_replyCommentByNewUserInWebsite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a Reply Comment To a Petition In WebSite By New User(Individual/ Company)");
			if(S41!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String replyComent_InWebByNew=Environment("Sheet_replyComent_InWebByNew"); 
				  int replyComent_InWebByNew_row=RC.getLastrowno(replyComent_InWebByNew); 
				  int replyComent_InWebByNew_col=RC.getLastcolmno(replyComent_InWebByNew); 
				  String[] replyComent_InWebByNew_ele=new String[replyComent_InWebByNew_col]; 
				  for (int replyComent_InWebByNew_index = 1; replyComent_InWebByNew_index < RC.getLastrowno(replyComent_InWebByNew); replyComent_InWebByNew_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S41);
					  System.out.println(RC.getStringCellData(replyComent_InWebByNew_index, RC.Current_Coulumn_Number(replyComent_InWebByNew, "RC_NU_ID"), replyComent_InWebByNew)); 
					  if (S41.equals(RC.getStringCellData(replyComent_InWebByNew_index, RC.Current_Coulumn_Number(replyComent_InWebByNew, "RC_NU_ID"),replyComent_InWebByNew)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(replyComent_InWebByNew_index, RC.Current_Coulumn_Number(replyComent_InWebByNew, "RC_NU_ID"),replyComent_InWebByNew)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int replyComent_InWebByNew_ind=0;replyComent_InWebByNew_ind<replyComent_InWebByNew_col;replyComent_InWebByNew_ind++) 
						  {
							  replyComent_InWebByNew_ele[replyComent_InWebByNew_ind]=RC.getStringCellData(replyComent_InWebByNew_index, replyComent_InWebByNew_ind, replyComent_InWebByNew);
							  System.out.println(replyComent_InWebByNew_ele[replyComent_InWebByNew_ind]); //call login as company method, pass array values
						  }
						  
						  if(replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrInd")].equals("Org"))
						  {
							  int firsttimeRegister=1;
							  String OrganizationRegister=Environment("Sheet_OrganizationRegister"); 
							  int OrganizationRegister_row=RC.getLastrowno(OrganizationRegister); 
							  int OrganizationRegister_col=RC.getLastcolmno(OrganizationRegister); 
							  String[] OrganizationRegister_ele=new String[OrganizationRegister_col]; 
							  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(OrganizationRegister); OrganizationRegister_index++) 
							  { 
								  System.out.println("for Loop");
								  System.out.println(replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrIndID")]);
								  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"), OrganizationRegister)); 
								  if (replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrIndID")].equals(
										  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<OrganizationRegister_col;OrganizationRegister_ind++) 
									  {
										  OrganizationRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, OrganizationRegister);
										  System.out.println(OrganizationRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
									  }
									  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
											  replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "Petition")]);
									  if(petitiontitle_check.equals("true"))
									  {
										  List<String> beforeReply=func_cases.petitionClickOnReplyCommentToARecentCommentInWebSite_ByNewUser(driver, 
												  replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "ReplyComment")]);
										  
										  func_cases.companyRegisteringToReplyACommentInWebsite(driver, 
												  firsttimeRegister,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "YourName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ContactNo")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "FEIN")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Website")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ConfirmPassword")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Code")],beforeReply.get(1));
										  func_cases.petitionRegisteredUserReplyToRecentComment(driver,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  beforeReply.get(0),replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "ReplyComment")]);
									  }	
								  }
							  }
						  }
						  else
						  {
							  if(replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrInd")].equals("Indiv"))
							  {
								  int firsttimeRegister=1;
								  String IndividualRegister=Environment("Sheet_IndividualRegister"); 
								  int IndividualRegister_row=RC.getLastrowno(IndividualRegister); 
								  int IndividualRegister_col=RC.getLastcolmno(IndividualRegister); 
								  String[] IndividualRegister_ele=new String[IndividualRegister_col]; 
								  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(IndividualRegister); OrganizationRegister_index++) 
								  { 
									  System.out.println("for Loop");
									  System.out.println(replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrIndID")]);
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"), IndividualRegister)); 
									  if (replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "OrgOrIndID")].equals(
											  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<IndividualRegister_col;OrganizationRegister_ind++) 
										  {
											  IndividualRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, IndividualRegister);
											  System.out.println(IndividualRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
										  }
										  String petitiontitle_check=func_cases.companyBase_Petitions(driver,
												  replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "Petition")]);
										  if(petitiontitle_check.equals("true"))
										  {
											  List<String> beforeReply=func_cases.petitionClickOnReplyCommentToARecentCommentInWebSite_ByNewUser(driver, 
													  replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "ReplyComment")]);
											  
											  func_cases.individualRegisteringToReplyACommentInWebsite(driver, 
													  firsttimeRegister,
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "FirstName")], 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "LastName")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ContactNumber")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ConfirmPassword")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Captcha")],beforeReply.get(1));
											  func_cases.petitionRegisteredUserReplyToRecentComment(driver,
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
													  beforeReply.get(0),replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(replyComent_InWebByNew, "ReplyComment")]);
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
	@DataProvider
	public Object[][] custData31() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),31);
		return testData;
	}
	@Test(dataProvider = "custData31",priority=32)
	public void replyCommentOnCompanySide(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Reply Comment to a petition by Company");
			if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S42!="")
						  {
								String Comp_RepCom=Environment("Sheet_Comp_RepCom"); 
								int Comp_RepCom_row=RC.getLastrowno(Comp_RepCom); 
								int Comp_RepCom_col=RC.getLastcolmno(Comp_RepCom); 
								String[] Comp_RepCom_ele=new String[Comp_RepCom_col]; 
								for (int Comp_RepCom_index = 1; Comp_RepCom_index < RC.getLastrowno(Comp_RepCom); Comp_RepCom_index++) 
								{ 
									 System.out.println("for Loop" );
									 System.out.println(S42);
									 System.out.println(RC.getStringCellData(Comp_RepCom_index, RC.Current_Coulumn_Number(Comp_RepCom, "PC_RepComID"), Comp_RepCom)); 
									 if (S42.equals(RC.getStringCellData(Comp_RepCom_index, RC.Current_Coulumn_Number(Comp_RepCom, "PC_RepComID"),Comp_RepCom)))
										 // Adduser contains company email_id at 1st column  for validation
									 { 
										 System.out.println("Matches ID to Register");
										 System.out.println(RC.getStringCellData(Comp_RepCom_index, RC.Current_Coulumn_Number(Comp_RepCom, "PC_RepComID"),Comp_RepCom)); 
										 //based on j value get the row data and do Adding Users
									   
										 for(int Comp_RepCom_ind=0;Comp_RepCom_ind<Comp_RepCom_col;Comp_RepCom_ind++) 
										 {
											 Comp_RepCom_ele[Comp_RepCom_ind]=RC.getStringCellData(Comp_RepCom_index, Comp_RepCom_ind, Comp_RepCom);
											 System.out.println(Comp_RepCom_ele[Comp_RepCom_ind]); //call login as company method, pass array values
										 }
										 func_cases.viewPetition(driver, Comp_RepCom_ele[RC.Current_Coulumn_Number(Comp_RepCom, "Petition")]);
										 List<String> status=func_cases.petitionReplyCommentsOnCompanySide(driver, 
												 Comp_RepCom_ele[RC.Current_Coulumn_Number(Comp_RepCom, "ReplyComment")]);
										 func_cases.CompanyLogout(driver);
										 func_cases.companyBase_Petitions(driver, Comp_RepCom_ele[RC.Current_Coulumn_Number(Comp_RepCom, "Petition")]);
										 func_cases.petitionNewCommentByCompValidatingOnWebSite(driver, status.get(1), status.get(2));
									 }
								 }
							}
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Comment on Company Side is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData32() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),32);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData32",priority=33)
	public void crowdFundCommentOnCompanySide(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Add A New Comment On CompanySide And Validating on Website");
			if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S37!="")
							 {
								
								 String CFComm_CompSide=Environment("Sheet_CFComm_CompSide"); 
								 int CFComm_CompSide_row=RC.getLastrowno(CFComm_CompSide); 
								 int CFComm_CompSide_col=RC.getLastcolmno(CFComm_CompSide); 
								 String[] CFComm_CompSide_ele=new String[CFComm_CompSide_col]; 
								 for (int CFComm_CompSide_index = 1; CFComm_CompSide_index < RC.getLastrowno(CFComm_CompSide); CFComm_CompSide_index++) 
								 { 
									 System.out.println("for Loop" );
									 System.out.println(S34);
									 System.out.println(RC.getStringCellData(CFComm_CompSide_index, RC.Current_Coulumn_Number(CFComm_CompSide, "CF_CompID"), CFComm_CompSide)); 
									 if (S37.equals(RC.getStringCellData(CFComm_CompSide_index, RC.Current_Coulumn_Number(CFComm_CompSide, "CF_CompID"),CFComm_CompSide)))
										 // Adduser contains company email_id at 1st column  for validation
									 { 
										 System.out.println("Matches ID to Register");
										 System.out.println(RC.getStringCellData(CFComm_CompSide_index, RC.Current_Coulumn_Number(CFComm_CompSide, "CF_CompID"),CFComm_CompSide)); 
										 //based on j value get the row data and do Adding Users
									   
										 for(int CFComm_CompSide_ind=0;CFComm_CompSide_ind<CFComm_CompSide_col;CFComm_CompSide_ind++) 
										 {
											 CFComm_CompSide_ele[CFComm_CompSide_ind]=RC.getStringCellData(CFComm_CompSide_index, CFComm_CompSide_ind, CFComm_CompSide);
											 System.out.println(CFComm_CompSide_ele[CFComm_CompSide_ind]); //call login as company method, pass array values
										 }
										 func_cases.viewCrowdFund(driver, CFComm_CompSide_ele[RC.Current_Coulumn_Number(CFComm_CompSide, "CrowdFund")]);
										 List<String> status=func_cases.petitionNewCommentsOnCompanySide(driver, 
												 CFComm_CompSide_ele[RC.Current_Coulumn_Number(CFComm_CompSide, "NewComment")]);
										 func_cases.CompanyLogout(driver);
										 func_cases.companyBase_CrowdFund(driver, CFComm_CompSide_ele[RC.Current_Coulumn_Number(CFComm_CompSide, "CrowdFund")]);
										 func_cases.petition_NewCommentByCompValidatingOnWebSite(driver, status.get(1), status.get(2));
									 }
								 }
							 }
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Comment on Company Side is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData33() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),33);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData33",priority=34)
	public void peti_CF_replyCommentByExistedUserInWebsite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a Reply Comment To a CrowdFund In WebSite By Existed User");
			if(S38!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String CFreplyComent_InWebByExisted=Environment("Sheet_CFreplyComent_InWebByExisted"); 
				  int replyComent_InWebByExisted_row=RC.getLastrowno(CFreplyComent_InWebByExisted); 
				  int replyComent_InWebByExisted_col=RC.getLastcolmno(CFreplyComent_InWebByExisted); 
				  String[] replyComent_InWebByExisted_ele=new String[replyComent_InWebByExisted_col]; 
				  for (int replyComent_InWebByExisted_index = 1; replyComent_InWebByExisted_index < RC.getLastrowno(CFreplyComent_InWebByExisted); replyComent_InWebByExisted_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S36);
					  System.out.println(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "RC_ExiID"), CFreplyComent_InWebByExisted)); 
					  if (S38.equals(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "RC_ExiID"),CFreplyComent_InWebByExisted)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(replyComent_InWebByExisted_index, RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "RC_ExiID"),CFreplyComent_InWebByExisted)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int replyComent_InWebByExisted_ind=0;replyComent_InWebByExisted_ind<replyComent_InWebByExisted_col;replyComent_InWebByExisted_ind++) 
						  {
							  replyComent_InWebByExisted_ele[replyComent_InWebByExisted_ind]=RC.getStringCellData(replyComent_InWebByExisted_index, replyComent_InWebByExisted_ind, CFreplyComent_InWebByExisted);
							  System.out.println(replyComent_InWebByExisted_ele[replyComent_InWebByExisted_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver,
								  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "Crowdfund")]);
						  if(petitiontitle_check.equals("true"))
						  {
							  func_cases.petitionReplyCommentToARecentCommentInWebSite_ByExistedUser(driver, 
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "ReplyComment")], 
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "Email")],
									  replyComent_InWebByExisted_ele[RC.Current_Coulumn_Number(CFreplyComent_InWebByExisted, "Password")]);
						  }
						  
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Reply Comment to CrowdFund in Website by existed user is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData34() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),34);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData34",priority=35)
	public void CF_newCommentInWebsite_ByExistedUser(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a New Comment To a Petition In WebSite By Existed User");
			if(S39!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String CF_NewCommentInWeb_Existed=Environment("Sheet_CF_NewCommentInWeb_Existed"); 
				  int CF_NewCommentInWeb_Existed_row=RC.getLastrowno(CF_NewCommentInWeb_Existed); 
				  int CF_NewCommentInWeb_Existed_col=RC.getLastcolmno(CF_NewCommentInWeb_Existed); 
				  String[] CF_NewCommentInWeb_Existed_ele=new String[CF_NewCommentInWeb_Existed_col]; 
				  for (int NewCommentInWeb_Existed_index = 1; NewCommentInWeb_Existed_index < RC.getLastrowno(CF_NewCommentInWeb_Existed); NewCommentInWeb_Existed_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S39);
					  System.out.println(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComInWebExistedID"), CF_NewCommentInWeb_Existed)); 
					  if (S39.equals(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComInWebExistedID"),CF_NewCommentInWeb_Existed)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(NewCommentInWeb_Existed_index, RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComInWebExistedID"),CF_NewCommentInWeb_Existed)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int NewCommentInWeb_Existed_ind=0;NewCommentInWeb_Existed_ind<CF_NewCommentInWeb_Existed_col;NewCommentInWeb_Existed_ind++) 
						  {
							  CF_NewCommentInWeb_Existed_ele[NewCommentInWeb_Existed_ind]=RC.getStringCellData(NewCommentInWeb_Existed_index, NewCommentInWeb_Existed_ind, CF_NewCommentInWeb_Existed);
							  System.out.println(CF_NewCommentInWeb_Existed_ele[NewCommentInWeb_Existed_ind]); //call login as company method, pass array values
						  }
						  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver, 
								  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "Crowdfund")]);
						  if(petitiontitle_check.equals("true"))
						  {
							  func_cases.newCommentPostInWebSite(driver, 
									  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComment")]);
							  String name=func_cases.CF_newCommentByExisterUserSignIn(driver, 
									  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "ExistedUserEmail")], 
									  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "ExistedUserPassword")],
									  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "Crowdfund")]);
							 // func_cases.companyBase_CrowdFund(driver, CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "Crowdfund")]);
							  //Thread.sleep(10000);
							  func_cases.petition_NewCommentByCompValidatingOnWebSite(driver,
									  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComment")],name);
							  if(S3!="")
							  {
								  func_cases.companyLogin(driver, 
										  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "CFCreatedIn")], 
										  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "CF_CreatePwd")]);
								  func_cases.viewCrowdFund(driver, 
										  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "Crowdfund")]);
								  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
										  CF_NewCommentInWeb_Existed_ele[RC.Current_Coulumn_Number(CF_NewCommentInWeb_Existed, "NewComment")],
										  name);
								  func_cases.CompanyLogout(driver);
							  }
						  }
					  }
				  }
			}
		}
	}
	@DataProvider
	public Object[][] custData35() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),35);
		return testData;
	}
	
	//@SuppressWarnings("null")//,dependsOnMethods="SavecreatePetition"
	
	@Test(dataProvider = "custData35",priority=36)
	public void CF_newCommentInWebsite_ByNewUser(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a New Comment To a Petition In WebSite By New(Individual or Oranization) User");
			if(S40!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String CF_NewCOmmentInWeb_NewUser=Environment("Sheet_CF_NewCOmmentInWeb_NewUser"); 
				  int CF_NewCOmmentInWeb_NewUser_row=RC.getLastrowno(CF_NewCOmmentInWeb_NewUser); 
				  int CF_NewCOmmentInWeb_NewUser_col=RC.getLastcolmno(CF_NewCOmmentInWeb_NewUser); 
				  String[] CF_NewCOmmentInWeb_NewUser_ele=new String[CF_NewCOmmentInWeb_NewUser_col]; 
				  for (int CF_NewCOmmentInWeb_NewUser_index = 1; CF_NewCOmmentInWeb_NewUser_index < RC.getLastrowno(CF_NewCOmmentInWeb_NewUser); CF_NewCOmmentInWeb_NewUser_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S40);
					  System.out.println(RC.getStringCellData(CF_NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NC_NewUserID"), CF_NewCOmmentInWeb_NewUser)); 
					  if (S40.equals(RC.getStringCellData(CF_NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NC_NewUserID"),CF_NewCOmmentInWeb_NewUser)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(CF_NewCOmmentInWeb_NewUser_index, RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NC_NewUserID"),CF_NewCOmmentInWeb_NewUser)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int CF_NewCOmmentInWeb_NewUser_ind=0;CF_NewCOmmentInWeb_NewUser_ind<CF_NewCOmmentInWeb_NewUser_col;CF_NewCOmmentInWeb_NewUser_ind++) 
						  {
							  CF_NewCOmmentInWeb_NewUser_ele[CF_NewCOmmentInWeb_NewUser_ind]=RC.getStringCellData(CF_NewCOmmentInWeb_NewUser_index, CF_NewCOmmentInWeb_NewUser_ind, CF_NewCOmmentInWeb_NewUser);
							  System.out.println(CF_NewCOmmentInWeb_NewUser_ele[CF_NewCOmmentInWeb_NewUser_ind]); //call login as company method, pass array values
						  }
						   
							  if(CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndiv")].equals("Org"))
							  {
								  int firsttimeRegister=1;
								  String OrganizationRegister=Environment("Sheet_OrganizationRegister"); 
								  int OrganizationRegister_row=RC.getLastrowno(OrganizationRegister); 
								  int OrganizationRegister_col=RC.getLastcolmno(OrganizationRegister); 
								  String[] OrganizationRegister_ele=new String[OrganizationRegister_col]; 
								  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(OrganizationRegister); OrganizationRegister_index++) 
								  { 
									  System.out.println("for Loop");
									  System.out.println(CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndivID")]);
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"), OrganizationRegister)); 
									  if (CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndivID")].equals(
											  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<OrganizationRegister_col;OrganizationRegister_ind++) 
										  {
											  OrganizationRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, OrganizationRegister);
											  System.out.println(OrganizationRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
										  }
										  
										  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver, 
												  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "Petition")]);
										  if(petitiontitle_check.equals("true"))
										  {
											  func_cases.newCommentPostInWebSite(driver, 
													  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")]);
										  
											  String name=func_cases.newCommentByNewCompanyRegisterSignIn(driver, 
												  firsttimeRegister,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "YourName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ContactNo")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "FEIN")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Website")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ConfirmPassword")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Code")], 
												  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "Petition")]);
											  
											  func_cases.petitionNewCommentByCompValidatingOnWebSite(driver,
													  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")],name);
											  if(S3!="")
											  {
												  func_cases.companyLogin(driver, 
														  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CFCreatedIn")], 
														  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CFCreatePwd")]);
												  func_cases.viewCrowdFund(driver, 
														  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CrowdFund")]);
												  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
														  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")],
														  name);
												  func_cases.CompanyLogout(driver);
											  }
										  }
										  
									  }
								  }
							  }
							  else
							  {
								  if(CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndiv")].equals("Indiv"))
								  {
									  int firsttimeRegister=1;
									  String IndividualRegister=Environment("Sheet_IndividualRegister"); 
										 int IndividualRegister_row=RC.getLastrowno(IndividualRegister); 
										 int IndividualRegister_col=RC.getLastcolmno(IndividualRegister); 
										 String[] IndividualRegister_ele=new String[IndividualRegister_col]; 
										 for (int IndividualRegister_index = 1; IndividualRegister_index < RC.getLastrowno(IndividualRegister); IndividualRegister_index++) 
										 { 
											 System.out.println("for Loop" );
											 System.out.println(CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndivID")]);
											 System.out.println(RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"), IndividualRegister)); 
											 if (CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "OrgORIndivID")].equals(
													 RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)))
												 // Adduser contains company email_id at 1st column  for validation
											 { 
												 System.out.println("Matches ID to Register");
												 System.out.println(RC.getStringCellData(IndividualRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)); 
												 //based on j value get the row data and do Adding Users
											   
												 for(int IndividualRegister_ind=0;IndividualRegister_ind<IndividualRegister_col;IndividualRegister_ind++) 
												 {
													 IndividualRegister_ele[IndividualRegister_ind]=RC.getStringCellData(IndividualRegister_index, IndividualRegister_ind, IndividualRegister);
													 System.out.println(IndividualRegister_ele[IndividualRegister_ind]); //call login as company method, pass array values
												 }
												 
												 String petitiontitle_check=func_cases.companyBase_CrowdFund(driver, 
														 CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CrowdFund")]);
												  if(petitiontitle_check.equals("true"))
												  {
													  func_cases.newCommentPostInWebSite(driver, 
															  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")]);
												  
													  String name=func_cases.newCommentByNewIndividualRegisterSignIn(driver, 
														 firsttimeRegister, 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "FirstName")], 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "LastName")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ContactNumber")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ConfirmPassword")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Captcha")], 
														 CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CrowdFund")]);
													  func_cases.petition_NewCommentByCompValidatingOnWebSite(driver,
															  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")],name);
													  if(S3!="")
													  {
														  func_cases.companyLogin(driver, 
																  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CFCreatedIn")], 
																  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CFCreatePwd")]);
														  func_cases.viewCrowdFund(driver, 
																  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "CrowdFund")]);
														  func_cases.newCommentByExistedUserInWebValidInCompany(driver,
																  CF_NewCOmmentInWeb_NewUser_ele[RC.Current_Coulumn_Number(CF_NewCOmmentInWeb_NewUser, "NewComment")],
																  name);
														  func_cases.CompanyLogout(driver);
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
	}
	@DataProvider
	public Object[][] custData36() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),36);
		return testData;
	}
	@Test(dataProvider = "custData36",priority=37)
	public void CF_replyCommentByNewUserInWebsite(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30, String S31, String S32, String S33,
			String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to add a Reply Comment To a Petition In WebSite By New User(Individual/ Company)");
			if(S41!="")
			{
				
				List<String> compInfo=new ArrayList<>();
				String CF_replyComent_InWebByNew=Environment("Sheet_CF_replyComent_InWebByNew"); 
				  int CF_replyComent_InWebByNew_row=RC.getLastrowno(CF_replyComent_InWebByNew); 
				  int CF_replyComent_InWebByNew_col=RC.getLastcolmno(CF_replyComent_InWebByNew); 
				  String[] CF_replyComent_InWebByNew_ele=new String[CF_replyComent_InWebByNew_col]; 
				  for (int CF_replyComent_InWebByNew_index = 1; CF_replyComent_InWebByNew_index < RC.getLastrowno(CF_replyComent_InWebByNew); CF_replyComent_InWebByNew_index++) 
				  { 
					  System.out.println("for Loop");
					  System.out.println(S41);
					  System.out.println(RC.getStringCellData(CF_replyComent_InWebByNew_index, RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "RC_NU_ID"), CF_replyComent_InWebByNew)); 
					  if (S41.equals(RC.getStringCellData(CF_replyComent_InWebByNew_index, RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "RC_NU_ID"),CF_replyComent_InWebByNew)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(CF_replyComent_InWebByNew_index, RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "RC_NU_ID"),CF_replyComent_InWebByNew)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int CF_replyComent_InWebByNew_ind=0;CF_replyComent_InWebByNew_ind<CF_replyComent_InWebByNew_col;CF_replyComent_InWebByNew_ind++) 
						  {
							  CF_replyComent_InWebByNew_ele[CF_replyComent_InWebByNew_ind]=RC.getStringCellData(CF_replyComent_InWebByNew_index, CF_replyComent_InWebByNew_ind, CF_replyComent_InWebByNew);
							  System.out.println(CF_replyComent_InWebByNew_ele[CF_replyComent_InWebByNew_ind]); //call login as company method, pass array values
						  }
						  
						  if(CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrInd")].equals("Org"))
						  {
							  int firsttimeRegister=1;
							  String OrganizationRegister=Environment("Sheet_OrganizationRegister"); 
							  int OrganizationRegister_row=RC.getLastrowno(OrganizationRegister); 
							  int OrganizationRegister_col=RC.getLastcolmno(OrganizationRegister); 
							  String[] OrganizationRegister_ele=new String[OrganizationRegister_col]; 
							  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(OrganizationRegister); OrganizationRegister_index++) 
							  { 
								  System.out.println("for Loop");
								  System.out.println(CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrIndID")]);
								  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"), OrganizationRegister)); 
								  if (CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrIndID")].equals(
										  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)))
									  // Adduser contains company email_id at 1st column  for validation
								  { 
									  
									  System.out.println("Matches ID to Register");
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(OrganizationRegister, "OrgRegID"),OrganizationRegister)); 
									  //based on j value get the row data and do Adding Users
									   
									  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<OrganizationRegister_col;OrganizationRegister_ind++) 
									  {
										  OrganizationRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, OrganizationRegister);
										  System.out.println(OrganizationRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
									  }
									  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver,
											  CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "Crowdfund")]);
									  if(petitiontitle_check.equals("true"))
									  {
										  List<String> beforeReply=func_cases.petitionClickOnReplyCommentToARecentCommentInWebSite_ByNewUser(driver, 
												  CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "ReplyComment")]);
										  
										  func_cases.companyRegisteringToReplyACommentInWebsite(driver, 
												  firsttimeRegister,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "YourName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgName")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ContactNo")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "FEIN")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Website")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "ConfirmPassword")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Code")],beforeReply.get(1));
										  func_cases.petitionRegisteredUserReplyToRecentComment(driver,
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "OrgEmailID")],
												  OrganizationRegister_ele[RC.Current_Coulumn_Number(OrganizationRegister, "Password")],
												  beforeReply.get(0),CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "ReplyComment")]);
									  }	
								  }
							  }
						  }
						  else
						  {
							  if(CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrInd")].equals("Indiv"))
							  {
								  int firsttimeRegister=1;
								  String IndividualRegister=Environment("Sheet_IndividualRegister"); 
								  int IndividualRegister_row=RC.getLastrowno(IndividualRegister); 
								  int IndividualRegister_col=RC.getLastcolmno(IndividualRegister); 
								  String[] IndividualRegister_ele=new String[IndividualRegister_col]; 
								  for (int OrganizationRegister_index = 1; OrganizationRegister_index < RC.getLastrowno(IndividualRegister); OrganizationRegister_index++) 
								  { 
									  System.out.println("for Loop");
									  System.out.println(CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrIndID")]);
									  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"), IndividualRegister)); 
									  if (CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "OrgOrIndID")].equals(
											  RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)))
										  // Adduser contains company email_id at 1st column  for validation
									  { 
										  
										  System.out.println("Matches ID to Register");
										  System.out.println(RC.getStringCellData(OrganizationRegister_index, RC.Current_Coulumn_Number(IndividualRegister, "Indiv_RegID"),IndividualRegister)); 
										  //based on j value get the row data and do Adding Users
										   
										  for(int OrganizationRegister_ind=0;OrganizationRegister_ind<IndividualRegister_col;OrganizationRegister_ind++) 
										  {
											  IndividualRegister_ele[OrganizationRegister_ind]=RC.getStringCellData(OrganizationRegister_index, OrganizationRegister_ind, IndividualRegister);
											  System.out.println(IndividualRegister_ele[OrganizationRegister_ind]); //call login as company method, pass array values
										  }
										  String petitiontitle_check=func_cases.companyBase_CrowdFund(driver,
												  CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "Crowdfund")]);
										  if(petitiontitle_check.equals("true"))
										  {
											  List<String> beforeReply=func_cases.petitionClickOnReplyCommentToARecentCommentInWebSite_ByNewUser(driver, 
													  CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "ReplyComment")]);
											  
											  func_cases.individualRegisteringToReplyACommentInWebsite(driver, 
													  firsttimeRegister,
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "FirstName")], 
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "LastName")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ContactNumber")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "ConfirmPassword")],
														 IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Captcha")],beforeReply.get(1));
											  func_cases.petitionRegisteredUserReplyToRecentComment(driver,
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "EmailID")],
													  IndividualRegister_ele[RC.Current_Coulumn_Number(IndividualRegister, "Password")],
													  beforeReply.get(0),CF_replyComent_InWebByNew_ele[RC.Current_Coulumn_Number(CF_replyComent_InWebByNew, "ReplyComment")]);
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
	@DataProvider
	public Object[][] custData37() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),37);
		return testData;
	}
	@Test(dataProvider = "custData37",priority=38)
	public void CF_replyCommentOnCompanySide(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Reply Comment to a petition by Company");
			if(S3!="")
			{
				  //Logs_DigiSurvey.startTestCase("Creating A survey, Update Questions, Save Survey, Share Survey, AnswerByEnduSer, Again check the response from comapnyside Where we created the survey");
				  String Login=Environment("Sheet_Login"); 
				  int Login_row=RC.getLastrowno(Login); 
				  int Login_col=RC.getLastcolmno(Login); 
				  String[] Login_ele=new String[Login_col]; 
				  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
				  { 
					  System.out.println("for Loop" );
					  System.out.println(S3);
					  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
					  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
						  // Adduser contains company email_id at 1st column  for validation
					  { 
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int col_Login=0;col_Login<Login_col;col_Login++) 
						  {
							  Login_ele[col_Login]=RC.getStringCellData(Login_index, col_Login, Login);
							  System.out.println(Login_ele[col_Login]); //call login as company method, pass array values
							  currentUsername = Login_ele[RC.Current_Coulumn_Number(Login, "Username")];
							  currentPassword = Login_ele[RC.Current_Coulumn_Number(Login, "Password")];
						  } 
						  func_cases.companyLogin(driver,Login_ele[RC.Current_Coulumn_Number(Login, "Username")], Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
						  if(S42!="")
							 {
								
								 String CF_Comp_RepCom=Environment("Sheet_CF_Comp_RepCom"); 
								 int CF_Comp_RepCom_row=RC.getLastrowno(CF_Comp_RepCom); 
								 int CF_Comp_RepCom_col=RC.getLastcolmno(CF_Comp_RepCom); 
								 String[] CF_Comp_RepCom_ele=new String[CF_Comp_RepCom_col]; 
								 for (int CF_Comp_RepCom_index = 1; CF_Comp_RepCom_index < RC.getLastrowno(CF_Comp_RepCom); CF_Comp_RepCom_index++) 
								 { 
									 System.out.println("for Loop" );
									 System.out.println(S42);
									 System.out.println(RC.getStringCellData(CF_Comp_RepCom_index, RC.Current_Coulumn_Number(CF_Comp_RepCom, "PC_RepComID"), CF_Comp_RepCom)); 
									 if (S42.equals(RC.getStringCellData(CF_Comp_RepCom_index, RC.Current_Coulumn_Number(CF_Comp_RepCom, "PC_RepComID"),CF_Comp_RepCom)))
										 // Adduser contains company email_id at 1st column  for validation
									 { 
										 System.out.println("Matches ID to Register");
										 System.out.println(RC.getStringCellData(CF_Comp_RepCom_index, RC.Current_Coulumn_Number(CF_Comp_RepCom, "PC_RepComID"),CF_Comp_RepCom)); 
										 //based on j value get the row data and do Adding Users
									   
										 for(int CF_Comp_RepCom_ind=0;CF_Comp_RepCom_ind<CF_Comp_RepCom_col;CF_Comp_RepCom_ind++) 
										 {
											 CF_Comp_RepCom_ele[CF_Comp_RepCom_ind]=RC.getStringCellData(CF_Comp_RepCom_index, CF_Comp_RepCom_ind, CF_Comp_RepCom);
											 System.out.println(CF_Comp_RepCom_ele[CF_Comp_RepCom_ind]); //call login as company method, pass array values
										 }
										 func_cases.viewCrowdFund(driver, CF_Comp_RepCom_ele[RC.Current_Coulumn_Number(CF_Comp_RepCom, "Crowdfund")]);
										 List<String> status=func_cases.petitionReplyCommentsOnCompanySide(driver, 
												 CF_Comp_RepCom_ele[RC.Current_Coulumn_Number(CF_Comp_RepCom, "ReplyComment")]);
										 func_cases.CompanyLogout(driver);
										 func_cases.companyBase_CrowdFund(driver, CF_Comp_RepCom_ele[RC.Current_Coulumn_Number(CF_Comp_RepCom, "Crowdfund")]);
										 func_cases.petitionNewCommentByCompValidatingOnWebSite(driver, status.get(1), status.get(2));
									 }
								 }
							 }
					  }
				  }
			}
		}
		else
			fl.disp_Message(driver, "", "Comment on Company Side is skipped", "", "", "");
	}
	@DataProvider
	public Object[][] custData38() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),38);
		return testData;
	}
	@Test(dataProvider = "custData38",priority=39)
	public void registerAsIndividual_ReferFriends(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Register As Individual and Share refer link with Companies And Individual Users");
			int firsttimeRegister=1;
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
		}
	}
	@DataProvider
	public Object[][] custData39() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),39);
		return testData;
	}
	@Test(dataProvider = "custData39",priority=40)
	public void registerAsCompany_ReferFriends(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(S1.equals("Y"))
		{
			Tcase.add(S2);
			String Description=Tcase.toString();
			ATUReports.currentRunDescription = Description;
			//ATUReports.currentRunDescription = S2;
			ATUReports.setTestCaseReqCoverage("This test is mapped to Register As Company and Share refer link with Companies And Individual Users");
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
						  if(S26=="")//ReferFriends  by default  ==>firsttimeRegister=1
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
		}
	}
	@DataProvider
	public Object[][] custData40() throws IOException 
	{
		Excel_Utils E_utils = new Excel_Utils(Environment("Excel"));
		Object[][] testData = E_utils.readXLSXFile1(Environment("Sheet_Control"),40);
		return testData;
	}
	@Test(dataProvider = "custData40",priority=41)
	public void Login_Logout(String S1,String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10,
			String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20,
			String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30,
			String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40, String S41, String S42) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		if(S1.equals("Y"))
		{
			Excel_Utils RC = new Excel_Utils(Environment("Excel"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
				Tcase.add(S2);
				String Description=Tcase.toString();
				ATUReports.currentRunDescription = Description;
				//ATUReports.currentRunDescription = S2;
				ATUReports.setTestCaseReqCoverage("This test is mapped to Register As Company and Share refer link with Companies And Individual Users");
				int firsttimeRegister=1;
				
				if(S3!="")
				{
					//company Registration
					  String Login=Environment("Sheet_Login"); 
					  int Login_row=RC.getLastrowno(Login); 
					  int Login_col=RC.getLastcolmno(Login); 
					  String[] Login_ele=new String[Login_col]; 
					  for (int Login_index = 1; Login_index < RC.getLastrowno(Login); Login_index++) 
					  { 
						  System.out.println("for Loop" );
						  System.out.println(S3);
						  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"), Login)); 
						  if (S3.equals(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)))
							  // Adduser contains company email_id at 1st column  for validation
						  {
							  System.out.println("Matches ID to Register");
							  System.out.println(RC.getStringCellData(Login_index, RC.Current_Coulumn_Number(Login, "LoginTest"),Login)); 
							  //based on j value get the row data and do Adding Users
							   
							  for(int Login_ind=0;Login_ind<Login_col;Login_ind++) 
							  {
								  Login_ele[Login_ind]=RC.getStringCellData(Login_index, Login_ind, Login);
								  System.out.println(Login_ele[Login_ind]); //call login as company method, pass array values
							  }
							  func_cases.companyLogin(driver, Login_ele[RC.Current_Coulumn_Number(Login, "Username")], 
									  Login_ele[RC.Current_Coulumn_Number(Login, "Password")]);
							 
							  func_cases.CompanyLogout(driver);
						  }
						  
					  }
				}
			
		}
		else
			fl.disp_MessageFailed(driver, "", "Testcase skipped", "", "", "");
	}
}