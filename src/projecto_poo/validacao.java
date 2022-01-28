/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto_poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author nazar
 */
public class validacao {
    private int inteiro;
    private float decimal;
    private String txt;
    private BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    
    public validacao(){
        inteiro=0;
        decimal=0;
        txt=null;
    }
    
    public int valInt(int num, int ii, int ss) throws IOException{
        inteiro=num;
        while(inteiro<ii || inteiro>ss){
            System.out.print("Invalido!Digite Novamente: ");
            inteiro=Integer.parseInt(x.readLine());
        }
        return inteiro;
    }
    
    public float valFloat(float num, float ii, float ss) throws IOException{
        decimal=num;
        while(decimal<ii || decimal>ss){
            System.out.print("Invalido!Digite Novamente: ");
            decimal=Float.parseFloat(x.readLine());
        }
        return decimal;
    }
    
    public String valTxt(String tt, int ii, int ss) throws IOException{
        this.txt=tt;
        while(txt.length()<ii || txt.length()>ss){
            System.out.print("Invalido!Digite Novamente: ");
            txt=(x.readLine());
        }
        return txt;
    }
}

