package br.edu.ifb.compiladores.lexico;

import java_cup.runtime.Symbol;
//import java_cup.sym;
import br.edu.ifb.compiladores.sintatico.Sym;
import br.edu.ifb.compiladores.view.Janela;

%%

// nome da classe gerada
%public
%class Lexico
%type Symbol
%unicode
// captura a linha e coluna do token
%line
%column
// indica compatibilidade com o cup (analisador sintático)
%cup

// Funções que serão utilizadas na leitura dos tokens
%{
    // Guarda informações do token simples
    private Symbol symbol(Integer type){
      Symbol s = new Symbol(type, yyline+1, yycolumn+1);
//      Janela.addTextTALexico(s.left + "\t" + s.right + "\t" + Sym.terminalNames[s.sym]);
      return s;
    }
    // Guarda informações do token que possui valor
    private Symbol symbol(Integer type, Object value){
      Symbol s = new Symbol(type, yyline+1, yycolumn+1, value);
//      Janela.addTextTALexico(s.left + "\t" + s.right + "\t" + Sym.terminalNames[s.sym]);
      return s;
    }
    // Mensagem de erro
    private void error(){
        Janela.addTextTALexico("<<###>> Caracter invalido: "+yytext()+" em ["+(yyline+1)+", "+(yycolumn+1)+"]");
        throw new Error("Caracter invalido: "+yytext()+" na linha "+(yyline+1)+" e na coluna "+(yycolumn+1));
    }
%}

// Símbolos
//BRANCO = [\0|\n|\t|\r| ]
IGNORE = [\n|\t|\r]
SPACE = [ ]

// Símbolos
PARENTESE_E = \(
PARENTESE_D = \)
CHAVE_E = \{
CHAVE_D = \}
VIRGULA = ,
PONTO_VIRGULA = ;

// Operadores
OP_MENOR = <
OP_MENOR_IGUAL = <=
OP_MAIOR = >
OP_MAIOR_IGUAL = >=
OP_IGUAL = =
OP_DIFERENTE = <>
OP_ATRIBUICAO = :=
OP_ADD = \+
OP_SUB = -
OP_MUL = \*
OP_DIV = \/
OP_PORCENTO = %
OP_NOT = "not"
OP_AND = "and"
OP_OR  = "or"

// Números
INTEIRO = 0|[1-9][0-9]*|[\+-][0-9]+                                 // inteiro com sinal
FLOAT = [0-9]*,[0-9]+|[0-9]+,[0-9]*|[0-9]*,[0-9]"E"[+-][0-9]+,[0-9] // ponto flutuante literal

// Caracter literal
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
KW_PRNT = "prnt"        // impressão
KW_INT = "int"          // indicador de tipo inteiro
KW_PROC = "proc"        // função
KW_VAR = "var"          // declaração de variável

// Comentários - Utiliza o código hexadecimal dos caracteres que pondem estar no comentário
COMMENT_SIMPLE = \*{2}[\x20-\xED]*[\n|\r]
COMMENT_MULTI = >{2} [\x20-\xED|\x09-\x0D]* <{2}

%%

<YYINITIAL> {
    // Operadores
    {OP_MENOR}       { return symbol(Sym.MENOR);          }
    {OP_MENOR_IGUAL} { return symbol(Sym.MENOR_IGUAL);    }
    {OP_MAIOR}       { return symbol(Sym.MAIOR);          }
    {OP_MAIOR_IGUAL} { return symbol(Sym.MAIOR_IGUAL);    }
    {OP_IGUAL}       { return symbol(Sym.IGUAL);          }
    {OP_DIFERENTE}   { return symbol(Sym.DIFERENTE);      }
    {OP_ATRIBUICAO}  { return symbol(Sym.SETOP);          }
    {OP_ADD}         { return symbol(Sym.ADD);            }
    {OP_SUB}         { return symbol(Sym.SUB);            }
    {OP_MUL}         { return symbol(Sym.MUL);            }
    {OP_DIV}         { return symbol(Sym.DIV);            }
    {OP_PORCENTO}    { return symbol(Sym.PORCENTO);       }
    {OP_NOT}         { return symbol(Sym.NOT);            }
    {OP_AND}         { return symbol(Sym.AND);            }
    {OP_OR}          { return symbol(Sym.OR);             }
    // Símbolos
    {PARENTESE_D}    { return symbol(Sym.PARENTESE_D);    }
    {PARENTESE_E}    { return symbol(Sym.PARENTESE_E);    }
    {CHAVE_D}        { return symbol(Sym.CHAVE_D);        }
    {CHAVE_E}        { return symbol(Sym.CHAVE_E);        }
    {VIRGULA}        { return symbol(Sym.VIRGULA);        }
    {PONTO_VIRGULA}  { return symbol(Sym.PONTO_VIRGULA);  }
    // Palavras chave
    {KW_PROC}        { return symbol(Sym.PROC);             }
    {KW_VAR}         { return symbol(Sym.VAR);              }
    {KW_IF}          { return symbol(Sym.IF);               }
    {KW_ELSE}        { return symbol(Sym.ELSE);             }
    {KW_WHILE}       { return symbol(Sym.WHILE);            }
    {KW_RETURN}      { return symbol(Sym.RETURN);           }
    {KW_INT}         { return symbol(Sym.INTEIRO_T);        }
    {KW_FLOAT}       { return symbol(Sym.FLOAT_T);          }
    {KW_CHAR}        { return symbol(Sym.CHAR_T);           }
    {KW_VOID}        { return symbol(Sym.VOID);             }
    {KW_PRNT}        { return symbol(Sym.PRNT);             }
    // valores
    {INTEIRO}        { return symbol(Sym.INTEIRO, yytext()); }
    {CHAR}           { return symbol(Sym.CHAR, yytext());    }
    {FLOAT}          { return symbol(Sym.FLOAT, yytext());   }
    // Identificador
    {ID}             { return symbol(Sym.ID, yytext()); }
    // Comentários
    {COMMENT_MULTI}  { }//Janela.addTextTALexico((yyline+1) + "\t" + (yycolumn+1) + "\tCOMENTARIO MULTILINHAS"); }
    {COMMENT_SIMPLE} { }//Janela.addTextTALexico((yyline+1) + "\t" + (yycolumn+1) + "\tCOMENTARIO SIMPLES"); }
    // Itens a serem ignorados
    {SPACE}          { }
    {IGNORE}         { }
}

<<EOF>>     { Janela.addTextTALexico("WHOOOOSH!!! NENHUM ERRO LEXICO ENCONTRADO!"); return symbol(Sym.EOF); }

[^]         { error(); }
