/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import CONEXION.ConsultasTabla;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author alber
 */
public class Excel {
    Connection c;
    int ambito;
    int cErrores;
    int cIdentificadores;
    int cComentarios;
    int cPR;
    int cEDec;
    int cEBin;
    int cEHex;
    int cEOct;
    int cTexto;
    int cFloat;
    int cComp;
    int cCar;
    int cOArit;
    int cOMon;
    int cOlog;
    int cORel;
    int cOBit;
    int cOid;
    int cSPun;
    int cSAgr;
    int cSAsig;
    int lineas;
    List<StrikerToken> tokens;
    List<KaijuError> errores;
    int cProg;
    int cConst;
    int cConstEnt;
    int cListTupRa;
    int cTerminoPas;
    int cEleva;
    int cSimplExpPas;
    int cFact;
    int cNot;
    int cOR;
    int cOPBIT;
    int cAND;
    int cANDLOG;
    int cORLOG;
    int cXORLOG;
    int cEST;
    int cASIGN;
    int cFunList;
    int cARR;
    int cFunciones;
    int cExpPas;
    
    //Contadores Ambito;
    int cAClasesAmbito=0;
    int cAClasesAmbitoTotal=0;
    int cADecimal=0;
    int cABinario=0;
    int cAOctal=0;
    int cAHexa=0;
    int cAFlotante=0;
    int cACadena=0;
    int cACaracter=0;
    int cACompleja=0;
    int cABooleana=0;
    int cANone=0;
    int cAArreglo=0;
    int cATupla=0;
    int cALista=0;
    int cARegistro=0;
    int cARango=0;
    int cAConjunto=0;
    int cADiccionario=0;
    LinkedList<SemanticaFase1> listaSemantica;
    List<FaseCrusadia2> listaSemantica2;

