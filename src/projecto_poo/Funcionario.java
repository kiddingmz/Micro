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
public class Funcionario extends Pessoa {
    private float salario;
    
    public Funcionario(int id, int nasci, String nome,String bi, float salario, int ctt){
        super(id, nasci, nome, bi);
        this.salario=salario;
    }
    
    public int getID(){return id;}
    public String toString(){
        return super.toString()+"Salario:\t"+salario;
    }
    
}
