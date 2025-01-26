package com.tek.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarPopupInGoibibo {

	public static void main(String[] args) {
		String monthyear = "January 2026";
		int date = 30;
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.goibibo.com/");
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		for(;;) {
			try {
				driver.findElement(By.xpath("//div[text()='"+monthyear+"']/ancestor::div[@class='DayPicker-Month']//p[text()='"+date+"']")).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		
	}
}
}
