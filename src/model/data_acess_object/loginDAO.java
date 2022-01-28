/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;

import Connection.ConnectionFactory;
import View.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.LoginS;


/**
 *
 * @author nazar
 */
public class loginDAO {
    
    public ArrayList<LoginS> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        ArrayList<LoginS> log=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from login");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                LoginS cl =new LoginS();
                cl.setId(rs.getInt("idFuncio"));
                cl.setPass(rs.getString("pass"));
                log.add(cl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }
        return log; 
    }
    public boolean create(String sql){
    
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        try {
            
            stnt=con.prepareStatement(sql);
            stnt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("ERRO "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection( con,stnt);
            
        }
        
    }
    
    public boolean valida(int id, String pass){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        boolean res = false;
        
        LoginS cl  = new LoginS();
        ArrayList<LoginS> alls = readAll();
        
        for(int j = 0; j < alls.size(); j++){
            if((id == alls.get(j).getId())&&(pass.equals(alls.get(j).getPass()))){
               res = true;  
            }
        }
        
        /*
        try {
            
            stnt=con.prepareStatement("Select count(idFuncio) as num from login where idFuncio = ? and pass = ?");
            stnt.setInt(1, id);
            stnt.setString(2, pass);
            rs=stnt.executeQuery();
            rs.first();
            
            res=rs.getInt("num");
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }*/
        return res;
    }
    
}
