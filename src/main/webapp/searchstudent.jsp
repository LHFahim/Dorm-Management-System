<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
<link type="text/css" rel="stylesheet" href="CSS/mod-add.css">
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
			<div>
				<h2>Students Information Portal</h2>
			</div>
		</div>
		
		<div id="get-container" class="two">
			<h3 class="second-header">Search Students</h3>
			
			<div class="form">
				<form action="ControllerServlet" method="GET">
					<input type="hidden" name="command" value="SEARCH">
					<table>
						<tbody>
							<tr>
								<td><label>Name: </label></td>
								<td> <input type="text" name="name"></td>
							</tr>
							
							<!--  <tr>
								<td><label>Student ID: </label></td>
								<td> <input type="number" min="7" minlength="7" maxlength="7" name="sid"></td>
							</tr>
							<tr>
								<td><label>Passport: </label></td>
								<td> <input type="text" name="passport"></td>
							</tr>-->
							
							<tr>
								<td><label></label></td>
								<td> <div class="save-button"><input type="submit" value="Search" class="save"> </div> </td>
							</tr>
						</tbody>
					</table>
				</form>
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