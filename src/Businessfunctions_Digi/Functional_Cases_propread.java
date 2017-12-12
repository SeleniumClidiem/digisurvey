package Businessfunctions_Digi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;import java.io.FileOutputStream;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.testng.Assert;


import FunctionalLibraries_Digi.Functional_Libraries;
import Loggings_Digi.Logs_DigiSurvey;
import Utilities_Digi.ClickEnterRobot;
import Utilities_Digi.DB_Connection_Digi_Candit;
import Utilities_Digi.DB_Connection_Digi_Company;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;
import Utilities_Digi.ScrollDownRobot;
import Utilities_Digi.UploadFile_Robot;
import Utilities_Digi.clickTabRobo;
import Utilities_Digi.closeopenedtab_robo;
import Utilities_Digi.copiedText_Robo;
import Utilities_Digi.copy_OpenTab_Paste;
import Utilities_Digi.excelRW;
import Utilities_Digi.newTab_robot;
import Utilities_Digi.pasteLinkinchildRobo;
import Utilities_Digi.paste_CopiedSurveyLinkRobo;
import Utilities_Digi.scrollDownInternal_Robo;
import Utilities_Digi.select_delete;
import Utilities_Digi.switchToSecondTab;
import Utilities_Digi.twoTabRobo;

public class Functional_Cases_propread extends Environment_proprties_Read
{
	
	Functional_Libraries fl = new Functional_Libraries();
	UploadFile_Robot UR = new UploadFile_Robot();
	String FEIN_From_Excel;
	String oldTab;
	String alert;
	String alert_contacts;
	String alert_bank;
	String alert_doc;
	String alert_publicView;
	int failed_Fields_comInfo;
	int failed_Fields_contacts;
	int failed_Fields_bank;
	int failed_Fields_doc;
	int failed_Fields_pubView1;
	int failed_Fields_pubView2;
	int failed_Fields_pubView;
	int document_rows=0;
	
	public void adminLogin(WebDriver driver, String emailID, String password) throws IOException, InterruptedException
	{
		String admin_URL=Environment("admin_URL");
		String emailID_admin_Xpath=Environment("emailID_admin_Xpath");
		String password_admin_Xpath=Environment("password_admin_Xpath");
		String login_admin_Xpath=Environment("login_admin_Xpath");
		
		fl.invokeApplication(driver, admin_URL, "Chrome", "http://localhost:4034/SuperAdminArea/ClidiemUser/ClidiemLogin", "Login to Digi Admin", "Launching the Digi Admin Login Page", "", "Y");
		System.out.println(emailID);
		fl.entervalueByXpath(driver, emailID_admin_Xpath, emailID, emailID, "", "enter emailId", "", "");
		System.out.println(password);
		fl.entervalueByXpath(driver, password_admin_Xpath, password, password, "", "enter Password", "", "");
		fl.ClickByXpath(driver, login_admin_Xpath, "", "Click on Login button", "Navigate to home page succesful", "", "Y");
	}
	public void adminLogout(WebDriver driver) throws IOException, InterruptedException
	{
		String image_logout_admin_Xpath=Environment("image_logout_admin_Xpath");
		String logout_Xpath=Environment("logout_Xpath");
		
		fl.ClickByXpath(driver, image_logout_admin_Xpath, "", "", "Click on Logout List", "", "");
		fl.ClickByXpath(driver, logout_Xpath, "", "", "click on logout option", "", "");
	}
	public void adminUsers(WebDriver driver, String user, String email, String contact, String role, String password) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String users_Admin_Xpath=Environment("users_Admin_Xpath");
		String search_AU_Xpath=Environment("search_AU_Xpath");
		String norecords_AU_Xpath=Environment("norecords_AU_Xpath");
		String add_AU_Xpath=Environment("add_AU_Xpath");
		String user_AU_Xpath=Environment("user_AU_Xpath");
		String email_AU_Xpath=Environment("email_AU_Xpath");
		String contact_AU_Xpath=Environment("contact_AU_Xpath");
		String role_AU_Xpath=Environment("role_AU_Xpath");
		String roleOptions_AU_Xpath=Environment("roleOptions_AU_Xpath");
		String password_AU_Xpath=Environment("password_AU_Xpath");
		String confirmPwd_AU_Xpath=Environment("confirmPwd_AU_Xpath");
		String save_AU_Xpath=Environment("save_AU_Xpath");
		String succesAlert_AU_Xpath=Environment("succesAlert_AU_Xpath");
		String validationfail_AU_Xpath=Environment("validationfail_AU_Xpath");
		
		int failed=0;
		fl.ClickByXpath(driver, users_Admin_Xpath, "", "", "Creating Users", "", "");
		
