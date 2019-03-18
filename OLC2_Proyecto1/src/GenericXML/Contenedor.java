/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class Contenedor implements Instruccion {

    LinkedList<Simbolo> elementos;
    LinkedList<nodoAST> hijos;
    int linea;

    public Contenedor(LinkedList<Simbolo> elementos, LinkedList<nodoAST> hijos, int linea) {
        this.elementos = elementos;
        this.hijos = hijos;
        this.linea = linea;
    }

    @Override
        /**
     * @param archivo  :  parametro que contrendra el apuntador a un archivo y por medio del cual se traduciran a codigo Funcion Script
     * @param listadoSimbolos : Parametro por el cual se pasa como apuntador un linkedList de simbolor y el cual 
     * se ira agregando cada simbolo del recorrido del arbol
     * @param ent : entorno o tabla del simbolos del analisis 
     * @param tipoEjecucion : variable que determina si es 
     * 0: es de tipo Traduccion , si es 
     * 1: es de tipo getArreglo
     */
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion)  {
        if (elementos.size() != 0) {
            String idContenedor = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.ID) {
                    if (ent.get(elemento.getValor().toString()) == null && ent.getGlobal(elemento.getValor().toString()) == null) {
                        idContenedor = elemento.getId();
                        break;
                    } else {
                        System.out.println("Error el id: " + elemento.getId() + "del contenedor  que intenta crear ya existe en linea: " + linea);
                        Editor.insertarTextoConsola("Error el id" + elemento.getId() + " del contenedor que intenta crear ya existe en linea: " + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " del contenedor que intenta crear ya existe");
                        return null;
                    }
                }
            }

            if (!idContenedor.equals("")) {
                //EMPEZAOS TRADUCCION
                try {
                    BufferedWriter bf = null;
                    LinkedList<Simbolo> elementosContenedor = null;
                    
                    if(tipoEjecucion == 0){
                        bf = new BufferedWriter(archivo);
                    }else{
                        elementosContenedor = new LinkedList<>();
                    }
                    
                    String idVentana = "";
                    //OBTENEMO EL ID DE LA VENTANA ACTUAL
                    Enumeration<Simbolo> elements = ent.getTablaSimbolos().elements();
                    while (elements.hasMoreElements()) {
                        Simbolo tmp = elements.nextElement();
                        if (tmp.getTipo().getTipoGxml() == Tipo.TipoGXML.VENTANA) { //SI HAY OTRA VENTANA QUE SEA PRINCIPAL
                            idVentana = tmp.getId().toString();
                        }
                    }

                    if (!idVentana.equals("")) {
                        if(tipoEjecucion == 0){
                            bf.write("var " + idContenedor + " = " + idVentana + ".crearContenedor(");
                        }
                        
                        
                        Simbolo contenedor = new Simbolo(idContenedor, new Tipo(Tipo.TipoGXML.CONTENEDOR));
                        contenedor.setValor(idVentana);
                        ent.put(idContenedor, contenedor);
                        ent.putGlobal(idContenedor, contenedor);

                        String x = "0";
                        String y = "0";
                        String alto = "10";
                        String ancho = "10";
                        String borde = "falso";
                        String color = "#C4CDC2";

                        for (Simbolo elemento : elementos) {

                            switch (elemento.getRolGxml()) {
                                case X:
                                    x = elemento.getValor().toString();
                                    if(tipoEjecucion ==1){
                                        Simbolo s = new Simbolo();
                                        s.setValor(x);
                                        s.setRolGxml(Simbolo.ROLGXML.X);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosContenedor.add(s);
                                    }
                                    break;
                                case Y:
                                    y = elemento.getValor().toString();
                                    if(tipoEjecucion ==1){
                                        Simbolo s = new Simbolo();
                                        s.setValor(y);
                                        s.setRolGxml(Simbolo.ROLGXML.Y);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosContenedor.add(s);
                                    }
                                    break;
                                case ALTO:
                                    alto = elemento.getValor().toString();
                                    if(tipoEjecucion ==1){
                                       Simbolo s = new Simbolo();
                                        s.setValor(alto);
                                        s.setRolGxml(Simbolo.ROLGXML.ALTO);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER)); 
                                        elementosContenedor.add(s);
                                    }
                                    break;

                                case ANCHO:
                                    ancho = elemento.getValor().toString();
                                    if(tipoEjecucion ==1){
                                        Simbolo s = new Simbolo();
                                        s.setValor(ancho);
                                        s.setRolGxml(Simbolo.ROLGXML.ANCHO);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosContenedor.add(s);
                                    }
                                    break;
                                case BORDE:
                                    borde = elemento.getValor().toString();
                                    if(tipoEjecucion ==1){
                                        Simbolo s = new Simbolo();
                                        s.setValor(borde);
                                        s.setRolGxml(Simbolo.ROLGXML.BORDE);
                                        s.setTipo(new Tipo(Tipo.Primitivo.BOOLEAN));
                                        elementosContenedor.add(s);
                                    }
                                    break;
                            }
                        }
                        
                        if(!x.equals("0") && !y.equals("0")){
                            if(tipoEjecucion == 0){
                                bf.write( alto +","+ancho+",\""+color+"\","+borde+","+x+","+y+");\n");
                                bf.flush();
                            }else{
                                Simbolo s = new Simbolo(idContenedor, new Tipo(Tipo.TipoGXML.CONTENEDOR));
                                s.setValor(idVentana); //SERVIRA COMO REFERENCIA PARA MAS ADELANTE
                                s.setElementos(elementosContenedor);
                                listadoSimbolos.add(s);
                            }
                            
                        } else{
                            System.out.println("hacen falta X y Y del contenedor = " + idContenedor + " en linea:" + linea);
                            Editor.insertarTextoConsola("hacen falta X y Y del contenedor= " + idContenedor + " en linea:" + linea);
                            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "hacen falta X y Y del contenedor = " + idContenedor);
                            return null;
                        }
                    } else {
                        System.out.println("No se encontro la ventana actual del contenedor = " + idContenedor + " en linea:" + linea);
                        Editor.insertarTextoConsola("No se encontro la ventana actual del contenedor = " + idContenedor + " en linea:" + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "No se encontro la ventana actual del contenedor = " + idContenedor);
                        return null;
                    }

                    
                    
                } catch (Exception e) {
                    System.out.println("algo salio mal en la escritura del archivo en una ventana linea:" + linea);
                    Editor.insertarTextoConsola("algo salio mal en la escritura del archivo en una ventana linea:" + linea);
                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal en la escritura del archivo en una ventana");
                    return null;
                }

            } else {
                System.out.println("Error hubo problemas con id del contenedor en linea: " + linea);
                Editor.insertarTextoConsola("Error hubo problemas con id del contenedor en linea: " + linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error hubo problemas con id del contenedor");
                return null;
            }
        } else {
            System.out.println("Error la etiqueta contenedor debe tener porlomenos 3 elementos que son id y tipo en linea: " + linea);
            Editor.insertarTextoConsola("Error la etiqueta contenedor debe tener porlomenos 3 elementos que son id y tipo en linea: " + linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error la contenedor ventana debe tener porlomenos 3 elementos que son id y tipo");
            return null;
        }
        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
