package br.edu.ifb.compiladores;

import java_cup.runtime.*;
//import sun.jvm.hotspot.debugger.cdbg.Sym;

%%
// nome da classe gerada
%class ProjetoLexico
%public
%unicode
// captura a linha e coluna do token
%line
%column
// indica compatibilidade com o cup (analisador sintático)
%cup

%{
    // Guarda informações do token simples
    private void symbol(String type){
        return new Symbol(type, yyline, yycolumn);
    }
    // Mensagem de erro
    private void error(){
        throw  new Error("Caracter invalido: "+ytext()+" na linha "+(yyline+1 + " e na coluna "+yycolumn+1);
    }
%}

// Símbolos
//WHITE = [\0|\n|\t|\r| ]
OP_REL = <|>|<=|>=|=|<>
OP_ATRIB = :=
SIMBOLOS = \(|\)|\{|\}|,|;

// Símbolos
PARL = \(
PARR = \)


// Operadores
OP_ADD = \+
OP_SUB = -
OP_MUL = \*
OP_DIV = \/
OP_PERCENT = %
OP_NOT = "not"
OP_AND = "and"
OP_OR  = "or"

// Números
INTEGER = [0-9]+
INT_LIT = \+-{1}[0-9]+
FLOAT = [0-9]*,[0-9]+|[0-9]+,[0-9]*
REAL = [0-9]*,[0-9]"E"[-|\+|][0-9]+,[0-9]

// caracter literal
CHAR = \'[a-zA-Z_|0-9|\n|\t| |:|\(|\)|,]\'

// Identificador
ID = [a-z][a-zA-Z_|0-9]*

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
// S[imbolos ignorados
IGNORE = [\0|\n|\t|\r| ]

%%

<YYINITIAL> {
    {COMMENT_MULTI}  { symbol("comentario S");   }
    {COMMENT_SIMPLE} { symbol("comentario M");   }
    {KW_PROC}       { symbol("Sym.DECLARA_FUNCAO");      }
    {KW_VAR}        { symbol("Sym.DECLARA_VARIAVEL");    }
    {KW_IF}         { symbol("Sym.IF");                  }
    {KW_ELSE}       { symbol("Sym.ELSE");                }
    {KW_WHILE}      { symbol("Sym.LACO");                }
    {KW_RETURN}     { symbol("Sym.RETORNO");             }
    {KW_INT}        { symbol("Sym.TIPO_INT");            }
    {KW_FLOAT}      { symbol("Sym.TIPO_FLOAT");          }
    {KW_CHAR}       { symbol("Sym.TIPO_CHAR");           }
    {KW_VOID}       { symbol("Sym.TIPO_VOID");           }
    {KW_PRINT}      { symbol("Sym.PRINT");               }
    {INTEGER}       { symbol("Sym.INTEIRO");             }
    {INT_LIT}       { symbol("Sym.INT_LIT");             }
    {CHAR}          { symbol("Sym.CHAR");                }
    {FLOAT}         { symbol("Sym.FLOAT");               }
    {REAL}          { symbol("Sym.FLOAT_LITERAL");       }
    {ID}            { symbol("Sym.ID");                  }
    {OP_ARI}        { symbol("Sym.ARITMETICO"); }
    {OP_REL}        { symbol("Sym.RELACIONAL"); }
    {OP_ATRIB}      { symbol("Sym.ATRIBUICAO"); }
    {SIMBOLOS}      { symbol("Sym.SIMBOLO");    }
    {IGNORE}         {}
}

<<EOF>>     { symbol("EOF");}

[^]         { error(); }