		fl.entervalueByXpath(driver, search_AU_Xpath, user, "", "", "Search with username before create user", "", "");
		int noRec=fun_cas.listSize(driver, norecords_AU_Xpath);
		if(noRec==1)
		{
			fl.ClickByXpath(driver, add_AU_Xpath, "", "", "Click on add", "", "");
			fl.entervalueByXpath(driver, user_AU_Xpath, user, "", "", "Enter username", "", "");
			int failed1=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed1;
			fl.entervalueByXpath(driver, email_AU_Xpath, email, "", "", "enter email", "", "");
			int failed2=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed2;
			fl.entervalueByXpath(driver, contact_AU_Xpath, contact, "", "", "enter contact no", "", "");
			int failed3=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed3;
			String role_check=fl.checkOptionValueInSelect(driver, role_AU_Xpath, roleOptions_AU_Xpath, role);
			int failed4=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed4;
			if(role_check.equals("true"))
				fl.selectDropdownByxpath(driver, role_AU_Xpath, role, "", "", "Role to be selected", "", "");
			else
				fl.disp_Message(driver, "", "", "Mentioned Role is not existed in dropdown", "", "");
			int failed5=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed5;
			fl.entervalueByXpath(driver, password_AU_Xpath, password, "", "", "password to be entered", "", "");
			int failed6=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed6;
			fl.entervalueByXpath(driver, confirmPwd_AU_Xpath, password, "", "", "password to be entered", "", "");
			int failed7=fun_cas.listSize(driver, validationfail_AU_Xpath);
			failed=failed7;
			fl.ClickByXpath(driver, save_AU_Xpath, "", "", "Click on save", "", "");
			//int failed=fun_cas.listSize(driver, validationfail_AU_Xpath);
			if(failed==0)
			{
				Thread.sleep(3000);
				String suc_alert=fl.getTextXPATH(driver, succesAlert_AU_Xpath, "", "", "Get Success Text", "", "");
				fl.disp_Message(driver, "", "", "", suc_alert, "");
			}
			else
			{
				fl.disp_Message(driver, "", "", "Mandidatory fields are not Filled", "", "");
			}
		}
		else
			fl.disp_Message(driver, "", "", "User Already Existed", "", "");
		
	}
	public String companyBase(WebDriver driver, String petitionTitle) throws IOException, InterruptedException
	{
		String status = null;
		String digi_BaseURL=Environment("digi_BaseURL");
		String petitions_BaseURL_Xpath=Environment("petitions_BaseURL_Xpath");
		String petitionTitle_searchXpath=Environment("petitionTitle_searchXpath");
		String checkFilteredPetitions_Xpath=Environment("checkFilteredPetitions_Xpath");
		String clickOnPetitionName_Xpath=Environment("clickOnPetitionName_Xpath");
		String searchPetitionButton_Xpath=Environment("searchPetitionButton_Xpath");
		try 
		{
			fl.invokeApplication(driver, digi_BaseURL, "Chrome", "localhost:4034", "Launching The App", "launch the App Home Page", "Launched the page", "");
			
			fl.ClickByXpath(driver, petitions_BaseURL_Xpath, "", "petition list view", "navigating to petitions list", "", "");
			
			fl.entervalueByXpath(driver, petitionTitle_searchXpath, petitionTitle, petitionTitle, "search with petition name", "display the related result", "", "");
			
			Thread.sleep(1000);
			
			//fl.ClickByXpath(driver, searchPetitionButton_Xpath, "", "to see the results", "click on search button", "", "Y");
			
			twoTabRobo robo = new twoTabRobo();
			
			List<WebElement> petitions = driver.findElements(By.xpath(checkFilteredPetitions_Xpath));
			
			int petitions_size=petitions.size();
			
			if(petitions_size>1)
			{
				for(int i=0;i<petitions_size;i++)
				{
					if(fl.getTextXPATH(driver, checkFilteredPetitions_Xpath+"["+i+"]"+clickOnPetitionName_Xpath, "", "Comparing Search petition Text with PetitionList", "If existed find the matched one", "", "").equals(petitionTitle))
					{
						fl.ClickByXpath(driver, checkFilteredPetitions_Xpath+"["+i+"]"+clickOnPetitionName_Xpath, "", "Go to selected petition view", "click on searched petition", "", "");
						status="true";
						return status;
					}
				}
			}
			else
			{
				if(petitions_size==1)
				{
					fl.ClickByXpath(driver, checkFilteredPetitions_Xpath+"["+1+"]"+clickOnPetitionName_Xpath, "", "Go to selected petition view", "click on searched petition", "", "");
					status="true";
					return status;
				}
				else
				{
					fl.disp_Message(driver, "", "Petition you searched with is Not Existed", "No petition is available: "+petitionTitle, "", "");
					status="false";
					return status;
				}
			}
			
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
		return "false";
	}
	public void petitionSign_ExistedUser(WebDriver driver, String status,String username, String password,
			String address, String org_support, String donate, String readTerms) throws IOException, InterruptedException
	{
		
		String signPetitionButton_Xpath=Environment("signPetitionButton_Xpath");
		String signin_ExistedUser_Xpath=Environment("signin_ExistedUser_Xpath");
		String username_ExistedUser_Xpath=Environment("username_ExistedUser_Xpath");
		String password_ExistedUser_Xpath=Environment("password_ExistedUser_Xpath");
		String login_ExistedUser_Xpath=Environment("login_ExistedUser_Xpath");
		String oops_msg_Xpath=Environment("oops_msg_Xpath");
		String back_ExistedUser_Xpath=Environment("back_ExistedUser_Xpath");
		String address_ExistedUser_Xpath=Environment("address_ExistedUser_Xpath");
		String orgFor_ExistedUser_Select_Xpath=Environment("orgFor_ExistedUser_Select_Xpath");
		String orgFor_ExistedUser_SelectOptions_Xpath=Environment("orgFor_ExistedUser_SelectOptions_Xpath");
		String donateCheck_Sign_Xpath=Environment("donateCheck_Sign_Xpath");
		String checkTerms_ExistedUser_Xpath=Environment("checkTerms_ExistedUser_Xpath");
		String submit_ExistedUser_Xpath=Environment("submit_ExistedUser_Xpath");
		
		
		try 
		{
			
			if(status.equals("true"))
			{
				if(fl.elementDisplayed(driver, signPetitionButton_Xpath,"").equals("true"))
				{
					fl.ClickByXpath(driver, signPetitionButton_Xpath, "", "To sign the Petition", "click on Sign Petition", "", "");
				
				
					fl.ClickByXpath(driver, signin_ExistedUser_Xpath, "", "Login to Account to sign the Petition", "Login Popup Has to be displayed", "", "");
				
					fl.entervalueByXpath(driver, username_ExistedUser_Xpath, username, "", "", "Username Value to be entered", "", "");
				
					fl.entervalueByXpath(driver, password_ExistedUser_Xpath, password, "", "", "Password to be entered", "", "");
				
					fl.ClickByXpath(driver, login_ExistedUser_Xpath, "", "", "Login Button to be clicked", "", "");
				
					if(fl.elementDisplayed(driver, oops_msg_Xpath,"").equals("true"))
					{
						fl.disp_Message(driver, "", "You are Invalid User", "check the create profile while fill the sign form", "", "");
					
						fl.ClickByXpath(driver, back_ExistedUser_Xpath, "", "Go back to fill sign form and creating profile", "Get Back to Sign Petition Form", "", "");
					
//Not an existed user ...while fill the form ...check the create profile field					
					}
					else
					{
//Successfully login the existed user ...just fill the form and submit
					
						fl.entervalueByXpath(driver, address_ExistedUser_Xpath, address, "", "", "Address to be entered", "", "");
					
						String check_orgname = fl.checkOptionValueInSelect(driver, orgFor_ExistedUser_Select_Xpath, orgFor_ExistedUser_SelectOptions_Xpath, org_support);
					
						System.out.println("organisation name status: "+check_orgname);
					
						if(check_orgname.equals("true"))
						{
							fl.selectDropdownByxpath(driver, orgFor_ExistedUser_Select_Xpath, org_support, "", "", "Org u Support has to be selected", "", "");
						}
						else
						{
							fl.disp_Message(driver, "", "Organization u support is not existed in dropdown list", " test case Failed", "", "");
							Assert.fail();
						}
						if(!donate.equals(""))
						{
							fl.ClickByXpath(driver, donateCheck_Sign_Xpath, "", "Existed user wants to Donate money", "check Donate Checkbox", "", "");
						}
						if(!readTerms.equals(""))
						{
							fl.ClickByXpath(driver, checkTerms_ExistedUser_Xpath, "", "", "check Read Terms and conditions", "", "");
						
							fl.ClickByXpath(driver, submit_ExistedUser_Xpath, "", "", "Submit your form with sign", "", "");
						}
						else
						{
							fl.disp_Message(driver, "", "Not check the terms", "Without Accepting terms you cant submit your sign", "", "");
							Assert.fail();
						
						}
					}
					
				}
				else
					fl.disp_Message(driver, "", "Sign Button is not available for this petition ", "SignButton Not Displaying", "", "");
			}
			
			
			
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void petitionSign_NewUser(WebDriver driver, String status, String firstname, String lastname,String emailID, String countrycode,
			String phoneNo, String Address, String occupation, String OrgFor, String visa, String sign, String createProfile,String password, String donate, String readTerms) throws IOException, InterruptedException
	{
		String petitions_BaseURL_Xpath=Environment("petitions_BaseURL_Xpath");
		String petitionTitle_searchXpath=Environment("petitionTitle_searchXpath");
		String checkFilteredPetitions_Xpath=Environment("checkFilteredPetitions_Xpath");
		String clickOnPetitionName_Xpath=Environment("clickOnPetitionName_Xpath");
		String searchPetitionButton_Xpath=Environment("searchPetitionButton_Xpath");
		String signPetitionButton_Xpath=Environment("signPetitionButton_Xpath");
		String firstname_SignNew_Xpath=Environment("firstname_SignNew_Xpath");
		String lastname_SignNew_Xpath=Environment("lastname_SignNew_Xpath");
		String emailID_SignNew_Xpath=Environment("emailID_SignNew_Xpath");
		String countryCode_SignNew_Xpath=Environment("countryCode_SignNew_Xpath");
		String countrycode_SignNew_selectOptions_Xpath=Environment("countrycode_SignNew_selectOptions_Xpath");
		String phonenumber_SignNew_Xpath=Environment("phonenumber_SignNew_Xpath");
		String address_SignNew_Xpath=Environment("address_SignNew_Xpath");
		String occupation_SignNew_Xpath=Environment("occupation_SignNew_Xpath");
		String occupation_SignNew_SelectOptions_Xpath=Environment("occupation_SignNew_SelectOptions_Xpath");
		String organizationFor_SignNew_select_Xpath=Environment("organizationFor_SignNew_select_Xpath");
		String organizationFor_SignNew_selectOptions_Xpath=Environment("organizationFor_SignNew_selectOptions_Xpath");
		String visaStatus_SignNew_select_Xpath=Environment("visaStatus_SignNew_select_Xpath");
		String visaStatus_SignNew_selectOptions_Xpath=Environment("visaStatus_SignNew_selectOptions_Xpath");
		String signature_SignNew_Xpath=Environment("signature_SignNew_Xpath");
		String createURprofile_SignNew_Xpath=Environment("createURprofile_SignNew_Xpath");
		String enterpassword_SignNew_Xpath=Environment("enterpassword_SignNew_Xpath");
		String donate_SignNew_Xpath=Environment("donate_SignNew_Xpath");
		String readPrivacyTerms_SignNew_Xpath=Environment("readPrivacyTerms_SignNew_Xpath");
		String submit_SignNew_Xpath=Environment("submit_SignNew_Xpath");
		
		
		
		try 
		{
			
			if(status.equals("true"))
			{
				if(fl.elementDisplayed(driver, signPetitionButton_Xpath,"").equals("true"))
				{
					fl.ClickByXpath(driver, signPetitionButton_Xpath, "", "To sign the Petition", "click on Sign Petition", "", "");
				
				
					if(!firstname.equals(""))
					{
						fl.entervalueByXpath(driver, firstname_SignNew_Xpath, firstname, firstname, "", "firstname to be entered", "", "");
					}
				
					if(!lastname.equals(""))
					{
						fl.entervalueByXpath(driver, lastname_SignNew_Xpath, lastname, "", "", "lastname value to be entered", "", "");
					}
					if(!emailID.equals(""))
					{
						fl.entervalueByXpath(driver, emailID_SignNew_Xpath, emailID, "", "", "Email id to be entered", "", "");
					}
					if(!countrycode.equals(""))
					{
						String country_check = fl.checkOptionValueInSelect(driver, countryCode_SignNew_Xpath, countrycode_SignNew_selectOptions_Xpath, countrycode);
					
						if(country_check.equals("true"))
						{
							fl.selectDropdownByxpath(driver, countryCode_SignNew_Xpath, countrycode, "", "", "contry code to be selected", "", "");
						}
					}
					if(!phoneNo.equals(""))
					{
						fl.entervalueByXpath(driver, phonenumber_SignNew_Xpath, phoneNo, "", "", "phone number to be entered", "", "");
					}
					if(!Address.equals(""))
					{
						fl.entervalueByXpath(driver, address_SignNew_Xpath, Address, "", "", "Address Value to be entered", "", "");
					
						clickTabRobo clickTab= new clickTabRobo();
					}
					if(!OrgFor.equals(""))
					{
						String org_check= fl.checkOptionValueInSelect(driver, organizationFor_SignNew_select_Xpath, organizationFor_SignNew_selectOptions_Xpath, OrgFor);
					
						if(org_check.equals("true"))
						{
							fl.selectDropdownByxpath(driver, organizationFor_SignNew_select_Xpath, OrgFor, "", "", "Org to be seleted", "", "");
						}
						else
						{
							fl.disp_Message(driver, "", "", "Org you want to select is not existed", "", "");
						}
					}
					if(!sign.equals(""))
					{
						fl.entervalueByXpath(driver, signature_SignNew_Xpath, sign, "", "", "Signature Value to be entered", "", "");
					}
					if(createProfile!="")
					{
						fl.ClickByXpath(driver, createURprofile_SignNew_Xpath, "", "", "Checkbox to be clicked", "", "");
					
						fl.entervalueByXpath(driver,enterpassword_SignNew_Xpath , password, "", "", "password value to b entered", "", "");
					}
					if(!donate.equals(""))
					{
						fl.ClickByXpath(driver, donate_SignNew_Xpath, "", "New User donating money", "Donate Enabled", "", "");
					}
					if(!readTerms.equals(""))
					{
						fl.ClickByXpath(driver, readPrivacyTerms_SignNew_Xpath, "", "", "terms and conditions to be checked", "", "");
					}
				
					fl.ClickByXpath(driver, submit_SignNew_Xpath, "", "", "Submit button to be clicked", "", "");
				}
				else
					fl.disp_Message(driver, "", "", "Sign Button Not Displayed", "", "");
				
				
			}
			
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
			
		}
		
		
	}
	public void donateFund(WebDriver driver, String status, String amount, String cardno,String expDate, String cvv, String firstname, String lastname, String phone, String email
			,  String address1, String address2, String city, String state, String country, String postcode, String makepayment, String reset) throws IOException, InterruptedException
	{
		String fundingButton_Xpath=Environment("fundingButton_Xpath");
		String creditSelect_Xpath=Environment("creditSelect_Xpath");
		String ok_Xpath=Environment("ok_Xpath");
		String externalWebsite_allow_Xpath=Environment("externalWebsite_allow_Xpath");
		String amountCompanyDonate_Xpath=Environment("amountCompanyDonate_Xpath");
		String firstname_CompanyDonate_Xpath=Environment("firstname_CompanyDonate_Xpath");
		String lastname_CompanyDonate_Xpath=Environment("lastname_CompanyDonate_Xpath");
		String phone_CompanyDonate_Xpath=Environment("phone_CompanyDonate_Xpath");
		String email_CompanyDonate_Xpath=Environment("email_CompanyDonate_Xpath");
		String cardnumber_CompanyDonate_Xpath=Environment("cardnumber_CompanyDonate_Xpath");
		String expirationDate_CompanyDonate_Xpath=Environment("expirationDate_CompanyDonate_Xpath");
		String cvv_CompanyDonate_Xpath=Environment("cvv_CompanyDonate_Xpath");
		String billingAddress1_CompanyDonate_Xpath=Environment("billingAddress1_CompanyDonate_Xpath");
		String billingAddress2_CompanyDonate_Xpath=Environment("billingAddress2_CompanyDonate_Xpath");
		String city_CompanyDonate_Xpath=Environment("city_CompanyDonate_Xpath");
		String state_CompanyDonate_Xpath=Environment("state_CompanyDonate_Xpath");
		String country_CompanyDonate_Xpath=Environment("country_CompanyDonate_Xpath");
		String postcode_CompanyDonate_Xpath=Environment("postcode_CompanyDonate_Xpath");
		String makePayment_CompanyDonate_Xpath=Environment("makePayment_CompanyDonate_Xpath");
		String reset_CompanyDonate_Xpath=Environment("reset_CompanyDonate_Xpath");
		try 
		{
			System.out.println(status+" donating status");
			if(status.equals("true")||status.contains("donate"))
			{
				//if(status.contains("donate"))
					fl.ClickByXpath(driver, fundingButton_Xpath, "", "Start Funding", "click on funding button", "", "");

					fl.ClickByXpath(driver, creditSelect_Xpath, "", "Click type of payment", "Select payment type", "", "");
				
					fl.ClickByXpath(driver, ok_Xpath, "", "Click ok to proceed the payment", "Click Ok", "", "");
				
					String Parent = driver.getWindowHandle();
				
					fl.ClickByXpath(driver, externalWebsite_allow_Xpath, "", "", "view the content of an external website", "", "");
				
					Set<String> set = new HashSet<String>(driver.getWindowHandles());
					for(String tab : set)
					{
						System.out.println("window :"+tab);
					}
					set.remove(Parent);
		
					driver.switchTo().window(set.iterator().next());
				
					Thread.sleep(3000);
				
					System.out.println(driver.getCurrentUrl());
					if(!amount.equals(""))
					{
						fl.entervalueByXpath(driver, amountCompanyDonate_Xpath, amount, "", "Succefully navigate to 3 rd party web page", "Amount to be entered", "", "");
					}
					if(!firstname.equals(""))
					{
						fl.entervalueByXpath(driver, firstname_CompanyDonate_Xpath, firstname, "", "", "First name to be entered", "", "");
					}
					if(!lastname.equals(""))
					{
						fl.entervalueByXpath(driver, lastname_CompanyDonate_Xpath, lastname, "", "", "lastname to be entered", "", "");
					}
					if(!phone.equals(""))
					{
						fl.entervalueByXpath(driver, phone_CompanyDonate_Xpath, phone, "", "", "Phone num to be entered", "", "");
					}
					if(!email.equals(""))
					{
						fl.entervalueByXpath(driver, email_CompanyDonate_Xpath, email, "", "", "email to be entered", "", "");
					}
					if(!cardno.equals(""))
					{
						fl.entervalueByXpath(driver, cardnumber_CompanyDonate_Xpath, cardno, "", "", "valid card details to be entered", "", "");
					}
					if(!expDate.equals(""))
					{
						fl.entervalueByXpath(driver, expirationDate_CompanyDonate_Xpath, expDate, "", "", "ExpDate to be entered", "", "");
					}
					if(!cvv.equals(""))
					{
						fl.entervalueByXpath(driver, cvv_CompanyDonate_Xpath, cvv, "", "", "cvv has to be entered", "", "");
					}
					if(!address1.equals(""))
					{
						fl.entervalueByXpath(driver, billingAddress1_CompanyDonate_Xpath, address1, "", "", "address1 to be entered", "", "");
					}
					if(!address2.equals(""))
					{
						fl.entervalueByXpath(driver, billingAddress2_CompanyDonate_Xpath, address2, "", "", "address2 to be entered", "", "");
					}
					if(!city.equals(""))
					{
						fl.entervalueByXpath(driver, city_CompanyDonate_Xpath, city, "", "", "city to be entered", "", "");
					}
					if(!state.equals(""))
					{
						fl.entervalueByXpath(driver, state_CompanyDonate_Xpath, state, "", "", "State to be entered", "", "");
					}
					if(!country.equals(""))
					{
						fl.entervalueByXpath(driver, country_CompanyDonate_Xpath, country, "", "", "country to be entered", "", "");
					}
					if(!postcode.equals(""))
					{
						fl.entervalueByXpath(driver, postcode_CompanyDonate_Xpath, postcode, "", "", "postcode to be entered", "", "");
					}
				
					if(!makepayment.equals(""))
					{
						fl.ClickByXpath(driver, makePayment_CompanyDonate_Xpath, "", "", "make payment", "", "");
					}
					if(!reset.equals(""))
					{
						fl.ClickByXpath(driver, reset_CompanyDonate_Xpath, "", "", "reset payment", "", "");
					}
					driver.close();
					driver.switchTo().window(Parent);
				
				
			}
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
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
			Functional_Cases_propread fun_cas= new Functional_Cases_propread();
			//Thread.sleep(10000);
			fl.invokeApplication(driver, Company_Baseurl, "Chrome", "http://localhost:4034/Account/Login", "Launching The App", "launch the App Home Page", "Launched the page", "Y");
			Logs_DigiSurvey.info("Invoke the application");

			driver.manage().window().maximize();
			//fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");
			
			fun_cas.clearTextfield(driver, Company_EmailIDxpath, EmailID);
			Thread.sleep(10000);
			fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, "", "", "", "", "");
			Logs_DigiSurvey.info("Entering username");
			
			fun_cas.clearTextfield(driver, Company_Passwordxpath, password);
			Thread.sleep(10000);
			fl.entervalueByXpath(driver, Company_Passwordxpath, password, "", "", "", "", "");
			Logs_DigiSurvey.info("Entering password");

			
			// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
			// "", "", "", "");
			Thread.sleep(10000);
			fl.ClickByXpath(driver, Company_LoginButtonxpath, "", "", "Login button to be clicked", "", "");
			Logs_DigiSurvey.info("Click on Login button Successful");
			

			//Thread.sleep(8000);
		} catch (WebDriverException e) {
			e.printStackTrace();
			Logs_DigiSurvey.info(e.getMessage());
			fl.disp_Message(driver, "", "Invoke Application Failed", "", "", "Y");
		}

	}
	public void validateReferedFriends(WebDriver driver, String name) throws IOException, InterruptedException
	{
		String Company_LogoXPATH=Environment("Company_LogoXPATH");
		String MyReferrals_Company_Xpath=Environment("MyReferrals_Company_Xpath");
		String advancedSearch_MR_Xpath=Environment("advancedSearch_MR_Xpath");
		String myRefValid_MR_Xpath=Environment("myRefValid_MR_Xpath");
		String searchName_MR_Xpath=Environment("searchName_MR_Xpath");
		String existedRow_MR_Xpath=Environment("existedRow_MR_Xpath");
		String noRec_MR_Xpath=Environment("noRec_MR_Xpath");
		String nameValid_MR_Xpath=Environment("nameValid_MR_Xpath");
		
		fl.JS_Element_Find(driver, Company_LogoXPATH);
		fl.ClickByXpath(driver, Company_LogoXPATH, "", "Validating registered refered Friends", "click on logout menu", "", "");
		fl.ClickByXpath(driver, MyReferrals_Company_Xpath, "", "", "click on myReferrals", "", "");
		String myRefPage=fl.getTextXPATH(driver, myRefValid_MR_Xpath, "", "Validating My Referrals list loaded or not", "", "", "");
		if(!myRefPage.contains("My Referrals"))
		{
			Assert.fail();
		}
		fl.ClickByXpath(driver, advancedSearch_MR_Xpath, "", "", "click on AdvancedSearch", "", "");
		fl.entervalueByXpath(driver, searchName_MR_Xpath, name, "", "", "enter search text:"+name, "", "");
		List<WebElement> cols = driver.findElements(By.xpath(existedRow_MR_Xpath));
		int col = cols.size();
		if(col==1)
		{
			if(fl.elementDisplayed(driver, noRec_MR_Xpath, "").equals("true"))
			{
				fl.disp_Message(driver, "", "", "No records Found with "+name, "", "Y");
			}
		}
		else
		{
			List<WebElement> refered =driver.findElements(By.xpath(nameValid_MR_Xpath));
			int size = refered.size();
			if(size==1)
			{
				String search = fl.getTextXPATH(driver, nameValid_MR_Xpath+"["+1+"]", "", "", "", "", "");
				if(search.equals(name))
				{
					fl.disp_Message(driver, "", "", "Registered user with Reference Link Added to My Refferals Succesfully", "", "Y");
				}
				else
				{
					fl.disp_Message(driver, "", "", "No records Found with  "+name, "", "Y");
				}
			}
			else
			{
				if(size>1)
				{
					for(int i=1;i<=size;i++)
					{
						String search = fl.getTextXPATH(driver, nameValid_MR_Xpath+"["+i+"]", "", "", "", "", "Y");
						if(search.equals(name))
						{
							fl.disp_Message(driver, "", "", "Registered user with Reference Link Added to My Refferals Succesfully", "", "Y");
						}
						else
						{
							fl.disp_Message(driver, "", "", "No records Found with  "+name, "", "Y");
						}
					}
				}
			}
		}
		/*try
		{
		if(row==1)
		{
			if(fl.elementDisplayed(driver, noRec_MR_Xpath, "").equals("true"))
			{
				fl.disp_Message(driver, "", "", "No records Found with "+name, "", "");
			}
			else
			{
			if(fl.elementEnabled(driver, nameValid_MR_Xpath+"["+1+"]", "").equals("true"))
			{
				String search = fl.getTextXPATH(driver, nameValid_MR_Xpath+"["+1+"]", "", "", "", "", "");
				if(search.equals(name))
				{
					fl.disp_Message(driver, "", "", "Registered user with Reference Link Added to My Refferals Succesfully", "", "");
				}
			}
		}
		else
		{
			List<WebElement> refered =driver.findElements(By.xpath(nameValid_MR_Xpath));
			int size = refered.size();
			for(int i=1;i<=size;i++)
				{
					String search = fl.getTextXPATH(driver, nameValid_MR_Xpath+"["+i+"]", "", "", "", "", "");
					if(search.equals(name))
					{
						fl.disp_Message(driver, "", "", "Registered user with Reference Link Added to My Refferals Succesfully", "", "");
					}
				}
		}
		}
		catch(NoSuchElementException e)
		{
			
		}*/
		//========================================
		
		
		
			
		
		
	}
	public void referFriends(WebDriver driver, String emailIds) throws IOException, InterruptedException
	{
		Excel_Utils util = new Excel_Utils(Environment("refer_Excel"));
		String referalLinktxtCopy_Xpath=Environment("referalLinktxtCopy_Xpath");
		String referalLinkCopy_Xpath=Environment("referalLinkCopy_Xpath");
		String referShare_Xpath=Environment("referShare_Xpath");
		String emailId_Xpath=Environment("emailId_Xpath");
		String referButton_Xpath=Environment("referButton_Xpath");
		String doneIndividual_Xpath=Environment("doneIndividual_Xpath");
		
		String Parent=driver.getWindowHandle();
		
		fl.ClickByXpath(driver, referalLinktxtCopy_Xpath, "", "", "Copy Referal Link", "", "");
		
		copiedText_Robo Copy_Survey_Link = new copiedText_Robo();
		Thread.sleep(3000);
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		System.out.println(set.size());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);
		driver.switchTo().window(set.iterator().next());
		Thread.sleep(3000);
		String referURL= driver.getCurrentUrl();
		System.out.println(referURL);
		driver.close();
		driver.switchTo().window(Parent);
		excelRW.writeExcel(Environment("refer_Excel"), 1, referURL);
		fl.ClickByXpath(driver, referShare_Xpath, "", "", "Click on Share", "", "");
		fl.entervalueByXpath(driver, emailId_Xpath, emailIds, "", "", "emai ids to be entered", "", "");
		fl.ClickByXpath(driver, referButton_Xpath, "", "", "click on Refer", "", "");
		fl.ClickByXpath(driver, doneIndividual_Xpath, "", "", "Click on Done", "", "");
		
		
	}
	public void CompanyProfile(WebDriver driver, String legalname, String website, String companytype, String business_structure, String contactnum, String duns_no, String industry, String subIndustry, String founded_year,
			String company_size, String parentcompany, String fbUrl, String twitterUrl, String linkedinUrl, String googleUrl, String currencyType,
			String yearly_curType, String year_cur_no, String year_curIn, String timezone, String street1Reg, String street2Reg,
			String cityReg, String countryReg, String stateReg, String zipReg, String Mail_AsReg, String street1Mail, String street2Mail,
			String cityMail, String countryMail, String stateMail, String zipMail, String president, String campaignManager,
			String accountManager, String routingNo, String bankName, String phNo, String accountNo, String accountType, String street1Bank,
			String street2Bank, String cityBank, String countryBank, String stateBank, String zipBank, String doctype, String docNotes,
			String docName, String filepath, String description, String image1path, String image2path, String image3path,
			String overview, String background) throws IOException, InterruptedException
	{
		String Company_LogoXPATH=Environment("Company_LogoXPATH");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String companyInformation_CP_Xpath=Environment("companyInformation_CP_Xpath");
		String editcompanyInformation_CP_Xpath=Environment("editcompanyInformation_CP_Xpath");
		String legalName_CP_Xpath=Environment("legalName_CP_Xpath");
		String website_CP_Xpath=Environment("website_CP_Xpath");
		String companyType_Select_CP_Xpath=Environment("companyType_Select_CP_Xpath");
		String companyType_SelectOptions_CP_Xpath=Environment("companyType_SelectOptions_CP_Xpath");
		String businessStructure_Select_CP_Xpath=Environment("businessStructure_Select_CP_Xpath");
		String businessStructure_SelectOptions_CP_Xpath=Environment("businessStructure_SelectOptions_CP_Xpath");
		String contactNo_CP_Xpath=Environment("contactNo_CP_Xpath");
		String dunsNo_CP_Xpath=Environment("dunsNo_CP_Xpath");
		String industry_Select_CP_Xpath=Environment("industry_Select_CP_Xpath");
		String industry_SelectOptions_CP_Xpath=Environment("industry_SelectOptions_CP_Xpath");
		String subIndustryClick_CP_Xpath=Environment("subIndustryClick_CP_Xpath");
		String industryList__CP_Xpath=Environment("industryList__CP_Xpath");
		String subIndustryLabelText_CP_Xpath=Environment("subIndustryLabelText_CP_Xpath");
		String subIndustry_Checkbox_CP_Xpath=Environment("subIndustry_Checkbox_CP_Xpath");
		String active_subIndustry_Xpath=Environment("active_subIndustry_Xpath");
		String deactivate_subIndustry_Xpath=Environment("deactivate_subIndustry_Xpath");
		String foundYear_CP_Xpath=Environment("foundYear_CP_Xpath");
		String companySize_CP_Xpath=Environment("companySize_CP_Xpath");
		String parentCompany_CP_XPath=Environment("parentCompany_CP_XPath");
		String facebookurl_CP_Xpath=Environment("facebookurl_CP_Xpath");
		String twitterURL_CP_Xpath=Environment("twitterURL_CP_Xpath");
		String linkedinURL_CP_Xpath=Environment("linkedinURL_CP_Xpath");
		String googleURL_CP_Xpath=Environment("googleURL_CP_Xpath");
		String currency_Select_CP_Xpath=Environment("currency_Select_CP_Xpath");
		String currency_SelectOptions_CP_Xpath=Environment("currency_SelectOptions_CP_Xpath");
		String yearlyCurrencyType_CP_Xpath=Environment("yearlyCurrencyType_CP_Xpath");
		String yearlynumber_CP_Xpath=Environment("yearlynumber_CP_Xpath");
		String yearlynumberIn_CP_Xpath=Environment("yearlynumberIn_CP_Xpath");
		String timezone_CP_Xpath=Environment("timezone_CP_Xpath");
		String street1_Reg_CP_Xpath=Environment("street1_Reg_CP_Xpath");
		String street2_Reg_CP_Xpath=Environment("street2_Reg_CP_Xpath");
		String city_Reg_CP_Xpath=Environment("city_Reg_CP_Xpath");
		String country_Reg_CP_Xpath=Environment("country_Reg_CP_Xpath");
		String state_Reg_CP_Xpath=Environment("state_Reg_CP_Xpath");
		String zipcode_Reg_CP_Xpath=Environment("zipcode_Reg_CP_Xpath");
		String sameAsRegistered_CP_Xpath=Environment("sameAsRegistered_CP_Xpath");
		String street1_Mail_CP_Xpath=Environment("street1_Mail_CP_Xpath");
		String street2_Mail_CP_Xpath=Environment("street2_Mail_CP_Xpath");
		String city_Mail_CP_Xpath=Environment("city_Mail_CP_Xpath");
		String country_Mail_CP_Xpath=Environment("country_Mail_CP_Xpath");
		String state_Mail_CP_Xpath=Environment("state_Mail_CP_Xpath");
		String zipcode_Mail_CP_Xpath=Environment("zipcode_Mail_CP_Xpath");
		String save_companyInfo_CP_Xpath=Environment("save_companyInfo_CP_Xpath");
		String cancel_companyInfo_CP_Xpath=Environment("cancel_companyInfo_CP_Xpath");
		String AlertMessage_Xpath=Environment("AlertMessage_Xpath");
		String failed_Fields_Xpath=Environment("failed_Fields_Xpath");
		String contacts_CP_Xpath=Environment("contacts_CP_Xpath");
		String editcontacts_CP_Xpath=Environment("editcontacts_CP_Xpath");
		String president_CP_Xpath=Environment("president_CP_Xpath");
		String campaignManager_CP_Xpath=Environment("campaignManager_CP_Xpath");
		String accountManager_CP_Xpath=Environment("accountManager_CP_Xpath");
		String saveContacts_CP_Xpath=Environment("saveContacts_CP_Xpath");
		String cancelContacts_CP_Xpath=Environment("cancelContacts_CP_Xpath");
		String bankAccounts_CP_Xpath=Environment("bankAccounts_CP_Xpath");
		String addBankAccount_CP_Xpath=Environment("addBankAccount_CP_Xpath");
		String routingnumber_Bank_CP_Xpath=Environment("routingnumber_Bank_CP_Xpath");
		String bankName_Bank_CP_Xpath=Environment("bankName_Bank_CP_Xpath");
		String phone_Bank_CP_Xpath=Environment("phone_Bank_CP_Xpath");
		String accountNo_Bank_CP_Xpath=Environment("accountNo_Bank_CP_Xpath");
		String accountType_BAnk_CP_Xpath=Environment("accountType_BAnk_CP_Xpath");
		String street1_Bank_CP_Xpath=Environment("street1_Bank_CP_Xpath");
		String street2_Bank_CP_Xpath=Environment("street2_Bank_CP_Xpath");
		String city_Bank_CP_Xpath=Environment("city_Bank_CP_Xpath");
		String country_Bank_CP_Xpath=Environment("country_Bank_CP_Xpath");
		String state_Bank_CP_Xpath=Environment("state_Bank_CP_Xpath");
		String zipcode_Bank_CP_Xpath=Environment("zipcode_Bank_CP_Xpath");
		String saveBank_CP_Xpath=Environment("saveBank_CP_Xpath");
		String cancelBank_CP_Xpath=Environment("cancelBank_CP_Xpath");
		String documents_CP_Xpath=Environment("documents_CP_Xpath");
		String addDocuments_CP_Xpath=Environment("addDocuments_CP_Xpath");
		String documentType_Select_CP_Xpath=Environment("documentType_Select_CP_Xpath");
		String documentType_SelectOptions_CP_Xpath=Environment("documentType_SelectOptions_CP_Xpath");
		String documentType_Name_CP_Xpath=Environment("documentType_Name_CP_Xpath");
		String documentType_Notes_CP_Xpath=Environment("documentType_Notes_CP_Xpath");
		String saveDocType_CP_Xpath=Environment("saveDocType_CP_Xpath");
		String cancelDocType_CP_Xpath=Environment("cancelDocType_CP_Xpath");
		String documentName_CP_Xpath=Environment("documentName_CP_Xpath");
		String filepath_CP_Xpath=Environment("filepath_CP_Xpath");
		String description_CP_Xpath=Environment("description_CP_Xpath");
		String saveDocuments_CP_Xpath=Environment("saveDocuments_CP_Xpath");
		String cancelDocuments_CP_Xpath=Environment("cancelDocuments_CP_Xpath");
		String failed_fields_Doc_Xpath=Environment("failed_fields_Doc_Xpath");
		String companyPublicView_CP_Xpath=Environment("companyPublicView_CP_Xpath");
		String overview_CPNoFill_Xpath=Environment("overview_CPNoFill_Xpath");
		String galleryImages_CPValid_Xpath=Environment("galleryImages_CPValid_Xpath");
		String attribute_CPValid_Xpath=Environment("attribute_CPValid_Xpath");
		String editCompanyPublicView_CP_Xpath=Environment("editCompanyPublicView_CP_Xpath");
		String Image1del_CP_Xpath=Environment("Image1del_CP_Xpath");
		String Image2del_CP_Xpath=Environment("Image2del_CP_Xpath");
		String Image3del_CP_Xpath=Environment("Image3del_CP_Xpath");
		String Image1_CP_Xpath=Environment("Image1_CP_Xpath");
		String Image2_CP_Xpath=Environment("Image2_CP_Xpath");
		String Image3_CP_Xpath=Environment("Image3_CP_Xpath");
		String overview_CP_Xpath=Environment("overview_CP_Xpath");
		String background_CP_Xpath=Environment("background_CP_Xpath");
		String save_CP_Xpath=Environment("save_CP_Xpath");
		String cancel_CP_Xpath=Environment("cancel_CP_Xpath");
		String image1_Failed_Xpath=Environment("image1_Failed_Xpath");
		String overview_failed_Xpath=Environment("overview_failed_Xpath");
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		UploadFile_Robot upload = new UploadFile_Robot();
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		/*try
		{*/
			fl.JS_Element_Find(driver, Company_LogoXPATH);
			fl.ClickByXpath(driver, Company_LogoXPATH, "", "Filling company Profile", "click on company logo", "", "");
			fl.ClickByXpath(driver, companyProfile_Xpath, "", "", "click on company profile", "", "");
			
			fl.ClickByXpath(driver, companyInformation_CP_Xpath, "", "", "Click on company Information tab", "", "");
			fl.ClickByXpath(driver, editcompanyInformation_CP_Xpath, "", "", "Edit Company Information", "", "");
			if(legalname!="")
			{
				fl.ClickByXpath(driver, legalName_CP_Xpath, "", "", "LegalName selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, legalName_CP_Xpath, legalname, "", "", "Legal name Modified", "", "");
			}
			if(website!="")
			{
				fl.ClickByXpath(driver, website_CP_Xpath, "", "", "Website selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, website_CP_Xpath, website, "", "", "Website Modified", "", "");	
			}
			if(companytype!="")
				fl.selectDropdownByxpath(driver, companyType_Select_CP_Xpath, companytype, "", "", "Selecting Company Type", "", "");
			if(business_structure!="")
				fl.selectDropdownByxpath(driver, businessStructure_Select_CP_Xpath, business_structure, "", "", "Selecting business Structure", "", "");
			if(contactnum!="")
			{
				fl.ClickByXpath(driver, contactNo_CP_Xpath, "", "", "ContactNumber selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, contactNo_CP_Xpath, contactnum, "", "", "ContactNumber Modified", "", "");	
			}
			if(duns_no!="")
			{
				fl.ClickByXpath(driver, dunsNo_CP_Xpath, "", "", "Duns Number selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, dunsNo_CP_Xpath, duns_no, "", "", "Entering the Duns No", "", "");
			}
			if(industry!="")
				fl.selectDropdownByxpath(driver, industry_Select_CP_Xpath, industry, "", "", "Selecting Industry Type", "", "");
			if(subIndustry!="")
			{
				String[] substring =subIndustry.split(",");
				int subS= substring.length;
				System.out.println("no of substrings:"+subS);
				fl.JS_Element_Find(driver, subIndustryClick_CP_Xpath);
				fl.ClickByXpath(driver, subIndustryClick_CP_Xpath, "", "", "Click on SubIndustry", "", "");
				List<WebElement> dropList = driver.findElements(By.xpath(industryList__CP_Xpath));
				int dropListSize = dropList.size();
				//before going to check , remove already selected options
				int active=fun_cas.listSize(driver, active_subIndustry_Xpath);
				System.out.println(active);
				if(active>=1)
				{
					for(int i=1;i<=active;i++)
					{
						/*if(i>1)
						fl.ClickByXpath(driver, subIndustryClick_CP_Xpath, "", "", "Click on SubIndustry", "", "");*/
						Thread.sleep(3000);
						fl.JS_Element_Find(driver, active_subIndustry_Xpath+"["+1+"]"+deactivate_subIndustry_Xpath);
						fl.ClickByXpath(driver, active_subIndustry_Xpath+"["+1+"]"+deactivate_subIndustry_Xpath, "", "", "", "", "");
						/*fl.ClickByXpath(driver, subIndustryClick_CP_Xpath, "", "", "Click on SubIndustry", "", "");*/
						
					}
				}
				for(int j=0;j<subS;j++)//if 3 commas in a string it splits into 4
				{
					for(int i=1;i<=dropListSize;i++)
					{
						String sub_string = substring[j].trim();
						String text = fl.getTextXPATH(driver, subIndustryLabelText_CP_Xpath+"["+i+"]", "", "", "Compare each item in  Sub Industry list with u mentioned", "", "");
						if(text.equals(sub_string))
						{
							fl.JS_Element_Find(driver, subIndustryLabelText_CP_Xpath+"["+i+"]"+subIndustry_Checkbox_CP_Xpath);
							fl.ClickByXpath(driver, subIndustryLabelText_CP_Xpath+"["+i+"]"+subIndustry_Checkbox_CP_Xpath, "", "", "SubIndustry selected :"+substring[j], "", "");
						}
					}
				}
				
			}
			if(founded_year!="")
			{
				fl.ClickByXpath(driver, foundYear_CP_Xpath, "", "", "found Year field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, foundYear_CP_Xpath, founded_year, "", "", "enter value in Founded year", "", "");
			}
			if(company_size!="")
				fl.selectDropdownByxpath(driver, companySize_CP_Xpath, company_size, "", "", "Select Company Size", "", "");
			if(parentcompany!="")
			{
				fl.ClickByXpath(driver, parentCompany_CP_XPath, "", "", "Parent company field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, parentCompany_CP_XPath, parentcompany, "", "", "Parent Company to be entered", "", "");
			}
			
			if(fbUrl!="")
			{
				fl.ClickByXpath(driver, facebookurl_CP_Xpath, "", "", "Parent company field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, facebookurl_CP_Xpath, fbUrl, "", "", "fb url to be entered", "", "");
			}
			
			if(twitterUrl!="")
			{
				fl.ClickByXpath(driver, twitterURL_CP_Xpath, "", "", "TwitterUrl field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, twitterURL_CP_Xpath, twitterUrl, "", "", "twitter url to be entered", "", "");
			}
			
			if(linkedinUrl!="")
			{
				fl.ClickByXpath(driver, linkedinURL_CP_Xpath, "", "", "LinkedInUrl field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, linkedinURL_CP_Xpath, linkedinUrl, "", "", "LinkedinUrl to be entered", "", "");
			}
			
			if(googleUrl!="")
			{
				fl.ClickByXpath(driver, googleURL_CP_Xpath, "", "", "GoogleUrl field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, googleURL_CP_Xpath, googleUrl, "", "", "Google Url to be entered", "", "");
			}
			if(currencyType!="")
				fl.selectDropdownByxpath(driver, currency_Select_CP_Xpath, currencyType, "", "", "Currency type to be selected", "", "");
			if(yearly_curType!="")
				fl.selectDropdownByxpath(driver, yearlyCurrencyType_CP_Xpath, yearly_curType, "", "", "Yearly Currency type to be selected", "", "");
			if(year_cur_no!="")
			{
				fl.ClickByXpath(driver, yearlynumber_CP_Xpath, "", "", "YearlyTurnover field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, yearlynumber_CP_Xpath, year_cur_no, "", "", "Currency Amount To be entered", "", "");
			}
			if(year_curIn!="")
				fl.selectDropdownByxpath(driver, yearlynumberIn_CP_Xpath, year_curIn, "", "Selecting thousand or lakhs or crores", "", "", "");
			if(timezone!="")
				fl.selectDropdownByxpath(driver, timezone_CP_Xpath, timezone, "", "", "timezone to be selected", "", "");
			fl.JS_Element_Find(driver, street1_Reg_CP_Xpath);
			if(street1Reg!="")
			{
				fl.ClickByXpath(driver, street1_Reg_CP_Xpath, "", "", "Street1 field selected & cleared", "", "");
				select_delete sel_del = new select_delete();
				fl.entervalueByXpath(driver, street1_Reg_CP_Xpath, street1Reg, "", "Company Registered Fields to be filled", "street1 to be entered", "", "");
			}
			else
			{
				if(street2Reg!="")
				{
					fl.ClickByXpath(driver, street2_Reg_CP_Xpath, "", "", "Street1 field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, street2_Reg_CP_Xpath, street2Reg, "", "", "Street 2 to be entered", "", "");
				}
				
				if(cityReg!="")
				{
					fl.ClickByXpath(driver, city_Reg_CP_Xpath, "", "", "City field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, city_Reg_CP_Xpath, cityReg, "", "", "city to be entered", "", "");
				}
				
				if(countryReg!="")
				{
					fl.ClickByXpath(driver, country_Reg_CP_Xpath, "", "", "Country field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, country_Reg_CP_Xpath, countryReg, "", "", "Country to be entered", "", "");
				}
				
				if(stateReg!="")
				{
					fl.ClickByXpath(driver, state_Reg_CP_Xpath, "", "", "State field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, state_Reg_CP_Xpath, stateReg, "", "", "State to be entered", "", "");
				}
				
				if(zipReg!="")
				{
					fl.ClickByXpath(driver, zipcode_Reg_CP_Xpath, "", "", "zipcode field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, zipcode_Reg_CP_Xpath, zipReg, "", "", "Zipcode to be entered", "", "");
				}
				
			}
			if(Mail_AsReg.equals(""))
			{
				fl.JS_Element_Find(driver, street1_Mail_CP_Xpath);
				if(street1Mail!="")
				{
					fl.ClickByXpath(driver, street1_Mail_CP_Xpath, "", "", "Street1 field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, street1_Mail_CP_Xpath, street1Mail, "", "Mailing Address is different", "enter street1 Mailing address", "", "");
				}
				else
				{
					if(street2Mail!="")
					{
						fl.ClickByXpath(driver, street2_Mail_CP_Xpath, "", "", "Street2 field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, street2_Mail_CP_Xpath, street2Mail, "", "", "Street 2 to be entered", "", "");
					}
					
					if(cityMail!="")
					{
						fl.ClickByXpath(driver, city_Mail_CP_Xpath, "", "", "City field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, city_Mail_CP_Xpath, cityMail, "", "", "city to be entered", "", "");
					}
					
					if(countryMail!="")
					{
						fl.ClickByXpath(driver, country_Mail_CP_Xpath, "", "", "Country field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, country_Mail_CP_Xpath, countryMail, "", "", "country to be entered", "", "");
					}
					
					if(stateMail!="")
					{
						fl.ClickByXpath(driver, state_Mail_CP_Xpath, "", "", "State field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, state_Mail_CP_Xpath, stateMail, "", "", "State to be entered", "", "");
					}
					if(zipMail!="")
					{
					fl.ClickByXpath(driver, zipcode_Mail_CP_Xpath, "", "", "Zipcode field selected & cleared", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, zipcode_Mail_CP_Xpath, zipMail, "", "", "zipcode to be entered", "", "");
					}
				}
			}
			else
			{
				//WebElement sameAsReg = driver.findElement(By.xpath(street1_Mail_CP_Xpath));
				fl.JS_Element_Find(driver, sameAsRegistered_CP_Xpath);
				fl.ClickByXpath(driver, sameAsRegistered_CP_Xpath, "", "check checkbox already selected or not", "", "", "");
				String same = fl.elementDisplayed(driver, street1_Mail_CP_Xpath, "");
				System.out.println("Same As Registered:"+same);
				if(same.equals("true"))
				{
					fl.ClickByXpath(driver, sameAsRegistered_CP_Xpath, "", "", "Already Selected the checkbox", "", "");
				}
				else
				{
					fl.disp_Message(driver, "", "", "Mailing Address Selected As Registered Address", "", "");
				}
				
			}
			fl.JS_Element_Find(driver, save_companyInfo_CP_Xpath);
			fl.ClickByXpath(driver, save_companyInfo_CP_Xpath, "", "", "Click on Save Button", "", "Y");
	
			
			/*failed_Fields_comInfo= fun_cas.listSize(driver, failed_Fields_Xpath);
//validation of Company Information
			if(failed_Fields_comInfo>=1)
			{
				fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
			}
			else
			{*/
//======================		
				alert=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
				System.out.println("alert text is :"+alert);
				fl.disp_Message(driver, "", "", "Message:"+alert, "", "");
//====================				
			/*}
			//Contacts
			if(failed_Fields_comInfo==0)
			{*/
//=======================				
				fl.JS_Element_Find(driver, contacts_CP_Xpath);
				fl.ClickByXpath(driver, contacts_CP_Xpath, "", "go to contacts tab", "", "", "Y");
				fl.ClickByXpath(driver, editcontacts_CP_Xpath, "", "editing contacts", "", "", "");
				if(president!="")
					fl.selectDropdownByxpath(driver, president_CP_Xpath, president, "", "", "president to be selected", "", "Y");
				if(campaignManager!="")
					fl.selectDropdownByxpath(driver, campaignManager_CP_Xpath, campaignManager, "", "", "Campaign Manager to be selected", "", "Y");
				if(accountManager!="")
					fl.selectDropdownByxpath(driver, accountManager_CP_Xpath, accountManager, "", "", "Account Manager to be selected", "", "Y");
				fl.ClickByXpath(driver, saveContacts_CP_Xpath, "", "Save Contacts", "", "", "Y");
//=========================				
				/*failed_Fields_contacts= fun_cas.listSize(driver, failed_Fields_Xpath);
				if(failed_Fields_contacts>=1)
				{
					fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
				}
				else
				{*/
//========================				
					alert_contacts=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
					fl.disp_Message(driver, "", "", "Message:"+alert_contacts, "", "");
//====================					
				/*}
				//Bank Accounts
				if(failed_Fields_contacts==0)
				{*/
					fl.JS_Element_Find(driver, bankAccounts_CP_Xpath);
					fl.ClickByXpath(driver, bankAccounts_CP_Xpath, "", "go to Bank Accounts Tab", "", "", "Y");
					fl.ClickByXpath(driver, addBankAccount_CP_Xpath, "", "Adding a bank account", "click on Add ", "", "Y");
					if(routingNo!="")
					{
						fl.ClickByXpath(driver, routingnumber_Bank_CP_Xpath, "", "", "RoutingNo field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, routingnumber_Bank_CP_Xpath, routingNo, "", "", "Routing Number to be entered", "", "");
					}
					if(bankName!="")
					{
						fl.ClickByXpath(driver, bankName_Bank_CP_Xpath, "", "", "BankName field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, bankName_Bank_CP_Xpath, bankName, "", "", "bank Name to be entered", "", "");
					}
					if(phNo!="")
					{
						fl.ClickByXpath(driver, phone_Bank_CP_Xpath, "", "", "PhnNo field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, phone_Bank_CP_Xpath, phNo, "", "", "Phone number to be entered", "", "");
					}
					if(accountNo!="")
					{
						fl.ClickByXpath(driver, accountNo_Bank_CP_Xpath, "", "", "Account Number field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, accountNo_Bank_CP_Xpath, accountNo, "", "", "account number to be enetered", "", "");
					}
					if(accountType!="")
						fl.selectDropdownByxpath(driver, accountType_BAnk_CP_Xpath, accountType, "", "", "Account type to be selected", "", "");
					if(street1Bank!="")
					{
						fl.ClickByXpath(driver, street1_Bank_CP_Xpath, "", "", "BankGeo field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, street1_Bank_CP_Xpath, street1Bank, "", "Bank Geo Address to be filled", "Street1 to be entered", "", "");
					}
					if(street2Bank!="")
					{
						fl.ClickByXpath(driver, street2_Bank_CP_Xpath, "", "", "BankStreet field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, street2_Bank_CP_Xpath, street2Bank, "", "", "Street2 to be entered", "", "");
					}
					if(cityBank!="")
					{
						fl.ClickByXpath(driver, city_Bank_CP_Xpath, "", "", "BankCity field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, city_Bank_CP_Xpath, cityBank, "", "", "city to be entered", "", "");
					}
					if(countryBank!="")
					{
						fl.ClickByXpath(driver, country_Bank_CP_Xpath, "", "", "BankCountry field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, country_Bank_CP_Xpath, countryBank, "", "", "Country to be entered", "", "");
					}
					if(stateBank!="")
					{
						fl.ClickByXpath(driver, state_Bank_CP_Xpath, "", "", "BankCountry field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, state_Bank_CP_Xpath, stateBank, "", "", "State to be entered", "", "");
					}
					if(zipBank!="")
					{
						fl.ClickByXpath(driver, zipcode_Bank_CP_Xpath, "", "", "BankCountry field selected & cleared", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, zipcode_Bank_CP_Xpath, zipBank, "", "", "zipcode to be entered", "", "");
					}
					jse.executeScript("window.scrollBy(0,-250)", "");
					//fl.JS_Element_Find(driver, saveBank_CP_Xpath);
					fl.ClickByXpath(driver, saveBank_CP_Xpath, "", "saving bank account details", "", "", "Y");
					Thread.sleep(3000);
					
					/*failed_Fields_bank= fun_cas.listSize(driver, failed_Fields_Xpath);
					if(failed_Fields_bank>=1)
					{
						fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
					}
					else
					{*/
						alert_bank=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
						fl.disp_Message(driver, "", "", "Message:"+alert_bank, "", "");
					/*}
					//Documents
					//fl.JS_Element_Find(driver, documents_CP_Xpath);
					if(failed_Fields_bank==0)
					{*/
//============================						
						Excel_Utils RC = new Excel_Utils(Environment("Excel"));
						fl.ClickByXpath(driver, documents_CP_Xpath, "", "Go to Documents tab", "", "", "");
						fl.ClickByXpath(driver, addDocuments_CP_Xpath, "", "Add Documents", "Click on Add", "", "");
						String Company_Documents=Environment("Sheet_Company_Documents"); 
						int Company_Documents_col=RC.getLastcolmno(Company_Documents); 
						String[] Company_Documents_ele=new String[Company_Documents_col]; 
						for (int Company_Documents_index = 1; Company_Documents_index < RC.getLastrowno(Company_Documents); Company_Documents_index++) 
						  {
							if (doctype.equals(RC.getStringCellData(Company_Documents_index, RC.Current_Coulumn_Number(Company_Documents, "DocID"),Company_Documents)))
								  // Adduser contains company email_id at 1st column  for validation
							  {
								document_rows++;
								for(int Company_Documents_ind=0;Company_Documents_ind<Company_Documents_col;Company_Documents_ind++) 
								{
									Company_Documents_ele[Company_Documents_ind]=RC.getStringCellData(Company_Documents_index, Company_Documents_ind, Company_Documents);
								  System.out.println(Company_Documents_ele[Company_Documents_ind]); //call login as company method, pass array values
								}
								
								if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")]!="")
								{
									String doc_Type=fl.checkOptionValueInSelect(driver, documentType_Select_CP_Xpath, documentType_SelectOptions_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")]);
									if(doc_Type.equals("true"))
									{
										fl.selectDropdownByxpath(driver, documentType_Select_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")], "", "", "Document type to be selected", "", "");
									}
									else
									{
										fl.selectDropdownByxpath(driver, documentType_Select_CP_Xpath, "+ Add New", "", "create New Document Type", "Click on Addnew", "", "");
										fl.entervalueByXpath(driver, documentType_Name_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")], "", "", "document type value to be entered", "", "");
										fl.entervalueByXpath(driver, documentType_Notes_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentNotes")], "", "", "document Notes to be entered", "", "");
										fl.ClickByXpath(driver, saveDocType_CP_Xpath, "", "", "Saving the created document type", "", "Y");
										fl.selectDropdownByxpath(driver, documentType_Select_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")], "", "", "created document type to be selected", "", "");
									}
								}
								if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_Name")]!="")
								{
									fl.ClickByXpath(driver, documentName_CP_Xpath, "", "", "Document Name field selected & cleared", "", "");
									select_delete sel_del = new select_delete();
									fl.entervalueByXpath(driver, documentName_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_Name")], "", "", "Document Name to be entered", "", "");
								}
								if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_FilePath")]!="")
								{
									fl.ClickByXpath(driver, filepath_CP_Xpath, "", "", "flle path to be chosen", "", "");
									upload.uploadFile(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_FilePath")]);
								}
								if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentDescription")]!="")
								{
									fl.ClickByXpath(driver, description_CP_Xpath, "", "", "Document Name field selected & cleared", "", "");
									select_delete sel_del = new select_delete();
									fl.entervalueByXpath(driver, description_CP_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentDescription")], "", "", "Description to be enetered", "", "");
								}
								fl.ClickByXpath(driver, saveDocuments_CP_Xpath, "", "", "save the Added Documents", "", "Y");
								alert_doc=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
								fl.disp_Message(driver, "", "", "Message:"+alert_doc, "", "");
							  }
						  }
//=============================						
						/*failed_Fields_doc= fun_cas.listSize(driver, failed_fields_Doc_Xpath);
						if(failed_Fields_doc>=1)
						{
							fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
						}
						else
						{
							alert_doc=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
							fl.disp_Message(driver, "", "", "Message:"+alert_doc, "", "");
						}
						//CompanyPublic View
						//fl.JS_Element_Find(driver, companyPublicView_CP_Xpath);
						if(failed_Fields_doc==0)
						{*/
//=============================						
							fl.ClickByXpath(driver, companyPublicView_CP_Xpath, "", "go to Company public view", "", "", "");
							String imag1_status=fun_cas.returnAttributeValue(driver, galleryImages_CPValid_Xpath+"[1]", attribute_CPValid_Xpath);
							String imag2_status=fun_cas.returnAttributeValue(driver, galleryImages_CPValid_Xpath+"[2]", attribute_CPValid_Xpath);
							String imag3_status=fun_cas.returnAttributeValue(driver, galleryImages_CPValid_Xpath+"[3]", attribute_CPValid_Xpath);
							fl.ClickByXpath(driver, editCompanyPublicView_CP_Xpath, "", "", "edit company public view", "", "");
							if(image1path!="")
							{
								if(imag1_status.equals("false"))
								{
									fl.ClickByXpath(driver, Image1del_CP_Xpath, "", "", "", "", "");
									fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image1path);
								}
								else
								{
									fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image1path);
								}
							}
							if(image2path!="")
							{
								if(imag2_status.equals("false"))
								{
									fl.ClickByXpath(driver, Image2del_CP_Xpath, "", "", "", "", "");
									fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									upload.uploadFile(image2path);
								}
								else
								{
									fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									upload.uploadFile(image2path);
								}
							}
							if(image3path!="")
							{
								if(imag3_status.equals("false"))
								{
									fl.ClickByXpath(driver, Image3del_CP_Xpath, "", "", "", "", "");
									fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									upload.uploadFile(image3path);
								}
								else
								{
									fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image3 to be uploaded", "", "");
									upload.uploadFile(image3path);
								}
							}
							if(overview!="")
							{
								fl.ClickByXpath(driver, overview_CP_Xpath, "", "", "Overview field selected & cleared", "", "");
								select_delete sel_del = new select_delete();
								fl.entervalueByXpath(driver, overview_CP_Xpath, overview, "", "", "overview to be entered", "", "");
							}
							if(background!="")
							{
								fl.JS_Element_Find(driver, background_CP_Xpath);
								fl.ClickByXpath(driver, background_CP_Xpath, "", "", "Overview field selected & cleared", "", "");
								select_delete sel_del = new select_delete();
								fl.entervalueByXpath(driver, background_CP_Xpath, background, "", "", "background text to be entered", "", "");
							}
							fl.JS_Element_Find(driver, save_CP_Xpath);
							fl.ClickByXpath(driver, save_CP_Xpath, "", "", "saving the Company public view", "", "Y");
//=================================							
							/*failed_Fields_pubView1= fun_cas.listSize(driver, image1_Failed_Xpath);
							failed_Fields_pubView2= fun_cas.listSize(driver, overview_failed_Xpath);
							failed_Fields_pubView=failed_Fields_pubView1+failed_Fields_pubView2;
							if(failed_Fields_pubView>=1)
							{
								fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
							}
							else
							{*/
//=================================						
								alert_publicView=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
								fl.disp_Message(driver, "", "", "Message:"+alert_publicView, "", "");
//=================================						
							/*}*/
						/*}
					}
				}
			}*/
	}
	public void companyInfoValidation(WebDriver driver, String legalname, String website, String companytype, String business_structure
		, String fein, String email, String contactnum, String dunsnum, String businessStruct, String industry, String sub_industry,
		String found_Year, String comp_siz, String parent_Comp, String fb, String twitter, String linkedIn, String google, String currency,
		String turnOverNo, String turnOverIn, String street2, String city, String state, String country, String zipcode, String mailingAddress,
		String mail_street2, String mail_city, String mail_state, String mail_country, String mail_zipcode, String timezone, String president, String campaignManager,
		String accountManager, String bankname, String routingNo, String accNo, String accType, String phnNo, String bankStreet, String bankCity,
		String bankCountry, String bankState, String bankZipcode, String docType, String docName, String docDesc, String docStatus,
		String image1, String image2, String image3, String overview, String background) throws IOException, InterruptedException
	{
		String companyInformation_CP_Xpath=Environment("companyInformation_CP_Xpath");
		String legalName_CPValid_Xpath=Environment("legalName_CPValid_Xpath");
		String website_CPValid_Xpath=Environment("website_CPValid_Xpath");
		String companyType_CPValid_Xpath=Environment("companyType_CPValid_Xpath");
		String fein_CPValid_Xpath=Environment("fein_CPValid_Xpath");
		String email_CPValid_Xpath=Environment("email_CPValid_Xpath");
		String contactNo_CPValid_Xpath=Environment("contactNo_CPValid_Xpath");
		String dunsNo_CPValid_Xpath=Environment("dunsNo_CPValid_Xpath");
		String businessStruc_CPValid_Xpath=Environment("businessStruc_CPValid_Xpath");
		String industry_CPValid_Xpath=Environment("industry_CPValid_Xpath");
		String subindustry_CPValid_Xpath=Environment("subindustry_CPValid_Xpath");
		String foundYear_CPValid_Xpath=Environment("foundYear_CPValid_Xpath");
		String companySize_CPValid_Xpath=Environment("companySize_CPValid_Xpath");
		String parent_CPValid_Xpath=Environment("parent_CPValid_Xpath");
		String fbURL_CPValid_Xpath=Environment("fbURL_CPValid_Xpath");
		String twitter_CPValid_Xpath=Environment("twitter_CPValid_Xpath");
		String linkedIn_CPValid_Xpath=Environment("linkedIn_CPValid_Xpath");
		String googleURL_CPValid_Xpath=Environment("googleURL_CPValid_Xpath");
		String currency_CPValid_Xpath=Environment("currency_CPValid_Xpath");
		String yearlyTurnover_CPValid_Xpath=Environment("yearlyTurnover_CPValid_Xpath");
		String registerd_CPValid_Xpath=Environment("registerd_CPValid_Xpath");
		String mailing_CPValid_Xpath=Environment("mailing_CPValid_Xpath");
		String timezone_CPValid_Xpath=Environment("timezone_CPValid_Xpath");
		
		String contacts_CP_Xpath=Environment("contacts_CP_Xpath");
		String president_CPValid_Xpath=Environment("president_CPValid_Xpath");
		String campaignManager_CPValid_Xpath=Environment("campaignManager_CPValid_Xpath");
		String accountManager_CPValid_Xpath=Environment("accountManager_CPValid_Xpath");
		
		String bankAccounts_CP_Xpath=Environment("bankAccounts_CP_Xpath");
		String bankDetails_CPValid_Xpath=Environment("bankDetails_CPValid_Xpath");
		String allbankNames_Xpath=Environment("allbankNames_Xpath");
		String bankName_CPValid_Xpath=Environment("bankName_CPValid_Xpath");
		String routingNo_CPValid_Xpath=Environment("routingNo_CPValid_Xpath");
		String accNo_CPValid_Xpath=Environment("accNo_CPValid_Xpath");
		String accType_CPValid_Xpath=Environment("accType_CPValid_Xpath");
		String phnNo_CPValid_Xpath=Environment("phnNo_CPValid_Xpath");
		String noBankAdrs_CPValid_Xpath=Environment("noBankAdrs_CPValid_Xpath");
		String bankAddress_CPValid_Xpath=Environment("bankAddress_CPValid_Xpath");
		String removeBank_CPValid_Xpath=Environment("removeBank_CPValid_Xpath");
		String editBank_CPValid_Xpath=Environment("editBank_CPValid_Xpath");
		
		String documents_CP_Xpath=Environment("documents_CP_Xpath");
		String documentRows_CPValid_Xpath=Environment("documentRows_CPValid_Xpath");
		String docInternalRows_Xpath=Environment("docInternalRows_Xpath");
		String docType_CPValid_Xpath=Environment("docType_CPValid_Xpath");
		String docName_CPValid_Xpath=Environment("docName_CPValid_Xpath");
		String docDesc_CPValid_Xpath=Environment("docDesc_CPValid_Xpath");
		String status_CPValid_Xpath=Environment("status_CPValid_Xpath");
		String editDoc_CPValid_Xpath=Environment("editDoc_CPValid_Xpath");
		String removeDoc_CPValid_Xpath=Environment("removeDoc_CPValid_Xpath");
		
		String companyPublicView_CP_Xpath=Environment("companyPublicView_CP_Xpath");
		String galleryImages_CPValid_Xpath=Environment("galleryImages_CPValid_Xpath");
		String attribute_CPValid_Xpath=Environment("attribute_CPValid_Xpath");
		String overview_CPValid_Xpath=Environment("overview_CPValid_Xpath");
		String background_CPValid_Xpath=Environment("background_CPValid_Xpath");
		
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		/*if(failed_Fields_comInfo==0)
		{*/
//=================		
			fl.ClickByXpath(driver, companyInformation_CP_Xpath, "", "", "Click on company Information tab", "", "");
			if(legalname!="")
				fun_cas.getTextValidation(driver, legalName_CPValid_Xpath, legalname);
			if(website!="")
				fun_cas.getTextValidation(driver, website_CPValid_Xpath, website);
			if(companytype!="")
				fun_cas.getTextValidation(driver, companyType_CPValid_Xpath, companytype);
			if(fein!="")
				fun_cas.getTextValidation(driver, fein_CPValid_Xpath, fein);
			if(email!="")
				fun_cas.getTextValidation(driver, email_CPValid_Xpath, email);
			if(contactnum!="")
			{
				String con = fl.getTextXPATH(driver, contactNo_CPValid_Xpath, "", "", "Validating :"+contactnum, "", "");
				String contact=con.replaceAll("[-()]", "");
				if(contactnum.contains(contact))
				{
					fl.disp_Message(driver, "", "", "field validation succesful", "", "");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
				}
			}
			if(dunsnum!="")
				fun_cas.getTextValidation(driver, dunsNo_CPValid_Xpath, dunsnum);
			if(businessStruct!="")
				fun_cas.getTextValidation(driver, businessStruc_CPValid_Xpath, businessStruct);
			if(industry!="")
				fun_cas.getTextValidation(driver, industry_CPValid_Xpath, industry);
			if(sub_industry!="")
				fun_cas.getTextValidation(driver, subindustry_CPValid_Xpath, sub_industry);
			if(found_Year!="")
				fun_cas.getTextValidation(driver, foundYear_CPValid_Xpath, found_Year);
			if(comp_siz!="")
				fun_cas.getTextValidation(driver, companySize_CPValid_Xpath, comp_siz);
			if(parent_Comp!="")
				fun_cas.getTextValidation(driver, parent_CPValid_Xpath, parent_Comp);
			if(fb!="")
				fun_cas.getTextValidation(driver, fbURL_CPValid_Xpath, fb);
			fl.JS_Element_Find(driver, twitter_CPValid_Xpath);
			if(twitter!="")
				fun_cas.getTextValidation(driver, twitter_CPValid_Xpath, twitter);
			if(linkedIn!="")
				fun_cas.getTextValidation(driver, linkedIn_CPValid_Xpath, linkedIn);
			if(google!="")
				fun_cas.getTextValidation(driver, googleURL_CPValid_Xpath, google);
			if(currency!="")
				fun_cas.getTextValidation(driver, currency_CPValid_Xpath, currency);
			//turnOver=currencyNo+currencyIn
			if(turnOverNo!="")
				fun_cas.getTextValidation(driver, yearlyTurnover_CPValid_Xpath, turnOverNo);
			if(turnOverIn!="")
				fun_cas.getTextValidation(driver, yearlyTurnover_CPValid_Xpath, turnOverIn);
			if(street2!="")
				fun_cas.getTextValidation(driver, registerd_CPValid_Xpath+"["+1+"]", street2);
			if(city!="")
				fun_cas.getTextValidation(driver, registerd_CPValid_Xpath+"["+2+"]", city);
			if(state!="")
				fun_cas.getTextValidation(driver, registerd_CPValid_Xpath+"["+3+"]", state);
			if(country!="")
				fun_cas.getTextValidation(driver, registerd_CPValid_Xpath+"["+4+"]", country);
			if(zipcode!="")
				fun_cas.getTextValidation(driver, registerd_CPValid_Xpath+"["+4+"]", zipcode);
			if(mailingAddress!="")
			{
				if(street2!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+1+"]", street2);
				if(city!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+2+"]", city);
				if(state!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+3+"]", state);
				if(country!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+4+"]", country);
				if(zipcode!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+4+"]", zipcode);
			
			}
			else
			{
				if(mail_street2!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+1+"]", mail_street2);
				if(mail_city!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+2+"]", mail_city);
				if(mail_state!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+3+"]", mail_state);
				if(mail_country!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+4+"]", mail_country);
				if(mail_zipcode!="")
					fun_cas.getTextValidation(driver, mailing_CPValid_Xpath+"["+4+"]", mail_zipcode);
			}
			if(timezone!="")
				fun_cas.getTextValidation(driver, timezone_CPValid_Xpath, timezone);
			//CONTACTS
			if(failed_Fields_contacts==0)
			{
				fl.JS_Element_Find(driver, contacts_CP_Xpath);
				fl.ClickByXpath(driver, contacts_CP_Xpath, "", "go to contacts tab", "", "", "Y");
				if(president!="")
					fun_cas.getTextValidation(driver, president_CPValid_Xpath, president);
				if(campaignManager!="")
					fun_cas.getTextValidation(driver, campaignManager_CPValid_Xpath, campaignManager);
				if(accountManager!="")
					fun_cas.getTextValidation(driver, accountManager_CPValid_Xpath, accountManager);
//=========================		
				//BANK ACCOUNTS
				/*if(failed_Fields_bank==0)
				{*/
					fl.ClickByXpath(driver, bankAccounts_CP_Xpath, "", "go to Bank Accounts Tab", "", "", "Y");
					int number=fun_cas.listSize(driver, bankDetails_CPValid_Xpath);
					System.out.println(number);
					for(int i=1;i<=number;i++)
					{
						
						fl.JS_Element_Find(driver, bankDetails_CPValid_Xpath+"["+i+"]"+allbankNames_Xpath);
						String bankName_text=fl.getTextXPATH(driver, bankDetails_CPValid_Xpath+"["+i+"]"+allbankNames_Xpath, "", "", "", "", "");
						System.out.println(bankName_text);
						System.out.println(bankname);
						if(bankName_text.contains(bankname))
						{
							Thread.sleep(3000);
							if(bankname!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankName_CPValid_Xpath, bankname);
							if(routingNo!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+routingNo_CPValid_Xpath, routingNo);
							if(accNo!="")
							{
								String acc = fl.getTextXPATH(driver, bankDetails_CPValid_Xpath+"["+i+"]"+accNo_CPValid_Xpath, "", "", "Validating :"+accNo, "", "");
								String acc_no=acc.substring(accNo.length()-4);
								System.out.println("Account number last 4 digits:"+acc_no);
								if(accNo.contains(acc_no))
								{
									fl.disp_Message(driver, "", "", "field validation succesful", "", "");
								}
								else
								{
									fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
								}
							}
							if(accType!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+accType_CPValid_Xpath, accType);
							if(phnNo!="")
							{
								String con = fl.getTextXPATH(driver, bankDetails_CPValid_Xpath+"["+i+"]"+phnNo_CPValid_Xpath, "", "", "Validating :"+phnNo, "", "");
								String contact=con.replaceAll("[-()]", "");
								if(phnNo.contains(contact))
								{
									fl.disp_Message(driver, "", "", "field validation succesful", "", "");
								}
								else
								{
									fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
								}
							}
							if(bankStreet!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankAddress_CPValid_Xpath+"[1]", bankStreet);
							if(bankCity!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankAddress_CPValid_Xpath+"[2]", bankCity);
							if(bankState!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankAddress_CPValid_Xpath+"[3]", bankState);
							if(bankCountry!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankAddress_CPValid_Xpath+"[4]", bankCountry);
							if(bankZipcode!="")
								fun_cas.getTextValidation(driver, bankDetails_CPValid_Xpath+"["+i+"]"+bankAddress_CPValid_Xpath+"[4]", bankZipcode);
							break;
						}
						else
						{
							if(i>number)
							{
								fl.disp_MessageFailed(driver, "", "", "No bank is Existing with this details", "", "Y");
							}
						}
					}
					
					
					//DOCUMENTS
					/*if(failed_Fields_doc==0)
					{*/
//============================================						
						fl.JS_Element_Find(driver, documents_CP_Xpath);
						fl.ClickByXpath(driver, documents_CP_Xpath, "", "Go to Documents tab", "", "", "");
						int docRows =fun_cas.listSize(driver, documentRows_CPValid_Xpath);
						int addedDoc=document_rows;
						Excel_Utils RC = new Excel_Utils(Environment("Excel"));
						String Company_Documents=Environment("Sheet_Company_Documents"); 
						int Company_Documents_col=RC.getLastcolmno(Company_Documents); 
						String[] Company_Documents_ele=new String[Company_Documents_col]; 
						for (int Company_Documents_index = 1; Company_Documents_index < RC.getLastrowno(Company_Documents); Company_Documents_index++) 
						  {
							if (docType.equals(RC.getStringCellData(Company_Documents_index, RC.Current_Coulumn_Number(Company_Documents, "DocID"),Company_Documents)))
								  // Adduser contains company email_id at 1st column  for validation
							  {
								//document_rows++;
								for(int Company_Documents_ind=0;Company_Documents_ind<Company_Documents_col;Company_Documents_ind++) 
								{
									Company_Documents_ele[Company_Documents_ind]=RC.getStringCellData(Company_Documents_index, Company_Documents_ind, Company_Documents);
									System.out.println(Company_Documents_ele[Company_Documents_ind]); //call login as company method, pass array values
								}
								for(int i=1;i<=docRows;i++)
								{
									fl.JS_Element_Find(driver, documentRows_CPValid_Xpath+"["+i+"]"+docType_CPValid_Xpath);
									String doc_Type=fl.getTextXPATH(driver, documentRows_CPValid_Xpath+"["+i+"]"+docType_CPValid_Xpath, "", "", "get the doc type ", "", "");
									if(doc_Type.equals(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")]))
									{
										//int internalRows=fun_cas.listSize(driver, documentRows_CPValid_Xpath+"["+i+"]"+docInternalRows_Xpath);
										if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")]!="")
											fun_cas.getTextValidation(driver, documentRows_CPValid_Xpath+"["+i+"]"+docType_CPValid_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentType")]);
										if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_Name")]!="")
											fun_cas.getTextValidation(driver, documentRows_CPValid_Xpath+"["+i+"]"+docName_CPValid_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "Document_Name")]);
										if(Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentDescription")]!="")
											fun_cas.getTextValidation(driver, documentRows_CPValid_Xpath+"["+i+"]"+docDesc_CPValid_Xpath, Company_Documents_ele[RC.Current_Coulumn_Number(Company_Documents, "DocumentDescription")]);
										if(docStatus!="")
											fun_cas.getTextValidation(driver, documentRows_CPValid_Xpath+"["+i+"]"+status_CPValid_Xpath, docStatus);
									}
								}
							  }
						  }
//==================================						
						
						//COMPANY PUBLIC VIEW
						/*if(failed_Fields_pubView==0)
						{*/
//===========================					
							fl.JS_Element_Find(driver, companyPublicView_CP_Xpath);
							fl.ClickByXpath(driver, companyPublicView_CP_Xpath, "", "go to Company public view", "", "", "");
							if(image1!="")
								fun_cas.attributeValue(driver, galleryImages_CPValid_Xpath+"[1]", attribute_CPValid_Xpath, image1);
							if(image2!="")
								fun_cas.attributeValue(driver, galleryImages_CPValid_Xpath+"[2]", attribute_CPValid_Xpath, image2);
							if(image3!="")
								fun_cas.attributeValue(driver, galleryImages_CPValid_Xpath+"[3]", attribute_CPValid_Xpath, image3);
							if(overview!="")
								fun_cas.getTextValidation(driver, overview_CPValid_Xpath, overview);
							if(background!="")
								fun_cas.getTextValidation(driver, background_CPValid_Xpath, background);
//=======================================							
							}
					/*}
				}
			}
		}*/
	}
	public void attributeValue(WebDriver driver, String xpath, String attribute, String value) throws InterruptedException
	{
		WebElement element = driver.findElement(By.xpath(xpath));
		String ima1 = element.getAttribute(attribute);
		System.out.println("Attribute value:"+ima1);
		for (int i = 0 ; i<value.length() ; i++)
		{
	        if (value.charAt(i) == '.')
	        {
	        	System.out.println(i);
	        	String subString=value.substring(i, value.length());
	        	System.out.println(subString);
	        	
	        	String str3 = value.replaceAll(subString, "");
	        	System.out.println("removing.jpg:"+str3);
	        	int k=str3.length();
	        	System.out.println(k);
	        	String[] arr=str3.split("\\\\");
	        	System.out.println(arr.length);
	        	System.out.println(arr[arr.length-1]);
	        	String excel=arr[arr.length-1];
	        	if(ima1.contains(excel))
	        	{
	        		fl.disp_Message(driver, "", "", "image uploaded succesfully", "", "");
	        	}
	        	else
	        	{
	        		fl.disp_Message(driver, "", "", "image upload failed", "", "Y");
	        	}
	        	break;
	        }
		}
	}
	public String returnAttributeValue(WebDriver driver, String xpath, String attribute)
	{
		WebElement element = driver.findElement(By.xpath(xpath));
		String ima1 = element.getAttribute(attribute);
		if(ima1.contains("/Assets/Images/"))
		{
			return "true";
		}
		else
		{
			return "false";
		}
	}
	public int listSize(WebDriver driver, String xpath)
	{
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		int i=elements.size();
		return i;
	}
	public void getTextValidation(WebDriver driver, String xpath, String text) throws InterruptedException
	{
		String legal = fl.getTextXPATH(driver, xpath, "", "", "Validating :"+text, "", "");
		int length_web=legal.length();
		int length_excel=text.length();
		if(length_web>=length_excel)
		{
			if(legal.contains(text))
			{
				fl.disp_Message(driver, "", "", "field validation succesful", "", "");
			}
			else
			{
				fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
			}
		}
		else
		{
			if(text.contains(legal))
			{
				fl.disp_Message(driver, "", "", "field validation succesful", "", "");
			}
			else
			{
				fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
			}
		}
	}
	public void myProfile(WebDriver driver, String name, String contact, String occupation, String visaStatus, String birthDate, String salarySel,
			String salary_val, String street1, String street2, String city,
			String country, String state, String zipcode, String timezone) throws InterruptedException, IOException
	{
		Functional_Cases_propread fun_case = new Functional_Cases_propread();
		String Company_LogoXPATH=Environment("Company_LogoXPATH");
		String myProfile_Company_Xpath=Environment("myProfile_Company_Xpath");
		String edit_MP_Xpath=Environment("edit_MP_Xpath");
		String name_MP_Xpath=Environment("name_MP_Xpath");
		String contact_MP_Xpath=Environment("contact_MP_Xpath");
		String occupation_MP_Xpath=Environment("occupation_MP_Xpath");
		String occupationOptions_MP_Xpath=Environment("occupationOptions_MP_Xpath");
		String visa_MP_Xpath=Environment("visa_MP_Xpath");
		String visaOptions_MP_Xpath=Environment("visaOptions_MP_Xpath");
		String birth_month_MP_Xpath=Environment("birth_month_MP_Xpath");
		String salary_MP_Xpath=Environment("salary_MP_Xpath");
		String salaryOptions_MP_Xpath=Environment("salaryOptions_MP_Xpath");
		String salaryNo_MP_Xpath=Environment("salaryNo_MP_Xpath");
		String street1_MP_Xpath=Environment("street1_MP_Xpath");
		String street2_MP_Xpath=Environment("street2_MP_Xpath");
		String city_MP_Xpath=Environment("city_MP_Xpath");
		String country_MP_Xpath=Environment("country_MP_Xpath");
		String state_MP_Xpath=Environment("state_MP_Xpath");
		String zipcode_MP_Xpath=Environment("zipcode_MP_Xpath");
		String timezone_SelectMP_Xpath=Environment("timezone_SelectMP_Xpath");
		String timezone_SelectOptionsMP_Xpath=Environment("timezone_SelectOptionsMP_Xpath");
		String update_MP_Xpath=Environment("update_MP_Xpath");
		String cancel_MP_Xpath=Environment("cancel_MP_Xpath");
		
		fl.JS_Element_Find(driver, Company_LogoXPATH);
		fl.ClickByXpath(driver, Company_LogoXPATH, "", "Filling Myprofile", "Click on company Logo", "", "");
		fl.ClickByXpath(driver, myProfile_Company_Xpath, "", "", "Click on My Profile", "", "");
		fl.ClickByXpath(driver, edit_MP_Xpath, "", "", "Click on Edit", "", "Y");
		if(name!="")
		{
			fun_case.clearTextfield(driver, name_MP_Xpath, name);
			fl.entervalueByXpath(driver, name_MP_Xpath, name, "", "", "enter name", "", "");
		}
		if(contact!="")
		{
			fun_case.clearTextfield(driver, contact_MP_Xpath, contact);
			fl.entervalueByXpath(driver, contact_MP_Xpath, contact, "", "", "enter contact no", "", "");
		}
		if(occupation!="")
		{
			String occ=fl.checkOptionValueInSelect(driver, occupation_MP_Xpath, occupationOptions_MP_Xpath, occupation);
			if(occ.equals("true"))
			{
				fl.selectDropdownByxpath(driver, occupation_MP_Xpath, occupation, "", "", "Selecting existed element", "", "");
			}
			else
			{
				fl.disp_Message(driver, "", "", "Mentioned item not existed in Dropdown", "", "");
			}
		}
		if(visaStatus!="")
		{
			String visa=fl.checkOptionValueInSelect(driver, visa_MP_Xpath, visaOptions_MP_Xpath, visaStatus);
			if(visa.equals("true"))
			{
				fl.selectDropdownByxpath(driver, visa_MP_Xpath, visaStatus, "", "", "Selecting existed element", "", "");
			}
			else
			{
				fl.disp_Message(driver, "", "", "Mentioned item not existed in Dropdown", "", "");
			}
		}
		if(birthDate!="")
		{
			fun_case.clearTextfield(driver, birth_month_MP_Xpath, birthDate);
			fl.entervalueByXpath(driver, birth_month_MP_Xpath, birthDate, "", "", "enter birthDate", "", "");
		}
		if(salarySel!="")
		{
			String salary_sel=fl.checkOptionValueInSelect(driver, salary_MP_Xpath, salaryOptions_MP_Xpath, salarySel);
			if(salary_sel.equals("true"))
			{
				fl.selectDropdownByxpath(driver, salary_MP_Xpath, salarySel, "", "", "Selecting Salary Type", "", "");
			}
			else
			{
				fl.disp_Message(driver, "", "", "Mentioned item not existed in Dropdown", "", "");
			}
		}
		if(salary_val!="")
		{
			fun_case.clearTextfield(driver, salaryNo_MP_Xpath, salary_val);
			fl.entervalueByXpath(driver, salaryNo_MP_Xpath, salary_val, "", "", "enter Salary Value", "", "");
		}
		if(timezone!="")
		{
			fun_case.clearTextfield(driver, timezone_SelectMP_Xpath, timezone);
			fl.selectDropdownByxpath(driver, timezone_SelectMP_Xpath, timezone, "", "", "", "", "");
		}
		if(street1!="")
		{
			fun_case.clearTextfield(driver, street1_MP_Xpath, street1);
			fl.entervalueByXpath(driver, street1_MP_Xpath, street1, "", "", "enter street1", "", "");
		}
		if(street2!="")
		{
			fun_case.clearTextfield(driver, street2_MP_Xpath, street2);
			fl.entervalueByXpath(driver, street2_MP_Xpath, street2, "", "", "enter street1", "", "");
		}
		if(city!="")
		{
			fun_case.clearTextfield(driver, city_MP_Xpath, city);
			fl.JS_Element_Find(driver, city_MP_Xpath);
			fl.entervalueByXpath(driver, city_MP_Xpath, city, "", "", "enter city", "", "");
		}
		if(country!="")
		{
			fun_case.clearTextfield(driver, country_MP_Xpath, country);
			fl.entervalueByXpath(driver, country_MP_Xpath, country, "", "", "enter country", "", "");
		}
		if(state!="")
		{
			fun_case.clearTextfield(driver, state_MP_Xpath, state);
			fl.entervalueByXpath(driver, state_MP_Xpath, state, "", "", "enter state", "", "");
		}
		if(zipcode!="")
		{
			fun_case.clearTextfield(driver, zipcode_MP_Xpath, zipcode);
			fl.entervalueByXpath(driver, zipcode_MP_Xpath, zipcode, "", "", "enter zipcode", "", "");
		}
		
		fl.ClickByXpath(driver, update_MP_Xpath, "", "", "click on Update", "", "Y");
	}
	public void myProfile_Validation(WebDriver driver, String name, String contact, String occupation, String visaStatus, String birthDate, String salarySel,
			String salary_val, String street1, String street2, String city,
			String country, String state, String zipcode, String timezone) throws InterruptedException, IOException
	{
			Functional_Cases_propread fun_cas= new Functional_Cases_propread();
			String name_MPV_Xpath=Environment("name_MPV_Xpath");
			//String empID_MPV_Xpath=Environment("empID_MPV_Xpath");
			String email_MPV_Xpath=Environment("email_MPV_Xpath");
			String contact_MPV_Xpath=Environment("contact_MPV_Xpath");
			String designation_MPV_Xpath=Environment("designation_MPV_Xpath");
			String role_MPV_Xpath=Environment("role_MPV_Xpath");
			String occupation_MPV_Xpath=Environment("occupation_MPV_Xpath");
			String visa_MPV_Xpath=Environment("visa_MPV_Xpath");
			String salary_MPV_Xpath=Environment("salary_MPV_Xpath");
			String birthMY_MPV_Xpath=Environment("birthMY_MPV_Xpath");
			String status_MPV_Xpath=Environment("status_MPV_Xpath");
			String street_MPV_Xpath=Environment("street_MPV_Xpath");
			String city_MPV_Xpath=Environment("city_MPV_Xpath");
			String state_MPV_Xpath=Environment("state_MPV_Xpath");
			String countryPin_MPV_Xpath=Environment("countryPin_MPV_Xpath");
			String timezone_MPV_Xpath=Environment("timezone_MPV_Xpath");
				
			if(name!="")
				fun_cas.getTextValidation(driver, name_MPV_Xpath, name);
			if(contact!="")
				fun_cas.getTextValidation(driver, contact_MPV_Xpath, contact);
			if(occupation!="")
				fun_cas.getTextValidation(driver, occupation_MPV_Xpath, occupation);
			if(visaStatus!="")
				fun_cas.getTextValidation(driver, visa_MPV_Xpath, visaStatus);
			if(salarySel!="")
			{
				if(salarySel=="AUD"||salarySel=="CAD"||salarySel=="USD")
				fun_cas.getTextValidation(driver, salary_MPV_Xpath, "$");
				if(salarySel=="GBP")
					fun_cas.getTextValidation(driver, salary_MPV_Xpath, "£");
				else
					if(salarySel=="INR")
					fl.disp_Message(driver, "", "", "Indian Currency Type", "", "");
			}
			if(salary_val!="")
				fun_cas.getTextValidation(driver, salary_MPV_Xpath, salary_val);
			if(birthDate!="")
			{
				String sal = fl.getTextXPATH(driver, birthMY_MPV_Xpath, "", "", "Validating :"+birthDate, "", "");
				String sal_val=sal.replaceAll("/", "");
				if(sal_val.contains(birthDate))
				{
					fl.disp_Message(driver, "", "", "field validation succesful", "", "");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
				}
			}
			
			if(street2!="")
				fun_cas.getTextValidation(driver, street_MPV_Xpath, street2);
			if(city!="")
				fun_cas.getTextValidation(driver, city_MPV_Xpath, city);
			if(state!="")
				fun_cas.getTextValidation(driver, state_MPV_Xpath, state);
			if(country!="")
				fun_cas.getTextValidation(driver, countryPin_MPV_Xpath, country);
			if(zipcode!="")
				fun_cas.getTextValidation(driver, countryPin_MPV_Xpath, zipcode);
			if(timezone!="")
				fun_cas.getTextValidation(driver, timezone_MPV_Xpath, timezone);
	}
	public void clearTextfield(WebDriver driver, String Xpath, String value) throws IOException, InterruptedException
	{
		
		if(!value.equals(""))
		{
			fl.ClickByXpath(driver, Xpath, "", "", "clear existing data", "", "");
			select_delete selDel = new select_delete();
			Thread.sleep(3000);
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
	public void candidateRegistration(WebDriver driver, int first, String firstname, String lastname, String emailid, String contactnumber, String password,
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
		String verifyTextIndividual_Xpath=Environment("verifyTextIndividual_Xpath");
		String verifyIndividual_Xpath=Environment("verifyIndividual_Xpath");

		try {
			if(first==1)
			{
				fl.invokeApplication(driver, Company_Baseurl, "", "Invoke Application", "", "", "", "");
				fl.ClickByXpath(driver, Signup_Xpath, "", "Click on Signup", "", "", "");
			}
			else
			{
				String referURL=excelRW.readExcel(Environment("refer_Excel"), 1);
				fl.invokeApplication(driver, referURL, "", "Invoke Application", "", "", "", "");
			}
			fl.entervalueByXpath(driver, FirstNameIndividual_Xpath, firstname, "enter firstname", "", "", "", "");
			
			fl.entervalueByXpath(driver, LastNameIndividual_Xpath, lastname, "enter lastname", "", "", "", "");
			
			fl.entervalueByXpath(driver, EmailIDIndividual_Xpath, emailid, "enter emailId", "", "", "", "");
			
			fl.entervalueByXpath(driver, ContactNumberIndividual_Xpath, contactnumber, "enter contact number", "", "", "", "");
			
			fl.entervalueByXpath(driver, PasswordIndividual_Xpath, password, "enter password", "", "", "", "");
			
			fl.entervalueByXpath(driver, ConfirmPasswordIndividual_Xpath, confirmpassword, "re-enter password", "", "", "", "");
			
			fl.ClickByXpath(driver, CaptchaIndivial_Xpath, "", "enter captcha", "", "", "");
			Thread.sleep(10000);
			fl.ClickByXpath(driver, AgreeIndividual_Xpath, "", "Accept Terms & Conditions", "", "", "");
			
			fl.ClickByXpath(driver, RegisterIndividual_Xpath, "", "click on Register", "", "", "");

			String Verify_Code_Candit = DB_Connection_Digi_Candit.Db_Connect(emailid);
			System.out.println(Verify_Code_Candit);

			fl.entervalueByXpath(driver, verifyTextIndividual_Xpath, Verify_Code_Candit, "", "", "",
					"", "");

			fl.ClickByXpath(driver, verifyIndividual_Xpath, "", "", "", "", "");
			Thread.sleep(10000);

		} 
		catch (WebDriverException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
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
	public void companyRegistration(WebDriver driver, int first, String yourname, String orgname, String orgemailid, String contno, String fein, String website,
			String password, String confirmpassword, String captcha) throws IOException, InterruptedException
			
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
		String verifyCodeOrg_Xpath=Environment("verifyCodeOrg_Xpath");
		String verifyOrg_Xpath=Environment("verifyOrg_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		try 
		{
			
			if(first==1)
			{
				fl.invokeApplication(driver, Company_Baseurl, "", "Invoke Application Url", "", "", "", "");
				fl.ClickByXpath(driver, Signup_Xpath, "", "click on Signup ", "", "", "");
			}
			else
			{
				String companyReferUrl=excelRW.readExcel(Environment("refer_Excel"), 1);
				fl.invokeApplication(driver, companyReferUrl, "", "Invoke Application Url", "", "", "", "");
			}



			fl.ClickByXpath(driver, Organization_Xpath, "", "Click on Organization", "", "", "");

			fl.entervalueByXpath(driver, YourNameOrg_Xpath, yourname, "enter name", "", "", "", "");

			fl.entervalueByXpath(driver, OrgaNameOrg_Xpath, orgname, "enter organization name", "", "", "", "");

			fl.entervalueByXpath(driver, OrgEmailID_Xpath, orgemailid, "enter company email id", "", "", "", "");

			fl.entervalueByXpath(driver, ContactNumber_Xpath, contno, "enter contact number", "", "", "", "");

			fl.entervalueByXpath(driver, Fein_Xpath, fein, "enter fein", "", "", "", "");
			FEIN_From_Excel = fein;

			System.out.println(FEIN_From_Excel);
			fl.entervalueByXpath(driver, WebsiteOrg_Xpath, website, "enter website", "", "", "", "");

			fl.entervalueByXpath(driver, PasswordOrg_Xpath, password, "enter password", "", "", "", "");

			fl.entervalueByXpath(driver, ConfirmPasswordOrg_Xpath, confirmpassword, "re-enter password", "", "", "", "");

			Thread.sleep(10000);
			fl.entervalueByXpath(driver, CaptchOrg_xpath, captcha, "enter captcha", "", "", "", "");
			
			
			fl.ClickByXpath(driver, AgreeOrg_Xpath, "", "Accept Terms & Conditions", "", "", "");

			// Thread.sleep(3000);
			fl.JS_Element_Find(driver, RegisterOrg_Xpath);
			fl.ClickByXpath(driver, RegisterOrg_Xpath, "", "Click on Register", "", "", "");
			
			String code = DB_Connection_Digi_Company.Db_Connect(fein);
			fl.entervalueByXpath(driver, verifyCodeOrg_Xpath, code, "", "", "Verification code to be entered", "", "");
			fl.ClickByXpath(driver, verifyOrg_Xpath, "", "", "click on Verify", "", "");
		} 
		catch (Exception e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
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
	public void create_Roles(WebDriver driver, String employee, String manager, String administrator, String rolename) throws IOException, InterruptedException
	{
		String Setup_Xpath=Environment("Setup_Xpath");
		String SetupRoles_Xpath=Environment("SetupRoles_Xpath");
		String new_CreateRole_Xpath=Environment("new_CreateRole_Xpath");
		String employee_CreateRole_Xpath=Environment("employee_CreateRole_Xpath");
		String manager_CreateRole_Xpath=Environment("manager_CreateRole_Xpath");
		String admin_CreateRole_Xpath=Environment("admin_CreateRole_Xpath");
		String rolename_CreateRole_Xpath=Environment("rolename_CreateRole_Xpath");
		String save_CreateRole_Xpath=Environment("save_CreateRole_Xpath");
		String errorAlert_createDesignation_Xpath=Environment("errorAlert_createDesignation_Xpath");
		try
		{
			fl.ClickByXpath(driver, Setup_Xpath, "", "Creating Roles", "Go to Setup Menu", "", "");
			fl.ClickByXpath(driver, SetupRoles_Xpath, "", "", "click on Roles", "", "");
			fl.ClickByXpath(driver, new_CreateRole_Xpath, "", "", "click on new", "", "Y");
			if(employee!="")
			{
				fl.ClickByXpath(driver, employee_CreateRole_Xpath, "", "", "", "", "");
			}
			if(manager!="")
			{
				fl.ClickByXpath(driver, manager_CreateRole_Xpath, "", "", "", "", "");
			}
			if(administrator!="")
			{
				fl.ClickByXpath(driver, admin_CreateRole_Xpath, "", "", "", "", "");
			}
			fl.entervalueByXpath(driver, rolename_CreateRole_Xpath, rolename, "", "", "Rolename to be entered", "", "");
			fl.ClickByXpath(driver, save_CreateRole_Xpath, "", "", "click on Save", "", "Y");
			if(fl.elementDisplayed(driver, errorAlert_createDesignation_Xpath, "").equals("true"))
			{
				fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
				Assert.fail();
			}
			
		} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void createdesignation(WebDriver driver, String designation_name, String notes) throws IOException, InterruptedException
	{
		String Setup_Xpath=Environment("Setup_Xpath");
		String SetupDesignation_Xapth=Environment("SetupDesignation_Xapth");
		String advancedSearch_createDesignation_Xpath=Environment("advancedSearch_createDesignation_Xpath");
		String searchName_createDesignation_Xpath=Environment("searchName_createDesignation_Xpath");
		String noRecord_createDesignation_Xpath=Environment("noRecord_createDesignation_Xpath");
		String new_createDesignation_Xpath=Environment("new_createDesignation_Xpath");
		String name_createDesignation_Xpath=Environment("name_createDesignation_Xpath");
		String notes_createDesignation_Xpath=Environment("notes_createDesignation_Xpath");
		String save_createDesignation_Xpath=Environment("save_createDesignation_Xpath");
		try 
		{
			fl.ClickByXpath(driver, Setup_Xpath, "", "Creating Roles", "Go to Setup Menu", "", "");
			fl.ClickByXpath(driver, SetupDesignation_Xapth, "", "", "Click on Designations", "", "");
			fl.ClickByXpath(driver, advancedSearch_createDesignation_Xpath, "", "", "Checking Designation Already existed or not", "", "");
			fl.entervalueByXpath(driver, searchName_createDesignation_Xpath, designation_name, "", "", "search with:"+designation_name, "", "");
			String norecords=fl.getTextXPATH(driver, noRecord_createDesignation_Xpath, "", "", "", "", "");
			if(norecords.contains("No"))
			{
				fl.ClickByXpath(driver, new_createDesignation_Xpath, "", "", "Click on new", "", "");
				fl.entervalueByXpath(driver, name_createDesignation_Xpath, designation_name, "", "", "designation name to be entered", "", "");
				fl.entervalueByXpath(driver, notes_createDesignation_Xpath, notes, "", "", "notes to be entered", "", "");
				fl.ClickByXpath(driver, save_createDesignation_Xpath, "", "", "Click on save", "", "");
			}
			else
			{
				fl.disp_Message(driver, "", "", "Error Occured", "", "Y");
			}
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public String AddUsers_inCompany(WebDriver driver, String name, String empID, String email, String contact, String designation, String role, 
			String parentRole, String reportManager, String hrManager, String street1, String street2, String city, String country,
			String state, String zipcode, String filepath, String description) throws IOException, InterruptedException 
	{
		
		System.out.println("creating users");
		String Setup_Xpath=Environment("Setup_Xpath");
		String mainMenuList_Xpath=Environment("mainMenuList_Xpath");
		String SetupUsers_Xapth=Environment("SetupUsers_Xapth");
		String Setup_DBiA_Xpath=Environment("Setup_DBiA_Xpath");
		String SetupUsers_DBiA_Xapth=Environment("SetupUsers_DBiA_Xapth");
		String goback_NewUser_Xpath=Environment("goback_NewUser_Xpath");
		String advancedSearch_NewUser_Xpath=Environment("advancedSearch_NewUser_Xpath");
		String username_NewUser_Xpath=Environment("username_NewUser_Xpath");
		String noUsers_NewUser_Xpath=Environment("noUsers_NewUser_Xpath");
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
		String Street2_Xpath=Environment("Street2_Xpath");
		String City_Xapth=Environment("City_Xapth");
		String Country_Select_Xpath=Environment("Country_Select_Xpath");
		String State_Select_Xapth=Environment("State_Select_Xapth");
		String Zipcode_Xpath=Environment("Zipcode_Xpath");
		String Filepath_Xpath=Environment("Filepath_Xpath");
		String Notes_Xpath=Environment("Notes_Xpath");
		String save_NewUser_Xpath=Environment("save_NewUser_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		try 
		{
			
			String url = driver.getCurrentUrl();
			if(!url.contains("/CreateNewUser"))
			{
				fl.JS_Element_Find(driver, Setup_Xpath);
				//jse.executeScript("window.scrollBy(0,-450)", "");
				Thread.sleep(3000);
				
				int mainList=fun_cas.listSize(driver, mainMenuList_Xpath);
				if(mainList==6)
				{
					fl.ClickByXpath(driver, Setup_DBiA_Xpath, "", "", "Setup Menu", "", "");
					fl.ClickByXpath(driver, SetupUsers_DBiA_Xapth, "", "Click on Users", "", "", "");
				}
				else
				{
					fl.ClickByXpath(driver, Setup_Xpath, "", "Setup Menu", "", "", "");

					fl.ClickByXpath(driver, SetupUsers_Xapth, "", "Click on Users", "", "", "");
				}
			}
			else
			{
				fl.ClickByXpath(driver, goback_NewUser_Xpath, "", "", "Click on Go Back", "", "");
			}
					
			fl.ClickByXpath(driver, advancedSearch_NewUser_Xpath, "", "Search for user before going to create", "", "", "");
			if(name!="")	
			fl.entervalueByXpath(driver, username_NewUser_Xpath, name, "", "", "", "", "");
					
			String noUsers=fl.getTextXPATH(driver, noUsers_NewUser_Xpath, "", "", "", "", "");
			if(noUsers.contains("No"))
			{
				fl.ClickByXpath(driver, New_User_Xapth, "", "Click on New User", "", "", "");

				if(name!="")
				fl.entervalueByXpath(driver, Name_NewUser_Xapth, name, "fill Name field", "", "", "", "");
				if(empID!="")
				fl.entervalueByXpath(driver, EmployeeID_NewUser_Xapth, empID, "fill EmployeeID field", "", "", "", "");
				if(email!="")
				fl.entervalueByXpath(driver, Email_NewUser_Xapth, email, "fill Email Field", "", "", "", "");
				if(contact!="")
				fl.entervalueByXpath(driver, Contact_NewUser_Xapth, contact, "fill Contact Field", "", "", "", "");
				if(designation!="")
				{
					String Designation = fl.checkOptionValueInSelect(driver, designation_NewUser_SelectXapth, designation_NewUser_SelectOptionsXapth ,designation);
					
					if(Designation.equals("true"))
					{
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, designation, "select dropdown item", "", "", "", "");
					}
					else
					{
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, "+ Add New", "Designation need to create", "", "", "", "");
					
						fl.entervalueByXpath(driver, NewDesignation_Xpath, designation, "enter value in New Designation", "", "", "", "");
						
						fl.ClickByXpath(driver, AdddesignationButton_Xpath, "", "Click on AddDesignation Button", "", "", "");
						
						fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, designation, "Selecting created Dropdown element", "", "", "", "");
						
					}
				}
				if(role!="")
				{
					String Role = fl.checkOptionValueInSelect(driver, Role_Select_Xapth, Role_SelectOption_Xapth ,role);
					
					if(Role.equals("true"))
					{
						fl.selectDropdownByxpath(driver, Role_Select_Xapth, role, "Selecting Role", "", "", "", "");
					}
					else
					{
						fl.selectDropdownByxpath(driver, Role_Select_Xapth, "+ Add New", "Need to create a new Role", "", "", "", "");
						
						fl.entervalueByXpath(driver, RoleName_Xpath, role, "Enter Role", "", "", "", "");
						
						fl.selectDropdownByxpath(driver, ParentRole_SelectXpath, parentRole, "Select Parent Role", "", "", "", "");
					
						fl.ClickByXpath(driver, AddRole_Xpath, "", "Click on AddRole", "", "", "");
						
						fl.selectDropdownByxpath(driver, Role_Select_Xapth, role, "", "", "Selecting created Role", "", "");
					}
				}
				if(street1!="")
				fl.entervalueByXpath(driver, Street1_Xpath, street1, "", "Stree1 Field to be filled", "", "", "");
				if(street2!="")	
				fl.entervalueByXpath(driver, Street2_Xpath, street2, "", "", "Stree1 Field to be filled", "", "");
				if(city!="")	
				fl.entervalueByXpath(driver, City_Xapth, city, "City Field to be filled", "", "", "", "");
				if(country!="")
				fl.entervalueByXpath(driver, Country_Select_Xpath, country, "Country field to be filled", "", "", "", "");
				if(state!="")
				fl.entervalueByXpath(driver, State_Select_Xapth, state, "State Field to be selected", "", "", "", "");
				if(zipcode!="")
				fl.entervalueByXpath(driver, Zipcode_Xpath, zipcode, "Zipcode to be entered", "", "", "", "");
				if(filepath!="")
				{
					fl.ClickByXpath(driver, Filepath_Xpath, "", "choose file to upload the file", "", "", "");
					
					UR.uploadFile(filepath);
				}
				if(description!="")
				fl.entervalueByXpath(driver, Notes_Xpath, description, "", "", "enter desription", "", "");
				fl.JS_Element_Find(driver, save_NewUser_Xpath);
				fl.ClickByXpath(driver, save_NewUser_Xpath, "", "save Created User", "", "", "Y");
				System.out.println("user details saved");
				Thread.sleep(1000);
				return "true";
			}
			else
			{
				//fl.disp_Message(driver, "", "Already Existed Username", "", "", "Y");
				String moreButtons_AUV_Xpath=Environment("moreButtons_AUV_Xpath");
				String row_NewUser_Xpath=Environment("row_NewUser_Xpath");
				String user_NewUser_Xpath=Environment("user_NewUser_Xpath");
				int more = fun_cas.listSize(driver, moreButtons_AUV_Xpath);
				int exist=0;
				for(int i=1;i<=more;i++)
				{
					String userN=fl.getTextXPATH(driver, row_NewUser_Xpath+"["+i+"]"+user_NewUser_Xpath, "", "", "check user existed or not", "", "");
					if(userN.equals(name))
					{
						exist++;
						
					}
				}
				if(exist==1)
					fl.disp_Message(driver, "", "Already Existed Username", "", "", "Y");
				else
				{
					fl.ClickByXpath(driver, New_User_Xapth, "", "Click on New User", "", "", "");

					if(name!="")
					fl.entervalueByXpath(driver, Name_NewUser_Xapth, name, "fill Name field", "", "", "", "");
					if(empID!="")
					fl.entervalueByXpath(driver, EmployeeID_NewUser_Xapth, empID, "fill EmployeeID field", "", "", "", "");
					if(email!="")
					fl.entervalueByXpath(driver, Email_NewUser_Xapth, email, "fill Email Field", "", "", "", "");
					if(contact!="")
					fl.entervalueByXpath(driver, Contact_NewUser_Xapth, contact, "fill Contact Field", "", "", "", "");
					if(designation!="")
					{
						String Designation = fl.checkOptionValueInSelect(driver, designation_NewUser_SelectXapth, designation_NewUser_SelectOptionsXapth ,designation);
						
						if(Designation.equals("true"))
						{
							fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, designation, "select dropdown item", "", "", "", "");
						}
						else
						{
							fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, "+ Add New", "Designation need to create", "", "", "", "");
						
							fl.entervalueByXpath(driver, NewDesignation_Xpath, designation, "enter value in New Designation", "", "", "", "");
							
							fl.ClickByXpath(driver, AdddesignationButton_Xpath, "", "Click on AddDesignation Button", "", "", "");
							
							fl.selectDropdownByxpath(driver, designation_NewUser_SelectXapth, designation, "Selecting created Dropdown element", "", "", "", "");
							
						}
					}
					if(role!="")
					{
						String Role = fl.checkOptionValueInSelect(driver, Role_Select_Xapth, Role_SelectOption_Xapth ,role);
						
						if(Role.equals("true"))
						{
							fl.selectDropdownByxpath(driver, Role_Select_Xapth, role, "Selecting Role", "", "", "", "");
						}
						else
						{
							fl.selectDropdownByxpath(driver, Role_Select_Xapth, "+ Add New", "Need to create a new Role", "", "", "", "");
							
							fl.entervalueByXpath(driver, RoleName_Xpath, role, "Enter Role", "", "", "", "");
							
							fl.selectDropdownByxpath(driver, ParentRole_SelectXpath, parentRole, "Select Parent Role", "", "", "", "");
						
							fl.ClickByXpath(driver, AddRole_Xpath, "", "Click on AddRole", "", "", "");
							
							fl.selectDropdownByxpath(driver, Role_Select_Xapth, role, "", "", "Selecting created Role", "", "");
						}
					}
					if(street1!="")
					fl.entervalueByXpath(driver, Street1_Xpath, street1, "", "Stree1 Field to be filled", "", "", "");
					if(street2!="")	
					fl.entervalueByXpath(driver, Street2_Xpath, street2, "", "", "Stree1 Field to be filled", "", "");
					if(city!="")	
					fl.entervalueByXpath(driver, City_Xapth, city, "City Field to be filled", "", "", "", "");
					if(country!="")
					fl.entervalueByXpath(driver, Country_Select_Xpath, country, "Country field to be filled", "", "", "", "");
					if(state!="")
					fl.entervalueByXpath(driver, State_Select_Xapth, state, "State Field to be selected", "", "", "", "");
					if(zipcode!="")
					fl.entervalueByXpath(driver, Zipcode_Xpath, zipcode, "Zipcode to be entered", "", "", "", "");
					if(filepath!="")
					{
						fl.ClickByXpath(driver, Filepath_Xpath, "", "choose file to upload the file", "", "", "");
						
						UR.uploadFile(filepath);
					}
					if(description!="")
					fl.entervalueByXpath(driver, Notes_Xpath, description, "", "", "enter desription", "", "");
					fl.JS_Element_Find(driver, save_NewUser_Xpath);
					fl.ClickByXpath(driver, save_NewUser_Xpath, "", "save Created User", "", "", "Y");
					System.out.println("user details saved");
					Thread.sleep(1000);
					return "true";
				}
					
					
				
			}
			
		}
		catch (WebDriverException e) 
		{
			fl.disp_Message(driver, "", "Error Occured:", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		return "false";
	}
	public void AddUsers_inCompanyValidation(WebDriver driver, String name, String empID, String email, String contact, String designation, String role, 
			String parentRole, String reportManager, String hrManager, String street1, String street2, String city, String country,
			String state, String zipcode, String filepath, String description) throws IOException, InterruptedException 
	{
		Functional_Cases_propread fun_cas  = new Functional_Cases_propread();
		String goback_NewUser_Xpath=Environment("goback_NewUser_Xpath");
		String advancedSearch_AUV_Xpath=Environment("advancedSearch_AUV_Xpath");
		String username_AUV_Xpath=Environment("username_AUV_Xpath");
		String moreButtons_AUV_Xpath=Environment("moreButtons_AUV_Xpath");
		String view_AUV_Xpath=Environment("view_AUV_Xpath");
		String Name_AUV_Xpath=Environment("Name_AUV_Xpath");
		String empID_AUV_Xpath=Environment("empID_AUV_Xpath");
		String emailID_AUV_Xpath=Environment("emailID_AUV_Xpath");
		String contact_AUV_Xpath=Environment("contact_AUV_Xpath");
		String role_AUV_Xpath=Environment("role_AUV_Xpath");
		String designation_AUV_Xpath=Environment("designation_AUV_Xpath");
		String street1_AUV_Xpath=Environment("street1_AUV_Xpath");
		String street2_AUV_Xpath=Environment("street2_AUV_Xpath");
		String city_AUV_Xpath=Environment("city_AUV_Xpath");
		String state_AUV_Xpath=Environment("state_AUV_Xpath");
		String country_AUV_Xpath=Environment("country_AUV_Xpath");
		String zipcode_AUV_Xpath=Environment("zipcode_AUV_Xpath");
		String note_AUV_Xpath=Environment("note_AUV_Xpath");
		String close_AUV_Xpath=Environment("close_AUV_Xpath");
		
		fl.ClickByXpath(driver, goback_NewUser_Xpath, "", "AddUser Validation", "", "", "");
		fl.ClickByXpath(driver, advancedSearch_AUV_Xpath, "", "", "Click on Advanced Search", "", "");
		fl.entervalueByXpath(driver, username_AUV_Xpath, name, "", "", "filter with:"+name, "", "");
		fl.ClickByXpath(driver, moreButtons_AUV_Xpath, "", "", "click more options", "", "");
		fl.ClickByXpath(driver, moreButtons_AUV_Xpath, "", "", "click more options", "", "");
		fl.ClickByXpath(driver, view_AUV_Xpath, "", "", "Click on View Option", "", "");
		
		if(name!="")
			fun_cas.getTextValidation(driver, username_AUV_Xpath, name);
		if(empID!="")
			fun_cas.getTextValidation(driver, empID_AUV_Xpath, empID);
		if(email!="")
			fun_cas.getTextValidation(driver, emailID_AUV_Xpath, email);
		if(contact!="")
		{
			//fun_cas.getTextValidation(driver, contact_AUV_Xpath, contact);
			String con = fl.getTextXPATH(driver, contact_AUV_Xpath, "", "", "Validating :"+contact, "", "");
			String cont=con.replaceAll("[()-]", "");
			if(contact.contains(cont))
			{
				fl.disp_Message(driver, "", "", "field validation succesful", "", "");
			}
			else
			{
				fl.disp_MessageFailed(driver, "", "", "Field validation fail", "", "Y");
			}
		}
		if(role!="")
			fun_cas.getTextValidation(driver, role_AUV_Xpath, role);
		if(designation!="")
			fun_cas.getTextValidation(driver, designation_AUV_Xpath, designation);
		if(street1!="")
			fun_cas.getTextValidation(driver, street1_AUV_Xpath, street1);
		if(street2!="")
			fun_cas.getTextValidation(driver, street2_AUV_Xpath, street2);
		if(city!="")
			fun_cas.getTextValidation(driver, city_AUV_Xpath, city);
		if(state!="")
			fun_cas.getTextValidation(driver, state_AUV_Xpath, state);
		if(country!="")
			fun_cas.getTextValidation(driver, country_AUV_Xpath, country);
		if(zipcode!="")
			fun_cas.getTextValidation(driver, zipcode_AUV_Xpath, zipcode);
		if(zipcode!="")
			fun_cas.getTextValidation(driver, zipcode_AUV_Xpath, zipcode);
		if(description!="")
			fun_cas.getTextValidation(driver, note_AUV_Xpath, description);
		fl.ClickByXpath(driver, close_AUV_Xpath, "", "", "Close the popup of userDetailView", "", "");
	}
	public void create_survey_Categeory(WebDriver driver, String new_or_existedName, String UpdatedName, String Notes ) throws IOException
	{
		//add these to properties file
		String Setup_Xpath=Environment("Setup_Xpath");
		String SetupSurveyCategeories_Xpath=Environment("SetupSurveyCategeories_Xpath");
		String NewSurveyCategeory_Xpath=Environment("NewSurveyCategeory_Xpath");
		String AdvanceSearch_Xpath=Environment("AdvanceSearch_Xpath");
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
			
			
			
			fl.ClickByXpath(driver, Setup_Xpath, "", "go to setup menu", "", "", "");
			
			Thread.sleep(1000);
			
			fl.ClickByXpath(driver, SetupSurveyCategeories_Xpath, "", "click on surveyCategeories", "", "", "");
			
			fl.ClickByXpath(driver, AdvanceSearch_Xpath, "", "click on advanced Search, checking once in categoryList whether the mentioned categeory existed or not", "", "", "");
			
			fl.entervalueByXpath(driver, SearchSurveyCategeory_Xpath, new_or_existedName, "enter the categoey name "+new_or_existedName, "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyCategeory_Xpath")).getText().contains("No"))//No Survey Categories
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "No Results found, click on new", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "enter name", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "save the created Survey Category", "", "", "");
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
			}
			else			
			if(fl.findByXpath(driver, Environment("SurveyCategeoryName_Xapth")).getText().equals(new_or_existedName))
			{
				fl.ClickByXpath(driver, EditSurveyCategeory_Xpath, "", "Edit Survey Categeory", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, UpdatedName, "update the Category name", "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter the notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "update SurveyCategory", "", "", "");
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
				
			}
			else
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "No Results found, click on new", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "enter name", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "save SurveyCategory", "", "", "");
				
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
			 String UpdatedName, String Notes, String subGroup) throws IOException, InterruptedException
	{
		//add Xpath to properties file
		String Setup_Xpath=Environment("Setup_Xpath");
		String AdvancedSearch_Groups_Xpath=Environment("AdvancedSearch_Groups_Xpath");
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
			fl.ClickByXpath(driver, Setup_Xpath, "", "click on setup menu", "", "", "");
			fl.ClickByXpath(driver, SetupGroups_Xpath, "", "click on Groups", "", "", "");
			Thread.sleep(3000);
			fl.ClickByXpath(driver, AdvancedSearch_Groups_Xpath, "", "click on advanced search", "", "", "");
			
			//Group name already there or not
			fl.entervalueByXpath(driver,searchSurveyGroup_Xpath , new_or_existedName, "search with name, before you are going to create ", "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyGroup_Xpath")).getText().contains("No"))//No Groups
			{
				//create new Group
				
				fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "Not having group with that name, click on new", "", "", "");
				
				//subgroup creation
				
				String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
				if(subgroup_TorF.equals("true"))
				{
					fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "subgroup is selected", "", "", "", "");
				}
				else
				{
					//sub group creation
					//fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "", "", "", "");
					
					//fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "", "", "", "");
					
					fl.disp_Message(driver, "", "Mentioned subgroup name is not existed , create the subgroup with that name", "", "", "");
					
					fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "enter the Subgroup:"+subGroup, "", "", "", "");
					
					fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "saving created subgroup", "", "", "");
					Thread.sleep(3000);
					//select subgroup
					//fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "click on new button in Groups", "", "", "");
					
					fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "select subgroup which you have created now", "", "", "", "");				
				}
				
				fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, new_or_existedName, "enter the Group name:"+new_or_existedName, "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "Save the created groups", "", "", "");
				
				//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
			}
			else
				if(fl.findByXpath(driver, Environment("GroupName_Xpath")).getText().equals(new_or_existedName))
				{
				
					fl.ClickByXpath(driver, Environment("EditExistedSurveyGroup_Xpath"), "", "Group name existed, Click on Edit", "", "", "");
						//check subgroup existed or not
					String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
					if(subgroup_TorF.equals("true"))
					{
						fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "Subgroup you mentioned is selected", "", "", "");
					}
					else
					{
						//sub group creation
						fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "Subgroup you mentioned is not existed, cancel the new popup", "", "", "");
					
						fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "click on New Survey Group", "", "", "");
					
						fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "", "enter the name of subgroup:"+subGroup, "", "", "");
					
						fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "saving the surveyGroup", "", "", "");
					
						//again search
						
						fl.entervalueByXpath(driver,searchSurveyGroup_Xpath , new_or_existedName, "Again search for existed survey group", "", "", "", "");
						
						//select subgroup
						
						fl.ClickByXpath(driver, Environment("EditExistedSurveyGroup_Xpath"), "", "click on edit", "", "", "");
						
						fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "select the created subgroup", "", "", "");
					
										
					}
				
					fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, UpdatedName, "update the groupname", "", "", "", "");
				
					fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "", "update the Group Notes", "", "", "");
				
					fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "update the survey Group", "", "", "");
				
					//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
				
				
					}
					else
					{
				
						//create new Group
				
						fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "Create New Group", "", "", "");
				
						//subgroup creation
				
						String subgroup_TorF=fl.checkOptionValueInSelect(driver, DropdownSelect_Xpath, DropdownSelectOptions_Xapth, subGroup);
				
						if(subgroup_TorF.equals("true"))
						{
							fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "Subgroup is selected", "", "", "");
						}
						else
						{
							//sub group creation
							//fl.ClickByXpath(driver, Environment("cancelSurveyGroup_Xpath"), "", "", "", "", "");
							
							//fl.ClickByXpath(driver, Environment("NewbuttonSurveyGroup_Xpath"), "", "", "", "", "");
							
							fl.disp_Message(driver, "", "Subgroup is not existed, need to create", "", "", "");
					
							fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, subGroup, "subgroup name is entered", "", "", "", "");
					
							fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "saving the subgroup", "", "", "");
					
					
					
							//click new, select subgroup
							fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "again click on new Group", "", "", "");
							
							fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "selecting the subgroup which you have created", "", "", "", "");
					
										
						}
				
						fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, new_or_existedName, "enter the survey Group name", "", "", "", "");
				
						fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "Enter the notes", "", "", "", "");
				
						fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "saving the survey Group", "", "", "");
				
						//fl.ClickByXpath(driver, cancelSurveyGroup_Xpath, "", "", "", "", "");
					}
			
				
			} 
			catch (InterruptedException e) 
			{
			
				e.printStackTrace();
				fl.disp_Message(driver, "", "error occured", "", "", "Y");
				Logs_DigiSurvey.info(e.getMessage());
			}
		
	}
	public void create_Survey(WebDriver driver,String SurveyCategeoryName, String SurveyCategeoryNotes,String SurveyGroupName
			, String SurveyGroupNotes, String SurveyGroupSubNotes, String SurveyName, String Description, String fileLocation, 
			String TemplateName, String Que_Control) throws IOException, InterruptedException
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
		String SurveyImage_Xpath=Environment("SurveyImage_Xpath");
		String SaveAsTemplate_Xpath=Environment("SaveAsTemplate_Xpath");
		String TemplateName_Xpath=Environment("TemplateName_Xpath");
		String SaveCreateSurevyAsTemplate_Xpath=Environment("SaveCreateSurevyAsTemplate_Xpath");
		String ExistedTemplates_Xpath=Environment("ExistedTemplates_Xpath");
		String selectExistedTemplate_select_Xpath=Environment("selectExistedTemplate_select_Xpath");
		String selectExistedTemplate_selectOptions_Xpath=Environment("selectExistedTemplate_selectOptions_Xpath");
		
		
		/*try 
		{*/
			
			//start=========================================
			fl.ClickByXpath(driver, Survey_Xpath, "", "Surveys Menu clicked", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "click on create Survey", "", "", "");
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
				
				fl.disp_Message(driver, "", "SurveyCategeory Not Existed , create a categeory from Setup", "", "", "");
				
				func_case.create_survey_Categeory(driver, SurveyCategeoryName, "", SurveyCategeoryNotes);
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "After creating categeory, go to surveys menu", "", "", "");
				
				fl.ClickByXpath(driver, createSurvey_Xpath, "", "click on create survey", "", "", "");
				
				
			}
			
			
			String check_SurveyGroupOPtion= fl.checkOptionValueInSelect(driver, SurveyGroupName_Xpath, SurveyGroupNameOptions_Xpath, SurveyGroupName);
			
			System.out.println(check_SurveyGroupOPtion);
			
			if(check_SurveyGroupOPtion.equals("true"))
			{
				/*fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, SurveyGroupName, "", "", "", "", "");*/
				
			}
			else
			{
				//if not found create surveyCategeory, call createSurveyGroup Method
				fl.disp_Message(driver, "", "need to create a survey group, with the name you mentioned", "", "", "");
				
				func_case.create_Gropus(driver, SurveyGroupName, "", SurveyGroupNotes, SurveyGroupSubNotes);
				
				Thread.sleep(3000);
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "after creating groups, Go to survey menu", "", "", "");
				
				fl.ClickByXpath(driver, createSurvey_Xpath, "", "click on create survey", "", "", "");
				
				/*Assert.fail("Mentioned SurveyGroup Not Existed");*/
			}
			if(TemplateName!="")
			{
				String exist=func_case.checkTemplateList(driver, TemplateName);
				if(exist.equals("true"))
				{
					fl.disp_Message(driver, "", "", "Templatename Already Existed", "", "");
					Assert.fail();
				}
				
			}
			
			fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, SurveyCategeoryName, "", "selected the SurveyCategeory"+SurveyCategeoryName, "", "", "");
			
			fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, SurveyGroupName, "Selected SurveyGroup"+SurveyGroupName, "", "", "", "");
			
			fl.entervalueByXpath(driver, SurveyName_Xapth, SurveyName, "entering the value of SurveyName:"+SurveyName, "", "", "", "");
			
			
			
			//preview surveyname
			Assert.assertTrue(fl.findByXpath(driver, SurveyPreviewName_Xpath).getText().equals(SurveyName),
					"preview Not matches with  survayname");
			
			
			
			fl.entervalueByXpath(driver, Survey_Description_Xpath,Description ,"Description to be entered", "", "", "", "");
			
			//preview description
			Assert.assertTrue(fl.findByXpath(driver, Survey_DescriptionPreview_Xpath).getText().equals(Description)
					,"Preview Not Matches with Description");
			
			fl.ClickByXpath(driver, SurveyImage_Xpath, "", "Upload SurveyImage", "", "", "");
			
			UploadFile_Robot upload = new UploadFile_Robot();
			upload.uploadFile(fileLocation);
			
			if(TemplateName!="")
			{
			
				fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "want to save Template", "", "", "");
			
				fl.entervalueByXpath(driver, TemplateName_Xpath,TemplateName, "enter the template name", "", "", "", "");
			}
		/*} 
		catch (InterruptedException e) 
		{	
			fl.disp_Message(driver, "", "error occured", "", "", "Y");
			e.printStackTrace();
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		
		
	}
	public String checkTemplateList(WebDriver driver, String template) throws IOException, InterruptedException
	{
		String templates_CS_Xpath=Environment("templates_CS_Xpath");
		String select_CS_Xpath=Environment("select_CS_Xpath");
		String selectOptions_CS_Xpath=Environment("selectOptions_CS_Xpath");
		String Done_CS_Xpath=Environment("Done_CS_Xpath");
		String cancel_CS_Xpath=Environment("cancel_CS_Xpath");
		
		fl.ClickByXpath(driver, templates_CS_Xpath, "", "", "", "", "");
		String status=fl.checkOptionValueInSelect(driver, select_CS_Xpath, selectOptions_CS_Xpath, template);
		if(status.equals("true"))
		{
			fl.selectDropdownByxpath(driver, select_CS_Xpath, select_CS_Xpath, "", "", "Selecting Template", "", "Y");
			/*fl.ClickByXpath(driver, Done_CS_Xpath, "", "", "Click Done to add questions to create survey", "", "");*/
			return "true";
		}
		else
		{
			fl.ClickByXpath(driver, cancel_CS_Xpath, "", "", "close the templates list start creating survey", "", "");
			return "false";
		}
	}
	public void checkSurveyTemplates(WebDriver driver, String template) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_SurveyTemplates_Xpath=Environment("survey_SurveyTemplates_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String compareTemplate_ACS_Xpath=Environment("compareTemplate_ACS_Xpath");
		
		String moreuttons_ACS_Xpath=Environment("moreuttons_ACS_Xpath");
		String edit_ACS_Xpath=Environment("edit_ACS_Xpath");
		String view_ACS_Xpath=Environment("view_ACS_Xpath");
		String delete_ACS_Xpath=Environment("delete_ACS_Xpath");
		
		fl.JS_Element_Find(driver, Survey_Xpath);
		fl.ClickByXpath(driver, Survey_Xpath, "", "", "Go to survey Menu", "", "");
		fl.ClickByXpath(driver, survey_SurveyTemplates_Xpath, "", "", "Click on SurveyTemplates", "", "");
		fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "", "click on Advanced Search", "", "");
		fl.entervalueByXpath(driver, compareTemplate_ACS_Xpath, template, "", "", "Templatename with which u have created survey", "", "");
		
		int temp_exist=0;
		fun_cas.listSize(driver, moreuttons_ACS_Xpath,template,compareTemplate_ACS_Xpath);
		/*if(more>1)
		{
			for(int i=1;i<=more;i++)
			{
				String temp_name = fl.getTextXPATH(driver, compareTemplate_ACS_Xpath+"["+i+"]", "", "", "get text of templatename", "", "");
				if(temp_name.equals(template))
				{
					temp_exist++;
				}
			}
		}
		else
		{
			if(more==1)
			{
				String temp_name = fl.getTextXPATH(driver, compareTemplate_ACS_Xpath+"["+1+"]", "", "", "get text of templatename", "", "");
				if(temp_name.equals(template))
				{
					temp_exist++;
				}
			}
			else
			{
				fl.disp_Message(driver, "", "", "Template you searched with is not existed", "", "");
			}
		}
		if(temp_exist==1)
		{
			fl.disp_Message(driver, "", "", "Template created while creating survey saved succesfully", "", "");
		}
		else
		{
			fl.disp_Message(driver, "", "", "Template you searched with not existed", "", "");
		}*/
	}
	public void listSize(WebDriver driver, String morexpath, String name, String Compare_Xpath) throws InterruptedException
	{
		List<WebElement> mores = driver.findElements(By.xpath(morexpath));
		int size = mores.size();
		int temp_exist=0;
		//return size;
		if(size>1)
		{
			for(int i=1;i<=size;i++)
			{
				String temp_name = fl.getTextXPATH(driver, Compare_Xpath+"["+i+"]", "", "", "get text of templatename", "", "");
				if(temp_name.equals(name))
				{
					temp_exist++;
				}
			}
		}
		else
		{
			if(size==1)
			{
				String temp_name = fl.getTextXPATH(driver, Compare_Xpath+"["+1+"]", "", "", "get text of templatename", "", "");
				if(temp_name.equals(name))
				{
					temp_exist++;
				}
			}
			else
			{
				fl.disp_Message(driver, "", "", "Template you searched with is not existed", "", "");
			}
		}
		if(temp_exist==1)
		{
			fl.disp_Message(driver, "", "", "Template created while creating saved succesfully", "", "");
		}
		else
		{
			fl.disp_Message(driver, "", "", "Template you searched with not existed", "", "");
		}
	}
	public void validatePublishedSurveyData(WebDriver driver, String categeoryname, String GroupName,
			String surveyname, String description, int No_Of_Que) throws IOException, InterruptedException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
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
		
		/*try 
		{*/
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to Survey Menu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			//search with surveyname
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "Search with surveyname", "", "", "", "");
			
			//click on more button
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on more options", "", "", "");
			
			//click on view
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "click on view survey", "", "", "Y");
			
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
			
			//fl.ClickByXpath(driver, BackButton_Xpath, "", "", "", "", "");
			fl.JS_Element_Find(driver, Survey_Xpath);
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go Back to Survey Menu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
		/*} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		
		
	}
	public String[] viewSurvey(WebDriver driver, String surveyname) throws IOException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String SurvayName_InView_Xpath=Environment("SurvayName_InView_Xpath");
		String TotalQue_InView_Xpath=Environment("TotalQue_InView_Xpath");
		String Pages_InView_Xpath=Environment("Pages_InView_Xpath");
		String Que_Tpe_InView_Xpath=Environment("Que_Tpe_InView_Xpath");
		
		try 
		{
			fl.JS_Element_Find(driver, Survey_Xpath);
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click On Advanced Search", "", "", "");
			
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
	public String[] viewQuiz(WebDriver driver, String quizname) throws IOException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String ViewonGrid_Quiz_Xpath=Environment("ViewonGrid_Quiz_Xpath");
		String quizType_QuizView_Xpath=Environment("quizType_QuizView_Xpath");
		String quizName_QuizView_Xpath=Environment("quizName_QuizView_Xpath");
		String weightage_QuizView_Xpath=Environment("weightage_QuizView_Xpath");
		String totalQue_QuizView_Xpath=Environment("totalQue_QuizView_Xpath");
		
		try 
		{
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, Quiz_Xpath, "", "View Quiz and Get the Total Questions", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "", "", "", "");
			String quizType = fl.getTextXPATH(driver, quizType_QuizView_Xpath, "", "", "", "", "");
			String quizName = fl.getTextXPATH(driver, quizName_QuizView_Xpath, "", "", "", "", "");
			String weightage = fl.getTextXPATH(driver, weightage_QuizView_Xpath, "", "", "", "", "");
			String totalQue = fl.getTextXPATH(driver, totalQue_QuizView_Xpath, "", "", "", "", "");
			return new String[]{ quizType , quizName , weightage , totalQue};
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public void quiz_fillAnswer(WebDriver driver)
	{
		
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
		
		//fl.invokeApplication(driver, "http://localhost:4034/Survey/Index/5pCJBNLMSls6u3NVbM2flA==/9JveYxxqcT1MkxEU9Y1iBZBQhKSsmwF37JHEzZk1zEc=", "", "", "", "", "", "");
			
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
	public String viewQuiz_QueType(WebDriver driver, int queNo) throws IOException
	{ 
		//frontend from end user validation
		
		String Que_Quiz_Xpath=Environment("Que_Quiz_Xpath");
		
		String checkbox_Quiz_Xpath=Environment("checkbox_Quiz_Xpath");
		String dropdown_Quiz_Xpath=Environment("dropdown_Quiz_Xpath");
		String radioOptions_Quiz_Xpath=Environment("radioOptions_Quiz_Xpath");
		String radioYes_Quiz_Xpath=Environment("radioYes_Quiz_Xpath");
		String rate_Quiz_Xpath=Environment("rate_Quiz_Xpath");
		String date_Quiz_Xpath=Environment("date_Quiz_Xpath");
		String file_Quiz_Xpath=Environment("file_Quiz_Xpath");
		String number_Quiz_Xpath=Environment("number_Quiz_Xpath");
		String singleText_Quiz_Xpath=Environment("singleText_Quiz_Xpath");
		String multiText_Quiz_Xpath=Environment("multiText_Quiz_Xpath");
		
				//List<WebElement> no_que=driver.findElements(By.xpath(No_Que_Xpath));
			
				fl.JS_Element_Find(driver, Que_Quiz_Xpath+"["+queNo+"]");
			
				try
				{	
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+dropdown_Quiz_Xpath).isEnabled())
					{
						return "Dropdown";
					}
					//String drop=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+dropdown_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+dropdown_Quiz_Xpath,"").equals("true"))
					{
						return "Dropdown";
					}
				}
				catch(Exception e)
				{
				
				}
					
			
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+checkbox_Quiz_Xpath).isEnabled())
					{
						return "Check Box";
					}
					//String check=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+checkbox_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+checkbox_Quiz_Xpath,"").equals("true"))
					{
						return "Check Box";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioOptions_Quiz_Xpath).isEnabled())
					{
						return "Radio Button";				
					}
					//String radio=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioOptions_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioOptions_Quiz_Xpath,"").equals("true"))
					{
						return "Radio Button";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioYes_Quiz_Xpath).isEnabled())
					{
						return "Yes or No";
					}
					//String yesNo=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioYes_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+radioYes_Quiz_Xpath,"").equals("true"))
					{
						return "Yes or No";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+rate_Quiz_Xpath).isEnabled())
					{
						return "Scale / Rate";
					}
					//String scale=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+rate_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+rate_Quiz_Xpath,"").equals("true"))
					{
						return "Scale / Rate";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+date_Quiz_Xpath).isEnabled())
					{
						return "Date";
					}
					//String date=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+date_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+date_Quiz_Xpath,"").equals("true"))
					{
						return "Date";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+file_Quiz_Xpath).isEnabled())
					{
						return "File Upload";
					}
					
					//String file=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+file_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+file_Quiz_Xpath,"").equals("true"))
					{
						return "File Upload";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+number_Quiz_Xpath).isEnabled())
					{
						return "Number";
					}
					//String num=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+number_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+number_Quiz_Xpath,"").equals("true"))
					{
						return "Number";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+singleText_Quiz_Xpath).isEnabled())
					{
						return "Single Line Text";
					}
					//String singlTxt=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+singleText_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+singleText_Quiz_Xpath,"").equals("true"))
					{
						return "Single Line Text";
					}
				}
				catch(Exception e)
				{
					
				}
				try
				{
					if(fl.findByXpath(driver, Que_Quiz_Xpath+"["+queNo+"]"+multiText_Quiz_Xpath).isEnabled())
					{
						return "Text Area";
					}
					//String textArea=fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+multiText_Quiz_Xpath);
					if(fl.elementDisplayed(driver, Que_Quiz_Xpath+"["+queNo+"]"+multiText_Quiz_Xpath,"").equals("true"))
					{
						return "Text Area";
					}
				}
				catch(Exception e)
				{
					
				}
			
			
			
		
		return null;
	}
	public String viewQuiz_GetQueText(WebDriver driver, int queNo) throws IOException
	{
		String Que_Quiz_Xpath=Environment("Que_Quiz_Xpath");
		String QueTotalText_Quiz_Xpath=Environment("QueTotalText_Quiz_Xpath");
		String QueNo_Remove_Quiz_Xpath=Environment("QueNo_Remove_Quiz_Xpath");
		String extraText ="\n";
		
		fl.JS_Element_Find(driver, Que_Quiz_Xpath+"["+queNo+"]");
		String totque = fl.getTextXPATH(driver, Que_Quiz_Xpath+"["+queNo+"]"+QueTotalText_Quiz_Xpath, "", "", "", "", "");
		//System.out.println(totque);
		String wasteText = fl.getTextXPATH(driver, Que_Quiz_Xpath+"["+queNo+"]"+QueNo_Remove_Quiz_Xpath, "", "", "", "", "");
		//System.out.println(wasteText);
		String removeText=wasteText+extraText;
		
		String question = totque.replace(removeText, "");
		//System.out.println("Question text in quiz view is "+question);
		
		return question;
	}
	public String viewSurvey_GetQueText(WebDriver driver, int queNo) throws IOException
	{
		String queText_CSV_Xpath=Environment("queText_CSV_Xpath");
		String QueTotalText_Quiz_Xpath=Environment("QueTotalText_Quiz_Xpath");
		String QueNo_Remove_Quiz_Xpath=Environment("QueNo_Remove_Quiz_Xpath");
		String extraText ="\n";
		
		fl.JS_Element_Find(driver, queText_CSV_Xpath+"["+queNo+"]");
		String question = fl.getTextXPATH(driver, queText_CSV_Xpath+"["+queNo+"]", "", "", "", "", "");
		
		
		return question;
	}
	public String updateSurveyName(WebDriver driver,String SearchWithSurveyName,String UpdatedSurveyCategeoryName, String UpdatedSurveyCategeoryNotes,String UpdatedSurveyGroupName
			, String UpdatedSurveyGroupNotes, String UpdatedSurveyGroupSubNotes, String UpdatedSurveyName, String UpdatedDescription,
			String AddQue) throws IOException, InterruptedException
	{
		Functional_Cases_propread func_case = new Functional_Cases_propread();
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
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
		
		
		/*try 
		{*/
			String NoSurveyFound = null;
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to SurveyMenu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Go to Surveys", "", "", "");
			
			if(SearchWithSurveyName!="")
			{
				//need to filter
				Thread.sleep(3000);
				fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advance Search", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "search with surveyname", "", "", "", "");
				String Nosurvey=fl.getTextXPATH(driver, NoSurveyFoundXpath, "", "checking the survey existed or not", "", "", "");
				System.out.println(Nosurvey);
				if(Nosurvey.contains("No "))
					
				//if(fl.elementDisplayed(driver, NoSurveyFoundXpath).equals("true"))
				{
					NoSurveyFound = fl.getTextXPATH(driver, NoSurveyFoundXpath, "", "No survey is existed with that survey name", "", "", "");
					
					System.out.println("Survey you searched with:"+SearchWithSurveyName+" "+NoSurveyFound);
					
					return "false";
				}
				
				else
				{
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More Options", "", "", "");
			
						fl.ClickByXpath(driver, EditonGrid_Xpath , "", "click on Edit", "", "", "Y");
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
								fl.disp_Message(driver, "", "Need to create SurveyCategory", "", "", "");
				
								func_case.create_survey_Categeory(driver, UpdatedSurveyCategeoryName, "", UpdatedSurveyCategeoryNotes);
						
								fl.ClickByXpath(driver, Survey_Xpath, "", "click on Survey", "", "", "");
								
								fl.ClickByXpath(driver, createSurvey_Xpath, "", "Click on Create Survey", "", "", "");
				
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
								fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
					
								fl.ClickByXpath(driver, createSurvey_Xpath, "", "Click On create Survey", "", "", "");
							}
				
						}
						//start filling survey with updated details=========================================
						/*fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
					fl.ClickByXpath(driver, createSurvey_Xpath, "", "", "", "", "");*/
						fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
						
						fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
						
						fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click On Advanced Search", "", "", "");
						
						fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "search with survey name", "", "", "", "");
						
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on more options", "", "", "");
			
						fl.ClickByXpath(driver, EditonGrid_Xpath , "", "click on edit grid", "", "", "");
						
				
						if(UpdatedSurveyCategeoryName!="")
						{
							fl.selectDropdownByxpath(driver, SurveyCategeory_Xpath, UpdatedSurveyCategeoryName, "", "updating categeoryname", "CatgeoryName Selected", "", "Y");
						}
						if(UpdatedSurveyGroupName!="")
						{
							fl.selectDropdownByxpath(driver, SurveyGroupName_Xpath, UpdatedSurveyGroupName, "", "updating Group", "GroupName Selected", "", "Y");
						}
						/*if(UpdatedSurveyName!="")
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
						}*/
			
						if(UpdatedDescription!="")
						{
							/*fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");
							fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "", "", "", "", "");*/
							fl.entervalueByXpath(driver, Survey_Description_Xpath, "", "clearing Description", "", "", "", "");
				
							Thread.sleep(1000);
				
							select_delete sel_del = new select_delete();
				
				
				
							//fl.clear_textfield(driver, Survey_Description_Xpath);
				
							fl.entervalueByXpath(driver, Survey_Description_Xpath, UpdatedDescription ,"entering updated description", "", "", "", "");
			
			
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
			
						fl.ClickByXpath(driver, updatecreateSurveyButton_Xpath, "", "click on Update", "", "", "");
						if(AddQue!="")
						{
							fl.ClickByXpath(driver, Survey_Xpath, "", "Click on SurveyMenu", "", "", "");
							
							fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
							
							fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
							
							fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "Search with SurveyName", "", "", "", "");
							
							fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
							fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "Click on more Options", "", "", "");
				
							fl.ClickByXpath(driver, EditonGrid_Xpath , "", "Click on Edit", "check the fields which are updated(categeoryName, GroupName,Description)", "", "Y");
						}
						
						return "true";
						}
					}
					
					
						
					
				
			
			/*}
			
		
		catch (Exception e) 
		{
			e.printStackTrace();
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		return null;
		
	}
	public void publish_SavedQuiz(WebDriver driver, String quizname) throws IOException, InterruptedException
	{
		String publish_Quiz_Xpath=Environment("publish_Quiz_Xpath");
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String row_quizList_Xpath=Environment("row_quizList_Xpath");
		String name_QuizList_Xpath=Environment("name_QuizList_Xpath");
		String status_QuizList_Xpath=Environment("status_QuizList_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, publish_Quiz_Xpath, "", "Publish Saved Quiz", "", "", "");
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, Quiz_Xpath, "", "after publish check whether it is published or not, go to Quiz menu", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "Click on Quizzes", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "Click on Advanced Search", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "searching with a quizname", "", "", "", "");
			
			List<WebElement> rows = driver.findElements(By.xpath(row_quizList_Xpath));
			int row = rows.size();
			if(row>1)
			{
				for(int i=1;i<=row;i++)
				{
					String name = fl.getTextXPATH(driver, row_quizList_Xpath+"["+i+"]"+name_QuizList_Xpath, "", "", "", "", "");
					if(name.equals(quizname))
					{
						String status = fl.getTextXPATH(driver, row_quizList_Xpath+"["+i+"]"+status_QuizList_Xpath, "status of the quiz published", "", "", "", "Y");
						if(status.contains("Open"))
						{
							fl.disp_Message(driver, "", "Published successfully, Status is open", "", "", "");
							Logs_DigiSurvey.info("Quiz Published Successfully, Status is open");
						}
						else
						{
							fl.disp_Message(driver, "", "failed quiz publishing", "", "", "Y");
							Logs_DigiSurvey.info("failed quiz publishing");
						}
					}
					else
					{
						fl.disp_Message(driver, "", "failed quiz publishing", "", "", "Y");
						Logs_DigiSurvey.info("failed quiz publishing");
					}
				}
			}
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "failed quiz publishing", "", "", "Y");
			Logs_DigiSurvey.info("failed quiz publishing");
			e.printStackTrace();
		}
	}
	public void after_surveyCreationClick_Publish(WebDriver driver) throws IOException
	{
		String PublishCreateSurevyAsTemplate_Xpath= Environment("PublishCreateSurevyAsTemplate_Xpath");;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-1000)", "");
	//avoiding save			
			/*fl.ClickByXpath(driver, SaveCreateSurevyAsTemplate_Xpath, "", "", "", "", "");*/
			
			fl.ClickByXpath(driver, PublishCreateSurevyAsTemplate_Xpath, "", "publish the quiz", "", "", "Y");
			
			if(fl.findByXpath(driver, Environment("SuccessSave_Xpath")).getText().contains("Success"))
			{
				System.out.println(fl.findByXpath(driver, Environment("SuccessSave_Xpath")).getText());
			}
			else
			{
				System.out.println("MAndidatory fields should be filled");
			}
			Logs_DigiSurvey.info("Publish  quiz ");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public void AddQuestionto_CreateSurvey(WebDriver driver, String QuestionText, String QuestionTag, String AnswerType,
			String NoofOptions_OR_Text, int Question_No, String option1, String option2, String option3, String option4, 
			String option5, String option6, String option7, String option8, String option9, String option10) throws IOException, InterruptedException
	{
		/*QuestionText="Q1";
		QuestionTag="Q_Tag1";
		AnswerType="Check Box";
		NoofOptions_OR_Text="2";*/
		
		String QuestionText_Xpath= Environment("QuestionText_Xpath");
		String QuestionUpdateText_Xpath=Environment("QuestionUpdateText_Xpath");
		//String QueTestInEdit_Xpath=Environment("QueTestInEdit_Xpath");Incase of edit, but its not needed above Xpath is handling that
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
		String SurveyPreviewName_Xpath=Environment("SurveyPreviewName_Xpath");
		//String Que_Prev_Xpath=Question_Xpath+"["+i+"]";
		//String Q1=Question_Xpath+"[1]";
		
		
		/*try 
		{*/
			System.out.println("function executing : queno is "+Question_No);
			
			//before going to add question to update survey , check the que already existed or noT
			
			fl.entervalueByXpath(driver, QuestionText_Xpath, QuestionText, "Question"+Question_No, "", "", "", "");
			
			Thread.sleep(1000);
			//check the que preview
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			
			/*if(fl.findByXpath(driver, Question_Xpath+"["+Question_No+"]").getText().equals(QuestionText))
			{
				System.out.println("question preview matches the text you entered in que field");
			}*/
			//jse.executeScript("window.scrollBy(0,1000)", "");
			fl.entervalueByXpath(driver, QuestionTag_Xpath, QuestionTag, "Question Tag value entered", "", "", "", "");
			
			String Check_AnswerType=fl.checkOptionValueInSelect(driver, selectTag_AnswerType_Xpath, selectTag_AnswerTypeOptions_Xpath, AnswerType);
			if(Check_AnswerType.equals("true"))
			{
				fl.selectDropdownByxpath(driver, selectTag_AnswerType_Xpath, AnswerType , "answer type selected", "", "", "", "Y");
			}
			else
			{
				Assert.fail("AnswerType You have given in Excel Not Matched with Any one of the given");
				fl.disp_Message(driver, "", "Answer type you mentioned is not matched with any of options", "", "", "");
			}
			System.out.println("Selected no of options");
			
			if(fl.elementDisplayed(driver, EnabledNumberOfOptioins_Xpath,"").equals("true"))
				{
					if(fl.findByXpath(driver,EnabledNumberOfOptioins_Xpath ).getText().contains("Number"))
					{
						System.out.println("Number value option enabled");
						fl.JS_Element_Find(driver, selectTag_NumberOfOptionsSelect_Xpath);
						String Check_options=fl.checkOptionValueInSelect(driver, selectTag_NumberOfOptionsSelect_Xpath, selectTag_NumberOfOptionsSelectOptions_Xpath, NoofOptions_OR_Text);
						if(Check_options.equals("true"))
						{
							fl.JS_Element_Find(driver, selectTag_NumberOfOptionsSelect_Xpath);
					
							fl.selectDropdownByxpath(driver, selectTag_NumberOfOptionsSelect_Xpath, NoofOptions_OR_Text, "selecting number of options:"+NoofOptions_OR_Text, "", "", "", "");
							if(NoofOptions_OR_Text!="")
							{
								System.out.println("options are available"+NoofOptions_OR_Text);
								if(NoofOptions_OR_Text.equals("2"))
								{
									System.out.println("option values to be entered");
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("3"))
								{
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("4"))
								{
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("5"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("6"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "option6 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("7"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
							
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
									
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "option6 value is entered", "", "", "", "");
							
									fl.JS_Element_Find(driver, NumberofOPtions_7_Text_Xpath);
									
									fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "option7 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("8"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
							
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
									
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "option6 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_7_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "option7 value is entered", "", "", "", "");
									
									fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "option8 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("9"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "option6 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_7_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "option7 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "option8 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_9_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_9_Text_Xpath, option9, "option9 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
								if(NoofOptions_OR_Text.equals("10"))
								{
						
									fl.entervalueByXpath(driver, NumberofOPtions_1_Text_Xpath, option1, "option1 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_2_Text_Xpath, option2, "option2 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_3_Text_Xpath, option3, "option3 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_4_Text_Xpath, option4, "option4 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_5_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_5_Text_Xpath, option5, "option5 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_6_Text_Xpath, option6, "option6 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_7_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_7_Text_Xpath, option7, "option7 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_8_Text_Xpath, option8, "option8 value is entered", "", "", "", "");
									
									fl.JS_Element_Find(driver, NumberofOPtions_9_Text_Xpath);
							
									fl.entervalueByXpath(driver, NumberofOPtions_9_Text_Xpath, option9, "option9 value is entered", "", "", "", "");
							
									fl.entervalueByXpath(driver, NumberofOPtions_10_Text_Xpath, option10, "option10 value is entered", "", "", "", "");
									
									jse.executeScript("window.scrollBy(0,500)", "");
						
								}
					
							}
							else
							{
								System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
								fl.disp_Message(driver, "", "NumberOf OPtions, You have given in Excel Not Matched with Any one of the given", "", "", "Y");
							}
						}
					}
				}
			
			if(fl.elementDisplayed(driver, EnabledEnterLabelText_Xpath,"").equals("true"))
				{
					if(fl.findByXpath(driver, EnabledEnterLabelText_Xpath).getText().contains("Text"))
					{
						fl.entervalueByXpath(driver, EnterLabelText_Xapth, "", "Entering Label Text is Optional", "", "", "", "");
					}
					else
					{
						fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
						System.out.println("Not Found");
					}
				}
			fl.JS_Element_Find(driver, AddQuestion_Xpath);
			fl.ClickByXpath(driver, AddQuestion_Xpath, "", "click on Add question", "", "", "");
				/*JavascriptExecutor js = (JavascriptExecutor)driver;
				//WebElement scroll = driver.findElement(By.xpath(internalScrollbar_Xpath));//*[@id='mCSB_2_dragger_vertical']/div
				WebElement que=driver.findElement(By.xpath(QuestionTextPreview_Xpath+"["+Question_No+"]"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView();", que);*/
				//fl.ClickByXpath(driver, QuestionTextPreview_Xpath+"["+1+"]", "", "", "", "", ""); 
			fl.ClickByXpath(driver, SurveyPreviewName_Xpath, "", "", "", "", "");
				fl.JS_Element_Find(driver, QuestionTextPreview_Xpath+"["+Question_No+"]");
				
			/*}*/
			
			Assert.assertTrue(fl.findByXpath(driver, QuestionTextPreview_Xpath+"["+Question_No+"]").getText().equals(QuestionText),
					"question preview not matches the text you entered in que field");
			
		/*} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		
	}
	/*public void AddQuestionto_UpdateSurvey(WebDriver driver, String QuestionText, String QuestionTag, String AnswerType,
			String NoofOptions_OR_Text, int Question_No, String option1, String option2, String option3, String option4, 
			String option5, String option6, String option7, String option8, String option9, String option10) throws IOException
	{
		QuestionText="Q1";
		QuestionTag="Q_Tag1";
		AnswerType="Check Box";
		NoofOptions_OR_Text="2";
		
		
		String QuestionUpdateText_Xpath=Environment("QuestionUpdateText_Xpath");
		//String QueTestInEdit_Xpath=Environment("QueTestInEdit_Xpath");Incase of edit, but its not needed above Xpath is handling that
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
			
			//before going to add question to update survey , check the que already existed or noT
			
			fl.entervalueByXpath(driver, QuestionUpdateText_Xpath, QuestionText, "", "", "", "", "");
			
			Thread.sleep(1000);
			//check the que preview
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			
			if(fl.findByXpath(driver, Question_Xpath+"["+Question_No+"]").getText().equals(QuestionText))
			{
				System.out.println("question preview matches the text you entered in que field");
			}
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
			
			if(fl.elementDisplayed(driver, EnabledNumberOfOptioins_Xpath,"").equals("true"))
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
			
			if(fl.elementDisplayed(driver, EnabledEnterLabelText_Xpath,"").equals("true"))
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
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}*/
	public int createdque(WebDriver driver) throws IOException
	{
		String NoofCreatedQue=Environment("NoofCreatedQue");
		  List<WebElement> createQue_no = driver.findElements(By.xpath(NoofCreatedQue));
		  int no_createdQ=createQue_no.size();
		  return no_createdQ;
	}
	public void update_SurveyAftrAdingQue(WebDriver driver) throws IOException, InterruptedException
	{
		String updatecreateSurveyButton_Xpath=Environment("updatecreateSurveyButton_Xpath");
		String Survey_Xpath=Environment("Survey_Xpath");
		
		String update=fl.JS_Element_Find(driver, updatecreateSurveyButton_Xpath);
		System.out.println("To update its scrolling up");
		if(update.equals("true"))
		{
			/*try 
			{*/
				fl.ClickByXpath(driver, updatecreateSurveyButton_Xpath, "", "", "Clicked Update Button", "", "");
			/*} 
			catch (InterruptedException e) 
			{
				fl.disp_Message(driver, "", "error Occured", "", "", "Y");
				e.printStackTrace();
				Logs_DigiSurvey.info(e.getMessage());
			}*/
		}
		//String Survey = fl.JS_Element_Find(driver, Survey_Xpath);
		
		/*try
		{
			fl.ClickByXpath(driver, updatecreateSurveyButton_Xpath, "", "", "", "", "");
		}
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}*/
		
	}
	public void save_CreatedSurvey(WebDriver driver) throws IOException, InterruptedException
	{
		String SaveCreateSurevyAsTemplate_Xpath=Environment("SaveCreateSurevyAsTemplate_Xpath");
		try 
		{
			fl.ClickByXpath(driver, SaveCreateSurevyAsTemplate_Xpath, "", "Save The created Survey", "", "", "Y");
		}
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured While Saving Created Survey", "", "", "Y");
			e.printStackTrace();
			Logs_DigiSurvey.info(e.getMessage());
		}
	}
	public void publishSavedSurveyQue(WebDriver driver, String SurveyName) throws IOException, InterruptedException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
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
			
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to SurveyMenu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "click on Advanced Search", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "search with survey name", "", "", "", "");
			
			List<WebElement> surveys = driver.findElements(By.xpath(No_ofsurveyDisplayed));
			int no_ofsurveys = surveys.size();
			System.out.println(no_ofsurveys+" no of surveys displayed");
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
						fl.ClickByXpath(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]", "", "Click on more options", "", "", "");
						//jse.executeScript("window.scrollBy(0,700)", "");
						fl.JS_Element_Find(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]");
					}
				}
			}
			if(no_ofsurveys==1)
			{
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More Options", "", "", "");
			}
			if(no_ofsurveys>=1)
			{
				Thread.sleep(1000);
			
				fl.JS_Element_Find(driver, publishonGrid_Xpath);
				
				fl.ClickByXpath(driver, publishonGrid_Xpath, "", "Publish on Grid", "", "", "Y");
			
				fl.ClickByXpath(driver, Proceed_Xpath, "", "Proceed publishing", "", "", "");
			
				if(fl.findByXpath(driver, success_Publish_Xpath).getText().contains("Success"))
				{
					System.out.println(fl.findByXpath(driver, success_Publish_Xpath).getText());
				}
			}
			
			
			
		} 
		catch (Exception e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void share_survey(WebDriver driver,String SurveyName, String EmailIDs, int noOfshares) throws IOException, InterruptedException
	{
		String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String shareonGrid_Xpath=Environment("shareonGrid_Xpath");
		String EmailId_Xpath=Environment("EmailId_Xpath");
		String sendSurvey_Xpath=Environment("sendSurvey_Xpath");
		String getLink_Xpath=Environment("getLink_Xpath");
		
		String WebLinks_Survey_Xpath=Environment("WebLinks_Survey_Xpath");
		String Link_Survey_Xpath=Environment("Link_Survey_Xpath");
		/*try
		{*/
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			fl.JS_Element_Find(driver, Survey_Xpath);
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click On Advanced Search", "", "", "");

			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "Search With survey Name", "", "", "", "");
			
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More options", "", "", "");
			
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
			fl.ClickByXpath(driver, viewonGrid_Xapth, "", "click on View Option", "", "", "");
			
			fl.ClickByXpath(driver, WebLinks_Survey_Xpath, "", "Go to Weblinks Tab", "", "", "");
			
			fl.ClickByXpath(driver, Link_Survey_Xpath, "", "", "Get The surveyLink and saved in SurveyLinks folder", "", "");
			
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

			
			
			
		/*} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}*/
		
		//return null;
	}
	/*Incase of gmail
	public void enduser_Login(WebDriver driver, String URL, String name, String password, String searchtext, int enduser_no) throws IOException*/
	public void enduser_Login(WebDriver driver, String URL, String EmailID, String password) throws IOException, InterruptedException
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
		
		/*try
		{*/
			//child
			fl.invokeApplication(driver, Company_Baseurl, "", "invoke "+Company_Baseurl, "", "", "", "");

			//fl.ClickByXpath(driver, Digi_CompanyLoginxpath, "", "", "", "", "");

			fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, "Enter EmailID", "", "", "", "");

			fl.entervalueByXpath(driver, Company_Passwordxpath, password, "Enter Password", "", "", "", "");

			Thread.sleep(3000);
			// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
			// "", "", "", "");
			fl.ClickByXpath(driver, Company_LoginButtonxpath, "", "Click on Login Button", "", "", "");
			
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
		/*}
		catch(Exception e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		
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
	public void answer_basedonQueType(WebDriver driver, int que_no, String que_type) throws IOException, InterruptedException
	{
		//String que_type=null;
		  Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		  String Survey_Answers=Environment("Sheet_Survey_Answers"); 
		  int Survey_Answers_row=RC.getLastrowno(Survey_Answers); 
		  int Survey_Answers_col=RC.getLastcolmno(Survey_Answers); 
		  String[] Survey_Answers_ele=new String[Survey_Answers_col]; 
		  String[] temp=null;
		  for (int Survey_Answers_Index = 1; Survey_Answers_Index < RC.getLastrowno(Survey_Answers); Survey_Answers_Index++) 
		  { 
			  System.out.println("for Loop" );
			  System.out.println(que_type);
			  System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"), Survey_Answers));
			  if(que_type.equals(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)) )
			  {
				  // Adduser contains company email_id at 1st column  for validation
			  	 
			  		System.out.println("Matches ID to Register");
			  		System.out.println(RC.getStringCellData(Survey_Answers_Index, RC.Current_Coulumn_Number(Survey_Answers, "QueAnsID"),Survey_Answers)); 
			  		//based on j value get the row data and do Adding Users
				   
			  		for(int Survey_Answers_Ind=0;Survey_Answers_Ind<Survey_Answers_col;Survey_Answers_Ind++) 
			  		{
			  			Survey_Answers_ele[Survey_Answers_Ind]=RC.getStringCellData(Survey_Answers_Index, Survey_Answers_Ind, Survey_Answers);
			  			System.out.println("Answer based on Que Type"+Survey_Answers_ele[Survey_Answers_Ind]);
			  		}
			  	
			  }
		  }
		Functional_Cases_propread fun_case = new Functional_Cases_propread();
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
		
		/*if(que_no>=5)
		{
			if(fl.elementDisplayed(driver, Que_Xpath+"["+que_no+"]","").equals("true"))
			{
				WebElement que_enable = driver.findElement(By.xpath(Que_Xpath+"["+que_no+"]"));
				jse.executeScript("arguments[0].scrollIntoView();", que_enable);
			}
		}*/
		if(que_no==1)
			fl.ClickByXpath(driver, Que_Xpath+"[1]", "", "", "click on question Page", "", "");
		else
		{
			int qu_no=que_no-1;
			fl.ClickByXpath(driver, Que_Xpath+"["+qu_no+"]", "", "", "click on question Page", "", "");
		}
		fl.JS_Element_Find(driver, Que_Xpath+"["+que_no+"]");
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
									  if(fl.elementDisplayed(driver, Que_Xpath+"["+que_no+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth,"").equals("true"))
									  {
										  //getting 1st option value from survey link
										  responseoptions=driver.findElement(By.xpath(Que_Xpath+"["+que_no+"]"+checkboxtext_Xapth+"["+i+"]"+checkboxtextspan_Xapth)).getText();
										  for(int j=0;j<10;j++)
										  {
							
											  if(responseoptions.equals(options[j]))//compare 1st option from survey link with each option from ans sheet
											  {
												  //answer write to excel sheet,along with queno
												  
												  
												  //matches get the column number from excel, now click on the respected i value
												  fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+chk1_Xpath+"["+i+"]"+chk2_Xpath, "", "Answering checkbox:"+i, "", "", "");
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
								  fun_case.saveSurvey(driver);
								  	/*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
								  	{
								  		
								  		WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Checkbox Ans checked", "", "Y");
								  	}*/
								  	
								  	/*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
								  		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
								  	break;
				
				case "Date": System.out.println("Date to be enetered");
				
							 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+date_Xpath, "08/21/1991", "", "", "", "", "");
//================							 
							 fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+date_Xpath,
									 Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Date")], "", "", "", "", "");
//================				
							 fun_case.saveSurvey(driver);
							/* if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
							 {
								 WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
							  		Thread.sleep(3000);
							  		jse.executeScript("arguments[0].scrollIntoView();", save);
							  		
							  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Date Ans Entered", "", "Y");
							 }*/
							 
							 /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
								 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
							 break;
									
				case "File Upload":System.out.println("file to be uploaded");
				
								   //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+File_Xpath, "", "", "", "", "");
								   //UR.uploadFile("D:\\Sailaja\\ScreenshotOuputConsole\\10Company_Creation.png");
//================					
								   fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+File_Xpath, "", "", "", "", "");
								   UR.uploadFile(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "File Upload")]);
