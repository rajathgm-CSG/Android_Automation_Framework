package Railway_App.testComponents;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.apache.commons.io.input.BoundedInputStream;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.service.DriverService;

public class BaseTest extends SuperTestNG {


	public AndroidDriver driver;


	public BaseTest() throws IOException {

		prop = new Properties();
		fis = new FileInputStream(propertyFilePath);
		prop.load(fis);

		if (prop.get("Environment").equals("TEST")) {

			System.out.println("test file initilized. .");

			TestDataPath = "\\src\\test\\java\\testdata\\TEST\\TestData.xlsx";

		} else if (prop.get("Environment").equals("DEV")) {

			TestDataPath = "\\src\\test\\java\\testdata\\DEV\\TestData.xlsx";
		}
	}


	public AndroidDriver intializeAndroidDriver() throws Exception {
		UiAutomator2Options Ds = new UiAutomator2Options();
		//DesiredCapabilities cap = new DesiredCapabilities();

		System.out.println("start connecting . .");

//		DesiredCapabilities Ds = new DesiredCapabilities();
//		Ds.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
//		Ds.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_9");
//		Ds.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
//		Ds.setCapability(MobileCapabilityType.PLATFORM_VERSION,"15.0");

			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Pixel_9");
			options.setPlatformName("Android");
			options.setPlatformVersion("15.0");
			options.setAutomationName("UiAutomator2");

			URL url = new URL("http://127.0.0.1:4723/wd/hub");

			//driver = new AndroidDriver(url,Ds);
			driver = new AndroidDriver(url, options);

			System.out.println("print the DRIVER..."+driver);
            return driver;
    }





	public void startServerAutomatically() {

		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(NodeExePath))
				.withAppiumJS(new File(NodeJSMainPath)).withIPAddress(ServerAddress).withArgument(GeneralServerFlag.BASEPATH, "/wd/hub").usingPort(4723));

		service.start();

		System.out.println("Server starts automatically . . .");
	}



	public void closeBrowser() {
		driver.close();
	}

	public String getScreenshot(String testCaseName, AndroidDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}



	public static void zoomOut(int s) throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < s; i++) {

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public static void zoomIn(int s) throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < s; i++) {

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}


	public void reportInfo(String msg) {
		test.pass(msg);
	}

	public void reportPass(String title, String exp, String act) {
		String message = "<b>" + "Expected " + title + " : " + "</b>" + "<font color=" + "green>" + exp + "</font>"
				+ "\t" + "<b>" + "Actual " + title + " : " + "</b>" + "<font color=" + "green>" + act + "</font>";
		test.pass(message);
	}

	public void reportFail(String title, String exp, String act) {
		String message = "<b>" + "Expected " + title + " : " + "</b>" + "<font color=" + "red>" + exp + "</font>" + "\t"
				+ "<b>" + "Actual " + title + " : " + "</b>" + "<font color=" + "red>" + act + "</font>";
		test.fail(message);
		softAssert.assertEquals(exp, act);
	}

	public void reportPayload(String msg) {
		Markup m = MarkupHelper.createCodeBlock(msg, CodeLanguage.JSON);
		test.info(m);
	}

	public void validateField(String title, String expected, String actual) {

		if (expected.equals(actual))
			reportPass(title, expected, actual);
		else
			reportFail(title, expected, actual);
	}

	public void validateNullValues(String title, String actual) {

		if (actual == null)
			reportPass(title, "shold have null ", "contain null");
		else
			reportFail(title, "shold have null ", "contain null");
	}

	public void reportScreenshot(String status, String exp, String act, AndroidDriver driver) throws IOException {

		String message = "<b>" + "Expected : " + "</font>" + "</b>" + exp + "\t" + "<b>" + "Actual : " + "</b>" + act;
		if (status.equals("PASS"))
			test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("FAIL"))
			test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("INFO"))
			test.info(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}

	public void reportScreenshot(AndroidDriver driver) throws IOException {

		test.info("<b>" + "<font color=" + "orange>" + "Screenshot" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}

	public String captureScreenShot(AndroidDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\" +folderName+ "\\" + getcurrentdateandtime() + ".jpg";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	private static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}

	public void testStepHandle(String teststatus, AndroidDriver driver, ExtentTest extenttest, Throwable throwable) {
		switch (teststatus) {
		case "FAIL":
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
			extenttest.log(Status.FAIL, throwable.fillInStackTrace());

			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
			} catch (IOException e) {
				// e.printStackTrace();
			}
			softAssert.fail();
			//VideoRecorder.stopRecording();
			break;

		case "PASS":
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;

		default:
			break;
		}
	}




	//Random String Generator

	public String randomStringGenerator() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String timestamp = sdf.format(date);
		String randomString = "testauto" + timestamp;
		return randomString;
	}

	//Random Integer Generator
	public String randomIntGenerator() {

		Random rand = new Random();
		int num = rand.nextInt((int) Math.pow(10, 10));
		return String.valueOf(num);

	}

	//// Random Email Generator

	public String randomEmailGenerator() {

		String[] emailProviders = {"gmail.com", "yahoo.com", "hotmail.com", "aol.com", "outlook.com"};
		Random random = new Random();
		String randomString = "testauto";
		for (int i = 0; i < 4; i++) {
			char c = (char) (random.nextInt(26) + 'a');
			randomString += c;
		}
		return randomString + "@" + emailProviders[random.nextInt(emailProviders.length)];

	}

	public static String getRamdomMAcAddress() {
		Random rand = new Random();
		return String.format("%02x-%02x-%02x-%02x-%02x", rand.nextInt(256), 0x5e, rand.nextInt(256), 0x53, 0xaf);
	}




}
