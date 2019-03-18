/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.util.Comparator;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class ArregloLlamada implements Expresion {

    String id;
    Expresion exp;
    int linea;
    Tipo tipo = new Tipo(Tipo.Primitivo.NULL);
    /**
     * si la llamada es 0 : entonces es una llamada a posicion del arreglo si la
     * llamada es 1 : entonces es una llamanda a descendente del arreglo
     */
    int tipoLlamada = 0;

    public ArregloLlamada(String id, Expresion exp, int linea) {
        this.id = id;
        this.exp = exp;
        this.linea = linea;
        tipoLlamada = 0;
    }

    public ArregloLlamada(String id, int tipoLlamada, int linea) {
        this.id = id;
        this.tipoLlamada = tipoLlamada;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        if(ent.get(id).getElementos().size() == 0){
            System.out.println("El valor con  el que se pretende accesar al arreglo vino nulo o el arreglo no tiene elementos en linea" + linea);
            Editor.insertarTextoConsola("El valor con  el que se pretende accesar al arreglo vino nulo o el arreglo no tiene elementos en linea" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El valor con  el que se pretende accesar al arreglo vino nulo o el arreglo no tiene elementos");
            return null;
        }
        
        //Calculando primero la Expresion
        switch (tipoLlamada) {
            case 0: //LLAMADA A UNA POSICION DEL ARREGLO

                Object valElemen = exp.getValor(ent);
                if (valElemen != null ) //si posicion a accesar no es nula
                {
                    if (exp.getTipo(ent).isNumeric()) { //si es numrica
                        //AHORA LLAMAMOS AL ARREGLO QUE ESTA EN LA TABLA DE SIMBOLOS
                        if (ent.get(id) != null) { //si existe el arreglo
                            //SI EXISTE ENTONCES SACAMOS SU LISTA DE SIMBOLOS PARA OBTENER LA POSICION SOLICITADA
                            LinkedList<Simbolo> valores = ent.get(id).getElementos();
                            Double d = (Double) valElemen;
                            int pos = d.intValue();
                            if (pos >= valores.size() || pos < 0) {
                                System.out.println("La posicion que desa acceder del arreglo es erronea en linea: " + linea);
                                Editor.insertarTextoConsola("La posicion que desa acceder del arreglo es erronea en linea: " + linea);
                                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "La posicion que desa acceder del arreglo es erronea");
                                return null;
                            }
                            if(valores.get(pos).getValor() != null){
                                tipo = valores.get(pos).getTipo();
                                return valores.get(pos).getValor();
                            }else{
                                System.out.println("Usted accedio a una variable de tipo GXML e  linea" + linea);
                                Editor.insertarTextoConsola("Usted accedio a una variable de tipo GXML  en linea" + linea);
                                return valores.get(pos); //retornamos el simbolo puede tener cuarquier rol ej ventana
                            }
                        }

                    } else {
                        System.out.println("Error las posiciones de los arreglos sole se accesan con numericos en linea" + linea);
                        Editor.insertarTextoConsola("Error las posiciones de los arreglos sole se accesan con numericos en linea" + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error las posiciones de los arreglos sole se accesan con numericos");
                        return null;
                    }
                } 
            break;

            case 1: //LLAMADA A LA FUNCION DESCENDENTE DEL ARREGLO
            mostrarValoresDescendente(ent.get(id).getElementos(), ent.get(id));
            break;

            case 2: //LLAMADA A LA FUNCION  ASCENDENTE DEL ARREGLO
                //verifico primero si el arreglo tiene elementos
                mostrarValoresAscendente(ent.get(id).getElementos(),ent.get(id));
            break;
                
            case 3: //LLAMADA A LA FUNCION  INVERTIR DEL ARREGLO
                mostrarValoresInvertidos(ent.get(id).getElementos());
            break;
             
            case 4: //LLAMADA A LA FUNCION  Maximo DEL ARREGLO
              return   mostrarValorMaximo(ent.get(id).getElementos(),ent.get(id));
                
            case 5: //LLAMADA A LA FUNCION  MINIMO DEL ARREGLO
                return mostrarValorMinimo(ent.get(id).getElementos(),ent.get(id));

        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
// ######################################################## METODO DESCENDENTE ###################################################
    public void mostrarValoresDescendente(LinkedList<Simbolo> elemntos, Simbolo arreglo) {

        if (arreglo.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO) { //si los componentes de los arregos son todos del mismo tipo
            Tipo tipoOrden  = new Tipo(Tipo.Primitivo.NULL);
            tipoOrden =  ((Simbolo)elemntos.get(0)).getTipo();
            switch(tipoOrden.getTipoPrimitivo()){
                case NUMBER:
                    LinkedList<Double> val = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val.add( new Double(s.getValor().toString()));
                    }

                    double tmp = 0;
                    //ordenamiento burbuja
                    for (int i = 1; i < val.size(); i++) {
                        for (int j = 0; j < val.size() - 1; j++) {
                            if (val.get(j)> val.get(j + 1)) { // cadena 1 es mayor que la cadena 2
                                tmp = val.get(j);
                                val.set(j, val.get(j + 1));
                                val.set(j + 1, tmp);
                            }
                        }
                    }

                    for (Double result : val) {
                        Editor.insertarTextoConsola(result.toString());
                    }
                break;
                    
                case STRING:
                    LinkedList<String> val2 = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val2.add(s.getValor().toString());
                    }

                    String tmp2 = "";
                    //ordenamiento burbuja
                    for (int i = 1; i < val2.size(); i++) {
                        for (int j = 0; j < val2.size() - 1; j++) {
                            if (val2.get(j).compareTo(val2.get(j + 1)) > 0) { // cadena 1 es mayor que la cadena 2
                                tmp2 = val2.get(j);
                                val2.set(j, val2.get(j+1));
                                val2.set(j + 1, tmp2);
                            }
                        }
                    }
                    for (String str : val2) {
                        Editor.insertarTextoConsola(str);
                    }
                break;
            }
            
            
        } else {
            System.out.println("El arreglo no puede hacer la funcion descendente porque no es homogenea en linea " + linea);
            Editor.insertarTextoConsola("El arreglo no puede hacer la funcion descendente porque no es homogenea en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo no puede hacer la funcion descendente porque no es homogenea");
        }

    }
// ######################################################## METODO ASCENDNETE ###################################################
    public void mostrarValoresAscendente(LinkedList<Simbolo> elemntos, Simbolo arreglo) {

        if (arreglo.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO) { //si los componentes de los arregos son todos del mismo tipo
            Tipo tipoOrden  = new Tipo(Tipo.Primitivo.NULL);
            tipoOrden =  ((Simbolo)elemntos.get(0)).getTipo();
            switch(tipoOrden.getTipoPrimitivo()){
                case NUMBER:
                    LinkedList<Double> val = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val.add( new Double(s.getValor().toString()));
                    }

                    double tmp = 0;
                    //ordenamiento burbuja
                    for (int i = 1; i < val.size(); i++) {
                        for (int j = 0; j < val.size() - 1; j++) {
                            if (val.get(j)< val.get(j + 1)) { // cadena 1 es menor que la cadena 2
                                tmp = val.get(j);
                                val.set(j, val.get(j + 1));
                                val.set(j + 1, tmp);
                            }
                        }
                    }

                    for (Double result : val) {
                        Editor.insertarTextoConsola(result.toString());
                    }
                break;
                    
                case STRING:
                    LinkedList<String> val2 = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val2.add(s.getValor().toString());
                    }

                    String tmp2 = "";
                    //ordenamiento burbuja
                    for (int i = 1; i < val2.size(); i++) {
                        for (int j = 0; j < val2.size() - 1; j++) {
                            if (val2.get(j).compareTo(val2.get(j + 1)) < 0) { // cadena 1 es menor que la cadena 2
                                tmp2 = val2.get(j);
                                val2.set(j, val2.get(j+1));
                                val2.set(j + 1, tmp2);
                            }
                        }
                    }
                    for (String str : val2) {
                        Editor.insertarTextoConsola(str);
                    }
                break;
            }
        } else {
            System.out.println("El arreglo no puede hacer la funcion ascendente porque no es homogenea en linea " + linea);
            Editor.insertarTextoConsola("El arreglo no puede hacer la funcion ascendente porque no es homogenea en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo no puede hacer la funcion ascendente porque no es homogenea");
        }

    }
    
    // ######################################################## METODO INVERTIR ###################################################
    public void mostrarValoresInvertidos(LinkedList<Simbolo> elemntos) {
        LinkedList<String> val2 = new LinkedList<>();
        for (Simbolo s : elemntos) {
            val2.add(s.getValor().toString());
        }

        String tmp2 = "";
        //ordenamiento burbuja
        for (int i = val2.size() - 1; i >= 0; i--) {
             Editor.insertarTextoConsola(val2.get(i));
        }
    }
    
    // ######################################################## METODO MAXIMO ###################################################
    public Object mostrarValorMaximo(LinkedList<Simbolo> elemntos,Simbolo arreglo) {

        if (arreglo.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO) { //si los componentes de los arregos son todos del mismo tipo
            Tipo tipoOrden  = new Tipo(Tipo.Primitivo.NULL);
            tipoOrden =  ((Simbolo)elemntos.get(0)).getTipo();
            switch(tipoOrden.getTipoPrimitivo()){
                case NUMBER:
                    LinkedList<Double> val = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val.add( new Double(s.getValor().toString()));
                    }

                    Double maximo =  new Double(0);
                    //ordenamiento burbuja
                    for (int i = 0; i < val.size(); i++) {
                        if(val.get(i) > maximo)
                            maximo = val.get(i);
                    }
//                   Editor.insertarTextoConsola(maximo.toString());
            tipo =  new Tipo(Tipo.Primitivo.NUMBER);        
            return maximo;
                   
                case STRING:
                    LinkedList<String> val2 = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val2.add(s.getValor().toString());
                    }

                    String tmp2 = "";
                    //ordenamiento burbuja
                    for (int i = 0 ; i < val2.size(); i++) {
                        if (val2.get(0).compareTo(tmp2) > 0) { 
                            tmp2 = val2.get(i);
                        }
                    }
                    tipo =  new Tipo(Tipo.Primitivo.STRING);        
                return tmp2;
            }
        } else {
            System.out.println("El arreglo no puede hacer la funcion maximo porque no es homogenea en linea " + linea);
            Editor.insertarTextoConsola("El arreglo no puede hacer la funcion maximo porque no es homogenea en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo no puede hacer la funcion maximo porque no es homogenea");
        }
        return null;
    }
    
    // ######################################################## METODO MINIMO ###################################################
    public Object mostrarValorMinimo(LinkedList<Simbolo> elemntos,Simbolo arreglo) {

        if (arreglo.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO) { //si los componentes de los arregos son todos del mismo tipo
            Tipo tipoOrden  = new Tipo(Tipo.Primitivo.NULL);
            tipoOrden =  ((Simbolo)elemntos.get(0)).getTipo();
            switch(tipoOrden.getTipoPrimitivo()){
                case NUMBER:
                    LinkedList<Double> val = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val.add( new Double(s.getValor().toString()));
                    }

                    Double minimo = val.get(0);
                    //ordenamiento burbuja
                    for (int i = 0; i < val.size(); i++) {
                        if(val.get(i) < minimo)
                            minimo = val.get(i);
                    }
                tipo =  new Tipo(Tipo.Primitivo.NUMBER);        
                return minimo;
                    
                case STRING:
                    LinkedList<String> val2 = new LinkedList<>();
                    for (Simbolo s : elemntos) {
                        val2.add(s.getValor().toString());
                    }

                    String tmp2 = "";
                    //ordenamiento burbuja
                    for (int i = 0 ; i < val2.size(); i++) {
                        if (val2.get(0).compareTo(tmp2) < 0) { 
                            tmp2 = val2.get(i);
                        }
                    }
                    tipo =  new Tipo(Tipo.Primitivo.STRING);        
                return tmp2;
            }
        } else {
            System.out.println("El arreglo no puede hacer la funcion minimo porque no es homogenea en linea " + linea);
            Editor.insertarTextoConsola("El arreglo no puede hacer la funcion minimo porque no es homogenea en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo no puede hacer la funcion minimo porque no es homogenea");
        }
        return null;
    }
}
