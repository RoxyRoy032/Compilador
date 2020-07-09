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
public class StrikerToken {
    private int token;
    private int line;
    private String lexema;
    
    public StrikerToken(){  
    }
    
    public StrikerToken(int token, int line, String lexema){
        this.token=token;
        this.line=line;
        this.lexema=lexema;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
}
