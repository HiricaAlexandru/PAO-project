/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Animal.Animal;
import Animal.Mamal.Bear;
import Context.Context;
import Location.Location;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class LocationRepository {
    public void createTable() {
        
        //int cageType, String Name, int id
        
        String sql = "CREATE TABLE IF NOT EXISTS location " +
                "(id SERIAL PRIMARY KEY, " +
                "cageType INTEGER, " + 
                "name VARCHAR(128))";
                

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            
            statement.execute(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS location_animal " +
                    "(id SERIAL PRIMARY KEY, " + 
                    "id_cage BIGINT UNSIGNED, " +
                    "id_animal BIGINT UNSIGNED, " + 
                    "FOREIGN KEY (id_cage) REFERENCES location(id))";


            statement.execute(sql);

            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    private void addToAsocciativeLocation(Location loc) throws SQLException{
        String sql = "INSERT INTO location_animal (id_cage, id_animal) VALUES (?, ?)";
        
        for(Integer number : loc.getIdsOfAnimals()){
            try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                preparedStatement.setInt(1, loc.getID());
                preparedStatement.setInt(2, number);
                preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
        }
        
        
    }
    
    public void addLocation(Location loc) throws SQLException{
        String sql = "INSERT INTO location (id, cageType, name) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, loc.getID());
            preparedStatement.setInt(2, loc.getCageType());
            preparedStatement.setString(3,loc.getName());
            addToAsocciativeLocation(loc);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
    
    private List<Integer> getAllAnimalsByIdAndLocation(int idLocation){
        String sql = "SELECT id_animal FROM location_animal WHERE id_cage=" + String.valueOf(idLocation);
        List<Integer> numbers = new LinkedList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                numbers.add(id);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return numbers;
    }
    
    public LinkedList<Location> getAllLocation(Context con) {
        String sql = "SELECT * FROM location";
        LinkedList<Location> locations = new LinkedList<>();

        try (Statement statement = DatabaseConnection.getInstance().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int cageType = resultSet.getInt(2);
                String nume = resultSet.getString(3);
                
                try{
                    Location location = new Location(cageType, nume, id);
                    List<Integer> id_list_animale = this.getAllAnimalsByIdAndLocation(id);
                    for(Integer number : id_list_animale){
                        Animal anm = con.getAnimalById(number);
                        location.addAnimal(anm);
                    }
                    locations.add(location);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return locations;
    }
}
