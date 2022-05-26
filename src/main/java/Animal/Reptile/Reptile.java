/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal.Reptile;

import Animal.Animal;

/**
 *
 * @author carja
 */
public abstract class Reptile extends Animal{
    private double bloodTemperature;
    
    @Override
    public String toString() {
        String result = "Reptila:"
                +"\n cu temperatura corpului de " + bloodTemperature + '\n';
        result += super.toString();
        
        return result;
    }
    
    public String getCSValue(){
        String resultat = super.getID() + ","
                + super.getEmployee().getID() + ","
                + super.getBornDate().toString() + ","
                + super.isAlive();
        resultat += "," + bloodTemperature;
        return resultat;
    }
    
    public double getBloodTemperature() {
        return bloodTemperature;
    }
    
    public abstract String getSkinColor();
    
    protected void setBloodTemperature(double bloodTemperature) {
        this.bloodTemperature = bloodTemperature;
    }
    
    
}
