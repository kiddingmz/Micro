/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author nazar
 */
class TratadorEvento implements ActionListener{
    Cliente cl ;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn =(JButton) e.getSource();
        String pv= btn.getName();
        if(pv=="addCli")
            cl =new Cliente();
            
    }
    
}
