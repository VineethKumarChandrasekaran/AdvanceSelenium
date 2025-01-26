package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadDataFromPropertyFileForRegistration {
public static WebDriver driver;
	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream("./src/test/resource/TestData/Data.properties");
		Properties properties = new Properties();
		properties.load(file);
        if(properties.getProperty("browser").equalsIgnoreCase("chrome")) {
        	driver = new ChromeDriver();
        }else if(properties.getProperty("browser").equalsIgnoreCase("firefox")) {
        	driver = new FirefoxDriver();
        }else {
        	driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.findElement(By.xpath("//a[text()='Register']")).click();
		if (driver.getTitle().equals(properties.getProperty("registerpagetitle"))) {
			   driver.findElement(By.xpath("//input[@id='gender-male']")).click();
			   driver.findElement(By.xpath("//input[@id='FirstName']")).clear();
			   driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(properties.getProperty("firstname"));
			   driver.findElement(By.xpath("//input[@id='LastName']")).clear();
			   driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(properties.getProperty("lastname"));
			   driver.findElement(By.xpath("//input[@id='Email']")).clear();
			   driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(properties.getProperty("email"));
			   driver.findElement(By.xpath("//input[@id='Password']")).clear();
			   driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(properties.getProperty("password"));
			   driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).clear();
			   driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(properties.getProperty("password"));
			   driver.findElement(By.xpath("//input[@id='register-button']")).click();
			   System.out.println("Registration Done Successfully");
		} else {
            System.out.println("Click on Register Link");
		}
		driver.manage().window().minimize();
		driver.quit();
	}
	
}
