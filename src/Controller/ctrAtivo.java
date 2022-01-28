/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Vector;
import model.data_acess_object.ativoDAO;
import model.value_object.Ativo;
import model.value_object.Emprestimo;

/**
 *
 * @author nazar
 */
public class ctrAtivo {
    ativoDAO adao;
    public ArrayList<Ativo> getList(){
        adao = new ativoDAO();
        ArrayList<Ativo> lst = adao.listar();
        return lst;
    }
    
    public static boolean updateCapital(double emp, double liq){
        
        ativoDAO atd = new ativoDAO();
        ctrAtivo c = new ctrAtivo();
        
        if(c.getList().get(0).getSaldo() < emp)
            return false;
   
        double sal = c.getList().get(0).getSaldo() - emp + liq;
        double lq = liq + c.getList().get(0).getLiq();
        double ep = emp + c.getList().get(0).getEmp();
        
        String sql = "Update ativo set saldo="
                +sal+",emp="+
                ep+""
                + ",liq="
                +lq+" where id=1";
        
        if(atd.create(sql))
            return true;
        
        return false;
    }
    
    public static boolean updateCapitalL(double liq){
        
        ativoDAO atd = new ativoDAO();
        ctrAtivo c = new ctrAtivo();
       
        double sal = c.getList().get(0).getSaldo() + liq;
        double lq = liq + c.getList().get(0).getLiq();
        double ep = c.getList().get(0).getEmp() - liq;
        
        String sql = "Update ativo set saldo="
                +sal+",emp="+
                ep+""
                + ",liq="
                +lq+" where id=1";
        
        if(atd.create(sql))
            return true;
        
        return false;
    }
}
