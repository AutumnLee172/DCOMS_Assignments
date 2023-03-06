    package RMI_Structures;

import RMI_Structures.Order.Order_Details;
import java.io.FileOutputStream;
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
import java.util.HashMap;
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
    public boolean admin_login(String email, String passwords) throws RemoteException {
        boolean hasRecord = false;
        
        if(email.equals("admin@gmail.com") && passwords.equals("admin")){
            hasRecord = true;
        }else{
            hasRecord = false;
        }
        
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
    
     @Override
     public ArrayList<Order> getOrders(String Customer_ID)throws RemoteException{
        ArrayList<Order> Orders = new ArrayList<Order>();
            openConnection();
            
            try {
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM Orders WHERE customer_id = ?");
            psmt.setString(1, Customer_ID);
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next()){
                
                String id = rs.getString("id");
                String CustomerID = rs.getString("customer_id");              
                Double total = rs.getDouble("total");
                String date = rs.getString("date"); 
                String address = rs.getString("address"); 
                String contact_number = rs.getString("contact_number"); 
                String payment_method = rs.getString("payment_method"); 
                String status = rs.getString("status"); 
               
               Orders.add(new Order(id,CustomerID,date,address, contact_number, payment_method,status,total));
               
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return Orders; 
     }
    
     @Override
     public HashMap<String, String> getOrderDetails(String OrderID)throws RemoteException{
         HashMap<String, String> OrderDetails = new HashMap<>();
            
         openConnection();
         
         //put basic info into the hashmap
         try {
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM Orders WHERE id = ?");
            psmt.setString(1, OrderID);
            ResultSet rs = psmt.executeQuery();
            
                rs.next();
                String id = rs.getString("id");
                String CustomerID = rs.getString("customer_id");              
                Double total = rs.getDouble("total");
                String date = rs.getString("date"); 
                String address = rs.getString("address"); 
                String contact_number = rs.getString("contact_number"); 
                String payment_method = rs.getString("payment_method"); 
                String status = rs.getString("status"); 
               
                OrderDetails.put("Order_ID", id);
                OrderDetails.put("CustomerID", CustomerID);
                OrderDetails.put("total", "" + total);
                OrderDetails.put("date", "" + date);
                OrderDetails.put("address", "" + address);
                OrderDetails.put("contact_number", "" + contact_number);
                OrderDetails.put("payment_method", "" + payment_method);
                OrderDetails.put("status", "" + status);
            
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         //put further info 
        try {
            String content= "";
            //PreparedStatement psmt = conn.prepareStatement("SELECT * FROM order_details LEFT JOIN product ON CAST(product.prodid AS varchar(255)) = order_details.product_id   WHERE order_details.order_id = ? ");
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM order_details WHERE order_id = ? ");
            psmt.setString(1, OrderID);
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next()){          
                String product_id = rs.getString("product_id"); 
                int quantity = rs.getInt("quantity"); 
                PreparedStatement productquery = conn.prepareStatement("SELECT * FROM product WHERE prodid = ? ");
                productquery.setString(1, product_id);
                ResultSet rsproduct = productquery.executeQuery();
                rsproduct.next();
                String product_name = rsproduct.getString("prodname");
               
               content = content + quantity + "x " + product_name + " (id: " + product_id + ") , ";
            }
            OrderDetails.put("content", "" + content);              
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return OrderDetails; 
     }
     
     @Override
     public boolean change_order_status(String OrderID, String Status)throws RemoteException{
        boolean hasChanged = false;
        try {
            openConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
                pstmt.setString(1, Status);
                pstmt.setString(2, OrderID);
                pstmt.executeUpdate();
                hasChanged = true;
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
            hasChanged = false;
        }
        closeConnection();        
        return hasChanged;
     }
    //product function 
     @Override
     public ArrayList<Product> getProducts()throws RemoteException{
        ArrayList<Product> products = new ArrayList<Product>();
            openConnection();
            
            try {
            Statement stmt = conn.createStatement();
            String Query = "(SELECT * FROM product )";
            ResultSet rs = stmt.executeQuery(Query);
            
            while(rs.next()){
                
                String id = rs.getString("prodid");
                int prodid = Integer.parseInt(id);
                //customerID is fixed(logged customer's id)
                String prodname = rs.getString("prodname");
                String proddescript = rs.getString("proddescript");
                String prodcategory = rs.getString("prodcategory"); 
                String prodquantity = rs.getString("prodquantity"); 
                String prodprice = rs.getString("prodprice"); 
                byte[] image = rs.getBytes("image");
               
               products.add(new Product(prodid,prodname,proddescript,prodcategory,prodquantity, prodprice, image));
               
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return products; 
     }
    
    
    //Cart Functions -----------------------------------------------------------

    @Override
    public void addToCart(String customerID, int productID, int quantity) throws RemoteException{
      openConnection();
      
      try {  
      Statement checkExisting = conn.createStatement();
      ResultSet ce = checkExisting.executeQuery("SELECT * FROM CART WHERE CUSTOMER_ID ='"+customerID+"' AND PRODUCT_ID ='"+productID+"'");
      if(ce.next()==true){
          String selectedCart = ce.getString("ID");
          int newQuantity = ce.getInt("QUANTITY");
          newQuantity += quantity;
          updateCartQuantity(selectedCart,newQuantity);
      }else
      
      {
     
       //generate new ID 
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
                pstmt.setInt(3, productID);
                pstmt.setInt(4, quantity);
                //Statement stmt = conn.createStatement();
                pstmt.executeUpdate();
                
                closeConnection();
            }    
        }catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
     @Override
     public ArrayList<Cart> getCustomerCart(String customerID)throws RemoteException{
        ArrayList<Cart> customerCartList = new ArrayList<Cart>();
            openConnection();
            
            try {
            Statement stmt = conn.createStatement();
            String Query = "(SELECT * FROM CART WHERE QUANTITY > 0 AND CUSTOMER_ID = '" + customerID+ "')";
            ResultSet rs = stmt.executeQuery(Query);
            
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
     
     @Override
     public int findProductQuantity(String productID)throws RemoteException{
         int quantityLeft = 0;
            
         int prodID = Integer.parseInt(productID);
         openConnection();
        
       try {
         Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PRODID,PRODQUANTITY FROM PRODUCT WHERE PRODID=" + prodID);
            
            if (rs.next() == false) { 
                System.out.println("Something wrong, could not find any product with this ID."); 
            }else
            {
               String quantity = rs.getString("PRODQUANTITY");              
               quantityLeft = Integer.parseInt(quantity);
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }

         closeConnection();
         
         return quantityLeft;
     }
     
     @Override
     public void updateCartQuantity(String cartID, int quantity) throws RemoteException{
     openConnection();
     
     try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE CART SET QUANTITY =? WHERE ID = ?" );
            pstmt.setInt(1, quantity);
            pstmt.setString(2,cartID);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
     closeConnection();
     }
     
     @Override
     public void deleteCartItem(ArrayList<String> cartID) throws RemoteException{
     openConnection();
     
     try {
         for(String row : cartID){
            PreparedStatement pstmt = conn.prepareStatement("UPDATE CART SET QUANTITY =? WHERE ID = ?" );
            pstmt.setInt(1, 0);
            pstmt.setString(2,row);
            pstmt.executeUpdate();
         }
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
     closeConnection();
     }
     
    @Override
     public String createOrder(Order order, ArrayList<String> checkoutList) throws RemoteException{       
         openConnection();
         String newOrderID ="";
          
         try {  
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(ID) as MaxNumber FROM ORDERS");   
                int max = 0;
                while (rs.next()) {
                    max = rs.getInt("MaxNumber") + 1;
                }
                
                //insert new Record
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ORDERS VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                newOrderID = "ORDER" + max;
                
                pstmt.setString(1, newOrderID);
                pstmt.setString(2, order.getCustomerID());
                pstmt.setDouble(3, order.getTotal());
                pstmt.setString(4, order.getDate());
                pstmt.setString(5,order.getAddress());
                pstmt.setString(6, order.getContact_number());
                pstmt.setString(7, order.getPayment());
                pstmt.setString(8, order.getStatus());
                
                pstmt.executeUpdate();
                
       //Starting to create new Order details ~ ~ ~ ~ 
         ResultSet rs2 = stmt.executeQuery("SELECT COUNT(ID) as MaxNumber FROM ORDER_DETAILS");
          while(rs2.next()){
              max = rs2.getInt("MaxNumber") +1;
          }
          
           pstmt = conn.prepareStatement("INSERT INTO ORDER_DETAILS VALUES (?, ?, ?, ?)");
           String newODID;
           
           //checkoutList pattern per 'record'{CartID,ProdID,Quantity}
           for(int i = 0; i < checkoutList.size(); i++){
            newODID = "OD" + max;
            pstmt.setString(1, newODID);
            pstmt.setString(2, newOrderID);
            pstmt.setString(3, checkoutList.get(i+1));
            pstmt.setInt(4, Integer.parseInt(checkoutList.get(i+2)));
           
            i+=2;
            max++;
            pstmt.executeUpdate();
           }
                
        }catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        
        return newOrderID;
     }
     
     @Override
     public ArrayList<Address> getCustomerAddress(String customerID)throws RemoteException{
         ArrayList<Address> addressList = new ArrayList<Address>();
            openConnection();
            
            try {
            Statement stmt = conn.createStatement();
            String Query = "(SELECT * FROM ADDRESS WHERE CUSTOMER_ID = '" + customerID+ "')";
            ResultSet rs = stmt.executeQuery(Query);
            
            while(rs.next()){
                int id = rs.getInt("ID");  
                String address = rs.getString("ADDRESS");
                String numb = rs.getString("CONTACT_NUMBER");  
                String country = rs.getString("COUNTRYCODE");
               
               addressList.add(new Address(id,customerID,address,numb,country));
               
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return addressList; 
     }
     
        @Override
    public void addAddress(Address address)throws RemoteException{
           openConnection();
       //generate new ID
        try {  
                //insert new Record
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ADDRESS(CUSTOMER_ID,ADDRESS,CONTACT_NUMBER,COUNTRYCODE) VALUES (?, ?, ?, ?)");
                
                pstmt.setString(1, address.getCustomerID());
                pstmt.setString(2, address.getAddress());
                pstmt.setString(3, address.getContactNumber());
                pstmt.setString(4, address.getCountryCode());
                
                pstmt.executeUpdate();
                
        }catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection(); 
    }
     
     @Override
      public void editAddress(Address address)throws RemoteException    {
           openConnection();
       //generate new ID
        try {  
                //insert new Record
                PreparedStatement pstmt = conn.prepareStatement("UPDATE ADDRESS "
                                                              + "SET ADDRESS = ?, CONTACT_NUMBER = ?, COUNTRYCODE = ? "
                                                              + "WHERE ID = ? ");
                
                pstmt.setString(1, address.getAddress());
                pstmt.setString(2, address.getContactNumber());
                pstmt.setString(3, address.getCountryCode());
                pstmt.setInt(4, address.getAddressID());
                
                pstmt.executeUpdate();
                
        }catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
      }
      
      @Override
      public void deleteAddress(Address address) throws RemoteException {
        openConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ADDRESS WHERE ID = ?");
            pstmt.setInt(1, address.getAddressID());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }
      
      @Override
      public boolean initializePayment()throws RemoteException{
          PaymentThread paymentThread = new PaymentThread();
          paymentThread.start();

            // Wait for payment to complete
          while (!paymentThread.isPaymentSuccess()) {
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          return paymentThread.isPaymentSuccess();
      }
    // Manage Product --------------------------------------------------------------------
    @Override
    public String Add_New_Product(String prodname, String proddescript, String category, String quantity, String price, byte[] image) throws RemoteException {
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product(prodid,prodname,proddescript,prodcategory,prodquantity,prodprice,image) VALUES (?, ?, ?, ?, ?, ?, ?)");
            int newID = max;
            pstmt.setInt(1, newID);
            pstmt.setString(2, prodname);
            pstmt.setString(3, proddescript);
            pstmt.setString(4, category);
            pstmt.setString(5, quantity);
            pstmt.setString(6, price);
            pstmt.setBytes(7, image);
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
    
    @Override
     public void deleteProduct(ArrayList<Integer> prodid) throws RemoteException{
     openConnection();
     
     try {
            PreparedStatement pstmt = conn.prepareStatement("Delete from product WHERE prodID = ? ");
            for(int row : prodid){
                 pstmt.setInt(1, row);
                 //pstmt.setString(1,row);
                 pstmt.executeUpdate();
             }
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();    
   }
    
    
    //Manage customer order
     @Override
     public ArrayList<Order> getCustOrders()throws RemoteException{
        ArrayList<Order> Orders = new ArrayList<Order>();
            openConnection();
            
            try {
            Statement stmt = conn.createStatement();
            String Query = "(SELECT * FROM Orders )";
            ResultSet rs = stmt.executeQuery(Query);
            
            while(rs.next()){
                
                String id = rs.getString("id");
                String CustomerID = rs.getString("customer_id");              
                Double total = rs.getDouble("total");
                String date = rs.getString("date"); 
                String address = rs.getString("address"); 
                String contact_number = rs.getString("contact_number"); 
                String payment_method = rs.getString("payment_method"); 
                String status = rs.getString("status"); 
               
               Orders.add(new Order(id,CustomerID,date,address, contact_number, payment_method,status,total));
               
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(extint.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
       return Orders; 
     }
    
    
}
