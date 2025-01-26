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

public class BrokenImage {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ucobank.com/en/");
		List<WebElement> images = driver.findElements(By.xpath("//img"));
		System.out.println("Count of the Images : " + images.size());
		for (WebElement image : images) {
			@SuppressWarnings("deprecation")
			String img = image.getAttribute("src");
			try {
				@SuppressWarnings("deprecation")
				URL url = new URL(img);
				HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
				int code = http.getResponseCode();
				if (code >= 400) {
					System.out.println(img + " ====> " + code);
				}
			} catch (Exception e) {

			}

		}
	}
}
