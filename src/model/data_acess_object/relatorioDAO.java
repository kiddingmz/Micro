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
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.Relatorio;

/**
 *
 * @author nazar
 */
public class relatorioDAO {
    Relatorio rl;
    public List<Relatorio> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        List<Relatorio> funcs=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from relatorio");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                rl =new Relatorio();
                rl.setIdEmp(rs.getInt("idEmp"));
                rl.setIdCli(rs.getInt("idCli"));
                rl.setIdFunc(rs.getInt("idFuncio"));
                rl.setNome(rs.getString("Nome"));
                rl.setData(rs.getString("dt"));
                rl.setVl(rs.getFloat("prest"));
                funcs.add(rl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection( con, stnt, rs);
        }
        return funcs; 
    }
    
    public ArrayList<Relatorio> readAllT(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        ArrayList<Relatorio> funcs=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from relatorio");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                rl =new Relatorio();
                rl.setIdre(rs.getInt("id"));
                rl.setIdEmp(rs.getInt("idEmp"));
                rl.setIdCli(rs.getInt("idCli"));
                rl.setIdFunc(rs.getInt("idFuncio"));
                rl.setNome(rs.getString("Nome"));
                rl.setData(rs.getString("dt"));
                rl.setVl(rs.getFloat("prest"));
                funcs.add(rl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection( con, stnt, rs);
        }
        return funcs; 
    }
}
