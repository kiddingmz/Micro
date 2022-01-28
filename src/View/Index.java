/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.ctrAtivo;
import Controller.ctrCliente;
import Controller.ctrEmprestimo;
import Controller.ctrFuncionario;
import Controller.ctrLogin;
import Controller.ctrRelatorio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.data_acess_object.clienteDAO;
import model.data_acess_object.funcionarioDAO;
import model.value_object.Ativo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author nazar
 */
public class Index extends JFrame implements ActionListener {
    
    //Public
    Container geral;
    JPanel topLevel, mnLevel,titLevel, central;
    JTextField pesq;
    JButton btnPesq, btnLogin, menuIni, menuEmp, menuCli, menuReg, menuFunc, menuSett;
    JLabel lblTit, lblLogo;
    static int Adm;
    //Home
                //JLabel lblIconMic;
                //JPanel infoLevel;
                //JButton btnStart;
    JPanel infoLevel[], infoLevelIntern[], paneCircularHome, paneBarrasHome;
    JLabel infoLabel[];
    int liq, activo, entrada, saida, numEmp, numCli, numActi, numLiq;
    DefaultPieDataset dadosGraficoCircular;
    JFreeChart circularHome, barrasHome;
    ChartPanel grfCircularHome, grfBarrasHome;
    DefaultCategoryDataset dadosGraficoBarra;
    
    //CLients
    JPanel clientAll,searchClient;
    DefaultTableModel client;
    JTable tblClient, tblFuncio;
    JScrollPane scrollClient;
    JTextField entryID;
    JLabel txtDig;
    JButton pesqClient, addClient,removeCli;
    
    //Funcionario
    JPanel searchFuncio;
    JButton pesqFuncio, addFuncio, editFuncio, salFuncio, removeFuncio;
    JPanel funcioAll;
    DefaultTableModel funcio;
    
    //Emprestimo
    float vlPres;
    JPanel emp;
    JButton pesqEmp,newEmp,editEmp,payEmp, histEmp;
    JLabel iconProfile, txtCli, txtEmp, txtNome,txtCred,txtVf,txtVp, txtFuncio;
    JTextField  entryCli, entryEmp,entryNome,entryCred,entryVf,entryVp,entryFuncio;
    
    //Settings
    JPanel settPaneIconPerfil;
    JButton settButSave, settButPerfil;
    JLabel settNome, settApelido, settIconPerfil, settAniversario, settTelefone, settEmail;
    JLabel settSenha, settSenhaAnt, settSenhaConf, settSubTituloH, settSubTituloM, settSubTituloF,settSubTituloP;
    Font settSub, settNormal; 
    JTextField settTextNome, settTextApelido, settTextAniversario, settTextTelefone, settTextEmail;
    JPasswordField settTextSenha, settTextSenhaAnt, settTextSenhaConf;
    JSeparator settM, settCU, settCD;
    JFileChooser settImagemPefil;
    
    //Registro
    JPanel subPainel;
    JButton btSemanal, btDiario, btMensal, btAnual;
    JTable tabela;
    JScrollPane sp;
    String [] nomeColunas;
    Object [][] info;
    DefaultTableModel reg;
    
    Cliente cl;
    Funcionario func;
    Emprestimo emprest;
    JButton gera;
    
