package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadDataFromExcelFileForLogin {
public static WebDriver driver;
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	FileInputStream file = new FileInputStream("./src/test/resource/demoapplication.xlsx");
    Workbook workbook = WorkbookFactory.create(file);
    if ( workbook.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue().equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	} else if (workbook.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue().equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
	} else {
		driver = new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(workbook.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue());
	driver.findElement(By.xpath("//a[text()='Log in']")).click();
	if (driver.getTitle().equals(workbook.getSheet("Login").getRow(1).getCell(0).getStringCellValue())) {
		driver.findElement(By.xpath("//input[@id='Email']")).clear();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(workbook.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue());
		driver.findElement(By.xpath("//input[@id='Password']")).clear();
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(workbook.getSheet("Sheet1").getRow(1).getCell(3).getStringCellValue());
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		if (driver.findElement(By.xpath("//a[@class='account']")).getText().equals(workbook.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue())) {
			System.out.println("Login Successful");
		}
	} else {
		System.out.println("Click on the Login Link");
	}
	driver.manage().window().minimize();
	driver.quit();
	}

}
