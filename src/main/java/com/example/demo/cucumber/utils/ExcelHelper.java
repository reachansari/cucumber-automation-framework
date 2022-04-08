package com.example.demo.cucumber.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.cucumber.model.StockData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class ExcelHelper {
  private static boolean headerWritten = false;
  private static int rowCount = 0;
  private static final String WORKBOOK_SHEET = "StockData";
  private static Workbook stockDataExcel;
  private static Sheet stockDataSheet;
  static {
    createExcel();
  }
  private static void createExcel() {
    if (stockDataExcel == null) {
      stockDataExcel = new XSSFWorkbook();
      stockDataSheet = stockDataExcel.createSheet(WORKBOOK_SHEET);
    }
  }
  private static void addHeaderToExcel() {
    if (stockDataExcel == null) {
      createExcel();
    }
    Row row = stockDataSheet.createRow(rowCount++);
    int cellCount = 0;
    Cell cell = row.createCell(cellCount++);
    cell.setCellValue("Company Name");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Rating");
    cell = row.createCell(cellCount++);
    cell.setCellValue("P/E");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Div Yield");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Promotor Holding");
    cell = row.createCell(cellCount++);
    cell.setCellValue("FII Holding");
    cell = row.createCell(cellCount++);
    cell.setCellValue("DII Holding");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Public Holding");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Quarterly PAT Margin");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Quarterly PAT Growth");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Annual PAT Margin");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Annual PAT Growth");
    cell = row.createCell(cellCount++);
    cell.setCellValue("Last Trading Price");
    cell = row.createCell(cellCount++);
    cell.setCellValue("High Price");
    cell = row.createCell(cellCount++);
    cell.setCellValue("EBITDA CAGR 10Y");
    cell = row.createCell(cellCount++);
    cell.setCellValue("EBITDA CAGR 5Y");
    cell = row.createCell(cellCount++);
    cell.setCellValue("EBITDA CAGR 3Y");
    cell = row.createCell(cellCount++);
    cell.setCellValue("PAT CAGR 10Y");
    cell = row.createCell(cellCount++);
    cell.setCellValue("PAT CAGR 5Y");
    cell = row.createCell(cellCount++);
    cell.setCellValue("PAT CAGR 3Y");
    headerWritten = true;
  }
  public static void addDataToExcel(StockData stockData) {
    if (headerWritten == false) {
      addHeaderToExcel();
    }
    Row row = stockDataSheet.createRow(rowCount++);
    int cellCount = 0;
    Cell cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getCompanyName());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getRating());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getStockPE());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getDivYield());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getPromotorHolding());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getFiiHolding());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getDiiHolding());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getPublicHolding());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getQuarterlyPatMargin());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getQuarterlyPatGrowth());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getAnnualPatMargin());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getAnnualPatGrowth());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getLastTradingPrice());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getHighPrice());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getEbitdaCagr10y());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getEbitdaCagr5y());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getEbitdaCagr3y());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getPatCagr10y());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getPatCagr5y());
    cell = row.createCell(cellCount++);
    cell.setCellValue(stockData.getPatCagr3y());
  }
  public static void writeDataToExcel() throws IOException {
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + "StockData.xlsx";
    FileOutputStream outputStream = new FileOutputStream(fileLocation);
    stockDataExcel.write(outputStream);
    stockDataExcel.close();
  }
}
