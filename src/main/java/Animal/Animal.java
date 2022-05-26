/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal;
import Employee.Employee;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author carja
 */
public abstract class Animal {
    private int ID;
    private Employee employee;
    private LocalDate bornDate;
    private boolean alive;
    
    private static ArrayList<Integer> idList = new ArrayList<Integer>();
    
    public abstract String getCSValue();
    
    public boolean isAlive() {
        return alive;
    }
    
    public static void setidList(ArrayList<Integer> idList){
        Animal.idList = idList;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public abstract int getType();//method that will return an int based on the animal's type
    
    public int getID() {
        return ID;
    }
    
 
    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    protected void setID(int ID) {
        boolean found = false;
        Integer maximumValue = -2000;
        for (int i = 0; i < idList.size(); i++) {
            if(idList.get(i).equals(Integer.valueOf(ID))){
                found = true;
            }
            
            if(idList.get(i) > maximumValue){
                maximumValue = idList.get(i);
            }
        }
        if(found == true){
            this.ID = maximumValue+1;
        }else{
            this.ID = ID;
        }
        
        idList.add(this.ID);
        
        
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    protected void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if(other.getID() == this.getID())
            return true;
        
        return false;
    }

    @Override
    public String toString() {
        String result = 
                "\n ID = " + ID 
                + "\n ingrijitor: " + employee.getName()
                + "\n nascut la data de: " + bornDate;
        if(alive == false){
            result += "\n nu este viu";
        }else{
            result += "\n este viu";
        }
        return result;
    }
    
    
        
    
}
