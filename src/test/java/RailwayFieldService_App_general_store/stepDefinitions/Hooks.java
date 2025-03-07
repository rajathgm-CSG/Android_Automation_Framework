package RailwayFieldService_App_general_store.stepDefinitions;


import java.io.IOException;

import org.testng.asserts.SoftAssert;

import Railway_App.testComponents.BaseTest;
//import DemoWebShop.testcomponents.BaseTest;
//import DemoWebShop.testcomponents.ReadXL;
//import DemoWebShop.testcomponents.TestSetup;
//import DemoWebShop.testcomponents.VideoRecorder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

	public Hooks() throws IOException{
		super();
	}
	
	@Before
	public void TestConfigSetup(Scenario scenario) throws Exception {
		
		this.scenario = scenario;
		test = extent.createTest(scenario.getName());
	    logInfo.set(test);
	    softAssert = new SoftAssert();
	    scenarioName = scenario.getName();
	    String []module =scenario.getName().split(" ");
	    test.assignCategory(module[0]);
	    
//	    if(prop.get("recording_enabled").equals("Y")){
//	    	try {
//				VideoRecorder.startRecording(scenarioName);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (AWTException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
	    
	}
	@After
	public void teardown(Scenario scenario) throws InterruptedException {
		softAssert.assertAll();
//		if(prop.get("recording_enabled").equals("Y")){
//			try {
//				
//				VideoRecorder.stopRecording();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
	

