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
    
   private String ID, prodname, proddescript, category;
    private byte[] image;
    private int quantity;
    private double  price;
    
    //metamorphosis
    public Product(String ID, String prodname, String proddescript, String category, int quantity, byte[] image, double price){
        this.setID(ID);
        this.setprodname(prodname);
        this.setproddescript(proddescript);
        this.setcategory(category);
        this.setquantity(quantity);
        this.setprice(price);
        this.setimage(image);
    };
    
    public String getID(){
        return ID;
    }
     
    public void setID(String id){
        this.ID = id;
    }
    
    public String getName(){
        return prodname;
    }
    
    public void setprodname(String prodname){
        this.prodname = prodname;
    }
    
    public String getDescript(){
        return proddescript;
    }
    
    public void setproddescript(String proddescript){
        this.proddescript = proddescript;
    }
    
    public String getCategory(){
        return category;
    }
    
    public void setcategory(String category){
        this.category = category;
    }
     
    public int getQuantity() {
        return quantity;
    }
    
    public void setquantity(int quantity){
        this.quantity = quantity;
    }
    
    public byte[] getImage(){
         return image;
    }
     
    public void setimage(byte[] image){
        this.image = image;
    }
    
    public double getPrice(){
        return price;
    }
     
    public void setprice(double price){
        this.price = price;
    }
    
}
