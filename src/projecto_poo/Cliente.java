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
public class Cliente extends Pessoa{

    
    private String estCv, morada;
    private float reMs;
    
    public Cliente(int id, String nome, int nasci, String estCvl, String bi, String morada, float rendMsl,int ctt){
        super(id, nasci, nome, bi);
        this.estCv=estCvl;
        this.morada=morada;
        this.reMs=rendMsl;
    }
    
    
    public float getRendMensal(){return reMs;}
    
    public String toString(){
        return super.toString()+"Morada\t\tRendimento Mensal\n"+morada+"\t\t"+reMs;
    }
}

