<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose an option</title>
<link type="text/css" rel="stylesheet" href="CSS/login.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if(session.getAttribute("username") == null){
			response.sendRedirect("login.jsp");
		}
	%>
	
	<h1>Index page! </h1>
	Welcome ${username} <br><br>
	
	
	<a href="ControllerServlet">Get student record</a> <br><br>
	<!--  <a href="updatestudent.jsp">Update student record</a> <br><br> -->
	<a href="addstudent.jsp">Add a student</a> <br><br>
	
	<a href="searchstudent.jsp">Search a student</a> <br><br>
	
	<form action="Logout">
		<input type="submit" value="Logout">
	</form>
</body>
</html>