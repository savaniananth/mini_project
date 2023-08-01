package com.example.demo.Healthcare;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static final String url = "jdbc:mysql://localhost:3306/hospital?createDatabaseIfNotExists=true";
	private static final String user = "root";
	private static final String password = "Tiger@123";
	private static final String JDBC_URL = "com.mysql.cj.jdbc.Driver";
	
	static Connection connection;
	
	public static Connection connect() {
		
		try{
			Class.forName(JDBC_URL);
			connection = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
