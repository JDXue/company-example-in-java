package com.starlingbank.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.externalservices.CourseService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEmployeePersistenceService implements EmployeePersistenceService {

    public DatabaseEmployeePersistenceService(){
    }

    @Override
    public void addNewManager(String name, String dateOfBirth, Salary salary) {
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
    public void addNewProgrammer(String name, String dateOfBirth, Salary salary) {
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
        String query = "SELECT employee.id, employee.name, employee.date_of_birth, employee.employee_type, salary.amount, salary.currency\n" +
                "FROM employee, salary\n" +
                "WHERE salary_id IN (\n" +
                "    SELECT id FROM salary\n" +
                "    WHERE amount IN (\n" +
                "        SELECT MAX(amount)\n" +
                "        FROM salary\n" +
                "    )\n" +
                ");";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryAmount = resultSet.getInt("amount");
                String salaryCurrencyType = resultSet.getString("currency");


                switch(employeeType){
                    case "PROGRAMMER":
                        employeesList.add(
                            new Programmer(
                                    id,
                                    name,
                                    dateOfBirth,
                                    null
                            ));
                        break;
                    case "MANAGER":
                        employeesList.add(
                                new Manager(
                                        id,
                                        name,
                                        dateOfBirth,
                                        null
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
        String query = "SELECT employee_type FROM employee WHERE id=" + managerId;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String employeeType = resultSet.getString("employee_type");
                if(employeeType.equals("MANAGER")){
                    String addEmployeeStatement = " INSERT INTO team (manager_id, employee_id)\n" +
                            " VALUES (?, ?)";
                    PreparedStatement nextPreparedStatement = conn.prepareStatement((addEmployeeStatement));
                    nextPreparedStatement.setInt(1,managerId);
                    nextPreparedStatement.setInt(2,employeeId);

                    int affectedRow = nextPreparedStatement.executeUpdate();

                }
            }

        } catch(SQLException e) {
            throw new IllegalStateException("could not add this employee of the team", e);
        }
        //add relationship between manager and employee
    }

    @Override
    public List<Integer> getTeam(int managerId) {
        List<Integer> teamIds = new ArrayList<>();
        //select employee id where employee is part of manager team
        String query = "SELECT employee_id FROM team WHERE manager_id=" + managerId;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
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
        String query = "SELECT * FROM employee INNER JOIN team ON team.employee_id=employee.id WHERE manager_id="+ managerId;

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");


                switch(employeeType){
                    case "PROGRAMMER":
                        employeesList.add(
                                new Programmer(
                                        id,
                                        name,
                                        dateOfBirth,
                                        null
                                ));
                        break;
                    case "MANAGER":
                        employeesList.add(
                                new Manager(
                                        id,
                                        name,
                                        dateOfBirth,
                                        null

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

    @Override
    public Employee getEmployeeFromEmployees(int employeeId) {
        String employeeIdStr = String.valueOf(employeeId);
        String query = "SELECT * FROM employee, salary" +
                "WHERE salary.id=employee.salary_id AND " +
                "employee.id=" + employeeIdStr;

        try (Connection conn = Database.getNewConnection();
              PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");


                switch (employeeType) {
                    case "PROGRAMMER":
                        return new Programmer(employeeId, name, dateOfBirth, null);

                    case "MANAGER":
                        return new Manager(employeeId, name, dateOfBirth, null);

                    default: {
                        throw new IllegalStateException("Employee type not recognised, employee type found: " + employeeType);
                    }
                }

            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot access employee "+ employeeIdStr + " in database", e);
        }

        return null;
    }

    @Override
    public Employee getEmployeeWithHighestSalary() {
            //selects all rows from employee that have salary id of the highest salary in salary table
            String query = "SELECT employee.id, employee.name, employee.date_of_birth, employee.employee_type, salary.amount, salary.currency\n" +
                    "FROM employee, salary\n" +
                    "WHERE employee.salary_id IN (\n" +
                    "    SELECT id FROM salary\n" +
                    "    WHERE amount IN (\n" +
                    "        SELECT MAX(amount)\n" +
                    "        FROM salary\n" +
                    "    )\n" +
                    ") AND\n" +
                    "\n" +
                    "salary.id=employee.salary_id;";

            try (Connection conn = Database.getNewConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String employeeType = resultSet.getString("employee_type");
                    String dateOfBirth = resultSet.getString("date_of_birth");
                    int salaryAmount = resultSet.getInt("amount");
                    String salaryCurrencyType = resultSet.getString("currency");

                    switch(employeeType){
                        case "PROGRAMMER":
                            return new Programmer(id,name,dateOfBirth,null);
                        case "MANAGER":
                            return new Manager(id,name,dateOfBirth,null);
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
        String query = "SELECT employee.id, employee.name, employee.date_of_birth, employee.employee_type, salary.amount, salary.currency \n" +
                "FROM employee\n" +
                "WHERE employee.salary_id IN (\n" +
                "    SELECT id FROM salary\n" +
                "        WHERE amount IN (\n" +
                "            SELECT MAX(amount)\n" +
                "            FROM salary\n" +
                "        )\n" +
                "    )\n" +
                "    ORDER BY date_of_birth LIMIT 1;";

        try (Connection conn = Database.getNewConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String employeeType = resultSet.getString("employee_type");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int salaryId= resultSet.getInt("amount");

                switch(employeeType){
                    case "PROGRAMMER":
                        return new Programmer(id, name, dateOfBirth, null);
                    case "MANAGER":
                        return new Manager(id, name, dateOfBirth, null);
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
