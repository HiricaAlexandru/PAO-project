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
public class Snake extends Reptile{
    private final String skinColor;
    private final double Length;
    public Snake(int ID,Employee employee, LocalDate bornDate, double bloodTemperature, String skinColor, double Length) {
        super.setID(ID);
        super.setEmployee(employee);
        super.setBornDate(bornDate);
        super.setBloodTemperature(bloodTemperature);
        super.setAlive(true);
        this.skinColor = skinColor;
        this.Length = Length;
    }
     
    public double getLength(){
        return this.Length;
    }
    
    @Override
    public String toString() {
        String result = super.toString();
        result += "\nEste sarpe: "
                + "\nculoare: " + skinColor
                + "\nlungime: " + Length;
        return result;
    }
    
    public String getCSValue(){
        String resultat = super.getCSValue();
        resultat += ',' + skinColor + ',' + Length;
        return resultat;
    }
    
    @Override
    public String getSkinColor() {
        return this.skinColor;
    }

    @Override
    public int getType() {
        return 4;
    }
}
