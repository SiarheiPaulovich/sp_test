package com.sp.mailru.runner;

import org.apache.log4j.Logger;
import org.testng.IConfigurationListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SuiteListener implements ISuiteListener, ITestListener, IConfigurationListener{

	private static final Logger LOG = Logger.getLogger(SuiteListener.class);
	
	@Override
	public void onStart(ISuite suite) {
		LOG.info("Suite started: " + suite.getName());		
	}

	@Override
	public void onFinish(ISuite suite) {
		LOG.info("Suite finished: " + suite.getName());		
	}

	@Override
	public void onTestStart(ITestResult result) {
		LOG.info("Test started: " + result.getName());		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info("Test PASSED: " + result.getName());		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.error("Test FAILED: " + result.getName());			
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOG.warn("Test SKIPPED: " + result.getName());			
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LOG.error("Test FailedButWithinSuccessPercentage : " + result.getName());		
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		LOG.info("onConfigurationSuccess");
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		LOG.error("onConfigurationFailure");
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		LOG.warn("onConfigurationFailure");
	}

	@Override
	public void onStart(ITestContext context) {
		LOG.info("onStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		LOG.info("onFinish");
	}

}