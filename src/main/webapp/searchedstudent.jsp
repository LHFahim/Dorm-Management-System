
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="CSS/list-style.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
	
		if(session.getAttribute("username") == null){
			response.sendRedirect("login.jsp");
		}
	%>
	
	
	<div class="container">
	
	<div id="header" class="one">
		<div id="title" class="flex-header">
			<h2>Welcome to Web Portal</h2>
		</div>
	</div>
	
	<div id="get-container" class="two">
			<div id="content" class="content">
				
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>	
						<th>Gender</th>
						<th>Major</th>		
						<th>Student ID</th>	
						<th>Passport</th>	
						<th>Room</th>
						<th>Birthday</th>			
						<th>Phone</th>
						<th>Action</th>
					</tr>
					<c:forEach var = "temp" items = "${STUDENT_LIST}">
						<!-- set up link for each student -->
						<c:url var="tempLink" value="ControllerServlet">
							<c:param name="command" value="LOAD"/>
							<c:param name="studentId" value="${temp.id}"/>
						</c:url>
						
						<!-- set up link to delete -->
						<c:url var="deleteLink" value="ControllerServlet">
							<c:param name="command" value="DELETE"/>
							<c:param name="studentId" value="${temp.id}"/>
						</c:url>
						
						<tr>
							<td>${temp.firstName} </td>
							<td>${temp.lastName} </td>
							<td>${temp.gender} </td>
							<td>${temp.major} </td>
							<td>${temp.sid} </td>
							<td>${temp.passport} </td>
							<td>${temp.room} </td>
							<td>${temp.dob} </td>
							<td>${temp.phone} </td>
							<td> 
								<div class="upde">
									<a href="${tempLink}">Update</a> 
									<a href="${deleteLink}">Delete</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				  <%--For displaying Previous link except for the 1st page --%>
				    <c:if test="${currentPage != 1}">
				        <td><a href="ControllerServlet?page=${currentPage - 1}">Previous</a></td>
				    </c:if>
				 
				    <%--For displaying Page numbers. 
				    The when condition does not display a link for the current page--%>
				    <table>
				        <tr>
				            <c:forEach begin="1" end="${noOfPages}" var="i">
				                <c:choose>
				                    <c:when test="${currentPage eq i}">
				                        <td>${i}</td>
				                    </c:when>
				                    <c:otherwise>
				                        <td><a href="ControllerServlet?page=${i}">${i}</a></td>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
				        </tr>
				    </table>
				     
				    <%--For displaying Next link --%>
				    <c:if test="${currentPage lt noOfPages}">
				        <td><a href="ControllerServlet?page=${currentPage + 1}">Next</a></td>
				    </c:if>
				
				
			</div>
			<div style="clear: both;"></div>
				<p>
					<a href="info.jsp">Back to main</a>
				</p>
		</div>
		
		<footer class="bottom">
            <a href="www.thoughtscloud.net">Website</a>
            <a href="https://www.youtube.com/channel/UCs-pkB03EYv8vg0luwupfCQ">Youtube</a>
            <p>Copyright &copy; Fahim Hassan 2021</p>
        </footer>
        
	</div>
	

</body>
</html>