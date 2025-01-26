package com.tek.datadriventesting;

public class GenerateAlphanumericValueTest {

	public static void main(String[] args) {
	
		int n = 20;
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder(n);
        for(int i=0;i<n;i++) {
        	int index = (int) (characters.length()*Math.random());
        	sb.append(characters.charAt(index));
        }
        System.out.println(sb);
	}

}
