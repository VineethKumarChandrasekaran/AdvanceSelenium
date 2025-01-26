package com.tek.datadriventesting;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpeningBrowserTest {
	public static WebDriver driver;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the Browser Name : ");
		String browser = s.nextLine();
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid Browser");
		}
	}

}
