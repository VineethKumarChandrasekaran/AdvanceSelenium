package com.tek.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class UnitTest_CheckProjectDataBase {
	@Test
	public static void checkDatabase() throws SQLException {
		Connection connection = null;
		try {
		String name = "Vineeth Kumar C";
		 Boolean flag = false;
		 Driver driver = new Driver();
		 DriverManager.registerDriver(driver);
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp10", "root", "root");
		 Statement statement = connection.createStatement();
		 ResultSet result = statement.executeQuery("select * from tp10");
		 while (result.next()) {
			String actualname = result.getString(2);
			if(name.equals(actualname)) {
				flag = true;
				System.out.println(name +" is available");
			}
		}
      if(flag==false) {
			 System.out.println(name +" is not available");
			 Assert.fail();
		 }
		}catch (Exception e) {
			System.out.println("Handled Exception");
		}finally {
			connection.close();
			System.out.println("===Connection Closed===");
		}
	}
	 
}
