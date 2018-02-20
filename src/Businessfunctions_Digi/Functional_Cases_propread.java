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
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.testng.Assert;

import com.sun.jna.platform.win32.OaIdl.FUNCDESC;

import FunctionalLibraries_Digi.Functional_Libraries;
import Loggings_Digi.Logs_DigiSurvey;
import Utilities_Digi.ClickEnterRobot;
import Utilities_Digi.DB_Connection_Digi_Candit;
import Utilities_Digi.DB_Connection_Digi_Company;
import Utilities_Digi.Environment_proprties_Read;
import Utilities_Digi.Excel_Utils;
import Utilities_Digi.RefreshRobot;
import Utilities_Digi.ScrollDownRobot;
import Utilities_Digi.UploadFile_Robot;
import Utilities_Digi.clickEndRobot;
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
	
	public int stringToIntegerconvert(String number)
	{
		int result = Integer.parseInt(number);
		return result;
	}
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
	public String companyBase_Petitions(WebDriver driver, String petitionTitle) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String status = null;
		String digi_BaseURL=Environment("digi_BaseURL");
		String petitions_BaseURL_Xpath=Environment("petitions_BaseURL_Xpath");
		String websiteLogin_Xpath=Environment("websiteLogin_Xpath");
		String petitionTitle_searchXpath=Environment("petitionTitle_searchXpath");
		String checkFilteredPetitions_Xpath=Environment("checkFilteredPetitions_Xpath");
		String clickOnPetitionName_Xpath=Environment("clickOnPetitionName_Xpath");
		String searchPetitionButton_Xpath=Environment("searchPetitionButton_Xpath");
		
		String petitionsDisplat_BUO_Xpath=Environment("petitionsDisplat_BUO_Xpath");
		String petitionTitles_BUO_Xpath=Environment("petitionTitles_BUO_Xpath");
		String petitionTitles1_BUO_Xpath=Environment("petitionTitles1_BUO_Xpath");
		String petitionTitles2_BUO_Xpath=Environment("petitionTitles2_BUO_Xpath");
		String loadMoreButton_BUO_Xpath=Environment("loadMoreButton_BUO_Xpath");
		String iframeOfChat_Xpath=Environment("iframeOfChat_Xpath");
		String minimizeChat_BUO_Xpath=Environment("minimizeChat_BUO_Xpath");
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		try 
		{
			
			
			
			fl.invokeApplication(driver, digi_BaseURL, "Chrome", "localhost:4034", "Launching The App", "launch the App Home Page", "Launched the page", "");
			
			fl.ClickByXpath(driver, petitions_BaseURL_Xpath, "", "petition list view", "navigating to petitions list", "", "");
			Thread.sleep(10000);
			fl.ClickByXpath(driver, websiteLogin_Xpath, "", "", "", "", "");
			
			String Parent=driver.getWindowHandle();
			Set<String> set = new HashSet<String>(driver.getWindowHandles());
			for(String tab : set)
			{
				System.out.println("window :"+tab);
			}
			set.remove(Parent);

			driver.switchTo().window(set.iterator().next());
//added lines	==============================================================		
			String secondURL=driver.getCurrentUrl();
			if(!secondURL.contains("/Account/Login"))
			{
				fun_cas.CompanyLogout(driver);
			}
//added lines end  ============================================================			
			driver.close();
			
			driver.switchTo().window(Parent);
			Thread.sleep(10000);
			int listSize=fun_cas.listSize(driver, petitionTitles_BUO_Xpath);
			System.out.println("petition list size="+listSize);
//START==================			
			/*driver.switchTo().frame(0);
			fl.ClickByXpath(driver, minimizeChat_BUO_Xpath, "", "Minimizing the chat", "", "", "");
			driver.switchTo().defaultContent();*/
//========================			
			
			for(int i=1;i<=listSize;i++)
			{
				if(i%4==0)
					fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
				String petiTitle=fl.getTextXPATH(driver, petitionTitles_BUO_Xpath+"["+i+"]", "", "", "Get petitionTile", "", "");
				System.out.println("Petition title "+i+":"+petiTitle);
				if(petitionTitle.equals(petiTitle))
				{
					fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
					//fl.JS_Element_Find(driver, petitionTitles_BUO_Xpath+"["+i+"]");
					Thread.sleep(6000);
					WebElement element = driver.findElement(By.xpath(iframeOfChat_Xpath));
					driver.switchTo().frame(element);
					//driver.switchTo().frame(1);
					int minimize=fun_cas.listSize(driver, minimizeChat_BUO_Xpath);
					if(minimize==1)
					{
						//if(fl.elementDisplayed(driver, minimizeChat_BUO_Xpath, "Checking Chat enabled or not").equals("true"))
							//fl.ClickByXpath(driver, minimizeChat_BUO_Xpath, "", "Minimizing the chat", "", "", "");
					}
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
					if(i%3==0)
					{
						fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
					}
					fl.ClickByXpath(driver,petitionTitles_BUO_Xpath+"["+i+"]", "", "", "Click on Selected Petition", "", "");
					
					Thread.sleep(5000);
					
					String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
					System.out.println("Fund Unavilable style attribute value is: "+fundUna_att);
					if(fundUna_att.contains("display: block;"))
						fl.ClickByXpath(driver, fundUnavilPopup_Xpath, "", "Click on ok funding Collection unavailable popup", "", "", "");
					return "true";
				}
				else
				{
					if(i%6==0 && i<=listSize)
					{
						fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
						fl.ClickByXpath(driver, loadMoreButton_BUO_Xpath, "", "", "Not available click on load more", "", "");
						
					}
					
				}
			}
			
			
			
			
			/*twoTabRobo robo = new twoTabRobo();
			
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
			}*/
			
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
			String address, String org_support, String occupation, String Visa, String DOB, String Salary, String sign, String donate, String QueAns, String dropdownAns, String amountToDonate, String readTerms) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
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
		String occupation_Sign_Xpath=Environment("occupation_Sign_Xpath");
		String visaStatus_select_Xpath=Environment("visaStatus_select_Xpath");
		String birth_Sign_Xpath=Environment("birth_Sign_Xpath");
		String salary_Sign_Xpath=Environment("salary_Sign_Xpath");
		String donateCheck_Sign_Xpath=Environment("donateCheck_Sign_Xpath");
		String quesAns_SignExisted_Xpath=Environment("quesAns_SignExisted_Xpath");
		String dropAns_SignExisted_Xpath=Environment("dropAns_SignExisted_Xpath");
		String amtToDonate_SignExisted_Xpath=Environment("amtToDonate_SignExisted_Xpath");
		String signature_Sign_Xpath=Environment("signature_Sign_Xpath");
		String checkTerms_ExistedUser_Xpath=Environment("checkTerms_ExistedUser_Xpath");
		String submit_ExistedUser_Xpath=Environment("submit_ExistedUser_Xpath");
		
		String occupationEnaExisted_Xpath=Environment("occupationEnaExisted_Xpath");
		String visaEnaExisted_Xpath=Environment("visaEnaExisted_Xpath");
		String birthEnaExisted_Xpath=Environment("birthEnaExisted_Xpath");
		String salaryEnaExisted_Xpath=Environment("salaryEnaExisted_Xpath");
		
		
		try 
		{
			
			if(status.equals("true"))
			{
				String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
				if(fundUna_att.contains("display: block;"))
					fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click ok Button on popup showed not collecting funds right now", "", "", "");
				
				if(fl.elementDisplayed(driver, signPetitionButton_Xpath,"").equals("true"))
				{
					fl.ClickByXpath(driver, signPetitionButton_Xpath, "", "To sign the Petition", "click on Sign Petition", "", "Y");
				
				
					fl.ClickByXpath(driver, signin_ExistedUser_Xpath, "", "Login to Account to sign the Petition", "Login Popup Has to be displayed", "", "");
				
					fl.entervalueByXpath(driver, username_ExistedUser_Xpath, username, "", "", "Username Value to be entered", "", "");
				
					fl.entervalueByXpath(driver, password_ExistedUser_Xpath, password, "", "", "Password to be entered", "", "");
				
					fl.ClickByXpath(driver, login_ExistedUser_Xpath, "", "", "Login Button to be clicked", "", "");
				
					/*if(fl.elementDisplayed(driver, oops_msg_Xpath,"").equals("true"))
					{
						fl.disp_Message(driver, "", "You are Invalid User", "check the create profile while fill the sign form", "", "");
					
						fl.ClickByXpath(driver, back_ExistedUser_Xpath, "", "Go back to fill sign form and creating profile", "Get Back to Sign Petition Form", "", "");
					
//Not an existed user ...while fill the form ...check the create profile field					
					}
					else
					{*/
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
						/*if(!donate.equals(""))
						{
							fl.ClickByXpath(driver, donateCheck_Sign_Xpath, "", "Existed user wants to Donate money", "check Donate Checkbox", "", "");
						}*/
						if(!address.equals(""))
						{
							fl.entervalueByXpath(driver, address_ExistedUser_Xpath, address, "", "Enter Address", "", "", "");
						}
						if(!occupation.equals(""))
						{
							String occu_att=fun_cas.returnAttribute(driver, occupationEnaExisted_Xpath, "style");
							if(occu_att.contains("display: block;"))
								fl.selectDropdownByxpath(driver, occupation_Sign_Xpath, occupation, "", "Selecting occupation", "", "", "");
							else
								fl.disp_Message(driver, "", "Occupation Field Not Enabled", "", "", "");
						}
						if(!Visa.equals(""))
						{
							String Visa_att=fun_cas.returnAttribute(driver, visaEnaExisted_Xpath, "style");
							if(Visa_att.contains("display: block;"))
								fl.selectDropdownByxpath(driver, visaStatus_select_Xpath, Visa, "", "Selecting occupation", "", "", "");
							else
								fl.disp_Message(driver, "", "Occupation Field Not Enabled", "", "", "");
						}
						if(!DOB.equals(""))
						{
							String DOB_att=fun_cas.returnAttribute(driver, birthEnaExisted_Xpath, "style");
							if(DOB_att.contains("display: block;"))
								fl.entervalueByXpath(driver, birth_Sign_Xpath, DOB, "", "Enter Address", "", "", "");
							
							else
								fl.disp_Message(driver, "", "Occupation Field Not Enabled", "", "", "");
						}
						if(!Salary.equals(""))
						{
							String Salary_att=fun_cas.returnAttribute(driver, salaryEnaExisted_Xpath, "style");
							if(Salary_att.contains("display: block;"))
								fl.entervalueByXpath(driver, salary_Sign_Xpath, Salary, "", "Enter Address", "", "", "");
							
							else
								fl.disp_Message(driver, "", "Occupation Field Not Enabled", "", "", "");
						}
						if(!sign.equals(""))
						{
							fl.ClickByXpath(driver, signature_Sign_Xpath, "", "", "", "", "");
							select_delete sel_del=new select_delete();
							fl.entervalueByXpath(driver, signature_Sign_Xpath, sign, "", "", "", "", "");
						}
						if(!QueAns.equals(""))
						{
							
							int textQue=fun_cas.listSize(driver, quesAns_SignExisted_Xpath);
							if(textQue>=1)
							{
								for(int i=1;i<=textQue;i++)
								{
									
										System.out.println("QueAns"+QueAns);
										fl.JS_Element_Find(driver, quesAns_SignExisted_Xpath+"["+i+"]");
										fl.entervalueByXpath(driver, quesAns_SignExisted_Xpath+"["+i+"]", QueAns, "", "Enter Answer Text in Text Field", "", "", "");
								
								}
							}
						}
						if(dropdownAns!="")
						{
							
							int dropList=fun_cas.listSize(driver, dropAns_SignExisted_Xpath);
							System.out.println("Dropdown Questions List: "+dropList);
							
							if(dropList>=1)
							{
								for(int i=1;i<=dropList;i++)
								{
										System.out.println("QueAns"+QueAns);
										fl.JS_Element_Find(driver, dropAns_SignExisted_Xpath+"["+i+"]");
										fl.selectDropdownByxpath(driver, dropAns_SignExisted_Xpath+"["+i+"]", dropdownAns, "", "Enter Answer Text in Text Field", "", "", "");
								}
							}
						}
						if(amountToDonate!="")
						{
							
							int dropList=fun_cas.listSize(driver, amtToDonate_SignExisted_Xpath);
							System.out.println("amountDonate Later Questions List: "+dropList);
							if(dropList==1)
							{
								fl.JS_Element_Find(driver, amtToDonate_SignExisted_Xpath);
								fl.entervalueByXpath(driver, amtToDonate_SignExisted_Xpath, amountToDonate, "", "Enter Answer Numeric in Text Field", "", "", "");
							}
							
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
					/*}*/
					
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
	public List<String> getFirstNameAndLastName(WebDriver driver) throws IOException, InterruptedException
	{
		List<String> myProf_Info=new ArrayList<>();
		String dashboard_webSite_Xpath=Environment("dashboard_webSite_Xpath");
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String myProfile_Company_Xpath=Environment("myProfile_Company_Xpath");
		String myProfile5_Company_Xpath=Environment("myProfile5_Company_Xpath");
		String name_MPV_Xpath=Environment("name_MPV_Xpath");
		String occupation_MPV_Xpath=Environment("occupation_MPV_Xpath");
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		String Parent=driver.getWindowHandle();
		fl.ClickByXpath(driver, dashboard_webSite_Xpath, "", "Click on Dashboard", "", "", "");
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		fl.ClickByXpath(driver, logout_img_Xpath, "", "Click on Logout Image", "", "", "");
		int logout_options=fun_cas.listSize(driver, logoutOptions_Xpath);
		if(logout_options==6)
		{
			fl.ClickByXpath(driver, myProfile_Company_Xpath, "", "Click on MyProfile", "", "", "");
		}
		else
		{
			if(logout_options==5)
			{
				fl.ClickByXpath(driver, myProfile5_Company_Xpath, "", "Click on MyProfile", "", "", "");
			}
			else
			{
				if(logout_options==4)
				{
					fl.ClickByXpath(driver, myProfile5_Company_Xpath, "", "Click on MyProfile", "", "", "");
				}
			}
			
		}
		String name=fl.getTextXPATH(driver, name_MPV_Xpath, "", "Get Name Text", "", "", "");
		fl.disp_Message(driver, "", "", "", name, "");
		myProf_Info.add(name);
		String occupation=fl.getTextXPATH(driver, occupation_MPV_Xpath, "", "Get Occupation", "", "", "");
		fl.disp_Message(driver, "", "", "", occupation, "");
		myProf_Info.add(occupation);
		driver.close();
		driver.switchTo().window(Parent);
		return myProf_Info;
		
	}
	public WebDriver petitionSign_NewUser(WebDriver driver, String status, String firstname, String lastname,String emailID, String countrycode,
			String phoneNo, String Address, String occupation, String OrgFor, String visa, String dob, String Salary,String sign, String createProfile,String password, String donate, String QueAns, 
			String dropdownAns, String amountToDonate, String readTerms) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String petitions_BaseURL_Xpath=Environment("petitions_BaseURL_Xpath");
		String petitionTitle_searchXpath=Environment("petitionTitle_searchXpath");
		String checkFilteredPetitions_Xpath=Environment("checkFilteredPetitions_Xpath");
		String clickOnPetitionName_Xpath=Environment("clickOnPetitionName_Xpath");
		String searchPetitionButton_Xpath=Environment("searchPetitionButton_Xpath");
		String websiteLogin_Xpath=Environment("websiteLogin_Xpath");
		String signPetitionButton_Xpath=Environment("signPetitionButton_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
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
		String birth_SignNew_Xpath=Environment("birth_SignNew_Xpath");
		String salary_SignNew_Xpath=Environment("salary_SignNew_Xpath");
		String signature_SignNew_Xpath=Environment("signature_SignNew_Xpath");
		String createURprofile_SignNew_Xpath=Environment("createURprofile_SignNew_Xpath");
		String enterpassword_SignNew_Xpath=Environment("enterpassword_SignNew_Xpath");
		String donate_SignNew_Xpath=Environment("donate_SignNew_Xpath");
		String quesAns_SignNew_Xpath=Environment("quesAns_SignNew_Xpath");
		String dropAns_SignNew_Xpath=Environment("dropAns_SignNew_Xpath");
		String amtToDonate_SignNew_Xpath=Environment("amtToDonate_SignNew_Xpath");
		String readPrivacyTerms_SignNew_Xpath=Environment("readPrivacyTerms_SignNew_Xpath");
		String submit_SignNew_Xpath=Environment("submit_SignNew_Xpath");
		String occupationEna_Xpath=Environment("occupationEna_Xpath");
		String visaEna_Xpath=Environment("visaEna_Xpath");
		String birthEna_Xpath=Environment("birthEna_Xpath");
		String salaryEna_Xpath=Environment("salaryEna_Xpath");
		
		
		
		try 
		{
			
			if(status.equals("true"))
			{
				RefreshRobot refreshrobo= new RefreshRobot();
				
				String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
				if(fundUna_att.contains("display: block;"))
					fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on fund collection option unavailable popup", "", "", "");
				Thread.sleep(3000);
				/*String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
				if(fundUna_att.contains("display: block;"))
					fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click ok Button on popup showed not collecting funds right now", "", "", "");
				*/
				if(fl.elementDisplayed(driver, signPetitionButton_Xpath,"").equals("true"))
				{
					/*fl.ClickByXpath(driver, websiteLogin_Xpath, "", "", "", "", "");
					driver.switchTo().defaultContent();*/
					
					fl.ClickByXpath(driver, signPetitionButton_Xpath, "", "To sign the Petition", "click on Sign Petition", "", "Y");
				
					
					
					Thread.sleep(3000);
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
					if(!occupation.equals(""))
					{
						String occu_att=fun_cas.returnAttribute(driver, occupationEna_Xpath, "style");
						if(occu_att.contains("display: block;"))
							fl.selectDropdownByxpath(driver, occupation_SignNew_Xpath, occupation, "", "Selecting Occupation", "", "", "");
						else
							fl.disp_Message(driver, "", "Occupation fields Not Existed in Form", "", "", "");
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
					if(!visa.equals(""))
					{
						String visa_att=fun_cas.returnAttribute(driver, visaEna_Xpath, "style");
						if(visa_att.contains("display: block;"))
							fl.selectDropdownByxpath(driver, visaStatus_SignNew_select_Xpath, visa, "", "Selecting Visa Type", "", "", "");
						else
							fl.disp_Message(driver, "", "Visa fields Not Existed in Form", "", "", "");
					}
					if(!dob.equals(""))
					{
						String dob_att=fun_cas.returnAttribute(driver, birthEna_Xpath, "style");
						if(dob_att.contains("display: block;"))
							fl.entervalueByXpath(driver, birth_SignNew_Xpath, dob, "", "Enter Birth Month And Year", "", "", "");
						else
							fl.disp_Message(driver, "", "DOB fields Not Existed in Form", "", "", "");
					}
					if(!Salary.equals(""))
					{
						String Salary_att=fun_cas.returnAttribute(driver, salaryEna_Xpath, "style");
						if(Salary_att.contains("display: block;"))
							fl.entervalueByXpath(driver, salary_SignNew_Xpath, Salary, "", "Enter Birth Month And Year", "", "", "");
						else
							fl.disp_Message(driver, "", "DOB fields Not Existed in Form", "", "", "");
						
					}
					if(!sign.equals(""))
					{
						fun_cas.clearTextfield(driver, signature_SignNew_Xpath, sign);
						fl.entervalueByXpath(driver, signature_SignNew_Xpath, sign, "", "", "Signature Value to be entered", "", "");
					}
					if(!QueAns.equals(""))
					{
						
						int textQue=fun_cas.listSize(driver, quesAns_SignNew_Xpath);
						if(textQue>=1)
						{
							for(int i=1;i<=textQue;i++)
							{
									System.out.println("QueAns"+QueAns);
									fl.JS_Element_Find(driver, quesAns_SignNew_Xpath+"["+i+"]");
									fl.entervalueByXpath(driver, quesAns_SignNew_Xpath+"["+i+"]", QueAns, "", "Enter Answer Text in Text Field", "", "", "");
							
							}
						}
					}
					if(dropdownAns!="")
					{
						
						int dropList=fun_cas.listSize(driver, dropAns_SignNew_Xpath);
						System.out.println("Dropdown Questions List: "+dropList);
						
						if(dropList>=1)
						{
							for(int i=1;i<=dropList;i++)
							{
								
									System.out.println("QueAns"+QueAns);
									fl.JS_Element_Find(driver, dropAns_SignNew_Xpath+"["+i+"]");
									fl.selectDropdownByxpath(driver, dropAns_SignNew_Xpath+"["+i+"]", dropdownAns, "", "Enter Answer Text in Text Field", "", "", "");
								
							}
						}
					}
					if(amountToDonate!="")
					{
						
						int dropList=fun_cas.listSize(driver, amtToDonate_SignNew_Xpath);
						System.out.println("amountDonate Later Questions List: "+dropList);
						
						if(dropList==1)
						{
							fl.JS_Element_Find(driver, amtToDonate_SignNew_Xpath);
							fl.entervalueByXpath(driver, amtToDonate_SignNew_Xpath, amountToDonate, "", "Enter Answer Numeric in Text Field", "", "", "");
						}
						
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
		
		return driver;
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
				fl.JS_Element_Find(driver, fundingButton_Xpath);
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
			String currentUrl=driver.getCurrentUrl();
			if(!currentUrl.contains("/Account/Login"))
			{
				fun_cas.CompanyLogout(driver);
			}
			fun_cas.clearTextfield(driver, Company_EmailIDxpath, EmailID);
			//Thread.sleep(10000);
			
			
				fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, EmailID, "Enter EmailId", "", "", "");
				Logs_DigiSurvey.info("Entering username");
			
				fun_cas.clearTextfield(driver, Company_Passwordxpath, password);
				Thread.sleep(5000);
				fl.entervalueByXpath(driver, Company_Passwordxpath, password, password, "Enter Password", "", "", "");
				Logs_DigiSurvey.info("Entering password");

			
			// fl.ClickByID(driver, Environment("CompanyLoginbuttonID"), "",
			// "", "", "", "");
			//Thread.sleep(10000);
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
		String logoutOptions_Xpath= Environment("logoutOptions_Xpath");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String companyProfile5_Xpath=Environment("companyProfile5_Xpath");
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
		String noAddedBank_CP_Xpath=Environment("noAddedBank_CP_Xpath");
		String bankAccounts_CP_Xpath=Environment("bankAccounts_CP_Xpath");
		String editBankAccount_CP_Xpath=Environment("editBankAccount_CP_Xpath");
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
		
		String bankName_ValidCP_Xpath=Environment("bankName_ValidCP_Xpath");
		String routingNo_ValidCP_Xpath=Environment("routingNo_ValidCP_Xpath");
		String accountNO_ValidCP_Xpath=Environment("accountNO_ValidCP_Xpath");
		String accountType_ValidCP_Xpath=Environment("accountType_ValidCP_Xpath");
		String phNo_ValidCP_Xpath=Environment("phNo_ValidCP_Xpath");
		String bankAddress_ValidCP_Xpath=Environment("bankAddress_ValidCP_Xpath");
		
		
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
//==================START		
			fl.JS_Element_Find(driver, Company_LogoXPATH);
			fl.ClickByXpath(driver, Company_LogoXPATH, "", "Filling company Profile", "click on company logo", "", "");
			int logoutOptions = fun_cas.listSize(driver, logoutOptions_Xpath);
			if(logoutOptions==6)
				fl.ClickByXpath(driver, companyProfile_Xpath, "", "", "click on company profile", "", "");
			else
				if(logoutOptions==5)
					fl.ClickByXpath(driver, companyProfile5_Xpath, "", "", "click on company profile", "", "");
			
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
//====================START END		
						/*if(i>1)
						fl.ClickByXpath(driver, subIndustryClick_CP_Xpath, "", "", "Click on SubIndustry", "", "");*/
//====================START		
						Thread.sleep(3000);
						fl.JS_Element_Find(driver, active_subIndustry_Xpath+"["+1+"]"+deactivate_subIndustry_Xpath);
						fl.ClickByXpath(driver, active_subIndustry_Xpath+"["+1+"]"+deactivate_subIndustry_Xpath, "", "", "", "", "");
//=====================START END						
						/*fl.ClickByXpath(driver, subIndustryClick_CP_Xpath, "", "", "Click on SubIndustry", "", "");*/
//===================START						
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
//===================START END				
				//WebElement sameAsReg = driver.findElement(By.xpath(street1_Mail_CP_Xpath));
//==================START						
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
	
//===================START END			
			/*failed_Fields_comInfo= fun_cas.listSize(driver, failed_Fields_Xpath);
//validation of Company Information
			if(failed_Fields_comInfo>=1)
			{
				fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
			}
			else
			{*/
//=================START=====		
				alert=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
				System.out.println("alert text is :"+alert);
				fl.disp_Message(driver, "", "", "Message:"+alert, "", "");
//====================	START END			
			/*}
			//Contacts
			if(failed_Fields_comInfo==0)
			{*/
//=======================	START			
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
//=========================	START END			
				/*failed_Fields_contacts= fun_cas.listSize(driver, failed_Fields_Xpath);
				if(failed_Fields_contacts>=1)
				{
					fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
				}
				else
				{*/
//========================	START			
					alert_contacts=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
					fl.disp_Message(driver, "", "", "Message:"+alert_contacts, "", "");
//====================		START END			
				/*}
				//Bank Accounts
				if(failed_Fields_contacts==0)
				{*/
//======================START						
					fl.JS_Element_Find(driver, bankAccounts_CP_Xpath);
					fl.ClickByXpath(driver, bankAccounts_CP_Xpath, "", "go to Bank Accounts Tab", "", "", "Y");
					
					String addEnabled=fl.elementDisplayed(driver, addBankAccount_CP_Xpath, "Checking Add Button Enabled or Not");
					if(addEnabled.equals("true"))
					{
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
//=============================START END						
					//fl.JS_Element_Find(driver, saveBank_CP_Xpath);
//======================START						
						fl.ClickByXpath(driver, saveBank_CP_Xpath, "", "saving bank account details", "", "", "Y");
						Thread.sleep(3000);
//=======================START END					
					/*failed_Fields_bank= fun_cas.listSize(driver, failed_Fields_Xpath);
					if(failed_Fields_bank>=1)
					{
						fl.disp_MessageFailed(driver, "", "", "ERROR:Mandidatory Fields Are not Filling", "FAILED", "Y");
					}
					else
					{*/
//==========START						
						alert_bank=fl.getTextXPATH(driver, AlertMessage_Xpath, "", "", "Get Alert Message", "", "");
						fl.disp_Message(driver, "", "", "Message:"+alert_bank, "", "");
					}
					else
					{
						String editEnabled=fl.elementDisplayed(driver, editBankAccount_CP_Xpath, "Checking edit Enbled or not");
						if(editEnabled.equals("true"))
						{
							fl.ClickByXpath(driver, editBankAccount_CP_Xpath, "", "Click on Edit", "", "", "");
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
							fl.ClickByXpath(driver, saveBank_CP_Xpath, "", "saving bank account details", "", "", "Y");
							Thread.sleep(3000);
						}
					}
//==============START END						
					/*}
					//Documents
					//fl.JS_Element_Find(driver, documents_CP_Xpath);
					if(failed_Fields_bank==0)
					{*/
//============================	START					
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
//=============================	START END					
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
							//fl.ClickByXpath(driver, editCompanyPublicView_CP_Xpath, "", "", "edit company public view", "", "");
							if(image1path!="")
							{
								if(imag1_status.equals("false"))
								{
									/*fl.ClickByXpath(driver, Image1del_CP_Xpath, "", "", "", "", "");*/
									fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									//fl.ClickByXpath(driver, galleryImages_CPValid_Xpath+"[1]", "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image1path);
								}
								else
								{
									fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image1path);
								}
							}
							if(image2path!="")
							{
								if(imag2_status.equals("false"))
								{
									/*fl.ClickByXpath(driver, Image2del_CP_Xpath, "", "", "", "", "");*/
									fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									//fl.ClickByXpath(driver, galleryImages_CPValid_Xpath+"[2]", "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image2path);
								}
								else
								{
									fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									upload.uploadFile(image2path);
								}
							}
							if(image3path!="")
							{
								if(imag3_status.equals("false"))
								{
									/*fl.ClickByXpath(driver, Image3del_CP_Xpath, "", "", "", "", "");*/
									fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									//fl.ClickByXpath(driver, galleryImages_CPValid_Xpath+"[2]", "", "", "image1 to be uploaded", "", "");
									upload.uploadFile(image3path);
								}
								else
								{
									fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image3 to be uploaded", "", "");
									//fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "image2 to be uploaded", "", "");
									upload.uploadFile(image3path);
								}
							}
							if(overview!="")
							{
								fl.JS_Element_Find(driver, overview_CP_Xpath);
								fl.ClickByXpath(driver, overview_CP_Xpath, "", "", "Overview field selected & cleared", "", "");
								select_delete sel_del = new select_delete();
								Thread.sleep(3000);
								fl.entervalueByXpath(driver, overview_CP_Xpath, overview, "", "", "overview to be entered", "", "");
							}
							if(background!="")
							{
								fl.JS_Element_Find(driver, background_CP_Xpath);
								fl.ClickByXpath(driver, background_CP_Xpath, "", "", "Overview field selected & cleared", "", "");
								select_delete sel_del = new select_delete();
								Thread.sleep(3000);
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
		String galleryImages1_CPValid_Xpath=Environment("galleryImages1_CPValid_Xpath");
		String galleryImages2_CPValid_Xpath=Environment("galleryImages2_CPValid_Xpath");
		String galleryImages3_CPValid_Xpath=Environment("galleryImages3_CPValid_Xpath");
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
								fun_cas.attributeValue(driver, galleryImages1_CPValid_Xpath, attribute_CPValid_Xpath, image1);
							if(image2!="")
								fun_cas.attributeValue(driver, galleryImages2_CPValid_Xpath, attribute_CPValid_Xpath, image2);
							if(image3!="")
								fun_cas.attributeValue(driver, galleryImages3_CPValid_Xpath, attribute_CPValid_Xpath, image3);
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
	        	if(ima1.contains(excel)||ima1.contains("data:image/png;base64"))
	        	{
	        		if(ima1.contains("data:image/png;base64"))
	        			fl.disp_Message(driver, "", "", "image uploaded succesfully: "+excel, "data:image/png;base64", "");
	        		else
	        			if(ima1.contains(excel))
	        				fl.disp_Message(driver, "", "", "image uploaded succesfully: "+excel, ima1, "");
	        	}
	        	else
	        	{
	        		fl.disp_MessageFailed(driver, "", "", "image upload failed: "+excel, "Error", "Y");
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
	public String returnAttribute(WebDriver driver, String xpath, String attribute)
	{
		WebElement element = driver.findElement(By.xpath(xpath));
		String ima1 = element.getAttribute(attribute);
		return ima1;
	}
	public String returnImagenameattributeValue(WebDriver driver, String xpath, String attribute)
	{
		WebElement element = driver.findElement(By.xpath(xpath));
		String ima1 = element.getAttribute(attribute);
		if(ima1.contains(".jpg"))
		{
			return ima1;
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
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String myProfile_Company_Xpath=Environment("myProfile_Company_Xpath");
		String myProfile5_Company_Xpath=Environment("myProfile5_Company_Xpath");
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
		int logOptions=fun_case.listSize(driver, logoutOptions_Xpath);
		if(logOptions==6)
			fl.ClickByXpath(driver, myProfile_Company_Xpath, "", "", "Click on My Profile", "", "");
		else
			if(logOptions==5)
				fl.ClickByXpath(driver, myProfile5_Company_Xpath, "", "", "Click on My Profile", "", "");
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
			
			fl.entervalueByXpath(driver, CaptchaIndivial_Xpath, "", "", "enter captcha", "", "", "");
			Thread.sleep(10000);
			fl.ClickByXpath(driver, AgreeIndividual_Xpath, "", "Accept Terms & Conditions", "", "", "");
			
			fl.ClickByXpath(driver, RegisterIndividual_Xpath, "", "click on Register", "", "", "");

			String Verify_Code_Candit = DB_Connection_Digi_Candit.Db_Connect(emailid);
			System.out.println(Verify_Code_Candit);

			fl.entervalueByXpath(driver, verifyTextIndividual_Xpath, Verify_Code_Candit, "", "", "",
					"", "");

			fl.ClickByXpath(driver, verifyIndividual_Xpath, "", "", "", "", "Y");
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
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();

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
			fl.JS_Element_Find(driver, SetupRoles_Xpath);
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
			fl.JS_Element_Find(driver, SetupDesignation_Xapth);
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
					fl.JS_Element_Find(driver, SetupUsers_DBiA_Xapth);
					fl.ClickByXpath(driver, SetupUsers_DBiA_Xapth, "", "Click on Users", "", "", "");
				}
				else
				{
					fl.ClickByXpath(driver, Setup_Xpath, "", "Setup Menu", "", "", "");
					fl.JS_Element_Find(driver, SetupUsers_Xapth);
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
			fl.JS_Element_Find(driver, SetupSurveyCategeories_Xpath);
			fl.ClickByXpath(driver, SetupSurveyCategeories_Xpath, "", "click on surveyCategeories", "", "", "");
			
			fl.ClickByXpath(driver, AdvanceSearch_Xpath, "", "click on advanced Search, checking once in categoryList whether the mentioned categeory existed or not", "", "", "");
			
			fl.entervalueByXpath(driver, SearchSurveyCategeory_Xpath, new_or_existedName, "enter the categoey name "+new_or_existedName, "", "", "", "");
			if(fl.findByXpath(driver, Environment("NoSurveyCategeory_Xpath")).getText().contains("No"))//No Survey Categories
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "No Results found, click on new", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "enter name", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "save the created Survey Category", "", "", "");
				
				Thread.sleep(20000);
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
			}
			else			
			if(fl.findByXpath(driver, Environment("SurveyCategeoryName_Xapth")).getText().equals(new_or_existedName))
			{
				fl.ClickByXpath(driver, EditSurveyCategeory_Xpath, "", "Edit Survey Categeory", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, UpdatedName, "update the Category name", "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter the notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "update SurveyCategory", "", "", "");
				
				Thread.sleep(20000);
				
				//fl.ClickByXpath(driver, CancelSurveyCategeory_Xapth, "", "", "", "", "");
				
			}
			else
			{
				fl.ClickByXpath(driver, NewSurveyCategeory_Xpath, "", "No Results found, click on new", "", "", "");
				
				fl.entervalueByXpath(driver, NameSurveyCategeory_Xpath, new_or_existedName, "enter name", "", "", "", "");
			
				fl.entervalueByXpath(driver, NotesSurveyCategeory_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, saveSurveyCategeory_Xpath, "", "save SurveyCategory", "", "", "");
				
				Thread.sleep(20000);
				
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
	public String create_QuestionGroup(WebDriver driver, String name, String Notes) throws IOException, InterruptedException
	{
		//add these to properties file
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String Setup_Xpath=Environment("Setup_Xpath");
		String SurveySetup_QueGrp_Xpath=Environment("SurveySetup_QueGrp_Xpath");
		String advancedSearch_CQG_Xpath=Environment("advancedSearch_CQG_Xpath");
		String search_CQG_Xpath=Environment("search_CQG_Xpath");
		String noRecords_CQG_Xpath=Environment("noRecords_CQG_Xpath");
		String existedRecords_CQG_Xpath=Environment("existedRecords_CQG_Xpath");
		String new_CQG_Xpath=Environment("new_CQG_Xpath");
		String name_CQG_Xpath=Environment("name_CQG_Xpath");
		String notes_CQG_Xpath=Environment("notes_CQG_Xpath");
		String save_CQG_Xpath=Environment("save_CQG_Xpath");
		
			fl.ClickByXpath(driver, Setup_Xpath, "", "go to setup menu", "", "", "");
			
			Thread.sleep(1000);
			fl.JS_Element_Find(driver, SurveySetup_QueGrp_Xpath);
			fl.ClickByXpath(driver, SurveySetup_QueGrp_Xpath, "", "click on SurveySetup> QuestionGroups", "", "", "");
			fl.ClickByXpath(driver, advancedSearch_CQG_Xpath, "", "Click on AdvancedSearch", "", "", "");
			fl.entervalueByXpath(driver, search_CQG_Xpath, name, "", "search in name Box with :"+name, "", "", "");
			int noRec=fun_cas.listSize(driver, noRecords_CQG_Xpath);
			if(noRec==1)
			{
				String NoRecMsg=fl.getTextXPATH(driver, noRecords_CQG_Xpath, "", "Get No Records Found Message", "", "", "");
				fl.disp_Message(driver, "", "", "No matching records found", NoRecMsg, "");
				fl.JS_Element_Find(driver, new_CQG_Xpath);
				fl.ClickByXpath(driver, new_CQG_Xpath, "", "Click on New Button", "", "", "");
				fl.entervalueByXpath(driver, name_CQG_Xpath, name, "", "Enter Question Group Name :"+name, "", "", "");
				fl.entervalueByXpath(driver, notes_CQG_Xpath, Notes, "", "Enter Question Group Notes:"+Notes, "", "", "");
				fl.ClickByXpath(driver, save_CQG_Xpath, "", "Click on Save Button", "", "", "");
				return "created";
			}
			else
			{
				
				if(noRec>=1)
				{
					for(int i=1;i<=noRec;i++)
					{
						String QueGrpName=fl.getTextXPATH(driver, existedRecords_CQG_Xpath, "", "Compare Existed Names With Required Name", "", "", "");
						if(QueGrpName.equals(name))
						{
							fl.disp_Message(driver, "", "Already Existed Question Group:"+name, "", "", "");
							return "existed";
						}
						else
						{
							if(i==noRec)
							{
								fl.disp_Message(driver, "", "", "No matching records found, need to create Question Group Name", "", "");
								fl.JS_Element_Find(driver, new_CQG_Xpath);
								fl.ClickByXpath(driver, new_CQG_Xpath, "", "Click on New Button", "", "", "");
								fl.entervalueByXpath(driver, name_CQG_Xpath, name, "", "Enter Question Group Name :"+name, "", "", "");
								fl.entervalueByXpath(driver, notes_CQG_Xpath, Notes, "", "Enter Question Group Notes:"+Notes, "", "", "");
								fl.ClickByXpath(driver, save_CQG_Xpath, "", "Click on Save Button", "", "", "");
								return "created";
							}
						}
					}
				}
				
			}
			return "null";
		
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
			fl.JS_Element_Find(driver, SetupGroups_Xpath);
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
					Thread.sleep(20000);
					//select subgroup
					//fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "", "", "", "", "");
					fl.ClickByXpath(driver, NewbuttonSurveyGroup_Xpath, "", "click on new button in Groups", "", "", "");
					
					fl.selectDropdownByxpath(driver, subGroup_SelectXpath, subGroup, "select subgroup which you have created now", "", "", "", "");				
				}
				
				fl.entervalueByXpath(driver, NameSurveyGroup_Xpath, new_or_existedName, "enter the Group name:"+new_or_existedName, "", "", "", "");
				
				fl.entervalueByXpath(driver, NotesSurveyGroup_Xpath, Notes, "enter Notes", "", "", "", "");
				
				fl.ClickByXpath(driver, SaveSurveyGroup_Xpath, "", "Save the created groups", "", "", "");
				
				Thread.sleep(20000);
				
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
					
					Thread.sleep(20000);
				
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
						
						Thread.sleep(20000);
				
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
			, String SurveyGroupNotes, String SurveyGroupSubNotes, String SurveyName, String Description, String fileLocation 
			, String languages, String allowAnonym, String TemplateName, String landingPage, String Que_Control) throws IOException, InterruptedException
	{
		Functional_Cases_propread func_case = new Functional_Cases_propread();
		
		//String CreateSurvey_Questions=Environment("CreateSurvey_Questions");
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
		String languages_CS_Xpath=Environment("languages_CS_Xpath");
		String lang_AlreadySel_CS_Xpath=Environment("lang_AlreadySel_CS_Xpath");
		String lang_sel_CS_Xpath=Environment("lang_sel_CS_Xpath");
		String langText_CS_Xpath=Environment("langText_CS_Xpath");
		String langSel_CS_Xpath=Environment("langSel_CS_Xpath");
		String allowAnonym_CS_Xpath=Environment("allowAnonym_CS_Xpath");
		String SaveAsTemplate_Xpath=Environment("SaveAsTemplate_Xpath");
		String TemplateName_Xpath=Environment("TemplateName_Xpath");
		String SaveCreateSurevyAsTemplate_Xpath=Environment("SaveCreateSurevyAsTemplate_Xpath");
		String landingCheck_CS_Xpath=Environment("landingCheck_CS_Xpath");
		String landingText_CS_Xpath=Environment("landingText_CS_Xpath");
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
				
				Thread.sleep(10000);
				
				fl.ClickByXpath(driver, Survey_Xpath, "", "after creating groups, Go to survey menu", "", "", "");
				
				fl.ClickByXpath(driver, createSurvey_Xpath, "", "click on create survey", "", "", "");
				
				/*Assert.fail("Mentioned SurveyGroup Not Existed");*/
			}
//TEMPLATE IN MASTERS REMOVED============================			
			/*if(TemplateName!="")
			{
				String exist=func_case.checkTemplateList(driver, TemplateName);
				if(exist.equals("false"))
				{
					fl.disp_Message(driver, "", "", "Templatename Already Existed", "", "");
					Assert.fail();
				}
				
			}*/
//========================================================			
//Checking Question group Existed or not before creating survey
			if(Que_Control!="")
			{
			  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
			  Excel_Utils RC = new Excel_Utils(Environment("Excel"));
			  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
			  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
			  String[] create_Que_ele=new String[create_Que_col]; 
			  
			  int Que_No=1;
			 
			  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
			  { 
				 
				  System.out.println("QueNO: "+Que_No);
				  System.out.println("for Loop" );
				  System.out.println(Que_Control);
				  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
				  if (Que_Control
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
					  func_case.create_QuestionGroup(driver, 
							  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")], 
							  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroupNotes")]);
				  } 
			  }

			}
//===========END			
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Surveys Menu clicked", "", "", "");
			
			fl.ClickByXpath(driver, createSurvey_Xpath, "", "click on create Survey", "", "", "");
			
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
			
//LANGUAGE SELECTION::
			fl.ClickByXpath(driver, languages_CS_Xpath, "", "Click on Languages Dropdown", "", "", "");
			int selected_Lang=func_case.listSize(driver, lang_AlreadySel_CS_Xpath);
			if(selected_Lang>=1)
			{
				for(int i=1;i<=selected_Lang;i++)
				{
					fl.JS_Element_Find(driver, lang_AlreadySel_CS_Xpath+"[1]/span");
					fl.ClickByXpath(driver, lang_AlreadySel_CS_Xpath+"[1]/span", "", "Unselect Already Selected Languages", "", "", "");
				}
			}
			int select =func_case.listSize(driver, lang_sel_CS_Xpath);
			System.out.println("Total Languages in Dropdown="+select);
			for(int i=1;i<=select;i++)
			{
				String lang=fl.getTextXPATH(driver, lang_sel_CS_Xpath+"["+i+"]"+langText_CS_Xpath, "", "Selecting Languages", "", "", "");
				if(languages.contains(lang))
				{
					
					fl.JS_Element_Find(driver, lang_sel_CS_Xpath+"["+i+"]"+langSel_CS_Xpath);
					String classAtt=func_case.returnAttribute(driver, lang_sel_CS_Xpath+"["+i+"]", "class");
					if(!classAtt.contains("selected"))
						fl.ClickByXpath(driver, lang_sel_CS_Xpath+"["+i+"]"+langSel_CS_Xpath, "", "Click on checkboxes", "", "", "");
					else
						fl.disp_Message(driver, "", "", "", "Language Already Selected", "");
				}
			}
			
//==========END		
			if(allowAnonym!="")
			{
				fl.ClickByXpath(driver, allowAnonym_CS_Xpath, "", "Check Allow Anonymous", "", "", "");
			}
			if(TemplateName!="")
			{
			
				fl.ClickByXpath(driver, SaveAsTemplate_Xpath, "", "want to save Template", "", "", "");
			
				fl.entervalueByXpath(driver, TemplateName_Xpath,TemplateName, "enter the template name", "", "", "", "");
			}
			if(landingPage!="")
			{
				fl.ClickByXpath(driver, landingCheck_CS_Xpath, "", "Check Landing Page Checkbox", "", "", "");
				fl.entervalueByXpath(driver, landingText_CS_Xpath, landingPage, "", "Enter Landing Page URL"+landingPage, "", "", "");
			}
		/*} 
		catch (InterruptedException e) 
		{	
			fl.disp_Message(driver, "", "error occured", "", "", "Y");
			e.printStackTrace();
			Logs_DigiSurvey.info(e.getMessage());
		}*/
		
		
	}
	public String checkTemplateButton_CreateSurvey(WebDriver driver, String template) throws IOException, InterruptedException
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
			fl.selectDropdownByxpath(driver, select_CS_Xpath, template, "", "", "Selecting Template", "", "Y");
			/*fl.ClickByXpath(driver, Done_CS_Xpath, "", "", "Click Done to add questions to create survey", "", "");*/
			return "true";
		}
		else
		{
			fl.ClickByXpath(driver, cancel_CS_Xpath, "", "", "close the templates list start creating survey", "", "");
			return "false";
		}
	}
	public String checkTemplateList(WebDriver driver, String template) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String Setup_Xpath=Environment("Setup_Xpath");
		String templates_SetupCS_Xpath=Environment("templates_SetupCS_Xpath");
		String advancedSearch_SetupCS_Xpath=Environment("advancedSearch_SetupCS_Xpath");
		String searchTemp_SetupCS_Xpath=Environment("searchTemp_SetupCS_Xpath");
		String noRecords_SetupCS_Xpath=Environment("noRecords_SetupCS_Xpath");
		String template_SetupCS_Xpath=Environment("template_SetupCS_Xpath");
		
		fl.JS_Element_Find(driver, Setup_Xpath);
		fl.ClickByXpath(driver, Setup_Xpath, "", "Click on Setup Menu", "", "", "");
		fl.JS_Element_Find(driver, templates_SetupCS_Xpath);
		fl.ClickByXpath(driver, templates_SetupCS_Xpath, "", "Click on Survey Templates ", "", "", "");
		fl.ClickByXpath(driver, advancedSearch_SetupCS_Xpath, "", "Click on Advanced Search Button", "", "", "");
		fl.entervalueByXpath(driver, searchTemp_SetupCS_Xpath, template, "", "Enter Tempplate Name in Search Box", "", "", "");
		
		int noRec=fun_cas.listSize(driver, noRecords_SetupCS_Xpath);
		if(noRec==1)
		{
			String noMsg=fl.getTextXPATH(driver, noRecords_SetupCS_Xpath, "", "Get No Records Message", "", "", "");
			fl.disp_Message(driver, "", "", "No matching records found", noMsg, "");
			return "true";
		}
		else
		{
			if(noRec>1)
			{
				int tempList=fun_cas.listSize(driver, template_SetupCS_Xpath);
				for(int i=1;i<=tempList;i++)
				{
					String tempName=fl.getTextXPATH(driver, template_SetupCS_Xpath+"["+i+"]", "", "Get Template Name Text And Compare", "", "", "");
					if(tempName.equals(template))
					{
						fl.disp_Message(driver, "", "Template Name Already Existed, Rename The Template ", "", "", "");
						return "false";
					}
				}
			}
		}
		return "null";
	}
	public void checkSurveyTemplates(WebDriver driver, String template) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String Setup_Xpath=Environment("Setup_Xpath");
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_SurveyTemplates_Xpath=Environment("survey_SurveyTemplates_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String search_ACS_Xpath=Environment("search_ACS_Xpath");
		String compareTemplate_ACS_Xpath=Environment("compareTemplate_ACS_Xpath");
		String compareTemplateName_ACS_Xpath=Environment("compareTemplateName_ACS_Xpath");
		
		String moreuttons_ACS_Xpath=Environment("moreuttons_ACS_Xpath");
		String edit_ACS_Xpath=Environment("edit_ACS_Xpath");
		String view_ACS_Xpath=Environment("view_ACS_Xpath");
		String delete_ACS_Xpath=Environment("delete_ACS_Xpath");
		
		/*fl.JS_Element_Find(driver, Survey_Xpath);
		fl.ClickByXpath(driver, Survey_Xpath, "", "", "Go to survey Menu", "", "");
		fl.ClickByXpath(driver, survey_SurveyTemplates_Xpath, "", "", "Click on SurveyTemplates", "", "");
		fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "", "click on Advanced Search", "", "");
		fl.entervalueByXpath(driver, compareTemplate_ACS_Xpath, template, "", "", "Templatename with which u have created survey", "", "");*/
		
		int temp_exist=0;
		fl.ClickByXpath(driver, Setup_Xpath, "", "Click on Setup Menu", "", "", "");
		fl.JS_Element_Find(driver, compareTemplate_ACS_Xpath);
		fl.ClickByXpath(driver, compareTemplate_ACS_Xpath, "", "Click on Survey Templates", "", "", "");
		fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "", "click on Advanced Search", "", "");
		fl.entervalueByXpath(driver, search_ACS_Xpath, template, "", "Enter template Name you want to search", "", "", "");
		fun_cas.listSize(driver, moreuttons_ACS_Xpath,template,compareTemplateName_ACS_Xpath);
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
	public int clcikMoreOptionsSurveyFromList(WebDriver driver, String surveyname) throws IOException, InterruptedException
	{
		String surveyNames_Xpath=Environment("surveyNames_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		int surveylist=fun_cas.listSize(driver, surveyNames_Xpath);
		if(surveylist>=1)
		{
			for(int i=1;i<=surveylist;i++)
			{
				fl.JS_Element_Find(driver, surveyNames_Xpath+"["+i+"]");
				String SurveyText=fl.getTextXPATH(driver, surveyNames_Xpath+"["+i+"]", "", "Get text of Survey Name", "", "", "");
				if(SurveyText.equals(surveyname))
				{
					if(surveylist>=1)
					{
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath+"["+i+"]", "", "click on More Options of Survey ", surveyname, SurveyText, "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath+"["+i+"]", "", "click on More Options of Survey ", surveyname, SurveyText, "");
						return i;
					}
					//break;
				}
				else
					fl.disp_Message(driver, "", "No record Matches With Surveyname", surveyname, SurveyText, "");
			}
		}
		return 0;
	}
	public void validatePublishedSurveyData(WebDriver driver, String categeoryname, String GroupName,
			String surveyname, String description, int No_Of_Que, String languages, String allowAnony, String landingPage) throws IOException, InterruptedException
	{
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String searchCreatedSurevey_Xpath=Environment("searchCreatedSurevey_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String MoreOPtionsonGrid_one_Xpath=Environment("MoreOPtionsonGrid_one_Xpath");
		//===validate fields
		String Cat_Name_AfterPublish_Xpath=Environment("Cat_Name_AfterPublish_Xpath");
		String Group_Name_AfterPublish_Xpath=Environment("Group_Name_AfterPublish_Xpath");
		String Survey_Name_AfterPublish_Xpath=Environment("Survey_Name_AfterPublish_Xpath");
		String Description_AfterPublish_Xpath=Environment("Description_AfterPublish_Xpath");
		String No_Of_Que_AfterPublish_Xpath=Environment("No_Of_Que_AfterPublish_Xpath");
		String languages_AfterPublish_Xpath=Environment("languages_AfterPublish_Xpath");
		String landingPage_AfterPublish_Xpath=Environment("landingPage_AfterPublish_Xpath");
		String allowaAnonym_AfterPublish_Xpath=Environment("allowaAnonym_AfterPublish_Xpath");
		String BackButton_Xpath=Environment("BackButton_Xpath");
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		/*try 
		{*/
		Thread.sleep(20000);
		int index = 0;
		fl.JS_Element_Find(driver, Survey_Xpath);
		
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to Survey Menu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			//search with surveyname
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "Search with surveyname", "", "", "", "");
			
			//click on more button
			int list= fun_cas.listSize(driver, MoreOPtionsonGrid_Xpath);
			if(list==1)
			{
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "click on more options", "", "", "");
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+1+"]", "", "click on view survey", "", "", "Y");
			}
			else
			{
				index=fun_cas.clcikMoreOptionsSurveyFromList(driver,surveyname);
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+index+"]", "", "click on view survey", "", "", "Y");
			}
			
			//click on view
			
			
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
			
			String lang=fl.getTextXPATH(driver, languages_AfterPublish_Xpath, "", "Get Languages", "", "", "");
			System.out.println(lang);
			String langu=lang.replace("English, ", "");
			String[] split=langu.split(",");
			for(int j=0;j<split.length;j++)
			{
				if(!languages.contains(split[j]))
				{
					//Assert.assertTrue(lang.contains(languages), "Languages Not Matching");
					fl.disp_Message(driver, "", "", languages, split[j], "");
				}
			}
			if(allowAnony!="")
			{
				String allowAno=fl.getTextXPATH(driver, allowaAnonym_AfterPublish_Xpath, "", "Get AlowAnonymous Status", "", "", "");
				
				fl.disp_Message(driver, "", "", "Allow Anonymous status:"+allowAnony, allowAno, "");
			}
			if(landingPage!="")
			{
				String land=fl.getTextXPATH(driver, landingPage_AfterPublish_Xpath, "", "Get Landing Page Url", "", "", "");
				fl.disp_Message(driver, "", "Landing Page", landingPage, land, "");
			}
			
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
		String MoreOPtionsonGrid_one_Xpath=Environment("MoreOPtionsonGrid_one_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String SurvayName_InView_Xpath=Environment("SurvayName_InView_Xpath");
		String TotalQue_InView_Xpath=Environment("TotalQue_InView_Xpath");
		String Pages_InView_Xpath=Environment("Pages_InView_Xpath");
		String Que_Tpe_InView_Xpath=Environment("Que_Tpe_InView_Xpath");
		String landingPag_InView_Xpath=Environment("landingPag_InView_Xpath");
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		try 
		{
			int index=0;
			fl.JS_Element_Find(driver, Survey_Xpath);
			fl.ClickByXpath(driver, Survey_Xpath, "", "", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "", "", "", "");
			
			Thread.sleep(10000);
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click On Advanced Search", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "", "", "", "", "");
			
/*			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");*/
			int list= fun_cas.listSize(driver, MoreOPtionsonGrid_Xpath);
			if(list==1)
			{
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "click on more options", "", "", "");
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+1+"]", "", "", "", "", "");
			}
			else
			{
				index=fun_cas.clcikMoreOptionsSurveyFromList(driver,surveyname);
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+index+"]", "", "Click on View Option", "", "", "");
			}
			
			
			
			String survey_Name = fl.getTextXPATH(driver, SurvayName_InView_Xpath, "", "Get SurveyName", "", "", "");
			
			String Total_Que = fl.getTextXPATH(driver, TotalQue_InView_Xpath, "", "Get Total Questions", "", "", "");
			
			String Pages = fl.getTextXPATH(driver, Pages_InView_Xpath, "", "Get Pages COunt", "", "", "");
			
			String land_page=fl.getTextXPATH(driver, landingPag_InView_Xpath, "", "Get Text of Landing Page", "", "", "");
			
			//String Que_Type = fl.getTextXPATH(driver, Que_Tpe_InView_Xpath, "", "", "", "", "");
			
			return new String[]{ survey_Name , Total_Que , Pages, land_page};
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
		String questionsTab_Quiz_Xpath=Environment("questionsTab_Quiz_Xpath");
		String quizType_QuizView_Xpath=Environment("quizType_QuizView_Xpath");
		String quizName_QuizView_Xpath=Environment("quizName_QuizView_Xpath");
		String weightage_QuizView_Xpath=Environment("weightage_QuizView_Xpath");
		String totalQue_QuizView_Xpath=Environment("totalQue_QuizView_Xpath");
		
		try 
		{
			String url= driver.getCurrentUrl();
			if(!url.contains("/QuizView/"))
			{
				fl.JS_Element_Find(driver, Quiz_Xpath);
				fl.ClickByXpath(driver, Quiz_Xpath, "", "View Quiz and Get the Total Questions", "", "", "");
				fl.ClickByXpath(driver, Quizzes_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "", "", "", "");
				fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "", "", "", "");
			}
			String quiz_Name = fl.getTextXPATH(driver, quizName_QuizView_Xpath, "", "", "", "", "");
			if(!quiz_Name.equals(quizname))
			{
				fl.JS_Element_Find(driver, Quiz_Xpath);
				fl.ClickByXpath(driver, Quiz_Xpath, "", "View Quiz and Get the Total Questions", "", "", "");
				fl.ClickByXpath(driver, Quizzes_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, AdvancedSearch_Xpath, "", "", "", "", "");
				fl.entervalueByXpath(driver, QuizNameSearchBox_Xpath, quizname, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOptions_Quiz_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, ViewonGrid_Quiz_Xpath, "", "", "", "", "");
			}
			fl.JS_Element_Find(driver, Quiz_Xpath);
			fl.ClickByXpath(driver, questionsTab_Quiz_Xpath, "", "", "", "", "");
			String quizType = fl.getTextXPATH(driver, quizType_QuizView_Xpath, "", "", "", "", "");
			String weightage = fl.getTextXPATH(driver, weightage_QuizView_Xpath, "", "", "", "", "");
			String totalQue = fl.getTextXPATH(driver, totalQue_QuizView_Xpath, "", "", "", "", "");
			return new String[]{ quizType , quiz_Name , weightage , totalQue};
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
			String Que_Control) throws IOException, InterruptedException
	{
		Functional_Cases_propread func_case = new Functional_Cases_propread();
		String Survey_Xpath=Environment("Survey_Xpath");
		String survey_Surveys_Xpath=Environment("survey_Surveys_Xpath");
		String AdvancedSearch_CreatedSurvey_Xpath=Environment("AdvancedSearch_CreatedSurvey_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String MoreOPtionsonGrid_one_Xpath=Environment("MoreOPtionsonGrid_one_Xpath");
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
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		/*try 
		 * 
		{*/
		int index=0;
			String NoSurveyFound = null;
			
			fl.JS_Element_Find(driver, Survey_Xpath);
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to SurveyMenu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Go to Surveys", "", "", "");
			
			if(SearchWithSurveyName!="")
			{
				//need to filter
				Thread.sleep(10000);
				fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advance Search", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SearchWithSurveyName, "search with surveyname", "", "", "", "");
				String Nosurvey=fl.getTextXPATH(driver, NoSurveyFoundXpath, "", "checking the survey existed or not", "", "", "");
				System.out.println(Nosurvey);
				if(Nosurvey.contains("No "))
					
				//if(fl.elementDisplayed(driver, NoSurveyFoundXpath).equals("true"))
				{
					NoSurveyFound = fl.getTextXPATH(driver, NoSurveyFoundXpath, "", "No survey is existed with that survey name", "", "", "");
					
					fl.disp_Message(driver, "", "", "No survey is existed with that survey name", "", "");
					System.out.println("Survey you searched with:"+SearchWithSurveyName+" "+NoSurveyFound);
					
					return "false";
				}
				
				else
				{
						/*fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More Options", "", "", "");*/
					int list= fun_cas.listSize(driver, MoreOPtionsonGrid_Xpath);
					if(list==1)
					{
						fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "click on more options", "", "", "");
						fl.ClickByXpath(driver, EditonGrid_Xpath+"["+1+"]" , "", "click on Edit", "", "", "Y");
					}
					else
					{
						index=fun_cas.clcikMoreOptionsSurveyFromList(driver,SearchWithSurveyName);
						fl.ClickByXpath(driver, EditonGrid_Xpath+"["+index+"]" , "", "click on Edit", "", "", "Y");
					}
			
						//fl.ClickByXpath(driver, EditonGrid_Xpath+"["+index+"]" , "", "click on Edit", "", "", "Y");
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
						if(Que_Control!="")
						{
						  String CreateSurvey_Questions=Environment("Sheet_CreateSurvey_Questions");
						  Excel_Utils RC = new Excel_Utils(Environment("Excel"));
						  int create_Que_row=RC.getLastrowno(CreateSurvey_Questions); 
						  int create_Que_col=RC.getLastcolmno(CreateSurvey_Questions); 
						  String[] create_Que_ele=new String[create_Que_col]; 
						  
						  int Que_No=1;
						 
						  for (int k = 1; k < RC.getLastrowno(CreateSurvey_Questions); k++) 
						  { 
							 
							  System.out.println("QueNO: "+Que_No);
							  System.out.println("for Loop" );
							  System.out.println(Que_Control);
							  System.out.println(RC.getStringCellData(k, RC.Current_Coulumn_Number(CreateSurvey_Questions, "QuestioID"), CreateSurvey_Questions)); 
							  if (Que_Control
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
								  func_case.create_QuestionGroup(driver, 
										  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroup")], 
										  create_Que_ele[RC.Current_Coulumn_Number(CreateSurvey_Questions, "QueGroupNotes")]);
							  } 
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
						if(Que_Control!="")
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
	public void AddQuestionto_CreateSurvey(WebDriver driver, String QuestionText, String QuestionTag, String QueGrp,String AnswerType,
			String NoofOptions_OR_Text, int Question_No, String option1, String option2, String option3, String option4, 
			String option5, String option6, String option7, String option8, String option9, String option10) throws IOException, InterruptedException
	{
		/*QuestionText="Q1";
		QuestionTag="Q_Tag1";
		AnswerType="Check Box";
		NoofOptions_OR_Text="2";*/
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String QuestionText_Xpath= Environment("QuestionText_Xpath");
		String QuestionUpdateText_Xpath=Environment("QuestionUpdateText_Xpath");
		//String QueTestInEdit_Xpath=Environment("QueTestInEdit_Xpath");Incase of edit, but its not needed above Xpath is handling that
		String QuestionTag_Xpath=Environment("QuestionTag_Xpath");
		String QuestionGroup_SelectXpath=Environment("QuestionGroup_SelectXpath");
		String QuestionGroup_SelectOptionsXpath=Environment("QuestionGroup_SelectOptionsXpath");
		String selectTag_AnswerType_Xpath=Environment("selectTag_AnswerType_Xpath");
		String selectTag_AnswerTypeOptions_Xpath=Environment("selectTag_AnswerTypeOptions_Xpath");
		String selectTag_NumberOfOptionsSelect_Xpath=Environment("selectTag_NumberOfOptionsSelect_Xpath");
		String selectTag_NumberOfOptionsSelectOptions_Xpath=Environment("selectTag_NumberOfOptionsSelectOptions_Xpath");
		String EnterLabelText_Xapth=Environment("EnterLabelText_Xapth");
		String AddQuestion_Xpath=Environment("AddQuestion_Xpath");
		String EnabledLabelText_Xpath=Environment("EnabledLabelText_Xpath");
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
		String EnabledNoOfOptions_Xpath=Environment("EnabledNoOfOptions_Xpath");
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
			
			String check_QueGrp=fl.checkOptionValueInSelect(driver, QuestionGroup_SelectXpath, QuestionGroup_SelectOptionsXpath, QueGrp);
			if(check_QueGrp.equals("true"))
			{
				fl.selectDropdownByxpath(driver, QuestionGroup_SelectXpath, QueGrp, "", "Selecting Question Group:"+QueGrp, "", "", "");
			}
			else
			{
				
				fl.disp_Message(driver, "", "QuestionGroup you mentioned is not matched with any of options , Create new Question Group", "", "", "");
				
			}
			
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
			
			String noOfOption_att=fun_cas.returnAttribute(driver, EnabledNoOfOptions_Xpath, "style");
			if(!noOfOption_att.contains("display: none;"))
			{
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
				}
			
				String labelText_Att=fun_cas.returnAttribute(driver, EnabledLabelText_Xpath, "style");
				if(!labelText_Att.contains("display: none;"))
				{
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
				Thread.sleep(5000);
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
		String alert_Xpath=Environment("alert_Xpath");
		try 
		{
			fl.ClickByXpath(driver, SaveCreateSurevyAsTemplate_Xpath, "", "Save The created Survey", "", "", "Y");
			Thread.sleep(30000);
			String alert=fl.getTextXPATH(driver, alert_Xpath+"[2]", "", "Get Alert Message", "", "", "");
			fl.disp_Message(driver, "", "", "", alert, "");
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
		String publish_CompanyProfileFill_Xpath=Environment("publish_CompanyProfileFill_Xpath");
		String gotoProfile_SurveyXpath=Environment("gotoProfile_SurveyXpath");
		String Proceed_Xpath=Environment("Proceed_Xpath");
		String success_Publish_Xpath=Environment("success_Publish_Xpath");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		
		try 
		{
			
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Go to SurveyMenu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			Thread.sleep(10000);
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "click on Advanced Search", "", "", "");
			
			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "search with survey name", "", "", "", "");
			
			List<WebElement> surveys = driver.findElements(By.xpath(No_ofsurveyDisplayed));
			int no_ofsurveys = surveys.size();
			System.out.println(no_ofsurveys+" no of surveys displayed");
			if(no_ofsurveys>=1)
			{
				for(int i=0;i<no_ofsurveys;i++)
				{
					int j=0;
					
					String surveynametext = surveys.get(i).getText();
					
					if(surveynametext.equals(SurveyName))
					{
						j=i+1;
						if(j<=1)
						fl.ClickByXpath(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]", "", "", "", "", "");
						fl.ClickByXpath(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]", "", "Click on more options", "", "", "");
						//jse.executeScript("window.scrollBy(0,700)", "");
						//fl.JS_Element_Find(driver, MoreOPtionsonGrid_moreXpath+"["+j+"]");
					}
				}
			}
			/*if(no_ofsurveys==1)
			{
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More Options", "", "", "");
			}*/
			if(no_ofsurveys>=1)
			{
				Thread.sleep(1000);
			
				fl.JS_Element_Find(driver, publishonGrid_Xpath);
				
				fl.ClickByXpath(driver, publishonGrid_Xpath, "", "Publish on Grid", "", "", "Y");
				
				//String publ=fl.getTextXPATH(driver, publish_CompanyProfileFill_Xpath, "", "", "get the publish popup text", "", "");
				
				
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
		String MoreOPtionsonGrid_one_Xpath=Environment("MoreOPtionsonGrid_one_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String shareonGrid_Xpath=Environment("shareonGrid_Xpath");
		String EmailId_Xpath=Environment("EmailId_Xpath");
		String sendSurvey_Xpath=Environment("sendSurvey_Xpath");
		String getLink_Xpath=Environment("getLink_Xpath");
		
		String WebLinks_Survey_Xpath=Environment("WebLinks_Survey_Xpath");
		String Link_Survey_Xpath=Environment("Link_Survey_Xpath");
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		/*try
		{*/
		int index=0;
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			fl.JS_Element_Find(driver, Survey_Xpath);
			
			fl.ClickByXpath(driver, Survey_Xpath, "", "Click on Survey Menu", "", "", "");
			
			fl.ClickByXpath(driver, survey_Surveys_Xpath, "", "Click on Surveys", "", "", "");
			
			Thread.sleep(10000);
			
			fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click On Advanced Search", "", "", "");

			fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, SurveyName, "Search With survey Name", "", "", "", "");
			
			/*fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "click on More options", "", "", "");*/
			int list= fun_cas.listSize(driver, MoreOPtionsonGrid_Xpath);
			if(list==1)
			{
				System.out.println("filter one entry,");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "click on more options", "", "", "");
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+1+"]", "", "click on View Option", "", "", "");
			}
			else
			{
				System.out.println("searching for survey");
				index=fun_cas.clcikMoreOptionsSurveyFromList(driver,SurveyName);
				fl.ClickByXpath(driver, viewonGrid_Xapth+"["+index+"]", "", "click on View Option", "", "", "");
			}
			
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
	public void enduser_Login(WebDriver driver, String URL, String EmailID, String password, String Anonymous, String landing) throws IOException, InterruptedException
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
		
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String username_Reg_Xpath=Environment("username_Reg_Xpath");
		String password_Reg_Xpath=Environment("password_Reg_Xpath");
		String login_Reg_Xpath=Environment("login_Reg_Xpath");
		String submitAnonym_Reg_Xpath=Environment("submitAnonym_Reg_Xpath");
		String sucesOk_Reg_Xpath=Environment("sucesOk_Reg_Xpath");
		
		fl.entervalueByXpath(driver, username_Reg_Xpath, EmailID, "", "Enter Valid Email ID", "", "", "");
		fl.entervalueByXpath(driver, password_Reg_Xpath, password, "", "Enter Valid Password", "", "", "");
		/*int anony_Enabled=fun_cas.listSize(driver, submitAnonym_Reg_Xpath);
		if(anony_Enabled==1)*/
		String checkbox=fl.elementEnabled(driver, submitAnonym_Reg_Xpath, "Checking Anonymous Response Checkbox Enabled or not");
		fl.disp_Message(driver, "", "", "true", checkbox, "Y");
		if(checkbox.equals("true")&&Anonymous.equals("T"))
			fl.ClickByXpath(driver, submitAnonym_Reg_Xpath, "", "Selecting checkbox", "", "", "");
		fl.ClickByXpath(driver, login_Reg_Xpath, "", "Click on Login And Submit Button", "", "", "");
		
//TEMPORARY COMMENT		
		fl.ClickByXpath(driver, sucesOk_Reg_Xpath, "", "Click on Okay Button in Displayed Sucesfuly Submitted Popup", landing, "", "Y");
		
		
//========START			
			/*String Company_Baseurl=Environment("Comapany_BaseURL_Digi");
			//String Digi_CompanyLoginxpath=Environment("Digi_CompanyLoginXPATH");
			String Company_EmailIDxpath=Environment("Company_EmailIDXPATH");
			String Company_Passwordxpath=Environment("Company_PasswordXPATH");
			String Company_LoginButtonxpath=Environment("Company_LoginButtonXPATH");
			 * fl.invokeApplication(driver, Company_Baseurl, "", "invoke "+Company_Baseurl, "", "", "", "");

			fl.entervalueByXpath(driver, Company_EmailIDxpath, EmailID, "Enter EmailID", "", "", "", "");

			fl.entervalueByXpath(driver, Company_Passwordxpath, password, "Enter Password", "", "", "", "");

			Thread.sleep(3000);
			
			fl.ClickByXpath(driver, Company_LoginButtonxpath, "", "Click on Login Button", "", "", "");
			
			Thread.sleep(3000);*/
//============START END			
			
			
			
			
			
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
	public void anonymousSurveySubmit(WebDriver driver, String firstname, String lastname, String emailId, String contact, String anonymous, String landing) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String continueWOLogin_Ano_Xpath=Environment("continueWOLogin_Ano_Xpath");
		String firstName_Ano_Xpath=Environment("firstName_Ano_Xpath");
		String lastName_Ano_Xpath=Environment("lastName_Ano_Xpath");
		String emailId_Ano_Xpath=Environment("emailId_Ano_Xpath");
		String contact_Ano_Xpath=Environment("contact_Ano_Xpath");
		String submitAnonym_Ano_Xpath=Environment("submitAnonym_Ano_Xpath");
		String submitBut_Ano_Xpath=Environment("submitBut_Ano_Xpath");
		String sucesOk_Reg_Xpath=Environment("sucesOk_Reg_Xpath");
		
		fl.ClickByXpath(driver, continueWOLogin_Ano_Xpath, "", "Click on Continue Without Login", "", "", "");
		fl.entervalueByXpath(driver, firstName_Ano_Xpath, firstname, "", "enter Anonymous first name", "", "", "");
		fl.entervalueByXpath(driver, lastName_Ano_Xpath, lastname, "", "Enter Anonymous last name", "", "", "");
		fl.entervalueByXpath(driver, emailId_Ano_Xpath, emailId, "", "Enter Anonymous Email Id", "", "", "");
		fl.entervalueByXpath(driver, contact_Ano_Xpath, contact, "", "Enter Anonymous Contact Number", "", "", "");
		/*int anonym=fun_cas.listSize(driver, submitAnonym_Ano_Xpath);
		if(anonym==1)*/
		String checkbox=fl.elementEnabled(driver, submitAnonym_Ano_Xpath, "Checking Anonymous Response Checkbox Enabled or not");
		fl.disp_Message(driver, "", "", "true", checkbox, "Y");
		if(checkbox.equals("true")&&anonymous.equals("T"))
			fl.ClickByXpath(driver, submitAnonym_Ano_Xpath, "", "check Submit As Anonymous", "", "", "");
		fl.ClickByXpath(driver, submitBut_Ano_Xpath, "", "Click on Submit Survey", "", "", "");
		fl.ClickByXpath(driver, sucesOk_Reg_Xpath, "", "Click on Okay in Displayed Popup", landing, "", "Y");
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
				
//fl.invokeApplication(driver, "http://localhost:4034/Survey/Index/vGYFuSURgaQAOUOIIwpp6A==/iw4ovayLZcBt%20G61ZShWm0mL3w0MF-aGFzdaj9E-Gxc=", "", "", "", "", "", "");
				
				
				
				//fl.ClickByXpath(driver, ResponseSubmit_Xpath, "", "", "", "", "");
			}
			
			
			
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

		
		
		
	}
	public void selectSurveyLanguageByEndUser(WebDriver driver, String language) throws IOException, InterruptedException
	{
		String lang_Select_Xpath=Environment("lang_Select_Xpath");
		String lang_SelectOptions_Xpath=Environment("lang_SelectOptions_Xpath");
		
		String check_Lang=fl.checkOptionValueInSelect(driver, lang_Select_Xpath, lang_SelectOptions_Xpath, language);
		if(check_Lang.equals("true"))
		fl.selectDropdownByxpath(driver, lang_Select_Xpath, language, "", "Select Language:"+language, "", "", "");
		else
		{
			fl.disp_Message(driver, "", "Specified Language not available in dropdown list", "", "", "Y");
			Assert.fail();
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
	//							  fun_case.saveSurvey(driver);
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
		//					 fun_case.saveSurvey(driver);
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
		//						   fun_case.saveSurvey(driver);
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
			//					fun_case.saveSurvey(driver);
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
		//					  fun_case.saveSurvey(driver);
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
			//						fun_case.saveSurvey(driver);
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
					//					fun_case.saveSurvey(driver);
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
				//				 fun_case.saveSurvey(driver);
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
					//			 fun_case.saveSurvey(driver);
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
			if(totalque>=1)
			{
				if(fl.elementDisplayed(driver, ResponseSubmit_Xpath,"").equals("true"))
				{
//========START					
					/*WebElement submit_enable = driver.findElement(By.xpath(ResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					Thread.sleep(20000);*/
					fl.JS_Element_Find(driver, ResponseSubmit_Xpath);
//===========START END					
					fl.ClickByXpath(driver, ResponseSubmit_Xpath, "", "", "Submit Button to be clicked", "", "");
					System.out.println("submit button clicked by enduser");
				}
				else
					fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			}
			
//=========START			
			/*if(totalque>3)
			{
				if(fl.elementDisplayed(driver, belowResponseSubmit_Xpath,"").equals("true"))
				{
					WebElement submit_enable = driver.findElement(By.xpath(belowResponseSubmit_Xpath));
					jse.executeScript("arguments[0].scrollIntoView();", submit_enable);
					Thread.sleep(20000);
					fl.ClickByXpath(driver, belowResponseSubmit_Xpath, "", "", "Submit Button to be clicked", "", "");
					System.out.println("submit button clicked by enduser");
				}
				else
					fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			}*/
//==========START END			
			
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
		String getCurrentUrl=driver.getCurrentUrl();
		if(!getCurrentUrl.contains("/Account/Login"))
		{
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
		String MoreOPtionsonGrid_one_Xpath=Environment("MoreOPtionsonGrid_one_Xpath");
		String MoreOPtionsonGrid_Xpath=Environment("MoreOPtionsonGrid_Xpath");
		String viewonGrid_Xapth=Environment("viewonGrid_Xapth");
		String SurveyViewSurveyNameXpath=Environment("SurveyViewSurveyNameXpath");
		String SurveyResponses_Xpath=Environment("SurveyResponses_Xpath");
		String lang_WiseResponses_Xpath=Environment("lang_WiseResponses_Xpath");
		String Multiple_ViewDetails_Xpath=Environment("Multiple_ViewDetails_Xpath");
		String Multiple_ViewNames_Xpath=Environment("Multiple_ViewNames_Xpath");
		String ViewDetails_EndUser_Xpath=Environment("ViewDetails_EndUser_Xpath");
		String ViewDetails_EndUser2_Xpath=Environment("ViewDetails_EndUser2_Xpath");
		String getSurveyName_Xpath=Environment("getSurveyName_Xpath");
		String getNoofQuestins_Xpath=Environment("getNoofQuestins_Xpath");
		String AnswerXpath=Environment("AnswerXpath");
		String removeFromAnswer_Xpath=Environment("removeFromAnswer_Xpath");
		String getTotalQue_Xpath=Environment("getTotalQue_Xpath");
		String submitLang_Survey_Xpath=Environment("submitLang_Survey_Xpath");
		
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		/*try 
		{*/
		int index=0;
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
				
				Thread.sleep(10000);
				
				fl.ClickByXpath(driver, AdvancedSearch_CreatedSurvey_Xpath, "", "Click on Advanced Search", "", "", "");
				
				fl.entervalueByXpath(driver, searchCreatedSurevey_Xpath, surveyname, "Search with SurveyName", "", "", "", "");
				
				/*fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "", "", "", "");
				fl.ClickByXpath(driver, MoreOPtionsonGrid_Xpath, "", "Click on More Options", "", "", "");*/
				int list_survey= fun_cas.listSize(driver, MoreOPtionsonGrid_Xpath);
				if(list_survey==1)
				{
					fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "", "", "", "");
					fl.ClickByXpath(driver, MoreOPtionsonGrid_one_Xpath, "", "click on more options", "", "", "");
					fl.ClickByXpath(driver, viewonGrid_Xapth+"["+1+"]", "", "Click on View option", "", "", "");
				}
				else
				{
					index=fun_cas.clcikMoreOptionsSurveyFromList(driver,surveyname);
					fl.ClickByXpath(driver, viewonGrid_Xapth+"["+index+"]", "", "Click on View option", "", "", "");
				}
				
				
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
			fl.ClickByXpath(driver, lang_WiseResponses_Xpath, "", "Select Internal Scrollbar", "", "", "");
			
			String oldTab =driver.getWindowHandle();
			System.out.println("Parent Window :"+oldTab);
		
			System.out.println("before clicking the survey Response VIEW");
		
			jse.executeScript("window.scrollBy(0,1000)", "");
		
			List<WebElement> no_enduser_response = driver.findElements(By.xpath(Multiple_ViewDetails_Xpath));
			int no_responses = no_enduser_response.size();
		
			for(int en_resp=1;en_resp <= no_responses;en_resp++)
			{
				fl.JS_Element_Find(driver, Multiple_ViewDetails_Xpath+"["+en_resp+"]");
				WebElement E1 = driver.findElement(By.xpath(Multiple_ViewDetails_Xpath+"["+en_resp+"]"+Multiple_ViewNames_Xpath));
				String user =E1.getText();
			
				if(user.contains(Enduser))
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
						String submitLang=fl.getTextXPATH(driver, submitLang_Survey_Xpath, "", "Get Survey Response Submit Language", "", "", "");
						
						list.add(submitLang);
					
					
					
					driver.close();//response window close
					
					driver.switchTo().window(oldTab);
					
					driver.navigate().refresh();
					
					jse.executeScript("window.scrollBy(0,-450)", "");
					
					return list;
				}
				else
				{
					System.out.println("no one gives the response to this survey: "+surveyname);
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
			//Thread.sleep(20000);
			System.out.println("Quiz Creation Staring");
			fl.ClickByXpath(driver, Quiz_Xpath, "", "Creating new Quiz", "", "", "");
			System.out.println("mouse hover on quiz , selecting create quiz");
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
		String Setup_Xpath=Environment("Setup_Xpath");
		
		String Quiz_Xpath=Environment("Quiz_Xpath");
		String QuizTemplates_Xpath=Environment("QuizTemplates_Xpath");
		String advancedSearch_QT_Xpath=Environment("advancedSearch_QT_Xpath");
		String new_QT_Xpath=Environment("new_QT_Xpath");
		String templateName_QT_Xpath=Environment("templateName_QT_Xpath");
		String more_QT_Xpath=Environment("more_QT_Xpath");
		String quiznameCompare_QT_Xpath=Environment("quiznameCompare_QT_Xpath");
		
		/*fl.JS_Element_Find(driver, Quiz_Xpath);
		fl.ClickByXpath(driver, Quiz_Xpath, "", "Created Template Validation", "Go to Quiz Menu", "", "");
		fl.ClickByXpath(driver, QuizTemplates_Xpath, "", "", "Click on Quiz Templates", "", "");
		fl.ClickByXpath(driver, advancedSearch_QT_Xpath, "", "", "Click on advanced search", "", "");
		fl.entervalueByXpath(driver, templateName_QT_Xpath, templatename, "", "", "Enter QuizTemplate name", "", "");*/
		
		fl.ClickByXpath(driver, Setup_Xpath, "", "Click on Setup Menu", "", "", "");
		fl.JS_Element_Find(driver, QuizTemplates_Xpath);
		fl.ClickByXpath(driver, QuizTemplates_Xpath, "", "Click on Quiz Templates", "", "", "");
		fl.ClickByXpath(driver, advancedSearch_QT_Xpath, "", "", "Click on advanced search", "", "");
		fl.entervalueByXpath(driver, templateName_QT_Xpath, templatename, "", "", "Enter QuizTemplate name", "", "");
		fun_cas.listSize(driver, more_QT_Xpath, templatename, quiznameCompare_QT_Xpath);
		
	}
	public void Quiz_Questions(WebDriver driver, String Que_text, String score, String seconds, String AnswerType, String NoofOptions_OR_Text,
			int Question_No, String option1,String option2,String option3,String option4,String option5,String option6,String option7,String option8
			,String option9,String option10, String EnterLabelText, String ValidateQue) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String Que_Text_Quiz_Xpath = Environment("Que_Text_Quiz_Xpath");
		String Weightage_label_Xpath=Environment("Weightage_label_Xpath");
		String withoutDur_score_Xpath=Environment("withoutDur_score_Xpath");
		String withDur_Indiv_Xpath=Environment("withDur_Indiv_Xpath");
		String WeightageScore_QuizXpath=Environment("WeightageScore_QuizXpath");
		String Duration_Label_Xpath=Environment("Duration_Label_Xpath");
		String DurationInSeconds_QuizXpath=Environment("DurationInSeconds_QuizXpath");
		String AnswerType_Quiz_SelectXpath=Environment("AnswerType_Quiz_SelectXpath");
		String EnabledNoOfOptions_Xpath=Environment("EnabledNoOfOptions_Xpath");
		String EnabledLabelText_Xpath=Environment("EnabledLabelText_Xpath");
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
				//String Weightage_label =fl.elementDisplayed(driver, Weightage_label_Xpath,"");
				
				String dur_att=fun_cas.returnAttribute(driver, withDur_Indiv_Xpath, "style");
				String score_att=fun_cas.returnAttribute(driver, withoutDur_score_Xpath, "style");
				
					if(score_att.contains("display: block;"))
					{
						fl.entervalueByXpath(driver, WeightageScore_QuizXpath, score, "can enter different score for Each Question in Quiz", "", "", "", "");
					}
				
				//String Duration_label = fl.getTextXPATH(driver, Duration_Label_Xpath, "", "", "", "", "");
				
					//String Duration_label =fl.elementDisplayed(driver, Duration_Label_Xpath,"");
			
					if(dur_att.contains("display: block;"))
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
			
			String noOfOption_att=fun_cas.returnAttribute(driver, EnabledNoOfOptions_Xpath, "style");
			if(!noOfOption_att.contains("display: none;"))
			{
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
			}
				else
				{
					//System.out.println("NumberOf OPtions, You have given in Excel Not Matched with Any one of the given");
					
					
				}
				
			String labelText_Att=fun_cas.returnAttribute(driver, EnabledLabelText_Xpath, "style");
			if(!labelText_Att.contains("display: none;"))
			{
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
	public void check_CP_Filled(WebDriver driver, String filepath, String filepath1, String filepath2, String overview, String background) throws IOException, InterruptedException
	{
		String Company_LogoXPATH=Environment("Company_LogoXPATH");
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String companyProfile5_Xpath=Environment("companyProfile5_Xpath");
		String companyPublicView_CP_Xpath=Environment("companyPublicView_CP_Xpath");
		String overview_CPNoFill_Xpath=Environment("overview_CPNoFill_Xpath");
		String editCompanyPublicView_CP_Xpath=Environment("editCompanyPublicView_CP_Xpath");
		String Image1_CP_Xpath=Environment("Image1_CP_Xpath");
		String Image2_CP_Xpath=Environment("Image2_CP_Xpath");
		String Image3_CP_Xpath=Environment("Image3_CP_Xpath");
		String overview_CP_Xpath=Environment("overview_CP_Xpath");
		String background_CP_Xpath=Environment("background_CP_Xpath");
		String save_CP_Xpath=Environment("save_CP_Xpath");
		
		String galleryImages_CPValid_Xpath=Environment("galleryImages_CPValid_Xpath");
		String attribute_CPValid_Xpath=Environment("attribute_CPValid_Xpath");
		String galleryFirstImg_CPValid_Xpath=Environment("galleryFirstImg_CPValid_Xpath");
		
		try
		{
			Functional_Cases_propread fun_cas = new Functional_Cases_propread();
			fl.ClickByXpath(driver, Company_LogoXPATH, "", "before publish the Survey, check companyPublicView Details filled or not, Click on Company image Dropdown", "", "", "");
			int options = fun_cas.listSize(driver, logoutOptions_Xpath);
			if(options==6)
			{
				fl.ClickByXpath(driver, companyProfile_Xpath, "", "click on company Profile", "", "", "");
			}
			else
			{
				if(options==5)
				{
					fl.ClickByXpath(driver, companyProfile5_Xpath, "", "click on company Profile", "", "", "");
				}
				else
				{
					fl.disp_Message(driver, "", "", "CompanyProfile Option Not Existed", "", "");
				}
			}
			if(options==6||options==5)
			{
				fl.ClickByXpath(driver, companyPublicView_CP_Xpath, "", "click on CompanyPublicView tab", "", "", "");
				//String overview_text = fl.getTextXPATH(driver, overview_CPNoFill_Xpath, "", "get the text of overview, if overview contains no data , fill the company Profile", "", "", "");
				int galery=fun_cas.listSize(driver, galleryFirstImg_CPValid_Xpath);
				/*String imag1_status=fun_cas.returnAttributeValue(driver, galleryImages_CPValid_Xpath+"[1]", attribute_CPValid_Xpath);
				String overview_text=fl.getTextXPATH(driver, overview_CP_Xpath, "", "get the text of overview, if overview contains no data , fill the company Profile", "", "", "");*/
				if(galery==1)
				{
					//fl.ClickByXpath(driver, editCompanyPublicView_CP_Xpath, "", "edit CompanyPublicView Details", "", "", "");
					fl.ClickByXpath(driver, Image1_CP_Xpath, "", "", "Uploading an image", "", "");
					Thread.sleep(3000);
					UploadFile_Robot image = new UploadFile_Robot();
					image.uploadFile(filepath);
					fl.disp_Message(driver, "", "Uploading 1st image", "", "", "Y");
					fl.ClickByXpath(driver, Image2_CP_Xpath, "", "", "Uploading an image", "", "");
					Thread.sleep(3000);
					image.uploadFile(filepath1);
					fl.disp_Message(driver, "", "Uploading 2nd image", "", "", "Y");
					fl.ClickByXpath(driver, Image3_CP_Xpath, "", "", "Uploading an image", "", "");
					Thread.sleep(3000);
					image.uploadFile(filepath2);
					fl.disp_Message(driver, "", "Uploading 3rd image", "", "", "Y");
					fl.entervalueByXpath(driver, overview_CP_Xpath, overview, "", "", "", "", "");
					fl.entervalueByXpath(driver, background_CP_Xpath, background, "", "", "", "", "");
					fl.JS_Element_Find(driver, save_CP_Xpath);
					fl.ClickByXpath(driver, save_CP_Xpath, "", "", "", "", "");
				}
				else
				{
					fl.disp_Message(driver, "", "Already Filled Company Profile", "", "", "Y");
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
			fl.JS_Element_Find(driver, Quiz_Xpath);
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
				//fl.ClickByXpath(driver, cancel_QuizPublish_Xpath, "", "not publishing the quiz existed in draft ", "", "", "Y");
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
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
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
		
		String expiresIn_Enable_Xpath=Environment("expiresIn_Enable_Xpath");
		String equal_Wgt_Enable_Xpath=Environment("equal_Wgt_Enable_Xpath");
		String totDur_Enable_Xpath=Environment("totDur_Enable_Xpath");
		String equDur_Enable_Xpath=Environment("equDur_Enable_Xpath");
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
				//String exp_day=fl.elementDisplayed(driver, ExpiresInDays_Xpath,"");
				String expire_att=fun_cas.returnAttribute(driver, expiresIn_Enable_Xpath, "style");
				if(!expire_att.contains("display: none;"))
				{
					
						fl.ClickByXpath(driver, ExpiresInDays_Xpath, "", "Click on checkbox expiresInDays", "", "", "");
						select_delete sel_del = new select_delete();
						fl.entervalueByXpath(driver, ExpiresInDays_Xpath, expire, "enter value in Days, expires in how many days", "", "", "", "");
					
				}
			}
			if(!eq_weight.equals(""))
			{
				String equal_wgt_att=fun_cas.returnAttribute(driver, equal_Wgt_Enable_Xpath, "style");
				if(!equal_wgt_att.contains("display: none;"))
				{
					fl.ClickByXpath(driver, Equal_Weight_Score_Xpath, "", "Click on Equal Weight", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, Equal_Weight_Score_Xpath, eq_weight, "enter the equal score", "", "", "", "");
				}
			}
			if(!equal_sec.equals(""))
			{
				String equalSec_att=fun_cas.returnAttribute(driver, equDur_Enable_Xpath, "style");
				if(!equalSec_att.contains("display: none;"))
				{
					fl.ClickByXpath(driver, Equal_Dur_Sec_Xpath, "", "click on equal Duration", "", "", "");
					select_delete sel_del = new select_delete();
					fl.entervalueByXpath(driver, Equal_Dur_Sec_Xpath, equal_sec, "enter the Equal seconds", "", "", "", "");
				}
			}
			if(!tot_duration.equals(""))
			{
				String totDur_att=fun_cas.returnAttribute(driver, totDur_Enable_Xpath, "style");
				if(!totDur_att.contains("display: none;"))
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
		String subject_Quiz_Xpath=Environment("subject_Quiz_Xpath");
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
			fl.ClickByXpath(driver, subject_Quiz_Xpath, "", "", "Scrolling inner scroll bar to view Quiz link", "", "");
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
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
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
					/*int nextque=que+1;
					fl.JS_Element_Find(driver, totalQuestions_QR_Xpath+"["+nextque+"]");*/
					
					if(que>5)
						jse.executeScript("window.scrollBy(0,450)", "");
					
					
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
			
			//fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "click on advanced search", "", "");
			Thread.sleep(10000);
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
	public void createPetition(WebDriver driver, String PetitionTitle, String campaignType, String PetitionCategeory,  String Tags,
			String enddate, String indefinite, String video, String currencyType, String fund_goal, String collectFundLater, String no_sign, String social_img, String gal_img1, String gal_img2, String gal_img3, String gal_img4, String DecisionMakerName,
			String Designation, String sup_image1,String sup_image2,String sup_image3,String sup_image4,String sup_image5,String FAQ_ID, String description,
			String linktext, String linkTextUrl, String newWindo, String browse_img, String img_link, String occupation, String visa, String age,
			String salary, String textQ, String dropQ, String noOptions, String option1, String option2, String option3
			, String option4, String option5, String option6, String option7, String option8, String option9, String option10,
			String submit, String draft)
					throws IOException, InterruptedException
	{
		UploadFile_Robot upload = new UploadFile_Robot();
		String Petitions_Xpath=Environment("Petitions_Xpath");
		String create_petition_Xpath=Environment("create_petition_Xpath");
		
		String PetitionTitle_Xpath=Environment("PetitionTitle_Xpath");
		
		String campaignType_SelectXpath=Environment("campaignType_SelectXpath");
		String campaignType_SelectOptionsXpath=Environment("campaignType_SelectOptionsXpath");
		String PetitionCategeory_SelectXpath=Environment("PetitionCategeory_SelectXpath");
		String PetitionCategeory_SelectOptionsXpath=Environment("PetitionCategeory_SelectOptionsXpath");
		
		String PetitionTags_Xpath=Environment("PetitionTags_Xpath");
		String endDate_Xpath=Environment("endDate_Xpath");
		String Indefinite_Xpath=Environment("Indefinite_Xpath");
		String videoLink_Xpath=Environment("videoLink_Xpath");
		
		String SelectCurrency_SelectXpath=Environment("SelectCurrency_SelectXpath");
		String SelectCurrency_SelectOptionsXpath=Environment("SelectCurrency_SelectOptionsXpath");
		String enabledFunGoal_Xpath=Environment("enabledFunGoal_Xpath");
		String FundingGoal_Xpath=Environment("FundingGoal_Xpath");
		String collectFundsLater_Xpath=Environment("collectFundsLater_Xpath");
		
		
		String SignaturesRequired_Xpath=Environment("SignaturesRequired_Xpath");
		String crop_Xpath=Environment("crop_Xpath");
		String socialImage_Xpath=Environment("socialImage_Xpath");
		String image1_Xpath=Environment("image1_Xpath");
		String image2_Xpath=Environment("image2_Xpath");
		String image3_Xpath=Environment("image3_Xpath");
		String image4_Xpath=Environment("image4_Xpath");
		
		String DecisionMakerPlus_Xpath=Environment("DecisionMakerPlus_Xpath");
		String DecisionMakerName_Xpath=Environment("DecisionMakerName_Xpath");
		String DecisionMakerDesignation_Xpath=Environment("DecisionMakerDesignation_Xpath");
		String addDecisionMaker_Xpath=Environment("addDecisionMaker_Xpath");
		
		String supportDocPlus_Xpath=Environment("supportDocPlus_Xpath");
		String image1Doc_Xpath=Environment("image1Doc_Xpath");
		String image2Doc_Xpath=Environment("image2Doc_Xpath");
		String image3Doc_Xpath=Environment("image3Doc_Xpath");
		String image4Doc_Xpath=Environment("image4Doc_Xpath");
		String image5Doc_Xpath=Environment("image5Doc_Xpath");
		
		String FAQPlus_Xpath=Environment("FAQPlus_Xpath");
		String FAQuestion_Xpath=Environment("FAQuestion_Xpath");
		String Answer_Xpath=Environment("Answer_Xpath");
		String addQuestion_FAQ_Xpath=Environment("addQuestion_FAQ_Xpath");
		String petitionDescription_Xpath=Environment("petitionDescription_Xpath");
		String linkButton_Xpath=Environment("linkButton_Xpath");
		String textToDisp_Link_Xpath=Environment("textToDisp_Link_Xpath");
		String urlLink_Xpath=Environment("urlLink_Xpath");
		String openInNewWindow_Xpath=Environment("openInNewWindow_Xpath");
		String insertLink_Xpath=Environment("insertLink_Xpath");
		String pictureButton_Xpath=Environment("pictureButton_Xpath");
		String imageURL_Xpath=Environment("imageURL_Xpath");
		String browsePicture_Xpath=Environment("browsePicture_Xpath");
		String insertImage_Xpath=Environment("insertImage_Xpath");
		String next_Page_Xpath=Environment("next_Page_Xpath");
		
//SIGNATURE FORM CONFIGURATION
		String ocupation_SFC_Xpath=Environment("ocupation_SFC_Xpath");
		String visaStatus_SFC_Xpath=Environment("visaStatus_SFC_Xpath");
		String age_SFC_Xpath=Environment("age_SFC_Xpath");
		String salary_SFC_Xpath=Environment("salary_SFC_Xpath");
		String addQuestion_SFC_Xpath=Environment("addQuestion_SFC_Xpath");
		String textbox_SFC_Xpath=Environment("textbox_SFC_Xpath");
		String textQue_SFC_Xpath=Environment("textQue_SFC_Xpath");
		String dropdown_SFC_Xpath=Environment("dropdown_SFC_Xpath");
		String dropQue_SFC_Xpath=Environment("dropQue_SFC_Xpath");
		String select_SFC_Xpath=Environment("select_SFC_Xpath");
		String selectOptions_SFC_Xpath=Environment("selectOptions_SFC_Xpath");
		String option1_SFC_Xpath=Environment("option1_SFC_Xpath");
		String option2_SFC_Xpath=Environment("option2_SFC_Xpath");
		String option3_SFC_Xpath=Environment("option3_SFC_Xpath");
		String option4_SFC_Xpath=Environment("option4_SFC_Xpath");
		String option5_SFC_Xpath=Environment("option5_SFC_Xpath");
		String option6_SFC_Xpath=Environment("option6_SFC_Xpath");
		String option7_SFC_Xpath=Environment("option7_SFC_Xpath");
		String option8_SFC_Xpath=Environment("option8_SFC_Xpath");
		String option9_SFC_Xpath=Environment("option9_SFC_Xpath");
		String option10_SFC_Xpath=Environment("option10_SFC_Xpath");
		
		
		String addTextQ_SFC_Xpath=Environment("addTextQ_SFC_Xpath");
		
		String submitForApproval_Xpath=Environment("submitForApproval_Xpath");
		String saveAsDraft_Xpath=Environment("saveAsDraft_Xpath");
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		/*try 
		{*/
			fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
			
			fl.ClickByXpath(driver, create_petition_Xpath, "", "", "Creating Petition UI Displayed", "", "");
			
			fl.entervalueByXpath(driver, PetitionTitle_Xpath, PetitionTitle, PetitionTitle, "", PetitionTitle+ "to be entered", "", "");
			if(campaignType.equals("Petitions for Signatures"))
			{
				fl.selectDropdownByxpath(driver, campaignType_SelectXpath, campaignType, campaignType, "", "Selecting Petitions for Signatures", "", "");
			}
			else
			{
				if(campaignType.equals("Petitions with Signatures & Funds"))
				{
					fl.selectDropdownByxpath(driver, campaignType_SelectXpath, campaignType, campaignType, "", "Selecting Petitions with Signatures & Funds", "", "");
				}
			}
			
			String Petition_Cat=fl.checkOptionValueInSelect(driver, PetitionCategeory_SelectXpath, PetitionCategeory_SelectOptionsXpath, PetitionCategeory);
			
			if(Petition_Cat.equals("true"))
			{
				fl.selectDropdownByxpath(driver, PetitionCategeory_SelectXpath, PetitionCategeory, PetitionCategeory, "", PetitionCategeory+" to be selected", "", "");
			}
			
			
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
			String fund_att=fun_cas.returnAttribute(driver, enabledFunGoal_Xpath, "style");
			if(!fund_att.contains("display:none;"))
			{
				if(currencyType!=""&&fund_goal!="")
				{
					fl.JS_Element_Find(driver, SelectCurrency_SelectXpath);
					fl.selectDropdownByxpath(driver, SelectCurrency_SelectXpath, currencyType, "", "", "Currency type to be selected", "", "");
					Thread.sleep(2000);
					fl.entervalueByXpath(driver, FundingGoal_Xpath, fund_goal, "", "", "funding goal to be entered", "", "");
				}
				if(!collectFundLater.equals(""))
				{
					fl.ClickByXpath(driver, collectFundsLater_Xpath, "", "Check Collect Funds Later Option", "", "", "");
				}
			}
			fl.entervalueByXpath(driver, SignaturesRequired_Xpath, no_sign, "", "", "number of sign to be entered", "", "");
			
			fl.JS_Element_Find(driver, socialImage_Xpath);
			fl.ClickByXpath(driver, socialImage_Xpath, "", "", "Uploading Social media Image", "", "");
			upload.uploadFile(social_img);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, image1_Xpath, "", "", "Upload image1", "", "");
			upload.uploadFile(gal_img1);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, image2_Xpath, "", "", "Upload image2", "", "");
			upload.uploadFile(gal_img2);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, image3_Xpath, "", "", "Upload image3", "", "");
			upload.uploadFile(gal_img3);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
			fl.ClickByXpath(driver, image4_Xpath, "", "", "Upload image4", "", "");
			upload.uploadFile(gal_img4);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
			
			fl.JS_Element_Find(driver, DecisionMakerPlus_Xpath);
			fl.ClickByXpath(driver, DecisionMakerPlus_Xpath, "", "", "Decision maker to be clicked", "", "");
			fl.entervalueByXpath(driver, DecisionMakerName_Xpath, DecisionMakerName, "", "", "decision maker to be entered", "", "");
			fl.entervalueByXpath(driver, DecisionMakerDesignation_Xpath, Designation, "", "", "", "", "");
			fl.ClickByXpath(driver, addDecisionMaker_Xpath, "", "", "Click on DecisionMaker", "", "");
			
			
			fl.ClickByXpath(driver, supportDocPlus_Xpath, "", "", "Support Documents to be clicked", "", "");
			//upload.uploadFile(supportDoc);
			fl.ClickByXpath(driver, image1Doc_Xpath, "", "", "Uploading Supporing Doc image1", "", "");
			upload.uploadFile(sup_image1);
			fl.ClickByXpath(driver, image2Doc_Xpath, "", "", "Uploading Supporing Doc image2", "", "");
			upload.uploadFile(sup_image2);
			fl.ClickByXpath(driver, image3Doc_Xpath, "", "", "Uploading Supporing Doc image3", "", "");
			upload.uploadFile(sup_image3);
			fl.ClickByXpath(driver, image4Doc_Xpath, "", "", "Uploading Supporing Doc image4", "", "");
			upload.uploadFile(sup_image4);
			fl.ClickByXpath(driver, image5Doc_Xpath, "", "", "Uploading Supporing Doc image5", "", "");
			upload.uploadFile(sup_image5);
			
			
			
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
			          fl.ClickByXpath(driver, addQuestion_FAQ_Xpath, "", "", "Add question to be clicked", "", "");
			          
				 }
			}
			Thread.sleep(3000);
			fl.ClickByXpath(driver, petitionDescription_Xpath, "", "", "clear sample data in petition Description", "", "");
			fun_cas.clearTextfield(driver, petitionDescription_Xpath,description);
			fl.entervalueByXpath(driver, petitionDescription_Xpath, description, "", "", "enter Petition Description", "", "");
			if(linktext!="")
			{
				fl.ClickByXpath(driver, linkButton_Xpath, "", "", "click on link button in petition description", "", "");
				fl.entervalueByXpath(driver, textToDisp_Link_Xpath, linktext, "", "", "Enter text you want to display as HyperLink Text", "", "");
				if(linkTextUrl!="")
				{
					fl.entervalueByXpath(driver, urlLink_Xpath, linkTextUrl, "", "", "enter url with which you are linkig above entered text", "", "");
					if(newWindo!="")
					fl.ClickByXpath(driver, openInNewWindow_Xpath, "", "", "when click on hyperlink it will open in new window", "", "");
					fl.ClickByXpath(driver, insertLink_Xpath, "", "", "click on Insert Link button to ass this hyperlink to petition Description", "", "");
					fl.ClickByXpath(driver, petitionDescription_Xpath, "", "", "clear sample data in petition Description", "", "");
					clickEndRobot endRobo = new clickEndRobot();
				}
				else
					fl.disp_MessageFailed(driver, "", "", "you need to give link text url", "", "Y");
			}
			else
			{
				if(linkTextUrl!="")
				{
					fl.entervalueByXpath(driver, urlLink_Xpath, linkTextUrl, "", "", "enter url with which you are linkig above entered text", "", "");
					if(newWindo!="")
					fl.ClickByXpath(driver, openInNewWindow_Xpath, "", "", "when click on hyperlink it will open in new window", "", "");
					fl.ClickByXpath(driver, insertLink_Xpath, "", "", "click on Insert Link button to ass this hyperlink to petition Description", "", "");
					fl.ClickByXpath(driver, petitionDescription_Xpath, "", "", "clear sample data in petition Description", "", "");
					clickEndRobot endRobo = new clickEndRobot();
				}
				
			}
			if(browse_img!="" && img_link!="")
			{
				fl.ClickByXpath(driver, pictureButton_Xpath, "", "", "click on insert picture icon in petition description", "", "");
				//fl.clear_textfield(driver, imageURL_Xpath);
				fl.entervalueByXpath(driver, imageURL_Xpath, img_link, "", "", "enter image link", "", "");
				fl.ClickByXpath(driver, browsePicture_Xpath, "", "", "click to browse image", "", "");
				upload.uploadFile(browse_img);
				Thread.sleep(3000);
//				fl.ClickByXpath(driver, insertImage_Xpath, "", "", "click on inser image button", "", "");
			}
			else
			{
				if(browse_img!="")
				{
					fl.ClickByXpath(driver, pictureButton_Xpath, "", "", "click on insert picture icon in petition description", "", "");
					fl.clear_textfield(driver, imageURL_Xpath);
					fl.entervalueByXpath(driver, imageURL_Xpath, img_link, "", "", "enter image link", "", "");
//					fl.ClickByXpath(driver, insertImage_Xpath, "", "", "click on inser image button", "", "");
				}
				else
				{
					if(img_link!="")
					{
						fl.ClickByXpath(driver, pictureButton_Xpath, "", "", "click on insert picture icon in petition description", "", "");
						fl.ClickByXpath(driver, browsePicture_Xpath, "", "", "click to browse image", "", "");
						upload.uploadFile(browse_img);
						Thread.sleep(3000);
						//fl.ClickByXpath(driver, insertImage_Xpath, "", "", "click on insert image button", "", "");
					}
				}
			}
			fl.JS_Element_Find(driver, next_Page_Xpath);
	        fl.ClickByXpath(driver, next_Page_Xpath, "", "", "navigate to next page", "", "");
	        
//SIGNATURE FORM CONFIGURATION
	        if(occupation!="")
	        fl.ClickByXpath(driver, ocupation_SFC_Xpath, "", "", "Display Occupation option in Petition Form", "", "");
	        if(visa!="")
	        fl.ClickByXpath(driver, visaStatus_SFC_Xpath, "", "", "Display VisaStatus option in Petition Form", "", "");
	        if(age!="")
	        fl.ClickByXpath(driver, age_SFC_Xpath, "", "", "Display ages option in Petition Form", "", "");
	        if(salary!="")
	        fl.ClickByXpath(driver, salary_SFC_Xpath, "", "", "Display Salary option in Petition Form", "", "");
	        if(textQ!=""||dropQ!="")
	        {
	        	fl.ClickByXpath(driver, addQuestion_SFC_Xpath, "", "", "Adding questions to petition form", "", "");
	        
	        	if(textQ!="")
	        	{
	        		fl.ClickByXpath(driver, textbox_SFC_Xpath, "", "", "Selecting textbox Question", "", "");
	        		fl.entervalueByXpath(driver, textQue_SFC_Xpath, textQ, "", "", "enter question", "", "");
	        	}
	        	else
	        	{
	        		if(dropQ!="")
	        		{
	        			fl.ClickByXpath(driver, dropdown_SFC_Xpath, "", "", "selecting Dropdown Question Type", "", "");
	        			fl.entervalueByXpath(driver, dropQue_SFC_Xpath, dropQ, "", "", "enter question", "", "");
	        			fl.selectDropdownByxpath(driver, select_SFC_Xpath, noOptions, "", "", "Selecting number options to be selected:"+noOptions, "", "");
//ass types of options(PENDING)	
	        			if(noOptions.equals("2"))
						{
							System.out.println("option values to be entered");
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("3"))
						{
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("4"))
						{
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("5"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("6"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option6_SFC_Xpath, option6, "option6 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("7"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
					
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
							
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option6_SFC_Xpath, option6, "option6 value is entered", "", "", "", "");
					
							fl.JS_Element_Find(driver, option7_SFC_Xpath);
							
							fl.entervalueByXpath(driver, option7_SFC_Xpath, option7, "option7 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("8"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
					
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
							
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option6_SFC_Xpath, option6, "option6 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option7_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option7_SFC_Xpath, option7, "option7 value is entered", "", "", "", "");
							
							fl.entervalueByXpath(driver, option8_SFC_Xpath, option8, "option8 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("9"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option6_SFC_Xpath, option6, "option6 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option7_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option7_SFC_Xpath, option7, "option7 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option8_SFC_Xpath, option8, "option8 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option9_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option9_SFC_Xpath, option9, "option9 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
						if(noOptions.equals("10"))
						{
				
							fl.entervalueByXpath(driver, option1_SFC_Xpath, option1, "option1 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option2_SFC_Xpath, option2, "option2 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option3_SFC_Xpath, option3, "option3 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option4_SFC_Xpath, option4, "option4 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option5_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option5_SFC_Xpath, option5, "option5 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option6_SFC_Xpath, option6, "option6 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option7_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option7_SFC_Xpath, option7, "option7 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option8_SFC_Xpath, option8, "option8 value is entered", "", "", "", "");
							
							fl.JS_Element_Find(driver, option9_SFC_Xpath);
					
							fl.entervalueByXpath(driver, option9_SFC_Xpath, option9, "option9 value is entered", "", "", "", "");
					
							fl.entervalueByXpath(driver, option10_SFC_Xpath, option10, "option10 value is entered", "", "", "", "");
							
							//jse.executeScript("window.scrollBy(0,500)", "");
				
						}
	        			
	        		}
	        	}
	        	fl.ClickByXpath(driver, addTextQ_SFC_Xpath, "", "", "click add button to add question to signature form", "", "");
	        }
	        
	        if(draft!="")
	        {
	        	fl.JS_Element_Find(driver, saveAsDraft_Xpath);
	        	fl.ClickByXpath(driver, saveAsDraft_Xpath, "", "", "SaveAs Draft to be clicked", "", "");
	        }
	        else
	        {
	        	if(submit!="")
	        	{
	        		fl.JS_Element_Find(driver, saveAsDraft_Xpath);
	        		fl.ClickByXpath(driver, submitForApproval_Xpath, "", "", "click on submit", "", "");
	        	}
	        }
			
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
		String searchPetitionName_Xpath=Environment("searchPetitionName_Xpath");
		String multiMoreButtonFiltered_Xpath=Environment("multiMoreButtonFiltered_Xpath");
		String multiMorePetitionNameXpath=Environment("multiMorePetitionNameXpath");
		String publish_DraftPetition_Xpath=Environment("publish_DraftPetition_Xpath");
		String proceed_Publishing_Xpath=Environment("proceed_Publishing_Xpath");
		
		/*try 
		{*/
			fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
			
			fl.ClickByXpath(driver, ExistedPetitions_Xpath, "", "Going to Existed Petitiion", "Petition Drafts should be Displayed", "", "");
			Thread.sleep(8000);
			fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "Click on Advanced Search", "", "");
			
			fl.entervalueByXpath(driver, searchPetitionName_Xpath, createdpetition, "", "Searching with petition name in drafts to publish", "display petition name search box", "", "");
			
			List<WebElement> buttons = driver.findElements(By.xpath(multiMoreButtonFiltered_Xpath));
			List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
			if(buttons.size()>1)
			{
				//List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
				
				if(names.size()>1)
				{
					for(int i=0;i<names.size();i++)
					{
						if(names.get(i).getText().equals(createdpetition))
						{
							int j=i+1;
							fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "", "", "", "");
							//fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
							
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
					if(names.get(0).getText().equals(createdpetition))
					{
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"[1]", "", "", "", "", "");
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"[1]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
					
						fl.ClickByXpath(driver, publish_DraftPetition_Xpath, "", "Publishing the searched petition in drafts", "Publish Proceed Popup has to be displayed", "", "");
					
						fl.ClickByXpath(driver, proceed_Publishing_Xpath, "", "Click on Proceed to publish", "Petition Published successfully", "", "");
					}
				}
				else
				{
					fl.disp_Message(driver, "", "There are no petition existed with "+createdpetition, "", "", "");
					System.out.println("There are no petition existed with "+createdpetition);
					
				}
			}
		/*} 
		catch (InterruptedException e)
		{
			fl.disp_Message(driver, "", "Error Occured", "", "", "Y");
			Logs_DigiSurvey.info(e.getMessage());
			e.printStackTrace();
		}*/
	}
	public void AprvRejctPetition(WebDriver driver, String campaignTitle, String approve, String reject, String comment, int first) throws IOException, InterruptedException
	{
		String petitions_Admin_Xpath=Environment("petitions_Admin_Xpath");
		String searchPeti_ARJ_Xpath=Environment("searchPeti_ARJ_Xpath");
		String titleMatches_ARJ_Xpath=Environment("titleMatches_ARJ_Xpath");
		String matchedRow_ARJ_Xpath=Environment("matchedRow_ARJ_Xpath");
		String reject_ARJ_Xpath=Environment("reject_ARJ_Xpath");
		String rejectOpen_ARJ_Xpath=Environment("rejectOpen_ARJ_Xpath");
		String rejectComments_ARJ_Xpath=Environment("rejectComments_ARJ_Xpath");
		String rejectOpenComments_ARJ_Xpath=Environment("rejectOpenComments_ARJ_Xpath");
		String rejectConfirm_ARJ_Xpath=Environment("rejectConfirm_ARJ_Xpath");
		String unpublishOpen_ARJ_Xpath=Environment("unpublishOpen_ARJ_Xpath");
		String accept_ARJ_Xpath=Environment("accept_ARJ_Xpath");
		String acceptComments_ARJ_Xpath=Environment("acceptComments_ARJ_Xpath");
		String approve_ARJ_Xpath=Environment("approve_ARJ_Xpath");
		String checkStatus_ARJ_Xpath=Environment("checkStatus_ARJ_Xpath");
		String noPetition_ARJ_Xpath=Environment("noPetition_ARJ_Xpath");
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		fl.JS_Element_Find(driver, petitions_Admin_Xpath);
		int clear=0;
		//Thread.sleep(20000);
		if(first==1)
		{
			fl.ClickByXpath(driver, petitions_Admin_Xpath, "", "", "Go to Petitions List", "", "");
			clear++;
		}
		Thread.sleep(6000);
		if(clear==0)
		{
			fun_cas.clearTextfield(driver, searchPeti_ARJ_Xpath, campaignTitle);
		}
		fl.entervalueByXpath(driver, searchPeti_ARJ_Xpath, campaignTitle, "", "", "search with petition title", "", "");
		//ClickEnterRobot enterRobo = new ClickEnterRobot();
		List<WebElement> elements = driver.findElements(By.xpath(titleMatches_ARJ_Xpath));
		if(elements.size()>=1)
		{
			for(int i=0;i<elements.size();i++)
			{
				int j=i+1;
				if(elements.get(i).getText().equals(campaignTitle))
				{
					fl.JS_Element_Find(driver, matchedRow_ARJ_Xpath+"["+j+"]");
					if(approve!="")
					{
						String  status=fl.getTextXPATH(driver, matchedRow_ARJ_Xpath+"["+j+"]"+checkStatus_ARJ_Xpath, "", "", "check status of petition before Accept", "", "Y");
						if(!status.equals("open")&&status.equals("Approval Pending"))
						{
							fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+accept_ARJ_Xpath, "", "", "", "", "");
							fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+accept_ARJ_Xpath, campaignTitle, "", "click on Accept Petition by admin", "", "Y");
							Thread.sleep(3000);
							fl.entervalueByXpath(driver, acceptComments_ARJ_Xpath, comment, "", "", "comment by Admin", "", "");
							fl.ClickByXpath(driver, approve_ARJ_Xpath, "", "", "click on Approveconfirm Button for petition:"+campaignTitle, "", "");
						}
						else
						{
							if(status.equals("open"))
							{
								fl.disp_MessageFailed(driver, "", "", "already opened:"+campaignTitle, "", "");
							}
							else
							{
								if(status.equals("UnPublished"))
								{
									fl.disp_MessageFailed(driver, "", "", "Already Unpublished"+campaignTitle, "", "");
								}
							}
						}
					}
					else
					{
						if(reject!="")
						{
							String  status=fl.getTextXPATH(driver, matchedRow_ARJ_Xpath+"["+j+"]"+checkStatus_ARJ_Xpath, "", "", "check status of petition before Accept", "", "Y");
							if(status.equals("Approval Pending"))
							{
								fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+reject_ARJ_Xpath, "", "", "", "", "");
								fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+reject_ARJ_Xpath, "", "", "click on reject Petition by admin", "", "Y");
								Thread.sleep(3000);
								fl.entervalueByXpath(driver, rejectComments_ARJ_Xpath, comment, "", "", "comment by Admin", "", "");
								fl.ClickByXpath(driver, rejectConfirm_ARJ_Xpath, "", "", "click on RejectConfirm Button for the first time:"+campaignTitle, "", "");
							}
							else
							{
								if(status.equals("open"))
								{
									fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+rejectOpen_ARJ_Xpath, "", "", "", "", "");
									fl.ClickByXpath(driver, matchedRow_ARJ_Xpath+"["+j+"]"+rejectOpen_ARJ_Xpath, "", "", "click on unpublish Petition by admin", "", "Y");
									Thread.sleep(3000);
									fl.entervalueByXpath(driver, rejectOpenComments_ARJ_Xpath, comment, "", "", "comment by Admin", "", "");
									fl.ClickByXpath(driver, unpublishOpen_ARJ_Xpath, "", "", "click on Unpublish already accepted petition by admin:"+campaignTitle, "", "");
								}
								else
								{
									fl.disp_MessageFailed(driver, "", "", "Already Unpublished"+campaignTitle, status, "");
								}
							}
						}
					}
				}
			}
		}
		else
		{
			String noPet=fl.getTextXPATH(driver, noPetition_ARJ_Xpath, "", "", "get text of no results", "", "");
			fl.disp_Message(driver, "", "", "No records Found", noPet, "");
		}
		
		
	}
	public void clickMore(WebDriver driver, String multiMoreButtonFiltered_Xpath, String multiMoreNameXpath, String searchName) throws InterruptedException
	{
		List<WebElement> buttons = driver.findElements(By.xpath(multiMoreButtonFiltered_Xpath));
		List<WebElement> names= driver.findElements(By.xpath(multiMoreNameXpath));
		if(buttons.size()>=1)
		{
			//List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
			if(names.size()>=1)
			{
				for(int i=0;i<names.size();i++)
				{
					if(names.get(i).getText().equals(searchName))
					{
						int j=i+1;
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "", "", "", "");
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
					}
				}
			}
		}
						
	}
	public void viewPetitionValidation(WebDriver driver, String campaignTitle, String campaignType, String PetitionCategeory,  String Tags,
			String enddate, String indefinite, String video, String currencyType, String fund_goal, String no_sign, String social_img, String gal_img1, String gal_img2, String gal_img3, String gal_img4, String DecisionMakerName,
			String Designation, String sup_image1,String sup_image2,String sup_image3,String sup_image4,String sup_image5,String FAQ_ID, String description,
			String linktext, String linkTextUrl, String newWindo, String browse_img, String img_link) throws IOException, InterruptedException
	{
		String Petitions_Xpath=Environment("Petitions_Xpath");
		String ExistedPetitions_Xpath=Environment("ExistedPetitions_Xpath");
		String advancedSearch_Drafts_Xpath=Environment("advancedSearch_Drafts_Xpath");
		String searchPetitionName_Xpath=Environment("searchPetitionName_Xpath");
		String multiMoreButtonFiltered_Xpath=Environment("multiMoreButtonFiltered_Xpath");
		String multiMorePetitionNameXpath=Environment("multiMorePetitionNameXpath");
		String view_DraftPetition_Xpath=Environment("view_DraftPetition_Xpath");
		
		String petitionFor_VAP_Xpath=Environment("petitionFor_VAP_Xpath");
		String categeory_VAP_Xpath=Environment("categeory_VAP_Xpath");
		String fundCurType_VAP_Xpath=Environment("fundCurType_VAP_Xpath");
		String fundGoal_VAP_Xpath=Environment("fundGoal_VAP_Xpath");
		String funReceived_VAP_Xpath=Environment("funReceived_VAP_Xpath");
		String signRequired_VAP_Xpath=Environment("signRequired_VAP_Xpath");
		String signReceived_VAP_Xpath=Environment("signReceived_VAP_Xpath");
		String indefinite_VAP_Xpath=Environment("indefinite_VAP_Xpath");
		String tags_VAP_Xpath=Environment("tags_VAP_Xpath");
		String availableAmount_VAP_Xpath=Environment("availableAmount_VAP_Xpath");
		String endDate_VAP_Xpath=Environment("endDate_VAP_Xpath");
		String video_VAP_Xpath=Environment("video_VAP_Xpath");
		
		String petitonDescription_VAP_Xpath=Environment("petitonDescription_VAP_Xpath");
		String totalDesLists_VAP_Xpath=Environment("totalDesLists_VAP_Xpath");
		String totalParagraphs_VAP_Xpath=Environment("totalParagraphs_VAP_Xpath");
		String singlePara_VAP_Xpath=Environment("singlePara_VAP_Xpath");
		String singleParaNoDesc=Environment("singleParaNoDesc");
		String singleParaImage_VAP_Xpath=Environment("singleParaImage_VAP_Xpath");
		String singleParaLinktext_VAP_Xpath=Environment("singleParaLinktext_VAP_Xpath");
		String singleParainclFontStyle_VAP_Xpath=Environment("singleParainclFontStyle_VAP_Xpath");
		String paraBreak_VAP_Xpath=Environment("paraBreak_VAP_Xpath");
		String overview_VAP_Xpath=Environment("overview_VAP_Xpath");
		String attribute_CPValid_Xpath=Environment("attribute_CPValid_Xpath");
		String gal1_VAP_Xpath=Environment("gal1_VAP_Xpath");
		String gal2_VAP_Xpath=Environment("gal2_VAP_Xpath");
		String gal3_VAP_Xpath=Environment("gal3_VAP_Xpath");
		String gal4_VAP_Xpath=Environment("gal4_VAP_Xpath");
		String gal5_VAP_Xpath=Environment("gal5_VAP_Xpath");
		String support1_VAP_Xpath=Environment("support1_VAP_Xpath");
		String support2_VAP_Xpath=Environment("support2_VAP_Xpath");
		String support3_VAP_Xpath=Environment("support3_VAP_Xpath");
		String support4_VAP_Xpath=Environment("support4_VAP_Xpath");
		String support5_VAP_Xpath=Environment("support5_VAP_Xpath");
		String totalDM_Xpath=Environment("totalDM_Xpath");
		String fullNameDM_VAP_Xpath=Environment("fullNameDM_VAP_Xpath");
		String designationDM_VAP_Xpath=Environment("designationDM_VAP_Xpath");
		String totalFAQ_VAP_Xpath=Environment("totalFAQ_VAP_Xpath");
		String commonqueFAQ_VAP_Xpath=Environment("commonqueFAQ_VAP_Xpath");
		String queTextFAQ_VAP_Xpath=Environment("queTextFAQ_VAP_Xpath");
		String ansFAQ_VAP_Xpath=Environment("ansFAQ_VAP_Xpath");
		String singleparaText;
		String sinParImageText;
		String sinParLinkText;
		
			Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		if(campaignTitle!="")
		{
			fl.JS_Element_Find(driver, Petitions_Xpath);
				fl.ClickByXpath(driver, Petitions_Xpath, "", "", "Validating Petition", "", "");
				fl.ClickByXpath(driver, ExistedPetitions_Xpath, "", "", "click on Petition List", "", "");
				Thread.sleep(10000);
				fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "Search For Petition", "", "");
				//fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "Search For Petition", "", "");
				fl.entervalueByXpath(driver, searchPetitionName_Xpath, campaignTitle, "", "", "enter Petition name:"+campaignTitle, "", "");
				fun_cas.clickMore(driver, multiMoreButtonFiltered_Xpath, multiMorePetitionNameXpath, campaignTitle);
				fl.ClickByXpath(driver, view_DraftPetition_Xpath, "", "", "Click on View", "", "");
				
				Thread.sleep(10000);
			if(campaignType!="")
			{
				String petiFor=fl.getTextXPATH(driver, petitionFor_VAP_Xpath, "", "", "get text of Petition For", "", "");
				System.out.println("petition for:"+petiFor);
				if(campaignType.equals(petiFor))
					fl.disp_Message(driver, "", "", "CampaignType Matches"+campaignType, petiFor, "");
				else
					fl.disp_MessageFailed(driver, "", "", "CampaignType Matches"+campaignType, petiFor, "Y");
				
			}
			if(PetitionCategeory!="")
			{
				String petiCat=fl.getTextXPATH(driver, categeory_VAP_Xpath, "", "", "Get CategeoryType", "", "");
				System.out.println(petiCat);
				if(PetitionCategeory.equals(petiCat))
					fl.disp_Message(driver, "", "", "Petition category:"+PetitionCategeory, petiCat, "");
				else
					fl.disp_MessageFailed(driver, "", "", "Petition category:"+PetitionCategeory, petiCat, "Y");
			}
			if(currencyType!="")
			{
				String curType=fl.getTextXPATH(driver, fundCurType_VAP_Xpath, "", "", "Get currency type", "", "");
				System.out.println(curType);
				if(currencyType.equals(curType))
					fl.disp_Message(driver, "", "", "CurrencyType"+currencyType, curType, "");
				
			}
			if(fund_goal!="")
			{
				String fndGoal_1=fl.getTextXPATH(driver, fundGoal_VAP_Xpath, "", "", "Get Fund Goal", "", "");
				System.out.println(fndGoal_1);
				String fndGoal=fndGoal_1.replaceAll("[,]", "");
				if(fndGoal.contains(fund_goal))
				//if(fndGoal.contains(fund_goal))
					fl.disp_Message(driver, "", "", "Fund Goal: "+fund_goal, fndGoal, "");
				else
					fl.disp_MessageFailed(driver, "", "", "Fund Goal::"+fndGoal, fndGoal, "Y");
					
			}
				/*String fndReceiv = fl.getTextXPATH(driver, funReceived_VAP_Xpath, "", "", "get fundRecived", "", "");
				System.out.println(fndReceiv);*/
			if(no_sign!="")
			{
				String signReq=fl.getTextXPATH(driver, signRequired_VAP_Xpath, "", "", "Get Signs Required", "", "");
				System.out.println(signReq);
				if(no_sign.equals(signReq))
					fl.disp_Message(driver, "", "", "Signs Required:"+no_sign, signReq, "");
				else
					fl.disp_MessageFailed(driver, "", "", "Signs Required:"+no_sign, signReq, "Y");
					
			}
				/*String signRec= fl.getTextXPATH(driver, signReceived_VAP_Xpath, "", "", "Get Signs Received", "", "");
				System.out.println(signRec);
				String ava_amount=fl.getTextXPATH(driver, availableAmount_VAP_Xpath, "", "", "Available Amount", "", "");
				System.out.println(ava_amount);*/
			
			if(enddate!="")//"01/31/2018";
			{
				String end_Date = fl.getTextXPATH(driver, endDate_VAP_Xpath, "", "", "Get End Date", "", "");
				System.out.println(end_Date);//"31 Jan 2018";
				
				String[] parts = enddate.split("/");
				
				for(int i=0;i<parts.length;i++)
				{
					System.out.println(parts[i]);
					if(i==0)
					{
						switch(parts[i])
						{
							case "01":parts[i]= "Jan";
									  break;
							case "02":parts[i]= "Feb";
									  break;
							case "03":parts[i]= "Mar";
									  break;
							case "04":parts[i]= "Apr";
									  break;
							case "05":parts[i]= "May";
									  break;
							case "06":parts[i]= "Jun";
									  break;
							case "07":parts[i]= "Jul";
									  break;
							case "08":parts[i]= "Aug";
									  break;
							case "09":parts[i]= "Sep";
									  break;
							case "10":parts[i]= "Oct";
									  break;
							case "11":parts[i]= "Nov";
									  break;
							case "12":parts[i]= "Dec";
									  break;
							
						}
						System.out.println("Month value is:"+parts[i]);
						if(end_Date.contains(parts[i]))
							fl.disp_Message(driver, "", "", "Expected:"+parts[i], end_Date, "");
						else
							fl.disp_MessageFailed(driver, "", "", "Expected:"+parts[i], end_Date, "Y");
					}
					if(i==1)
					{
						if(end_Date.contains(parts[i]))
							fl.disp_Message(driver, "", "", "Expected:"+parts[i], end_Date, "");
						else
							fl.disp_MessageFailed(driver, "", "", "Expected:"+parts[i], end_Date, "Y");
					}
					if(i==2)
					{
						if(end_Date.contains(parts[i]))
							fl.disp_Message(driver, "", "", "Expected:"+parts[i], end_Date, "");
						else
							fl.disp_MessageFailed(driver, "", "", "Expected:"+parts[i], end_Date, "Y");
					}
				}
				
				
			}
			if(indefinite!="")	
			{
				String indef_disp=fl.elementDisplayed(driver, indefinite_VAP_Xpath, "element displaying or not");
				if(indef_disp.equals("true"))
				{
					String indef = fl.getTextXPATH(driver, indefinite_VAP_Xpath, "", "", "Get Indefinite Status", "", "");
					System.out.println(indef);
					if(indef.equals("Yes"))
						fl.disp_Message(driver, "", "", "Indefinite:Yes", indef, "");
					else
						fl.disp_MessageFailed(driver, "", "", "Indefinite:not Enabled", "", "Y");
				}
				
			}
			if(Tags!="")
			{
				int tot_tag = fun_cas.listSize(driver, tags_VAP_Xpath);
				for(int i=1;i<=tot_tag;i++)
				{
					String tags=fl.getTextXPATH(driver, tags_VAP_Xpath+"["+i+"]", "", "", "Get tag text "+i, "", "");
					System.out.println(tags);
					if(Tags.contains(tags))
						fl.disp_Message(driver, "", "", "Tag text:"+Tags, tags, "");
					else
						fl.disp_MessageFailed(driver, "", "", "Tag text:"+Tags, tags, "Y");
				}
			}
			
			if(video!="")
			{
				int tot_videos=fun_cas.listSize(driver, video_VAP_Xpath);
		
				if(tot_videos==1)
				{
					fl.JS_Element_Find(driver, video_VAP_Xpath);
					//driver.switchTo().frame(0);
					String videoLink1=fun_cas.returnAttribute(driver, video_VAP_Xpath, attribute_CPValid_Xpath);
					System.out.println(videoLink1);
					
					String[] parts = videoLink1.split("/");
					System.out.println("length="+parts.length);
					for(int i=0;i<parts.length;i++)
					{
						System.out.println(parts[i]);
					}
					System.out.println("Last part of link:"+parts[parts.length-1]);
					String videoLink=parts[parts.length-1];
					
					//driver.switchTo().defaultContent();
					if(video.contains(videoLink))
						fl.disp_Message(driver, "", "", "Video link"+video, videoLink, "");
					else
						fl.disp_MessageFailed(driver, "", "", "Video link"+video, videoLink, "Y");
				}
			}
			if(description!="")
			{
				fl.JS_Element_Find(driver, petitonDescription_VAP_Xpath);
				List<WebElement> innerPara=driver.findElements(By.xpath(totalParagraphs_VAP_Xpath));
				if(innerPara.size()==0)
				{//if single para , get text, img and linktext if existed
					int brk_nodesc=fun_cas.listSize(driver, singlePara_VAP_Xpath+singleParaNoDesc);
					if(brk_nodesc==1)
					{
						String singleParaDescrText=fl.getTextXPATH(driver, singlePara_VAP_Xpath, "", "", "get text of single para", "", "");
						System.out.println(singleParaDescrText);
						if(description.contains(singleParaDescrText))
							fl.disp_Message(driver, "", "", "single para Description text:"+description, singleParaDescrText, "");
						else
							fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleParaDescrText, "Y");
					}
				}
				else
				{
			
					if(innerPara.size()==1)
					{
						//get single Para Text
						int fontText=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParainclFontStyle_VAP_Xpath);
						if(fontText==1)
						{
							singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParainclFontStyle_VAP_Xpath, "", "", "get single para text includes font", "", "");
							System.out.println(singleparaText);
							if(description.contains(singleparaText))
								fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
							else
								fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
								
						}
						else
						{
							if(fontText==0)
							{
								singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath, "", "", "get single para text", "", "");
								System.out.println(singleparaText);
								if(description.contains(singleparaText))
									fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
								else
									fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
							}
						}
						//get single para  imag attribute value
						if(browse_img!="")
						{
							int singleparaimg =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath);
							if(singleparaimg==1)
							{
								sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath, "data-filename");
								System.out.println(sinParImageText);
								if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
									fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
								else
									fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
							}
							else
							{
								if(singleparaimg>1)
								{
									for(int i=1;i<=singleparaimg;i++)
									{
										sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath+"["+i+"]", "data-filename");
										System.out.println(sinParImageText);
										if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
											fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
										else
											fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
									}
								}
								else
								{
									if(singleparaimg==0)
									{
										fl.disp_Message(driver, "", "Single para contains no images", "no images found", "", "");
									}
								}
							}
						}
						//get single para link text //linkTextUrl//newWindo
						if(linktext!="")
						{
							int singleparalink =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath);
							if(singleparalink==1)
							{
								sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath, "", "", "get Single Para Link Text", "", "");
								System.out.println(sinParLinkText);
								if(linktext.equals(sinParLinkText))
									fl.disp_Message(driver, "", "", "Single para contains Linktext:"+linktext, sinParLinkText, "");
								else
									fl.disp_Message(driver, "", "", "Single para contains No Linktext:"+linktext, sinParLinkText, "");
							}
							else
							{
								if(singleparalink>1)
								{
									for(int i=1;i<=singleparalink;i++)
									{
										fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath+"["+i+"]");
										sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath+"["+i+"]", "", "", "get Single Para contains multiple Link Text", "", "");
										System.out.println(sinParLinkText);
										if(linktext.equals(sinParLinkText))
											fl.disp_Message(driver, "", "", "Single para contains Linktext:"+linktext, sinParLinkText, "");
										else
											fl.disp_Message(driver, "", "", "Single para contains No Linktext:"+linktext, sinParLinkText, "");
									}
								}
								else
								{
									if(singleparalink==0)
									{
										fl.disp_Message(driver, "", "Single para contains no link text", "no link text  found", "", "");
									}
								}
							}
						}
					}
						else
						{
							if(innerPara.size()>1)
							{
								for(int k=1;k<=innerPara.size();k++)
								{
									fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
									//get single Para linkText
						
									int fontText=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath);
									if(fontText==1)
									{
										int brk=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath+paraBreak_VAP_Xpath);
										if(brk==0)
									{
									
										singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath, "", "", "get multiple para text includes font", "", "");
										System.out.println(singleparaText);
										if(description.contains(singleparaText))
											fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
										else
											fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
									}
								}
								else
								{
									int overview=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+overview_VAP_Xpath);
								
									if(fontText==0)
									{
										if(overview==0)
										{
											singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]", "", "", "get multiple para text", "", "");
											System.out.println(singleparaText);
											if(description.contains(singleparaText))
												fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
											else
												fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
										}
									}
								}
							//get single para  imag attribute value  //browse_img
								if(browse_img!="")
								{
									int singleparaimg =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath);
									if(singleparaimg==1)
									{
										sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath, "data-filename");
										System.out.println(sinParImageText);
										if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
											fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
										else
											fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
									}
									else
									{
										if(singleparaimg>1)
										{
											for(int i=1;i<=singleparaimg;i++)
											{
												fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
												sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath+"["+i+"]", "data-filename");
												System.out.println(sinParImageText);
												if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
													fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
												else
													fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
											}
										}
										else
										{
											if(singleparaimg==0)
											{
												fl.disp_Message(driver, "", "multiple para contains no images", "no images found", "", "");
											}
										}
									}
								}
							//get single para link text //linkTextUrl //newWindo
							if(linktext!="")
							{
								int singleparalink =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath);
								if(singleparalink==1)
								{
									sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath, "", "", "get multiple Para Link Text", "", "");
									System.out.println(sinParLinkText);
									if(linktext.equals(sinParLinkText))
										fl.disp_Message(driver, "", "", "Multiple para contains Linktext:"+linktext, sinParLinkText, "");
									else
										fl.disp_Message(driver, "", "", "Multiple para contains No Linktext:"+linktext, sinParLinkText, "");
								}
								else
								{
									if(singleparalink>1)
									{
										for(int i=1;i<=singleparalink;i++)
										{
											fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
											sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath, "", "", "get multiple Para contains multiple Link Text", "", "");
											System.out.println(sinParLinkText);
											if(linktext.equals(sinParLinkText))
												fl.disp_Message(driver, "", "", "Multiple para contains Linktext:"+linktext, sinParLinkText, "");
											else
												fl.disp_Message(driver, "", "", "Multiple para contains No Linktext:"+linktext, sinParLinkText, "");
										}
									}
									else
									{
										if(singleparalink==0)
										{
											fl.disp_Message(driver, "", "multiple para contains no link text", "no link text  found", "", "");
										}
									}
								}
							}
						}
					}
				}
			}
		}
				//IMAGE VALIDATION
				fl.JS_Element_Find(driver, gal1_VAP_Xpath);
			if(social_img!="")
			{
				fun_cas.attributeValue(driver, gal1_VAP_Xpath, attribute_CPValid_Xpath, social_img);
			}
			if(gal_img1!="")
			{
				fun_cas.attributeValue(driver, gal2_VAP_Xpath, attribute_CPValid_Xpath, gal_img1);
			}
			if(gal_img2!="")
			{
				fun_cas.attributeValue(driver, gal3_VAP_Xpath, attribute_CPValid_Xpath, gal_img2);
			}
			if(gal_img3!="")
			{
				fun_cas.attributeValue(driver, gal4_VAP_Xpath, attribute_CPValid_Xpath, gal_img3);
			}
			if(gal_img4!="")
			{
				fun_cas.attributeValue(driver, gal5_VAP_Xpath, attribute_CPValid_Xpath, gal_img4);
			}
			
			//DECISION MAKER VALIDATION
			//DecisionMakerName,Designation
				int dm_s=fun_cas.listSize(driver, totalDM_Xpath);
				if(dm_s>=1)
				{
					for(int i=1;i<=dm_s;i++)
					{
						String fullname = fl.getTextXPATH(driver, totalDM_Xpath+"["+i+"]"+fullNameDM_VAP_Xpath, "", "", "get text of desionamker "+i+" fullname:", "", "");
						System.out.println("DM_Fullname"+i+":"+fullname);
						if(fullname.equals(DecisionMakerName))
							fl.disp_Message(driver, "", "", "decision maker name:"+DecisionMakerName, fullname, "");
						else
							fl.disp_MessageFailed(driver, "", "", "decision maker name:"+DecisionMakerName, fullname, "Y");
						String designation = fl.getTextXPATH(driver, totalDM_Xpath+"["+i+"]"+designationDM_VAP_Xpath, "", "", "get text of desionamker "+i+" Designation:", "", "");
						System.out.println("DM_Designation"+i+":"+designation);
						if(designation.equals(Designation))
							fl.disp_Message(driver, "", "", "decision maker Designation:"+Designation, designation, "");
						else
							fl.disp_MessageFailed(driver, "", "", "decision maker Designation:"+Designation, designation, "Y");
					}
				}
				else
				{
					fl.disp_Message(driver, "", "", "you are not given any decision maker details", "", "");
				}
			
			//SUPPORTING DOCUMENTS VALIDATION
			fl.JS_Element_Find(driver, support1_VAP_Xpath);
			if(sup_image1!="")
			{
				fun_cas.attributeValue(driver, support1_VAP_Xpath, attribute_CPValid_Xpath, sup_image1);
			}
			if(sup_image2!="")
			{
				fun_cas.attributeValue(driver, support2_VAP_Xpath, attribute_CPValid_Xpath, sup_image2);
			}
			if(sup_image3!="")
			{
				fun_cas.attributeValue(driver, support3_VAP_Xpath, attribute_CPValid_Xpath, sup_image3);
			}
			if(sup_image4!="")
			{
				fun_cas.attributeValue(driver, support4_VAP_Xpath, attribute_CPValid_Xpath, sup_image4);
			}
			if(sup_image5!="")
			{
				fun_cas.attributeValue(driver, support5_VAP_Xpath, attribute_CPValid_Xpath, sup_image5);
			}
			//FAQ VALIDATION
			if(FAQ_ID!="")
			{
				int queno=0;
				List<String> faq = new ArrayList<>();
				//List<String> faq = null;
				List<String> webfaq = new ArrayList<>();
				//List<String> webfaq=null;
				Excel_Utils RC = new Excel_Utils(Environment("Excel"));
				String FAQ_CreatePetition=Environment("Sheet_FAQ_CreatePetition"); 
				int FAQ_CreatePetition_row=RC.getLastrowno(FAQ_CreatePetition); 
				System.out.println("total faq petition rows are:"+FAQ_CreatePetition_row);
				int FAQ_CreatePetition_col=RC.getLastcolmno(FAQ_CreatePetition); 
				String[] FAQ_CreatePetition_ele=new String[FAQ_CreatePetition_col]; 
				for (int FAQ_CreatePetition_index = 1; FAQ_CreatePetition_index < FAQ_CreatePetition_row; FAQ_CreatePetition_index++) 
				{ 
					 System.out.println("for Loop" );
					 System.out.println(FAQ_ID);
					 System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"), FAQ_CreatePetition)); 
					 if (FAQ_ID.equals(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)))
						  // Adduser contains company email_id at 1st column  for validation
					 { 
						 queno++;
						  System.out.println("Matches ID to Register");
						  System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)); 
						  //based on j value get the row data and do Adding Users
						   
						  for(int FAQ_CreatePetition_ind=0;FAQ_CreatePetition_ind<FAQ_CreatePetition_col;FAQ_CreatePetition_ind++) 
						  {
							  FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]=RC.getStringCellData(FAQ_CreatePetition_index, FAQ_CreatePetition_ind, FAQ_CreatePetition);
							  System.out.println(FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]); //call login as company method, pass array values
			  
				  
						  }
						  faq.add(FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Question")]);
						  faq.add(FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Answer")]);
				          
					 }
				}
				
				int faqs=fun_cas.listSize(driver, totalFAQ_VAP_Xpath);
				System.out.println("In validation : Total faq are:"+queno);
				
