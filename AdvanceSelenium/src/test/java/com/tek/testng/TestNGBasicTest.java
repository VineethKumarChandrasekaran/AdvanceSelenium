package com.tek.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGBasicTest {
	
	@Test(priority = 0)
	public void userregistration() {
		System.out.println("User Registration Done");
	}
	
	@Test(priority = 1,dependsOnMethods = "userregistration")
	public void userlogin() {
		System.out.println("User Login Done");
		Boolean login = true;
		Assert.assertEquals(login, false);
	}
	
	@Test(priority = 2,dependsOnMethods = "userlogin",invocationCount = 10)
	public void userdashboard() {
		System.out.println("User Dashboard Displayed");
	}
	
	@Test(priority = 3,dependsOnMethods = "userlogin",enabled = false)
	public void userlogout() {
		System.out.println("User Logout Done");
	}

}
