package com.example.demo.cucumber.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowserHelper {
	//private static final String CHROME_DRIVER = "Y:\\UAT\\FinIQ\\Paul\\ChromeDriver\\99.0.4844.51\\chromedriver.exe";
	private static WebDriver webDriver;
	static {
		instantiateBrowser();
	}

	private static void instantiateBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static WebDriver openBrowser() {
		if (webDriver == null) {
			instantiateBrowser();
		}
		return webDriver;
	}

	public static WebDriver getBrowserInstance() {
		return openBrowser();
	}

	public static void closeBrowser() {
		webDriver.quit();
		webDriver = null;
	}

	public static void waitForPageToLoadFully() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// System.out.println("Page has not loaded yet…waiting");
			}
			if (jsExecutor.executeScript("return document.readyState").toString().equals("complete")) {
				// System.out.println("Page has loaded fully.");
				break;
			}
		}
	}
}