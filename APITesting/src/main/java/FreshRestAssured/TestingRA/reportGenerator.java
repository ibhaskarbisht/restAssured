package FreshRestAssured.TestingRA;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
public class reportGenerator {
	
	public static ExtentReports report;
	public static ExtentTest test;
	
	public static void genReport()
	{
		report = new ExtentReports(System.getProperty("user.dir")+ "/test-output/generatedReport.html");
	}
	

}
