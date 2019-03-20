// jar en la pagina -> http://www2.cs.tum.edu/projects/cup/
package analizadores.FS;
import java_cup.runtime.*;
import java.util.LinkedList;

// ************** importaciones de las clases que se utilizaran en el analisis *********
import FuncionScript.AST.Expresiones.*;
import FuncionScript.AST.Expresiones.Operacion.*;
import FuncionScript.Entorno.*;
import FuncionScript.AST.Instrucciones.*;
//import FuncionScript.AST.Instrucciones.InterfazUsuario.*;
import FuncionScript.AST.*;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import FuncionScript.AST.Instrucciones.Condicionales.*;

import FuncionScript.AST.Expresiones.InterfazUsuario.*;
import FuncionScript.AST.Instrucciones.InterfazUsuario.*;
//acciones que se programan en el parser

parser code
{:
    public AST ast;
    /**Metodo al que se llama automáticamente ante algún error sintactico.*/
    public void syntax_error(Symbol s){
        System.out.println("Error sintactico en la Línea " + s.left +" Columna "+(s.right+1)+ ". Identificador "
        +s.value + " no reconocido." );
         ManejadorErroresFS.getInstance().setErrorSintactico(s.left,s.right+1, "problemas con el caracter = " + s.value);
    }
         /**Metodo al que se llama en el momento en que ya no es posible una recuperación de
    errores.*/
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{
        System.out.println("Error sintactico en la Línea " + (s.right+1)+ " Columna "+s.left+". Identificador " +
        s.value + " no reconocido.");   
	}
    

:}

terminal String tImprimir;
terminal String tTrue;
terminal String tFalse;
terminal String tPtoComa;
terminal String tMas;
terminal String tMenos;
terminal String tDiv;
terminal String tPor;
terminal String tElev;
terminal String tIgual;
terminal String tParOpen;
terminal String tParClose;
terminal String tEntero;
terminal String tCad;
terminal String tDec;
terminal String tId;
terminal String tAumen;
terminal String tDecremen;
terminal String unarioNeg;
terminal String tVar;
terminal String tComa;
terminal String umenos;
terminal Strng tMayorQue,tMenorQue,tMayorIgual,tMenorIgual,tIgualDoble,tDistinto;
terminal String tNulo;
terminal String tSumaAsig,tRestaAsig,tMultipliAsig,tDiviAsig;
terminal String tSi,tSino,tInterrogacion,tDosPuntos;
terminal String tLlvOpen,tLlvClose,tCaso,tDefecto,tSelecciona,tBreak;
terminal String tAnd,tOr,tNot;
terminal String tReturn,tFuncion,tCorchOpen,tCorchClose,tPunto,res_Descendente,res_Ascendente;
terminal String res_Invertir,res_maximo,res_minimo;
terminal String res_crear_ventana,res_crear_contenedor,res_crear_texto;
terminal String res_leerGxml,res_obtenerPorEtiqueta,res_crear_caja_texto,res_crear_boton;
terminal String res_alClick;

non terminal S;
non terminal Instruccion DECLARACION;
non terminal LinkedList<Identificador> LST_ID;
non terminal Expresion EXPRESION;
non terminal nodoAST INSTRUCCION;
non terminal LinkedList<nodoAST> INSTRUCCIONES;
non terminal Instruccion ASIG_VAL;
non terminal Imprimir IMPRIMIR;
non terminal Aumento AUMENTO;
non terminal Decremento DECREMENTO;
non terminal Asignacion ASIGNACION;
non terminal Asignacion_Operacion ASIG_OP;
non terminal Asignacion_Operacion.TipoAO TIPO_AO;
non terminal  If IF;
non terminal Switch SWITCH;
non terminal LinkedList<Caso> LST_CASOS;
non terminal Caso CASO;
non terminal Break BREAK;
non terminal Return RETURN;
non terminal Funcion FUNCION;
non terminal LinkedList<Simbolo> PARAMETROS;
non terminal LinkedList<Simbolo> TIPO_PARAMETROS_FUNCION;
non terminal Simbolo PARAMETRO;
non terminal FuncionLLamada FUNCION_LLAMADA;
non terminal LinkedList<Expresion> PARAMETROS_CALL;
non terminal LinkedList<Expresion> TIPO_PARAMETROS;
non terminal Object TIPO_DEC_ASIG;
non terminal LinkedList<Expresion> TIPO_PARAM_ARREGLO;
non terminal ArregloLlamada ARREGLO_LLAMADA;
non terminal AsignacionArreglo ASIGNACION_ARREGLO;
non terminal CrearVentana CREAR_VENTANA;
non terminal CrearContenedor CREAR_CONTENEDOR;
non terminal CrearTexto CREAR_TEXTO;
non terminal LeerGxml LEER_GXML;
non terminal ObtenerPorEtiqueta OBTENER_POR_ETIQUETA;
non terminal ObjetoLlamada LLAMADA_OBJETO;
non terminal CrearCajaTexto CREAR_CAJA_TEXTO;
non terminal CrearBoton CREAR_BOTON;
non terminal AlClic POSIBLES_LLAMADAS;

