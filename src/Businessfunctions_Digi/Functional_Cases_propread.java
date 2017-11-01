package Businessfunctions_Digi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import Utilities_Digi.ScrollDownRobot;
import Utilities_Digi.UploadFile_Robot;
import Utilities_Digi.closeopenedtab_robo;
import Utilities_Digi.copiedText_Robo;
import Utilities_Digi.copy_OpenTab_Paste;
import Utilities_Digi.newTab_robot;
import Utilities_Digi.paste_CopiedSurveyLinkRobo;
import Utilities_Digi.scrollDownInternal_Robo;
import Utilities_Digi.select_delete;
import Utilities_Digi.switchToSecondTab;

public class Functional_Cases_propread extends Environment_proprties_Read
{
	
	Functional_Libraries fl = new Functional_Libraries();
	UploadFile_Robot UR = new UploadFile_Robot();
	String FEIN_From_Excel;
	String oldTab;
	
	
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
			//Thread.sleep(10000);
			fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");

			driver.manage().window().maximize();
			//fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

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
	public void CompanyProfile(WebDriver driver, String S1, String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10
			, String S11, String S12, String S13, String S14, String S15, String S16, String S17, String S18, String S19, String S20
			, String S21, String S22, String S23, String S24, String S25, String S26, String S27, String S28, String S29, String S30
			, String S31, String S32, String S33, String S34, String S35, String S36, String S37, String S38, String S39, String S40
			, String S41, String S42, String S43, String S44, String S45, String S46, String S47, String S48, String S49) throws InterruptedException, IOException {
		
		
		try {
			System.out.println("company profile Fill");
			// find i value in which email id is there pass that to company
				// profile element locators

				fl.ClickByXpath(driver, Environment("CompanyProfileSettingxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("CompanyProfilexpath"), "", "", "", "", "");

				fl.ClickByID(driver, Environment("EditpersonalInfoID"), "", "", "", "", "");

				fl.entervalueByID(driver, Environment("CompanyLegalNameiD"),
						S1, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("CompanySiteNameID"),
						S2, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectCompanyTypeID"),
						S3, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectBusinessStructureID"),
						S4, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("CompanyContactNumberID"),
						S5, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("CompanydunsNumID"),
						S6, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectIndustryprofileID"),
						S7, "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("SelectProfileSubIndustryxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("Selectallsubindustryxpath"), "", "", "", "", "");

				fl.entervalueByID(driver, Environment("CompanyFoundedYearID"),
						S8, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectCompanySizeID"),
						S9, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectCompanyCurrencyID"),
						S10, "", "", "", "", "");

				fl.selectDropdownByxpath(driver, Environment("SelectCompanyTimesheetTypeID"),
						S11, "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("StaffingSolutionRedioButtonxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("StaffingCategoriesSupportedxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("SelectStaffingsupportoptionxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("StaffingServicesxpath"), "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("SelectStaffingServicesxpath"), "", "", "", "", "");

				fl.entervalueByID(driver, Environment("RegisterdStreetID"),
						S12, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("RegisteredCityID"),
						S13, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectRegisteredCountryID"),
						S14, "", "", "", "", "");

				fl.selectDropdownByxpath(driver, Environment("SelectRegisteredStateID"),
						S15, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("RegisteredZipcodeID"),
						S16, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("RegisteredTimeZoneID"),
						S17, "", "", "", "", "");

				fl.checkboxByxpath(driver, Environment("SameAsRegisteredAddress"), "", "", "", "", "", "");

				// fl.ClickByID(driver,
				// Environment("updateCompanypersonalinfoID"), "", "", "",
				// "", "");

				fl.ClickByXpath(driver, Environment("ProfileNextButtonxpath"), "", "", "", "", "");

				// fl.ClickByXpath(driver,
				// Environment("ResponsiblePartiesxpath"), "", "", "", "",
				// "");

				fl.ClickByID(driver, Environment("addownerDetailsID"), "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectownerTitleID"),
						S18, "", "", "", "", "");

				fl.entervalueByXpath(driver, Environment("SelectNewOwnerXPATH"),
						S19, "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("NewOwner_Save_XPATH"), "", "", "", "", "");

				// select by passing a value if list is already there
				fl.selectDropdownByID(driver, Environment("SelectownerTitleID"),
						S19, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("ownerNameID"),
						S20, "", "", "", "", "");

				fl.entervalueByXpath(driver, Environment("OwnerSSNNoID"),
						S21, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("OwnerEmailID"),
						S22, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("OwnerContactNoID"),
						S23, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("OwnerHomeStreetID"),
						S24, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("ownerCityID"),
						S25, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectownercountryID"),
						S26, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectownerStateID"),
						S27, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("OwnerZipcodeID"),
						S28, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("OwnerTimeZoneID"),
						S29, "", "", "", "", "");

				fl.ClickByXpath(driver, Environment("SameMaillingAddressCheckboxXpath"), "", "", "", "", "");

				/*
				 * JavascriptExecutor jse = (JavascriptExecutor) driver;
				 * jse.executeScript("window.scrollBy(0,300)", "");
				 * 
				 * Thread.sleep(10000);
				 */
				fl.ClickByXpath(driver, Environment("updateownerdetailsID"), "", "", "", "", "");

				// fl.ClickByXpath(driver,
				// Environment("ProfileNextButtonxpath"), "", "", "", "",
				// "");

				fl.ClickByXpath(driver, Environment("BankAccountxpth"), "", "", "", "", "");

				fl.ClickByID(driver, Environment("AddBankDetailsID"), "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankRoutingNoID"),
						S30, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankNameID"),
						S31, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankAccountNoId"),
						S32, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectAccountTypeID"),
						S33, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankStreetID"),
						S34, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankcityID"),
						S35, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectBankCountryID"),
						S36, "", "", "", "", "");

				fl.selectDropdownByID(driver, Environment("SelectBankStateID"),
						S37, "", "", "", "", "");

				fl.entervalueByID(driver, Environment("BankZipcodeID"),
						S38, "", "", "", "", "");

				// fl.ClickByXpath(driver,
				// Environment("BankDetails_NextXPATH"), "", "", "", "",
				// "");

				fl.ClickByID(driver, Environment("SaveBankDetailsId"), "", "", "", "", "");

//Documents
				fl.ClickByXpath(driver, Environment("DocumentsXPATH"), "", "", "", "", "");
				
				//fl.ClickByXpath(driver, Environment("DocumentsEditXPATH"), "", "", "", "", "");
				
				fl.ClickByXpath(driver, Environment("AddDocumentsXPATH"), "", "", "", "", "");
				
				fl.selectDropdownByxpath(driver, Environment("DocumentTypeXPATH"),S42, "", "", "", "","");
				
				fl.entervalueByXpath(driver, Environment("DocumentNameXPATH"), S43, "", "", "", "","");
				
				fl.ClickByXpath(driver, Environment("ChooseFileXPATH"), "", "", "", "", "");
				
				UR.uploadFile("D:\\Sailaja\\DocumentsTab_Data\\File_Sample.txt");
				
				fl.ClickByXpath(driver, Environment("SaveDocumentsXPATH"), "", "", "", "", "");
				
				boolean Add_Insurance_Selection=driver.findElement(By.xpath(Environment("AddInsuranceXPATH"))).isSelected();
				if(Add_Insurance_Selection!=true)
				{
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,1000)", "");
					 
				}
				fl.ClickByXpath(driver, Environment("AddInsuranceXPATH"), "", "", "", "", "");
				
				fl.selectDropdownByxpath(driver, Environment("InsuranceTypeXPATH"),S44, "", "", "", "","");
				
				fl.selectDropdownByxpath(driver, Environment("InsuredAmountXPATH"),S45, "", "", "", "","");
				
				fl.entervalueByXpath(driver, Environment("InsuranceAmountValueXPATH"), S46, "", "", "", "","");
				
				//Added 46 should add in excel sheet
				fl.entervalueByXpath(driver, Environment("InsuranceValidFromXPATH"), S47, "", "", "", "", "");
				fl.entervalueByXpath(driver, Environment("InsuranceValidToXPATH"), S48, "", "", "", "", "");
				//changed 46 to 48
				fl.entervalueByXpath(driver, Environment("InsuranceDocumentNameXPATH"), S49, "", "", "", "","");
				
				fl.ClickByXpath(driver, Environment("BrowserInsuranceFileXPATH"), "", "", "", "", "");
				
				UR.uploadFile("D:\\Sailaja\\DocumentsTab_Data\\File_Sample.txt");
				
				fl.ClickByXpath(driver, Environment("SaveInsuranceXPATH"), "", "", "", "", "");
				
				System.out.println(driver.findElement(By.xpath(Environment("InsuranceNextButtonXPATH"))));
				boolean documents_next=driver.findElement(By.xpath(Environment("InsuranceNextButtonXPATH"))).isSelected();
				if(documents_next!=true)
				{
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,1000)", "");
					
				}
				
//Contacts
				fl.ClickByXpath(driver, Environment("InsuranceNextButtonXPATH"), "", "", "", "", "");

				//fl.ClickByXpath(driver, Environment("CompanyProfile_ContactDetails_XPATH"), "", "", "", "", "");

				fl.ClickByID(driver, Environment("EditContactDetailsID"), "", "", "", "", "");

				
				if (S39 != null&&S39 !=""
						&&S40!=null&&S40 !=""
						&&S41!=null&&S41 !="")
				{

					fl.selectDropdownByID(driver, Environment("SelectAccountManagerID"),
							S39, "", "", "", "", "");

					fl.selectDropdownByID(driver, Environment("SelectContractManagerID"),
							S40, "", "", "", "", "");

					fl.selectDropdownByID(driver, Environment("SelectDeliveryManagerID"),
							S41, "", "", "", "", "");
				}

				else {
					System.out.println(
							"Should update Account Manager,Contract Manager,Delivery Manager in CompanyProfile Sheet Based on ADDUSer Sheet");
					/*FunctionalCases F_Cases = new FunctionalCases();
					F_Cases.company_Logout(driver,"");*/
										
				}
				fl.ClickByID(driver, Environment("SaveContactDetailsID"), "", "", "", "", "");
				System.out.println("User Saved SUccessfully");
				/*Functional_Cases_propread F_Cases = new Functional_Cases_propread();
				F_Cases.company_Logout(driver,"");*/
			

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
	public void candidateRegistration(WebDriver driver, String firstname, String lastname, String emailid, String contactnumber, String password,
			String confirmpassword, String captcha) throws IOException, InterruptedException, ClassNotFoundException, SQLException 
	{
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		String Signup_Xpath=Environment("Signup_Xpath");
		String FirstNameIndividual_Xpath=Environment("FirstNameIndividual_Xpath");
		String LastNameIndividual_Xpath=Environment("LastNameIndividual_Xpath");
		String EmailIDIndividual_Xpath=Environment("EmailIDIndividual_Xpath");
		String ContactNumberIndividual_Xpath=Environment("ContactNumberIndividual_Xpath");
		String PasswordIndividual_Xpath=Environment("PasswordIndividual_Xpath");
		String ConfirmPasswordIndividual_Xpath=Environment("ConfirmPasswordIndividual_Xpath");
		String CaptchaIndivial_Xpath=Environment("CaptchaIndivial_Xpath");
		String AgreeIndividual_Xpath=Environment("AgreeIndividual_Xpath");
		String RegisterIndividual_Xpath=Environment("RegisterIndividual_Xpath");
		

		try {
			fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");
			
			fl.ClickByXpath(driver, Signup_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, FirstNameIndividual_Xpath, firstname, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, LastNameIndividual_Xpath, lastname, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, EmailIDIndividual_Xpath, emailid, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, ContactNumberIndividual_Xpath, contactnumber, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, PasswordIndividual_Xpath, password, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, ConfirmPasswordIndividual_Xpath, confirmpassword, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, CaptchaIndivial_Xpath, captcha, "", "", "", "", "");
			
			fl.ClickByXpath(driver, AgreeIndividual_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, RegisterIndividual_Xpath, "", "", "", "", "");

			/*String Verify_Code_Candit = DB_Connection_Digi_Candit.Db_Connect(S3);
			System.out.println(Verify_Code_Candit);

			fl.entervalueByXpath(driver, Environment("Verififcation_textboxXPATH"), Verify_Code_Candit, "", "", "",
					"", "");

			fl.ClickByXpath(driver, Environment("verify_click"), "", "", "", "", "");
			Thread.sleep(10000);*/

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
	public void companyRegistration(WebDriver driver, String yourname, String orgname, String orgemailid, String contno, String fein, String website,
			String password, String confirmpassword, String captcha) throws IOException
			
	{
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		String Signup_Xpath=Environment("Signup_Xpath");
		String Organization_Xpath=Environment("Organization_Xpath");
		String YourNameOrg_Xpath=Environment("YourNameOrg_Xpath");
		String OrgaNameOrg_Xpath=Environment("OrgaNameOrg_Xpath");
		String OrgEmailID_Xpath=Environment("OrgEmailID_Xpath");
		String ContactNumber_Xpath=Environment("ContactNumber_Xpath");
		String Fein_Xpath=Environment("Fein_Xpath");
		String WebsiteOrg_Xpath=Environment("WebsiteOrg_Xpath");
		String PasswordOrg_Xpath=Environment("PasswordOrg_Xpath");
		String ConfirmPasswordOrg_Xpath=Environment("ConfirmPasswordOrg_Xpath");
		String CaptchOrg_xpath=Environment("CaptchOrg_xpath");
		String AgreeOrg_Xpath=Environment("AgreeOrg_Xpath");
		String RegisterOrg_Xpath=Environment("RegisterOrg_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		try 
		{
			

			fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");

			fl.ClickByXpath(driver, Signup_Xpath, "", "", "", "", "");

			fl.ClickByXpath(driver, Organization_Xpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, YourNameOrg_Xpath, yourname, "", "", "", "", "");

			fl.entervalueByXpath(driver, OrgaNameOrg_Xpath, orgname, "", "", "", "", "");

			fl.entervalueByXpath(driver, OrgEmailID_Xpath, orgemailid, "", "", "", "", "");

			fl.entervalueByXpath(driver, ContactNumber_Xpath, contno, "", "", "", "", "");

			fl.entervalueByXpath(driver, Fein_Xpath, fein, "", "", "", "", "");
			FEIN_From_Excel = fein;

			System.out.println(FEIN_From_Excel);
			fl.entervalueByXpath(driver, WebsiteOrg_Xpath, website, "", "", "", "", "");

			fl.entervalueByXpath(driver, PasswordOrg_Xpath, password, "", "", "", "", "");

			fl.entervalueByXpath(driver, ConfirmPasswordOrg_Xpath, confirmpassword, "", "", "", "", "");

			fl.entervalueByXpath(driver, CaptchOrg_xpath, captcha, "", "", "", "", "");

			fl.ClickByXpath(driver, AgreeOrg_Xpath, "", "", "", "", "");

			// Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,1000)", "");
			fl.ClickByXpath(driver, RegisterOrg_Xpath, "", "", "", "", "");
			

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
	public void AddUsers_inCompany(WebDriver driver, String S1, String S2, String S3, String S4, String S5, String S6, String S7, String S8, String S9, String S10, String S11, String S12, String S13, String S14, String S15) throws IOException, InterruptedException 
	{
		
		System.out.println("creating users");
		String Setup_Xpath=Environment("Setup_Xpath");
		String SetupUsers_Xapth=Environment("SetupUsers_Xapth");
		String New_User_Xapth=Environment("New_User_Xapth");
		String Name_NewUser_Xapth=Environment("Name_NewUser_Xapth");
		String EmployeeID_NewUser_Xapth=Environment("EmployeeID_NewUser_Xapth");
		String Email_NewUser_Xapth=Environment("Email_NewUser_Xapth");
		String Contact_NewUser_Xapth=Environment("Contact_NewUser_Xapth");
		String designation_NewUser_SelectXapth=Environment("designation_NewUser_SelectXapth");
		String designation_NewUser_SelectOptionsXapth=Environment("designation_NewUser_SelectOptionsXapth");
		String AddNewDesignation_Xpath=Environment("AddNewDesignation_Xpath");
		String NewDesignation_Xpath=Environment("NewDesignation_Xpath");
		String AdddesignationButton_Xpath=Environment("AdddesignationButton_Xpath");
		String Role_Select_Xapth=Environment("Role_Select_Xapth");
		String Role_SelectOption_Xapth=Environment("Role_SelectOption_Xapth");
		String RoleName_Xpath=Environment("RoleName_Xpath");
		String ParentRole_SelectXpath=Environment("ParentRole_SelectXpath");
		String AddRole_Xpath=Environment("AddRole_Xpath");
		String ReportingManager_Select_Xpath=Environment("ReportingManager_Select_Xpath");
		String ReportingManager_SelectOptions_Xpath=Environment("ReportingManager_SelectOptions_Xpath");
		String HRManager_SelectXpath=Environment("HRManager_SelectXpath");
		String HRManager_SelectOption_Xpath=Environment("HRManager_SelectOption_Xpath");
		String Street1_Xpath=Environment("Street1_Xpath");
		String City_Xapth=Environment("City_Xapth");
		String Country_Select_Xpath=Environment("Country_Select_Xpath");
		String State_Select_Xapth=Environment("State_Select_Xapth");
		String Zipcode_Xpath=Environment("Zipcode_Xpath");
		String Filepath_Xpath=Environment("Filepath_Xpath");
		String save_NewUser_Xpath=Environment("save_NewUser_Xpath");
		
		try 
		{
			
			
			
					fl.ClickByXpath(driver, Setup_Xpath, "", "", "", "", "");

					fl.ClickByXpath(driver, SetupUsers_Xapth, "", "", "", "", "");
					
					fl.ClickByXpath(driver, New_User_Xapth, "", "", "", "", "");

					fl.entervalueByXpath(driver, Name_NewUser_Xapth, S1, "", "", "", "", "");

					fl.entervalueByXpath(driver, EmployeeID_NewUser_Xapth, S2, "", "", "", "", "");

					fl.entervalueByXpath(driver, Email_NewUser_Xapth, S3, "", "", "", "", "");

					fl.entervalueByXpath(driver, Contact_NewUser_Xapth, S4, "", "", "", "", "");

					String Designation = fl.checkOptionValueInSelect(driver, designation_NewUser_SelectXapth, designation_NewUser_SelectOptionsXapth ,S5);
					
					if(Designation.equals("true"))
					{
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, S5, "", "", "", "", "");
					}
					else
					{
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, "+ Add New", "", "", "", "", "");
						
						fl.entervalueByXpath(driver, NewDesignation_Xpath, S5, "", "", "", "", "");
						
						fl.ClickByXpath(driver, AdddesignationButton_Xpath, "", "", "", "", "");
						
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, S5, "", "", "", "", "");
						
					}
					
					String Role = fl.checkOptionValueInSelect(driver, Role_Select_Xapth, Role_SelectOption_Xapth ,S6);
					
					if(Role.equals("true"))
					{
						fl.selectDropdownByxpath(driver, Role_Select_Xapth, S6, "", "", "", "", "");
					}
					else
					{
						fl.selectDropdownByxpath(driver, Role_Select_Xapth, "+ Add New", "", "", "", "", "");
						
						fl.entervalueByXpath(driver, RoleName_Xpath, S6, "", "", "", "", "");
						
						fl.selectDropdownByxpath(driver, ParentRole_SelectXpath, S7, "", "", "", "", "");
						
						fl.ClickByXpath(driver, AddRole_Xpath, "", "", "", "", "");
						
						
					}
					

					/*Functional_Cases_propread F_Cases = new Functional_Cases_propread();
					F_Cases.createUser_NewDesignation(driver, S6,S5);// F_Cases.createUser_NewDesignation(driver,i,"")..to select existed option
					F_Cases.createUser_NewRole(driver,S7,S8);*/
					
					
					String ReportManager = fl.checkOptionValueInSelect(driver, ReportingManager_Select_Xpath, ReportingManager_SelectOptions_Xpath ,S8);
					if(ReportManager.equals("true"))
					{
						fl.selectDropdownByxpath(driver, ReportingManager_Select_Xpath, S8, "", "", "", "", "");
					}
					
					String HRManager = fl.checkOptionValueInSelect(driver, HRManager_SelectXpath, HRManager_SelectOption_Xpath ,S9);
					if(HRManager.equals("true"))
					{
						fl.selectDropdownByxpath(driver, HRManager_SelectXpath, S9, "", "", "", "", "");
					}

					fl.entervalueByXpath(driver, Street1_Xpath, S10, "", "", "", "", "");

					fl.entervalueByXpath(driver, City_Xapth, S11, "", "", "", "", "");

					fl.selectDropdownByxpath(driver, Country_Select_Xpath, S12, "", "", "", "", "");

					fl.selectDropdownByxpath(driver, State_Select_Xapth, S13, "", "", "", "", "");

					fl.entervalueByXpath(driver, Zipcode_Xpath, S14, "", "", "", "", "");
					
					fl.ClickByXpath(driver, Filepath_Xpath, "", "", "", "", "");
					
					UR.uploadFile(S15);

					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,250)", "");

					fl.ClickByXpath(driver, save_NewUser_Xpath, "", "", "", "", "");
					System.out.println("user details saved");
					Thread.sleep(1000);
					
			
			
				
		}catch (WebDriverException e) {
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
		
		/*String AddNew_SurveyCat_Xpath=Environment("AddNew_SurveyCat_Xpath");
		String NewcategeoryName_Xpath=Environment("NewcategeoryName_Xpath");
		String NewCategeoryNotes_Xpath=Environment("NewCategeoryNotes_Xpath");
		String AddCategeory_Xpath=Environment("AddCategeory_Xpath");*/
		try 
		{
			
			/*fl.selectDropdownByxpath(driver, AddNew_SurveyCat_Xpath, "+ Add New", "", "", "", "", "");
			
			fl.entervalueByXpath(driver, NewcategeoryName_Xpath, new_or_existedName, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, NewCategeoryNotes_Xpath, Notes, "", "", "", "", "");*/
			
			
			
			fl.ClickByXpath(driver, Setup_Xpath, "", "", "", "", "");
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, SetupSurveyCategeories_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, SearchSurveyCategeory_Xpath, new_or_existedName, "", "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyCategeory_Xpath")).getText().contains("No"))//No Survey Categories
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
			/*if(New.equals(Environment("New/Edit")))//if you want to Edit existed===========================No Need
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
			if(fl.findByXpath(driver, Environment("NoSurveyGroup_Xpath")).getText().contains("No"))//No Groups
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
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
				
				fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
				
				
			}
			
			//=======Mentioned SurveyGroup existed or not==========
			//start=========================================
			/*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");*/
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
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
				
				fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");
				
				/*Assert.fail("Mentioned SurveyGroup Not Existed");*/
			}
			//start=========================================
			/*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");*/
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
	public String[] viewSurvey(WebDriver driver, String surveyname) throws IOException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String SurvayName_InView_Xpath=Environment("SurvayName_InView_Xpath");
		String TotalQue_InView_Xpath=Environment("TotalQue_InView_Xpath");
		String Pages_InView_Xpath=Environment("Pages_InView_Xpath");
		String Que_Tpe_InView_Xpath=Environment("Que_Tpe_InView_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "", "", "", "", "");
			
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
			
			String survey_Name = fl.getTextXPATH(driver, SurvayName_InView_Xpath, "", "", "", "", "");
			
			String Total_Que = fl.getTextXPATH(driver, TotalQue_InView_Xpath, "", "", "", "", "");
			
			String Pages = fl.getTextXPATH(driver, Pages_InView_Xpath, "", "", "", "", "");
			
			//String Que_Type = fl.getTextXPATH(driver, Que_Tpe_InView_Xpath, "", "", "", "", "");
			
			return new String[]{ survey_Name , Total_Que , Pages};
		} 
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
		return null;
		
	}
	public String viewsurvey_QueType(WebDriver driver, int queNo) throws IOException
	{ 
		//call after viewSurvey()
		
		/*String Que_Tpe_InView_Xpath=Environment("Que_Tpe_InView_Xpath");
		
		String Que_Type = fl.getTextXPATH_WithoutClick(driver, Que_Tpe_InView_Xpath+"["+queNo+"]", "", "", "", "", "");
		System.out.println(Que_Type);
		return Que_Type;*/
		
		//frontend from end user validation
		String Responsesave_Xpath=Environment("Responsesave_Xpath");
		String ResponseSubmit_Xpath=Environment("ResponseSubmit_Xpath");
		String No_Que_Xpath=Environment("No_Que_Xpath");
		
		String Que_Xpath=Environment("Que_Xpath");
		
		String checkbox_Xapth=Environment("checkbox_Xapth");
		String dropdown_Xpath=Environment("dropdown_Xpath");
		String radiooptions_Xpath=Environment("radiooptions_Xpath");
		String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
		String rate_Xpath=Environment("rate_Xpath");
		String date_Xpath=Environment("date_Xpath");
		String File_Xpath=Environment("File_Xpath");
		String Phone_Xpath=Environment("Phone_Xpath");
		String feedback_Xpath=Environment("feedback_Xpath");
		String brief_Xpath=Environment("brief_Xpath");
		
		fl.invokeApplication(driver, "http://localhost:4034/Survey/Index/5pCJBNLMSls6u3NVbM2flA==/9JveYxxqcT1MkxEU9Y1iBZBQhKSsmwF37JHEzZk1zEc=", "", "", "", "", "", "");
			
			//List<WebElement> no_que=driver.findElements(By.xpath(No_Que_Xpath));
			
			
				
				if(queNo>5)
				{
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,200)", "");
				}
			
				constructor_checkbox obj_check = new constructor_checkbox();
				String check = obj_check.checkbox(driver, queNo);
				if(check!=null)
					return "Check Box";
				
				constructor_brief obj_brief = new constructor_brief();
				String brief = obj_brief.brief(driver, queNo);
				if(brief!=null)
					return "Text Area";
				
				constructor_date obj_date = new constructor_date();
				String date=obj_date.date(driver, queNo);
				if(date!=null)
					return "Date";
				
				constructor_feedback obj_feedback = new constructor_feedback();
				String feedback=obj_feedback.feedback(driver, queNo);
				if(feedback!=null)
					return "Single Line Text";
				
				constructor_File obj_file = new constructor_File();
				String file=obj_file.File(driver, queNo);
				if(file!=null)
					return "File Upload";
				
				constructor_Phone obj_phone = new constructor_Phone();
				String phone = obj_phone.Phone(driver, queNo);
				if(phone!=null)
					return "Number";
				
				constructor_radiooptions obj_radiooptions = new constructor_radiooptions();
				String radiooptions = obj_radiooptions.radiooptions(driver, queNo);
				if(radiooptions!=null)
					return "Radion Button";
				
				constructor_radioYesorNo obj_radioYN = new constructor_radioYesorNo();
				String radioYN = obj_radioYN.radioYesorNo(driver, queNo);
				if(radioYN!=null)
					return "Yes or No";
				
				constructor_rate obj_rate = new constructor_rate();
				String rate = obj_rate.rate(driver, queNo);
				if(rate!=null)
					return "Scale/Rate";
				
				contructor_dropdown obj_dropdown = new contructor_dropdown();
				String dropdown = obj_dropdown.dropdown(driver, queNo);
				if(dropdown!=null)
					return "Dropdown";
			
			/*try
			{	
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+dropdown_Xpath).isEnabled())
				{
					return "Dropdown";
				}
			}
			catch(Exception e)
			{
			
			}
				
		
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+checkbox_Xapth).isEnabled())
				{
					return "Check Box";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+radiooptions_Xpath).isEnabled())
				{
					return "Radion Button";				
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+radioYesorNo_Xpath).isEnabled())
				{
					return "Yes or No";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+rate_Xpath).isEnabled())
				{
					return "Scale/Rate";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+date_Xpath).isEnabled())
				{
					return "Date";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+File_Xpath).isEnabled())
				{
					return "File Upload";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+Phone_Xpath).isEnabled())
				{
					return "Number";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+feedback_Xpath).isEnabled())
				{
					return "Single Line Text";
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(fl.findByXpath(driver, Que_Xpath+"["+queNo+"]"+brief_Xpath).isEnabled())
				{
					return "Text Area";
				}
			}
			catch(Exception e)
			{
				
			}*/
			
		
		return null;
	}
	public void updateSurveyName(WebDriver driver,String SearchWithSurveyName,String UpdatedSurveyCategeoryName, String UpdatedSurveyCategeoryNotes,String UpdatedSurveyGroupName
			, String UpdatedSurveyGroupNotes, String UpdatedSurveyGroupSubNotes, String UpdatedSurveyName, String UpdatedDescription) throws IOException
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
		String NoSurveyFoundXpath=Environment("NoSurveyFoundXpath");
		try 
		{
			String NoSurveyFound = null;
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			if(SearchWithSurveyName!="")
			{
				//need to filter
				Thread.sleep(3000);
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "", "", "", "", "");
				
				
					if(fl.elementEnabled(driver, MoreOPtionsonGrid_Xpath).equals("true"))
					{
			
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
						fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
						
						fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
						
						fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "", "", "", "", "");
						
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
						fl.ClickByXpath(driver, EditonGrid_Xpath , "", "", "", "", "");
						
				
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
							fl.entervalueByXpath(driver, SurveyName_Xapth, "", "", "", "", "", "");
							fl.entervalueByXpath(driver, SurveyName_Xapth, "", "", "", "", "", "");
				
							Thread.sleep(1000);
				
							select_delete sel_del = new select_delete();
				
				
				
							//fl.clear_textfield(driver, SurveyName_Xapth);
				
							fl.entervalueByXpath(driver, SurveyName_Xapth, UpdatedSurveyName, "", "", "", "", "");
			
			
							//preview surveyname
							Assert.assertTrue(fl.findByXpath(driver, SurveyPreviewName_Xpath).getText().equals(UpdatedSurveyName),
									"preview Not matches with  survayname");
						}
			
						if(UpdatedDescription!="")
						{
							fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");
							fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");
							fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");
				
							Thread.sleep(1000);
				
							select_delete sel_del = new select_delete();
				
				
				
							//fl.clear_textfield(driver, Survey_Description_Xpath);
				
							fl.entervalueByXpath(driver, Survey_Description_Xpath, UpdatedDescription ,"", "", "", "", "");
			
			
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
					else
					{
						if(fl.elementEnabled(driver, NoSurveyFoundXpath).equals("true"))
						{
							NoSurveyFound = fl.getTextXPATH(driver, NoSurveyFoundXpath, "", "", "", "", "");
							
							System.out.println("Survey you searched with:"+SearchWithSurveyName+" "+NoSurveyFound);
						}
					}
					
						
					
				
			
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void publish_SavedSurvey(WebDriver driver)
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
		String EnabledNumberOfOptioins_Xpath=Environment("EnabledNumberOfOptioins_Xpath");
		String internalScrollbar_Xpath=Environment("internalScrollbar_Xpath");
		//String Que_Prev_Xpath=Question_Xpath+"["+i+"]";
		//String Q1=Question_Xpath+"[1]";
		
		
		try 
		{
			System.out.println("function executing : queno is "+Question_No);
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
			
			String Check_AnswerType=fl.checkOptionValueInSelect(driver, selectTag_AnswerType_Xpath, selectTag_AnswerTypeOptions_Xpath, AnswerType);
			if(Check_AnswerType.equals("true"))
			{
				fl.selectDropdownByxpath(driver, selectTag_AnswerType_Xpath, AnswerType , "", "", "", "", "");
			}
			else
			{
				Assert.fail("AnswerType You have given in Excel Not Matched with Any one of the given");
			}
			System.out.println("Selected no of options");
			
			if(fl.elementEnabled(driver, EnabledNumberOfOptioins_Xpath).equals("true"))
				{
					if(fl.findByXpath(driver,EnabledNumberOfOptioins_Xpath ).getText().contains("Number"))
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
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("3"))
								{
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("4"))
								{
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("5"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("6"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
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
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
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
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
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
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
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
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
					
							}
							else
							{
								System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
							}
						}
					}
				}
			
			if(fl.elementEnabled(driver, EnabledEnterLabelText_Xpath).equals("true"))
				{
					if(fl.findByXpath(driver, EnabledEnterLabelText_Xpath).getText().contains("Text"))
					{
						fl.entervalueByXpath(driver, EnterLabelText_Xapth, "", "", "", "", "", "");
					}
					else
					{
						System.out.println("Not Found");
					}
				}
			if(fl.findByXpath(driver, EnabledEnterLabelText_Xpath).getText().contains("Text"))
			{
				jse.executeScript("window.scrollBy(0,450)", "");
			}
			
			fl.ClickByXpath(driver, AddQuestion_Xpath, "", "", "", "", "");
			//to validate question name
			if(Question_No<=4)
			{
				jse.executeScript("window.scrollBy(0,-1000)", "");//scroll up
			}
			if(Question_No>=5)
			{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				//WebElement scroll = driver.findElement(By.xpath(internalScrollbar_Xpath));//*[@id='mCSB_2_dragger_vertical']/div
				WebElement que=driver.findElement(By.xpath(QuestionTextPreview_Xpath+"["+Question_No+"]"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView();", que);
				
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
		String No_ofsurveyDisplayed=Environment("No_ofsurveyDisplayed");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String MoreOPtionsonGrid_moreXpath=Environment("MoreOPtionsonGrid_moreXpath");
		String publishonGrid_Xpath=Environment("publishonGrid_Xpath");
		String Proceed_Xpath=Environment("Proceed_Xpath");
		String success_Publish_Xpath=Environment("success_Publish_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		
		try 
		{
			
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "", "", "", "", "");
			
			List<WebElement> surveys = driver.findElements(By.xpath(No_ofsurveyDisplayed));
			int no_ofsurveys = surveys.size();
			System.out.println(no_ofsurveys+"no of surveys displayed");
			if(no_ofsurveys>1)
			{
				for(int i=0;i<no_ofsurveys;i++)
				{
					int j=0;
					
					String surveynametext = surveys.get(i).getText();
					
					if(surveynametext.equals(SurveyName))
					{
						j=i+1;
						fl.ClickByXpath(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]", "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]", "", "", "", "", "");
						jse.executeScript("window.scrollBy(0,700)", "");
					}
				}
			}
			if(no_ofsurveys==1)
			{
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			}
			
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
	public void share_survey(WebDriver driver,String SurveyName, String EmailIDs, int noOfshares) throws IOException
	{
		String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String shareonGrid_Xpath=Environment("shareonGrid_Xpath");
		String EmailId_Xpath=Environment("EmailId_Xpath");
		String sendSurvey_Xpath=Environment("sendSurvey_Xpath");
		String getLink_Xpath=Environment("getLink_Xpath");
		
		String WebLinks_Survey_Xpath=Environment("WebLinks_Survey_Xpath");
		String Link_Survey_Xpath=Environment("Link_Survey_Xpath");
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "", "", "", "", "");
			
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			Thread.sleep(1000);
			
			/*fl.ClickByXpath(driver, shareonGrid_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, EmailId_Xpath, EmailIDs, "", "", "", "", "");
			
			fl.ClickByXpath(driver, sendSurvey_Xpath, "", "", "", "", "");*/
			
			//get link
			
			/*ScrollDownRobot scroll_robo = new ScrollDownRobot();
			
			fl.ClickByXpath(driver, getLink_Xpath, "", "", "", "", "");
			
			Actions act = new Actions(driver);
			act.doubleClick(driver.findElement(By.xpath(getLink_Xpath))).build().perform();
			act.click(driver.findElement(By.xpath(getLink_Xpath))).build().perform();
			
			Thread.sleep(3000);
			
			copy_OpenTab_Paste survey_Link_open = new copy_OpenTab_Paste();*/
			
			
			
//go to view survey > WebLinks > to get surveylink			
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
			
			fl.ClickByXpath(driver, WebLinks_Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, Link_Survey_Xpath, "", "", "", "", "");
			
			//for my ref added these 2 lines
            /*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");*/
			
			
			
			
//link opening in enduser module instaed of in share, below statement placed in enduser
			String Parent=driver.getWindowHandle();
			
			copiedText_Robo Copy_Survey_Link = new copiedText_Robo();
			
			//get the surveylinktext from new tab
			
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			System.out.println(newTab.size());
			newTab.remove(Parent);
			System.out.println(newTab.get(0));
			// change focus to new tab
			driver.switchTo().window(newTab.get(0));
			
			String SurveyURL = driver.getCurrentUrl();
			FileInputStream fis = new FileInputStream(new File("SurveyLink_Excel\\Survey_Links.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			
			System.out.println("exceel row "+noOfshares+" is set by survey link :"+SurveyURL);
			
			sheet1.getRow(noOfshares).createCell(0).setCellValue(SurveyURL);
			
			FileOutputStream fout = new FileOutputStream("SurveyLink_Excel\\Survey_Links.xlsx");
			wb.write(fout);
			wb.close();
			
			driver.close();
			Thread.sleep(3000);
			
			driver.switchTo().window(Parent);
			
			//logout current window , inorder to login as an end user , to answer survey
			
			/*Functional_Cases_propread fun_propread = new Functional_Cases_propread();
			fun_propread.CompanyLogout(driver);*/

			
			
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		//return null;
	}
	/*Incase of gmail
	public void enduser_Login(WebDriver driver, String URL, String name, String password, String searchtext, int enduser_no) throws IOException*/
	public void enduser_Login(WebDriver driver, String URL, String EmailID, String password) throws IOException
	{
		/*INCASE OF GMAIL
		 * String NewUserAccount_Xpath=Environment("NewUserAccount_Xpath");
		String username_Xpath=Environment("username_Xpath");
		String user_Next_Xpath=Environment("user_Next_Xpath");
		String password_Xpath=Environment("password_Xpath");
		String passwordNext_Xpath=Environment("passwordNext_Xpath");
		String search_Xpath=Environment("search_Xpath");
		String click_search_Xpath=Environment("click_search_Xpath");
		String click_survey_Xpath=Environment("click_survey_Xpath");
		String link_Click_Xpath=Environment("link_Click_Xpath");
		String no_of_mails_Xpath=Environment("no_of_mails_Xpath");
		String last_mail_Xpath=Environment("last_mail_Xpath");*/
		
		String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
		//String Digi_CompanyLoginxpath=Environment("Digi_CompanyLoginXPATH");
		String Company_EmailIDxpath=Environment("Company_EmailIDXPATH");
		String Company_Passwordxpath=Environment("Company_PasswordXPATH");
		String Company_LoginButtonxpath=Environment("Company_LoginButtonXPATH");
		
		try
		{
			//child
			fl.invokeApplication(driver, Company_Baseurl, "", "", "", "", "", "");

			//fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, "", "", "", "", "");

			fl.entervalueByXpath(driver, Company_Passwordxpath, password, "", "", "", "", "");

			Thread.sleep(3000);
			// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
			// "", "", "", "");
			fl.ClickByXpath(driver, Company_LoginButtonxpath, "", "", "", "", "");
			
			Thread.sleep(3000);
			
			
			
			
			
			
			/*INCASE OF USING GMAIL
			fl.invokeApplication(driver, URL, "", "", "", "", "", "");
		
			 if(enduser_no>1)
				fl.ClickByXpath(driver, NewUserAccount_Xpath, "", "", "", "", "");
		
			fl.entervalueByXpath(driver, username_Xpath, name, "", "", "", "", "");
		
			fl.ClickByXpath(driver, user_Next_Xpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, password_Xpath, password, "", "", "", "", "");
		
			fl.ClickByXpath(driver, passwordNext_Xpath, "", "", "", "", "");
			
			
		
			
			fl.entervalueByXpath(driver, search_Xpath, searchtext, "", "", "", "", "");
		
			fl.ClickByXpath(driver, click_search_Xpath, "", "", "", "", "");
		
			fl.ClickByXpath(driver, click_survey_Xpath, "", "", "", "", "");   
		
			List<WebElement> mails=driver.findElements(By.xpath(no_of_mails_Xpath));
			int size=mails.size();
			
			System.out.println("no of mails are: "+size);
			
			if(size==1)
			{
				Thread.sleep(1000);
				
				fl.ClickByXpath(driver, link_Click_Xpath, "", "", "", "", "");
				
			
			}
			else
			{
				for(int i=1;i<size;i++)
				{
					if(i==size-1)
					{
						last_mail_Xpath=last_mail_Xpath+"["+i+"]";
					
						fl.ClickByXpath(driver, last_mail_Xpath, "", "", "", "", "");
			
						fl.ClickByXpath(driver, link_Click_Xpath, "", "", "", "", "");
					}
			
				}
			}  */
		}
		catch(Exception e)
		{
			
		}
		
	}
	public void parent_getSurveyLink(WebDriver driver, String SurveyName) throws IOException
	{
		String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String WebLinks_Survey_Xpath=Environment("WebLinks_Survey_Xpath");
		String Link_Survey_Xpath=Environment("Link_Survey_Xpath");
		
		
		try 
		{
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "", "", "", "", "");
	
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
		
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
		
			fl.ClickByXpath(driver, WebLinks_Survey_Xpath, "", "", "", "", "");
		
			fl.ClickByXpath(driver, Link_Survey_Xpath, "", "", "", "", "");
		
			//copiedText_Robo Copy_Survey_Link = new copiedText_Robo();
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	public void answerSurveyByEndUser(WebDriver driver, String SaveResponse, String SubmitResponse) throws IOException
	{
		
		
		/*String oldTab = driver.getWindowHandle();
		
		newTab_robot new_tab= new newTab_robot();
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		
		driver.close();
	    // change focus back to old tab
	    driver.switchTo().window(oldTab);*/
		
		
		String Responsesave_Xpath=Environment("Responsesave_Xpath");
		String ResponseSubmit_Xpath=Environment("ResponseSubmit_Xpath");
		String No_Que_Xpath=Environment("No_Que_Xpath");
		
		String Que_Xpath=Environment("Que_Xpath");
		
		String checkbox_Xapth=Environment("checkbox_Xapth");
		String dropdown_Xpath=Environment("dropdown_Xpath");
		String radiooptions_Xpath=Environment("radiooptions_Xpath");
		String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
		String rate_Xpath=Environment("rate_Xpath");
		String date_Xpath=Environment("date_Xpath");
		String File_Xpath=Environment("File_Xpath");
		String Phone_Xpath=Environment("Phone_Xpath");
		String feedback_Xpath=Environment("feedback_Xpath");
		String brief_Xpath=Environment("brief_Xpath");

		try 
		{
			//fl.invokeApplication(driver, "http://localhost:4034/Survey/Index/geeN4jI8BNlQO4R7hPA7ig==/GcuutBuEmAiLV152n3AUCmzcw8l-IgzndrsYavM25ME=", "", "", "", "", "", "");
			
			List<WebElement> no_que=driver.findElements(By.xpath(No_Que_Xpath));
			
			for(int i=1;i<=no_que.size();i++)
			{
				System.out.println(i);
				if(i>5)
				{
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,200)", "");
				}
				
					
				
				
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+dropdown_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.selectDropdownByIndexxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, 1, "", "", "", "", "");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+checkbox_Xapth).isEnabled())
					{
						System.out.println("checkbox");
						fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+checkbox_Xapth, "", "", "", "", "");
					
						continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try{
					
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+radiooptions_Xpath);
							String text=fl.getTextXPATH(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath+"/following-sibling::span", "", "", "", "", "");
							
							if(text.contains("Yes")||text.contains("No"))
							{
								//fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath, "", "", "", "", "");
							}
							
							{
								fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radiooptions_Xpath, "", "", "", "", "");
								continue;
							}
							
							
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath).isEnabled())
					{
								System.out.println(Que_Xpath+"["+i+"]"+radioYesorNo_Xpath);
								fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+radioYesorNo_Xpath, "", "", "", "", "");
								continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					
				
							if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+rate_Xpath).isEnabled())
							{
									System.out.println(Que_Xpath+"["+i+"]"+rate_Xpath);
									fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+rate_Xpath, "", "", "", "", "");
									continue;
							}
				}
			
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+date_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+date_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+date_Xpath, "08/21/1991", "", "", "", "", "");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+File_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+File_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.ClickByXpath(driver, Que_Xpath+"["+i+"]"+File_Xpath, "", "", "", "", "");
							UR.uploadFile("D:\\Sailaja\\ScreenshotOuputConsole\\10Company_Creation.png");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+Phone_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+Phone_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+Phone_Xpath, "9533676851", "", "", "", "", "");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+feedback_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+feedback_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+feedback_Xpath, "Single line comment", "", "", "", "", "");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
				try
				{
					if(fl.findByXpath(driver, Que_Xpath+"["+i+"]"+brief_Xpath).isEnabled())
					{
							System.out.println(Que_Xpath+"["+i+"]"+brief_Xpath);
							//fl.selectDropdownByxpath(driver, Que_Xpath+"["+i+"]"+dropdown_Xpath, "aeiou", "", "", "", "", "");
							fl.entervalueByXpath(driver, Que_Xpath+"["+i+"]"+brief_Xpath, "Multi line comment", "", "", "", "", "");
							continue;
					}
				}
				catch(Exception e)
				{
					
					//System.out.println(e.getMessage());
				}
					
				
				
				
			}
			if(SaveResponse!="")
			{
				fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
			}
			/*System.out.println(driver.getTitle());
			driver.close();
			// change focus back to old tab
		    driver.switchTo().window(oldTab);
		    System.out.println(driver.getTitle());*/
		    
			if(SubmitResponse!="")
			{
				//again  click on the gmail inbox >  survey link
			    
			    //again switch to new tab, check the entered option are saved or not after that submit
				
				/*String oldTab_1 = driver.getWindowHandle();
				
				newTab_robot new_tab_1= new newTab_robot();
				
				ArrayList<String> newTab_1 = new ArrayList<String>(driver.getWindowHandles());
				newTab.remove(oldTab_1);
				
				// change focus to new tab
				driver.switchTo().window(newTab_1.get(0));*/
				
				fl.invokeApplication(driver, "http://localhost:4034/Survey/Index/vGYFuSURgaQAOUOIIwpp6A==/iw4ovayLZcBt%20G61ZShWm0mL3w0MF-aGFzdaj9E-Gxc=", "", "", "", "", "", "");
				
				
				
				//fl.ClickByXpath(driver, ResponseSubmit_Xpath, "", "", "", "", "");
			}
			
			
			
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

		
		
		
	}
	public void answer_basedonQueType(WebDriver driver, int que_no, String que_type, String SurveyName) throws IOException, InterruptedException
	{
		//String que_type=null;
		  Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		  String Survey_Answers=Environment("Sheet_Survey_Answers"); 
		  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
		  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
		  String[] Survey_Answers_ele=new String[Survey_Answers_col]; 
		  for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
		  { 
			  System.out.println("for Loop" );
			  System.out.println(que_type);
			  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers)); 
			  if (que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)))
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
			  }
		  }
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String Responsesave_Xpath=Environment("Responsesave_Xpath");
		String belowResponsesave_Xpath=Environment("belowResponsesave_Xpath");
		String No_Que_Xpath=Environment("No_Que_Xpath");
		
		String Que_Xpath=Environment("Que_Xpath");
		
		String checkbox_Xapth=Environment("checkbox_Xapth");
		String dropdown_Xpath=Environment("dropdown_Xpath");
		String radiooptions_Xpath=Environment("radiooptions_Xpath");
		String radioYesorNo_Xpath=Environment("radioYesorNo_Xpath");
		String rate_Xpath=Environment("rate_Xpath");
		String date_Xpath=Environment("date_Xpath");
		String File_Xpath=Environment("File_Xpath");
		String Phone_Xpath=Environment("Phone_Xpath");
		String feedback_Xpath=Environment("feedback_Xpath");
		String brief_Xpath=Environment("brief_Xpath");
		
