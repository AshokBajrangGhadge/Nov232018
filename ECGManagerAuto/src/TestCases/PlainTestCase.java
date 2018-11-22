package TestCases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PlainTestCase {

	static public WebDriver driver;

	public static void main(String[] args) throws IOException {
		try {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");

			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

			System.setProperty("webdriver.ie.driver", "../ECGManagerAuto/IEDirver/IEDriverServer.exe");

			driver = new InternetExplorerDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://localhost/HealthView/Login.aspx");
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='txtUserName']")).sendKeys("appapollo");
			driver.findElement(By.xpath(".//*[@id='txtPassword']")).sendKeys("dna");
			driver.findElement(By.xpath(".//*[@id='btnLogin']")).click();
			Thread.sleep(5000);

			driver.findElement(By.id("ctl00_cpb_gridCallbackPanel_gridFilterData_DXDataRow0")).click();
			Thread.sleep(3000);
			driver.switchTo().frame("worklistViewFrame");
			Thread.sleep(2000);
			driver.findElement(By.id("lnkEditAdtMismatch")).click();
			
			
			//MyClick.myClick("id","lnkEditAdtMismatch","click", driver);
			
			
//			Thread.sleep(5000);
//			MyClick.myClick("id","txtMRNMismatchMRN","send", driver);
//			Thread.sleep(5000);
			
			try {
				System.out.println("Trying caliper");
				driver.findElement(By.id("txtMRNMismatchMRN")).click();
				System.out.println("done Trying caliper");
			} catch (Exception e) {
				System.out.println("in catch");
				Thread.sleep(5000);
				List<WebElement> allmyelement = driver.findElements(By.tagName("iframe"));
				System.out.println(allmyelement.size());
				if (allmyelement.size() > 0) {
					boolean present = false;
					System.out.println("in if(allmyelement.size()>0)");
					for (int i1 = 0; i1 <= allmyelement.size() - 1; i1++) {
						// System.out.println("in FOOR");
						System.out.println("farme :" + i1 + "  :  : " + allmyelement.get(i1).getAttribute("id"));
					}

					String iframename = null;
					// =====================================2 nd for
					// =======================================

					// for(int i=0;i<=allmyelement.size()-1;i++)
					for (int i = allmyelement.size() - 1; i >= 0; i--) {
						String parentWindowHandle1 = driver.getWindowHandle();
						System.out.println(i + "    :  " + "in FOOR");
						System.out.println("farme :" + i + "  :  : " + allmyelement.get(i).getAttribute("id")); // allelements.get(i).getAttribute("id"));
						iframename = allmyelement.get(i).getAttribute("id").toString();
						if (!(allmyelement.get(i).getAttribute("id").isEmpty())) {
							System.out.println("Valus is not Empty");
							driver.switchTo().frame(allmyelement.get(i).getAttribute("id"));
							System.out.println("After switch to frame");
							Thread.sleep(2000);
							{

								try {
									driver.findElement(By.id("txtMRNMismatchMRN"));
									present = true;
									System.out.println("exist hai bol raha hai");
								} catch (Exception eg) {
									System.out.println("True False=False");
									present = false;
									System.out.println("exist NAHI hai bol raha hai");
								}

								try {
									// if(driver.findElement(By.id("btnCaliperCurrent")).isDisplayed()||driver.findElement(By.id("btnCaliperCurrent")).isEnabled()||driver.findElement(By.id("btnCaliperCurrent")).isSelected())
									if (present) {
										System.out.println("in IF Before caliper click an dframe is " + iframename);
										driver.findElement(By.id("txtMRNMismatchMRN")).sendKeys("AhokGXXX");
										System.out.println("Returnning To Parent");
										driver.switchTo().window(parentWindowHandle1);
										System.out.println("Returned To Parent");
										System.out.println("I am btnCaliperCurrent Present in frame " + iframename);
										break;
									}
								} catch (Exception v) {
									System.out.println("chalo next step check karo " + v);
								}
							}
						} else {
							System.out.println("Valus is Empty");
						}

						System.out.println("Returnning To Parent");
						driver.switchTo().window(parentWindowHandle1);
						System.out.println("Returned To Parent");
					}
				} else {
					System.out.println("Main if");
				}
			}

			System.out.println("Done.............Ashok");
			Thread.sleep(7000);
			// driver.get(url);

			Thread.sleep(7000);
			System.out.println("End process...................................................");
			driver.close();
			driver.quit();
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		} catch (Exception e) {

			System.out.println("msg : " + e);
			driver.close();
			driver.quit();
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		}

	}

}
