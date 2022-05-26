/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsoleInterface;

import Animal.Animal;
import Animal.Mamal.Bear;
import Animal.Mamal.Lion;
import Animal.Mamal.Mamal;
import Animal.Reptile.Lizard;
import Animal.Reptile.Reptile;
import Animal.Reptile.Snake;
import Context.Context;
import Employee.Employee;
import Location.Location;
import Services.Audit;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author carja
 */
public class ConsoleInterface {
    private static ConsoleInterface obj = null;
    private static Context con = null;
    private static Scanner scan = null;
    
    private ConsoleInterface(){}
    
    public static void constructObject(Context context){
        if(con == null){
            con = context;
        }
        
        if(obj == null){
            obj = new ConsoleInterface();
        }
        
        if(scan == null){
            scan = new Scanner(System.in);
        }
    }
    
    public static ConsoleInterface getInstance() throws Exception{
        if(obj == null){
            obj = new ConsoleInterface();
        }
        if(scan == null){
            scan = new Scanner(System.in);
        }
        if(con == null)
            throw new Exception("No context!");
        
        return obj;
    }
    
    private LocalDate readDate() throws Exception{
        try{
            System.out.println("Introduceti ziua nasterii animalului");
            System.out.print("Ziua: ");
            int dayBeginning = scan.nextInt();
            System.out.print("Luna: ");
            int monthBeginning = scan.nextInt();
            System.out.print("An: ");
            int yearBeginning = scan.nextInt();
            scan.nextLine();
            LocalDate time1 = LocalDate.of(yearBeginning, monthBeginning, dayBeginning);
            return time1;
        }catch(Exception e){
            throw e;
        }
    }
    
    private Employee showShowAndReturnEmployee() throws Exception{
        LinkedList<String> names = con.getAllEmployeesNames();
        
        
        int numberEmp = 0;
        for(int i = 0;i<names.size();i++){
            
            if(con.getEmployee(names.get(i)).getHired() == true){
                System.out.println(""+i +": "+ names.get(i));
                numberEmp+=1;
                
            }
        }
        
        if(names.size() == 0 || numberEmp == 0){
            throw new Exception("Nu exista angajati!");
        }
        
        System.out.print("Pick option: ");
        int option;
        option = scan.nextInt();
        scan.nextLine();
        
        String name = names.get(option);
        
        try{
            Employee emp = con.getEmployee(name);
            return emp;
        }catch(Exception e){
            throw e;
        }
        
    }
    
    
    private Bear readBear(int ID, Employee employeeTemp, LocalDate bornDateTemp){
        Bear animal = null;
        System.out.print("Introduceti greutatea ursului: ");
        double weight = scan.nextDouble();
        System.out.print("Introduceti viteza maxima a ursului: ");
        double maxSpeed = scan.nextDouble();
        scan.nextLine();
        
        animal = new Bear(ID, employeeTemp, bornDateTemp, weight, maxSpeed);
        return animal;
       
    }
    
    private Lion readLion(int ID, Employee employeeTemp, LocalDate bornDateTemp){
        Lion animal = null;
        System.out.print("Introduceti greutatea leului: ");
        double weight = scan.nextDouble();
        scan.nextLine();
        animal = new Lion(ID, employeeTemp, bornDateTemp, weight);
        return animal;
       
        
    }
    
    private Lizard readLizard(int ID, Employee employeeTemp, LocalDate bornDateTemp){
        Lizard animal = null;
  
        System.out.print("Introduceti temperatura sangelui: ");
        double bloodTemperature = scan.nextDouble();
        scan.nextLine();
        System.out.print("Introduceti culoarea pielii: ");
        String skinColor = scan.nextLine();
        animal = new Lizard(ID, employeeTemp, bornDateTemp, bloodTemperature, skinColor);
        
        return animal;
    }
    
     private Snake readSnake(int ID, Employee employeeTemp, LocalDate bornDateTemp){
        Snake animal = null;
  
        System.out.print("Introduceti temperatura sangelui: ");
        double bloodTemperature = scan.nextDouble();
        scan.nextLine();
        System.out.print("Introduceti culoarea pielii: ");
        String skinColor = scan.nextLine();
        System.out.print("Lungimea sarpelui este (in cm): ");
        double length = scan.nextDouble();
        scan.nextLine();
        animal = new Snake(ID, employeeTemp, bornDateTemp, bloodTemperature, skinColor,length);
        return animal;
     }
    
