<%@ page import="java.util.List" %>
<%@ page import="com.bnu.wsd.sys.entity.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bnu.wsd.sys.entity.Student" %>
<%@ page import="com.bnu.wsd.sys.dao.StudentDAO" %>
<%@ page import="com.bnu.wsd.sys.dao.CourseDAO" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
	<title>Add Registration</title>

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
		<h3>Add Registration</h3>
		<%!
			StudentDAO studentDAO = new StudentDAO();
			CourseDAO courseDAO = new CourseDAO();
			List<Course> courses =null;
			List<Student> students = null;
			public void setupData() {
				try {
					courses = courseDAO.getAllCourse();
					/*for(Course c : courses){
						System.out.println(c);
					}*/
					students = studentDAO.getAllStudents();
					/*for(Student s : students){
						System.out.println(s);
					}*/
				}
				catch (SQLException e){
					e.printStackTrace();
				}
			}
		%>
		<%
			setupData();
		%>
		<form action="${context}/RegistrationController" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Registration Date:</label></td>
						<td><input type="text" name="registrationDate" /></td>
					</tr>

					<tr>
						<td><label>Student Name:</label></td>
						<td><select name="newStudent">
							<%
								for (Student student:students) {
							%>
								<option value="<%=student.getStudentId()%>">
									<%= student.getFirstName() +" ,"+student.getLastName()%>
								</option>
							<%
								}
							%>
						</select>
						</td>
					</tr>

					<tr>
						<td><label>Course Name:</label></td>
						<td><select name="newCourse">
							<%
								for (Course course:courses) {
							%>
							<option value="<%=course.getCourseId()%>">
								<%= course.getCourseCode() +" ,"+course.getCourseName()%>
							</option>
							<%
								}
							%>
						</select>
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











