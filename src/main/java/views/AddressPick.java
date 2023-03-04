/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import RMI_Structures.Address;
import RMI_Structures.Customer;
import RMI_Structures.RMIinterface;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Boey
 */
public class AddressPick extends javax.swing.JFrame {

    int selectedAddress =-1;
    Customer LoggedCustomer;
    ArrayList<Address> addressList = new ArrayList<>();
    RMIinterface Obj;
    boolean edit,selecting;
    JTextArea addressTA;
    JComboBox countrycode;
    JTextField number;
    
    /**
     * Creates new form AddressPick
     */

    public AddressPick(){
        initComponents();
    }
    
    //simply manage addresses
    public AddressPick(Customer cm) {
         this.LoggedCustomer = cm;
        initComponents();
        loadAddresses();
        btnSelect.setEnabled(false);
        selecting = false;
    }

    //to return something
    public AddressPick(Customer cm, JTextArea ta, JComboBox cb, JTextField tf) {
        this.LoggedCustomer = cm;
        this.addressTA = ta;
        countrycode = cb;
        number = tf;
        selecting = true;
        initComponents();
        loadAddresses();
        
    }
    
    public final void loadAddresses(){
        //load addresses ---------------
        try {
            Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
            addressList = Obj.getCustomerAddress(LoggedCustomer.getID());
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(AddressPick.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--------------------------------
        panelAddresses.setLayout(new GridLayout(addressList.size(), 1, 5, 1));

        for (int i = 0; i < addressList.size(); i++) {
            JTextArea addressDisplay = new JTextArea();
            addressDisplay.setText(addressList.get(i).getAddress() + "\n\n");
            addressDisplay.append(addressList.get(i).getCountryCode() + addressList.get(i).getContactNumber());
            
            addressDisplay.setEditable(false);
            addressDisplay.putClientProperty("AddressIndex", i);

            addressDisplay.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int AddressIndex = (int) addressDisplay.getClientProperty("AddressIndex");
                    selectedAddress = AddressIndex;
                }
            });
            
            //adjusting each 'address card' display attributes --
            addressDisplay.setLineWrap(true);
            addressDisplay.setCaretPosition(0);
            //addressDisplay.setPreferredSize(new Dimension(330, 80));

            JScrollPane areaScrollPane = new JScrollPane(addressDisplay);
            areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            areaScrollPane.getVerticalScrollBar().setValue(0);
            areaScrollPane.setPreferredSize(new Dimension(330, 80));
            // ---------------------------------------------------
            panelAddresses.add(areaScrollPane);

        }
    }

    
    public int selectCountryCode(String code){
        code = code.replaceAll("\\D+","");
        
        switch(code){
            case "60" -> {
                return 0;
            }
            case "62" -> {
                return 1;
            }
            case "63" -> {
                return 2;
            }
            case "65" -> {
                return 3;
            }   
            case "66" -> {
                return 4;
            }
            default -> {
                return 0;
            }
        }
    }
    
    public void doneButtonSelection(){
        if(selecting){
            AddressPick ap = new AddressPick(LoggedCustomer,addressTA,countrycode,number);
            ap.setVisible(true);
            dialogNewAddress.dispose();
            this.dispose();
        }else{
            AddressPick ap = new AddressPick(LoggedCustomer);
            ap.setVisible(true);
            dialogNewAddress.dispose();
            this.dispose();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogNewAddress = new javax.swing.JDialog();
        lblContact = new javax.swing.JLabel();
        comboCountryCode = new javax.swing.JComboBox<>();
        txtContactNumber = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        panelParent = new javax.swing.JScrollPane();
        panelAddresses = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSelect = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        dialogNewAddress.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogNewAddress.setResizable(false);

        lblContact.setText("Contact Number :");

        comboCountryCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(MY) +60", "(ID) +62", "(PH) +63", "(SG) +65", "(TH) +66" }));

        txtContactNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactNumberKeyTyped(evt);
            }
        });

        lblAddress.setText("Address               :");

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        txtAddress.setColumns(20);
        txtAddress.setLineWrap(true);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        javax.swing.GroupLayout dialogNewAddressLayout = new javax.swing.GroupLayout(dialogNewAddress.getContentPane());
        dialogNewAddress.getContentPane().setLayout(dialogNewAddressLayout);
        dialogNewAddressLayout.setHorizontalGroup(
            dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogNewAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dialogNewAddressLayout.createSequentialGroup()
                        .addGroup(dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddress)
                            .addComponent(lblContact))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dialogNewAddressLayout.createSequentialGroup()
                                .addComponent(comboCountryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogNewAddressLayout.setVerticalGroup(
            dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogNewAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dialogNewAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCountryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDone, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout panelAddressesLayout = new javax.swing.GroupLayout(panelAddresses);
        panelAddresses.setLayout(panelAddressesLayout);
        panelAddressesLayout.setHorizontalGroup(
            panelAddressesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );
        panelAddressesLayout.setVerticalGroup(
            panelAddressesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        panelParent.setViewportView(panelAddresses);

        btnNew.setText("Add New Address");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSelect.setText("Select");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelParent)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelParent, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete))
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        dialogNewAddress.setSize(new java.awt.Dimension(370, 279));
        dialogNewAddress.setLocationRelativeTo(null);
        dialogNewAddress.setVisible(true);
        
        txtAddress.setText("");
        comboCountryCode.setSelectedIndex(0);
        txtContactNumber.setText("");
        
        edit = false;
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtContactNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNumberKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContactNumberKeyTyped

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        if (txtAddress.getText().isEmpty() || txtContactNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address or contact number is/are empty.");
        }else{
        try {
            if (edit) {
                Address address = addressList.get(selectedAddress);
                address.setAddress(txtAddress.getText());
                address.setContactNumber(txtContactNumber.getText());
                address.setCountryCode(comboCountryCode.getSelectedItem().toString());

                Obj.editAddress(address);
                
                doneButtonSelection();
            } else {               
                String address = txtAddress.getText();
                String numb =  txtContactNumber.getText();
                String country = comboCountryCode.getSelectedItem().toString();
                Address NewAddress = new Address(LoggedCustomer.getID(),address,numb,country);

                Obj.addAddress(NewAddress);
                
            AddressPick ap = new AddressPick();
            ap.setVisible(true);
            dialogNewAddress.dispose();
            this.dispose();
            doneButtonSelection();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AddressPick.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(selectedAddress < 0){
          JOptionPane.showMessageDialog(this, "No address is selected.");  
        }else{
        
        dialogNewAddress.setSize(new java.awt.Dimension(370, 279));
        dialogNewAddress.setLocationRelativeTo(null);
        dialogNewAddress.setVisible(true);
        
        txtAddress.setText(addressList.get(selectedAddress).getAddress());
        comboCountryCode.setSelectedIndex(selectCountryCode(addressList.get(selectedAddress).getCountryCode()));
        txtContactNumber.setText(addressList.get(selectedAddress).getContactNumber());
        
        edit = true;
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
                if(selectedAddress < 0){
          JOptionPane.showMessageDialog(this, "No address is selected.");  
        }else{
        int input = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this address info?", "Delete confirmation ",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == JOptionPane.OK_OPTION) {
            try {
                Obj.deleteAddress(addressList.get(selectedAddress));
                
                AddressPick ap = new AddressPick();
            ap.setVisible(true);
            this.dispose();
            } catch (RemoteException ex) {
                Logger.getLogger(AddressPick.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        if(selectedAddress < 0){
          JOptionPane.showMessageDialog(this, "No address is selected.");  
        }else{
        addressTA.setText(addressList.get(selectedAddress).getAddress());
        countrycode.setSelectedIndex(selectCountryCode(addressList.get(selectedAddress).getCountryCode()));
        number.setText(addressList.get(selectedAddress).getContactNumber());
        this.dispose();
        }
        
    }//GEN-LAST:event_btnSelectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddressPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddressPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddressPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddressPick().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSelect;
    private javax.swing.JComboBox<String> comboCountryCode;
    private javax.swing.JDialog dialogNewAddress;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblContact;
    private javax.swing.JPanel panelAddresses;
    private javax.swing.JScrollPane panelParent;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtContactNumber;
    // End of variables declaration//GEN-END:variables
}
