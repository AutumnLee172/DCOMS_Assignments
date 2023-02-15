import java.rmi.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {
    public static void main(String args[])throws MalformedURLException, NotBoundException, RemoteException
    {
        RMIinterface Obj = (RMIinterface)Naming.lookup("rmi://localhost:1040/add");
        Scanner sc = new Scanner(System.in);//accepting from the user
        System.out.println("Enter any number");
        double r = sc.nextDouble();
        System.out.print("Radius is " + r + '\n');
        System.out.print("The area of the cicrle is : " + Obj.circlearea(r) + '\n');
        System.out.print("The circumference of the circle is : " + Obj.circumference(r) + '\n');
        System.out.print("The diameter of the circle is : " + Obj.diameter(r) + '\n');
    }
}
