package RailwayFieldService_App_general_store.TestRunners;



import java.awt.AWTException;

import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\RailwayFieldService_App_general_store\\features",  tags = "@TC_001 or @TC_002", glue = "RailwayFieldService_App_general_store/stepDefinitions", monochrome = true,plugin = {"html:target/cucumber.html"})
public class Appium_UI_Runner  extends AbstractTestNGCucumberTests{

	@BeforeClass
	public void beforeCalss() throws AWTException {

//		System.out.println("Runner executing . . .");

	}

}
