package com.tek.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CalendarPopupInRedBus {

	public static void main(String[] args) {
		String month = "Mar 2026";
		int date = 15;	
		int count = 0;
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.redbus.in/");
        driver.findElement(By.xpath("//i[@class='sc-cSHVUG NyvQv icon icon-datev2']")).click();
        Actions action = new Actions(driver);
        while(true) {
        String actmonth = driver.findElement(By.xpath("//div[@style='flex-grow: 2; font-size: 0.875rem;']")).getText();
        if(actmonth.contains(month)) {
            driver.findElement(By.xpath("//div[@style='flex-grow: 2; font-size: 0.875rem;']/ancestor::div[@class='sc-jzJRlG hrJoeL']//span[text()='"+date+"']")).click();
            	break;
            }else {
            	if(count==0) {
            	action.click(driver.findElement(By.xpath("//*[@id='Layer_1']/*[name()='path']"))).perform();
            	}else {
            		action.click(driver.findElement(By.xpath("//*[@id='Layer_1']/*[@d='M25.53,0.13A2.49,2.49,0,0,1,27.3.86L72.21,45.77a6,6,0,0,1,0,8.49l-45,45a2.5,2.5,0,1,1-3.54-3.54l45-45a1,1,0,0,0,0-1.42L23.76,4.39A2.5,2.5,0,0,1,25.53.13Z']"))).perform();
            	}
              }
        count++;
            }
        	
        }
	}
