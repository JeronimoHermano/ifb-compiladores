package br.edu.ifb.compiladores.projetoFinal;
import java_cup.runtime.*;

%%
// nome da classe gerada
%class Lexico
//%type Token
//%public
// captura a linha e coluna do token
%line
%column
// indica compatibilidade com o cup (analisador sintático
%cup
%cupdebug

// Símbolos
DIGIT = [0-9]
ALPHA = [a-zA-Z]
ALPHA_NUM = [DIGIT|ALPHA]
WHITE = [\0|\n|\t|\r| ]+
SIGNAL = [+-]
OP_REL = ["<"|">"|"<="|">="|"="|"<>"]
OP_ARI = SIGNAL|"*"|"/"|"%"|"and"|"or"|"not"

// Números
INTEGER = [SIGNAL?|[0|1-9]DIGIT*]|[0|[1-9]DIGIT*]
FLOAT = [{INTEGER}*","{DIGIT}+|{INTEGER}+","{DIGIT}*]

// Identificador
ID = [a-z][ALPHA|DIGIT|_]*

// Palavras reservadas (Key Words - KW)
KW_IF = "if"
KW_ELSE = "else"
KW_WHILE = "while"
KW_RETURN = "return"
KW_FLOAT = "float"
KW_CHAR = "char"
KW_VOID = "void"
KW_PRINT = "print"
KW_INT = "int"
KW_ATRIB = ":="
//KW_AND = "and"
//KW_OR = "or"
//KW_NOT = "not"

// Comentários
COMMENT_SIMPLE = [*{2}.*?|WHITE]
COMMENT_MULTI = [>{2}.*?|WHITE<{2}]

%{
    /* Guarda informações do token simples
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn, value);
    }
    /* Guarda informações do token que possui valor embutido
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }*/
%}

%%