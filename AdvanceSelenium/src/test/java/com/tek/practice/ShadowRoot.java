package com.tek.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ShadowRoot {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoapps.qspiders.com/ui/shadow/nested?sublist=2");
        SearchContext shadow = driver.findElement(By.xpath("//form/div[1]")).getShadowRoot();
        System.out.println("Host : "+shadow);
        driver.findElement(By.xpath("//h1[text()='Login']")).click();
        Actions action = new Actions(driver);
    	action.sendKeys(Keys.TAB).perform();
    	action.sendKeys("vineethkumar2797@gmail.com").perform();
    	action.sendKeys(Keys.TAB).perform();
    	action.sendKeys("Vineeth Kumar").perform();
	}

}
