
<html>

<head>
	<title>Update Students</title>
	<link type="text/css" rel="stylesheet" href="CSS/list-style.css">
	<link type="text/css" rel="stylesheet" href="CSS/mod-add.css">
</head>

<body>

	<div class="container">

		<div id="wrapper">
			<div id="header" class="one">
				<h2>Student Information Portal</h2>
			</div>
		</div>
		
		<div class="two">
			<h3>Update Students</h3>
			
			<div class="form">
			
				<form action="ControllerServlet" method="GET">
					<input type="hidden" name="command" value="UPDATE">
					<input type="hidden" name="studentId" value="${theStudent.id}">
					<table>
						<tbody>
							<tr>
								<td><label>First Name: </label></td>
								<td> <input type="text" name="firstName" value="${theStudent.firstName}"></td>
							</tr>
							<tr>
								<td><label>Last Name: </label></td>
								<td> <input type="text" name="lastName" value="${theStudent.lastName}"></td>
							</tr>
							<tr>
								<td><label>Room-type: </label></td>
								<td> <input type="text" name="room" value="${theStudent.room}"></td>
							</tr>
							<tr>
								<td><label>Phone: </label></td>
								<td> <input type="text" name="phone" value="${theStudent.phone}"></td>
							</tr>
							<tr>
								<td><label></label></td>
								<td> <input type="submit" value="Save" class="save"></td>
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
	</div>
</body>

</html>