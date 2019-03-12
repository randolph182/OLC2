/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.RetornoSecundario;
import FuncionScript.AST.Expresiones.Return;
import FuncionScript.AST.Instrucciones.Break;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import herramientas.ContadorBreak;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author rm
 */
public class Switch implements Instruccion{



    Expresion expresion;
    LinkedList<Caso> casos;
    int linea;
    public Switch(Expresion expresion, LinkedList<Caso> casos,int linea) {
        this.expresion = expresion;
        this.casos = casos;
        this.linea = linea;
    }
        
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno nuevoEnt = new Entorno(ent);
        Object control = expresion.getValor(nuevoEnt);
        //VALIDAR QUE TODOS LOS CASOS SEAN DEL MISMO TIPO
        for (int i = 0; i < casos.size()-1; i++) { //no tomamos en cuenta el default
            casos.get(i).expresion.getValor(nuevoEnt);
            if(expresion.getTipo(nuevoEnt).getTipoPrimitivo() != casos.get(i).expresion.getTipo(nuevoEnt).getTipoPrimitivo()){
                System.out.println("Error en switchhay un caso que no es del tipo control");
                Editor.insertarTextoConsola("Error en switch hay un caso que no es del tipo control. Linea: " + casos.get(i).linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(casos.get(i).linea, "Error en switch hay un caso que no es del tipo control");
                casos.remove(i); //quito ese caso  aca me recupero semanticamente
            }
        }
        
        ContadorBreak.getInstance().incrementarContador(); //porque es un ambito al cual puede haber un break
        int valTmpBrake = ContadorBreak.getInstance().getContador();
        boolean flag = false;
        //despues de obtener el valor necesito ir comparando para ver en que caso concuerda
        //debo tomar en cuenta que si no viene un brake sigo ejecutando todos los cases
        int i =0;
        for (i =0; i < casos.size() - 1; i++) {
            //De la lista de casos si alguno concuerda con el control del switch
            if(casos.get(i).expresion.getValor(nuevoEnt).equals(control)  || flag){
                //ejecuto su instrucciones  y todas las demas sino se encuentra detener
                flag = true;
                
                for(nodoAST nodo: casos.get(i).instrucciones){
                    if(nodo instanceof Instruccion){ //debo ejecutar esa instruccion
                        Instruccion ins = (Instruccion)nodo;
                        //verfico sino vino un breake;
                        if(ins instanceof Break){
                            ContadorBreak.getInstance().decrementarContador();
                            return null;
                        } else {
                            //::::::::::::::::::::::::: INSTRUCCION NORMAL :::::::::::::::::::
                            Object a = ins.ejecutar(ent);
                            if(a != null){
                                RetornoSecundario rs = (RetornoSecundario)a;
                                return rs;
                            } 
                        }
                    } else if (nodo instanceof Return) {
                
                        Expresion e = (Expresion)nodo;
                        Object a = e.getValor(ent);
                        if (a != null) {
                            Tipo  tipo = e.getTipo(ent);
                            RetornoSecundario rs = new RetornoSecundario(a, tipo, linea);
                            return rs; //:::::::::::::::::::::::::::::::::::::::::
                        }
                    } else if (nodo instanceof Expresion) {
                        Expresion exp = (Expresion) nodo;
                        Object b = exp.getValor(ent);
                        if(b != null){
                            RetornoSecundario rs = (RetornoSecundario)b;
                            return rs;
                        }
                    }
                                //::::::::::::::::: COMPROBACION DEL BREAKE ANIDADO
                    if(ContadorBreak.getInstance().getContador() < valTmpBrake ){
                        return null; // es porque en una instruccion se activo un breake se necesita salir
                    }
                }
            }
        }
        //SI LLEGA HASTA AQUI ES PORQUE NO VINO BREAK O ALGUNA QUE INTERRUMPA EL SWITCH
        for(nodoAST nodo: casos.get(i).instrucciones){
            if(nodo instanceof Instruccion){ //debo ejecutar esa instruccion
                Instruccion ins = (Instruccion)nodo;
                //verfico sino vino un breake;
                if(ins instanceof Break){
                    ContadorBreak.getInstance().decrementarContador();
                    return null;
                } else {
                    //::::::::::::::::::::::::: INSTRUCCION NORMAL :::::::::::::::::::
                    Object a = ins.ejecutar(ent);
                    if(a != null){
                        RetornoSecundario rs = (RetornoSecundario)a;
                        return rs;
                    } 
                }
            } else if (nodo instanceof Return) {

                Expresion e = (Expresion)nodo;
                Object a = e.getValor(ent);
                if (a != null) {
                    Tipo  tipo = e.getTipo(ent);
                    RetornoSecundario rs = new RetornoSecundario(a, tipo, linea);
                    return rs; //:::::::::::::::::::::::::::::::::::::::::
                }
            } else if (nodo instanceof Expresion) {
                Expresion exp = (Expresion) nodo;
                Object b = exp.getValor(ent);
                if(b != null){
                    RetornoSecundario rs = (RetornoSecundario)b;
                    return rs;
                }
            }
            if(ContadorBreak.getInstance().getContador() < valTmpBrake ){
                return null; // es porque en una instruccion se activo un breake se necesita salir
            }
        }
        
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
