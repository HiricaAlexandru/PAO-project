/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal.Mamal;

import Animal.Animal;

/**
 *
 * @author carja
 */
public abstract class Mamal extends Animal{
    private double animalWeight;
    
    public String getCSValue(){
        String resultat = super.getID() + ","
                + super.getEmployee().getID() + ","
                + super.getBornDate().toString() + ","
                + super.isAlive();
        resultat += "," + animalWeight;
        return resultat;
                
    }
    
    public double getWeight(){
        return this.animalWeight;
    }
    
    protected void setWeight(double animalWeight){
        this.animalWeight = animalWeight;
    }

    @Override
    public String toString() {
        String result = "Mamiferul:"
                +"\n cu greutatea " + animalWeight + '\n';
        result += super.toString();
        
        return result;
    }
    
    public abstract double getNecessaryFoodWeight();//method that calculates the necessary food intake
    
    
}
