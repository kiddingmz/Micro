/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;


import Controller.ctrFuncionario;
import Controller.ctrRelatorio;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.gc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import model.data_acess_object.clienteDAO;
import model.data_acess_object.relatorioDAO;
import model.value_object.Relatorio;
import projecto_poo.Pessoa;

/**
 *
 * @author kiDDing
 */
public class GeraPdf {
    com.itextpdf.text.Document doc;
    String path;
    PdfPCell  cel24, celBranco1, infoLogo, infoCliente1, infoCliente2, infoCliente3, quebraLinha1,infoEmp1, infoEmp2, infoRecConteudo;
    PdfPCell  infoEmp3, infoEmp4, infoEmp5, infoEmp6, infoEmp1f, infoEmp2f, infoEmp3f, infoEmp4f, infoEmp5f, infoEmp6f, infoEmp7, infoEmp7f;
    PdfPCell  ftableTotal, infoEmpSubTotal, infoEmpSubTotal1, infoEmpSubTotal2, infoEmpSubTotal3, infoEmpTotal, infoEmpTotalSoma;
    Paragraph p;
    PdfPTable tableSuperior, tableSuperiorInfo, tableSuperiorBaixo, tableCentral, tableTotal, infoRec;
    Image image;
    Font fontH1,fontH2,fontH3, fontH4,fontA, fontB;
    private SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    private  GregorianCalendar gc = new GregorianCalendar();
    private Date dataAtuali;
    String dataAtual;
    relatorioDAO pdf;
    public GeraPdf() throws ParseException {
    
    }
    
    public void faturaPdf() throws ParseException {
        path = System.getProperty("user.dir") + "/fatura.pdf";
        doc = new com.itextpdf.text.Document();
        dataAtual = dataFormatada.format(dataFormatada.parse(dataFormatada.format(gc.getTime())));
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(path));
             doc.open();
             