    public Excel(List tokens, List errores, int lineas, int cProg, int cConst, int cConstEnt, int cListTupRa,
            int cTerminoPas, int cEleva, int cSimplExpPas, int cFact, int cNot, int cOR, int cOPBIT,
            int cAND, int cANDLOG, int cORLOG, int cXORLOG, int cEST, int cASIGN, int cFunList, int cARR,int cFunciones, int cExpPas, Connection c, int ambito,LinkedList<SemanticaFase1> listaSemantica,
            List<FaseCrusadia2> listaSemantica2) {
        this.ambito=ambito;
        this.c=c;
        this.cErrores = 0;
        this.cIdentificadores = 0;
        this.cComentarios = 0;
        this.cPR = 0;
        this.cEDec = 0;
        this.cEBin = 0;
        this.cEHex = 0;
        this.cEOct = 0;
        this.cTexto = 0;
        this.cFloat = 0;
        this.cComp = 0;
        this.cCar = 0;
        this.cOArit = 0;
        this.cOMon = 0;
        this.cOlog = 0;
        this.cORel = 0;
        this.cOBit = 0;
        this.cOid = 0;
        this.cSPun = 0;
        this.cSAgr = 0;
        this.cSAsig = 0;
        this.tokens=tokens;
        this.errores=errores;
        this.lineas=lineas;
        
        this.cProg = cProg;
        this.cConst = cConst;
        this.cConstEnt = cConstEnt;
        this.cListTupRa = cListTupRa;
        this.cTerminoPas = cTerminoPas;
        this.cEleva = cEleva;
        this.cSimplExpPas = cSimplExpPas;
        this.cFact = cFact;
        this.cNot = cNot;
        this.cOR = cOR;
        this.cOPBIT = cOPBIT;
        this.cAND = cAND;
        this.cANDLOG = cANDLOG;
        this.cORLOG = cORLOG;
        this.cXORLOG = cXORLOG;
        this.cEST = cEST;
        this.cASIGN = cASIGN;
        this.cFunList = cFunList;
        this.cARR = cARR;
        this.cFunciones = cFunciones;
        this.cExpPas = cExpPas;
        this.listaSemantica=listaSemantica;
        this.listaSemantica2=listaSemantica2;
    }
    
    
    
    
    public void crearExcel(){
                String nombreArchivo="Martin-Ruvalcaba-Rodriguez-Semantica-2.xls";
		String rutaArchivo= "C:\\Ficheros-Excel\\"+nombreArchivo;
		String nombreHoja1="Lista de Tokens";
                String nombreHoja2="Lista de Errores";
                String nombreHoja3="Contadores";
                String nombreHoja4="Tabla de Contadores de Lexico";
                String nombreHoja5="Tabla de Contadores de Sintactico";
                String nombreHoja6="Tabla de Contadores de Ambito";
                String nombreHoja7="Contadores Sem 1";
                String nombreHoja8="Contadores Sem 2";
		
		Workbook libro= new HSSFWorkbook();
		Sheet hoja1 = libro.createSheet(nombreHoja1);
                Sheet hoja2 = libro.createSheet(nombreHoja2);
                Sheet hoja3 = libro.createSheet(nombreHoja3);
                Sheet hoja4 = libro.createSheet(nombreHoja4);
                Sheet hoja5 = libro.createSheet(nombreHoja5);
                Sheet hoja6 = libro.createSheet(nombreHoja6);
                Sheet hoja7 = libro.createSheet(nombreHoja7);
                Sheet hoja8 = libro.createSheet(nombreHoja8);
                
		//cabecera de la hoja de excel
		String [] Cabecera1= new String[]{"Linea", "Token","Lexema"};
                String [] Cabecera2= new String[]{"Linea", "Error","Descripcion","Tipo","Lexema"};
                String [] Cabecera3= new String[]{"Errores", "Identificadores","Comentarios","Palabras Reservadas",
                                                    "CE-Dec","CE-Bin","CE-Hex","CE-Oct","Ctexto","CFloat","CNComp","Ccar",
                                                    "Oper-Arit","Oper-Monog","Oper-Log","Oper-Bit","Oper-Ident","Relacional",
                                                    "Sig-Punt","Sig-Agrup","Sig-Asig"};
                String [] Cabecera4= new String[]{"Linea","Errores", "Identificadores","Comentarios","Palabras Reservadas",
                                                    "CE-Dec","CE-Bin","CE-Hex","CE-Oct","Ctexto","CFloat","CNComp","Ccar",
                                                    "Oper-Arit","Oper-Monog","Oper-Log","Oper-Bit","Oper-Ident","Relacional",
                                                    "Sig-Punt","Sig-Agrup","Sig-Asig"};
		String [] Cabecera5= new String[]{"Program","Constante","Const Entero","List-Tup-Rangos","Termino Pascal","Elevacion",
                                                    "Simple Exp-Pas","Factor","Not","OR","OPBIT","AND","ANDLOG","ORLOG","XORLOG",
                                                    "EST","ASIGN","FunList","ARR","Funciones","Exp-Pas"};
                String [] Cabecera6= new String[]{"Ambito","Decimal","Binario","Octal","Hexadecimal","Flotante","Cadena","Caracter","Compleja",
                                                    "Booleana","None","Arreglo","Tuplas","Lista","Registro","Rango","Conjunto","Diccionario","Total/Amb"};
                String [] Cabecera6_2= new String[]{"","Tot_Decimal","Tot_Binario","Tot_Octal","Tot_Hexadecimal","Tot_Flotante","Tot_Cadena","Tot_Caracter","Tot_Compleja",
                                                    "Tot_Booleana","Tot_None","Tot_Arreglo","Tot_Tuplas","Tot_Lista","Tot_Registro","Tot_Rango","Tot_Conjunto","Tot_Diccionario","Total Gral"};
		String [] Cabecera7= new String[]{"Enteros"};
                String [] Cabecera7_2= new String[]{"","TD","TDB","TDO","TDH","TF","TS","TCH","TC","TB","TN","TL","TR","TV","Asignacion"};
                String [] Cabecera8= new String[]{"Regla","Tope Pila","Valor Real","Linea","Estado","Ambito"};
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
                Font font = libro.createFont();
                font.setBold(true);
                style.setFont(font);
        
        
		//generar los datos para el documento
                
                for (int i = 0; i < listaSemantica2.size()+1; i++) {
			Row row=hoja8.createRow(i);//se crea las filas
			for (int j = 0; j <Cabecera8.length; j++) {
				if (i==0) {//para la cabecera
						Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
						cell.setCellStyle(style); // se añade el style crea anteriormente 
						cell.setCellValue(Cabecera8[j]);//se añade el contenido					
				}else{//para el contenido
                                    Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                    switch(j){
                                        case 0:
                                            cell.setCellValue(listaSemantica2.get(i-1).getRegla()); //se añade el contenido
                                            break;
                                        case 1:
                                            cell.setCellValue(listaSemantica2.get(i-1).getTopePil()); //se añade el contenido
                                            break;
                                        case 2:
                                            cell.setCellValue(listaSemantica2.get(i-1).getValorReal()); //se añade el contenido
                                            break;
                                        case 3:
                                            cell.setCellValue(listaSemantica2.get(i-1).getLinea()); //se añade el contenido
                                            break;
                                        case 4:
                                            cell.setCellValue(listaSemantica2.get(i-1).getEstado()); //se añade el contenido
                                            break;
                                        case 5:
                                            cell.setCellValue(listaSemantica2.get(i-1).getAmbito()); //se añade el contenido
                                            break;
                                    }
                                    
				}				
			}
		}
                
                
		for (int i = 0; i < tokens.size()+1; i++) {
			Row row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <Cabecera1.length; j++) {
				if (i==0) {//para la cabecera
						Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
						cell.setCellStyle(style); // se añade el style crea anteriormente 
						cell.setCellValue(Cabecera1[j]);//se añade el contenido					
				}else{//para el contenido
                                    Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                    if(tokens.get(i-1).getToken()!=-2 && tokens.get(i-1).getToken()!=-3){
                                        switch(j){
                                            case 0:
                                                cell.setCellValue(tokens.get(i-1).getLine()); //se añade el contenido
                                                break;
                                            case 1:
                                                cell.setCellValue(tokens.get(i-1).getToken()); //se añade el contenido
                                                break;
                                            case 2:
                                                cell.setCellValue(tokens.get(i-1).getLexema()); //se añade el contenido
                                                break;
                                        }
                                    }
				}				
			}
		}
                for (int i = 0; i < errores.size()+1; i++) {
                    Row row=hoja2.createRow(i);//se crea las filas
                    for (int j = 0; j <Cabecera2.length; j++) {
                            if (i==0) {//para la cabecera
                                            Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                            cell.setCellStyle(style); // se añade el style crea anteriormente 
                                            cell.setCellValue(Cabecera2[j]);//se añade el contenido					
                            }else{//para el contenido
                                    Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                    switch(j){
                                        case 0:
                                            cell.setCellValue(errores.get(i-1).getLine()); //se añade el contenido
                                            break;
                                        case 1:
                                            cell.setCellValue(errores.get(i-1).getError()); //se añade el contenido
                                            break;
                                        case 2:
                                            cell.setCellValue(errores.get(i-1).getDescripcion()); //se añade el contenido
                                            break;
                                        case 3:
                                            cell.setCellValue(errores.get(i-1).getTipo());
                                            break;
                                        case 4:
                                            cell.setCellValue(errores.get(i-1).getLexema()); //se añade el contenido
                                            break;
                                    }

                            }				
                    }
		}
                
