package RMI_Structures;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Autumn
 */
public class Customer {
    private String Name;
    private String ID;
    
    public Customer(String id){
        setID(id);
    }
    
    public void setID(String id){
        this.ID = id;
    }
    
    public void setName(String name){
        this.Name = name;
    }
    
    public String getName(){
        return Name;
    }
}
