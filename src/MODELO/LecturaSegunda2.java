/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONEXION.ConsultasTabla;
import CONTROL.FaseCrusadia2;
import CONTROL.KaijuError;
import CONTROL.SemanticaFase1;
import CONTROL.StrikerToken;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LecturaSegunda2 {
    Connection c;
    
    
    Lexico lexico;
    List<StrikerToken> tokens;
    List<KaijuError> errores;
    List<FaseCrusadia2> reglas;
    LinkedList<StrikerToken> tokensSintx;
    LinkedList<StrikerToken> operandosSemantica;
    LinkedList<StrikerToken> operadoresSemantica;
    LinkedList<StrikerToken> operandosSemantica2;
    LinkedList<SemanticaFase1> listaSemantica;

    public LinkedList<SemanticaFase1> getListaSemantica() {
        return listaSemantica;
    }

    public void setListaSemantica(LinkedList<SemanticaFase1> listaSemantica) {
        this.listaSemantica = listaSemantica;
    }
    Stack<Integer> PS;
    Stack<Integer> Ambi;
    Stack<Integer> AuxAmbi;
    String tipoLexico= "Lexico";
    String tipoSintactico= "Sintactico";
    String tipoAmbito="Ambito";
    String tipoSem1="Semantica 1";
    int ambito;
    //Contadores
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
    
    int cTDecimal=0;
    int cTBinario=0;
    int cTOctal=0;
    int cTHexadecimal=0;
    int cTFlotante=0;
    int cTCadena=0;
    int cTCaracter=0;
    int cTCompleja=0;
    int cTBooleana=0;
    int cTNone=0;
    int cTLista=0;
    int cTRango=0;
    int cTVariant=0;
    int cTTupla=0;
    int cTDiccionarios=0;
    int cTConjuntos=0;

    public List<FaseCrusadia2> getReglas() {
        return reglas;
    }

    public void setReglas(List<FaseCrusadia2> reglas) {
        this.reglas = reglas;
    }
    
    public int getcTDecimal() {
        return cTDecimal;
    }

    public void setcTDecimal(int cTDecimal) {
        this.cTDecimal = cTDecimal;
    }

    public int getcTBinario() {
        return cTBinario;
    }

    public void setcTBinario(int cTBinario) {
        this.cTBinario = cTBinario;
    }

    public int getcTOctal() {
        return cTOctal;
    }

    public void setcTOctal(int cTOctal) {
        this.cTOctal = cTOctal;
    }

    public int getcTHexadecimal() {
        return cTHexadecimal;
    }

    public void setcTHexadecimal(int cTHexadecimal) {
        this.cTHexadecimal = cTHexadecimal;
    }

    public int getcTFlotante() {
        return cTFlotante;
    }

    public void setcTFlotante(int cTFlotante) {
        this.cTFlotante = cTFlotante;
    }

    public int getcTCadena() {
        return cTCadena;
    }

    public void setcTCadena(int cTCadena) {
        this.cTCadena = cTCadena;
    }

    public int getcTCaracter() {
        return cTCaracter;
    }

    public void setcTCaracter(int cTCaracter) {
        this.cTCaracter = cTCaracter;
    }

    public int getcTCompleja() {
        return cTCompleja;
    }

    public void setcTCompleja(int cTCompleja) {
        this.cTCompleja = cTCompleja;
    }

    public int getcTBooleana() {
        return cTBooleana;
    }

    public void setcTBooleana(int cTBooleana) {
        this.cTBooleana = cTBooleana;
    }

    public int getcTNone() {
        return cTNone;
    }

    public void setcTNone(int cTNone) {
        this.cTNone = cTNone;
    }

    public int getcTLista() {
        return cTLista;
    }

    public void setcTLista(int cTLista) {
        this.cTLista = cTLista;
    }

    public int getcTRango() {
        return cTRango;
    }

    public void setcTRango(int cTRango) {
        this.cTRango = cTRango;
    }

    public int getcTVariant() {
        return cTVariant;
    }

    public void setcTVariant(int cTVariant) {
        this.cTVariant = cTVariant;
    }
    
    public LecturaSegunda2(){
        
    }
    
    public List<StrikerToken> getTokens() {
        return tokens;
    }
    
    public List<KaijuError> getErrores() {
        return errores;
    }
    
    public int getLinea() {
        return linea;
    }
    
    int linea;
    public void Caracter(JTextArea jta, JTable jTT, JTable jTE,JTable jTR, Connection c){
        this.c=c;
        try{
            Statement st=c.createStatement();
            String SQL="DELETE FROM Ambito2";
            st.executeUpdate(SQL);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("Hola mundo");
        lexico = new Lexico(jta, jTE, jTT);
        lexico.lexicoRun();
        tokens=lexico.getTokens();
        errores=lexico.getErrores();
        linea=lexico.getLinea();
        Sintaxis(jTE,jTR);
        System.out.println("YA SE ACABO");
    }
    
    public void Sintaxis(JTable jTE, JTable jTR){
        int estadoCelda = 0;
        int columna = 0;
        int produccionEstado =0;
        String descripcion="";
        cProg=0;
        cConst=0;
        cConstEnt=0;
        cListTupRa=0;
        cTerminoPas=0;
        cEleva=0;
        cSimplExpPas=0;
        cFact=0;
        cNot=0;
        cOR=0;
        cOPBIT=0;
        cAND=0;
        cANDLOG=0;
        cORLOG=0;
        cXORLOG=0;
        cEST=0;
        cASIGN=0;
        cFunList=0;
        cARR=0;
        cFunciones=0;
        cExpPas=0;
        
        
        //Area de Declaracion
        boolean AreaDec=true;
        boolean altaTabla=false;
        boolean actualizacionTabla=false;
        boolean yaDeclarada=false;
        boolean varDeclarada=false;
        boolean conjunto=true;
        int conjuntoDef=0;
        
        
        //Ambito
        ambito=0;
        Ambi = new Stack<Integer>();
        AuxAmbi = new Stack<Integer>();
        Ambi.push(ambito);
        int ambitoAnt=0;
        
        //Tipo de variable que ira a la tabla para su actualizacion
        int clasSimbol=0;
        
        //DATOS DE LA TABLA
        String id="";
        String idPar="";
        String tipo="";
        String clase="";
        int ambFun=0;
        int tArr=0;
        int dimArr=0;
        int noParr=0;
        int tParr=0;
        String tParrPar="";
        
        //SI SE DECLARA LA FUNCION
        boolean siHizo=false;
        //id del diccionario;
        String idDicc="";
        String idDiccAnt="";
        String idLlave="";
        //contador de tuplas
        int numTupla=0;
        //contadores de rango
        String intervalo1="";
        String intervalo2="";
        String avance="";
        //contadores de conjuntos
        int numConj=0;
        int banderaConj=0;
        int verga=0;
        //contadores de diccionarios
        int numDicc=0;
        String tipoDiccionario="";
        //Contador arreglo
        int contArr=0;
        String nomPar="";
        int contPar=0;
        
        //DATOS DE LOS ARRGELOS
        String tipo1="";
        String tipo2="";
        String tipo3="";
        String valor1="";
        String valor2="";
        String valor3="";
        boolean v9351=false;
        boolean v9352=false;
        boolean v9353=false;
        boolean menor=false;
        boolean errorDeclaracion=false;
        boolean mismoT=true;
        boolean arregloLista=true;
        boolean tipoTupla=false;
        //FUnciones
        boolean tipoFuncion=false;
        int contParr=0;
        int contParr2=0;
        int parametrosFuncion=0;
        boolean errorContFunciones=false;
        boolean masReturns=false;
        String tiposFuncion="";
        boolean bo953=false;
        int laFuncionTok=0;
        int laFuncionLin=0;
        String laFuncionLex="";
        boolean funcionPar=false;
        String nombreFP="";
        
        boolean errorUtilizacion=false;
        StrikerToken valor1_1=null;
        StrikerToken valor2_1=null;
        StrikerToken valor3_1=null;
        String valor0_2="";
        String valor1_2="";
        String valor2_2="";
        String valor3_2="";
        
        //SIMBOLOS DE ++ y --
        boolean masM=false;
        boolean menosM=false;
        
        //Diccionarios AREA EJECUCION
        boolean tipodiccionario=false;
        
        //PILAS DE SEMANTICA 1
        operandosSemantica = new LinkedList<StrikerToken>();
        operadoresSemantica = new LinkedList<StrikerToken>();
        listaSemantica = new LinkedList<SemanticaFase1>();
        
        operandosSemantica2 = new LinkedList<StrikerToken>();
        
        reglas = new LinkedList();
        
        
        DefaultTableModel modeloErrores = (DefaultTableModel) jTE.getModel();
        DefaultTableModel modeloReglas = (DefaultTableModel) jTR.getModel();
        PS = new Stack<Integer>();
        tokensSintx = new LinkedList<StrikerToken>();
        transformarALink();
        PS.push(-98);
        PS.push(701);
        int veces=0;
        tokensSintx.add(new StrikerToken(-98,linea,"$"));
        
        do{
            switch (PS.lastElement()){
                //Termino del area de declaracion
/**************/case 901:
                    AreaDec=false;
                    PS.pop();
                    break;
                //TVolver al area de declaracion
/**************/case 902:
                    try{        
                        Statement st=c.createStatement();
                        AreaDec=true;
                        if(!operandosSemantica2.isEmpty()){
                            if(masReturns){
                                String SQL="UPDATE Ambito2 SET Clase='Funcion' WHERE  Id = '"+operandosSemantica2.getLast().getLexema()+"' AND Ambito="+operandosSemantica2.getLast().getToken()+";";
                                st.executeUpdate(SQL);
                                operandosSemantica2.removeLast();
                            }
                            else{
                                reglasT(1150, "No Return", tokensSintx.getFirst().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.peek(), modeloReglas, jTR);
                                operandosSemantica2.removeLast();
                            }

                        }
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }    
                    tiposFuncion="";
                    masReturns=false;
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    Ambi.pop();
                    PS.pop();
                    break;
                //Declaracion de Funciones
/**************/case 903:
                    altaTabla=true;
                    clase="Procedimiento";
                    idPar=tokensSintx.getFirst().getLexema();
                    clasSimbol=2;
                    PS.pop();
                    break;
/**************/case 904:
                //Pila Nuevo Ambito
                    PS.pop();
                    break;
                //Pila Eliminar Ambito
/**************/case 905:
                    
                    PS.pop();
                    break;
                //Declaracion de variables
/**************/case 906:
                    altaTabla=true;
                    clase="Variable";
                    clasSimbol=1;
                    PS.pop();
                    break;
                //AREA DE INSERT VARIABLE
/**************/case 907:
                    if(AreaDec){
                        if(!varDeclarada){
                            if(!yaDeclarada){
                                if(!duplicados(id,Ambi.lastElement())){
                                    try{
                                        Statement st=c.createStatement();
                                        String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito)\n" +
                                            "VALUES ('"+id+"','"+tipo+"','"+clase+"',"+Ambi.lastElement()+")";
                                        //System.out.println(SQL);
                                        st.executeUpdate(SQL);
                                        reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else{
                                    descripcion="Variable Duplicada";
                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                        }
                    }
                    varDeclarada=false;
                    yaDeclarada=false;
                    altaTabla=false;
                    numTupla=0;
                    numConj=0;
                    numDicc=0;
                    id="";
                    tipo="";
                    clase="";
                    PS.pop();
                    break;
                //Declaracion de Parametros
/**************/case 908:
                    altaTabla=true;
                    clase="Parametro";
                    contPar++;
                    clasSimbol=3;
                    PS.pop();
                    break;
                //Declaracion de Parametros
/**************/case 909:
                    altaTabla=true;
                    clase="Parametros";
                    contPar++;
                    clasSimbol=3;
                    PS.pop();
                    break;
                //Tipos de parametros, variables o funciones
/**************/case 910:
                    if(AreaDec){
                        tipo="Decimal";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    
                    PS.pop();
                    break;
/**************/case 911:
                    if(AreaDec){
                        tipo="Binario";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    
                    PS.pop();
                    break;
/**************/case 912:
                    if(AreaDec){
                        tipo="Octal";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 913:
                    if(AreaDec){
                        tipo="Hexadecimal";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 914:
                    if(AreaDec){
                        tipo="Flotante";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 915:
                    if(AreaDec){
                        //System.out.println("1515151515151515151515151 "+Ambi.peek());
                        tipo="Cadena";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 916:
                    if(AreaDec){
                        tipo="Caracter";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 917:
                    if(AreaDec){
                        tipo="Compleja";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    break;
/**************/case 918:
                    if(AreaDec){
                        tipo="Booleana";
                        idLlave=tokensSintx.getFirst().getLexema();
                    }
//                    else{
//                        operandosSemantica.add(tokensSintx.getFirst());
//                    }
                    PS.pop();
                    break;
/**************/case 919:
                    if(AreaDec){
                        tipo="None";
                        idLlave=tokensSintx.getFirst().getLexema();
                        
                    }
                    else{
                        operandosSemantica.add(tokensSintx.getFirst());
                    }
                    PS.pop();
                    //76558291
                    break;
/**************/case 920:
                    if(AreaDec){
                        
                        //clase="Lista";
                        tipo="Struct";
                        varDeclarada=true;
                        idPar=id;
                        if(!duplicados(id,Ambi.lastElement())){
                            try{
                                Statement st=c.createStatement();
                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Ambito)\n" +
                                    "VALUES ('"+id+"','"+tipo+"',"+Ambi.lastElement()+")";
                                st.executeUpdate(SQL);
//                                ambitoAnt=Ambi.peek();
//                                ambito++;
//                                Ambi.push(ambito);
//                                SQL="UPDATE Ambito2 SET AmbitoCrea = "+Ambi.lastElement()+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
//                                System.out.println(SQL);
//                                st.executeUpdate(SQL);
                            reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Puto el que lo lea", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            descripcion="Variable Duplicada";
                            errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
                            jTE.setModel(  modeloErrores);
                            reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            yaDeclarada=true;
                        }
                    }
                    else if(!AreaDec){
                        switch(operandosSemantica.getLast().getToken()){
                            case -99:
                                break;
                            case -100:
                                arregloLista=false;
                                break;
                            case -101:
                                tipoTupla=true;
                                break;
                            case -104:
                                tipodiccionario=true;
                                arregloLista=false;
                                break;
                            default:
                                errorUtilizacion=true;
                                break;
                        }
                    }
//                    else{
//                        System.out.println("DUPLICADAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAirwbjdivbiwujerdsviuerwsndiuvcbnws");
//                        if(!variableDeclarada(id,Ambi)){
//                                
//                                descripcion="Variable No Declarada";
//                                errores.add(new KaijuError(tokensSintx.getFirst().getLine(),833,descripcion,tipoAmbito,tokensSintx.getFirst().getLexema()));                        
//                                modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),833,descripcion,tipoAmbito,tokensSintx.getFirst().getLexema()});
//                                jTE.setModel(modeloErrores);
//                            }
//                    }
                    
                    PS.pop();
                    break;
/**************/case 921:
//                    UPDATE Customers
//SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
                    
//WHERE CustomerID = 1;
                    if(duplicados(idPar,ambitoAnt)){
                        //System.out.println("AJAJAJAJAJAJAJAAAAAAAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJJJJJAAAAAAAAAAAAAAAAAAAAAAAA");
                        try{
                            Statement st=c.createStatement();
                            String SQL="UPDATE Ambito2 SET NoParr = "+contPar+", AmbitoCrea="+Ambi.lastElement()+" WHERE  Id = '"+tParrPar+"' AND Ambito="+ambitoAnt+";";
                            //System.out.println(SQL);
                            st.executeUpdate(SQL);
                            operandosSemantica2.add(new StrikerToken(ambitoAnt,0,tParrPar));
                            
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    noParr=0;
                    contPar=0;
                    PS.pop();
                    break;
/**************/case 922:
                    if (AreaDec){
                        if(!duplicados(id,Ambi.lastElement())){
                            try{
                                varDeclarada=true;
                                Statement st=c.createStatement();
                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito)\n" +
                                    "VALUES ('"+id+"','Struct','Tupla',"+Ambi.lastElement()+")";
                                //System.out.println(SQL);
                                st.executeUpdate(SQL);
                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                ambitoAnt=Ambi.lastElement();
                                ambito++;
                                Ambi.push(ambito);
                                SQL="UPDATE Ambito2 SET AmbitoCrea = "+Ambi.lastElement()+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                st.executeUpdate(SQL);
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            descripcion="Variable Duplicada";
                            errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
                            jTE.setModel(modeloErrores);
                            reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            yaDeclarada=true;
                        }
                    }
                    altaTabla=false;
                    tipo="";
                    clase="";
                    PS.pop();
                    break;
/**************/case 923:
                    if(AreaDec){
                        if(!yaDeclarada){
                            try{
                                Statement st=c.createStatement();
                                String SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                    "VALUES ('"+tipo+"','DatoTupla',"+Ambi.lastElement()+","+numTupla+",'"+id+"')";
                                //System.out.println(SQL);
                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                st.executeUpdate(SQL);numTupla++;
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    PS.pop();
                    break;
/**************/case 924:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(duplicados(id,ambitoAnt)){
                                try{
                                    Statement st=c.createStatement();
                                    String SQL="UPDATE Ambito2 SET TamArr = "+numTupla+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                    //System.out.println(SQL);
                                    st.executeUpdate(SQL);
                                    Ambi.pop();
                                }catch(SQLException e){
                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                    numTupla=0;
                    PS.pop();
                    break;
/**************/case 925:
                    if(AreaDec){
                        if(!duplicados(id,Ambi.lastElement())){
                            try{
                                varDeclarada=true;
                                Statement st=c.createStatement();
                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito)\n" +
                                    "VALUES ('"+id+"','Struct','Rango',"+Ambi.lastElement()+")";
                                //System.out.println(SQL);
                                st.executeUpdate(SQL);
                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            descripcion="Variable Duplicada";
                            errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
                            jTE.setModel(modeloErrores);
                            reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            yaDeclarada=true;
                        }
                    }
                    else{
                    }
                    tipo="";
                    clase="";
                    PS.pop();
                    break;
/**************/case 926:
                    
                    if(!yaDeclarada){
                        if(menor){
                            intervalo1="-"+tokensSintx.getFirst().getLexema();
                        }
                        else{
                            intervalo1=tokensSintx.getFirst().getLexema();
                        }
                    }
                    PS.pop();
                    
                    break;
/**************/case 927:
                    
                    if(!yaDeclarada){
                        if(menor){
                            intervalo2="-"+tokensSintx.getFirst().getLexema();
                        }
                        else{
                            intervalo2=tokensSintx.getFirst().getLexema();
                        }
                        if(intervalo1.equals(intervalo2)){
                            reglasT(1162, "D", tokensSintx.getFirst().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            intervalo2="TV";
                        }
                    }
                    PS.pop();
                    break;
/**************/case 928:
                    if(!yaDeclarada){
                        if(menor){
                            avance="-"+tokensSintx.getFirst().getLexema();
                        }
                        else{
                            avance=tokensSintx.getFirst().getLexema();
                        }
                        if(intervalo1.equals(intervalo2)){
                            reglasT(1162, "D", tokensSintx.getFirst().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            intervalo2="TV";
                        }
                    }
                    PS.pop();
                    
                    break;
/**************/case 929:
                    if(AreaDec){
                        if(!duplicados(id,Ambi.lastElement())){
                            try{
                                varDeclarada=true;
                                Statement st=c.createStatement();
                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Ambito)\n" +
                                    "VALUES ('"+id+"','Struct',"+Ambi.lastElement()+")";
                                //System.out.println(SQL);
                                st.executeUpdate(SQL);
                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                ambitoAnt=Ambi.lastElement();
                                ambito++;
                                Ambi.push(ambito);
                                SQL="UPDATE Ambito2 SET AmbitoCrea = "+Ambi.lastElement()+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                //System.out.println(SQL);
                                st.executeUpdate(SQL);
                                idDicc=tokensSintx.getFirst().getLexema();
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            descripcion="Variable Duplicada";
                            errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
                            jTE.setModel(modeloErrores);
                            reglasT(1130, "Par", id, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            yaDeclarada=true;
                        }
                    }
                    //System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW "+Ambi.peek());
                    altaTabla=false;
                    tipo="";
                    clase="";
                    PS.pop();
                    break;
/**************/case 930:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(conjuntoDef==0 || conjuntoDef==1){
                                conjuntoDef=1;
                                conjunto=false;
                                clase="datoDic";
                                if(!duplicados(id,Ambi.lastElement())){
                                    try{
                                        Statement st=c.createStatement();

                                        String SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                            "VALUES ('"+tipo+"','"+clase+"',"+Ambi.lastElement()+",'"+idDicc+"',"+numDicc+",'"+id+"')";
                                        //System.out.println(SQL);
                                        st.executeUpdate(SQL);
                                        numDicc++;
                                        tipoDiccionario=tipo;
                                        idDiccAnt=idDicc;
                                        idDicc=tokensSintx.getFirst().getLexema();
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }if(conjuntoDef==1){
                                    //System.out.println("DAME EL PUTO LEXEMA ALV "+tokensSintx.getFirst().getLexema());
                                    idDicc=tokensSintx.get(2).getLexema();
                                    }
                                }
                                if(conjuntoDef==1){
                                    //System.out.println("DAME EL PUTO LEXEMA ALV "+tokensSintx.getFirst().getLexema());
                                    idDicc=tokensSintx.get(2).getLexema();
                                }
                            }
                        }
                    }
                    
                    
                    PS.pop();
                    break;
/**************/case 931:
                    if(!yaDeclarada){
                        if(!duplicados(id,Ambi.lastElement())){
                            try{
                                Statement st=c.createStatement();
                                String SQL="UPDATE Ambito2 SET Llave = '"+idLlave+"' WHERE  Tipo = '"+tipoDiccionario+"' AND Clase='"+clase+"' AND Ambito="+Ambi.lastElement()+" AND Valor='"+idDiccAnt+"' AND NomPosicion="+(numDicc-1)+" AND ListaPertenece='"+id+"';";

                                //System.out.println(SQL);
                                st.executeUpdate(SQL);
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    PS.pop();
                    break;
/**************/case 932:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(banderaConj<=1){
                                //System.out.println("PASASTE POR AQUI PENDEJOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                                if(conjuntoDef==0||conjuntoDef==2){
                                    conjuntoDef=2;
                                    clase="datoConj";
                                    //System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!");
                                    if(duplicados(id,ambitoAnt)){
                                        //System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA?????????????????");
                                        try{
                                            //System.out.println("EL AMBITO AQUI ES = :"+Ambi.lastElement());
                                            Statement st=c.createStatement();
                                            String SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                "VALUES ('"+tipo+"','"+clase+"',"+Ambi.lastElement()+","+numConj+",'"+id+"')";
                                            //System.out.println(SQL);
                                            st.executeUpdate(SQL);
                                            numConj++;
                                        }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                banderaConj++;
                            }
                        }
                    }
                    else{
                        banderaConj--;
                    }
                    PS.pop();
                    break;
/**************/case 933:
                    if(!yaDeclarada){
                        if(duplicados(id,ambitoAnt)){
                        
                            switch(conjuntoDef){
                                case 0:
                                    try{
                                        Statement st=c.createStatement();
                                        String SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                            "VALUES ('"+tipo+"','datoConj',"+Ambi.lastElement()+","+numConj+",'"+id+"')";
                                        //System.out.println(SQL);
                                        st.executeUpdate(SQL);
                                        numConj++;
                                        SQL="UPDATE Ambito2 SET Clase='Conjunto', TamARR = "+numConj+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                        st.executeUpdate(SQL);
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                case 1:
                                    try{
                                        Statement st=c.createStatement();
                                        String SQL="UPDATE Ambito2 SET Clase='Diccionario', TamARR = "+numDicc+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                        st.executeUpdate(SQL);
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                case 2:
                                    if(!duplicados(id,Ambi.lastElement())){
                                        try{
                                            Statement st=c.createStatement();
                                            String SQL="UPDATE Ambito2 SET Clase='Conjunto', TamARR = "+numConj+" WHERE  Id = '"+id+"' AND Ambito="+ambitoAnt+";";
                                            st.executeUpdate(SQL);
                                        }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    break;
                            }
                            banderaConj=0;
                            conjuntoDef=0;
                            Ambi.pop();
                        }
                    }
                    
                    PS.pop();
                    break;
/**************/case 934:
                    PS.pop();
                    break;
/**************/case 935:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(!errorDeclaracion){
                                contArr++;
                                switch(contArr){
                                    case 1:
                                        if(!menor){
                                            valor1=idLlave;
                                        }
                                        else{
                                            valor1="-"+idLlave;
                                            if(!tipo.equals("Decimal")){
                                                v9351=true;
                                            }
                                        }
                                        tipo1=tipo;
                                        break;
                                    case 2:
                                        if(!menor){
                                            valor2=idLlave;
                                        }
                                        else{
                                            valor2="-"+idLlave;
                                            if(!tipo.equals("Decimal")){
                                                v9352=true;
                                            }
                                        }
                                        if(!tipo1.equals(tipo)){
                                            mismoT=false;
                                        }
                                        tipo2=tipo;
                                        break;
                                    case 3:
                                        if(!menor){
                                            valor3=idLlave;
                                        }
                                        else{
                                            valor3="-"+idLlave;
                                            if(!tipo.equals("Decimal")){
                                                v9353=true;
                                            }
                                        }
                                        if(tipo1.equals(tipo)){
                                            if(!tipo2.equals(tipo)){
                                                mismoT=false;
                                                tipo3=tipo;
                                            }
                                            else{
                                                tipo3=tipo;
                                                if(tipo1.equals("Decimal") && tipo2.equals("Decimal") && tipo3.equals("Decimal")){
                                                    if(valor1.charAt(0)=='-'){
                                                        if(valor2.charAt(0)=='-'){
                                                            if(valor3.charAt(0)=='-'){
                                                            
                                                            }
                                                            else{
                                                                v9351=true;
                                                                v9352=true;
                                                                reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                        }
                                                        else{
                                                            v9351=true;
                                                            if(Integer.parseInt(valor2)>0){
                                                                reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                            else if(Integer.parseInt(valor2)==0){
                                                                if(valor3.charAt(0)=='-'){
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    v9353=true;
                                                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                    jTE.setModel(modeloErrores);
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                            else{
                                                                v9352=true;
                                                                reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(Integer.parseInt(valor1)>0){
                                                            if(valor2.charAt(0)=='-'){
                                                                v9352=true;
                                                                if(valor3.charAt(0)=='-'){
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    v9353=true;
                                                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                    jTE.setModel(modeloErrores);
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                            else{
                                                                if(Integer.parseInt(valor1)>Integer.parseInt(valor2)){
                                                                    if(valor3.charAt(0)=='-'){
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        v9353=true;
                                                                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                    jTE.setModel(modeloErrores);
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                                else if(Integer.parseInt(valor1)<Integer.parseInt(valor2)){
                                                                    if(valor3.charAt(0)=='-'){
                                                                        v9353=true;
                                                                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                        jTE.setModel(modeloErrores);
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                                else{
                                                                    v9351=true;
                                                                    v9352=true;
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                            
                                                        }
                                                        else if(Integer.parseInt(valor1)<0){
                                                            v9351=true;
                                                            if(valor2.charAt(0)=='-'){
                                                                v9352=true;
                                                                reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                            else{
                                                                if(Integer.parseInt(valor2)>0){
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else if(Integer.parseInt(valor2)==0){
                                                                    if(valor3.charAt(0)=='-'){
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        v9353=true;
                                                                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                        jTE.setModel(modeloErrores);
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                                else{
                                                                    v9353=true;
                                                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                    jTE.setModel(modeloErrores);
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(valor2.charAt(0)=='-'){
                                                                v9352=true;
                                                                if(valor3.charAt(0)=='-'){
                                                                    v9353=true;
                                                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                    jTE.setModel(modeloErrores);
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                
                                                            }
                                                            else{
                                                                if(Integer.parseInt(valor2)>0){
                                                                    if(valor3.charAt(0)=='-'){
                                                                        v9353=true;
                                                                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3));                        
                                                                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en el desplazamiento","Semantica 2",valor3});
                                                                        jTE.setModel(modeloErrores);
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                                else if(Integer.parseInt(valor2)<0){
                                                                    v9351=true;
                                                                    v9352=true;
                                                                    reglasT(1031, tipo3, valor3, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else{
                                            mismoT=false;
                                            tipo3=tipo;
                                        }
                                        break;
                                    }
                                    menor=false;
                                    
//                            try{
//                                Statement st=c.createStatement();
//                                String SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
//                                    "VALUES ('"+tipo+"','DatoLista',"+Ambi.lastElement()+","+contArr+",'"+id+"')";
//                                System.out.println(SQL);
//                                contArr++;
//                                st.executeUpdate(SQL);
//                            }catch(SQLException e){
//                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
//                            }
                            }
                        }
                    }
                    else{
                        
                        contArr++;
                        if(!errorUtilizacion){
                            switch(contArr){
                                case 1:
                                        if(menor){
                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"));                        
                                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"});
                                            jTE.setModel(modeloErrores);
                                            valor1_1=operandosSemantica.getLast();
                                            operandosSemantica.removeLast();
                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                            errorUtilizacion=true;
                                        }
                                        else{
                                            if(!tipodiccionario){
                                                if(operandosSemantica.getLast().getToken()==-4){
                                                    valor1_1=operandosSemantica.getLast();
                                                    reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                }
                                                else{
                                                    valor1_1=operandosSemantica.getLast();
                                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                    jTE.setModel(modeloErrores);
                                                    reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                    errorUtilizacion=true;
                                                    operandosSemantica.removeLast();
                                                    operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                }
                                            }
                                            else{
                                                if(operandosSemantica.getLast().getToken()==-8 || operandosSemantica.getLast().getToken()==-9){
                                                    valor1_1=operandosSemantica.getLast();
                                                }
                                                else{
                                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Valor no cadena","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Valor no cadena","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                    jTE.setModel(modeloErrores);
                                                    valor1_1=operandosSemantica.getLast();
                                                    errorUtilizacion=true;
                                                    operandosSemantica.removeLast();
                                                    operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                }
                                            }
                                        }

                                    break;
                                case 2:
                                    if(!tipodiccionario){
                                        if(menor){
                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"));                        
                                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"});
                                            jTE.setModel(modeloErrores);
                                            valor2_1=operandosSemantica.getLast();
                                            errorUtilizacion=true;
                                            operandosSemantica.removeLast();
                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                        }
                                        else{
                                            if(operandosSemantica.getLast().getToken()==-4){
                                                valor2_1=operandosSemantica.getLast();
                                                reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                            }
                                            else{
                                                errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                jTE.setModel(modeloErrores);
                                                valor2_1=operandosSemantica.getLast();
                                                reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                errorUtilizacion=true;
                                                operandosSemantica.removeLast();
                                                operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                            }
                                        }
                                    }
                                    else{
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Solo es una dimencion el diccionario","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Solo es una dimencion el diccionario","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        valor2_1=operandosSemantica.getLast();
                                        errorUtilizacion=true;
                                        operandosSemantica.removeLast();
                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    break;
                                case 3:
                                    if(!tipodiccionario){
                                        if(menor){
                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"));                        
                                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Aparicion de menos en area de ejecucion","Semantica 2","-"});
                                            jTE.setModel(modeloErrores);
                                            valor3_1=operandosSemantica.getLast();
                                            errorUtilizacion=true;
                                            operandosSemantica.removeLast();
                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                        }
                                        else{
                                            if(operandosSemantica.getLast().getToken()==-4){
                                                valor3_1=operandosSemantica.getLast();
                                                reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);

                                            }
                                            else{
                                                errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Valor no decimal","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                jTE.setModel(modeloErrores);
                                                valor3_1=operandosSemantica.getLast();
                                                reglasT(1040, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                errorUtilizacion=true;
                                                operandosSemantica.removeLast();
                                                operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                            }
                                        }
                                    }
                                    else{
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Solo es una dimencion el diccionario","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Solo es una dimencion el diccionario","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        valor3_1=operandosSemantica.getLast();
                                        errorUtilizacion=true;
                                        operandosSemantica.removeLast();
                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    break;
                            }
                            menor=false;
                        }
                    }
                    PS.pop();
                    break;
/**************/case 936:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(!errorDeclaracion){
                                if(duplicados(idPar,Ambi.lastElement())){
                                    try{
                                        Statement st=c.createStatement();
                                        int dimensiones=0;
                                        if(mismoT){
                                                
                                            String SQL="UPDATE Ambito2 SET Clase = 'Lista-Arr', TamArr = "+contArr+", Llave= '"+tipo1+"' WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                            
                                            st.executeUpdate(SQL);
                                            switch(contArr){
                                                case 1:
                                                    if(tipo1.equals("Decimal")){
                                                        if(v9351){
                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                            "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                            st.executeUpdate(SQL);
                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                        else{
                                                            if(valor1.charAt(0)=='-'){
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                st.executeUpdate(SQL);
//                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                            else{

                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                st.executeUpdate(SQL);
//                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(!v9351){
                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                            "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                            st.executeUpdate(SQL);
//                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                        else{
                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                            "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                            st.executeUpdate(SQL);
//                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    if(tipo1.equals("Decimal") && tipo2.equals("Decimal")){
                                                        if(v9351){
                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                            "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                            if(v9352){
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                st.executeUpdate(SQL);
//                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            }
                                                            else{
                                                                if(valor2.charAt(0)=='-'){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                    st.executeUpdate(SQL);
//                                                                    reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                    st.executeUpdate(SQL);
//                                                                    reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(valor1.charAt(0)=='-'){
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                if(v9352){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                    st.executeUpdate(SQL);
//                                                                    reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    if(valor2.charAt(0)=='-'){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                if(v9352){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                    st.executeUpdate(SQL);
//                                                                    reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    if(valor2.charAt(0)=='-'){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 2 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                        "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                        st.executeUpdate(SQL);
                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                        st.executeUpdate(SQL);
                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                        st.executeUpdate(SQL);
//                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                    }
                                                    break;
                                                case 3:
                                                    if(tipo1.equals("Decimal") && tipo2.equals("Decimal") && tipo3.equals("Decimal")){
                                                        if(v9351){
                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                            "VALUES ("+ambitoAnt+",'TV',1,'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                            if(v9352){
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                if(v9353){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                    st.executeUpdate(SQL);
//                                                                    reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                }
                                                                else{
                                                                    if(valor3.charAt(0)=='-'){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(valor2.charAt(0)=='-'){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    if(v9353){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        if(valor3.charAt(0)=='-'){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    if(v9353){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        if(valor3.charAt(0)=='-'){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(valor1.charAt(0)=='-'){
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                if(v9352){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    if(v9353){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        if(valor3.charAt(0)=='-'){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(valor2.charAt(0)=='-'){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        if(v9353){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            if(valor3.charAt(0)=='-'){
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                            else{
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                        }
                                                                    }
                                                                    else{
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        if(v9353){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            if(valor3.charAt(0)=='-'){
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                            else{
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 3 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                                st.executeUpdate(SQL);
                                                                if(v9352){
                                                                    SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                    "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                    st.executeUpdate(SQL);
                                                                    if(v9353){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                        st.executeUpdate(SQL);
//                                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                    }
                                                                    else{
                                                                        if(valor3.charAt(0)=='-'){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(valor2.charAt(0)=='-'){
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'TV',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        if(v9353){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            if(valor3.charAt(0)=='-'){
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                            else{
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                        }
                                                                    }
                                                                    else{
                                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                                        st.executeUpdate(SQL);
                                                                        if(v9353){
                                                                            SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                            "VALUES ("+ambitoAnt+",'TV',3,'"+idPar+"')";
                                                                            st.executeUpdate(SQL);
                                                                            SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                            st.executeUpdate(SQL);
//                                                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                        }
                                                                        else{
                                                                            if(valor3.charAt(0)=='-'){
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                            else{
                                                                                SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                                                "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                                                st.executeUpdate(SQL);
                                                                                SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                                                st.executeUpdate(SQL);
//                                                                                reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                        "VALUES ("+ambitoAnt+",'"+valor1+"',1,'"+idPar+"')";
                                                        st.executeUpdate(SQL);
                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                        "VALUES ("+ambitoAnt+",'"+valor2+"',2,'"+idPar+"')";
                                                        st.executeUpdate(SQL);
                                                        SQL="INSERT INTO Ambito2 (Ambito, Valor, NomPosicion, ListaPertenece)\n" +
                                                        "VALUES ("+ambitoAnt+",'"+valor3+"',3,'"+idPar+"')";
                                                        st.executeUpdate(SQL);
                                                        SQL="UPDATE Ambito2 SET NoParr = 1 WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                                        st.executeUpdate(SQL);
//                                                        reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                    }
                                                    break;
                                            }
                                        }
                                        else{
                                            reglasT(1130, "Id", idPar, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                            ambitoAnt=Ambi.peek();
                                            ambito++;
                                            Ambi.push(ambito);
                                            String SQL="UPDATE Ambito2 SET Clase='Lista', AmbitoCrea = "+Ambi.lastElement()+" WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                            //System.out.println(SQL);
                                            st.executeUpdate(SQL);
                                            SQL="UPDATE Ambito2 SET TamArr = "+contArr+" WHERE  Id = '"+idPar+"' AND Ambito="+Ambi.lastElement()+";";
                                            //System.out.println(SQL);
                                            st.executeUpdate(SQL);
                                            
                                            for (int i = 0; i < contArr; i++) {
                                                switch(i){
                                                    case 0:
                                                        if(!v9351){
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('"+tipo1+"','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        else{
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('None','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        break;
                                                    case 1:
                                                        if(!v9352){
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('"+tipo2+"','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        else{
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('None','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        break;
                                                    case 2:
                                                        if(!v9352){
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('"+tipo3+"','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        else{
                                                            SQL="INSERT INTO Ambito2 (Tipo, Clase, Ambito, NomPosicion, ListaPertenece)\n" +
                                                                "VALUES ('None','DatoLista',"+Ambi.lastElement()+","+(i+1)+",'"+idPar+"')";
                                                            st.executeUpdate(SQL);
                                                        }
                                                        break;
                                                }
                                            }
                                            Ambi.pop();
                                        }  
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "PUTA MADRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                            else{
                                Statement st;
                                try {
                                    st = c.createStatement();
                                    String SQL="DELETE FROM Ambito2 WHERE  Id = '"+idPar+"' AND Ambito="+ambitoAnt+";";
                                    st.executeUpdate(SQL);
                                    errores.add(new KaijuError(tokensSintx.getFirst().getLine(),850,"Error en declaracion de arreglo",tipoSintactico,idPar));                        
                                    modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en declaracion de arreglo",tipoSintactico,idPar});
                                    jTE.setModel(modeloErrores);
                                } 
                                catch (SQLException ex) {
                                    Logger.getLogger(LecturaSegunda2.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    else{
                        if(!errorUtilizacion){
                            if(!arregloLista){
                                try{
                                    ResultSet rs;
                                    switch(contArr){
                                        case 1:
                                            operandosSemantica.removeLast();
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            int dimAr=0;
                                            String tipoArr="";
                                            int tamArr=0;
                                            int ambitoArr=0;
                                            boolean seEncontroDato=false;
                                            do{
                                                int ultimoAmbi=AuxAmbi.pop();
                                                String SQL= "SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                rs = ConsultasTabla.getTabla(SQL);
                                                while (rs.next() && !seEncontroDato) {
                                                    seEncontroDato=true;
                                                    dimAr=rs.getInt("NoParr");
                                                    tipoArr=rs.getString("Llave");
                                                    tamArr=rs.getInt("TamArr");
                                                    if(!tipodiccionario){
                                                        if(dimAr==1){
                                                            reglasT(1030, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                            String valorValido=valor1_1.getLexema();
                                                            if(!(valorValido.equals("TD")) && valorNumerico(valorValido)){
                                                                int valorLex=Integer.parseInt(valorValido);
                                                                if(tipoArr.equals("Decimal")){
                                                                    SQL= "SELECT * FROM Ambito2 WHERE ListaPertenece='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                                    rs = ConsultasTabla.getTabla(SQL);
                                                                    int i = 0;
                                                                    boolean correcto=true;
                                                                    String valor;
                                                                    while(rs.next()){
                                                                        
                                                                        i++;
                                                                        switch(i){
                                                                            case 1:
                                                                                if((!rs.getString("Valor").equals("TV"))){
                                                                                    valor=rs.getString("Valor");
                                                                                    if(valor.charAt(0)=='-'){
                                                                                        String valorOriginal="";
                                                                                        for (int j = i; j < valor.length(); j++) {
                                                                                            valorOriginal+=valor.charAt(i);
                                                                                        }
                                                                                        if(valorLex>=0 && valorLex<Integer.parseInt(valorOriginal)){

                                                                                        }
                                                                                        else{
                                                                                            correcto=false;
                                                                                        }
                                                                                    }
                                                                                    else{
                                                                                        if(!(valorLex>=rs.getInt("Valor"))){
                                                                                            correcto=false;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 2:
                                                                                if((!rs.getString("Valor").equals("TV"))){
                                                                                    if(!(valorLex<rs.getInt("Valor"))){
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                break;
                                                                        }
                                                                    }
                                                                    if(correcto){
                                                                        metodoCorrecto(modeloReglas, jTR, -4,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Binario")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -5,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Octal")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -7,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Hexadecimal")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -6,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Flotante")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -10,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Cadena")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -8,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Caracter")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -12,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Compleja")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -11,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                                else if(tipoArr.equals("Booleana")){
                                                                    if(Integer.parseInt(valor1_1.getLexema())<tamArr){
                                                                        metodoCorrecto(modeloReglas, jTR, -105,valor1_1, "True", "True");
                                                                    }
                                                                    else{
                                                                        metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(tipoArr.equals("Binario")){
                                                                    metodoCorrecto(modeloReglas, jTR, -5,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Octal")){
                                                                    metodoCorrecto(modeloReglas, jTR, -7,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Hexadecimal")){
                                                                    metodoCorrecto(modeloReglas, jTR, -6,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Flotante")){
                                                                    metodoCorrecto(modeloReglas, jTR, -10,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Cadena")){
                                                                    metodoCorrecto(modeloReglas, jTR, -8,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Compleja")){
                                                                    metodoCorrecto(modeloReglas, jTR, -11,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Booleana")){
                                                                    metodoCorrecto(modeloReglas, jTR, -105,valor1_1, "True", "True");
                                                                }
                                                                else if(tipoArr.equals("Decimal")){
                                                                    metodoCorrecto(modeloReglas, jTR, -4,valor1_1, "True", "True");
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error de dimensiones","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error de dimensiones","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                            jTE.setModel(modeloErrores);
                                                            String nombreArr=operandosSemantica.getLast().getLexema();
                                                            operandosSemantica.removeLast();
                                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                                            reglasT(1130, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                    }
                                                    else{
                                                        String valorValido=valor1_1.getLexema();
                                                        SQL= "SELECT * FROM Ambito2 WHERE ListaPertenece='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+(ultimoAmbi+1);
                                                        rs = ConsultasTabla.getTabla(SQL);
                                                        boolean encontrado=false;
                                                        while(rs.next() && !seEncontroDato){
                                                            seEncontroDato=true;
                                                            StrikerToken sts=null;
                                                            if((rs.getString("Llave").equals("TV")) || valor1_1.getLexema().equals(rs.getString("Llave"))){
                                                                
                                                                if(rs.getString("Tipo").equals("None")){
                                                                    operandosSemantica.getLast().setToken(-53);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Decimal")){
                                                                    operandosSemantica.getLast().setToken(-4);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Flotante")){
                                                                    operandosSemantica.getLast().setToken(-10);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Cadena")){
                                                                    operandosSemantica.getLast().setToken(-9);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Caracter")){
                                                                    operandosSemantica.getLast().setToken(-12);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Binario")){
                                                                    operandosSemantica.getLast().setToken(-5);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Hexadecimal")){
                                                                    operandosSemantica.getLast().setToken(-6);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Octal")){
                                                                    operandosSemantica.getLast().setToken(-7);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Compleja")){
                                                                    operandosSemantica.getLast().setToken(-11);
                                                                }
                                                                else if(rs.getString("Tipo").equals("Booleana")){
                                                                    operandosSemantica.getLast().setToken(-105);
                                                                }
                                                                encontrado=true;
                                                            } 
                                                        }
                                                        if(encontrado){
                                                            reglasT(1060, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                        else{
                                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error de diccionario","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                            jTE.setModel(modeloErrores);
                                                            String nombreArr=operandosSemantica.getLast().getLexema();
                                                            operandosSemantica.removeLast();
                                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                                            reglasT(1060, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                        }
                                                    }
                                                }
                                            }while(!AuxAmbi.isEmpty() && !seEncontroDato);
                                            break;
                                        case 2:
                                            operandosSemantica.removeLast();
                                            operandosSemantica.removeLast();
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            ResultSet rs2;
                                            int dimAr2=0;
                                            String tipoArr2="";
                                            int tamArr2=0;
                                            boolean seEncontroDato2=false;
                                            do{
                                                int ultimoAmbi=AuxAmbi.pop();
                                                String SQL= "SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                rs = ConsultasTabla.getTabla(SQL);
                                                
                                                while (rs.next()) {
                                                    dimAr2=rs.getInt("NoParr");
                                                    tipoArr2=rs.getString("Llave");
                                                    tamArr2=rs.getInt("TamArr");
                                                    if(dimAr2==2){
                                                        reglasT(1030, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        String valorValido1=valor1_1.getLexema();
                                                        String valorValido2=valor2_1.getLexema();
                                                        if((!(valorValido1.equals("TD"))) && (!(valorValido2.equals("TD"))) && valorNumerico(valorValido1) && valorNumerico(valorValido2)){
                                                            int valorLex1=Integer.parseInt(valorValido1);
                                                            int valorLex2=Integer.parseInt(valorValido2);
                                                            if(tipoArr2.equals("Decimal")){
                                                                SQL= "SELECT * FROM Ambito2 WHERE ListaPertenece='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                                rs = ConsultasTabla.getTabla(SQL);
                                                                int i = 0;
                                                                boolean correcto=true;
                                                                String valor11;
                                                                String valor21;
                                                                while(rs.next()){
                                                                    seEncontroDato2=true;
                                                                    i++;
                                                                    switch(i){
                                                                        case 1:
                                                                            if((!rs.getString("Valor").equals("TV"))){
                                                                                valor11=rs.getString("Valor");
                                                                                if(valor11.charAt(0)=='-'){
                                                                                    String valorOriginal1="";
                                                                                    for (int j = 1; j < valor11.length(); j++) {
                                                                                        valorOriginal1+=valor11.charAt(j);
                                                                                    }
                                                                                    if(valorLex1>=0 && valorLex1<Integer.parseInt(valorOriginal1)){
                                                                                        
                                                                                    }
                                                                                    else{
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    correcto=false;
                                                                                    
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            if((!rs.getString("Valor").equals("TV"))){
                                                                                valor21=rs.getString("Valor");
                                                                                if(valor21.charAt(0)=='-'){
                                                                                    String valorOriginal2="";
                                                                                    for (int j = 1; j < valor21.length(); j++) {
                                                                                        valorOriginal2+=valor21.charAt(j);
                                                                                    }
                                                                                    if(valorLex2>=0 && valorLex2<Integer.parseInt(valorOriginal2)){
                                                                                        
                                                                                    }
                                                                                    else{
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    correcto=false;
                                                                                    
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                    switch(i){
                                                                        case 1:
                                                                            if(correcto){
                                                                                metodoCorrecto(modeloReglas, jTR, -4,valor1_1, "True", "True");
                                                                            }
                                                                            else{
                                                                                metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            if(correcto){
                                                                                metodoCorrecto(modeloReglas, jTR, -4,valor2_1, "True", "True");
                                                                            }
                                                                            else{
                                                                                metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                            }
                                                                            break;
                                                                    }
                                                                    correcto=true;
                                                                    
                                                                }
                                                                
                                                            }
                                                            else if(tipoArr2.equals("Binario")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -5,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Octal")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -7,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Hexadecimal")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -6,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Flotante")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -10,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Cadena")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -8,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Caracter")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -12,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Compleja")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -11,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr2.equals("Booleana")){
                                                                if(Integer.parseInt(valor2_1.getLexema())<tamArr2){
                                                                    metodoCorrecto(modeloReglas, jTR, -105,valor2_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(tipoArr2.equals("Binario")){
                                                                metodoCorrecto(modeloReglas, jTR, -5,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Octal")){
                                                                metodoCorrecto(modeloReglas, jTR, -7,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Hexadecimal")){
                                                                metodoCorrecto(modeloReglas, jTR, -6,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Flotante")){
                                                                metodoCorrecto(modeloReglas, jTR, -10,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Cadena")){
                                                                metodoCorrecto(modeloReglas, jTR, -8,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Compleja")){
                                                                metodoCorrecto(modeloReglas, jTR, -11,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Booleana")){
                                                                metodoCorrecto(modeloReglas, jTR, -105,valor2_1, "True", "True");
                                                            }
                                                            else if(tipoArr2.equals("Decimal")){
                                                                metodoCorrecto(modeloReglas, jTR, -4,valor2_1, "True", "True");
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en dimension","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en dimension","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                        jTE.setModel(modeloErrores);
                                                        String nombreArr=operandosSemantica.getLast().getLexema();
                                                        operandosSemantica.removeLast();
                                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                                        reglasT(1030, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                    }
                                                }
                                            }while(!AuxAmbi.isEmpty() && !seEncontroDato2);
                                            break;
                                        case 3:
                                            operandosSemantica.removeLast();
                                            operandosSemantica.removeLast();
                                            operandosSemantica.removeLast();
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            int dimAr3=0;
                                            String tipoArr3="";
                                            int tamArr3=0;
                                            boolean seEncontroDato3=false;
                                            do{
                                                int ultimoAmbi=AuxAmbi.pop();
                                                String SQL= "SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                rs = ConsultasTabla.getTabla(SQL);
                                                while (rs.next()) {
                                                    dimAr3=rs.getInt("NoParr");
                                                    tipoArr3=rs.getString("Llave");
                                                    tamArr3=rs.getInt("TamArr");
                                                    if(dimAr3==3){
                                                        String valorValido1=valor1_1.getLexema();
                                                        String valorValido2=valor2_1.getLexema();
                                                        String valorValido3=valor3_1.getLexema();
                                                        reglasT(1030, simboloTab2(valor3_1.getToken()), valor3_1.getLexema(), valor3_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                        if((!(valorValido1.equals("TD"))) && (!(valorValido2.equals("TD"))) && (!(valorValido3.equals("TD"))) && valorNumerico(valorValido1) && valorNumerico(valorValido2) && valorNumerico(valorValido3)){
                                                            int valorLex1=Integer.parseInt(valorValido1);
                                                            int valorLex2=Integer.parseInt(valorValido2);
                                                            int valorLex3=Integer.parseInt(valorValido3);
                                                            if(tipoArr3.equals("Decimal")){
                                                                SQL= "SELECT * FROM Ambito2 WHERE ListaPertenece='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+ultimoAmbi;
                                                                rs = ConsultasTabla.getTabla(SQL);
                                                                int i = 0;
                                                                boolean correcto=true;
                                                                String valor11;
                                                                String valor21;
                                                                String valor31;
                                                                while(rs.next()){
                                                                    seEncontroDato3=true;
                                                                    i++;
                                                                    switch(i){
                                                                        case 1:
                                                                            
                                                                            if((!rs.getString("Valor").equals("TV"))){
                                                                                valor11=rs.getString("Valor");
                                                                                if(valor11.charAt(0)=='-'){
                                                                                    String valorOriginal1="";
                                                                                    for (int j = 1; j < valor11.length(); j++) {
                                                                                        valorOriginal1+=valor11.charAt(j);
                                                                                    }
                                                                                    if(valorLex1>=0 && valorLex1<Integer.parseInt(valorOriginal1)){
                                                                                        
                                                                                    }
                                                                                    else{
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    correcto=false;
                                                                                    
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            if((!rs.getString("Valor").equals("TV"))){
                                                                                valor21=rs.getString("Valor");
                                                                                if(valor21.charAt(0)=='-'){
                                                                                    String valorOriginal2="";
                                                                                    for (int j = 1; j < valor21.length(); j++) {
                                                                                        valorOriginal2+=valor21.charAt(j);
                                                                                    }
                                                                                    if(valorLex2>=0 && valorLex2<Integer.parseInt(valorOriginal2)){
                                                                                        
                                                                                    }
                                                                                    else{
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    correcto=false;
                                                                                    
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 3:
                                                                            if((!rs.getString("Valor").equals("TV"))){
                                                                                valor31=rs.getString("Valor");
                                                                                if(valor31.charAt(0)=='-'){
                                                                                    String valorOriginal3="";
                                                                                    for (int j = 1; j < valor31.length(); j++) {
                                                                                        valorOriginal3+=valor31.charAt(j);
                                                                                    }
                                                                                    if(valorLex3>=0 && valorLex3<Integer.parseInt(valorOriginal3)){
                                                                                        
                                                                                    }
                                                                                    else{
                                                                                        correcto=false;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    correcto=false;
                                                                                    
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                    switch(i){
                                                                        case 1:
                                                                            if(correcto){
                                                                                metodoCorrecto(modeloReglas, jTR, -4,valor1_1, "True", "True");
                                                                            }
                                                                            else{
                                                                                metodoCorrectoF(modeloReglas, jTR, valor1_1, "True", "False", modeloErrores, jTE);
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            if(correcto){
                                                                                metodoCorrecto(modeloReglas, jTR, -4,valor2_1, "True", "True");
                                                                            }
                                                                            else{
                                                                                metodoCorrectoF(modeloReglas, jTR, valor2_1, "True", "False", modeloErrores, jTE);
                                                                            }
                                                                            break;
                                                                        case 3:
                                                                            if(correcto){
                                                                                metodoCorrecto(modeloReglas, jTR, -4,valor3_1, "True", "True");
                                                                            }
                                                                            else{
                                                                                metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                            }
                                                                            break;
                                                                    }
                                                                    correcto=true;
                                                                }
                                                                
                                                            }
                                                            else if(tipoArr3.equals("Binario")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -5,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Octal")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -7,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Hexadecimal")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -6,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Flotante")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -10,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Cadena")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -8,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Caracter")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -12,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Compleja")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -11,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                            else if(tipoArr3.equals("Booleana")){
                                                                if(Integer.parseInt(valor3_1.getLexema())<tamArr3){
                                                                    metodoCorrecto(modeloReglas, jTR, -105,valor3_1, "True", "True");
                                                                }
                                                                else{
                                                                    metodoCorrectoF(modeloReglas, jTR, valor3_1, "True", "False", modeloErrores, jTE);
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(tipoArr3.equals("Binario")){
                                                                metodoCorrecto(modeloReglas, jTR, -5,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Octal")){
                                                                metodoCorrecto(modeloReglas, jTR, -7,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Hexadecimal")){
                                                                metodoCorrecto(modeloReglas, jTR, -6,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Flotante")){
                                                                metodoCorrecto(modeloReglas, jTR, -10,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Cadena")){
                                                                metodoCorrecto(modeloReglas, jTR, -8,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Compleja")){
                                                                metodoCorrecto(modeloReglas, jTR, -11,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Booleana")){
                                                                metodoCorrecto(modeloReglas, jTR, -105,valor3_1, "True", "True");
                                                            }
                                                            else if(tipoArr3.equals("Decimal")){
                                                                metodoCorrecto(modeloReglas, jTR, -4,valor3_1, "True", "True");
                                                            }
                                                            
                                                        }
                                                    }
                                                    else{
                                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en dimension","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en dimension","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                        jTE.setModel(modeloErrores);
                                                        String nombreArr=operandosSemantica.getLast().getLexema();
                                                        operandosSemantica.removeLast();
                                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                                        reglasT(1030, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                    }
                                                }
                                            }while(!AuxAmbi.isEmpty() && !seEncontroDato3);
                                            break;
                                    }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

                                }
                            }
                            else{
                                switch(contArr){
                                    case 1:
                                        try{
//                                            if(!tipoTupla){
                                                reglasT(1030, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
//                                            }
                                            operandosSemantica.removeLast();
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            ResultSet rs115,rs115_2;
                                            int dimAr115=0;
                                            String tipoArr115="";
                                            int tamArr115=0;
                                            int ambitoC115=0;
                                            boolean seEncontroDato=false;
                                            do{
                                                int auxiliarUlt=AuxAmbi.pop();
                                                String SQL= "SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND Ambito="+auxiliarUlt;
                                                rs115 = ConsultasTabla.getTabla(SQL);
                                                while (rs115.next()) {
                                                    tamArr115=rs115.getInt("TamArr");
                                                    ambitoC115=rs115.getInt("AmbitoCrea");
                                                    String valorValido=valor1_1.getLexema();
                                                    if(!(valorValido.equals("TD")) && valorNumerico(valorValido)){
                                                        int valorLex=Integer.parseInt(valorValido);
                                                        if(valorLex>=0 && valorLex<tamArr115){
                                                            reglasT(1050, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "True", auxiliarUlt, modeloReglas, jTR);
                                                            SQL= "SELECT * FROM Ambito2 WHERE Ambito='"+ambitoC115+"' AND ListaPertenece='"+operandosSemantica.getLast().getLexema()+"'";
                                                            rs115_2 = ConsultasTabla.getTabla(SQL);
                                                            int valortabla=0;
                                                            String tipoLista="";
                                                            boolean encontrado=false;
                                                            while (rs115_2.next()) {
                                                                seEncontroDato=true;
                                                                if(valortabla==valorLex){
                                                                    encontrado=true;
                                                                    tipoLista=rs115_2.getString("Tipo");
                                                                }
                                                                valortabla++;
                                                            }
                                                            if(encontrado){
                                                                if(tipoTupla){
                                                                    reglasT(1070, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "True", auxiliarUlt, modeloReglas, jTR);
                                                                }
                                                                if(tipoLista.equals("Binario")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-5,tokensSintx.getFirst().getLine(),nombreArr));
                                                                }
                                                                else if(tipoLista.equals("Octal")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-7,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                                else if(tipoLista.equals("Hexadecimal")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-6,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                                else if(tipoLista.equals("Flotante")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                        operandosSemantica.removeLast();
                                                                        operandosSemantica.add(new StrikerToken(-10,tokensSintx.getFirst().getLine(),nombreArr));
                                                                        
                                                                }
                                                                else if(tipoLista.equals("Cadena")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-8,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                                else if(tipoLista.equals("Compleja")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-11,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                                else if(tipoLista.equals("Booleana")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-105,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                                else if(tipoLista.equals("Decimal")){
                                                                    String nombreArr=operandosSemantica.getLast().getLexema();
                                                                    operandosSemantica.removeLast();
                                                                    operandosSemantica.add(new StrikerToken(-4,tokensSintx.getFirst().getLine(),nombreArr));
                                                                    
                                                                }
                                                            }
                                                            else{
                                                                
                                                                String nombreArr=operandosSemantica.getLast().getLexema();
                                                                operandosSemantica.removeLast();
                                                                operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),nombreArr));
                                                            }
                                                        }
                                                        else{
                                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en rango de arreglo","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),850,"Error en rango de arreglo","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                            jTE.setModel(modeloErrores);
                                                            String nombreArr=operandosSemantica.getLast().getLexema();
                                                            operandosSemantica.removeLast();
                                                            operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                                            reglasT(1050, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", auxiliarUlt, modeloReglas, jTR);
                                                            if(tipoTupla){
                                                                errores.add(new KaijuError(valor1_1.getLine(),850,"Error en tupla","Semantica 2",valor1_1.getLexema()));                        
                                                                modeloErrores.addRow(new Object[]{valor1_1.getLine(),850,"Error en tupla","Semantica 2",valor1_1.getLexema()});
                                                                jTE.setModel(modeloErrores);
                                                                reglasT(1070, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", auxiliarUlt, modeloReglas, jTR);
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        String nombreArr=operandosSemantica.getLast().getLexema();
                                                        operandosSemantica.removeLast();
                                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),nombreArr));
                                                    }
                                                }
                                            }while(!AuxAmbi.isEmpty()&&!seEncontroDato);
                                        }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "PUTA MADRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        
                                        break;
                                    case 2:
                                        operandosSemantica.removeLast();
                                        operandosSemantica.removeLast();
                                        String nombreArr1=operandosSemantica.getLast().getLexema();
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Mas de una dimension","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Mas de una dimension","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        operandosSemantica.removeLast();
                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                        reglasT(1030, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                        break;
                                    case 3:
                                        operandosSemantica.removeLast();
                                        operandosSemantica.removeLast();
                                        operandosSemantica.removeLast();
                                        String nombreArr2=operandosSemantica.getLast().getLexema();
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Mas de una dimension","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Mas de una dimension","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        operandosSemantica.removeLast();
                                        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                        reglasT(1030, simboloTab2(valor3_1.getToken()), valor3_1.getLexema(), valor3_1.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                        break;
                                }
                            }
                        }
                        else{
                            switch(contArr){
                                case 1:
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    operandosSemantica.removeLast();
                                    String nombreArr1=operandosSemantica.getLast().getLexema();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                    break;
                                case 2:
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor2_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor2_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    String nombreArr2=operandosSemantica.getLast().getLexema();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                    break;
                                case 3:
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor1_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor2_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor2_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor2_1.getToken()), valor2_1.getLexema(), valor2_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    if(tipodiccionario){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor3_1.getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),850,"Error en diccionario","Semantica 2",valor3_1.getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1060, simboloTab2(valor3_1.getToken()), valor3_1.getLexema(), valor3_1.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    String nombreArr3=operandosSemantica.getLast().getLexema();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
                                    break;
                            }
                        }
                    }
                    mismoT=true;
                    idPar="";
                    contArr=0;
                    contArr=0;
                    tipo1="";
                    tipo2="";
                    tipo3="";
                    valor1="";
                    valor2="";
                    valor3="";
                    v9351=false;
                    v9352=false;
                    v9353=false;
                    errorDeclaracion=false;
                    errorUtilizacion=false;
                    arregloLista=true;
                    tipodiccionario=false;
                    tipoTupla=false;
                    PS.pop();
                    break;
                    //SEMANTICA 1
/**************/case 944:
                    operadoresSemantica.add(tokensSintx.getFirst());
                    PS.pop();
                    break;
/**************/case 945:
                    StrikerToken sTT,sTP;
                    boolean sonLosDos=false;
                    sTT=operandosSemantica.getLast();
                    operandosSemantica.removeLast();
                    int error=0;
                    //System.out.println("AQUI va operando "+operadoresSemantica.getLast().getLexema());
                    sTP = tipoSemantica1(sTT,operandosSemantica.getLast(),operadoresSemantica.getLast(), modeloErrores, jTE);
                    if(sTT.getToken()==-106&&operandosSemantica.getLast().getToken()==-106){
                        sonLosDos=true;
                    }
                    if(sTP.getToken()==-106&&!sonLosDos){
                        if(operadoresSemantica.getLast().getLexema().equals("+")){
                            descripcion="Error en la suma ";
                            error=836;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("-")){
                            descripcion="Error en la resta ";
                            error=837;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   -   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   -   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("*")){
                            descripcion="Error en la multiplicacion ";
                            error=838;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   *   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   *   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("/")){
                            descripcion="Error en la division ";
                            error=839;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   /   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   /   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }//LOGICOS
                        else if(operadoresSemantica.getLast().getLexema().equals("!")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   !   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   !   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("&&")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   &&   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   &&   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("||")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ||   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ||   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("|")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   |   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   |   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("&")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   &   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   &   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("^")){
                            descripcion="Error en la operador logico ";
                            error=840;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ^   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ^   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        
 //racionales
                        else if(operadoresSemantica.getLast().getLexema().equals("<")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("<=")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <=   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <=   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals(">")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals(">=")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >=   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >=   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("==")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ==   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ==   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("!=")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   !=   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   !=   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("##")){
                            descripcion="Error en la operador racional ";
                            error=841;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ##   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   ##   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        //DESPLAZAMIENTO
                        else if(operadoresSemantica.getLast().getLexema().equals("<<")){
                            descripcion="Error en la operador desplazamiento ";
                            error=842;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <<   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   <<   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals(">>")){
                            descripcion="Error en la operador desplazamiento ";
                            error=842;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >>   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   >>   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        //div entera
                        else if(operadoresSemantica.getLast().getLexema().equals("//")){
                            descripcion="Error en la operador div entera ";
                            error=843;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   //   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   //   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                        else if(operadoresSemantica.getLast().getLexema().equals("%")){
                            descripcion="Error en la operador div entera ";
                            error=843;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   %   "+sTT.getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   %   "+sTT.getLexema()});
                            jTE.setModel(modeloErrores);
                        }
                    }    
                    //System.out.println(sTP.getLine()+" "+sTP.getToken()+" "+sTP.getLexema());
                    operadoresSemantica.removeLast();
                    operandosSemantica.removeLast();
                    operandosSemantica.add(sTP);
                    sonLosDos=false;
                    PS.pop();
                    
                    break;
/**************/case 946:
                    if(!operandosSemantica.isEmpty()){
                        StrikerToken sTT2, sTT3;
                        sTT2=operandosSemantica.getLast();
                        operandosSemantica.removeLast();
                        String lex="";
                        if(!operandosSemantica.isEmpty()){
                            if(operadoresSemantica.getLast().getLexema().equals("=")){
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1020, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1020, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("+=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"+");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("/=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"/");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("*=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"*");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("-=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"-");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("//=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"//");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("**=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"**");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                            else if(operadoresSemantica.getLast().getLexema().equals("%=")){
                                sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"%");
                                sTT2=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                                if(!asignacion(sTT2,lex, modeloErrores, jTE)){
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    descripcion="Asignacion no valida";
                                    lex = asignacion2(sTT2,lex);
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,descripcion,tipoSem1,lex});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                }
                                else{
                                    reglasT(1090, simboloTab2(sTT2.getToken()), sTT2.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1021, "Var/Par/Arr", operandosSemantica.getLast().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                }
                            }
                        }
                        else{
                            if(sTT2.getToken()==-53){
                                ResultSet rs;
                                try{
                                    AuxAmbi = (Stack<Integer>) Ambi.clone();
                                    boolean encontrado=false;
                                    boolean tipoFuncion1=false;
                                    boolean tipoProcedimiento1=false;
                                    do{
                                        int ultiAmbito=AuxAmbi.pop();
                                        String SQL="SELECT * FROM Ambito2 WHERE Id='"+sTT2.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                                        rs = ConsultasTabla.getTabla(SQL);
                                        if (rs.next()) {
                                            if(rs.getString("Clase").equals("Procedimiento")){
                                                errores.add(new KaijuError(sTT2.getLine(),852,"Impresion de procedimiento","Semantica 2",tokensSintx.getFirst().getLexema()));                        
                                                modeloErrores.addRow(new Object[]{sTT2.getLine(),852,"Impresion de procedimiento","Semantica 2",tokensSintx.getFirst().getLexema()});
                                                jTE.setModel(modeloErrores);
                                            }
                                            
                                        }
                                    }while(!AuxAmbi.empty() && !encontrado);
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
    //                    for (int i = 0; i < listaSemantica.size(); i++) {
    //                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    //                    }
                        operandosSemantica.clear();
                        operadoresSemantica.clear();
                        cTDecimal=0;
                        cTBinario=0;
                        cTOctal=0;
                        cTHexadecimal=0;
                        cTFlotante=0;
                        cTCadena=0;
                        cTCaracter=0;
                        cTCompleja=0;
                        cTBooleana=0;
                        cTNone=0;
                        cTLista=0;
                        cTRango=0;
                        cTVariant=0;
                        cTTupla=0;
                        cTDiccionarios=0;
                        cTConjuntos=0;
                    }
                    PS.pop();
                    
                    break;
/**************/case 947:
                    StrikerToken sTIF;
                    boolean sTPIF;
                    sTIF=operandosSemantica.getLast();
                    operandosSemantica.removeLast();
                    sTPIF = expRegla1010(sTIF);
                    
                    if (sTPIF){
                        reglasT(1010, simboloTab2(sTIF.getToken()), sTIF.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    }else{
                        errores.add(new KaijuError(sTIF.getLine(),835,"Error en el if: No booleano","Semantica 2",sTIF.getLexema()));                        
                        modeloErrores.addRow(new Object[]{sTIF.getLine(),835,"Error en el if: No booleano","Semantica 2",sTIF.getLexema()});
                        jTE.setModel(modeloErrores);
                        reglasT(1010, simboloTab2(sTIF.getToken()), sTIF.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                    }
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    PS.pop();
                    break;
/**************/case 948:
                    StrikerToken sTWHILE;
                    boolean sTPWHILE;
                    sTWHILE=operandosSemantica.getLast();
                    operandosSemantica.removeLast();
                    sTPWHILE = expRegla1010(sTWHILE);
                    
                    if (sTPWHILE){
                        reglasT(1011, simboloTab2(sTWHILE.getToken()), sTWHILE.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    }else{
                        errores.add(new KaijuError(sTWHILE.getLine(),835,"Error en el while: No booleano","Semantica 2",sTWHILE.getLexema()));                        
                        modeloErrores.addRow(new Object[]{sTWHILE.getLine(),835,"Error en el while: No booleano","Semantica 2",sTWHILE.getLexema()});
                        jTE.setModel(modeloErrores);
                        reglasT(1011, simboloTab2(sTWHILE.getToken()), sTWHILE.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                    }
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    PS.pop();
                    break;
/**************/case 949:
                    StrikerToken sTELIF;
                    boolean sTPELIF;
                    sTELIF=operandosSemantica.getLast();
                    sTPELIF = expRegla1010(sTELIF);
                    
                    if (sTPELIF){
                        reglasT(1012, simboloTab2(sTELIF.getToken()), sTELIF.getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    }else{
                        errores.add(new KaijuError(sTELIF.getLine(),835,"Error en el elif: No booleano","Semantica 2",sTELIF.getLexema()));                        
                        modeloErrores.addRow(new Object[]{sTELIF.getLine(),835,"Error en el elif: No booleano","Semantica 2",sTELIF.getLexema()});
                        jTE.setModel(modeloErrores);
                        reglasT(1012, simboloTab2(sTELIF.getToken()), sTELIF.getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                    }
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    PS.pop();
                    break;
/**************/case 950:
                    menor=true;
                    if(tipodiccionario){
                        reglasT(1061, "operador", "-", operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    }
                    if(tipoTupla){
                        reglasT(1071, "operador", "-", operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    }
                    PS.pop();
                    break;
/**************/case 951:
                    if(!operandosSemantica2.isEmpty()){
                        if(!operandosSemantica.isEmpty()){
                            try{
                                if(masReturns){
                                    if(operandosSemantica.getLast().getToken()==-53){
                                        ResultSet rs;
                                        try{
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            boolean encontrado=false;
                                            boolean tipoFuncion1=false;
                                            boolean tipoProcedimiento1=false;
                                            do{
                                                int ultiAmbito=AuxAmbi.pop();
                                                String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                                                rs = ConsultasTabla.getTabla(SQL);
                                                if (rs.next()) {
                                                    encontrado=true;
                                                    if(rs.getString("Clase").equals("Funcion")){
                                                        tipoFuncion1=true;
                                                    }
                                                    else if(rs.getString("Clase").equals("Procedimiento")){
                                                        tipoProcedimiento1=true;
                                                    }
                                                    boolean valorAceptado=false;
                                                    if(tipoFuncion1){
                                                        String cadena2 = rs.getString("Rango");
                                                        String []valores2 = cadena2.split(",");
                                                        boolean mas=false;
                                                        for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                                            
                                                                tiposFuncion=tiposFuncion+","+valores2[i];
                                                        }
                                                    }
                                                    else if(tipoProcedimiento1){
                                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),838,"Uso de Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),838,"Uso de Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                        tiposFuncion=-106+"";
                                                    }
                                                }
                                            }while(!AuxAmbi.empty() && !encontrado);
                                        }catch(SQLException e){
                                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }else{
                                        tiposFuncion=tiposFuncion+","+operandosSemantica.getLast().getToken();
                                    }
                                    
                                }
                                else{
                                    if(operandosSemantica.getLast().getToken()==-53){
                                        ResultSet rs;
                                        try{
                                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                                            boolean encontrado=false;
                                            boolean tipoFuncion1=false;
                                            boolean tipoProcedimiento1=false;
                                            do{
                                                int ultiAmbito=AuxAmbi.pop();
                                                String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                                                rs = ConsultasTabla.getTabla(SQL);
                                                if (rs.next()) {
                                                    encontrado=true;
                                                    if(rs.getString("Clase").equals("Funcion")){
                                                        tipoFuncion1=true;
                                                    }
                                                    else if(rs.getString("Clase").equals("Procedimiento")){
                                                        tipoProcedimiento1=true;
                                                    }
                                                    boolean valorAceptado=false;
                                                    if(tipoFuncion1){
                                                        String cadena2 = rs.getString("Rango");
                                                        String []valores2 = cadena2.split(",");
                                                        boolean mas=false;
                                                        for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                                            if(!mas){
                                                                tiposFuncion=valores2[i];
                                                                mas=true;
                                                            }
                                                            else{
                                                                tiposFuncion=tiposFuncion+","+valores2[i];
                                                            }
                                                        }
                                                    }
                                                    else if(tipoProcedimiento1){
                                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),838,"Uso de Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),838,"Uso de Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()});
                                                        tiposFuncion=-106+"";
                                                    }
                                                }
                                            }while(!AuxAmbi.empty() && !encontrado);
                                        }catch(SQLException e){
                                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }else{
                                        tiposFuncion=operandosSemantica.getLast().getToken()+"";
                                    }
                                    masReturns=true;
                                }
                                Statement st=c.createStatement();
                                String SQL="UPDATE Ambito2 SET Rango='"+tiposFuncion+"' WHERE  Id = '"+operandosSemantica2.getLast().getLexema()+"' AND Ambito="+operandosSemantica2.getLast().getToken()+";";
//                                System.out.println(SQL);
                                st.executeUpdate(SQL);
                                reglasT(1140, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
//                    if(parametrosFuncion)
//                    
//                    contArr++;
//                    
//                    operandosSemantica2.add(operandosSemantica.getLast());
                    PS.pop();
                    break;
/**************/case 952:
                    try{
                        AuxAmbi = (Stack<Integer>) Ambi.clone();
                        ResultSet rs;
                        do{
                            bo953=true;
                            int ultiAmbito=AuxAmbi.pop();
                            String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                            rs = ConsultasTabla.getTabla(SQL);
                            if (rs.next()) {
                                
                                funcionPar=true;
                                if(rs.getString("Clase").equals("Funcion")){
                                    tipoFuncion=true;
                                }
                                laFuncionTok=operandosSemantica.getLast().getToken();
                                laFuncionLin=operandosSemantica.getLast().getLine();
                                laFuncionLex=operandosSemantica.getLast().getLexema();
                                parametrosFuncion=rs.getInt("NoParr");
                            }
                        }while(!AuxAmbi.empty());
                    }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

                    }
                    PS.pop();
                    break;
/**************/case 953:
                    if(bo953){
                        if(!(laFuncionTok==operandosSemantica.getLast().getToken() && laFuncionLin==operandosSemantica.getLast().getLine() && laFuncionLex.equals(operandosSemantica.getLast().getLexema()))){
                            contParr++;
                        }
                    }
                    else{
                        operandosSemantica.clear();
                        operadoresSemantica.clear();
                    }
                    PS.pop();
                    break;
/**************/case 954:
                    if(funcionPar){
                        if(contParr==parametrosFuncion){
                            reglasT(1100, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                        }
                        else{
                            if(contParr==0){
                                errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Parametros vacios","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Parametros vacios","Semantica 2",operandosSemantica.getLast().getLexema()});
                                jTE.setModel(modeloErrores);
                                reglasT(1100, "Vacio", " ", operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            else{
                                errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Parametros incompletos","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Parametros incompletos","Semantica 2",operandosSemantica.getLast().getLexema()});
                                jTE.setModel(modeloErrores);
                                reglasT(1100, simboloTab2(operandosSemantica.getLast().getToken()), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                        }
                        for (int i = 0; i < contParr; i++) {
                            operandosSemantica.removeLast();
                        }
                        System.out.println(operandosSemantica.getLast().getLexema());
                        if(tipoFuncion){
                            reglasT(1120, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                        }
                        else{
                            reglasT(1110, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                        }
                        
                    }
                    else{
                        for (int i = 0; i < contParr; i++) {
                            operandosSemantica.removeLast();
                        }
                    }
                    contParr=0;
                    tipoFuncion=false;
                    bo953=false;
                    funcionPar=false;
                    PS.pop();
                    break;
/**************/case 955:
                    if(AreaDec){
                        if(!yaDeclarada){
                            if(duplicados(id,Ambi.lastElement())){
                                try{
                                    if(intervalo2.equals("TV")){
                                    }
                                    else{
                                        if(Integer.parseInt(intervalo1)<Integer.parseInt(intervalo2)){
                                            if(Integer.parseInt(avance)>0){
                                                reglasT(1163, "D", avance, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                reglasT(1160, "D", avance, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                            }
                                            else{
                                                errores.add(new KaijuError(tokensSintx.getFirst().getLine(),835,"Avance incompatible","Semantica 2",avance));                        
                                                modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),835,"Avance incompatible","Semantica 2",avance});
                                                jTE.setModel(modeloErrores);
                                                reglasT(1163, "D", avance, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                avance="TV";
                                            }
                                        }
                                        else if(Integer.parseInt(intervalo1)>Integer.parseInt(intervalo2)){
                                            if(Integer.parseInt(avance)<0){
                                                reglasT(1163, "D", avance, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                reglasT(1161, "D", avance, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                            }
                                            else{
                                                errores.add(new KaijuError(tokensSintx.getFirst().getLine(),835,"Avance incompatible","Semantica 2",avance));                        
                                                modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),835,"Avance incompatible","Semantica 2",avance});
                                                jTE.setModel(modeloErrores);
                                                reglasT(1163, "D", avance, tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                                avance="TV";
                                            }
                                        }
                                    }
                                    Statement st=c.createStatement();
                                    String SQL="UPDATE Ambito2 SET Rango = '"+intervalo1+","+intervalo2+"', Avance = '"+avance+"' WHERE  Id = '"+id+"' AND Ambito="+Ambi.lastElement()+";";
                                    //System.out.println(SQL);
                                    st.executeUpdate(SQL);
                                }catch(SQLException e){
                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                    else{
                        if(intervalo2.equals("TV")){
                            operandosSemantica.removeLast();
                            operandosSemantica.removeLast();
                            operandosSemantica.removeLast();
                            operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),"TR"));
                        }
                        else{
                            if(Integer.parseInt(intervalo1)<Integer.parseInt(intervalo2)){
                                if(Integer.parseInt(avance)>0){
                                    reglasT(1163, "D", avance, operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1160, "D", avance, operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),"TR"));
                                }
                                else{
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Avance incompatible","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Avance incompatible","Semantica 2",operandosSemantica.getLast().getLexema()});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1163, "D", avance, operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),"TR"));
                                }
                            }
                            else if(Integer.parseInt(intervalo1)>Integer.parseInt(intervalo2)){
                                if(Integer.parseInt(avance)<0){
                                    reglasT(1163, "D", avance, operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    reglasT(1161, "D", avance, operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),"TR"));
                                }
                                else{
                                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Avance incompatible","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Avance incompatible","Semantica 2",operandosSemantica.getLast().getLexema()});
                                    jTE.setModel(modeloErrores);
                                    reglasT(1163, "D", avance, operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.removeLast();
                                    operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),"TR"));
                                }
                            }
                        }
                    }
                    //System.out.println("NUMERO DE DATOS EN TUPLA = "+numTupla);
                    numTupla=0;
                    PS.pop();
                    break;
/**************/case 956:
                    reglasT(1080, "For", "For", operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                    switch(operandosSemantica.getLast().getLexema()){
                        case "TD":case "TF":case "TS":case "TCH":case "TDB":case "TDH":case "TDO":case "TC":case "TB":case "TL":case "TLA": case "TT":case "TCT":case "TDIC":
                        case "TN":case "TR":
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Datos no validos en el for","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Datos no validos en el for","Semantica 2",operandosSemantica.getLast().getLexema()});
                            jTE.setModel(modeloErrores);
                            reglasT(1081, operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            operandosSemantica.getLast().setToken(-106);
                            operandosSemantica.getLast().setLexema("TV");
                            break;
                        case "TV":
                            reglasT(1081, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            break;
                        default :
                            if(operandosSemantica.getLast().getToken()==-53){
                                ResultSet rs;
                                try{
                                    AuxAmbi = (Stack<Integer>) Ambi.clone();
                                    boolean encontrado=false;
                                    boolean tipoFuncion1=false;
                                    boolean tipoProcedimiento1=false;
                                    do{
                                        int ultiAmbito=AuxAmbi.pop();
                                        String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                                        rs = ConsultasTabla.getTabla(SQL);
                                        if (rs.next()) {
                                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Datos no validos en el for","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Datos no validos en el for","Semantica 2",operandosSemantica.getLast().getLexema()});
                                            jTE.setModel(modeloErrores);
                                            reglasT(1081, operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                            operandosSemantica.getLast().setToken(-106);
                                            operandosSemantica.getLast().setLexema("TV");
                                        }
                                    }while(!AuxAmbi.empty() && !encontrado);
                                    if(!encontrado){
                                        reglasT(1081, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else{
                                reglasT(1081, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            break;
                    }
                    PS.pop();
                    break;
/**************/case 957:
                    boolean compatible=false;
                    if(operandosSemantica.getLast().getToken()==-100){
                        ResultSet rs;
                        try{
                            boolean encontrado=false;
                            do{
                                int ultiAmbito=AuxAmbi.pop();
                                String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND Clase=Lista-Arr AND Ambito="+ultiAmbito;
                                rs = ConsultasTabla.getTabla(SQL);
                                if(rs.next()){
                                    int token=0;
                                    operandosSemantica.removeLast();
                                    if(rs.getString("Llave").equals("None")){
                                        token=-53;
                                    }
                                    else if(rs.getString("Llave").equals("Decimal")){
                                        token=-4;
                                        operandosSemantica.add(new StrikerToken(-4,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Flotante")){
                                        token=-10;
                                        operandosSemantica.add(new StrikerToken(-10,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Cadena")){
                                        token=-9;
                                        operandosSemantica.add(new StrikerToken(-9,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Caracter")){
                                        token=-12;
                                        operandosSemantica.add(new StrikerToken(-12,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Binario")){
                                        token=-5;
                                        operandosSemantica.add(new StrikerToken(-5,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Hexadecimal")){
                                        token=-6;
                                        operandosSemantica.add(new StrikerToken(-6,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Octal")){
                                        token=-7;
                                        operandosSemantica.add(new StrikerToken(-7,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Compleja")){
                                        token=-11;
                                        operandosSemantica.add(new StrikerToken(-11,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    else if(rs.getString("Llave").equals("Booleana")){
                                        token=-105;
                                        operandosSemantica.add(new StrikerToken(-105,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                    }
                                    if(operandosSemantica.getLast().getToken()==token){
                                        reglasT(1082, "Id", rs.getString("Id"), operandosSemantica.getLast().getLine(), "True", ultiAmbito, modeloReglas, jTR);
                                        compatible=true;
                                    }
                                    else{
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),835,"Error en los diccionarios","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),835,"Error en los diccionarios","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        reglasT(1082, "Id", rs.getString("Id"), operandosSemantica.getLast().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                        compatible=true;
                                    }
                                }
                            }while(!AuxAmbi.empty() && !encontrado);
                            
                            
                        }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if(operandosSemantica.getLast().getToken()==-53){
                        ResultSet rs;
                        try{
                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                            boolean encontrado=false;
                            boolean tipoFuncion1=false;
                            boolean tipoProcedimiento1=false;
                            do{
                                int ultiAmbito=AuxAmbi.pop();
                                String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                                rs = ConsultasTabla.getTabla(SQL);
                                if (rs.next()) {
                                    encontrado=true;
                                    if(rs.getString("Clase").equals("Funcion")){
                                        tipoFuncion1=true;
                                    }
                                    else if(rs.getString("Clase").equals("Procedimiento")){
                                        tipoProcedimiento1=true;
                                    }
                                    boolean valorAceptado=false;
                                    StrikerToken segundo=null;
                                    if(tipoFuncion1){
                                        segundo=operandosSemantica.getLast();
                                        operandosSemantica.removeLast();
                                        String cadena2 = rs.getString("Rango");
                                        String []valores2 = cadena2.split(",");
                                        
                                        for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                            if(Integer.parseInt(valores2[i])==-100){
                                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                compatible=true;
                                            }
                                            else if(operandosSemantica.getLast().getToken()==-4){
                                                if(Integer.parseInt(valores2[i])==-99 || Integer.parseInt(valores2[i])==-100 || Integer.parseInt(valores2[i])==-102){
                                                    reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                    compatible=true;
                                                }
                                            }
                                            else if(operandosSemantica.getLast().getToken()==-12){
                                                if(Integer.parseInt(valores2[i])==-8 || Integer.parseInt(valores2[i])==-9){
                                                    reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                    compatible=true;
                                                }
                                            }
                                            else if(operandosSemantica.getLast().getToken()==-106){
                                                if(Integer.parseInt(valores2[i])==-99 || Integer.parseInt(valores2[i])==-100 || Integer.parseInt(valores2[i])==-102 || Integer.parseInt(valores2[i])==-8 || Integer.parseInt(valores2[i])==-9){
                                                    reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                    compatible=true;
                                                }
                                            }
                                        }
                                        if(!compatible){
                                            errores.add(new KaijuError(segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()));                        
                                            modeloErrores.addRow(new Object[]{segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()});
                                            jTE.setModel(modeloErrores);
                                            reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                                            compatible=true;
                                        }
                                    }
                                    else if(tipoProcedimiento1){
                                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),838,"Asignar un Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),838,"Asignar un Procedimiento","Semantica 2",operandosSemantica.getLast().getLexema()});
                                        jTE.setModel(modeloErrores);
                                        operandosSemantica.getLast().setToken(-106);
                                        operandosSemantica.getLast().setLexema("TV");
                                        reglasT(1082, "Id", operandosSemantica.getLast().getLexema(), operandosSemantica.getLast().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                    }
                                }
                            }while(!AuxAmbi.empty() && !encontrado);
                        }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if(!compatible){
                        StrikerToken segundo=operandosSemantica.getLast();
                        operandosSemantica.removeLast();
                        if(operandosSemantica.getLast().getToken()==-4){
                            if(segundo.getToken()==-99 || segundo.getToken()==-100 || segundo.getToken()==-102){
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            else{
                                errores.add(new KaijuError(segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()));                        
                                            modeloErrores.addRow(new Object[]{segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()});
                                            jTE.setModel(modeloErrores);
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                        }
                        else if(operandosSemantica.getLast().getToken()==-12){
                            if(segundo.getToken()==-8 || segundo.getToken()==-9){
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            else{
                                errores.add(new KaijuError(segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()));                        
                                            modeloErrores.addRow(new Object[]{segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()});
                                            jTE.setModel(modeloErrores);
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                        }
                        else if(operandosSemantica.getLast().getToken()==-106){
                            if(segundo.getToken()==-99 || segundo.getToken()==-100 || segundo.getToken()==-102 || segundo.getToken()==-8 || segundo.getToken()==-9){
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            else{
                                errores.add(new KaijuError(segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()));                        
                                            modeloErrores.addRow(new Object[]{segundo.getLine(),835,"No compatible con id for","Semantica 2",segundo.getLexema()});
                                            jTE.setModel(modeloErrores);
                                reglasT(1082, "Id", segundo.getLexema(), segundo.getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                        }
                    }
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    PS.pop();
                    break;
/**************/case 958:
                    boolean sonLosDos2=false;
                    if(masM){
                        StrikerToken sTT2, sTT3, sTT4;
                        sTT2=new StrikerToken(-4,operandosSemantica.getLast().getLine(),"1");
                        String lex="";
                        sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"+");
                        sTT4=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                        if(sTT2.getToken()==-106&&operandosSemantica.getLast().getToken()==-106){
                            sonLosDos2=true;
                        }
                        if(sTT4.getToken()==-106&&!sonLosDos2){
                            descripcion="Error en la suma ";
                            error=836;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   1"));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   1"});
                            jTE.setModel(modeloErrores);
                            operandosSemantica.removeLast();
                            operandosSemantica.add(sTT4);
                        }
//                        if(!asignacion(sTT2,lex, modeloErrores, jTE)){

//                        }
//                        else{
//                            operandosSemantica.removeLast();
//                            operandosSemantica.add(sTT2);
//                        }
                    }
                    sonLosDos2=false;
                    masM=false;
                    PS.pop();
                    break;
/**************/case 959:
                    boolean sonLosDos3=false;
                    if(menosM){
                        StrikerToken sTT2, sTT3, sTT4;
                        sTT2=new StrikerToken(-4,operandosSemantica.getLast().getLine(),"1");
                        String lex="";
                        sTT3=new StrikerToken(operandosSemantica.getLast().getToken(),operandosSemantica.getLast().getLine(),"-");
                        sTT4=tipoSemantica1(sTT2,operandosSemantica.getLast(),sTT3,modeloErrores, jTE);
                        if(sTT2.getToken()==-106&&operandosSemantica.getLast().getToken()==-106){
                            sonLosDos2=true;
                        }
                        if(sTT4.getToken()==-106&&!sonLosDos3){
                            descripcion="Error en la suma ";
                            error=836;
                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   -   1"));                        
                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   -   1"});
                            jTE.setModel(modeloErrores);
                            operandosSemantica.removeLast();
                            operandosSemantica.add(sTT4);
                        }
//                        if(!asignacion(sTT2,lex, modeloErrores, jTE)){
//                            descripcion="Error en la suma ";
//                            error=836;
//                            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   1"));                        
//                            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),error,descripcion,tipoSem1,operandosSemantica.getLast().getLexema()+"   +   1"});
//                            jTE.setModel(modeloErrores);
//                            operandosSemantica.removeLast();
//                            operandosSemantica.add(sTT2);
//                        }
//                        else{
//                            operandosSemantica.removeLast();
//                            operandosSemantica.add(sTT2);
//                        }
                    }
                    sonLosDos3=false;
                    menosM=false;
                    PS.pop();
                    break;
/**************/case 960:
                    masM=true;
                    PS.pop();
                    break;
/**************/case 961:
                    menosM=true;
                    PS.pop();
                    break;
/**************/case 962:
                    operandosSemantica.clear();
                    operadoresSemantica.clear();
                    PS.pop();
                    break;
            }
            
            
//            if(tokensSintx.getFirst().getToken()!=-98){
                veces++;
//                System.out.println("VECES DE VUELTA "+veces);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (!noSiTerminal(PS.lastElement())){
                    columna = columnSintaxis(tokensSintx.getFirst().getToken());
                    estadoCelda = calculoEstado(PS.lastElement(),columna);
                    if(estadoCelda == 133){
                        contadoresNoTerminales(PS.lastElement(),tokensSintx.getFirst().getLexema(),columna,tokensSintx.getFirst().getLine());
                        PS.pop();
                    }
                    else if(estadoCelda>=800){
                        descripcion = cambioDescrip(estadoCelda);
                        //System.out.println("VECES EN VUELTA "+veces);
                        //System.out.println("PILA");
                        for (int i = 0; i < PS.size(); i++) {
                            //System.out.println(PS.get(i)+" ");
                        }
                        //System.out.println(" ");
                        //System.out.println("LISTA TOK");
                        for (int i = 0; i < tokensSintx.size(); i++) {
                            //System.out.print(tokensSintx.get(i).getToken());
                        }
                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),estadoCelda,descripcion,tipoSintactico,tokensSintx.getFirst().getLexema()));                        
                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),estadoCelda,descripcion,tipoSintactico,tokensSintx.getFirst().getLexema()});
                        jTE.setModel(modeloErrores);
                        //System.out.println("LA FILA ES LA :"+(PS.lastElement())+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        //System.out.println("LA columna ES LA :"+columna+"CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                        //System.out.println("ERROR "+estadoCelda+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        tokensSintx.removeFirst();
                    }
                    else if(estadoCelda >= 1 && estadoCelda <= 135){
                        contadoresNoTerminales(PS.lastElement(),tokensSintx.getFirst().getLexema(),columna,tokensSintx.getFirst().getLine());
                        PS.pop();
                        for (int i = 0; i < produccionPila(estadoCelda).length; i++) {
                            PS.push(produccionPila(estadoCelda)[i]);
                        }
                    }
                }
                else if (noSiTerminal(PS.lastElement())){
                    if(PS.lastElement() == tokensSintx.getFirst().getToken()){
                        if(AreaDec&&tokensSintx.getFirst().getToken()==-1){
                            if(altaTabla){
                                switch(clasSimbol){
                                    case 1:
                                        id=tokensSintx.getFirst().getLexema();
                                        clase="Variable";
                                        break;
                                    case 2:
                                        tParr++;
                                        id=tokensSintx.getFirst().getLexema();
                                        clase="Procedimiento";
                                        tParrPar=tokensSintx.getFirst().getLexema();
                                        tipo="None";
                                        if(!duplicados(id,Ambi.lastElement())){
                                            try{
                                                Statement st=c.createStatement();
                                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito, NoParr, TParr)\n" +
                                                    "VALUES ('"+id+"','"+tipo+"','"+clase+"',"+Ambi.lastElement()+","+noParr+",'"+tParr+"')";
                                                //System.out.println(SQL);
                                                st.executeUpdate(SQL);
                                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                ambitoAnt=Ambi.peek();
                                                ambito++;
                                                Ambi.push(ambito);
                                                siHizo=true;
                                            }catch(SQLException e){
                                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                        else{
//                                            descripcion="Variable Duplicada";
//                                            errores.add(new KaijuError(tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id));                        
//                                            modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoAmbito,id});
//                                            jTE.setModel(modeloErrores);
                                            tParrPar="~"+tokensSintx.getFirst().getLexema();
                                            try{
                                                Statement st=c.createStatement();
                                                String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito, NoParr, TParr)\n" +
                                                    "VALUES ('~"+id+"','"+tipo+"','"+clase+"',"+Ambi.lastElement()+","+noParr+",'"+tParr+"')";
                                                //System.out.println(SQL);
                                                st.executeUpdate(SQL);
                                                reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                ambitoAnt=Ambi.peek();
                                                ambito++;
                                                Ambi.push(ambito);
                                                siHizo=true;
                                            }catch(SQLException e){
                                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            yaDeclarada=true;
                                        }
                                        break;
                                    case 3:
                                        id=tokensSintx.getFirst().getLexema();
                                        clase="Parametro";
                                        tipo="None";
                                        if(!yaDeclarada){
                                            if(duplicadosFuncion(tParrPar,ambitoAnt)){
                                            
                                                try{
                                                    noParr++;
                                                    Statement st=c.createStatement();
                                                    String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito, NoParr, TParr)\n" +
                                                        "VALUES ('"+id+"','"+tipo+"','"+clase+"',"+Ambi.lastElement()+","+noParr+",'"+tParrPar+"')";
                                                    //System.out.println(SQL);
                                                    st.executeUpdate(SQL);
                                                    reglasT(1130, "Par", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                        }
                                        else{
                                            if(duplicadosFuncion(tParrPar,ambitoAnt)){
                                            
                                                try{
                                                    noParr++;
                                                    Statement st=c.createStatement();
                                                    String SQL="INSERT INTO Ambito2 (Id, Tipo, Clase, Ambito, NoParr, TParr)\n" +
                                                        "VALUES ('"+id+"','"+tipo+"','"+clase+"',"+Ambi.lastElement()+","+noParr+",'"+tParrPar+"')";
                                                    //System.out.println(SQL);
                                                    st.executeUpdate(SQL);
                                                    reglasT(1130, "Id", id, tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                                }catch(SQLException e){
                                                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                        }
                                        break;
                                }
                                
                            }
                            else{
                                
                            }
                        }
                        else if(!AreaDec&&tokensSintx.getFirst().getToken()==-1){
                            
                            if(!variableDeclarada(tokensSintx.getFirst().getLexema(),Ambi)&&tokensSintx.getFirst().getToken()==-1){
                                
                                descripcion="Variable No Declarada";
                                errores.add(new KaijuError(tokensSintx.getFirst().getLine(),833,descripcion,tipoAmbito,tokensSintx.getFirst().getLexema()));                        
                                modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),833,descripcion,tipoAmbito,tokensSintx.getFirst().getLexema()});
                                jTE.setModel(modeloErrores);
                                operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                reglasT(1130, "Id", tokensSintx.getFirst().getLexema(), tokensSintx.getFirst().getLine(), "False", Ambi.lastElement(), modeloReglas, jTR);
                            }
                            else if(variableDeclarada(tokensSintx.getFirst().getLexema(),Ambi)&&tokensSintx.getFirst().getToken()==-1){
                                StrikerToken sTT2;
                                ResultSet rs;
                                AuxAmbi = (Stack<Integer>) Ambi.clone();
                                int tam=AuxAmbi.size();
                                boolean encontrado=false;
                                do{
                                    
                                    String SQL= "SELECT * FROM Ambito2 WHERE Id='"+tokensSintx.getFirst().getLexema()+"' AND Ambito="+AuxAmbi.pop();
                                    rs = ConsultasTabla.getTabla(SQL);
                                    //System.out.println(SQL+" Variable Declarada");
                                    try{
                                        while (rs.next()) {
                                            String claseT = rs.getString("Clase");
                                            String tipoT = rs.getString("Tipo");
                                            if(tipoT.equals("Struct")){
                                                if(claseT.equals("Lista")){
                                                    operandosSemantica.add(new StrikerToken(-99,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("Lista-Arr")){
                                                    operandosSemantica.add(new StrikerToken(-100,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("Tupla")){
                                                    operandosSemantica.add(new StrikerToken(-101,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("Rango")){
                                                    operandosSemantica.add(new StrikerToken(-102,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("Conjunto")){
                                                    operandosSemantica.add(new StrikerToken(-103,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("Diccionario")){
                                                    operandosSemantica.add(new StrikerToken(-104,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(claseT.equals("None")){
                                                    operandosSemantica.add(new StrikerToken(-53,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                            }
                                            else{
                                                if(tipoT.equals("None")){
                                                    operandosSemantica.add(new StrikerToken(-53,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Decimal")){
                                                    operandosSemantica.add(new StrikerToken(-4,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Flotante")){
                                                    operandosSemantica.add(new StrikerToken(-10,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Cadena")){
                                                    operandosSemantica.add(new StrikerToken(-9,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Caracter")){
                                                    operandosSemantica.add(new StrikerToken(-12,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Binario")){
                                                    operandosSemantica.add(new StrikerToken(-5,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Hexadecimal")){
                                                    operandosSemantica.add(new StrikerToken(-6,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Octal")){
                                                    operandosSemantica.add(new StrikerToken(-7,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Compleja")){
                                                    operandosSemantica.add(new StrikerToken(-11,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                else if(tipoT.equals("Booleana")){
                                                    operandosSemantica.add(new StrikerToken(-105,tokensSintx.getFirst().getLine(),tokensSintx.getFirst().getLexema()));
                                                    encontrado=true;
                                                }
                                                
                                            }
                                            reglasT(1130, "Id", tokensSintx.getFirst().getLexema(), tokensSintx.getFirst().getLine(), "True", Ambi.lastElement(), modeloReglas, jTR);
                                            
                                        }
                                    }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                while(!AuxAmbi.isEmpty()&&!encontrado);
                                
                            }
                            else if (
                                tokensSintx.getFirst().getToken()==-54||tokensSintx.getFirst().getToken()==-56||tokensSintx.getFirst().getToken()==-105){
                                operandosSemantica.add(tokensSintx.getFirst());
                            }
                        }
                        
                        tokensSintx.removeFirst();
                        PS.pop();
                    } else {
                        descripcion="Error de fuerza bruta";
                        errores.add(new KaijuError(tokensSintx.getFirst().getLine(),estadoCelda,descripcion,tipoSintactico,tokensSintx.getFirst().getLexema()));                        
                        modeloErrores.addRow(new Object[]{tokensSintx.getFirst().getLine(),832,descripcion,tipoSintactico,tokensSintx.getFirst().getLexema()});
                        jTE.setModel(modeloErrores);
                        PS.clear();
                    }
                }
//            }
        }while(!tokensSintx.isEmpty() && !PS.isEmpty());
        
    }
    
    public boolean asignacion(StrikerToken sTT2, String lex, DefaultTableModel modeloErrores, JTable jTE){
        
        if(operandosSemantica.getLast().getToken()==-53){
            ResultSet rs;
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                boolean encontrado=false;
                boolean tipoFuncion1=false;
                boolean tipoProcedimiento1=false;
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        errores.add(new KaijuError(operandosSemantica.getLast().getLine(),838,"Asignacion a una Funcion o Procedimiento","Semantica 2",tokensSintx.getFirst().getLexema()));                        
                        modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),838,"Asignacion a una Funcion o Procedimiento","Semantica 2",tokensSintx.getFirst().getLexema()});
                        jTE.setModel(modeloErrores);
                        operandosSemantica.getLast().setToken(-106);
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(sTT2.getToken()==-53){
            ResultSet rs;
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                boolean encontrado=false;
                boolean tipoFuncion1=false;
                boolean tipoProcedimiento1=false;
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+sTT2.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        encontrado=true;
                        if(rs.getString("Clase").equals("Funcion")){
                            tipoFuncion1=true;
                        }
                        else if(rs.getString("Clase").equals("Procedimiento")){
                            tipoProcedimiento1=true;
                        }
                        
                        boolean valorAceptado=false;
                        if(tipoFuncion1){
                            String cadena2 = rs.getString("Rango");
                            String []valores2 = cadena2.split(",");
                            for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                if(operandosSemantica.getLast().getToken()==Integer.parseInt(valores2[i]) || operandosSemantica.getLast().getToken()==-106){
                                    sTT2.setToken(operandosSemantica.getLast().getToken());
                                }
                            }
                        }
                        else if(tipoProcedimiento1){
                            errores.add(new KaijuError(sTT2.getLine(),838,"Asignar un Procedimiento","Semantica 2",sTT2.getLexema()));                        
                            modeloErrores.addRow(new Object[]{sTT2.getLine(),838,"Asignar un Procedimiento","Semantica 2",sTT2.getLexema()});
                            jTE.setModel(modeloErrores);
                            sTT2.setToken(-106);
                        }
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(sTT2.getToken()==-8 || sTT2.getToken()==-9){
            sTT2.setToken(-8);
        }
        if(operandosSemantica.getLast().getToken() == -8 || operandosSemantica.getLast().getToken() == -9){
            operandosSemantica.getLast().setToken(-8);
        }
        if(sTT2.getToken()==-54 || sTT2.getToken()==-56 || sTT2.getToken()==-105){
            sTT2.setToken(-105);
        }
        if(operandosSemantica.getLast().getToken() == -54 || operandosSemantica.getLast().getToken() == -56 || operandosSemantica.getLast().getToken() == -105){
            operandosSemantica.getLast().setToken(-105);
        }
        if(sTT2.getToken()==operandosSemantica.getLast().getToken()||operandosSemantica.getLast().getToken()==-106 || sTT2.getToken()==-106){
               if(sTT2.getToken()==-106){
                   lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TV";
               }
               else{
                switch(operandosSemantica.getLast().getToken()){
                     case -4:
                         lex=simboloTab2(-4)+"<-TD";
                         break;
                     case -10:
                         lex=simboloTab2(-10)+"<-TF";
                         break;
                     case -8:
                         lex=simboloTab2(-8)+"<-TS";
                         break;
                     case -9:
                         lex=simboloTab2(-9)+"<-TS";
                         break;
                     case -12:
                         lex=simboloTab2(-12)+"<-TCH";
                         break;
                     case -5:
                         lex=simboloTab2(-5)+"<-TDB";
                         break;
                     case -6:
                         lex=simboloTab2(-6)+"<-TDH";
                         break;
                     case -7:
                         lex=simboloTab2(-7)+"<-TDO";
                         break;
                     case -11:
                         lex=simboloTab2(-11)+"<-TC";
                         break;
                     case -54:
                         lex=simboloTab2(-54)+"<-TB";
                         break;
                     case -56:
                         lex=simboloTab2(-56)+"<-TB";
                         break;
                     case -105:
                         lex=simboloTab2(-105)+"<-TB";
                         break;
                     case -99:
                         lex=simboloTab2(-99)+"<-TL";
                         break;
                     case -100:
                         lex=simboloTab2(-100)+"<-TLA";
                         break;
                     case -101:
                         lex=simboloTab2(-101)+"<-TT";
                         break;
                     case -103:
                         lex=simboloTab2(-103)+"<-TCT";
                         break;
                     case -104:
                         lex=simboloTab2(-104)+"<-TDIC";
                         break;
                     case -53:
                         lex=simboloTab2(-53)+"<-TN";
                         break;
                     case -102:
                         lex=simboloTab2(-102)+"<-TR";
                         break;
                     case -106:
                         lex=simboloTab2(-106)+"<-TV";
                         break;

                 }
            }   
//            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            listaSemantica.add(new SemanticaFase1(operandosSemantica.getLast().getLine(),
            cTDecimal,cTBinario,cTOctal,cTHexadecimal,cTFlotante,cTCadena,cTCaracter,
            cTCompleja,cTBooleana,cTNone,cTLista,cTRango,cTVariant,cTTupla,cTDiccionarios,cTConjuntos,lex));
            return true;
        }
        else{
            switch(sTT2.getToken()){
                case -4:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TD";
                    break;
                case -10:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TF";
                    break;
                case -8:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TS";
                    break;
                case -9:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TS";
                    break;
                case -12:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TCH";
                    break;
                case -5:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDB";
                    break;
                case -6:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDH";
                    break;
                case -7:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDO";
                    break;
                case -11:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TC";
                    break;
                case -54:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -56:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -105:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -99:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TL";
                    break;
                case -100:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TLA";
                    break;
                case -101:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TT";
                    break;
                case -103:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TCT";
                    break;
                case -104:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDIC";
                    break;
                case -53:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TN";
                    break;
                case -102:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TR";
                    break;
                case -106:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TV";
                    break;

            }
            listaSemantica.add(new SemanticaFase1(operandosSemantica.getLast().getLine(),
            cTDecimal,cTBinario,cTOctal,cTHexadecimal,cTFlotante,cTCadena,cTCaracter,
            cTCompleja,cTBooleana,cTNone,cTLista,cTRango,cTVariant,cTTupla,cTDiccionarios,cTConjuntos,lex));
            return false;
            


        }
    }
    
    public boolean expRegla1010(StrikerToken sTT2){
        if(sTT2.getToken()==-53){
            ResultSet rs;
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                boolean encontrado=false;
                boolean tipoFuncion1=false;
                boolean tipoProcedimiento1=false;
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+sTT2.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        encontrado=true;
                        if(rs.getString("Clase").equals("Funcion")){
                            tipoFuncion1=true;
                        }
                        else if(rs.getString("Clase").equals("Procedimiento")){
                            tipoProcedimiento1=true;
                        }
                        
                        if(tipoFuncion1){
                            String cadena2 = rs.getString("Rango");
                            String []valores2 = cadena2.split(",");
                            boolean valorAceptado=false;
                            for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                if(Integer.parseInt(valores2[i])==-105 || Integer.parseInt(valores2[i])==-54 || Integer.parseInt(valores2[i])==-56 || operandosSemantica.getLast().getToken()==-106){
                                    sTT2.setToken(-105);
                                }
                            }
                        }
                        else if(tipoProcedimiento1){
                            sTT2.setToken(-106);
                        }
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(sTT2.getToken()==-105||sTT2.getToken()==-54||sTT2.getToken()==-56 || sTT2.getToken()==-106){
            return true;
        }
        else{
            return false;
        }
    }
//    public boolean expRegla1020(StrikerToken sTT2, String lex){
//        if(sTT2.getToken()==-105||sTT2.getToken()==-54||sTT2.getToken()==-56|| sTT2.getToken()==-106){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    
    public String asignacion2(StrikerToken sTT2, String lex){
        
        if(operandosSemantica.getLast().getToken()==-53){
            ResultSet rs;
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                boolean encontrado=false;
                boolean tipoFuncion1=false;
                boolean tipoProcedimiento1=false;
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+operandosSemantica.getLast().getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        operandosSemantica.getLast().setToken(-106);
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(sTT2.getToken()==-53){
            ResultSet rs;
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                boolean encontrado=false;
                boolean tipoFuncion1=false;
                boolean tipoProcedimiento1=false;
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+sTT2.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        encontrado=true;
                        if(rs.getString("Clase").equals("Funcion")){
                            tipoFuncion1=true;
                        }
                        else if(rs.getString("Clase").equals("Procedimiento")){
                            tipoProcedimiento1=true;
                        }
                        
                        if(tipoFuncion1){
                            String cadena2 = rs.getString("Rango");
                            String []valores2 = cadena2.split(",");
                            boolean valorAceptado=false;
                            for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                if(operandosSemantica.getLast().getToken()==Integer.parseInt(valores2[i]) || operandosSemantica.getLast().getToken()==-106){
                                    sTT2.setToken(operandosSemantica.getLast().getToken());
                                }
                            }
                        }
                        else if(tipoProcedimiento1){
                            sTT2.setToken(-106);
                        }
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(sTT2.getToken()==-8 || sTT2.getToken()==-9){
            sTT2.setToken(-8);
        }
        if(operandosSemantica.getLast().getToken() == -8 || operandosSemantica.getLast().getToken() == -9){
            operandosSemantica.getLast().setToken(-8);
        }
        if(sTT2.getToken()==-54 || sTT2.getToken()==-56 || sTT2.getToken()==-105){
            sTT2.setToken(-105);
        }
        if(operandosSemantica.getLast().getToken() == -54 || operandosSemantica.getLast().getToken() == -56 || operandosSemantica.getLast().getToken() == -105){
            operandosSemantica.getLast().setToken(-105);
        }
        if(sTT2.getToken()==operandosSemantica.getLast().getToken()){
            
            switch(operandosSemantica.getLast().getToken()){
                 case -4:
                    lex=simboloTab2(-4)+"<-TD";
                    break;
                case -10:
                    lex=simboloTab2(-10)+"<-TF";
                    break;
                case -8:
                    lex=simboloTab2(-8)+"<-TS";
                    break;
                case -9:
                    lex=simboloTab2(-9)+"<-TS";
                    break;
                case -12:
                    lex=simboloTab2(-12)+"<-TCH";
                    break;
                case -5:
                    lex=simboloTab2(-5)+"<-TDB";
                    break;
                case -6:
                    lex=simboloTab2(-6)+"<-TDH";
                    break;
                case -7:
                    lex=simboloTab2(-7)+"<-TDO";
                    break;
                case -11:
                    lex=simboloTab2(-11)+"<-TC";
                    break;
                case -54:
                    lex=simboloTab2(-54)+"<-TB";
                    break;
                case -56:
                    lex=simboloTab2(-56)+"<-TB";
                    break;
                case -105:
                    lex=simboloTab2(-105)+"<-TB";
                    break;
                case -99:
                    lex=simboloTab2(-99)+"<-TL";
                    break;
                case -100:
                    lex=simboloTab2(-100)+"<-TLA";
                    break;
                case -101:
                    lex=simboloTab2(-101)+"<-TT";
                    break;
                case -103:
                    lex=simboloTab2(-103)+"<-TCT";
                    break;
                case -104:
                    lex=simboloTab2(-104)+"<-TDIC";
                    break;
                case -53:
                    lex=simboloTab2(-53)+"<-TN";
                    break;
                case -102:
                    lex=simboloTab2(-102)+"<-TR";
                    break;
                case -106:
                    lex=simboloTab2(-106)+"<-TV";
                    break;

            }
//            listaSemantica.add(new SemanticaFase1(operandosSemantica.getLast().getLine(),
//            cTDecimal,cTBinario,cTOctal,cTHexadecimal,cTFlotante,cTCadena,cTCaracter,
//            cTCompleja,cTBooleana,cTNone,cTLista,cTRango,cTVariant,cTTupla,cTDiccionarios,cTConjuntos,lex));
            return lex;
        }
        else{
            switch(sTT2.getToken()){
                 case -4:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TD";
                    break;
                case -10:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TF";
                    break;
                case -8:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TS";
                    break;
                case -9:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TS";
                    break;
                case -12:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TCH";
                    break;
                case -5:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDB";
                    break;
                case -6:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDH";
                    break;
                case -7:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDO";
                    break;
                case -11:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TC";
                    break;
                case -54:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -56:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -105:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TB";
                    break;
                case -99:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TL";
                    break;
                case -100:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TLA";
                    break;
                case -101:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TT";
                    break;
                case -103:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TCT";
                    break;
                case -104:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TDIC";
                    break;
                case -53:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TN";
                    break;
                case -102:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TR";
                    break;
                case -106:
                    lex=simboloTab2(operandosSemantica.getLast().getToken())+"<-TV";
                    break;

            }
//            listaSemantica.add(new SemanticaFase1(operandosSemantica.getLast().getLine(),
//            cTDecimal,cTBinario,cTOctal,cTHexadecimal,cTFlotante,cTCadena,cTCaracter,
//            cTCompleja,cTBooleana,cTNone,cTLista,cTRango,cTVariant,cTTupla,cTDiccionarios,cTConjuntos,lex));
            return lex;
            


        }
    }
    
    public String simboloTab(int a){
        String lex="";
        switch(operandosSemantica.getLast().getToken()){
            case -4:
                lex="D";
                break;
            case -10:
                lex="F";
                break;
            case -8:
                lex="S";
                break;
            case -9:
                lex="S";
                break;
            case -12:
                lex="CH";
                break;
            case -5:
                lex="DB";
                break;
            case -6:
                lex="DH";
                break;
            case -7:
                lex="DO";
                break;
            case -11:
                lex="C";
                break;
            case -54:
                lex="B";
                break;
            case -56:
                lex="B";
                break;
            case -105:
                lex="B";
                break;
            case -99:
                lex="L";
                break;
            case -100:
                lex="LA";
                break;
            case -101:
                lex="T";
                break;
            case -103:
                lex="CT";
                break;
            case -104:
                lex="DIC";
                break;
            case -53:
                lex="N";
                break;
            case -102:
                lex="R";
                break;
            case -106:
                lex="V";
                break;
        }
        return lex;
    }
    
    public String simboloTab2(int a){
        String lex="";
        switch(a){
            case -4:
                lex="D";
                break;
            case -10:
                lex="F";
                break;
            case -8:
                lex="S";
                break;
            case -9:
                lex="S";
                break;
            case -12:
                lex="CH";
                break;
            case -5:
                lex="DB";
                break;
            case -6:
                lex="DH";
                break;
            case -7:
                lex="DO";
                break;
            case -11:
                lex="C";
                break;
            case -54:
                lex="B";
                break;
            case -56:
                lex="B";
                break;
            case -105:
                lex="B";
                break;
            case -99:
                lex="L";
                break;
            case -100:
                lex="LA";
                break;
            case -101:
                lex="T";
                break;
            case -103:
                lex="CT";
                break;
            case -104:
                lex="DIC";
                break;
            case -53:
                lex="N";
                break;
            case -102:
                lex="R";
                break;
            case -106:
                lex="V";
                break;
        }
        return lex;
    }
    
    public boolean variableDeclarada(String lexema, Stack<Integer> Ambi) {
        boolean dec = false;
        ResultSet rs;
        AuxAmbi = (Stack<Integer>) Ambi.clone();
        do{
            String SQL= "SELECT * FROM Ambito2 WHERE Id='"+lexema+"' AND Ambito="+AuxAmbi.peek();
            rs = ConsultasTabla.getTabla(SQL);
            //System.out.println(SQL+" Variable Declarada");
            try{
                while (rs.next()) {
                    //System.out.println("Si Declarada");
                    dec=true;
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

            }
            AuxAmbi.pop();
        }
        while(!AuxAmbi.isEmpty());
        return dec;
    }
    
    
    public boolean duplicados(String x, int y){
        boolean dup = false;
        ResultSet rs = ConsultasTabla.getTabla("SELECT * FROM Ambito2 WHERE Id='"+x+"' AND Ambito="+y);
        //System.out.println("SELECT * FROM Ambito2 WHERE Id='"+x+"' AND Ambito="+y);
        try{
            if (rs.next()) {
                dup=true;
                
                //System.out.println("ESTA DUPLICADA ALV");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            
        }
        return dup;
    }
    
    public boolean duplicadosFuncion(String x, int y){
        boolean dup = false;
        ResultSet rs = ConsultasTabla.getTabla("SELECT * FROM Ambito2 WHERE Id='"+x+"' AND Ambito="+y+" AND Tipo='None'");
        //System.out.println("SELECT * FROM Ambito2 WHERE Id='"+x+"' AND Ambito="+y);
        try{
            if (rs.next()) {
                dup=true;
                //System.out.println("ESTA DUPLICADA ALV");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            
        }
        return dup;
    }
    
    //METODOS DE SINTAXIS
    
    public boolean noSiTerminal(int x){
        if (x==133) {
            return false;
        }
        else if (x<700){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void transformarALink(){
        int tokk;
        for (int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).getToken()!=-3 && tokens.get(i).getToken()!=-2){
                
                if(tokens.get(i).getToken()==-9){
                    tokk=-8;
                }else{
                    tokk=tokens.get(i).getToken();
                }
                tokensSintx.add(new StrikerToken(tokk,tokens.get(i).getLine(),tokens.get(i).getLexema()));
            }
         }
    }
    
    public int columnSintaxis(int tok) {
        int colum=0;
        switch(tok){
                case -1:
                    colum=2;
                break;
                case -4:
                    colum=34;
                break;
                case -5:
                    colum=35;
                break;
                case -6:
                    colum=36;
                break;
                case -7:
                    colum=37;
                break;
                case -8:
                    colum=28;
                break;
                case -9:
                    colum=28;
                break;
                case -10:
                    colum=27;
                break;
                case -11:
                    colum=30;
                break;
                case -12:
                    colum=29;
                break;
                case -13:
                    colum=39;
                break;
                case -14:
                    colum=38;
                break;
                case -15:
                    colum=14;
                break;
                case -16:
                    colum=21;
                break;
                case -17:
                    colum=15;
                break;
                case -18:
                    colum=16;
                break;
                case -19:
                    colum=17;
                break;
                case -20:
                    colum=40;
                break;
                case -21:
                    colum=41;
                break;
                case -22:
                    colum=42;
                break;
                case -23:
                    colum=22;
                break;
                case -24:
                    colum=18;
                break;
                case -25:
                    colum=74;
                break;
                case -26:
                    colum=75;
                break;
                case -27:
                    colum=79;
                break;
                case -28:
                    colum=78;
                break;
                case -29:
                    colum=76;
                break;
                case -30:
                    colum=77;
                break;
                case -31:
                    colum=24;
                break;
                case -32:
                    colum=25;
                break;
                case -33:
                    colum=26;
                break;
                case -34:
                    colum=19;
                break;
                case -35:
                    colum=20;
                break;
                case -36:
                    colum=8;
                break;
                case -37:
                    colum=9;
                break;
                case -38:
                    colum=4;
                break;
                case -39:
                    colum=12;
                break;
                case -40:
                    colum=3;
                break;
                case -41:
                    colum=13;
                break;
                case -42:
                    colum=5;
                break;
                case -43:
                    colum=11;
                break;
                case -44:
                    colum=6;
                break;
                case -45:
                    colum=58;
                break;
                case -46:
                    colum=61;
                break;
                case -47:
                    colum=60;
                break;
                case -48:
                    colum=63;
                break;
                case -49:
                    colum=59;
                break;
                case -50:
                    colum=62;
                break;
                case -51:
                    colum=64;
                break;
                case -52:
                    colum=83;
                break;
                case -53:
                    colum=33;
                break;
                case -54:
                    colum=31;
                break;
                case -55:
                    colum=50;
                break;
                case -56:
                    colum=32;
                break;
                case -57:
                    colum=51;
                break;
                case -58:
                    colum=54;
                break;
                case -59:
                    colum=55;
                break;
                case -60:
                    colum=1;
                break;
                case -61:
                    colum=43;
                break;
                case -62:
                    colum=44;
                break;
                case -63:
                    colum=48;
                break;
                case -64:
                    colum=47;
                break;
                case -65:
                    colum=84;
                break;
                case -66:
                    colum=82;
                break;
                case -67:
                    colum=85;
                break;
                case -68:
                    colum=86;
                break;
                case -69:
                    colum=45;
                break;
                case -70:
                    colum=87;
                break;
                case -71:
                    colum=52;
                break;
                case -72:
                    colum=88;
                break;
                case -73:
                    colum=49;
                break;
                case -74:
                    colum=89;
                break;
                case -75:
                    colum=80;
                break;
                case -76:
                    colum=81;
                break;
                case -77:
                    colum=23;
                break;
                case -78:
                    colum=10;
                break;
                case -79:
                    colum=7;
                break;
                case -80:
                    colum=90;
                break;
                case -81:
                    colum=91;
                break;
                case -82:
                    colum=92;
                break;
                case -83:
                    colum=93;
                break;
                case -84:
                    colum=94;
                break;
                case -85:
                    colum=65;
                break;
                case -86:
                    colum=66;
                break;
                case -87:
                    colum=67;
                break;
                case -88:
                    colum=68;
                break;
                case -89:
                    colum=69;
                break;
                case -90:
                    colum=70;
                break;
                case -91:
                    colum=71;
                break;
                case -92:
                    colum=72;
                break;
                case -93:
                    colum=73;
                break;
                case -94:
                    colum=46;
                break;
                case -95:
                    colum=57;
                break;
                case -96:
                    colum=56;
                break;
                case -97:
                    colum=53;
                break;
                case -98:
                    colum=95;
                    break;
        }
        return colum;
    }

    public int calculoEstado(Integer tok, int col) {
        int fila;
        fila=tok-700;
        int celda;
        
        String nombreArchivoSin = "Matriz-Sint.xls";
        String rutaArchivoSin = "C:\\Ficheros-Excel\\" + nombreArchivoSin;
        try (FileInputStream file = new FileInputStream(new File(rutaArchivoSin))){
             // leer archivo excel
            Workbook worbook = new HSSFWorkbook(file);
            //obtener la hoja que se va leer
            Sheet sheet = worbook.getSheetAt(0);
            Row row = sheet.getRow(fila);
            Cell cell = row.getCell(col);
            celda=(int)cell.getNumericCellValue();
            return celda;
        } catch (Exception e) {
            e.getMessage();
            return -1;
        }
    }
    
    public StrikerToken tipoSemantica1(StrikerToken tok1, StrikerToken tok2, StrikerToken tok3, DefaultTableModel modeloErrores, JTable jTE) {
        ResultSet rs;
        String lex="";
        String celda="";
        boolean tipoFuncion1=false;
        boolean tipoProcedimiento1=false;
        boolean tipoFuncion2=false;
        boolean tipoProcedimiento2=false;
        boolean encontrado=false;
        if(tok1.getToken()==-53){
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();

                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+tok1.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        encontrado=true;
                        if(rs.getString("Clase").equals("Funcion")){
                            tipoFuncion1=true;
                        }
                        else if(rs.getString("Clase").equals("Procedimiento")){
                            tipoProcedimiento1=true;
                        }
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        encontrado=false;
        }
        if(tok2.getToken()==-53){
            try{
                AuxAmbi = (Stack<Integer>) Ambi.clone();
                do{
                    int ultiAmbito=AuxAmbi.pop();
                    String SQL="SELECT * FROM Ambito2 WHERE Id='"+tok2.getLexema()+"' AND (Clase='Funcion' OR Clase='Procedimiento') AND Ambito="+ultiAmbito;
                    rs = ConsultasTabla.getTabla(SQL);
                    if (rs.next()) {
                        encontrado=true;
                        if(rs.getString("Clase").equals("Funcion")){
                            tipoFuncion2=true;
                        }
                        else if(rs.getString("Clase").equals("Procedimiento")){
                            tipoProcedimiento2=true;
                        }
                    }
                }while(!AuxAmbi.empty() && !encontrado);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            encontrado=false;
        }
        
        if(!tipoProcedimiento2){
            if(!tipoFuncion2){
                if(!tipoProcedimiento1){
                    if(!tipoFuncion1){
                        celda = busquedaExcel(tok2.getToken(),tok1.getToken(),tok3.getLexema());
                    }
                    else{
                        try{
                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                            StrikerToken stSus;
                            do{
                                int ultiAmbito=AuxAmbi.pop();
                                String SQL="SELECT Rango FROM Ambito2 WHERE Id='"+tok1.getLexema()+"' AND Clase='Funcion' AND Ambito="+ultiAmbito;
                                rs = ConsultasTabla.getTabla(SQL);
                                if (rs.next()) {
                                    encontrado=true;
                                    String cadena = rs.getString("Rango");
                                    String[] valores = cadena.split(",");
                                    boolean valorAcptado=false;
                                    for (int i = 0; i < valores.length && !valorAcptado; i++) {
                                        celda = busquedaExcel(tok2.getToken(),Integer.parseInt(valores[i]),tok3.getLexema());
                                        if(!celda.equals("error")){
                                            valorAcptado=true;
                                        }
                                    }
                                }
                            }while(!AuxAmbi.empty() && !encontrado);
                        }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    celda = busquedaExcel(tok2.getToken(),-106,tok3.getLexema());
                    errores.add(new KaijuError(tok1.getLine(),837,"Procedimiento no maneja return","Semantica 2",tok1.getLexema()));                        
                    modeloErrores.addRow(new Object[]{tok1.getLine(),837,"Procedimiento no maneja return","Semantica 2",tok1.getLexema()});
                    jTE.setModel(modeloErrores);
                }
            }
            else{
                String[] valores1=null;
                try{
                    AuxAmbi = (Stack<Integer>) Ambi.clone();
                    do{
                        int ultiAmbito=AuxAmbi.pop();
                        String SQL="SELECT Rango FROM Ambito2 WHERE Id='"+tok2.getLexema()+"' AND Clase='Funcion' AND Ambito="+ultiAmbito;
                        rs = ConsultasTabla.getTabla(SQL);
                        if (rs.next()) {
                            encontrado=true;
                            String cadena = rs.getString("Rango");
                            valores1 = cadena.split(",");
                        }
                    }while(!AuxAmbi.empty() && !encontrado);
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
                encontrado=false;
                if(!tipoProcedimiento1){
                    if(!tipoFuncion1){
                        boolean valorAcptado=false;
                        for (int i = 0; i < valores1.length && !valorAcptado; i++) {
                            celda = busquedaExcel(Integer.parseInt(valores1[i]),tok1.getToken(),tok3.getLexema());
                            if(!celda.equals("error")){
                                valorAcptado=true;
                            }
                        }
                    }
                    else{
                        String[] valores2=null;
                        try{
                            AuxAmbi = (Stack<Integer>) Ambi.clone();
                            boolean valorAceptado=false;
                            do{
                                int ultiAmbito=AuxAmbi.pop();
                                String SQL="SELECT Rango FROM Ambito2 WHERE Id='"+tok1.getLexema()+"' AND Clase='Funcion' AND Ambito="+ultiAmbito;
                                rs = ConsultasTabla.getTabla(SQL);
                                if (rs.next()) {
                                    encontrado=true;
                                    String cadena2 = rs.getString("Rango");
                                    valores2 = cadena2.split(",");
                                    for (int i = 0; i < valores2.length && !valorAceptado; i++) {
                                        for (int j = 0; j < valores1.length && !valorAceptado; j++) {
                                            celda = busquedaExcel(Integer.parseInt(valores1[i]),Integer.parseInt(valores2[j]),tok3.getLexema());
                                            if(!celda.equals("error")){
                                                valorAceptado=true;
                                            }
                                        }
                                        
                                    }
                                }
                            }while(!AuxAmbi.empty() && !encontrado);
                        }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    errores.add(new KaijuError(operandosSemantica.getLast().getLine(),837,"Procedimiento no maneja return","Semantica 2",operandosSemantica.getLast().getLexema()));                        
                    modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),837,"Procedimiento no maneja return","Semantica 2",operandosSemantica.getLast().getLexema()});
                    jTE.setModel(modeloErrores);
                    boolean valorAcptado=false;
                    for (int i = 0; i < valores1.length && !valorAcptado; i++) {
                        celda = busquedaExcel(-106,Integer.parseInt(valores1[i]),tok3.getLexema());
                        if(!celda.equals("error")){
                            valorAcptado=true;
                        }
                    }
                }
            }
        }
        else{
            errores.add(new KaijuError(operandosSemantica.getLast().getLine(),837,"Procedimiento no maneja return","Semantica 2",operandosSemantica.getLast().getLexema()));                        
            modeloErrores.addRow(new Object[]{operandosSemantica.getLast().getLine(),837,"Procedimiento no maneja return","Semantica 2",operandosSemantica.getLast().getLexema()});
            jTE.setModel(modeloErrores);
            if(!tipoProcedimiento1){
                if(!tipoFuncion1){
                    celda = busquedaExcel(-106,tok2.getToken(),tok3.getLexema());
                }
                else{
                    try{
                        AuxAmbi = (Stack<Integer>) Ambi.clone();
                        do{
                            int ultiAmbito=AuxAmbi.pop();
                            String SQL="SELECT Rango FROM Ambito2 WHERE Id='"+tok1.getLexema()+"' AND Clase='Funcion' AND Ambito="+ultiAmbito;
                            rs = ConsultasTabla.getTabla(SQL);
                            if (rs.next()) {
                                encontrado=true;
                                String cadena = rs.getString("Rango");
                                String[] valores = cadena.split(",");
                                boolean valorAcptado=false;
                                for (int i = 0; i < valores.length && !valorAcptado; i++) {
                                    celda = busquedaExcel(-106,Integer.parseInt(valores[i]),tok3.getLexema());
                                    if(!celda.equals("error")){
                                        valorAcptado=true;
                                    }
                                }
                            }
                        }while(!AuxAmbi.empty() && !encontrado);
                    }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, "Alguno de los campos no tiene informacion", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else{
                errores.add(new KaijuError(tok1.getLine(),837,"Procedimiento no maneja return","Semantica 2",tok1.getLexema()));                        
                modeloErrores.addRow(new Object[]{tok1.getLine(),837,"Procedimiento no maneja return","Semantica 2",tok1.getLexema()});
                jTE.setModel(modeloErrores);
                celda = busquedaExcel(-106,-106,tok3.getLexema());
            }
        }
        int tok4=0;
        if(celda.equals("Decimal")){
            tok4=-4;
            lex="TD";
            cTDecimal++;
        }
        else if(celda.equals("Float")){
            tok4=-10;
            lex="TF";
            cTFlotante++;
        }
        else if(celda.equals("Cadena")){
            tok4=-8;
            lex="TS";
            cTCadena++;
        }
        else if(celda.equals("Char")){
            tok4=-12;
            lex="TCH";
            cTCaracter++;
        }
        else if(celda.equals("Binario")){
            tok4=-5;
            lex="TDB";
            cTBinario++;
        }
        else if(celda.equals("Hexadecimal")){
            tok4=-6;
            lex="TDH";
            cTHexadecimal++;
        }
        else if(celda.equals("Octal")){
            tok4=-7;
            lex="TDO";
            cTOctal++;
        }
        else if(celda.equals("Compleja")){
            tok4=-11;
            lex="TC";
            cTCompleja++;
        }
        else if(celda.equals("Boolean")){
            tok4=-105;
            lex="TB";
            cTBooleana++;
        }
        else if(celda.equals("Listas")){
            tok4=-99;
            lex="TL";
            cTLista++;
        }
        else if(celda.equals("Arreglos")){
            tok4=-100;
            lex="TLA";
            cTLista++;
        }
        else if(celda.equals("Tuplas")){
            tok4=-101;
            lex="TT";
            cTTupla++;
        }
        else if(celda.equals("Conjuntos")){
            tok4=-103;
            lex="TCT";
            cTConjuntos++;
        }
        else if(celda.equals("Diccionario")){
            tok4=-104;
            lex="TDIC";
            cTDiccionarios++;
        }
        else if(celda.equals("None")){
            tok4=-53;
            lex="TN";
            cTNone++;
        }
        else if(celda.equals("Rango")){
            tok4=-102;
            lex="TR";
            cTRango++;
        }
        else if(celda.equals("Variante")){
            tok4=-106;
            lex="TV";
            cTVariant++;
        }
        else if(celda.equals("error")){
            tok4=-106;
            lex="TV";
            cTVariant++;

        }
        else{
            return new StrikerToken();
        }
        return new StrikerToken(tok4,tok2.getLine(),lex);
        
    }
    
    public String busquedaExcel(int tok2, int tok1, String tok3){
        String celda="";
        int hoja=0;
        int fila=0;
        int columna=0;
        switch(tok2){
                case -4:
                    fila=1;
                    break;
                case -5:
                    fila=5;
                    break;
                case -6:
                    fila=6;
                    break;
                case -7:
                    fila=7;
                    break;
                case -8:
                    fila=3;
                    break;
                case -9:
                    fila=3;
                    break;
                case -10:
                    fila=2;
                    break;
                case -11:
                    fila=8;
                    break;
                case -12:
                    fila=4;
                    break;
                case -53:
                    fila=15;
                    break;
                case -54:
                    fila=9;
                    break;
                case -56:
                    fila=9;
                    break;
                case -99:
                    fila=10;
                    break;
                case -100:
                    fila=11;
                    break;
                case -101:
                    fila=12;
                    break;
                case -102:
                    fila=16;
                    break;
                case -103:
                    fila=13;
                    break;
                case -104:
                    fila=14;
                    break;
                case -105:
                    fila=9;
                    break;
                case -106:
                    fila=17;
                    break;
            }

            switch(tok1){
                case -4:
                    columna=1;
                    break;
                case -5:
                    columna=5;
                    break;
                case -6:
                    columna=6;
                    break;
                case -7:
                    columna=7;
                    break;
                case -8:
                    columna=3;
                    break;
                case -9:
                    columna=3;
                    break;
                case -10:
                    columna=2;
                    break;
                case -11:
                    columna=8;
                    break;
                case -12:
                    columna=4;
                    break;
                case -53:
                    columna=15;
                    break;
                case -54:
                    columna=9;
                    break;
                case -56:
                    columna=9;
                    break;
                case -99:
                    columna=10;
                    break;
                case -100:
                    columna=11;
                    break;
                case -101:
                    columna=12;
                    break;
                case -102:
                    columna=16;
                    break;
                case -103:
                    columna=13;
                    break;
                case -104:
                    columna=14;
                    break;
                case -105:
                    columna=9;
                    break;
                case -106:
                    columna=17;
                    break;
            }

            if(tok3.equals("+")){
                hoja=0;
            }
            else if(tok3.equals("-")){
                hoja=1;
            }
            else if(tok3.equals("*")){
                hoja=2;
            }
            else if(tok3.equals("/")){
                hoja=3;
            }//LOGICOS
            else if(tok3.equals("!")){
                hoja=5;
            }
            else if(tok3.equals("&&")){
                hoja=5;
            }
            else if(tok3.equals("||")){
                hoja=5;
            }
            else if(tok3.equals("|")){
                hoja=5;
            }
            else if(tok3.equals("&")){
                hoja=5;
            }
            else if(tok3.equals("^")){
                hoja=4;
            }
    //racionales
            else if(tok3.equals("<")){
                hoja=4;
            }
            else if(tok3.equals("<=")){
                hoja=4;
            }
            else if(tok3.equals(">")){
                hoja=4;
            }
            else if(tok3.equals(">=")){
                hoja=4;
            }
            else if(tok3.equals("==")){
                hoja=4;
            }
            else if(tok3.equals("!=")){
                hoja=4;
            }
            else if(tok3.equals("##")){
                hoja=4;
            }
            //DESPLAZAMIENTO
            else if(tok3.equals("<<")){
                hoja=6;
            }
            else if(tok3.equals(">>")){
                hoja=6;
            }
            //DIVISION ENTERA
            else if(tok3.equals("//")){
                hoja=7;
            }
            else if(tok3.equals("%")){
                hoja=7;
            }

            String nombreArchivoSin = "tablaSemantica1.xls";
            String rutaArchivoSin = "C:\\Ficheros-Excel\\" + nombreArchivoSin;
        try (
            FileInputStream file = new FileInputStream(new File(rutaArchivoSin))){
             // leer archivo excel
            Workbook worbook = new HSSFWorkbook(file);
            //obtener la hoja que se va leer
            Sheet sheet = worbook.getSheetAt(hoja);
            Row row = sheet.getRow(fila);
            Cell cell = row.getCell(columna);
            celda=cell.getStringCellValue();
        } catch (Exception e) {
            e.getMessage();
        }   
        return celda;
        
    }

    public int [] produccionPila(int estadoCelda) {
        int[] x = null;
        
        switch (estadoCelda){
                case 1:
                    x=new int[8];
                    x[0]=902;
                    x[1]=-43;
                    x[2]=703;
                    x[3]=946;
                    x[4]=740;
                    x[5]=-42;
                    x[6]=901;
                    x[7]=702;
                break;
                case 2:
                    x=new int[12];
                    x[0]=702;
                    x[1]=-36;
                    x[2]=905;
                    x[3]=701;
                    x[4]=921;
                    x[5]=-39;
                    x[6]=705;
                    x[7]=904;
                    x[8]=-38;
                    x[9]=-1;
                    x[10]=903;
                    x[11]=-60;
                break;
                case 3:
                    x=new int[7];
                    x[0]=702;
                    x[1]=-36;
                    x[2]=907;
                    x[3]=722;
                    x[4]=-44;
                    x[5]=-1;
                    x[6]=906;
                break;
                case 4:
                    x=new int[4];
                    x[0]=703;
                    x[1]=946;
                    x[2]=740;
                    x[3]=-36;
                break;
                case 5:
                    x=new int[4];
                    x[0]=704;
                    x[1]=-1;
                    x[2]=908;
                    x[3]=-37;
                break;
                case 6:
                    x=new int[3];
                    x[0]=704;
                    x[1]=-1;
                    x[2]=909;
                break;
                case 7:
                    x=new int[2];
                    x[0]=707;
                    x[1]=712;
                break;
                case 8:
                    x=new int[5];
                    x[0]=707;
                    x[1]=945;
                    x[2]=712;
                    x[3]=-15;
                    x[4]=944;
                break;
                case 9:
                    x=new int[5];
                    x[0]=707;
                    x[1]=945;
                    x[2]=712;
                    x[3]=-17;
                    x[4]=944;
                break;
                case 10:
                    x=new int[5];
                    x[0]=707;
                    x[1]=945;
                    x[2]=712;
                    x[3]=-18;
                    x[4]=944;
                break;
                case 11:
                    x=new int[5];
                    x[0]=707;
                    x[1]=945;
                    x[2]=712;
                    x[3]=-19;
                    x[4]=944;
                break;
                case 12:
                    x=new int[2];
                    x[0]=709;
                    x[1]=758;
                break;
                case 13:
                    x=new int[5];
                    x[0]=709;
                    x[1]=945;
                    x[2]=758;
                    x[3]=-24;
                    x[4]=944;
                break;
                case 14:
                    x=new int[2];
                    x[0]=711;
                    x[1]=720;
                break;
                case 15:
                    x=new int[5];
                    x[0]=711;
                    x[1]=945;
                    x[2]=720;
                    x[3]=-34;
                    x[4]=944;
                break;
                case 16:
                    x=new int[5];
                    x[0]=711;
                    x[1]=945;
                    x[2]=720;
                    x[3]=-35;
                    x[4]=944;
                break;
                case 17:
                    x=new int[2];
                    x[0]=713;
                    x[1]=733;
                break;
                case 18:
                    x=new int[5];
                    x[0]=713;
                    x[1]=945;
                    x[2]=733;
                    x[3]=-16;
                    x[4]=944;
                break;
                case 19:
                    x=new int[2];
                    x[0]=715;
                    x[1]=710;
                break;
                case 20:
                    x=new int[5];
                    x[0]=715;
                    x[1]=945;
                    x[2]=710;
                    x[3]=-31;
                    x[4]=944;
                break;
                case 21:
                    x=new int[2];
                    x[0]=717;
                    x[1]=718;
                break;
                case 22:
                    x=new int[5];
                    x[0]=717;
                    x[1]=945;
                    x[2]=718;
                    x[3]=-32;
                    x[4]=944;
                break;
                case 23:
                    x=new int[2];
                    x[0]=719;
                    x[1]=714;
                break;
                case 24:
                    x=new int[5];
                    x[0]=719;
                    x[1]=945;
                    x[2]=714;
                    x[3]=-33;
                    x[4]=944;
                break;
                case 25:
                    x=new int[2];
                    x[0]=721;
                    x[1]=706;
                break;
                case 26:
                    x=new int[5];
                    x[0]=721;
                    x[1]=945;
                    x[2]=706;
                    x[3]=-14;
                    x[4]=944;
                break;
                case 27:
                    x=new int[5];
                    x[0]=721;
                    x[1]=945;
                    x[2]=706;
                    x[3]=-13;
                    x[4]=944;
                break;
                case 28:
                    x=new int[1];
                    x[0]=723;
                break;
                case 29:
                    x=new int[2];
                    x[0]=-10;
                    x[1]=914;
                break;
                case 30:
                    x=new int[2];
                    x[0]=-8;
                    x[1]=915;
                break;
                case 31:
                    x=new int[2];
                    x[0]=-12;
                    x[1]=916;
                break;
                case 32:
                    x=new int[1];
                    x[0]=724;
                break;
                case 33:
                    x=new int[2];
                    x[0]=-11;
                    x[1]=917;
                break;
                case 34:
                    x=new int[2];
                    x[0]=-54;
                    x[1]=918;
                break;
                case 35:
                    x=new int[2];
                    x[0]=-56;
                    x[1]=918;
                break;
                case 36:
                    x=new int[1];
                    x[0]=726;
                break;
                case 37:
                    x=new int[2];
                    x[0]=-53;
                    x[1]=919;
                break;
                case 38:
                    x=new int[1];
                    x[0]=725;
                break;
                case 39:
                    x=new int[2];
                    x[0]=-4;
                    x[1]=910;
                break;
                case 40:
                    x=new int[2];
                    x[0]=-5;
                    x[1]=911;
                break;
                case 41:
                    x=new int[2];
                    x[0]=-6;
                    x[1]=913;
                break;
                case 42:
                    x=new int[2];
                    x[0]=-7;
                    x[1]=912;
                break;
                case 43:
                    x=new int[1];
                    x[0]=727;
                break;
                case 44:
                    x=new int[7];
                    x[0]=924;
                    x[1]=-39;
                    x[2]=728;
                    x[3]=923;
                    x[4]=708;
                    x[5]=922;
                    x[6]=-38;
                break;
                case 45:
                    x=new int[1];
                    x[0]=750;
                break;
                case 46:
                    x=new int[16];
                    x[0]=955;
                    x[1]=-39;
                    x[2]=724;
                    x[3]=928;
                    x[4]=751;
                    x[5]=-37;
                    x[6]=724;
                    x[7]=927;
                    x[8]=751;
                    x[9]=-37;
                    x[10]=724;
                    x[11]=926;
                    x[12]=751;
                    x[13]=925;
                    x[14]=-38;
                    x[15]=-61;
                break;
                case 47:
                    x=new int[7];
                    x[0]=933;
                    x[1]=-43;
                    x[2]=730;
                    x[3]=729;
                    x[4]=722;
                    x[5]=929;
                    x[6]=-42;
                break;
                case 48:
                    x=new int[4];
                    x[0]=728;
                    x[1]=923;
                    x[2]=708;
                    x[3]=-37;
                break;
                case 49:
                    x=new int[4];
                    x[0]=931;
                    x[1]=722;
                    x[2]=930;
                    x[3]=-79;
                break;
                case 50:
                    x=new int[7];
                    x[0]=730;
                    x[1]=729;
                    x[2]=934;
                    x[3]=932;
                    x[4]=722;
                    x[5]=932;
                    x[6]=-37;
                break;
                case 51:
                    x=new int[2];
                    x[0]=732;
                    x[1]=754;
                break;
                case 52:
                    x=new int[5];
                    x[0]=732;
                    x[1]=945;
                    x[2]=754;
                    x[3]=-22;
                    x[4]=944;
                break;
                case 53:
                    x=new int[1];
                    x[0]=734;
                break;
                case 54:
                    x=new int[1];
                    x[0]=722;
                break;
                case 55:
                    x=new int[2];
                    x[0]=735;
                    x[1]=-1;
                break;
                case 56:
                    x=new int[5];
                    x[0]=735;
                    x[1]=958;
                    x[2]=-1;
                    x[3]=-20;
                    x[4]=960;
                break;
                case 57:
                    x=new int[5];
                    x[0]=735;
                    x[1]=959;
                    x[2]=-1;
                    x[3]=-21;
                    x[4]=961;
                break;
                case 58:
                    x=new int[1];
                    x[0]=756;
                break;
                case 59:
                    x=new int[2];
                    x[0]=736;
                    x[1]=739;
                break;
                case 60:
                    x=new int[3];
                    x[0]=958;
                    x[1]=-20;
                    x[2]=960;
                break;
                case 61:
                    x=new int[3];
                    x[0]=959;
                    x[1]=-21;
                    x[2]=961;
                break;
                case 62:
                    x=new int[2];
                    x[0]=748;
                    x[1]=-78;
                break;
                case 63:
                    x=new int[2];
                    x[0]=737;
                    x[1]=746;
                break;
                case 64:
                    x=new int[1];
                    x[0]=708;
                break;
                case 65:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=738;
                    x[2]=-38;
                    x[3]=-62;
                break;
                case 66:
                    x=new int[1];
                    x[0]=-8;
                break;
                case 67:
                    x=new int[1];
                    x[0]=750;
                break;
                case 68:
                    x=new int[1];
                    x[0]=741;
                break;
                case 69:
                    x=new int[1];
                    x[0]=756;
                break;
                case 70:
                    x=new int[5];
                    x[0]=962;
                    x[1]=-39;
                    x[2]=743;
                    x[3]=-38;
                    x[4]=-69;
                break;
                case 71:
                    x=new int[5];
                    x[0]=962;
                    x[1]=-39;
                    x[2]=743;
                    x[3]=-38;
                    x[4]=-94;
                break;
                case 72:
                    x=new int[9];
                    x[0]=-96;
                    x[1]=745;
                    x[2]=744;
                    x[3]=946;
                    x[4]=740;
                    x[5]=-79;
                    x[6]=947;
                    x[7]=708;
                    x[8]=-64;
                break;
                case 73:
                    x=new int[11];
                    x[0]=-96;
                    x[1]=744;
                    x[2]=946;
                    x[3]=740;
                    x[4]=-79;
                    x[5]=957;
                    x[6]=708;
                    x[7]=-97;
                    x[8]=956;
                    x[9]=708;
                    x[10]=-63;
                break;
                case 74:
                    x=new int[8];
                    x[0]=-95;
                    x[1]=744;
                    x[2]=946;
                    x[3]=740;
                    x[4]=-79;
                    x[5]=948;
                    x[6]=708;
                    x[7]=-73;
                break;
                case 75:
                    x=new int[1];
                    x[0]=-55;
                break;
                case 76:
                    x=new int[1];
                    x[0]=-57;
                break;
                case 77:
                    x=new int[3];
                    x[0]=951;
                    x[1]=708;
                    x[2]=-71;
                break;
                case 78:
                    x=new int[1];
                    x[0]=708;
                break;
                case 79:
                    x=new int[4];
                    x[0]=742;
                    x[1]=953;
                    x[2]=708;
                    x[3]=-37;
                break;
                case 80:
                    x=new int[3];
                    x[0]=742;
                    x[1]=953;
                    x[2]=708;
                break;
                case 81:
                    x=new int[4];
                    x[0]=744;
                    x[1]=946;
                    x[2]=740;
                    x[3]=-36;
                break;
                case 82:
                    x=new int[8];
                    x[0]=745;
                    x[1]=744;
                    x[2]=946;
                    x[3]=740;
                    x[4]=-79;
                    x[5]=949;
                    x[6]=708;
                    x[7]=-58;
                break;
                case 83:
                    x=new int[4];
                    x[0]=744;
                    x[1]=946;
                    x[2]=740;
                    x[3]=-59;
                break;
                case 84:
                    x=new int[1];
                    x[0]=747;
                break;
                case 85:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-44;
                    
                break;
                case 86:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-45;
                break;
                case 87:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-49;
                break;
                case 88:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-47;
                break;
                case 89:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-46;
                break;
                case 90:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-50;
                break;
                case 91:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-48;
                break;
                case 92:
                    x=new int[2];
                    x[1]=944;
                    x[0]=-51;
                break;
                case 93:
                    x=new int[1];
                    x[0]=749;
                break;
                case 94:
                    x=new int[3];
                    x[0]=-39;
                    x[1]=-38;
                    x[2]=-85;
                break;
                case 95:
                    x=new int[3];
                    x[0]=-39;
                    x[1]=-38;
                    x[2]=-86;
                break;
                case 96:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-87;
                break;
                case 97:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-88;
                break;
                case 98:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-89;
                break;
                case 99:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-90;
                break;
                case 100:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-91;
                break;
                case 101:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-92;
                break;
                case 102:
                    x=new int[6];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-37;
                    x[3]=708;
                    x[4]=-38;
                    x[5]=-93;
                break;
                case 103:
                    x=new int[8];
                    x[0]=936;
                    x[1]=-41;
                    x[2]=752;
                    x[3]=935;
                    x[4]=708;
                    x[5]=751;
                    x[6]=920;
                    x[7]=-40;
                break;
                case 104:
                    x=new int[2];
                    x[0]=950;
                    x[1]=-14;
                break;
                case 105:
                    x=new int[5];
                    x[0]=753;
                    x[1]=935;
                    x[2]=708;
                    x[3]=751;
                    x[4]=-79;
                break;
                case 106:
                    x=new int[4];
                    x[0]=935;
                    x[1]=708;
                    x[2]=751;
                    x[3]=-79;
                break;
                case 107:
                    x=new int[2];
                    x[0]=755;
                    x[1]=716;
                break;
                case 108:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-25;
                    x[4]=944;
                break;
                case 109:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-26;
                    x[4]=944;
                break;
                case 110:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-29;
                    x[4]=944;
                break;
                case 111:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-30;
                    x[4]=944;
                break;
                case 112:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-28;
                    x[4]=944;
                break;
                case 113:
                    x=new int[5];
                    x[0]=755;
                    x[1]=945;
                    x[2]=716;
                    x[3]=-27;
                    x[4]=944;
                break;
                case 114:
                    x=new int[3];
                    x[0]=755;
                    x[1]=716;
                    x[2]=-75;
                break;
                case 115:
                    x=new int[3];
                    x[0]=755;
                    x[1]=716;
                    x[2]=-76;
                break;
                case 116:
                    x=new int[3];
                    x[0]=755;
                    x[1]=716;
                    x[2]=-66;
                break;
                case 117:
                    x=new int[3];
                    x[0]=755;
                    x[1]=716;
                    x[2]=-52;
                break;
                case 118:
                    x=new int[1];
                    x[0]=757;
                break;
                case 119:
                    x=new int[6];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-37;
                    x[3]=708;
                    x[4]=-38;
                    x[5]=-65;
                break;
                case 120:
                    x=new int[6];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-37;
                    x[3]=708;
                    x[4]=-38;
                    x[5]=-67;
                break;
                case 121:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-68;
                break;
                case 122:
                    x=new int[6];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-37;
                    x[3]=708;
                    x[4]=-38;
                    x[5]=-70;
                break;
                case 123:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-72;
                break;
                case 124:
                    x=new int[3];
                    x[0]=-39;
                    x[1]=-38;
                    x[2]=-74;
                break;
                case 125:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-80;
                break;
                case 126:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-81;
                break;
                case 127:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-82;
                break;
                case 128:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-83;
                break;
                case 129:
                    x=new int[4];
                    x[0]=-39;
                    x[1]=708;
                    x[2]=-38;
                    x[3]=-84;
                break;
                case 130:
                    x=new int[2];
                    x[0]=759;
                    x[1]=731;
                break;
                case 131:
                    x=new int[5];
                    x[0]=759;
                    x[1]=945;
                    x[2]=731;
                    x[3]=-23;
                    x[4]=944;
                break;
                case 132:
                    x=new int[3];
                    x[0]=759;
                    x[1]=731;
                    x[2]=-77;
                break;
                case 133:
                    x=new int[1];
                    x[0]=133;
                break;
                case 134:
                    x=new int [5];
                    x[0]=954;
                    x[1]=-39;
                    x[2]=743;
                    x[3]=952;
                    x[4]=-38;
                    break;
        }
        return x;
    }

    public String cambioDescrip(int estadoCelda) {
        String descrip="";
        switch(estadoCelda){
            case 800:
                descrip="Se puso de mas";
                break;
                case 801:
                descrip="Se esperaba def, id, {";
                break;
                case 802:
                descrip="Se esperaba def, id";
                break;
                case 803:
                descrip="Se esperaba ,(coma)";
                break;
                case 804:
                descrip="Se esperaba ConstFlotante, ConstCadena, ConstCaracter, Decimal, Binario, Hexadecimal, Octal, ConstCompleja, true, false, (, [, range, {, none, id, ++, --, findall, remplace, len, sample, choice, random, randrange, mean, median, variance, sum";
                break;
                case 805:
                descrip="Se esperaba *, /, //, %";
                break;
                case 806:
                descrip="Se esperaba ||";
                break;
                case 807:
                descrip="Se esperaba <<, >>";
                break;
                case 808:
                descrip="Se esperaba **";
                break;
                case 809:
                descrip="Se esperaba &";
                break;
                case 810:
                descrip="Se esperaba |";
                break;
                case 811:
                descrip="Se esperaba ^";
                break;
                case 812:
                descrip="Se esperaba -, +";
                break;
                case 813:
                descrip="Se esperaba ConstFlotante, ConstCadena, ConstCaracter, Decimal, Binario, Hexadecimal, Octal, ConstCompleja, true, false, (, [, range, {, none";
                break;
                case 814:
                descrip="Se esperaba Decimal, Binario, Hexadecimal, Octal";
                break;
                case 815:
                descrip="Se esperaba (, [, range, {";
                break;
                case 816:
                descrip="Se esperaba :";
                break;
                case 817:
                descrip="Se esperaba !";
                break;
                case 818:
                descrip="Se esperaba [, ++, --, ., =,+=,/=,*=, -=, //=, **=, %=";
                break;
                case 819:
                descrip="Se esperaba =,+=,/=,*=, -=, //=, **=, %=";
                break;
                case 820:
                descrip="Se esperaba ConstFlotante, ConstCadena, ConstCaracter, Decimal, Binario, Hexadecimal, Octal, ConstCompleja, true, false, (, [, range, {, none, id, ++, --, findall, remplace, len, sample, choice, random, randrange, mean, median, variance, sum, input";
                break;
                case 821:
                descrip="Se esperaba ConstCadena";
                break;
                case 822:
                descrip="Se esperaba [";
                break;
                case 823:
                descrip="Se esperaba ConstFlotante, ConstCadena, ConstCaracter, Decimal, Binario, Hexadecimal, Octal, ConstCompleja, true, false, (, [, range, {, none, id, ++, --, findall, remplace, len, sample, choice, random, randrange, mean, median, variance, sum, print, println, if, for, while, break, continue, return";
                break;
                case 824:
                descrip="Se esperaba ;";
                break;
                case 825:
                descrip="Se esperaba elif, else";
                break;
                case 826:
                descrip="Se esperaba sort, reverse, count, index, append, extend, pop, remove, insert";
                break;
                case 827:
                descrip="Se esperaba -";
                break;
                case 828:
                descrip="Se esperaba <, <=, ==, !=, >=, >, is, isnot, in, innot";
                break;
                case 829:
                descrip="Se esperaba findall, remplace, len, sample, choice, random, randrange, mean, median, variance, sum";
                break;
                case 830:
                descrip="Se esperaba &&, ##";
                break;
                case 831:
                descrip="Se esperaba id";
                break;
        }
        return descrip;
    }

    public int getcProg() {
        return cProg;
    }

    public void setcProg(int cProg) {
        this.cProg = cProg;
    }

    public int getcConst() {
        return cConst;
    }

    public void setcConst(int cConst) {
        this.cConst = cConst;
    }

    public int getcConstEnt() {
        return cConstEnt;
    }

    public void setcConstEnt(int cConstEnt) {
        this.cConstEnt = cConstEnt;
    }

    public int getcListTupRa() {
        return cListTupRa;
    }

    public void setcListTupRa(int cListTupRa) {
        this.cListTupRa = cListTupRa;
    }

    public int getcTerminoPas() {
        return cTerminoPas;
    }

    public void setcTerminoPas(int cTerminoPas) {
        this.cTerminoPas = cTerminoPas;
    }

    public int getcEleva() {
        return cEleva;
    }

    public void setcEleva(int cEleva) {
        this.cEleva = cEleva;
    }

    public int getcSimplExpPas() {
        return cSimplExpPas;
    }

    public void setcSimplExpPas(int cSimplExpPas) {
        this.cSimplExpPas = cSimplExpPas;
    }

    public int getcFact() {
        return cFact;
    }

    public void setcFact(int cFact) {
        this.cFact = cFact;
    }

    public int getcNot() {
        return cNot;
    }

    public void setcNot(int cNot) {
        this.cNot = cNot;
    }

    public int getcOR() {
        return cOR;
    }

    public void setcOR(int cOR) {
        this.cOR = cOR;
    }

    public int getcOPBIT() {
        return cOPBIT;
    }

    public void setcOPBIT(int cOPBIT) {
        this.cOPBIT = cOPBIT;
    }

    public int getcAND() {
        return cAND;
    }

    public void setcAND(int cAND) {
        this.cAND = cAND;
    }

    public int getcANDLOG() {
        return cANDLOG;
    }

    public void setcANDLOG(int cANDLOG) {
        this.cANDLOG = cANDLOG;
    }

    public int getcORLOG() {
        return cORLOG;
    }

    public void setcORLOG(int cORLOG) {
        this.cORLOG = cORLOG;
    }

    public int getcXORLOG() {
        return cXORLOG;
    }

    public void setcXORLOG(int cXORLOG) {
        this.cXORLOG = cXORLOG;
    }

    public int getcEST() {
        return cEST;
    }

    public void setcEST(int cEST) {
        this.cEST = cEST;
    }

    public int getcASIGN() {
        return cASIGN;
    }

    public void setcASIGN(int cASIGN) {
        this.cASIGN = cASIGN;
    }

    public int getcFunList() {
        return cFunList;
    }

    public void setcFunList(int cFunList) {
        this.cFunList = cFunList;
    }

    public int getcARR() {
        return cARR;
    }

    public void setcARR(int cARR) {
        this.cARR = cARR;
    }

    public int getcFunciones() {
        return cFunciones;
    }

    public void setcFunciones(int cFunciones) {
        this.cFunciones = cFunciones;
    }

    public int getcExpPas() {
        return cExpPas;
    }

    public void setcExpPas(int cExpPas) {
        this.cExpPas = cExpPas;
    }
    
    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

    private void contadoresNoTerminales(int estadoCelda, String aux, int columna, int linea) {
        int produccion=estadoCelda-700;
        
        switch(produccion){
            case 1:
                cProg++;
                break;
            case 6:
                cTerminoPas++;
                break;
            case 8:
                cOR++;
                break;
            case 10:
                cOPBIT++;
                break;
            case 12:
                cEleva++;
                break;
            case 14:
                cANDLOG++;
                break;
            case 16:
                cORLOG++;
                break;
            case 18:
                cXORLOG++;
                break;
            case 20:
                cSimplExpPas++;
                break;
            case 22:
                cConst++;
                break;
            case 24:
                cConstEnt++;
                break;
            case 26:
                cListTupRa++;
                break;
            case 31:
                cNot++;
                break;
            case 33:
                cFact++;
                break;
            case 40:
                cEST++;
                break;
            case 46:
                cASIGN++;
                break;
            case 48:
                cFunList++;
                break;
            case 50:
                cARR++;
                break;
            case 54:
                cExpPas++;
                break;
            case 56:
                cFunciones++;
                break;
            case 58:
                cAND++;
                break;
        }
    }
    public void metodoCorrecto(DefaultTableModel modeloReglas, JTable jTR, int token, StrikerToken valor1_1, String b, String b2){
        String nombreArr=operandosSemantica.getLast().getLexema();
        operandosSemantica.removeLast();
        operandosSemantica.add(new StrikerToken(token,tokensSintx.getFirst().getLine(),nombreArr));
//        reglasT(1130, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), b, Ambi.lastElement(), modeloReglas, jTR);
        reglasT(1050, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), b2, Ambi.lastElement(), modeloReglas, jTR);
    }
    public void metodoCorrectoF(DefaultTableModel modeloReglas, JTable jTR, StrikerToken valor1_1, String b, String b2, DefaultTableModel modeloErrores, JTable jTE){
        String nombreArr=operandosSemantica.getLast().getLexema();
        operandosSemantica.removeLast();
        operandosSemantica.add(new StrikerToken(-106,tokensSintx.getFirst().getLine(),"TV"));
//        reglasT(1130, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), b, Ambi.lastElement(), modeloReglas, jTR);
        errores.add(new KaijuError(valor1_1.getLine(),850,"Valor no encontrado","Semantica 2",valor1_1.getLexema()));                        
        modeloErrores.addRow(new Object[]{valor1_1.getLine(),850,"Valor no encontrado","Semantica 2",valor1_1.getLexema()});
        jTE.setModel(modeloErrores);
        reglasT(1050, simboloTab2(valor1_1.getToken()), valor1_1.getLexema(), valor1_1.getLine(), b2, Ambi.lastElement(), modeloReglas, jTR);
    }
    
    public void erroresT(){
        
    }
    
    public void reglasT(int r, String d, String l, int lin, String e, int am, DefaultTableModel modeloReglas, JTable jTR){
        reglas.add(new FaseCrusadia2(r,d,l,lin,e,am));                        
        modeloReglas.addRow(new Object[]{r,d,l,lin,e,am});
        jTR.setModel(modeloReglas);
    }

    private boolean valorNumerico(String valorValido) {
        try {
            Integer.parseInt(valorValido);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }
    
}
