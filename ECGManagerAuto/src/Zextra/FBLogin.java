package Zextra;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FBLogin 
{

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		try
		{
		System.setProperty("webdriver.ie.driver", "../ECGManagerAuto/IEDirver/IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		WebDriver driver = new InternetExplorerDriver(caps); 
		
		driver.get("https://en-gb.facebook.com/");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("test");
		
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("test");
		
		driver.findElement(By.xpath(".//*[@id='u_0_q']")).click();
		Thread.sleep(5000);
		
		driver.close();
		driver.quit();
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		
	}catch(Exception e)
	{
		System.out.println(e);
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
	}
		

	}

}
