package com.tek.via;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TS_Via_Bus_Test {
	public static WebDriver driver;

	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream file = new FileInputStream("./src/test/resource/Via.properties");
		Properties properties = new Properties();
		properties.load(file);
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(opt);
		} else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("url"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Bus')]")).click();
		driver.findElement(By.xpath("//input[@id='src']")).clear();
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys(properties.getProperty("from"));
		driver.findElement(By.xpath("//div[text()='Bangalore']")).click();
		driver.findElement(By.xpath("//input[@id='dest']")).clear();
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys(properties.getProperty("to"));
		driver.findElement(By.xpath("//div[text()='Chennai']")).click();
		driver.findElement(By.xpath("//div[@class='calendar-icon']")).click();
		driver.findElement(By.xpath("//div[text()='6']")).click();
		driver.findElement(By.xpath("//input[@class='search-btn search-journey']")).click();
		if (driver.getTitle().equals(properties.getProperty("pagetitle"))) {
			Thread.sleep(5000);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			File per = new File("./Screenshots/BusResults.png");
			FileUtils.copyFile(temp, per);
			System.out.println("Bus results Page Screenshot Successful");
		}
		WebElement logo = driver.findElement(By.xpath("//div[@class='logoCont']"));
		File temp = logo.getScreenshotAs(OutputType.FILE);
		File per = new File("./Screenshots/Logo.png");
		FileUtils.copyFile(temp, per);
		System.out.println("Logo Screenshot Successful");
		driver.manage().window().minimize();
		driver.quit();
	}

}
