package FunctionalLibraries_Digi;

import java.io.IOException;
import java.util.List;
//import java.util.NoSuchElementException;//removed
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;//added
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import Loggings_Digi.Logs_DigiSurvey;
import Utilities_Digi.Environment_proprties_Read;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Functional_Libraries extends Environment_proprties_Read{
	
	//Pass the url into the browser
	
		public void invokeApplication(WebDriver driver, String URL, String browser, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
			
			try{
				
				driver.get(URL);
				reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
				Logs_DigiSurvey.startTestCase(input);
				//driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
			//return driver;
		}
		
	public void navigateurl(WebDriver driver, String navURL, String browser, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
			
			try{
				
				driver.navigate().to(navURL);
				reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
			}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
		}
	    public void ClickByID(WebDriver driver, String Id, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	
	    	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	    		            .withTimeout(20, TimeUnit.SECONDS)
	    		            .pollingEvery(10, TimeUnit.SECONDS)
	    		            .ignoring(NoSuchElementException.class);
	    		
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(Id)));
	    		Thread.sleep(3000);
	    		driver.findElement(By.id(Id)).click();
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    	}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	public void ClickByXpath(WebDriver driver, String Xpath,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	
	    	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	    		            .withTimeout(20, TimeUnit.SECONDS)
	    		            .pollingEvery(10, TimeUnit.SECONDS)
	    		            .ignoring(NoSuchElementException.class);
	    		
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
	    		Thread.sleep(3000);
	    		driver.findElement(By.xpath(Xpath)).click();
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    	}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	
	public void disp_Message(WebDriver driver, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
    	
    	try{
    		
    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
    		            .withTimeout(20, TimeUnit.SECONDS)
    		            .pollingEvery(10, TimeUnit.SECONDS)
    		            .ignoring(NoSuchElementException.class);
    		//if(ActualResult=="SUCCESS")
    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
    		/*if(ActualResult=="FAILED")
        		reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");*/
    	}catch(NoSuchElementException e){
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}catch (WebDriverException e){
			
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}
    }