//================									   
								   fun_case.saveSurvey(driver);
								   /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
								   {
									   WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "File to be uploaded", "", "Y");
								   }*/
								   /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
										   fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
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
								fun_case.saveSurvey(driver);
							    /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
							    {
							    	WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
							  		Thread.sleep(3000);
							  		jse.executeScript("arguments[0].scrollIntoView();", save);
							  		
							  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Dropdown To be selected", "", "Y");
							    }*/
							    /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
							    		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
								break;
									
				case "Number":System.out.println("Number to be clicked");
				
							  //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+Phone_Xpath, "9533676851", "", "", "", "", "");
//================					
							  fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+Phone_Xpath, 
									  Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Number")] , "", "", "", "", "");
//================	
							  fun_case.saveSurvey(driver);
							  /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
							  {
								  WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
							  		Thread.sleep(3000);
							  		jse.executeScript("arguments[0].scrollIntoView();", save);
							  		
							  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Number To be entered", "", "Y");
							  }*/
							  /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
									  fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
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
									fun_case.saveSurvey(driver);
								    /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
								    {
								    	WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "RadioButton to be clicked", "", "Y");
								    }*/
								    /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
								    		fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
									break;
									
				case "Scale / Rate":System.out.println("rating to be entered");
				
									//fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+rate_Xpath, "", "", "", "", "");
