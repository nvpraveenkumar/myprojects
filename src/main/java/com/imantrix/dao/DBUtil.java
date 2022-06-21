package com.imantrix.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	// JDBC driver name and database URL
		private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		private static final String DB_URL = "jdbc:mysql://localhost/test";

		// Database credentials
		private static final String USER = "root";
		private static final String PASS = "root";
		
		private static Connection conn = null;
		
		private DBUtil() {
			
		}
	
	
	public static Connection getConnection() {
		if(conn!=null) {
			return conn;
		}
		try{
			
			Class.forName(JDBC_DRIVER);
			System.out.println("connecting to a selected database");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("connected database successfully");
			
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	}
