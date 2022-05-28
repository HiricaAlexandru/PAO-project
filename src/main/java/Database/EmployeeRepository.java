/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author carja
 */

import Employee.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//private final String Name;
//    private double Salary;
//    private int ID;
//    private boolean hired;

public class EmployeeRepository {
    public void createTable() {
        
    
        String sql = "CREATE TABLE IF NOT EXISTS employee " +
                "(id SERIAL PRIMARY KEY, " +
                "name VARCHAR(256)," +
                "salary REAL," +
                "hired BOOL)";

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            statement.execute(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public void addEmployee(Employee emp) throws SQLException{
        String sql = "INSERT INTO employee (id, name, salary, hired) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, emp.getID());
            preparedStatement.setString(2, emp.getName());
            preparedStatement.setDouble(3, emp.getSalary());
            preparedStatement.setBoolean(4, emp.getHired());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
    
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee emp = new Employee(resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(1), resultSet.getBoolean(4));
                employees.add(emp);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return employees;
    }
}