//===========newly added xpaths
		String noofcheckboxes_Xpath=Environment("noofcheckboxes_Xpath");
		String checkboxtext_Xapth=Environment("checkboxtext_Xapth");
		String checkboxtextspan_Xapth=Environment("checkboxtextspan_Xapth");
		
		String chk1_Xpath=Environment("chk1_Xpath");
		String chk2_Xpath=Environment("chk2_Xpath");
		
		String noofdropdownoptions_Xpath=Environment("noofdropdownoptions_Xpath");
		
		String noofradiooptions_Xpath=Environment("noofradiooptions_Xpath");
		String radiooption1_Xpath=Environment("radiooption1_Xpath");
		String radiooptionclick_Xpath=Environment("radiooptionclick_Xpath");
		String radiooptiontext_Xpath=Environment("radiooptiontext_Xpath");
		
		String ratedynamic_Xpath=Environment("ratedynamic_Xpath");
		
		String radioNo_Xpath=Environment("radioNo_Xpath");
		
//===========end newly added xpaths		
		
		//share Xpaths
		/*String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String WebLinks_Survey_Xpath=Environment("WebLinks_Survey_Xpath");
		String Link_Survey_Xpath=Environment("Link_Survey_Xpath");*/
		
		System.out.println("que type ="+que_type);
		
		if(que_no>=5)
		{
			if(fl.elementEnabled(driver, Que_Xpath+"["+que_no+"]").equals("true"))
			{
				WebElement que_enable = driver.findElement(By.xpath(Que_Xpath+"["+que_no+"]"));
				jse.executeScript("arguments[0].scrollIntoView();", que_enable);
			}
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
		int attempt=0;
			switch(que_type)
			{
				
				case "Check Box": System.out.println("checkbox to be clicked");
				
								  //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+checkbox_Xapth, "", "", "", "", "");
	//========================================			
								  String responseoptions = null;
								  //if you want remove above options[] intialize here
								  
				
								  List<WebElement> chkoptions = driver.findElements(By.xpath(Que_Xpath+"["+que_no+"]"+noofcheckboxes_Xpath));
								  int chksize = chkoptions.size();
								  for(int i=1;i<=chksize;i++)//to get each option text
								  {
									  //get the option text from survey link
									  if(fl.elementEnabled(driver, Que_Xpath+"["+que_no+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth).equals("true"))
									  {
										  //getting 1st option value from survey link
										  responseoptions=driver.findElement(By.xpath(Que_Xpath+"["+que_no+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth)).getText();
										  for(int j=0;j<10;j++)
										  {
							
											  if(responseoptions.equals(options[j]))//compare 1st option from survey link with each option from ans sheet
											  {
												  //answer write to excel sheet,along with queno
												  
												  
												  //matches get the column number from excel, now click on the respected i value
												  fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+chk1_Xpath+"["+i+"]"+chk2_Xpath, "", "", "", "", "");
												  attempt++;
											  }
							
										  }
									  }
									  if(i==chksize&&attempt==0)
									  {
										  System.out.println("You are not Answered this question");
									  }
									  else
									  {
										  if(i==chksize&&attempt!=0)
										  {
											  System.out.println("You are selected "+attempt+" checkboxes");
										  }
									  }
								  }
//=========================================								  
								  	if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								  	
								  	if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
								  		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
								  	break;
				
				case "Date": System.out.println("Date to be enetered");
				
							 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+date_Xpath, "08/21/1991", "", "", "", "", "");
