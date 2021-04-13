package com.tweetapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static String dbhost = "jdbc:mysql://localhost:3306/tweetapp";
	private static String username = "root";
	private static String password = "password-1";
	private static Connection conn;

	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try {
			conn = DriverManager.getConnection(dbhost, username, password);
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
