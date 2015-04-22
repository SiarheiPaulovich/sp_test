package com.sp.mailru.runner;

import org.apache.log4j.Logger;
import org.testng.IConfigurationListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SuiteListener implements ISuiteListener, ITestListener, IConfigurationListener{

	private static final Logger LOG = Logger.getLogger(SuiteListener.class);
	
	@Override
	public void onStart(ISuite suite) {
		LOG.info("Suite '" + suite.getName() + "' started");		
	}

	@Override
	public void onFinish(ISuite suite) {
		LOG.info("Suite '" + suite.getName() + "' finished");
		for (ISuiteResult res : suite.getResults().values()) {
			if((res.getTestContext().getFailedConfigurations().size() +
					res.getTestContext().getFailedTests().size() + 
					res.getTestContext().getSkippedConfigurations().size() +
					res.getTestContext().getSkippedTests().size()) != 0){
				LOG.info("Suite '" + suite.getName() + "' FAILED");
				return;
			}
			LOG.info("Suite '" + suite.getName() + "' PASSED");
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		LOG.info("Test '" + result.getName() + "' started");		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info("Test '" + result.getName() + "' PASSED");			
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.error("Test '" + result.getName() + "' FAILED");			
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOG.error("Test '" + result.getName() + "' SKIPPED");			
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

}