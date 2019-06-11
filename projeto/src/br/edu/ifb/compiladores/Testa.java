package br.edu.ifb.compiladores;

import br.edu.ifb.compiladores.lexico.Lexico;
import br.edu.ifb.compiladores.sintatico.Sintatico;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Paths;

public class Testa {

    private static void testeSintatico(Reader reader){
        try {
            Lexico lexico = new Lexico(reader);
            Sintatico sintatico = new Sintatico(lexico);
            Object result = sintatico.parse().value;

            System.out.println("Compilacao concluida com sucesso...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String path = Paths.get("").toAbsolutePath().toString()
                        + "/src/br/edu/ifb/compiladores/"
                        + "exemplo1.txt";

        testeSintatico(new FileReader(path));

        Reader r = new StringReader(path);
    }
}
