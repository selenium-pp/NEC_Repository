package nec_automation_main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserFunctions extends GenericFunctions{
	
	// Declaring the global variables
	WebDriver driver;
	XLS_Reader xls = null;
	Properties projConfig = null;
	Properties objRep = null;
	FileInputStream fs_projConfig = null;
	FileInputStream fs_objRep = null;
	WebDriverWait wait = null;
	Actions act = null;
	String strMessage;
	
	public void ebay_SetUp() throws IOException{
		
	//Initializing the Properties object
		projConfig = new Properties();
		fs_projConfig = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\nec_automation_resources\\projectConfiguration.properties");
		projConfig.load(fs_projConfig);
		
		objRep = new Properties();
		fs_objRep = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\nec_automation_resources\\objectRepository.properties");
		objRep.load(fs_objRep);
		
		xls = new XLS_Reader(System.getProperty("user.dir")+"\\DataFile\\DataSheet.xlsx");
	
	}
	
	//Open the IE Browser
	//Input				:	void
	//Output			: 	Opens the IE Browser
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_OpenBrowser() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	//Open the Ebay web application
	//Input	  			:	void
	//Output   			: 	Opens the Ebay web application
	//Last Modified By  :	Prakash
	//Last Modified On  :	Nov 01, 2017
	
	public void ebay_OpenApplication(){
		
		driver.get(projConfig.getProperty("URL"));		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,20);
	
	}
	


	//Select the Shoes from Fashion section
	//Input				:	void
	//Output			: 	Select the Shoes from Fashion section
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_SelectShoes() {
		ebay_gf_MouseHoverObject(driver, objRep, "fashion_xpath");
		ebay_gf_ClickObject(driver, objRep, "shoes_xpath");		
	}
	
	//Goes to the Featured Store
	//Input				:	void
	//Output			: 	Go to the Myer Store for search
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_SelectFeaturedStore() {
		ebay_gf_EnterData(driver, objRep, "Searcheditbox_id", projConfig.getProperty("Featured_Store"));
		ebay_gf_ClickObject(driver, objRep, "Searchbtn_id");
		ebay_gf_ClickObject(driver, objRep, "Myerlink_xpath");
		
	}
	

	//Search the item
	//Input				:	void
	//Output			: 	Search the Headphones item
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_SearchItem() {
		ebay_gf_EnterData(driver, objRep, "Myersearch_xpath", projConfig.getProperty("item_name"));
		ebay_gf_ClickObject(driver, objRep, "Myersearchbtn_name");		
	}
	
	
	//Select the specified item
	//Input				:	void
	//Output			: 	Selects the specified headphone
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_SelectItem() {
		ebay_gf_ClickObject(driver, objRep, "Headphone_xpath");
		System.out.println(driver.findElement(By.xpath(".//*[@id='fshippingCost']/span")).getText());
	}

	//Validate the Postage
	//Input				:	void
	//Output			: 	Validates the postage fee
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	public void ebay_VerifyPostage() {
		String strPostage = driver.findElement(By.xpath(".//*[@id='fshippingCost']/span")).getText();
		if(strPostage.equals("AU $9.95")){
			strMessage = "Postage for the Headphones is as expected - AUD 9.95";
		}	
		else{
			strMessage = "Postage for the Headphones is not as expected - AUD 9.95";				
		}
		System.out.println(strMessage);
	}
	
	//To Register Users
	//Input			: void
	//Output		: All the browser sessions are closed
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	
	public void ebay_RegUser(){
		ebay_gf_ClickObject(driver, objRep, "reglink_id");
		
		String firstname_excel = xls.getCellData("TestUsers", "FirstName", 2);
		String lastname_excel = xls.getCellData("TestUsers", "LastName", 2);
		String email_excel = xls.getCellData("TestUsers", "EmailID", 2);
		String password_excel = xls.getCellData("TestUsers", "Password", 2);

		ebay_gf_EnterData(driver, objRep, "fname_id", firstname_excel);
		ebay_gf_EnterData(driver, objRep, "lname_id", lastname_excel);
		ebay_gf_EnterData(driver, objRep, "email_id", email_excel);
		ebay_gf_EnterData(driver, objRep, "pwd_id", password_excel);
		
		ebay_gf_ClickObject(driver, objRep, "regbtn_id");
		
	}

	//To Close the Browser Session
	//Input			: void
	//Output		: All the browser sessions are closed
	//Last Modified By	:	Prakash
	//Last Modified On	:	Nov 01, 2017
	
	public void ebay_CloseBrowser(){
		
		driver.quit();
		
	}
	

}
