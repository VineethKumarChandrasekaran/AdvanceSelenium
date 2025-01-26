package com.tek.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class WriteDataInDataBase {

	public static void main(String[] args) throws SQLException {
	Driver driver = new Driver();
	DriverManager.registerDriver(driver);
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp10", "root", "root");
    System.out.println("===Connection Done===");
    Statement statement = connection.createStatement();
    int result = statement.executeUpdate("insert into tp10 values(2,'Vineeth Kumar C','vineethkumar2797@gmail.com',9629945555,'Vineeth','Kumar')");
    System.out.println(result);
    connection.close();
	}

}
