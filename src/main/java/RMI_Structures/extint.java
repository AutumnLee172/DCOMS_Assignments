package RMI_Structures;

import java.rmi.*;
import java.net.*;
import java.lang.Math;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Autumn
 */
public class extint extends UnicastRemoteObject implements RMIinterface {

    Connection conn;

    public extint() throws RemoteException {
        super();
    }

    @Override
    public int add(int x, int y) throws RemoteException {
        return x + y;
    }

    @Override
    public double circlearea(double r) throws RemoteException {
        return 3.142 * (Math.pow(r, 2));
    }

    @Override
    public double circumference(double r) throws RemoteException {
        return 2 * 3.142 * r;
    }

    @Override
    public double diameter(double r) throws RemoteException {
        return 2 * r;
    }

    @Override
    public void openConnection()throws RemoteException{
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/KGF", "dcoms", "1234");       
                conn.setAutoCommit(false);
                System.out.println("Connection Created");
            
            } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void closeConnection()throws RemoteException{
        try {
            conn.commit();
               conn.close();
               System.out.println("Connection closed");               
            } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public String customer_register(String email, String name, String passwords) throws RemoteException {
        String result = "";
        try {        
            openConnection();
            
            //check if email exists
            PreparedStatement checkEmail = conn.prepareStatement("SELECT COUNT(*) AS NumberofUser FROM customers WHERE email = ?");
            checkEmail.setString(1, email);
            ResultSet emailResult = checkEmail.executeQuery();
            emailResult.next();
            //if email exists
            if (emailResult.getInt(1) != 0) {
                result = "Email existed";
            } else {
                //if email does not exist
                //generate new ID
                Statement getMaxID = conn.createStatement();
                ResultSet rs = getMaxID.executeQuery("SELECT COUNT(id) as MaxNumber FROM customers");
                int max = 0;
                while (rs.next()) {
                    max = rs.getInt("MaxNumber") + 1;
                }
                //insert new Record
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers(id,name,password,email) VALUES (?, ?, ?, ?)");
                String newID = "C" + max;
                pstmt.setString(1, newID);
                pstmt.setString(2, name);
                pstmt.setString(3, passwords.trim());
                pstmt.setString(4, email.trim());
                //Statement stmt = conn.createStatement();
                pstmt.executeUpdate();
                
                closeConnection();
                
                result = "Successfully registered!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
            result = "An error has occured!";
        }
        return result;
    }

    @Override
    public boolean customer_login(String email, String passwords) throws RemoteException {
        boolean hasRecord = false;
        try {
            openConnection();
            
            PreparedStatement psmt = conn.prepareStatement("SELECT COUNT(*) AS NumberofUser FROM customers WHERE email = ? AND password = ?");
            psmt.setString(1, email);
            psmt.setString(2, passwords);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            //if email exists
            if(rs.getInt(1) != 0) {
                hasRecord = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        
        return hasRecord;
    }

    @Override
    public Customer customer_setup(String email) throws RemoteException {
        String name = null, id = null;
        try {
            openConnection();
            
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM customers WHERE email = ?");
            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            name = rs.getString("name");
            id = rs.getString("id");
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        Customer cm = new Customer(email, name, id);
        return cm;
    }
    
    //Cart Functions -----------------------------------------------------------

    @Override
    public void addToCart() throws RemoteException{
      
    }
    
    
}
