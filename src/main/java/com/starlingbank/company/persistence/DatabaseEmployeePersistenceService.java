package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEmployeePersistenceService implements EmployeePersistenceService {

    public DatabaseEmployeePersistenceService() {
    }

    @Override
    public void addNewManager(String name, String dateOfBirth, int salaryId) {
        String statement = "INSERT INTO employee (name, date_of_birth, employee_type)\n" +
                "    VALUES(?, ?, 'MANAGER')";
        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
            preparedStatement.setString(1, name);

            preparedStatement.setDate(2, Date.valueOf(dateOfBirth));

            int affectedRow = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new IllegalStateException("Could not add manager to database", e);
        }
    }

    @Override
    public void addNewProgrammer(String name, String dateOfBirth, int salaryId) {
        String statement = "INSERT INTO employee (name, date_of_birth, employee_type)\n" +
                "    VALUES(?, ?, 'PROGRAMMER')";
        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
            preparedStatement.setString(1, name);

            preparedStatement.setDate(2, Date.valueOf(dateOfBirth));

            int affectedRow = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new IllegalStateException("Could not add programmer to database", e);
        }
    }

    @Override
    public List<Employee> listEmployees() {
        List<Employee> employeesList = new ArrayList<>();
        String query = "SELECT id, name, employee_type, date_of_birth, salary_id " +
                "FROM employee;";


        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");


                switch (employeeType) {
                    case "PROGRAMMER":
                        employeesList.add(
                                new Programmer(
                                        id,
                                        name,
                                        dateOfBirth,
                                        salaryId
                                ));
                        break;
                    case "MANAGER":
                        employeesList.add(
                                new Manager(
                                        id,
                                        name,
                                        dateOfBirth,
                                        salaryId
                                )
                        );
                        break;
                    default: {
                        throw new IllegalStateException("Employee type not recognised, employee type found: " + employeeType);
                    }
                }

//                if(employeeType.equals("PROGRAMMER")){
//
//                } else {
//                    throw new IllegalStateException("Employee type not recognised");
//                }


            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }

        return employeesList;
    }

    @Override
    public void addEmployeeToTeam(int managerId, int employeeId) {
        //check if managerId is valid manager
        List<Employee> employeesList = new ArrayList<>();
        String query = "SELECT employee_type FROM employee WHERE id= ?";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, managerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String employeeType = resultSet.getString("employee_type");
                if (employeeType.equals("MANAGER")) {
                    String addEmployeeStatement = " INSERT INTO team (manager_id, employee_id) " +
                            " VALUES (?, ?)";
                    PreparedStatement nextPreparedStatement = conn.prepareStatement((addEmployeeStatement));
                    nextPreparedStatement.setInt(1, managerId);
                    nextPreparedStatement.setInt(2, employeeId);

                    int affectedRow = nextPreparedStatement.executeUpdate();

                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("could not add this employee of the team", e);
        }
        //add relationship between manager and employee
    }

    @Override
    public List<Integer> getTeam(int managerId) {
        List<Integer> teamIds = new ArrayList<>();
        //select employee id where employee is part of manager team
        String query = "SELECT employee_id FROM team WHERE manager_id=?";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, managerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer employeeId = resultSet.getInt("employee_id");
                teamIds.add(employeeId);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access manager " + managerId + " in database", e);
        }


        return teamIds;
    }

    @Override
    public List<Employee> getTeamMembers(int managerId) {
        List<Employee> employeesList = new ArrayList<>();
        String query = "SELECT id, name, employee_type, date_of_birth, salary_id FROM employee " +
                "INNER JOIN team ON team.employee_id=employee.id " +
                "WHERE manager_id=?";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, managerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");


                switch (employeeType) {
                    case "PROGRAMMER":
                        employeesList.add(
                                new Programmer(
                                        id,
                                        name,
                                        dateOfBirth,
                                        salaryId
                                ));
                        break;
                    case "MANAGER":
                        employeesList.add(
                                new Manager(
                                        id,
                                        name,
                                        dateOfBirth,
                                        salaryId

                                )
                        );
                        break;
                    default: {
                        throw new IllegalStateException("Employee type not recognised, employee type found: " + employeeType);
                    }
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }

        return employeesList;
    }

