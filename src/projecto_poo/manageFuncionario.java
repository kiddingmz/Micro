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
public class manageFuncionario {
    private BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    private Vector vt;
    private Funcionario fc;
    private int id, nasci,ctt;
    private float salario;
    private String nome, bi;
    private validacao vl;
    
    public manageFuncionario(int answ) throws IOException{
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
                case 4:
                    System.out.println("<<<<<<<<<<");
                    break;
                default:
                    System.out.println("OPS ERRADA!");
                    break;
            }
      
    }
    
    public int gerarID(){
        int id=1000;
        int numEst = vt.size();
        Funcionario temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Funcionario)vt.elementAt(k); 
            id=temp.getId();
        }
        id++;
        return id;
    }
    
    public void Create() throws IOException{
        int answ;
        do{
            id=gerarID();
            System.out.print("Digite o Nome do Funcionario: ");
            nome=vl.valTxt(x.readLine(), 5, 45);
            System.out.print("Digite o ano do nascimento do funcionario: ");
            nasci=vl.valInt(Integer.parseInt(x.readLine()), 1900, 2021);
            System.out.print("Digite o B.I do Funcionario: ");
            bi=vl.valTxt(x.readLine(),5 , 6);
            System.out.print("Digite o salario do Funcionario: ");
            salario=vl.valInt(Integer.parseInt(x.readLine()), 3500, 120000);
            fc=new Funcionario(id, nasci, nome, bi, salario,ctt);
            vt.addElement(fc);
            System.out.println("--------------------------");
            System.out.print("Pretende Adcionar mais funcionarios?[1-SIM//0-NAO]: ");
            answ=Integer.parseInt(x.readLine());
        }while(answ==1);
        vt.trimToSize();
        gravarFichObj();
    }
    
    public void search() throws IOException{
        int cod, verif=0;
        System.out.print("Digite o ID do Funcionario: ");
        cod=vl.valInt(Integer.parseInt(x.readLine()), 1000, 2000);
        int numEst = vt.size(); 
        Funcionario temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Funcionario)vt.elementAt(k); 
            if(temp.getID()==cod){
                System.out.println(temp.toString());
                verif=1;
            }
        }
        if(verif==0)
            System.out.println("Funcionario Nao Encontrado!");
    }
    
    public void gravarFichObj() { 
        
        try {
            FileOutputStream ff = new FileOutputStream("Funcionario");
            ObjectOutputStream ss = new ObjectOutputStream(ff);
            ss.writeObject(vt);
            ss.close();
        } catch (IOException xx) {
            System.out.println(xx.getMessage());
        }
    }
    
    public void leitura(){ //faz a leitura do ficheiro de obj e armazena em um array de obj
        try {
            FileInputStream ii = new FileInputStream ("Funcionario");
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
        Funcionario temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Funcionario)vt.elementAt(k); 
            System.out.println(temp.toString()+"\n--------------------");  
        }
    }
    
}