non terminal  nodoAST LLAMADA_ALCLIC;

precedence right tIgual;

//ternario
precedence left  tInterrogacion,tDosPuntos;

//operadores logico
precedence left tOr;
precedence left tAnd;
//Operaciones relacionales
precedence left  tIgualDoble,tDistinto;
//Operaciones relacionales
precedence left  tMenorQue,tMenorIgual,tMayorQue,tMayorIgual;
//Operaciones Artimeticas
precedence left  tMas,tMenos;
precedence left  tPor,tDiv;
precedence right  tElev;
precedence left  tAumen,tDecremen;
//op logico
precedence right tNot;
precedence right  umenos;

/* ******************************************* GRAMATICA **************************************** */
start with S;

S::=	INSTRUCCIONES:a  {: parser.ast = new AST(a);:};

INSTRUCCIONES::= INSTRUCCIONES:a INSTRUCCION:b    {:RESULT = a; RESULT.add(b);:}
                |INSTRUCCION:a         {:RESULT = new LinkedList<>(); RESULT.add(a);:}
                ;

INSTRUCCION::=  DECLARACION:a                      {:RESULT = a;:}
                | AUMENTO:a                         {:RESULT = a;:}
                | DECREMENTO:a                      {:RESULT = a;:}
                | IMPRIMIR:a                        {: RESULT = a;:}
                | ASIGNACION:a                      {: RESULT = a;:}
                | ASIG_OP:a                         {: RESULT = a;:}
                | IF:a                              {: RESULT = a;:}
                |SWITCH:a                           {: RESULT = a;:}
                |BREAK:a                            {: RESULT = a;:}
                |RETURN:a                           {: RESULT = a;:}
                |FUNCION:a                          {: RESULT = a;:}
                |FUNCION_LLAMADA:a tPtoComa         {: RESULT = a;:}
                |ARREGLO_LLAMADA:a tPtoComa               {: RESULT = a;:}
                |ASIGNACION_ARREGLO:a               {: RESULT = a;:}
                |CREAR_TEXTO:a {: RESULT = a;:}
                |CREAR_CAJA_TEXTO:a {: RESULT = a;:}
                |AL_CLIC:a {: RESULT = a;:}
                | error tPtoComa
                |error tLlvClose
                ;

POSIBLES_LLAMADAS::= DECLARACION
                    |

ARREGLO_LLAMADA::= tId:id tCorchOpen EXPRESION:exp tCorchClose {:RESULT = new ArregloLlamada(id,exp,idleft);:}
                    |tId:id tPunto res_Descendente tParOpen tParClose{: RESULT = new ArregloLlamada(id,1,idleft); :}
                    |tId:id tPunto res_Ascendente tParOpen tParClose{: RESULT = new ArregloLlamada(id,2,idleft); :}
                    |tId:id tPunto res_Invertir tParOpen tParClose{: RESULT = new ArregloLlamada(id,3,idleft); :}
                    |tId:id tPunto res_maximo tParOpen tParClose{: RESULT = new ArregloLlamada(id,4,idleft); :}
                    |tId:id tPunto res_minimo tParOpen tParClose{: RESULT = new ArregloLlamada(id,5,idleft); :}
                    ;

