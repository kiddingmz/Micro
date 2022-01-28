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
public class manageEmprestimo {
    /*. Para gerar o emprestimo deve se ter o valor do crédito desejado, o numero de prestações, id do cliente, as garantias do cliente, 
    ou seja os bens que o cliente coloca ao emprestimo  como garantia, e é no fim armazenado o valor total a pagar, isto é,
    o valor do crédito com os juros. Cada emprestimo possui uma id. */
    private int id, idFuncio, idCliente, numPrest;
    private float vlTot,credito, vlPrest;
    private boolean stts;
    private String garantia;
    private BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    private validacao vl;
    private Emprestimo emp;
    private Vector vt;
    private verClient vc;
    private verFuncio vf;
    
    public int gerarID(){
        int id=2000;
        int numEst = vt.size();
        Emprestimo temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Emprestimo)vt.elementAt(k); 
            id=temp.getIdEmpr();
        }
        id++;
        return id;
    }
    
    public manageEmprestimo(int res) throws IOException{
        
        boolean asw;
        vf= new verFuncio();
        vl=new validacao();
        vt=new Vector();
        leitura();
            switch (res){
                case 1:
                    do{
                        System.out.println("Digite o Id do Funcionario: ");
                        idFuncio=Integer.parseInt(x.readLine());
                        asw= vf.search(idFuncio);
                    }while(asw==false);
                    create();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    paymentPrest();
                    break;
                case 4:
                    impr();
                    System.out.println("-------------------");
                    break;
                case 5:
                    cancelarEmprestimo();
                    break;
                default:
                    System.out.println("ERROr 404: TRY AGAIN");
                    break;
            }
        
        
        
    }
    public void paymentPrest() throws IOException{
        int cod, num;float val;
        boolean search;
        System.out.println("Digite o ID do Emprestimo: ");
        cod=Integer.parseInt(x.readLine());
        search=vf.searchEmprestimo(cod);
        if(search!=false){
            System.out.println("Digite o valor a pagar: ");
            val=vl.valFloat(Float.parseFloat(x.readLine()), 100, 1000000);
            int numEst = vt.size(); 
            Emprestimo temp; 
            for (int k=0;k<numEst; k++) {    
            temp = (Emprestimo)vt.elementAt(k); 
            if(temp.getIdEmpr()==cod){
                temp.setSaldo(val);
                
                System.out.println("ID do Emprestimo\tID do Cliente\tValor Pago\tsaldo\n"+temp.getIdEmpr()+"\t\t\t"+temp.getIdCliente()
                                    +"\t\t"+val+"\t\t"+temp.getValorTot());
                System.out.println(">>>>>>Pagamento efectuado com sucesso!");
                gravarFichObj();
            }
        }
        }
    }
    
    public void create() throws IOException{
        float juro, sal;
        boolean verif, aprovar;
        int answ;
            id=gerarID();
            System.out.print("Digite o ID do Cliente: ");
            idCliente=Integer.parseInt(x.readLine());
            vc=new verClient();
            verif=vc.search(idCliente);
            if(verif==false)
                System.out.print("ERRO 404-id.cliente.invalido");
            else{
                sal=vc.getSal();
                System.out.print("Digite o valor de credito desejado(2,000.00MT---1,000,000.00MT): ");
                credito=vl.valInt(Integer.parseInt(x.readLine()), 2000, 1000000);
                System.out.print("Digite o numero de prestacoes(1-60): ");
                numPrest=vl.valInt(Integer.parseInt(x.readLine()), 1, 60);
                System.out.print("Mencione as garantias: ");
                garantia=x.readLine();
                juro=calcJuros(numPrest);
                vlTot=credito+credito*juro;
                vlPrest=vlTot/numPrest;
                aprovar=verificar( sal,vlPrest);
                if(aprovar==true){
                    stts=true;
                    emp=new Emprestimo(id, idCliente,idFuncio, credito, vlTot,vlPrest,numPrest, stts, garantia,juro);
                    vt.addElement(emp);
                    System.out.println("Nome do Cliente\t\tValor do Emprestimo\tPercentagem de juros\tValor total\tValor mensal\tStatus\tID Funcionario Que aprovou\n"
                                        +vc.getNome()+"\t"+credito+"\t\t"+juro+"\t\t\t"+vlTot+"\t"+vlPrest+"\t"+stts+"\t\t"+idFuncio);
                } else{
                    System.out.println("ERR) 404: O rendimento mensal nao e suficiente para pagar a prestacao mensal");
                }
                vt.trimToSize();
                gravarFichObj();
            }
        
    }
    
    public boolean verificar(float sal, float msl){
        boolean res=true;
        if(msl>sal)
            res=false;
        return res;
    }
    
    public float calcJuros(int nump){
        float value=0;
        if(nump>=1 & nump<24)
            value=(float) 0.35;
        else{
            if(nump>=24 & nump<48)
                value=(float) 0.45;
            if (nump>=48 & nump<=60)
                value=(float) 0.55;   
        }
        return value;
    }
    
    public void search() throws IOException{
        int cod, verif=0;
        System.out.print("Digite o ID do Emprestimo: ");
        cod=Integer.parseInt(x.readLine());
        int numEst = vt.size(); 
        Emprestimo temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Emprestimo)vt.elementAt(k); 
            if(temp.getIdEmpr()==cod){
                System.out.println(temp.toString());
                verif=1;
            }
        }
        if(verif==0)
            System.out.println("Emprestimo Nao Encontrado!");
    }
    
    public void gravarFichObj() { 
        
        try {
            FileOutputStream ff = new FileOutputStream("Emprestimo");
            ObjectOutputStream ss = new ObjectOutputStream(ff);
            ss.writeObject(vt);
            ss.close();
        } catch (IOException xx) {
            System.out.println(xx.getMessage());
        }
    }
    
    public void leitura(){ //faz a leitura do ficheiro de obj e armazena em um array de obj
        try {
            FileInputStream ii = new FileInputStream ("Emprestimo");
            ObjectInputStream tt = new ObjectInputStream (ii);
            vt = (Vector)tt.readObject();
            tt.close();
        } catch (ClassNotFoundException cc){
            System.out.println("Verifique a existencia da classe!!!"); 
        } catch (IOException pp){
            System.out.println("Problemas no leitura do ficheiro!");
        }

    }
    
    public void impr(){
        leitura() ;
        int numEst = vt.size(); 
        Emprestimo temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Emprestimo)vt.elementAt(k); 
            System.out.println(temp.toString()+"\n--------------------");  
        }
    }
    
    public void cancelarEmprestimo() throws IOException{
        int id;
        System.out.print("Digite o ID do Emprestimo: ");
        id=Integer.parseInt(x.readLine());
        int verif=0,numEst = vt.size(); 
        Emprestimo temp; 
        for (int k=0;k<numEst; k++) { 
            temp = (Emprestimo)vt.elementAt(k); 
            if(temp.getIdEmpr()==id){
                temp.setStts(false);
                temp.setSaldo(temp.getValorTot());
                System.out.println("Emprestimo Cancelado!");
                gravarFichObj();
                verif=1;
            }
        }
        if(verif==0)
            System.out.println("Emprestimo Nao Encontrado!");
    }
    
}
