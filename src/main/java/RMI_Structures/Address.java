/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

/**
 *
 * @author Boey
 */
public class Address implements java.io.Serializable{
    int addressID;
    String customerID,address,contactNumber,countryCode;

    public Address() {
    }

    public Address(String customerID, String address, String contactNumber, String countryCode) {
        //constructor for creating new address
        this.customerID = customerID;
        this.address = address;
        this.contactNumber = contactNumber;
        this.countryCode = countryCode;
    }
    
    public Address(int addressID, String customerID, String address, String contactNumber, String countryCode) {
        this.addressID = addressID;
        this.customerID = customerID;
        this.address = address;
        this.contactNumber = contactNumber;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    
}
