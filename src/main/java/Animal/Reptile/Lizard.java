/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal.Reptile;

import Employee.Employee;
import java.time.LocalDate;

/**
 *
 * @author carja
 */
public class Lizard extends Reptile{
    
    private final String skinColor;
    
    public Lizard(int ID,Employee employee, LocalDate bornDate, double bloodTemperature, String skinColor) {
        super.setID(ID);
        super.setEmployee(employee);
        super.setBornDate(bornDate);
        super.setBloodTemperature(bloodTemperature);
        super.setAlive(true);
        this.skinColor = skinColor;
    }
    
    public String getCSValue(){
        String resultat = super.getCSValue();
        resultat += ',' + skinColor;
        return resultat;
    }
    
    @Override
    public String toString() {
        String result = super.toString();
        result += "\nEste soparla: "
                + "\nculoare: " + skinColor;
        return result;
    }
    
    
    @Override
    public String getSkinColor() {
        return this.skinColor;
    }

    @Override
    public int getType() {
        return 3;
    }
    
}
