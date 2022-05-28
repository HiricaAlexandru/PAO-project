/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


import Animal.Animal;
import Animal.Reptile.Snake;
import Context.Context;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import java.sql.Statement;


/**
 *
 * @author carja
 */
public class SnakeRepository {
    //int ID,Employee employee, LocalDate bornDate, double bloodTemperature, String skinColor, double Length
    public void createTable() {
        
    
        String sql = "CREATE TABLE IF NOT EXISTS snake " +
                "(id SERIAL PRIMARY KEY, " +
                "emp_id BIGINT UNSIGNED, " + 
                "day INTEGER, " +
                "month INTEGER, " + 
                "year INTEGER, " + 
                "blood_temperature REAL, "+
                "skin_color VARCHAR(256), "+
                "length REAL, "+
                "FOREIGN KEY (emp_id) REFERENCES employee(id))";
                

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            statement.execute(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public void addSnake(Snake liz) throws SQLException{
        String sql = "INSERT INTO snake (id, emp_id, day, month, year, blood_temperature, skin_color, length) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, liz.getID());
            preparedStatement.setInt(2, liz.getEmployee().getID());
            preparedStatement.setInt(3, liz.getBornDate().getDayOfMonth());
            preparedStatement.setInt(4, liz.getBornDate().getMonth().getValue());
            preparedStatement.setInt(5, liz.getBornDate().getYear());
            preparedStatement.setDouble(6, liz.getBloodTemperature());
            preparedStatement.setString(7, liz.getSkinColor());
            preparedStatement.setDouble(8, liz.getLength());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
    
    public List<Animal> getAllSnakes(Context con) {
        String sql = "SELECT * FROM snake";
         LinkedList<Animal> snakes = new LinkedList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int emp_id = resultSet.getInt(2);
                int day = resultSet.getInt(3);
                int month = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                
                double blood_temperature = resultSet.getDouble(6);
                String skinColor = resultSet.getString(7);
                double length = resultSet.getDouble(8);
                LocalDate data = LocalDate.of(year,month,day);
                try{
                    Snake sn = new Snake(id,con.getEmployeeById(emp_id),data,blood_temperature,skinColor, length);
                    snakes.add(sn);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return snakes;
    }
}
