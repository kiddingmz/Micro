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
public class MenuPrincipal {
    private manageCliente mc ;
    private manageEmprestimo me;
    private manageFuncionario mf;
    /**
     * @param args the command line arguments
     */
    public MenuPrincipal () throws IOException {
        // TODO code application logic here
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        int ans;
        do{
            System.out.println("<<<<<KHUVU MICROOCREDITOS>>>>>>");
            System.out.println("            1-Adcionar Clientes");
            System.out.println("            2-Procurar um Clientes");
            System.out.println("            3-Ver Todos Clientes");
            System.out.println("            4-Realizar Emprestimo");
            System.out.println("            5-Procurar Emprestimo");
            System.out.println("            6-Efectuar Pagamento de Prestacao");
            System.out.println("            7-Cancelar Emprestimo");
            System.out.println("            8-Ver Todos Emprestimos");
            System.out.println("            9-Adcionar Funcionarios");
            System.out.println("            10-Procurar um Funcionario");
            System.out.println("            11-Ver Todos Funcionarios");
            System.out.println("            12-sair");
            System.out.print("OPS: ");
            ans=Integer.parseInt(x.readLine());
            switch(ans){
                case 1:
                    mc =new manageCliente(1);
                    break;
                case 2:
                    mc =new manageCliente(2);
                    break;
                case 3:
                    mc =new manageCliente(3);
                    break;
                case 4:
                    me= new manageEmprestimo(1);
                    break;
                case 5:
                    me= new manageEmprestimo(2);
                    break;
                case 6:
                    me= new manageEmprestimo(3);
                    break;
                case 7:
                    me= new manageEmprestimo(5);
                    break;
                case 8:
                    me= new manageEmprestimo(4);
                    break;
                case 9:
                    mf=new manageFuncionario(1);
                    break;
                case 10:
                    mf=new manageFuncionario(2);
                    break;
                case 11:
                    mf=new manageFuncionario(3);
                    break;
                case 12:
                    
                    break;
                default:
                    System.out.println("Opcao Errada!");
                    break;
            }
        }while(ans!=12);
    }
    
}
