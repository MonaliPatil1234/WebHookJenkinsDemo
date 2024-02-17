package mavenFirstProj.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import mavenFirstProj.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
public class Listeners extends baseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public Listeners() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		ITestListener.super.onTestStart(result);//unique thread id Errorvalidationtest->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test is PASSED");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		//test.log(Status.FAIL, "Test Failed");
		test.fail(result.getThrowable());
		try 
		{
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try 
		{
			
			String path=getScreenshot(result.getMethod().getMethodName()+result.getMethod().getCurrentInvocationCount(),driver);
			test.addScreenCaptureFromPath(path, result.getMethod().getMethodName()+result.getMethod().getCurrentInvocationCount());
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		ITestListener.super.onFinish(context);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@SuppressWarnings("removal")
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage1(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestFailedButWithinSuccessPercentage1(result);
	}

}
