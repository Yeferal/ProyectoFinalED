package analizador;
import java_cup.runtime.*;
//import objetos.Error;
import java.util.ArrayList;

%%
%{
    //coidgo de usuario en sintaxis java
    /*ArrayList<Error> listaErrores = new  ArrayList<>();
    

public ArrayList<Error> getListaErrores(){
        return listaErrores;
    }*/

%}

    //directivas
%public 
%class AnalizadorLexico
%cupsym Simbolos
%cup
%char
%line
%column
%full
//%ignorecase

%unicode

    //expreciones regulares

Caracteres      = [\"_@+*#.~`!$%"^"&()={}"[" "]"\;:"'"<>/?"-"ñ]
Letra           = [a-zA-Z]
Identificador   = ({Letra}|{Digito}|{Caracteres}) ({Letra}|{Digito}|{Caracteres})*
Digito          = [0-9]
Numero          = {Digito} {Digito}*
Espacio         = [ \t\r\n]+
%%

<YYINITIAL> {
    {Espacio}                       {/*Ignore*/}
    ({Numero}|{Numero}"."{Numero})  {System.out.println("NUMERO: "+yytext()); return new Symbol(Simbolos.NUMERO , yycolumn, yyline, yytext());}
    {Identificador}                 {System.out.println("identificador: "+yytext()); return new Symbol(Simbolos.IDENTIFICADOR , yycolumn, yyline, yytext());}
    "|"                             {System.out.println("|"); return new Symbol(Simbolos.LINEA , yycolumn, yyline, yytext());}
    
}
