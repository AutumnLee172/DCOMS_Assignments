package RMI_Structures;


import java.awt.List;
import java.rmi.*;
import java.net.*;
import java.util.ArrayList;

public interface RMIinterface extends Remote{
    public void openConnection()throws RemoteException;
    public void closeConnection()throws RemoteException;
    
   // customer function
    public String customer_register(String email, String name, String passwords)throws RemoteException;
    public boolean customer_login(String email, String passwords)throws RemoteException;
    public Customer customer_setup(String email)throws RemoteException;
    public String customer_edit(String email, String username, String passwords)throws RemoteException;
    
    //Cart Functions
    public void addToCart(String customerID, String productID, int quantity)throws RemoteException;

    public ArrayList<Cart> getCustomerCart(String customerID)throws RemoteException;
    public String findProductName(String productID) throws RemoteException;
    public double findProductPrice(String productID) throws RemoteException;
    public void updateCartQuantity(String cartID,int quantity) throws RemoteException;
    public void deleteCartItem(ArrayList<String> cartID) throws RemoteException;
    
    //Admin 
    public String Add_New_Product(String prodname, String category, String quantity, String price, byte[] image) throws RemoteException; 
}