    public Index(int Adm){
        this.Adm=Adm;
        
        setTitle("HOME");
        setSize(1360,745);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Container GERAL
        geral = getContentPane();
        geral.setBackground(new java.awt.Color(237, 237, 237));
        geral.setLayout(null);
        
        //Icons
        ImageIcon logoPesq=new ImageIcon("Imagens\\logoPesquisar.jpeg");
        ImageIcon logoLogin=new ImageIcon("Imagens\\logoLogin.jpeg");
        ImageIcon logoHome=new ImageIcon("Imagens\\home.jpeg");
        ImageIcon logoClient=new ImageIcon("Imagens\\client.jpeg");
        ImageIcon logoEmprestimo=new ImageIcon("Imagens\\emprestimo.jpeg");
        ImageIcon logoFuncionario=new ImageIcon("Imagens\\funcionario.jpeg");
        ImageIcon logoRegistro=new ImageIcon("Imagens\\registro.jpeg");
        ImageIcon logoSettings=new ImageIcon("Imagens\\settings.jpeg");
        ImageIcon logoKhuvu=new ImageIcon("Imagens\\logoKhuvu.jpeg");
        
        //logo Khuvu
        lblLogo=new JLabel();
        lblLogo.setIcon(logoKhuvu);
        lblLogo.setBounds(30,10,200,80);
        //TextFields
        pesq=new JTextField("");//Para Pesquisar algo
        pesq.setBackground(Color.WHITE);
        pesq.setBounds(250,25,250,50);
        
        //Buttons
        btnPesq=new JButton();//Para Clicar em pesquisar
        btnPesq.setIcon(logoPesq);
        btnPesq.setBackground(new java.awt.Color(0, 223, 186));
        btnPesq.setBorder(null);
        btnPesq.setBounds(500,25,90,50);
        
        btnLogin=new JButton("˅");
        btnLogin.setBackground(new java.awt.Color(237, 237, 237));
        btnLogin.setIcon(logoLogin);
        btnLogin.setBorder(null);
        btnLogin.setBounds(1260,20,70,60);
        btnLogin.setToolTipText("ID do Funcionário: "+Adm);
        btnLogin.addActionListener(this);
        //Label TITULO
        Font ftTit=new Font("Courier",Font.BOLD, 19);
        lblTit= new JLabel("");
        lblTit.setFont(ftTit);
        lblTit.setHorizontalAlignment(SwingConstants.LEFT);
        lblTit.setBackground(new java.awt.Color(97,124,140));
        lblTit.setForeground(Color.WHITE);
        lblTit.setBounds(50,20,275,40);
        
        //Estererelizaação da BARRA DE MENU
        Font ft=new Font("Courier",Font.BOLD, 16);

        menuIni=new JButton("Página Inicial");
        menuIni.setName("ini");
        menuIni.setIcon(logoHome);
        menuIni.setHorizontalAlignment(SwingConstants.LEFT);
        menuIni.setBackground(new java.awt.Color(58,70,80));
        menuIni.setForeground(Color.WHITE);
        menuIni.setBorder(null);
        menuIni.setFont(ft);
        menuIni.setBounds(25,25,200,50);
        menuIni.addActionListener(this);
        
        
        
        
        menuEmp=new JButton("Empréstimo");
        menuEmp.setName("emp");
        menuEmp.setIcon(logoEmprestimo);
        menuEmp.setHorizontalAlignment(SwingConstants.LEFT);
        menuEmp.setBackground(new java.awt.Color(58,70,80));
        menuEmp.setForeground(Color.WHITE);
        menuEmp.setBorder(null);
        menuEmp.setFont(ft);
        menuEmp.setBounds(25,115,200,50);
        menuEmp.addActionListener(this);
        
        
        menuCli=new JButton("Cliente");
        menuCli.setName("cli");
        menuCli.setIcon(logoClient);
        menuCli.setHorizontalAlignment(SwingConstants.LEFT);
        menuCli.setBackground(new java.awt.Color(58,70,80));
        menuCli.setForeground(Color.WHITE);
        menuCli.setBorder(null);
        menuCli.setFont(ft);
        menuCli.setBounds(25,205,200,50);
        menuCli.addActionListener(this);
        
        
        menuFunc=new JButton("Funcionário");
        menuFunc.setName("fun");
        menuFunc.setIcon(logoFuncionario);
        menuFunc.setHorizontalAlignment(SwingConstants.LEFT);
        menuFunc.setBackground(new java.awt.Color(58,70,80));
        menuFunc.setForeground(Color.WHITE);
        menuFunc.setBorder(null);
        menuFunc.setFont(ft);
        menuFunc.setBounds(25,295,200,50);
        menuFunc.addActionListener(this);
        
        
        menuReg=new JButton("Relatorio");
        menuReg.setName("reg");
        menuReg.setIcon(logoRegistro);
        menuReg.setHorizontalAlignment(SwingConstants.LEFT);
        menuReg.setBackground(new java.awt.Color(58,70,80));
        menuReg.setForeground(Color.WHITE);
        menuReg.setBorder(null);
        menuReg.setFont(ft);
        menuReg.setBounds(25,385,200,50);
        menuReg.addActionListener(this);
        
        
        menuSett=new JButton("Settings");
        menuSett.setName("set");
        menuSett.setIcon(logoSettings);
        menuSett.setHorizontalAlignment(SwingConstants.LEFT);
        menuSett.setBackground(new java.awt.Color(58,70,80));
        menuSett.setForeground(Color.WHITE);
        menuSett.setBorder(null);
        menuSett.setFont(ft);
        menuSett.setBounds(25,475,200,50);
        menuSett.addActionListener(this);
        
        //Painel TOP
        topLevel=new JPanel();
        topLevel.setLayout(null);
        topLevel.setBounds(0,0,1360,100);
        topLevel.add(btnPesq);
        topLevel.add(btnLogin);
        topLevel.add(pesq);
        topLevel.add(lblLogo);
        //Painel Lateral
        mnLevel=new JPanel();
        mnLevel.setLayout(null);
        mnLevel.setBackground(new java.awt.Color(58,70,80));
        mnLevel.setBounds(0,100,250,645);
        mnLevel.add(menuIni);
        mnLevel.add(menuEmp);
        mnLevel.add(menuCli);
        mnLevel.add(menuFunc);
        mnLevel.add(menuReg);
        mnLevel.add(menuSett);
        
        //Painel Titulo de menu
        titLevel=new JPanel();
        titLevel.setLayout(null);
        titLevel.setBackground(new java.awt.Color(97,124,140));
        titLevel.setBounds(250,100,1110,80);
        titLevel.add(lblTit);
        //Painel Central
        central=new JPanel();
        central.setLayout(null);
        central.setBounds(250,180,1110,645);
        
        painelHome();
        
        geral.add(topLevel);
        geral.add(mnLevel);
        geral.add(titLevel);
        geral.add(central);
        setVisible(true);
    }
    
    public void setReg(){
        ctrEmprestimo ett=new ctrEmprestimo();
        this.numEmp=ett.getEmpTot(1)+ett.getEmpTot(0);
        this.numActi=ett.getEmpTot(1);
        this.numLiq=ett.getEmpTot(0);
        
        ctrCliente edd=new ctrCliente();
        this.numCli=edd.getCliTot();
    }
    
