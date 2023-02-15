package RMI_Structures;

import java.rmi.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class register {
    public static void main(String args[])throws RemoteException
    {
        Registry reg = LocateRegistry.createRegistry(1040);
        reg.rebind("KGF", new extint());
       
    }
}
