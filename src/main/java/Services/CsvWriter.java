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
import Employee.Employee;
import Location.Location;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author carja
 */
public class CsvWriter {
    private static FileWriter fr = null;
    private static BufferedWriter br = null;
    

    private static String getCSVHeaderAnimal(){
        String resultat = "ID,Employee_ID,BornDate,isAlive";
        return resultat;
    }
    
    private static String getCSVHeaderMamal(){
        String resultat = CsvWriter.getCSVHeaderAnimal();
        resultat+= ",animal_weight";
        return resultat;
    }
    
    private static String getCSVHeaderLion(){
        String resultat = CsvWriter.getCSVHeaderMamal() + ",roar_db";
        return resultat;
    }
    
    private static String getCSVHeaderBear(){
        String resultat = CsvWriter.getCSVHeaderMamal() + ",max_speed";
        return resultat;
    }
    
    private static String getCSVHeaderReptile(){
        String resultat = CsvWriter.getCSVHeaderAnimal() + ",blood_temperature";
        return resultat;
    }
    
    private static String getCSVHeaderSnake(){
        String resultat = CsvWriter.getCSVHeaderReptile() + ",Length,Skin_color";
        return resultat;
    }
    
    private static String getCSVHeaderLizard(){
        String resultat = CsvWriter.getCSVHeaderReptile() + ",Skin_color";
        return resultat;
    }
    
    private static String getCSVHeaderLocation(){
        String resultat = "ID,Cage_type,Name,Animals_ids";
        return resultat;
    }

    private static String getCSVHeaderEmployee(){
        String resultat = "ID,Salary,Name,Hired";
        return resultat;
    }
    
    private static void writeToCsvBear(LinkedList<Animal> allAnimals) throws IOException{
        fr = new FileWriter("bears.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderBear() + '\n');
        
        for(Animal anm : allAnimals){
            if(anm instanceof Bear){
                br.write(anm.getCSValue()+'\n');
            }
        }
        
        br.flush();
        fr.close();
    }
    
    private static void writeToCsvLion(LinkedList<Animal> allAnimals) throws IOException{
        fr = new FileWriter("lions.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderLion() + '\n');
        
        for(Animal anm : allAnimals){
            if(anm instanceof Lion){
                br.write(anm.getCSValue()+'\n');
            }
        }
        
        br.flush();
        fr.close();
    }
    
    private static void writeToCsvSnake(LinkedList<Animal> allAnimals) throws IOException{
        fr = new FileWriter("snake.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderSnake() + '\n');
        
        for(Animal anm : allAnimals){
            if(anm instanceof Snake){
                br.write(anm.getCSValue()+'\n');
            }
        }
        
        br.flush();
        fr.close();
    }
    
    private static void writeToCsvLizard(LinkedList<Animal> allAnimals) throws IOException{
        fr = new FileWriter("lizard.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderLizard() + '\n');
        
        for(Animal anm : allAnimals){
            if(anm instanceof Lizard){
                br.write(anm.getCSValue()+'\n');
            }
        }
        
        br.flush();
        fr.close();
    }
    
    private static void writeToCsvEmployee(ArrayList<Employee> allEmployees) throws IOException{
        fr = new FileWriter("employees.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderEmployee() + '\n');
        
        for(Employee anm : allEmployees){
            br.write(anm.getCSValue()+'\n');
            
        }
        
        br.flush();
        fr.close();
    }
    
    private static void writeToCsvLocations(LinkedList<Location> allLocations) throws IOException{
        fr = new FileWriter("location.csv");
        br = new BufferedWriter(fr);
        br.write(CsvWriter.getCSVHeaderLocation() + '\n');
        
        for(Location anm : allLocations){
            br.write(anm.getCSValue()+'\n');
            
        }
        
        br.flush();
        fr.close();
    }

    public static void writeToCsv(LinkedList<Animal> allAnimals, ArrayList<Employee> allEmployees, LinkedList<Location> allLocations) throws IOException{
        writeToCsvLion(allAnimals);
        writeToCsvBear(allAnimals);
        writeToCsvSnake(allAnimals);
        writeToCsvLizard(allAnimals);
        writeToCsvEmployee(allEmployees);
        writeToCsvLocations(allLocations);
    }
    
    
}
