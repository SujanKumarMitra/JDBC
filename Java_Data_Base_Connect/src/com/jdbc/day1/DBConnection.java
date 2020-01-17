package com.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection con = null;
	public Connection dbConnect() {
		try {
			System.out.println("Connecting...");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_tutorial", "root", "");
			System.out.println("Connected!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		DBConnection db = new DBConnection();
		db.dbConnect();

	}

}
