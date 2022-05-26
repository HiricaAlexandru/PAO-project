/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Animal.Animal;
import Animal.Mamal.Bear;
import Animal.Mamal.Lion;
import Animal.Reptile.Lizard;
import Animal.Reptile.Snake;
import Context.Context;
import Employee.Employee;
import Location.Location;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author carja
 */
public class CsvReader {
    private static List<String> tokenizeCSVRow(String str){
        List<String> elephantList = Arrays.asList(str.split(","));
        return elephantList;
    }
    
    private static ArrayList<Integer> getIdsFromLine(String line){
        
        String str = line;  
        if(str.length() == 0){
            return null;
        }
        str = str.replaceAll("[^-?0-9]+", " "); 
        List<String> resultat = Arrays.asList(str.trim().split(" "));
        ArrayList<Integer> listaFinala= new ArrayList<Integer>();
        
        for(String res : resultat){
            Integer digit = Integer.valueOf(res);
            listaFinala.add(digit);
        }
        
        return listaFinala;
    }
    
    
    public static ArrayList<Employee> readEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            File myObj = new File("employees.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              List<String> parsedData = tokenizeCSVRow(data);
              //String Name, double Salary, int ID, boolean hired
              //ID,Salary,Name,Hired
              int ID = Integer.valueOf(parsedData.get(0));
              double Salary = Double.valueOf(parsedData.get(1));
              String Name = parsedData.get(2);
              boolean hired = Boolean.valueOf(parsedData.get(3));
              Employee emp = new Employee(Name, Salary, ID,hired);
              employees.add(emp);
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
      
        }
        return employees;
    }
    
    private static void readLions(LinkedList<Animal> allAnimals, Context con){
        //ID,Employee_ID,BornDate,isAlive,animal_weight,roar_db
        try {
            File myObj = new File("lions.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> parsedData = tokenizeCSVRow(data);
                int ID = Integer.valueOf(parsedData.get(0));
                int EmployeeID = Integer.valueOf(parsedData.get(1));
                LocalDate bornDate = LocalDate.parse(parsedData.get(2));
                boolean isAlive = Boolean.valueOf(parsedData.get(3));
                double animalWeigt = Double.valueOf(parsedData.get(4));
                double roarDb = Double.valueOf(parsedData.get(5));
                Employee emp = con.getEmployeeById(EmployeeID);
                Lion li = new Lion(ID, emp, bornDate, animalWeigt, roarDb);
                allAnimals.add(li);
              
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        catch(Exception e){
            System.out.println("Error with searching employee");
        }
    }
    
    private static void readBears(LinkedList<Animal> allAnimals, Context con){
        //ID,Employee_ID,BornDate,isAlive,animal_weight,max_speed
        try {
            File myObj = new File("bears.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> parsedData = tokenizeCSVRow(data);
                int ID = Integer.valueOf(parsedData.get(0));
                int EmployeeID = Integer.valueOf(parsedData.get(1));
                LocalDate bornDate = LocalDate.parse(parsedData.get(2));
                boolean isAlive = Boolean.valueOf(parsedData.get(3));
                Employee emp = con.getEmployeeById(EmployeeID);
                double animalWeight = Double.valueOf(parsedData.get(4));
                double maxSpeed = Double.valueOf(parsedData.get(5));
                Bear br = new Bear(ID, emp, bornDate, animalWeight, maxSpeed);
                allAnimals.add(br);

            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
      
        }
        catch(Exception e){
            System.out.println("Error with searching employee");
        }
    }
    
    private static void readSnake(LinkedList<Animal> allAnimals, Context con){
        //ID,Employee_ID,BornDate,isAlive,blood_temperature,Length,Skin_color
        try {
            File myObj = new File("snake.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> parsedData = tokenizeCSVRow(data);
                int ID = Integer.valueOf(parsedData.get(0));
                int EmployeeID = Integer.valueOf(parsedData.get(1));
                
                LocalDate bornDate = LocalDate.parse(parsedData.get(2));
                boolean isAlive = Boolean.valueOf(parsedData.get(3));
                Employee emp = con.getEmployeeById(EmployeeID);
                Double blood_temperature = Double.valueOf(parsedData.get(4));
                Double Length = Double.valueOf(parsedData.get(6));
                String SkinColor = parsedData.get(5);

                Snake sn = new Snake(ID, emp, bornDate, blood_temperature, SkinColor, Length);
                allAnimals.add(sn);
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        catch(Exception e){
            System.out.println("Error with searching employee");
        }
    }
    
    private static void readLizard(LinkedList<Animal> allAnimals, Context con){
        //ID,Employee_ID,BornDate,isAlive,blood_temperature,Skin_color
        try {
            File myObj = new File("lizard.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> parsedData = tokenizeCSVRow(data);
                int ID = Integer.valueOf(parsedData.get(0));
                int EmployeeID = Integer.valueOf(parsedData.get(1));
                LocalDate bornDate = LocalDate.parse(parsedData.get(2));
                boolean isAlive = Boolean.valueOf(parsedData.get(3));
                Employee emp = con.getEmployeeById(EmployeeID);
                Double blood_temperature = Double.valueOf(parsedData.get(4));
                String SkinColor = parsedData.get(5);
                
                Lizard liz = new Lizard(ID, emp, bornDate, blood_temperature, SkinColor);
                
                allAnimals.add(liz);

            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        catch(Exception e){
            System.out.println("Error with searching employee");
        }
    }
    
    public static LinkedList<Animal> readAnimals(Context con) {
        LinkedList<Animal> allAnimals = new LinkedList<>();
        
        readLions(allAnimals,con);
        readBears(allAnimals,con);
        readSnake(allAnimals,con);
        readLizard(allAnimals,con);
        
        return allAnimals;
    }

    public static LinkedList<Location> readLocations(Context con) {
        LinkedList<Location> allLoc = new LinkedList<>();
        try {
            File myObj = new File("location.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              List<String> parsedData = tokenizeCSVRow(data);
              //ID,Cage_type,Name,Animals_ids
              int ID = Integer.valueOf(parsedData.get(0));
              int cageType = Integer.valueOf(parsedData.get(1));
              String Name = parsedData.get(2);
              Location loc = new Location(cageType, Name, ID);
              ArrayList<Integer> animalIDS = getIdsFromLine(parsedData.get(3));
              if(animalIDS != null){
                  for(Integer nr : animalIDS){
                      loc.addAnimal(con.getAnimalById(nr));
                  }
              }
              
              allLoc.add(loc);
              
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }catch (Exception e) {
            System.out.println("No animal found!");
        }
        return allLoc;
    }
    
    
}
