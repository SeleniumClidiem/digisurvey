package Businessfunctions_Digi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import FunctionalLibraries_Digi.Functional_Libraries;

import Utilities_Digi.DB_Connection_Digi_Candit;
import Utilities_Digi.DB_Connection_Digi_Company;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;
import Utilities_Digi.UploadFile_Robot;
import Utilities_Digi.select_delete;

public class Functional_Cases_propread extends Environment_proprties_Read
{
	
	Functional_Libraries fl = new Functional_Libraries();
	UploadFile_Robot UR = new UploadFile_Robot();
	String FEIN_From_Excel;
	
	
	public void companyLogin(WebDriver driver,String EmailID,  String password )
			throws IOException, InterruptedException 
	{
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		String Digi_CompanyLoginxpath=Environment("Digi_CompanyLoginXPATH");
		String Company_EmailIDxpath=Environment("Company_EmailIDXPATH");
		String Company_Passwordxpath=Environment("Company_PasswordXPATH");
		String Company_LoginButtonxpath=Environment("Company_LoginButtonXPATH");

		/*System.out.println(driver);
		System.out.println(CompanyLoginID_xpath);
		System.out.println(url);
		System.out.println(CompanyUsernameID_xpath);
		System.out.println(EmailID);
		System.out.println(CompanyPasswordID_xpath);
		System.out.println(password);
		System.out.println(CompanyLoginButton_xpath);*/

		try {
			Thread.sleep(10000);
			fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");

			fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, "", "", "", "", "");

			fl.entervalueByXpath(driver, Company_Passwordxpath, password, "", "", "", "", "");

			Thread.sleep(3000);
			// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
			// "", "", "", "");
			fl.ClickByXpath(driver, Company_LoginButtonxpath, "", "", "", "", "");

			Thread.sleep(8000);
		} catch (WebDriverException e) {
			e.printStackTrace();
		}

	}
	
	public void companyLogout(WebDriver driver, String Company_LogoXPATH, String Company_LogoutXPATH)
	{
		try 
		{
			fl.ClickByXpath(driver, Company_LogoXPATH, "", "", "", "", "");
			
			fl.ClickByXpath(driver, Company_LogoutXPATH, "", "", "", "", "");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	public void candidateRegistration(WebDriver driver, String S1, String S2, String S3, String S4, String S5,
			String S6, String S7) throws IOException, InterruptedException, ClassNotFoundException, SQLException 
	{

		try {
			
			fl.invokeApplication(driver, Environment("Comapany_BaseURL_Digi"), "", "", "", "", "", "");
			
			fl.ClickByXpath(driver, Environment("Candit_SignUPxpath"), "", "", "", "", "");

			//fl.ClickByID(driver, Environment("CandidatetabID"), "", "", "", "", "");

			fl.entervalueByID(driver, Environment("FirstNameID"), S1, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("LastNameID"), S2, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("EmailID"), S3, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ContactNoID"), S4, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("PasswordID"), S5, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ConfirmPasswordID"), S6, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, Environment("CaptchXPATH"), S7, "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("CheckboxXpath"), "", "", "", "", "");

			Thread.sleep(3000);
			fl.ClickByCSS(driver, Environment("Registercss"), "", "", "", "", "");

			String Verify_Code_Candit = DB_Connection_Digi_Candit.Db_Connect(S3);
			System.out.println(Verify_Code_Candit);

			fl.entervalueByXpath(driver, Environment("Verififcation_textboxXPATH"), Verify_Code_Candit, "", "", "",
					"", "");

			fl.ClickByXpath(driver, Environment("verify_click"), "", "", "", "", "");
			Thread.sleep(10000);

		} 
		catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
	public void candidateLogin(WebDriver driver, String Company_BaseURL, String Candit_EmailXPATH, String Candit_Email, 
			String Candit_PasswordXPATH, String Candit_Password, String CanditLogin_ButtonXPATH) throws IOException, InterruptedException 
	{

		try 
		{
			
			fl.invokeApplication(driver, Company_BaseURL, "", "", "", "", "", "");

			//fl.ClickByID(driver, Environment("CandidateLoginID"), "", "", "", "", "");

			fl.entervalueByID(driver, Candit_EmailXPATH, Candit_Email, "", "", "", "", "");

			fl.entervalueByID(driver, Candit_PasswordXPATH, Candit_Password, "", "", "", "", "");

			fl.ClickByID(driver, CanditLogin_ButtonXPATH, "", "", "", "", "");

		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
	public void canditProfile_Edit(WebDriver driver) throws InterruptedException, IOException {
//1.s1-S29, 2.S1-S15(sub:S1-S6),3.S1-S10,4.S1-S71,5.S1-S6
		try {

			fl.ClickByXpath(driver, Environment("EditProfilexpath"), "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ProfileFirstNameID"), "Aakhil", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ProfileMidNameID"), "A", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ProfileLastNameID"), "ASe", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("PreviousLastNameID"), "Zeel", "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectContactCodeID"), "IND(+91)", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("ContactNumID"), "9923423567", "", "", "", "", "");

			fl.selectDropdownByxpath(driver, Environment("SSNNoFormatxpath"), "Last 5 Digits", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("SSNNumID"), "12342", "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectHighestDegreeID"), "Bachelors Degree", "", "", "", "",
					"");

			fl.ClickByID(driver, Environment("DateOfBirthID"), "", "", "", "", "");

			fl.selectDropdownByxpath(driver, Environment("DOBMonthxpath"), "Mar", "", "", "", "", "");

			fl.selectDropdownByxpath(driver, Environment("DOBYearxpath"), "1994", "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("DOBDatexpath"), "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectTimeZone1ID"), "", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("AboutYourselfID"), "12342", "", "", "", "", "");

			fl.ClickByID(driver, Environment("UpdateGeneralinfoID"), "12342", "", "", "", "");

			fl.ClickByXpath(driver, Environment("JobRequirmentxpath"), "", "", "", "", "");

			fl.entervalueByID(driver, Environment("TittleID"), "ABC", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("TotalExperienceYearsID"), "1", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("TotalExperienceMonthID"), "2", "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("JobTypeinfoxpath"), "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("SelectJobTypexpath"), "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectavailabilityID"), "2 weeks Notice", "", "", "", "",
					"");

			fl.selectDropdownByID(driver, Environment("SelectVisaTypeID"), "B-1", "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectSponserOptionID"), "Open for W2", "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("IndustriesWorkedxpath"), "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("SelectIndustriesWorkxpath"), "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("SelectJobProfileCategoryID"), "Education", "", "", "", "",
					"");

			fl.selectDropdownByID(driver, Environment("SelectJobProfileSubCategoryID"), "Director of Education", "",
					"", "", "", "");

			fl.selectDropdownByID(driver, Environment("MinSalariCurrencyID"), "USD", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("MinSalariID"), "60", "", "", "", "", "");

			fl.selectDropdownByID(driver, Environment("MinSalariRateCurrencyID"), "USD", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("MinSalaruRateID"), "20", "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CurrentLocationID"), "Hydrabad", "", "", "", "", "");

			fl.ClickByID(driver, Environment("UpdateJobRequirementID"), "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("UpdateJobRequirementID"), "", "", "", "", "");

			fl.entervalueByXpath(driver, Environment("CurrentLocationID"), "Java", "", "", "", "", "");

			fl.ClickByID(driver, Environment("UpdateJobRequirementID"), "", "", "", "", "");

		} catch (WebDriverException e) {
			e.printStackTrace();
		}

	}
	public void companyRegistration(WebDriver driver, String S1, String S2, String S3, String S4, String S5, String S6,
			String S7, String S8, String S9,String S10)
			throws IOException, InterruptedException, FileNotFoundException, ClassNotFoundException, SQLException 
	{

		try 
		{
			
			//ExcelUtils RC = new ExcelUtils(Environment("Excel"));

			fl.invokeApplication(driver, Environment("BaseURL"), "", "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("CompanySignUPxpath"), "", "", "", "", "");

			fl.ClickByID(driver, Environment("CompanytabID"), "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CompanyfirstnameID"), S1, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CompanyNameID"), S2, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CompanyEmailID"), S3, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CompanyContactNumID"), S4, "", "", "", "", "");

			fl.entervalueByName(driver, Environment("CompanyFEINName"), S5, "", "", "", "", "");
			FEIN_From_Excel = S5;

			System.out.println(FEIN_From_Excel);
			fl.entervalueByName(driver, Environment("CompanyWebSiteName"), S6, "", "", "", "", "");

			fl.entervalueByName(driver, Environment("CompanyPasswordName"), S7, "", "", "", "", "");

			fl.entervalueByID(driver, Environment("CompanyconfirmPasswordID"), S8, "", "", "", "", "");

			fl.entervalueByXpath(driver, Environment("Captcha"), S9, "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("CompanyCheckboxXpath"), "", "", "", "", "");

			// Thread.sleep(3000);
			fl.ClickByXpath(driver, Environment("CompanyRegisterxpath"), "", "", "", "", "");
			Thread.sleep(10000);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void VerifyEmaidID(WebDriver driver,String FEIN_From_Excel)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		
		try {
			
			String Veify_Code = DB_Connection_Digi_Company.Db_Connect(FEIN_From_Excel);
			System.out.println(Veify_Code);

			fl.entervalueByXpath(driver, Environment("Verififcation_textboxXPATH"), Veify_Code, "", "", "", "", "");

			fl.ClickByXpath(driver, Environment("verify_click"), "", "", "", "", "");
			Thread.sleep(10000);

			if (driver.findElement(By.xpath(Environment("successXPATH"))).isDisplayed()) {
				fl.ClickByXpath(driver, Environment("successXPATH"), "", "", "", "", "");
			}
			// WebElement Resend
			// =driver.findElement(By.xpath(Environment("ResendXpath")));

			else {
				fl.ClickByXpath(driver, Environment("ResendXpath"), "", "", "", "", "");
				String Veify_Code_1 = DB_Connection_Digi_Company.Db_Connect(FEIN_From_Excel);
				System.out.println(Veify_Code_1);

				fl.entervalueByXpath(driver, Environment("Verififcation_textbox"), Veify_Code, "", "", "", "", "");
				fl.ClickByXpath(driver, Environment("verify_click"), "", "", "", "", "");

			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}

	}
	public void userCreation_inCompany(WebDriver driver, String S1, String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10, String S11, String S12, String S13, String S14, String S15, String S16) throws IOException, InterruptedException 
	{
		//forcely navigating to this page
		//driver.get("http://localhost:15860/CompanyArea/User/CreateNewUser");
		System.out.println("creating user");
		try 
		{
			
			
			if(driver.findElement(By.xpath(Environment("Masterxpath"))).isEnabled())
			{
		
					fl.ClickByXpath(driver, Environment("Masterxpath"), "", "", "", "", "");

					fl.ClickByXpath(driver, Environment("Usersxpath"), "", "", "", "", "");

					fl.ClickByXpath(driver, Environment("AddNewUserxpath"), "", "", "", "", "");
				
					fl.entervalueByID(driver, Environment("UserNameID"), S1, "", "", "", "", "");

					fl.entervalueByID(driver, Environment("UserEmpIdID"), S2, "", "", "", "", "");

					fl.entervalueByID(driver, Environment("UserEmailID"), S3, "", "", "", "", "");

					fl.entervalueByID(driver, Environment("UserContactNoID"), S4, "", "", "", "", "");

					fl.ClickByID(driver, Environment("UserDesignationID"), "", "", "", "", "");

					Functional_Cases_propread F_Cases = new Functional_Cases_propread();
					F_Cases.createUser_NewDesignation(driver, S6,S5);// F_Cases.createUser_NewDesignation(driver,i,"")..to select existed option
					F_Cases.createUser_NewRole(driver,S7,S8);

					fl.entervalueByID(driver, Environment("UserStreet1ID"), S12, "", "", "", "", "");

					fl.entervalueByID(driver, Environment("UserCityID"), S13, "", "", "", "", "");

					fl.selectDropdownByID(driver, Environment("UserCountryID"), S14, "", "", "", "", "");

					fl.selectDropdownByID(driver, Environment("UserStateID"), S15, "", "", "", "", "");

					fl.entervalueByID(driver, Environment("UserZipcodeID"), S16, "", "", "", "", "");

					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,250)", "");

					fl.ClickByXpath(driver, Environment("UserSaveButtonxpath"), "", "", "", "", "");
					System.out.println("user details saved");
					Thread.sleep(1000);
					
			
			}
			else
				System.out.println("Should get Approval by Clidiem Admin");
				
		}catch (WebDriverException e) {
			e.printStackTrace();
		}

	}
	public void createUser_NewDesignation(WebDriver driver, String new_or_Existed,String Designation)
			throws InterruptedException, IOException {
		
		Excel_Utils RC =new Excel_Utils(Environment("Excel"));

		try {
			
			if (new_or_Existed.equals("NEW"))// designation not there in dropdown it will create the option
			{
				// Add New Designation if first time login
				fl.ClickByXpath(driver, Environment("AddNewDesignationXPATH"), "", "", "", "", "");

				fl.entervalueByXpath(driver, Environment("DesignationNameXPATH"),
						Designation, "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("AddNewToListXPATH"), "", "", "", "", "");
			}
			// select the created designation value
			fl.ClickByID(driver, Environment("UserDesignationID"), "", "", "", "", "");
			
			fl.selectDropdownByID(driver, Environment("UserDesignationID"), Designation,
					"", "", "", "", "");
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
	public void createUser_NewRole(WebDriver driver, String Role,String Parent_Role) throws IOException, InterruptedException {
		
		try {
			
			
			if(Parent_Role!="Blank")
			//if (RC.getStringCellData(j, 9, "ADDUser") != "") designation not there in dropdown it will create the option
			{

				fl.ClickByID(driver, Environment("UserRoleID"), "", "", "", "", "");
				
				fl.selectDropdownByxpath(driver, Environment("AddNewUserRoleXPATH"), "+ Add New", "", "", "", "",
						"");
				fl.entervalueByXpath(driver, Environment("NewRoleNameXPATH"), Role,
						"", "", "", "", "");
				fl.selectDropdownByxpath(driver, Environment("ParentRoleXPTAH"),
						Parent_Role, "", "", "", "", "");
				fl.ClickByXpath(driver, Environment("AddNewRolebuttonXPATH"), "", "", "", "", "");
			}

			fl.ClickByID(driver, Environment("UserRoleID"), "", "", "", "", "");
			fl.selectDropdownByID(driver, Environment("UserRoleID"), Role, "", "", "", "", "");
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		}

	}
	
	public void create_survey_Categeory(WebDriver driver, String new_or_existedName, String UpdatedName, String Notes ) throws IOException
	{
		//add these to properties file
		String Setup_Xpath=Environment("Setup_Xpath");
		String SetupSurveyCategeories_Xpath=Environment("SetupSurveyCategeories_Xpath");
		String NewSurveyCategeory_Xpath=Environment("NewSurveyCategeory_Xpath");
		String SearchSurveyCategeory_Xpath=Environment("SearchSurveyCategeory_Xpath");
		String NameSurveyCategeory_Xpath=Environment("NameSurveyCategeory_Xpath");
		String NotesSurveyCategeory_Xpath=Environment("NotesSurveyCategeory_Xpath");
		String EditSurveyCategeory_Xpath=Environment("EditSurveyCategeory_Xpath");
		String saveSurveyCategeory_Xpath=Environment("saveSurveyCategeory_Xpath");
		String CancelSurveyCategeory_Xapth=Environment("CancelSurveyCategeory_Xapth");
		try 
		{
			
			fl.ClickByXpath(driver, Setup_Xpath, "", "", "", "", "");
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, SetupSurveyCategeories_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, SearchSurveyCategeory_Xpath, new_or_existedName, "", "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyCategeory_Xpath")).getText().contains("No matching records"))
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "", "", "", "");
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
			}
			else			
			if(fl.findByXpath(driver, Environment("SurveyCategeoryName_Xapth")).getText().equals(new_or_existedName))
			{
				fl.ClickByXpath(driver, EditSurveyCategeory_Xpath, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, UpdatedName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "", "", "", "");
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
				
			}
			else
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "", "", "", "");
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
				
			}
			/*if(New.equals(Environment("New/Edit")))//if you want to Edit existed
			{
							
				fl.ClickByXpath(driver, EditSurveyCategeory_Xpath, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, UpdatedName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "", "", "", "");
				
			}
			else
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "", "", "", "");
			
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "", "", "", "", "");
			}*/
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public void create_Gropus(WebDriver driver,  String new_or_existedName,
			 String UpdatedName, String Notes, String subGroup) throws IOException
	{
		//add Xpath to properties file
		String Setup_Xpath=Environment("Setup_Xpath");
		
		String NewbuttonSurveyGroup_Xpath=Environment("NewbuttonSurveyGroup_Xpath");
		String NameSurveyGroup_Xpath=Environment("NameSurveyGroup_Xpath");
		String SetupGroups_Xpath=Environment("SetupGroups_Xpath");
		String SaveSurveyGroup_Xpath=Environment("SaveSurveyGroup_Xpath");
		String searchSurveyGroup_Xpath=Environment("searchSurveyGroup_Xpath");
		String DropdownSelect_Xpath=Environment("DropdownSelect_Xpath");
		String DropdownSelectOptions_Xapth=Environment("DropdownSelectOptions_Xapth");
		String subGroup_SelectXpath=Environment("subGroup_SelectXpath");
		String NotesSurveyGroup_Xpath=Environment("NotesSurveyGroup_Xpath");
		String cancelSurveyGroup_Xpath=Environment("cancelSurveyGroup_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Setup_Xpath, "", "", "", "", "");
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, SetupGroups_Xpath, "", "", "", "", "");
			
			//Group name already there or not
			fl.entervalueByXpath(driver,searchSurveyGroup_Xpath , new_or_existedName, "", "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyGroup_Xpath")).getText().contains("No matching records"))
			{
				//create new Group
				
				fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "", "", "", "");
				
				//subgroup creation
				
				String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
				if(subgroup_TorF.equals("true"))
				{
					fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
				}
				else
				{
					//sub group creation
					//fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "", "", "", "");
					
					//fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "", "", "", "");
					
					fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "", "", "", "", "");
					
					fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
					
					//select subgroup
					//fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "", "", "", "");
					
					fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");				
				}
				
				fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, new_or_existedName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "", "", "", "", "");
				
				fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
				
				//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
			}
			else
				if(fl.findByXpath(driver, Environment("GroupName_Xpath")).getText().equals(new_or_existedName))
				{
				
					fl.ClickByXpath(driver, Environment("EditExistedSurveyGroup_Xpath"), "", "", "", "", "");
						//check subgroup existed or not
					String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
					if(subgroup_TorF.equals("true"))
					{
						fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					}
					else
					{
						//sub group creation
						fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "", "", "", "");
					
						fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "", "", "", "");
					
						fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "", "", "", "", "");
					
						fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
					
						//again search
						
						fl.entervalueByXpath(driver,searchSurveyGroup_Xpath , new_or_existedName, "", "", "", "", "");
						
						//select subgroup
						
						fl.ClickByXpath(driver, Environment("EditExistedSurveyGroup_Xpath"), "", "", "", "", "");
						
						fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					
										
					}
				
					fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, UpdatedName, "", "", "", "", "");
				
					fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "", "", "", "", "");
				
					fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
				
					//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
				
				
					}
					else
					{
				
						//create new Group
				
						fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "", "", "", "");
				
						//subgroup creation
				
						String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
						if(subgroup_TorF.equals("true"))
						{
							fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
						}
						else
						{
							//sub group creation
							//fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "", "", "", "");
							
							//fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "", "", "", "");
					
							fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "", "", "", "", "");
					
							fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
					
					
					
							//click new, select subgroup
							fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "", "", "", "");
							
							fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					
										
						}
				
						fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, new_or_existedName, "", "", "", "", "");
				
						fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "", "", "", "", "");
				
						fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "", "", "", "");
				
						//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
					}
			
				
			} 
			catch (InterruptedException e) 
			{
			
				e.printStackTrace();
			}
		
	}
	public void create_Survey(WebDriver driver,String SurveyCategeoryName, String SurveyCategeoryNotes,String SurveyGroupName
			, String SurveyGroupNotes, String SurveyGroupSubNotes, String SurveyName, String Description,
			String TemplateName, String Que_Control) throws IOException
	{
		Functional_Cases_propread func_case = new Functional_Cases_propread();
		String CreateSurvey_Questions=Environment("CreateSurvey_Questions");
		
		String Survey_Xpath=Environment("Survey_Xpath");
		String createSurvey_Xpath=Environment("createSurvey_Xpath");
		String SurveyCategeory_Xpath=Environment("SurveyCategeory_Xpath");
		String SurveyCategeoryOptions_Xpath=Environment("SurveyCategeory_Options_XPATH");
		String SurveyGroupName_Xpath=Environment("SurveyGroupName_Xpath");
		String SurveyGroupNameOptions_Xpath=Environment("SurveyGroupName_Options_Xpath");
		String SurveyName_Xapth=Environment("SurveyNameXPATH");
		String SurveyPreviewName_Xpath=Environment("SurveyPreviewName_Xpath");
		String Survey_Description_Xpath=Environment("Survey_Description_Xpath");
		String Survey_DescriptionPreview_Xpath=Environment("Survey_DescriptionPreview_Xpath");
		String SaveAsTemplate_Xpath=Environment("SaveAsTemplate_Xpath");
		String TemplateName_Xpath=Environment("TemplateName_Xpath");
		String SaveCreateSurevyAsTemplate_Xpath=Environment("SaveCreateSurevyAsTemplate_Xpath");
		String ExistedTemplates_Xpath=Environment("ExistedTemplates_Xpath");
		String selectExistedTemplate_select_Xpath=Environment("selectExistedTemplate_select_Xpath");
		String selectExistedTemplate_selectOptions_Xpath=Environment("selectExistedTemplate_selectOptions_Xpath");
		
		
		try 
		{
			
			//start=========================================
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
			//end=========================================
			//==========before going to create survey make sure that mentioned surveyCategeory And SurveyGroup
			
			String check_Survey_categeoryOPtion = fl.checkOptionValueInSelect(driver, SurveyCategeory_Xpath, SurveyCategeoryOptions_Xpath ,SurveyCategeoryName);
			
			System.out.println(check_Survey_categeoryOPtion);
			
			if(check_Survey_categeoryOPtion.equals("true"))
			{
				/*fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, SurveyCategeoryName, "", "", "", "", "");*/
				
			}
			else
			{
				//if not found create surveyCategeory, call createSurveyCategeory Method
				
				func_case.create_survey_Categeory(driver, SurveyCategeoryName, "", SurveyCategeoryNotes);
				
				/*Assert.fail("Mentioned SurveyCategeory Not Existed");*/
			}
			
			//=======Mentioned SurveyGroup existed or not==========
			//start=========================================
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
			//end=========================================
			String check_SurveyGroupOPtion= fl.checkOptionValueInSelect(driver, SurveyGroupName_Xpath, SurveyGroupNameOptions_Xpath, SurveyGroupName);
			
			System.out.println(check_SurveyGroupOPtion);
			
			if(check_SurveyGroupOPtion.equals("true"))
			{
				/*fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, SurveyGroupName, "", "", "", "", "");*/
				
			}
			else
			{
				//if not found create surveyCategeory, call createSurveyGroup Method
				
				func_case.create_Gropus(driver, SurveyGroupName, "", SurveyGroupNotes, SurveyGroupSubNotes);
				
				/*Assert.fail("Mentioned SurveyGroup Not Existed");*/
			}
			//start=========================================
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
			//end=========================================
			
			fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, SurveyCategeoryName, "", "", "", "", "");
			
			fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, SurveyGroupName, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, SurveyName_Xapth, SurveyName, "", "", "", "", "");
			
			
			
			//preview surveyname
			Assert.assertTrue(fl.findByXpath(driver, SurveyPreviewName_Xpath).getText().equals(SurveyName),
					"preview Not matches with  survayname");
			
			
			
			fl.entervalueByXpath(driver, Survey_Description_Xpath,Description ,"", "", "", "", "");
			
			//preview description
			Assert.assertTrue(fl.findByXpath(driver, Survey_DescriptionPreview_Xpath).getText().equals(Description)
					,"Preview Not Matches with Description");
			
			
			
			fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, TemplateName_Xpath,TemplateName, "", "", "", "", "");
			
			//Adding Questions hardcoded
			
			/*for(int i=0;i<3;i++)
			{
				func_case.AddQuestionto_CreateSurvey(driver, "", "", "", "",i+1);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,1000)", "");//scroll up
			}*/
					  
	


			
			
			
			
			/*fl.ClickByXpath(driver, ExistedTemplates_Xpath, "", "", "", "", "");
			
			String check_ExistedTemplates= fl.checkOptionValueInSelect(driver, selectExistedTemplate_select_Xpath, selectExistedTemplate_selectOptions_Xpath, TemplateName);
			
			System.out.println(check_SurveyGroupOPtion);*/
			
			//click on publish in survey
