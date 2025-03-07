package RailwayFieldService_App_general_store.stepDefinitions;

import java.io.IOException;
import java.util.Map;

import Railway_App.testComponents.BaseTest;
import Railway_App.testComponents.ExcelFileReader;
import Railway_App.testComponents.TestSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class CommonSteps extends BaseTest {

	TestSetup testSetup;

	public CommonSteps(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}


	
	
	@Then("I close the application")
	public void i_close_the_app_application() {
		try {
			test = logInfo.get().createNode("I close the application");
			
			Map<String, String> testData = ExcelFileReader.getDataInMap("Device_setup", "RFS_01");
			reportInfo(testData.toString());
			
			String currentActivity = testSetup.pageObjectManager.getSetUpDeviceAndApplicationPage().closeApp();
			
			reportScreenshot(testSetup.driver);
			
			if(currentActivity!=testData.get("App_activity_Name"))
				reportPass("App activity status", "App closed successfully", "App closed successfully");
			else
				reportFail("App activity status", "App closed successfully", "App Not closed successfully");
			
			testSetup.pageObjectManager.getSetUpDeviceAndApplicationPage().quiteAndroidDriver();
			
			//service.stop();
		}
		catch(Exception e)
		{
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
	}


}
