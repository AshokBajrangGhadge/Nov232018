package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ExcelReaderCls.ExcelSheetReader;
import MasterClsPkg.MasterActions;
import MasterClsPkg.executionReport;
import MasterClsPkg.screnshotcls;

public class ECGMS 
{

	public static String TestCaseName;
	public static int TestCaseStapeNo;
	public static String TestCaseStapeStatus="Pass";
	public String IsNeedToRun;
	public static String TestCasePriority;
	public static int ExecutionTestCaseNo;
	public static String TestCaseTitle;
	public static String FailedError;
	public static String parentWindowHandleq;
	//public static String parentWindowHandleq;
	public static void main(String[] args) throws Exception 
	{
		try
		{
		
		
		String WorkbookPath="../ECGManagerAuto/Workbook/HV_TestCases_Selenium.xlsx";
	    // String WorkbookPath="./Workbook/HV_TestCases_Selenium.xlsx";
		//Create object of ExcelSheetReader to get sheet
		ExcelSheetReader workbook1=new ExcelSheetReader();
		Sheet sh1=workbook1.getSheetName(WorkbookPath,"TestCaseSummry");
		Row r1;
		int k=sh1.getLastRowNum()-sh1.getFirstRowNum();
		
		
		//To get total number of test cases to be execute.. Set as 0
		ExecutionTestCaseNo=0;
		
		//Code to get total number of Test Cases To be executing. Get total test case count which to be execute i.e. Y value test cases.
		for (int i = 0; i <= k; i++) 
		{
			ECGMS ecgms1=new ECGMS();
			r1=sh1.getRow(i);
		
			
			ecgms1.IsNeedToRun=r1.getCell(1).toString();
			
			if(ecgms1.IsNeedToRun.equalsIgnoreCase("Y"))
			{
				ExecutionTestCaseNo=ExecutionTestCaseNo+1;
			}
			
		}
		
		System.out.println("Execution Test Case No  ::"+ECGMS.ExecutionTestCaseNo);
		//Above code give test case count(i.e ECGMS.ExecutionTestCaseNo) which to be execute
		
		
		//Code to start execution of test case one by one.
		for (int l = 1; l <=k; l++) 
		{
			int stepno=1;
			
			ECGMS ecgms=new ECGMS();
			
			//load row of "TestCaseSummry" sheet into r1
			r1=sh1.getRow(l);
		
			//take cell value in to "ecgms.IsNeedToRun" static string variable.
			ecgms.IsNeedToRun=r1.getCell(1).toString();
			
			//Check whether "ecgms.IsNeedToRun" is having value "Y" or not
			if(ecgms.IsNeedToRun.equalsIgnoreCase("Y"))
			{
				//Store Current Test Case Name from "TestCaseSummry" sheet into "ecgms.TestCaseName" beacuse its related value is "Y"
				ECGMS.TestCaseName=r1.getCell(0).toString();
				System.out.println(ecgms.IsNeedToRun+"------------"+ECGMS.TestCaseName);
				
				//System.setProperty("webdriver.ie.driver", "../ECGManagerAuto/IEDirver/3.7.0/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", "../ECGManagerAuto/IEDirver/IEDriverServer.exe");
				//System.setProperty("webdriver.ie.driver", "./IEDirver/IEDriverServer.exe");
				//System.setProperty("webdriver.ie.driver", "C:\\java-neon\\ECGManagerAuto\\Used\\IEDirver\\32\\IEDriverServer.exe");
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				WebDriver webDriver = new InternetExplorerDriver(caps); 
				//System.setProperty("webdriver.gecko.driver", "..\\ECGManagerAuto\\Used\\FFox\\geckodriver.exe");
				//DesiredCapabilities caps = DesiredCapabilities.firefox();
			
				//WebDriver webDriver = new InternetExplorerDriver(caps);
				//WebDriver webDriver=new FirefoxDriver();
							
				 
				//Create object of ExcelSheetReader to get sheet
					ExcelSheetReader workbook=new ExcelSheetReader();
				//Loaded "Login" sheet to sh	
					Sheet sh=workbook.getSheetName(WorkbookPath,"ECGMSTC");
					Row r;
					int j=sh.getLastRowNum()-sh.getFirstRowNum();
					MasterActions obj=new MasterActions(webDriver);
					
					System.out.println("Start");
					
					try
					{
						
						
						for(int i=1;i<=j;i++)
							
							
						{
							
							
							MasterActions.TestCasePriorityRep=false;
							ECGMS.TestCaseStapeNo=stepno;
							r=sh.getRow(i);
							//boolean b=r.getCell(0).toString().equalsIgnoreCase(ECGMS.TestCaseName);
							//new code
							ECGMS.TestCasePriority=r.getCell(9).toString();
							
							System.out.println("Runung or not : "+r.getCell(0).toString()+" "+ECGMS.TestCaseName+"  "+r.getCell(0).toString().equalsIgnoreCase(ECGMS.TestCaseName));
							
							//Check same test case step is execution
							if(r.getCell(0).toString().equalsIgnoreCase(ECGMS.TestCaseName))
							{
								System.out.println("Start : "+i);
								
								
								System.out.print(r.getCell(4)+"-------"+r.getCell(6)+"--------"+r.getCell(7)+"-------"+r.getCell(8)+"--------"+r.getCell(9)+"--------"+r.getCell(10));
								
								System.out.println();
								
								if(r.getCell(10).toString().equalsIgnoreCase("N"));
									{
										screnshotcls.screshotpath="";
									}
							
								//Code to call MasterAction method.
								obj.myAction(r.getCell(4).toString(), r.getCell(6).toString(),r.getCell(7).toString(), r.getCell(8).toString(),r.getCell(10).toString());
								
								
								//Code to call report function//Need durag's code
								
								System.out.println("Test Case Name : "+ECGMS.TestCaseName+"   ::  Test Case Step no. is : "+ECGMS.TestCaseStapeNo+  "  :: Test Case Stape Status : "+ECGMS.TestCaseStapeStatus + " :: Message "+ MasterActions.ExceptionMessage.toString()+"  :: screnshotc "+screnshotcls.screshotpath+ "Failed Details  :: "+MasterActions.ExceptionMessage);
								//Write Report
								String ReportFile="../ECGManagerAuto/ExecutionReport/Report.xlsx";
								//String ReportFile="./ExecutionReport/Report.xlsx";
								executionReport.executionReportDetails(ReportFile, i);
							    
								//Code to get Test Case Title Name
								if(stepno==1)
									{
										ECGMS.TestCaseTitle=r.getCell(1).toString();
										System.out.println("Test Case Title Name is :: "+ECGMS.TestCaseTitle);
									}
							
								//Code to Decide stop test case execution based on Priority of test step
									if(MasterActions.TestCasePriorityRep==true)
									break;
									
									stepno=stepno+1;
								}
							
							}
						
					}catch(NoSuchElementException e)
						{
							
							 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
							 System.out.println("Test Case Exception "+e.getMessage());
						}
						System.out.println("Done");
			
					
							
					
			}//1st if
			
		 }	//master for
		}catch(Exception e)
		{
			System.out.println(e);
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		}

	}

}
