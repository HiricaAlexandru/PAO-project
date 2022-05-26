/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import Animal.Animal;
import Employee.Employee;
import Location.Location;
import Services.CsvReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author carja
 */
public class Context {

    LinkedList<Animal> allAnimals;
    ArrayList<Employee> allEmployees;
    LinkedList<Location> allLocations;
    
    private static Context obj = null;

    public LinkedList<Animal> getAllAnimals() {
        return allAnimals;
    }

    public void setAllAnimals(LinkedList<Animal> allAnimals) {
        this.allAnimals = allAnimals;
    }

    public ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(ArrayList<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public LinkedList<Location> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(LinkedList<Location> allLocations) {
        this.allLocations = allLocations;
    }
    private Context(){
        this.allAnimals = new LinkedList<Animal>();
        this.allEmployees = new ArrayList<Employee>();
        this.allLocations = new LinkedList<Location>();
    }
    
    public static Context getInstance(){
        if(obj == null){
            obj = new Context();
        }
        
        return obj;
    }
    
    public void addAnimalWithoutChecking(Animal animalToAdd){
        allAnimals.add(animalToAdd);
    }
    public void addEmployeeWithoutChecking(Employee employeeToAdd){
        allEmployees.add(employeeToAdd);
    }
    
    public void addLocationWithoutChecking(Location locationToAdd){
        allLocations.add(locationToAdd);
    }
   
    
    public void addAnimal(Animal animalToAdd) throws Exception{
        for(int i = 0;i<allAnimals.size();i++){
            if(allAnimals.get(i).equals(animalToAdd)){
                throw new Exception("Duplicate!");
            }
        }
        
        allAnimals.add(animalToAdd);
    }
    
    public void addEmployee(Employee employeeToAdd) throws Exception{
        for(int i = 0;i<allEmployees.size();i++){
            if(allEmployees.get(i).equals(employeeToAdd)){
                throw new Exception("Duplicate!");
            }
        }
        
        allEmployees.add(employeeToAdd);
    }
    
    public void addLocation(Location locationToAdd) throws Exception{
        for(int i = 0;i<allLocations.size();i++){
            if(allLocations.get(i).equals(locationToAdd)){
                throw new Exception("Duplicate!");
            }
        }
        
        allLocations.add(locationToAdd);
    }
    
    public LinkedList<String> getAllEmployeesNames(){
        LinkedList<String> names = new LinkedList<>();
        
        for(int i = 0;i < allEmployees.size();i++){
            names.add(allEmployees.get(i).getName());
        }
        return names;
    }
    
    public LinkedList<Integer> getAllIDAnimals(){
        LinkedList<Integer> IDS = new LinkedList<>();
        for(Animal animal : allAnimals){
            IDS.add(animal.getID());
        }
        return IDS;
    }
    
    public LinkedList<Integer> getAllIDAnimals(int option){
        LinkedList<Integer> IDS = new LinkedList<>();
        for(Animal animal : allAnimals){
            if(animal.getType() == option)
                IDS.add(animal.getID());
        }
        return IDS;
    }
    
    public LinkedList<Integer> getAllIDEmployees(){
        LinkedList<Integer> IDS = new LinkedList<>();
        for(Employee emp : allEmployees){
            IDS.add(emp.getID());
        }
        return IDS;
    }
    
    public LinkedList<String> getAllIdAndNameStringLocation(){
        LinkedList<String> result = new LinkedList<>();
        
        for(Location loc : this.allLocations){
            result.add("" + loc.getID() + ":" + loc.getName());
        }
        return result;
    }
    
    public LinkedList<Integer> getAllIdLocation(){
        LinkedList<Integer> result = new LinkedList<>();
        
        for(Location loc : this.allLocations){
            result.add(loc.getID());
        }
        return result;
    }
    
    public Animal getAnimalById(int ID) throws Exception{
        for(Animal animal : allAnimals){
            if(animal.getID() == ID){
                return animal;
            }
        }
        throw new Exception("Niciun animal gasit!");
    }
    
    public Location getLocationById(int ID) throws Exception{
        for(Location location : this.allLocations){
            if(location.getID() == ID){
                return location;
            }
        }
        throw new Exception("Nicio locatie nu a fost gasita!");
    }
    
    public Employee getEmployeeById(int ID) throws Exception{
        for(Employee emp : allEmployees){
            if(emp.getID() == ID){
                return emp;
            }
        }
        throw new Exception("Niciun angajat gasit!");
    }
    
    public Employee getEmployee(String name) throws Exception{
        for(Employee emp : allEmployees){
            if(emp.getName().equals((name))){
                return emp;
            }
        }
        throw new Exception("Invalid employee!");
    }
    
    public void removeAnimal(Animal animalToRemove) throws Exception{
        for(int i = 0;i<allAnimals.size();i++){
            if(allAnimals.get(i).equals(animalToRemove)){
                allAnimals.get(i).setAlive(false);
                
                //deleting from the location
                for(int j = 0;j<allLocations.size();j++)
                    if(allLocations.get(j).removeAnimal(animalToRemove) == true)
                        break;
                
            }
        }
        
        
        
        throw new Exception("Not found!");
    }
    
    public void removeEmployee(Employee emp) throws Exception{
        for(int i = 0;i<allEmployees.size();i++){
            if(allEmployees.get(i).equals(emp)){
                allEmployees.get(i).setHired(false);
                
                //deleting from the location
                for(int j = 0;j<this.allAnimals.size();j++)
                    if(allAnimals.get(j).getEmployee().equals(emp))
                        allAnimals.get(j).setEmployee(null);
                        break;
                
            }
        }

        throw new Exception("Not found!");
    }
    
    
    public void readAllCsvData() throws Exception{
        this.allEmployees = CsvReader.readEmployees();
        this.allAnimals = CsvReader.readAnimals(this);
        this.allLocations = CsvReader.readLocations(this);
    }
    
    
}
