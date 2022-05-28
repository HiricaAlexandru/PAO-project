/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Animal.Animal;
import Animal.Mamal.Bear;
import Animal.Mamal.Lion;
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
public class LionRepository {
    //int ID,Employee employee, LocalDate bornDate, double animalWeight
    public void createTable() {
        
    
        String sql = "CREATE TABLE IF NOT EXISTS lion " +
                "(id SERIAL PRIMARY KEY, " +
                "emp_id BIGINT UNSIGNED, " + 
                "day INTEGER, " +
                "month INTEGER, " + 
                "year INTEGER, " + 
                "weight REAL, "+
                "FOREIGN KEY (emp_id) REFERENCES employee(id))";
                

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            statement.execute(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public void addLion(Lion lion) throws SQLException{
        String sql = "INSERT INTO lion (id, emp_id, day, month, year, weight) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, lion.getID());
            preparedStatement.setInt(2, lion.getEmployee().getID());
            preparedStatement.setInt(3, lion.getBornDate().getDayOfMonth());
            preparedStatement.setInt(4, lion.getBornDate().getMonth().getValue());
            preparedStatement.setInt(5, lion.getBornDate().getYear());
            preparedStatement.setDouble(6, lion.getWeight());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
    
    public List<Animal> getAllLions(Context con) {
        String sql = "SELECT * FROM lion";
         LinkedList<Animal> lions = new LinkedList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int emp_id = resultSet.getInt(2);
                int day = resultSet.getInt(3);
                int month = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                
                double weight = resultSet.getDouble(6);
                
                LocalDate data = LocalDate.of(year,month,day);
                try{
                    Lion ll = new Lion(id,con.getEmployeeById(emp_id),data,weight);
                    lions.add(ll);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return lions;
    }
}
