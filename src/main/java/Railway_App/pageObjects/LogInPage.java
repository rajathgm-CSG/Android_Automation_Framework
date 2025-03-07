package Railway_App.pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import Railway_App.componentGroups.ReusableLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class LogInPage extends ReusableLibrary {

	AndroidDriver driver;

	public LogInPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
	WebElement inpEmailId;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
	WebElement inpPassword;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Please Enter Valid Email\"]")
	WebElement errorMsgOfEmailId; //android.view.View[@content-desc="Please Enter Valid Email"]

	@FindBy(xpath = "//android.widget.Button[@content-desc='Login']")
	WebElement txtLoginPage;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Login']")
	static WebElement btnLogin;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]/android.view.View[1]")
	static WebElement viewIcon;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView")
	static WebElement Image;  //android.widget.ImageView[@content-desc="Daily logs"]
	// android.widget.Button[@content-desc="Punch In"]
	//android.widget.ImageView[@content-desc='Daily logs']/android.widget.EditText[1]

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Daily logs\"]/android.widget.EditText[1]")
	static WebElement truckID;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Daily logs\"]/android.widget.EditText[2]")
	static WebElement MileageId;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Rail Road List\n" + " *\"]")
	WebElement slctRailRoadList;  //android.widget.Button[@content-desc="Rail Road List * Select Rail Road List"]
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"BNSF\"]")
	WebElement slctRailRoadListtext;

//android.widget.Button[@content-desc="Helpers *"]
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Helpers\n" + " *\"]")
	WebElement inpHelpers;

	@FindBy(xpath = "//android.view.View[@content-desc=\"John Smith john.smith@example.com JS\"]")
	WebElement inpHelpersjohn;
	@FindBy(xpath = "//android.view.View[@content-desc=\"Sarah Johnson sarah.johnson@example.com SJ\"]")
	WebElement inpHelperssarah;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Add Helper\"]")
	WebElement Addhelper;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
	WebElement HelperName;
	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
	WebElement HelperEmail;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Save Selections\"]")
	WebElement saveselection;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Daily logs\"]/android.widget.EditText[3]")
	WebElement HotelName;

