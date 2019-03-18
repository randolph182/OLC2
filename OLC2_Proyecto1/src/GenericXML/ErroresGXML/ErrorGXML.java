/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GenericXML.ErroresGXML;

/**
 * 
 * @author randolph muy
 */
public class ErrorGXML {
    int linea;
    int columna;
    String descripcion;
    String tipo;

    public ErrorGXML(int linea, int columna, String descripcion, String tipo) {
        this.linea = linea;
        this.columna = columna;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    
    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
