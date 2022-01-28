/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;

import Controller.ctrCliente;
import model.value_object.Cliente;

/**
 *
 * @author nazar
 */
public class exec {
    public static void main(String[] args) {
        clienteDAO dd= new clienteDAO();
        if(dd.verID(7)!=false)
            System.out.println("Encontrado");
        else
            System.out.println("NAO ENCONTRADO");
    }
}
