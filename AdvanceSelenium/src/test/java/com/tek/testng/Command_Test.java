package com.tek.testng;

import org.testng.annotations.Test;

public class Command_Test {
	@Test
	public void commands() {
     String url = System.getProperty("url");
     System.out.println(url);
	}
}
