package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.externalservices.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCoursePersistenceService implements CoursePersistenceService {

    public DatabaseCoursePersistenceService(){
    }

    @Override
    public void addCourse(String newCourse) {
        String statement = "INSERT INTO course (course_name)\n" +
                "    VALUES(?)";
        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {

            preparedStatement.setString(1, newCourse);

            int affectedRow = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new IllegalStateException("Could not add course to database ", e);
        }
    }

    public void enroll(int employeeId, int courseId) {
        //add a way of checking id is valid?
        String checkForValidIds = "SELECT employee.id, course.id FROM employee, course WHERE employee.id= ? AND course.id= ?";
        String statement = "INSERT INTO course_employee (employee_id, course_id)\n" +
                "    VALUES(?,?)";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setInt(1,employeeId);
                preparedStatement.setInt(2,courseId);

                int affectedRow = preparedStatement.executeUpdate();

//            try {
//                PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
//
//                preparedStatement.setInt(1, courseId);
//                preparedStatement.setInt(2, employeeId);
//
//                int affectedRow = preparedStatement.executeUpdate();
//            }




        } catch (SQLException e) {
            throw new IllegalStateException("Could not enroll employee to this course ", e);
        }
    }

    @Override
    public List<Course> listCourses() {
        List<Course> courseList = new ArrayList<>();
        String query = "SELECT id, course_name FROM course";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("course_name");

                courseList.add(
                        new Course(id,name)
                );
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access course in database", e);
        }

        return courseList;
    }

    @Override
    public List<Course> showWhatCoursesPersonIsEnrolledIn(int employeeId){
        List<Course> coursesEnrolledIn = new ArrayList<>();

        String employeeIdString = String.valueOf(employeeId);
        String query = "SELECT id, course_name " +
                "FROM course " +
                "INNER JOIN course_employee ON course.id=course_employee.course_id " +
                "WHERE employee_id=" + employeeIdString;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("id");
                String courseName = resultSet.getString("course_name");

                coursesEnrolledIn.add(new Course(courseId, courseName));
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access course in database", e);
        }

        return coursesEnrolledIn;

    }


    @Override
    public Employee getEmployeeEnrolledInMostCourses(){
        String query = "SELECT * FROM employee " +
                "WHERE id IN ( " +
                "    SELECT employee_id FROM course_employee " +
                "    GROUP BY employee_id " +
                "    ORDER BY COUNT(*) DESC " +
                "    LIMIT 1 " +
                ")";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");


                switch(employeeType){
                    case "PROGRAMMER":
                        return new Programmer(id,name,dateOfBirth,salaryId);
                    case "MANAGER":
                        return new Manager(id,name,dateOfBirth,salaryId);
                    default:
                        throw new IllegalStateException("Could not identify this type of employee, type found: " + employeeType);
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }
        return null;
    }




}
