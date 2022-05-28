/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Animal.Animal;
import Animal.Mamal.Bear;
import Context.Context;
import Employee.Employee;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author carja
 */
public class BearRepository {
    //int ID,Employee employee, LocalDate bornDate, double animalWeight, double maxSpeed
    public void createTable() {
        
    
        String sql = "CREATE TABLE IF NOT EXISTS bear " +
                "(id SERIAL PRIMARY KEY, " +
                "emp_id BIGINT UNSIGNED, " + 
                "day INTEGER, " +
                "month INTEGER, " + 
                "year INTEGER, " + 
                "weight REAL, " + 
                "max_speed REAL, " +
                "FOREIGN KEY (emp_id) REFERENCES employee(id))";
                

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            statement.execute(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public void addBear(Bear br) throws SQLException{
        String sql = "INSERT INTO bear (id, emp_id, day, month, year, weight, max_speed) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, br.getID());
            preparedStatement.setInt(2, br.getEmployee().getID());
            preparedStatement.setInt(3, br.getBornDate().getDayOfMonth());
            preparedStatement.setInt(4, br.getBornDate().getMonth().getValue());
            preparedStatement.setInt(5, br.getBornDate().getYear());
            preparedStatement.setDouble(6, br.getWeight());
            preparedStatement.setDouble(7, br.getMaxSpeed());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
    
    public List<Animal> getAllBears(Context con) {
        String sql = "SELECT * FROM bear";
         LinkedList<Animal> bears = new LinkedList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int emp_id = resultSet.getInt(2);
                int day = resultSet.getInt(3);
                int month = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                
                double weight = resultSet.getDouble(6);
                double max_speed = resultSet.getDouble(7);
                
                LocalDate data = LocalDate.of(year,month,day);
                try{
                    Bear br = new Bear(id,con.getEmployeeById(emp_id),data,weight,max_speed);
                    bears.add(br);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return bears;
    }
}
