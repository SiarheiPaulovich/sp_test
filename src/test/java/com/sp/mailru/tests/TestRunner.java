package com.sp.mailru.tests;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

public class TestRunner {

	public static void main(String[] args) {
		final TestNG testNG = new TestNG(true);
		final Parser parser = new Parser("src\\test\\resources\\testng.xml");
		List<XmlSuite> suites = null;
		try {
			suites = parser.parseToList();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(testNG != null){
			testNG.setXmlSuites(suites);
			testNG.run();
		}
	}
}
