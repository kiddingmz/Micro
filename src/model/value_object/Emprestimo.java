/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.value_object;

import projecto_poo.*;
import java.io.Serializable;

/**
 *
 * @author nazar
 */
public class Emprestimo implements Serializable{
 
    private int id, idCliente,idFuncio,numPrest;
    private float vlTot,credito, vlPrest, vlPago, jr;
    private boolean stts;
    private String garantia;
    
    public Emprestimo(int id, int idC, int idF, float crdt,float vT, float vlPrest, int num, boolean stts, String grt, float jr ){
        this.id=id;
        this.idCliente=idC;
        this.idFuncio=idF;
        this.numPrest=num;
        this.vlTot=vT;
        this.credito=crdt;
        this.vlPrest=vlPrest;
        this.stts=stts;
        this.garantia=grt;
        this.jr=jr;
        this.vlPago=(crdt+crdt*jr)-vT;
    }

    public void setJr(float jr) {
        this.jr = jr;
    }
    
    public Emprestimo(){}

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getVlPago() {
        return vlPago;
    }
    
    
    
    public void setNumPrest(int numPrest) {
        this.numPrest = numPrest;
    }

    public void setStts(boolean stts) {
        this.stts = stts;
    }

    public String getGarantia() {
        return garantia;
    }

    public int getIdFuncio() {
        return idFuncio;
    }
    
    
    
    public int getIdEmpr(){return id;}
    public int getIdCliente(){return idCliente;}
    public int getPrest(){return numPrest;}
    public float getCredito(){return credito;}
    public float getValorTot(){return vlTot;}
    public boolean getStts(){return stts;}
    public float getValorPrest(){return vlPrest;}

    public int getNumPrest() {
        return numPrest;
    }

    public float getJr() {
        return jr;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void setIdFuncio(int idFuncio) {
        this.idFuncio = idFuncio;
    }

    public void setVlTot(float vlTot) {
        this.vlTot = vlTot;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }

    public void setVlPrest(float vlPrest) {
        this.vlPrest = vlPrest;
    }

    public void setVlPago(float vlPago) {
        this.vlPago = vlPago;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }
    
    
    
  
    public String toString(){
        return "Id Emprestimo\tId do Cliente\t\tValor do Emprestimo\tValor em falta\tValor Pago\tStatus\tID Funcionario Que aprovou\n"
                                        +id+"\t\t"+idCliente+"\t\t\t"+credito+"\t\t"+vlTot+"\t"+vlPago+"\t\t"+stts+"\t\t"+idFuncio;
    }
    
}