//================							 
							 fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+date_Xpath,
									 Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")], "", "", "", "", "");
//================					
							 if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
								 fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
							 
							 if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
								 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
							 break;
									
				case "File Upload":System.out.println("file to be uploaded");
				
								   //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+File_Xpath, "", "", "", "", "");
								   //UR.uploadFile("D:\\Sailaja\\ScreenshotOuputConsole\\10Company_Creation.png");
//================					
								   fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+File_Xpath, "", "", "", "", "");
								   UR.uploadFile(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "File Upload")]);
//================									   
								   
								   if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
									   fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								   if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
										   fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
								   break;
									
				case "Dropdown":System.out.println("dropdown to be clicked");
				
							    //fl.selectDropdownByIndexxpath(driver, Que_Xpath+"["+que_no+"]"+dropdown_Xpath, 1, "", "", "", "", "");
//================					
								List<WebElement> dropOptions = driver.findElements(By.xpath(Que_Xpath+"["+que_no+"]"+noofdropdownoptions_Xpath));
								int optionsize = dropOptions.size();
				
								for(int i=1;i<=optionsize;i++)//to get option text from surveylink
								{
									String drop = fl.getTextXPATH(driver, Que_Xpath+"["+que_no+"]"+noofdropdownoptions_Xpath+"["+i+"]", "", "", "", "", "");
									for(int j=0;j<10;j++)//to get excel options
									{
										if(drop.equals(options[j]))
										{
											fl.selectDropdownByxpath(driver, Que_Xpath+"["+que_no+"]"+dropdown_Xpath, options[j], "", "", "", "", "");
											attempt++;
										}
									}
									if(i==optionsize&&attempt==0)
									{
										System.out.println("You are not Answered this question");
									}
									if(i==optionsize&&attempt!=0)
									{
										System.out.println("You are selected "+attempt+" dropboxes, but last string in excel is selected");
									}
									
								}
