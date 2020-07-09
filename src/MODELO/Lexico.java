/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROL.KaijuError;
import CONTROL.StrikerToken;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author alber
 */
public class Lexico {
    JTable jTE;
    JTable jTT;
    List<StrikerToken> tokens ;
    List<KaijuError> errores ;
    JTextArea jta;
    int linea;

    @Override
    public String toString() {
        return tokens.toString();
    }
    
    public Lexico(JTextArea jta, JTable jTE, JTable jTT){
        this.jTE=jTE;
        this.jTT=jTT;
        this.jta=jta;
    }
    
    public void lexicoRun(){
        
        String cadena = jta.getText().toLowerCase();
        
        String nombreArchivo = "Matriz2.xls";
        String rutaArchivo = "C:\\Ficheros-Excel\\" + nombreArchivo;
        DefaultTableModel modeloTokens = (DefaultTableModel) jTT.getModel();
        DefaultTableModel modeloErrores = (DefaultTableModel) jTE.getModel();
        try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
            // leer archivo excel
            Workbook worbook = new HSSFWorkbook(file);
            //obtener la hoja que se va leer
            Sheet sheet = worbook.getSheetAt(0);
            linea = 1;
            String tipoLexico= "Lexico";
            String tipoSintactico= "Sintactico";
            int estado = 0;
            int estadoAnt = 0;
            int errorNo = 0;
            int posicion=0;
            int retorno=0;
            char caracter;
            int celda=0;
            boolean com=false;
            String[] palabrasRes;
            String[] operadoresIdent;
            tokens = new LinkedList();
            errores = new LinkedList();
            String lexema = "";
            String lexema3 = "";
            String lexema4 = "+";
            String lexema5 = "";
            String descripcion="";
            
            palabrasRes=palabrasReservadas();
            operadoresIdent=operadoresId();
            boolean bandera2=true;
            boolean bandera3=true;
            boolean bandera4=true;
            boolean bandera5=true;
            boolean banderaparent=false;
            while(posicion!=cadena.length()){
                bandera2=true;
                caracter=cadena.charAt(posicion);
                if(caracter=='a' || caracter=='c' || caracter=='d' || caracter=='f' 
                        || caracter>='A'&&caracter<='D' || caracter=='F'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(1);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='b'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(2);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='e' || caracter=='E'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(3);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='j'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(4);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter>='g'&&caracter<='i' || caracter>='k'&&caracter<='w'
                         || caracter=='y' || caracter=='z' ||caracter>='G'&&caracter<='Z'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(5);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='x'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(6);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='0'){
                    
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(7);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='1'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(8);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='2'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(9);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='3'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(10);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='4'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(11);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='5'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(12);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='6'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(13);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='7'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(14);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='8'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(15);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='9'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(16);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='\n'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(17);
                    celda=(int)cell.getNumericCellValue();
                    com=false;
                }
                else if(caracter==' '){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(18);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='\t'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(19);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='.'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(20);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='_'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(21);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='('){
                    boolean bandera1 = true;
                    boolean bandera31 = true;
                    int posicion2 = posicion+1;
                    int cont=1;
                    int linea2 = linea;
                    String lexema2="(";
                    int celda2=0;
                    int estado2=4;
                    if(banderaparent&&!com){
                        if(posicion2<cadena.length()){
                            
                            while (bandera1&&posicion2<cadena.length()&&bandera31){
                                switch (cadena.charAt(posicion2)) {
                                    case '0':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(7);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '1':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(8);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '2':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(9);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '3':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(10);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '4':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(11);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '5':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(12);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '6':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(13);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '7':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(14);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '8':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(15);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '9':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(16);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '.':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(20);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case '+':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(35);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case 'e':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(3);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case 'E':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(3);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case 'j':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(4);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    case ')':
                                        {
                                            Row row = sheet.getRow(estado2+1);
                                            Cell cell = row.getCell(23);
                                            celda2=(int)cell.getNumericCellValue();
                                            break;
                                        }
                                    default:
                                        celda2=900;
                                        break;
                                }
                                estado2=celda2;
                                if(estado2==-38){
                                    tokens.add(new StrikerToken(estado2,linea2,lexema2));
                                    modeloTokens.addRow(new Object[]{linea2,estado2,lexema2});
                                    jTT.setModel(modeloTokens);
                                    celda=0;
                                    estado2=0;
                                    lexema="";
                                    bandera1=false;
                                    banderaparent=false;
                                }
                                else if(estado2<0){
                                    lexema2+=cadena.charAt(posicion2);
                                    tokens.add(new StrikerToken(estado2,linea2,lexema2));
                                    modeloTokens.addRow(new Object[]{linea2,estado2,lexema2});
                                    jTT.setModel(modeloTokens);
                                    celda=0;
                                    estado2=0;
                                    posicion=posicion2;
                                    lexema="";
                                    bandera1=false;
                                    banderaparent=false;
                                }
                                else if(estado2>=900){
                                    lexema="(";
                                    estado2=-38;
                                    tokens.add(new StrikerToken(estado2,linea2,lexema));
                                    modeloTokens.addRow(new Object[]{linea2,estado2,lexema});
                                    jTT.setModel(modeloTokens);
                                    celda=0;
                                    estado2=0;
                                    lexema="";
                                    bandera1=false;
                                    banderaparent=false;
                                }
                                else{
                                    lexema2+=cadena.charAt(posicion2);
                                    cont++;
                                    posicion2++;
                                    if(posicion2==cadena.length()){
                                        lexema="(";
                                        estado2=-38;
                                        tokens.add(new StrikerToken(estado2,linea2,lexema));
                                        modeloTokens.addRow(new Object[]{linea2,estado2,lexema});
                                        jTT.setModel(modeloTokens);
                                        celda=0;
                                        estado2=0;
                                        lexema="";
                                        bandera1=false;
                                        banderaparent=false;
                                    }
                                }
                            }
                            if(estado2!=0){
                                lexema="(";
                                estado2=-38;
                                tokens.add(new StrikerToken(estado2,linea2,lexema));
                                modeloTokens.addRow(new Object[]{linea2,estado2,lexema});
                                jTT.setModel(modeloTokens);
                                celda=0;
                                lexema="";
                                banderaparent=false;
                            }
                        }
                    }
                    else{
                        Row row = sheet.getRow(estado+1);
                        Cell cell = row.getCell(22);
                        celda=(int)cell.getNumericCellValue();
                        
                            banderaparent=true;
                        
                        
                    }
                }
                else if(caracter==')'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(23);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='['){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(24);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter==']'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(25);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='{'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(26);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='}'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(27);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='%'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(28);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='&'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(29);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='|'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(30);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='!'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(31);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='/'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(32);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='^'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(33);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='#'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(34);
                    celda=(int)cell.getNumericCellValue();
                    com=true;
                }
                else if(caracter=='+'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(35);
                    celda=(int)cell.getNumericCellValue();
                    if(estadoAnt!=28){
                        bandera3=false;
                    }
                    
                }
                else if(caracter=='-'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(36);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='*'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(37);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='='){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(38);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='<'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(39);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='>'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(40);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter==';'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(41);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter==','){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(42);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='\''){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(43);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='"'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(44);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter==':'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(46);
                    celda=(int)cell.getNumericCellValue();
                }
                else if(caracter=='ñ'){
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(5);
                    celda=(int)cell.getNumericCellValue();
                }
                else{
                    Row row = sheet.getRow(estado+1);
                    Cell cell = row.getCell(45);
                    celda=(int)cell.getNumericCellValue();
                }
                estado=celda;
                if(estado<0){
                    if(estado==-20 || estado==-45 || estado==-21 || estado==-46 ||
                            estado==-47 || estado==-48 || estado==-49 || estado==-50 || estado==-51 ||
                            estado==-2 || estado==-3 || estado==-12 || estado==-8 ||
                            estado==-9 || estado==-78 || estado==-77 || estado==-79){
                        lexema+=caracter;
                        tokens.add(new StrikerToken(estado,linea,lexema));
                        if(estado!=-2 && estado!=-3){
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                        }
                        
                        if (caracter=='\n'){
                            linea++;
                            bandera2=false;
                        }
                        if (caracter=='('){
                                    banderaparent=true;
                                }
                        estado=0;
                        estadoAnt = estado;
                        lexema="";
                        lexema3="";
                        lexema5="";
                        bandera3=true;
                        bandera4=true;
                        posicion++;
                        
                    }
                    else if (estado!=-11){
                        boolean encontrado=false;
                        for (int i = 0; i < palabrasRes.length; i++) {
                            if(lexema.equals(palabrasRes[i])){
                                switch(i){
                                    case 0:
                                        estado=-52;
                                            break;
                                    case 1:
                                        estado=-53;
                                            break;
                                    case 2:
                                        estado=-54;
                                            break;
                                    case 3:
                                        estado=-55;
                                            break;
                                    case 4:
                                        estado=-56;
                                            break;
                                    case 5:
                                        estado=-57;
                                            break;
                                    case 6:
                                        estado=-58;
                                            break;
                                    case 7:
                                        estado=-59;
                                            break;
                                    case 8:
                                        estado=-60;
                                            break;
                                    case 9:
                                        estado=-61;
                                            break;
                                    case 10:
                                        estado=-62;
                                            break;
                                    case 11:
                                        estado=-63;
                                            break;
                                    case 12:
                                        estado=-64;
                                            break;
                                    case 13:
                                        estado=-65;
                                            break;
                                    case 14:
                                        estado=-66;
                                            break;
                                    case 15:
                                        estado=-67;
                                            break;
                                    case 16:
                                        estado=-68;
                                            break;
                                    case 17:
                                        estado=-69;
                                            break;
                                    case 18:
                                        estado=-70;
                                            break;
                                    case 19:
                                        estado=-71;
                                            break;
                                    case 20:
                                        estado=-72;
                                            break;
                                    case 21:
                                        estado=-73;
                                            break;
                                    case 22:
                                        estado=-74;
                                            break;
                                    case 23:
                                        estado=-80;
                                        break;
                                        case 24:
                                        estado=-81;
                                        break;
                                        case 25:
                                        estado=-82;
                                        break;
                                        case 26:
                                        estado=-83;
                                        break;
                                        case 27:
                                        estado=-84;
                                        break;
                                        case 28:
                                        estado=-85;
                                        break;
                                        case 29:
                                        estado=-86;
                                        break;
                                        case 30:
                                        estado=-87;
                                        break;
                                        case 31:
                                        estado=-88;
                                        break;
                                        case 32:
                                        estado=-89;
                                        break;
                                        case 33:
                                        estado=-90;
                                        break;
                                        case 34:
                                        estado=-91;
                                        break;
                                        case 35:
                                        estado=-92;
                                        break;
                                        case 36:
                                        estado=-93;
                                        break;
                                        case 37:
                                        estado=-94;
                                        break;
                                        case 38:
                                        estado=-95;
                                        break;
                                        case 39:
                                        estado=-96;
                                        break;
                                        case 40:
                                        estado=-97;
                                        break;
                                }
                                encontrado=true;
                            }
                        }
                        if(encontrado==true){
                            tokens.add(new StrikerToken(estado,linea,lexema));
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                            if (caracter=='\n'){
                                bandera2=false;
                            }
                            if (caracter=='('){
                                    banderaparent=true;
                                }
                            estado=0;
                            estadoAnt = estado;
                            lexema="";
                            lexema3="";
                            lexema5="";
                            bandera3=true;
                            bandera4=true;
                        }
                        else{
                            boolean encontrado2=false;
                            for (int i = 0; i < operadoresIdent.length; i++) {
                                if(lexema.equals(operadoresIdent[i])){
                                    switch(i){
                                        case 0:
                                            estado=-75;
                                            break;
                                        case 1:
                                            estado=-76;
                                            break;
                                    }
                                    encontrado2=true;
                                }
                            }
                            if(encontrado2==true){
                                tokens.add(new StrikerToken(estado,linea,lexema));
                                modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                jTT.setModel(modeloTokens);
                                if (caracter=='\n'){
                                    bandera2=false;
                                }
                                if (caracter=='('){
                                    banderaparent=true;
                                }
                                estado=0;
                                estadoAnt = estado;
                                lexema="";
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                            }
                            else{
                                tokens.add(new StrikerToken(estado,linea,lexema));
                                modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                jTT.setModel(modeloTokens);
                                if (caracter=='\n'){
                                    bandera2=false;
                                }
                                if (caracter=='('){
                                    banderaparent=true;
                                }
                                estado=0;
                                estadoAnt = estado;
                                lexema="";
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                
                            }
                        }
                    }
                    else if (estadoAnt==29){
                        
                        tokens.add(new StrikerToken(estado,linea,lexema));
                        modeloTokens.addRow(new Object[]{linea,estado,lexema});
                        jTT.setModel(modeloTokens);
                        if (caracter=='\n'){
                            bandera2=false;
                        }
                        if (caracter=='('){
                                    banderaparent=true;
                                }
                        lexema="";
                        estado=0;
                        estadoAnt = estado;
                        lexema3="";
                        lexema5="";
                        bandera3=true;
                        bandera4=true;
                    }
                }
                else if(estado>=900){
                    if(estado<950){
                        lexema+=caracter;
                        switch (estado){
                            case 900:
                                errorNo=900;
                                descripcion="Se esperaba un número del 0-1";
                                break;
                            case 901:
                                errorNo=901;
                                descripcion="Se esperaba un número del 0-9 o un menos (-)";
                                break;
                            case 902:
                                errorNo=902;
                                descripcion="Se esperaba cualquier número del 0-9";
                                break;
                            case 903:
                                errorNo=903;
                                descripcion="Se esperaba un número del 0-9, una letra de la a-f o de la A-F";
                                break;
                            case 904:
                                errorNo=904;
                                descripcion="Se esperaba un número del 0-7";
                                break;
                            case 905:
                                errorNo=905;
                                descripcion="Se esperaba un número del 0-9 o una j";
                                break;
                            case 906:
                                errorNo=906;
                                descripcion="Se esperaba un número del 0-9, un punto (.) o un e ó E";
                                break;
                            case 907:
                                errorNo=907;
                                descripcion="Se esperaba un número del 0-9, un e ó E";
                                break;
                            case 908:
                                errorNo=908;
                                descripcion="Se esperaba un número del 0-9, o un ) (Paréntesis cerrado)";
                                break;
                            case 909:
                                errorNo=909;
                                descripcion="Se esperaba un número del 0-9, un + o un .(Punto)";
                                break;
                            case 910:
                                errorNo=910;
                                descripcion="Se esperaba un número del 0-9 o un +";
                                break;
                            case 911:
                                errorNo=911;
                                descripcion="Se esperaba un número del 0-9, una j o un .(punto)";
                                break;
                            case 912:
                                errorNo=912;
                                descripcion="Se esperaba una apostrofe u otra cosa excepto salto de línea";
                                break;
                            case 913:
                                errorNo=913;
                                descripcion="Se esperaba una apostrofe";
                                break;
                            case 914:
                                errorNo=914;
                                descripcion="Se esperaba un espacio (\\s), un salto de línea (\\n), un guion bajo (_), numero del 0 – 9, del a-z, de la A-Z, un paréntesis abierto ((), un paréntesis cerrado ()), un corchete abierto ([), un corchete cerrado (]), una llave abierta ({), una llave cerrada (}), un mas (+), un menos (-), un asterisco (*), una barra (/), un porcentaje (%), un símbolo de exclamación (!), una &, una barra (|), un símbolo de menor que (<), un símbolo de mayor que (>), un símbolo de gorrito (^), un símbolo igual (=), un punto y coma (;), una coma (,), un símbolo de gato (#), una apostrofe (‘) ó unas comillas (“).";
                                posicion++;
                                break;
                            case 915:
                                errorNo=915;
                                descripcion="Se esperaba un mas, una J o un punto";
                                break;
                            case 916:
                                errorNo=916;
                                descripcion="Se esperaba un punto o un numero";
                                break;
                            case 917:
                                errorNo=917;
                                descripcion="Se esperaba un + (Mas)";
                                break;
                            case 918:
                                errorNo=918;
                                descripcion="Se esperaba una j";
                                break;
                        }
                        errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema));
                        modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema});
                        jTE.setModel(modeloErrores);
                        estado=0;
                        estadoAnt = estado;
                        lexema="";
                        lexema3="";
                        lexema5="";
                        bandera3=true;
                        bandera4=true;
                    }
                    else{
                        char[] vector = lexema3.toCharArray();
                        String punto = ".";
                        String folte = "e";
                        String foltE = "E";
                        int estT = -4;
                        for (int i = 0; i < lexema3.length(); i++) {
                            String letra = String.valueOf(vector[i]);
                            if(punto.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(folte.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(foltE.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                        }
                        switch(estado){
                            case 950:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 951:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-4,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-4,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 952:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=902;
                                descripcion="Se esperaba cualquier número del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 953:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-10,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-10,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 954:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 955:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-7,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-7,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 956:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 957:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            
                        }
                        if(caracter=='\n'){
                            bandera2=false;
                        }
                    }
                }
                else if(estado==0){
                    posicion++;
                    estadoAnt = estado;
                }
                else{
                    if(caracter!='('||com){
                        lexema+=caracter;
                        if(bandera3){
                            lexema3+=caracter;
                        }
                        if(bandera3==false && bandera4==false ){
                            lexema5+=caracter;
                        }
                        if(bandera3==false){
                            bandera4=false;
                        }
                        posicion++;
                        estadoAnt = estado;
                    }
                        
                }
                if(caracter=='\n'&&bandera2==true){
                    linea++;
                }
            }
            if(estado!=0){
                Row row = sheet.getRow(estado+1);
                Cell cell = row.getCell(45);
                celda=(int)cell.getNumericCellValue();
                estado=celda;
                if(estado<0){
                    if(estado==-20 || estado==-45 || estado==-21 || estado==-46 ||
                            estado==-47 || estado==-48 || estado==-49 || estado==-50 || estado==-51 ||
                            estado==-2 || estado==-3 || estado==-12 || estado==-8 ||
                            estado==-9 || estado==-78 || estado==-77 || estado==-79){
                        tokens.add(new StrikerToken(estado,linea,lexema));
                        if(estado!=-2 && estado!=-3){
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                        }
                        
                    }
                    else if (estado!=-11){
                        boolean encontrado=false;
                        
                        for (int i = 0; i < palabrasRes.length; i++) {
                            if(lexema.equals(palabrasRes[i])){
                                switch(i){
                                    case 0:
                                        estado=-52;
                                            break;
                                    case 1:
                                        estado=-53;
                                            break;
                                    case 2:
                                        estado=-54;
                                            break;
                                    case 3:
                                        estado=-55;
                                            break;
                                    case 4:
                                        estado=-56;
                                            break;
                                    case 5:
                                        estado=-57;
                                            break;
                                    case 6:
                                        estado=-58;
                                            break;
                                    case 7:
                                        estado=-59;
                                            break;
                                    case 8:
                                        estado=-60;
                                            break;
                                    case 9:
                                        estado=-61;
                                            break;
                                    case 10:
                                        estado=-62;
                                            break;
                                    case 11:
                                        estado=-63;
                                            break;
                                    case 12:
                                        estado=-64;
                                            break;
                                    case 13:
                                        estado=-65;
                                            break;
                                    case 14:
                                        estado=-66;
                                            break;
                                    case 15:
                                        estado=-67;
                                            break;
                                    case 16:
                                        estado=-68;
                                            break;
                                    case 17:
                                        estado=-69;
                                            break;
                                    case 18:
                                        estado=-70;
                                            break;
                                    case 19:
                                        estado=-71;
                                            break;
                                    case 20:
                                        estado=-72;
                                            break;
                                    case 21:
                                        estado=-73;
                                            break;
                                    case 22:
                                        estado=-74;
                                            break;
                                    case 23:
                                        estado=-80;
                                        break;
                                        case 24:
                                        estado=-81;
                                        break;
                                        case 25:
                                        estado=-82;
                                        break;
                                        case 26:
                                        estado=-83;
                                        break;
                                        case 27:
                                        estado=-84;
                                        break;
                                        case 28:
                                        estado=-85;
                                        break;
                                        case 29:
                                        estado=-86;
                                        break;
                                        case 30:
                                        estado=-87;
                                        break;
                                        case 31:
                                        estado=-88;
                                        break;
                                        case 32:
                                        estado=-89;
                                        break;
                                        case 33:
                                        estado=-90;
                                        break;
                                        case 34:
                                        estado=-91;
                                        break;
                                        case 35:
                                        estado=-92;
                                        break;
                                        case 36:
                                        estado=-93;
                                        break;
                                        case 37:
                                        estado=-94;
                                        break;
                                        case 38:
                                        estado=-95;
                                        break;
                                        case 39:
                                        estado=-96;
                                        break;
                                        case 40:
                                        estado=-97;
                                        break;
                                }
                                encontrado=true;
                            }
                        }
                        if(encontrado==true){
                            tokens.add(new StrikerToken(estado,linea,lexema));
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                        }
                        else{
                            boolean encontrado2=false;
                            for (int i = 0; i < operadoresIdent.length; i++) {
                                if(lexema.equals(operadoresIdent[i])){
                                    switch(i){
                                        case 0:
                                            estado=-75;
                                            break;
                                        case 1:
                                            estado=-76;
                                            break;
                                    }
                                    encontrado2=true;
                                }
                            }
                            if(encontrado2==true){
                                tokens.add(new StrikerToken(estado,linea,lexema));
                                modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                jTT.setModel(modeloTokens);
                            }
                            else{
                                tokens.add(new StrikerToken(estado,linea,lexema));
                                modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                jTT.setModel(modeloTokens);
                            }
                        }
                    }
                    else if (estadoAnt==29){
                        tokens.add(new StrikerToken(estado,linea,lexema));
                        modeloTokens.addRow(new Object[]{linea,estado,lexema});
                        jTT.setModel(modeloTokens);
                        lexema="";
                        estado=0;
                        estadoAnt = estado;
                        posicion++;
                    }
                }
                else if(estado>=900){
                    if(estado<950){
                        switch (estado){
                            case 900:
                                errorNo=900;
                                descripcion="Se esperaba un número del 0-1";
                                break;
                            case 901:
                                errorNo=901;
                                descripcion="Se esperaba un número del 0-9 o un menos (-)";
                                break;
                            case 902:
                                errorNo=902;
                                descripcion="Se esperaba cualquier número del 0-9";
                                break;
                            case 903:
                                errorNo=903;
                                descripcion="Se esperaba un número del 0-9, una letra de la a-f o de la A-F";
                                break;
                            case 904:
                                errorNo=904;
                                descripcion="Se esperaba un número del 0-7";
                                break;
                            case 905:
                                errorNo=905;
                                descripcion="Se esperaba un número del 0-9 o una j";
                                break;
                            case 906:
                                errorNo=906;
                                descripcion="Se esperaba un número del 0-9, un punto (.) o un e ó E";
                                break;
                            case 907:
                                errorNo=907;
                                descripcion="Se esperaba un número del 0-9, un e ó E";
                                break;
                            case 908:
                                errorNo=908;
                                descripcion="Se esperaba un número del 0-9, o un ) (Paréntesis cerrado)";
                                break;
                            case 909:
                                errorNo=909;
                                descripcion="Se esperaba un número del 0-9, un + o un .(Punto)";
                                break;
                            case 910:
                                errorNo=910;
                                descripcion="Se esperaba un número del 0-9 o un +";
                                break;
                            case 911:
                                errorNo=911;
                                descripcion="Se esperaba un número del 0-9, una j o un .(punto)";
                                break;
                            case 912:
                                errorNo=912;
                                descripcion="Se esperaba una apostrofe u otra cosa excepto salto de línea";
                                break;
                            case 913:
                                errorNo=913;
                                descripcion="Se esperaba una apostrofe";
                                break;
                            case 914:
                                errorNo=914;
                                descripcion="Se esperaba un espacio (\\s), un salto de línea (\\n), un guion bajo (_), numero del 0 – 9, del a-z, de la A-Z, un paréntesis abierto ((), un paréntesis cerrado ()), un corchete abierto ([), un corchete cerrado (]), una llave abierta ({), una llave cerrada (}), un mas (+), un menos (-), un asterisco (*), una barra (/), un porcentaje (%), un símbolo de exclamación (!), una &, una barra (|), un símbolo de menor que (<), un símbolo de mayor que (>), un símbolo de gorrito (^), un símbolo igual (=), un punto y coma (;), una coma (,), un símbolo de gato (#), una apostrofe (‘) ó unas comillas (“).";
                                posicion++;
                                break;
                            case 915:
                                errorNo=915;
                                descripcion="Se esperaba un mas, una J o un punto";
                                break;
                            case 916:
                                errorNo=916;
                                descripcion="se esperaba un punto o numero";
                                break;
                            case 917:
                                errorNo=917;
                                descripcion="se esperaba un mas (+)";
                                break;
                            case 918:
                                errorNo=918;
                                descripcion="se esperaba una j";
                                break;
                        }
                        errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema));
                        modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema});
                        jTE.setModel(modeloErrores);
                        estado=0;
                        estadoAnt = estado;
                        lexema="";
                        lexema3="";
                        lexema5="";
                        bandera3=true;
                        bandera4=true;
                    }
                    else{
                        char[] vector = lexema3.toCharArray();
                        String punto = ".";
                        String folte = "e";
                        String foltE = "E";
                        int estT = -4;
                        for (int i = 0; i < lexema3.length(); i++) {
                            String letra = String.valueOf(vector[i]);
                            if(punto.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(folte.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(foltE.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                        }
                        switch(estado){
                            case 950:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 951:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-4,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-4,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 952:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=902;
                                descripcion="Se esperaba cualquier número del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 953:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-10,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-10,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 954:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 955:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-7,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-7,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 956:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 957:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            
                        }
                    }
                }
                else if(estado>0){
                    row = sheet.getRow(estado+1);
                    cell = row.getCell(17);
                    celda=(int)cell.getNumericCellValue();
                    estado=celda;
                    if(estado<0){
                        if(estado==-20 || estado==-45 || estado==-21 || estado==-46 ||
                                estado==-47 || estado==-48 || estado==-49 || estado==-50 || estado==-51 ||
                                estado==-2 || estado==-3 || estado==-12 || estado==-8 ||
                                estado==-9|| estado==-78 || estado==-77 || estado==-79){
                            tokens.add(new StrikerToken(estado,linea,lexema));
                            if(estado!=-2 && estado!=-3){
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                            }
                            
                        }
                        else if (estado!=-11){
                            boolean encontrado=false;
                            for (int i = 0; i < palabrasRes.length; i++) {
                                if(lexema.equals(palabrasRes[i])){
                                    switch(i){
                                    case 0:
                                        estado=-52;
                                            break;
                                    case 1:
                                        estado=-53;
                                            break;
                                    case 2:
                                        estado=-54;
                                            break;
                                    case 3:
                                        estado=-55;
                                            break;
                                    case 4:
                                        estado=-56;
                                            break;
                                    case 5:
                                        estado=-57;
                                            break;
                                    case 6:
                                        estado=-58;
                                            break;
                                    case 7:
                                        estado=-59;
                                            break;
                                    case 8:
                                        estado=-60;
                                            break;
                                    case 9:
                                        estado=-61;
                                            break;
                                    case 10:
                                        estado=-62;
                                            break;
                                    case 11:
                                        estado=-63;
                                            break;
                                    case 12:
                                        estado=-64;
                                            break;
                                    case 13:
                                        estado=-65;
                                            break;
                                    case 14:
                                        estado=-66;
                                            break;
                                    case 15:
                                        estado=-67;
                                            break;
                                    case 16:
                                        estado=-68;
                                            break;
                                    case 17:
                                        estado=-69;
                                            break;
                                    case 18:
                                        estado=-70;
                                            break;
                                    case 19:
                                        estado=-71;
                                            break;
                                    case 20:
                                        estado=-72;
                                            break;
                                    case 21:
                                        estado=-73;
                                            break;
                                    case 22:
                                        estado=-74;
                                            break;
                                    case 23:
                                        estado=-80;
                                        break;
                                        case 24:
                                        estado=-81;
                                        break;
                                        case 25:
                                        estado=-82;
                                        break;
                                        case 26:
                                        estado=-83;
                                        break;
                                        case 27:
                                        estado=-84;
                                        break;
                                        case 28:
                                        estado=-85;
                                        break;
                                        case 29:
                                        estado=-86;
                                        break;
                                        case 30:
                                        estado=-87;
                                        break;
                                        case 31:
                                        estado=-88;
                                        break;
                                        case 32:
                                        estado=-89;
                                        break;
                                        case 33:
                                        estado=-90;
                                        break;
                                        case 34:
                                        estado=-91;
                                        break;
                                        case 35:
                                        estado=-92;
                                        break;
                                        case 36:
                                        estado=-93;
                                        break;
                                        case 37:
                                        estado=-94;
                                        break;
                                        case 38:
                                        estado=-95;
                                        break;
                                        case 39:
                                        estado=-96;
                                        break;
                                        case 40:
                                        estado=-97;
                                        break;
                                }
                                    encontrado=true;
                                }
                            }
                            if(encontrado==true){
                                tokens.add(new StrikerToken(estado,linea,lexema));
                                modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                jTT.setModel(modeloTokens);
                            }
                            else{
                                boolean encontrado2=false;
                                for (int i = 0; i < operadoresIdent.length; i++) {
                                    if(lexema.equals(operadoresIdent[i])){
                                        switch(i){
                                        case 0:
                                            estado=-75;
                                            break;
                                        case 1:
                                            estado=-76;
                                            break;
                                    }
                                        encontrado2=true;
                                    }
                                }
                                if(encontrado2==true){
                                    tokens.add(new StrikerToken(estado,linea,lexema));
                                    modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                    jTT.setModel(modeloTokens);
                                }
                                else{
                                    tokens.add(new StrikerToken(estado,linea,lexema));
                                    modeloTokens.addRow(new Object[]{linea,estado,lexema});
                                    jTT.setModel(modeloTokens);
                                }
                            }
                        }
                        else if (estadoAnt==29){
                            tokens.add(new StrikerToken(estado,linea,lexema));
                            modeloTokens.addRow(new Object[]{linea,estado,lexema});
                            jTT.setModel(modeloTokens);
                            lexema="";
                            estado=0;
                            estadoAnt = estado;
                            posicion++;
                        }
                    }
                    else if(estado>=900){
                        if(estado<950){
                            switch (estado){
                                case 900:
                                    errorNo=900;
                                    descripcion="Se esperaba un número del 0-1";
                                    break;
                                case 901:
                                    errorNo=901;
                                    descripcion="Se esperaba un número del 0-9 o un menos (-)";
                                    break;
                                case 902:
                                    errorNo=902;
                                    descripcion="Se esperaba cualquier número del 0-9";
                                    break;
                                case 903:
                                    errorNo=903;
                                    descripcion="Se esperaba un número del 0-9, una letra de la a-f o de la A-F";
                                    break;
                                case 904:
                                    errorNo=904;
                                    descripcion="Se esperaba un número del 0-7";
                                    break;
                                case 905:
                                    errorNo=905;
                                    descripcion="Se esperaba un número del 0-9 o una j";
                                    break;
                                case 906:
                                    errorNo=906;
                                    descripcion="Se esperaba un número del 0-9, un punto (.) o un e ó E";
                                    break;
                                case 907:
                                    errorNo=907;
                                    descripcion="Se esperaba un número del 0-9, un e ó E";
                                    break;
                                case 908:
                                    errorNo=908;
                                    descripcion="Se esperaba un número del 0-9, o un ) (Paréntesis cerrado)";
                                    break;
                                case 909:
                                    errorNo=909;
                                    descripcion="Se esperaba un número del 0-9, un + o un .(Punto)";
                                    break;
                                case 910:
                                    errorNo=910;
                                    descripcion="Se esperaba un número del 0-9 o un +";
                                    break;
                                case 911:
                                    errorNo=911;
                                    descripcion="Se esperaba un número del 0-9, una j o un .(punto)";
                                    break;
                                case 912:
                                    errorNo=912;
                                    descripcion="Se esperaba una apostrofe u otra cosa excepto salto de línea";
                                    break;
                                case 913:
                                    errorNo=913;
                                    descripcion="Se esperaba una apostrofe";
                                    break;
                                case 914:
                                    errorNo=914;
                                    descripcion="Se esperaba un espacio (\\s), un salto de línea (\\n), un guion bajo (_), numero del 0 – 9, del a-z, de la A-Z, un paréntesis abierto ((), un paréntesis cerrado ()), un corchete abierto ([), un corchete cerrado (]), una llave abierta ({), una llave cerrada (}), un mas (+), un menos (-), un asterisco (*), una barra (/), un porcentaje (%), un símbolo de exclamación (!), una &, una barra (|), un símbolo de menor que (<), un símbolo de mayor que (>), un símbolo de gorrito (^), un símbolo igual (=), un punto y coma (;), una coma (,), un símbolo de gato (#), una apostrofe (‘) ó unas comillas (“).";
                                    break;
                                case 915:
                                errorNo=915;
                                descripcion="Se esperaba un mas, una J o un punto";
                                break;
                            case 916:
                                errorNo=916;
                                descripcion="Se esperaba un punto o un numero";
                                break;
                            case 917:
                                errorNo=917;
                                descripcion="Se esperaba un + (Mas)";
                                break;
                            case 918:
                                errorNo=918;
                                descripcion="Se esperaba una j";
                                break;
                            }
                            errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema));
                            modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema});
                            jTE.setModel(modeloErrores);
                            estado=0;
                            estadoAnt = estado;
                            lexema="";
                            lexema3="";
                            lexema5="";
                            bandera3=true;
                            bandera4=true;
                            posicion++;
                        }
                        else{
                            char[] vector = lexema3.toCharArray();
                        String punto = ".";
                        String folte = "e";
                        String foltE = "E";
                        int estT = -4;
                        for (int i = 0; i < lexema3.length(); i++) {
                            String letra = String.valueOf(vector[i]);
                            if(punto.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(folte.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                            else if(foltE.equalsIgnoreCase(letra)){
                                estT= -10;
                            }
                        }
                        switch(estado){
                            case 950:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 951:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-4,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-4,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 952:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=902;
                                descripcion="Se esperaba cualquier número del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 953:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-10,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-10,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 954:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 955:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-7,linea,lexema5));
                                modeloTokens.addRow(new Object[]{linea,-7,lexema5});
                                jTT.setModel(modeloTokens);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 956:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            case 957:
                                tokens.add(new StrikerToken(estT,linea,lexema3));
                                modeloTokens.addRow(new Object[]{linea,estT,lexema3});
                                jTT.setModel(modeloTokens);
                                tokens.add(new StrikerToken(-13,linea,lexema4));
                                modeloTokens.addRow(new Object[]{linea,-13,lexema4});
                                jTT.setModel(modeloTokens);
                                errorNo=901;
                                descripcion="Se esperaba un mas(+), un menos(-) o un numero del 0-9";
                                errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema5));
                                modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema5});
                                jTE.setModel(modeloErrores);
                                lexema="";
                                estado=0;
                                estadoAnt = estado;
                                lexema3="";
                                lexema5="";
                                bandera3=true;
                                bandera4=true;
                                break;
                            
                        }
                        }
                    }
                    else{
                        errorNo=919;
                        descripcion="No se cerro comentario con triple apostrofe(''')";
                        errores.add(new KaijuError(linea,errorNo,descripcion,tipoLexico,lexema));                        
                        modeloErrores.addRow(new Object[]{linea,errorNo,descripcion,tipoLexico,lexema});
                        tokens.add(new StrikerToken(-3,linea,lexema));
                        jTE.setModel(modeloErrores);
                        estado=0;
                        estadoAnt = estado;
                        lexema="";
                        lexema3="";
                        lexema5="";
                        bandera3=true;
                        bandera4=true;
                        posicion++;
                    }
                }
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public List<StrikerToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<StrikerToken> tokens) {
        this.tokens = tokens;
    }

    public List<KaijuError> getErrores() {
        return errores;
    }

    public void setErrores(List<KaijuError> errores) {
        this.errores = errores;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    public String [] palabrasReservadas(){
        String [] palabrasRes = {"innot","none","true","break","false","continue","elif",
                                "else","def","range","input","for","if","findall",
                                "in","replace","len","print","sample","return",
                                "choice","while","random","randrange","mean","median",
                                "variance","sum","sort","reverse","count","index","append",
                                "extend","pop","remove","insert","println","wend","end","to"};
        return palabrasRes;
    }
    
    public String [] operadoresId(){
        String [] palabrasRes = {"is","isnot"};
        return palabrasRes;
    }
    
}

