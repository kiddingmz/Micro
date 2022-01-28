/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto_poo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

/**
 *
 * @author nazar
 */
public class verClient {
    private Cliente cl;
    private Vector vt;
    private String nome;
    private float sal;
    private Pessoa ppl;
    
    public verClient(){
        vt=new Vector();
        leitura();
    }
    public boolean search(int cod) throws IOException{
        int verif=0;
        boolean res=false;
        int numEst = vt.size(); 
        for (int k=0;k<numEst; k++) { 
            ppl = (Pessoa)vt.elementAt(k); 
            if(ppl.getId()==cod & ppl instanceof Cliente ){
                res=true;
                cl=(Cliente)ppl;
                nome=cl.getNome();
                sal=cl.getRendMensal();
                verif=1;
            }
        }
        if(verif==0)
            System.out.println("Cliente Nao Encontrado!");
        return res;
    }
    
   
    public void leitura(){ //faz a leitura do ficheiro de obj e armazena em um array de obj
        try {
            FileInputStream ii = new FileInputStream ("Cliente");
            ObjectInputStream tt = new ObjectInputStream (ii);
            vt = (Vector)tt.readObject();
            tt.close();
        } catch (ClassNotFoundException cc){
            System.out.println("Verifique a existencia da classe Revista!!!"); 
        } catch (IOException pp){
            System.out.println("Problemas no leitura do ficheiro!");
        }

    }
    public float getSal(){return sal;}
    public String getNome(){return nome;}
}

