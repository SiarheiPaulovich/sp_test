package com.sp.mailru.runner;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

public class TestRunner {

	private static final Logger LOG = Logger.getLogger(TestRunner.class);
	
	public static void main(String[] args) {
		final TestNG testNG = new TestNG(true);
		File testng = new File("testng.xml");
		if(! (testng.exists() && testng.canRead())){
			LOG.error("TestNG config file 'testng.xml' is not accessable");
			System.exit(1);
		}
		final Parser parser = new Parser("testng.xml");
		List<XmlSuite> suites = null;
		try {
			suites = parser.parseToList();
		} catch (Exception e) {
			LOG.error("Error while parsing testng.xml: " + e.getMessage());
			System.exit(1);
		}
		
		if(testNG != null){
			testNG.setXmlSuites(suites);
			testNG.run();
		}
	}
}
