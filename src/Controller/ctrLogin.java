/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.data_acess_object.loginDAO;

/**
 *
 * @author nazar
 */
public class ctrLogin {
    loginDAO lg;
    public ctrLogin(){
        lg = new loginDAO();
    }
    
    public boolean valida(int id, String pass){
        
        boolean scc= lg.valida(id, pass);
        return scc;
    }
    public boolean update(int id, String pass){
        boolean scc= lg.create("update login set pass="+pass+" where idFuncio="+id);
        return scc;
    }
}
