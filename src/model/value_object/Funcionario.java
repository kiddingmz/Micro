/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.value_object;

import projecto_poo.*;

/**
 *
 * @author nazar
 */
public class Funcionario extends Pessoa {
    private float salario;
    private String sx;
    private int ctt;

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public void setCtt(int ctt) {
        this.ctt = ctt;
    }
    
    public Funcionario(){}
    
    public Funcionario(int id, int nasci, String nome,String bi, String sx,float salario, int ctt){
        super(id, nasci, nome, bi);
        this.salario=salario;
        this.sx=sx;
        this.ctt=ctt;
    }

    public float getSalario() {
        return salario;
    }

    public String getSx() {
        return sx;
    }

    public int getCtt() {
        return ctt;
    }
    
    public int getID(){return id;}
    public String toString(){
        return super.toString()+"Salario:\t"+salario;
    }
    
}
