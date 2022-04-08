package com.example.demo.cucumber.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.cucumber.model.StockData;
import com.example.demo.cucumber.utils.ChromeBrowserHelper;
import com.example.demo.cucumber.utils.ExcelHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RateStarSteps {
	
	private static final Logger LOG = LoggerFactory.getLogger(RateStarSteps.class);
	private static final String RATE_STAR_URL = "https://www.ratestar.in/";
	private static final String STOCK_NAME_XPATH_BASE_PAGE = "//input[@id='txtStock']";
	private static final String STOCK_NAME_XPATH_OTHER_PAGES = "//input[@id='txtSearch']";
	private static final String SEARCH_XPATH_BASE_PAGE = "//body/form[@id='form1']/section[1]/section[2]/div[1]/div[1]/div[2]/div[1]/span[1]/i[1]";
	private static final String SEARCH_XPATH_OTHER_PAGES = "//input[@id='ImageButton1']";
	private static final String COMPANY_NAME = "//span[@id='lblCompany']";
	private static final String RATING = "//span[@id='lblRating']";
	private static final String STOCK_PE = "//span[@id='lblStockPE']";
	private static final String DIV_YIELD = "//span[@id='lblDivYeild']";
	private static final String PROMOTOR_HOLDING = "/html[1]/body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]";
	private static final String FII_HOLDING = "/html[1]/body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[1]";
	private static final String DII_HOLDING = "/html[1]/body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[2]";
	private static final String PUBLIC_HOLDING = "/html[1]/body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[3]";
	private static final String QTR_PAT_MARGIN = "//tbody/tr[@id='rptQtrStd_tr_qtrStd_10']/td[1]";
	private static final String QTR_PAT_GROWTH = "//tbody/tr[@id='rptQtrStd_tr_qtrStd_9']/td[1]";
	private static final String ANUL_PAT_MARGIN = "//body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/section[2]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[1]/div[16]";
	private static final String ANUL_PAT_GROWTH = "//body[1]/form[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/section[2]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[1]/div[17]";
	private static final String LAST_TRADING_PRICE = "//span[@id='lblLTP']";
	private static final String HIGH_PRICE = "//span[@id='lblwHigh']";
	private static final String EBITDA_CAGR_10Y = "//span[@id='EBITDA_Cagr10y']";
	private static final String EBITDA_CAGR_5Y = "//span[@id='EBITDA_Cagr5y']";
	private static final String EBITDA_CAGR_3Y = "//span[@id='EBITDA_Cagr3y']";
	private static final String PAT_CAGR_10Y = "//span[@id='PAT_Cagr10y']";
	private static final String PAT_CAGR_5Y = "//span[@id='PAT_Cagr5y']";
	private static final String PAT_CAGR_3Y = "//span[@id='PAT_Cagr3y']";
	
	
	private static WebDriver BROWSER = ChromeBrowserHelper.getBrowserInstance();

	@Given("RateStar site launched in browser")
	public void launchRateStarInBrowser() {
		LOG.debug("Launching RateStar.in in Chrome Browser");
		BROWSER.navigate().to(RATE_STAR_URL);
		BROWSER.manage().window().maximize();
	}

	@When("Stock Name {string} entered successfully")
	public void stockEnteredSuccessfully(String stockName) {
		LOG.debug("Entering StockName and Clicking on Search to retrieve the Stock Data");
		WebElement stockNameText;
		try {
			stockNameText = BROWSER.findElement(By.xpath(STOCK_NAME_XPATH_BASE_PAGE));
		} catch (NoSuchElementException exception) {
			stockNameText = BROWSER.findElement(By.xpath(STOCK_NAME_XPATH_OTHER_PAGES));
		}
		stockNameText.clear();
		stockNameText.sendKeys(stockName);
		WebElement searchButton;
		try {
			searchButton = BROWSER.findElement(By.xpath(SEARCH_XPATH_BASE_PAGE));
		} catch (NoSuchElementException exception) {
			searchButton = BROWSER.findElement(By.xpath(SEARCH_XPATH_OTHER_PAGES));
		}
		searchButton.click();
		ChromeBrowserHelper.waitForPageToLoadFully();
	}

	@Then("Retrieve Data from RateStar for the stock {string}")
	public void retrieveData(String stockName) {
		LOG.debug("Required Data retrieved for the Stock {} ", stockName);
		String companyName = BROWSER.findElement(By.xpath(COMPANY_NAME)).getText();
		String rating = BROWSER.findElement(By.xpath(RATING)).getText();
		String lastTradingPrice = BROWSER.findElement(By.xpath(LAST_TRADING_PRICE)).getText();
		String highPrice = BROWSER.findElement(By.xpath(HIGH_PRICE)).getText();
		String ebitdaCagr10y = BROWSER.findElement(By.xpath(EBITDA_CAGR_10Y)).getText();
		String ebitdaCagr5y = BROWSER.findElement(By.xpath(EBITDA_CAGR_5Y)).getText();
		String ebitdaCagr3y = BROWSER.findElement(By.xpath(EBITDA_CAGR_3Y)).getText();
		String patCagr10y = BROWSER.findElement(By.xpath(PAT_CAGR_10Y)).getText();
		String patCagr5y = BROWSER.findElement(By.xpath(PAT_CAGR_5Y)).getText();
		String patCagr3y = BROWSER.findElement(By.xpath(PAT_CAGR_3Y)).getText();
		String stockPE = BROWSER.findElement(By.xpath(STOCK_PE)).getText();
		String divYield = BROWSER.findElement(By.xpath(DIV_YIELD)).getText();
		String promotorHolding = BROWSER.findElement(By.xpath(PROMOTOR_HOLDING)).getText();
		String fiiHolding = BROWSER.findElement(By.xpath(FII_HOLDING)).getText();
		String diiHolding = BROWSER.findElement(By.xpath(DII_HOLDING)).getText();
		String publicHolding = BROWSER.findElement(By.xpath(PUBLIC_HOLDING)).getText();
		String qtrPatMargin = BROWSER.findElement(By.xpath(QTR_PAT_MARGIN)).getText();
		String qtrPatGrowth = BROWSER.findElement(By.xpath(QTR_PAT_GROWTH)).getText();
		String anlPatMargin = BROWSER.findElement(By.xpath(ANUL_PAT_MARGIN)).getText();
		String anlPatGrowth = BROWSER.findElement(By.xpath(ANUL_PAT_GROWTH)).getText();
		System.out.println("Quarterly Profit Margin");
		System.out.println(qtrPatMargin);
		System.out.println("Quarterly Profit Growth");
		System.out.println(qtrPatGrowth);
		System.out.println("Annual Profit Margin");
		System.out.println(anlPatMargin);
		System.out.println("Annual Profit Growth");
		System.out.println(anlPatGrowth);
		StockData stockData = new StockData();
		stockData.setCompanyName(companyName);
		stockData.setRating(rating);
		stockData.setLastTradingPrice(lastTradingPrice);
		stockData.setHighPrice(highPrice);
		stockData.setEbitdaCagr10y(ebitdaCagr10y);
		stockData.setEbitdaCagr5y(ebitdaCagr5y);
		stockData.setEbitdaCagr3y(ebitdaCagr3y);
		stockData.setPatCagr10y(patCagr10y);
		stockData.setPatCagr5y(patCagr5y);
		stockData.setPatCagr3y(patCagr3y);
		stockData.setStockPE(stockPE);
		stockData.setDivYield(divYield);
		stockData.setPromotorHolding(promotorHolding);
		stockData.setFiiHolding(fiiHolding);
		stockData.setDiiHolding(diiHolding);
		stockData.setPublicHolding(publicHolding);
		stockData.setQuarterlyPatMargin(qtrPatMargin);
		stockData.setQuarterlyPatGrowth(qtrPatGrowth);
		stockData.setAnnualPatMargin(anlPatMargin);
		stockData.setAnnualPatGrowth(anlPatGrowth);
		ExcelHelper.addDataToExcel(stockData);
	}
}