             fontH1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
             fontH2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
             fontH3 = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL);
             fontB = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
             fontH4 = new Font(Font.FontFamily.HELVETICA, 7.5f, Font.BOLD);
            
             //Logo Khuvu
            //ImageData imagedata = ImageDataFactory.create("Imagens2\\money.png");
             image = Image.getInstance("Imagens2\\logoKhuvu.jpeg");
     
            //Adicao do logo e fatura
            tableSuperior = new PdfPTable(4);
            cel24 = new PdfPCell(new Paragraph("FATURA No.  #000034", fontB));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            celBranco1.setBorder(Rectangle.NO_BORDER);
            cel24.setBorder(Rectangle.NO_BORDER);
            
            
            tableSuperior.addCell(image);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(cel24);
            doc.add(tableSuperior); 
            
            //Adicionar info localizacao da empresa
            //Info por baixo da logo
            tableSuperiorInfo = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("KHUVU\nKhuvu Microcredito\nAv. de Moçambique"
                    + "\nRua 0943 Jordao\nCell: +258 948384\n", fontH2)));
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorInfo.addCell(infoLogo);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            doc.add(tableSuperiorInfo); 
            
            //Tabela info cliente
            tableSuperiorBaixo = new PdfPTable(4);
            infoCliente1 = new PdfPCell(new Paragraph(new Phrase("\n\nInformacao do cliente", fontH4)));
            infoCliente2 = new PdfPCell(new Paragraph(new Phrase("\n"
                    + "Nome:  Cao Tem Pele\n"
                    + "Bairro: Southmoz\n", fontH2)));
            
            infoCliente3 = new PdfPCell(new Paragraph(new Phrase("Data: "+dataAtual+"\n", fontH2)));
            quebraLinha1 = new PdfPCell(new Paragraph(new Phrase("\n")));
            infoCliente1.setBorder(Rectangle.NO_BORDER);
            infoCliente2.setBorder(Rectangle.NO_BORDER);
            infoCliente3.setBorder(Rectangle.NO_BORDER);
            quebraLinha1.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorBaixo.addCell(infoCliente1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(infoCliente3);
            tableSuperiorBaixo.addCell(infoCliente2);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            doc.add(tableSuperiorBaixo);
            
            
            //Tabela emprestimo
            tableCentral = new PdfPTable(6);
            //String cabecalho[] = {"Codigo", "Valor do Credito", "Valor Remanescente", "Valor Arrematado", "Cod, Funcionario"};
            /*
            tableCentral.addCell(cabecalho[0]);
            tableCentral.addCell(cabecalho[1]);
            tableCentral.addCell(cabecalho[2]);
            tableCentral.addCell(cabecalho[3]);
            tableCentral.addCell(cabecalho[4]);
            */
            
            infoEmp1 = new PdfPCell(new Paragraph(new Phrase("Codigo", fontH2)));
            infoEmp2 = new PdfPCell(new Paragraph(new Phrase("Valor do Credito", fontH2)));
            infoEmp3 = new PdfPCell(new Paragraph(new Phrase("Valor Remanescente", fontH2)));
            infoEmp4 = new PdfPCell(new Paragraph(new Phrase("Valor Arrematado", fontH2)));
            infoEmp5 = new PdfPCell(new Paragraph(new Phrase("Data do Pedido", fontH2)));
            infoEmp6 = new PdfPCell(new Paragraph(new Phrase("Cod. Funcionario", fontH2)));
            
            tableCentral.addCell(infoEmp1);
            tableCentral.addCell(infoEmp2);
            tableCentral.addCell(infoEmp3);
            tableCentral.addCell(infoEmp4);
            tableCentral.addCell(infoEmp5);
            tableCentral.addCell(infoEmp6);
            
            //Teste Preencher tabela
            
            for(int i = 0; i < 6; i++){
                infoEmp1f = new PdfPCell(new Paragraph(new Phrase("1", fontH2)));
                infoEmp2f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*56586f, fontH2)));
                infoEmp3f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*128785f, fontH2)));
                infoEmp4f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*1188658f, fontH2)));
                infoEmp5f = new PdfPCell(new Paragraph(new Phrase("2021-09-"+(i+1), fontH2)));
                infoEmp6f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*2, fontH2)));

                tableCentral.addCell(infoEmp1f);
                tableCentral.addCell(infoEmp2f);
                tableCentral.addCell(infoEmp3f);
                tableCentral.addCell(infoEmp4f);
                tableCentral.addCell(infoEmp5f);
                tableCentral.addCell(infoEmp6f);

                
            }
            
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            
            doc.add(tableCentral);
            
            //Tabela total
            tableTotal = new PdfPTable(6);
            infoEmpSubTotal = new PdfPCell(new Paragraph(new Phrase("SubTotal: ", fontH2)));
            infoEmpSubTotal1 = new PdfPCell(new Paragraph(new Phrase("412424.0", fontH2)));
            infoEmpSubTotal2 = new PdfPCell(new Paragraph(new Phrase("521521.0 ", fontH2)));
            infoEmpSubTotal3 = new PdfPCell(new Paragraph(new Phrase("534535.0 ", fontH2)));
            infoEmpTotal = new PdfPCell(new Paragraph(new Phrase("Total: ", fontH2)));
            infoEmpTotalSoma = new PdfPCell(new Paragraph(new Phrase("2421412421.0", fontH2)));
            
            
            tableTotal.addCell(infoEmpSubTotal);
            tableTotal.addCell(infoEmpSubTotal1);
            tableTotal.addCell(infoEmpSubTotal2);
            tableTotal.addCell(infoEmpSubTotal3);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
           
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(infoEmpTotal);
            tableTotal.addCell(infoEmpTotalSoma);
            
            doc.add(tableTotal);
            doc.close();
            Desktop.getDesktop().open(new File(path));

        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
    public void relatorioPDF() throws ParseException {
        
        path = System.getProperty("user.dir") + "/Relatorio.pdf";
        doc = new com.itextpdf.text.Document();
        dataAtual = dataFormatada.format(dataFormatada.parse(dataFormatada.format(gc.getTime())));
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(path));
             doc.open();
             
             fontH1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
             fontH2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
             fontH3 = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL);
             fontB = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
             fontH4 = new Font(Font.FontFamily.HELVETICA, 7.5f, Font.BOLD);
            
             //Logo Khuvu
            //ImageData imagedata = ImageDataFactory.create("Imagens2\\money.png");
             image = Image.getInstance("Imagens2\\logoKhuvu.jpeg");
     
            //Adicao do logo e fatura
            tableSuperior = new PdfPTable(4);
            cel24 = new PdfPCell(new Paragraph("RELATORIO No.  #000034", fontB));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            celBranco1.setBorder(Rectangle.NO_BORDER);
            cel24.setBorder(Rectangle.NO_BORDER);
            
            
            tableSuperior.addCell(image);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(cel24);
            doc.add(tableSuperior); 
            
            //Adicionar info localizacao da empresa
            //Info por baixo da logo
            tableSuperiorInfo = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("KHUVU\nKhuvu Microcredito\nAv. de Moçambique"
                    + "\nRua 0943 Jordao\nCell: +258 948384\n", fontH2)));
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorInfo.addCell(infoLogo);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            doc.add(tableSuperiorInfo); 
        
        
            //Tabela info cliente
            tableSuperiorBaixo = new PdfPTable(4);
            infoCliente1 = new PdfPCell(new Paragraph(new Phrase("\n\nInformacao do funcionario", fontH4)));
            infoCliente2 = new PdfPCell(new Paragraph(new Phrase("\n"
                    + "Codigo:  "+Index.Adm+"\n\n", fontH2)));
            
            infoCliente3 = new PdfPCell(new Paragraph(new Phrase("Data: "+dataAtual+"\n", fontH2)));
            quebraLinha1 = new PdfPCell(new Paragraph(new Phrase("\n")));
            infoCliente1.setBorder(Rectangle.NO_BORDER);
            infoCliente2.setBorder(Rectangle.NO_BORDER);
            infoCliente3.setBorder(Rectangle.NO_BORDER);
            quebraLinha1.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorBaixo.addCell(infoCliente1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(infoCliente3);
            tableSuperiorBaixo.addCell(infoCliente2);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            doc.add(tableSuperiorBaixo);
            
            
            //Tabela emprestimo
            tableCentral = new PdfPTable(7);
            //String cabecalho[] = {"Codigo", "Valor do Credito", "Valor Remanescente", "Valor Arrematado", "Cod, Funcionario"};
            /*
            tableCentral.addCell(cabecalho[0]);
            tableCentral.addCell(cabecalho[1]);
            tableCentral.addCell(cabecalho[2]);
            tableCentral.addCell(cabecalho[3]);
            tableCentral.addCell(cabecalho[4]);
            */
            
            infoEmp1 = new PdfPCell(new Paragraph(new Phrase("Codigo", fontH2)));
            infoEmp2 = new PdfPCell(new Paragraph(new Phrase("Codigo do Emp.", fontH2)));
            infoEmp3 = new PdfPCell(new Paragraph(new Phrase("Codigo do Cliente", fontH2)));
            infoEmp4 = new PdfPCell(new Paragraph(new Phrase("Nome", fontH2)));
            infoEmp5 = new PdfPCell(new Paragraph(new Phrase("Prestação", fontH2)));
            infoEmp6 = new PdfPCell(new Paragraph(new Phrase("Cod. Funcionario", fontH2)));
            infoEmp7 = new PdfPCell(new Paragraph(new Phrase("Data do Pedido", fontH2)));
            
            tableCentral.addCell(infoEmp1);
            tableCentral.addCell(infoEmp2);
            tableCentral.addCell(infoEmp3);
            tableCentral.addCell(infoEmp4);
            tableCentral.addCell(infoEmp5);
            tableCentral.addCell(infoEmp6);
            tableCentral.addCell(infoEmp7);
            
            ctrRelatorio ctrCli = new ctrRelatorio();
            ArrayList<Relatorio> cle = ctrCli.listarT();
            
            //Teste Preencher tabela
            double soma = 0;
            for(int i = 0; i < cle.size(); i++){
                infoEmp1f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getIdre(), fontH2)));
                infoEmp2f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getIdEmp(), fontH2)));
                infoEmp3f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getIdCli(), fontH2)));
                infoEmp4f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getNome(), fontH2)));
                infoEmp5f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getVl(), fontH2)));
                infoEmp6f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getIdFunc(), fontH2)));
                infoEmp7f = new PdfPCell(new Paragraph(new Phrase(""+cle.get(i).getData(), fontH2)));

                tableCentral.addCell(infoEmp1f);
                tableCentral.addCell(infoEmp2f);
                tableCentral.addCell(infoEmp3f);
                tableCentral.addCell(infoEmp4f);
                tableCentral.addCell(infoEmp5f);
                tableCentral.addCell(infoEmp6f);
                tableCentral.addCell(infoEmp7f);
                soma += +cle.get(i).getVl();
                
            }
            
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            
            doc.add(tableCentral);
            
            //Tabela total
            tableTotal = new PdfPTable(6);
            infoEmpSubTotal = new PdfPCell();
            infoEmpSubTotal1 = new PdfPCell();
            infoEmpSubTotal2 = new PdfPCell();
            infoEmpSubTotal3 = new PdfPCell();
            infoEmpTotal = new PdfPCell(new Paragraph(new Phrase("Total: ", fontH2)));
            
            infoEmpTotalSoma = new PdfPCell(new Paragraph(new Phrase(String.format("%.2f",soma), fontH2)));
            
            
            tableTotal.addCell(infoEmpSubTotal);
            tableTotal.addCell(infoEmpSubTotal1);
            tableTotal.addCell(infoEmpSubTotal2);
            tableTotal.addCell(infoEmpSubTotal3);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
           
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(infoEmpTotal);
            tableTotal.addCell(infoEmpTotalSoma);
            
            doc.add(tableTotal);
            doc.close();
            Desktop.getDesktop().open(new File(path));

        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
    public void historicoPDF() throws ParseException {
        dataAtual = dataFormatada.format(dataFormatada.parse(dataFormatada.format(gc.getTime())));
        path = System.getProperty("user.dir") + "/Historico.pdf";
        doc = new com.itextpdf.text.Document();
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(path));
             doc.open();
             
             fontH1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
             fontH2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
             fontH3 = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL);
             fontB = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
             fontH4 = new Font(Font.FontFamily.HELVETICA, 7.5f, Font.BOLD);
            
             //Logo Khuvu
            //ImageData imagedata = ImageDataFactory.create("Imagens2\\money.png");
             image = Image.getInstance("Imagens2\\logoKhuvu.jpeg");
     
            //Adicao do logo e fatura
            tableSuperior = new PdfPTable(4);
            cel24 = new PdfPCell(new Paragraph("HISTORICO No.  #000034", fontB));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            celBranco1.setBorder(Rectangle.NO_BORDER);
            cel24.setBorder(Rectangle.NO_BORDER);
            
            
            tableSuperior.addCell(image);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(cel24);
            doc.add(tableSuperior); 
            
            //Adicionar info localizacao da empresa
            //Info por baixo da logo
            tableSuperiorInfo = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("KHUVU\nKhuvu Microcredito\nAv. de Moçambique"
                    + "\nRua 0943 Jordao\nCell: +258 948384\n", fontH2)));
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorInfo.addCell(infoLogo);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            tableSuperiorInfo.addCell(celBranco1);
            doc.add(tableSuperiorInfo); 
            
            //Tabela info cliente
            tableSuperiorBaixo = new PdfPTable(4);
            infoCliente1 = new PdfPCell(new Paragraph(new Phrase("\n\nInformacao do cliente", fontH4)));
            infoCliente2 = new PdfPCell(new Paragraph(new Phrase("\n"
                    + "Nome:  Cao Tem Pele\n"
                    + "Bairro: Southmoz\n", fontH2)));
            
            infoCliente3 = new PdfPCell(new Paragraph(new Phrase("Data: "+dataAtual+"\n", fontH2)));
            quebraLinha1 = new PdfPCell(new Paragraph(new Phrase("\n")));
            infoCliente1.setBorder(Rectangle.NO_BORDER);
            infoCliente2.setBorder(Rectangle.NO_BORDER);
            infoCliente3.setBorder(Rectangle.NO_BORDER);
            quebraLinha1.setBorder(Rectangle.NO_BORDER);
            
            tableSuperiorBaixo.addCell(infoCliente1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(infoCliente3);
            tableSuperiorBaixo.addCell(infoCliente2);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(celBranco1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            tableSuperiorBaixo.addCell(quebraLinha1);
            doc.add(tableSuperiorBaixo);
            
            
            //Tabela emprestimo
            tableCentral = new PdfPTable(6);
            //String cabecalho[] = {"Codigo", "Valor do Credito", "Valor Remanescente", "Valor Arrematado", "Cod, Funcionario"};
            /*
            tableCentral.addCell(cabecalho[0]);
            tableCentral.addCell(cabecalho[1]);
            tableCentral.addCell(cabecalho[2]);
            tableCentral.addCell(cabecalho[3]);
            tableCentral.addCell(cabecalho[4]);
            */
            
            infoEmp1 = new PdfPCell(new Paragraph(new Phrase("Codigo", fontH2)));
            infoEmp2 = new PdfPCell(new Paragraph(new Phrase("Valor do Credito", fontH2)));
            infoEmp3 = new PdfPCell(new Paragraph(new Phrase("Valor Remanescente", fontH2)));
            infoEmp4 = new PdfPCell(new Paragraph(new Phrase("Valor Arrematado", fontH2)));
            infoEmp5 = new PdfPCell(new Paragraph(new Phrase("Data do Pedido", fontH2)));
            infoEmp6 = new PdfPCell(new Paragraph(new Phrase("Cod. Funcionario", fontH2)));
            
            tableCentral.addCell(infoEmp1);
            tableCentral.addCell(infoEmp2);
            tableCentral.addCell(infoEmp3);
            tableCentral.addCell(infoEmp4);
            tableCentral.addCell(infoEmp5);
            tableCentral.addCell(infoEmp6);
            
            //Teste Preencher tabela
            
            for(int i = 0; i < 6; i++){
                infoEmp1f = new PdfPCell(new Paragraph(new Phrase("1", fontH2)));
                infoEmp2f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*56586f, fontH2)));
                infoEmp3f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*128785f, fontH2)));
                infoEmp4f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*1188658f, fontH2)));
                infoEmp5f = new PdfPCell(new Paragraph(new Phrase("2021-09-"+(i+1), fontH2)));
                infoEmp6f = new PdfPCell(new Paragraph(new Phrase(""+(i+1)*2, fontH2)));

                tableCentral.addCell(infoEmp1f);
                tableCentral.addCell(infoEmp2f);
                tableCentral.addCell(infoEmp3f);
                tableCentral.addCell(infoEmp4f);
                tableCentral.addCell(infoEmp5f);
                tableCentral.addCell(infoEmp6f);

                
            }
            
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            tableCentral.addCell(quebraLinha1);
            
            doc.add(tableCentral);
            
            //Tabela total
            tableTotal = new PdfPTable(6);
            infoEmpSubTotal = new PdfPCell(new Paragraph(new Phrase("SubTotal: ", fontH2)));
            infoEmpSubTotal1 = new PdfPCell(new Paragraph(new Phrase("412424.0", fontH2)));
            infoEmpSubTotal2 = new PdfPCell(new Paragraph(new Phrase("521521.0 ", fontH2)));
            infoEmpSubTotal3 = new PdfPCell(new Paragraph(new Phrase("534535.0 ", fontH2)));
            infoEmpTotal = new PdfPCell(new Paragraph(new Phrase("Total: ", fontH2)));
            infoEmpTotalSoma = new PdfPCell(new Paragraph(new Phrase("2421412421.0", fontH2)));
            
            
            tableTotal.addCell(infoEmpSubTotal);
            tableTotal.addCell(infoEmpSubTotal1);
            tableTotal.addCell(infoEmpSubTotal2);
            tableTotal.addCell(infoEmpSubTotal3);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
            tableTotal.addCell(quebraLinha1);
           
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(celBranco1);
            tableTotal.addCell(infoEmpTotal);
            tableTotal.addCell(infoEmpTotalSoma);
            
            doc.add(tableTotal);
            doc.close();
            Desktop.getDesktop().open(new File(path));

        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    public static void main(String[] args) throws ParseException {
        new GeraPdf();
    }
    
    public void reciboPdf(String valor, String id) throws ParseException {
        
        clienteDAO c = new clienteDAO();
        model.value_object.Cliente dados = c.validaID(Integer.parseInt(id));
        
        dataAtual = dataFormatada.format(dataFormatada.parse(dataFormatada.format(gc.getTime())));
        path = System.getProperty("user.dir") + "/recibo.pdf";
        doc = new com.itextpdf.text.Document();
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(path));
             doc.open();
            
            fontH1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            fontH2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
            fontH3 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            fontB = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
            fontA = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
             
             //Logo Khuvu
             image = Image.getInstance("Imagens2\\logoKhuvu.jpeg");
             
            //Adicao do logo e info da empresa
            tableSuperior = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("KHUVU\nKhuvu Microcredito\nAv. de Moçambique"
                    + "\nRua 0943 Jordao\nCell: +258 948384\n", fontH2)));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            celBranco1.setBorder(Rectangle.NO_BORDER);
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperior.addCell(image);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(infoLogo);
            doc.add(tableSuperior); 
            
            //Titulo Recibo
            p = new Paragraph(new Phrase("RECIBO\n\n", fontH1));
            p.setAlignment(1);
            doc.add(p);
            
            //Info nr recibo e quantia
            tableSuperior = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase(valor+" MT",fontA)));
            cel24 = new PdfPCell(new Paragraph("RECIBO No.  #000034", fontB));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            quebraLinha1 = new PdfPCell(new Paragraph(new Phrase("\n")));
            quebraLinha1.setBorder(Rectangle.NO_BORDER);
            celBranco1.setBorder(Rectangle.NO_BORDER);
            cel24.setBorder(Rectangle.NO_BORDER);
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperior.addCell(infoLogo);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(cel24);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            doc.add(tableSuperior); 
            
            //Variaveis de teste info
            String nomeCliente = dados.getNome();
            String quantia = "";
            String referente = "";
          
            //Info do recibo
            infoRec = new PdfPTable(1);
            infoRecConteudo = new PdfPCell(new Paragraph(new Phrase(
                    "\n\nRecebemos do (a) Exmo Sr.(a)   "+nomeCliente.toUpperCase()+"  a quantia de "+valor+""
                     + "\nreferente a      "+referente.toUpperCase()+"    que passamos o presente recibo."
                     + "\n\nMetodo de pagamento usado ........."
                     + ".................\n\n\n"
                    , fontH3))
            ); 
            
            infoRec.addCell(infoRecConteudo);
            infoRec.addCell(quebraLinha1);
            doc.add(infoRec);
            
            //Data e assinatura
            infoRec = new PdfPTable(2);
            infoEmp1 = new PdfPCell(new Paragraph(new Phrase("aos "+dataAtual+"\n\n", fontH2)));
            infoEmp2 = new PdfPCell(new Paragraph(new Phrase("Assinatura e Carimbo\n"
                    + "\n\n.........................................", fontH2)));
            infoEmp3 = new PdfPCell(new Paragraph(new Phrase("Assinatura Funcionario\n"
                    + "\n\n.........................................", fontH2)));
            infoEmp1.setBorder(Rectangle.NO_BORDER);
            infoEmp2.setBorder(Rectangle.NO_BORDER);
            infoEmp3.setBorder(Rectangle.NO_BORDER);
            
            infoRec.addCell(celBranco1);
            infoRec.addCell(infoEmp1);
            infoRec.addCell(infoEmp3);
            infoRec.addCell(infoEmp2);
            doc.add(infoRec);
            
            //Separador
            p = new Paragraph(new Phrase("\n- - - - - - - - - - - - - - - - - - - - - - - - - "
                    + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -", fontA));
            p.setAlignment(0);
            doc.add(p);
            
             //Logo Khuvu
             image = Image.getInstance("Imagens2\\logoKhuvu.jpeg");
             
            //Adicao do logo e info da empresa
            tableSuperior = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("KHUVU\nKhuvu Microcredito\nAv. de Moçambique"
                    + "\nRua 0943 Jordao\nCell: +258 948384\n", fontH2)));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            celBranco1.setBorder(Rectangle.NO_BORDER);
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(image);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(infoLogo);
            doc.add(tableSuperior); 
            
            //Titulo Recibo
            p = new Paragraph(new Phrase("RECIBO\n\n", fontH1));
            p.setAlignment(1);
            doc.add(p);
            
            //Info nr recibo e quantia
            tableSuperior = new PdfPTable(4);
            infoLogo = new PdfPCell(new Paragraph(new Phrase("2000 MT",fontA)));
            cel24 = new PdfPCell(new Paragraph("RECIBO No.  #000034", fontB));
            celBranco1 = new PdfPCell(new Paragraph("", fontH2));
            quebraLinha1 = new PdfPCell(new Paragraph(new Phrase("\n")));
            quebraLinha1.setBorder(Rectangle.NO_BORDER);
            celBranco1.setBorder(Rectangle.NO_BORDER);
            cel24.setBorder(Rectangle.NO_BORDER);
            infoLogo.setBorder(Rectangle.NO_BORDER);
            
            tableSuperior.addCell(infoLogo);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(celBranco1);
            tableSuperior.addCell(cel24);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            tableSuperior.addCell(quebraLinha1);
            doc.add(tableSuperior); 
            
            //Info do recibo
            infoRecConteudo = new PdfPCell(new Paragraph(new Phrase(
                    "\n\nRecebemos do (a) Exmo Sr.(a)   "+nomeCliente.toUpperCase()+"  a quantia de "+valor+""
                     + "\nreferente a      "+referente.toUpperCase()+"    que passamos o presente recibo."
                     + "\n\nMetodo de pagamento usado ........."
                     + ".................\n\n\n"
                    , fontH3))
            ); 
            
            infoRec.addCell(infoRecConteudo);
            infoRec.addCell(quebraLinha1);
            doc.add(infoRec);
           
            //Data e assinatura
            infoRec = new PdfPTable(2);
            infoEmp1 = new PdfPCell(new Paragraph(new Phrase("aos "+dataAtual+"\n\n", fontH2)));
            infoEmp2 = new PdfPCell(new Paragraph(new Phrase("Assinatura e Carimbo\n"
                    + "\n\n.........................................", fontH2)));
            infoEmp3 = new PdfPCell(new Paragraph(new Phrase("Assinatura Funcionario\n"
                    + "\n\n.........................................", fontH2)));
            infoEmp1.setBorder(Rectangle.NO_BORDER);
            infoEmp2.setBorder(Rectangle.NO_BORDER);
            infoEmp3.setBorder(Rectangle.NO_BORDER);
            
            infoRec.addCell(celBranco1);
            infoRec.addCell(infoEmp1);
            infoRec.addCell(infoEmp3);
            infoRec.addCell(infoEmp2);
            doc.add(infoRec);
            
            doc.close();
            Desktop.getDesktop().open(new File(path));

        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
   
}
