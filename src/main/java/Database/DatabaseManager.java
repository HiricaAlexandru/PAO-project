/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Animal.Animal;
import Animal.Mamal.Bear;
import Animal.Mamal.Lion;
import Animal.Reptile.Lizard;
import Animal.Reptile.Snake;
import Context.Context;
import Employee.Employee;
import Location.Location;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author carja
 */
public class DatabaseManager {
    private Context con;
    private static DatabaseManager DbM = null;
    private EmployeeRepository employeeRepository;
    private BearRepository bearRepository;
    private LionRepository lionRepository;
    private LizardRepository lizardRepository;
    private SnakeRepository snakeRepository;
    private LocationRepository locationRepository;
    
    private DatabaseManager(){
        con = Context.getInstance();
        employeeRepository = new EmployeeRepository();
        bearRepository = new BearRepository();
        lionRepository = new LionRepository();
        lizardRepository = new LizardRepository();
        snakeRepository = new SnakeRepository();
        locationRepository = new LocationRepository();
        employeeRepository.createTable();
        bearRepository.createTable();
        lionRepository.createTable();
        lizardRepository.createTable();
        snakeRepository.createTable();
        locationRepository.createTable();
        
    }
    
    public static DatabaseManager getInstance(){
        if(DbM == null){
            DbM = new DatabaseManager();
        }
        
        return DbM;
    }
    private ArrayList<Employee> readEmployees(){
        ArrayList<Employee> emp = (ArrayList<Employee>) employeeRepository.getAllEmployees();
        return emp;
        
    }
    
    private LinkedList<Animal> readAnimals(){
        List<Animal> bears = bearRepository.getAllBears(con);
        List<Animal> lions = lionRepository.getAllLions(con);
        List<Animal> lizards = lizardRepository.getAllLizards(con);
        List<Animal> snakes = snakeRepository.getAllSnakes(con);
        LinkedList<Animal> allAnimals = new LinkedList<>();
        allAnimals.addAll(bears);
        allAnimals.addAll(lions);
        allAnimals.addAll(lizards);
        allAnimals.addAll(snakes);
        return allAnimals;
    }
    public void LoadData(){
        con.setAllEmployees(this.readEmployees());
        con.setAllAnimals(this.readAnimals());
        con.setAllLocations(locationRepository.getAllLocation(con));
        
        
    }
    
    private void saveEmployees(){
        List<Employee> employees = con.getAllEmployees();
        
        for(Employee emp : employees){
            try{
                employeeRepository.addEmployee(emp);
            }catch(SQLException e){
                
            }
        }
        
    }
    private void saveAnimals() {
        List<Animal> allAnimals = con.getAllAnimals();
        
        for(Animal animal : allAnimals){
            try{
                if(animal instanceof Lion){
                    lionRepository.addLion((Lion) animal);
                }
                
                if(animal instanceof Bear){
                    bearRepository.addBear((Bear) animal);
                }
                
                if(animal instanceof Lizard){
                    lizardRepository.addLizard((Lizard) animal);
                }
                
                if(animal instanceof Snake){
                    snakeRepository.addSnake((Snake) animal);
                }
                
            }catch(SQLException e){}
        }
        
    }
    
    private void saveLocations(){
        for(Location loc : con.getAllLocations()){
            try{
                locationRepository.addLocation(loc);
            }catch(SQLException e){}
        }
    }
    
    public void saveData(){
       saveEmployees();
       saveAnimals();
       saveLocations();
    }

    
    
    
}
