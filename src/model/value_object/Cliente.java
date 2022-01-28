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
public class Cliente extends Pessoa{

    
    private String estCv, morada,sexo;
    private float reMs;
    private int ctt;
    
    public Cliente(){
    }

    public void setEstCv(String estCv) {
        this.estCv = estCv;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setReMs(float reMs) {
        this.reMs = reMs;
    }

    public void setCtt(int ctt) {
        this.ctt = ctt;
    }
    
    public Cliente(int id, String nome, String sexo,int nasci, String estCvl, String bi, String morada, float rendMsl,int ctt){
        super(id, nasci, nome, bi);
        this.estCv=estCvl;
        this.morada=morada;
        this.reMs=rendMsl;
        this.ctt=ctt;
        this.sexo=sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
            
    public String getEstCv() {
        return estCv;
    }

    public String getMorada() {
        return morada;
    }

    public int getCtt() {
        return ctt;
    }
    
    
    public float getRendMensal(){return reMs;}
    
    public String toString(){
        return super.toString()+"Morada\t\tRendimento Mensal\n"+morada+"\t\t"+reMs;
    }
}