FUNCION_LLAMADA::=  tId:id tParOpen TIPO_PARAMETROS:pars tParClose {:RESULT = new FuncionLLamada(pars,id,idleft);:}
                    ;

TIPO_PARAMETROS::= PARAMETROS_CALL:a {:RESULT = a; :}
                    |/* EMPTY*/    {: RESULT = new LinkedList<>(); :}
                    ;


PARAMETROS_CALL::=  PARAMETROS_CALL:a tComa EXPRESION:b {: RESULT = a; RESULT.add(b);:}
                    | EXPRESION:a                     {:RESULT = new LinkedList<>(); RESULT.add(a); :}
                    ;


RETURN::=       tReturn:a EXPRESION:b tPtoComa  {: RESULT = new Return(b,aleft);:}
                | tReturn:a  tPtoComa   {:RESULT = new Return(aleft);:}
                ;


FUNCION::=      tFuncion tId:id tParOpen TIPO_PARAMETROS_FUNCION:par tParClose tLlvOpen INSTRUCCIONES:ins tLlvClose
                {:RESULT = new Funcion(id,par,ins,idleft);:}
                ;

TIPO_PARAMETROS_FUNCION::=  PARAMETROS:a {: RESULT = a; :}
                            |/*empty*/   {: RESULT = new LinkedList<>();:}
                            ;

PARAMETROS::=   PARAMETROS:pars tComa PARAMETRO:par {: RESULT = pars; RESULT.add(par);:}
                |PARAMETRO:par                 {:RESULT = new LinkedList<>(); RESULT.add(par);:}
                ;


PARAMETRO::=    tVar tId:id {: RESULT = new Simbolo(id,new Tipo(Tipo.Primitivo.NULL));:}
                ;

BREAK::=        tBreak tPtoComa      {: RESULT =  new Break(); :}
                ;

SWITCH::=       tSelecciona tParOpen EXPRESION:control tParClose tLlvOpen LST_CASOS:casos tLlvClose 
                    {: RESULT = new Switch(control,casos,controlleft);:}
                ;

LST_CASOS::=    LST_CASOS:lc CASO:c     {:RESULT = lc; RESULT.add(c);:}
                |CASO:c                 {:RESULT = new LinkedList<>(); RESULT.add(c);:}
                ;

CASO::=         tCaso EXPRESION:exp tDosPuntos tLlvOpen INSTRUCCIONES:ins tLlvClose {: RESULT = new Caso(exp,ins,expleft);:}
                |tDefecto tDosPuntos tLlvOpen INSTRUCCIONES:ins tLlvClose       {: RESULT = new Caso(ins,insleft);:}
                ;



IF::=           tSi tParOpen EXPRESION:cond tParClose tLlvOpen INSTRUCCIONES:ins tLlvClose
                    {: RESULT = new If(cond,ins,condleft);:}
                | tSi tParOpen EXPRESION:cond tParClose tLlvOpen INSTRUCCIONES:ins1 tLlvClose tSino tLlvOpen INSTRUCCIONES:ins2 tLlvClose
                    {: RESULT = new If(cond,ins1,ins2,condleft); :}
                | tSi tParOpen EXPRESION:cond tParClose tLlvOpen INSTRUCCIONES:ins tLlvClose tSino IF:tIf 
                    {: RESULT = new If(cond,ins,tIf,condleft); :}
                ; 

ASIG_OP::=      tId:a TIPO_AO:b EXPRESION:c tPtoComa {: RESULT = new Asignacion_Operacion(a,b,c,aleft);:}
                ;

TIPO_AO::=      tSumaAsig               {:RESULT = Asignacion_Operacion.TipoAO.SUMA;:}
                |tRestaAsig             {:RESULT = Asignacion_Operacion.TipoAO.RESTA;:}
                |tMultipliAsig          {:RESULT = Asignacion_Operacion.TipoAO.MULTIPLICACION;:}
                |tDiviAsig              {:RESULT = Asignacion_Operacion.TipoAO.DIVISION;:}
                ;               

ASIGNACION::=  tId:a tIgual EXPRESION:b tPtoComa    {:RESULT = new Asignacion(a,b,aleft);:}
                ;

