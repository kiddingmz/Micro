/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto_poo;

import java.io.Serializable;

/**
 *
 * @author nazar
 */
public class Emprestimo implements Serializable{
 
    private int id, idCliente,idFuncio,numPrest;
    private float vlTot,credito, vlPrest, vlPago;
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
        this.garantia=garantia;
        this.vlPago=(crdt+crdt*jr)-vT;
    }
    
    void setIdCliente(int id){this.id=id;}
    void setSaldo(float vl){this.vlTot-=vl;this.vlPago+=vl;}
    void setStts(boolean stts){this.stts=stts;}
    void setNumPrest(int num){this.numPrest-=num;}
    
    public int getIdEmpr(){return id;}
    public int getIdCliente(){return idCliente;}
    public int getPrest(){return numPrest;}
    public float getCredito(){return credito;}
    public float getValorTot(){return vlTot;}
    public boolean getStts(){return stts;}
    public float getValorPrest(){return vlPrest;}
  
    public String toString(){
        return "Id Emprestimo\tId do Cliente\t\tValor do Emprestimo\tValor em falta\tValor Pago\tStatus\tID Funcionario Que aprovou\n"
                                        +id+"\t\t"+idCliente+"\t\t\t"+credito+"\t\t"+vlTot+"\t"+vlPago+"\t\t"+stts+"\t\t"+idFuncio;
    }
    
}

