package com.bnu.wsd.sys.dao;

import com.bnu.wsd.sys.entity.Course;
import com.bnu.wsd.sys.entity.Registration;
import com.bnu.wsd.sys.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
	Connection theCon;
	PreparedStatement preparedStatement;
	CourseDAO courseDAO;
	StudentDAO studentDAO;
	public RegistrationDAO() {
		super();
		theCon = DBConnection.makeConnection();
		courseDAO = new CourseDAO();
		studentDAO = new StudentDAO();
		// TODO Auto-generated constructor stub
	}
	
	public void insert(Registration s){
		String strInsert = "INSERT INTO registration (registration_date,student_id,course_id) VALUES (?,?,?)";
		try {
			System.out.println("Ready for Registration : "+s);
			preparedStatement =  
					theCon.prepareStatement(strInsert,
							PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,s.getRegDate());
			preparedStatement.setInt(2,s.getStudent().getStudentId());
			preparedStatement.setInt(3,s.getCourse().getCourseId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@SuppressWarnings("unchecked")
	public ResultSet getAllRegistrationsFromDB(){
		ResultSet rs = null;
		String strQuery = "Select * from registration";
		try {
			preparedStatement = theCon.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery(strQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;		
	}
	
	public List<Registration> getAllRegistration() throws SQLException{
		ResultSet rs = getAllRegistrationsFromDB();
		List<Registration> registrationList = new ArrayList<Registration>();
		while(rs.next()){
			int id = rs.getInt("registration_id");
			String registrationDate = rs.getString("registration_date");
			int studentId = rs.getInt("student_id");
			int courseId = rs.getInt("course_id");
			Course course =  courseDAO.getCourse(String.valueOf(courseId));
			Student student = studentDAO.getStudent(String.valueOf(studentId));
			Registration registration = new Registration(id, registrationDate, course, student);
			registrationList.add(registration);
		}
		return registrationList;
	}
	
	@SuppressWarnings("unchecked")
	public Registration getRegistration(String registrationId){
			ResultSet rs = null;
			Registration registration = null;
			String strQuery = "SELECT * FROM registration where registration_id=?";
			try {
				preparedStatement = theCon.prepareStatement(strQuery);
				int id = Integer.parseInt(registrationId);
				preparedStatement.setInt(1,id);
				rs = preparedStatement.executeQuery();


				System.out.println("RS : "+rs);
				if(rs.next()){
					int idNew = rs.getInt("registration_id");
					String registrationDate = rs.getString("registration_date");
					String courseId = rs.getString("course_id");
					String studentId = rs.getString("student_id");
					Course course = courseDAO.getCourse(courseId);
					Student student = studentDAO.getStudent(studentId);
					registration = new Registration(idNew, registrationDate, course, student);
					System.out.println("Registration : "+registration);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Registration : "+registration);
			return registration;
	 }
	public List<Registration> getRegistration(int studentId){
		ResultSet rs = null;
		List<Registration> registrationList = new ArrayList<Registration>();
		String strQuery = "SELECT * FROM registration where student_id=?";
		try {
			preparedStatement = theCon.prepareStatement(strQuery);
			preparedStatement.setInt(1,studentId);
			rs = preparedStatement.executeQuery();


			System.out.println("RS : "+rs);
			while(rs.next()){
				int idNew = rs.getInt("registration_id");
				String registrationDate = rs.getString("registration_date");
				String courseId = rs.getString("course_id");
				String studentIdStr = rs.getString("student_id");
				Course course = courseDAO.getCourse(courseId);
				Student student = studentDAO.getStudent(studentIdStr);
				Registration registration = new Registration(idNew, registrationDate, course, student);
				registrationList.add(registration);
				System.out.println("Registration : "+registration);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registrationList;
	}
	public void updateRegistration(Registration theRegistration) throws Exception {

		//Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection

			// create SQL update statement
			String strQuery = "UPDATE registration SET registration_id =?, registration_date =?, student_id =?, course_id = ? WHERE registration_id = ?;";

			// prepare statement
			myStmt = theCon.prepareStatement(strQuery);

			// set params
			myStmt.setString(1, theRegistration.getRegDate());
			myStmt.setInt(2, theRegistration.getStudent().getStudentId());
			myStmt.setInt(3, theRegistration.getCourse().getCourseId());
			myStmt.setInt(4, theRegistration.getRegistrationId());

			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			//close(myConn, myStmt, null);
		}
	}

	public void deleteRegistration(String theRegistrationId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert student id to int
			int registrationId = Integer.parseInt(theRegistrationId);

			// get connection to database
			//myConn = dataSource.getConnection();

			// create sql to delete student
			String sql = "delete from registration where registration_id=?";

			// prepare statement
			myStmt = theCon.prepareStatement(sql);

			// set params
			myStmt.setInt(1, registrationId);

			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			//close(myConn, myStmt, null);
		}
	}

}
