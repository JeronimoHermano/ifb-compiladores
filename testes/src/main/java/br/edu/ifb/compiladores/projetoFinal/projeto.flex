package br.edu.ifb.compiladores.projetoFinal;

import java_cup.runtime.*;
import sun.jvm.hotspot.debugger.cdbg.Sym;

%%
// nome da classe gerada
%class ProjetoFinal
//%type Token
%public
%unicode
// captura a linha e coluna do token
%line
%column
// indica compatibilidade com o cup (analisador sintático)
%cup

%{
    // Guarda informações do token simples
    private Symbol symbol(int type){
      System.out.println(type + " " + yyline +" "+ yycolumn +" "+ value);
      return new Symbol(type, yyline, yycolumn, value);
    }
    // Mensagem de erro
    private void error(){
        throw  new Error("Caracter invalido: " + yytext()+" na linha "+yyline+1 + "e na coluna "+yycolumn+1);
    }
%}

// Símbolos e operadores (OP)
IGNORE = \0|\n|\t|\r
DIGIT = 0-9
ALPHA = a-zA-Z_
ALPHA_NUM = DIGIT|ALPHA
WHITE = [\0|\n|\t|\r| ]
OP_REL = <|>|<=|>=|=|<>
OP_ARI = \+|-|\*|\/|%|"and"|"or"|"not"
OP_ATRIB = :=
SIMBOLOS = \(|\)|\{|\}|,|;
SINAL = \+-

// Números
INTEGER = {DIGIT}+|{SINAL}{1}{DIGIT}+
FLOAT = {DIGIT}*,{DIGIT}+|{DIGIT}+,{DIGIT}*
REAL = {FLOAT}"E"[-|\+| ]{FLOAT}

// caracter literal
CHAR = \'[ALPHA_NUM|\n|\t| |:|\(|\)|,]*\'

// Identificador
ID = a-z{ALPHA_NUM}*

// Palavras reservadas (Key Words - KW)
KW_IF = "if"            // condicional
KW_ELSE = "else"        // senão
KW_WHILE = "while"      // laço de repetição
KW_RETURN = "return"    // retorno de função
KW_FLOAT = "float"      // tipo ponto flutuante
KW_CHAR = "char"        // tipo caractere
KW_VOID = "void"        // tipo vazio
KW_PRINT = "prnt"       // impressão
KW_INT = "int"          // indicador de tipo inteiro
KW_PROC = "proc"        // função
KW_VAR = "var"          // declaração de variável

// Comentários
COMMENT_SIMPLE = \*{2}[\x20-\xED]*[\n|\r]
COMMENT_MULTI = >{2} [\x20-\xED|\x09-\x0D]* <{2}

%%

<YYINITIAL> {
    {IGNORE}        { }
    {COMMENT_MULTI} { }//return new Symbol(Sym.COMENTARIO_MULTILINHAS);}
    {COMMENT_SIMPLE}{ }//return new Symbol(Sym.COMENTARIO_SIMPLES);    }
    {KW_PROC}       { return new Symbol(Sym.DECLARA_FUNCAO);      }
    {KW_VAR}        { return new Symbol(Sym.DECLARA_VARIAVEL);    }
    {KW_IF}         { return new Symbol(Sym.IF);                  }
    {KW_ELSE}       { return new Symbol(Sym.ELSE);                }
    {KW_WHILE}      { return new Symbol(Sym.LACO);                }
    {KW_RETURN}     { return new Symbol(Sym.RETORNO);             }
    {KW_INT}        { return new Symbol(Sym.TIPO_INT);            }
    {KW_FLOAT}      { return new Symbol(Sym.TIPO_FLOAT);          }
    {KW_CHAR}       { return new Symbol(Sym.TIPO_CHAR);           }
    {KW_VOID}       { return new Symbol(Sym.TIPO_VOID);           }
    {KW_PRINT}      { return new Symbol(Sym.PRINT);               }
    {INTEGER}       { return new Symbol(Sym.INTEIRO);             }
    {CHAR}          { return new Symbol(Sym.CHAR);                }
    {FLOAT}         { return new Symbol(Sym.FLOAT);               }
    {REAL}          { return new Symbol(Sym.FLOAT_LITERAL);       }
    {ID}            { return new Symbol(Sym.ID);                  }
    {OP_ARI}        { return new Symbol(Sym.ARITMETICO);    }
    {OP_REL}        { return new Symbol(Sym.RELACIONAL);    }
    {OP_ATRIB}      { return new Symbol(Sym.ATRIBUICAO);    }
    {SIMBOLOS}      { return new Symbol(Sym.SIMBOLO);    }
}

<<EOF>>     { return symbol(Sym.EOF); }

[^]         { error(); }