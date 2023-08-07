<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>

<head>
	<title>Update Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Course Registration App</h2>
			<c:set var="context" value="${pageContext.request.contextPath}" />
		</div>
	</div>
	
	<div id="container">
		<h3>Update Course</h3>
		
		<form action="StudentController" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="studentId" value="${THE_STUDENT.studentId}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName"
								   value="${THE_STUDENT.firstName}" /></td>
					</tr>

					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName"
								   value="${THE_STUDENT.lastName}" /></td>
					</tr>

					<tr>
						<td><label>Address:</label></td>
						<td><input type="text" name="address"
								   value="${THE_STUDENT.address}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="StudentController">Back to List</a>
		</p>
	</div>
</body>

</html>











