/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.zooproject;
import Animal.Animal;
import ConsoleInterface.ConsoleInterface;
import Context.Context;
import Database.DatabaseConnection;
import Database.DatabaseManager;
import Database.EmployeeRepository;
import Employee.Employee;
import Location.Location;
import Services.Audit;
import Services.CsvWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class ZooProject {
   
    
    public static void main(String[] args) throws Exception {
       Audit aud = null;
       aud = Audit.getInstance();
       Context con = Context.getInstance();
       ConsoleInterface.constructObject(con);
       //con.readAllCsvData();
       DatabaseManager dmb = DatabaseManager.getInstance();
       dmb.LoadData();
       ConsoleInterface interfata = ConsoleInterface.getInstance();
       interfata.showMenu();
       
       
       dmb.saveData();
       
       //LinkedList<Animal> allAnimals = con.getAllAnimals();
       //ArrayList<Employee> allEmployees =con.getAllEmployees();
       //LinkedList<Location> allLocations = con.getAllLocations();
       //CsvWriter.writeToCsv(allAnimals, allEmployees, allLocations);
      
       
      
    }
}