//TOTAL QUESTIONS IN FAQ MATCHES OR NOT		
				int faq_siz=faq.size()/2;
				/*if(faq_siz==faqs)
				{
					fl.disp_Message(driver, "", "", "Total Question in FAQ in view Same", "", "");
				}*/
				if(faqs>=1)
				{
					for(int i=1;i<=faqs;i++)
					{
						String faq_Q=fl.getTextXPATH(driver, commonqueFAQ_VAP_Xpath+i+queTextFAQ_VAP_Xpath, "", "", "get question "+i+" text", "", "");
						System.out.println(faq_Q);
						webfaq.add(faq_Q);
						//fl.disp_Message(driver, "", "", "get question "+i+" text:"+, ActualResult, Screenshot);
						String faq_A=fl.getTextXPATH(driver, commonqueFAQ_VAP_Xpath+i+ansFAQ_VAP_Xpath, "", "", "get answer "+i+" text", "", "");
						System.out.println(faq_A);
						webfaq.add(faq_A);
					}
				}
				System.out.println("faq excel list size::"+faq.size());
				System.out.println("faq web list size::"+webfaq.size());
				int webfaq_siz=webfaq.size()/2;
				String webfaq_size=String.valueOf(webfaq_siz);
				if(faq_siz==webfaq_siz)
				{
					fl.disp_Message(driver, "", "", "FAQ questions are"+faq_siz, webfaq_size, "");
					for(int i=0;i<webfaq.size();i++)
					{
						if(i%2==0)
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
								fl.disp_MessageFailed(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							
						}	
						else
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
							{
								fl.disp_MessageFailed(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							}
						}
					}
				}
				else
					fl.disp_MessageFailed(driver, "", "", "FAQ questions are :"+faq_siz, webfaq_size, "Y");
			}
			else
			{
				fl.disp_Message(driver, "", "", "No FAQ given by you", "", "");
			}
		}
	}
	public void ValidatePetiInWebsite(WebDriver driver, String petition, String raisedFund, String signReq,
			String description, String browse_imag, String link, String faqID) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String createBy_PVW_Xpath=Environment("createBy_PVW_Xpath");
		String createorg_PVW_Xpath=Environment("createorg_PVW_Xpath");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName2_PVW_Xpath=Environment("suppOrgName2_PVW_Xpath");
		String raisedFund_PVW_Xpath=Environment("raisedFund_PVW_Xpath");
		String signReq_PVW_Xpath=Environment("signReq_PVW_Xpath");
		String daysLeft_PVW_Xpath=Environment("daysLeft_PVW_Xpath");
		String details_PVW_Xpath=Environment("details_PVW_Xpath");
		String totalPara_PVW_Xpath=Environment("totalPara_PVW_Xpath");
		String noDesc_PVW_Xpath=Environment("noDesc_PVW_Xpath");
		String fontDesc_PVW_Xpath=Environment("fontDesc_PVW_Xpath");
		String linktext_PVW_Xpath=Environment("linktext_PVW_Xpath");
		String img_PVW_Xpath=Environment("img_PVW_Xpath");
		String faqs_PVW_Xpath=Environment("faqs_PVW_Xpath");
		String totFaq_PVW_Xpath=Environment("totFaq_PVW_Xpath");
		String queFaq_PVW_Xpath=Environment("queFaq_PVW_Xpath");
		String ansFaq_PVW_Xpath=Environment("ansFaq_PVW_Xpath");
		if(petition.equals("true"))
		{
			fun_cas.getTextValidation(driver, raisedFund_PVW_Xpath, raisedFund);
			fun_cas.getTextValidation(driver, signReq_PVW_Xpath, signReq);
			fl.ClickByXpath(driver, details_PVW_Xpath, "", "", "Click on Details Tab", "", "");
			
			int paras = fun_cas.listSize(driver, totalPara_PVW_Xpath);
			if(paras==1)
			{
				int nopara=fun_cas.listSize(driver, totalPara_PVW_Xpath+noDesc_PVW_Xpath);
				if(nopara==1)
				{
					fl.disp_Message(driver, "", "", "No Descrption available", "", "");
				}
				else
				{
					int singleParaText=fun_cas.listSize(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath);
					if(singleParaText==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath, description);
					int singleParaImg=fun_cas.listSize(driver, totalPara_PVW_Xpath+img_PVW_Xpath);
					if(singleParaImg==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+img_PVW_Xpath, browse_imag);
					int singleParaLink=fun_cas.listSize(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath);
					if(singleParaLink==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath, link);
				}
				
			}
			else
			{
				if(paras>1)
				{
					for(int i=1;i<=paras;i++)
					{
						int multipleParaNoText=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+fontDesc_PVW_Xpath+noDesc_PVW_Xpath);
						if(multipleParaNoText==1)
						{
							
						}
						else
						{
							int multipleParaText=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+fontDesc_PVW_Xpath);
							if(multipleParaText==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath, description);
							int multipleParaImg=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+img_PVW_Xpath);
							if(multipleParaImg==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+img_PVW_Xpath, browse_imag);
							int multipleParaLink=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+linktext_PVW_Xpath);
							if(multipleParaLink==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath, link);
						}
					}
				}
			}
			if(faqID!="")
			{
				//FAQ VALIDATION
				int queno=0;
				List<String> faq = new ArrayList<>();
				//List<String> faq = null;
				List<String> webfaq = new ArrayList<>();
				//List<String> webfaq=null;
				Excel_Utils RC = new Excel_Utils(Environment("Excel"));
				String FAQ_CreatePetition=Environment("Sheet_FAQ_CreatePetition"); 
				int FAQ_CreatePetition_row=RC.getLastrowno(FAQ_CreatePetition); 
				System.out.println("total faq petition rows are:"+FAQ_CreatePetition_row);
				int FAQ_CreatePetition_col=RC.getLastcolmno(FAQ_CreatePetition); 
				String[] FAQ_CreatePetition_ele=new String[FAQ_CreatePetition_col]; 
				for (int FAQ_CreatePetition_index = 1; FAQ_CreatePetition_index < FAQ_CreatePetition_row; FAQ_CreatePetition_index++) 
				{ 
					System.out.println("for Loop" );
					System.out.println(faqID);
					System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"), FAQ_CreatePetition)); 
					if (faqID.equals(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)))
						// Adduser contains company email_id at 1st column  for validation
					{ 
						queno++;
						System.out.println("Matches ID to Register");
						System.out.println(RC.getStringCellData(FAQ_CreatePetition_index, RC.Current_Coulumn_Number(FAQ_CreatePetition, "FAQID"),FAQ_CreatePetition)); 
						//based on j value get the row data and do Adding Users
					   
						for(int FAQ_CreatePetition_ind=0;FAQ_CreatePetition_ind<FAQ_CreatePetition_col;FAQ_CreatePetition_ind++) 
						{
							FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]=RC.getStringCellData(FAQ_CreatePetition_index, FAQ_CreatePetition_ind, FAQ_CreatePetition);
							System.out.println(FAQ_CreatePetition_ele[FAQ_CreatePetition_ind]); //call login as company method, pass array values
		  
			  
						}
						faq.add(FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Question")]);
						faq.add(FAQ_CreatePetition_ele[RC.Current_Coulumn_Number(FAQ_CreatePetition, "Answer")]);
			          
					}
				}
				fl.ClickByXpath(driver, faqs_PVW_Xpath, "", "", "Click on FAQs tab", "", "");
			
				int faqs=fun_cas.listSize(driver, totFaq_PVW_Xpath);
				System.out.println("In validation : Total faq are:"+queno);
			
				int faq_siz=faq.size()/2;
			
				if(faqs>=1)
				{
					for(int i=1;i<=faqs;i++)
					{
						String faq_Q=fl.getTextXPATH(driver, totFaq_PVW_Xpath+"["+i+"]"+queFaq_PVW_Xpath, "", "", "get question "+i+" text", "", "");
						System.out.println(faq_Q);
						webfaq.add(faq_Q);
						//fl.disp_Message(driver, "", "", "get question "+i+" text:"+, ActualResult, Screenshot);
						String faq_A=fl.getTextXPATH(driver, totFaq_PVW_Xpath+"["+i+"]"+ansFaq_PVW_Xpath, "", "", "get answer "+i+" text", "", "");
						System.out.println(faq_A);
						webfaq.add(faq_A);
					}
				}
				System.out.println("faq excel list size::"+faq.size());
				System.out.println("faq web list size::"+webfaq.size());
				int webfaq_siz=webfaq.size()/2;
				String webfaq_size=String.valueOf(webfaq_siz);
				if(faq_siz==webfaq_siz)
				{
					fl.disp_Message(driver, "", "", "FAQ questions are"+faq_siz, webfaq_size, "");
					for(int i=0;i<webfaq.size();i++)
					{
						if(i%2==0)
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
								fl.disp_MessageFailed(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							
						}	
						else
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
							{
								fl.disp_MessageFailed(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							}
						}
					}
				}
				else
					fl.disp_MessageFailed(driver, "", "", "FAQ questions are :"+faq_siz, webfaq_size, "Y");
			}
		}
	}
	public List<String> beforeSignNewUserValidation(WebDriver driver) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String sign_FundEnbleCheckInWebSite_Xpath=Environment("sign_FundEnbleCheckInWebSite_Xpath");
		String signOrFundEnabled_Xpath=Environment("signOrFundEnabled_Xpath");
		String signRec_PVW_Xpath=Environment("signRec_PVW_Xpath");
		String signReq_PVW_Xpath=Environment("signReq_PVW_Xpath");
		String signPercent_PVW_Xpath=Environment("signPercent_PVW_Xpath");
		String raisedFund_PVW_Xpath=Environment("raisedFund_PVW_Xpath");
		String receivedFund_PVW_Xpath=Environment("receivedFund_PVW_Xpath");
		String funPercent_PVW_Xpath=Environment("funPercent_PVW_Xpath");
		int i=0;
		List<String> signs_funds = new ArrayList<>();
		
		int signFunEnbled=fun_cas.listSize(driver, sign_FundEnbleCheckInWebSite_Xpath);
		
		if(signFunEnbled==2)
		{
			String SignsRec=fl.getTextXPATH(driver, signRec_PVW_Xpath, "", "", "before Signed by user", "", "");
		
			if(SignsRec!="")
			{
				i++;
				fl.disp_Message(driver, "", "", "Before Sign ", SignsRec, "");
				signs_funds.add(SignsRec);
			}
			else
			{
				signs_funds.add("SignNotAvailable");
			}
		
			String fundRec=fl.getTextXPATH(driver, receivedFund_PVW_Xpath, "", "", "before donating fund", "", "");
			if(fundRec!="")
			{
				i++;
				fl.disp_Message(driver, "", "", "Before funding", fundRec, "");
				signs_funds.add(fundRec);
			}
			else
			{
				signs_funds.add("FundNotAvailable");
			}
		}
		else
		{
			if(signFunEnbled==1)
			{
				String enabled=fun_cas.returnAttribute(driver, signOrFundEnabled_Xpath, "class");
				if(enabled.contains("sign"))
				{
					String SignsRec=fl.getTextXPATH(driver, signRec_PVW_Xpath, "", "", "before Signed by user", "", "");
					
					if(SignsRec!="")
					{
						i++;
						fl.disp_Message(driver, "", "", "Before Sign ", SignsRec, "");
						signs_funds.add(SignsRec);
						signs_funds.add("FundNotAvailable");
					}
					else
					{
						signs_funds.add("SignNotAvailable");
						signs_funds.add("FundNotAvailable");
					}
					
				}
				else
				{
					if(enabled.contains("money"))
					{
						String fundRec=fl.getTextXPATH(driver, receivedFund_PVW_Xpath, "", "", "before donating fund", "", "");
						if(fundRec!="")
						{
							i++;
							fl.disp_Message(driver, "", "", "Before funding", fundRec, "");
							signs_funds.add("SignNotAvailable");
							signs_funds.add(fundRec);
						}
						else
						{
							signs_funds.add("SignNotAvailable");
							signs_funds.add("FundNotAvailable");
						}
					}
				}
			}
		}
		
		return signs_funds;
		
	}
	
	public void afterSignNewUserValidation(WebDriver driver, List<String> beforeSign) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String signRec_PVW_Xpath=Environment("signRec_PVW_Xpath");
		String signReq_PVW_Xpath=Environment("signReq_PVW_Xpath");
		String signPercent_PVW_Xpath=Environment("signPercent_PVW_Xpath");
		String raisedFund_PVW_Xpath=Environment("raisedFund_PVW_Xpath");
		String receivedFund_PVW_Xpath=Environment("receivedFund_PVW_Xpath");
		String funPercent_PVW_Xpath=Environment("funPercent_PVW_Xpath");
		
		List<String> signs_funds = new ArrayList<>();
		System.out.println("list element sign:"+beforeSign.get(0));
		System.out.println("list element fund:"+beforeSign.get(1));
		
		String beforeSign_Count=beforeSign.get(0);
		String beforeFund_Count=beforeSign.get(1);
		Thread.sleep(3000);
		
		if(!beforeSign_Count.equals("SignNotAvailable"))
		{
			String SignsRec=fl.getTextXPATH(driver, signRec_PVW_Xpath, "", "", "After Signed by user", "", "");
			System.out.println("Total Signatures in String format:"+SignsRec);
			int RecSign=fun_cas.stringToIntegerconvert(SignsRec);
			System.out.println("After sign received sign in int format:"+RecSign);
			int RecSignBeforSign_a=fun_cas.stringToIntegerconvert(beforeSign.get(0));
			System.out.println("Before sign received sign in int format:"+beforeSign.get(0));
			int RecSignBeforSign=RecSignBeforSign_a+1;
			if(RecSign==RecSignBeforSign)
			{
				fl.disp_Message(driver, "", "", "After Sign : "+RecSignBeforSign_a, SignsRec, "Y");
			}
		}
		if(!beforeFund_Count.equals("FundNotAvailable"))
		{
			String fundRec=fl.getTextXPATH(driver, receivedFund_PVW_Xpath, "", "", "after donating fund", "", "");
			String fundReceived=fundRec.replaceAll("[$ ]", "");//replaceAll("[-()]", "");
			System.out.println("After replacing $ with null after sign string:"+fundReceived);
			int RecFund=fun_cas.stringToIntegerconvert(fundReceived);
		
			String beforeSignFund=beforeSign.get(1).replaceAll("[$ ]", "");
			System.out.println("After replacing $ with null before sign string:"+beforeSignFund);
			int RecFundBeforSign_a=fun_cas.stringToIntegerconvert(beforeSignFund);
		
			int RecFundBeforSign=RecFundBeforSign_a+1;
			if(RecFund==RecFundBeforSign)
			{
				fl.disp_Message(driver, "", "", "After Sign : "+RecFundBeforSign_a, fundRec, "Y");
			}
		}
	}
	public void viewPetition(WebDriver driver, String Petition) throws InterruptedException, IOException
	{
		String Petitions_Xpath= Environment("Petitions_Xpath");
		String ExistedPetitions_Xpath = Environment("ExistedPetitions_Xpath");
		String advancedSearch_Drafts_Xpath= Environment("advancedSearch_Drafts_Xpath");
		String searchPetitionName_Xpath=Environment("searchPetitionName_Xpath");
		String multiMoreButtonFiltered_Xpath= Environment("multiMoreButtonFiltered_Xpath");
		String multiMorePetitionNameXpath= Environment("multiMorePetitionNameXpath");
		String view_DraftPetition_Xpath= Environment("view_DraftPetition_Xpath");
		
		fl.ClickByXpath(driver, Petitions_Xpath, "creating a petition", "Petitions", "Petitions menu displayed", "", "");
		
		fl.ClickByXpath(driver, ExistedPetitions_Xpath, "", "Going to Existed Petitiion", "Petition Displayed", "", "");
		Thread.sleep(10000);
		fl.ClickByXpath(driver, advancedSearch_Drafts_Xpath, "", "", "", "", "");
		
		fl.entervalueByXpath(driver, searchPetitionName_Xpath, Petition, "", "Searching with petition name ", "display petition name search box", "", "");
		
		List<WebElement> buttons = driver.findElements(By.xpath(multiMoreButtonFiltered_Xpath));
		List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
		if(buttons.size()>1)
		{
			//List<WebElement> names= driver.findElements(By.xpath(multiMorePetitionNameXpath));
			
			if(names.size()>1)
			{
				for(int i=0;i<names.size();i++)
				{
					if(names.get(i).getText().equals(Petition))
					{
						int j=i+1;
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "", "", "", "");
						fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"["+j+"]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
						
						fl.ClickByXpath(driver, view_DraftPetition_Xpath, "", "click on view", "", "", "");
						break;
						
					}
				}
			}
		}
		else
		{
			if(buttons.size()==1)
			{
				if(names.get(0).getText().equals(Petition))
				{
					fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"[1]", "", "", "", "", "");
					fl.ClickByXpath(driver, multiMoreButtonFiltered_Xpath+"[1]", "", "MoreOptions button to be clicked", "Display More Options", "", "");
				
					fl.ClickByXpath(driver, view_DraftPetition_Xpath, "", "Click on View", "", "", "");
				
					
				}
			}
			else
			{
				fl.disp_Message(driver, "", "There are no petition existed with "+Petition, "", "", "");
				System.out.println("There are no petition existed with "+Petition);
				
			}
		}
	}
	public void signFundValidInCompany(WebDriver driver, String firstName, String lastName, String org, String occupation, String address) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String signTab_SFV_Xpath= Environment("signTab_SFV_Xpath");
		String fundTab_SFV_Xpath=Environment("fundTab_SFV_Xpath");
		String totalSigns_SFV_Xpath=Environment("totalSigns_SFV_Xpath");
		String name_SFV_Xpath=Environment("name_SFV_Xpath");
		String org_SFV_Xpath=Environment("org_SFV_Xpath");
		String occupation_SFV_Xpath=Environment("occupation_SFV_Xpath");
		String address_SFV_Xpath=Environment("address_SFV_Xpath");
		//String emailID_SFV_Xpath=Environment("emailID_SFV_Xpath");
		
		fl.ClickByXpath(driver, signTab_SFV_Xpath, "", "", "Click on Signatures Tab", "", "");
		int signs=fun_cas.listSize(driver, totalSigns_SFV_Xpath);
		if(signs>=1)
		{
			int k=0;
			for(int i=1;i<=signs;i++)
			{
				fl.JS_Element_Find(driver, totalSigns_SFV_Xpath+"["+i+"]"+name_SFV_Xpath);
				String Name=fl.getTextXPATH(driver, totalSigns_SFV_Xpath+"["+i+"]"+name_SFV_Xpath, "", "Get Name", "", "", "");
				String Orga=fl.getTextXPATH(driver, totalSigns_SFV_Xpath+"["+i+"]"+org_SFV_Xpath, "", "Get Organization supported for", "", "", "");
				if(Name.contains(firstName)&&Name.contains(lastName)&&Orga.equals(org))
				{
					if(occupation!="")
					{
						String Occu=fl.getTextXPATH(driver, totalSigns_SFV_Xpath+"["+i+"]"+occupation_SFV_Xpath, "", "Get Occupation", "", "", "");
						if(Occu.equals(occupation))
						{
							fl.disp_Message(driver, "", "", "Petition Signed by User Name listed in Signatures tab in Detailed Petition View:"+firstName+","+lastName+","+org+","+occupation, Name+","+Orga+","+Occu, "Y");
							break;
						}
						else
						{
							fl.disp_MessageFailed(driver, "", "", "Petition Signed by User Occupation listed in Signatures tab in Detailed Petition View:"+occupation, Occu, "Y");
							break;
						}
					}
					if(address!="")
					{
						String addr=fl.getTextXPATH(driver, totalSigns_SFV_Xpath+"["+i+"]"+address_SFV_Xpath, "", "Get Address:", "", "", "");
						if(addr.equals(address))
						{
							fl.disp_Message(driver, "", "", "Petition Signed by Address listed in Signatures tab in Detailed Petition View:"+firstName+","+lastName+","+org+","+address, Name+","+Orga+","+addr, "Y");
							break;
						}
						else
						{
							fl.disp_Message(driver, "", "", "Petition Signed by User Occupation listed in Signatures tab in Detailed Petition View:"+address, addr, "Y");
							break;
						}
					}
					k++;
					fl.disp_Message(driver, "", "", "Petition Signed by User Name listed in Signatures tab in Detailed Petition View:"+firstName, Name, "Y");
					break;
				}
				else
				{
					if(k==0&&i==signs)
					{
						fl.disp_MessageFailed(driver, "", "", "Petition Signed by User Name not listed in Signatures tab in Detailed Petition View:"+firstName, Name, "Y");
						break;
					}
				}
					
			}
		}
		else
		{
			if(signs==0)
			{
				fl.disp_MessageFailed(driver, "", "", "Petition Signed by User Name not listed in Signatures tab in Detailed Petition View:"+firstName, "Error Occured", "Y");
			}
		}
		
	}
	public List<String> supportCauseExistedCompany(WebDriver driver, String status,String email, String password, String comments) throws IOException, InterruptedException
	{
		List<String> comp_Info=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String Comapany_BaseURL_Digi=Environment("Comapany_BaseURL_Digi");
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName2_PVW_Xpath=Environment("suppOrgName2_PVW_Xpath");
		String companyName_SCV_Xpath=Environment("companyName_SCV_Xpath");
		String UserName_PVW_Xpath=Environment("UserName_PVW_Xpath");
		String companyEmail_PVW_Xpath=Environment("companyEmail_PVW_Xpath");
		String supportTheCause_Xpath=Environment("supportTheCause_Xpath");
		String username_Company_Xpath=Environment("username_Company_Xpath");
		String password_Company_Xpath=Environment("password_Company_Xpath");
		String login_Company_Xpath=Environment("login_Company_Xpath");
		String cancel_Company_Xpath=Environment("cancel_Company_Xpath");
		String comments_Xpath=Environment("comments_Xpath");
		String douwanttoDonate_Xpath=Environment("douwanttoDonate_Xpath");
		String submit_Company_Cause_Xpath=Environment("submit_Company_Cause_Xpath");
		String cancel_CompanySupportCause_Xpath=Environment("cancel_CompanySupportCause_Xpath");
		String dashboard_webSite_Xpath=Environment("dashboard_webSite_Xpath");
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String companyProfile5_Xpath=Environment("companyProfile5_Xpath");
		String legalName_CPValid_Xpath=Environment("legalName_CPValid_Xpath");
		String logoutName_Xpath=Environment("logoutName_Xpath");
		
		Thread.sleep(10000);
		String beforSuportOrgNo=fl.getTextXPATH(driver, suppOrgNo_PVW_Xpath, "", "", "before support total supported org", "", "");
		comp_Info.add(beforSuportOrgNo);
		System.out.println("before support org total org are:"+beforSuportOrgNo);
		
		if(status.equals(""))
		{
			String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
			if(fundUna_att.contains("display: block;"))
				fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "CLick on ok popup(funding unavailable)", "", "", "");
			fl.ClickByXpath(driver, supportTheCause_Xpath, "", "", "Click on Support Cause", "", "");
			fl.entervalueByXpath(driver, username_Company_Xpath, email, "", "", "Enter User name", "", "");
			fl.entervalueByXpath(driver, password_Company_Xpath, password, "", "", "Enter Password", "", "");
			fl.ClickByXpath(driver, login_Company_Xpath, "", "", "Click on Login Button", "", "");
			fl.entervalueByXpath(driver, comments_Xpath, comments, "", "", "", "", "");
			fl.ClickByXpath(driver, submit_Company_Cause_Xpath, "", "", "Click on Submit", "", "");
		
			String Parent=driver.getWindowHandle();
		
			fl.ClickByXpath(driver, dashboard_webSite_Xpath, "", "Click on Dashboard Button In WebSite", "", "", "");
		
		
			Set<String> set = new HashSet<String>(driver.getWindowHandles());
			for(String tab : set)
			{
				System.out.println("window :"+tab);
			}
			set.remove(Parent);

			driver.switchTo().window(set.iterator().next());
		
			String userName=fl.getTextXPATH(driver, logoutName_Xpath, "", "Get UserName", "", "", "");
			comp_Info.add(userName);
		
			fl.ClickByXpath(driver, logout_img_Xpath, "", "Click on Logout Image", "", "", "");
			int logoutOptions=fun_cas.listSize(driver, logoutOptions_Xpath);
			if(logoutOptions==6)
			{
				fl.ClickByXpath(driver, companyProfile_Xpath, "", "Click on Company Profile", "", "", "");
				String legalName=fl.getTextXPATH(driver, legalName_CPValid_Xpath, "", "Get Company Legal Name", "", "", "");
				comp_Info.add(legalName);
			}
			else
			{
				if(logoutOptions==5)
				{
					fl.ClickByXpath(driver, companyProfile5_Xpath, "", "Click on Company Profile", "", "", "");
					String legalName=fl.getTextXPATH(driver, legalName_CPValid_Xpath, "", "Get Company Legal Name", "", "", "");
					comp_Info.add(legalName);
				}
			}
			fun_cas.CompanyLogout(driver);
			driver.close();
			driver.switchTo().window(Parent);
		
			
		}
		return comp_Info;
		//Thread.sleep(10000);
		/*String afterSuportOrgNo=fl.getTextXPATH(driver, suppOrgNo_PVW_Xpath, "", "", "after support total supported org", "", "");
		
		fl.disp_Message(driver, "", "", "before Support:"+beforSuportOrgNo, "after Support:"+afterSuportOrgNo, "");
		
		int companies=fun_cas.stringToIntegerconvert(afterSuportOrgNo);
		System.out.println("Total Supported Org are:"+companies);
		if(companies>=1)
		{
			int count =0;
			for(int i=1;i<=companies;i++)
			{
				System.out.println("iteration:"+i);
				driver.navigate().refresh();
				fl.ClickByXpath(driver, suppOrgName1_PVW_Xpath, "", "Click on Support Organizations List", "", "", "");
				fl.JS_Element_Find(driver, suppOrgName1_PVW_Xpath+"["+i+"]");
				fl.ClickByXpath(driver, suppOrgName1_PVW_Xpath+"["+i+"]", "", "", "click on company:"+i, "", "");
				Thread.sleep(3000);
				
				String comp_email=fl.getTextXPATH(driver, companyEmail_PVW_Xpath, "", "", "Get email Id", "", "");
				if(comp_email.equals(email))
				{
					count++;
					String companyName=fl.getTextXPATH(driver, companyName_SCV_Xpath, "", "", "Get Text of Company to validate on CompanySide SupportOrganizations List", "", "");
					fl.disp_Message(driver, "", "Company Name Text:", "", companyName, "");
					comp_Info.add(companyName);
					fl.disp_Message(driver, "", "Comments Are:", "", comments, "");
					comp_Info.add(comments);
					fl.disp_Message(driver, "", "", email, comp_email, "Y");
					break;
				}
				else
				{
					System.out.println("email not matches driver navigate to back");
					Thread.sleep(3000);
					System.out.println(driver);
					driver.navigate().back();
				}
				
				
				
			}
			if(count==0)
			{
				fl.disp_MessageFailed(driver, "", "", email +"Not updated in Support company list", "", "Y");
			}
			
		}
		driver.get(Comapany_BaseURL_Digi);
		String url= driver.getCurrentUrl();
		if(!url.contains("/Account/Login"))
		{
			String userName=fl.getTextXPATH(driver, UserName_PVW_Xpath, "", "", "Get text of UserName", "", "");
			fl.disp_Message(driver, "", "User name is:", "", userName, "");
			comp_Info.add(userName);
			fun_cas.CompanyLogout(driver);
		}
		return comp_Info;*/
	}
	public void AfterSupportedCompanyApprovedBy_CompanyWhoCreatedPetitionValidatingInNWebsite(WebDriver driver, String beforSuportOrgNo, String email, String comments) throws IOException, InterruptedException
	{
		
		List<String> comp_Info=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String Comapany_BaseURL_Digi=Environment("Comapany_BaseURL_Digi");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName2_PVW_Xpath=Environment("suppOrgName2_PVW_Xpath");
		String companyName_SCV_Xpath=Environment("companyName_SCV_Xpath");
		String UserName_PVW_Xpath=Environment("UserName_PVW_Xpath");
		String companyEmail_PVW_Xpath=Environment("companyEmail_PVW_Xpath");
		String supportTheCause_Xpath=Environment("supportTheCause_Xpath");
		String username_Company_Xpath=Environment("username_Company_Xpath");
		String password_Company_Xpath=Environment("password_Company_Xpath");
		String login_Company_Xpath=Environment("login_Company_Xpath");
		String cancel_Company_Xpath=Environment("cancel_Company_Xpath");
		String comments_Xpath=Environment("comments_Xpath");
		String douwanttoDonate_Xpath=Environment("douwanttoDonate_Xpath");
		String submit_Company_Cause_Xpath=Environment("submit_Company_Cause_Xpath");
		String cancel_CompanySupportCause_Xpath=Environment("cancel_CompanySupportCause_Xpath");
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		String afterSuportOrgNo=fl.getTextXPATH(driver, suppOrgNo_PVW_Xpath, "", "", "after support total supported org", "", "");
		
		fl.disp_Message(driver, "", "", "before Support:"+beforSuportOrgNo, "after Support:"+afterSuportOrgNo, "");
		
		int companies=fun_cas.stringToIntegerconvert(afterSuportOrgNo);
		System.out.println("Total Supported Org are:"+companies);
		if(companies>=1)
		{
			int count =0;
			for(int i=1;i<=companies;i++)
			{
				System.out.println("iteration:"+i);
				driver.navigate().refresh();
				
				String fundUna_att_1=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
				if(fundUna_att.contains("display: block;"))
					fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
				
				//fl.ClickByXpath(driver, suppOrgName1_PVW_Xpath, "", "Click on Support Organizations List", "", "", "");
				fl.JS_Element_Find(driver, suppOrgName1_PVW_Xpath+"["+i+"]");
				fl.ClickByXpath(driver, suppOrgName1_PVW_Xpath+"["+i+"]", "", "", "click on company:"+i, "", "");
				Thread.sleep(3000);
				
				String comp_email=fl.getTextXPATH(driver, companyEmail_PVW_Xpath, "", "", "Get email Id", "", "");
				if(comp_email.equals(email))
				{
					count++;
					String companyName=fl.getTextXPATH(driver, companyName_SCV_Xpath, "", "", "Get Text of Company to validate on CompanySide SupportOrganizations List", "", "");
					fl.disp_Message(driver, "", "Company Name Text:", "", companyName, "");
					comp_Info.add(companyName);
					fl.disp_Message(driver, "", "Comments Are:", "", comments, "");
					comp_Info.add(comments);
					fl.disp_Message(driver, "", "", email, comp_email, "Y");
					break;
				}
				else
				{
					System.out.println("email not matches driver navigate to back");
					Thread.sleep(3000);
					System.out.println(driver);
					driver.navigate().back();
				}
				
				
				
			}
			if(count==0)
			{
				fl.disp_MessageFailed(driver, "", "", email +"Not updated in Support company list", "", "Y");
			}
			
		}
		driver.get(Comapany_BaseURL_Digi);
		String url= driver.getCurrentUrl();
		if(!url.contains("/Account/Login"))
		{
			String userName=fl.getTextXPATH(driver, UserName_PVW_Xpath, "", "", "Get text of UserName", "", "");
			fl.disp_Message(driver, "", "User name is:", "", userName, "");
			comp_Info.add(userName);
			fun_cas.CompanyLogout(driver);
		}
		
	}
	public String supportCauseNewUserAfterRegister(WebDriver driver, String status,String email, String password, String comments) throws IOException, InterruptedException
	{
		
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String Comapany_BaseURL_Digi=Environment("Comapany_BaseURL_Digi");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName2_PVW_Xpath=Environment("suppOrgName2_PVW_Xpath");
		String companyName_SCV_Xpath=Environment("companyName_SCV_Xpath");
		String UserName_PVW_Xpath=Environment("UserName_PVW_Xpath");
		String companyEmail_PVW_Xpath=Environment("companyEmail_PVW_Xpath");
		String supportTheCause_Xpath=Environment("supportTheCause_Xpath");
		String username_Company_Xpath=Environment("username_Company_Xpath");
		String password_Company_Xpath=Environment("password_Company_Xpath");
		String login_Company_Xpath=Environment("login_Company_Xpath");
		String cancel_Company_Xpath=Environment("cancel_Company_Xpath");
		String comments_Xpath=Environment("comments_Xpath");
		String douwanttoDonate_Xpath=Environment("douwanttoDonate_Xpath");
		String submit_Company_Cause_Xpath=Environment("submit_Company_Cause_Xpath");
		String cancel_CompanySupportCause_Xpath=Environment("cancel_CompanySupportCause_Xpath");
		
		Thread.sleep(10000);
		String beforSuportOrgNo=fl.getTextXPATH(driver, suppOrgNo_PVW_Xpath, "", "", "before support total supported org", "", "");
		System.out.println("before support org total org are:"+beforSuportOrgNo);
		
		if(status.equals(""))
			fl.ClickByXpath(driver, supportTheCause_Xpath, "", "", "Click on Support Cause", "", "");
		fl.entervalueByXpath(driver, username_Company_Xpath, email, "", "", "Enter User name", "", "");
		fl.entervalueByXpath(driver, password_Company_Xpath, password, "", "", "Enter Password", "", "");
		fl.ClickByXpath(driver, login_Company_Xpath, "", "", "Click on Login Button", "", "");
		fl.entervalueByXpath(driver, comments_Xpath, comments, "", "", "", "", "");
		fl.ClickByXpath(driver, submit_Company_Cause_Xpath, "", "", "Click on Submit", "", "");
		//Thread.sleep(10000);
		return beforSuportOrgNo;
	}
	
	public void approveSupportCauseRegisterCompany(WebDriver driver, String email, String password, String petition,
			String accept, String reject, String rejectComments, String orgName) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String suportOrgTab_SCV_Xpath=Environment("suportOrgTab_SCV_Xpath");
		String totCompSuport_SCV_Xpath=Environment("totCompSuport_SCV_Xpath");
		String orgName_SCV_Xpath=Environment("orgName_SCV_Xpath");
		String supDate_SCV_Xpath=Environment("supDate_SCV_Xpath");
		String funds_SCV_Xpath=Environment("funds_SCV_Xpath");
		String fundedDate_SCV_Xpath=Environment("fundedDate_SCV_Xpath");
		String status_SCV_Xpath=Environment("status_SCV_Xpath");
		String comments_SCV_Xpath=Environment("comments_SCV_Xpath");
		String morOption_SCV_Xpath=Environment("morOption_SCV_Xpath");
		String approve_SCV_Xpath=Environment("approve_SCV_Xpath");
		String confirmApprove_SCV_Xpath=Environment("confirmApprove_SCV_Xpath");
		String approveDate_SCV_Xpath=Environment("approveDate_SCV_Xpath");
		String reject_SCV_Xpath=Environment("reject_SCV_Xpath");
		String rejctComnts_SCV_Xpath=Environment("rejctComnts_SCV_Xpath");
		String confirmReject_SCV_Xpath=Environment("confirmReject_SCV_Xpath");
		
		fun_cas.companyLogin(driver, email, password);
		
		fun_cas.viewPetition(driver, petition);
		fl.ClickByXpath(driver, suportOrgTab_SCV_Xpath, "", "Click on Support Organizations", "", "", "");
		
		Thread.sleep(3000);
		int supportedOrg=fun_cas.listSize(driver, totCompSuport_SCV_Xpath);
		if(supportedOrg>=1)
		{
			for(int i=1;i<=supportedOrg;i++)
			{
				String org_name=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+orgName_SCV_Xpath, "", "", "Get text of company name", "", "");
				if(org_name.equals(orgName))
				{
					/*String supDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+supDate_SCV_Xpath, "", "", "Get Suported Date", "", "");
					fl.disp_Message(driver, "", "", "", supDate, "");
					String funds=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+funds_SCV_Xpath, "", "", "Get Funds", "", "");
					fl.disp_Message(driver, "", "", "", funds, "");
					String fundDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+fundedDate_SCV_Xpath, "", "", "Get Funded Date", "", "");
					fl.disp_Message(driver, "", "", "", fundDate, "");
					String Status=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "");
					fl.disp_Message(driver, "", "", "", Status, "");
					String comnts=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+comments_SCV_Xpath, "", "", "Get Comments", "", "");
					
					if(comments.contains(comnts))
						fl.disp_Message(driver, "", "", "Comments By Company:"+comments, comnts, "Y");*/
					String Status=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "");
					fl.disp_Message(driver, "", "", "", Status, "");
					if(Status.contains("Pending"))
					{
						if(accept!="")
						{
							if(supportedOrg>1)
								fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
							else
							{
								if(supportedOrg==1)
								{
									fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "");
									//fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
								}
							}
							Thread.sleep(3000);
							fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approve_SCV_Xpath, "", "", "Click On Approve", "", "Y");
							fl.ClickByXpath(driver, confirmApprove_SCV_Xpath, "", "", "Click on Confirm Approve", "", "Y");
							driver.navigate().refresh();
							
							Thread.sleep(5000);
							fl.ClickByXpath(driver, suportOrgTab_SCV_Xpath, "", "", "Click on Support Organizations", "", "");
							String activ=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "Y");
							if(activ.contains("Active"))
								fl.disp_Message(driver, "", "", "Company Approved & Status is Active", activ, "");
							String aprOrRjctDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approveDate_SCV_Xpath, "", "", "Get Approved Date", "", "");
							fl.disp_Message(driver, "", "", "", aprOrRjctDate, "");
						}
						else
						{
							if(reject!="")
							{
								if(supportedOrg>1)
									fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
								else
								{
									if(supportedOrg==1)
									{
										fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "");
										//fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
									}
								}
								fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+reject_SCV_Xpath, "", "", "Click On Reject", "", "Y");
								fl.entervalueByXpath(driver, rejctComnts_SCV_Xpath, rejectComments, "", "", "Enter Reject Comments", "", "Y");
								fl.ClickByXpath(driver, confirmReject_SCV_Xpath, "", "", "Click on Confirm Reject", "", "Y");
								String activ=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "Y");
								if(activ.contains("Rejected"))
									fl.disp_Message(driver, "", "", "Company Rejected & Status is Rejected", activ, "Y");
								String aprOrRjctDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approveDate_SCV_Xpath, "", "", "Get Rejected Date", "", "Y");
								fl.disp_Message(driver, "", "", "", aprOrRjctDate, "");
							}
						}
					}
				}
			}
		}
		fun_cas.CompanyLogout(driver);
	}
	public List<String> supportCauseNewUserAfterRegisterValidation(WebDriver driver, String email, String password, String comments, String beforSuportOrgNo) throws IOException, InterruptedException
	{
		List<String> comp_Info=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName1A_PVW_Xpath=Environment("suppOrgName1A_PVW_Xpath");
		String suppOrgName2A_PVW_Xpath=Environment("suppOrgName2A_PVW_Xpath");
		String companyEmail_PVW_Xpath=Environment("companyEmail_PVW_Xpath");
		String companyName_SCV_Xpath=Environment("companyName_SCV_Xpath");
		String Comapany_BaseURL_Digi=Environment("Comapany_BaseURL_Digi");
		String UserName_PVW_Xpath=Environment("UserName_PVW_Xpath");
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String companyProfile_Xpath=Environment("companyProfile_Xpath");
		String legalName_CPValid_Xpath=Environment("legalName_CPValid_Xpath");
		String companyProfile5_Xpath=Environment("companyProfile5_Xpath");
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		String afterSuportOrgNo=fl.getTextXPATH(driver, suppOrgNo_PVW_Xpath, "", "", "after support total supported org", "", "");
		
		fl.disp_Message(driver, "", "", "before Support:"+beforSuportOrgNo, "after Support:"+afterSuportOrgNo, "");
		
		int companies=fun_cas.stringToIntegerconvert(afterSuportOrgNo);
		System.out.println("Total Supported Org are:"+companies);
		if(companies>=1)
		{
			int count =0;
			for(int i=1;i<=companies;i++)
			{
				System.out.println("iteration:"+i);
				//driver.navigate().refresh();
				
				//Thread.sleep(10000);
				if(i!=1)
				{
					String fundUna_att_1=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
					if(fundUna_att.contains("display: block;"))
						fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
				}
				
				int comp_list=fun_cas.listSize(driver, suppOrgName1A_PVW_Xpath);
				//int comp2_list=fun_cas.listSize(driver, suppOrgName2A_PVW_Xpath);
				if(comp_list!=0)
				{
					fl.JS_Element_Find(driver, suppOrgName1A_PVW_Xpath+"["+i+"]");
					fl.ClickByXpath(driver, suppOrgName1A_PVW_Xpath+"["+i+"]", "", "", "click on company:"+i, "", "");
					Thread.sleep(3000);
				}
				else
				{
					int comp2_list=fun_cas.listSize(driver, suppOrgName2A_PVW_Xpath);
					if(comp2_list!=0)
					{
						fl.JS_Element_Find(driver, suppOrgName2A_PVW_Xpath+"["+i+"]");
						fl.ClickByXpath(driver, suppOrgName2A_PVW_Xpath+"["+i+"]", "", "", "click on company:"+i, "", "");
						Thread.sleep(3000);
					}
				}
				
				String comp_email=fl.getTextXPATH(driver, companyEmail_PVW_Xpath, "", "", "Get email Id", "", "");
				if(comp_email.equals(email))
				{
					count++;
					String companyName=fl.getTextXPATH(driver, companyName_SCV_Xpath, "", "", "Get Text of Company to validate on CompanySide SupportOrganizations List", "", "");
					fl.disp_Message(driver, "", "Company Name Text:", "", companyName, "");
					comp_Info.add(companyName);
					fl.disp_Message(driver, "", "Comments Are:", "", comments, "");
					comp_Info.add(comments);
					fl.disp_Message(driver, "", "", email, comp_email, "Y");
					break;
				}
				else
				{
					System.out.println("email not matches driver navigate to back");
					Thread.sleep(3000);
					System.out.println(driver);
					driver.navigate().back();
				}
				
				
				
			}
			if(count==0)
			{
				fl.disp_MessageFailed(driver, "", "", email +"Not updated in Support company list", "", "Y");
			}
			
		}
		driver.get(Comapany_BaseURL_Digi);
		String url= driver.getCurrentUrl();
		if(!url.contains("/Account/Login"))
		{
			String Parent=driver.getWindowHandle();
			Set<String> set = new HashSet<String>(driver.getWindowHandles());
			for(String tab : set)
			{
				System.out.println("window :"+tab);
			}
			set.remove(Parent);

			driver.switchTo().window(set.iterator().next());
			driver.get("localhost:4034/Account/Login");
			
			fl.ClickByXpath(driver, logout_img_Xpath, "", "Click on Logout Image", "", "", "");
			int logoutOptions=fun_cas.listSize(driver, logoutOptions_Xpath);
			if(logoutOptions==6)
			{
				fl.ClickByXpath(driver, companyProfile_Xpath, "", "Click on Company Profile", "", "", "");
				String legalName=fl.getTextXPATH(driver, legalName_CPValid_Xpath, "", "Get Company Legal Name", "", "", "");
				comp_Info.add(legalName);
			}
			else
			{
				if(logoutOptions==5)
				{
					fl.ClickByXpath(driver, companyProfile5_Xpath, "", "Click on Company Profile", "", "", "");
					String legalName=fl.getTextXPATH(driver, legalName_CPValid_Xpath, "", "Get Company Legal Name", "", "", "");
					comp_Info.add(legalName);
				}
			}
			
			driver.close();
			driver.switchTo().window(Parent);
			
			/*String userName=fl.getTextXPATH(driver, UserName_PVW_Xpath, "", "", "Get text of UserName", "", "");
			fl.disp_Message(driver, "", "User name is:", "", userName, "");
			comp_Info.add(userName);*/
			fun_cas.CompanyLogout(driver);
		}
		else
		{
			if(url.contains("/Account/Login"))
			{
				fun_cas.companyLogin(driver, email, password);
				String userName=fl.getTextXPATH(driver, UserName_PVW_Xpath, "", "", "Get text of UserName", "", "");
				fl.disp_Message(driver, "", "User name is:", "", userName, "");
				comp_Info.add(userName);
				fun_cas.CompanyLogout(driver);
			}
		}
		return comp_Info;
	}
	public void supportCauseNewCompany(WebDriver driver, int first, String yourname, String orgname, String orgemailid, String contno, String fein, String website,
			String password, String confirmpassword, String captcha) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String supportTheCause_Xpath=Environment("supportTheCause_Xpath");
		String signUp_Company_Xpath=Environment("signUp_Company_Xpath");
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		fl.ClickByXpath(driver, supportTheCause_Xpath, "", "", "Click on Support Cause", "", "");
		fl.ClickByXpath(driver, signUp_Company_Xpath, "", "Click on SignUp Button To Register a New Company", "", "", "");
		
		String Parent=driver.getWindowHandle();
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		fun_cas.companyRegistration(driver, first, yourname, orgname, orgemailid, contno, fein, website, password, confirmpassword, captcha);
		
