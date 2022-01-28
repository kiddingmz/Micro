/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.value_object;

import projecto_poo.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author nazar
 */
public class validar {
    private int inteiro;
    private float decimal;
    private String txt;
    private BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    
    public validar(){
        inteiro=0;
        decimal=0;
        txt=null;
    }
    
    public boolean valInt(int num, int ii, int ss){
        inteiro=num;
        if(inteiro<ii || inteiro>ss){
            return false;
        }else
            return true;
    }
    
    public boolean valFloat(float num, int ii, int ss){
        decimal=num;
        if(decimal<ii || decimal>ss){
            return false;
        }else
            return true;
    }
    
    public boolean valBi(String num) {
        txt=num;
        if(txt.length()<13 || txt.length()>13){
            return false;
        }else
            return true;
    }
    
    
    public boolean valTxt(String tt, int ii, int ss){
        this.txt=tt;
        if(txt.length()<ii || txt.length()>ss){
            return false;
        }
        return true;
    }
}

