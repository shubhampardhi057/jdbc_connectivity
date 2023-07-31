package com.prowings.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
	
	static Connection  con = null;
	
	public static Connection getConnection() {
		
		System.out.println("Creating connection object !!!");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Properties myProp = new Properties();
			myProp.load(new FileInputStream("E:\\jdbc_connectivity\\db.properties"));
			
			String url = myProp.getProperty("dburl");
			String usr = myProp.getProperty("dbusername");
			String pwd = myProp.getProperty("dbpwd");
			
			con = DriverManager.getConnection(url,usr,pwd);
			
			System.out.println("Connection Object Created Successfully !!!");
		}
		catch (Exception e) {
			System.out.println("error in creating connection object!!");
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public static void closeConnection() {
		
		try {
			System.out.println("Closing the Connection !!!");
			con.close();
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
	}
	

}
