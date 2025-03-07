package Railway_App.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentCardsReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.configuration.ViewName;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.Scenario;

public class SuperTestNG {
	public FileInputStream fis;
	public static Properties prop;

	public static ExtentSparkReporter report = null;
	//	public static ExtentHtmlReporter report = null;

	public static ExtentReports extent = null;
	public ExtentTest test = null;
	public static ThreadLocal<ExtentTest> logInfo = new ThreadLocal<ExtentTest>(); // Thread safe
	public static String configpath = null;
	public static String runConfigSheetname = null;
	public static String env = null;
	public static String testDataFile = null;
	public static Properties properties;

	public static SoftAssert softAssert = null;
	public static String scenarioName = null;

	public static String TestDataPath = "\\src\\test\\java\\testdata\\TEST\\TestData.xlsx";  //changed null to path
	public static final String propertyFilePath = System.getProperty("user.dir")
			+ "//src//test//resources//globalSettings.properties";
	public static int rowcount = 1;
	public static String fileName;
	public static String folderName;



	public static String totalScenrios;
	public static String startTime;
	public static String endTime;
	public static String duration;




	public 	static Scenario scenario;
	public static String reportPath="https://globalcsg.sharepoint.com/:i:/r/sites/DT-EngineeringTeam-CT_QaAnt/Shared%20Documents/CT_QaAnt/Reports/creativelogo.png?csf=1&web=1";



	//To run Appium server automatically
	public static AppiumDriverLocalService service;
	public static String NodeExePath = "C:\\Program Files\\nodejs\\node.exe";
	public static String NodeJSMainPath = "C:\\Program Files\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	public static String ServerAddress = "127.0.0.1";


	public static ExtentReports setUp() {

		folderName = "Run_" + setFolderName();
		fileName = "Run_" + getTimeStamp();
		String reportLocation = System.getProperty("user.dir") + "\\"+"Automation Report"+"\\" + folderName+ "\\" + "Report" + ".html";

		report = new ExtentSparkReporter(reportLocation);
		//			report = new ExtentHtmlReporter(reportLocation);

		report.config().setEncoding("utf-8");
		report.loadXMLConfig(new File("extentconfig.xml"),true);
		report.config().setAutoCreateRelativePathMedia(true);
		report.config().setTheme(Theme.STANDARD);
		report.config().setCSS(".badge-primary { background-color: #f8f9fa;color: #007bff;}");
		System.out.println("Extent Report location initialized . . .");
		report.start();

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "General store mobile application");
		//		extent.setSystemInfo("Environment URL", "https://test-license-manager.safefleetcloud.com/");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		System.out.println("System Info. set in Extent Report");
		//	JsonFile.writeDirectoryName(folderName);
		return extent;
	}

// getTimeStamp

	public static String getTimeStamp() {
		Locale locale = new Locale("en", "UK");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "MMMMM dd,yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);

		String timestamp = simpleDateFormat.format(new Date());
		return timestamp;
	}
	public static String setFolderName() {
		Locale locale = new Locale("en", "UK");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "MMMMMdd_yyyyHHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);

		String timestamp = simpleDateFormat.format(new Date());
		return timestamp;
	}

	public static String getDuration(String st,String et) throws ParseException {
		Locale locale = new Locale("en", "UK");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "MMMMM dd,yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);
		Date datetime1 = simpleDateFormat.parse(st);
		Date datetime2 = simpleDateFormat.parse(et);

		// Calculate the difference between the datetimes in milliseconds
		long diffInMillies = Math.abs(datetime1.getTime() - datetime2.getTime());

		// Convert the milliseconds to formatted string
		long diffSeconds = diffInMillies / 1000 % 60;
		long diffMinutes = diffInMillies / (60 * 1000) % 60;
		long diffHours = diffInMillies / (60 * 60 * 1000) % 24;
		long diffDays = diffInMillies / (24 * 60 * 60 * 1000);

		String durationTime = String.format("%02d:%02d:%02d",diffHours, diffMinutes, diffSeconds);
		durationTime = insertCharAtIndex(durationTime,'h',2);
		durationTime = insertCharAtIndex(durationTime,'m',6);
		durationTime = insertCharAtIndex(durationTime,'s',10);
		return durationTime;
	}

	public static String insertCharAtIndex(String str, char ch, int index) {
		String result = null;
		if (index < 0 || index > str.length()) {
			result = str;
		} else {
			result = str.substring(0, index) + ch + str.substring(index);
		}
		return result;
	}
}