    private Mamal readMamal(int ID, Employee employeeTemp, LocalDate bornDateTemp, int option){
        Mamal animalToReturn = null;
        
        if(option == 1){
            animalToReturn = readBear(ID, employeeTemp, bornDateTemp);
        }else{
            animalToReturn = readLion(ID, employeeTemp, bornDateTemp);
        }
        return animalToReturn;
    }
    
    private Reptile readReptile(int ID, Employee employeeTemp, LocalDate bornDateTemp, int option){
        Reptile animalToReturn = null;
        
        if(option == 3){
            animalToReturn = readLizard(ID, employeeTemp, bornDateTemp);
        }else{
            animalToReturn = readSnake(ID, employeeTemp, bornDateTemp);
        }
        return animalToReturn;
    }
    
    private Animal readAnimal() throws Exception{
        System.out.println("Pick an employee to take care of the animal: ");
        Employee employeeTemp = null;
        try{
            employeeTemp = showShowAndReturnEmployee();
            if(employeeTemp.getHired() == false)
                throw new Exception("No longer working");
        }catch(Exception e){
            throw e;
        }
        System.out.print("Type the ID: ");
        int ID = scan.nextInt();
        LocalDate bornDateTemp = readDate();
        
        
        System.out.println("Pick the type of animal to read");
        System.out.println("1. Bear");
        System.out.println("2. Lion");
        System.out.println("3. Lizard");
        System.out.println("4. Snake");
        System.out.print("Pick option: ");
        int option = scan.nextInt();
        Animal animalToCreate = null;
        
        if(option == 1 || option == 2){
            animalToCreate = readMamal(ID, employeeTemp, bornDateTemp,option);
        }else{
            animalToCreate = readReptile(ID, employeeTemp, bornDateTemp,option);
        }
        
        return animalToCreate;
        
    }
    
