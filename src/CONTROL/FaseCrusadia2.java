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
public class FaseCrusadia2 {
    private int regla;
    private String topePil;
    private String valorReal;
    private int linea;
    private String estado;
    private int ambito;

    public FaseCrusadia2(int regla, String topePil, String valorReal, int linea, String estado, int ambito) {
        this.regla = regla;
        this.topePil = topePil;
        this.valorReal = valorReal;
        this.linea = linea;
        this.estado = estado;
        this.ambito = ambito;
    }

    public int getRegla() {
        return regla;
    }

    public void setRegla(int regla) {
        this.regla = regla;
    }

    public String getTopePil() {
        return topePil;
    }

    public void setTopePil(String topePil) {
        this.topePil = topePil;
    }

    public String getValorReal() {
        return valorReal;
    }

    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }
    
    
}
