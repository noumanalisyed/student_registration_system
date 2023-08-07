package com.bnu.wsd.sys.controller;

import com.bnu.wsd.sys.dao.CourseDAO;
import com.bnu.wsd.sys.dao.RegistrationDAO;
import com.bnu.wsd.sys.dao.StudentDAO;
import com.bnu.wsd.sys.entity.Course;
import com.bnu.wsd.sys.entity.Registration;
import com.bnu.wsd.sys.entity.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/RegistrationController")
public class RegistrationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RegistrationDAO registrationDAO;
	Registration theRegistration;
	StudentDAO studentDAO;
	CourseDAO courseDAO;
	Student student;
	Course course;

	public RegistrationControllerServlet(){
	    try {
            init();
        }catch (ServletException r){
	        r.getMessage();
        }
    }
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			registrationDAO = new RegistrationDAO();
			theRegistration  = new Registration();
			courseDAO = new CourseDAO();
			studentDAO = new StudentDAO();
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listRegistrations(request, response);
				break;
				
			case "ADD":
				addRegistration(request, response);
				break;
				
			case "LOAD":
				loadRegistration(request, response);
				break;
				
			case "UPDATE":
				updateRegistration(request, response);
				break;
			
			case "DELETE":
				deleteRegistration(request, response);
				break;
				
			default:
				listRegistrations(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteRegistration(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theRegistrationId = request.getParameter("registrationId");
		
		// delete student from database
		theRegistration = registrationDAO.getRegistration(theRegistrationId);
		course = courseDAO.getCourse(String.valueOf(theRegistration.getCourse().getCourseId()));
		student = studentDAO.getStudent(String.valueOf(theRegistration.getStudent().getStudentId()));
		course.removeRegistration(theRegistration);
		student.removeRegistration(theRegistration);
		registrationDAO.deleteRegistration(theRegistrationId);
		
		// send them back to "list students" page
		listRegistrations(request, response);
	}

	private void updateRegistration(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("registrationId"));
		String registrationDate = request.getParameter("registrationDate");
		String courseId = request.getParameter("courseId");
		String studentId = request.getParameter("studentId");

		course = courseDAO.getCourse(courseId);
		student = studentDAO.getStudent(studentId);

		// create a new student object
		theRegistration = new Registration(id, registrationDate, course, student);
		course.addRegistration(theRegistration);
		student.addRegistration(theRegistration);
		// perform update on database
		registrationDAO.updateRegistration(theRegistration);
		
		// send them back to the "list students" page
		listRegistrations(request, response);
		
	}

	private void loadRegistration(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String registrationId = request.getParameter("registrationId");
		
		// get student from database (db util)
		System.out.println("Registration Id  : "+registrationId);
		Registration theRegistration = registrationDAO.getRegistration(registrationId);
		
		// place student in the request attribute
		List<Course> courseList = courseDAO.getAllCourse();
		List<Student> studentList = studentDAO.getAllStudents();
		// add students to the request
		request.setAttribute("STUDENT_LIST",studentList);
		request.setAttribute("COURSE_LIST",courseList);

		request.setAttribute("THE_REGISTRATION", theRegistration);
		
		// send to jsp page: update-course-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/view/update-registration-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String registrationDate = request.getParameter("registrationDate");
		String studentId = request.getParameter("newStudent");
		String courseId = request.getParameter("newCourse");

		course = courseDAO.getCourse(courseId);
		student = studentDAO.getStudent(studentId);
		// create a new student object
		theRegistration = new Registration(registrationDate,course,student);
		//theRegistration.getCourse().addRegistration(theRegistration);
		//theRegistration.getStudent().addRegistration(theRegistration);
		// add the student to the database
		registrationDAO.insert(theRegistration);
				
		// send back to main page (the student list)
		listRegistrations(request, response);
	}

	private void listRegistrations(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// get students from db util
		List<Registration> registrations = registrationDAO.getAllRegistration();
		List<Course> courseList = courseDAO.getAllCourse();
		List<Student> studentList = studentDAO.getAllStudents();
		// add students to the request
		System.out.println("Registration List Size "+registrations.size());
		System.out.println("Student List Size "+studentList.size());
		System.out.println("Course List Size "+courseList.size());
		request.setAttribute("REGISTRATION_LIST", registrations);
		request.setAttribute("STUDENT_LIST",studentList);
		request.setAttribute("COURSE_LIST",courseList);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list-registration.jsp");
		dispatcher.forward(request, response);
	}

}













