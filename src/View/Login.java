/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ctrLogin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;


/**
 *
 * @author nazar
 */
public class Login extends JFrame implements ActionListener{
    
    JLabel imgLeft, msgImgLeft, titulo, us, logUser, ps, logPass, logoKhuvu;
    JTextField user;
    JPasswordField pass;
    JButton send;
    JPanel master;
    Font loginU, fontImgLetf, fontTitulo;
    JSeparator sepUser, sepPass;
    int nr;
   
    public Login(){
        setSize(800,550);
        setTitle("LOGIN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        master = new JPanel();
        master.setLayout(null);
        add(master);
        master.setBackground(Color.white);
        
        //Imagem lado esquerdo
        imgLeft = new JLabel();
        imgLeft.setIcon( new ImageIcon("Imagens2\\money.png"));
        imgLeft.setBounds(40, 15, 360, 394);
        
        
        
        //Titlo  para os TextField
        fontTitulo = new Font("DialogInput",Font.BOLD,44);
        titulo = new JLabel();
        titulo.setFont(fontTitulo);
        titulo.setText("Entrar");
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setBounds(530,60,350,50);
        
        //Fonte
        loginU = new Font("DialogInput",Font.ROMAN_BASELINE,16);
        
        //User-Name
        us = new JLabel("usuário");
        us.setFont(loginU);
        us.setBounds(450,160,80,20);
        user = new JTextField(30);
        user.setBorder(null);
        user.setBounds(500,210,240,40);
        logUser = new JLabel("",CENTER);
        logUser.setIcon(new ImageIcon("Imagens2\\userLogo.png"));
        logUser.setBounds(450,210,39,35);
        
        //Separador User
        sepUser =new JSeparator(JSeparator.HORIZONTAL);
        sepUser.setBounds(450,250,300,2);
        sepUser.setForeground(Color.black);
        
        //User-Pass
        ps = new JLabel("senha");
        ps.setFont(loginU);
        ps.setBounds(450,280,80,20);
        pass = new JPasswordField();
        pass.setBorder(null);
        pass.setBounds(500,330,240,40);
        logPass = new JLabel("",CENTER);
        logPass.setIcon(new ImageIcon("Imagens2\\passLogo.png"));
        logPass.setBounds(450,330,39,35);
        
        //Separador pass
        sepPass = new JSeparator(JSeparator.HORIZONTAL);
        sepPass.setBounds(450,370,300,2);
        sepPass.setForeground(Color.black);
        
        //BOTAO SEND
        send = new JButton("ENTRAR");
        send.addActionListener(this);
        send.setBounds(650,400,90,40);
        send.setBackground(new java.awt.Color(0, 223, 186));
        send.setBorder(null);
        send.setForeground(Color.white);
        
        //Logo
        //logoKhuvu = new JLabel("",CENTER);
        //logoKhuvu.setIcon(new ImageIcon("Imagens\\logoKhuvu.jpeg")); 
        //logoKhuvu.setBounds(500,100,200,70);
        
        master.add(sepPass);
       // master.add(logoKhuvu);
        master.add(sepUser);
        master.add(send);
        master.add(ps);
        master.add(pass);
        master.add(logPass);
        master.add(us);
        master.add(user);
        master.add(logUser);        
        master.add(titulo);
       // master.add(msgImgLeft);
        master.add(imgLeft);
        
        setVisible(true);
    }
    
    private String randomImageLogin(){
        String imagens[] = {"signin-image.jpg","signup-image.jpg"};
        nr = (int)(Math.random()*2);  
        return imagens[nr];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         char t[] = pass.getPassword();
         char[] n = t;
        if(e.getSource() == send){
            ctrLogin lg=new ctrLogin();
            if(user.getText().equalsIgnoreCase("") || pass.equals(""))
                JOptionPane.showMessageDialog(null,
                    "Campo vazio","Aviso",
                    JOptionPane.WARNING_MESSAGE); 
            else
                if(lg.valida(Integer.parseInt(user.getText()), pass.getText())==true){
                    new Index(Integer.parseInt(user.getText()));
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null,"User e/ou Password Incorreto!","Aviso",JOptionPane.WARNING_MESSAGE); 
           
        }
    }
    
    
    public static void main(String[] args) {
        new Login();
    }

  
}
