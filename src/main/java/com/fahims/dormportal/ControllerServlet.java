package com.fahims.dormportal;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/test_dorm")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
//		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			switch(theCommand) {
				case "LIST":
					studentsList(request, response);
					break;
				case "ADD":
					addStudent(request, response);
					break;
				case "LOAD":
					loadStudent(request, response);
					break;
				case "UPDATE":
					updateStudent(request, response);
					break;
				case "DELETE":
					deleteStudent(request, response);
					break;
				case "SEARCH":
					searchStudent(request, response);
				default:
					studentsList(request, response);
			}
			
			studentsList(request, response);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}


	private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
		
		int page = 1;
        int recordsPerPage = 4;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        List <Student> students = studentDbUtil.searchStudents(name, (page-1)*recordsPerPage,
															recordsPerPage);
        
        int noOfRecords = studentDbUtil.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
        // add students to request
     	request.setAttribute("STUDENT_LIST", students);
    	request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		
		// send to updatestudent.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/searchedstudent.jsp");
		dispatcher.forward(request, response);
	}


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		// read student id from jsp page
		String id = request.getParameter("studentId");
		
		//delete student from database
		studentDbUtil.deleteStudent(id);
		
		// send back student list
		studentsList(request, response);
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		// read student info from updatestudent.jsp
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		
		String room = request.getParameter("room");
		
		String phone = request.getParameter("phone");
				
						
		//create new student object
		Student temp = new Student(id, firstName, lastName, room, phone);
		
		// perform update on db
		studentDbUtil.updateStudent(temp);
		
		// send back to list
		studentsList(request, response);
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		
		// read student id from form
		String studentId = request.getParameter("studentId");
		
		// get student from database
		Student theStudent = studentDbUtil.getStudent(studentId);
		
		// place student in request attribute
		request.setAttribute("theStudent", theStudent);
		
		// send to updatestudent.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updatestudent.jsp");
		dispatcher.forward(request, response);
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		//read student info from the form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		String sid = request.getParameter("sid");
		String passport = request.getParameter("passport");
		String room = request.getParameter("room");
		String dob = request.getParameter("dob");
		String phone = request.getParameter("phone");
		
				
		//create new student object
		Student temp = new Student(firstName, lastName, gender, major, sid,
				passport, room, dob, phone);
				
		//add new student to database
		studentDbUtil.addStudent(temp);
				
		//send the list back to main
		studentsList(request, response);
				
		
	}


	private void studentsList(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		int page = 1;
        int recordsPerPage = 4;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
		
        
		// get students from db util
		List <Student> students = studentDbUtil.getStudents((page-1)*recordsPerPage,
                											recordsPerPage);
		int noOfRecords = studentDbUtil.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
		// add students to request
		request.setAttribute("STUDENT_LIST", students);
		request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		
		//send to jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/getstudents.jsp");
		dispatcher.forward(request, response);
	}

}
