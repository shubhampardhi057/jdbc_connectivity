package com.prowings.jdbc;

import java.sql.*;

public class TestConnectivityEmployee {

	public static void main(String[] args) {

		Empolyee emp1 = new Empolyee(10, "Ram", "Pune");

		Connection con = null;
		
		Statement stat = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdbconnectivity","root","Shubham@057");

			stat = con.createStatement();

			ResultSet rs = stat.executeQuery("select * from employee");

			int id;
			String name;
			String address;

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name").trim();
				address = rs.getString("address").trim();
				
				System.out.println("Id :="+ id +"  Name := "+ name + " Address :="+ address);
			}
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			 try {
	                    con.close();
	                    
	                    stat.close();
	                    
	            } catch (SQLException e) {
	                e.printStackTrace();
			

		}

	}
}
}