    private void readAndAddAnimal(){
        try{
            Animal animal = readAnimal();
            con.addAnimal(animal);
            Audit.writeToAudit("Read_Animal");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    private void declareTheDeathOfAnimal() throws Exception{
        LinkedList<Integer> Ids = con.getAllIDAnimals();
        Animal animal = null;
        for(Integer id: Ids){
            System.out.print("ID: "+ id +" ");
            try{
                animal = con.getAnimalById(id);
            }catch(Exception e){
                throw e;
            }
            if(animal instanceof Lizard){
                System.out.println("soparla");
            }
            if(animal instanceof Bear){
                System.out.println("urs");
            }
            if(animal instanceof Lion){
                System.out.println("leu");
            }
            if(animal instanceof Snake){
                System.out.println("sarpe");
            }
            
        }
        
        System.out.print("Scie ID:");
        int option = scan.nextInt();
        scan.nextLine();
        try{
            con.removeAnimal(con.getAnimalById(option));
            Audit.writeToAudit("Death_of_Animal");
        }catch(Exception e){
            throw e;
        }
        
    }
    
    private void declareTheDeathOfAnimalMenu(){
        try{
            declareTheDeathOfAnimal();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private void animalOperations(){
        System.out.println("Options");
        System.out.println("1. Read a new Animal");
        System.out.println("2. Declare the death of an animal");
        System.out.print("Pick option:");
        int option;
        option = scan.nextInt();
        scan.nextLine();
        switch(option){
            case 1:
                readAndAddAnimal();
                break;
            case 2:
                declareTheDeathOfAnimalMenu();
                break;
        }
        if(option > 2 || option < 1){
            System.out.println("Alegere invalida!");
            return;
        }
        
    }
    
    private void readEmployee(){
        System.out.print("ID angajat: ");
        int ID = scan.nextInt();
        scan.nextLine();
        System.out.print("Numele angajatului este: ");
        String nume = scan.nextLine();
        System.out.print("Salariul angajatului este de: ");
        double Salary = scan.nextDouble();
        scan.nextLine();
        Employee emp = new Employee(nume, Salary, ID);
        try{
            con.addEmployee(emp);
            Audit.writeToAudit("Read_Employee");
        }catch(Exception e){
            System.out.println("Eroare in a adauga un agajat!");
        }
    }
    
    private void fireEmployee(){
        LinkedList<Integer> Ids = con.getAllIDEmployees();
        LinkedList<String> names = con.getAllEmployeesNames();
        for(int i = 0; i < Ids.size();i++){
            
            try{
                if(con.getEmployeeById(Ids.get(i)).getHired() == true)
                    System.out.println("ID: "+ Ids.get(i) +": "+names.get(i)); 
            }catch(Exception e){}
            
        }
        System.out.print("Scrie ID pentru a fi sters: ");
        int option = scan.nextInt();
        scan.nextLine();
        try{
            con.removeEmployee(con.getEmployeeById(option));
            Audit.writeToAudit("Fired_Employee");
        }catch(Exception e){
            System.out.println("Eroare la stergere!");
        }
    }
    
    private void employeeOperations(){
        System.out.println("Options");
        System.out.println("1. Citeste un nou angajat");
        System.out.println("2. Concediaza un angajat");
        System.out.print("Alege optiune:");
        int option;
        option = scan.nextInt();
        scan.nextLine();
        
        switch(option){
            case 1:
                readEmployee();
                break;
            case 2:
                fireEmployee();
                break;
        }
        if(option > 2 || option < 1){
            System.out.println("Alegere invalida!");
            return;
        }
    }
    
    private void listAllEmployees() throws Exception{
        LinkedList<Integer> ids = con.getAllIDEmployees();
        
        for(Integer id : ids){
            System.out.println(con.getEmployeeById(id).toString());
        }
    }
    
    private void listAllAnimals() throws Exception{
        LinkedList<Integer> ids = con.getAllIDAnimals();
        
        for(Integer id : ids){
            System.out.println(con.getAnimalById(id).toString());
        }
    }
    private void listAllLocations() throws Exception{
        LinkedList<Integer> ids = con.getAllIdLocation();
        
        for(Integer id : ids){
            System.out.println(con.getLocationById(id).toString());
        }
    }
    
    private void afisari() {
        System.out.println("Alegeti optiune: ");
        System.out.println("1. Listeaza toate animalele");
        System.out.println("2. Listeaza toti angajatii");
        System.out.println("3. Listeaza toate locatiile");
        System.out.print("Alegeti optiunea: ");
        int option = scan.nextInt();
        scan.nextLine();
         
        try{
         
        switch(option){
            case 1:
                listAllAnimals();
                Audit.writeToAudit("List_Animals");
                break;
                 
             case 2:
                listAllEmployees();
                Audit.writeToAudit("List_Employees");
                break;
            case 3:
                listAllLocations();
                Audit.writeToAudit("List_Locations");
                break;
                 
         }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }

    
    private int readOption(){
        int option;
        System.out.print("Pick option:");
        option = scan.nextInt();
        scan.nextLine();
        
        switch(option){
            case 1:
                animalOperations();
                break;
            case 2:
                employeeOperations();
                break;
            case 3:
                locationOperations();
                break;
            case 4: 
                afisari();
                break;
            
        }
        if(option > 5 || option < 1)
            System.out.println("Alegere invalida!");
        
        return option;
            
        
    }
    
    public void showMenu(){
        int option = 100;
        
        while(option != 5){
            System.out.println("Menu: ");
            System.out.println("1. Operatiuni animale");
            System.out.println("2. Operationu angajati");
            System.out.println("3. Operatiuni locatii");
            System.out.println("4. Afisari");
            System.out.println("5. Iesire");
            option = readOption();
        }
    }

    private void locationOperations() {
        System.out.println("Options");
        System.out.println("1. Citeste o noua locatie");
        System.out.println("2. Pune un animal intr-o locatie");
        System.out.println("3. Sterge un animal dintr-o locatie");
        System.out.print("Pick option:");
        int option;
        option = scan.nextInt();
        scan.nextLine();
        
        switch(option){
            case 1:
                readNewLocation();
                break;
            case 2:
                addAnimalToLocation();
                break;
            case 3:
                removeAnimalFromLocation();
                break;
                
        }
        
        if(option > 3 || option < 1){
            System.out.println("Alegere invalida!");
        }
    
    }

    private void readNewLocation() {
        //int cageType, String Name, int id;
        System.out.print("Introduceti Id: ");
        int id = scan.nextInt();
        scan.nextLine();
        
        //1 leu 2 bear 3 lizard 4 snake
        System.out.println("Alegeti optiune a pentru tipul animalelor care pot fi tinute in acest loc:");
        System.out.println("1: Leu");
        System.out.println("2: Urs");
        System.out.println("3: Soparla");
        System.out.println("4: Sarpe");
        int cageType = scan.nextInt();
        scan.nextLine();
        
        if(cageType < 1 || cageType > 4){
            System.out.println("Alegere incorecta!");
            return;
        }
        System.out.print("Numele locatiei este: ");
        String name = scan.nextLine();
        
        Location loc = new Location(cageType, name, id);
        try{
            con.addLocation(loc);
            Audit.writeToAudit("Read_Location");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
     
    }
    
    private LinkedList<Integer> getAllAvailableAnimals(int option){
        LinkedList<Integer> Ids = con.getAllIDAnimals(option); 
        LinkedList<Integer> usedIDS = new LinkedList<>();
        LinkedList<Integer> locationId = con.getAllIdLocation();
        LinkedList<Integer> availableIDS = new LinkedList<>();
        for(Integer idLocation : locationId){
            try{
               Location locTemp = con.getLocationById(idLocation);
               if(locTemp.getCageType() == option){
                   for(Integer IdAnimal : Ids)
                       if(locTemp.searchIfAnimalIdInLocation(IdAnimal)){
                           usedIDS.add(IdAnimal);
                       }
               }
            }catch(Exception e){}
        }
        for(int i = 0;i<Ids.size();i++){
            boolean result = false;
            for(int j = 0;j<usedIDS.size();j++){
                if(Ids.get(i) == usedIDS.get(j))
                    result = true;
            }
            if(result == false){
                availableIDS.add(Ids.get(i));
            }
        }
        return availableIDS;
    }
    
    private void addAnimalToLocation() {
        LinkedList<String> locations= con.getAllIdAndNameStringLocation();
        for(String IdName : locations){
            System.out.println(IdName);
        }
        
        System.out.print("Id-ul custii alese este: ");
        int idCusca = scan.nextInt();
        scan.nextLine();
        
        int option; 
        try {
            option = con.getLocationById(idCusca).getCageType();
        } catch (Exception ex) {
            System.out.println("Eroare");
            return;
        }
        
        if(option > 4 || option < 1){
            System.out.println("Optiune invalida!");
            return;
        }
        LinkedList<Integer> Ids = getAllAvailableAnimals(option);
        if(Ids.size() == 0){
            System.out.println("No id available!");
            return;
        }
        System.out.println("Id-urile care pot fi alese sunt: ");
        for(Integer id : Ids){
            System.out.println("ID:" + id);
        }
        System.out.println("Id-ul ales este: ");
        int optionAnimal = scan.nextInt();
        scan.nextLine();
        try {
            Animal anm = con.getAnimalById(optionAnimal);
            con.getLocationById(idCusca).addAnimal(anm);
            Audit.writeToAudit("Add_Animal_To_Location");
        } catch (Exception ex) {
            System.out.println("A existat o eroare!");;
        }
        
        
        
    }

    private void removeAnimalFromLocation() {
       LinkedList<String> locations= con.getAllIdAndNameStringLocation();
        for(String IdName : locations){
            System.out.println(IdName);
        }
        
        System.out.print("Id-ul custii alese este: ");
        int idCusca = scan.nextInt();
        scan.nextLine(); 
        Location loc;
        try {
            loc = con.getLocationById(idCusca);
        } catch (Exception ex) {
            System.out.println("Eroare");
            return;
        }
        
        for(String anm : loc.getIdAndNameOfAnimals()){
            System.out.println(anm);
        }
        
        System.out.print("Scrieti id animal care doriti a fi sters: ");
        int id = scan.nextInt();
        scan.nextLine();
        
        loc.removeAnimal(id);
        try{
            Audit.writeToAudit("Remove_Animal_Location");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

   
}
