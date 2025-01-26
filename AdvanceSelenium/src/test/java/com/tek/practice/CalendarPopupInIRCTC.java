package com.tek.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CalendarPopupInIRCTC {

	public static void main(String[] args) {
		String month = "February2025";
		int date = 14;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.irctc.co.in/nget/train-search");
        driver.findElement(By.xpath("//span[@class='ng-tns-c58-10 ui-calendar']")).click();
        while(true) {
        	String actmonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-title ng-tns-c58-10']")).getText();
        	if(actmonth.equalsIgnoreCase(month)) {
        driver.findElement(By.xpath("//div[@class='ui-datepicker-title ng-tns-c58-10']/ancestor::div[@class='ui-datepicker-group ui-widget-content ng-tns-c58-10 ng-star-inserted']//a[text()="+date+"]")).click();
        	break;
        }
				driver.findElement(By.xpath("//span[@class='ui-datepicker-next-icon pi pi-chevron-right ng-tns-c58-10']")).click();
			
        }
	}

}
