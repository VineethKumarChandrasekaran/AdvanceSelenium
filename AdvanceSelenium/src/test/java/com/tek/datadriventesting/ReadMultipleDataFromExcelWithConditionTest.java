package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelWithConditionTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resource/demoapplication.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("TC");
		String TC ="TS_DWS_002";
        for (Row row : sheet) {
        	Cell testcelldata = row.getCell(0);
			if(testcelldata!=null&&testcelldata.getStringCellValue().equalsIgnoreCase(TC)) {
				for (Cell cell : row) {
					System.out.print(cell.getStringCellValue()+"\t");
				}
			}
			System.out.println();
		}
        workbook.close();
        file.close();
	}

}
