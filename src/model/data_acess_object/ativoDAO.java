/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.Ativo;
import model.value_object.Funcionario;

/**
 *
 * @author nazar
 */
public class ativoDAO {
    
    public ArrayList<Ativo> listar(){
        ArrayList<Ativo> lst= new ArrayList<>();
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        
        try {
            stnt=con.prepareStatement("Select * from ativo");
            rs=stnt.executeQuery();
            while(rs.next()){
                Ativo at = new Ativo();
                at.setSaldo(rs.getFloat("saldo"));
                at.setEmp(rs.getFloat("emp"));
                at.setLiq(rs.getFloat("liq"));
                lst.add(at);
            }
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }
        
        return lst;
    }
    
    Funcionario fc;
    
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
            ConnectionFactory.closeConnection(con,stnt);
            
        }
        
    }
}
