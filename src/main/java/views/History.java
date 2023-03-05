/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import RMI_Structures.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        } catch (NotBoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public void setOrders(Order[] orders) throws FileNotFoundException, IOException {
        orderPanel.removeAll();
        int numOrders = orders.length;
        //int numRows = (int) Math.ceil((double) numOrders / 3);
        orderPanel.setLayout(new GridLayout(numOrders, 3, 5, 5));
        for (int i = 0; i < numOrders; i++) {
            JPanel panel = new JPanel();
            panel.setMaximumSize(new Dimension(200, 80));
            panel.setPreferredSize(new Dimension(200, 80));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            final String od = orders[i].getOrderID();
            // add a MouseListener to each product panel
             panel.addMouseListener(new MouseAdapter() {
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
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel dateLabel = new JLabel(orders[i].getDate() + " (id: " + orders[i].getOrderID()+")");
            dateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            //panel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel address = new JLabel("Address: " + orders[i].getAddress());
            JLabel status = new JLabel("Status: " + orders[i].getStatus());
            JLabel total = new JLabel("Total: $" + orders[i].getTotal());
            panel.add(dateLabel);
            panel.add(address);
            panel.add(status);
            panel.add(total);        
            dateLabel.setAlignmentY(Component.TOP_ALIGNMENT);
            address.setAlignmentY(Component.TOP_ALIGNMENT);
            status.setAlignmentY(Component.TOP_ALIGNMENT);
            total.setAlignmentY(Component.TOP_ALIGNMENT);
            orderPanel.add(panel);            
            
        }
        orderPanel.revalidate();
        orderPanel.repaint();
    }

     private void displayOrder(String OrderID) throws IOException {
        
        // create a new JFrame to display the product
        JFrame frame = new JFrame("Order Details");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        HashMap<String, String> OrderDetails = null;
        RMIinterface Obj;
         try {
             Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
             OrderDetails = Obj.getOrderDetails(OrderID);
         } catch (NotBoundException | MalformedURLException | RemoteException ex) {
             Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
         }
          
        // create UI components to display the product details
        JLabel Order_ID = new JLabel("Order ID: " + OrderDetails.get("Order_ID"));
        Order_ID.setFont(new Font("Tahoma", Font.BOLD, 16));
        JLabel dateLabel = new JLabel("Date: " + OrderDetails.get("date"));
        JLabel contentLabel = new JLabel("Content: " + OrderDetails.get("content"));
        //JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        
        // JTextArea descriptionArea = new JTextArea(product.getDescription());
        // descriptionArea.setLineWrap(true);
        // descriptionArea.setWrapStyleWord(true);
        

        // add components to the frame
       frame.add(Order_ID);
       frame.add(dateLabel);
       frame.add(contentLabel);
        //frame.add(categoryLabel);
        //frame.add(priceLabel);
        // frame.add(scrollPane);

        // display the frame
       frame.setVisible(true);
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
