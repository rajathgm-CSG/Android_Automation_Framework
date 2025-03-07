package Railway_App.testComponents;

import Railway_App.componentGroups.ReusableLibrary;
import io.appium.java_client.android.AndroidDriver;

public class TestSetup {

	public AndroidDriver driver;
	
	public BaseTest baseTest;	
	public PageObjectManager pageObjectManager;
	
	public ReusableLibrary reusableLibrary;
	public ExcelFileReader excelReader;

	
	public TestSetup() throws Exception {
		baseTest = new BaseTest();
		
		driver = baseTest.intializeAndroidDriver();
		
		pageObjectManager = new PageObjectManager(driver);
		reusableLibrary = new ReusableLibrary(driver);
		excelReader = new ExcelFileReader();
		
	}
}
