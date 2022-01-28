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
public class Pessoa implements Serializable{
    protected int id, nasc;
    protected String nome, bi;
    
    public Pessoa(){}

  
    public Pessoa(int id, int nasc, String nome, String Bi){
        this.id=id;
        this.nasc=nasc;
        this.nome=nome;
        this.bi=Bi;
    }
    
  public void setId(int id) {
        this.id = id;
    }

    public void setNasc(int nasc) {
        this.nasc = nasc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }
    
    public int getId() {
        return id;
    }

    public String getBi() {
        return bi;
    }

    public int getNasc() {
        return nasc;
    }

    public String getNome() {
        return nome;
    }
    
    public String toString(){
        return "ID\tNome\t\t\tBI\t\tAno Nasc\n"+id+"\t"+nome+"\t"+bi+"\t\t"+nasc+"\n";
    }
    
}
