<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<title>Course Registration App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Course Registration App</h2>
			<c:set var="context" value="${pageContext.request.contextPath}" />
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			<input type="button" value="Add Registration"
				   onclick="window.location.href='view/add-registration-form.jsp'; return false;"
				   class="add-student-button"/>
			<table>
			
				<tr>
					<th>Registration Date</th>
					<th>Course </th>
					<th>Student</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempRegistration" items="${REGISTRATION_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="RegistrationController">
						<c:param name="command" value="LOAD" />
						<c:param name="registrationId" value="${tempRegistration.registrationId}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="RegistrationController">
						<c:param name="command" value="DELETE" />
						<c:param name="registrationId" value="${tempRegistration.registrationId}" />
					</c:url>
																		
					<tr>
						<td> ${tempRegistration.regDate} </td>
						<td> ${tempRegistration.course.courseName} </td>
						<td> ${tempRegistration.student.firstName} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this registration?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
	</br>
	</br>
	<p>

	</p>
	<div id="wrapper-new">
		<div id="header">
			<h2>Navigation </h2>
		</div>
	</div>
	<div id="content-new">
		<table>
			<tr>
				<td>Courses {Add, Update, Delete}</td>
				<td><a href="${context}/CourseController">Courses</a></td>
			</tr>
			<tr>
				<td>Registrations {Add, Update, Delete}</td>
				<td><a href="${context}/RegistrationController">Registrations</a></td>
			</tr>
			<tr>
				<td>Students {Add, Update, Delete}</td>
				<td><a href="${context}/StudentController">Students</a></td>
			</tr>
		</table>
	</div>
</body>


</html>








