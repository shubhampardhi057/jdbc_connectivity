package com.prowings.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectivityStudent {

	public static void main(String[] args) {

		Student s1 = new Student(101, "Shubham", "Pune");

		Connection con = null;

		Statement stat = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seconddbconnectivity", "root",
					"Shubham@057");

			stat = con.createStatement();

			ResultSet rs1 = stat.executeQuery("select * from student");

			int roll;
			String name;
			String address;

			while (rs1.next()) {

				roll = rs1.getInt("roll");
				name = rs1.getString("name").trim();
				address = rs1.getString("address").trim();

				System.out.println("Roll :=" + roll + "  Name :=" + name + "   Address :=" + address);
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
