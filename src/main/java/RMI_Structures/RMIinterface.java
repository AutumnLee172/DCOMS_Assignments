package RMI_Structures;


import java.awt.List;
import java.rmi.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface RMIinterface extends Remote{
    public void openConnection()throws RemoteException;
    public void closeConnection()throws RemoteException;
    
   // customer function
    public String customer_register(String email, String name, String passwords)throws RemoteException;
    public boolean customer_login(String email, String passwords)throws RemoteException;
    public Customer customer_setup(String email)throws RemoteException;
    public String customer_edit(String email, String username, String passwords)throws RemoteException;
    public ArrayList<Order> getOrders(String Customer_ID)throws RemoteException;
    public HashMap<String, String> getOrderDetails(String OrderID)throws RemoteException;
    public boolean change_order_status(String OrderID, String Status)throws RemoteException;
    
    //admin login
    public boolean admin_login(String email, String passwords)throws RemoteException;
    
    // product function
    public ArrayList<Product> getProducts()throws RemoteException;
    
    //Cart Functions
    public void addToCart(String customerID, int productID, int quantity)throws RemoteException;
    public ArrayList<Cart> getCustomerCart(String customerID)throws RemoteException;
    public String findProductName(String productID) throws RemoteException;
    public double findProductPrice(String productID) throws RemoteException;
    public int findProductQuantity(String productID)throws RemoteException;
    public void updateCartQuantity(String cartID,int quantity) throws RemoteException;
    public void deleteCartItem(ArrayList<String> cartID) throws RemoteException;
    
    public String createOrder(Order order, ArrayList<String> checkoutList) throws RemoteException;
    
    public ArrayList<Address> getCustomerAddress(String customerID)throws RemoteException;
    public void addAddress(Address address)throws RemoteException;
    public void editAddress(Address address)throws RemoteException;
    public void deleteAddress(Address address)throws RemoteException;
    public boolean initializePayment()throws RemoteException;
    
    //Admin 
    public String Add_New_Product(String prodname, String proddescript, String category, String quantity, String price, byte[] image) throws RemoteException; 
    public void deleteProduct(ArrayList<Integer> prodid) throws RemoteException;
    public void updateProduct(String prodid,String quantity, String price) throws RemoteException;
    
    //manage customer order
    public ArrayList<Order> getCustOrders()throws RemoteException;


}

