/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.business_object.manageCliente;
import model.data_acess_object.clienteDAO;
import model.value_object.Cliente;
import model.value_object.validar;

/**
 *
 * @author nazar
 */
public class ctrCliente {
    validar vd=new validar();
    Cliente cl;
    int id,nasci,ctt;
    String nome,estCvl, bi,morada,sexo;
    float rendMsl;
    Vector lst;
    
    public static int gerador(){
        clienteDAO cdao =new clienteDAO();
        int num= cdao.lastId()+1;
        return num;
    }
    
    public boolean cadastrar(int id,String nome, String sexo, int nasci, String estCvl, String bi, String morada, float rendMsl,int ctt){
        manageCliente mC=new manageCliente();
        if(vd.valTxt(nome, 5, 45) && vd.valInt(nasci, 1900, 2021) && vd.valTxt(estCvl, 4, 15)
                && vd.valBi(bi) && vd.valTxt(morada, 5, 45) && vd.valFloat(rendMsl, 1000, 1000000) && vd.valInt(ctt, 800000000, 899999999) ==true){
            cl=new Cliente(id, nome, sexo, nasci, estCvl, bi, morada, rendMsl, ctt);
            boolean res=mC.salvar(cl);
            return res;
        }
            return false;
    }
    
    public boolean modificar(int id,String nome, String sexo, int nasci, String estCvl, String bi, String morada, float rendMsl,int ctt){
        manageCliente mC=new manageCliente();
        if(vd.valTxt(nome, 5, 45) && vd.valInt(nasci, 1900, 2021) && vd.valTxt(estCvl, 4, 15)
                && vd.valBi(bi) && vd.valTxt(morada, 5, 45) && vd.valFloat(rendMsl, 1000, 1000000) && vd.valInt(ctt, 800000000, 899999999) ==true){
            cl=new Cliente(id, nome, sexo, nasci, estCvl, bi, morada, rendMsl, ctt);
            boolean res=mC.alterar(cl);
            return res;
        }
            return false;
    }
    public Vector listar(){
        clienteDAO cdao=new clienteDAO();
        int i=0;
        lst=new Vector();
        for(Cliente cl:cdao.readAll()){
            lst.add(new Object[]{cl.getId(),cl.getNome(),cl.getCtt()});
            i++;
        }
        return lst;
    }
    public boolean remover(int id){
         try{
                clienteDAO cdao=new clienteDAO();
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
    public boolean encontrar(int id){
        manageCliente mC=new manageCliente();
        if(mC.encontrar(id)==true){
            cl=mC.getCl();
            this.id=cl.getId();
            nome=cl.getNome();
            nasci=cl.getNasc();
            estCvl=cl.getEstCv();
            bi=cl.getBi();
            morada=cl.getMorada();
            rendMsl=cl.getRendMensal();
            ctt=cl.getCtt();
            sexo=cl.getSexo();
            return true;
        }
        else{
            return false;
        }
    }
    public int getCliTot(){
        clienteDAO edd= new clienteDAO();
        return edd.count();
    
    }

    public String getSexo() {
        return sexo;
    }

    public validar getVd() {
        return vd;
    }

    public Cliente getCl() {
        return cl;
    }

    public int getId() {
        return id;
    }

    public int getNasci() {
        return nasci;
    }

    public int getCtt() {
        return ctt;
    }

    public String getNome() {
        return nome;
    }

    public String getEstCvl() {
        return estCvl;
    }

    public String getBi() {
        return bi;
    }

    public String getMorada() {
        return morada;
    }

    public float getRendMsl() {
        return rendMsl;
    }

    
    
    
}
