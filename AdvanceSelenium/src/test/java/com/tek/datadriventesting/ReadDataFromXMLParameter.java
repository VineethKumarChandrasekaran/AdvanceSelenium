package com.tek.datadriventesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromXMLParameter {
public static WebDriver driver;
	@Test
	public static void login(XmlTest test) {
		if (test.getParameter("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (test.getParameter("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(test.getParameter("url"));
		driver.findElement(By.xpath("//input[@name='user_name']")).clear();
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(test.getParameter("username"));
		driver.findElement(By.xpath("//input[@name='user_password']")).clear();
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(test.getParameter("password"));
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		if (driver.getTitle().equals(test.getParameter("title"))) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Not Successful - Check Login Credentials");
		}
		driver.manage().window().minimize();
		driver.quit();
	}
}
