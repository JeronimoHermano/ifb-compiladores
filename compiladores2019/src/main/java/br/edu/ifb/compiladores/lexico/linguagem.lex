package br.edu.ifb.compiladores.lexico;

import java_cup.runtime.*;

%%

%{


private void print(String name, String value, Integer line, Integer column)    {
    //System.out.println( value + "\t|\t" + line + " " + column + "\t|\t" + name);
    System.out.print(line + " ");
    System.out.print(column + " ");
    System.out.print(name + "\t\t");
    System.out.println(value);
}

%}

%public
%class Lexico
%type void
%line
%column

KEY_WOKD    = "program"|"var"|"integer"|"rea"]|"boolean"|"procedure"|"begin"|"end"|"if"|"then"|"else"|"while"|"do"|"not"
ID          = [a-zA-Z][a-zA-Z0-9]*
INTEGER     = 0|[1-9][0-9]*
FLOAT       = [0-9]+.[0-9]*
WHITE       = [ ]|[\t]
BREAK_LINE  = [\n]
DELIMITER   = [;]|[.]|[:]|[(]|[)]|[,]
ASSIGNMENT  = :=
RELATIONAL  = [=]|[<]|[>]|[<=]|[>=]|[<>]
ADD         = [+]|[-]|[or]
MUL         = [*]|[/]|[and]

program = "program"

%%

{program}       { print(yytext(), "", yyline, yycolumn);}
{KEY_WOKD}      { print("palavra reservada", yytext(), yyline, yycolumn); }
{ID}            { print("identificador    ", yytext(), yyline, yycolumn); }
{INTEGER}       { print("inteiro          ", yytext(), yyline, yycolumn); }
{FLOAT}         { print("ponto flutuante  ", yytext(), yyline, yycolumn); }
{WHITE}         { print("espaço em branco ", yytext(), yyline, yycolumn); }
{BREAK_LINE}    { print("quebra de linha  ", "\\n", yyline, yycolumn); }
{DELIMITER}     { print("delimitador      ", yytext(), yyline, yycolumn); }
{ASSIGNMENT}    { print("atribuicao       ", yytext(), yyline, yycolumn); }
{RELATIONAL}    { print("realcionais      ", yytext(), yyline, yycolumn); }
{ADD}           { print("realcionais      ", yytext(), yyline, yycolumn); }
{MUL}           { print("realcionais      ", yytext(), yyline, yycolumn); }

. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }