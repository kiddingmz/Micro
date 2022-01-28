/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import model.business_object.manageEmprestimo;
import model.data_acess_object.emprestimoDAO;
import model.value_object.Emprestimo;
import model.value_object.validar;

/**
 *
 * @author nazar
 */
public class ctrEmprestimo {
    
    validar vd;
    public model.value_object.Emprestimo empClass;
    
    private int id, idFuncio, idCliente, numPrest;
    private float vlTot,credito, vlPrest,juro,sal;
    private boolean stts;
    private String garantia;
    manageEmprestimo mE;
    
    public ctrEmprestimo(){
        vd=new validar();
        mE=new manageEmprestimo();
    }
    public int gerador(){
        emprestimoDAO cdao=new emprestimoDAO();
        int num=cdao.lastId()+1;
        return num;
    }
    public boolean newEmprestimo(int id, int idC, int idF, float crdt,float vT, float vlPrest, int num, boolean stts, String grt, float jr){
        empClass=new Emprestimo(id, idC, idF, crdt, vT, vlPrest, num, stts, grt, jr);
        return true;
    }
    
    public boolean validaDados(int idC,float crdt,int num,String grt){
        if(vd.valFloat(crdt, 100, 1000000) && vd.valInt(num, 1, 60) && vd.valTxt(grt, 4, 45)){
            this.idCliente=idC;
            this.credito=crdt;
            this.numPrest=num;
            this.garantia=grt;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaEmp(){
        
        ctrCliente ctrCli=new ctrCliente();
        ctrCli.encontrar(idCliente);
        sal=ctrCli.getRendMsl();
        
        juro=mE.calcJuros(numPrest);
        vlTot=credito+credito*juro;
        vlPrest=vlTot/numPrest;
        boolean res=true;
        if(vlPrest>sal)
            res=false;
        return res;
    }
    
    public boolean find(int id){
        manageEmprestimo me= new manageEmprestimo();
        boolean scc= me.encontrar(id);
        if(scc==true){
            empClass=me.getEmp();
            return true;
        }
        else
            return false;
    }
    
    public int getEmpTot(int i){
        emprestimoDAO edd= new emprestimoDAO();
        if(i == 1)
            return edd.count(true);
        if(i == 0)
            return edd.count(false);
        return -1;
    }
    
    public boolean payment(int id, float vl){
        manageEmprestimo me= new manageEmprestimo();
        boolean res=me.pagar(id, vl);
        return res;
    }
    
    public Emprestimo getEmp(){
        return empClass;
    }
    
    public boolean salvarEmp(){
        boolean scc=mE.salvar(empClass);
        return scc;
    }

    public float getJuro() {
        return juro;
    }

    public float getVlTot() {
        return vlTot;
    }

    public float getVlPrest() {
        return vlPrest;
    }
    
    
}
