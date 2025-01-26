package com.tek.testng;

import org.testng.annotations.Test;

public class EcommerceDemo {
  
	@Test(priority = 0, invocationCount = 1, enabled = true)
	public void register() {
		System.out.println("Register Done");
	}
	
	@Test(priority = 2, invocationCount = 1, enabled = true)
	public void myProfile() {
		System.out.println("My Profile");
	}
	
	@Test(priority = 1, invocationCount = 2, enabled = true)
	public void login() {
		System.out.println("Login Done");
	}
	
	@Test(priority = 3, invocationCount = 1, enabled = true)
	public void search() {
		System.out.println("Search the Product");
	}
	
	@Test(priority = 6, invocationCount = 1, enabled = true)
	public void wishlist() {
		System.out.println("WishList of the Products");
	}
	
	@Test(priority = 5, invocationCount = 1, enabled = true)
	public void cart() {
		System.out.println("Add the Products to Cart");
	}
	
	@Test(priority = 7, invocationCount = 1, enabled = false)
	public void seller() {
		System.out.println("Become a Seller");
	}
	
	@Test(priority = 4, invocationCount = 1, enabled = true)
	public void orders() {
		System.out.println("Your Orders");
	}
	
	public static void main(String[]args) {
		System.out.println("I'm Main Method");
	}
}
