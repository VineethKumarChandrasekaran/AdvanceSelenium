package com.tek.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class Ninza_HRM_ {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ChromeOptions chromeoptions;
	public static FirefoxOptions firefoxoptions;
	public static EdgeOptions edgeoptions;
	public static Connection connection;
	public static void main(String[] args) throws IOException, SQLException {
			Random random = new Random();
			int randomInt = random.nextInt(1000);
			FileInputStream file = new FileInputStream("./src/test/resource/ninza_hrm.properties");
			Properties properties = new Properties();
			properties.load(file);
			String projectname = (properties.getProperty("projectname") + randomInt);
			try {
				Driver driverdriver = new Driver();
				DriverManager.registerDriver(driverdriver);
				connection = DriverManager.getConnection(properties.getProperty("databaseurl"), properties.getProperty("databaseusername"), properties.getProperty("databasepassword"));
				System.out.println("DataBase Connection Done");
				Statement statement = connection.createStatement();
				int result = statement.executeUpdate("insert into project values("+projectname+",'Vineeth Kumar C','06/12/2024',"+projectname+",'Created',0)");
					if (result==1) {
						System.out.println(projectname + " is added in the Database");
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
								Select select = new Select(driver.findElement(By.xpath("//div[@class='col-sm-6']/select[@class='form-control']")));
								select.selectByVisibleText("Search by Project Name");
								driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(projectname);
								wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//tr[@class='tr']/td)[2]"))));
								if((driver.findElement(By.xpath("(//tr[@class='tr']/td)[2]")).getText()).equalsIgnoreCase(projectname)) {
									System.out.println(projectname + " is Displayed in the Application");
								}else {
									System.out.println(projectname + " is Not Displayed in the Application");
								}
							}

					}else {
					System.out.println(projectname + " is not added in the Database");
				}
			} 
			}catch (Exception e) {
				System.out.println("Exception has been handled");
			} finally {
				connection.close();
				System.out.println("Database Connection Closed");
			}
				}
	}
