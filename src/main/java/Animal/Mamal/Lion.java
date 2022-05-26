/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal.Mamal;

import Employee.Employee;
import static java.lang.Math.random;
import java.time.LocalDate;

/**
 *
 * @author carja
 */
public class Lion extends Mamal{
    private double roarDb;
    public Lion(int ID,Employee employee, LocalDate bornDate, double animalWeight) {
        super.setID(ID);
        super.setEmployee(employee);
        super.setBornDate(bornDate);
        super.setWeight(animalWeight);
        super.setAlive(true);
        this.roarDb = Math.random() * 10 + 100;
    }
    
    public Lion(int ID,Employee employee, LocalDate bornDate, double animalWeight, double roarDb) {
        super.setID(ID);
        super.setEmployee(employee);
        super.setBornDate(bornDate);
        super.setWeight(animalWeight);
        super.setAlive(true);
        this.roarDb = roarDb;
    }
    
    public String getCSValue(){
        String resultat = super.getCSValue();
        resultat += ',' + roarDb;
        return resultat;
    }
    
    
    
    
    public double getRoarDb(){
        return this.roarDb;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += "\nEste Leu si iar ragetul sau are: " + roarDb + " decibeli";
        return result;
    }
    
    @Override
    public double getNecessaryFoodWeight() {
        double Weight = super.getWeight();
        return 0.2 * Weight;
    }

    @Override
    public int getType() {
        //1 is for the lion
        return 1;
    }
    
}
