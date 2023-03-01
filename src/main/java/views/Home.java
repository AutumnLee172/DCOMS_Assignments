/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import RMI_Structures.*;
import java.awt.BorderLayout;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Autumn
 */
public class Home extends javax.swing.JFrame {

    private JPanel productPanel;
    Customer LoggedCustomer;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
    }

    public Home(Customer cm) throws IOException {
        LoggedCustomer = cm;
        initComponents();
        lblHello.setText("Hello, " + LoggedCustomer.getName());

        //for testing use
        productPanel = new JPanel();
        productPanel.setBackground(Color.WHITE);
        pnlProduct.setViewportView(productPanel);
        try {
            RMIinterface Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
            ArrayList<Product> productsArrayList = Obj.getProducts();
            Product[] products = new Product[productsArrayList.size()];
            productsArrayList.toArray(products);
            setProducts(products);
        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //for testing use
    public void setProducts(Product[] products) throws FileNotFoundException, IOException {
        productPanel.removeAll();
        int numProducts = products.length;
        int numRows = (int) Math.ceil((double) numProducts / 3);
        productPanel.setLayout(new GridLayout(numRows, 3, 10, 10));
        for (int i = 0; i < numProducts; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(200, 200));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            // add a MouseListener to each product panel
            panel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // display the selected product
                    displayProduct(products[i]);
                }
            });

            JLabel nameLabel = new JLabel(products[i].getName());
            nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

            JLabel imageLabel = new JLabel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            imageLabel.setSize(170, 170);
            if (products[i].getImage() != null) {
                InputStream is = new ByteArrayInputStream(products[i].getImage());
                BufferedImage someImage = ImageIO.read(is);
                ImageIcon icon = new ImageIcon(someImage);
                Image img = icon.getImage();
                Image img2 = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(img2);
                imageLabel.setIcon(image);
            }
            JLabel categoryLabel = new JLabel(products[i].getCategory());
            categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel priceLabel = new JLabel("$" + products[i].getPrice());
            priceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(nameLabel);
            panel.add(imageLabel);
            panel.add(categoryLabel);
            panel.add(priceLabel);
            productPanel.add(panel);
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

// method to display the selected product
    private void displayProduct(Product product) {
        // create a new JFrame to display the product
        JFrame frame = new JFrame("Product Details");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        // create UI components to display the product details
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        JLabel categoryLabel = new JLabel("Category: " + product.getCategory());
        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));

        // add components to the frame
        frame.add(nameLabel);
        frame.add(categoryLabel);
        frame.add(priceLabel);
        frame.add(scrollPane);

        // display the frame
        frame.setVisible(true);
    }

    public ImageIcon resizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        ImageIcon image = new ImageIcon(img);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHello = new javax.swing.JLabel();
        btnCart = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlProduct = new javax.swing.JScrollPane();
        selectedProductPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 600));

        lblHello.setText("jLabel1");

        btnCart.setText("Cart");
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });

        btnProfile.setText("Profile");
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        pnlProduct.setHorizontalScrollBar(null);
        pnlProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout selectedProductPanelLayout = new javax.swing.GroupLayout(selectedProductPanel);
        selectedProductPanel.setLayout(selectedProductPanelLayout);
        selectedProductPanelLayout.setHorizontalGroup(
            selectedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        selectedProductPanelLayout.setVerticalGroup(
            selectedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectedProductPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 748, Short.MAX_VALUE)
                        .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btnCart)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCart)
                .addGap(152, 152, 152))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        //CartMenu cartMenu = new CartMenu(LoggedCustomer);
        //        cartMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCartActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        Profile pf = new Profile(LoggedCustomer);
        pf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        LoginRegister lr = new LoginRegister();
        this.dispose();
        lr.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void pnlProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlProductMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        JFrame frame = new JFrame("Fixed Size UI Example");
//
//        // Set the size of the frame
//        frame.setSize(1980, 1000);
//
//        // Set the resizable property to false
//        frame.setResizable(false);
//
//        // Show the frame
//        frame.setVisible(true);
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProfile;
    private javax.swing.JLabel lblHello;
    private javax.swing.JScrollPane pnlProduct;
    private javax.swing.JPanel selectedProductPanel;
    // End of variables declaration//GEN-END:variables
}
