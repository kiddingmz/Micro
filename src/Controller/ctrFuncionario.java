/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Vector;
import javax.swing.JOptionPane;
import model.business_object.manageCliente;
import model.business_object.manageFuncionario;
import model.data_acess_object.clienteDAO;
import model.data_acess_object.funcionarioDAO;
import model.value_object.Funcionario;
import model.value_object.validar;


/**
 *
 * @author nazar
 */
public class ctrFuncionario {
    validar vd;
    Funcionario fnc;
    
    int id, nasc,ctt;
    String nome, sexo,bi;
    float salario;
    
    public int gerador(){
        funcionarioDAO cdao=new funcionarioDAO();
        int num=cdao.lastId()+1;
        return num;
    }
    
    public boolean cadastrar(int id,int nasci, String nome,String bi, String sx,float salario, int ctt){
       
        vd=new validar();
        if(vd.valInt(nasci, 1900, 2021) && vd.valTxt(nome, 5, 45) && vd.valBi(bi) && vd.valTxt(sx, 6, 10) && vd.valFloat(salario, 1000, 100000)
                && vd.valInt(ctt, 800000000, 899999999) == true){
            
            fnc=new Funcionario(id, nasci, nome, bi, sx,salario, ctt);
            manageFuncionario mF=new manageFuncionario();
            mF.salvar(fnc);
            return true;
        }else
            return false;
            
    }
    public boolean modificar(int id,int nasci, String nome,String bi, String sx,float salario, int ctt){
       
        vd=new validar();
        if(vd.valInt(nasci, 1900, 2021) && vd.valTxt(nome, 5, 45) && vd.valBi(bi) && vd.valTxt(sx, 6, 10) && vd.valFloat(salario, 1000, 100000)
                && vd.valInt(ctt, 800000000, 899999999) == true){
            
            fnc=new Funcionario(id, nasci, nome, bi, sx,salario, ctt);
            manageFuncionario mF=new manageFuncionario();
            boolean res=mF.alterar(fnc);
            return res;
        }else
            return false;
            
    }
    public Vector listar(){
        funcionarioDAO cdao=new funcionarioDAO();
        int i=0;
        Vector lst=new Vector();
        for(Funcionario fc:cdao.readAll()){
            lst.add(new Object[]{fc.getId(),fc.getNome()});
            i++;
        }
        return lst;
    }
    public boolean encontrar(int id){
        manageFuncionario mF=new manageFuncionario();
        Funcionario fc;
        if(mF.encontrar(id)==true){
            fc=mF.getCl();
            this.id=fc.getId();
            nome=fc.getNome();
            nasc=fc.getNasc();
            bi=fc.getBi();
            salario=fc.getSalario();
            ctt=fc.getCtt();
            sexo=fc.getSx();
            return true;
        }
        else{
            return false;
        }
    }
    public boolean remover(int id){ 
        try{
            funcionarioDAO cdao=new funcionarioDAO();
            boolean scc=cdao.delID(id);
            if(scc){
                return true;
            } else{
                    return false;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro: "+e);
                return false;
            }
      
    }

    public validar getVd() {
        return vd;
    }

    public Funcionario getFnc() {
        return fnc;
    }

    public int getId() {
        return id;
    }

    public int getNasc() {
        return nasc;
    }

    public int getCtt() {
        return ctt;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getBi() {
        return bi;
    }

    public float getSalario() {
        return salario;
    }
    
}
