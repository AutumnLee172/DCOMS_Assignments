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
import java.util.ArrayList;
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
    
    @Override
    public String customer_edit(String email, String username, String passwords) throws RemoteException {
       String result = null;
       try {
            openConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE customers SET name = ?, password = ? WHERE email = ?");
                pstmt.setString(1, username);
                pstmt.setString(2, passwords.trim());
                pstmt.setString(3, email.trim());
                pstmt.executeUpdate();
                result = "Successfully updated";
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
            result = "An error has occurred: " + ex;
        }
        closeConnection();
        return result;
    }
    
    //Cart Functions -----------------------------------------------------------

    @Override
    public void addToCart(String customerID, String productID, int quantity) throws RemoteException{
      openConnection();
       //generate new ID
        try {  
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(id) as MaxNumber FROM CART");   
                int max = 0;
                while (rs.next()) {
                    max = rs.getInt("MaxNumber") + 1;
                }
                //insert new Record
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CART VALUES (?, ?, ?, ?)");
                String newID = "CART" + max;
                
                pstmt.setString(1, newID);
                pstmt.setString(2, customerID);
                pstmt.setString(3, productID);
                pstmt.setInt(4, quantity);
                //Statement stmt = conn.createStatement();
                pstmt.executeUpdate();
                
        }catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
      
    }
    
   // @Override
     public ArrayList<Cart> getCustomerCart(String customerID)throws RemoteException{
        ArrayList<Cart> customerCartList = new ArrayList<Cart>();
            openConnection();
            
            try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CART WHERE Customer_ID ='" + customerID + "'");
            
            while(rs.next()){
                String id = rs.getString("ID");
                //customerID is fixed(logged customer's id)
                String product = rs.getString("Product_ID");
                int quantity = rs.getInt("Quantity");               
               
               customerCartList.add(new Cart(id,customerID,product,quantity));
               
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return customerCartList; 
     }
     
    @Override
     public String findProductName(String productID) throws RemoteException{
         String result = "";
         int prodID = Integer.parseInt(productID);
         
         openConnection();
         
         Statement stmt;
        try {
            stmt = conn.createStatement();
       
            ResultSet rs = stmt.executeQuery("SELECT PRODID,PRODNAME FROM PRODUCT WHERE PRODID=" + prodID);
            
            if (rs.next() == false) { 
                System.out.println("Something wrong, could not find any product with this ID."); 
            }else
            {
               result = rs.getString("PRODNAME");          
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }

         closeConnection();
         
         return result;
     }
    
     @Override
     public double findProductPrice(String productID) throws RemoteException{
         double result = 0.0;
         int prodID = Integer.parseInt(productID);
         openConnection();
        
       try {
         Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PRODID,PRODPRICE FROM PRODUCT WHERE PRODID=" + prodID);
            
            if (rs.next() == false) { 
                System.out.println("Something wrong, could not find any product with this ID."); 
            }else
            {
               String productPrice = rs.getString("PRODPRICE");              
               result = Double.parseDouble(productPrice);
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }

         closeConnection();
         
         return result;
     }
     
    // Admin --------------------------------------------------------------------
    @Override
    public String Add_New_Product(String prodname, String category, String quantity, String price) throws RemoteException {
       String result = "";
        try {
            openConnection();         
            
            Statement getMaxID = conn.createStatement();
            ResultSet rs = getMaxID.executeQuery("SELECT COUNT(prodid) as MaxNumber FROM product");
            int max = 0;
            while (rs.next()) {
                max = rs.getInt("MaxNumber") + 1;
            }
                
            //insert new Record
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product(prodid,prodname,prodcategory,prodquantity,prodprice) VALUES (?, ?, ?, ?,?)");
            int newID = max;
            pstmt.setInt(1, newID);
            pstmt.setString(2, prodname);
            pstmt.setString(3, category);
            pstmt.setString(4, quantity);
            pstmt.setString(5, price);
            //Statement stmt = conn.createStatement();
            pstmt.executeUpdate();
            
            closeConnection();
             
            result = "Successfully added new item!";         
            
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
            result = "Please try again!";
        }
       return result;
    }
    
    
}
