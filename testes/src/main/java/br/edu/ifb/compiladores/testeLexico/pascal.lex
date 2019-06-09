import java_cup.runtime.Symbol;

%%

%cup
%public
%class pascal
%type Token
%line
%column

%{
    private Token token(Token.T type){              return new Token(type, yyline, yycolumn);       }
    private Token token(Token.T type, Object val){  return new Token(type, val, yyline, yycolumn);  }
%}

DIGIT = [0-9]
LETTER = [a-zA-Z]


ID          = {LETTER}({LETTER}|{DIGIT})*

INTEGER     = {DIGIT}+
FLOAT       = {DIGIT}+"."{DIGIT}*
            | {DIGIT}*"."{DIGIT}+
STRING      = \~\"{LETTER}+\"\~

VARIABLE    = @{ID}
EXPRESSION  = EXPRESSION "+" EXPRESSION
            | EXPRESSION "-" EXPRESSION
            | EXPRESSION "*" EXPRESSION
            | EXPRESSION "/" EXPRESSION
            | ID
            | INTEGER


IGNORE = [\n|\s|\t\r]

%%

<YYINITIAL> {
    "program"       {return new Symbol(Sym.PROGRAM); }

    {IGNORE}        { return null;}
}
<<EOF>> { return new Symbol( Sym.EOF ); }