/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import RMI_Structures.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Autumn
 */
public class History extends javax.swing.JFrame {

    private JPanel orderPanel;
    Customer LoggedCustomer;

    /**
     * Creates new form History
     */
    public History() {
        initComponents();
    }

    public History(Customer cm) throws IOException {
        initComponents();
        this.LoggedCustomer = cm;
        this.setLocationRelativeTo(null);
        orderPanel = new JPanel();
        orderPanel.setBackground(Color.WHITE);
        pnlOrders.setViewportView(orderPanel);
        try {
            RMIinterface Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
            ArrayList<Order> OrderArrayList = Obj.getOrders(LoggedCustomer.getID());
            Order[] Orders = new Order[OrderArrayList.size()];
            OrderArrayList.toArray(Orders);
            setOrders(Orders);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void setOrders(Order[] orders) throws FileNotFoundException, IOException {
    orderPanel.removeAll();
    int numOrders = orders.length;
    orderPanel.setLayout(new GridLayout(numOrders, 1, 5, 5)); // use a vertical grid layout
    for (int i = 0; i < numOrders; i++) {
        JPanel orderSubPanel = new JPanel();
        orderSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        orderSubPanel.setLayout(new BorderLayout());
        final String od = orders[i].getOrderID();
        // add a MouseListener to each product panel
        orderSubPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // display the selected product
                    displayOrder(od);
                } catch (IOException ex) {
                    Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        JLabel dateLabel = new JLabel(orders[i].getDate() + " (id: " + orders[i].getOrderID() + ")");
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        JLabel address = new JLabel("Address: " + orders[i].getAddress());
        JLabel status = new JLabel("Status: " + orders[i].getStatus());
        JLabel total = new JLabel("Total: $" + orders[i].getTotal());
        labelsPanel.add(dateLabel);
        labelsPanel.add(address);
        labelsPanel.add(status);
        labelsPanel.add(total);
        orderSubPanel.add(labelsPanel, BorderLayout.CENTER);

        // create and add the button to the sub-panel's east (right-hand side)
        JButton button = new JButton("Request to cancel");
         button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // display the selected order details
                    cancelOrder(od);
                } catch (IOException ex) {
                    Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
         if(orders[i].getStatus().equals("Completed") || orders[i].getStatus().equals("Request for cancel")){
             button.setEnabled(false);
         }
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(button, BorderLayout.EAST);
        orderSubPanel.add(buttonPanel, BorderLayout.SOUTH);

        orderPanel.add(orderSubPanel);
    }
    orderPanel.revalidate();
    orderPanel.repaint();
}


    private void displayOrder(String OrderID) throws IOException {

        OrderDetails od = new OrderDetails(OrderID);
        od.setVisible(true);

    }

    private void cancelOrder(String OrderID) throws IOException{
        int input = JOptionPane.showConfirmDialog(this,
                "Are you sure to request for a cancel ont his order?", "Order Cancellation ",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == JOptionPane.OK_OPTION) {
        try {
            Socket socket = new Socket("localhost", 5000);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String inputsocket = OrderID +  "," + "Request for cancel";
            out.println(inputsocket);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
          History hs = new History(LoggedCustomer);
          hs.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        lblOrderHistory = new javax.swing.JLabel();
        pnlOrders = new javax.swing.JScrollPane();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 204));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblOrderHistory.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblOrderHistory.setText("Order History");

        pnlOrders.setHorizontalScrollBar(null);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(lblOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home hm;
        try {
            hm = new Home(this.LoggedCustomer);
            hm.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblOrderHistory;
    private javax.swing.JScrollPane pnlOrders;
    // End of variables declaration//GEN-END:variables
}
