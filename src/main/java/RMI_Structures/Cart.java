/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

/**
 *
 * @author Boey
 */
public class Cart implements java.io.Serializable{
     private String CartID, CustomerID, ProductID;
     private int Quantity;
     
     public Cart(){
     }
     
     public Cart(String cartID, String customerID, String productID, int quantity){
         this.CartID = cartID;
         this.CustomerID = customerID;
         this.ProductID = productID;
         this.Quantity = quantity;
     };

    public String getCartID() {
        return CartID;
    }

    public void setCartID(String CartID) {
        this.CartID = CartID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
     
     
}
