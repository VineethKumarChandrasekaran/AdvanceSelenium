package com.tek.testng;

import org.testng.annotations.Test;

public class DataProvider {
	
	@Test(dataProvider = "logindata")
	public void login(String username, String password, Long mobile, String email) {
		System.out.println("Login Username : "+username);
		System.out.println("Login Password : "+password);
		System.out.println("User Mobile : "+mobile);
		System.out.println("User Email : "+email);
	}
	

	@org.testng.annotations.DataProvider
	public Object[][] logindata(){
		return new Object[][] {
			{"John Wick","Johnwick@123",9876543210L,"johnwick@gmail.com"},
			{"Thomas Shelby","Thomasshelby@123",9876543211L,"thomasshelby@gmail.com"},
			{"James Bond","Jamesbond@123",9876543212L,"jamesbond@gmail.com"}
			};
	}
}
