/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

/**
 *
 * @author rm
 */
public class CadenaString {
    
    public boolean isCharacter(String cadena){
        if(cadena.length() > 1)
            return false;
        return true;
    }
    
    public boolean characterIsLetter(String cadena){
        char c = cadena.charAt(0);
        if(Character.isLetter(c)){
            return true;
        }
        return false;
    }
    
    public int getAsciiCharacter(String cadena){
        char c = cadena.charAt(0);
        return (int)c;
    }
    
    public Object aumentarCharacter(String cadena){
         char c = cadena.charAt(0);
         c++;
         return c;
    }
    
    public Object decrementarCharacter(String cadena){
         char c = cadena.charAt(0);
         c--;
         return c;
    }

    /* Metodo que retorna un double producto de la sumatoria de cada ASCII que forma una cadena 
        funciona para Strings y caracter
    */
    public double getAsciiFromString(String cadena){ 
        int contador = 0;
        for (int i = 0; i < cadena.length() ; i++ ) {
            contador += (int)cadena.charAt(i);
        }
        return new Double(contador);
    }
}