//confirm whether logout required or not
		
		driver.close();
		
		driver.switchTo().window(Parent);
	}
	public void validatingSuportedOrgOnCompSide(WebDriver driver, String accept, String reject, String rejectComments, String orgName,
			String comments, String username) throws IOException, InterruptedException
	{
		
		List<String> comp_Info=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String suportOrgTab_SCV_Xpath=Environment("suportOrgTab_SCV_Xpath");
		String SearchClick_SCV_Xpath=Environment("SearchClick_SCV_Xpath");
		String orgNameField_SCV_Xpath=Environment("orgNameField_SCV_Xpath");
		String totCompSuport_SCV_Xpath=Environment("totCompSuport_SCV_Xpath");
		String morOption_SCV_Xpath=Environment("morOption_SCV_Xpath");
		String approve_SCV_Xpath=Environment("approve_SCV_Xpath");
		String confirmApprove_SCV_Xpath=Environment("confirmApprove_SCV_Xpath");
		String reject_SCV_Xpath=Environment("reject_SCV_Xpath");
		String rejctComnts_SCV_Xpath=Environment("rejctComnts_SCV_Xpath");
		String confirmReject_SCV_Xpath=Environment("confirmReject_SCV_Xpath");
		String cancelReject_SCV_Xpath=Environment("cancelReject_SCV_Xpath");
		String orgName_SCV_Xpath=Environment("orgName_SCV_Xpath");
		String supDate_SCV_Xpath=Environment("supDate_SCV_Xpath");
		String funds_SCV_Xpath=Environment("funds_SCV_Xpath");
		String fundedDate_SCV_Xpath=Environment("fundedDate_SCV_Xpath");
		String status_SCV_Xpath=Environment("status_SCV_Xpath");
		String comments_SCV_Xpath=Environment("comments_SCV_Xpath");
		String approveDate_SCV_Xpath=Environment("approveDate_SCV_Xpath");
		
		String updatesTab_SCV_Xpath=Environment("updatesTab_SCV_Xpath");
		String totUpdate_SCV_Xpath=Environment("totUpdate_SCV_Xpath");
		String username_SCV_Xpath=Environment("username_SCV_Xpath");
		String date_SCV_Xpath=Environment("date_SCV_Xpath");
		String action_SCV_Xpath=Environment("action_SCV_Xpath");
		
		//GO TO SUPPORT ORGANIZATIONS TAB
		fl.ClickByXpath(driver, suportOrgTab_SCV_Xpath, "", "", "Click on Support Organizations", "", "");
		/*fl.ClickByXpath(driver, SearchClick_SCV_Xpath, "", "", "Click on advancedSearch", "", "");
		fl.entervalueByXpath(driver, orgNameField_SCV_Xpath, orgName, "", "", "Enter Org Name Search Field", "", "");*/
		Thread.sleep(3000);
		int supportedOrg=fun_cas.listSize(driver, totCompSuport_SCV_Xpath);
		if(supportedOrg>=1)
		{
			for(int i=1;i<=supportedOrg;i++)
			{
				String org_name=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+orgName_SCV_Xpath, "", "", "Get text of company name", "", "");
				if(org_name.equals(orgName))
				{
					String supDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+supDate_SCV_Xpath, "", "", "Get Suported Date", "", "");
					fl.disp_Message(driver, "", "", "", supDate, "");
					String funds=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+funds_SCV_Xpath, "", "", "Get Funds", "", "");
					fl.disp_Message(driver, "", "", "", funds, "");
					String fundDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+fundedDate_SCV_Xpath, "", "", "Get Funded Date", "", "");
					fl.disp_Message(driver, "", "", "", fundDate, "");
					String Status=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "");
					fl.disp_Message(driver, "", "", "", Status, "");
					String comnts=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+comments_SCV_Xpath, "", "", "Get Comments", "", "");
					
					if(comments.contains(comnts))
						fl.disp_Message(driver, "", "", "Comments By Company:"+comments, comnts, "Y");
						
						comp_Info.add(comnts);
					if(Status.contains("Pending"))
					{
						if(accept!="")
						{
							if(supportedOrg>1)
								fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
							else
							{
								if(supportedOrg==1)
								{
									fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "");
									//fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
								}
							}
							Thread.sleep(3000);
							fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approve_SCV_Xpath, "", "", "Click On Approve", "", "Y");
							fl.ClickByXpath(driver, confirmApprove_SCV_Xpath, "", "", "Click on Confirm Approve", "", "Y");
							driver.navigate().refresh();
							
							Thread.sleep(5000);
							fl.ClickByXpath(driver, suportOrgTab_SCV_Xpath, "", "", "Click on Support Organizations", "", "");
							String activ=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "Y");
							if(activ.contains("Active"))
								fl.disp_Message(driver, "", "", "Company Approved & Status is Active", activ, "");
							String aprOrRjctDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approveDate_SCV_Xpath, "", "", "Get Approved Date", "", "");
							fl.disp_Message(driver, "", "", "", aprOrRjctDate, "");
						}
						else
						{
							if(reject!="")
							{
								if(supportedOrg>1)
									fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
								else
								{
									if(supportedOrg==1)
									{
										fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "");
										//fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+morOption_SCV_Xpath, "", "", "Click On More Options", "", "Y");
									}
								}
								fl.ClickByXpath(driver, totCompSuport_SCV_Xpath+"["+i+"]"+reject_SCV_Xpath, "", "", "Click On Reject", "", "Y");
								fl.entervalueByXpath(driver, rejctComnts_SCV_Xpath, rejectComments, "", "", "Enter Reject Comments", "", "Y");
								fl.ClickByXpath(driver, confirmReject_SCV_Xpath, "", "", "Click on Confirm Reject", "", "Y");
								String activ=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "Y");
								if(activ.contains("Rejected"))
									fl.disp_Message(driver, "", "", "Company Rejected & Status is Rejected", activ, "Y");
								String aprOrRjctDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+approveDate_SCV_Xpath, "", "", "Get Rejected Date", "", "Y");
								fl.disp_Message(driver, "", "", "", aprOrRjctDate, "");
							}
						}
					}
					break;
				}
			}
		}
		
		//GO TO UPDATES TAB
		fl.ClickByXpath(driver, updatesTab_SCV_Xpath, "", "", "Click on Updates Tab", "", "");
		int updateList=fun_cas.listSize(driver, totUpdate_SCV_Xpath);
		if(updateList>=1)
		{
			int iterat=0;
			for(int i=1;i<=updateList;i++)
			{
				fl.JS_Element_Find(driver, totUpdate_SCV_Xpath+"["+i+"]");
				String user=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+username_SCV_Xpath, "", "", "Get username", "", "");
				fl.disp_Message(driver, "", "Username Comparing:", username, user, "Y");
				if(user.contains(username))
				{
					iterat++;
					String actDate=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+date_SCV_Xpath, "", "", "Get Action Date", "", "");
					fl.disp_Message(driver, "", "Action Date :", "", actDate, "");
					String action=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+action_SCV_Xpath, "", "", "Get Action", "", "");
					fl.disp_Message(driver, "", "Action is:", "", action, "");
					if(action.contains("Supported to petition"))
					{
						fl.disp_Message(driver, "", "", "Supported to petition", action, "");
						break;
					}
					else
					{
						fl.disp_Message(driver, "", "", "Error Occured", action, "");
						//break;
					}
				}
				else
				{
					if(i==updateList&&iterat==0)
					fl.disp_Message(driver, "", "", "Suported Company Name Not Available in Supported Organizations Tab on Company Side", "", "");
					//break;
				}
			}
		}
		
	}
	public void validatingNewSuportedOrgOnCompSide(WebDriver driver, String orgName,
			String comments, String username) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String suportOrgTab_SCV_Xpath=Environment("suportOrgTab_SCV_Xpath");
		String SearchClick_SCV_Xpath=Environment("SearchClick_SCV_Xpath");
		String orgNameField_SCV_Xpath=Environment("orgNameField_SCV_Xpath");
		String totCompSuport_SCV_Xpath=Environment("totCompSuport_SCV_Xpath");
		String morOption_SCV_Xpath=Environment("morOption_SCV_Xpath");
		String approve_SCV_Xpath=Environment("approve_SCV_Xpath");
		String confirmApprove_SCV_Xpath=Environment("confirmApprove_SCV_Xpath");
		String reject_SCV_Xpath=Environment("reject_SCV_Xpath");
		String rejctComnts_SCV_Xpath=Environment("rejctComnts_SCV_Xpath");
		String confirmReject_SCV_Xpath=Environment("confirmReject_SCV_Xpath");
		String cancelReject_SCV_Xpath=Environment("cancelReject_SCV_Xpath");
		String orgName_SCV_Xpath=Environment("orgName_SCV_Xpath");
		String supDate_SCV_Xpath=Environment("supDate_SCV_Xpath");
		String funds_SCV_Xpath=Environment("funds_SCV_Xpath");
		String fundedDate_SCV_Xpath=Environment("fundedDate_SCV_Xpath");
		String status_SCV_Xpath=Environment("status_SCV_Xpath");
		String comments_SCV_Xpath=Environment("comments_SCV_Xpath");
		String approveDate_SCV_Xpath=Environment("approveDate_SCV_Xpath");
		
		String updatesTab_SCV_Xpath=Environment("updatesTab_SCV_Xpath");
		String totUpdate_SCV_Xpath=Environment("totUpdate_SCV_Xpath");
		String username_SCV_Xpath=Environment("username_SCV_Xpath");
		String date_SCV_Xpath=Environment("date_SCV_Xpath");
		String action_SCV_Xpath=Environment("action_SCV_Xpath");
		
		//GO TO SUPPORT ORGANIZATIONS TAB
		fl.ClickByXpath(driver, suportOrgTab_SCV_Xpath, "", "", "Click on Support Organizations", "", "");
		/*fl.ClickByXpath(driver, SearchClick_SCV_Xpath, "", "", "Click on advancedSearch", "", "");
		fl.entervalueByXpath(driver, orgNameField_SCV_Xpath, orgName, "", "", "Enter Org Name Search Field", "", "");*/
		Thread.sleep(3000);
		int supportedOrg=fun_cas.listSize(driver, totCompSuport_SCV_Xpath);
		if(supportedOrg>=1)
		{
			for(int i=1;i<=supportedOrg;i++)
			{
				String org_name=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+orgName_SCV_Xpath, "", "", "Get text of company name", "", "");
				if(org_name.equals(orgName))
				{
					String supDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+supDate_SCV_Xpath, "", "", "Get Suported Date", "", "");
					fl.disp_Message(driver, "", "", "", supDate, "");
					String funds=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+funds_SCV_Xpath, "", "", "Get Funds", "", "");
					fl.disp_Message(driver, "", "", "", funds, "");
					String fundDate=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+fundedDate_SCV_Xpath, "", "", "Get Funded Date", "", "");
					fl.disp_Message(driver, "", "", "", fundDate, "");
					String Status=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+status_SCV_Xpath, "", "", "Get Status Of Company", "", "");
					fl.disp_Message(driver, "", "", "", Status, "");
					String comnts=fl.getTextXPATH(driver, totCompSuport_SCV_Xpath+"["+i+"]"+comments_SCV_Xpath, "", "", "Get Comments", "", "");
					
					if(comments.contains(comnts))
					{
						fl.disp_Message(driver, "", "", "Comments By Company:"+comments, comnts, "Y");
						break;
					}
					
				}
			}
		}
		
		//GO TO UPDATES TAB
		fl.ClickByXpath(driver, updatesTab_SCV_Xpath, "", "", "Click on Updates Tab", "", "");
		int updateList=fun_cas.listSize(driver, totUpdate_SCV_Xpath);
		if(updateList>=1)
		{
			int iterat=0;
			for(int i=1;i<=updateList;i++)
			{
				fl.JS_Element_Find(driver, totUpdate_SCV_Xpath+"["+i+"]");
				String user=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+username_SCV_Xpath, "", "", "Get username", "", "");
				fl.disp_Message(driver, "", "", orgName, user, "Y");
				if(user.contains(orgName))
				{
					iterat++;
					String actDate=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+date_SCV_Xpath, "", "", "Get Action Date", "", "");
					fl.disp_Message(driver, "", "Action Date :", "", actDate, "");
					String action=fl.getTextXPATH(driver, totUpdate_SCV_Xpath+"["+i+"]"+action_SCV_Xpath, "", "", "Get Action", "", "");
					fl.disp_Message(driver, "", "Action is:", "", action, "");
					if(action.contains("Supported to petition"))
					{
						fl.disp_Message(driver, "", "", "Supported to petition", action, "");
						break;
					}
					else
						fl.disp_Message(driver, "", "", "Error Occured", action, "");
				}
				else
				{
					if(i==updateList&&iterat==0)
					fl.disp_MessageFailed(driver, "", "", "Suported Company Name Not Available in Supported Organizations Tab on Company Side", "", "");
				}
			}
		}
	}
	public void saveCreatedCrowdFund(WebDriver driver, String beneficiary, String beneficiaryName, String relationType, String beneficiaryImage, String campaignTitle, String campaignType, String category, String indef, String endDate,
			String videoLink, String tags, String fundGoal, String fund, String socialImage, String image1, String image2, String image3, String image4,
			String supImag1, String supImag2, String supImag3, String supImag4, String supImag5, String faq, String message, String linkText,
			String link, String newWindo, String image, String imageURL, String save, String submit) throws IOException, InterruptedException
	{
		String alert_Xpath=Environment("alert_Xpath");
		UploadFile_Robot upload=new UploadFile_Robot();
		String CrowdFunding_Xpath=Environment("CrowdFunding_Xpath");
		String createCrowd_CCF_Xpath=Environment("createCrowd_CCF_Xpath");
		String myself_CCF_Xpath=Environment("myself_CCF_Xpath");
		String somebody_CCF_Xpath=Environment("somebody_CCF_Xpath");
		String beneficiaryName_CCF_Xpath=Environment("beneficiaryName_CCF_Xpath");
		String relationType_CCF_Xpath=Environment("relationType_CCF_Xpath");
		String beneficiaryImage_CCF_Xpath=Environment("beneficiaryImage_CCF_Xpath");
		String campaignTtile_CCF_Xpath=Environment("campaignTtile_CCF_Xpath");
		String campaignType_CCF_Xpath=Environment("campaignType_CCF_Xpath");
		String campaignTypeOptions_CCF_Xpath=Environment("campaignTypeOptions_CCF_Xpath");
		String category_CCF_Xpath=Environment("category_CCF_Xpath");
		String categoryOptions_CCF_Xpath=Environment("categoryOptions_CCF_Xpath");
		String indefinite_CCF_Xpath=Environment("indefinite_CCF_Xpath");
		String endDate_CCF_Xpath=Environment("endDate_CCF_Xpath");
		String videoLink_CCF_Xpath=Environment("videoLink_CCF_Xpath");
		String tags_CCF_Xpath=Environment("tags_CCF_Xpath");
		String fundingGoal_CCF_Xpath=Environment("fundingGoal_CCF_Xpath");
		String fundingGoalOptions_CCF_Xpath=Environment("fundingGoalOptions_CCF_Xpath");
		String funding_CCF_Xpath=Environment("funding_CCF_Xpath");
		String crop_Xpath=Environment("crop_Xpath");
		String socialImg_CCF_Xpath=Environment("socialImg_CCF_Xpath");
		String image1_CCF_Xpath=Environment("image1_CCF_Xpath");
		String image2_CCF_Xpath=Environment("image2_CCF_Xpath");
		String image3_CCF_Xpath=Environment("image3_CCF_Xpath");
		String image4_CCF_Xpath=Environment("image4_CCF_Xpath");
		String suportingDocPlus_CCF_Xpath=Environment("suportingDocPlus_CCF_Xpath");
		String sup1_CCF_Xpath=Environment("sup1_CCF_Xpath");
		String sup2_CCF_Xpath=Environment("sup2_CCF_Xpath");
		String sup3_CCF_Xpath=Environment("sup3_CCF_Xpath");
		String sup4_CCF_Xpath=Environment("sup4_CCF_Xpath");
		String sup5_CCF_Xpath=Environment("sup5_CCF_Xpath");
		String faqPlus_CCF_Xpath=Environment("faqPlus_CCF_Xpath");
		String question_CCF_Xpath=Environment("question_CCF_Xpath");
		String answer_CCF_Xpath=Environment("answer_CCF_Xpath");
		String addFaq_CCF_Xpath=Environment("addFaq_CCF_Xpath");
		String description_CCF_Xpath=Environment("description_CCF_Xpath");
		String link_CCF_Xpath=Environment("link_CCF_Xpath");
		String textToDisplay_CCF_Xpath=Environment("textToDisplay_CCF_Xpath");
		String linkURL_CCF_Xpath=Environment("linkURL_CCF_Xpath");
		String openInNewWindow_CCF_Xpath=Environment("openInNewWindow_CCF_Xpath");
		String insertLink_CCF_Xpath=Environment("insertLink_CCF_Xpath");
		String picture_CCF_Xpath=Environment("picture_CCF_Xpath");
		String browse_CCF_Xpath=Environment("browse_CCF_Xpath");
		String imageURL_CCF_Xpath=Environment("imageURL_CCF_Xpath");
		String insertImage_CCF_Xpath=Environment("insertImage_CCF_Xpath");
		String saveAsDraft_CCF_Xpath=Environment("saveAsDraft_CCF_Xpath");
		String submitForApproval_CCF_Xpath=Environment("submitForApproval_CCF_Xpath");
		
		fl.ClickByXpath(driver, CrowdFunding_Xpath, "", "click on crowd Funding Menu", "", "", "");
		fl.ClickByXpath(driver, createCrowd_CCF_Xpath, "", "Click on create Crowd Funding", "", "", "");
		
		if(beneficiary!="")
		{
			if(beneficiary.equals("Myself"));
				//fl.ClickByXpath(driver, myself_CCF_Xpath, "", "Check Myself Option", "", "", "");
			else
			{
				fl.ClickByXpath(driver, somebody_CCF_Xpath, "", "Check Somebody Else ", "", "", "");
				if(beneficiaryName!="")
				{
					fl.entervalueByXpath(driver, beneficiaryName_CCF_Xpath, beneficiaryName, "", "enter beneficiary name", "", "", "");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "Mandidatory to fill beneficiary name", "", "", "Y");
				}
				if(relationType!="")
				{
					fl.selectDropdownByxpath(driver, relationType_CCF_Xpath, relationType, "", "Select relation type option from drop list", "", "", "Y");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "Mandidatory to Select Relation Type", "", "", "Y");
				}
				if(beneficiaryImage!="")
				{
					fl.ClickByXpath(driver, beneficiaryImage_CCF_Xpath, "", "Click ChooseFile Option To upload Beneficiary Image", "", "", "");
					upload.uploadFile(beneficiaryImage);
				}
			}
		}
		if(campaignTitle!="")
		{
			fl.entervalueByXpath(driver, campaignTtile_CCF_Xpath, campaignTitle, "", "Enter Campaign Title: "+campaignTitle, "", "", "");
		}
		if(campaignType!="")
		{
			String status=fl.checkOptionValueInSelect(driver, campaignType_CCF_Xpath, campaignTypeOptions_CCF_Xpath, campaignType);
			if(status.equals("true"))
				fl.selectDropdownByxpath(driver, campaignType_CCF_Xpath, campaignType, "", "Selecting CampaignType Option:"+campaignType, "", "", "");
			else
				fl.disp_Message(driver, "", "Given Campaign Type Not Existed in Dropdown List", "", "", "");
		}
		if(category!="")
		{
			String status=fl.checkOptionValueInSelect(driver, category_CCF_Xpath, categoryOptions_CCF_Xpath, category);
			if(status.equals("true"))
				fl.selectDropdownByxpath(driver, category_CCF_Xpath, category, "", "Selecting category Type:"+category, "", "", "");
			else
				fl.disp_Message(driver, "", "Given Category Item Not Existed in Dropdown", "", "", "");
		}
		if(indef!="")
		{
			if(indef.equals("T"))
				fl.ClickByXpath(driver, indefinite_CCF_Xpath, "", "Check Indefinite Option", "", "", "");
			else
				fl.disp_Message(driver, "", "Not Checking Indefinite Option", "", "", "");
		}
		if(endDate!="")
		{
			fl.ClickByXpath(driver, endDate_CCF_Xpath, "", "Selecting end Date Field", "", "", "");
			fl.entervalueByXpath(driver, endDate_CCF_Xpath, endDate, "", "Enter EndDate Value:"+endDate, "", "", "");
		}
		if(videoLink!="")
		{
			fl.entervalueByXpath(driver, videoLink_CCF_Xpath, videoLink, "", "Enter Video Link : "+videoLink, "", "", "");
		}
		if(tags!="")
		{
			fl.entervalueByXpath(driver, tags_CCF_Xpath, tags, "", "Enter Value In tags: "+tags, "", "", "");
		}
		if(fundGoal!="")
		{
			String status=fl.checkOptionValueInSelect(driver, fundingGoal_CCF_Xpath, fundingGoalOptions_CCF_Xpath, fundGoal);
			if(status.equals("true"))
				fl.selectDropdownByxpath(driver, fundingGoal_CCF_Xpath, fundGoal, "", "Selecting Fund Goal Dropdown :"+fundGoal, "", "", "");
			else
				fl.disp_Message(driver, "", "Given Fund goal Type is not existed in Dropdown", "", "", "");
		}
		if(fund!="")
		{
			fl.entervalueByXpath(driver, funding_CCF_Xpath, fund, "", "Fund Entered :"+fund, "", "", "");
		}
		if(socialImage!="")
		{
			fl.ClickByXpath(driver, socialImg_CCF_Xpath, "", "Click on Social Media Icon To upload Image", "", "", "");
			upload.uploadFile(socialImage);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
		}
		if(image1!="")
		{
			fl.ClickByXpath(driver, image1_CCF_Xpath, "", "Click on Image1 in Slider images to upload Image", "", "", "");
			upload.uploadFile(image1);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
		}
		if(image2!="")
		{
			fl.ClickByXpath(driver, image2_CCF_Xpath, "", "Click on Image2 in Slider images to upload Image", "", "", "");
			upload.uploadFile(image2);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
		}
		if(image3!="")
		{
			fl.ClickByXpath(driver, image3_CCF_Xpath, "", "Click on Image3 in Slider images to upload Image", "", "", "");
			upload.uploadFile(image3);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
		}
		if(image4!="")
		{
			fl.ClickByXpath(driver, image4_CCF_Xpath, "", "Click on Image4 in Slider images to upload Image", "", "", "");
			upload.uploadFile(image4);
			fl.ClickByXpath(driver, crop_Xpath, "", "", "", "", "");
		}
		if(supImag1!=""||supImag2!=""||supImag3!=""||supImag4!=""||supImag5!="")
		{
			fl.JS_Element_Find(driver, suportingDocPlus_CCF_Xpath);
			fl.ClickByXpath(driver, suportingDocPlus_CCF_Xpath, "", "Click on Supporting Documents Button", "", "", "");
			fl.JS_Element_Find(driver, sup1_CCF_Xpath);
			if(supImag1!="")
			{
				fl.ClickByXpath(driver, sup1_CCF_Xpath, "", "Click on Supporting Document 1 to upload image", "", "", "");
				upload.uploadFile(supImag1);
			}
			if(supImag2!="")
			{
				fl.ClickByXpath(driver, sup2_CCF_Xpath, "", "Click on Supporting Document 2 to upload image", "", "", "");
				upload.uploadFile(supImag2);
			}
			if(supImag3!="")
			{
				fl.ClickByXpath(driver, sup3_CCF_Xpath, "", "Click on Supporting Document 3 to upload image", "", "", "");
				upload.uploadFile(supImag3);
			}
			if(supImag4!="")
			{
				fl.ClickByXpath(driver, sup4_CCF_Xpath, "", "Click on Supporting Document 4 to upload image", "", "", "");
				upload.uploadFile(supImag4);
			}
			if(supImag5!="")
			{
				fl.ClickByXpath(driver, sup5_CCF_Xpath, "", "Click on Supporting Document 5 to upload image", "", "", "");
				upload.uploadFile(supImag5);
			}
		}
		if(faq!="")
		{
			fl.JS_Element_Find(driver, faqPlus_CCF_Xpath);
			fl.ClickByXpath(driver, faqPlus_CCF_Xpath, "", "FAQ Clicked", "", "", "");
			
			Excel_Utils RC = new Excel_Utils(Environment("Excel"));
			String Crowd_FAQ=Environment("Sheet_Crowd_FAQ"); 
			  int Crowd_FAQ_row=RC.getLastrowno(Crowd_FAQ); 
			  int Crowd_FAQ_col=RC.getLastcolmno(Crowd_FAQ); 
			  String[] Crowd_FAQ_ele=new String[Crowd_FAQ_col]; 
			  for (int Crowd_FAQ_index = 1; Crowd_FAQ_index < RC.getLastrowno(Crowd_FAQ); Crowd_FAQ_index++) 
			  { 
				  System.out.println("for Loop" );
				  System.out.println(faq);
				  System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"), Crowd_FAQ)); 
				  if (faq.equals(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)))
					  // Adduser contains company email_id at 1st column  for validation
				  { 
					  
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int Crowd_FAQ_ind=0;Crowd_FAQ_ind<Crowd_FAQ_col;Crowd_FAQ_ind++) 
					  {
						  Crowd_FAQ_ele[Crowd_FAQ_ind]=RC.getStringCellData(Crowd_FAQ_index, Crowd_FAQ_ind, Crowd_FAQ);
						  System.out.println(Crowd_FAQ_ele[Crowd_FAQ_ind]); //call login as company method, pass array values
					  }
					  fl.JS_Element_Find(driver, question_CCF_Xpath);
					  fl.entervalueByXpath(driver, question_CCF_Xpath, Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Question")], "",
							  "Enter FAQ Question:"+Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Question")], "", "", "");
					  fl.entervalueByXpath(driver, answer_CCF_Xpath, Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Answer")], "",
							  "Enter FAQ Answer:"+Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Answer")], "", "", "");
					  fl.ClickByXpath(driver, addFaq_CCF_Xpath, "", "Click on Add Question Button ", "", "", "");
				  }
			  }
			  
		}
		if(message!="")
		{
			fl.JS_Element_Find(driver, description_CCF_Xpath);
			fl.ClickByXpath(driver, description_CCF_Xpath, "", "Click on Message Box to clear the sample data", "", "", "");
			select_delete sel_del= new select_delete();
			fl.entervalueByXpath(driver, description_CCF_Xpath, message, "", "Enter Message Description: "+message, "", "", "");
			
		}
		if(linkText!=""||link!="")
		{
			
			fl.ClickByXpath(driver, link_CCF_Xpath, "", "Click on Insert Link Button", "", "", "");
			if(linkText!="")
				fl.entervalueByXpath(driver, textToDisplay_CCF_Xpath, linkText, "", "Enter Link Name:"+linkText, "", "", "");
			if(link!="")
				fl.entervalueByXpath(driver, linkURL_CCF_Xpath, link, "", "Enter Link Text:"+link, "", "", "");
			if(newWindo!="")
				fl.ClickByXpath(driver, openInNewWindow_CCF_Xpath, "", "Check Open In New Window", "", "", "");
			fl.ClickByXpath(driver, insertLink_CCF_Xpath, "", "Click on Insert Link Button", "", "", "");
			fl.ClickByXpath(driver, description_CCF_Xpath, "", "Click on Message Field", "", "", "");
			clickEndRobot endRobo = new clickEndRobot();
		}
		if(image!=""||imageURL!="")
		{
			fl.ClickByXpath(driver, picture_CCF_Xpath, "", "Click on Picture icon in Message Field", "", "", "");
			if(imageURL!="")
				fl.entervalueByXpath(driver, imageURL_CCF_Xpath, imageURL, "", "Enter Image Url:"+imageURL, "", "", "");
			if(image!="")
			{
				fl.ClickByXpath(driver, browse_CCF_Xpath, "", "Click on Browse Button:"+image, "", "", "");
				upload.uploadFile(image);
			}
//TEMPORARY COMMENTING			
			//fl.ClickByXpath(driver, insertImage_CCF_Xpath, "", "Click on Insert Image Button", "", "", "");
		}
		if(save!="")
		{
			fl.ClickByXpath(driver, saveAsDraft_CCF_Xpath, "", "Click On SaveAsDraft", "", "", "");
			Thread.sleep(3000);
			fl.JS_Element_Find(driver, alert_Xpath);
			//String alerMsg=fl.getTextXPATH(driver, alert_Xpath+"[2]", "", "get alert message Text", "", "", "");
			//fl.disp_Message(driver, "", "", "", alerMsg, "");
		}
		else
			if(submit!="")
			{
				fl.ClickByXpath(driver, submitForApproval_CCF_Xpath, "", "Click on Submit For Approval", "", "", "");
				fl.JS_Element_Find(driver, alert_Xpath);
				String alerMsg=fl.getTextXPATH(driver, alert_Xpath, "", "get alert message Text", "", "", "");
				fl.disp_Message(driver, "", "", "", alerMsg, "");
			}
		
	}
	public String viewCrowdFund(WebDriver driver, String CrowdTitle) throws IOException, InterruptedException
	{
		String status = null;
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String CrowdFunding_Xpath=Environment("CrowdFunding_Xpath");
		String crowdFundList_CCF_Xpath=Environment("crowdFundList_CCF_Xpath");
		String advancedSearch_VCF_Xpath=Environment("advancedSearch_VCF_Xpath");
		String serachTitle_VCF_Xpath=Environment("serachTitle_VCF_Xpath");
		String moreButtons_VCF_Xpath=Environment("moreButtons_VCF_Xpath");
		String title_VCF_Xpath=Environment("title_VCF_Xpath");
		String NoRedords_VCF_Xpath=Environment("NoRedords_VCF_Xpath");
		String petiFor_VCF_Xpath=Environment("petiFor_VCF_Xpath");
		String petiCat_VCF_Xpath=Environment("petiCat_VCF_Xpath");
		String fundGoal_VCF_Xpath=Environment("fundGoal_VCF_Xpath");
		String fund_VCF_Xpath=Environment("fund_VCF_Xpath");
		String fundRec_VCF_Xpath=Environment("fundRec_VCF_Xpath");
		String availableAmo_VCF_Xpath=Environment("availableAmo_VCF_Xpath");
		String endDate_VCF_Xpath=Environment("endDate_VCF_Xpath");
		String tags_VCF_Xpath=Environment("tags_VCF_Xpath");
		
		fl.ClickByXpath(driver, CrowdFunding_Xpath, "", "Click on Crowd Fund Menu", "", "", "");
		fl.ClickByXpath(driver, crowdFundList_CCF_Xpath, "", "Click on CrowdFund List", "", "", "");
		Thread.sleep(10000);
		fl.ClickByXpath(driver, advancedSearch_VCF_Xpath, "", "Click on Advanced Search", "", "", "");
		fl.entervalueByXpath(driver, serachTitle_VCF_Xpath, CrowdTitle, "", "enter CrowdFund Title in Search Box:"+CrowdTitle, "", "", "");
		
		int more= fun_cas.listSize(driver, moreButtons_VCF_Xpath);
		if(more>1)
		{
			for(int i=1;i<=more;i++)
			{
				String title= fl.getTextXPATH(driver, title_VCF_Xpath+"["+i+"]", "", "Get title text and compare with :"+CrowdTitle, "", "", "");
				if(title.equals(CrowdTitle))
				{
					fl.ClickByXpath(driver, title_VCF_Xpath+"["+i+"]", "", "", "", "", "");
					fl.ClickByXpath(driver, title_VCF_Xpath+"["+i+"]", "", "Click on Crowd Fund Title name , to go detail view", "", "", "");
					status="true";
					break;
				}
			}
			
		}
		else
		{
			if(more==1)
			{
				fl.ClickByXpath(driver, title_VCF_Xpath+"[1]", "", "", "", "", "");
				fl.ClickByXpath(driver, title_VCF_Xpath+"[1]", "", "Click on Crowd Fund Title, To go Detail View", "", "", "");
				status="true";
			}
			else
				if(more==0)
				{
					String noRec=fl.getTextXPATH(driver, NoRedords_VCF_Xpath, "", "Get No Records Existed with :"+CrowdTitle, "", "", "");
					fl.disp_MessageFailed(driver, "", "", "No matching records found", noRec, "");
					status="false";
				}
		}
		return status;
	}
	
	public void validateCrowdFund(WebDriver driver, String crowdFor, String crowdCat, String fund, String endDate, String beneficiaryName, String relationType, String beneficiaryImage, 
			String tags, String video, String description, String linktext, String linkTextUrl, String newWindo, String browse_img, String img_link, String social_img, 
			String gal_img1, String gal_img2, String gal_img3, String gal_img4, String sup_image1,String sup_image2,String sup_image3,
			String sup_image4,String sup_image5, String FAQ_ID)throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String petiFor_VCF_Xpath=Environment("petiFor_VCF_Xpath");
		String petiCat_VCF_Xpath=Environment("petiCat_VCF_Xpath");
		String fundGoal_VCF_Xpath=Environment("fundGoal_VCF_Xpath");
		String fund_VCF_Xpath=Environment("fund_VCF_Xpath");
		String fundRec_VCF_Xpath=Environment("fundRec_VCF_Xpath");
		String availableAmo_VCF_Xpath=Environment("availableAmo_VCF_Xpath");
		String endDate_VCF_Xpath=Environment("endDate_VCF_Xpath");
		String beneficiaryName_VCF_Xpath=Environment("beneficiaryName_VCF_Xpath");
		String relationType_VCF_Xpath=Environment("relationType_VCF_Xpath");
		String benImage_VCF_Xpath=Environment("benImage_VCF_Xpath");
		String tags_VCF_Xpath=Environment("tags_VCF_Xpath");
		String video_VAP_Xpath=Environment("video_VAP_Xpath");
		String attribute_CPValid_Xpath=Environment("attribute_CPValid_Xpath");
		
		String petitonDescription_VAP_Xpath=Environment("petitonDescription_VAP_Xpath");
		String totalParagraphs_VAP_Xpath=Environment("totalParagraphs_VAP_Xpath");
		String singlePara_VAP_Xpath=Environment("singlePara_VAP_Xpath");
		String singleParaNoDesc=Environment("singleParaNoDesc");
		String singleParainclFontStyle_VAP_Xpath=Environment("singleParainclFontStyle_VAP_Xpath");
		String singleParaImage_VAP_Xpath=Environment("singleParaImage_VAP_Xpath");
		String singleParaLinktext_VAP_Xpath=Environment("singleParaLinktext_VAP_Xpath");
		String overview_VAP_Xpath=Environment("overview_VAP_Xpath");
		String paraBreak_VAP_Xpath=Environment("paraBreak_VAP_Xpath");
		String gal1_VAP_Xpath=Environment("gal1_VAP_Xpath");
		String gal2_VAP_Xpath=Environment("gal2_VAP_Xpath");
		String gal3_VAP_Xpath=Environment("gal3_VAP_Xpath");
		String gal4_VAP_Xpath=Environment("gal4_VAP_Xpath");
		String gal5_VAP_Xpath=Environment("gal5_VAP_Xpath");
		String supImg1_VCF_Xpath=Environment("supImg1_VCF_Xpath");
		String supImg2_VCF_Xpath=Environment("supImg2_VCF_Xpath");
		String supImg3_VCF_Xpath=Environment("supImg3_VCF_Xpath");
		String supImg4_VCF_Xpath=Environment("supImg4_VCF_Xpath");
		String supImg5_VCF_Xpath=Environment("supImg5_VCF_Xpath");
		String totalFAQCF_VAP_Xpath=Environment("totalFAQCF_VAP_Xpath");
		String commonqueFAQCF_VAP_Xpath=Environment("commonqueFAQCF_VAP_Xpath");
		String queTextFAQ_VAP_Xpath=Environment("queTextFAQ_VAP_Xpath");
		String ansFAQ_VAP_Xpath=Environment("ansFAQ_VAP_Xpath");
		
		String singleparaText;
		String sinParImageText;
		String sinParLinkText;
		
		if(crowdFor!="")
		{
			String crowdFo=fl.getTextXPATH(driver, petiFor_VCF_Xpath, "", "Get Petition for field text", "", "", "");
			//fl.disp_Message(driver, "", "", crowdFo, crowdFor, "");
			if(crowdFor.equals(crowdFo))
				fl.disp_Message(driver, "", "Matching with input", crowdFor, crowdFo, "");
			else
				fl.disp_MessageFailed(driver, "", "Not Matches with input", crowdFor, crowdFo, "Y");
		}
		if(crowdCat!="")
		{
			String crowdca=fl.getTextXPATH(driver, petiCat_VCF_Xpath, "", "Get Petition Category", "", "", "");
			if(crowdCat.equals(crowdca))
				fl.disp_Message(driver, "", "Matching with Input", crowdCat, crowdca, "");
			else
				fl.disp_MessageFailed(driver, "", "Not Matches with input", crowdCat, crowdca, "Y");
		}
		if(fund!="")
		{
			String fun=fl.getTextXPATH(driver, fund_VCF_Xpath, "", "Get fund", "", "", "");
			if(fund.equals(fun))
				fl.disp_Message(driver, "", "Matching with Input", fund, fun, "");
			else
				fl.disp_MessageFailed(driver, "", "Not Matches with input", fund, fun, "Y");
		}
		if(endDate!="")
		{
			String date=fl.getTextXPATH(driver, endDate_VCF_Xpath, "", "Get End Date Text", "", "", "Y");
			//if(endDate.equals(date))
		}
		if(beneficiaryName!="")
		{
			String ben_name=fl.getTextXPATH(driver, beneficiaryName_VCF_Xpath, "", "get Beneficiary name", "", "", "");
			if(ben_name.equals(beneficiaryName))
				fl.disp_Message(driver, "", "Comparing beneficiaryname with input", beneficiaryName, ben_name, "");
			else
				fl.disp_MessageFailed(driver, "", "Not Matches with input", beneficiaryName, ben_name, "Y");
		}
		if(relationType!="")
		{
			String rel_Type=fl.getTextXPATH(driver, relationType_VCF_Xpath, "", "Get Relation Type", "", "", "");
			if(relationType.equals(rel_Type))
				fl.disp_Message(driver, "", "Comparing relationType with input", beneficiaryName, rel_Type, "");
			else
				fl.disp_MessageFailed(driver, "", "Not Matches with input", beneficiaryName, rel_Type, "Y");
		}
		if(beneficiaryImage!="")
		{
			fun_cas.attributeValue(driver, benImage_VCF_Xpath, "src", beneficiaryImage);
		}
		if(tags!="")
		{
			int totTag=fun_cas.listSize(driver, tags_VCF_Xpath);
			for(int i=1;i<=totTag;i++)
			{
				String tag=fl.getTextXPATH(driver, tags_VCF_Xpath+"["+i+"]", "", "Get Each Tag Text", "", "", "");
				if(tags.contains(tag))
				{
					fl.disp_Message(driver, "", "Matches With Input", tags, tag, "");
					break;
				}
				else
				{
					if(i<=totTag)
					{
						fl.disp_MessageFailed(driver, "", "Not Matches with input", tags, tag, "Y");
						break;
					}
				}
			}
		}
		if(video!="")
		{
			int tot_videos=fun_cas.listSize(driver, video_VAP_Xpath);
	
			if(tot_videos==1)
			{
				fl.JS_Element_Find(driver, video_VAP_Xpath);
				//driver.switchTo().frame(0);
				String videoLink1=fun_cas.returnAttribute(driver, video_VAP_Xpath, attribute_CPValid_Xpath);
				System.out.println(videoLink1);
				
				String[] parts = videoLink1.split("/");
				System.out.println("length="+parts.length);
				for(int i=0;i<parts.length;i++)
				{
					System.out.println(parts[i]);
				}
				System.out.println("Last part of link:"+parts[parts.length-1]);
				String videoLink=parts[parts.length-1];
				
				//driver.switchTo().defaultContent();
				if(video.contains(videoLink))
					fl.disp_Message(driver, "", "", "Video link"+video, videoLink, "");
				else
					fl.disp_MessageFailed(driver, "", "", "Video link"+video, videoLink, "Y");
			}
		}
		if(description!="")
		{
			fl.JS_Element_Find(driver, petitonDescription_VAP_Xpath);
			List<WebElement> innerPara=driver.findElements(By.xpath(totalParagraphs_VAP_Xpath));
			if(innerPara.size()==0)
			{//if single para , get text, img and linktext if existed
				int brk_nodesc=fun_cas.listSize(driver, singlePara_VAP_Xpath+singleParaNoDesc);
				if(brk_nodesc==1)
				{
					String singleParaDescrText=fl.getTextXPATH(driver, singlePara_VAP_Xpath, "", "", "get text of single para", "", "");
					System.out.println(singleParaDescrText);
					if(description.contains(singleParaDescrText))
						fl.disp_Message(driver, "", "", "single para Description text:"+description, singleParaDescrText, "");
					else
						fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleParaDescrText, "Y");
				}
			}
			else
			{
		
				if(innerPara.size()==1)
				{
					//get single Para Text
					int fontText=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParainclFontStyle_VAP_Xpath);
					if(fontText==1)
					{
						singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParainclFontStyle_VAP_Xpath, "", "", "get single para text includes font", "", "");
						System.out.println(singleparaText);
						if(description.contains(singleparaText))
							fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
						else
							fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
							
					}
					else
					{
						if(fontText==0)
						{
							singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath, "", "", "get single para text", "", "");
							System.out.println(singleparaText);
							if(description.contains(singleparaText))
								fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
							else
								fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
						}
					}
					//get single para  imag attribute value
					if(browse_img!="")
					{
						int singleparaimg =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath);
						if(singleparaimg==1)
						{
							sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath, "data-filename");
							System.out.println(sinParImageText);
							if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
								fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
							else
								fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
						}
						else
						{
							if(singleparaimg>1)
							{
								for(int i=1;i<=singleparaimg;i++)
								{
									sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+singleParaImage_VAP_Xpath+"["+i+"]", "data-filename");
									System.out.println(sinParImageText);
									if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
										fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
									else
										fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
								}
							}
							else
							{
								if(singleparaimg==0)
								{
									fl.disp_Message(driver, "", "Single para contains no images", "no images found", "", "");
								}
							}
						}
					}
					//get single para link text //linkTextUrl//newWindo
					if(linktext!="")
					{
						int singleparalink =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath);
						if(singleparalink==1)
						{
							sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath, "", "", "get Single Para Link Text", "", "");
							System.out.println(sinParLinkText);
							if(linktext.equals(sinParLinkText))
								fl.disp_Message(driver, "", "", "Single para contains Linktext:"+linktext, sinParLinkText, "");
							else
								fl.disp_Message(driver, "", "", "Single para contains No Linktext:"+linktext, sinParLinkText, "");
						}
						else
						{
							if(singleparalink>1)
							{
								for(int i=1;i<=singleparalink;i++)
								{
									fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath+"["+i+"]");
									sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+singleParaLinktext_VAP_Xpath+"["+i+"]", "", "", "get Single Para contains multiple Link Text", "", "");
									System.out.println(sinParLinkText);
									if(linktext.equals(sinParLinkText))
										fl.disp_Message(driver, "", "", "Single para contains Linktext:"+linktext, sinParLinkText, "");
									else
										fl.disp_Message(driver, "", "", "Single para contains No Linktext:"+linktext, sinParLinkText, "");
								}
							}
							else
							{
								if(singleparalink==0)
								{
									fl.disp_Message(driver, "", "Single para contains no link text", "no link text  found", "", "");
								}
							}
						}
					}
				}
				else
				{
					if(innerPara.size()>1)
					{
						for(int k=1;k<=innerPara.size();k++)
						{
							fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
							//get single Para linkText
				
							int fontText=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath);
							if(fontText==1)
							{
								int brk=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath+paraBreak_VAP_Xpath);
								if(brk==0)
								{
							
								singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParainclFontStyle_VAP_Xpath, "", "", "get multiple para text includes font", "", "");
								System.out.println(singleparaText);
								if(description.contains(singleparaText))
									fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
								else
									fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
								}
							}
							else
							{
								int overview=fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+overview_VAP_Xpath);
						
								if(fontText==0)
								{
									if(overview==0)
									{
										singleparaText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]", "", "", "get multiple para text", "", "");
										System.out.println(singleparaText);
										if(description.contains(singleparaText))
											fl.disp_Message(driver, "", "", "single para Description text:"+description, singleparaText, "");
										else
											fl.disp_MessageFailed(driver, "", "", "single para Description text:"+description, singleparaText, "Y");
									}
								}
							}
							//get single para  imag attribute value  //browse_img
							if(browse_img!="")
							{
								int singleparaimg =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath);
								if(singleparaimg==1)
								{
									sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath, "data-filename");
									System.out.println(sinParImageText);
									if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
										fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
									else
										fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
								}
								else
								{
									if(singleparaimg>1)
									{
										for(int i=1;i<=singleparaimg;i++)
										{
											fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
											sinParImageText=returnImagenameattributeValue(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaImage_VAP_Xpath+"["+i+"]", "data-filename");
											System.out.println(sinParImageText);
											if(browse_img.contains(sinParImageText)||img_link.contains(sinParImageText))
												fl.disp_Message(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "");
											else
												fl.disp_MessageFailed(driver, "", "", "single para Description Image:"+browse_img, sinParImageText, "Y");
										}	
									}
									else
									{
										if(singleparaimg==0)
										{
											fl.disp_Message(driver, "", "multiple para contains no images", "no images found", "", "");
										}
									}
								}
							}
							//get single para link text //linkTextUrl //newWindo
							if(linktext!="")
							{
								int singleparalink =fun_cas.listSize(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath);
								if(singleparalink==1)
								{
									sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath, "", "", "get multiple Para Link Text", "", "");
									System.out.println(sinParLinkText);
									if(linktext.equals(sinParLinkText))
										fl.disp_Message(driver, "", "", "Multiple para contains Linktext:"+linktext, sinParLinkText, "");
									else
										fl.disp_Message(driver, "", "", "Multiple para contains No Linktext:"+linktext, sinParLinkText, "");
								}
								else
								{
									if(singleparalink>1)
									{
										for(int i=1;i<=singleparalink;i++)
										{
											fl.JS_Element_Find(driver, totalParagraphs_VAP_Xpath+"["+k+"]");
											sinParLinkText=fl.getTextXPATH(driver, totalParagraphs_VAP_Xpath+"["+k+"]"+singleParaLinktext_VAP_Xpath, "", "", "get multiple Para contains multiple Link Text", "", "");
											System.out.println(sinParLinkText);
											if(linktext.equals(sinParLinkText))
												fl.disp_Message(driver, "", "", "Multiple para contains Linktext:"+linktext, sinParLinkText, "");
											else
												fl.disp_Message(driver, "", "", "Multiple para contains No Linktext:"+linktext, sinParLinkText, "");
										}
									}
									else
									{
										if(singleparalink==0)
										{
											fl.disp_Message(driver, "", "multiple para contains no link text", "no link text  found", "", "");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		//IMAGE VALIDATION
		fl.JS_Element_Find(driver, gal1_VAP_Xpath);
		if(social_img!="")
		{
			fun_cas.attributeValue(driver, gal1_VAP_Xpath, attribute_CPValid_Xpath, social_img);
		}
		if(gal_img1!="")
		{
			fun_cas.attributeValue(driver, gal2_VAP_Xpath, attribute_CPValid_Xpath, gal_img1);
		}
		if(gal_img2!="")
		{
			fun_cas.attributeValue(driver, gal3_VAP_Xpath, attribute_CPValid_Xpath, gal_img2);
		}
		if(gal_img3!="")
		{
			fun_cas.attributeValue(driver, gal4_VAP_Xpath, attribute_CPValid_Xpath, gal_img3);
		}
		if(gal_img4!="")
		{
			fun_cas.attributeValue(driver, gal5_VAP_Xpath, attribute_CPValid_Xpath, gal_img4);
		}
		//SUPPORTING DOCUMENTS VALIDATION
		fl.JS_Element_Find(driver, supImg1_VCF_Xpath);
		if(sup_image1!="")
		{
			fun_cas.attributeValue(driver, supImg1_VCF_Xpath, attribute_CPValid_Xpath, sup_image1);
		}
		if(sup_image2!="")
		{
			fun_cas.attributeValue(driver, supImg2_VCF_Xpath, attribute_CPValid_Xpath, sup_image2);
		}
		if(sup_image3!="")
		{
			fun_cas.attributeValue(driver, supImg3_VCF_Xpath, attribute_CPValid_Xpath, sup_image3);
		}
		if(sup_image4!="")
		{
			fun_cas.attributeValue(driver, supImg4_VCF_Xpath, attribute_CPValid_Xpath, sup_image4);
		}
		if(sup_image5!="")
		{
			fun_cas.attributeValue(driver, supImg5_VCF_Xpath, attribute_CPValid_Xpath, sup_image5);
		}
		//FAQ VALIDATION
		if(FAQ_ID!="")
		{
			int queno=0;
			List<String> faq = new ArrayList<>();
			//List<String> faq = null;
			List<String> webfaq = new ArrayList<>();
			//List<String> webfaq=null;
			Excel_Utils RC = new Excel_Utils(Environment("Excel"));
			String Crowd_FAQ=Environment("Sheet_Crowd_FAQ"); 
			int Crowd_FAQ_row=RC.getLastrowno(Crowd_FAQ); 
			System.out.println("total faq petition rows are:"+Crowd_FAQ_row);
			int Crowd_FAQ_col=RC.getLastcolmno(Crowd_FAQ); 
			String[] Crowd_FAQ_ele=new String[Crowd_FAQ_col]; 
			for (int Crowd_FAQ_index = 1; Crowd_FAQ_index < Crowd_FAQ_row; Crowd_FAQ_index++) 
			{ 
				 System.out.println("for Loop" );
				 System.out.println(FAQ_ID);
				 System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"), Crowd_FAQ)); 
				 if (FAQ_ID.equals(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)))
					  // Adduser contains company email_id at 1st column  for validation
				 { 
					 queno++;
					  System.out.println("Matches ID to Register");
					  System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)); 
					  //based on j value get the row data and do Adding Users
					   
					  for(int Crowd_FAQ_ind=0;Crowd_FAQ_ind<Crowd_FAQ_col;Crowd_FAQ_ind++) 
					  {
						  Crowd_FAQ_ele[Crowd_FAQ_ind]=RC.getStringCellData(Crowd_FAQ_index, Crowd_FAQ_ind, Crowd_FAQ);
						  System.out.println(Crowd_FAQ_ele[Crowd_FAQ_ind]); //call login as company method, pass array values
		  
			  
					  }
					  faq.add(Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Question")]);
					  faq.add(Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Answer")]);
			          
				 }
			}
			
			int faqs=fun_cas.listSize(driver, totalFAQCF_VAP_Xpath);
			System.out.println("In validation : Total faq are:"+queno);
			
//TOTAL QUESTIONS IN FAQ MATCHES OR NOT		
			int faq_siz=faq.size()/2;
			/*if(faq_siz==faqs)
			{
				fl.disp_Message(driver, "", "", "Total Question in FAQ in view Same", "", "");
			}*/
			if(faqs>=1)
			{
				for(int i=1;i<=faqs;i++)
				{
					String faq_Q=fl.getTextXPATH(driver, commonqueFAQCF_VAP_Xpath+i+queTextFAQ_VAP_Xpath, "", "", "get question "+i+" text", "", "");
					System.out.println(faq_Q);
					webfaq.add(faq_Q);
					//fl.disp_Message(driver, "", "", "get question "+i+" text:"+, ActualResult, Screenshot);
					String faq_A=fl.getTextXPATH(driver, commonqueFAQCF_VAP_Xpath+i+ansFAQ_VAP_Xpath, "", "", "get answer "+i+" text", "", "");
					System.out.println(faq_A);
					webfaq.add(faq_A);
				}
			}
			System.out.println("faq excel list size::"+faq.size());
			System.out.println("faq web list size::"+webfaq.size());
			int webfaq_siz=webfaq.size()/2;
			String webfaq_size=String.valueOf(webfaq_siz);
			if(faq_siz==webfaq_siz)
			{
				fl.disp_Message(driver, "", "", "FAQ questions are"+faq_siz, webfaq_size, "");
				for(int i=0;i<webfaq.size();i++)
				{
					if(i%2==0)
					{
						System.out.println("FAQ QUESTION:%"+faq.get(i));
						System.out.println("FAQ QUESTION:%"+webfaq.get(i));
						if(webfaq.get(i).contains(faq.get(i)))
						{
							fl.disp_Message(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "");
						}
						else
							fl.disp_MessageFailed(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "Y");
						
					}	
					else
					{
						System.out.println("FAQ QUESTION:%"+faq.get(i));
						System.out.println("FAQ QUESTION:%"+webfaq.get(i));
						if(webfaq.get(i).contains(faq.get(i)))
						{
							fl.disp_Message(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "");
						}
						else
						{
							fl.disp_MessageFailed(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "Y");
						}
					}
				}
			}
			else
				fl.disp_MessageFailed(driver, "", "", "FAQ questions are :"+faq_siz, webfaq_size, "Y");
		}
		else
		{
			fl.disp_Message(driver, "", "", "No FAQ given by you", "", "");
		}
	}
	public List<String> moreOptionsCrowdFund(WebDriver driver, String CrowdTitle) throws IOException, InterruptedException
	{
		String status = null;
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String CrowdFunding_Xpath=Environment("CrowdFunding_Xpath");
		String crowdFundList_CCF_Xpath=Environment("crowdFundList_CCF_Xpath");
		String advancedSearch_VCF_Xpath=Environment("advancedSearch_VCF_Xpath");
		String serachTitle_VCF_Xpath=Environment("serachTitle_VCF_Xpath");
		String moreButtons_VCF_Xpath=Environment("moreButtons_VCF_Xpath");
		String title_VCF_Xpath=Environment("title_VCF_Xpath");
		String NoRedords_VCF_Xpath=Environment("NoRedords_VCF_Xpath");
		String petiFor_VCF_Xpath=Environment("petiFor_VCF_Xpath");
		String petiCat_VCF_Xpath=Environment("petiCat_VCF_Xpath");
		String fundGoal_VCF_Xpath=Environment("fundGoal_VCF_Xpath");
		String fund_VCF_Xpath=Environment("fund_VCF_Xpath");
		String fundRec_VCF_Xpath=Environment("fundRec_VCF_Xpath");
		String availableAmo_VCF_Xpath=Environment("availableAmo_VCF_Xpath");
		String endDate_VCF_Xpath=Environment("endDate_VCF_Xpath");
		String tags_VCF_Xpath=Environment("tags_VCF_Xpath");
		List<String> list=new ArrayList<>();
		
		fl.ClickByXpath(driver, CrowdFunding_Xpath, "", "Click on Crowd Fund Menu", "", "", "");
		fl.ClickByXpath(driver, crowdFundList_CCF_Xpath, "", "Click on CrowdFund List", "", "", "");
		Thread.sleep(10000);
		fl.ClickByXpath(driver, advancedSearch_VCF_Xpath, "", "Click on Advanced Search", "", "", "");
		fl.entervalueByXpath(driver, serachTitle_VCF_Xpath, CrowdTitle, "", "enter CrowdFund Title in Search Box:"+CrowdTitle, "", "", "");
		String iter;
		int more= fun_cas.listSize(driver, moreButtons_VCF_Xpath);
		if(more>1)
		{
			for(int i=1;i<=more;i++)
			{
				String title= fl.getTextXPATH(driver, title_VCF_Xpath+"["+i+"]", "", "Get title text and compare with :"+CrowdTitle, "", "", "");
				if(title.equals(CrowdTitle))
				{
					fl.ClickByXpath(driver, moreButtons_VCF_Xpath+"["+i+"]", "", "Click on More button", "", "", "");
					status="true";
					
					iter=String.valueOf(i);;
					list.add(iter);
					list.add(status);
					break;
				}
			}
			
		}
		else
		{
			if(more==1)
			{
				fl.ClickByXpath(driver, moreButtons_VCF_Xpath+"[1]", "", "", "", "", "");
				fl.ClickByXpath(driver, moreButtons_VCF_Xpath+"[1]", "", "Click on More Button", "", "", "");
				iter="1";
				list.add(iter);
				status="true";
				list.add(status);
				
			}
			else
				if(more==0)
				{
					String noRec=fl.getTextXPATH(driver, NoRedords_VCF_Xpath, "", "Get No Records Existed with :"+CrowdTitle, "", "", "");
					fl.disp_MessageFailed(driver, "", "", "No matching records found", noRec, "");
					iter="0";
					list.add(iter);
					status="false";
					list.add(status);
				
				}
		}
		return list;
	}
	public void publishSavedCrowdFund(WebDriver driver, String i) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String moreButtons_VCF_Xpath=Environment("moreButtons_VCF_Xpath");
		String submitForApproval_VCO_Xpath=Environment("submitForApproval_VCO_Xpath");
		String proceed_PSC_Xpath=Environment("proceed_PSC_Xpath");
		String alert_Xpath=Environment("alert_Xpath");
		int j=fun_cas.stringToIntegerconvert(i);
		
		fl.ClickByXpath(driver, moreButtons_VCF_Xpath+"["+j+"]"+submitForApproval_VCO_Xpath, "", "Click on Submit For Approval", "", "", "");
		fl.ClickByXpath(driver, proceed_PSC_Xpath, "", "Click on Proceed Submit For Approval", "", "", "");
		
		String status=fl.getTextXPATH(driver, alert_Xpath+"[2]", "", "Get Suucess Mesaage", "", "", "");
		fl.disp_Message(driver, "", "", "CrowdFunding successfully submitted for approval", status, "");
	}
	public void AprvRejctCrowdFund(WebDriver driver, String campaignTitle, String approve, String reject, String comment, int first) throws IOException, InterruptedException
	{
		String crowdFunds_Admin_Xpath=Environment("crowdFunds_Admin_Xpath");
		String search_CFA_Xpath=Environment("search_CFA_Xpath");
		String titleMatches_CFA_Xpath=Environment("titleMatches_CFA_Xpath");
		String matchedRow_CFA_Xpath=Environment("matchedRow_CFA_Xpath");
		String reject_CFA_Xpath=Environment("reject_CFA_Xpath");
		String rejectOpen_CFA_Xpath=Environment("rejectOpen_CFA_Xpath");
		String rejectComments_CFA_Xpath=Environment("rejectComments_CFA_Xpath");
		String rejectOpenComments_CFA_Xpath=Environment("rejectOpenComments_CFA_Xpath");
		String rejectConfirm_CFA_Xpath=Environment("rejectConfirm_CFA_Xpath");
		String unpublishOpen_CFA_Xpath=Environment("unpublishOpen_CFA_Xpath");
		String approve_CFA_Xpath=Environment("approve_CFA_Xpath");
		String acceptComments_CFA_Xpath=Environment("acceptComments_CFA_Xpath");
		String approveConfirm_CFA_Xpath=Environment("approveConfirm_CFA_Xpath");
		String checkStatus_CFA_Xpath=Environment("checkStatus_CFA_Xpath");
		String noPetition_CFA_Xpath=Environment("noPetition_CFA_Xpath");
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		fl.JS_Element_Find(driver, crowdFunds_Admin_Xpath);
		int clear=0;
		//Thread.sleep(20000);
		if(first==1)
		{
			fl.ClickByXpath(driver, crowdFunds_Admin_Xpath, "", "", "Go to crowdfund List", "", "");
			clear++;
		}
		Thread.sleep(3000);
		if(clear==0)
		{
			fun_cas.clearTextfield(driver, search_CFA_Xpath, campaignTitle);
		}
		
		Thread.sleep(10000);
		fl.entervalueByXpath(driver, search_CFA_Xpath, campaignTitle, "", "", "search with crowdfund title", "", "");
		//ClickEnterRobot enterRobo = new ClickEnterRobot();
		List<WebElement> elements = driver.findElements(By.xpath(titleMatches_CFA_Xpath));
		if(elements.size()>=1)
		{
			for(int i=0;i<elements.size();i++)
			{
				int j=i+1;
				if(elements.get(i).getText().equals(campaignTitle))
				{
					fl.JS_Element_Find(driver, matchedRow_CFA_Xpath+"["+j+"]");
					if(approve!="")
					{
						String  status=fl.getTextXPATH(driver, matchedRow_CFA_Xpath+"["+j+"]"+checkStatus_CFA_Xpath, "", "", "check status of crowdfund before Accept", "", "Y");
						if(!status.equals("open")&&status.equals("Approval Pending"))
						{
							fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+approve_CFA_Xpath, "", "", "", "", "");
							fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+approve_CFA_Xpath, campaignTitle, "", "click on Accept crowdfund by admin", "", "Y");
							Thread.sleep(3000);
							fl.entervalueByXpath(driver, acceptComments_CFA_Xpath, comment, "", "", "comment by Admin", "", "");
							fl.ClickByXpath(driver, approveConfirm_CFA_Xpath, "", "", "click on Approveconfirm Button for crowdfund:"+campaignTitle, "", "");
							break;
						}
						else
						{
							if(status.equals("open"))
							{
								fl.disp_MessageFailed(driver, "", "", "already opened:"+campaignTitle, "", "");
								break;
							}
							else
							{
								if(status.equals("UnPublished"))
								{
									fl.disp_MessageFailed(driver, "", "", "Already Unpublished"+campaignTitle, "", "");
									break;
								}
							}
						}
					}
					else
					{
						if(reject!="")
						{
							String  status=fl.getTextXPATH(driver, matchedRow_CFA_Xpath+"["+j+"]"+checkStatus_CFA_Xpath, "", "", "check status of crowdfund before Accept", "", "Y");
							if(status.equals("Approval Pending"))
							{
								fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+reject_CFA_Xpath, "", "", "", "", "");
								fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+reject_CFA_Xpath, "", "", "click on reject crowdfund by admin", "", "Y");
								Thread.sleep(3000);
								fl.entervalueByXpath(driver, rejectComments_CFA_Xpath, comment, "", "", "comment by Admin", "", "");
								fl.ClickByXpath(driver, rejectConfirm_CFA_Xpath, "", "", "click on RejectConfirm Button for the first time:"+campaignTitle, "", "");
								break;
							}
							else
							{
								if(status.equals("open"))
								{
									fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+rejectOpen_CFA_Xpath, "", "", "", "", "");
									fl.ClickByXpath(driver, matchedRow_CFA_Xpath+"["+j+"]"+rejectOpen_CFA_Xpath, "", "", "click on unpublish crowdfund by admin", "", "Y");
									Thread.sleep(3000);
									fl.entervalueByXpath(driver, rejectOpenComments_CFA_Xpath, comment, "", "", "comment by Admin", "", "");
									fl.ClickByXpath(driver, unpublishOpen_CFA_Xpath, "", "", "click on Unpublish already accepted crowdfund by admin:"+campaignTitle, "", "");
									break;
								}
								else
								{
									fl.disp_MessageFailed(driver, "", "", "Already Unpublished"+campaignTitle, status, "");
									break;
								}
							}
						}
					}
				}
			}
		}
		else
		{
			String noPet=fl.getTextXPATH(driver, noPetition_CFA_Xpath, "", "", "get text of no results", "", "");
			fl.disp_Message(driver, "", "", "No matching records found", noPet, "");
		}
		
		
	}
	public String companyBase_CrowdFund(WebDriver driver, String crowdFundTitle) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String status = null;
		String digi_BaseURL=Environment("digi_BaseURL");
		String browseCF_CFO_Xpath=Environment("browseCF_CFO_Xpath");
		String websiteLogin_Xpath=Environment("websiteLogin_Xpath");
		String petitionTitle_searchXpath=Environment("petitionTitle_searchXpath");
		String checkFilteredPetitions_Xpath=Environment("checkFilteredPetitions_Xpath");
		String clickOnPetitionName_Xpath=Environment("clickOnPetitionName_Xpath");
		String searchPetitionButton_Xpath=Environment("searchPetitionButton_Xpath");
		
		String petitionsDisplat_BUO_Xpath=Environment("petitionsDisplat_BUO_Xpath");
		String cfTitles_BUO_Xpath=Environment("cfTitles_BUO_Xpath");
		String petitionTitles1_BUO_Xpath=Environment("petitionTitles1_BUO_Xpath");
		String petitionTitles2_BUO_Xpath=Environment("petitionTitles2_BUO_Xpath");
		String loadMoreButton_BUO_Xpath=Environment("loadMoreButton_BUO_Xpath");
		try 
		{
			fl.invokeApplication(driver, digi_BaseURL, "Chrome", "localhost:4034", "Launching The App", "launch the App Home Page", "Launched the page", "");
			fl.ClickByXpath(driver, browseCF_CFO_Xpath, "", "CrowdFund list view", "navigating to CrowdFund list", "", "");
			fl.ClickByXpath(driver, websiteLogin_Xpath, "", "", "", "", "");
			
			String Parent=driver.getWindowHandle();
			Set<String> set = new HashSet<String>(driver.getWindowHandles());
			for(String tab : set)
			{
				System.out.println("window :"+tab);
			}
			set.remove(Parent);

			driver.switchTo().window(set.iterator().next());
//added lines	==============================================================		
			String secondURL=driver.getCurrentUrl();
			if(!secondURL.contains("/Account/Login"))
			{
				fun_cas.CompanyLogout(driver);
			}
//added lines end  ============================================================			
			driver.close();
			
			driver.switchTo().window(Parent);
			Thread.sleep(10000);
			int listSize=fun_cas.listSize(driver, cfTitles_BUO_Xpath);
			System.out.println("petition list size="+listSize);
			for(int i=1;i<=listSize;i++)
			{
				if(i%4==0)
					fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
				String cFTitle=fl.getTextXPATH(driver, cfTitles_BUO_Xpath+"["+i+"]", "", "", "Get CrowdFundTitle", "", "");
				System.out.println("CrowdFund title "+i+":"+cFTitle);
				if(crowdFundTitle.equals(cFTitle))
				{
					fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
					fl.ClickByXpath(driver,cfTitles_BUO_Xpath+"["+i+"]", "", "", "Click on Selected CrowdFund", "", "");
					return "true";
				}
				else
				{
					if(i%6==0 && i<=listSize)
					{
						fl.JS_Element_Find(driver, petitionsDisplat_BUO_Xpath+"["+i+"]");
						fl.ClickByXpath(driver, loadMoreButton_BUO_Xpath, "", "", "Not available click on load more", "", "");
					}
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
	public void ValidateCFInWebsite(WebDriver driver, String cf_status, String raisedFund,
			String description, String browse_imag, String link, String faqID) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String createBy_PVW_Xpath=Environment("createBy_PVW_Xpath");
		String createorg_PVW_Xpath=Environment("createorg_PVW_Xpath");
		String suppOrgNo_PVW_Xpath=Environment("suppOrgNo_PVW_Xpath");
		String suppOrgName1_PVW_Xpath=Environment("suppOrgName1_PVW_Xpath");
		String suppOrgName2_PVW_Xpath=Environment("suppOrgName2_PVW_Xpath");
		String raisedFund_PVW_Xpath=Environment("raisedFund_PVW_Xpath");
		String signReq_PVW_Xpath=Environment("signReq_PVW_Xpath");
		String daysLeft_PVW_Xpath=Environment("daysLeft_PVW_Xpath");
		String details_PVW_Xpath=Environment("details_PVW_Xpath");
		String totalPara_PVW_Xpath=Environment("totalPara_PVW_Xpath");
		String noDesc_PVW_Xpath=Environment("noDesc_PVW_Xpath");
		String fontDesc_PVW_Xpath=Environment("fontDesc_PVW_Xpath");
		String linktext_PVW_Xpath=Environment("linktext_PVW_Xpath");
		String img_PVW_Xpath=Environment("img_PVW_Xpath");
		String faqs_PVW_Xpath=Environment("faqs_PVW_Xpath");
		String totFaq_PVW_Xpath=Environment("totFaq_PVW_Xpath");
		String queFaq_PVW_Xpath=Environment("queFaq_PVW_Xpath");
		String ansFaq_PVW_Xpath=Environment("ansFaq_PVW_Xpath");
		if(cf_status.equals("true"))
		{
			fun_cas.getTextValidation(driver, raisedFund_PVW_Xpath, raisedFund);
			
			fl.ClickByXpath(driver, details_PVW_Xpath, "", "", "Click on Details Tab", "", "");
			
			int paras = fun_cas.listSize(driver, totalPara_PVW_Xpath);
			if(paras==1)
			{
				int nopara=fun_cas.listSize(driver, totalPara_PVW_Xpath+noDesc_PVW_Xpath);
				if(nopara==1)
				{
					fl.disp_Message(driver, "", "", "No Descrption available", "", "");
				}
				else
				{
					int singleParaText=fun_cas.listSize(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath);
					if(singleParaText==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath, description);
					int singleParaImg=fun_cas.listSize(driver, totalPara_PVW_Xpath+img_PVW_Xpath);
					if(singleParaImg==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+img_PVW_Xpath, browse_imag);
					int singleParaLink=fun_cas.listSize(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath);
					if(singleParaLink==1)
						fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath, link);
				}
				
			}
			else
			{
				if(paras>1)
				{
					for(int i=1;i<=paras;i++)
					{
						int multipleParaNoText=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+fontDesc_PVW_Xpath+noDesc_PVW_Xpath);
						if(multipleParaNoText==1)
						{
							
						}
						else
						{
							int multipleParaText=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+fontDesc_PVW_Xpath);
							if(multipleParaText==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+fontDesc_PVW_Xpath, description);
							int multipleParaImg=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+img_PVW_Xpath);
							if(multipleParaImg==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+img_PVW_Xpath, browse_imag);
							int multipleParaLink=fun_cas.listSize(driver, totalPara_PVW_Xpath+"["+i+"]"+linktext_PVW_Xpath);
							if(multipleParaLink==1)
								fun_cas.getTextValidation(driver, totalPara_PVW_Xpath+linktext_PVW_Xpath, link);
						}
					}
				}
			}
			if(faqID!="")
			{
				//FAQ VALIDATION
				int queno=0;
				List<String> faq = new ArrayList<>();
				//List<String> faq = null;
				List<String> webfaq = new ArrayList<>();
				//List<String> webfaq=null;
				Excel_Utils RC = new Excel_Utils(Environment("Excel"));
				String Crowd_FAQ=Environment("Sheet_Crowd_FAQ"); 
				int Crowd_FAQ_row=RC.getLastrowno(Crowd_FAQ); 
				System.out.println("total faq petition rows are:"+Crowd_FAQ_row);
				int Crowd_FAQ_col=RC.getLastcolmno(Crowd_FAQ); 
				String[] Crowd_FAQ_ele=new String[Crowd_FAQ_col]; 
				for (int Crowd_FAQ_index = 1; Crowd_FAQ_index < Crowd_FAQ_row; Crowd_FAQ_index++) 
				{ 
					System.out.println("for Loop" );
					System.out.println(faqID);
					System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"), Crowd_FAQ)); 
					if (faqID.equals(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)))
						// Adduser contains company email_id at 1st column  for validation
					{ 
						queno++;
						System.out.println("Matches ID to Register");
						System.out.println(RC.getStringCellData(Crowd_FAQ_index, RC.Current_Coulumn_Number(Crowd_FAQ, "FAQ_ID"),Crowd_FAQ)); 
						//based on j value get the row data and do Adding Users
					   
						for(int Crowd_FAQ_ind=0;Crowd_FAQ_ind<Crowd_FAQ_col;Crowd_FAQ_ind++) 
						{
							Crowd_FAQ_ele[Crowd_FAQ_ind]=RC.getStringCellData(Crowd_FAQ_index, Crowd_FAQ_ind, Crowd_FAQ);
							System.out.println(Crowd_FAQ_ele[Crowd_FAQ_ind]); //call login as company method, pass array values
		  
			  
						}
						faq.add(Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Question")]);
						faq.add(Crowd_FAQ_ele[RC.Current_Coulumn_Number(Crowd_FAQ, "Answer")]);
			          
					}
				}
				fl.ClickByXpath(driver, faqs_PVW_Xpath, "", "", "Click on FAQs tab", "", "");
			
				int faqs=fun_cas.listSize(driver, totFaq_PVW_Xpath);
				System.out.println("In validation : Total faq are:"+queno);
			
				int faq_siz=faq.size()/2;
			
				if(faqs>=1)
				{
					for(int i=1;i<=faqs;i++)
					{
						String faq_Q=fl.getTextXPATH(driver, totFaq_PVW_Xpath+"["+i+"]"+queFaq_PVW_Xpath, "", "", "get question "+i+" text", "", "");
						System.out.println(faq_Q);
						webfaq.add(faq_Q);
						//fl.disp_Message(driver, "", "", "get question "+i+" text:"+, ActualResult, Screenshot);
						String faq_A=fl.getTextXPATH(driver, totFaq_PVW_Xpath+"["+i+"]"+ansFaq_PVW_Xpath, "", "", "get answer "+i+" text", "", "");
						System.out.println(faq_A);
						webfaq.add(faq_A);
					}
				}
				System.out.println("faq excel list size::"+faq.size());
				System.out.println("faq web list size::"+webfaq.size());
				int webfaq_siz=webfaq.size()/2;
				String webfaq_size=String.valueOf(webfaq_siz);
				if(faq_siz==webfaq_siz)
				{
					fl.disp_Message(driver, "", "", "FAQ questions are"+faq_siz, webfaq_size, "");
					for(int i=0;i<webfaq.size();i++)
					{
						if(i%2==0)
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
								fl.disp_MessageFailed(driver, "", "", "FAQ Question:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							
						}	
						else
						{
							System.out.println("FAQ QUESTION:%"+faq.get(i));
							System.out.println("FAQ QUESTION:%"+webfaq.get(i));
							if(webfaq.get(i).contains(faq.get(i)))
							{
								fl.disp_Message(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "");
							}
							else
							{
								fl.disp_MessageFailed(driver, "", "", "FAQ Answer:"+i+":"+faq.get(i), webfaq.get(i), "Y");
							}
						}
					}
				}
				else
					fl.disp_MessageFailed(driver, "", "", "FAQ questions are :"+faq_siz, webfaq_size, "Y");
			}
		}
	}
	public List<String> petitionNewCommentsOnCompanySide(WebDriver driver, String comment) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		List<String> list=new ArrayList<>();
		
		String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
		String commentsTab_PVC_Xpath=Environment("commentsTab_PVC_Xpath");
		String addComments_PVC_Xpath=Environment("addComments_PVC_Xpath");
		String commentText_PVC_Xpath=Environment("commentText_PVC_Xpath");
		String send_PVC_Xpath=Environment("send_PVC_Xpath");
		String close_PVC_Xpath=Environment("close_PVC_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		String time_PVC_Xpath=Environment("time_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		String replyLink_PVC_Xpath=Environment("replyLink_PVC_Xpath");
		String replycomment_PVC_Xpath=Environment("replycomment_PVC_Xpath");
		String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
		String replyButton_PVC_Xpath=Environment("replyButton_PVC_Xpath");
		String totRepliesForAMainComment_PVC_Xpath=Environment("totRepliesForAMainComment_PVC_Xpath");
		String replyNameANDTime_PVC_XPath=Environment("replyNameANDTime_PVC_XPath");
		String replyTime_PVC_Xpath=Environment("replyTime_PVC_Xpath");
		String replyComment_PVC_Xpath=Environment("replyComment_PVC_Xpath");
		
		fl.JS_Element_Find(driver, logoutNameText_Xpath);
		String name=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Name of Who Logged in ", "", "", "");
		
		fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "Click on Comments Tab", "", "", "");
		fl.ClickByXpath(driver, addComments_PVC_Xpath, "", "Click on Add Comment to add a new Comment", "", "", "");
		fl.entervalueByXpath(driver, commentText_PVC_Xpath, comment, "", "Enter Comment Text", "", "", "");
		fl.ClickByXpath(driver, send_PVC_Xpath, "", "Click on Send Button", "", "", "");
		driver.navigate().refresh();
		fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "Click on Comments Tab", "", "", "");
		int totComments=fun_cas.listSize(driver, totComments_PVC_Xpath);
		System.out.println(totComments);
		String totComments_no=String.valueOf(totComments);
		list.add(totComments_no);
		String recentComment=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+comment_PVC_Xpath, "", "Get Recent New Comment Text To Validate", "", "", "");
		fl.disp_Message(driver, "", "New Comment Text is:", comment, recentComment, "");
		list.add(recentComment);
		if(recentComment.equals(comment))
		{
			String postedBy=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+nameNDTime_PVC_Xpath, "", "Get Deatils of who posted comment", "", "", "");
			fl.disp_Message(driver, "", "New Comment Posted By :", name, postedBy, "");
			list.add(postedBy);
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "New Comment Text on Comapny Side (Expected) is not matched with input", comment, recentComment, "");
			Assert.fail("CommentText Mismatch On Company side and website");
		}
		return list;
	}
	public List<String> petitionReplyCommentsOnCompanySide(WebDriver driver, String comment) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		List<String> list=new ArrayList<>();
		
		String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
		String commentsTab_PVC_Xpath=Environment("commentsTab_PVC_Xpath");
		//String addComments_PVC_Xpath=Environment("addComments_PVC_Xpath");
		//String commentText_PVC_Xpath=Environment("commentText_PVC_Xpath");
		//String send_PVC_Xpath=Environment("send_PVC_Xpath");
		String close_PVC_Xpath=Environment("close_PVC_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
		String alreadyExistedReplies_PVC_Xpath=Environment("alreadyExistedReplies_PVC_Xpath");
		String firstCommentReplyLink_Xpath=Environment("firstCommentReplyLink_Xpath");
		String replyBox_Xpath=Environment("replyBox_Xpath");
		String replyButton_Xpath=Environment("replyButton_Xpath");
		
		//VALIDATION
		String totRepComntsForFirstComment_Xpath=Environment("totRepComntsForFirstComment_Xpath");
		String totRepComntsForAFirstComment_Xpath=Environment("totRepComntsForAFirstComment_Xpath");
		String totCountRepComntsForFirstComment_Xpath=Environment("totCountRepComntsForFirstComment_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		String time_PVC_Xpath=Environment("time_PVC_Xpath");

		
		fl.JS_Element_Find(driver, logoutNameText_Xpath);
		String name=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Name of Who Logged in ", "", "", "");
		System.out.println("Get Name of Who Logged in: "+name);
		fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "Click on Comments Tab", "", "", "");
		//int totComments=fun_cas.listSize(driver, totComments_PVC_Xpath);
		//System.out.println(totComments);
		//String totComments_no=String.valueOf(totComments);
		//list.add(totComments_no);
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on first Comment to add a Reply comment", "", "", "");
		int totComments=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
		System.out.println(totComments);
		String totComments_no=String.valueOf(totComments);
		list.add(totComments_no);
		
		int totReplies=fun_cas.listSize(driver, totComments_PVC_Xpath+"[1]"+totCountRepComntsForFirstComment_Xpath);
		String countBeforeReply=String.valueOf(totComments);
		if(totReplies>=0)
		{
			//String countBeforeReply=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+totCountRepComntsForFirstComment_Xpath, "", "Get Total Reply Comment Count For First Comment", "", "", "");
			System.out.println("total li and count no values are"+totComments_no+","+countBeforeReply);
			if(totComments_no.equals(countBeforeReply))
			{
		
				//fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on first Comment to add a Reply comment", "", "", "");
		
				fl.entervalueByXpath(driver, replyBox_Xpath, comment, "", "Enter Reply Comment Text", "", "", "");
				fl.ClickByXpath(driver, replyButton_Xpath, "", "Click on Reply Button", "", "", "");
		
				driver.navigate().refresh();
				fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "After Refreshing webpage Click on Comments Tab", "", "", "");
				Thread.sleep(3000);
				fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on first Comment to add a Reply comment", "", "", "");
				int totReplyComments=fun_cas.listSize(driver, totRepComntsForFirstComment_Xpath);
				System.out.println("totReplyComments "+totReplyComments);
				String totreplyComments_num=String.valueOf(totReplyComments);
				System.out.println("totreplyComments_num "+totreplyComments_num);
				int totComent=totComments+1;
				String totreplyComments_no=String.valueOf(totComent);
				fl.disp_Message(driver, "", "After Adding a reply comment Total Reply comments for first comment are ", totreplyComments_no, totreplyComments_num, "");
			
				//fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on first Comment to add a Reply comment", "", "", "");
				Thread.sleep(3000);
				int totReplyComments_num=fun_cas.listSize(driver, totComments_PVC_Xpath+"[1]"+totCountRepComntsForFirstComment_Xpath);
				fl.JS_Element_Find(driver, totRepComntsForFirstComment_Xpath+"["+totreplyComments_num+"]"+comment_PVC_Xpath);
//if reply comment adding at last position uncomment below line, otherwise comment it				
				//String recentReplyComment=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+totreplyComments_num+"]"+comment_PVC_Xpath, "", "Get Recent New Reply Comment Text To Validate", "", "", "");
				String recentReplyComment=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+1+"]"+comment_PVC_Xpath, "", "Get Recent New Reply Comment Text To Validate", "", "", "");
				System.out.println("recentReplyComment "+recentReplyComment);
				fl.disp_Message(driver, "", "New Comment Text is:", comment, recentReplyComment, "");
				list.add(recentReplyComment);
				if(recentReplyComment.equals(comment))
				{
					String postedBy=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+totreplyComments_num+"]"+nameNDTime_PVC_Xpath, "", "Get Deatils of who posted comment", "", "", "");
					fl.disp_Message(driver, "", "New Comment Posted By :", name, postedBy, "");
					list.add(postedBy);
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "New Reply Comment Text on Comapny Side (Expected) is not matched with input", comment, recentReplyComment, "");
					Assert.fail("CommentText Mismatch On Company side and website");
				}
			}
		}
		else
		{
			
			
		}
		return list;
	}
	public void petitionNewCommentByCompValidatingOnWebSite(WebDriver driver, String comment, String nameAndTime) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String firstCommentReplyLink_Xpath=Environment("firstCommentReplyLink_Xpath");
		String totRepComntsForFirstComment_Xpath=Environment("totRepComntsForFirstComment_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		String time_PVC_Xpath=Environment("time_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		System.out.println("petitionNewCommentByCompValidatingOnWebSite");
		
		driver.navigate().refresh();
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		Thread.sleep(3000);
		fl.JS_Element_Find(driver, commentstab_PVC_Web_Xpath);
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Click on Comments Tab On WebSite", "", "", "");
		
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on First Comment Reply Link", "", "", "");
		int tot_ReplyComments=fun_cas.listSize(driver, totRepComntsForFirstComment_Xpath);
		System.out.println("Total Reply Comments For First Comment In WebSite:"+tot_ReplyComments);
		fl.JS_Element_Find(driver, totRepComntsForFirstComment_Xpath+"["+1+"]"+comment_PVC_Xpath);
//if the reply comment added at last position uncomment below line, otherwise keep below step comment		
		//String recentComment=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+tot_ReplyComments+"]"+comment_PVC_Xpath, "", "Get Comment Text", "", "", "");
		String recentComment=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+1+"]"+comment_PVC_Xpath, "", "Get Comment Text", "", "", "");
		if(comment.equals(recentComment))
		{
			fl.disp_Message(driver, "", "Comment Text on Comapny Side (Expected) And WebSite Side(Actual) is", comment, recentComment, "");
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "Comment Text on Comapny Side (Expected) And WebSite Side(Actual) is", comment, recentComment, "");
			Assert.fail("CommentText Mismatch On Company side and website ");
		}
		String namAndTim=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+tot_ReplyComments+"]"+nameNDTime_PVC_Xpath, "", "Get name And Time on WebSide", "", "", "");
		if(nameAndTime!="")
		{
			if(nameAndTime.equals(namAndTim))
			{
				fl.disp_Message(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
			}
			else
				if(namAndTim.contains(nameAndTime))
				{
					fl.disp_Message(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
					Assert.fail("Name And Time Mismatch On Company side and website ");
				}
		}
		
	}
	/*public void petition_NewCommentByCompValidatingOnWebSite(WebDriver driver, String comment, String nameAndTime) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String firstCommentReplyLink_Xpath=Environment("firstCommentReplyLink_Xpath");
		String totRepComntsForFirstComment_Xpath=Environment("totRepComntsForFirstComment_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		String time_PVC_Xpath=Environment("time_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		System.out.println("petitionNewCommentByCompValidatingOnWebSite");
		
		driver.navigate().refresh();
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		Thread.sleep(3000);
		fl.JS_Element_Find(driver, commentstab_PVC_Web_Xpath);
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Click on Comments Tab On WebSite", "", "", "");
		
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+firstCommentReplyLink_Xpath, "", "Click on First Comment Reply Link", "", "", "");
		int tot_Comments=fun_cas.listSize(driver, totComments_PVC_Xpath);
		System.out.println("Total Comments In WebSite:"+tot_Comments);
		fl.JS_Element_Find(driver, totComments_PVC_Xpath+"["+1+"]");
//if the reply comment added at last position uncomment below line, otherwise keep below step comment		
		//String recentComment=fl.getTextXPATH(driver, totRepComntsForFirstComment_Xpath+"["+tot_ReplyComments+"]"+comment_PVC_Xpath, "", "Get Comment Text", "", "", "");
		String recentComment=fl.getTextXPATH(driver, totComments_PVC_Xpath+"["+1+"]"+comment_PVC_Xpath, "", "Get Comment Text", "", "", "");
		if(comment.equals(recentComment))
		{
			fl.disp_Message(driver, "", "Comment Text on Comapny Side (Expected) And WebSite Side(Actual) is", comment, recentComment, "");
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "Comment Text on Comapny Side (Expected) And WebSite Side(Actual) is", comment, recentComment, "");
			Assert.fail("CommentText Mismatch On Company side and website ");
		}
		String namAndTim=fl.getTextXPATH(driver, totComments_PVC_Xpath+"["+1+"]"+nameNDTime_PVC_Xpath, "", "Get name And Time on WebSide", "", "", "");
		if(nameAndTime!="")
		{
			if(nameAndTime.equals(namAndTim))
			{
				fl.disp_Message(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
			}
			else
				if(namAndTim.contains(nameAndTime))
				{
					fl.disp_Message(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
				}
				else
				{
					fl.disp_MessageFailed(driver, "", "Name And Time on Comapny Side (Expected) And WebSite Side(Actual) is", nameAndTime, namAndTim, "");
					Assert.fail("Name And Time Mismatch On Company side and website ");
				}
		}
		
	}*/
	public void petition_NewCommentByCompValidatingOnWebSite(WebDriver driver, String comment, String nameAndTime) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String firstCommentReplyLink_Xpath=Environment("firstCommentReplyLink_Xpath");
		String totRepComntsForFirstComment_Xpath=Environment("totRepComntsForFirstComment_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		String time_PVC_Xpath=Environment("time_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		
		driver.navigate().refresh();
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		Thread.sleep(3000);
		fl.JS_Element_Find(driver, commentstab_PVC_Web_Xpath);
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Click on Comments Tab On WebSite", "", "", "");
		
		int tot_Comments=fun_cas.listSize(driver, totComments_PVC_Xpath);
		
		String commentWeb=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+comment_PVC_Xpath, "", "Get Comment Text", "", "", "");
		if(commentWeb.equals(comment))
		{
			fl.disp_Message(driver, "", "Comparing Comment Text in Company And WebSite", comment, commentWeb, "");
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "Comparing Comment Text in Company And WebSite", comment, commentWeb, "");
		}
		String nameAndTimeWeb=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+nameNDTime_PVC_Xpath, "", "Get Text of User", "", "", "");
		if(nameAndTimeWeb.contains(nameAndTime))
		{
			fl.disp_Message(driver, "", "Comparing User in Company And WebSite", nameAndTime, nameAndTimeWeb, "");
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "Comparing User in Company And WebSite", nameAndTime, nameAndTimeWeb, "");
		}
	}
	public List<String> petitionReplyCommentToARecentCommentInWebSite_ByExistedUser(WebDriver driver, String replyComment, String username, String password) 
			throws IOException, InterruptedException
	{
		List<String> list=new ArrayList<>();
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String dashboard_webSite_Xpath=Environment("dashboard_webSite_Xpath");
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		
		String replyLink_PVC_Xpath=Environment("replyLink_PVC_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
		String replycomment_PVC_Xpath=Environment("replycomment_PVC_Xpath");
		String replyButton_PVC_Xpath=Environment("replyButton_PVC_Xpath");
		String replyComment_PVC_Xpath=Environment("replyComment_PVC_Xpath");
		String replyNameANDTime_PVC_XPath=Environment("replyNameANDTime_PVC_XPath");
		String alreadyExistedReplies_PVC_Xpath=Environment("alreadyExistedReplies_PVC_Xpath");
		String countNo_replyLinkAfter_PVC_Xpath=Environment("countNo_replyLinkAfter_PVC_Xpath");
		
		//LOGIN
		String username_ExistedUser_Xpath=Environment("username_ExistedUser_Xpath");
		String password_ExistedUser_Xpath=Environment("password_ExistedUser_Xpath");
		String login_ExistedUser_Xpath=Environment("login_ExistedUser_Xpath");
		String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
		
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Reply to a Recent Comment ,Click on Comment Tab", "", "", "");
		int totComents=fun_cas.listSize(driver, commentstab_PVC_Web_Xpath);
		String totalComments=String.valueOf(totComents);
		list.add(totalComments);
		
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+replyLink_PVC_Xpath, "", "Click on First recent comment Reply Button ", "", "", "");
		int totRepliesforFirstComment=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
		String beforeReply=String.valueOf(totRepliesforFirstComment);
		
		fl.entervalueByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replycomment_PVC_Xpath, replyComment, "", "Enter Reply Comment", "", "", "");
		fl.ClickByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath, "", "Click on Reply Button ", "", "", "");
		
		//LOGIN  BY EXISTED USER
		fl.entervalueByXpath(driver, username_ExistedUser_Xpath, username, "", "", "Username Value to be entered", "", "");
		fl.entervalueByXpath(driver, password_ExistedUser_Xpath, password, "", "", "Password to be entered", "", "");
		fl.ClickByXpath(driver, login_ExistedUser_Xpath, "", "", "Login Button to be clicked", "", "");
		
		fl.JS_Element_Find(driver, dashboard_webSite_Xpath);
		fl.ClickByXpath(driver, dashboard_webSite_Xpath, "", "Click on Dashboard Button", "", "", "");
		
		String Parent=driver.getWindowHandle();
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);
		driver.switchTo().window(set.iterator().next());
		String login_UserName=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Login Name", "", "", "");
		list.add(login_UserName);
		fun_cas.CompanyLogout(driver);
		driver.close();
		driver.switchTo().window(Parent);
		
		fl.JS_Element_Find(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath);
		fl.ClickByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath, "", "Click on Reply Button ", "", "", "");
		
		driver.navigate().refresh();
		
		String fundUna_att1=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Reply to a Recent Comment ,Click on Comment Tab", "", "", "");
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+replyLink_PVC_Xpath, "", "Click on First recent comment Reply Button ", "", "", "");
		
		int totRepliesAfterReply=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
		String totReplyAfterReply=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+countNo_replyLinkAfter_PVC_Xpath, "", "Get total Replies for Main Comment", "", "", "");
		//String afterReply=String.valueOf(totRepliesAfterReply);
		int afterReply=Integer.parseInt(totReplyAfterReply);
		System.out.println("After Reply Total Comments Value Converted to int : "+afterReply);
		
		if(totRepliesAfterReply==afterReply)
		{
			
			list.add(totReplyAfterReply);
			fl.disp_Message(driver, "", "Validating Count After reply Link text ,after adding one more reply comment", beforeReply, totReplyAfterReply, "");
			String repliedComment=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totRepliesAfterReply+"]"+replyComment_PVC_Xpath, "", "Get Recent Replied COmment Text", "", "", "");
			fl.disp_Message(driver, "", "Validating Replied Comment", replyComment, repliedComment, "");
			list.add(repliedComment);
			String details=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totRepliesAfterReply+"]"+replyNameANDTime_PVC_XPath, "", "Get Name And Posted Time", "", "", "");
			fl.disp_Message(driver, "", "Validating Replied Comment By UserName", login_UserName, details, "");
			list.add(details);
		}
		else
			fl.disp_MessageFailed(driver, "", "Count After reply Link text is not changed after adding one more reply comment", "", "", "Y");
		return list;	
	}
	public List<String> petitionClickOnReplyCommentToARecentCommentInWebSite_ByNewUser(WebDriver driver, String replyComment) 
			throws IOException, InterruptedException
	{
		List<String> list=new ArrayList<>();
		Functional_Cases_propread fun_cas = new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String dashboard_webSite_Xpath=Environment("dashboard_webSite_Xpath");
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		
		String replyLink_PVC_Xpath=Environment("replyLink_PVC_Xpath");
		
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
		String replycomment_PVC_Xpath=Environment("replycomment_PVC_Xpath");
		String replyButton_PVC_Xpath=Environment("replyButton_PVC_Xpath");
		String replyComment_PVC_Xpath=Environment("replyComment_PVC_Xpath");
		String replyNameANDTime_PVC_XPath=Environment("replyNameANDTime_PVC_XPath");
		String alreadyExistedReplies_PVC_Xpath=Environment("alreadyExistedReplies_PVC_Xpath");
		String countNo_replyLinkAfter_PVC_Xpath=Environment("countNo_replyLinkAfter_PVC_Xpath");
		String signUp_Company_Xpath=Environment("signUp_Company_Xpath");
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Reply to a Recent Comment ,Click on Comment Tab", "", "", "");
		int totComents=fun_cas.listSize(driver, commentstab_PVC_Web_Xpath);
		String totalComments=String.valueOf(totComents);
		//list.add(totalComments);
		
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+replyLink_PVC_Xpath, "", "Click on First recent comment Reply Button ", "", "", "");
		int totRepliesforFirstComment=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
		String beforeReply=String.valueOf(totRepliesforFirstComment);
		list.add(beforeReply);
		
		fl.entervalueByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replycomment_PVC_Xpath, replyComment, "", "Enter Reply Comment", "", "", "");
		fl.ClickByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath, "", "Click on Reply Button ", "", "", "");
		
		fl.ClickByXpath(driver, signUp_Company_Xpath, "", "Click on Signup Button", "", "", "");
		String Parent=driver.getWindowHandle();
		list.add(Parent);
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		return list;	
	}
	public void companyRegisteringToReplyACommentInWebsite(WebDriver driver,int first, String yourname, String orgname, String orgemailid, String contno, String fein, String website,
			String password, String confirmpassword, String captcha, String screen) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		fun_cas.companyRegistration(driver, first, yourname, orgname, orgemailid, contno, fein, website, password, confirmpassword, captcha);
		fun_cas.CompanyLogout(driver);
		
		driver.close();
		driver.switchTo().window(screen);
	}
	public void individualRegisteringToReplyACommentInWebsite(WebDriver driver,int first, String firstname, String lastname, String emailid, 
			String contactnumber, String password, String confirmpassword, String captcha, String screen) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		fun_cas.candidateRegistration(driver, first, firstname, lastname, emailid, contactnumber, password, confirmpassword, captcha);
		Thread.sleep(20000);
		fun_cas.CompanyLogout(driver);
		
		driver.close();
		driver.switchTo().window(screen);
	}
	public void petitionRegisteredUserReplyToRecentComment(WebDriver driver, String username, String password, String beforeReply,
			 String replyComment) throws IOException, InterruptedException
	{
		List<String> list=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		//LOGIN
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");
		String logout_img_Xpath=Environment("logout_img_Xpath");
		String logoutOptions_Xpath=Environment("logoutOptions_Xpath");
		String myProfile_Company_Xpath=Environment("myProfile_Company_Xpath");
		String myProfile5_Company_Xpath=Environment("myProfile5_Company_Xpath");
				String username_ExistedUser_Xpath=Environment("username_ExistedUser_Xpath");
				String password_ExistedUser_Xpath=Environment("password_ExistedUser_Xpath");
				String login_ExistedUser_Xpath=Environment("login_ExistedUser_Xpath");
				String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
				
				String dashboard_webSite_Xpath=Environment("dashboard_webSite_Xpath");
				String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
				String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
				String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
				String replycomment_PVC_Xpath=Environment("replycomment_PVC_Xpath");
				String replyButton_PVC_Xpath=Environment("replyButton_PVC_Xpath");
				String replyComment_PVC_Xpath=Environment("replyComment_PVC_Xpath");
				String replyLink_PVC_Xpath=Environment("replyLink_PVC_Xpath");
				String replyNameANDTime_PVC_XPath=Environment("replyNameANDTime_PVC_XPath");
				String alreadyExistedReplies_PVC_Xpath=Environment("alreadyExistedReplies_PVC_Xpath");
				String countNo_replyLinkAfter_PVC_Xpath=Environment("countNo_replyLinkAfter_PVC_Xpath");
				
		//LOGIN  BY EXISTED USER
				fl.entervalueByXpath(driver, username_ExistedUser_Xpath, username, "", "", "Username Value to be entered", "", "");
				fl.entervalueByXpath(driver, password_ExistedUser_Xpath, password, "", "", "Password to be entered", "", "");
				fl.ClickByXpath(driver, login_ExistedUser_Xpath, "", "", "Login Button to be clicked", "", "");
				
				fl.ClickByXpath(driver, replyButton_PVC_Xpath, "", "Click on Reply Button", "", "", "");
				
				fl.JS_Element_Find(driver, dashboard_webSite_Xpath);
				fl.ClickByXpath(driver, dashboard_webSite_Xpath, "", "Click on Dashboard Button", "", "", "");
				
				String Parent=driver.getWindowHandle();
				Set<String> set = new HashSet<String>(driver.getWindowHandles());
				for(String tab : set)
				{
					System.out.println("window :"+tab);
				}
				set.remove(Parent);
				driver.switchTo().window(set.iterator().next());
				String login_UserName=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Login Name", "", "", "");
				list.add(login_UserName);
				/*fl.ClickByXpath(driver, logout_img_Xpath, "", "Click on Logout Image", "", "", "");
				int logout_options=fun_cas.listSize(driver, logoutOptions_Xpath);
				if(logout_options==6)
				{
					fl.ClickByXpath(driver, myProfile_Company_Xpath, "", "Click on MyProfile", "", "", "");
				}
				else
				{
					if(logout_options==5)
					{
						fl.ClickByXpath(driver, myProfile5_Company_Xpath, "", "Click on MyProfile", "", "", "");
					}
					else
					{
						if(logout_options==4)
						{
							fl.ClickByXpath(driver, myProfile5_Company_Xpath, "", "Click on MyProfile", "", "", "");
						}
					}
					
				}*/
				fun_cas.CompanyLogout(driver);
				driver.close();
				driver.switchTo().window(Parent);
				
				/*fl.JS_Element_Find(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath);
				fl.ClickByXpath(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+replyButton_PVC_Xpath, "", "Click on Reply Button ", "", "", "");
				*/
				driver.navigate().refresh();
				
				String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
				if(fundUna_att.contains("display: block;"))
					fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
				
				fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Reply to a Recent Comment ,Click on Comment Tab", "", "", "");
				fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+replyLink_PVC_Xpath, "", "Click on First recent comment Reply Button ", "", "", "");
				
				int totRepliesAfterReply=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
				String totReplyAfterReply=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+countNo_replyLinkAfter_PVC_Xpath, "", "Get total Replies for Main Comment", "", "", "");
				//String afterReply=String.valueOf(totRepliesAfterReply);
				int afterReply=Integer.parseInt(totReplyAfterReply);
				System.out.println("After Reply Total Comments Value Converted to int : "+afterReply);
				
				if(totRepliesAfterReply==afterReply)
				{
					
					list.add(totReplyAfterReply);
					fl.disp_Message(driver, "", "Validating Count After reply Link text ,after adding one more reply comment", beforeReply, totReplyAfterReply, "");
					String repliedComment=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totRepliesAfterReply+"]"+replyComment_PVC_Xpath, "", "Get Recent Replied COmment Text", "", "", "");
					fl.disp_Message(driver, "", "", replyComment, repliedComment, "");
					list.add(repliedComment);
					String details=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totRepliesAfterReply+"]"+replyNameANDTime_PVC_XPath, "", "Get Name And Posted Time", "", "", "");
					fl.disp_Message(driver, "", "", login_UserName, details, "");					
					list.add(details);
				}
				else
					fl.disp_MessageFailed(driver, "", "Count After reply Link text is not changed after adding one more reply comment", "", "", "Y");
	}
	public void petitionReplyCommentToARecentCommentInWebSite_ByExistedUserValidation(WebDriver driver, String totMainComments, String username, String totReplies, String repliedComm, String repliedDetail) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas= new Functional_Cases_propread();
		String commentsTab_PVC_Xpath=Environment("commentsTab_PVC_Xpath");
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String replyLink_PVC_Xpath=Environment("replyLink_PVC_Xpath");
		String totRepliesForAllMainComment_PVC_Xpath=Environment("totRepliesForAllMainComment_PVC_Xpath");
		String alreadyExistedReplies_PVC_Xpath=Environment("alreadyExistedReplies_PVC_Xpath");
		String replyNameANDTime_PVC_XPath=Environment("replyNameANDTime_PVC_XPath");
		String replyComment_PVC_Xpath=Environment("replyComment_PVC_Xpath");
		
		fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "Click on Comments Tab", "", "", "");
		int totMainComm=fun_cas.listSize(driver, totComments_PVC_Xpath);
		String totalMainComm=String.valueOf(totMainComm);
		if(totMainComments.equals(totalMainComm))
		{
			fl.disp_Message(driver, "", "Compare TotalMain Comments In WebSite And Company", totMainComments, totalMainComm, "");
		}
		else
			fl.disp_MessageFailed(driver, "", "Compare TotalMain Comments In WebSite And Company", totMainComments, totalMainComm, "");
		
		fl.ClickByXpath(driver, totComments_PVC_Xpath+"[1]"+replyLink_PVC_Xpath, "", "Click on Reply Link", "", "", "");
		
		int totReplyComnts=fun_cas.listSize(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath);
		String totalReplyComnts=String.valueOf(totReplyComnts);
		if(totReplies.equals(totalReplyComnts))
		{
			fl.disp_Message(driver, "", "Compare Reply Comments for first recent comment In WebSite And Company", totReplies, totalReplyComnts, "");
		}
		else
			fl.disp_MessageFailed(driver, "", "Compare Reply Comments for first recent comment In WebSite And Company", totReplies, totalReplyComnts, "");
		
		String user=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totReplyComnts+"]"+replyNameANDTime_PVC_XPath, "", "Get username and Time of replied comment", "", "", "");
		String replyComment=fl.getTextXPATH(driver, totRepliesForAllMainComment_PVC_Xpath+"[1]"+alreadyExistedReplies_PVC_Xpath+"["+totReplyComnts+"]"+replyComment_PVC_Xpath, "", "Get Reply Text", "", "", "");
		if(user.contains(username))
		{
			fl.disp_Message(driver, "", "Compare User who replied to first recent comment, validate on Company Side", "", "", "");
		}
		else
			fl.disp_MessageFailed(driver, "", "Compare User who replied to first recent comment, validate on Company Side", "", "", "");
		if(repliedComm.equals(replyComment))
		{
			fl.disp_Message(driver, "", "Compare Reply Coment text In WebSite And Company Side", repliedComm, replyComment, "");
		}
		else
			fl.disp_MessageFailed(driver, "", "Compare Reply Coment text In WebSite And Company Side", repliedComm, replyComment, "");
		if(user.equals(repliedDetail))
		{
			fl.disp_Message(driver, "", "Compare Replied Text Name And Time In WebSite And CompanySide", repliedDetail, user, "");
		}
		else
			fl.disp_MessageFailed(driver, "", "Compare Replied Text Name And Time In WebSite And CompanySide", repliedDetail, user, "");
	}
	public void newCommentPostInWebSite(WebDriver driver, String comment) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String fundUnavilPopup_Xpath=Environment("fundUnavilPopup_Xpath");
		String fundUnavailable_Xpath=Environment("fundUnavailable_Xpath");	
		String commentstab_PVC_Web_Xpath=Environment("commentstab_PVC_Web_Xpath");
		String addComment_WNC_Xpath=Environment("addComment_WNC_Xpath");
		String commentText_WNC_Xpath=Environment("commentText_WNC_Xpath");
		String send_WNC_Xpath=Environment("send_WNC_Xpath");
		String cancel_WNC_Xpath=Environment("cancel_WNC_Xpath");
		
		
		String fundUna_att=fun_cas.returnAttribute(driver, fundUnavilPopup_Xpath, "style");
		if(fundUna_att.contains("display: block;"))
			fl.ClickByXpath(driver, fundUnavailable_Xpath, "", "Click on Popup Collecting fund unavailable", "", "", "");
		
		fl.JS_Element_Find(driver, commentstab_PVC_Web_Xpath);
		fl.ClickByXpath(driver, commentstab_PVC_Web_Xpath, "", "Click on Comments Tab", "", "", "");
		fl.ClickByXpath(driver, addComment_WNC_Xpath, "", "Click on Add Comment", "", "", "");
		fl.entervalueByXpath(driver, commentText_WNC_Xpath, comment, "", "Enter Comment Text", "", "", "");
		fl.ClickByXpath(driver, send_WNC_Xpath, "", "Click on Send button", "", "", "");
	}
	public String newCommentByExisterUserSignIn(WebDriver driver, String email, String password, String petitioin) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String username_Reg_Xpath=Environment("username_Reg_Xpath");
		String password_Reg_Xpath=Environment("password_Reg_Xpath");
		String login_Reg_Xpath=Environment("login_Reg_Xpath");
		String send_WNC_Xpath=Environment("send_WNC_Xpath");
		String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
		
		Thread.sleep(3000);
		fl.entervalueByXpath(driver, username_Reg_Xpath, email, "", "Enter Email Id", "", "", "");
		fl.entervalueByXpath(driver, password_Reg_Xpath, password, "", "Enter password", "", "", "");
		fl.ClickByXpath(driver, login_Reg_Xpath, "", "Click on login button", "", "", "");
		//fl.JS_Element_Find(driver, send_WNC_Xpath);
		fl.ClickByXpath(driver, send_WNC_Xpath, "", "Click on Send Button", "", "", "");
		driver.get(Environment("Comapany_BaseURL_Digi"));
		String name=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Username", "", "", "");
