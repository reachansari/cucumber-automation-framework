package com.example.demo.cucumber;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.example.demo.cucumber.utils.ChromeBrowserHelper;
import com.example.demo.cucumber.utils.ExcelHelper;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/main/resources/features/" }, glue = {
		"com.example.demo.cucumber" }, tags = "@Sanity")
public class RunCucumberTest extends AbstractTestNGCucumberTests {
	private static final Logger LOG = LoggerFactory.getLogger(RunCucumberTest.class);

	@BeforeSuite(alwaysRun = true)
	public static void setup() {
		LOG.debug(">>> Before Suite <<<");
		ChromeBrowserHelper.openBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public static void tearDown() throws IOException {
		LOG.debug(">>> After Suite <<<");
		ChromeBrowserHelper.closeBrowser();
		ExcelHelper.writeDataToExcel();
	}
}