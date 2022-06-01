package com.fahims.dormportal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	String sql = "select * from login where uname=? and pass=?";
	String url = "jdbc:mysql://localhost:3306/test_dorm?useSSL=false&serverTimezone=UTC";
	String username = "root";
	String password = "pass";
	
	public boolean check(String uname, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(url, username, password);
			PreparedStatement myStmt =  myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setString(2, pass);
			
			ResultSet myRs = myStmt.executeQuery();
			if(myRs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
