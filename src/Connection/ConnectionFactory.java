/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nazar
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/company";
    private static final String USER ="root";
    private static final String PASS="";
    
    public static Connection getConnection(){
    
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException |SQLException ex) {
            throw new RuntimeException("ERRO NA CONEXAO",ex);
        } 
        
    }
    
    public static void closeConnection(Connection con){
        if(con!=null)
            try {
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void closeConnection(Connection con,PreparedStatement stnt){
        closeConnection(con);
        try {
            if(stnt!=null)
               stnt.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con,PreparedStatement stnt, ResultSet rs){
        closeConnection(con,stnt);
        
        try {
            if(rs!=null)
                rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]){
        getConnection();
    }
}
