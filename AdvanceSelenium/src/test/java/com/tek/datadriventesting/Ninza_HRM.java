package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Driver;

public class Ninza_HRM {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ChromeOptions chromeoptions;
	public static FirefoxOptions firefoxoptions;
	public static EdgeOptions edgeoptions;
	public static Connection connection;

	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		FileInputStream file = new FileInputStream("./src/test/resource/ninza_hrm.properties");
		Properties properties = new Properties();
		properties.load(file);
		if (properties.getProperty("browser").equals("chrome")) {
			chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeoptions);
		} else if (properties.getProperty("browser").equals("firefox")) {
			firefoxoptions = new FirefoxOptions();
			firefoxoptions.addPreference("dom.webnotifications.enabled", false);
			firefoxoptions.addPreference("dom.push.enabled", false);
			driver = new FirefoxDriver(firefoxoptions);
		} else {
			edgeoptions = new EdgeOptions();
			edgeoptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeoptions);
		}
		String projectname = (properties.getProperty("projectname") + randomInt);
		Boolean flag = false;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get(properties.getProperty("url"));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(properties.getProperty("username"));
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		wait.until(ExpectedConditions.urlToBe(properties.getProperty("dashboardurl")));
		if (driver.getCurrentUrl().equals(properties.getProperty("dashboardurl"))) {
			System.out.println("Sign In Successful");
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			wait.until(ExpectedConditions.urlToBe(properties.getProperty("projectsurl")));
			if (driver.getCurrentUrl().equals(properties.getProperty("projectsurl"))) {
				driver.findElement(By.xpath("//span[text()='Create Project']")).click();
				driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectname);
				driver.findElement(By.xpath("//input[@name='createdBy']"))
						.sendKeys(properties.getProperty("projectmanager"));
				Select select = new Select(driver.findElement(By.xpath("//label[text()='Project Status* ']/following-sibling::select[@name='status']")));
				select.selectByValue("Created");
				driver.findElement(By.xpath("//input[@value='Add Project']")).click();
	            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='Toastify__toast-body']"))));
				if ((driver.findElement(By.xpath("//div[@class='Toastify__toast-body']")).getText()).equals("Project "+projectname+" Successfully Added")) {
					System.out.println(projectname + "created");
					try {
						Driver driver = new Driver();
						DriverManager.registerDriver(driver);
						connection = DriverManager.getConnection(properties.getProperty("databaseurl"), properties.getProperty("databaseusername"), properties.getProperty("databasepassword"));
						System.out.println("DataBase Connection Done");
						Statement statement = connection.createStatement();
						ResultSet result = statement.executeQuery("select * from project");
						while (result.next()) {
							if (result.getString(4).equals(projectname)) {
								flag = true;
								System.out.println(projectname + " is available in the Database");
							}
						}
						if (flag == false) {
							System.out.println(projectname + " is not available in the Database");
						}
					} catch (Exception e) {
						System.out.println("Exception has been handled");
					} finally {
						connection.close();
						System.out.println("Database Connection Closed");
					}
				} else {
					System.out.println(projectname + " is not created");
				}
			}
		}
		driver.manage().window().minimize();
		driver.quit();
	}
}