public void disp_MessageFailed(WebDriver driver, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
    	try{
    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
    		            .withTimeout(20, TimeUnit.SECONDS)
    		            .pollingEvery(10, TimeUnit.SECONDS)
    		            .ignoring(NoSuchElementException.class);
    		reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    		
    	
    }
	public void clear_textfield(WebDriver driver, String xpath)
	{
		
		WebElement element = driver.findElement(By.xpath(xpath));
    	if(element!=null)
    	{
    		driver.findElement(By.xpath(xpath)).click();;
    	}
    	else
    	{
    		reportstep(driver,"", "", "FAILED", "", "","Y");
    	}
    		
	}
	/*public WebElement findElementsByXpath(WebDriver driver,String Xpath)
	{
		WebElement element = driver.findElement(By.xpath(""));
	}*/

	/*public void ClickByindexXpath(WebDriver driver, String Xpath, int index, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
		
		try{
			
			FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(20, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
			
			waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
			
			Thread.sleep(3000);
			driver.findElement(By.xpath(Xpath)).click();
		   
			//reportstep(input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
		}catch(NoSuchElementException e){
			e.printStackTrace();
			//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
			//System.out.println(e.getMessage());
		}catch (WebDriverException e){
			
			e.printStackTrace();
			//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
			//System.out.println(e.getMessage());
		}
	}*/
	    
	public void ClickByCSS(WebDriver driver, String css,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	    	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	    		            .withTimeout(60, TimeUnit.SECONDS)
	    		            .pollingEvery(10, TimeUnit.SECONDS)
	    		            .ignoring(NoSuchElementException.class);
	    		
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	    		driver.findElement(By.cssSelector(css)).click();
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    	}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }

	public void ClickByName(WebDriver driver, String name,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
		
		try{
			
			FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(20, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
			
			waitforElement.until(ExpectedConditions.elementToBeClickable(By.name(name)));
			driver.findElement(By.name(name)).click();
			reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
		}catch(NoSuchElementException e){
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}catch (WebDriverException e){
			
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}
	}

	    
	public void entervalueByID(WebDriver driver, String ID, String value,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	    	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		//waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(ID)));    commented
	    		driver.findElement(By.id(ID)).clear();
	    		driver.findElement(By.id(ID)).sendKeys(value);
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    	}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	 public void entervalueByName(WebDriver driver, String name, String value,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	    	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.name(name)));
	    		driver.findElement(By.name(name)).clear();
	    		driver.findElement(By.name(name)).sendKeys(value);
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    	}catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	 
	     public void selectDropdownByID(WebDriver driver, String id,String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	 
	    	 try{
	     		
	     		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	 		            .withTimeout(60, TimeUnit.SECONDS)
	 		            .pollingEvery(10, TimeUnit.SECONDS)
	 		            .ignoring(NoSuchElementException.class);
	     		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	     		WebElement element = driver.findElement(By.id(id));
	     		Select dropdownElement = new Select(element);
	     		Thread.sleep(4000);
	     		dropdownElement.selectByVisibleText(text);
	     		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	     	}catch(NoSuchElementException e){
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}catch (WebDriverException e){
	 			
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}
	     }
	     
	     
	public void selectDropdownByID_checkOPtionexist(WebDriver driver, String id,String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	 
	    	 try{
	     		
	     		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	 		            .withTimeout(60, TimeUnit.SECONDS)
	 		            .pollingEvery(10, TimeUnit.SECONDS)
	 		            .ignoring(NoSuchElementException.class);
	     		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	     		WebElement element = driver.findElement(By.id(id));
	     		Select dropdownElement = new Select(element);
	     		Thread.sleep(4000);
	     		//Assert.assertEquals(text, getSelectedOption(element), "Selected Value not displaying");
	     		List<WebElement> drop_List=dropdownElement.getOptions();
	     		System.out.println("List Hold options");
	     		for(WebElement option : drop_List){
	     			System.out.println(option.getText());
	     	        if(option.getText().equals(text)) {
	     	            option.click();
	     	            break;
	     	        	
	     	        }
	     		}
	     		
	     		
	     		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	     	}catch(NoSuchElementException e){
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}catch (WebDriverException e){
	 			
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}
	    	 
	     }
	     

	     
	public void selectDropdownByxpath(WebDriver driver, String xpath,String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	 
	    	 try{
	     		
	     		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	 		            .withTimeout(80, TimeUnit.SECONDS)
	 		            .pollingEvery(10, TimeUnit.SECONDS)
	 		            .ignoring(NoSuchElementException.class);
	     		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	     		WebElement element = driver.findElement(By.xpath(xpath));
	     		Select dropdownElement = new Select(element);
	     		Thread.sleep(3000);
	     		dropdownElement.selectByVisibleText(text);
	     		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	     	}catch(NoSuchElementException e){
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}catch (WebDriverException e){
	 			
	 			e.printStackTrace();
	 			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			//System.out.println(e.getMessage());
	 		}
	     }
	public void selectDropdownByIndexxpath(WebDriver driver, String xpath,int index, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
   	 
   	 try{
    		
    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
		            .withTimeout(80, TimeUnit.SECONDS)
		            .pollingEvery(10, TimeUnit.SECONDS)
		            .ignoring(NoSuchElementException.class);
    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    		WebElement element = driver.findElement(By.xpath(xpath));
    		Select dropdownElement = new Select(element);
    		Thread.sleep(3000);
    		dropdownElement.selectByIndex(index);
    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
    	}catch(NoSuchElementException e){
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}catch (WebDriverException e){
			
			e.printStackTrace();
			reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
			//System.out.println(e.getMessage());
		}
    }
	   
	    public void entervalueByXpath(WebDriver driver, String xpath, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    		
	    		//driver.findElement(By.xpath(xpath)).clear();but sir we have to enter the value in that xpth
	    		
	    		driver.findElement(By.xpath(xpath)).sendKeys(value);
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	    
	    public void entervalueByXpath(WebDriver driver, String xpath, int index, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    		
	    		List<WebElement> o_note_col = driver.findElements(By.xpath(xpath));
	    		
				o_note_col.get(index).sendKeys(value);
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	   /* public void entevalueByXpath(WebDriver driver, String xpath,String value){
	    	
	try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    		
	    		List<WebElement> o_note_col = driver.findElements(By.xpath(xpath));
	    		
				o_note_col.get(0).sendKeys(value);
	    		//reportstep(input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
				System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
				//System.out.println(e.getMessage());
			}
	    }*/
	    
	    
	public void entervalueBycss(WebDriver driver, String css, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	    		
	    		//driver.findElement(By.cssSelector(css)).clear();
	    		
	    		driver.findElement(By.cssSelector(css)).sendKeys(value);
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	    void SelectCheckboxbyDropdown(WebDriver driver1 , String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) 
	    {
			
	        try{
	              List<WebElement> Value = driver1.findElements(By.className("checkbox"));
	              java.util.Iterator<WebElement> itera = Value.iterator();
	                    while(itera.hasNext()){
	                          WebElement ele = itera.next();
	                                if(ele.getText().equals(text)){
	                                      ele.findElement(By.tagName("input")).click();
	                                      break;
	                                }
	                    }
	                 reportstep(driver1,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver1,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				System.out.println(e.getMessage());
			}catch (WebDriverException e){
				reportstep(driver1,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				System.out.println(e.getMessage());
				e.printStackTrace();
				//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
				//System.out.println(e.getMessage());
			}
	    }
	    public void CheckboxbyID(WebDriver driver, String id, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	    		
	    		driver.findElement(By.id(id)).click();
	    		
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	    
	    
	    public  WebElement findByXpath(WebDriver driver, String xpath)
	    {
	    	WebElement element = driver.findElement(By.xpath(xpath));
	    	if(element!=null)
	    	{
	    		return element;
	    	}
	    	else
	    	{
	    		reportstep(driver,"", "", "FAILED", "", "","Y");
	    	}
	    		return null;
	    }
	    public String JS_Element_Find(WebDriver driver, String xpath)
	    {
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	WebElement element = driver.findElement(By.xpath(xpath));
	    	if(element!=null)
	    	{
	    		js.executeScript("arguments[0].scrollIntoView();", element);
	    		return "true";
	    	}
	    	else
	    	{
	    		reportstep(driver,"", "", "FAILED", "", "","Y");
	    	}
	    	return null;
	    	
	    }
	   public String elementDisplayed(WebDriver driver, String xpath, String Description) throws StaleElementReferenceException
	   {
		   try
		   {
			   FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			  WebElement element = driver.findElement(By.xpath(xpath));
			   System.out.println(element.isDisplayed());
			   if(element.isDisplayed())
	    		
			   {
				   Logs_DigiSurvey.info(Description);
				   return "true";
			   }
		   }
		   catch(NoSuchElementException e)
		   {
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", "", "","Y");
				Logs_DigiSurvey.info("element is not displayed on web page");
				return "false";
			}
		   catch (WebDriverException e)
		   {
				
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", "", "","Y");
				Logs_DigiSurvey.info("element is not displayed on web page");
				return "false";
			}
		   
		   return "false";
	   }
	   public String elementEnabled(WebDriver driver, String xpath, String Description) throws StaleElementReferenceException
	   {
		   try
		   {
			   FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			   WebElement element = driver.findElement(By.xpath(xpath));
			   System.out.println(element.isEnabled());
			   if(element.isEnabled())
			   {
				   Logs_DigiSurvey.info(Description);
				   return "true";
			   }
		   }
		   catch(NoSuchElementException e)
		   {
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", "", "","Y");
				//System.out.println(e.getMessage());
				Logs_DigiSurvey.info("element is not enabled on web page");
				return "false";
			}
		   catch (WebDriverException e)
		   {
				
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", "", "","Y");
				Logs_DigiSurvey.info("element is not enabled on web page");
				//reportstep(input, Description, "FAIL
				return "false";
			}
		   
		   return "false";
	   }
	   
	   public String elementSelected(WebDriver driver, String xpath)
	   {
		   try
		   {
			   FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			   WebElement element = driver.findElement(By.xpath(xpath));
			   System.out.println(element.isSelected());
			   if(element.isSelected())
			   {
				   return "true";
			   }
		   }
		   catch(NoSuchElementException e){
				//e.printStackTrace();
				reportstep(driver,"", "Element Not Selected", "FAILED", "", "","Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}
		   catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,"", "Element Not Selected", "FAILED", "", "","Y");
				Logs_DigiSurvey.info(e.getMessage());
				//reportstep(input, Description, "FAILED", ExpectedResult, ActualResult,Screenshot);
				//System.out.println(e.getMessage());
			}
		   return "false";
	   }
	    
	    public void checkboxByxpath(WebDriver driver, String xpath, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot) throws InterruptedException{
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    		Thread.sleep(3000);
	    		driver.findElement(By.xpath(xpath)).click();
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				//System.out.println(e.getMessage());
			}
	    }
	    
	public void RedioButtonByID(WebDriver driver, String id, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	    		
	    		driver.findElement(By.id(id)).click();
	    		
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	    		
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}
	    }
	    
	    public void RediobuttonByxpath(WebDriver driver, String xpath, String value, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    		
	    		driver.findElement(By.xpath(xpath)).click();
	    		reportstep(driver,input, Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}catch (WebDriverException e){
				
				e.printStackTrace();
				reportstep(driver,input, Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}
	    }
	    
	    public void assertextbyID(WebDriver driver, String locator, String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
	    		String vText = driver.findElement(By.id(locator)).getText();
	    		Assert.assertEquals(vText.contains(text), true);
	    		reportstep(driver,"", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}
	    }
	public void assertextbyXPATH(WebDriver driver, String locator, String text, String input, String Description, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	        try{
	    		
	    		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
			            .withTimeout(60, TimeUnit.SECONDS)
			            .pollingEvery(10, TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
	    		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	    		
	    		String vText = driver.findElement(By.xpath(locator)).getText();
	    		Assert.assertEquals(vText.contains(text), true);
	    		reportstep(driver,"", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	        }catch(NoSuchElementException e){
				e.printStackTrace();
				reportstep(driver,"", Description, "FAILED", ExpectedResult, ActualResult,"Y");
				Logs_DigiSurvey.info(e.getMessage());
				//System.out.println(e.getMessage());
			}
	    }
	   
	    public String getTextXPATH(WebDriver driver, String locator,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot)
	    {
	    	 try{
	     		
	     		FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	 		            .withTimeout(60, TimeUnit.SECONDS)
	 		            .pollingEvery(10, TimeUnit.SECONDS)
	 		            .ignoring(NoSuchElementException.class);
	     		waitforElement.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	     		reportstep(driver,"", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	     		String vText = driver.findElement(By.xpath(locator)).getText();
	     		return vText;
	     		//Assert.assertEquals(vText.contains(text), true);
	     		//reportstep("", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	         }catch(NoSuchElementException e){
	 			e.printStackTrace();
	 			reportstep(driver,"", Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			Logs_DigiSurvey.info(e.getMessage());
	 			//System.out.println(e.getMessage());
	 		}
			return "false";
	    	
	    }
	    public String getTextXPATH_WithoutClick(WebDriver driver, String locator,  String input, String Description, String ExpectedResult, String ActualResult, String Screenshot)
	    {
	    	 try{
	     		
	     		/*FluentWait<WebDriver> waitforElement = new FluentWait<WebDriver>(driver)
	 		            .withTimeout(60, TimeUnit.SECONDS)
	 		            .pollingEvery(10, TimeUnit.SECONDS)
	 		            .ignoring(NoSuchElementException.class);*/
	    		 reportstep(driver,"", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	     		String vText = driver.findElement(By.xpath(locator)).getText();
	     		return vText;
	     		//Assert.assertEquals(vText.contains(text), true);
	     		//reportstep("", Description, "SUCCESS", ExpectedResult, ActualResult,Screenshot);
	         }catch(NoSuchElementException e){
	 			e.printStackTrace();
	 			reportstep(driver,"", Description, "FAILED", ExpectedResult, ActualResult,"Y");
	 			Logs_DigiSurvey.info(e.getMessage());
	 			//System.out.println(e.getMessage());
	 		}
			return null;
	    	
	    }
	    
	    //issue resolved?
	  
	    public static void reportstep(WebDriver driver,String input, String Description,String Status, String ExpectedResult, String ActualResult, String Screenshot){
	    	
	    	//ATUReports.setWebDriver(driver);
	    	//System.setProperty("atu.reporter.config", "lib\\atu.properties");//D:\\Sailaja\\ATUReporter_Selenium_testNG_5.5 BETA + ATU Recorder 2.1+javadoc+propfile\\atu.properties
	    	//System.out.println(Status);
	    	//System.out.println(Screenshot+" Expected to be Y");
	    	if(Status.toUpperCase().equals("SUCCESS") && Screenshot.toUpperCase().equals("Y"))
	    	{
	    		//ATUReports.add("pass step 1", LogAs.PASSED, new CaptureScreen(ScreenshotOf.Desktop));
	    		ATUReports.add(Description, input, ExpectedResult, ActualResult, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	    		
	    	}else if(Status.toUpperCase().equals("SUCCESS") && Screenshot.toUpperCase().equals("")) // where is this clss?
	    	{
	    		//ATUReports.add("pass step 1", LogAs.PASSED, new CaptureScreen(ScreenshotOf.Desktop));can't have
	    		ATUReports.add(Description, input, ExpectedResult, ActualResult, LogAs.PASSED, null);
	    	}if(Status.toUpperCase().equals("FAILED") && Screenshot.toUpperCase().equals("Y"))
	    	{
	    	
	    		//ATUReports.add("pass step 1", LogAs.PASSED, new CaptureScreen(ScreenshotOf.Desktop));
	    		ATUReports.add(Description, input, ExpectedResult, "Error Occured: Please Check Logs", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	    	}else if(Status.toUpperCase().equals("FAILED") && Screenshot.toUpperCase().equals(""))
	    	{
	    		//ATUReports.add("pass step 1", LogAs.PASSED, new CaptureScreen(ScreenshotOf.Desktop));
	    		ATUReports.add(Description, input, ExpectedResult, "Error Occured: Please Check Logs", LogAs.FAILED, null);
	    	}
	    	
	    	
	    }
	    
	    public String checkOptionValueInSelect(WebDriver driver,String selecttag_Xpath, String selectOptions_Xpath ,String SearchText) throws InterruptedException
	    {
	    	WebElement selecttag;
			try 
			{
				selecttag = driver.findElement(By.xpath(selecttag_Xpath));
			
				Thread.sleep(3000);
				
				selecttag.click();
				
				Thread.sleep(3000);
			
				List<WebElement> selecttag_opitons = driver.findElements(By.xpath(selectOptions_Xpath));
				
				
				Thread.sleep(3000);
			
				int select_index;
				
				for(select_index=0;select_index<selecttag_opitons.size();select_index++)
				{
					if(selecttag_opitons.get(select_index).getText().equals(SearchText))
					{
						return "true";
					}
								
				}
				if(select_index==selecttag_opitons.size())
				{
					return "false";
				}
				
			}
			catch (NullPointerException e) 
			{
				reportstep(driver, "", "error occured in dropdown cheking element", "", "", "", "Y");
				Logs_DigiSurvey.info(e.getMessage());
				e.printStackTrace();
			}
			return null;
			
	    }

}
