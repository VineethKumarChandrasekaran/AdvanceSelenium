package com.tek.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ReadDataFromDataBase {

	public static void main(String[] args) throws SQLException {
		
		//Step 1: Load/Register the Database Driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//Step 2: Connect the Database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp10", "root", "root");
        System.out.println("===Connection Done===");
        
        //Step 3: Create a Statement
         Statement statement = connection.createStatement();
         
         //Step 4 : Execute the Statement
         ResultSet result = statement.executeQuery("select * from tp10");
         while (result.next()) {
			System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
		}
         
         //Step 5 : Close Connection
         connection.close();
	}

}
