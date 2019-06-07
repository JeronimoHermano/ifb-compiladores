package br.edu.ifb.compiladores;

import br.edu.ifb.compiladores.lexico.ProjetoFinal;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Testa {

    public static void main(String[] args) throws IOException {

        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/src/br/edu/ifb/compiladores/";
        String file = rootPath + subPath + "exemplo1.txt";

        ProjetoFinal projetoFinal = new ProjetoFinal(new FileReader(file));
        projetoFinal.yylex();
    }
}
