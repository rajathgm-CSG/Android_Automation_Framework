package Railway_App.testComponents;

import Railway_App.pageObjects.LogInPage;
import Railway_App.pageObjects.SetUpDeviceAndApplication;
import io.appium.java_client.android.AndroidDriver;

public class PageObjectManager {


	public AndroidDriver driver;

	public LogInPage lp;

	public SetUpDeviceAndApplication sd;

//	public ProductHomePage ph;
//
//	public CartPage cp;





	public PageObjectManager(AndroidDriver driver) {
		this.driver=driver;
	}

	public LogInPage getLogInPage() {
		lp = new LogInPage(driver);
		return lp;
	}

	public SetUpDeviceAndApplication getSetUpDeviceAndApplicationPage() {
		sd = new SetUpDeviceAndApplication(driver);
		return sd;
	}

}
