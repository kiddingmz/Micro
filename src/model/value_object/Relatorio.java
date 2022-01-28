/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.value_object;

import java.io.Serializable;

/**
 *
 * @author nazar
 */
public class Relatorio implements Serializable {
    private int idEmp, idCli, idFunc, idre;

    public int getIdre() {
        return idre;
    }

    public void setIdre(int idre) {
        this.idre = idre;
    }
    private String nome, data;
    private float vl;

    public Relatorio() {
    }

    
    /*public Relatorio(int idEmp, int idCli, int idFunc, String nome, String data, float vl) {
        this.idEmp = idEmp;
        this.idCli = idCli;
        this.idFunc = idFunc;
        this.nome = nome;
        this.data = data;
        this.vl = vl;
    }*/

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getVl() {
        return vl;
    }

    public void setVl(float vl) {
        this.vl = vl;
    }
    
    
}
