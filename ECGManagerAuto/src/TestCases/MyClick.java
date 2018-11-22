package TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyClick 
{
	
	public static void myClick(String LocatorType,String LocatorValue,String action,WebDriver driver) throws InterruptedException
	{
		try 
		{
			
		driver.findElement(By.id(LocatorValue)).click();
			
		} catch (Exception e) 
		{
		
			Thread.sleep(1000);
			List<WebElement> allmyelement = driver.findElements(By.tagName("iframe"));
			
			if (allmyelement.size() > 0) 
			{
				boolean present = false;
				String iframename = null;

				for (int i = 0; i <= allmyelement.size() - 1; i++)

				{
					iframename = allmyelement.get(i).getAttribute("id").toString();
					System.out.println("Loop no "+i+"         Frame name is "+iframename);
					String parentWindowHandle1 = driver.getWindowHandle();
					if (!(allmyelement.get(i).getAttribute("id").isEmpty())) 
					{
						driver.switchTo().frame(allmyelement.get(i).getAttribute("id"));
						Thread.sleep(1000);
						{
							try 
							{
								driver.findElement(By.id(LocatorValue));
								present = true;
								
							} catch (Exception eg) 
							{
								present = false;
							}

							try {

								if (present) 
								{
									if(LocatorType.equalsIgnoreCase("xpath") && action.equalsIgnoreCase("click") )
									{
									driver.findElement(By.xpath(LocatorValue)).click();
									driver.switchTo().window(parentWindowHandle1);
									System.out.println("Frame name is "+iframename);
									break;
									}
									if(LocatorType.equalsIgnoreCase("id") && action.equalsIgnoreCase("click"))
									{
									driver.findElement(By.id(LocatorValue)).click();
									driver.switchTo().window(parentWindowHandle1);
									System.out.println("Frame name is "+iframename);
									break;
									}
									if(LocatorType.equalsIgnoreCase("xpath") && action.equalsIgnoreCase("send"))
									{
									driver.findElement(By.xpath(LocatorValue)).sendKeys("AshokMyData");
									driver.switchTo().window(parentWindowHandle1);
									System.out.println("Frame name is "+iframename);
									break;
									}
									if(LocatorType.equalsIgnoreCase("id") && action.equalsIgnoreCase("send"))
									{
									driver.findElement(By.id(LocatorValue)).sendKeys("AshokMyData");
									driver.switchTo().window(parentWindowHandle1);
									System.out.println("Frame name is "+iframename);
									break;
									}
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
		
	}

}