//=====================				
									int rate = Integer.parseInt(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Scale / Rate")]);
									fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+ratedynamic_Xpath+"["+rate+"]", "", "", "", "", "");
//=====================					
									fun_case.saveSurvey(driver);
									/*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
									{
										WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Scale/Rate To be selected", "", "Y");
									}*/
									/*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
											fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
									break;
									
				case "Single Line Text":System.out.println("single line text to be eneterd");
				
										//fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+feedback_Xpath, "Single line comment", "", "", "", "", "");
//==================										
										fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+feedback_Xpath, 
												Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Single Line Text")], "", "", "", "", "");
//====================				
										fun_case.saveSurvey(driver);
										/*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
										{
											WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
									  		Thread.sleep(3000);
									  		jse.executeScript("arguments[0].scrollIntoView();", save);
									  		
									  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Single line text to be entered", "", "Y");
										}*/
										/*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
												fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
										break;
									
				case "Text Area":System.out.println("text area to be entered");
				
								 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+brief_Xpath, "Multi line comment", "", "", "", "", "");
//======================								 
								 fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+brief_Xpath, 
										 Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "TextArea")], "", "", "", "", "");
//======================			
								 fun_case.saveSurvey(driver);
								 /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
								 {
									 WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "MultiLine Ans to be entered in textarea", "", "Y");
								 }*/
								  /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
										 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
								 break;
									
				case "Yes or No":System.out.println("Y/N to be selected");
				
								 //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioYesorNo_Xpath, "", "", "", "", "");
