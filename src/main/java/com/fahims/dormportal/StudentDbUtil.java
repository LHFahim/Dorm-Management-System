package com.fahims.dormportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;
	private int noOfRecords;
	
	public StudentDbUtil(DataSource ds) {
		dataSource = ds;
	}
	
	public List<Student> getStudents(int offset, int noOfRecords) throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		
		// setting up jdbc objects to fetch data from db
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
//		String tempSql = "select * from students %d OFFSET %d";
		
		try {
			// get a connection, execute query, process result
			myConn = dataSource.getConnection();
			
			String sql = "select SQL_CALC_FOUND_ROWS * from students limit "
	                + offset + ", " + noOfRecords;
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			
			while(myRs.next()) {
				//retrieve info
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String gender = myRs.getString("gender");				
				String major = myRs.getString("major");
				String sid = myRs.getString("sid");
				String passport = myRs.getString("passport");
				String room = myRs.getString("room");
				String dob = myRs.getString("dob");
				String phone = myRs.getString("phone");
				
				
				//add retrieved info to students list here
				Student temp = new Student(id, firstName, lastName, gender, major,
								sid, passport, room, dob, phone);
				students.add(temp);
			}
			
			myRs.close();
            
            myRs = myStmt.executeQuery("SELECT FOUND_ROWS()");
            if(myRs.next())
                this.noOfRecords = myRs.getInt(1);
			
			
			return students;
		}
		finally {
			// close jdbc objects
			close(myConn, myStmt, myRs);
		}
			
	}
	
	public int getNoOfRecords() {
        return noOfRecords;
    }
	

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addStudent(Student temp) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "insert into students "
						+ "(firstName, lastName, gender, major, sid, passport, room, dob, phone) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, temp.getFirstName());
			myStmt.setString(2, temp.getLastName());
			myStmt.setString(3, temp.getGender());
			myStmt.setString(4, temp.getMajor());
			myStmt.setString(5, temp.getSid());
			myStmt.setString(6, temp.getPassport());
			myStmt.setString(7, temp.getRoom());
			myStmt.setString(8, temp.getDob());
			myStmt.setString(9, temp.getPhone());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public Student getStudent(String studentId) throws Exception {
		Student theStudent = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stuid;
		
		try {
			//convert string studentId to int
			stuid = Integer.parseInt(studentId);
			
			// get connection to database, 
			//prepare statement and execute query
			
			conn = dataSource.getConnection();
			
			String sql = "select * from students where id=?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, stuid);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				//retrieve info
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");				
				String major = rs.getString("major");
				String sid = rs.getString("sid");
				String passport = rs.getString("passport");
				String room = rs.getString("room");
				String dob = rs.getString("dob");
				String phone = rs.getString("phone");
				
				
				//add retrieved info to students list here
				theStudent = new Student(id, firstName, lastName, gender, major,
								sid, passport, room, dob, phone);
			}
			else {
				throw new Exception("Failed to find student with ID " + stuid);
			}
			
			return theStudent;
		}
		finally {
			close(conn, stmt, rs);
		}
	}

	public void updateStudent(Student temp) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "update students "
						+"set firstName=?, lastName=?, room=?, phone=? "
						+"where id = ?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, temp.getFirstName());
			myStmt.setString(2, temp.getLastName());
			myStmt.setString(3, temp.getRoom());
			myStmt.setString(4, temp.getPhone());
			myStmt.setInt(5, temp.getId());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(String id) throws Exception{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			int sid = Integer.parseInt(id);
			
			conn = dataSource.getConnection();
			
			String sql = "delete from students where id=?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, sid);
			
			stmt.execute();
		}
		finally {
			close(conn, stmt, null);
		}
	}

	public List<Student> searchStudents(String name, int offset, int noOfRecords) throws Exception {
		
		List<Student> students = new ArrayList<>();
	
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			
			
			conn = dataSource.getConnection();
			
			
			String sql = "select SQL_CALC_FOUND_ROWS * from students where firstName like ? or lastName like ? limit "
						+ offset + ", " + noOfRecords ;
			
			stmt = conn.prepareStatement(sql);
			
//	
			stmt.setString(1, "%" + name + "%");
			stmt.setString(2, "%" + name + "%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				//retrieve info
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");				
				String major = rs.getString("major");
				String sid = rs.getString("sid");
				String passport = rs.getString("passport");
				String room = rs.getString("room");
				String dob = rs.getString("dob");
				String phone = rs.getString("phone");
				
				
				//add retrieved info to students list here
				Student theStudent = new Student(id, firstName, lastName, gender, major,
								sid, passport, room, dob, phone);
				students.add(theStudent);
			}
			
			rs.close();
            
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
			
			
			return students;
			
			
			
		}
		finally {
			close(conn, stmt, rs);
		}
		
	}
	
}
