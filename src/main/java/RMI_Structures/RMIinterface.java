package RMI_Structures;


import java.awt.List;
import java.rmi.*;
import java.net.*;

public interface RMIinterface extends Remote{
    public void openConnection()throws RemoteException;
    public void closeConnection()throws RemoteException;
    
   // public String getCustomerName(String id)throws RemoteException;
    public String customer_register(String email, String name, String passwords)throws RemoteException;
    public boolean customer_login(String email, String passwords)throws RemoteException;
    public Customer customer_setup(String email)throws RemoteException;
    
    //Cart Functions
    public void addToCart(String customerID, String productID, int quantity)throws RemoteException;
    public List<Cart> getCustomerCart(String customerID)throws RemoteException;
    public String findProductName(String productID) throws RemoteException;
    public double findProductPrice(String productID) throws RemoteException;
    
    //Admin 
    public String Add_New_Product(String prodname, String category, String quantity, String price) throws RemoteException; 
    
}

