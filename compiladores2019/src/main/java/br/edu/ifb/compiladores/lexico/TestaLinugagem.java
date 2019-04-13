package br.edu.ifb.compiladores.lexico;

import java.io.IOException;
import java.io.StringReader;

public class TestaLinugagem {

    public static void main(String[] args) throws IOException {

        String expr = "program teste;\n" +
                "var\n" +
                " valor1: integer;\n" +
                " valor2: real;\n" +
                "begin\n" +
                " valor1 := 10;\n" +
                "end. \n";

        Lexico lexical = new Lexico(new StringReader(expr));
        lexical.yylex();
    }
}