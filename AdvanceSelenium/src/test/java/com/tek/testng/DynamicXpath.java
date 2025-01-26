package com.tek.testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicXpath {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://www.amazon.in/");

            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.clear();
            searchBox.sendKeys("Refrigerator");
            driver.findElement(By.id("nav-search-submit-button")).click();

            List<WebElement> doubleDoorItems = driver.findElements(By.xpath("//span[contains(text(),'Double')]"));
            List<WebElement> doubleDoorPrices = driver.findElements(By.xpath("//span[contains(text(),'Double')]/../../../..//span[@class='a-price-whole']"));

            System.out.println("Number of Double Door Refrigerators: " + doubleDoorItems.size());
            System.out.println("Number of Prices Found: " + doubleDoorPrices.size());

            if (doubleDoorItems.size() == doubleDoorPrices.size()) {
                for (int i = 0; i < doubleDoorItems.size(); i++) {
                    System.out.println("Refrigerator: " + doubleDoorItems.get(i).getText());
                    System.out.println("Price: ₹" + doubleDoorPrices.get(i).getText());
                }
            } else {
                System.out.println("Mismatch between the number of refrigerators and their prices.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
