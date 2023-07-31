package com.prowings.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	public StudentRepository() {
		super();
	}

	public List<Student> getAllStudent() {

		List<Student> result = new ArrayList<>();

		Student s = new Student();

		Statement stat = null;
		ResultSet rs = null;

		try {
			Connection con = MyConnection.getConnection();

			stat = con.createStatement();
			rs = stat.executeQuery("select * from student");

			int roll;
			String name;
			String address;

			while (rs.next()) {

				roll = rs.getInt("roll");
				name = rs.getString("name").trim();
				address = rs.getString("address").trim();

				System.out.println("Roll :=" + roll + "  Name :=" + name + "   Address :=" + address);

				s.setRoll(roll);
				s.setName(name);
				s.setAddress(address);

				result.add(s);
			}
		} catch (SQLException e) {
			System.out.println("error while retriving data");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		MyConnection.closeConnection();

		return result;
	}

	public Student getStudentByRoll(int roll) {

		Student s = new Student();

		Statement stat = null;

		ResultSet rs = null;

		try {

			Connection con = MyConnection.getConnection();

			stat = con.createStatement();
			rs = stat.executeQuery("select * from student where roll =" + roll);

			int roll1;
			String name;
			String address;

			if (rs.next()) {

				roll = rs.getInt("roll");
				name = rs.getString("name").trim();
				address = rs.getString("address").trim();

				System.out.println("Roll :=" + roll + "  Name :=" + name + "   Address :=" + address);

				s.setRoll(roll);
				s.setName(name);
				s.setAddress(address);
			} else {
				System.out.println("no student record found with " + roll + " number");

			}
		} catch (SQLException e) {
			System.out.println("error while retriving data");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConnection.closeConnection();
		}
		return s;

	}

	public boolean addNewStudent(Student std) {

//		Statement stat = null;

		PreparedStatement stat = null;
		
		int  res =0;
		
		try {

			Connection con = MyConnection.getConnection();

//			stat = con.createStatement();
			
			stat = con.prepareStatement("insert into student values(?,?,?)");
			
			stat.setInt(1,std.getRoll());
			stat.setString(2,std.getName());
			stat.setString(3,std.getAddress());
			
			res = stat.executeUpdate();
			
			if(res == 1) {
				System.out.println("Student Added Successfully");
			}
			else
			{
				System.out.println("Student Not Added ");
			}
			
			


//			res = stat.execute("insert into student values (" + std.getRoll() + "," + "\'" + std.getName() + "\'" + ","
//					+ "\'" + std.getAddress() + "\'" + ")");
			
			
		} catch (Exception e) {
			System.out.println("error while inserting the record");
			e.printStackTrace();
		} finally {

			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConnection.closeConnection();
		}

		return res == 1 ? true : false ;

	}

	public boolean deleteStudentByRoll(int roll) {


		String deleteQuery = "delete from student where roll = "+roll;
		
		Statement stat = null;

		try {

			Connection con = MyConnection.getConnection();

			stat = con.createStatement();

			int deleteRes = stat.executeUpdate(deleteQuery);
			
			if(deleteRes == 1) {
				System.out.println("Student Deleted Successfully !!!");
				return true;
			}
			else
			{
				System.out.println("Student Not Deleted Successfully !!!");
				
			}
			
			

		} catch (Exception e) {
			System.out.println("error while inserting the record");
			e.printStackTrace();
		} finally {

			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConnection.closeConnection();
		}

		return false;

	}

	public Student updateStudent(int roll,Student std) {

		
		String updateQuery = "update student set name = "+"\'" +std.getName()+"\'" +"," +"address ="+"\'" +std.getAddress() +"\'" +" where roll ="+ roll;

		System.out.println("UPDATE QUERY :"+updateQuery);
		
		Statement stat = null;
		ResultSet rs = null;


		try {

			Connection con = MyConnection.getConnection();

			stat = con.createStatement();
			
			int updateRes = stat.executeUpdate(updateQuery);
			
			if(updateRes == 1) {
				
				System.out.println("Student Updated Successfully");
			}
			else
			{
				System.out.println("Student Not Updated Successfully");
			}
			
			
		} catch (Exception e) {
			System.out.println("error while inserting the record");
			e.printStackTrace();
		} finally {

			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConnection.closeConnection();
		}

		return null;

	}
	
	// stored procedure execution using callable statement example
	
	public void testCallableStat() {
		
		CallableStatement stat = null;
		
		try {
			
			Connection con = MyConnection.getConnection();
			
			stat = con.prepareCall("{call getAllStudentsWithGivenAddr(?)}");
			
			stat.setString(1,"Pune");
			
			ResultSet rs = stat.executeQuery();
		
		
		while (rs.next()) {
			int rno = rs.getInt("roll");
			String nm = rs.getString("name").trim();
			String addr = rs.getString("address").trim();

			System.out.println("Roll : " + rno + "    Name : " + nm + "    Address : " + addr);
		}

		
	} catch (Exception e) {
		System.out.println("error while inserting the record");
		e.printStackTrace();
	}
	finally {
		
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyConnection.closeConnection();
	}
	}

}
