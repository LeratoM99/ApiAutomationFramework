package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .build();

        // Updated reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("API Automation Portfolio");
        sparkReporter.config().setReportName("User API Tests");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Lerato Motaung");
        extent.setSystemInfo("Project", "API Automation Portfolio");
    }

    @AfterClass
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }

    protected void logRequestResponse(String requestBody, Response response) {
        test.info(MarkupHelper.createLabel("Request Body", ExtentColor.BLUE));
        test.info(requestBody);

        test.info(MarkupHelper.createLabel("Response Body", ExtentColor.GREEN));
        test.info(response.getBody().asString());

        int status = response.getStatusCode();
        test.info(MarkupHelper.createLabel("Status Code: " + status,
                status >= 400 ? ExtentColor.RED : ExtentColor.GREEN));
    }
}