                for (int i = 0; i < tokens.size(); i++) {
                    switch(tokens.get(i).getToken()){
                        case -1:
                            cIdentificadores++;
                            System.out.println("IDENTIFICADOR");
                            System.out.println(cIdentificadores);
                            break;
                        case -2:
                            cComentarios++;
                            break;
                        case -3:
                            cComentarios++;
                            break;
                        case -4:
                            cEDec++;
                            break;
                        case -5:
                            cEBin++;
                            break;
                        case -6:
                            cEHex++;
                            break;
                        case -7:
                            cEOct++;
                            break;
                        case -8:
                            cTexto++;
                            break;
                        case -9:
                            cTexto++;
                            break;
                        case -10:
                            cFloat++;
                            break;
                        case -11:
                            cComp++;
                            break;
                        case -12:
                            cCar++;
                            break;
                        case -13:
                            cOArit++;
                            break;
                        case -14:
                            cOArit++;
                            break;
                        case -15:
                            cOArit++;
                            break;
                        case -16:
                            cOArit++;
                            break;
                        case -17:
                            cOArit++;
                            break;
                        case -18:
                            cOArit++;
                            break;
                        case -19:
                            cOArit++;
                            break;
                        case -20:
                            cOMon++;
                            break;
                        case -21:
                            cOMon++;
                            break;
                        case -22:
                            cOlog++;
                            break;
                        case -23:
                            cOlog++;
                            break;
                        case -24:
                            cOlog++;
                            break;
                        case -25:
                            cORel++;
                            break;
                        case -26:
                            cORel++;
                            break;
                        case -27:
                            cORel++;
                            break;
                        case -28:
                            cORel++;
                            break;
                        case -29:
                            cORel++;
                            break;
                        case -30:
                            cORel++;
                            break;
                        case -31:
                            cOBit++;
                            break;
                        case -32:
                            cOBit++;
                            break;
                        case -33:
                            cOBit++;
                            break;
                        case -34:
                            cOBit++;
                            break;
                        case -35:
                            cOBit++;
                            break;
                        case -36:
                            cSPun++;
                            break;
                        case -37:
                            cSPun++;
                            break;
                        case -38:
                            cSAgr++;
                            break;
                        case -39:
                            cSAgr++;
                            break;
                        case -40:
                            cSAgr++;
                            break;
                        case -41:
                            cSAgr++;
                            break;
                        case -42:
                            cSAgr++;
                            break;
                        case -43:
                            cSAgr++;
                            break;
                        case -44:
                            cSAsig++;
                            break;
                        case -45:
                            cSAsig++;
                            break;
                        case -46:
                            cSAsig++;
                            break;
                        case -47:
                            cSAsig++;
                            break;
                        case -48:
                            cSAsig++;
                            break;
                        case -49:
                            cSAsig++;
                            break;
                        case -50:
                            cSAsig++;
                            break;
                        case -51:
                            cSAsig++;
                            break;
                        case -52:
                            cPR++;
                            break;
                        case -53:
                            cPR++;
                            break;
                        case -54:
                            cPR++;
                            break;
                        case -55:
                            cPR++;
                            break;
                        case -56:
                            cPR++;
                            break;
                        case -57:
                            cPR++;
                            break;
                        case -58:
                            cPR++;
                            break;
                        case -59:
                            cPR++;
                            break;
                        case -60:
                            cPR++;
                            break;
                        case -61:
                            cPR++;
                            break;
                        case -62:
                            cPR++;
                            break;
                        case -63:
                            cPR++;
                            break;
                        case -64:
                            cPR++;
                            break;
                        case -65:
                            cPR++;
                            break;
                        case -66:
                            cPR++;
                            break;
                        case -67:
                            cPR++;
                            break;
                        case -68:
                            cPR++;
                            break;
                        case -69:
                            cPR++;
                            break;
                        case -70:
                            cPR++;
                            break;
                        case -71:
                            cPR++;
                            break;
                        case -72:
                            cPR++;
                            break;
                        case -73:
                            cPR++;
                            break;
                        case -74:
                            
                            cPR++;
                            
                            break;
                        case -75:
                            System.out.println("AJAJAJAJAJA");
                            cOid++;
                            System.out.println(cOid);
                            break;
                        
                        case -76:
                            cOid++;
                            break;
                        case -77:
                            cOlog++;
                            break;
                        case -78:
                            cSPun++;
                            break;
                        case -79:
                            cSPun++;
                            break;
                        case -80:
                            cPR++;
                            break;
                        case -81:
                            cPR++;
                            break;
                        case -82:
                            cPR++;
                            break;
                        case -83:
                            cPR++;
                            break;
                        case -84:
                            cPR++;
                            break;
                        case -85:
                            cPR++;
                            break;
                        case -86:
                            cPR++;
                            break;
                        case -87:
                            cPR++;
                            break;
                        case -88:
                            cPR++;
                            break;
                        case -89:
                            cPR++;
                            break;
                        case -90:
                            cPR++;
                            break;
                        case -91:
                            cPR++;
                            break;
                        case -92:
                            cPR++;
                            break;
                        case -93:
                            cPR++;
                            break;
                        case -94:
                            cPR++;
                            break;
                        case -95:
                            cPR++;
                            break;
                        case -96:
                            cPR++;
                            break;
                        case -97:
                            cPR++;
                            break;
                    }
                }
                System.out.println("PALABRA RESERVADA");
                System.out.println(cPR);
                cErrores=errores.size();
		for (int i = 0; i < 2; i++) {
                    Row row=hoja3.createRow(i);//se crea las filas
                    for (int j = 0; j <Cabecera3.length; j++) {
                        if (i==0) {//para la cabecera
                                        Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                        cell.setCellStyle(style); // se añade el style crea anteriormente 
                                        cell.setCellValue(Cabecera3[j]);//se añade el contenido					
                        }else{//para el contenido
                            Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                            switch(j){
                                case 0:
                                    cell.setCellValue(cErrores); //se añade el contenido
                                    break;
                                case 1:
                                    cell.setCellValue(cIdentificadores); //se añade el contenido
                                    break;
                                case 2:
                                    cell.setCellValue(cComentarios); //se añade el contenido
                                    break;
                                case 3:
                                    cell.setCellValue(cPR); //se añade el contenido
                                    break;
                                case 4:
                                    cell.setCellValue(cEDec); //se añade el contenido
                                    break;
                                case 5:
                                    cell.setCellValue(cEBin); //se añade el contenido
                                    break;
                                case 6:
                                    cell.setCellValue(cEHex); //se añade el contenido
                                    break;
                                case 7:
                                    cell.setCellValue(cEOct); //se añade el contenido
                                    break;
                                case 8:
                                    cell.setCellValue(cTexto); //se añade el contenido
                                    break;
                                case 9:
                                    cell.setCellValue(cFloat); //se añade el contenido
                                    break;
                                case 10:
                                    cell.setCellValue(cComp); //se añade el contenido
                                    break;
                                case 11:
                                    cell.setCellValue(cCar); //se añade el contenido
                                    break;
                                case 12:
                                    cell.setCellValue(cOArit); //se añade el contenido
                                    break;
                                case 13:
                                    cell.setCellValue(cOMon); //se añade el contenido
                                    break;
                                case 14:
                                    cell.setCellValue(cOlog); //se añade el contenido
                                    break;
                                case 15:
                                    cell.setCellValue(cOBit); //se añade el contenido
                                    break;
                                case 16:
                                    cell.setCellValue(cOid); //se añade el contenido
                                    break;
                                case 17:
                                    cell.setCellValue(cORel); //se añade el contenido
                                break;
                                case 18:
                                    cell.setCellValue(cSPun); //se añade el contenido
                                    break;
                                case 19:
                                    cell.setCellValue(cSAgr); //se añade el contenido
                                    break;
                                case 20:
                                    cell.setCellValue(cSAsig); //se añade el contenido
                                    break;
                            }
                        }				
                    }
		}
                inicializar();
                for (int i = 0; i <= lineas; i++) {
                    Row row=hoja4.createRow(i);//se crea las filas
                    inicializar();
                    for (int j = 0; j<Cabecera4.length; j++) {
                        inicializar();
                        if(i==0){
                            Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                            cell.setCellStyle(style); // se añade el style crea anteriormente 
                            cell.setCellValue(Cabecera4[j]);//se añade el contenido	
                        }
                        else{
                            for (int k = 0; k < errores.size(); k++) {
                                if(errores.get(k).getLine()==i){
                                    cErrores++;
                                }
                            }
                            for (int l = 0; l < tokens.size(); l++) {
                                if(tokens.get(l).getLine()==i){
                                    switch(tokens.get(l).getToken()){
                                        case -1:
                                            cIdentificadores++;
                                            break;
                                        case -2:
                                            cComentarios++;
                                            System.out.println("Entro "+i);
                                            break;
                                        case -3:
                                            cComentarios++;
                                            break;
                                        case -4:
                                            cEDec++;
                                            break;
                                        case -5:
                                            cEBin++;
                                            break;
                                        case -6:
                                            cEHex++;
                                            break;
                                        case -7:
                                            cEOct++;
                                            break;
                                        case -8:
                                            cTexto++;
                                            break;
                                        case -9:
                                            cTexto++;
                                            break;
                                        case -10:
                                            cFloat++;
                                            break;
                                        case -11:
                                            cComp++;
                                            break;
                                        case -12:
                                            cCar++;
                                            break;
                                        case -13:
                                            cOArit++;
                                            break;
                                        case -14:
                                            cOArit++;
                                            break;
                                        case -15:
                                            cOArit++;
                                            break;
                                        case -16:
                                            cOArit++;
                                            break;
                                        case -17:
                                            cOArit++;
                                            break;
                                        case -18:
                                            cOArit++;
                                            break;
                                        case -19:
                                            cOArit++;
                                            break;
                                        case -20:
                                            cOMon++;
                                            break;
                                        case -21:
                                            cOMon++;
                                            break;
                                        case -22:
                                            cOlog++;
                                            break;
                                        case -23:
                                            cOlog++;
                                            break;
                                        case -24:
                                            cOlog++;
                                            break;
                                        case -25:
                                            cORel++;
                                            break;
                                        case -26:
                                            cORel++;
                                            break;
                                        case -27:
                                            cORel++;
                                            break;
                                        case -28:
                                            cORel++;
                                            break;
                                        case -29:
                                            cORel++;
                                            break;
                                        case -30:
                                            cORel++;
                                            break;
                                        case -31:
                                            cOBit++;
                                            break;
                                        case -32:
                                            cOBit++;
                                            break;
                                        case -33:
                                            cOBit++;
                                            break;
                                        case -34:
                                            cOBit++;
                                            break;
                                        case -35:
                                            cOBit++;
                                            break;
                                        case -36:
                                            cSPun++;
                                            break;
                                        case -37:
                                            cSPun++;
                                            break;
                                        case -38:
                                            cSAgr++;
                                            break;
                                        case -39:
                                            cSAgr++;
                                            break;
                                        case -40:
                                            cSAgr++;
                                            break;
                                        case -41:
                                            cSAgr++;
                                            break;
                                        case -42:
                                            cSAgr++;
                                            break;
                                        case -43:
                                            cSAgr++;
                                            break;
                                        case -44:
                                            cSAsig++;
                                            break;
                                        case -45:
                                            cSAsig++;
                                            break;
                                        case -46:
                                            cSAsig++;
                                            break;
                                        case -47:
                                            cSAsig++;
                                            break;
                                        case -48:
                                            cSAsig++;
                                            break;
                                        case -49:
                                            cSAsig++;
                                            break;
                                        case -50:
                                            cSAsig++;
                                            break;
                                        case -51:
                                            cSAsig++;
                                            break;
                                        case -52:
                                            cPR++;
                                            break;
                                        case -53:
                                            cPR++;
                                            break;
                                        case -54:
                                            cPR++;
                                            break;
                                        case -55:
                                            cPR++;
                                            break;
                                        case -56:
                                            cPR++;
                                            break;
                                        case -57:
                                            cPR++;
                                            break;
                                        case -58:
                                            cPR++;
                                            break;
                                        case -59:
                                            cPR++;
                                            break;
                                        case -60:
                                            cPR++;
                                            break;
                                        case -61:
                                            cPR++;
                                            break;
                                        case -62:
                                            cPR++;
                                            break;
                                        case -63:
                                            cPR++;
                                            break;
                                        case -64:
                                            cPR++;
                                            break;
                                        case -65:
                                            cPR++;
                                            break;
                                        case -66:
                                            cPR++;
                                            break;
                                        case -67:
                                            cPR++;
                                            break;
                                        case -68:
                                            cPR++;
                                            break;
                                        case -69:
                                            cPR++;
                                            break;
                                        case -70:
                                            cPR++;
                                            break;
                                        case -71:
                                            cPR++;
                                            break;
                                        case -72:
                                            cPR++;
                                            break;
                                        case -73:
                                            cPR++;
                                            break;
                                        case -74:
                                            cPR++;
                                            break;
                                        case -75:
                                            cOid++;
                                            break;
                                        case -76:
                                            cOid++;
                                            break;
                                        case -77:
                                            cOlog++;
                                            break;
                                        case -78:
                                            cSPun++;
                                            break;
                                        case -79:
                                            cSPun++;
                                            break;
                                        case -80:
                                            cPR++;
                                            break;
                                        case -81:
                                            cPR++;
                                            break;
                                        case -82:
                                            cPR++;
                                            break;
                                        case -83:
                                            cPR++;
                                            break;
                                        case -84:
                                            cPR++;
                                            break;
                                        case -85:
                                            cPR++;
                                            break;
                                        case -86:
                                            cPR++;
                                            break;
                                        case -87:
                                            cPR++;
                                            break;
                                        case -88:
                                            cPR++;
                                            break;
                                        case -89:
                                            cPR++;
                                            break;
                                        case -90:
                                            cPR++;
                                            break;
                                        case -91:
                                            cPR++;
                                            break;
                                        case -92:
                                            cPR++;
                                            break;
                                        case -93:
                                            cPR++;
                                            break;
                                        case -94:
                                            cPR++;
                                            break;
                                        case -95:
                                            cPR++;
                                            break;
                                        case -96:
                                            cPR++;
                                            break;
                                        case -97:
                                            cPR++;
                                            break;
                                    }
                                }
                                if(cErrores==0 && cIdentificadores==0 && cComentarios==0 && cEDec==0 && cEDec==0 && cEBin==0 &&
                                        cEHex==0 && cEOct==0 && cTexto==0 && cFloat==0 && cComp==0 && cCar==0 && 
                                        cOArit==0 && cOMon==0 && cOlog==0 && cORel==0 && cOBit==0 &&
                                        cOid==0 && cSPun==0 && cSAgr==0 && cSAsig==0 && cPR==0){
                                }
                                else{
                                    Cell cell= row.createCell(j);
                                    switch(j){
                                        case 0:
                                            cell.setCellValue(i); //se añade el contenido
                                            break;
                                        case 1:
                                            cell.setCellValue(cErrores); //se añade el contenido
                                            break;
                                        case 2:
                                            cell.setCellValue(cIdentificadores); //se añade el contenido
                                            break;
                                        case 3:
                                            cell.setCellValue(cComentarios); //se añade el contenido
                                            break;
                                        case 4:
                                            cell.setCellValue(cPR); //se añade el contenido
                                            break;
                                        case 5:
                                            cell.setCellValue(cEDec); //se añade el contenido
                                            break;
                                        case 6:
                                            cell.setCellValue(cEBin); //se añade el contenido
                                            break;
                                        case 7:
                                            cell.setCellValue(cEHex); //se añade el contenido
                                            break;
                                        case 8:
                                            cell.setCellValue(cEOct); //se añade el contenido
                                            break;
                                        case 9:
                                            cell.setCellValue(cTexto); //se añade el contenido
                                            break;
                                        case 10:
                                            cell.setCellValue(cFloat); //se añade el contenido
                                            break;
                                        case 11:
                                            cell.setCellValue(cComp); //se añade el contenido
                                            break;
                                        case 12:
                                            cell.setCellValue(cCar); //se añade el contenido
                                            break;
                                        case 13:
                                            cell.setCellValue(cOArit); //se añade el contenido
                                            break;
                                        case 14:
                                            cell.setCellValue(cOMon); //se añade el contenido
                                            break;
                                        case 15:
                                            cell.setCellValue(cOlog); //se añade el contenido
                                            break;
                                        case 16:
                                            cell.setCellValue(cOBit); //se añade el contenido
                                            break;
                                        case 17:
                                            cell.setCellValue(cOid); //se añade el contenido
                                            break;
                                        case 18:
                                            cell.setCellValue(cORel); //se añade el contenido
                                            break;
                                        case 19:
                                            cell.setCellValue(cSPun); //se añade el contenido
                                            break;
                                        case 20:
                                            cell.setCellValue(cSAgr); //se añade el contenido
                                            break;
                                        case 21:
                                            cell.setCellValue(cSAsig); //se añade el contenido
                                            break;
                                                                                    
                                    }
                                }
                            }
                        }
                    }
                }
                //HOJA NUMERO 5
                for (int i = 0; i < 2; i++) {
                    Row row=hoja5.createRow(i);//se crea las filas
                    for (int j = 0; j <Cabecera5.length; j++) {
                        if (i==0) {//para la cabecera
                                        Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                        cell.setCellStyle(style); // se añade el style crea anteriormente 
                                        cell.setCellValue(Cabecera5[j]);//se añade el contenido					
                        }else{//para el contenido
                            Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                            switch(j){
                                case 0:
                                    cell.setCellValue(cProg); //se añade el contenido
                                    break;
                                case 1:
                                    cell.setCellValue(cConst); //se añade el contenido
                                    break;
                                case 2:
                                    cell.setCellValue(cConstEnt); //se añade el contenido
                                    break;
                                case 3:
                                    cell.setCellValue(cListTupRa); //se añade el contenido
                                    break;
                                case 4:
                                    cell.setCellValue(cTerminoPas); //se añade el contenido
                                    break;
                                case 5:
                                    cell.setCellValue(cEleva); //se añade el contenido
                                    break;
                                case 6:
                                    cell.setCellValue(cSimplExpPas); //se añade el contenido
                                    break;
                                case 7:
                                    cell.setCellValue(cFact); //se añade el contenido
                                    break;
                                case 8:
                                    cell.setCellValue(cNot); //se añade el contenido
                                    break;
                                case 9:
                                    cell.setCellValue(cOR); //se añade el contenido
                                    break;
                                case 10:
                                    cell.setCellValue(cOPBIT); //se añade el contenido
                                    break;
                                case 11:
                                    cell.setCellValue(cAND); //se añade el contenido
                                    break;
                                case 12:
                                    cell.setCellValue(cANDLOG); //se añade el contenido
                                    break;
                                case 13:
                                    cell.setCellValue(cORLOG); //se añade el contenido
                                    break;
                                case 14:
                                    cell.setCellValue(cXORLOG); //se añade el contenido
                                    break;
                                case 15:
                                    cell.setCellValue(cEST); //se añade el contenido
                                    break;
                                case 16:
                                    cell.setCellValue(cASIGN); //se añade el contenido
                                    break;
                                case 17:
                                    cell.setCellValue(cFunList); //se añade el contenido
                                break;
                                case 18:
                                    cell.setCellValue(cARR); //se añade el contenido
                                    break;
                                case 19:
                                    cell.setCellValue(cFunciones); //se añade el contenido
                                    break;
                                case 20:
                                    cell.setCellValue(cExpPas); //se añade el contenido
                                    break;
                            }
                        }				
                    }
		}
                //HOJA NUMERO 6
                
//                ResultSet rs = ConsultasTabla.getTabla("SELECT  FROM Ambito2");
//                ResultSet rs = ConsultasTabla.getTabla("SELECT  FROM Ambito2");
//                try{
                    for (int i = 0; i <= ambito+3; i++) {
                        Row row=hoja6.createRow(i);
                        for (int j = 0; j<Cabecera6.length; j++) {
                            if (i==0) {//para la cabecera
                                Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                cell.setCellStyle(style); // se añade el style crea anteriormente 
                                cell.setCellValue(Cabecera6[j]);//se añade el contenido					
                            }
                            else if(i==ambito+3){
                                Cell cell= row.createCell(j);
                                cell.setCellStyle(style);
                                cell.setCellValue(Cabecera6_2[j]);
                                
                            }
                            else if(i==ambito+2){
                                Cell cell= row.createCell(j);
                                switch(j){
                                    case 0:
                                        cell.setCellValue("");
                                        break;
                                    case 1:
                                        cell.setCellValue(cADecimal);
                                        break;
                                    case 2:
                                        cell.setCellValue(cABinario);
                                        break;
                                    case 3:
                                        cell.setCellValue(cAOctal);
                                        break;
                                    case 4:
                                        cell.setCellValue(cAHexa);
                                        break;
                                    case 5:
                                        cell.setCellValue(cAFlotante);
                                        break;
                                    case 6:
                                        cell.setCellValue(cACadena);
                                        break;
                                    case 7:
                                        cell.setCellValue(cACaracter);
                                        break;
                                    case 8:
                                        cell.setCellValue(cACompleja);
                                        break;
                                    case 9:
                                        cell.setCellValue(cABooleana);
                                        break;
                                    case 10:
                                        cell.setCellValue(cANone);
                                        break;
                                    case 11:
                                        cell.setCellValue(cAArreglo);
                                        break;
                                    case 12:
                                        cell.setCellValue(cATupla);
                                        break;
                                    case 13:
                                        cell.setCellValue(cALista);
                                        break;
                                    case 14:
                                        cell.setCellValue(cARegistro);
                                        break;
                                    case 15:
                                        cell.setCellValue(cARango);
                                        break;
                                    case 16:
                                        cell.setCellValue(cAConjunto);
                                        break;
                                    case 17:
                                        cell.setCellValue(cADiccionario);
                                        break;
                                    case 18:
                                        cell.setCellValue(cAClasesAmbitoTotal);
                                        break;
                                }
                                
                            }
                            else if(i<=ambito+1){//para el contenido
                                Cell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                switch(j){
                                    case 0:
                                        cell.setCellValue(i-1); //se añade el contenido
                                        break;
                                    case 1:
                                        ResultSet rs1 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Decimal' AND Ambito="+(i-1));
                                        try{
                                            if (rs1.next()) {
                                                cell.setCellValue(rs1.getInt(1)); //se añade el contenido
                                                cADecimal=cADecimal+rs1.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs1.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 2:
                                        ResultSet rs2 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Binario' AND Ambito="+(i-1));
                                        try{
                                            if (rs2.next()) {
                                                cell.setCellValue(rs2.getInt(1)); //se añade el contenido
                                                cABinario=cABinario+rs2.getInt(1);
                                                cAClasesAmbito+=rs2.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 3:
                                        ResultSet rs3 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Octal' AND Ambito="+(i-1));
                                        try{
                                            if (rs3.next()) {
                                                cell.setCellValue(rs3.getInt(1)); //se añade el contenido
                                                cAOctal=cAOctal+rs3.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs3.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 4:
                                        ResultSet rs4 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Hexadecimal' AND Ambito="+(i-1));
                                        try{
                                            if (rs4.next()) {
                                                cell.setCellValue(rs4.getInt(1)); //se añade el contenido
                                                cAHexa=cAHexa+rs4.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs4.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 5:
                                        ResultSet rs5 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Flotante' AND Ambito="+(i-1));
                                        try{
                                            if (rs5.next()) {
                                                cell.setCellValue(rs5.getInt(1)); //se añade el contenido
                                                cAFlotante=cAFlotante+rs5.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs5.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 6:
                                        ResultSet rs6 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Cadena' AND Ambito="+(i-1));
                                        try{
                                            if (rs6.next()) {
                                                cell.setCellValue(rs6.getInt(1)); //se añade el contenido
                                                cACadena=cACadena+rs6.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs6.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 7:
                                        ResultSet rs7 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Caracter' AND Ambito="+(i-1));
                                        try{
                                            if (rs7.next()) {
                                                cell.setCellValue(rs7.getInt(1)); //se añade el contenido
                                                cACaracter=cACaracter+rs7.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs7.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 8:
                                        ResultSet rs8 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Compleja' AND Ambito="+(i-1));
                                        try{
                                            if (rs8.next()) {
                                                cell.setCellValue(rs8.getInt(1)); //se añade el contenido
                                                cACompleja=cACompleja+rs8.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs8.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 9:
                                        ResultSet rs9 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Booleana' AND Ambito="+(i-1));
                                        try{
                                            if (rs9.next()) {
                                                cell.setCellValue(rs9.getInt(1)); //se añade el contenido
                                                cABooleana=cABooleana+rs9.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs9.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 10:
                                        ResultSet rs10 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='None' AND Ambito="+(i-1));
                                        try{
                                            if (rs10.next()) {
                                                cell.setCellValue(rs10.getInt(1)); //se añade el contenido
                                                cANone=cANone+rs10.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs10.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 11:
                                        ResultSet rs11 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Arreglo' AND Ambito="+(i-1));
                                        try{
                                            if (rs11.next()) {
                                                cell.setCellValue(rs11.getInt(1)); //se añade el contenido
                                                cAArreglo=cAArreglo+rs11.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs11.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 12:
                                        ResultSet rs12 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Tupla' AND Ambito="+(i-1));
                                        try{
                                            if (rs12.next()) {
                                                cell.setCellValue(rs12.getInt(1)); //se añade el contenido
                                                cATupla=cATupla+rs12.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs12.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 13:
                                        ResultSet rs13 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Lista' AND Ambito="+(i-1));
                                        try{
                                            if (rs13.next()) {
                                                cell.setCellValue(rs13.getInt(1)); //se añade el contenido
                                                cALista=cALista+rs13.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs13.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        rs13 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Lista-Arr' AND Ambito="+(i-1));
                                        try{
                                            if (rs13.next()) {
                                                cell.setCellValue(rs13.getInt(1)); //se añade el contenido
                                                cALista=cALista+rs13.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs13.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 14:
                                        ResultSet rs14 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Tipo='Registro' AND Ambito="+(i-1));
                                        try{
                                            if (rs14.next()) {
                                                cell.setCellValue(rs14.getInt(1)); //se añade el contenido
                                                cARegistro=cARegistro+rs14.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs14.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 15:
                                        ResultSet rs15 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Rango' AND Ambito="+(i-1));
                                        try{
                                            if (rs15.next()) {
                                                cell.setCellValue(rs15.getInt(1)); //se añade el contenido
                                                cARango=cARango+rs15.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs15.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 16:
                                        ResultSet rs16 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Conjunto' AND Ambito="+(i-1));
                                        try{
                                            if (rs16.next()) {
                                                cell.setCellValue(rs16.getInt(1)); //se añade el contenido
                                                cAConjunto=cAConjunto+rs16.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs16.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 17:
                                        ResultSet rs17 = ConsultasTabla.getTabla("SELECT count(*) FROM Ambito2 WHERE Clase='Diccionario' AND Ambito="+(i-1));
                                        try{
                                            if (rs17.next()) {
                                                cell.setCellValue(rs17.getInt(1)); //se añade el contenido
                                                cADiccionario=cADiccionario+rs17.getInt(1);
                                                cAClasesAmbito=cAClasesAmbito+rs17.getInt(1);
                                            }
                                        }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        break;
                                    case 18:
                                        cell.setCellValue(cAClasesAmbito);
                                        cAClasesAmbitoTotal=cAClasesAmbitoTotal+cAClasesAmbito;
                                        cAClasesAmbito=0;
                                        break;
                                }

                            }				
                    
                        }
                    }
                    //HOJA 7
                    for (int i = 0; i < listaSemantica.size()+2; i++) {
                        System.out.println("SEMANTICA 1");
                        Row row=hoja7.createRow(i);
                        for (int j = 0; j<Cabecera7_2.length; j++) {
                            if (i==1) {//para la cabecera
                                Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                cell.setCellStyle(style); // se añade el style crea anteriormente 
                                cell.setCellValue(Cabecera7_2[j]);//se añade el contenido					
                            }
                            else if(i>=2){
                                Cell cell= row.createCell(j);
                                switch(j){
                                    case 0:
                                        cell.setCellValue(listaSemantica.get(i-2).getLine());
                                        break;
                                    case 1:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTDecimal());
                                        break;
                                    case 2:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTBinario());
                                        break;
                                    case 3:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTOctal());
                                        break;
                                    case 4:cell.setCellValue(listaSemantica.get(i-2).getcTHexadecimal());
                                        break;
                                    case 5:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTFlotante());
                                        break;
                                    case 6:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTCadena());
                                        break;
                                    case 7:cell.setCellValue(listaSemantica.get(i-2).getcTCaracter());
                                        break;
                                    case 8:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTCompleja());
                                        break;
                                    case 9:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTBooleana());
                                        break;
                                    case 10:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTNone());
                                        break;
                                    case 11:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTLista());
                                        break;
                                    case 12:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTRango());
                                        break;
                                    case 13:
                                        cell.setCellValue(listaSemantica.get(i-2).getcTVariant());
                                        break;
                                    case 14:
                                        cell.setCellValue(listaSemantica.get(i-2).getLexema());
                                        break;
                                }
                            }
                        }
                    }
                    Simbolos_Tabla(libro);
                    
                    
		File file;
		file = new File(rutaArchivo);
		try (FileOutputStream fileOuS = new FileOutputStream(file)){						
			if (file.exists()) {// si el archivo existe se elimina
				file.delete();
			}
			libro.write(fileOuS);
			fileOuS.flush();
			fileOuS.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
        }
    public void Simbolos_Tabla(Workbook libro){
        String nombreHoja7="Tabla Simbolos";
        Sheet hoja7 = libro.createSheet(nombreHoja7);
        String [] Cabecera7 = new String[]{"Id", "Tipo","Clase","Ambito","TamArr","AmbitoCrea","Valor","NomPosicion","Llave","Rango","Avance","ListaPertenece","NoParr","TParr"};
        int simbolos = 1;
        char a=' ';
        
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        
        Row row=hoja7.createRow(0);
        for (int j = 0; j<Cabecera7.length; j++) {
                Cell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                cell.setCellStyle(style); // se añade el style crea anteriormente 
                cell.setCellValue(Cabecera7[j]);//se añade el contenido	
        }
        
        ResultSet rs = ConsultasTabla.getTabla("SELECT * FROM Ambito2");
            try{
                while (rs.next()) {
                    row=hoja7.createRow(simbolos);
                    for (int i = 0; i < Cabecera7.length; i++) {
                        Cell cell= row.createCell(i);
                        switch(i){
                            case 0:
                                cell.setCellValue(rs.getString("Id")); //se añade el contenido
                                break;
                            case 1:
                                cell.setCellValue(rs.getString("Tipo")); //se añade el contenido
                                break;
                            case 2:
                                cell.setCellValue(rs.getString("Clase")); //se añade el contenido
                                break;
                            case 3:
                                cell.setCellValue(rs.getString("Ambito"));
                                break;
                            case 4:
                                cell.setCellValue(rs.getString("TamArr"));
                                break;
                            case 5:
                                cell.setCellValue(rs.getString("AmbitoCrea"));
                                break;
                            case 6:
                                cell.setCellValue(rs.getString("Valor"));
                                break;
                            case 7:
                                cell.setCellValue(rs.getString("NomPosicion"));
                                break;
                            case 8:
                                cell.setCellValue(rs.getString("Llave"));
                                break;
                            case 9:
                                cell.setCellValue(rs.getString("Rango"));
                                break;
                            case 10:
                                cell.setCellValue(rs.getString("Avance"));
                                break;
                            case 11:
                                cell.setCellValue(rs.getString("ListaPertenece"));
                                break;
                            case 12:
                                cell.setCellValue(rs.getString("NoParr"));
                                break;
                            case 13:
                                cell.setCellValue(rs.getString("TParr"));
                                break;
                        }
                    }
                    simbolos++;
                    
                }
            }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }

                
    }
    public void inicializar(){
        cErrores = 0;
        cIdentificadores = 0;
        cComentarios = 0;
        cPR = 0;
        cEDec = 0;
        cEBin = 0;
        cEHex = 0;
        cEOct = 0;
        cTexto = 0;
        cFloat = 0;
        cComp = 0;
        cCar = 0;
        cOArit = 0;
        cOMon = 0;
        cOlog = 0;
        cORel = 0;
        cOBit = 0;
        cOid = 0;
        cSPun = 0;
        cSAgr = 0;
        cSAsig = 0;
    }
}