//================								    
							    if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
							    	fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
							    if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
							    		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
								break;
									
				case "Number":System.out.println("Number to be clicked");
				
							  //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+Phone_Xpath, "9533676851", "", "", "", "", "");
//================					
							  fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+Phone_Xpath, 
									  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Number")] , "", "", "", "", "");
//================	
							  if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
								  fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
							  if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
									  fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
							  break;
									
				case "Radio Button":System.out.println("radio button to be clicked");
								    
									//fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radiooptions_Xpath, "", "", "", "", "");
//===================				
									List<WebElement> radioOptions = driver.findElements(By.xpath(Que_Xpath+"["+que_no+"]"+noofradiooptions_Xpath));
									int radiosize = radioOptions.size();
				
									for(int i=1;i<=radiosize;i++)
									{
										String Radio = fl.getTextXPATH(driver, Que_Xpath+"["+que_no+"]"+radiooption1_Xpath+"["+i+"]"+radiooptiontext_Xpath, "", "", "", "", "");
										for(int j=0;j<10;j++)
										{
											if(Radio.equals(options[j]))
											{
												fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radiooption1_Xpath+"["+i+"]"+radiooptionclick_Xpath, "", "", "", "", "");
												attempt++;
											}
										}
										if(i==radiosize&&attempt==0)
										{
											System.out.println("You are not Answered this question");
										}
										else
										{
											if(i==radiosize&&attempt!=0)
											{
												System.out.println("You are selected "+attempt+" radiobutton, but last string in excel is selected");
											}
										}
									}
