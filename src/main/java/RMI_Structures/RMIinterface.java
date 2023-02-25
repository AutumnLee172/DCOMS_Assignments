package RMI_Structures;


import java.rmi.*;
import java.net.*;

public interface RMIinterface extends Remote{
    public int add(int x, int y)throws RemoteException;
    public double circlearea(double r)throws RemoteException;
    public double circumference(double r)throws RemoteException;
    public double diameter(double r)throws RemoteException;
   // public String getCustomerName(String id)throws RemoteException;
    public String customer_register(String email, String name, String passwords)throws RemoteException;
    public boolean customer_login(String email, String passwords)throws RemoteException;
    public Customer customer_setup(String email)throws RemoteException;
    
    
    //Cart Functions
    public void addToCart();
}