ASIGNACION_ARREGLO::= tId:a tCorchOpen EXPRESION:b tCorchClose tIgual EXPRESION:c tPtoComa {:RESULT = new AsignacionArreglo(a,b,c,aleft);:}
                        ;                

AUMENTO::=      tId:a tAumen tPtoComa              {: RESULT = new Aumento(a,aleft);:}
                ;

DECREMENTO::=   tId:a tDecremen tPtoComa           {: RESULT = new Decremento(a,aleft);:}
                ;

IMPRIMIR::=     tImprimir tParOpen EXPRESION:a tParClose tPtoComa {:RESULT = new Imprimir(a,aleft);:}
                ;                

DECLARACION::=   tVar  ASIG_VAL:a tPtoComa          {:RESULT = a;:}
                ;

ASIG_VAL::=     tId:a                             {: Identificador id = new Identificador(a,aleft);
                                                        LinkedList<Identificador> lId = new LinkedList<>();
                                                        lId.add(id);
                                                        RESULT = new Declaracion(lId,aleft);
                                                        
                                                    :}
                // |tId:a   tIgual tCorchOpen PARAMETROS_CALL:b tCorchClose    {:RESULT = new Arreglo(a,b,aleft);:} //arreglos                                                                   
                | LST_ID:a tIgual TIPO_DEC_ASIG:b   {:
                                                        if(b instanceof Expresion){
                                                            RESULT = new Declaracion(a,(Expresion)b,bleft);
                                                        }else{
                                                            RESULT = new Arreglo(a,(LinkedList<Expresion>)b,aleft);
                                                        }
                                                    :}
                ;

TIPO_DEC_ASIG::= EXPRESION:a                                {:RESULT = a;:}
                | tCorchOpen TIPO_PARAM_ARREGLO:a tCorchClose  {:RESULT = a;:} /*Arreglo*/
                ;

TIPO_PARAM_ARREGLO::= PARAMETROS_CALL:a {:RESULT = a; :}
                | /* empty*/            {: RESULT = new LinkedList<>();:}
                ;

LST_ID::=       LST_ID:a tComa tId:b                {:RESULT = a; Identificador id = new Identificador(b,bleft); RESULT.add(id);:}
                |tId:a                              {:  RESULT = new LinkedList<>();
                                                        Identificador id = new Identificador(a,aleft);
                                                        RESULT.add(id);
                                                    :}
                ;

