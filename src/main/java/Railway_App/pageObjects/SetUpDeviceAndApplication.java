package Railway_App.pageObjects;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import Railway_App.componentGroups.ReusableLibrary;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SetUpDeviceAndApplication  extends ReusableLibrary {

	AndroidDriver driver;

	public SetUpDeviceAndApplication(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}








	public String connectVirtualDevice() throws MalformedURLException {

//		System.out.println("Starts connecting device...");

		String deviceConnectStatus = driver.currentActivity();

		System.out.println("deviceConnectStatus : "+deviceConnectStatus);

		return deviceConnectStatus;
	}



	public boolean verifyAppInstallStatus(Map<String, String> testData) {

		boolean IsAppInstalled = driver.isAppInstalled(testData.get("APP_PACKAGE"));

		if(IsAppInstalled) {

			openMenu();
//			System.out.println(IsAppInstalled+" == true means installed");
			return IsAppInstalled;
		}
		else {
			//			driver.installApp(System.getProperty("user.dir")+"/APK/Unit_Converter.apk");

			driver.installApp(System.getProperty("user.dir")+testData.get("Path_of_Apk"));
			try 
			{
				Thread.sleep(5000);
				System.out.println("Applicstion installed now");
				openMenu();

				return true;
			}
			catch(Exception e){
				//testStepHandle("FAIL", testSetup.driver, test, e);
			}
			//Thread.sleep(15000);
		}
		return IsAppInstalled;
	}

	public void openMenu() {

		new TouchAction<>(driver).press(PointOption.point(802,2332)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))

		.moveTo(PointOption.point(802, 1700))

		.release().perform();

	}



	public String luanchAppUsingDesiredCapabilities(Map<String, String> testData) throws InterruptedException {

//		driver.startActivity(new Activity(testData.get("APP_PACKAGE"),testData.get("APP_ACTIVITY")));
//
//		Thread.sleep(10000);
//
//		String currentActivity = driver.currentActivity();
//
//	//	System.out.println("Current activity : "+currentActivity);
//
//		return currentActivity;

		driver.activateApp(testData.get("APP_PACKAGE"));

//		// Start the activity
//		Map<String, Object> args = new HashMap<>();
//		args.put("appPackage", testData.get("APP_PACKAGE"));
//		args.put("appActivity", testData.get("APP_ACTIVITY"));
//		System.out.println("Starting app activity . . .");
//		driver.executeScript("mobile: startActivity", args);
//		Thread.sleep(10000);

		//System.out.println( testData.get("APP_ACTIVITY")+" and "+testData.get("APP_PACKAGE"));

		Map<String, Object> args = new HashMap<>();
		args.put("appPackage", testData.get("App_activity_Name"));
		//args.put("appActivity",  testData.get("APP_ACTIVITY"));
		args.put("intentAction", "android.intent.action.MAIN");
		args.put("intentCategory", "android.intent.category.LAUNCHER");
		args.put("intentFlags", "0x10200000");
		Thread.sleep(5000);
		// Get the current activity name
		String currentActivity = driver.currentActivity();
		Thread.sleep(10000);
		System.out.println("Current app activity . . ."+currentActivity);

		return currentActivity;
	}


	public String closeApp() throws InterruptedException {

		String appPackage = driver.getCurrentPackage();
		
		System.out.println("App package for terminate : "+appPackage);
		
		driver.terminateApp(appPackage);
		
		sleepMin();
		
		backswipe();
		
		String currentActivity = driver.currentActivity();

	//	System.out.println("Current activity : "+currentActivity);
		
	//	driver.quit();

		return currentActivity;
	}


	public void openRecentScreens(int...args) throws InterruptedException {

		new TouchAction<>(driver).press(PointOption.point(args[0],args[1])).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))

		.moveTo(PointOption.point(args[2], args[3])).release().perform();

		sleepMin();
	}


	public String killAPP(int...args) throws Exception

	{

		new TouchAction<>(driver).press(PointOption.point(args[0],args[1])).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))

		.moveTo(PointOption.point(args[2], args[3])).release().perform();

		sleepMin();
		
		String currentActivity = driver.currentActivity();

	//	System.out.println("Current activity : "+currentActivity);

		return currentActivity;
	}

	public void quiteAndroidDriver() {

		driver.quit();

	}
}
