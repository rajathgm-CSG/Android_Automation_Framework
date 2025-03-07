package RailwayFieldService_App_general_store.stepDefinitions;

import Railway_App.testComponents.BaseTest;
import Railway_App.testComponents.ExcelFileReader;
import Railway_App.testComponents.TestSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.Map;

public class LoginStepDefs extends BaseTest {
    TestSetup testSetup;


    public LoginStepDefs(TestSetup testSetup) throws IOException {
        this.testSetup = testSetup;
    }

    @Given("I connect virtual device with Desired Capabilities {string}")
    public void iConnectVirtualDeviceWithDesiredCapabilities(String tcid) {
        try {
            test = logInfo.get().createNode("I connect virtual device with Desired Capabilities");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Device_setup", tcid);
            reportInfo(testData.toString());

            System.out.println("print the EXCEL PATH..." + System.getProperty("user.dir") + TestDataPath);
            System.out.println("Fetching data for TCID: " + tcid);
            System.out.println("Test Data: " + testData);


            String deviceConnectionStatus = testSetup.pageObjectManager.getSetUpDeviceAndApplicationPage().connectVirtualDevice();

            reportScreenshot(testSetup.driver);

            if (deviceConnectionStatus.equalsIgnoreCase(testData.get("DeviceActivity"))) {
                reportPass("Device activity name", testData.get("DeviceActivity"), deviceConnectionStatus);
                reportInfo("Device " + deviceConnectionStatus + " connected successfully");

            } else
                reportFail("Device status", "Device Connected successfully", "Device Not Connected successfully");
        } catch (Exception e) {
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    @Given("I verify app install or not if not i Install app using testdata {string}")
    public void i_verify_app_install_or_not_if_not_install_app(String tcid) {
        String appName = "Demo";
        try {
            test = logInfo.get().createNode("I verify app install or not if not i Install app using testdata");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Device_setup", tcid);
            reportInfo(testData.toString());

            boolean installStatus = testSetup.pageObjectManager.getSetUpDeviceAndApplicationPage().verifyAppInstallStatus(testData);
            reportScreenshot(testSetup.driver);
            //String appName = "Demo";

            if (installStatus == true)
                reportPass("App status", "'" + testData.get("Application_Name") + "' Application installed successfully", "Demo" + "Application installed successfully");
                // reportPass("App status", "'" + testData.get("Application_Name") + "' Application installed successfully", "Demo" + "Application installed successfully");

            else
                reportFail("App status", "'" + testData.get("Application_Name") + "' Application installed successfully", "Demo" + "Application Not installed successfully");
            //reportFail("App status", "'"+testData.get("Application_Name")+"' Application installed successfully",  "'"+testData.get("Application_Name")+"' Application Not installed successfully");

        } catch (Exception e) {
            testStepHandle("FAIL", testSetup.driver, test, e);
        }

    }


    @Given("I luanch app using desired capabilities by testdata {string}")
    public void i_luanch_app(String tcid) {
        try {
            test = logInfo.get().createNode("I luanch app using desired capabilities by testdata");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Device_setup", tcid);
            reportInfo(testData.toString());

            String ActivityStatus = testSetup.pageObjectManager.getSetUpDeviceAndApplicationPage().luanchAppUsingDesiredCapabilities(testData);

            reportScreenshot(testSetup.driver);

            if (ActivityStatus.equalsIgnoreCase(testData.get("App_activity_Name")))
                reportPass("App activityStatus", "'" + testData.get("App_activity_Name") + "' Application lunched successfully", "'" + ActivityStatus + "' Application lunched successfully");
            else
                reportFail("App activityStatus", "'" + testData.get("App_activity_Name") + "' Application lunched successfully", "'" + ActivityStatus + "' Application lunched successfully");
        } catch (Exception e) {
            e.printStackTrace();
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    //NEw step def file added ------------------
    @Given("I enter the valid email id and valid password {string}")
    public void i_enter_the_valid_email_id_and_valid_password(String tcid) {
        try {
            test = logInfo.get().createNode("I enter the valid email id and valid password");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Login_Info", tcid);
            reportInfo(testData.toString());

            // Verify EMAIL :-------------------
            String EmailStatus = testSetup.pageObjectManager.getLogInPage().enterEmailID(testData);

            if (EmailStatus.equalsIgnoreCase(testData.get("Email"))) {
//                reportScreenshot(testSetup.driver);
                reportPass("Email", testData.get("Email"), EmailStatus);
            } else
                reportFail("Email status", "Email entered successfully", "Email not entered successfully");

            // Verify PASSWORD :-------------------
            String PasswordStatus = testSetup.pageObjectManager.getLogInPage().enterPassword(testData);
//            reportScreenshot(testSetup.driver);

            if (PasswordStatus.equalsIgnoreCase(testData.get("Password"))) {
                reportPass("Password", testData.get("Password"), PasswordStatus);
                reportScreenshot(testSetup.driver);
            } else
                reportFail("Password status", "Password entered successfully", "Password not entered successfully");


        } catch (Exception e) {
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    @And("I verify that application successfully navigates to the home page {string}")
    public void i_verify_that_application_successfully_navigates_to_the_home_page(String tcid) {
        try {
            test = logInfo.get().createNode("I verify that application successfully navigates to the home page");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Home_Page", tcid);
            reportInfo(testData.toString());
            testSetup.pageObjectManager.getLogInPage().clickOnLogin();
            reportScreenshot(testSetup.driver);


            Boolean Imageview = testSetup.pageObjectManager.getLogInPage().verifyHomePage();
            if (Imageview == true) {
                reportPass("Image view", "true", "true");
                reportInfo("Verified that CSG Image view is displayed successfully");

            } else {
                reportFail("Image view", "false", "false");
                reportInfo("Verify that Image view not displayed successfully");
            }

        } catch (Exception e) {
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    @And("I entered daily logs details using testdata {string}")
    public void i_entered_daily_logs_details_using_testdata(String tcid) {
        try {
            test = logInfo.get().createNode("I entered daily logs details using testdata");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Home_Page", tcid);
            reportInfo(testData.toString());


            //1. verify Truck ID
            String TruckIdStatus = testSetup.pageObjectManager.getLogInPage().enterTruckId(testData);
            if (TruckIdStatus.equalsIgnoreCase(testData.get("Truck ID"))) {
                reportPass("Truck ID", testData.get("Truck ID"), TruckIdStatus);
            } else
                reportFail("Truck ID status", "Truck ID entered successfully", "Truck ID not entered successfully");


            //2. verify Mileage ID
            String MileageStatus = testSetup.pageObjectManager.getLogInPage().enterMileage(testData);
            if (MileageStatus.equalsIgnoreCase(testData.get("Mileage"))) {
                reportPass("Mileage", testData.get("Mileage"), MileageStatus);
            } else
                reportFail("Mileage status", "Mileage entered successfully", "Mileage not entered successfully");


            //3. verify Mileage ID
            testSetup.pageObjectManager.getLogInPage().enterRailRoadList(testData);


            //4. verify HotelName
            String hotelname = testSetup.pageObjectManager.getLogInPage().enterHotelName(testData);
            if (hotelname.equalsIgnoreCase(testData.get("HotelName"))) {
                reportPass("HotelName", testData.get("HotelName"), hotelname);
                reportScreenshot(testSetup.driver);
            } else
                reportFail("HotelName", "HotelName entered successfully", "HotelName not entered successfully");


            //5. verify Helper
            testSetup.pageObjectManager.getLogInPage().enterHelper(testData);


            //5.1 verify NAME:-
            String Namestatus = testSetup.pageObjectManager.getLogInPage().verifyHelperName(testData);
            if (Namestatus.equalsIgnoreCase(testData.get("AddHelper"))) {
                reportPass("Helper Name", testData.get("AddHelper"), Namestatus);
                //reportScreenshot(testSetup.driver);
            } else
                reportFail("Helper Name", "Helper Name entered successfully", "Helper Name not entered successfully");


            //5.2 verify EMAIL:-
            String Emailstatus = testSetup.pageObjectManager.getLogInPage().verifyHelperEmail(testData);
            if (Emailstatus.equalsIgnoreCase(testData.get("AddHelperEmail"))) {
                reportPass("Helper Email", testData.get("AddHelperEmail"), Emailstatus);
                reportScreenshot(testSetup.driver);
            } else
                reportFail("Helper Email", "Helper Email entered successfully", "Helper Email not entered successfully");

            testSetup.pageObjectManager.getLogInPage().clickAddhelper();


            //6. verify save selection
            testSetup.pageObjectManager.getLogInPage().saveselection();
            //reportScreenshot(testSetup.driver);


            //7. verify State
            testSetup.pageObjectManager.getLogInPage().hotelState();
            reportScreenshot(testSetup.driver);

            testSetup.pageObjectManager.getLogInPage().clickActivity();
            reportScreenshot(testSetup.driver);

//            testSetup.pageObjectManager.getLogInPage().backButton();
//            reportInfo("Verified & Entered all the Daily logs details & proceed for activities");

        } catch (Exception e) {
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    @And("I verify the daily activity status {string}")
    public void i_verify_the_daily_activity_status(String tcid) {
        try {
            test = logInfo.get().createNode("I verify the daily activity status");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Device_setup", tcid);
            reportInfo(testData.toString());

            testSetup.pageObjectManager.getLogInPage().clickInprogress();
            reportScreenshot(testSetup.driver);

            String CommentsStatus = testSetup.pageObjectManager.getLogInPage().clickPauseActivity(testData);
            reportScreenshot(testSetup.driver);

            if (CommentsStatus.equalsIgnoreCase(testData.get("Comments")))
                reportPass("comments", testData.get("Comments"), CommentsStatus);
            else
                reportFail("comments", "Comments entered successfully", "Comments not entered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }


    @Then("I logout the application")
    public void iLogoutTheApplication() throws InterruptedException, IOException {
        try {
            test = logInfo.get().createNode("I logout the application");

            testSetup.pageObjectManager.getLogInPage().clickOnLogoutOption();
            reportScreenshot(testSetup.driver);
            testSetup.pageObjectManager.getLogInPage().logoutButton();
//            reportScreenshot(testSetup.driver);

            Boolean Imageview = testSetup.pageObjectManager.getLogInPage().verifyLoginPage();
            reportScreenshot(testSetup.driver);
            if (Imageview == true) {
                reportPass("Image view", "true", "true");
                reportInfo("Verified that login page is displayed successfully");

            } else {
                reportFail("Image view", "false", "false");
                reportInfo("Verify that login page not displayed successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
    }

    @And("I verify the Activities page {string}")
    public void iVerifyTheActivitiesPage(String tcid) {
        try {
            test = logInfo.get().createNode("I verify the Activities page");

            Map<String, String> testData = ExcelFileReader.getDataInMap("Home_Page", tcid);
            reportInfo(testData.toString());

            Boolean TextStatus = testSetup.pageObjectManager.getLogInPage().verifyActivity();
            reportScreenshot(testSetup.driver);

            if (TextStatus == true) {
                reportPass("Activity Page displayed", "true", "true");
                reportInfo("Verified that Activity page is displayed successfully");
            }else {
                reportFail("Activity Page displayed", "false", "false");
                reportInfo("Verified that Activity page is not displayed successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            testStepHandle("FAIL", testSetup.driver, test, e);
        }
        testSetup.pageObjectManager.getLogInPage().backButton();
    }
}
