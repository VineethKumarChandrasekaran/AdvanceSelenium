package com.tek.practice;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ucobank.com/en/");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		System.out.println("Count of the Links : " + links.size());
		for (WebElement link : links) {
			@SuppressWarnings("deprecation")
			String urllink = link.getAttribute("href");
			try {
				@SuppressWarnings("deprecation")
				URL url = new URL(urllink);
				HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
				int code = http.getResponseCode();
				if (code >= 400) {
					System.out.println(urllink + " ====> " + code);
				}
			} catch (Exception e) {

			}

		}
	}
}
