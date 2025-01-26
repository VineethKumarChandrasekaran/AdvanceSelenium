package com.tek.datadriventesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadDataFromXMLFile {
	public static WebDriver driver;

	@Parameters({ "browser", "url", "username", "password", "title" })
	@Test
	public void login(@Optional("edge") String browser, String url, String username, String password, String title) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).clear();
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).clear();
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		if (driver.getTitle().equals(title)) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Not Successful - Check Login Credentials");
		}
		driver.manage().window().minimize();
		driver.quit();
	}
}