//=====================				
								    if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
								    	fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								    if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
								    		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
									break;
									
				case "Scale / Rate":System.out.println("rating to be entered");
				
									//fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+rate_Xpath, "", "", "", "", "");
//=====================				
									int rate = Integer.parseInt(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Scale / Rate")]);
									fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+ratedynamic_Xpath+"["+rate+"]", "", "", "", "", "");
//=====================									
									if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
										fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
									if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
											fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
									break;
									
				case "Single Line Text":System.out.println("single line text to be eneterd");
				
										//fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+feedback_Xpath, "Single line comment", "", "", "", "", "");
//==================										
										fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+feedback_Xpath, 
												Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Single Line Text")], "", "", "", "", "");
//====================				
										if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
											fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
										if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
												fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
										break;
									
				case "Text Area":System.out.println("text area to be entered");
				
								 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+brief_Xpath, "Multi line comment", "", "", "", "", "");
//======================								 
								 fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+brief_Xpath, 
										 Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "TextArea")], "", "", "", "", "");
//======================				
								 if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
									 fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								  if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
										 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
								 break;
									
				case "Yes or No":System.out.println("Y/N to be selected");
				
								 //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioYesorNo_Xpath, "", "", "", "", "");
//=========================								 
								 if(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")].equals("Yes"))
								 {
									 fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioYesorNo_Xpath, "", "", "", "", "");
								 }
								 else
								 {
									 if(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")].equals("No"))
									 {
										 fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioNo_Xpath, "", "", "", "", "");
									 }
								 }
