/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import RMI_Structures.Customer;
import RMI_Structures.RMIinterface;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Autumn
 */
public class AdminLogin extends javax.swing.JFrame {

    /**
     * Creates new form AdminLogin
     */
    public AdminLogin() {
           initComponents();
         this.setLocationRelativeTo(null);
         this.setSize(1200,600);
         pnlMain.setVisible(true);
         pnldashboard.setVisible(false); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnldashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        manageprod = new javax.swing.JButton();
        managecustorder = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        lblAdminPanel = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblLoginResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnldashboard.setMaximumSize(new java.awt.Dimension(1200, 600));
        pnldashboard.setPreferredSize(new java.awt.Dimension(1200, 600));

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 48)); // NOI18N
        jLabel1.setText("Admin Dashboard");

        manageprod.setFont(new java.awt.Font("Snap ITC", 3, 18)); // NOI18N
        manageprod.setText("Manage Product");
        manageprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageprodActionPerformed(evt);
            }
        });

        managecustorder.setFont(new java.awt.Font("Snap ITC", 3, 18)); // NOI18N
        managecustorder.setText("Manage Customer Order");
        managecustorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managecustorderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnldashboardLayout = new javax.swing.GroupLayout(pnldashboard);
        pnldashboard.setLayout(pnldashboardLayout);
        pnldashboardLayout.setHorizontalGroup(
            pnldashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldashboardLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jLabel1))
            .addGroup(pnldashboardLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(manageprod)
                .addGap(217, 217, 217)
                .addComponent(managecustorder))
        );
        pnldashboardLayout.setVerticalGroup(
            pnldashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldashboardLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addGap(142, 142, 142)
                .addGroup(pnldashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageprod)
                    .addComponent(managecustorder)))
        );

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setMaximumSize(new java.awt.Dimension(1200, 600));
        pnlMain.setPreferredSize(new java.awt.Dimension(1200, 600));

        lblAdminPanel.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        lblAdminPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdminPanel.setText("Admin Panel");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email");

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setText("Password");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblAdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassword)
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnLogin)
                                        .addComponent(lblLoginResult, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(682, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblAdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(18, 18, 18)
                .addComponent(lblLoginResult, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(482, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(pnldashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 6585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(pnldashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addGap(143, 143, 143)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = txtEmail.getText();
        String passwords = txtPassword.getText();
        try {
            RMIinterface Obj = (RMIinterface)Naming.lookup("rmi://localhost:1040/KGF");
           if(Obj.admin_login(email, passwords)){
                lblLoginResult.setText("you are logged in.");
                //AdminHome ah = new AdminHome();
                //ah.setVisible(true);
                //this.dispose();
                pnlMain.setVisible(false);
                pnldashboard.setVisible(true); 
            }else{
                lblLoginResult.setText("Incorrect email or passwords.");
            }
        } catch (NotBoundException | IOException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void manageprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageprodActionPerformed
        // TODO add your handling code here:
         AdminHome ah = new AdminHome();
         ah.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_manageprodActionPerformed

    private void managecustorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managecustorderActionPerformed
        CustomerOrder custorder = new CustomerOrder();
        custorder.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_managecustorderActionPerformed

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
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAdminPanel;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLoginResult;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JButton managecustorder;
    private javax.swing.JButton manageprod;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnldashboard;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