//check		
		fun_cas.companyBase_Petitions(driver, petitioin);
		return name;
	}
	public String CF_newCommentByExisterUserSignIn(WebDriver driver, String email, String password, String petitioin) throws IOException, InterruptedException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String username_Reg_Xpath=Environment("username_Reg_Xpath");
		String password_Reg_Xpath=Environment("password_Reg_Xpath");
		String login_Reg_Xpath=Environment("login_Reg_Xpath");
		String send_WNC_Xpath=Environment("send_WNC_Xpath");
		String logoutNameText_Xpath=Environment("logoutNameText_Xpath");
		
		Thread.sleep(3000);
		fl.entervalueByXpath(driver, username_Reg_Xpath, email, "", "Enter Email Id", "", "", "");
		fl.entervalueByXpath(driver, password_Reg_Xpath, password, "", "Enter password", "", "", "");
		fl.ClickByXpath(driver, login_Reg_Xpath, "", "Click on login button", "", "", "");
		//fl.JS_Element_Find(driver, send_WNC_Xpath);
		fl.ClickByXpath(driver, send_WNC_Xpath, "", "Click on Send Button", "", "", "");
		driver.get(Environment("Comapany_BaseURL_Digi"));
		String name=fl.getTextXPATH(driver, logoutNameText_Xpath, "", "Get Username", "", "", "");
