package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GenerateRandomNumberTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	Random random = new Random();
	int randomInt = random.nextInt(1000);
	FileInputStream fis = new FileInputStream("./src/test/resource/demoapplication.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	Cell cell = workbook.getSheet("Sheet1").getRow(0).createCell(4);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("firstname"+randomInt);
	FileOutputStream fos = new FileOutputStream("./src/test/resource/demoapplication.xlsx");
	workbook.write(fos);
	workbook.close();
	System.out.println("====Executed===");
	}

}
