/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

/**
 *
 * @author User
 */
public class Product implements java.io.Serializable{
    
    private String prodname, category, quantity, price;
    private byte[] image;
    
    private int ID;
    
    public Product(String prodname, String category, String quantity, String price){
        this.setprodname(prodname);
        this.setcategory(category);
        this.setquantity(quantity);
        this.setprice(price);
    }
    
    //metamorphosis
     public Product(int ID, String prodname, String category, String quantity, String price, byte[] image){
        this.setID(ID);
        this.setprodname(prodname);
        this.setcategory(category);
        this.setquantity(quantity);
        this.setprice(price);
        this.setimage(image);
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public void setprodname(String prodname){
        this.prodname = prodname;
    }
    
     public void setcategory(String category){
        this.category = category;
    }
     
    public void setquantity(String quantity){
        this.quantity = quantity;
    }
    
     public void setimage(byte[] image){
        this.image = image;
    }
    
    public void setprice(String price){
        this.price = price;
    }
    
    public String getName(){
        return prodname;
    }
    
    public String getCategory(){
        return category;
    }
    
     public String getPrice(){
        return price;
    }
     
     public byte[] getImage(){
         return image;
     }
    
}
