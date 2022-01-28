/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ctrCliente;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.data_acess_object.clienteDAO;

/**
 *
 * @author nazar
 */
public class editCliente extends JDialog implements ActionListener{
    
    Container ctn;
    JLabel lblTit, perfil,txtID,txtNome,txtData,txtEst,txtBI,txtMorada,txtRend,txtCont,txtSexo;
    JTextField entryID,entryNome,entryData,entryEst,entryBI,entryMorada,entryRend,entryCont,entrySexo;
    JComboBox <String> jcbSexo,jcbEst;
    JButton btnSave,btnCancel,btnRemove;
    
    public editCliente(int id,String nome, String sexo,int nasci, String estCvl, String bi, String morada, float rendMsl,int ctt){
        setSize(900,380);
        setTitle("EDIT Cliente");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);//Quando Aberta, nenhuma outra podera ser aberta
        setLocationRelativeTo(null);
        
        ctn=getContentPane();
        ctn.setLayout(null);
        ctn.setBackground(Color.WHITE);
        
        Font ftTit=new Font("Courier",Font.BOLD, 16);
        //Borda do Titulo
        JPanel Borda = new JPanel();
        Borda.setLayout(null);
        Borda.setBounds(0,0,900,50);
        Borda.setBackground(new java.awt.Color(58,70,80));
        //Titulo
        lblTit=new JLabel("Editar Informaçoes do Cliente");
        lblTit.setForeground(Color.WHITE);
        lblTit.setFont(ftTit);
        lblTit.setHorizontalAlignment(SwingConstants.LEFT);
        lblTit.setBounds(50,10,250,30);
        Borda.add(lblTit);
        //Profile Pic
        perfil=new JLabel();
        perfil.setBorder(BorderFactory.createLineBorder(Color.black));
        perfil.setBounds(30,90,170,190);
        //Labels
        JSeparator sep=new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.black);
        sep.setBounds(220,280,630,2);
        ctn.add(sep);
        
        txtID=new JLabel("ID Cliente:");
        txtID.setBounds(220,90,100,20);
        entryID=new JTextField();
        entryID.setBounds(370,90,100,20);
        entryID.setText(Integer.toString(id));
        entryID.setEditable(false);
        
        txtNome=new JLabel("Nome:");
        txtNome.setBounds(220,130,100,20);
        entryNome=new JTextField(nome);
        entryNome.setBounds(370,130,200,20);
        
        txtSexo=new JLabel("Sexo:");
        txtSexo.setBounds(620,130,100,20);
        jcbSexo=new JComboBox();
        if(sexo.equals("Masculino")){
            jcbSexo.addItem("Masculino");
            jcbSexo.addItem("Femenino");}
        if(sexo.equals("Femenino")){
            jcbSexo.addItem("Femenino");
            jcbSexo.addItem("Masculino");
        }
        jcbSexo.setBounds(710,130,100,20);
        
        txtData=new JLabel("Ano Nascimento:");
        txtData.setBounds(220,170,100,20);
        entryData=new JTextField(Integer.toString(nasci));
        entryData.setBounds(370,170,50,20);
        
        txtEst=new JLabel("Estado Civil:");
        txtEst.setBounds(620,170,90,20);
        jcbEst=new JComboBox();
        if(estCvl.equals("Solteiro")){
            jcbEst.addItem("Solteiro");
            jcbEst.addItem("Casado");
            jcbEst.addItem("Divorciado");}
        if(estCvl.equals("Casado")){
            jcbEst.addItem("Casado");
            jcbEst.addItem("Solteiro");
            jcbEst.addItem("Divorciado");
        }
        if(estCvl.equals("Divorciado")){
            jcbEst.addItem("Divorciado");
            jcbEst.addItem("Casado");
            jcbEst.addItem("Solteiro");
        } 
        jcbEst.setBounds(710,170,100,20);
        
        txtBI=new JLabel("Nr de BI:");
        txtBI.setBounds(620,210,90,20);
        entryBI=new JTextField(bi);
        entryBI.setBounds(710,210,120,20);
        
        txtMorada=new JLabel("Morada:");
        txtMorada.setBounds(220,210,100,20);
        entryMorada=new JTextField(morada);
        entryMorada.setBounds(370,210,200,20);
        
        txtRend=new JLabel("Rendimento Mensal:");
        txtRend.setBounds(220,250,150,20);
        entryRend=new JTextField(Float.toString(rendMsl));
        entryRend.setBounds(370,250,100,20);
        
        txtCont=new JLabel("Contacto:");
        txtCont.setBounds(620,250,90,20);
        entryCont=new JTextField(Integer.toString(ctt));
        entryCont.setBounds(710,250,120,20);
        //Botoes save e remove
        btnSave=new JButton("SALVAR");
        btnSave.setBackground(new java.awt.Color(0, 223, 186));
        btnSave.setForeground(Color.black);
        btnSave.setBounds(370,300,100,30);
        btnSave.addActionListener(this);
        
        btnCancel=new JButton("Cancelar");
        btnCancel.setBackground(new java.awt.Color(0, 223, 186));
        btnCancel.setForeground(Color.black);
        btnCancel.setBounds(490,300,100,30);
        btnCancel.addActionListener(this);
        
        btnRemove=new JButton("Remover Cliente");
        btnRemove.setBackground(Color.red);
        btnRemove.setForeground(Color.white);
        btnRemove.setBounds(610,300,150,30);
        btnRemove.addActionListener(this);
        //
        ctn.add(btnRemove);
        ctn.add(btnCancel);
        ctn.add(btnSave);
        ctn.add(txtID);
        ctn.add(txtNome);
        ctn.add(txtData);
        ctn.add(txtEst);
        ctn.add(txtBI);
        ctn.add(txtMorada);
        ctn.add(txtRend);
        ctn.add(txtCont);
        ctn.add(txtSexo);
        ctn.add(jcbSexo);
        ctn.add(entryID);
        ctn.add(entryNome);
        ctn.add(entryData);
        ctn.add(jcbEst);
        ctn.add(entryBI);
        ctn.add(entryMorada);
        ctn.add(entryRend);
        ctn.add(entryCont);
        ctn.add(perfil);
        ctn.add(Borda);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
  //      new editCliente(1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btnSave){
            boolean scc;
            try{
                ctrCliente ctr=new ctrCliente();
                scc=ctr.modificar(Integer.parseInt(entryID.getText()),entryNome.getText(), jcbSexo.getSelectedItem().toString(),Integer.parseInt(entryData.getText()), jcbEst.getSelectedItem().toString(), 
                        entryBI.getText(), entryMorada.getText(), Float.parseFloat(entryRend.getText()), Integer.parseInt(entryCont.getText()));
                
                if(scc){
                    JOptionPane.showMessageDialog(null, "Actualizado Com Sucesso!");
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Os Campos não foram preenchidos Correctamente!");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro: "+e);
            }
        }
        if(ae.getSource()==btnCancel){
            int yes_no=JOptionPane.showConfirmDialog(null, "Tem Certeza?","Confirm",JOptionPane.YES_NO_OPTION);
            if(yes_no==JOptionPane.YES_OPTION){
                dispose();
            }
        }
        if(ae.getSource()==btnRemove){
            ctrCliente ctr=new ctrCliente();
            boolean scc;
            int yes_no=JOptionPane.showConfirmDialog(null, "Tem Certeza?","Confirm",JOptionPane.YES_NO_OPTION);
            if(yes_no==JOptionPane.YES_OPTION){
                scc=ctr.remover(Integer.parseInt(entryID.getText()));
                if(scc){
                    JOptionPane.showMessageDialog(null, "Deletado Com Sucesso!");
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Erro!");
                }
            }
            
        }
        
    }
    
}
