/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Vector;
import model.data_acess_object.relatorioDAO;
import model.value_object.Relatorio;

/**
 *
 * @author nazar
 */
public class ctrRelatorio {
    Vector lst;
    
    public Vector listar(){
        relatorioDAO cdao=new relatorioDAO();
        int i=0;
        lst=new Vector();
        for(Relatorio cl:cdao.readAll()){
            lst.add(new Object[]{cl.getIdEmp(),cl.getNome(),cl.getVl(),cl.getIdFunc(),cl.getData()});
            i++;
        }
        return lst;
    }
    
    public ArrayList<Relatorio> listarT(){
        relatorioDAO cdao=new relatorioDAO();
        
        return cdao.readAllT();
    }
}
