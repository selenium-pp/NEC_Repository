package NEC_Automation;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import nec_automation_main.UserFunctions;

public class Ebay_Test extends UserFunctions{


	@BeforeSuite
	public void ebay_Initialization() throws IOException{
		
		//Initializing the Properties object
		ebay_SetUp();
	
	}
	
	@Test
	public void ebay_Scenario1() throws IOException{
		
		//Open the IE Browser
		ebay_OpenBrowser();
		
		//Open the Ebay web application
		ebay_OpenApplication();
		
		//Select the shoes from Fashion Store
		ebay_SelectShoes();
		
		//Select the Featured Stores
		ebay_SelectFeaturedStore();
		
		//Search for the specified item
		ebay_SearchItem();
		
		//Select the items from the list
		ebay_SelectItem();
		
		//Validates the data for the specified item			
		ebay_VerifyPostage();
		
		//To Close the Browser Session
		ebay_CloseBrowser();
		
	}
	
	@Test
	public void Scenario2(){
		//Open the IE Browser
		ebay_OpenBrowser();
		
		//Open the Ebay web application
		ebay_OpenApplication();
		
	}
}
