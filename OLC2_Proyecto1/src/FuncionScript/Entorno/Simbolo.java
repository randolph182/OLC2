/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.Entorno;

import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.AST.nodoAST;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;


    
    private LinkedList<Simbolo> elementos;
    private LinkedList<nodoAST> instrucciones;
    private LinkedList<Identificador> ids;
    private int  linea;
    
    private ROL rol;


    private ROLGXML rolGxml;
    public Simbolo(){
    }
    
    public Simbolo(String id,Object valor,Tipo tipo){
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public Simbolo(String id,Object valor,Tipo tipo,ROLGXML rol){
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.rolGxml = rol;
    }
    
    
    public Simbolo(LinkedList<Identificador> ids, int linea){
        this.ids = ids;
        this.linea = linea;
    }
    
    public Simbolo(String id,Tipo tipo){
        this.id = id;
        this.tipo = tipo;
    }
    
    public Simbolo(String id,LinkedList<Simbolo> parametros, LinkedList<nodoAST> instrucciones, int linea) {
        this.id = id;
        this.elementos = parametros;
        this.instrucciones = instrucciones;
        this.linea = linea;
    }
    
    public Simbolo(String id,LinkedList<Simbolo> elementos, int linea) {
        this.id = id;
        this.elementos = elementos;
        this.linea = linea;
    }
    
    
    
    public ROL getRol() {
        return rol;
    }

    public void setRol(ROL rol) {
        this.rol = rol;
    }
    
    public LinkedList<Identificador> getLstIds() {
        return ids;
    }

    public void setLstIds(LinkedList<Identificador> ids) {
        this.ids = ids;
    }
    
    
    public LinkedList<Simbolo> getElementos() {
    return elementos;
    }

    public void setElementos(LinkedList<Simbolo> parametros) {
        this.elementos = parametros;
    }

    public LinkedList<nodoAST> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<nodoAST> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    
    public String getId(){
        return id;
    }
    
    public Object getValor(){
        return valor;
    }
    
    public void setValor(Object valor){
        this.valor = valor;
    }
    
    public Tipo getTipo(){
        return tipo;
    }
    
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
        public ROLGXML getRolGxml() {
        return rolGxml;
    }

    public void setRolGxml(ROLGXML rolGxml) {
        this.rolGxml = rolGxml;
    }
    
    
    
    public enum ROLGXML{
        ID,
        TIPO,
        COLOR,
        ACCION_INICIAL,
        ACCION_FINAL
        
    }
    
    public enum ROL{
        FUNCION,
        VARIABLE,
        ARREGLO_HOMOGENEO,
        ARREGLO_HETEROGENEO,
        VENTANA
        
    }
    
}