//check		
		fun_cas.companyBase_CrowdFund(driver, petitioin);
		return name;
	}
	public void newCommentByExistedUserInWebValidInCompany(WebDriver driver, String comment, String name) throws IOException, InterruptedException
	{
		List<String> list=new ArrayList<>();
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		String commentsTab_PVC_Xpath=Environment("commentsTab_PVC_Xpath");
		String totComments_PVC_Xpath=Environment("totComments_PVC_Xpath");
		String comment_PVC_Xpath=Environment("comment_PVC_Xpath");
		String nameNDTime_PVC_Xpath=Environment("nameNDTime_PVC_Xpath");
		
		fl.ClickByXpath(driver, commentsTab_PVC_Xpath, "", "Click on Comments Tab", "", "", "");
		int totComments=fun_cas.listSize(driver, totComments_PVC_Xpath);
		System.out.println(totComments);
		String totComments_no=String.valueOf(totComments);
		list.add(totComments_no);
		String recentComment=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+comment_PVC_Xpath, "", "Get Recent New Comment Text To Validate", "", "", "");
		fl.disp_Message(driver, "", "New Comment Text is:", comment, recentComment, "");
		list.add(recentComment);
		if(recentComment.equals(comment))
		{
			String postedBy=fl.getTextXPATH(driver, totComments_PVC_Xpath+"[1]"+nameNDTime_PVC_Xpath, "", "Get Deatils of who posted comment", "", "", "");
			fl.disp_Message(driver, "", "New Comment Posted By :", name, postedBy, "");
			list.add(postedBy);
		}
		else
		{
			fl.disp_MessageFailed(driver, "", "New Comment Text on Comapny Side (Expected) is not matched with input", comment, recentComment, "");
			Assert.fail("CommentText Mismatch On Company side and website");
		}
	}
	public String  newCommentByNewCompanyRegisterSignIn(WebDriver driver, int first, String yourname, String orgname, String orgemailid, String contno, String fein, String website,
			String password, String confirmpassword, String captcha, String petition) throws InterruptedException, IOException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		String signUp_Company_Xpath=Environment("signUp_Company_Xpath");
		
		
		fl.ClickByXpath(driver, signUp_Company_Xpath, "", "Click on SignUp Button To Register a New Company", "", "", "");
		
		String Parent=driver.getWindowHandle();
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		fun_cas.companyRegistration(driver, first, yourname, orgname, orgemailid, contno, fein, website, password, confirmpassword, captcha);
		
		//confirm whether logout required or not
		
		driver.close();
		
		driver.switchTo().window(Parent);
		
		String name=fun_cas.newCommentByExisterUserSignIn(driver, orgemailid, password, petition);
		return name;
	}
	public String  newCommentByNewIndividualRegisterSignIn(WebDriver driver, int first, String firstname, String lastname, String emailid, String contactnumber,
			String password, String confirmpassword, String captcha, String petition) throws InterruptedException, IOException, ClassNotFoundException, SQLException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		String signUp_Company_Xpath=Environment("signUp_Company_Xpath");
		
		
		fl.ClickByXpath(driver, signUp_Company_Xpath, "", "Click on SignUp Button To Register a New Company", "", "", "");
		
		String Parent=driver.getWindowHandle();
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		fun_cas.candidateRegistration(driver, first, firstname, lastname, emailid, contactnumber, password, confirmpassword, captcha);
		
		//confirm whether logout required or not
		
		driver.close();
		
		driver.switchTo().window(Parent);
		
		String name=fun_cas.CF_newCommentByExisterUserSignIn(driver, emailid, password, petition);
		return name;
	}
	public String  peti_newCommentByNewIndividualRegisterSignIn(WebDriver driver, int first, String firstname, String lastname, String emailid, String contactnumber,
			String password, String confirmpassword, String captcha, String petition) throws InterruptedException, IOException, ClassNotFoundException, SQLException
	{
		Functional_Cases_propread fun_cas=new Functional_Cases_propread();
		
		String signUp_Company_Xpath=Environment("signUp_Company_Xpath");
		
		
		fl.ClickByXpath(driver, signUp_Company_Xpath, "", "Click on SignUp Button To Register a New Company", "", "", "");
		
		String Parent=driver.getWindowHandle();
		Set<String> set = new HashSet<String>(driver.getWindowHandles());
		for(String tab : set)
		{
			System.out.println("window :"+tab);
		}
		set.remove(Parent);

		driver.switchTo().window(set.iterator().next());
		fun_cas.candidateRegistration(driver, first, firstname, lastname, emailid, contactnumber, password, confirmpassword, captcha);
		
		//confirm whether logout required or not
		
		driver.close();
		
		driver.switchTo().window(Parent);
		
		String name=fun_cas.newCommentByExisterUserSignIn(driver, emailid, password, petition);
		return name;
	}
	
}