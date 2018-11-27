
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manaraph
 */
import javax.swing.JPasswordField;
public class Login3 extends JFrame{
    
    String username = "";
    String password = "";
    
    ResultSet rs = null;
    Statement st = null;
    DBconnect conn = null;
    
    public Login3() {
        initComponents();
        
        try {
            initComponents();
            
            conn = new DBconnect();
            st = conn.getConnection().createStatement();
            
            
            String createDB = "CREATE DATABASE IF NOT EXISTS examprojectdb";
            String useDB = "USE examprojectdb";
            String createTable = "CREATE TABLE IF NOT EXISTS attendance(S_N INT(5) AUTO_INCREMENT,"
                    + "Tag_Number VARCHAR(50), PRIMARY KEY (S_N));";
            this.setResizable(true);
            
            st.executeUpdate(createDB);//creats either database or table if it does not exist.
            st.executeUpdate(useDB);
            st.executeUpdate(createTable);
        } catch (SQLException err) {

            JOptionPane.showMessageDialog(null, "There was a Problem Trying to connect to the Database \n" + err
            + "\nThe Server might not have been started...");
        }
    }
                         
    private void initComponents() {

        securityPanel = new javax.swing.JPanel();
        instructionLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("FUT Minna Examination Management System - Attendance");

        instructionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        instructionLabel.setText("Login:  Administrators Only");

        usernameLabel.setText("Username: ");

        passwordLabel.setText("Password: ");
        
        securityPanel.setBackground(Color.orange);

        loginButton.setText("Take Attendance");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeAttendanceButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register Students");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout securityPanelLayout = new javax.swing.GroupLayout(securityPanel);
        securityPanel.setLayout(securityPanelLayout);
        securityPanelLayout.setHorizontalGroup(
            securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(instructionLabel)
                    .addGroup(securityPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(securityPanelLayout.createSequentialGroup()
                                .addComponent(usernameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(securityPanelLayout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(securityPanelLayout.createSequentialGroup()
                                        .addComponent(loginButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(registerButton)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(passwordField))))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        securityPanelLayout.setVerticalGroup(
            securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(instructionLabel)
                .addGap(18, 18, 18)
                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(registerButton))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(securityPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(securityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        username = usernameField.getText();
        password = passwordField.getText();
        final String query = "select count(*) from administrators where Username = '"+username+"' and Password = '"+password+"'";
        
        JOptionPane.showMessageDialog(null, "Login: " +  username + " " + password);
        
        try {
            rs=st.executeQuery(query);
            while(rs.next()){
                String count1 =rs.getString("count(*)");
                int count = Integer.parseInt(count1);
                
                if( count != 0){
                    SerialTest main = new SerialTest();
                    main.initialize();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Access Denied... Wrong Username or Password",
                        "ATTENTION!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error while trying to Execute Your command \n" + e , 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    private void takeAttendanceButtonActionPerformed(java.awt.event.ActionEvent evt) {  
         username = usernameField.getText();
        password = passwordField.getText();
        final String query = "select count(*) from administrators where Username = '"+username+"' and Password = '"+password+"'";
        
        JOptionPane.showMessageDialog(null, "Login: " +  username + " " + password);
        
        try {
            rs=st.executeQuery(query);
            while(rs.next()){
                String count1 =rs.getString("count(*)");
                int count = Integer.parseInt(count1);
                
                if( count != 0){
                    SerialTest main = new SerialTest();
                    main.initialize();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Access Denied... Wrong Username or Password",
                        "ATTENTION!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error while trying to Execute Your command \n" + e , 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(Login3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPanel securityPanel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration                   
}