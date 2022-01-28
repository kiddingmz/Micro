/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business_object;

import Controller.ctrCliente;
import model.data_acess_object.clienteDAO;
import model.data_acess_object.emprestimoDAO;
import model.value_object.Cliente;
import model.value_object.Emprestimo;

/**
 *
 * @author nazar
 */
public class manageEmprestimo {
    
    emprestimoDAO eDAO; 
    Emprestimo emp;
    
    public float calcJuros(int nump){
        float value=0;
        if(nump>=1 & nump<24)
            value=(float) 0.35;
        else{
            if(nump>=24 & nump<48)
                value=(float) 0.45;
            if (nump>=48 & nump<=60)
                value=(float) 0.55;   
        }
        return value;
    }
    
    public boolean salvar(Emprestimo tt){
        emp=tt;
        String sql="insert into emprestimo values("+emp.getIdEmpr()+","+emp.getIdCliente()+","+emp.getCredito()+","+emp.getJr()+","+emp.getValorTot()+","+emp.getValorPrest()+","+emp.getNumPrest()
                +","+emp.getStts()+",'"+emp.getGarantia()+"',"+emp.getIdFuncio()+")";
        eDAO=new emprestimoDAO();
        boolean res=eDAO.create(sql);
        return res;
    }
    
    public boolean alterar(Emprestimo tt){
        emp=tt;
        String sql="Update emprestimo set Cred= "+emp.getCredito()+", jur= "+emp.getJr()+","
                + " vT= "+emp.getValorTot()+", vlPres= "+emp.getValorPrest()+", num= "+emp.getNumPrest()+", stts="+emp.getStts()+" , gar='"+emp.getGarantia()+"',"
                + " idFunc= "+emp.getIdFuncio()+" where id="+emp.getIdEmpr();
        eDAO=new emprestimoDAO();
        boolean res=eDAO.create(sql);
        return res;
    }
    public boolean pagar(int id, float vl){
        
        String sql="Update emprestimo set vT = vT-"+vl+" where idEmp="+id;
        eDAO=new emprestimoDAO();
        boolean res=eDAO.create(sql);
        return res;
    }
    public boolean encontrar(int id){
        eDAO=new emprestimoDAO();
        boolean scc=eDAO.verID(id);
        if(scc==true){
            emp=eDAO.validaID(id);
            return true;
        }
        else
            return false;
    }
    
    public Emprestimo getEmp(){
        return emp;
    }
    
}
