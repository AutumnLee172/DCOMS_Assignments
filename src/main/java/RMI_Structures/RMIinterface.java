package RMI_Structures;


import java.rmi.*;
import java.net.*;

public interface RMIinterface extends Remote{
    public int add(int x, int y)throws RemoteException;
    public double circlearea(double r)throws RemoteException;
    public double circumference(double r)throws RemoteException;
    public double diameter(double r)throws RemoteException;
    public String getCustomerName(String id)throws RemoteException;
    public void customer_register(String name, String passwords)throws RemoteException;
}

