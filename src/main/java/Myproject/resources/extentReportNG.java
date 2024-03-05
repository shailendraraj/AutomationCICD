package Myproject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportNG {
	
	public static ExtentReports getReportobject() {
		
		//ExtentReports , ExtentSparkReporter
				String path = System.getProperty("user.dir")+"//reports//" + "index.html" ;
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Result");	
				reporter.config().setDocumentTitle("Test Results");
				
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Shailendra Kumar");
				return extent;
	}
	

}
