/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

/**
 *
 * @author Boey
 */
public class Order implements java.io.Serializable{
    private String orderID,customerID,date,address,contact_number,payment,status;
    private double total;
    
    public Order(){     
    }

    public Order(String orderID, String customerID, String date, String address, String contact_number, String payment, String status, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
        this.address = address;
        this.contact_number = contact_number;
        this.payment = payment;
        this.status = status;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
 public class Order_Details implements java.io.Serializable{
     private String odID,orderID,productID;
             private int quantity;

        public Order_Details() {
        }

        public Order_Details(String odID, String orderID, String productID, int quantity) {
            this.odID = odID;
            this.orderID = orderID;
            this.productID = productID;
            this.quantity = quantity;
        }

        public String getOdID() {
            return odID;
        }

        public void setOdID(String odID) {
            this.odID = odID;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getProductID() {
            return productID;
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
     
     
 }   
}
