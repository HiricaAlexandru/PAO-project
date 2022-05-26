/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee;

import java.util.ArrayList;

/**
 *
 * @author carja
 */
public class Employee {
    private final String Name;
    private double Salary;
    private int ID;
    private static ArrayList<Integer> idList = new ArrayList<Integer>();
    private boolean hired;
    
    
    public Employee(String Name, double Salary, int ID) {
        this.Name = Name;
        this.Salary = Salary;
        this.setID(ID);
        this.hired = true;
    }
    
    public Employee(String Name, double Salary, int ID, boolean hired) {
        this.Name = Name;
        this.Salary = Salary;
        this.setID(ID);
        this.hired = hired;
    }
    
    public String getCSValue(){
        String resultat = this.getID() + ","
                + this.Salary + ','
                + this.Name + ','
                + this.hired;
        return resultat;        
    }
    
    public String getName(){
        return this.Name;
    }

    public boolean getHired(){
        return this.hired;
    }
    
    public static void setidList(ArrayList<Integer> idList){
        Employee.idList = idList;
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
        final Employee other = (Employee) obj;
        return this.ID == other.ID;
    }
    
    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
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

    public Integer getID() {
        return this.ID;
    }
    
    @Override 
    public String toString(){
    
        String result = "Angajatul: " + this.Name
                        +"\n are un salariu: " + this.Salary
                        +"\nID: " + this.ID;
        
        if(this.hired == true)
            result+="\neste angajat";
        else
            result+="\nnu mai lucreaza";
        return result;
    }
    
    public void setHired(boolean hired){
        this.hired = hired;
    }
}
