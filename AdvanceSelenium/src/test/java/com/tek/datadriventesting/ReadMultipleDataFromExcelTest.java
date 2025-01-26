package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class ReadMultipleDataFromExcelTest {
	public static WebDriver driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resource/demoapplication.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("Sheet1");
		for (Row row : sheet) {
			for (Cell cell : row) {

				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue() + " \t ");
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() + " \t ");
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + " \t ");
					break;
				default:
					System.out.print("Invaild DataType");
					break;
				}

			}
			System.out.println();
		}
       workbook.close();
       file.close();
	}

}
