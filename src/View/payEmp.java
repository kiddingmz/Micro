/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ctrAtivo;
import static Controller.ctrAtivo.updateCapitalL;
import Controller.ctrEmprestimo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.business_object.manageEmprestimo;

/**
 *
 * @author nazar
 */
public class payEmp extends JDialog implements ActionListener{
    JLabel lblCoin, lblMet, lblInfo, lblValor, lblID;
    public JTextField entryID,entryVl;
    JButton pay, canc;
    JPanel all;
    JComboBox jcbMet;
    public payEmp(int id, float vl){
        setModal(true);
        setTitle("PAYMENT");
        setSize(350,320);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        //painel geral
        all=new JPanel();
        all.setLayout(null);
        getContentPane().add(all);
        //lbl coin mzn
        Font ftTit=new Font("Courier",Font.BOLD, 12);
        lblCoin=new JLabel("MZN");
        lblCoin.setOpaque(true);
        lblCoin.setBackground((new java.awt.Color(58,70,80)));
        lblCoin.setFont(ftTit);
        lblCoin.setHorizontalAlignment(SwingConstants.CENTER);
        lblCoin.setBounds(150,20,50,25);
        lblCoin.setForeground(Color.white);
        all.add(lblCoin);
        //Metodo de pagamento
        Font ft2=new Font("Courier",Font.BOLD, 14);
        lblMet=new JLabel("Método de Pagamento");
        lblMet.setFont(ft2);
        lblMet.setForeground(new java.awt.Color(58,70,80));
        lblMet.setBounds(30,60,200,30);
        
        jcbMet=new JComboBox();
        jcbMet.addItem("CASH MZN");
        jcbMet.addItem("VISA");
        jcbMet.addItem("CHEQUE");
        jcbMet.setForeground(new java.awt.Color(58,70,80));
        jcbMet.setBackground(Color.white);
        jcbMet.setBounds(30,90,290,30);
        //Info Line
        lblInfo=new JLabel("Informações do Pagamento");
        lblInfo.setFont(ft2);
        lblInfo.setForeground(new java.awt.Color(58,70,80));
        lblInfo.setBounds(30,130,200,30);
        
        lblID=new JLabel("ID do Empréstimo:");
        lblID.setForeground(new java.awt.Color(58,70,80));
        lblID.setBounds(50,170,120,25);
        entryID=new JTextField(Integer.toString(id));
        entryID.setEditable(false);
        entryID.setBounds(170,170,150,25);
        
        lblValor=new JLabel("Valor:");
        lblValor.setForeground(new java.awt.Color(58,70,80));
        lblValor.setBounds(50,205,200,25);
        entryVl=new JTextField(Float.toString(vl));
        entryVl.setBounds(170,205,150,25);
        JSeparator sep=new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(new java.awt.Color(58,70,80));
        sep.setBounds(30,240,290,2);
        //aprovar e cancelar
        pay=new JButton("PAGAR");
        pay.setBackground(new java.awt.Color(58,70,80));
        pay.setForeground(Color.white);
        pay.setBounds(75,250,120,25);
        pay.addActionListener(this);
        
        canc=new JButton("Cancelar");
        canc.setBackground(new java.awt.Color(58,70,80));
        canc.setForeground(Color.white);
        canc.setBounds(200,250,120,25);
        //pay.setBounds();
        
        all.add(canc);
        all.add(pay);
        all.add(sep);
        all.add(entryID);
        all.add(entryVl);
        all.add(lblID);
        all.add(lblValor);
        all.add(lblInfo);
        all.add(jcbMet);
        all.add(lblMet);
        
        setVisible(true);
    }
    public static void main(String[] args) {
       // new payEmp(3, (float) 20000);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==pay){
            ctrEmprestimo ce=new ctrEmprestimo();
            boolean res=ce.payment(Integer.parseInt(entryID.getText()), Float.parseFloat(entryVl.getText()));
            if(res==true){
                if(ctrAtivo.updateCapitalL(Float.parseFloat(entryVl.getText())))
                  JOptionPane.showMessageDialog(null, "Pagamento Realizado Com Sucesso!");
            }
            else
                JOptionPane.showMessageDialog(null, "Pagamento Falhou!");
            dispose();
        }
    }
    
}