//=========================								 
								 if(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")].equals("Yes"))
								 {
									 fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioYesorNo_Xpath, "", "", "Yes to be selected", "", "Y");
								 }
								 else
								 {
									 if(Survey_Answers_ele[RC.Current_Coulumn_Number(Survey_Answers, "Yes or No")].equals("No"))
									 {
										 fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioNo_Xpath, "", "", "No to be selected", "", "Y");
									 }
								 }
//===========================		
								 fun_case.saveSurvey(driver);
								 /*if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
								 {
									 WebElement save = driver.findElement(By.xpath(Responsesave_Xpath));
								  		Thread.sleep(3000);
								  		jse.executeScript("arguments[0].scrollIntoView();", save);
								  		
								  		fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "", "", "");
								 }*/
								 /*if(fl.elementDisplayed(driver, belowResponsesave_Xpath).equals("true"))
										 fl.ClickByXpath(driver, belowResponsesave_Xpath, "", "", "", "", "");*/
								 break;
				
				
			
				}
			
		
	}
	public void saveSurvey(WebDriver driver) throws IOException, InterruptedException
	{
		String Responsesave_Xpath=Environment("Responsesave_Xpath");
		if(fl.elementDisplayed(driver, Responsesave_Xpath,"").equals("true"))
	  	{
	  		try 
	  		{
	  			fl.JS_Element_Find(driver, Responsesave_Xpath);
				fl.ClickByXpath(driver, Responsesave_Xpath, "", "", "Checkbox Ans checked", "", "Y");
			} 
	  		catch (InterruptedException e)
	  		{
	  			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
				e.printStackTrace();
				Logs_DigiSurvey.info(e.getMessage());
			}
	  	}
	}
	public void saveSurveySubmit(WebDriver driver, int totalque) throws IOException, InterruptedException
	{
		String ResponseSubmit_Xpath=Environment("ResponseSubmit_Xpath");
		String belowResponseSubmit_Xpath=Environment("belowResponseSubmit_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		/*try 
		{*/
			if(totalque<=3)
			{
				if(fl.elementDisplayed(driver, ResponseSubmit_Xpath,"").equals("true"))
				{
					WebElement submit_enable = driver.findElement(By.xpath(ResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					fl.ClickByXpath(driver, ResponseSubmit_Xpath, "", "", "Submit Button to be clicked", "", "");
					System.out.println("submit button clicked by enduser");
				}
				else
					fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			}
			
			
			if(totalque>3)
			{
				if(fl.elementDisplayed(driver, belowResponseSubmit_Xpath,"").equals("true"))
				{
					WebElement submit_enable = driver.findElement(By.xpath(belowResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					fl.ClickByXpath(driver, belowResponseSubmit_Xpath, "", "", "Submit Button to be clicked", "", "");
					System.out.println("submit button clicked by enduser");
				}
				else
					fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			}
			
			
			//call robot method to close extra open window
			//closeopenedtab_robo closetab= new closeopenedtab_robo();
			
			//driver.switchTo().window(oldTab);
			System.out.println("Response Submitted by user");
		/*} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}*/
	}
	public void CompanyLogout(WebDriver driver) throws IOException, InterruptedException
	{
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String CompanyLogout_Xpath=Environment("CompanyLogout_Xpath");
		String CompanyLogout5_Xpath=Environment("CompanyLogout5_Xpath");
		String CompanyLogout4_Xpath=Environment("CompanyLogout4_Xpath");
		String companyLogoutMenu_Xpath=Environment("companyLogoutMenu_Xpath");
		
		/*try 
		{*/
			fl.JS_Element_Find(driver, logout_img_Xpath);
			fl.ClickByXpath(driver, logout_img_Xpath, "", "Click on Logout Image", "", "", "");
			List<WebElement> logout = driver.findElements(By.xpath(companyLogoutMenu_Xpath));
			int logoutMenuItems=logout.size();
			if(logoutMenuItems==6)
			{
				if(fl.elementDisplayed(driver, CompanyLogout_Xpath,"").equals("true"))
				{
					fl.ClickByXpath(driver, CompanyLogout_Xpath, "", "Click on Logout", "Company logged out succesfully", "", "Y");
				}
			}
			else
			{
				if(logoutMenuItems==4)
				{
					if(fl.elementDisplayed(driver, CompanyLogout4_Xpath,"").equals("true"))
					{
						fl.ClickByXpath(driver, CompanyLogout4_Xpath, "", "Click on Logout", "Individual Logged ot succesfully", "", "Y");
					}
				}
				else
				{
					if(logoutMenuItems==5)
					{
						if(fl.elementDisplayed(driver, CompanyLogout5_Xpath,"").equals("true"))
						{
							fl.ClickByXpath(driver, CompanyLogout5_Xpath, "", "Click on Logout", "Individual Logged ot succesfully", "", "Y");
						}
					}
				}
			}
		/*} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
		}*/
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
	public List<String> surveyresponse_ofEndUsers(WebDriver driver, String surveyname, String Enduser) throws IOException, InterruptedException
	{
		String ExtraAns =" ";
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String Survey_Xpath = Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String SurveyViewSurveyNameXpath=Environment("SurveyViewSurveyNameXpath");
		String SurveyResponses_Xpath=Environment("SurveyResponses_Xpath");
		String Multiple_ViewDetails_Xpath=Environment("Multiple_ViewDetails_Xpath");
		String Multiple_ViewNames_Xpath=Environment("Multiple_ViewNames_Xpath");
		String ViewDetails_EndUser_Xpath=Environment("ViewDetails_EndUser_Xpath");
		String ViewDetails_EndUser2_Xpath=Environment("ViewDetails_EndUser2_Xpath");
		String getSurveyName_Xpath=Environment("getSurveyName_Xpath");
		String getNoofQuestins_Xpath=Environment("getNoofQuestins_Xpath");
		String AnswerXpath=Environment("AnswerXpath");
		String removeFromAnswer_Xpath=Environment("removeFromAnswer_Xpath");
		String getTotalQue_Xpath=Environment("getTotalQue_Xpath");
		
		
		/*try 
		{*/
			List<String> list = new ArrayList<>();
			String[] que_ans = null;
			int total_que = 0;
			String tot_q = null;
			String currenturl = driver.getCurrentUrl();
			if(!currenturl.contains("/SurveyView/"))
			{
				jse.executeScript("window.scrollBy(0,-450)", "");
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
				
				fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
				
				fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "Search with SurveyName", "", "", "", "");
				
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "Click on More Options", "", "", "");
				
				fl.ClickByXpath(driver, viewonGrid_Xapth, "", "Click on View option", "", "", "");
			}
			
			String SurveyViewSurveyName = fl.getTextXPATH(driver, SurveyViewSurveyNameXpath, "", "Get the Surveyname Text in Survey View", "", "", "");
			
			if(!surveyname.equals(SurveyViewSurveyName))
			{
				jse.executeScript("window.scrollBy(0,-450)", "");
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
				
				fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
				
				fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "Search with surveyName", "", "", "", "");
				
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "Click on More Options", "", "", "");
				
				fl.ClickByXpath(driver, viewonGrid_Xapth, "", "Click On View", "", "", "");
			}
			fl.ClickByXpath(driver, SurveyResponses_Xpath, "", "Click on Survey Response To view the Response by User", "", "", "");
			
			String oldTab =driver.getWindowHandle();
			System.out.println("Parent Window :"+oldTab);
		
			System.out.println("before clicking the survey Response VIEW");
		
			jse.executeScript("window.scrollBy(0,1000)", "");
		
			List<WebElement> no_enduser_response = driver.findElements(By.xpath(Multiple_ViewDetails_Xpath));
			int no_responses = no_enduser_response.size();
		
			for(int en_resp=1;en_resp <= no_responses;en_resp++)
			{
				WebElement E1 = driver.findElement(By.xpath(Multiple_ViewDetails_Xpath+"["+en_resp+"]"+Multiple_ViewNames_Xpath));
				String user =E1.getText();
			
				if(user.equals(Enduser))
				{
					if(fl.elementDisplayed(driver, ViewDetails_EndUser_Xpath+"["+en_resp+"]"+ViewDetails_EndUser2_Xpath,"").equals("true"))
					{
						fl.ClickByXpath(driver, ViewDetails_EndUser_Xpath+"["+en_resp+"]"+ViewDetails_EndUser2_Xpath, "", "click on the reponse by "+user, "", "", "");
				
						Thread.sleep(3000);
				
						Set<String> set = new HashSet<String>(driver.getWindowHandles());
						for(String tab : set)
						{
							System.out.println("window :"+tab);
						}
						set.remove(oldTab);
				
						driver.switchTo().window(set.iterator().next());
				
						System.out.println("child window URL : "+driver.getCurrentUrl());
				
						String name_survey = fl.getTextXPATH(driver, getSurveyName_Xpath, "", "", "End User Reponse View from companyside", "", "");
						System.out.println(name_survey);
					
						//response ans info
						list.add(name_survey);
				
						List<WebElement> No_of_Que=driver.findElements(By.xpath(getNoofQuestins_Xpath));
				
						total_que=No_of_Que.size();
					
						if(fl.elementDisplayed(driver, getTotalQue_Xpath,"").equals("true"))
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
						
							if(que>5)
							jse.executeScript("window.scrollBy(0,450)", "");
						
						}
					}
					else
					{
						System.out.println("no one gives the response to this survey: "+surveyname);
					}
					
					driver.close();//response window close
					
					driver.switchTo().window(oldTab);
					
					driver.navigate().refresh();
					
					jse.executeScript("window.scrollBy(0,-450)", "");
					
					return list;
				}
			}
			
		/*} 
		catch(NumberFormatException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			System.out.println(e.getMessage());
		}*/
		return null;
	}
	public void createQuizTempalte(WebDriver driver, String templateName, String QuizType,String QuizName, String QuizDescipt, 
			String SaveAsTempl, String NewTemplate, String Indiv_Que_Dur, String TotalMinutes , String EqualWeight,String score, String EqualDur, String seconds, String ExpiresInDays) throws IOException, InterruptedException
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
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		try 
		{
			JavascriptExecutor jse  = (JavascriptExecutor)driver;
			
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Creating new Quiz", "", "", "");
			
			fl.ClickByXpath(driver, createQuiz_Xpath, "", "", "", "", "");
			
			String template= fl.checkOptionValueInSelect(driver, selectTemplate_SelectXpath, selectTemplate_SelectOptionsXpath, templateName);
			
			if(template.equals("true"))
			{
				fl.selectDropdownByxpath(driver, selectTemplate_SelectXpath, templateName, "Selecting existed dropdown option", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizName_Xapth, QuizName, "Entering value in Quiz name", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizDescription_Xpath, QuizDescipt, "Entering Description", "", "", "", "");
				
				if(SaveAsTempl!="")
				{
					//fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "saving as Template", "", "", "");
					
					//fl.entervalueByXpath(driver, Template_Xpath, templateName, "Naming the Template", "", "", "", "");
				}
			}
			else
			{
				fl.selectDropdownByxpath(driver, QuizType_SelectXpath, QuizType, "Template you mentioned is not Existed, creating a template with that Name", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizName_Xapth, QuizName, "Entering Value in Quizname field", "", "", "", "");
				
				fl.entervalueByXpath(driver, QuizDescription_Xpath, QuizDescipt, "Entering Description", "", "", "", "");
				
				if(SaveAsTempl!="")
				{					
					fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "saving as Template", "", "", "");
					
					fl.entervalueByXpath(driver, Template_Xpath, templateName, "Naming the Template", "", "", "", "");
				}
				
				if(QuizType.equals("With Duration"))
				{
					if(Indiv_Que_Dur!="")
					{
						fl.ClickByXpath(driver, Individual_Que_Dur_Xpath, "", "With Duration and selecting Individual Que Duration type Quiz", "", "", "");
					
						if(EqualWeight!="")
						{
							fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "Selecting Equal Weight in Individual", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "entering equal Score for each que", "", "", "", "");
						}
						if(EqualDur!="")
						{
							//jse.executeScript("window.scrollBy(0,250)", "");
							
							fl.ClickByXpath(driver, Equal_Dura_Xpath, "", "Selecting Equal Duration in Individual", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Dur_Sec_Xpath, seconds, "entering equal seconds for each que", "", "", "", "");
						}
					}
					else
					{
						if(TotalMinutes!="")
						{
							fl.entervalueByXpath(driver, TotalDur_Xpath, TotalMinutes, "With Duration and selecting Total Duration in Minutes", "", "", "", "");
						}
						if(EqualWeight!="")
						{
							fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "Selecting Equal Weight", "", "", "");
						
							fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "Entering equal score for each question", "", "", "", "");
						}
					}
				}
				else								//without duration
				{
					if(EqualWeight!="")
					{
						fl.ClickByXpath(driver, Equal_Weight_Xpath, "", "Quiz of Type WithOut Duration", "", "", "");
					
						fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, score, "selecting Equal Weight score for quiz of type Without Duration", "", "", "", "");
					}
					fl.entervalueByXpath(driver, ExpiresInDays_Xpath, ExpiresInDays, "Mention How many days the quiz is active", "", "", "", "");
				}
					
			}
				
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	public void checkQuizTemplates(WebDriver driver, String templatename) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String QuizTemplates_Xpath=Environment("QuizTemplates_Xpath");
		String advancedSearch_QT_Xpath=Environment("advancedSearch_QT_Xpath");
		String new_QT_Xpath=Environment("new_QT_Xpath");
		String templateName_QT_Xpath=Environment("templateName_QT_Xpath");
		String more_QT_Xpath=Environment("more_QT_Xpath");
		String quiznameCompare_QT_Xpath=Environment("quiznameCompare_QT_Xpath");
		
		fl.JS_Element_Find(driver, Quiz_Xpath);
		fl.ClickByXpath(driver, Quiz_Xpath, "", "Created Template Validation", "Go to Quiz Menu", "", "");
		fl.ClickByXpath(driver, QuizTemplates_Xpath, "", "", "Click on Quiz Templates", "", "");
		fl.ClickByXpath(driver, advancedSearch_QT_Xpath, "", "", "Click on advanced search", "", "");
		fl.entervalueByXpath(driver, templateName_QT_Xpath, templatename, "", "", "Enter QuizTemplate name", "", "");
		
		
		fun_cas.listSize(driver, more_QT_Xpath, templatename, quiznameCompare_QT_Xpath);
		
	}
	public void Quiz_Questions(WebDriver driver, String Que_text, String score, String seconds, String AnswerType, String NoofOptions_OR_Text,
			int Question_No, String option1,String option2,String option3,String option4,String option5,String option6,String option7,String option8
			,String option9,String option10, String EnterLabelText, String ValidateQue) throws IOException, InterruptedException
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
		String AddQuestion_To_ExistedQuiz=Environment("AddQuestion_To_ExistedQuiz");
		String Que_Queno_Xpath=Environment("Que_Queno_Xpath");
		String QuizQueno_Xpath=Environment("QuizQueno_Xpath");