//    public List<String> showWhatCoursesMyEmployeesAreEnrolledIn(int managerId) {
//
//        for (Employee employee : this.getTeamMembers(managerId)) {
//            List<String> courseNames = databaseCourseService.showWhatCoursesPersonIsEnrolledIn(employee);
//            employeesEnrolledToCourses.addAll(courseNames);
//        }
//
//        return employeesEnrolledToCourses;
//    }

    @Override
    public Employee getEmployeeFromEmployees(int employeeId) {

        String query = "SELECT id, name, employee_type, date_of_birth, salary_id FROM employee " +
                "WHERE employee.id=?";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");


                switch (employeeType) {
                    case "PROGRAMMER":
                        return new Programmer(employeeId, name, dateOfBirth, salaryId);

                    case "MANAGER":
                        return new Manager(employeeId, name, dateOfBirth, salaryId);

                    default: {
                        throw new IllegalStateException("Employee type not recognised, employee type found: " + employeeType);
                    }
                }

            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee " + employeeId + " in database", e);
        }

        return null;
    }

    @Override
    public Employee getEmployeeWithHighestSalary() {
        //selects all rows from employee that have salary id of the highest salary in salary table
        String query = "SELECT id, name, date_of_birth, employee_type, date_of_birth, salary_id" +
                "FROM employee" +
                "WHERE employee.salary_id IN ( " +
                "    SELECT id FROM salary " +
                "    WHERE amount IN ( " +
                "        SELECT MAX(amount) " +
                "        FROM salary " +
                "    ) " +
                ") LIMIT 1 ";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");

                switch (employeeType) {
                    case "PROGRAMMER":
                        return new Programmer(id, name, dateOfBirth, salaryId);
                    case "MANAGER":
                        return new Manager(id, name, dateOfBirth, salaryId);
                    default:
                        throw new IllegalStateException("Could not identify this type of employee, type found: " + employeeType);
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }
        return null;
    }

    @Override
    public Employee getOldestEmployee() {
        String query = "SELECT employee.id, employee.name, employee.date_of_birth, employee.employee_type, employee.salary_id " +
                "FROM employee " +
                "ORDER BY date_of_birth LIMIT 1;";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId = resultSet.getInt("salary_id");

                switch (employeeType) {
                    case "PROGRAMMER":
                        return new Programmer(id, name, dateOfBirth, salaryId);
                    case "MANAGER":
                        return new Manager(id, name, dateOfBirth, salaryId);
                    default:
                        throw new IllegalStateException("Could not identify this type of employee, type found: " + employeeType);
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }
        return null;
    }




    public void changeEmployeeName(int employeeId, String newName) {
        String query = "UPDATE employees" +
                "SET name= ?" +
                "WHERE id= ?;";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, employeeId);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }

    }

    public List<String> showCoursesTeamIsEnrolledIn(int managerId) {
        List<String> employeeCourses = new ArrayList<>();
        String query = "SELECT course_name FROM course" +
                "WHERE id IN (" +
                "    SELECT course_id FROM course_employee" +
                "    WHERE employee_id IN(" +
                "        SELECT employee_id FROM team" +
                "        WHERE manager_id = ?" +
                "    )" +
                ");";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.setInt(1, managerId);

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                employeeCourses.add(courseName);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee in database", e);
        }
        return employeeCourses;
    }

    public void removeTeamMember(int managerId, int employeeId) {
        String statement = "DELETE FROM team " +
                "WHERE manager_id= ? AND employee_id= ?" ;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
            preparedStatement.setInt(1, managerId);
            preparedStatement.setInt(2, employeeId);

            int affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot delete employee from team in database", e);
        }
    }

    public void removeEmployee(int employeeId) {
        String statement = "DELETE FROM employee " +
                "WHERE employee_id= ?" ;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
            preparedStatement.setInt(1, employeeId);

            int affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot delete employee in database", e);
        }
    }

    public void removeTeam(int managerId) {
        String statement = "DELETE FROM team " +
                "WHERE manager_id= ?" ;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
            preparedStatement.setInt(1, managerId);

            int affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot delete employee from team in database", e);
        }
    }
}
