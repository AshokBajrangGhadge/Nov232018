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

public class AutoElementSearch {

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

			driver.get("http://10.75.0.190/HealthView/Login.aspx");
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='txtUserName']")).sendKeys("appapollo");
			driver.findElement(By.xpath(".//*[@id='txtPassword']")).sendKeys("dna");
			driver.findElement(By.xpath(".//*[@id='btnLogin']")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("ctl00_cpb_gridCallbackPanel_gridFilterData_DXDataRow0")).click();
			Thread.sleep(5000);

			try 
			{
				driver.findElement(By.id("btnCaliperCurrent")).click();
				
			} catch (Exception e) 
			{
			
				Thread.sleep(5000);
				List<WebElement> allmyelement = driver.findElements(By.tagName("iframe"));
				
				if (allmyelement.size() > 0) 
				{
					boolean present = false;
					String iframename = null;

					for (int i = 0; i <= allmyelement.size() - 1; i++)

					{
						String parentWindowHandle1 = driver.getWindowHandle();
						iframename = allmyelement.get(i).getAttribute("id").toString();
						if (!(allmyelement.get(i).getAttribute("id").isEmpty())) {
							driver.switchTo().frame(allmyelement.get(i).getAttribute("id"));
							Thread.sleep(2000);
							{
								try 
								{
									driver.findElement(By.id("btnCaliperCurrent"));
									present = true;
									
								} catch (Exception eg) 
								{
									present = false;
								}

								try {

									if (present) 
									{
										driver.findElement(By.id("btnCaliperCurrent")).click();
										driver.switchTo().window(parentWindowHandle1);
										break;
									}
								} catch (Exception v) 
								{
									System.out.println("Exception is :: " + v);
								}

							}

						}

						// Returnning To Parent
						driver.switchTo().window(parentWindowHandle1);

					}
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
