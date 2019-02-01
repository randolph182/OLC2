/*----------------- 1era Area: Codigo de usuario --------------------*/

package olc2_Tarea1;
import java_cup.runtime.*;
import java.io.Reader;
import javax.swing.JOptionPane;

/*----------------- 2da Area: Opciones y declaraciones --------------------*/
%%
%{
//----------> Codigo de usuario en sintaxis java    
%}
//----------> Directivas
%public
%class scanner
%cup
%char
%column
%full
%ignorecase
%line
%unicode
//------> Expresiones regulares
id = [a-zA-z][_a-zA-Z0-9]*
//------> Estados
%%
/*----------------- 3era Area: Reglas lexicas --------------------*/
//---->simbolos
<YYINITIAL> {
"int"           {System.out.print("int "); return new Symbol(sym.tInt, yychar,yyline);}
"bool"           {System.out.print("bool "); return new Symbol(sym.tBool, yychar,yyline);}
"char"           {System.out.print("char "); return new Symbol(sym.tChar, yychar,yyline);}
"{"             {System.out.print("{"); return new Symbol(sym.tLlvOpn, yychar,yyline);}
"}"             {System.out.print("}"); return new Symbol(sym.tLlvCls, yychar,yyline);}
";"             {System.out.print(";"); return new Symbol(sym.tPComa, yychar,yyline);}
{id}            {System.out.print("id"); return new Symbol(sym.tId, yychar,yyline,yytext());}

[ \t\r\f\n]+		{}
.	{
            JOptionPane.showMessageDialog(null, "Error Lexico vino : "+yytext()+" en la linea "+(yyline+1)+" y columna "+(yychar+1));
	}	
}
