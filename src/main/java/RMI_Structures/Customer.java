package RMI_Structures;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Autumn
 */
public class Customer implements java.io.Serializable{
    private String Name, ID, Email;
    
    public Customer(String email, String Name, String ID){
        this.setEmail(email);
        this.setName(Name);
        this.setID(ID);
    }
    
    public void setID(String id){
        this.ID = id;
    }
    
     public void setEmail(String Email){
        this.Email = Email;
    }
    
    public void setName(String name){
        this.Name = name;
    }
    
    public String getID(){
        return Name;
    }
    
    public String getName(){
        return Name;
    }
}
