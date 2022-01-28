/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto_poo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author nazar
 */
public class manageCliente {
    private int id, nasc, ctt;
    private String nome, estCv, morada, bi;
    private float reMs;
    private BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    private Cliente cl;
    private validacao vl;
    private Vector vt;
    
    public manageCliente(int answ) throws IOException{
        vl = new validacao();
        vt=new Vector();
        leitura();

            switch (answ){
                case 1:
                    Create();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    impr();
                    break;
                default:
                    System.out.println("OPS ERRADA!");
                    break;
            }
       
    
    }
    
    public int gerarID(){
        int id=100;
        int numEst = vt.size();
        Cliente temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Cliente)vt.elementAt(k); 
            id=temp.getId();
        }
        id++;
        return id;
    }
    
    public void Create() throws IOException{
        int answ;
        do{
            
            cl=new Cliente(id, nome, nasc, estCv, bi, morada, reMs,ctt);
            vt.addElement(cl);
            System.out.println("--------------------------");
            System.out.print("Pretende Adcionar mais clientes?[1-SIM//0-NAO]: ");
            answ=Integer.parseInt(x.readLine());
        }while(answ==1);
        vt.trimToSize();
        gravarFichObj();
    }
    
    public void search() throws IOException{
        int cod, verif=0;
        System.out.print("Digite o ID do Cliente: ");
        cod=vl.valInt(Integer.parseInt(x.readLine()), 100, 999);
        int numEst = vt.size(); 
        Cliente temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Cliente)vt.elementAt(k); 
            if(temp.getId()==cod){
                System.out.println(temp.toString());
                verif=1;
            }
        }
        if(verif==0)
            System.out.println("Cliente Nao Encontrado!");
    }
    
    public void gravarFichObj() { 
        
        try {
            FileOutputStream ff = new FileOutputStream("Cliente");
            ObjectOutputStream ss = new ObjectOutputStream(ff);
            ss.writeObject(vt);
            ss.close();
        } catch (IOException xx) {
            System.out.println(xx.getMessage());
        }
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
    
    public void impr(){
        leitura() ;
        int numEst = vt.size(); 
        Cliente temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Cliente)vt.elementAt(k); 
            System.out.println(temp.toString()+"\n--------------------");  
        }
    }
 
}