    public void painelHome(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        setReg();
        lblTit.setText("Pagina Inicial");
        setTitle("HOME");
        
        central.setBackground(Color.WHITE);
        
        infoLevelIntern = new JPanel[8];
        infoLevel = new JPanel[4];
        infoLabel = new JLabel[8];
        
        for(int i =  0; i < infoLevel.length; i++)
            infoLevel[i] = new JPanel();
        
        for(int i =  0; i < infoLabel.length; i++){
            infoLabel[i] = new JLabel();
            infoLevelIntern[i] = new JPanel();
        }
        
        //Fonte
        Font infoFtU = new Font("Courier",Font.BOLD, 24);
        Font infoFtL = new Font("Courier",Font.ROMAN_BASELINE, 16);
        
        //Label Clientes
        infoLabel[0].setText(Integer.toString(numCli)); //BD info. nr de clientes
        infoLabel[0].setForeground (Color.white);
        infoLabel[0].setFont(infoFtU);
        infoLabel[1].setText("Clientes");
        infoLabel[1].setForeground (Color.white);
        infoLabel[1].setFont(infoFtL);
        
        //Label Emprestimo
        infoLabel[2].setText(Integer.toString(numEmp)); //BD info. nr de emprestimos
        infoLabel[2].setForeground (Color.white);
        infoLabel[2].setFont(infoFtU);
        infoLabel[3].setText("Emprestimos");
        infoLabel[3].setForeground (Color.white);
        infoLabel[3].setFont(infoFtL);
        
        //Label Liquidado
        infoLabel[4].setText(Integer.toString(numLiq)); //BD info. nr de liquidações
        infoLabel[4].setForeground (Color.white);
        infoLabel[4].setFont(infoFtU);
        infoLabel[5].setText("Liquidado");
        infoLabel[5].setForeground (Color.white);
        infoLabel[5].setFont(infoFtL);
        
        activo = numActi;
        liq = numLiq;
        entrada=10000000;
        saida=1000000;
        float rr=((float)numLiq/numActi)*100;
        System.out.println(rr);
         System.out.println(numActi);
          System.out.println(numLiq);
        //Label Percentagem Emprestimo/Liquidado
       
        infoLabel[6].setText( String.format("%.2f", rr)+"%");
        infoLabel[6].setForeground (Color.white);
        infoLabel[6].setFont(infoFtU);
        infoLabel[7].setText("L/E");
        infoLabel[7].setForeground (Color.white);
        infoLabel[7].setFont(infoFtL);
        
        //Painel Clientes
        infoLevel[0].setBounds(50,30,200,90);
        infoLevel[0].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].setLayout(new GridLayout(2,1));
        infoLevelIntern[0].setBackground(new java.awt.Color(0,120,50));
        infoLevelIntern[1].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].add(infoLevelIntern[0]);
        infoLevel[0].add(infoLevelIntern[1]);
        infoLevelIntern[0].add(infoLabel[0]);
        infoLevelIntern[1].add(infoLabel[1]);
        
        //Painel Emprestimo
        infoLevel[1].setBounds(300,30,200,90);
        infoLevel[1].setBackground(new java.awt.Color(0,102,204));
        infoLevel[1].setLayout(new GridLayout(2,1));
        infoLevelIntern[2].setBackground(new java.awt.Color(0,120,204));
        infoLevelIntern[3].setBackground(new java.awt.Color(0,120,204));
        infoLevel[1].add(infoLevelIntern[2]);
        infoLevel[1].add(infoLevelIntern[3]);
        infoLevelIntern[2].add(infoLabel[2]);
        infoLevelIntern[3].add(infoLabel[3]);
        
        //Painel Liquidado
        infoLevel[2].setBounds(550,30,200,90);
        infoLevel[2].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].setLayout(new GridLayout(2,1));
        infoLevelIntern[4].setBackground(new java.awt.Color(178,102,255));
        infoLevelIntern[5].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].add(infoLevelIntern[4]);
        infoLevel[2].add(infoLevelIntern[5]);
        infoLevelIntern[4].add(infoLabel[4]);
        infoLevelIntern[5].add(infoLabel[5]);
        
        //Painel Percentagem Emprestimo/Liquidado
        infoLevel[3].setBounds(800,30,200,90);
        infoLevel[3].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].setLayout(new GridLayout(2,1));
        infoLevelIntern[6].setBackground(new java.awt.Color(255,153,51));
        infoLevelIntern[7].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].add(infoLevelIntern[6]);
        infoLevel[3].add(infoLevelIntern[7]);
        infoLevelIntern[6].add(infoLabel[6]);
        infoLevelIntern[7].add(infoLabel[7]);
        
        //semanal, mensal, anual;
        
        activo = numActi;
        liq = numLiq;
        entrada=10000000;
        saida=1000000;
        
        //Painel Grafico Circular
        paneCircularHome = new JPanel();
        paneCircularHome.setBounds(50,150,500,360);
        paneCircularHome.setLayout(new BorderLayout());
        paneCircularHome.setBackground(new java.awt.Color(147,147,147));
        dadosGraficoCircular = new DefaultPieDataset();
        dadosGraficoCircular.setValue("Entrada", entrada); // Definindo categorias
        dadosGraficoCircular.setValue("Saídas", saida);
        
        //Grafico Circular
        circularHome = ChartFactory.createPieChart("Activos", dadosGraficoCircular, true,true,false);
        grfCircularHome = new ChartPanel(circularHome);
        paneCircularHome.add(grfCircularHome, BorderLayout.CENTER);
        
        //Painel Grafico de barras
        paneBarrasHome = new JPanel();
        paneBarrasHome.setBounds(570,150,440,360);
        paneBarrasHome.setLayout(new BorderLayout());
        paneBarrasHome.setBackground(new java.awt.Color(147,147,147));
        dadosGraficoBarra = new DefaultCategoryDataset();
        dadosGraficoBarra.setValue(activo,"Activo", "Activo");
        dadosGraficoBarra.setValue(liq,"Liquidado", "Liquidado");
        
         //Grafico de Barras
        barrasHome = ChartFactory.createBarChart("Emprestimo", "", "Saldo", dadosGraficoBarra);
        grfBarrasHome = new ChartPanel(barrasHome);
        paneBarrasHome.add(grfBarrasHome, BorderLayout.CENTER);
                
        central.add(infoLevel[0]);
        central.add(infoLevel[1]);
        central.add(infoLevel[2]);
        central.add(infoLevel[3]);
        central.add(paneCircularHome);
        central.add(paneBarrasHome);
        
    }
    
    public void painelClientes(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        setTitle("CLIENTE");
        lblTit.setText("CLIENTE");
        //Painel contendo Lista de clientes
        clientAll=new JPanel();
        clientAll.setLayout(null);
        clientAll.setBackground(Color.WHITE);
        clientAll.setBounds(50,225,600,260);
        central.add(clientAll);
        
        JSeparator sep=new JSeparator(JSeparator.VERTICAL);
        sep.setBounds(680,50,2,435);
        central.add(sep);
        //Label do titulo
        JPanel borda=new JPanel();
        borda.setLayout(null);
        borda.setBackground(new java.awt.Color(58,70,80));
        borda.setBounds(0,0,600,50);
        clientAll.add(borda);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblWl=new JLabel("Lista de Clientes");
        lblWl.setFont(ft);
        lblWl.setBounds(50,10,350,30);
        lblWl.setHorizontalAlignment(SwingConstants.LEFT);
        lblWl.setForeground(Color.WHITE);
        borda.add(lblWl);
        //Table
        client= new DefaultTableModel();
        client.addColumn("ID");
        client.addColumn("Nome");
        client.addColumn("Contacto");
        tblClient=new JTable(client);
        tblClient.enable(false);
        scrollClient=new JScrollPane(tblClient);
        scrollClient.setBounds(0,50,600,210);
        readClientes();
        clientAll.add(scrollClient);
        //painel de pesquisa
        searchClient=new JPanel();
        searchClient.setBorder(BorderFactory.createLineBorder(Color.black));
        searchClient.setLayout(null);
        searchClient.setBackground(Color.WHITE);
        searchClient.setBounds(50,50,600,155);
        central.add(searchClient);
        JPanel bordaS=new JPanel();
        bordaS.setLayout(null);
        bordaS.setBackground(new java.awt.Color(58,70,80));
        bordaS.setBounds(0,0,600,50);
        searchClient.add(bordaS);
        
        JLabel lblS=new JLabel("Pesquisar");
        lblS.setFont(ft);
        lblS.setBounds(50,10,150,30);
        lblS.setHorizontalAlignment(SwingConstants.LEFT);
        lblS.setForeground(Color.WHITE);
        bordaS.add(lblS);
        //Pesquisar
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Cliente:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        
        entryID=new JTextField();
        entryID.setBounds(50,100,260,30);
        
        pesqClient=new JButton("Pesquisar");
        pesqClient.setBackground(new java.awt.Color(0, 202, 188));
        pesqClient.setBounds(320,100,100,30);
        pesqClient.setForeground(Color.WHITE);
        pesqClient.addActionListener(this);
        
        searchClient.add(pesqClient);
        searchClient.add(txtDig);
        searchClient.add(entryID);
        //Acionar Clientes
        addClient=new JButton("ADD CLIENT");
        addClient.setBackground(new java.awt.Color(0, 202, 188));
        addClient.setForeground(Color.WHITE);
        addClient.setBounds(720,50,170,40);
        addClient.addActionListener(this);
        
        central.add(addClient);
        
    }
    
    public void painelFuncionario(){
        central.removeAll();
        central.revalidate();
        central.repaint();

        setTitle("Funcionário");
        lblTit.setText("Funcionário");
        
        //Search bar
        searchFuncio=new JPanel();
        searchFuncio.setLayout(null);
        searchFuncio.setBackground(Color.WHITE);
        searchFuncio.setBorder(BorderFactory.createLineBorder(Color.black));
        searchFuncio.setBounds(50,50,600,155);
        central.add(searchFuncio);
        JPanel bordaF=new JPanel();
        bordaF.setLayout(null);
        bordaF.setBackground(new java.awt.Color(58,70,80));
        bordaF.setBounds(0,0,600,50);
        searchFuncio.add(bordaF);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblF=new JLabel("Pesquisar");
        lblF.setFont(ft);
        lblF.setBounds(50,10,150,30);
        lblF.setHorizontalAlignment(SwingConstants.LEFT);
        lblF.setForeground(Color.WHITE);
        bordaF.add(lblF);
        //Pesquisar
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Funcionário:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        
        entryID=new JTextField(); //DIgitar o ID que pretende encontrar
        entryID.setBounds(50,100,260,30);
        
        pesqFuncio=new JButton("Pesquisar");
        pesqFuncio.setBackground(new java.awt.Color(0, 202, 188));
        pesqFuncio.setBounds(320,100,100,30);
        pesqFuncio.setForeground(Color.WHITE);
        pesqFuncio.addActionListener(this);
        
        searchFuncio.add(pesqFuncio);
        searchFuncio.add(txtDig);
        searchFuncio.add(entryID);
        //Lista Funcio
        funcioAll=new JPanel();
        funcioAll.setLayout(null);
        funcioAll.setBounds(50,225,600,260);
        funcioAll.setBackground(Color.WHITE);
        central.add(funcioAll);
        
        JPanel bordaL=new JPanel();
        bordaL.setLayout(null);
        bordaL.setBackground(new java.awt.Color(58,70,80));
        bordaL.setBounds(0,0,600,50);
        funcioAll.add(bordaL);
        
        JLabel lblL=new JLabel("Lista de Funcionários");
        lblL.setFont(ft);
        lblL.setBounds(50,10,200,30);
        lblL.setHorizontalAlignment(SwingConstants.LEFT);
        lblL.setForeground(Color.WHITE);
        bordaL.add(lblL);
        
       
        funcio=new DefaultTableModel();
        funcio.addColumn("ID");
        funcio.addColumn("Nome");
        tblFuncio=new JTable(funcio);
        tblFuncio.enable(false);
        JScrollPane scrollFuncio=new JScrollPane(tblFuncio);
        scrollFuncio.setBackground(Color.WHITE);
        scrollFuncio.setBounds(0,50,600,210);
        funcioAll.add(scrollFuncio);
        
         readFuncionarios();
        JSeparator barSep=new JSeparator(JSeparator.VERTICAL);
        barSep.setBounds(680,50,2,435);
        
        
        //TOOLS
        JPanel bordaT=new JPanel();
        bordaT.setLayout(null);
        bordaT.setBackground(new java.awt.Color(58,70,80));
        bordaT.setBounds(710,50,350,50);
        //central.add(bordaT);
        
        JLabel lblT=new JLabel("TOOLS");
        lblT.setFont(ft);
        lblT.setBounds(50,10,200,30);
        lblT.setHorizontalAlignment(SwingConstants.LEFT);
        lblT.setForeground(Color.WHITE);
        bordaT.add(lblT);
        
        central.add(barSep);
        
        addFuncio=new JButton("ADD Funcionário");
        addFuncio.setBounds(720,50,170,40);
        addFuncio.setBackground(new java.awt.Color(0, 202, 188));
        addFuncio.setForeground(Color.WHITE);
        addFuncio.addActionListener(this);
        central.add(addFuncio);
        
        salFuncio=new JButton("Folha Salarial");
        salFuncio.setBounds(790,180,170,40);
        salFuncio.setBackground(new java.awt.Color(0, 202, 188));
        salFuncio.setForeground(Color.WHITE);
        //central.add(salFuncio);
        
    }
    
    public static void main(String[] args) {
        new Index(2008);
    }
    
    public void painelEmprestimo(){
        central.removeAll();
        central.revalidate();
        central.repaint();

        setTitle("EMPRÉSTIMO");
        lblTit.setText("Empréstimo");
        
        emp=new JPanel();
        emp.setLayout(null);
        emp.setBorder(BorderFactory.createLineBorder(Color.black));
        emp.setBackground(Color.WHITE);
        
        emp.setBounds(50,100,1010,400);
        
        JPanel bordaF=new JPanel();
        bordaF.setLayout(null);
        bordaF.setBackground(new java.awt.Color(58,70,80));
        bordaF.setBounds(0,0,1010,50);
        emp.add(bordaF);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblF=new JLabel("Informações do Empréstimo ");
        lblF.setFont(ft);
        lblF.setBounds(50,10,250,30);
        lblF.setHorizontalAlignment(SwingConstants.LEFT);
        lblF.setForeground(Color.WHITE);
        bordaF.add(lblF);
        //Create EMP
        newEmp=new JButton("Novo Empréstimo");
        newEmp.setBackground(new java.awt.Color(0, 202, 188));
        newEmp.setForeground(Color.WHITE);
        newEmp.setBounds(50,30,175,50);
        newEmp.addActionListener(this);
        
        histEmp=new JButton("Histórico");
        histEmp.setBackground(new java.awt.Color(0, 202, 188));
        histEmp.setForeground(Color.WHITE);
        histEmp.setBounds(250,30,175,50);
        
        JSeparator sep=new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.BLACK);
        sep.setBounds(50,90,1010,2);
        
        central.add(sep);
        central.add(newEmp);
        central.add(histEmp);
        //pesquisa
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Empréstimo:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        emp.add(txtDig);
        
        entryID=new JTextField(); //DIgitar o ID que pretende encontrar
        entryID.setBounds(270,60,200,30);
        emp.add(entryID);
        
        pesqEmp=new JButton("Pesquisar");
        pesqEmp.setBackground(new java.awt.Color(0, 202, 188));
        pesqEmp.setBounds(470,60,100,30);
        pesqEmp.setForeground(Color.WHITE);
        pesqEmp.addActionListener(this);
        emp.add(pesqEmp);
        
        JSeparator separador=new JSeparator(JSeparator.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setBounds(50,100,910,2);
        emp.add(separador);
        //foto de perfil
        iconProfile=new JLabel();
        iconProfile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iconProfile.setBounds(50,150,175,200);
        emp.add(iconProfile);
        //Botoes operacao Emp
        /*JSeparator separador2=new JSeparator(JSeparator.VERTICAL);
        separador2.setForeground(Color.BLACK);
        separador2.setBounds(660,110,2,200);
        */
        
        payEmp=new JButton("Efectuar Pagamento");
        payEmp.setBackground(new java.awt.Color(0, 202, 188));
        payEmp.setBounds(720,350, 175, 35);
        payEmp.setForeground(Color.WHITE);
        payEmp.addActionListener(this);
       

        emp.add(payEmp);
        //DADDOS
        
        txtEmp=new JLabel("ID:");
        txtEmp.setBounds(255,110,120,30);
        entryEmp=new JTextField("");
        entryEmp.setBounds(390,110,250,30);
        entryEmp.setEditable(false);
        
        txtCli=new JLabel("ID Cliente:");
        txtCli.setBounds(255,150,140,30);
        entryCli=new JTextField();
        entryCli.setBounds(390,150,250,30);
        entryCli.setEditable(false);
        
        txtNome=new JLabel("Nome do Cliente:");
        txtNome.setBounds(255,190,140,30);
        entryNome=new JTextField();
        entryNome.setBounds(390,190,250,30);
        entryNome.setEditable(false);
        
        txtCred=new JLabel("Valor do Crédito:");
        txtCred.setBounds(255,230,140,30);
        entryCred=new JTextField();
        entryCred.setBounds(390,230,250,30);
        entryCred.setEditable(false);
        
        txtVf=new JLabel("Valor Remanescente:");
        txtVf.setBounds(255,270,140,30);
        entryVf=new JTextField();
        entryVf.setBounds(390,270,250,30);
        entryVf.setEditable(false);
        
        txtVp=new JLabel("Valor Arrematado:");
        txtVp.setBounds(255,310,140,30);
        entryVp=new JTextField();
        entryVp.setBounds(390,310,250,30);
        entryVp.setEditable(false);
        
        txtFuncio=new JLabel("ID Funcionário:");
        txtFuncio.setBounds(255,350,140,30);
        entryFuncio=new JTextField();
        entryFuncio.setBounds(390,350,250,30);
        entryFuncio.setEditable(false);
        
        Font ft3=new Font("Courier",Font.PLAIN, 12);
        emp.add(txtEmp);
        emp.add(txtCli);
        emp.add(txtNome);
        emp.add(txtCred);
        emp.add(txtVf);
        emp.add(txtVp);
        emp.add(txtFuncio);
        emp.add(entryEmp);
        emp.add(entryCli);
        emp.add(entryNome);
        emp.add(entryCred);
        emp.add(entryVf);
        emp.add(entryVp);
        emp.add(entryFuncio);
        //emp.add(separador2);
        
        central.add(emp);
    }
    
    public void painelRegistro(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        setTitle("Registo");
        lblTit.setText("Relatorio");
        
        infoLevelIntern = new JPanel[8];
        infoLevel = new JPanel[4];
        infoLabel = new JLabel[8];
        
        for(int i =  0; i < infoLevel.length; i++)
            infoLevel[i] = new JPanel();
        
        for(int i =  0; i < infoLabel.length; i++){
            infoLabel[i] = new JLabel();
            infoLevelIntern[i] = new JPanel();
        }
        
        //Fonte
        Font infoFtU = new Font("Courier",Font.BOLD, 24);
        Font infoFtL = new Font("Courier",Font.ROMAN_BASELINE, 16);
        
        //Listar 
        ctrAtivo ctr= new ctrAtivo();
        ArrayList<Ativo> ls = ctr.getList();
        
        //Label Capital
        
        infoLabel[0].setText(Float.toString(ls.get(0).getSaldo()));
        infoLabel[0].setForeground (Color.white);
        infoLabel[0].setFont(infoFtU);
        infoLabel[1].setText("Capital(MT)");
        infoLabel[1].setForeground (Color.white);
        infoLabel[1].setFont(infoFtL);
        
        //Label Emprestimo
        infoLabel[2].setText(Float.toString(ls.get(0).getEmp()));
        infoLabel[2].setForeground (Color.white);
        infoLabel[2].setFont(infoFtU);
        infoLabel[3].setText("Emprestimo(MT)");
        infoLabel[3].setForeground (Color.white);
        infoLabel[3].setFont(infoFtL);
        
        //Label Liquidado
        infoLabel[4].setText(Float.toString(ls.get(0).getLiq()));
        infoLabel[4].setForeground (Color.white);
        infoLabel[4].setFont(infoFtU);
        infoLabel[5].setText("Liquidado(MT)");
        infoLabel[5].setForeground (Color.white);
        infoLabel[5].setFont(infoFtL);
        
        String tex = "LUCRO";
        if(ls.get(0).getEmp() > ls.get(0).getLiq())
            tex = "PREJUIZO";
        if(ls.get(0).getEmp() == ls.get(0).getLiq())
            tex = "EQUILIBRADO";
        //double per = ls.get(0).getSaldo() - ls.get(0).getLiq())
        double perl = (ls.get(0).getEmp())/(ls.get(0).getSaldo());
        
        
        //Label Estado
        infoLabel[6].setText(String.valueOf(String.format("%.2f", perl*100)));
        infoLabel[6].setForeground (Color.white);
        infoLabel[6].setFont(infoFtU);
        infoLabel[7].setText("Estado: "+tex);
        infoLabel[7].setForeground (Color.white);
        infoLabel[7].setFont(infoFtL);
        
        //Painel Capital
        infoLevel[0].setBounds(50,30,200,90);
        infoLevel[0].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].setLayout(new GridLayout(2,1));
        infoLevelIntern[0].setBackground(new java.awt.Color(0,120,50));
        infoLevelIntern[1].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].add(infoLevelIntern[0]);
        infoLevel[0].add(infoLevelIntern[1]);
        infoLevelIntern[0].add(infoLabel[0]);
        infoLevelIntern[1].add(infoLabel[1]);
        
        //Painel Emprestimo
        infoLevel[1].setBounds(300,30,200,90);
        infoLevel[1].setBackground(new java.awt.Color(0,102,204));
        infoLevel[1].setLayout(new GridLayout(2,1));
        infoLevelIntern[2].setBackground(new java.awt.Color(0,120,204));
        infoLevelIntern[3].setBackground(new java.awt.Color(0,120,204));
        infoLevel[1].add(infoLevelIntern[2]);
        infoLevel[1].add(infoLevelIntern[3]);
        infoLevelIntern[2].add(infoLabel[2]);
        infoLevelIntern[3].add(infoLabel[3]);
        
        //Painel Liquidado
        infoLevel[2].setBounds(550,30,200,90);
        infoLevel[2].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].setLayout(new GridLayout(2,1));
        infoLevelIntern[4].setBackground(new java.awt.Color(178,102,255));
        infoLevelIntern[5].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].add(infoLevelIntern[4]);
        infoLevel[2].add(infoLevelIntern[5]);
        infoLevelIntern[4].add(infoLabel[4]);
        infoLevelIntern[5].add(infoLabel[5]);
        
        //Painel Estado
        infoLevel[3].setBounds(800,30,200,90);
        infoLevel[3].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].setLayout(new GridLayout(2,1));
        infoLevelIntern[6].setBackground(new java.awt.Color(255,153,51));
        infoLevelIntern[7].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].add(infoLevelIntern[6]);
        infoLevel[3].add(infoLevelIntern[7]);
        infoLevelIntern[6].add(infoLabel[6]);
        infoLevelIntern[7].add(infoLabel[7]);
        
        //Sub-Painel Menu
        subPainel = new JPanel();
        subPainel.setBackground(new java.awt.Color(97,124,140));
        subPainel.setBounds(0,150,1110,50);
        JLabel ttt= new JLabel("HISTÓRICO DE PAGAMENTOS");
        ttt.setBounds(50,10,200,30);
        ttt.setForeground(Color.white);
        
        
        gera =new JButton("Relatorio PDF");
        gera.setBounds(600,5,170,40);
        gera.setBackground(new java.awt.Color(0, 202, 188));
        gera.setForeground(Color.WHITE);
        gera.addActionListener(this);
        subPainel.add(gera);
        
        //Buttons
        btSemanal = new JButton("Semanal");
        btSemanal.setBorder(null);
        btSemanal.setBounds(30,10,230,30);
        btSemanal.setBackground(new java.awt.Color(58,70,80));
        btSemanal.setForeground(Color.white);
        btDiario = new JButton("Diario");
        btDiario.setBorder(null);
        btDiario.setBounds(295,10,230,30);
        btDiario.setBackground(new java.awt.Color(58,70,80));
        btDiario.setForeground(Color.white);
        btMensal = new JButton("Mensal");
        btMensal.setBorder(null);
        btMensal.setBounds(555,10,230,30);
        btMensal.setBackground(new java.awt.Color(58,70,80));
        btMensal.setForeground(Color.white);
        btAnual = new JButton("Anual");
        btAnual.setBorder(null);
        btAnual.setBounds(820,10,230,30);
        btAnual.setBackground(new java.awt.Color(58,70,80));
        btAnual.setForeground(Color.white);
        
        //Tabela
        paneCircularHome = new JPanel();
        paneCircularHome.setBounds(50,220,740,290);
        paneCircularHome.setLayout(new BorderLayout());
     
        reg= new DefaultTableModel();
        reg.addColumn("ID Empréstimo");
        reg.addColumn("Nome");
        reg.addColumn("Pagamento");
        reg.addColumn("ID Funcionário");
        reg.addColumn("Data");
        
        tabela = new JTable(reg);
        tabela.enable(false);
        tabela.setFillsViewportHeight(true);
        sp = new JScrollPane(tabela);
        paneCircularHome.add(sp);
        readRegistro();
        
        //Painel Grafico de barras
        paneBarrasHome = new JPanel();
        paneBarrasHome.setBounds(790,220,290,280);
        paneBarrasHome.setLayout(new BorderLayout());
        paneBarrasHome.setBackground(new java.awt.Color(147,147,147));
        dadosGraficoBarra = new DefaultCategoryDataset();
        dadosGraficoBarra.setValue(activo,"Activo", "Activo");
        dadosGraficoBarra.setValue(liq,"Liquidado", "Liquidado");
        
         //Grafico de Barras
        barrasHome = ChartFactory.createBarChart("Emprestimo", "", "Saldo", dadosGraficoBarra);
        grfBarrasHome = new ChartPanel(barrasHome);
        paneBarrasHome.add(grfBarrasHome, BorderLayout.CENTER);
        
        subPainel.setLayout(null);
        subPainel.add(ttt);
        //subPainel.add(btSemanal);
        //subPainel.add(btMensal);
        //subPainel.add(btAnual);
        central.add(paneCircularHome);
        central.add(paneBarrasHome);
        central.add(subPainel);
        central.add(infoLevel[0]);
        central.add(infoLevel[1]);
        central.add(infoLevel[2]);
        central.add(infoLevel[3]);
    }
    
    public void painelConfiguracoes(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        setTitle("DEFINIÇÕES");
        lblTit.setText("Configurações de Perfil");
        
        //Painel settings
        //sett = new JPanel();
        //sett.setBounds(0,0, 500, 200);
        //sett.setBackground(Color.red);
        //central.add(sett);
        
        //Fonte
        settSub = new Font("Sans",Font.BOLD,15);
        settNormal = new Font("DialogInput",Font.ROMAN_BASELINE,16);
        
        //Scroll
        //sp = new JScrollPane();
        
        ctrFuncionario ff= new ctrFuncionario();
        ff.encontrar(Adm);
        //Subtitulo
        settSubTituloH = new JLabel("Detalhes de Perfil");
        settSubTituloH.setBounds(50, 0, 300, 30);
        settSubTituloH.setFont(settSub);
        settNome = new JLabel("Nome");
        settNome.setBounds(50, 50, 300, 30);
        settNome.setFont(settNormal);
        settApelido = new JLabel("Sexo");
        settApelido.setBounds(50, 100, 300, 30);
        settApelido.setFont(settNormal);
        settAniversario = new JLabel("ID Funcionário");
        settAniversario.setBounds(50, 150, 300, 30);
        settAniversario.setFont(settNormal);
        
        //Subtitulo
        settSubTituloM = new JLabel("Informação de Contato");
        settSubTituloM.setBounds(50, 220, 300, 30);
        settSubTituloM.setFont(settSub);
        settTelefone = new JLabel("Telefone");
        settTelefone.setBounds(50, 270, 300, 30);
        settTelefone.setFont(settNormal);
        settEmail = new JLabel("Email");
        settEmail.setBounds(50, 320, 300, 30);
        settEmail.setFont(settNormal);
        
        //Subtitulo
        settSubTituloF = new JLabel("Senha");
        settSubTituloF.setBounds(550, 220, 300, 30);
        settSubTituloF.setFont(settSub);
        settSenhaAnt = new JLabel("Senha Anterior");
        settSenhaAnt.setBounds(550, 270, 300, 30);
        settSenhaAnt.setFont(settNormal);
        settSenha = new JLabel("Senha Atual");
        settSenha.setBounds(550, 320, 300, 30);
        settSenha.setFont(settNormal);
        settSenhaConf = new JLabel("Confirma a Senha");
        settSenhaConf.setBounds(550, 370, 300, 30);
        settSenhaConf.setFont(settNormal);
         
       //TextField
        settTextNome = new JTextField(ff.getNome());
        settTextNome.setBounds(260, 50, 250, 30);
        settTextNome.setEditable(false);
        settTextApelido = new JTextField(ff.getSexo());
        settTextApelido.setBounds(260, 100, 250, 30);
        settTextApelido.setEditable(false);
        settTextAniversario = new JTextField(Integer.toString(ff.getId()));
        settTextAniversario.setBounds(260, 150, 250, 30);
        settTextAniversario.setEditable(false);
        settTextTelefone = new JTextField(Integer.toString(ff.getCtt()));
        settTextTelefone.setBounds(260, 270, 250, 30);
        settTextTelefone.setEditable(false);
        settTextEmail = new JTextField("adminKhuvu@khuvu.com");
        settTextEmail.setBounds(260, 320, 250, 30);
        settTextEmail.setEditable(false);
    
        //PasswordField
        settTextSenha = new JPasswordField();
        settTextSenha.setBounds(750, 320, 250, 30);
        settTextSenhaAnt = new JPasswordField();
        settTextSenhaAnt.setBounds(750, 270, 250, 30);
        settTextSenhaConf = new JPasswordField();
        settTextSenhaConf.setBounds(750, 370, 250, 30);
        
        
       //Botoes
        settButPerfil = new JButton("Upload");
        settButPerfil.setFont(settSub);
        settButPerfil.setBounds(775, 180, 100, 20);
        settButPerfil.addActionListener(this);
        settButPerfil.setBackground(new java.awt.Color(0, 223, 186));
        settButPerfil.setBorder(null);
        settButPerfil.setForeground(Color.white);
        settButSave = new JButton("Salvar");
        settButSave.setFont(settSub);
        settButSave.setBounds(790, 460, 100, 30);
        settButSave.addActionListener(this);
        settButSave.setBackground(new java.awt.Color(0, 223, 186));
        settButSave.setBorder(null);
        settButSave.setForeground(Color.white);
        
        //Titulo Perfil
        settSubTituloP = new JLabel("Foto de Perfil");
        settSubTituloP.setBounds(770, 0, 300, 50);
        settSubTituloP.setFont(settSub);
        
        // Borda Icon Perfil
        settPaneIconPerfil = new JPanel();
        //settPaneIconPerfil.setLayout(null);
        settPaneIconPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        settPaneIconPerfil.setBounds(750,50,145,120);
        
        //Icon Perfil
        settIconPerfil = new JLabel();
        settIconPerfil.setIcon(new ImageIcon("Imagens2\\money.png"));
        settPaneIconPerfil.setLayout(new GridLayout(1,1));
        settIconPerfil.setBounds(5, 5, 145, 120);
        settPaneIconPerfil.add(settIconPerfil);
        
        //Separador Meio/ Lateral em cima/ laberal em baixo
        settM = new JSeparator(JSeparator.HORIZONTAL);
        settM.setBounds(50, 210, 950, 5);
        settM.setForeground(Color.black);
        settCU = new JSeparator(JSeparator.VERTICAL);
        settCU.setBounds(530,220,2,200);
        settCU.setForeground(Color.black);
        settCD = new JSeparator(JSeparator.VERTICAL);
        settCD.setBounds(650,10,2,190);
        settCD.setForeground(Color.black);
       
        //central.add(sp);
        central.add(settM);
        central.add(settCU);
        central.add(settCD);
        central.add(settSubTituloP);
        central.add(settPaneIconPerfil);
        central.add(settButSave);
        central.add(settButPerfil);
        central.add(settTextNome);
        central.add(settTextApelido);
        central.add(settTextAniversario);
        central.add(settTextTelefone);
        central.add(settTextEmail);
        central.add(settTextSenha);
        central.add(settTextSenhaAnt);
        central.add(settTextSenhaConf);
        central.add(settSubTituloH);
        central.add(settNome);
        central.add(settApelido);
        central.add(settAniversario);
        central.add(settSubTituloM);
        central.add(settTelefone);
        central.add(settEmail);
        central.add(settSubTituloF);
        central.add(settSenhaAnt);
        central.add(settSenha);
        central.add(settSenhaConf);
       // sp.setViewportView(area);
        //central.add(sp);
    }
    
    public void readClientes(){ 
        ctrCliente ctrCli =new ctrCliente();
        Vector cle=ctrCli.listar();
        for(int i=0;i<cle.size();i++)
            client.addRow((Object[]) cle.elementAt(i));
        
    }
    
    public void readRegistro(){ 
        ctrRelatorio ctrCli =new ctrRelatorio();
        Vector cle=ctrCli.listar();
        for(int i=0;i<cle.size();i++)
            reg.addRow((Object[]) cle.elementAt(i));
        
    }
    
    public void readFuncionarios(){ 
        ctrFuncionario ctrFun =new ctrFuncionario();
        Vector lst=ctrFun.listar();
        for(int i=0;i<lst.size();i++){
            funcio.addRow((Object[]) lst.elementAt(i));
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == histEmp){
            
        }
        if(ae.getSource() == gera){
            GeraPdf g;
            try {
                g = new GeraPdf();
                g.relatorioPDF();
            } catch (ParseException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        if(ae.getSource()==btnLogin)
            painelConfiguracoes();
        
        if(ae.getSource()==menuIni){
            
            
            painelHome();
        }
        if(ae.getSource()==menuCli){
           
            painelClientes();
        }
        if(ae.getSource()==menuFunc){
            
            painelFuncionario();
        }
        if(ae.getSource()==menuEmp){
            
            painelEmprestimo();
        }
        if(ae.getSource() == menuReg){
            
            painelRegistro();
        }    
        if(ae.getSource() == menuSett){
            
            painelConfiguracoes();
        }
        
        if(ae.getSource() == settButPerfil){
            settImagemPefil = new JFileChooser();
            settImagemPefil.setCurrentDirectory(new File("user.dir"));
            settImagemPefil.addChoosableFileFilter(new FileNameExtensionFilter("jpeg","png","jpg","gif"));
            
            if(settImagemPefil.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                settIconPerfil.setIcon(new ImageIcon(String.valueOf(settImagemPefil.getSelectedFile()))); 
            
        }
        
       /* if(ae.getSource() == settButSave){
            ctrLogin lg=new ctrLogin();
            if(settSenha.getText()=="" || settSenhaAnt.getText()==""||settSenhaConf.getText()=="" )
                JOptionPane.showMessageDialog(null, "Preencha Todos Campos!");
            else{
                
                    if(lg.valida(Adm,settSenhaAnt.getText())==true){
                        if(lg.update(Adm, settSenhaConf.getText())==true){
                            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                            settSenhaAnt.setText("");
                            settSenha.setText("");
                            settSenhaConf.setText("");
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Erro ao Salvar Senha!","ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Senha Anterior Incorreto!","Aviso",JOptionPane.WARNING_MESSAGE);
                
            }   
        }*/
        
        if(ae.getSource()==addClient){
            cl=new Cliente();
            painelClientes();
        }
        if(ae.getSource()==newEmp){
            emprest = new Emprestimo(2001);}
        if(ae.getSource()==addFuncio){
            
            func=new Funcionario();
            painelFuncionario();
        }
        if(ae.getSource()==payEmp){
            payEmp pp= new payEmp(Integer.parseInt(entryEmp.getText()),vlPres);
        }
        
        if(ae.getSource()==pesqClient){
            int id=Integer.parseInt(entryID.getText());
            boolean scc;
            try{
                ctrCliente ctr=new ctrCliente();
                scc= ctr.encontrar(Integer.parseInt(entryID.getText()));
                if(scc==true){
                    editCliente edt=new editCliente(ctr.getId(), ctr.getNome(), ctr.getSexo(),ctr.getNasci(), ctr.getEstCvl(), ctr.getBi(), ctr.getMorada(), ctr.getRendMsl(), ctr.getCtt());
                
                }
                else
                    JOptionPane.showMessageDialog(null, "Cliente não Encontrado!");
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }finally{painelClientes();}
        }
        if(ae.getSource()==pesqFuncio){
            int id=Integer.parseInt(entryID.getText());
            boolean scc;
            try{
                ctrFuncionario ctr=new ctrFuncionario();
                scc= ctr.encontrar(id);
                if(scc==true){
                    editFuncionario edf=new editFuncionario(ctr.getId(),ctr.getNasc(),ctr.getNome(), ctr.getBi(), ctr.getSexo(), ctr.getSalario(),ctr.getCtt());
                }
                else
                    JOptionPane.showMessageDialog(null, "Funcionário não Encontrado!");
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }finally{painelFuncionario();}
        }
        
        if(ae.getSource()==pesqEmp){
           
            ctrEmprestimo ctrEmp = new ctrEmprestimo();
            boolean scc=ctrEmp.find(Integer.parseInt(entryID.getText()));
            if(scc==true){
                entryEmp.setText(Integer.toString(ctrEmp.empClass.getIdEmpr()));
                entryCli.setText(Integer.toString(ctrEmp.empClass.getIdCliente()));
                entryCred.setText(Float.toString(ctrEmp.empClass.getCredito()));
                entryVf.setText(Float.toString(ctrEmp.empClass.getValorTot()));
                entryVp.setText(Float.toString(ctrEmp.empClass.getVlPago()));  
                entryFuncio.setText(Integer.toString(ctrEmp.empClass.getIdFuncio()));
                vlPres=ctrEmp.empClass.getValorPrest();
            }
            else
                JOptionPane.showMessageDialog(null, "Emprestimo não encontrado");
                
        }
    }
}
