/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Location;

import Animal.Animal;
import Animal.Mamal.Bear;
import Animal.Mamal.Lion;
import Animal.Reptile.Lizard;
import Animal.Reptile.Snake;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author carja
 */
public class Location {
    private final int cageType;
    private int id;
    private String Name;
    private LinkedList<Animal> animalList;
    private static ArrayList<Integer> idList = new ArrayList<Integer>();
    
    public Location(int cageType, String Name, int id) {
        this.cageType = cageType;
        this.Name = Name;
        this.setID(id);
        this.animalList = new LinkedList<Animal>();
    }
    
    public Location(int cageType, String Name, int id, LinkedList<Animal> animalList) {
        this.cageType = cageType;
        this.Name = Name;
        this.setID(id);
        this.animalList = animalList;
    }
    
    public String getCSValue(){
        String resultat = this.getID() + ","
                + this.cageType + ','
                + this.Name + ",\"";
        for(int i = 0;i < this.animalList.size(); i++){
            resultat += this.animalList.get(i).getID();
            if(i != this.animalList.size()-1)
                resultat += ' ';
            
        }        
        resultat += '"';
        return resultat;
                
    }
    
    public static void setidList(ArrayList<Integer> idList){
        Location.idList = idList;
    }
    
    public boolean searchIfAnimalIdInLocation(int ID){
        for(Animal anm : animalList){
            if(anm.getID() == ID)
                return true;
        }
        return false;
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
            this.id = maximumValue+1;
        }else{
            this.id = ID;
        }
        
        idList.add(this.id);
        
        
    }
    
    public void addAnimal(Animal animal)throws RuntimeException{
        if(animal.getType() != this.cageType){
            throw new RuntimeException("Cage not compatible!");
        }else{
            this.animalList.add(animal);
        }
    }
    
    public boolean removeAnimal(Animal animal){
        for(int i = 0;i<animalList.size();i++){
            if(animalList.get(i).equals(animal)){
                animalList.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    public LinkedList<String> getIdAndNameOfAnimals(){
        LinkedList<String> listAnimale = new LinkedList<>();
        for(Animal anm : this.animalList){
            listAnimale.add("ID:" + anm.getID());
        }
        return listAnimale;
    }
    
    public boolean removeAnimal(int id){
        for(int i = 0;i<animalList.size();i++){
            if(animalList.get(i).getID() == id){
                animalList.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String toString(){
        String result = "ID: " + this.id
                       +"\nName: " + this.Name
                       +"\n List of animals in this cage:";
        int size = this.animalList.size();
        if(size <= 0){
            result += "\nNo animals";
        }else{
            for(int i = 0;i<size;i++){
                int id = this.animalList.get(i).getID();
                Animal tempAnimal= this.animalList.get(i);
                result+='\n' + id + ": ";
                if(tempAnimal instanceof Lizard){
                    result+="soparla";
                }
                
                if(tempAnimal instanceof Snake){
                    result+="sarpe";
                }
                
                if(tempAnimal instanceof Bear){
                    result+="urs";
                }
                
                if(tempAnimal instanceof Lion){
                    result+="leu";
                }
                
                
            }
        }
        return result;
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
        final Location other = (Location) obj;
        if (this.cageType != other.cageType) {
            return false;
        }
        return Objects.equals(this.Name, other.Name);
    }
    
    public String getName() {
        return Name;
    }
    
    public int getCageType(){
        return this.cageType;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    public int getID() {
        return this.id;
    }

}
