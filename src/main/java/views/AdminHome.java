/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;
import RMI_Structures.*;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class AdminHome extends javax.swing.JFrame {
    Product ProdID;
    /**
     * Creates new form AdminHome
     */
    private ImageIcon format = null;
    String fname = null;
    int s= 0;
    byte[] pimage = null;
    
    DefaultTableModel model;
    ArrayList<Product> productlist = new ArrayList<Product>();
    RMIinterface Obj;
        
    public AdminHome() {
        initComponents();
        this.setSize(1200,600);
       
        pnlManage.setVisible(true);
        pnlAddProduct.setVisible(false);
        
         try {

            Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
            productlist = Obj.getProducts();
            
            String prodName, Quantity, Price, Description, Category;
            int ID;
            byte[] image;
            boolean select = false;
            
            this.model = (DefaultTableModel) TableProductList.getModel();
            model.setRowCount(0);
        
            for (int i = 0; i < productlist.size(); i++) {
                ID = productlist.get(i).getID();
                prodName = productlist.get(i).getName();
                Category = productlist.get(i).getCategory();
                Description = productlist.get(i).getDescript();
                Quantity = productlist.get(i).getQuantity();
                Price = productlist.get(i).getPrice();
                image = productlist.get(i).getImage();

                Object rowData[] = {ID, prodName, Description, Category, Quantity, Price, image, select};

                model = (DefaultTableModel) TableProductList.getModel();
                model.addRow(rowData);
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlAddProduct = new javax.swing.JPanel();
        lblquantity = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblprice = new javax.swing.JLabel();
        lblcategory = new javax.swing.JLabel();
        lblprodname = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        txtprodname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        selectcategory = new javax.swing.JComboBox<>();
        btadd = new javax.swing.JButton();
        btcancel = new javax.swing.JButton();
        lblresult = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        lblimg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtproddescript = new javax.swing.JTextArea();
        pnlManage = new javax.swing.JPanel();
        addprod = new javax.swing.JButton();
        editprod = new javax.swing.JButton();
        deleteprod = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableProductList = new javax.swing.JTable();
        btmanageorder = new javax.swing.JButton();
        btlogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlAddProduct.setPreferredSize(new java.awt.Dimension(1200, 600));

        lblquantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblquantity.setText("Product Quantity: ");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel2.setText("Add New Product");

        lblprice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblprice.setText("Product Price:");

        lblcategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblcategory.setText("Product Category: ");

        lblprodname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblprodname.setText("Product Name: ");

        txtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpriceActionPerformed(evt);
            }
        });

        selectcategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baby Products", "Accessories", "Home Applications", "Mobile Phones" }));
        selectcategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectcategoryActionPerformed(evt);
            }
        });

        btadd.setText("Add");
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        btcancel.setText("Cancel");
        btcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancelActionPerformed(evt);
            }
        });

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Product Description: ");

        txtproddescript.setColumns(20);
        txtproddescript.setRows(5);
        jScrollPane1.setViewportView(txtproddescript);

        javax.swing.GroupLayout pnlAddProductLayout = new javax.swing.GroupLayout(pnlAddProduct);
        pnlAddProduct.setLayout(pnlAddProductLayout);
        pnlAddProductLayout.setHorizontalGroup(
            pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jLabel2))
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblprodname))
                    .addComponent(jLabel3)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblcategory))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblquantity)))
                .addGap(158, 158, 158)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtprodname, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(lblprice)
                .addGap(166, 166, 166)
                .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(btnUpload))
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(btadd)
                .addGap(58, 58, 58)
                .addComponent(btcancel))
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(lblresult, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlAddProductLayout.setVerticalGroup(
            pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblprodname)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3)
                        .addGap(48, 48, 48)
                        .addComponent(lblcategory)
                        .addGap(51, 51, 51)
                        .addComponent(lblquantity))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addComponent(txtprodname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(selectcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblprice)
                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnUpload)))
                .addGap(37, 37, 37)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btadd)
                    .addComponent(btcancel))
                .addGap(341, 341, 341)
                .addComponent(lblresult, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlManage.setMinimumSize(new java.awt.Dimension(4634, 456));
        pnlManage.setPreferredSize(new java.awt.Dimension(1200, 600));

        addprod.setText("Add Product");
        addprod.setToolTipText("");
        addprod.setActionCommand("Manage Product");
        addprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addprodActionPerformed(evt);
            }
        });

        editprod.setText("Edit Product");
        editprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editprodActionPerformed(evt);
            }
        });

        deleteprod.setText("Delete Product");
        deleteprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteprodActionPerformed(evt);
            }
        });

        TableProductList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Product Description", "Product Category", "Product Quantity", "Product Price", "Image", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableProductList);
        if (TableProductList.getColumnModel().getColumnCount() > 0) {
            TableProductList.getColumnModel().getColumn(0).setResizable(false);
            TableProductList.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableProductList.getColumnModel().getColumn(1).setResizable(false);
            TableProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
            TableProductList.getColumnModel().getColumn(2).setResizable(false);
            TableProductList.getColumnModel().getColumn(2).setPreferredWidth(50);
            TableProductList.getColumnModel().getColumn(3).setResizable(false);
            TableProductList.getColumnModel().getColumn(3).setPreferredWidth(20);
            TableProductList.getColumnModel().getColumn(4).setResizable(false);
            TableProductList.getColumnModel().getColumn(4).setPreferredWidth(20);
            TableProductList.getColumnModel().getColumn(5).setResizable(false);
            TableProductList.getColumnModel().getColumn(5).setPreferredWidth(20);
            TableProductList.getColumnModel().getColumn(6).setResizable(false);
            TableProductList.getColumnModel().getColumn(6).setPreferredWidth(10);
            TableProductList.getColumnModel().getColumn(7).setResizable(false);
            TableProductList.getColumnModel().getColumn(7).setPreferredWidth(5);
        }

        btmanageorder.setText("Manage Customer Order");
        btmanageorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmanageorderActionPerformed(evt);
            }
        });

        btlogout.setText("Logout");
        btlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlManageLayout = new javax.swing.GroupLayout(pnlManage);
        pnlManage.setLayout(pnlManageLayout);
        pnlManageLayout.setHorizontalGroup(
            pnlManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(pnlManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlManageLayout.createSequentialGroup()
                        .addComponent(addprod, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(editprod, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(deleteprod)
                        .addGap(361, 361, 361)
                        .addComponent(btmanageorder)
                        .addGap(30, 30, 30)
                        .addComponent(btlogout))))
        );
        pnlManageLayout.setVerticalGroup(
            pnlManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pnlManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addprod)
                    .addComponent(editprod)
                    .addComponent(deleteprod)
                    .addComponent(btmanageorder)
                    .addComponent(btlogout)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6609, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(pnlAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 4222, Short.MAX_VALUE)
                    .addContainerGap(2365, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 943, Short.MAX_VALUE)
                    .addComponent(pnlManage, javax.swing.GroupLayout.PREFERRED_SIZE, 4722, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 944, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3150, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(pnlAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 1565, Short.MAX_VALUE)
                    .addContainerGap(1529, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteprodActionPerformed
        int input = JOptionPane.showConfirmDialog(this,
            "Are you sure about removing the selected product?", "Remove confirmation ",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (input == JOptionPane.OK_OPTION) {

            ArrayList<Integer> deletingItems = new ArrayList<>();
            ArrayList<Integer> rowsToRemove = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean checked = (Boolean) model.getValueAt(i, 7);

                if (checked) {
                    deletingItems.add((Integer) model.getValueAt(i, 0));//get prodid
                    rowsToRemove.add(i);
                }
            }

            // Remove rows outside of the loop
            for (int i = rowsToRemove.size() - 1; i >= 0; i--) {
                model.removeRow(rowsToRemove.get(i));
            }

            //Once we have the list to delete cart item, run delete cart function
            try {
                Obj.deleteProduct(deletingItems);
            } catch (RemoteException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteprodActionPerformed

    private void editprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editprodActionPerformed
      
        try {
            int column = 0;
            int row = TableProductList.getSelectedRow();
            String value = TableProductList.getModel().getValueAt(row, column).toString();
            int prodid = Integer.parseInt(value);
            
            EditProduct editprod = new EditProduct(Obj.getdisplayProduct(prodid));
            editprod.setVisible(true);
            this.dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_editprodActionPerformed

    private void addprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addprodActionPerformed
        // TODO add your handling code here:
       
        pnlAddProduct.setVisible(true);
        pnlManage.setVisible(false);

    }//GEN-LAST:event_addprodActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        JFileChooser fchoser = new JFileChooser();
        fchoser.showOpenDialog(null);
        File f=fchoser.getSelectedFile();
        fname = f.getAbsolutePath();
        ImageIcon micon = new ImageIcon(fname);
        try{
            File image = new File(fname);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readnum; (readnum=fis.read(buf)) != -1;){
                baos.write(buf,0,readnum);
            }
            pimage=baos.toByteArray();
            lblimg.setIcon(resizeImage(fname, buf));
        }catch(Exception e){

        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelActionPerformed

       
        pnlAddProduct.setVisible(false);
        pnlManage.setVisible(true);

    }//GEN-LAST:event_btcancelActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        try {
            String result = "";
            RMIinterface Obj;

            Obj = (RMIinterface)Naming.lookup("rmi://localhost:1040/KGF");

            String prodname = txtprodname.getText();
            String proddescript = txtproddescript.getText();
            String category = selectcategory.getSelectedItem().toString();
            String quantity = txtquantity.getText();
            String price = txtprice.getText();
            //result = System.out.println("a: " + prodname);
            result = Obj.Add_New_Product(prodname, proddescript, category, quantity, price, pimage);
            lblresult.setText(result);

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        AdminHome adhome = new AdminHome();
        adhome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btaddActionPerformed

    private void selectcategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectcategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectcategoryActionPerformed

    private void txtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceActionPerformed

    private void btmanageorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmanageorderActionPerformed
        CustomerOrder custorder = new CustomerOrder();
        custorder.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btmanageorderActionPerformed

    private void btlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlogoutActionPerformed
        AdminLogin adlogin = new AdminLogin();
        adlogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btlogoutActionPerformed
       
    public ImageIcon resizeImage(String imagePath, byte[] pic){
        ImageIcon myImage = null;
        if(imagePath != null){
            myImage = new ImageIcon(imagePath);
        }else{
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lblimg.getHeight(), lblimg.getWidth(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    
    
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
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableProductList;
    private javax.swing.JButton addprod;
    private javax.swing.JButton btadd;
    private javax.swing.JButton btcancel;
    private javax.swing.JButton btlogout;
    private javax.swing.JButton btmanageorder;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton deleteprod;
    private javax.swing.JButton editprod;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblcategory;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblprice;
    private javax.swing.JLabel lblprodname;
    private javax.swing.JLabel lblquantity;
    private javax.swing.JLabel lblresult;
    private javax.swing.JPanel pnlAddProduct;
    private javax.swing.JPanel pnlManage;
    private javax.swing.JComboBox<String> selectcategory;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextArea txtproddescript;
    private javax.swing.JTextField txtprodname;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
