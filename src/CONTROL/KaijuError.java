/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

/**
 *
 * @author alber
 */
public class KaijuError {
    private int line;
    private int error;
    private String descripcion;
    private String Tipo;
    private String lexema;
    
    public KaijuError(){  
    }
    
    public KaijuError(int line, int error, String descripcion, String Tipo, String lexema){
        this.line=line;
        this.error=error;
        this.descripcion=descripcion;
        this.Tipo=Tipo;
        this.lexema=lexema;
    }
    
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
}
