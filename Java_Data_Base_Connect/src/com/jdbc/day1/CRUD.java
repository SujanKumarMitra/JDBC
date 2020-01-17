package com.jdbc.day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CRUD {
	public Connection con = null;
	public PreparedStatement ps = null;
	DBConnection dc = new DBConnection();
	private static Scanner scan;
	public void dataInsert()  {
		try {
//			Take user input variable
			scan = new Scanner(System.in);
			
			System.out.println("Enter your name");
			String name = scan.next();
			
			System.out.println("Enter your email:");
			String email = scan.next();
			
			System.out.println("Enter your phone number:");
			long number = scan.nextLong();
//			Call dbconnection
			con = dc.dbConnect();
			
//			Execute SQL query
			String insertQuery = "INSERT INTO student VALUES(DEFAULT, ?, ?, ?)";
			ps = con.prepareStatement(insertQuery);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setLong(3, number);
			
			int i = ps.executeUpdate();
			
//			Check data entry
			if(i>0) {
				System.out.println("Data inserted sucessfully");
			}
			else {
				System.out.println("Transaction failed!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void dataDelete() {
		try {
			scan = new Scanner(System.in);
//			Take user input
			System.out.println("Enter your email which your want to delete:");
			String email = scan.next();
//			Call connection
			con = dc.dbConnect();
//			Execute SQL query
			String deleteQuery = "DELETE FROM student WHERE semail=?";
			ps = con.prepareStatement(deleteQuery);
			ps.setString(1, email);
			
			int i = ps.executeUpdate();
//			Check data entry
			if(i>0) {
				System.out.println("Data deleted sucessfully");
			}
			else {
				System.out.println("Transaction failed!");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void dataUpdate() {
		try {
			scan = new Scanner(System.in);
//			Take user input
			System.out.println("Enter your name to find :");
			String name = scan.next();
			System.out.println("Enter your email-id which you want to update:");
			String email = scan.next();
			System.out.println("Enter your mobile no. to update:");
			long number = scan.nextLong();
//			Call connection
			con = dc.dbConnect();
//			Execute SQL query
			String dataUpdate = "UPDATE student SET semail =?, sphone=? WHERE sname=?";
			ps = con.prepareStatement(dataUpdate);
			ps.setString(1,email);
			ps.setLong(2, number);
			ps.setString(3, name);
			
			int i = ps.executeUpdate();
//			Check data entry
			if(i>0) {
				System.out.println("Data updated sucessfully");
			}
			else {
				System.out.println("Transaction failed!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	day 2
	public void selectData() {
		try {
			scan = new Scanner(System.in);
			System.out.print("Enter your name:");
			String name = scan.next();
			con = dc.dbConnect();
			String selectQuery = "SELECT sname, semail, sphone FROM student where sname=?";
			ps = con.prepareStatement(selectQuery);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Name :"+rs.getString(1));
				System.out.println("Email :"+rs.getString(2));
				System.out.println("Phone :"+rs.getLong(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchData() {
		try {
			scan = new Scanner(System.in);
//			System.out.print("Enter your name:");
//			String name = scan.next();
			con = dc.dbConnect();
			String selectQuery = "SELECT * FROM student";
			ps = con.prepareStatement(selectQuery);
//			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Name :"+rs.getString(2));
				System.out.println("Email :"+rs.getString(3));
				System.out.println("Phone :"+rs.getLong(4));
				System.out.println("----------------------");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("1.Insert\n2.Delete\n3.Update\n4.Select\n5.Fetch Data\n6.Exit");
		CRUD cr = new CRUD();
		while (true) {
			scan = new Scanner(System.in);
			int ch = scan.nextInt();
			switch(ch) {
			case 1:
				cr.dataInsert();
				break;
			case 2:
				cr.dataDelete();
				break;
			case 3:
				cr.dataUpdate();
				break;
			case 4:
				cr.selectData();
				break;
			case 5:
				cr.fetchData();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Wrong choice!");
			}
		}

	}

}