//===========================				
								 if(fl.elementEnabled(driver, Responsesave_Xpath).equals("true"))
									 fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								 if(fl.elementEnabled(driver, belowResponsesave_Xpath).equals("true"))
										 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");
								 break;
				
				
			
		}
	}
	public void saveSurveySubmit(WebDriver driver, int totalque) throws IOException
	{
		String ResponseSubmit_Xpath=Environment("ResponseSubmit_Xpath");
		String belowResponseSubmit_Xpath=Environment("belowResponseSubmit_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		try 
		{
			if(totalque<=3)
			{
				if(fl.elementEnabled(driver, ResponseSubmit_Xpath).equals("true"))
				{
					WebElement submit_enable = driver.findElement(By.xpath(ResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					fl.ClickByXpath(driver, ResponseSubmit_Xpath, "", "", "", "", "");
					System.out.println("submit button clicked by enduser");
				}
			}
			
			
			if(totalque>3)
			{
				if(fl.elementEnabled(driver, belowResponseSubmit_Xpath).equals("true"))
				{
					WebElement submit_enable = driver.findElement(By.xpath(belowResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					fl.ClickByXpath(driver, belowResponseSubmit_Xpath, "", "", "", "", "");
					System.out.println("submit button clicked by enduser");
				}
			}
			
			
			//call robot method to close extra open window
			//closeopenedtab_robo closetab= new closeopenedtab_robo();
			
			//driver.switchTo().window(oldTab);
			System.out.println("Response Submitted by user");
		} 
		catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	public void CompanyLogout(WebDriver driver) throws IOException
	{
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String CompanyLogout_Xpath=Environment("CompanyLogout_Xpath");
		String CompanyLogout4_Xpath=Environment("CompanyLogout4_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, logout_img_Xpath, "", "", "", "", "");
			//fl.ClickByXpath(driver, logout_img_Xpath, "", "", "", "", "");
			if(fl.elementEnabled(driver, CompanyLogout_Xpath).equals("true"))
			{
				fl.ClickByXpath(driver, CompanyLogout_Xpath, "", "", "", "", "");
			}
			else
			{
				if(fl.elementEnabled(driver, CompanyLogout4_Xpath).equals("true"))
				{
					fl.ClickByXpath(driver, CompanyLogout4_Xpath, "", "", "", "", "");
				}
			}
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void surveycount(WebDriver driver) throws IOException
	{
		String Dashboard_Xpath=Environment("Dashboard_Xpath");
		
		String All_Survey_Xpath = Environment("All_Survey_Xpath");
		String open_Survey_Xpath=Environment("open_Survey_Xpath");
		String dratfs_Survey_Xpath=Environment("dratfs_Survey_Xpath");
		String closed_Survey_Xpath=Environment("closed_Survey_Xpath");
		String deleted_Survey_Xpath=Environment("deleted_Survey_Xpath");
		try 
		{
			fl.ClickByXpath(driver, Dashboard_Xpath, "", "", "", "", "");
			
			String all_surveys=fl.getTextXPATH(driver, All_Survey_Xpath, "", "", "", "", "");
			System.out.println("All Surveys:"+all_surveys);
			
			String open_surveys=fl.getTextXPATH(driver, open_Survey_Xpath, "", "", "", "", "");
			System.out.println("Opened Survey:"+open_surveys);
			
			String drafts_surveys=fl.getTextXPATH(driver, dratfs_Survey_Xpath, "", "", "", "", "");
			System.out.println("Drafts Surveys:"+drafts_surveys);
			
			String closed_surveys=fl.getTextXPATH(driver, closed_Survey_Xpath, "", "", "", "", "");
			System.out.println("closed Surveys:"+closed_surveys);
			
			String delete_surveys=fl.getTextXPATH(driver, deleted_Survey_Xpath, "", "", "", "", "");
			System.out.println("Deleted Surveys:"+delete_surveys);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
	public List<String> surveyresponse_ofEndUsers(WebDriver driver, String surveyname) throws IOException, InterruptedException
	{
		String ExtraAns =" ";
		
		String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		
		String SurveyResponses_Xpath=Environment("SurveyResponses_Xpath");
		String ViewDetails_EndUser_Xpath=Environment("ViewDetails_EndUser_Xpath");
		String getSurveyName_Xpath=Environment("getSurveyName_Xpath");
		String getNoofQuestins_Xpath=Environment("getNoofQuestins_Xpath");
		String AnswerXpath=Environment("AnswerXpath");
		String removeFromAnswer_Xpath=Environment("removeFromAnswer_Xpath");
		String getTotalQue_Xpath=Environment("getTotalQue_Xpath");
		
		try 
		{
			List<String> list = new ArrayList<>();
			String[] que_ans = null;
			int total_que = 0;
			String tot_q = null;
			String currenturl = driver.getCurrentUrl();
			if(!currenturl.contains("/SurveyView/"))
			{
				fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
				
				fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "", "", "", "", "");
				
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				
				fl.ClickByXpath(driver, viewonGrid_Xapth, "", "", "", "", "");
			}
			
			
			fl.ClickByXpath(driver, SurveyResponses_Xpath, "", "", "", "", "");
			
			String oldTab =driver.getWindowHandle();
			System.out.println("Parent Window :"+oldTab);
			
			System.out.println("before clicking the survey Response VIEW");
			
			if(fl.elementEnabled(driver, ViewDetails_EndUser_Xpath).equals("true"))
			{
				fl.ClickByXpath(driver, ViewDetails_EndUser_Xpath, "", "", "", "", "");
			
				Thread.sleep(3000);
			
				Set<String> set = new HashSet<String>(driver.getWindowHandles());
				for(String tab : set) 
				{
					System.out.println("window :"+tab);
				}
				set.remove(oldTab);
			
				driver.switchTo().window(set.iterator().next());
			
				System.out.println("child window URL : "+driver.getCurrentUrl());
			
				String name_survey = fl.getTextXPATH(driver, getSurveyName_Xpath, "", "", "", "", "");
				System.out.println(name_survey);
				
				//response ans info
				list.add(name_survey);
			
				List<WebElement> No_of_Que=driver.findElements(By.xpath(getNoofQuestins_Xpath));
			
				total_que=No_of_Que.size();
				
				if(fl.elementEnabled(driver, getTotalQue_Xpath).equals("true"))
				{
					WebElement tot_Ques = driver.findElement(By.xpath(getTotalQue_Xpath));
					
					tot_q=tot_Ques.getText();
					
					//response ans info
					list.add(tot_q);
				}
				
				System.out.println("Total Questions Are :"+total_que);
				
				que_ans = new String[total_que-1];
				
				for(int que=1;que <= total_que;que++)
				{
					String TotAns_answer = fl.getTextXPATH(driver, AnswerXpath+"["+que+"]", "", "", "", "", "");
					
					//System.out.println(TotAns_answer);//Ans: checkbox1
					
					String extra_Ans = fl.getTextXPATH(driver, removeFromAnswer_Xpath, "", "", "", "", "");
					
					//System.out.println(extra_Ans);//Ans:
					
					String remove_ans=extra_Ans+ExtraAns;//"Ans: "
					
					String actualAns= TotAns_answer.replace(remove_ans, "");
					
					System.out.println(actualAns);
					
					//que_ans[que-1]=actualAns;
					
					//response ans info
					list.add(actualAns);
					
				}
			}
			else
			{
				System.out.println("no one gives the response to this survey: "+surveyname);
			}
			return list;
		} 
		catch(NumberFormatException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	public void createQuizTempalte(WebDriver driver, String templateName, String QuizType,String QuizName, String QuizDescipt, 
			String SaveAsTempl, String NewTemplate, String Indiv_Que_Dur, String TotalMinutes , String EqualWeight,String score, String EqualDur, String seconds, String ExpiresInDays) throws IOException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String createQuiz_Xpath=Environment("createQuiz_Xpath");
		String selectTemplate_SelectXpath=Environment("selectTemplate_SelectXpath");
		String QuizType_SelectXpath=Environment("QuizType_SelectXpath");
		String selectTemplate_SelectOptionsXpath=Environment("selectTemplate_SelectOptionsXpath");
		String QuizName_Xapth=Environment("QuizName_Xapth");
		String QuizDescription_Xpath=Environment("QuizDescription_Xpath");
		String SaveAsTemplate_Xpath=Environment("SaveAsTemplate_Xpath");
		String Template_Xpath=Environment("Template_Xpath");
		String Individual_Que_Dur_Xpath=Environment("Individual_Que_Dur_Xpath");
		String Equal_Weight_Xpath=Environment("Equal_Weight_Xpath");
		String Equal_Weight_Score_Xpath=Environment("Equal_Weight_Score_Xpath");
		String Equal_Dura_Xpath=Environment("Equal_Dura_Xpath");
		String Equal_Dur_Sec_Xpath=Environment("Equal_Dur_Sec_Xpath");
		String TotalDur_Xpath=Environment("TotalDur_Xpath");
		String ExpiresInDays_Xpath=Environment("ExpiresInDays_Xpath");
		
		try 
		{
			JavascriptExecutor jse  = (JavascriptExecutor)driver;
			
			fl.ClickByXpath(driver, Quiz_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, createQuiz_Xpath, "", "", "", "", "");
			
			String template= fl.checkOptionValueInSelect(driver, selectTemplate_SelectXpath, selectTemplate_SelectOptionsXpath, templateName);
			
			if(template.equals("true"))
			{
				fl.selectDropdownByxpath(driver, selectTemplate_SelectXpath, templateName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizName_Xapth, QuizName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizDescription_Xpath, QuizDescipt, "", "", "", "", "");
				
				if(SaveAsTempl!="")
				{
					fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "", "", "", "");
					
					fl.entervalueByXpath(driver, Template_Xpath, templateName, "", "", "", "", "");
				}
			}
			else
			{
				fl.selectDropdownByxpath(driver, QuizType_SelectXpath, QuizType, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizName_Xapth, QuizName, "", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizDescription_Xpath, QuizDescipt, "", "", "", "", "");
				
				if(SaveAsTempl!="")
				{
					fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "", "", "", "");
					
					fl.entervalueByXpath(driver, Template_Xpath, templateName, "", "", "", "", "");
				}
				
				if(QuizType.equals("With Duration"))
				{
					if(Indiv_Que_Dur!="")
					{
						fl.ClickByXpath(driver, Individual_Que_Dur_Xpath, "", "", "", "", "");
					
						if(EqualWeight!="")
						{
							fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "", "", "", "", "");
						}
						if(EqualDur!="")
						{
							jse.executeScript("window.scrollBy(0,250)", "");
							
							fl.ClickByXpath(driver, Equal_Dura_Xpath, "", "", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Dur_Sec_Xpath, seconds, "", "", "", "", "");
						}
					}
					else
					{
						if(TotalMinutes!="")
						{
							fl.entervalueByXpath(driver, TotalDur_Xpath, TotalMinutes, "", "", "", "", "");
						}
						if(EqualWeight!="")
						{
							fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "", "", "", "", "");
						}
					}
				}
				else								//without duration
				{
					if(EqualWeight!="")
					{
						fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "", "", "", "");
					
						fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "", "", "", "", "");
					}
					fl.entervalueByXpath(driver, ExpiresInDays_Xpath, ExpiresInDays, "", "", "", "", "");
				}
					
			}
				
		} 
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	public void Quiz_Questions(WebDriver driver, String Que_text, String score, String seconds, String AnswerType, String NoofOptions_OR_Text,
			int Question_No, String option1,String option2,String option3,String option4,String option5,String option6,String option7,String option8
			,String option9,String option10, String EnterLabelText, String ValidateQue) throws IOException
	{
		String Que_Text_Quiz_Xpath = Environment("Que_Text_Quiz_Xpath");
		String Weightage_label_Xpath=Environment("Weightage_label_Xpath");
		String WeightageScore_QuizXpath=Environment("WeightageScore_QuizXpath");
		String Duration_Label_Xpath=Environment("Duration_Label_Xpath");
		String DurationInSeconds_QuizXpath=Environment("DurationInSeconds_QuizXpath");
		String AnswerType_Quiz_SelectXpath=Environment("AnswerType_Quiz_SelectXpath");
		String AnswerType_Quiz_SelectOption_Xpath=Environment("AnswerType_Quiz_SelectOption_Xpath");
		String NumberofOptionLabel_Xpath=Environment("NumberofOptionLabel_Xpath");
		String NumberofOption_Select_Xpath=Environment("NumberofOption_Select_Xpath");
		String NumberofOption_SelectOption_Xpath=Environment("NumberofOption_SelectOption_Xpath");
		String OPtions_1_Quiz_Text_Xpath=Environment("OPtions_1_Quiz_Text_Xpath");
		String OPtions_2_Quiz_Text_Xpath=Environment("OPtions_2_Quiz_Text_Xpath");
		String OPtions_3_Quiz_Text_Xpath=Environment("OPtions_3_Quiz_Text_Xpath");
		String OPtions_4_Quiz_Text_Xpath=Environment("OPtions_4_Quiz_Text_Xpath");
		String OPtions_5_Quiz_Text_Xpath=Environment("OPtions_5_Quiz_Text_Xpath");
		String OPtions_6_Quiz_Text_Xpath=Environment("OPtions_6_Quiz_Text_Xpath");
		String OPtions_7_Quiz_Text_Xpath=Environment("OPtions_7_Quiz_Text_Xpath");
		String OPtions_8_Quiz_Text_Xpath=Environment("OPtions_8_Quiz_Text_Xpath");
		String OPtions_9_Quiz_Text_Xpath=Environment("OPtions_9_Quiz_Text_Xpath");
		String OPtions_10_Quiz_Text_Xpath=Environment("OPtions_10_Quiz_Text_Xpath");
		String EnterLabelText_Quiz_Xpath=Environment("EnterLabelText_Quiz_Xpath");
		String EnterLabel_Quiz_Xpath=Environment("EnterLabel_Quiz_Xpath");
		String AddQuestion_Quiz_Xpath=Environment("AddQuestion_Quiz_Xpath");
		String Que_Queno_Xpath=Environment("Que_Queno_Xpath");
		String QuizQueno_Xpath=Environment("QuizQueno_Xpath");
		String QueExtraText=".  ";
		
		try 
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,1000)", "");
			
			fl.entervalueByXpath(driver, Que_Text_Quiz_Xpath, Que_text, "", "", "", "", "");
			
			
				//String Weightage_label = fl.getTextXPATH(driver, Weightage_label_Xpath, "", "", "", "", "");
				String Weightage_label =fl.elementEnabled(driver, Weightage_label_Xpath);
				
					if(Weightage_label.equals("true"))
					{
						fl.entervalueByXpath(driver, WeightageScore_QuizXpath, score, "", "", "", "", "");
					}
				
				//String Duration_label = fl.getTextXPATH(driver, Duration_Label_Xpath, "", "", "", "", "");
				
					String Duration_label =fl.elementEnabled(driver, Duration_Label_Xpath);
			
					if(Duration_label.equals("true"))
					{
						fl.entervalueByXpath(driver, DurationInSeconds_QuizXpath, seconds, "", "", "", "", "");
					}
				
			
			String AnswerType_check= fl.checkOptionValueInSelect(driver, AnswerType_Quiz_SelectXpath, AnswerType_Quiz_SelectOption_Xpath, AnswerType);
					
			if(AnswerType_check.equals("true"))
			{
				fl.selectDropdownByxpath(driver, AnswerType_Quiz_SelectXpath, AnswerType, "", "", "", "", "");
				
				fl.ClickByXpath(driver, AnswerType_Quiz_SelectXpath, "", "", "", "", "");
			}
			else
			{
				Assert.fail("AnswerType You have given in Excel Not Matched with Any one of the given");
			}
			
			
				String labelText = fl.elementEnabled(driver, NumberofOptionLabel_Xpath);
			
				if(labelText.equals("true"))
				{
					System.out.println("Number value option enabled");
				
					String Check_options=fl.checkOptionValueInSelect(driver, NumberofOption_Select_Xpath, NumberofOption_SelectOption_Xpath, NoofOptions_OR_Text);
				
					if(Check_options.equals("true"))
					{
					
						fl.selectDropdownByxpath(driver, NumberofOption_Select_Xpath, NoofOptions_OR_Text, "", "", "", "", "");
						
						fl.ClickByXpath(driver, NumberofOption_Select_Xpath, "", "", "", "", "");
					
						if(NoofOptions_OR_Text!="")
						{
							System.out.println("options are available "+NoofOptions_OR_Text);
						
							if(NoofOptions_OR_Text.equals("2"))
							{
								System.out.println("option values to be entered");
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
						
							}
							if(NoofOptions_OR_Text.equals("3"))
							{
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
								
							}
							if(NoofOptions_OR_Text.equals("4"))
							{
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("5"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("6"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("7"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("8"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("9"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_9_Quiz_Text_Xpath, option9, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("10"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_9_Quiz_Text_Xpath, option9, "", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_10_Quiz_Text_Xpath, option10, "", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
					
						}
						
					}
				}
				else
				{
					//System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
					
					
				}
				
				String text_check = fl.elementEnabled(driver, EnterLabelText_Quiz_Xpath);
				if(text_check.equals("true"))
				{
					if(text_check.contains("Text"))
					{
						if(EnterLabelText!="")
						{
							fl.entervalueByXpath(driver, EnterLabel_Quiz_Xpath, EnterLabelText, "", "", "", "", "");
						}
					
					}
					else
					{
						System.out.println("Not Found");
					}
				}
				
			System.out.println("befor addquestion click");
			
			fl.ClickByXpath(driver, AddQuestion_Quiz_Xpath, "", "", "", "", "");
			
			System.out.println("after addquestion click");
			
			//to validate question name
			if(Question_No<=4)
			{
				jse.executeScript("window.scrollBy(0,-1000)", "");//scroll up
			}
			if(ValidateQue!="")
			{
				if(fl.elementEnabled(driver, Que_Queno_Xpath).equals("true"))
				{
					System.out.println("Preview Que Text: "+fl.findByXpath(driver, Que_Queno_Xpath+"["+Question_No+"]").getText());
				
					String QueNoText = fl.findByXpath(driver, QuizQueno_Xpath+"["+Question_No+"]").getText();
					String ReplaceText = QueNoText+QueExtraText;
				
					String Quizque_text = fl.findByXpath(driver, Que_Queno_Xpath+"["+Question_No+"]").getText().replace(
						ReplaceText, "");
					System.out.println("After removing subString :"+Quizque_text);
				
					Assert.assertTrue(Quizque_text.equals(Que_text),"question preview not matches the text you entered in que field");
				}
			}
			
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void saveCreatedQuiz(WebDriver driver) throws IOException
	{
		String save_Quiz_Xpath=Environment("save_Quiz_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try
		{
			jse.executeScript("window.scrollBy(0,-1000)", "");
			fl.ClickByXpath(driver, save_Quiz_Xpath, "", "", "", "", "");
		} 
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
	}
	public void cancelCreatedQuiz(WebDriver driver) throws IOException
	{
		String cancel_Quiz_Xpath=Environment("cancel_Quiz_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try 
		{
			jse.executeScript("window.scrollBy(0,-1000)", "");
			fl.ClickByXpath(driver, cancel_Quiz_Xpath, "", "", "", "", "");
		} 
		catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	public void publishCreatedQuiz(WebDriver driver) throws IOException
	{
		String publish_Quiz_Xpath=Environment("publish_Quiz_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try 
		{
			jse.executeScript("window.scrollBy(0,-1000)", "");
			fl.ClickByXpath(driver, publish_Quiz_Xpath, "", "", "", "", "");
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	public void publishExistedQuiz(WebDriver driver, String quizname, String proceed) throws IOException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String PublishonGrid_Quiz_Xpath=Environment("PublishonGrid_Quiz_Xpath");
		String Proceed_QuizPublish_Xpath=Environment("Proceed_QuizPublish_Xpath");
		String cancel_QuizPublish_Xpath=Environment("cancel_QuizPublish_Xpath");
		String success_QuizPublish_Xpath=Environment("success_QuizPublish_Xpath");
		String Quizname_Status_Xpath=Environment("Quizname_Status_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Quiz_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "", "", "", "");
			
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "", "", "", "", "");
			
			//after filtering get tha status, if it is draft then only below statements to be executed
			
			if(fl.getTextXPATH(driver, Quizname_Status_Xpath, "", "", "", "", "").contains("Draft"))
			{
			
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			
				fl.ClickByXpath(driver, PublishonGrid_Quiz_Xpath, "", "", "", "", "");
			
				if(proceed!="")
				{
					fl.ClickByXpath(driver, Proceed_QuizPublish_Xpath, "", "", "", "", "");
				
					String success_msg = fl.getTextXPATH(driver, success_QuizPublish_Xpath, "", "", "", "", "");
				
					if(success_msg.contains("success"))
					{
						System.out.println(success_msg);
					}
				}
				else
				{
					fl.ClickByXpath(driver, cancel_QuizPublish_Xpath, "", "", "", "", "");
				}
			}
		}
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
	}
	

}
