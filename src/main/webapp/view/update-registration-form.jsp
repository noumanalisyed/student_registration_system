<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
	<title>Update Registration</title>

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
		<h3>Update Registration</h3>
		
		<form action="RegistrationController" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="registrationId" value="${THE_REGISTRATION.registrationId}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Registration Date:</label></td>
						<td><input type="text" name="registrationDate"
								   value="${THE_REGISTRATION.regDate}" /></td>
					</tr>

					<tr>
						<td><label>Student Name:</label></td>
						<sql:setDataSource var="studentList"
										   driver="com.mysql.jdbc.Driver"
										   url="jdbc:mysql://localhost:3306/student_db"
										   user="admin"
										   password="admin"/>

						<sql:query var="STUDENT_LIST" dataSource="${studentList}">
							SELECT * FROM student
						</sql:query>
						<td><select name="studentId">
							<option value="${THE_REGISTRATION.studentId}" selected>${THE_REGISTRATION.studentId}</option>
							<c:forEach items="${STUDENT_LIST}" var="student">
								<c:if test="${student.studentId} != ${THE_REGISTRATION.studentId}>
									<c:option value="${student.studentId}">
										<c:out value="${student.firstName}, ${student.lastName}"/>
									</c:option>
								</c:if>
							</c:forEach>
						</select>
						</td>
					</tr>

					<tr>
						<td><label>Course Name:</label></td>
						<sql:setDataSource var="courseList"
										   driver="com.mysql.jdbc.Driver"
										   url="jdbc:mysql://localhost:3306/student_db"
										   user="admin"
										   password="admin"/>

						<sql:query var="COURSE_LIST" dataSource="${courseList}">
							SELECT * FROM course
						</sql:query>
						<td><select name="courseId">
							<c:forEach items="${COURSE_LIST.rows}" var="course">
								<option value="${course.courseId}">
									<c:out value="${course.courseCode}, ${course.courseName}"/></option>
							</c:forEach>
						</select>
						</td>
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
			<a href="${context}/RegistrationController">Back to List</a>
		</p>
	</div>
</body>

</html>











