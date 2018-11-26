
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manaraph
 */
public class DBconnect {
    
        Connection connection = null;

    public Connection getConnection(){
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String myurl = "jdbc:mysql://localhost:3306/examprojectdb";      
            connection = DriverManager.getConnection(myurl, "root", "");       //get a connection
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error while trying to Establish a Connection \n" + e 
                + "\nThe Server might not have been started...", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return connection;
    }

    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