//		String QueExtraText=".  ";
		String QueExtraText="\n";
		
		try 
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			//jse.executeScript("window.scrollBy(0,1000)", "");
			
			fl.JS_Element_Find(driver, Que_Text_Quiz_Xpath);
			
			fl.entervalueByXpath(driver, Que_Text_Quiz_Xpath, Que_text, "Entering the Question Text", "", "", "", "");
			
			
				//String Weightage_label = fl.getTextXPATH(driver, Weightage_label_Xpath, "", "", "", "", "");
				String Weightage_label =fl.elementDisplayed(driver, Weightage_label_Xpath,"");
				
					if(Weightage_label.equals("true"))
					{
						fl.entervalueByXpath(driver, WeightageScore_QuizXpath, score, "can enter different score for Each Question in Quiz", "", "", "", "");
					}
				
				//String Duration_label = fl.getTextXPATH(driver, Duration_Label_Xpath, "", "", "", "", "");
				
					String Duration_label =fl.elementDisplayed(driver, Duration_Label_Xpath,"");
			
					if(Duration_label.equals("true"))
					{
						fl.entervalueByXpath(driver, DurationInSeconds_QuizXpath, seconds, "can enter different Time duration for Each Question in Quiz", "", "", "", "");
					}
				
			
			String AnswerType_check= fl.checkOptionValueInSelect(driver, AnswerType_Quiz_SelectXpath, AnswerType_Quiz_SelectOption_Xpath, AnswerType);
					
			if(AnswerType_check.equals("true"))
			{
				fl.selectDropdownByxpath(driver, AnswerType_Quiz_SelectXpath, AnswerType, "selecting Answer Type", "", "", "", "");
				
				fl.ClickByXpath(driver, AnswerType_Quiz_SelectXpath, "", "", "", "", "");
			}
			else
			{
				fl.disp_Message(driver, "", "AnswerType You have given in Excel Not Matched with Any one of the given", "failed", "failed", "");
				Assert.fail("AnswerType You have given in Excel Not Matched with Any one of the given");
			}
			
			
				String labelText = fl.elementDisplayed(driver, NumberofOptionLabel_Xpath,"");
			
				if(labelText.equals("true"))
				{
					System.out.println("Number value option enabled");
				
					String Check_options=fl.checkOptionValueInSelect(driver, NumberofOption_Select_Xpath, NumberofOption_SelectOption_Xpath, NoofOptions_OR_Text);
				
					if(Check_options.equals("true"))
					{
					
						fl.selectDropdownByxpath(driver, NumberofOption_Select_Xpath, NoofOptions_OR_Text, "selecting number of options", "", "", "", "");
						
						fl.ClickByXpath(driver, NumberofOption_Select_Xpath, "", "", "", "", "");
					
						if(NoofOptions_OR_Text!="")
						{
							System.out.println("options are available "+NoofOptions_OR_Text);
						
							if(NoofOptions_OR_Text.equals("2"))
							{
								System.out.println("option values to be entered");
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
						
							}
							if(NoofOptions_OR_Text.equals("3"))
							{
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
								
							}
							if(NoofOptions_OR_Text.equals("4"))
							{
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("5"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("6"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "option6 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("7"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "option6 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_7_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "option7 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("8"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "option6 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_7_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "option7 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "option8 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("9"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "option6 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_7_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "option7 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "option8 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_9_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_9_Quiz_Text_Xpath, option9, "option9 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
							if(NoofOptions_OR_Text.equals("10"))
							{
						
								fl.entervalueByXpath(driver, OPtions_1_Quiz_Text_Xpath, option1, "option1 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_2_Quiz_Text_Xpath, option2, "option2 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_3_Quiz_Text_Xpath, option3, "option3 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_4_Quiz_Text_Xpath, option4, "option4 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_5_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_5_Quiz_Text_Xpath, option5, "option5 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_6_Quiz_Text_Xpath, option6, "option6 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_7_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_7_Quiz_Text_Xpath, option7, "option7 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_8_Quiz_Text_Xpath, option8, "option8 value has entered", "", "", "", "");
								
								fl.JS_Element_Find(driver, OPtions_9_Quiz_Text_Xpath);
							
								fl.entervalueByXpath(driver, OPtions_9_Quiz_Text_Xpath, option9, "option9 value has entered", "", "", "", "");
							
								fl.entervalueByXpath(driver, OPtions_10_Quiz_Text_Xpath, option10, "option10 value has entered", "", "", "", "");
								
								jse.executeScript("window.scrollBy(0,500)", "");
						
							}
					
						}
						
					}
				}
				else
				{
					//System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
					
					
				}
				
				String text_check = fl.elementDisplayed(driver, EnterLabelText_Quiz_Xpath,"");
				if(text_check.equals("true"))
				{
					if(text_check.contains("Text"))
					{
						if(EnterLabelText!="")
						{
							fl.entervalueByXpath(driver, EnterLabel_Quiz_Xpath, EnterLabelText, "Label text for Question", "", "", "", "");
						}
					
					}
					else
					{
						System.out.println("Not Found");
					}
				}
				
			System.out.println("befor addquestion click");
			
			
			/*System.out.println("Displayed "+driver.findElement(By.xpath(AddQuestion_Quiz_Xpath)).isDisplayed());
			System.out.println("Enabled "+driver.findElement(By.xpath(AddQuestion_Quiz_Xpath)).isEnabled());
			System.out.println("Selected "+driver.findElement(By.xpath(AddQuestion_Quiz_Xpath)).isSelected());*/
			
			String addQue=fl.elementDisplayed(driver, AddQuestion_Quiz_Xpath,"");
			
			System.out.println("AddQuestionElement Enable or Not :"+addQue);
			
			if(addQue.equals("true"))
			{
				fl.JS_Element_Find(driver, AddQuestion_Quiz_Xpath);
			
				fl.ClickByXpath(driver, AddQuestion_Quiz_Xpath, "", "Adding Question to the quiz", "", "", "");
			}
			/*else
			{
				System.out.println("after addquestion click");
			
				String addQue_UpdateQuiz=fl.elementDisplayed(driver, AddQuestion_To_ExistedQuiz);
			
				if(addQue_UpdateQuiz.equals("true"))
				{
					fl.JS_Element_Find(driver, AddQuestion_To_ExistedQuiz);
					
					fl.ClickByXpath(driver, AddQuestion_To_ExistedQuiz, "", "", "", "", "");
				}
			}*/
			
			//to validate question name
			/*if(Question_No<=4)
			{
				jse.executeScript("window.scrollBy(0,-1000)", "");//scroll up
			}*/
			if(ValidateQue!="")
			{
				fl.JS_Element_Find(driver, Que_Queno_Xpath);
					fl.JS_Element_Find(driver, Que_Queno_Xpath+"["+Question_No+"]");
					
					System.out.println("Preview Que Text: "+fl.findByXpath(driver, Que_Queno_Xpath+"["+Question_No+"]").getText());
				
					String QueNoText = fl.findByXpath(driver, QuizQueno_Xpath+"["+Question_No+"]").getText();
					String ReplaceText = QueNoText+QueExtraText;
					System.out.println("Question no text : "+QueNoText);
					System.out.println("QuestionExtraText :"+QueExtraText);
					System.out.println("Replace Text :"+ReplaceText);
				
					String Quizque_text = fl.findByXpath(driver, Que_Queno_Xpath+"["+Question_No+"]").getText().replace(
						ReplaceText, "");
					System.out.println("After removing subString :"+Quizque_text);
				    
					Assert.assertTrue(Quizque_text.equals(Que_text),"question preview not matches the text you entered in que field");
					fl.disp_Message(driver, "", "Question Preview Matches with preview", "", "", "Y");
			}
			
		}
		catch (Exception e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	public int existedQueInQuiz(WebDriver driver) throws IOException
	{
		String Que_Queno_Xpath=Environment("Que_Queno_Xpath");
		List<WebElement> totQue = driver.findElements(By.xpath(Que_Queno_Xpath));
		int QueNo=totQue.size();
		return QueNo;
	}
	public void saveCreatedQuiz(WebDriver driver) throws IOException, InterruptedException
	{
		String save_Quiz_Xpath=Environment("save_Quiz_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try
		{
			jse.executeScript("window.scrollBy(0,-1000)", "");
			fl.ClickByXpath(driver, save_Quiz_Xpath, "", "Saving the Quiz", "", "", "");
			Logs_DigiSurvey.info("Saved the quiz successfully");
		} 
		catch (Exception e)
		{
			fl.disp_Message(driver, "", "Error Occured:", "", "", "Y");
			Logs_DigiSurvey.info("quiz saving functionality failed");
			e.printStackTrace();
		}
	}
	public void cancelCreatedQuiz(WebDriver driver) throws IOException, InterruptedException
	{
		String cancel_Quiz_Xpath=Environment("cancel_Quiz_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try 
		{
			jse.executeScript("window.scrollBy(0,-1000)", "");
			fl.ClickByXpath(driver, cancel_Quiz_Xpath, "", "cancel the quiz", "", "", "");
			Logs_DigiSurvey.info("Cancel the quiz without saving");
		} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.error("quiz cancelling without save is not working");
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
	public void check_CP_Filled(WebDriver driver, String filepath, String overview, String background) throws IOException, InterruptedException
	{
		String Company_LogoXPATH=Environment("Company_LogoXPATH");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String companyPublicView_CP_Xpath=Environment("companyPublicView_CP_Xpath");
		String overview_CPNoFill_Xpath=Environment("overview_CPNoFill_Xpath");
		String editCompanyPublicView_CP_Xpath=Environment("editCompanyPublicView_CP_Xpath");
		String Image1_CP_Xpath=Environment("Image1_CP_Xpath");
		String overview_CP_Xpath=Environment("overview_CP_Xpath");
		String background_CP_Xpath=Environment("background_CP_Xpath");
		String save_CP_Xpath=Environment("save_CP_Xpath");
		
		try
		{
			fl.ClickByXpath(driver, Company_LogoXPATH, "", "before publish the quiz, check companyPublicView Details filled or not, Click on Company image Dropdown", "", "", "");
			fl.ClickByXpath(driver, companyProfile_Xpath, "", "click on company Profile", "", "", "");
			fl.ClickByXpath(driver, companyPublicView_CP_Xpath, "", "click on CompanyPublicView tab", "", "", "");
			String overview_text = fl.getTextXPATH(driver, overview_CPNoFill_Xpath, "", "get the text of overview, if overview contains no data , fill the company Profile", "", "", "");
			if(overview_text.contains("no Data"))
			{
				fl.ClickByXpath(driver, editCompanyPublicView_CP_Xpath, "", "edit CompanyPublicView Details", "", "", "");
				fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "Uploading an image", "", "");
				Thread.sleep(3000);
				UploadFile_Robot image = new UploadFile_Robot();
				image.uploadFile(filepath);
				fl.entervalueByXpath(driver, overview_CP_Xpath, overview, "", "", "", "", "");
				fl.entervalueByXpath(driver, background_CP_Xpath, background, "", "", "", "", "");
				fl.JS_Element_Find(driver, save_CP_Xpath);
				fl.ClickByXpath(driver, save_CP_Xpath, "", "", "", "", "");
			}
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void publishExistedQuiz(WebDriver driver, String quizname, String proceed) throws IOException, InterruptedException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String PublishonGrid_Quiz_Xpath=Environment("PublishonGrid_Quiz_Xpath");
		String gotoProfile_popUpHead_Xpath=Environment("gotoProfile_popUpHead_Xpath");
		String gotoProfile_button_Xpath=Environment("gotoProfile_button_Xpath");
		/*String companyPublicView_CP_Xpath=Environment("companyPublicView_CP_Xpath");
		String editCompanyPublicView_CP_Xpath=Environment("editCompanyPublicView_CP_Xpath");
		String Image1_CP_Xpath=Environment("Image1_CP_Xpath");
		String overview_CP_Xpath=Environment("overview_CP_Xpath");
		String background_CP_Xpath=Environment("background_CP_Xpath");
		String save_CP_Xpath=Environment("save_CP_Xpath");*/
		String Proceed_QuizPublish_Xpath=Environment("Proceed_QuizPublish_Xpath");
		String cancel_QuizPublish_Xpath=Environment("cancel_QuizPublish_Xpath");
		String success_QuizPublish_Xpath=Environment("success_QuizPublish_Xpath");
		String Quizname_Status_Xpath=Environment("Quizname_Status_Xpath");
		
		/*try 
		{*/
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Publishing the quiz existed Quizzes list Drafts", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "searching with a quizname", "", "", "", "");
			//after filtering get tha status, if it is draft then only below statements to be executed
			String status=fl.getTextXPATH(driver, Quizname_Status_Xpath, "", "see the status of Quiz", "", "", "");
			System.out.println(status);
			if(status.contains("Draft"))
			{
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "its status is Draft, click on more options", "", "", "");
				//based on status decide whether it is to be published or already published
				
					fl.ClickByXpath(driver, PublishonGrid_Quiz_Xpath, "", "Click on Publish", "publishing the existed draft", "", "");
				
					if(proceed!="")
					{
						fl.ClickByXpath(driver, Proceed_QuizPublish_Xpath, "", "proceed to publish the quiz", "", "", "");
						Logs_DigiSurvey.info("Publishing the quiz successfully");
						String success_msg = fl.getTextXPATH(driver, success_QuizPublish_Xpath, "", "", "", "", "");
						if(success_msg.contains("success"))
						{
							fl.disp_Message(driver, "", "Publishing quiz succesfully", "", "", "");
							System.out.println(success_msg);
						}
						else
						{
							fl.disp_Message(driver, "", "error occured when doing publish", "", "", "Y");
							Logs_DigiSurvey.info("error occured when doing publish");
						}
					}
					
			}
			else
			{
				Logs_DigiSurvey.info("not publishing the ");
				fl.ClickByXpath(driver, cancel_QuizPublish_Xpath, "", "not publishing the quiz existed in draft ", "", "", "Y");
			}
			
		/*}
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info("not publishing the ");
			e.printStackTrace();
		}*/
	}
	
	public void answerQuiz(WebDriver driver, String que_type, int curentque, int totque) throws IOException, InterruptedException
	{
		  Functional_Cases_propread fun_case = new Functional_Cases_propread();
		  Excel_Utils RC = new Excel_Utils(Environment("Excel"));
		  String Quiz_Answers=Environment("Sheet_Quiz_Answers"); 
		  int Quiz_Answers_row=RC.getLastrowno(Quiz_Answers); 
		  int Quiz_Answers_col=RC.getLastcolmno(Quiz_Answers); 
		  String[] Quiz_Answers_ele=new String[Quiz_Answers_col]; 
		  String[] temp=null;
		  for (int Quiz_Answers_Index = 1; Quiz_Answers_Index < RC.getLastrowno(Quiz_Answers); Quiz_Answers_Index++) 
		  { 
			  System.out.println("for Loop" );
			  System.out.println(que_type);
			  System.out.println(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"), Quiz_Answers));
			  if(que_type.equals(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"),Quiz_Answers)) )
			  {
				  // Adduser contains company email_id at 1st column  for validation
			  	 
			  		System.out.println("Matches ID to Register");
			  		System.out.println(RC.getStringCellData(Quiz_Answers_Index, RC.Current_Coulumn_Number(Quiz_Answers, "QueAnsID"),Quiz_Answers)); 
			  		//based on j value get the row data and do Adding Users
				   
			  		for(int Quiz_Answers_Ind=0;Quiz_Answers_Ind<Quiz_Answers_col;Quiz_Answers_Ind++) 
			  		{
			  			Quiz_Answers_ele[Quiz_Answers_Ind]=RC.getStringCellData(Quiz_Answers_Index, Quiz_Answers_Ind, Quiz_Answers);
			  			System.out.println("Answer based on Que Type"+Quiz_Answers_ele[Quiz_Answers_Ind]);
			  		}
			  	
			  }
		  }
		  String que_quizEnduser_Xpath=Environment("que_quizEnduser_Xpath");
		  
		  String noofcheckboxes_quizEnduser_Xpath=Environment("noofcheckboxes_quizEnduser_Xpath");
		  String chk1_quizEnduser_Xpath=Environment("chk1_quizEnduser_Xpath");
		  String checkboxtextspan_quizEnduser_Xpath=Environment("checkboxtextspan_quizEnduser_Xpath");
		  
		  String date_quizEnduser_quizEnduser_Xpath=Environment("date_quizEnduser_quizEnduser_Xpath");
		  
		  String dropdown_Select_quizEnduser_Xpath=Environment("dropdown_Select_quizEnduser_Xpath");
		  String dropdown_SelectOptions_quizEnduser_Xpath=Environment("dropdown_SelectOptions_quizEnduser_Xpath");
		  
		  String chooseFile_quizEnduser_Xpath=Environment("chooseFile_quizEnduser_Xpath");
		  
		  String number_quizEnduser_Xpath=Environment("number_quizEnduser_Xpath");
		  
		  String numofRadiooptions_quizEnduser_Xpath=Environment("numofRadiooptions_quizEnduser_Xpath");
		  String radioOptionText_quizEnduser_Xpath=Environment("radioOptionText_quizEnduser_Xpath");
		  String radioButton_quizEnduser_Xpath=Environment("radioButton_quizEnduser_Xpath");
		  
		  String rate_quizEnduser_Xpath=Environment("rate_quizEnduser_Xpath");
		  
		  String singleLineText_quizEnduser_Xpath=Environment("singleLineText_quizEnduser_Xpath");
		  
		  String multiLineText_quizEnduser_Xpath=Environment("multiLineText_quizEnduser_Xpath");
		  
		  String radioYN_quizEnduser_Xpath=Environment("radioYN_quizEnduser_Xpath");
		  String radioYN_click_quizEnduser_Xpath=Environment("radioYN_click_quizEnduser_Xpath");
		  
		  String previous_quizEnduser_Xpath=Environment("previous_quizEnduser_Xpath");
		  String submit_quizEnduser_Xpath=Environment("submit_quizEnduser_Xpath");
		 
		  String saveAndNext_quizEnduser_Xpath=Environment("saveAndNext_quizEnduser_Xpath");
		  
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
		int attempt=0;
			switch(que_type)
			{
				
				case "Check Box": System.out.println("checkbox to be clicked");
				
								  //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+checkbox_Xapth, "", "", "", "", "");
//========================================			
								  String responseoptions = null;
								  //if you want remove above options[] intialize here
								  
				
								  List<WebElement> chkoptions = driver.findElements(By.xpath(que_quizEnduser_Xpath+noofcheckboxes_quizEnduser_Xpath));
								  int chksize = chkoptions.size();
								  for(int i=1;i<=chksize;i++)//to get each option text
								  {
									  //get the option text from survey link
									  if(fl.elementDisplayed(driver, que_quizEnduser_Xpath+noofcheckboxes_quizEnduser_Xpath+"["+i+"]"+checkboxtextspan_quizEnduser_Xpath,"").equals("true"))
									  {
										  //getting 1st option value from survey link
										  responseoptions=driver.findElement(By.xpath(que_quizEnduser_Xpath+noofcheckboxes_quizEnduser_Xpath+"["+i+"]"+checkboxtextspan_quizEnduser_Xpath)).getText();
										  for(int j=0;j<10;j++)
										  {
							
											  if(responseoptions.equals(options[j]))//compare 1st option from survey link with each option from ans sheet
											  {
												  //answer write to excel sheet,along with queno
												  
												  
												  //matches get the column number from excel, now click on the respected i value
												  try 
												  {
													fl.ClickByXpath(driver, que_quizEnduser_Xpath+noofcheckboxes_quizEnduser_Xpath+"["+i+"]"+chk1_quizEnduser_Xpath, "", "click on checkbox "+i, "", "", "");
												  } 
												  catch (InterruptedException e) 
												  {
													  fl.disp_Message(driver, "", "Error Occured", "", "", "");
													  Logs_DigiSurvey.info(e.getMessage());
													  e.printStackTrace();
												  }
												  attempt++;
											  }
							
										  }
									  }
									  if(i==chksize&&attempt==0)
									  {
										  fl.disp_Message(driver, "", "Not Answered checkbox Question", "", "", "Y");
										  System.out.println("You are not Answered this question");
									  }
									  else
									  {
										  if(i==chksize&&attempt!=0)
										  {
											  fl.disp_Message(driver, "", "You are selected "+attempt+" checkboxes", "", "", "Y");
											  System.out.println("You are selected "+attempt+" checkboxes");
										  }
									  }
								  }
//=========================================	
								  if(curentque==totque)
									  fun_case.submitQuizResponse(driver);
								  else
									  fun_case.saveAnsweredQuestion_Quiz(driver);
								  	
								  	break;
				
				case "Date": System.out.println("Date to be enetered");
				
							 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+date_Xpath, "08/21/1991", "", "", "", "", "");
//================							 
							 fl.entervalueByXpath(driver, que_quizEnduser_Xpath+date_quizEnduser_quizEnduser_Xpath,
									 Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Date")], "", "Date Question Answered", "", "", "");
//================				
							 if(curentque==totque)
								  fun_case.submitQuizResponse(driver);
							 else
								 fun_case.saveAnsweredQuestion_Quiz(driver);
							 break;
									
				case "File Upload":System.out.println("file to be uploaded");
				
								   //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+File_Xpath, "", "", "", "", "");
								   //UR.uploadFile("D:\\Sailaja\\ScreenshotOuputConsole\\10Company_Creation.png");
//================					
									try 
									{
										fl.ClickByXpath(driver, que_quizEnduser_Xpath+chooseFile_quizEnduser_Xpath, "", "click on choose file", "", "", "");
									} 
									catch (InterruptedException e1) 
									{
										fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
										Logs_DigiSurvey.info(e1.getMessage());
										e1.printStackTrace();
									}
								   UR.uploadFile(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "File Upload")]);
//================									   
								   if(curentque==totque)
									   fun_case.submitQuizResponse(driver);
								   else
									   fun_case.saveAnsweredQuestion_Quiz(driver);
								   break;
									
				case "Dropdown":System.out.println("dropdown to be clicked");
				
							    //fl.selectDropdownByIndexxpath(driver, Que_Xpath+"["+que_no+"]"+dropdown_Xpath, 1, "", "", "", "", "");
//================					
								List<WebElement> dropOptions = driver.findElements(By.xpath(que_quizEnduser_Xpath+dropdown_SelectOptions_quizEnduser_Xpath));
								int optionsize = dropOptions.size();
				
								for(int i=1;i<=optionsize;i++)//to get option text from surveylink
								{
									String drop = fl.getTextXPATH(driver, que_quizEnduser_Xpath+dropdown_SelectOptions_quizEnduser_Xpath+"["+i+"]", "", "compare each option in dropdown to answer in excel", "", "", "");
									for(int j=0;j<10;j++)//to get excel options
									{
										if(drop.equals(options[j]))
										{
											try 
											{
												fl.selectDropdownByxpath(driver, que_quizEnduser_Xpath+dropdown_Select_quizEnduser_Xpath, options[j], "both are matching selected that option", "", "", "", "");
											}
											catch (InterruptedException e) 
											{
												fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
												Logs_DigiSurvey.info(e.getMessage());
												e.printStackTrace();
											}
											attempt++;
										}
									}
									if(i==optionsize&&attempt==0)
									{
										fl.disp_Message(driver, "", "Not selected any option of dropdown", "", "", "");
										System.out.println("You are not Answered this question");
									}
									if(i==optionsize&&attempt!=0)
									{
										fl.disp_Message(driver, "", "You are selected "+attempt+" dropboxes, but last string in excel is selected", "", "", "");
										System.out.println("You are selected "+attempt+" dropboxes, but last string in excel is selected");
									}
									
								}
//================				
								if(curentque==totque)
									  fun_case.submitQuizResponse(driver);
								else
									fun_case.saveAnsweredQuestion_Quiz(driver);
								break;
									
				case "Number":System.out.println("Number to be clicked");
				
							  //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+Phone_Xpath, "9533676851", "", "", "", "", "");
//================					
							  fl.entervalueByXpath(driver, que_quizEnduser_Xpath+number_quizEnduser_Xpath, 
									  Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Number")] , "Enter Number in the field", "", "", "", "");
//================	
							  if(curentque==totque)
								  fun_case.submitQuizResponse(driver);
							  else
								  fun_case.saveAnsweredQuestion_Quiz(driver);
							  break;
									
				case "Radio Button":System.out.println("radio button to be clicked");
								    
									//fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radiooptions_Xpath, "", "", "", "", "");
//===================				
									List<WebElement> radioOptions = driver.findElements(By.xpath(que_quizEnduser_Xpath+numofRadiooptions_quizEnduser_Xpath));
									int radiosize = radioOptions.size();
				
									for(int i=1;i<=radiosize;i++)
									{
										String Radio = fl.getTextXPATH(driver, que_quizEnduser_Xpath+numofRadiooptions_quizEnduser_Xpath+"["+i+"]"+radioOptionText_quizEnduser_Xpath, "", "get each radio option text with excel answer", "", "", "");
										for(int j=0;j<10;j++)
										{
											if(Radio.equals(options[j]))
											{
												try 
												{
													fl.ClickByXpath(driver, que_quizEnduser_Xpath+numofRadiooptions_quizEnduser_Xpath+"["+i+"]"+radioButton_quizEnduser_Xpath, "", "select radio option "+i, "", "", "");
												} 
												catch (InterruptedException e)
												{
													fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
													Logs_DigiSurvey.info(e.getMessage());
													e.printStackTrace();
												}
												attempt++;
											}
										}
										if(i==radiosize&&attempt==0)
										{
											fl.disp_Message(driver, "", "You are not Answered this question", "", "", "");
											System.out.println("You are not Answered this question");
										}
										else
										{
											if(i==radiosize&&attempt!=0)
											{
												fl.disp_Message(driver, "", "You are selected "+attempt+" radiobutton, but last string in excel is selected", "", "", "");
												System.out.println("You are selected "+attempt+" radiobutton, but last string in excel is selected");
											}
										}
									}
//=====================				
									if(curentque==totque)
										  fun_case.submitQuizResponse(driver);
									else
										  fun_case.saveAnsweredQuestion_Quiz(driver);
									break;
									
				case "Scale / Rate":System.out.println("rating to be entered");
				
									//fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+rate_Xpath, "", "", "", "", "");
//=====================				
									int rate = Integer.parseInt(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Scale / Rate")]);
									try 
									{
										fl.ClickByXpath(driver, que_quizEnduser_Xpath+rate_quizEnduser_Xpath+"["+rate+"]", "", "give rating as "+rate, "", "", "");
									}
									catch (InterruptedException e1) 
									{
										fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
										Logs_DigiSurvey.info(e1.getMessage());
										e1.printStackTrace();
									}
//=====================				
									if(curentque==totque)
										  fun_case.submitQuizResponse(driver);
									else
										fun_case.saveAnsweredQuestion_Quiz(driver);
									break;
									
				case "Single Line Text":System.out.println("single line text to be eneterd");
				
										//fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+feedback_Xpath, "Single line comment", "", "", "", "", "");
//==================										
										fl.entervalueByXpath(driver, que_quizEnduser_Xpath+singleLineText_quizEnduser_Xpath, 
												Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Single Line Text")], "enter single line text", "", "", "", "");
//====================				
										if(curentque==totque)
											  fun_case.submitQuizResponse(driver);
										else
											  fun_case.saveAnsweredQuestion_Quiz(driver);
										break;
									
				case "Text Area":System.out.println("text area to be entered");
				
								 //fl.entervalueByXpath(driver, Que_Xpath+"["+que_no+"]"+brief_Xpath, "Multi line comment", "", "", "", "", "");
//======================								 
								 fl.entervalueByXpath(driver, que_quizEnduser_Xpath+multiLineText_quizEnduser_Xpath, 
										 Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "TextArea")],"" , "enter multi line text", "", "", "");
//======================			
								 if(curentque==totque)
									  fun_case.submitQuizResponse(driver);
								 else
									 fun_case.saveAnsweredQuestion_Quiz(driver);
								 break;
									
				case "Yes or No":System.out.println("Y/N to be selected");
				
								 //fl.ClickByXpath(driver, Que_Xpath+"["+que_no+"]"+radioYesorNo_Xpath, "", "", "", "", "");
//=========================								 
								 if(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Yes or No")].equals("Yes"))
								 {
									 try 
									 {
										fl.ClickByXpath(driver, que_quizEnduser_Xpath+radioYN_quizEnduser_Xpath+"["+1+"]"+radioYN_click_quizEnduser_Xpath, "", "Answer Yes or No question", "Yes to be selected", "", "");
									 }
									 catch (InterruptedException e)
									 {
										 fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
										 Logs_DigiSurvey.info(e.getMessage());
										 e.printStackTrace();
									 }
								 }
								 else
								 {
									 if(Quiz_Answers_ele[RC.Current_Coulumn_Number(Quiz_Answers, "Yes or No")].equals("No"))
									 {
										 try 
										 {
											fl.ClickByXpath(driver, que_quizEnduser_Xpath+radioYN_quizEnduser_Xpath+"["+2+"]"+radioYN_click_quizEnduser_Xpath, "", "Answer Yes or No question", "No to be selected", "", "");
										 } 
										 catch (InterruptedException e) 
										 {
											 fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
											 Logs_DigiSurvey.info(e.getMessage());
											 e.printStackTrace();
										 }
									 }
								 }
//===========================		
								 if(curentque==totque)
									  fun_case.submitQuizResponse(driver);
								 else
									 fun_case.saveAnsweredQuestion_Quiz(driver);
								 break;
				
				
			
				}
	}
	public void saveAnsweredQuestion_Quiz(WebDriver driver) throws IOException, InterruptedException
	{
		String saveAndNext_quizEnduser_Xpath=Environment("saveAndNext_quizEnduser_Xpath");
		
		if(fl.elementDisplayed(driver, saveAndNext_quizEnduser_Xpath,"").equals("true"))
	  	{
	  		
	  		WebElement save = driver.findElement(By.xpath(saveAndNext_quizEnduser_Xpath));
	  		fl.JS_Element_Find(driver, saveAndNext_quizEnduser_Xpath);
	  		try
	  		{
				fl.ClickByXpath(driver, saveAndNext_quizEnduser_Xpath, "", "save Answered Question", "Checkbox Ans checked", "", "");
			} 
	  		catch (InterruptedException e) 
	  		{
	  			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
	  			Logs_DigiSurvey.info(e.getMessage());
				e.printStackTrace();
			}
	  	}
	}
	public void submitQuizResponse(WebDriver driver) throws IOException, InterruptedException
	{
		String submit_quizEnduser_Xpath=Environment("submit_quizEnduser_Xpath");
		String submitProceed_quizEnduser_Xpath=Environment("submitProceed_quizEnduser_Xpath");
		
	  		WebElement save = driver.findElement(By.xpath(submit_quizEnduser_Xpath));
	  		fl.JS_Element_Find(driver, submit_quizEnduser_Xpath);
	  		try
	  		{
				fl.ClickByXpath(driver, submit_quizEnduser_Xpath, "", "", "submitted Ans succesfully", "", "");
				fl.ClickByXpath(driver, submitProceed_quizEnduser_Xpath, "", "Proceed to submit the quiz", "", "", "");
			} 
	  		catch (InterruptedException e) 
	  		{
	  			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
	  			Logs_DigiSurvey.info(e.getMessage());
				e.printStackTrace();
			}
	  	
	}
	public void editUpdateQuiz(WebDriver driver, String quizname, String description, String expire, String eq_weight, String equal_sec, String tot_duration) throws IOException, InterruptedException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String EditonGrid_Quiz_Xpath=Environment("EditonGrid_Quiz_Xpath");
		String QuizDescription_Xpath=Environment("QuizDescription_Xpath");
		String QuizDescriptionPreview_Xpath=Environment("QuizDescriptionPreview_Xpath");
		String TotalDur_Xpath=Environment("TotalDur_Xpath");
		String Equal_Weight_Xpath=Environment("Equal_Weight_Xpath");
		String Equal_Weight_Score_Xpath=Environment("Equal_Weight_Score_Xpath");
		String Equal_Dura_Xpath=Environment("Equal_Dura_Xpath");
		String Equal_Dur_Sec_Xpath=Environment("Equal_Dur_Sec_Xpath");
		String ExpiresInDays_Xpath=Environment("ExpiresInDays_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Quiz_Xpath, "", "To Edit Quiz,go to Quiz Menu", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "click on Quizzes", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "Click on Advanced Search", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, quizname, "Search with "+quizname, "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "click on more options", "", "", "");
			fl.ClickByXpath(driver, EditonGrid_Quiz_Xpath, "", "click on edit", "", "", "");
			if(!description.equals(""))
			{
				fl.ClickByXpath(driver, QuizDescription_Xpath, "", "click on quiz description", "", "", "");
				select_delete sel_del = new select_delete();
				Thread.sleep(3000);
				fl.entervalueByXpath(driver, QuizDescription_Xpath, description, "enter description", "", "", "", "");
			}
			if(!expire.equals(""))
			{
				String exp_day=fl.elementDisplayed(driver, ExpiresInDays_Xpath,"");
				if(exp_day.equals("true"))
				{
					fl.ClickByXpath(driver, ExpiresInDays_Xpath, "", "Click on checkbox expiresInDays", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, ExpiresInDays_Xpath, expire, "enter value in Days, expires in how many days", "", "", "", "");
				}
			}
			if(!eq_weight.equals(""))
			{
				String eq_wgt=fl.elementDisplayed(driver, Equal_Weight_Score_Xpath,"");
				if(eq_wgt.equals("true"))
				{
					fl.ClickByXpath(driver, Equal_Weight_Score_Xpath, "", "Click on Equal Weight", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, eq_weight, "enter the equal score", "", "", "", "");
				}
			}
			if(!equal_sec.equals(""))
			{
				String eq_sec= fl.elementDisplayed(driver, Equal_Dur_Sec_Xpath,"");
				if(eq_sec.equals("true"))
				{
					fl.ClickByXpath(driver, Equal_Dur_Sec_Xpath, "", "click on equal Duration", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, Equal_Dur_Sec_Xpath, equal_sec, "enter the Equal seconds", "", "", "", "");
				}
			}
			if(!tot_duration.equals(""))
			{
				String tot_dur = fl.elementDisplayed(driver, TotalDur_Xpath,"");
				if(tot_dur.equals("true"))
				{
					fl.ClickByXpath(driver, TotalDur_Xpath, "", "Click on total Duration", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, TotalDur_Xpath, tot_duration, "", "if Total Duration enabled for this quiz", "Modify the TotalDuration", "", "");
				}
			}
			
		} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void shareQuiz(WebDriver driver, String quizname, int noOfshares) throws IOException, InterruptedException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String ViewonGrid_Quiz_Xpath=Environment("ViewonGrid_Quiz_Xpath");
		String weblinks_Quiz_Xpath=Environment("weblinks_Quiz_Xpath");
		String quizLink_Xpath=Environment("quizLink_Xpath");
		
		String Email_tab_Xpath=Environment("Email_tab_Xpath");
		String getLink_Xpath=Environment("getLink_Xpath");
		
		
		
		try 
		{
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Get the Quiz WebLink and write to excel, go to Quiz Menu", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "click on Quizzes", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "Click on Advanced Search", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "Search with quizname", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "click on More options", "", "", "");
			fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "view the selected quiz", "", "", "");
			fl.ClickByXpath(driver, Email_tab_Xpath, "", "click on email tab", "", "", "");
			fl.JS_Element_Find(driver, getLink_Xpath);
			String linkText = fl.getTextXPATH(driver, getLink_Xpath, "", "", "", "", "");
			System.out.println("QuizLink text copie from email"+linkText);
			
			FileInputStream fis = new FileInputStream(new File("QuizLink_Excel\\Quiz_Links.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			System.out.println("exceel row "+noOfshares+" is set by survey link :"+linkText);
			sheet1.getRow(noOfshares).createCell(0).setCellValue(linkText);
			FileOutputStream fout = new FileOutputStream("QuizLink_Excel\\Quiz_Links.xlsx");
			wb.write(fout);
			/*fl.ClickByXpath(driver, weblinks_Quiz_Xpath, "", "Get the quiz weblink", "click on weblinks tab", "", "");
			fl.ClickByXpath(driver, quizLink_Xpath, "", "", "", "", "");
			
			String Parent=driver.getWindowHandle();
			copiedText_Robo Copy_Survey_Link = new copiedText_Robo();
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			System.out.println(newTab.size());
			newTab.remove(Parent);
			System.out.println(newTab.get(0));
			driver.switchTo().window(newTab.get(0));
			String SurveyURL = driver.getCurrentUrl();
			FileInputStream fis = new FileInputStream(new File("QuizLink_Excel\\Quiz_Links.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			System.out.println("exceel row "+noOfshares+" is set by survey link :"+SurveyURL);
			sheet1.getRow(noOfshares).createCell(0).setCellValue(SurveyURL);
			FileOutputStream fout = new FileOutputStream("QuizLink_Excel\\Quiz_Links.xlsx");
			wb.write(fout);
			wb.close();
			driver.close();
			Thread.sleep(3000);
			driver.switchTo().window(Parent);*/
			
		}
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	public List<String> quiz_ResponseView(WebDriver driver, String quizname, String enduser) throws InterruptedException, IOException
	{
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String Quizzes_Xpath=Environment("Quizzes_Xpath");
		String AdvancedSearch_Xpath=Environment("AdvancedSearch_Xpath");
		String QuizNameSearchBox_Xpath=Environment("QuizNameSearchBox_Xpath");
		String MoreOptions_Quiz_Xpath=Environment("MoreOptions_Quiz_Xpath");
		String quizname_QR_Xpath=Environment("quizname_QR_Xpath");
		String quizname_QView_Xpath=Environment("quizname_QView_Xpath");
		String ViewonGrid_Quiz_Xpath=Environment("ViewonGrid_Quiz_Xpath");
		String responses_QR_Xpath=Environment("responses_QR_Xpath");
		String viewDetails_QR_Xpath=Environment("viewDetails_QR_Xpath");
		String responseName1_QR_Xpath=Environment("responseName1_QR_Xpath");
		String responseName2_QR_Xpath=Environment("responseName2_QR_Xpath");
		String extraresponseName_QR_Xpath=Environment("extraresponseName_QR_Xpath");
		String getNoofQuestins_QR_Xpath=Environment("getNoofQuestins_QR_Xpath");
		String totalQue_QR_Xpath=Environment("totalQue_QR_Xpath");
		String totalQuestions_QR_Xpath=Environment("totalQuestions_QR_Xpath");
		String queText_QR_Xpath=Environment("queText_QR_Xpath");
		String totansText_QR_Xpath=Environment("totansText_QR_Xpath");
		String ansText_QR_Xpath=Environment("ansText_QR_Xpath");
		
		String currenturl = driver.getCurrentUrl();
		List<String> list = new ArrayList<>();
		int total_que;
		String tot_q;
		String[] que_ans = null;
		String ExtraAns=" ";
		String extra="\n";
		
		if(!currenturl.contains("/QuizView/"))
		{
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Get the Quiz WebLink and write to excel, go to Quiz Menu", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "click on Quizzes", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "Click on Advanced Search", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "Search with quizname", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "click on More options", "", "", "");
			fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "view the selected quiz", "", "", "");
		}
		String quiz_name = fl.getTextXPATH(driver, quizname_QView_Xpath, "", "", "Quiz name comparision", "", "");
		if(!quiz_name.equals(quizname))
		{
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Get the Quiz WebLink and write to excel, go to Quiz Menu", "", "", "");
			fl.ClickByXpath(driver, Quizzes_Xpath, "", "click on Quizzes", "", "", "");
			fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "Click on Advanced Search", "", "", "");
			fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "Search with quizname", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "click on More options", "", "", "");
			fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "view the selected quiz", "", "", "");
		}
		
		fl.ClickByXpath(driver, responses_QR_Xpath, "", "", "Click on Responses tab", "", "");
		String oldTab =driver.getWindowHandle();
		System.out.println("Parent Window :"+oldTab);
		
		List<WebElement> views = driver.findElements(By.xpath(viewDetails_QR_Xpath));
		int responses = views.size();
		for(int i=1;i<=responses;i++)
		{
			String tot_username = fl.getTextXPATH(driver, responseName1_QR_Xpath+"["+i+"]"+responseName2_QR_Xpath, "", "", "find Endusername with "+enduser, "", "");
			
			String removeuser=fl.getTextXPATH(driver, responseName1_QR_Xpath+"["+i+"]"+extraresponseName_QR_Xpath, "", "", "find Endusername with "+enduser, "", "");
			System.out.println("removable string "+removeuser);
			String remove=extra+removeuser;
			System.out.println("Total Removable "+remove);
			String username=tot_username.replace(remove, "");
			System.out.println("Username is : "+username);
			System.out.println("enuser name is : "+enduser);
			fl.JS_Element_Find(driver, viewDetails_QR_Xpath+"["+i+"]");
			if(username.equals(enduser))
			{
				
				fl.ClickByXpath(driver, viewDetails_QR_Xpath+"["+i+"]", "", "", "Click on view details", "", "");
				Thread.sleep(3000);
				Set<String> set = new HashSet<String>(driver.getWindowHandles());
				for(String tab : set)
				{
					System.out.println("window :"+tab);
				}
				set.remove(oldTab);
				driver.switchTo().window(set.iterator().next());
				System.out.println("child window URL : "+driver.getCurrentUrl());
				String quiz=fl.getTextXPATH(driver, quizname_QR_Xpath, "", "", "Getting quiz name from response view page", "", "");
				list.add(quiz);
				List<WebElement> No_of_Que=driver.findElements(By.xpath(getNoofQuestins_QR_Xpath));
				total_que=No_of_Que.size();
				if(fl.elementDisplayed(driver, totalQue_QR_Xpath,"").equals("true"))
				{
					WebElement tot_Ques = driver.findElement(By.xpath(totalQue_QR_Xpath));
					tot_q=tot_Ques.getText();
					//response ans info
					list.add(tot_q);
				}
				System.out.println("Total Questions Are :"+total_que);
				que_ans = new String[total_que-1];
				for(int que=1;que <= total_que;que++)
				{
					String TotAns_answer = fl.getTextXPATH(driver, totalQuestions_QR_Xpath+"["+que+"]"+totansText_QR_Xpath, "", "", "Get the Total Answer", "", "");
					//System.out.println(TotAns_answer);//Ans: checkbox1
					String extra_Ans = fl.getTextXPATH(driver, totalQuestions_QR_Xpath+"["+que+"]"+ansText_QR_Xpath, "", "", "Get Extra Text in answer", "", "");
					//System.out.println(extra_Ans);//Ans:
					String remove_ans=extra_Ans+ExtraAns;//"Ans: "
					String actualAns= TotAns_answer.replace(remove_ans, "");
					System.out.println(actualAns);
					//que_ans[que-1]=actualAns;
					//response ans info
					list.add(actualAns);
					int nextque=que+1;
					fl.JS_Element_Find(driver, totalQuestions_QR_Xpath+"["+nextque+"]");
				}
				driver.close();//response window close
				driver.switchTo().window(oldTab);
				driver.navigate().refresh();
				return list;
			}
			else
			{
				System.out.println(username+"- Not matched with the user you mentioned in excel  "+enduser);
			}
		}
		return null;
	}
	
	public String beforeCreation_checkExistedPetitions(WebDriver driver, String petitionname) throws IOException, InterruptedException
	{
		String Petitions_Xpath=Environment("Petitions_Xpath");
		String ExistedPetitions_Xpath=Environment("ExistedPetitions_Xpath");
		String advancedSearch_Drafts_Xpath=Environment("advancedSearch_Drafts_Xpath");
		String searchPetitionName_Xpath
		=Environment("searchPetitionName_Xpath");
		String multiMoreButtonFiltered_Xpath=Environment("multiMoreButtonFiltered_Xpath");
		String multiMorePetitionNameXpath=Environment("multiMorePetitionNameXpath");
		
		try {
			fl.JS_Element_Find(driver, Petitions_Xpath);
			
			fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
			
			fl.ClickByXpath(driver, ExistedPetitions_Xpath, "", "Going to Existed Petitiion", "Petition Drafts should be Displayed", "", "");
			
			fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "click on advanced search", "", "");
			
			fl.entervalueByXpath(driver, searchPetitionName_Xpath, petitionname, "", "Searching with petition name in drafts to publish", "display petition name search box", "", "");
			
			List<WebElement> buttons = driver.findElements(By.xpath(multiMoreButtonFiltered_Xpath));
			
			if(buttons.size()>=1)
			{
				List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
				
				if(names.size()>=1)
				{
					for(int i=0;i<names.size();i++)
					{
						if(names.get(i).getText().equals(petitionname))
						{
							System.out.println("Petition u want to create petitiontitle already existed need to chnge title name");
							fl.disp_Message(driver, "", "if Petition Already Existed", "Petition Existed With that Title : "+petitionname, "", "");
							return "true";
						}
					}
				}
			}
			
			
		}
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
		
		
		return "false";
		
	}
	public void displayMessage_Report(WebDriver driver, String petitionaname)
	{
		try 
		{
			fl.disp_Message(driver, "", "cheking the drafts,if petition existed or not", "Petition Not Existed "+petitionaname, "", "");
			Logs_DigiSurvey.info("Petition Not Existed "+petitionaname);
		} 
		catch (InterruptedException e)
		{
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void createPetition(WebDriver driver, String campaignType, String PetitionCategeory, String PetitionTitle, String Tags,
			String enddate, String indefinite, String video, String currencyType, String fund_goal, String no_sign, String DecisionMakerName,
			String Designation, String overview, String overviewImage, String background, String back_image, String solutionText, String solution_image,
			String risktext, String risk_image, String supportDoc, String FAQ_ID) throws IOException, InterruptedException
	{
		UploadFile_Robot upload = new UploadFile_Robot();
		String Petitions_Xpath=Environment("Petitions_Xpath");
		String create_petition_Xpath=Environment("create_petition_Xpath");
		String peti_For_Sign_Xpath=Environment("peti_For_Sign_Xpath");
		String peti_WithSign_Fund_Xpath=Environment("peti_WithSign_Fund_Xpath");
		String continue_Xpath=Environment("continue_Xpath");
		String campaignType_SelectXpath=Environment("campaignType_SelectXpath");
		String campaignType_SelectOptionsXpath=Environment("campaignType_SelectOptionsXpath");
		String PetitionCategeory_SelectXpath=Environment("PetitionCategeory_SelectXpath");
		String PetitionCategeory_SelectOptionsXpath=Environment("PetitionCategeory_SelectOptionsXpath");
		String PetitionTitle_Xpath=Environment("PetitionTitle_Xpath");
		String PetitionTags_Xpath=Environment("PetitionTags_Xpath");
		String SelectCurrency_SelectXpath=Environment("SelectCurrency_SelectXpath");
		String SelectCurrency_SelectOptionsXpath=Environment("SelectCurrency_SelectOptionsXpath");
		String FundingGoal_Xpath=Environment("FundingGoal_Xpath");
		String endDate_Xpath=Environment("endDate_Xpath");
		String Indefinite_Xpath=Environment("Indefinite_Xpath");
		String videoLink_Xpath=Environment("videoLink_Xpath");
		String SignaturesRequired_Xpath=Environment("SignaturesRequired_Xpath");
		
		String DecisionMakerPlus_Xpath=Environment("DecisionMakerPlus_Xpath");
		String DecisionMakerName_Xpath=Environment("DecisionMakerName_Xpath");
		String DecisionMakerDesignation_Xpath=Environment("DecisionMakerDesignation_Xpath");
		String addDecisionMaker_Xpath=Environment("addDecisionMaker_Xpath");
		
		String OverviewPlus_Xpath=Environment("OverviewPlus_Xpath");
		String OverviewText_Xpath=Environment("OverviewText_Xpath");
		String OverviewImage_Xpath=Environment("OverviewImage_Xpath");
		String Background_Plus_Xpath=Environment("Background_Plus_Xpath");
		String Background_TextXpath=Environment("Background_TextXpath");
		String Background_File_Xpath=Environment("Background_File_Xpath");
		String SolutionPlus_Xpath=Environment("SolutionPlus_Xpath");
		String SolutionText_Xpath=Environment("SolutionText_Xpath");
		String SolutionImage_Xpath=Environment("SolutionImage_Xpath");
		String RiskPlus_Xpath=Environment("RiskPlus_Xpath");
		String RiskText_Xpath=Environment("RiskText_Xpath");
		String RiskImage_Xpath=Environment("RiskImage_Xpath");
		
		String supportDocPlus_Xpath=Environment("supportDocPlus_Xpath");
		String supportDocImages_Xpath=Environment("supportDocImages_Xpath");
		
		String FAQPlus_Xpath=Environment("FAQPlus_Xpath");
		String FAQuestion_Xpath=Environment("FAQuestion_Xpath");
		String Answer_Xpath=Environment("Answer_Xpath");
		String addQuestion_Xpath=Environment("addQuestion_Xpath");
		String next_Page_Xpath=Environment("next_Page_Xpath");
		
		String saveAsDraft_Xpath=Environment("saveAsDraft_Xpath");
		/*try 
		{*/
			fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
			
			fl.ClickByXpath(driver, create_petition_Xpath, "", "", "Creating Petition UI Displayed", "", "");
			
			/*String camp_type= fl.checkOptionValueInSelect(driver, campaignType_SelectXpath, campaignType_SelectOptionsXpath, campaignType);
			
			if(camp_type.equals("true"))
			{
				fl.selectDropdownByxpath(driver, campaignType_SelectXpath, campaignType, campaignType, "", campaignType+" to be selected", "", "");
			}*/
			if(campaignType.equals("Petitions for Signatures"))
			{
				fl.ClickByXpath(driver, peti_For_Sign_Xpath, "", "", "selecting petition for signs", "", "");
			}
			else
			{
				if(campaignType.equals("Petitions with Signatures & Funds"))
				{
					fl.ClickByXpath(driver, peti_WithSign_Fund_Xpath, "", "", "selecting petition for signs and funds", "", "");
				}
			}
			fl.ClickByXpath(driver, continue_Xpath, "", "", "click continue", "", "");
			String Petition_Cat=fl.checkOptionValueInSelect(driver, PetitionCategeory_SelectXpath, PetitionCategeory_SelectOptionsXpath, PetitionCategeory);
			
			if(Petition_Cat.equals("true"))
			{
				fl.selectDropdownByxpath(driver, PetitionCategeory_SelectXpath, PetitionCategeory, PetitionCategeory, "", PetitionCategeory+" to be selected", "", "");
			}
			fl.entervalueByXpath(driver, PetitionTitle_Xpath, PetitionTitle, PetitionTitle, "", PetitionTitle+ "to be entered", "", "");
			
			if(Tags.contains(","))
			{
				fl.entervalueByXpath(driver, PetitionTags_Xpath, Tags, "", "", "Multi Tag Values to be entered", "", "");
				Thread.sleep(3000);
				ClickEnterRobot enter = new ClickEnterRobot();
			}
			else
			{
				fl.entervalueByXpath(driver, PetitionTags_Xpath, Tags, "", "", "Single Tag Value to be entered", "", "");
			}
			fl.entervalueByXpath(driver, endDate_Xpath, enddate, "", "", "Enter End Date", "", "");
			
			if(!indefinite.equals(""))
				
				fl.ClickByXpath(driver, Indefinite_Xpath, "", "", "select Indefinite checkbox", "", "");
			fl.entervalueByXpath(driver, videoLink_Xpath, video, "", "", "enter video link", "", "");
			
			if(campaignType.equals("Petitions with Signatures & Funds"))
			{
				fl.selectDropdownByxpath(driver, SelectCurrency_SelectXpath, currencyType, "", "", "Currency type to be selected", "", "");
				fl.entervalueByXpath(driver, FundingGoal_Xpath, fund_goal, "", "", "funding goal to be entered", "", "");
			}
			fl.entervalueByXpath(driver, SignaturesRequired_Xpath, no_sign, "", "", "number of sign to be entered", "", "");
			fl.JS_Element_Find(driver, DecisionMakerPlus_Xpath);
			fl.ClickByXpath(driver, DecisionMakerPlus_Xpath, "", "", "Decision maker to be clicked", "", "");
			fl.entervalueByXpath(driver, DecisionMakerName_Xpath, DecisionMakerName, "", "", "decision maker to be entered", "", "");
			fl.entervalueByXpath(driver, DecisionMakerDesignation_Xpath, Designation, "", "", "", "", "");
			fl.ClickByXpath(driver, addDecisionMaker_Xpath, "", "", "Click on DecisionMaker", "", "");
			
			fl.ClickByXpath(driver, OverviewPlus_Xpath, "", "", "", "", "");
			fl.entervalueByXpath(driver, OverviewText_Xpath, overview, "", "", "Enter overview Text", "", "");
			fl.ClickByXpath(driver, OverviewImage_Xpath, "", "", "Overview image to be uploaded", "", "");
			upload.uploadFile(overviewImage);
			fl.JS_Element_Find(driver, Background_Plus_Xpath);
			fl.ClickByXpath(driver, Background_Plus_Xpath, "", "", "Background clicked", "", "");
			fl.entervalueByXpath(driver, Background_TextXpath, background, "", "", "background text to be entered", "", "");
			fl.ClickByXpath(driver, Background_File_Xpath, "", "", "background image to be uploaded", "", "");
			upload.uploadFile(back_image);
			
			fl.ClickByXpath(driver, SolutionPlus_Xpath, "", "", "Solution to be clicked", "", "");
			fl.entervalueByXpath(driver, SolutionText_Xpath, solutionText, "", "", "Solution text to be entered", "", "");
			fl.ClickByXpath(driver, SolutionImage_Xpath, "", "", "Solution image to be uploaded", "", "");
			upload.uploadFile(solution_image);
			fl.JS_Element_Find(driver, RiskPlus_Xpath);
			fl.ClickByXpath(driver, RiskPlus_Xpath, "", "", "Risks clicked", "", "");
			fl.entervalueByXpath(driver, RiskText_Xpath, risktext, "", "", "risk text to be entered", "", "");
			fl.ClickByXpath(driver, RiskImage_Xpath, "", "", "Risk images to be clicked", "", "");
			upload.uploadFile(risk_image);
			
			fl.ClickByXpath(driver, supportDocPlus_Xpath, "", "", "Support Documents to be clicked", "", "");
			fl.ClickByXpath(driver, supportDocImages_Xpath, "", "", "Support Documents To be uploaded", "", "");
			upload.uploadFile(supportDoc);
			fl.ClickByXpath(driver, FAQPlus_Xpath, "", "", "FAQ clicked", "", "");
			
			Excel_Utils RC = new Excel_Utils(Environment("Excel"));
			String FAQ_CreatePetition=Environment("Sheet_FAQ_CreatePetition"); 
			int FAQ_CreatePetition_row=RC.getLastrowno(FAQ_CreatePetition); 
			int FAQ_CreatePetition_col=RC.getLastcolmno(FAQ_CreatePetition); 
			String[] FAQ_CreatePetition_ele=new String[FAQ_CreatePetition_col]; 
			for (int FAQ_CreatePetition_index = 1; FAQ_CreatePetition_index < RC.getLastrowno(FAQ_CreatePetition); FAQ_CreatePetition_index++) 
			{ 
				 System.out.println("for Loop" );
				 System.out.println(FAQ_ID);
				 System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"), FAQ_CreatePetition)); 
				 if (FAQ_ID.equals(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)))
					  // Adduser contains company email_id at 1st column  for validation
				 { 
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int FAQ_CreatePetition_ind=0;FAQ_CreatePetition_ind<FAQ_CreatePetition_col;FAQ_CreatePetition_ind++) 
					  {
						  FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]=RC.getStringCellData(FAQ_CreatePetition_index, FAQ_CreatePetition_ind, FAQ_CreatePetition);
						  System.out.println(FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]); //call login as company method, pass array values
		  
			  
					  }
			          fl.entervalueByXpath(driver, FAQuestion_Xpath, FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Question")], "", "", "Question text to be entered", "", "");
			          fl.entervalueByXpath(driver, Answer_Xpath, FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Answer")], "", "", "Answers to be entered", "", "");
			          fl.ClickByXpath(driver, addQuestion_Xpath, "", "", "Add question to be clicked", "", "");
			          
				 }
			}
			Thread.sleep(3000);
			fl.JS_Element_Find(driver, next_Page_Xpath);
	        fl.ClickByXpath(driver, next_Page_Xpath, "", "", "navigate to next page", "", "");
	        fl.JS_Element_Find(driver, saveAsDraft_Xpath);
	        fl.ClickByXpath(driver, saveAsDraft_Xpath, "", "", "SaveAs Draft to be clicked", "", "");
			
		/*} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}*/
	}
	public void saveCreatedPetition(WebDriver driver) throws IOException, InterruptedException
	{
		String saveCreatedPetition_Xpath=Environment("saveCreatedPetition_Xpath");
		String success_PetitionXpath=Environment("success_PetitionXpath");
		String successMsg_PetitionXpath=Environment("successMsg_PetitionXpath");
		
		fl.JS_Element_Find(driver, saveCreatedPetition_Xpath);
		
		try
		{
			fl.ClickByXpath(driver, saveCreatedPetition_Xpath, "", "Created Petition is to be saved", "created petition saved successfully", "", "");
		} 
		catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void publishCreatedPetition(WebDriver driver) throws IOException, InterruptedException
	{
		String publishCreatedPetition_Xpath=Environment("publishCreatedPetition_Xpath");
		
		fl.JS_Element_Find(driver, publishCreatedPetition_Xpath);
		
		try 
		{
			fl.ClickByXpath(driver, publishCreatedPetition_Xpath, "", "Publish Created Petition", "Publishing the Petition", "", "");
		} catch (InterruptedException e) 
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	public void publishSavedPetition(WebDriver driver, String createdpetition) throws IOException, InterruptedException
	{
		String Petitions_Xpath=Environment("Petitions_Xpath");
		String ExistedPetitions_Xpath=Environment("ExistedPetitions_Xpath");
		String advancedSearch_Drafts_Xpath=Environment("advancedSearch_Drafts_Xpath");
		String multiMoreButtonFiltered_Xpath=Environment("multiMoreButtonFiltered_Xpath");
		String multiMorePetitionNameXpath=Environment("multiMorePetitionNameXpath");
		String publish_DraftPetition_Xpath=Environment("publish_DraftPetition_Xpath");
		String proceed_Publishing_Xpath=Environment("proceed_Publishing_Xpath");
		
		try 
		{
			fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
			
			fl.ClickByXpath(driver, ExistedPetitions_Xpath, "", "Going to Existed Petitiion", "Petition Drafts should be Displayed", "", "");
			
			fl.entervalueByXpath(driver, advancedSearch_Drafts_Xpath, createdpetition, "", "Searching with petition name in drafts to publish", "display petition name search box", "", "");
			
			List<WebElement> buttons = driver.findElements(By.xpath(multiMoreButtonFiltered_Xpath));
			
			if(buttons.size()>1)
			{
				List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
				
				if(names.size()>1)
				{
					for(int i=0;i<names.size();i++)
					{
						if(names.get(i).getText().equals(createdpetition))
						{
							fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+i+"]", "", "", "", "", "");
							fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+i+"]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
							
							fl.ClickByXpath(driver, publish_DraftPetition_Xpath, "", "Publishing the searched petition in drafts", "Publish Proceed Popup has to be displayed", "", "");
							
							fl.ClickByXpath(driver, proceed_Publishing_Xpath, "", "Click on Proceed to publish", "Petition Published successfully", "", "");
						}
					}
				}
			}
			else
			{
				if(buttons.size()==1)
				{
					fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath, "", "", "", "", "");
					fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath, "", "MoreOptions button to be clicked", "Display More Options", "", "");
					
					fl.ClickByXpath(driver, publish_DraftPetition_Xpath, "", "Publishing the searched petition in drafts", "Publish Proceed Popup has to be displayed", "", "");
					
					fl.ClickByXpath(driver, proceed_Publishing_Xpath, "", "Click on Proceed to publish", "Petition Published successfully", "", "");
				}
				else
				{
					fl.disp_Message(driver, "", "There are no petition existed with "+createdpetition, "", "", "");
					System.out.println("There are no petition existed with "+createdpetition);
					
				}
			}
		} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}
	}
	

}
