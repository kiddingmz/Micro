/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business_object;

import model.data_acess_object.clienteDAO;
import model.value_object.Cliente;

/**
 *
 * @author nazar
 */

public class manageCliente {
    
    Cliente cl;
    clienteDAO cDAO;
    
    public boolean salvar(Cliente tt){
        cl=tt;
        String sql="insert into Cliente values("+cl.getId()+",'"+cl.getNome()+"','"+cl.getSexo()+"',"+cl.getNasc()+",'"+cl.getEstCv()+"','"+cl.getBi()+"','"+cl.getMorada()+"',"+cl.getRendMensal()+","+cl.getCtt()+")";
        cDAO=new clienteDAO();
        boolean res=cDAO.create(sql);
        return res;
    }
    
    public boolean alterar(Cliente tt){
        cl=tt;
        String sql="Update cliente set nome='"+cl.getNome()+"',sexo='"+cl.getSexo()+"',nasc="+cl.getNasc()+",estCivil='"+cl.getEstCv()+"',bi='"+cl.getBi()+
                "',morada='"+cl.getMorada()+"',rend="+cl.getRendMensal()+",ctt="+cl.getCtt()+" where id="+cl.getId();
        cDAO=new clienteDAO();
        boolean res=cDAO.create(sql);
        return res;
    }
    
    public boolean encontrar(int id){
        cDAO=new clienteDAO();
        boolean scc=cDAO.verID(id);
        if(scc==true){
            cl=cDAO.validaID(id);
            return true;
        }
        else
            return false;
    }

    public Cliente getCl() {
        return cl;
    }
    
    
}
