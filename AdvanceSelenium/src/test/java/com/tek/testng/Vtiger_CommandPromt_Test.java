package com.tek.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Vtiger_CommandPromt_Test {
	public static WebDriver driver;
	@Test
	public void login() {
		String url = System.getProperty("url");
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
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
		if (driver.getTitle().equals("Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM")) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Not Successful - Check Login Credentials");
		}
		driver.manage().window().minimize();
		driver.quit();
	}

}