//===saved survey publish			
			//func_case.survey_publishSavedSurveyQue(driver, SurveyName);
			
			
			
//===========================================			
			
			
			
		} 
		catch (InterruptedException e) 
		{	
			e.printStackTrace();
		}
		
		
	}
	public void validatePublishedSurveyData(WebDriver driver, String categeoryname, String GroupName,
			String surveyname, String description, int No_Of_Que) throws IOException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		//===validate fields
		String Cat_Name_AfterPublish_Xpath=Environment("Cat_Name_AfterPublish_Xpath");
		String Group_Name_AfterPublish_Xpath=Environment("Group_Name_AfterPublish_Xpath");
		String Survey_Name_AfterPublish_Xpath=Environment("Survey_Name_AfterPublish_Xpath");
		String Description_AfterPublish_Xpath=Environment("Description_AfterPublish_Xpath");
		String No_Of_Que_AfterPublish_Xpath=Environment("No_Of_Que_AfterPublish_Xpath");
		String BackButton_Xpath=Environment("BackButton_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			//search with surveyname
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "", "", "", "", "");
			
			//click on more button
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			//click on view
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
			
			String cat_Name=fl.getTextXPATH(driver, Cat_Name_AfterPublish_Xpath, "", "", "", "", "");
			Assert.assertTrue(cat_Name.equals(categeoryname), "categeory name not matched after publish");
			
			String grp_Name= fl.getTextXPATH(driver, Group_Name_AfterPublish_Xpath, "", "", "", "", "");
			Assert.assertTrue(grp_Name.equals(GroupName), "Group name not matching after publish");
			
			String survy_Name = fl.getTextXPATH(driver, Survey_Name_AfterPublish_Xpath, "", "", "", "", "");
			Assert.assertTrue(survy_Name.equals(surveyname), "survay name not matched after publish");
			
			String descr = fl.getTextXPATH(driver, Description_AfterPublish_Xpath, "", "", "", "", "");
			Assert.assertTrue(descr.equals(description), "Description not matching afetr publish");
			
			Integer i = new Integer(No_Of_Que);
			String QueNO=i.toString();
			System.out.println(No_Of_Que);
			System.out.println(QueNO);
			
			String que_no = fl.getTextXPATH(driver, No_Of_Que_AfterPublish_Xpath, "", "", "", "", "");
			System.out.println(que_no);
			Assert.assertTrue(que_no.equals(QueNO), "Que No not matching after publish");
			
			//===after complete validation click on back button
			
			fl.ClickByXpath(driver, BackButton_Xpath, "", "", "", "", "");
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	public void updateSurveyName(WebDriver driver,String SearchWithSurveyName,String UpdatedSurveyCategeoryName, String UpdatedSurveyCategeoryNotes,String UpdatedSurveyGroupName
			, String UpdatedSurveyGroupNotes, String UpdatedSurveyGroupSubNotes, String UpdatedSurveyName, String UpdatedDescription,
			String UpdatedTemplateName) throws IOException
	{
		Functional_Cases_propread func_case = new Functional_Cases_propread();
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String EditonGrid_Xpath=Environment("EditonGrid_Xpath");
		
		//String Survey_Xpath=Environment("Survey_Xpath");
		String createSurvey_Xpath=Environment("createSurvey_Xpath");
		String SurveyCategeory_Xpath=Environment("SurveyCategeory_Xpath");
		String SurveyCategeoryOptions_Xpath=Environment("SurveyCategeory_Options_XPATH");
		String SurveyGroupName_Xpath=Environment("SurveyGroupName_Xpath");
		String SurveyGroupNameOptions_Xpath=Environment("SurveyGroupName_Options_Xpath");
		String SurveyName_Xapth=Environment("SurveyNameXPATH");
		String SurveyPreviewName_Xpath=Environment("SurveyPreviewName_Xpath");
		String Survey_Description_Xpath=Environment("Survey_Description_Xpath");
		String Survey_DescriptionPreview_Xpath=Environment("Survey_DescriptionPreview_Xpath");
		String SaveAsTemplate_Xpath=Environment("SaveAsTemplate_Xpath");
		String TemplateName_Xpath=Environment("TemplateName_Xpath");
		String updatecreateSurveyButton_Xpath=Environment("updatecreateSurveyButton_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		try 
		{
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			if(SearchWithSurveyName!="")
			{
				//need to filter
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "", "", "", "", "");
			}
			
			//click on more button
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, EditonGrid_Xpath , "", "", "", "", "");
			if(UpdatedSurveyCategeoryName!="")
			{
			
				String check_Survey_categeoryOPtion = fl.checkOptionValueInSelect(driver, SurveyCategeory_Xpath, SurveyCategeoryOptions_Xpath ,UpdatedSurveyCategeoryName);
			
				System.out.println(check_Survey_categeoryOPtion);
			
				if(check_Survey_categeoryOPtion.equals("true"))
				{
					/*fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, SurveyCategeoryName, "", "", "", "", "");*/
				
				}
				else
				{
					//if not found create surveyCategeory, call createSurveyCategeory Method
				
					func_case.create_survey_Categeory(driver, UpdatedSurveyCategeoryName, "", UpdatedSurveyCategeoryNotes);
					
					fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
					
					fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
				
					/*Assert.fail("Mentioned SurveyCategeory Not Existed");*/
				}
			
				//=======Mentioned SurveyGroup existed or not==========
				//start=========================================
				
			//end=========================================
			}
			if(UpdatedSurveyGroupName!="")
			{
				String check_SurveyGroupOPtion= fl.checkOptionValueInSelect(driver, SurveyGroupName_Xpath, SurveyGroupNameOptions_Xpath, UpdatedSurveyGroupName);
			
				System.out.println(check_SurveyGroupOPtion);
			
				if(check_SurveyGroupOPtion.equals("true"))
				{
					/*fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, SurveyGroupName, "", "", "", "", "");*/
				
				}
				else
				{
					//if not found create surveyCategeory, call createSurveyGroup Method
				
					func_case.create_Gropus(driver, UpdatedSurveyGroupName, "", UpdatedSurveyGroupNotes, UpdatedSurveyGroupSubNotes);
				
					/*Assert.fail("Mentioned SurveyGroup Not Existed");*/
					fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
					
					fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
				}
				
			}
			//start filling survey with updated details=========================================
			/*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");*/
				
			if(UpdatedSurveyCategeoryName!="")
			{
				fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, UpdatedSurveyCategeoryName, "", "", "", "", "");
			}
			if(UpdatedSurveyGroupName!="")
			{
				fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, UpdatedSurveyGroupName, "", "", "", "", "");
			}
			if(UpdatedSurveyName!="")
			{
				fl.entervalueByXpath(driver, SurveyName_Xapth, "", "", "", "", "", "");
				
				select_delete sel_del = new select_delete();
				
				
				fl.entervalueByXpath(driver, SurveyName_Xapth, UpdatedSurveyName, "", "", "", "", "");
			
			
				//preview surveyname
				Assert.assertTrue(fl.findByXpath(driver, SurveyPreviewName_Xpath).getText().equals(UpdatedSurveyName),
					"preview Not matches with  survayname");
			}
			
			if(UpdatedDescription!="")
			{
				fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");
				
				select_delete sel_del = new select_delete();
				
				fl.entervalueByXpath(driver, Survey_Description_Xpath,UpdatedDescription ,"", "", "", "", "");
			
			
				//preview description
				Assert.assertTrue(fl.findByXpath(driver, Survey_DescriptionPreview_Xpath).getText().equals(UpdatedDescription)
					,"Preview Not Matches with Description");
			
			}
			//not available Save As template in edit mode
			/*if(UpdatedTemplateName!="")
			{
			
				fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "", "", "", "");
				
				select_delete sel_del = new select_delete();
				
				fl.entervalueByXpath(driver, TemplateName_Xpath,"", "", "", "", "", "");
			
				fl.entervalueByXpath(driver, TemplateName_Xpath,UpdatedTemplateName, "", "", "", "", "");
			}*/
			
			fl.ClickByXpath(driver, updatecreateSurveyButton_Xpath, "", "", "", "", "");
			
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void publish_SavedSurvey()
	{
		
	}
	public void after_surveyCreationClick_Publish(WebDriver driver) throws IOException
	{
		String PublishCreateSurevyAsTemplate_Xpath= Environment("PublishCreateSurevyAsTemplate_Xpath");;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-1000)", "");
	//avoiding save			
			/*fl.ClickByXpath(driver, SaveCreateSurevyAsTemplate_Xpath, "", "", "", "", "");*/
			
			fl.ClickByXpath(driver, PublishCreateSurevyAsTemplate_Xpath, "", "", "", "", "");
			
			if(fl.findByXpath(driver, Environment("SuccessSave_Xpath")).getText().contains("Success"))
			{
				System.out.println(fl.findByXpath(driver, Environment("SuccessSave_Xpath")).getText());
			}
			else
			{
				System.out.println("MAndidatory fields should be filled");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public void AddQuestionto_CreateSurvey(WebDriver driver, String QuestionText, String QuestionTag, String AnswerType,
			String NoofOptions_OR_Text, int Question_No, String option1, String option2, String option3, String option4, 
			String option5, String option6, String option7, String option8, String option9, String option10) throws IOException
	{
		/*QuestionText="Q1";
		QuestionTag="Q_Tag1";
		AnswerType="Check Box";
		NoofOptions_OR_Text="2";*/
		
		String QuestionText_Xpath= Environment("QuestionText_Xpath");
		String QuestionTag_Xpath=Environment("QuestionTag_Xpath");
		String selectTag_AnswerType_Xpath=Environment("selectTag_AnswerType_Xpath");
		String selectTag_AnswerTypeOptions_Xpath=Environment("selectTag_AnswerTypeOptions_Xpath");
		String selectTag_NumberOfOptionsSelect_Xpath=Environment("selectTag_NumberOfOptionsSelect_Xpath");
		String selectTag_NumberOfOptionsSelectOptions_Xpath=Environment("selectTag_NumberOfOptionsSelectOptions_Xpath");
		String EnterLabelText_Xapth=Environment("EnterLabelText_Xapth");
		String AddQuestion_Xpath=Environment("AddQuestion_Xpath");
		String EnabledEnterLabelText_Xpath=Environment("EnabledEnterLabelText_Xpath");
		String NumberofOPtions_1_Text_Xpath=Environment("NumberofOPtions_1_Text_Xpath");
		String NumberofOPtions_2_Text_Xpath=Environment("NumberofOPtions_2_Text_Xpath");
		String NumberofOPtions_3_Text_Xpath=Environment("NumberofOPtions_3_Text_Xpath");
		String NumberofOPtions_4_Text_Xpath=Environment("NumberofOPtions_4_Text_Xpath");
		String NumberofOPtions_5_Text_Xpath=Environment("NumberofOPtions_5_Text_Xpath");
		String NumberofOPtions_6_Text_Xpath=Environment("NumberofOPtions_6_Text_Xpath");
		String NumberofOPtions_7_Text_Xpath=Environment("NumberofOPtions_7_Text_Xpath");
		String NumberofOPtions_8_Text_Xpath=Environment("NumberofOPtions_8_Text_Xpath");
		String NumberofOPtions_9_Text_Xpath=Environment("NumberofOPtions_9_Text_Xpath");
		String NumberofOPtions_10_Text_Xpath=Environment("NumberofOPtions_10_Text_Xpath");
		String QuestionTextPreview_Xpath=Environment("QuestionTextPreview_Xpath");
		//String Que_Prev_Xpath=Question_Xpath+"["+i+"]";
		//String Q1=Question_Xpath+"[1]";
		
		
		try 
		{
			fl.entervalueByXpath(driver, QuestionText_Xpath, QuestionText, "", "", "", "", "");
			Thread.sleep(1000);
			//check the que preview
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			
			/*if(fl.findByXpath(driver, Question_Xpath+"["+Question_No+"]").getText().equals(QuestionText))
			{
				System.out.println("question preview matches the text you entered in que field");
			}*/
			//jse.executeScript("window.scrollBy(0,1000)", "");
			fl.entervalueByXpath(driver, QuestionTag_Xpath, QuestionTag, "", "", "", "", "");
			
			String Check_AnswerType=fl.checkOptionValueInSelect(driver, selectTag_AnswerType_Xpath, selectTag_AnswerTypeOptions_Xpath, "Check Box");
			if(Check_AnswerType.equals("true"))
			{
				fl.selectDropdownByxpath(driver, selectTag_AnswerType_Xpath, AnswerType , "", "", "", "", "");
			}
			else
			{
				Assert.fail("AnswerType You have given in Excel Not Matched with Any one of the given");
			}
			System.out.println("Selected no of options");
			if(fl.findByXpath(driver, Environment("EnabledNumberOfOptioins_Xpath")).getText().contains("Number"))
			{
				System.out.println("Number value option enabled");
				String Check_options=fl.checkOptionValueInSelect(driver, selectTag_NumberOfOptionsSelect_Xpath, selectTag_NumberOfOptionsSelectOptions_Xpath, NoofOptions_OR_Text);
				if(Check_options.equals("true"))
				{
					
					fl.selectDropdownByxpath(driver, selectTag_NumberOfOptionsSelect_Xpath, NoofOptions_OR_Text, "", "", "", "", "");
					if(NoofOptions_OR_Text!="")
					{
						System.out.println("options are available"+NoofOptions_OR_Text);
						if(NoofOptions_OR_Text.equals("2"))
						{
							System.out.println("option values to be entered");
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("3"))
						{
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("4"))
						{
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("5"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("6"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("7"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("8"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("9"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_9_Text_Xpath, option9, "", "", "", "", "");
						
						}
						if(NoofOptions_OR_Text.equals("10"))
						{
						
							fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_9_Text_Xpath, option9, "", "", "", "", "");
							
							fl.entervalueByXpath(driver, NumberofOPtions_10_Text_Xpath, option10, "", "", "", "", "");
						
						}
					
					}
					else
					{
					System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
					}
				}
			}
			if(fl.findByXpath(driver, EnabledEnterLabelText_Xpath).getText().contains("Text"))
			{
				
				
				fl.entervalueByXpath(driver, EnterLabelText_Xapth, "", "", "", "", "", "");
			}
			else
			{
				System.out.println("Not Found");
			}
			
			
			
			
			fl.ClickByXpath(driver, AddQuestion_Xpath, "", "", "", "", "");
			//to validate question name
			if(Question_No<=4)
			{
				jse.executeScript("window.scrollBy(0,-1000)", "");//scroll up
			}
			
			Assert.assertTrue(fl.findByXpath(driver, QuestionTextPreview_Xpath+"["+Question_No+"]").getText().equals(QuestionText),
					"question preview not matches the text you entered in que field");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void save_CreatedSurvey(WebDriver driver) throws IOException
	{
		String SaveCreateSurevyAsTemplate_Xpath=Environment("SaveCreateSurevyAsTemplate_Xpath");
		try 
		{
			fl.ClickByXpath(driver, SaveCreateSurevyAsTemplate_Xpath, "", "", "", "", "");
		}
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
	}
	public void publishSavedSurveyQue(WebDriver driver, String SurveyName) throws IOException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String publishonGrid_Xpath=Environment("publishonGrid_Xpath");
		String Proceed_Xpath=Environment("Proceed_Xpath");
		String success_Publish_Xpath=Environment("success_Publish_Xpath");
		
		try 
		{
			
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "", "", "", "", "");
			
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			
			
			/*JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,1000)", "");
			
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");*/
			
			/*WebElement More_click = driver.findElement(By.xpath("//*[@id='divactionsquestionnaire']/button"));
			if(More_click.isEnabled())
			{
				System.out.println("button enabled");
			}
			*/
			
			//More_click.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, publishonGrid_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, Proceed_Xpath, "", "", "", "", "");
			
			if(fl.findByXpath(driver, success_Publish_Xpath).getText().contains("Success"))
			{
				System.out.println(fl.findByXpath(driver, success_Publish_Xpath).getText());
			}
			
			
			
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
