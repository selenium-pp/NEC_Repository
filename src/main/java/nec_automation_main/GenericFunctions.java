package nec_automation_main;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GenericFunctions {
	//To enter a data in the text field
	//Input				:	WebDriver object, Properties object with the location of the Project configuration
	//						file, the locator for the element in the properties file and the data to be entered
	//Output			: 	Data will be entered in the corresponding text field
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_gf_EnterData(WebDriver driver, Properties prop, String locator, String data)
	{
		WebElement textField = ebay_gf_GetLocation(driver, prop, locator);
		textField.clear();
		textField.sendKeys(data);
	}
	
	//To click a button
		//Input				:	WebDriver object, Properties object with the location of the Project configuration
		//						file and the locator for the element in the properties file
		//Output			: 	Button will be clicked
		//Last Modified By	:	Prakash
		//Last Modified On	:	Nov 01, 2017
	public void ebay_gf_ClickObject(WebDriver driver, Properties prop, String locator)
	{
		WebElement object = ebay_gf_GetLocation(driver, prop, locator);
		object.click();	
	}

	
	//To click a button
	//Input				:	WebDriver object, Properties object with the location of the Project configuration
	//						file and the locator for the element in the properties file
	//Output			: 	Button will be clicked
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_gf_MouseHoverObject(WebDriver driver, Properties prop, String locator) 
	{
		Actions action = new Actions(driver);
		WebElement object = ebay_gf_GetLocation(driver, prop, locator);
		action.moveToElement(object).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//To get the location of an element
	//Input				:	WebDriver object, Properties object with the location of the Project configuration
	//						file and the locator for the element in the properties file
	//Output			: 	WebElement which is the location of the element
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public WebElement ebay_gf_GetLocation(WebDriver driver, Properties prop, String locator)
	{
		WebElement location = null;
		try{
			if(locator.endsWith("name"))
				location = driver.findElement(By.name(prop.getProperty(locator)));
			else if(locator.endsWith("xpath"))
				location = driver.findElement(By.xpath(prop.getProperty(locator)));
			else if(locator.endsWith("id"))
				location = driver.findElement(By.id(prop.getProperty(locator)));
		}catch(Exception e){
			location = null;
		}
		return location;
		
	}
	
	//To handle Javascript alerts
	//Input				:	WebDriver object
	//Output			: 	True if alert is present; False if alert is not present
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public boolean ebay_gf_HandleAlert(WebDriver driver){
		
		try{
			driver.switchTo().alert();
			return true;
		}catch(Throwable t){
			return false;
		}
		
	}
	

}
