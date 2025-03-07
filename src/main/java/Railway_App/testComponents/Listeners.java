package Railway_App.testComponents;
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

//import playarea.ZAPSecurityTest;


public class Listeners extends BaseTest implements ITestListener{


	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static ExtentReports extent;

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

		System.out.println(scenarioName +" : PASS");
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", scenarioName, rowcount, 1);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", "PASS", rowcount, 2);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 3);
		rowcount++;
	}
//commented 30-01
	public void onTestFailure(ITestResult result) {

	System.out.println(scenarioName +" : FAIL");
//		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", scenarioName, rowcount, 1);
//		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", "FAIL", rowcount, 2);
//		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 3);
//		rowcount++;
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		startTime =  getTimeStamp();
		SuperTestNG.extent= setUp();

		startServerAutomatically();
	}

	public void onFinish(ITestContext context) {
		endTime =  getTimeStamp();

		try {
			duration = getDuration(startTime, endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SuperTestNG.extent.flush();


		System.out.println("Toal : "+SuperTestNG.extent.getStats().getParentCount()+" Pass : "+SuperTestNG.extent.getStats().getParentCountPass()+" Fail : "+SuperTestNG.extent.getStats().getParentCountFail()+" Skip : "+SuperTestNG.extent.getStats().getParentCountSkip());

		System.out.println("Server stopping now . . .");
		service.stop();
	}


}