//android.widget.Button[@content-desc="Hotel State"]


	@FindBy(xpath = "//android.widget.Button[@content-desc='Hotel State']")
	WebElement ClickHotel;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"USA-states\"]")
	WebElement Hotelstate;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Go to Activites\"]")
	WebElement Gotoactivities;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Maintenance/Service Activity Description :- This is the description of the Maintenance/Service activity Employee Name :- Test\"]")
	WebElement MaintananceService;

	@FindBy(xpath = "//android.view.View[@content-desc=\"In Progress Tab 2 of 4\"]")
	WebElement inprogress;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Paused Tab 3 of 4\"]")
	WebElement paused;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Start Activity\"]")
	WebElement startActivity;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Maintenance/Service Activity This is the description of the Maintenance/Service activity\"]")
	WebElement maintanActivity;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Pause Activity\"]/android.widget.RadioButton\n")
	WebElement pauseActivityRadioButton;

	@FindBy(xpath = "//android.widget.EditText")
	WebElement Comments;

	//LOGOUT---
	@FindBy(xpath = "//android.widget.Button[@content-desc='Open navigation menu']")
	WebElement slctMenu;

	@FindBy(xpath = "//android.view.View[@content-desc='Logout']")
	WebElement btnLogout;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Logout']")
	WebElement clickLogout;

	@FindBy(xpath = "//android.widget.ImageView")
	WebElement imgLoginPage;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Back\"]")
	WebElement backbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Activities\"]")
	WebElement textActivities;




	public String enterEmailID(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(inpEmailId);
		sleepMin();
		waitForElementToBeClickableThenClearThenSendkeys(inpEmailId, testData.get("Email"));
		sleepMin();

		String Actualemail = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("Email")+"']")).getText();
		System.out.println("TEXTTTTTTTTT EMAIL:-----" + Actualemail);   //android.widget.EditText[@text="testdata23456"]a
		return Actualemail;
	}

	public String enterPassword(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(inpPassword);
		waitForElementToBeClickableThenClearThenSendkeys(inpPassword, testData.get("Password"));
		sleepMin();
		waitForElementToBeClickableThenClick(viewIcon);
		sleepMin();
		System.out.println("CHEKINGGGGGGGGGGGGGGGGGG-----------------PWD----------");
		String Actualpassword = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("Password")+"']")).getText();
		System.out.println("TEXTTTTTTTTT PWD:-----" + Actualpassword);
		return Actualpassword;
	}

	public void clickOnLogin() throws InterruptedException {
		waitForElementToBeClickableThenClick(btnLogin);
		sleepAvg();
		// driver.currentActivity();
	}

	public Boolean verifyHomePage() {
		if (Image.isDisplayed()) {
			return true;
		}
		return false;
	}

	public String enterTruckId(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(truckID);
		waitForElementToBeClickableThenSendkeys(truckID, testData.get("Truck ID"));
		sleepMin();
		System.out.println("TRUCK ID----");
		String ActualId = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("Truck ID")+"']")).getText();
		System.out.println("TRUCK ID----" + ActualId);
		return ActualId;
	}
	public String enterMileage(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(MileageId);
		waitForElementToBeClickableThenSendkeys(MileageId, testData.get("Mileage"));
		sleepMin();
		System.out.println("Mileage----");
		String ActualMileage = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("Mileage")+"']")).getText();
		System.out.println("Mileage----" + ActualMileage);
		return ActualMileage;
	}
	public void enterRailRoadList(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(slctRailRoadList);
		sleepMin();
		waitForElementToBeClickableThenClick(slctRailRoadListtext);
		sleepMin();

	}

	public String enterHotelName(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(HotelName);
		waitForElementToBeClickableThenSendkeys(HotelName, testData.get("HotelName"));
		sleepMin();

		String ActualHotelName = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("HotelName")+"']")).getText();
		System.out.println("HotelName----" + ActualHotelName);

		return ActualHotelName;
	}


	public void enterHelper(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(inpHelpers);
		sleepMin();
		waitForElementToBeClickableThenClick(Addhelper);
		sleepMin();
	}

	public String verifyHelperName(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(HelperName);
		waitForElementToBeClickableThenSendkeys(HelperName, testData.get("AddHelper"));
		sleepMin();
		String ActualHelperName = driver.findElement(By.xpath("//android.widget.EditText[@text='" + testData.get("AddHelper") + "']")).getText();
		System.out.println("Helper Name----" + ActualHelperName);
		return ActualHelperName;
	}

	public String verifyHelperEmail(Map<String, String> testData) throws InterruptedException {
		sleepMin();
		waitForElementToBeClickableThenClick(HelperEmail);
		waitForElementToBeClickableThenSendkeys(HelperEmail, testData.get("AddHelperEmail"));
		sleepMin();
		String ActualHelperEmail = driver.findElement(By.xpath("//android.widget.EditText[@text='"+testData.get("AddHelperEmail")+"']")).getText();
		System.out.println("Helper Email----" + ActualHelperEmail);

		return ActualHelperEmail;
	}

	public void clickAddhelper() throws InterruptedException {
		waitForElementToBeClickableThenClick(Addhelper);
		sleepMin();
	}

	public void saveselection() throws InterruptedException {
		waitForElementToBeClickableThenClick(saveselection);
		//scrollToElement();
		sleepMin();
	}

	public void hotelState() throws InterruptedException {
		waitForElementToBeClickableThenClick(ClickHotel);
		waitForElementToBeClickableThenClick(Hotelstate);
		sleepMin();
	}

	public void clickActivity()  {
		waitForElementToBeClickableThenClick(Gotoactivities);
	}

	public void clickInprogress() throws InterruptedException {
		waitForElementToBeClickableThenClick(MaintananceService);
		sleepMin();
		waitForElementToBeClickableThenClick(startActivity);
		sleepMin();
		waitForElementToBeClickableThenClick(inprogress);
		waitForElementToBeClickableThenClick(paused);
		sleepMin();

		waitForElementToBeClickableThenClick(maintanActivity);
		sleepMin();
	}
	public String clickPauseActivity(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(pauseActivityRadioButton);
		waitForElementToBeClickableThenClick(Comments);
		sleepMin();
		waitForElementToBeClickableThenSendkeys(Comments, testData.get("Comments"));
		String ActualComments = Comments.getText();
		System.out.println("CHECKINH COMMENTS---->" + ActualComments);
		return ActualComments;
	}
	public void backButton() {
		waitForElementToBeClickableThenClick(backbutton);
	}

	//LOGOUT---

	public void clickOnLogoutOption() throws InterruptedException {
		sleepMin();
		waitForElementToBeClickableThenClick(slctMenu);
		waitForElementToBeClickableThenClick(btnLogout);
		sleepMin();

	}
	public void logoutButton() throws InterruptedException {
		waitForElementToBeClickableThenClick(clickLogout);
		sleepAvg();
	}

	//LOGOUT---

	public Boolean verifyLoginPage() {
		if (imgLoginPage.isDisplayed()) {
			return true;
		}
		return false;
	}

	public Boolean verifyActivity() throws InterruptedException {
		sleepAvg();
		if (backbutton.isDisplayed()) {
			return true;
		}
		return false;
		}
//	public String verifyActivity(Map<String, String> testData) throws InterruptedException {
//		sleepMin();
//		String ActualText = textActivities.getText();
//		System.out.println("TEXT ------------------------>" + ActualText);
//		return ActualText;
//	}

}
