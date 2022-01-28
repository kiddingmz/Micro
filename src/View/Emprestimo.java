/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.ctrAtivo;
import Controller.ctrCliente;
import Controller.ctrEmprestimo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author nazar
 */
public class Emprestimo extends JDialog implements ActionListener{
    JPanel ctn,addEmp;
    JLabel lblTit, lblID,perfil;
    JTextField inID;
     JButton btnSearch, btnVer, btnValidar,btnCancelar, historico;
    //painel ADD;
    JLabel txtID,txtCred,txtNum,txtVpr,txtVt,txtGar,txtJur, txtRem,txtFuncio;
    JTextField Id,inCred,inNum,inVt,inVpr,inGar,inJr,inRem,inFunc;
    
    model.value_object.Emprestimo cl;
    ctrCliente ctrCli=new ctrCliente();
    ctrEmprestimo ctrEmp =new ctrEmprestimo();
    ctrAtivo ativ = new ctrAtivo();
    private static int id;
    
    public Emprestimo(int id){
        this.id=id;
        setSize(800,480);
        setTitle("Empréstimo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);//Quando Aberta, nenhuma outra podera ser aberta
        setLocationRelativeTo(null);
        
        ctn=new JPanel();
        ctn.setLayout(null);
        ctn.setBackground(new java.awt.Color(237, 237, 237));
        add(ctn);
        
        Font ftTit=new Font("Courier",Font.BOLD, 16);
        //Borda do Titulo
        JPanel Borda = new JPanel();
        Borda.setLayout(null);
        Borda.setBounds(0,0,800,65);
        Borda.setBackground(new java.awt.Color(58,70,80));
        //Titulo
        lblTit=new JLabel("Novo Empréstimo");
        lblTit.setForeground(Color.WHITE);
        lblTit.setFont(ftTit);
        lblTit.setHorizontalAlignment(SwingConstants.LEFT);
        lblTit.setBounds(50,15,150,35);
        Borda.add(lblTit);
        //Pesquisa
        lblID=new JLabel("Digite o ID do Cliente");
        lblID.setBounds(50,90,170,25);
        inID=new JTextField();
        inID.setBounds(180,90,100,25);
        btnSearch=new JButton("Procurar");
        btnSearch.setBounds(280,90,100,25);
        btnSearch.setBackground(new java.awt.Color(0, 223, 186));
        btnSearch.addActionListener(this);
        
        
        JSeparator sep=new JSeparator(JSeparator.HORIZONTAL);
        sep.setBounds(50,140,700,2);
        sep.setForeground(Color.black);
        
        //painel ADD
        addEmp=new JPanel();
        addEmp.setBounds(50,165,700,265);
        addEmp.setBackground(Color.white);
        addEmp.setBorder(BorderFactory.createLineBorder(Color.black));
        addEmp.setLayout(null);
        
        painelADD();
        
        ctn.add(addEmp);
        ctn.add(sep);
        ctn.add(lblID);
        ctn.add(inID);
        ctn.add(btnSearch);
        ctn.add(Borda);
        setVisible(true);
    }
    public void painelADD(){
        addEmp.removeAll();
        addEmp.revalidate();
        addEmp.repaint();
        addEmp.setVisible(false);
        //perfil icon
        perfil=new JLabel();
        perfil.setBorder(BorderFactory.createLineBorder(Color.black));
        perfil.setBounds(30,30,160,190);
        //
        txtID=new JLabel("ID Empréstimo:");
        txtID.setBounds(200,30,100,20);
        Id=new JTextField(Integer.toString(ctrEmp.gerador()));
        Id.setEditable(false);
        Id.setBounds(320,30,50,20);
        
        txtCred=new JLabel("Crédito:");
        txtCred.setBounds(200,60,70,20);
        inCred=new JTextField();
        inCred.setBounds(320,60,100,20);
        
        txtNum=new JLabel("Nr de Prestações:");
        txtNum.setBounds(480,60,120,20);
        inNum=new JTextField();
        inNum.setBounds(600,60,50,20);
        
        txtGar=new JLabel("Garantias:");
        txtGar.setBounds(200,90,70,20);
        inGar=new JTextField();
        inGar.setBounds(320,90,100,20);
        
        //botao para verificar aprovação
        btnVer=new JButton("Verificar");
        btnVer.setBounds(480,90,170,20);
        btnVer.setBackground(new java.awt.Color(0, 223, 186));
        btnVer.addActionListener(this);
        //
        txtVt=new JLabel("Valor Ajuízado:");
        txtVt.setBounds(200,130,120,20);
        inVt=new JTextField();
        inVt.setBounds(320,130,100,20);
        inVt.setEditable(false);
        
        txtJur=new JLabel("Juros:");
        txtJur.setBounds(480,130,50,20);
        inJr=new JTextField();
        inJr.setBounds(600,130,50,20);
        inJr.setEditable(false);
        
        txtVpr=new JLabel("Valor Mensal:");
        txtVpr.setBounds(200,160,120,20);
        inVpr=new JTextField();
        inVpr.setBounds(320,160,100,20);
        inVpr.setEditable(false);
        
        txtFuncio=new JLabel("ID Funcionário:");
        txtFuncio.setBounds(200,190,120,20);
        inFunc=new JTextField(Integer.toString(id));
        inFunc.setBounds(320,190,50,20);
        inFunc.setEditable(false);
        //Botao Cancelar e Aprovar
        btnValidar=new JButton("Aprovar");
        btnValidar.setBounds(290,225,170,20);
        btnValidar.setBackground(new java.awt.Color(0, 223, 186));
        btnValidar.addActionListener(this);
        
        btnCancelar=new JButton("Cancelar");
        btnCancelar.setBounds(480,225,170,20);
        btnCancelar.setBackground(new java.awt.Color(0, 223, 186));
        btnCancelar.addActionListener(this);
        //
        JSeparator sepH=new JSeparator(JSeparator.HORIZONTAL);
        sepH.setBounds(200,120,450,2);
        sepH.setForeground(Color.black);
       
        addEmp.add(btnValidar);
        addEmp.add(btnCancelar);
        addEmp.add(txtFuncio);
        addEmp.add(inFunc);
        addEmp.add(inVt);
        addEmp.add(inVpr);
        addEmp.add(inJr);
        addEmp.add(txtVt);
        addEmp.add(txtJur);
        addEmp.add(txtVpr);
        addEmp.add(sepH);
        addEmp.add(btnVer);
        addEmp.add(perfil);
        addEmp.add(txtID);
        addEmp.add(txtCred);
        addEmp.add(txtNum);
        addEmp.add(txtGar);
        addEmp.add(Id);
        addEmp.add(inCred);
        addEmp.add(inNum);
        addEmp.add(inGar);
    }
    
     public static void main(String[] args) {
        new Emprestimo(id);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btnSearch){
            ctrCli=new ctrCliente();
            boolean scc=ctrCli.encontrar(Integer.parseInt(inID.getText()));
            if(scc==true){
                addEmp.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Cliente Não Encontrado!");
            }
        }
        if(ae.getSource()==btnVer){
            
            try{
                boolean scc= ctrEmp.validaDados(Integer.parseInt(inID.getText()),Float.parseFloat(inCred.getText()), Integer.parseInt(inNum.getText()), inGar.getText());
                if(scc==true){
                    scc=ctrEmp.validaEmp();
                    if(scc==true){
                        inFunc.setEditable(true);
                        inVt.setText(Float.toString(ctrEmp.getVlTot()));
                        inVpr.setText(Float.toString(ctrEmp.getVlPrest()));
                        inJr.setText(Float.toString(ctrEmp.getJuro()));
                        ctrEmp.newEmprestimo(Integer.parseInt(Id.getText()), Integer.parseInt(inID.getText()), Integer.parseInt(inFunc.getText()), Float.parseFloat(inCred.getText()), Float.parseFloat(inVt.getText())
                                , Float.parseFloat(inVpr.getText()), Integer.parseInt(inNum.getText()), true, inGar.getText(), Float.parseFloat(inJr.getText()));
                      
                    }else{
                        JOptionPane.showMessageDialog(null, "O Cliente não possui rendimento mensal suficiente!");
                    }
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Dados Invalidos!");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }
        }
        if(ae.getSource()==btnValidar){
            
            boolean scc = false;
            
                if(ctrAtivo.updateCapital(Float.parseFloat(inVpr.getText()), 0)){
                     JOptionPane.showMessageDialog(null, "Salvo Com Sucesso!");
                     if(!ctrEmp.salvarEmp())
                         JOptionPane.showMessageDialog(null, "ERRO ","ERRO",JOptionPane.ERROR_MESSAGE);
                     this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "ERRO ","Não é possivel fazer o emprestimo",JOptionPane.ERROR_MESSAGE);
                
         }
           
       
        if(ae.getSource()==btnCancelar){
            
                this.dispose();
            
        }
    }
}
