/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;


import Connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.Cliente;
import model.value_object.Emprestimo;

/**
 *
 * @author nazar
 */
public class clienteDAO {
    
    Cliente scl;
    
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
        
    }//Salvar Cliente na base de dados
    
    public ArrayList<Cliente> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        ArrayList<Cliente> clientes=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from cliente");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                Cliente cl =new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setNome(rs.getString("nome"));
                cl.setSexo(rs.getString("sexo"));
                cl.setNasc(rs.getInt("nasc"));
                cl.setBi(rs.getString("bi"));
                cl.setMorada(rs.getString("morada"));
                cl.setReMs(rs.getFloat("rend"));
                cl.setCtt(rs.getInt("ctt"));
                cl.setEstCv(rs.getString("estCivil"));
                clientes.add(cl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }
        return clientes; 
    }//Ler todos Clientes
    
    public Cliente validaID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        
        scl = new Cliente();
        ArrayList<Cliente> cliente = readAll();
        
        for(int j = 0; j < cliente.size(); j++){
            if(id == cliente.get(j).getId()){
                scl =new Cliente();
                scl.setId(cliente.get(j).getId());
                scl.setNome(cliente.get(j).getNome());
                scl.setSexo(cliente.get(j).getSexo());
                scl.setNasc(cliente.get(j).getNasc());
                scl.setBi(cliente.get(j).getBi());
                scl.setMorada(cliente.get(j).getMorada());
                scl.setReMs(cliente.get(j).getRendMensal());
                scl.setCtt(cliente.get(j).getCtt());
                scl.setEstCv(cliente.get(j).getEstCv());
            }
        }
        return scl;
        /*
        try {
            
            stnt=con.prepareStatement("Select*from cliente where id = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            
                scl =new Cliente();
                scl.setId(rs.getInt("id"));
                scl.setNome(rs.getString("nome"));
                scl.setSexo(rs.getString("sexo"));
                scl.setNasc(rs.getInt("nasc"));
                scl.setBi(rs.getString("bi"));
                scl.setMorada(rs.getString("morada"));
                scl.setReMs(rs.getFloat("rend"));
                scl.setCtt(rs.getInt("ctt"));
                scl.setEstCv(rs.getString("estCivil"));
                return scl;
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }
         */
    }
    
    public int lastId(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        int num = 0;
        ArrayList<Cliente> cliente = readAll();
        
        for(int j = 0; j < cliente.size(); j++){
              if(j == (cliente.size() - 1))
                  num = cliente.get(j).getId() + 1;
        }
        return num;
        /*
        try {
            
            stnt=con.prepareStatement("Select max(id) as num from cliente");
            rs=stnt.executeQuery();
            rs.first();
            
            return rs.getInt("num");
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally{
            ConnectionFactory.closeConnection( con, stnt, rs);
        }*/
    }
    
    
    public int count(){
        int num = 0;
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        
        ArrayList<Cliente> cliente = readAll();
        
        for(int j = 0; j < cliente.size(); j++)
            num++;
        /*
        try {
             
            stnt=con.prepareStatement("Select count(nome) as num from cliente");
            rs=stnt.executeQuery();
            rs.first();
            num=rs.getInt("num");
            return num;
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally{
            ConnectionFactory.closeConnection(con, stnt, rs);
        }*/
        return num;
         
    }
    
    public boolean verID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        boolean status = false; 
        
        ArrayList<Cliente> cliente = readAll();
        
        for(int j = 0; j < cliente.size(); j++){
            if(id == cliente.get(j).getId())
                status = true;
        }
        return status;
        /*try {
             
            stnt=con.prepareStatement("Select count(nome) from cliente where id = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            int num=rs.getInt("count(nome)");
            if(num==1)
                return true;
            else
                return false;
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection( con, stnt, rs);
        }*/
         
    }
    public boolean delID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
             
            stnt=con.prepareStatement("Delete from cliente where id = ?");
            stnt.setInt(1, id);
            stnt.executeUpdate();
            return true;
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection( con, stnt, rs);
        }
         
    }

    
}
















