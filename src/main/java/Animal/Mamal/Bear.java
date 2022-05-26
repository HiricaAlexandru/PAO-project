/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal.Mamal;

import Employee.Employee;
import java.time.LocalDate;

/**
 *
 * @author carja
 */
public class Bear extends Mamal{
    public double maxSpeed;
    public Bear(int ID,Employee employee, LocalDate bornDate, double animalWeight, double maxSpeed) {
        super.setID(ID);
        super.setEmployee(employee);
        super.setBornDate(bornDate);
        super.setWeight(animalWeight);
        super.setAlive(true);
        this.maxSpeed = maxSpeed;
    }
    
    public String getCSValue(){
        String resultat = super.getCSValue();
        resultat += ',' + maxSpeed;
        return resultat;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
    
    @Override
    public String toString() {
        String result = super.toString();
        result += "\nEste urs si viteza sa maxima este de: " + maxSpeed + " km/h";
        return result;
    }
    
    @Override
    public double getNecessaryFoodWeight() {
        double Weight = super.getWeight();
        return Weight * 1.4;
    }

    @Override
    public int getType() {
        return 2;
    }
    
}
