package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Set file path for the HTML report
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("API Automation Report");
            spark.config().setReportName("User API Tests");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
