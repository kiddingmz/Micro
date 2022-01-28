/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business_object;

import model.data_acess_object.funcionarioDAO;
import model.value_object.Funcionario;

/**
 *
 * @author nazar
 */
public class manageFuncionario {
    Funcionario cl;
    funcionarioDAO cDAO;
    public boolean salvar(Funcionario tt){
        cl=tt;
        String sql="insert into funcionario values("+cl.getId()+",'"+cl.getNome()+"','"+cl.getSx()+"',"+cl.getNasc()+",'"+cl.getBi()+"',"+cl.getSalario()+","+cl.getCtt()+")";
        cDAO=new funcionarioDAO();
        boolean res=cDAO.create(sql);
        return res;
    }
    
    public boolean alterar(Funcionario tt){
        cl=tt;
        String sql="Update funcionario set nome='"+cl.getNome()+"',sexo='"+cl.getSx()+"',nasc="+cl.getNasc()+",bi='"+cl.getBi()+
                "',salario="+cl.getSalario()+",ctt="+cl.getCtt()+" where id="+cl.getId();
        cDAO=new funcionarioDAO();
        boolean res=cDAO.create(sql);
        return res;
    }
    
    public boolean encontrar(int id){
        cDAO=new funcionarioDAO();
        boolean scc=cDAO.verID(id);
        if(scc==true){
            cl=cDAO.validaID(id);
            return true;
        }
        else
            return false;
    }

    public Funcionario getCl() {
        return cl;
    }
    
}
