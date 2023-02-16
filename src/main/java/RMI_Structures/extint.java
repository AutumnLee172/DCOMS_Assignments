package RMI_Structures;

import java.rmi.*;
import java.net.*;
import java.lang.Math;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Autumn
 */
public class extint extends UnicastRemoteObject implements RMIinterface{
     Connection conn;
    public extint()throws RemoteException
    {   
        super();
    }
    @Override
    public int add(int x, int y)throws RemoteException
    {
        return x+y;
    }
    @Override
    public double circlearea(double r)throws RemoteException
    {
        return 3.142*(Math.pow(r, 2));
    }
    @Override
    public double circumference(double r)throws RemoteException
    {
        return 2*3.142*r;
    }
    @Override
    public double diameter(double r)throws RemoteException
    {
        return 2*r;
    }
    @Override
    public String getCustomerName(String id)throws RemoteException
    {
       Customer cm = new Customer(id);
       return cm.getName();
    }
    @Override
    public void customer_register(String name, String passwords)throws RemoteException
    {
      
       try {
       conn = DriverManager.getConnection("jdbc:derby://localhost:1527/KGF","dcoms","1234");
       conn.setAutoCommit(false);
       System.out.println("Connection Created");
       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers(id,name,password) VALUES (?, ?, ?)");
       //temp id, should change it to automate id afterwards
       String temp = "C003";
       pstmt.setString(1, temp);
       pstmt.setString(2, name);
       pstmt.setString(3, passwords);
       //Statement stmt = conn.createStatement();
       pstmt.executeUpdate();
       conn.commit();
       conn.close();
        }
       catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Successfully inserted");
    }
}