EXPRESION::=     tMenos EXPRESION:a                 {:RESULT = new Aritmetica(a,Operacion.Operador.NEGACION,aleft);:} %prec umenos
                |EXPRESION:a tMas EXPRESION:b       {:RESULT = new Aritmetica(a,b,Operacion.Operador.SUMA,aleft);:}
                |EXPRESION:a tMenos EXPRESION:b     {:RESULT = new Aritmetica(a,b,Operacion.Operador.RESTA,aleft);:}
                |EXPRESION:a tPor EXPRESION:b       {:RESULT = new Aritmetica(a,b,Operacion.Operador.MULTIPLICACION,aleft);:}
                |EXPRESION:a tDiv EXPRESION:b       {:RESULT = new Aritmetica(a,b,Operacion.Operador.DIVISION,aleft);:}
                |EXPRESION:a tElev EXPRESION:b      {:RESULT = new Aritmetica(a,b,Operacion.Operador.ELEVACION,aleft);:}

                |EXPRESION:a tIgualDoble EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.IGUAL_QUE,aleft);:}
                |EXPRESION:a tDistinto EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.DIFERENTE_QUE,aleft);:}
                |EXPRESION:a tMenorQue EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.MENOR_QUE,aleft);:}
                |EXPRESION:a tMenorIgual EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.MENOR_IGUAL_QUE,aleft);:}
                |EXPRESION:a tMayorQue EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.MAYOR_QUE,aleft);:}
                |EXPRESION:a tMayorIgual EXPRESION:b      {:RESULT = new Relacional(a,b,Operacion.Operador.MAYOR_IGUAL_QUE,aleft);:}

                |EXPRESION:a tAnd EXPRESION:b             {:RESULT = new Logica(a,b,Operacion.Operador.AND,aleft);:}
                |EXPRESION:a tOr EXPRESION:b             {:RESULT = new Logica(a,b,Operacion.Operador.OR,aleft);:}
                |tNot EXPRESION:a                        {:RESULT = new Logica(a,Operacion.Operador.NOT,aleft);:}

                |EXPRESION:cond tInterrogacion EXPRESION:t tDosPuntos EXPRESION:f {: RESULT = new Ternario(cond,t,f,condleft);:}
                |tParOpen EXPRESION:a tParClose     {:RESULT = a;:}
                |tEntero:a                          {:RESULT = new Unario(new Double(a),new Tipo(Tipo.Primitivo.NUMBER),aleft);:}
                |tDec:a                             {:RESULT = new Unario(new Double(a),new Tipo(Tipo.Primitivo.NUMBER),aleft);:}
                |tCad:a                             {:RESULT = new Unario(a,new Tipo(Tipo.Primitivo.STRING),aleft);:}
                |tId:a                              {:RESULT = new Identificador(a,aleft);:}
                |tId:a tAumen                       {:  Identificador id = new Identificador(a,aleft);
                                                        RESULT = new Aritmetica(id,Operacion.Operador.AUMENTO,aleft);
                                                    :}
                |tId:a tDecremen                    {:  Identificador id = new Identificador(a,aleft);
                                                        RESULT = new Aritmetica(id,Operacion.Operador.DECREMENTO,aleft); 
                                                    :}
                |tTrue:a                            {:RESULT = new Unario(a,new Tipo(Tipo.Primitivo.BOOLEAN),aleft);:}
                |tFalse:a                           {:RESULT = new Unario(a,new Tipo(Tipo.Primitivo.BOOLEAN),aleft);:}
                |ARREGLO_LLAMADA:a                     {: RESULT = a; :}
                |FUNCION_LLAMADA:a                    {:RESULT = a;:}
                |CREAR_VENTANA:a                    {:RESULT = a;:}
                |CREAR_CONTENEDOR:a                 {:RESULT = a;:}
                |LEER_GXML:a                 {:RESULT = a;:}
                |OBTENER_POR_ETIQUETA:a {:RESULT = a;:}
                |LLAMADA_OBJETO:a {:RESULT = a;:}
                |CREAR_BOTON:a {:RESULT = a;:}
                ;



CREAR_BOTON::= tId:a tPunto res_crear_boton tParOpen TIPO_PARAMETROS:b tParClose 
                {:RESULT = new CrearBoton(a,b,aleft);:}
                ;

CREAR_CAJA_TEXTO::= tId:a tPunto res_crear_caja_texto tParOpen TIPO_PARAMETROS:b tParClose tPtoComa
                    {:RESULT = new CrearCajaTexto(b,a,aleft);:}
                    ;

CREAR_TEXTO::= tId:a tPunto res_crear_texto tParOpen TIPO_PARAMETROS:b tParClose tPtoComa
                {:RESULT = new CrearTexto(b,a,aleft);:}
                ;

LLAMADA_OBJETO::=  tId:a tPunto tId:b {:RESULT = new ObjetoLlamada(a,b,aleft);:}
                ;

LEER_GXML::=    res_leerGxml tParOpen tCad:a tParClose {:RESULT = new LeerGxml(a,aleft);:}
                ;

OBTENER_POR_ETIQUETA::=   tId:a  tPunto  res_obtenerPorEtiqueta  tParOpen  tCad:b   tParClose
                            {:RESULT = new ObtenerPorEtiqueta(a,b,aleft);:}
                        ;           


CREAR_VENTANA::=    res_crear_ventana tParOpen TIPO_PARAMETROS:a tParClose 
                    {: RESULT = new CrearVentana(a,aleft);:}
                    ;     

CREAR_CONTENEDOR::=  tId:a tPunto res_crear_contenedor tParOpen TIPO_PARAMETROS:b tParClose 
                        {: RESULT = new CrearContenedor(b,a,aleft);:}
                    ;

AL_CLIC::= tId:a tPunto res_alClick tParOpen INSTRUCCION:b tParClose tPtoComa
            {:RESULT = new AlClic(a,b,aleft);:}
            ;
      