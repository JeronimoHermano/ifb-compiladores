package br.edu.ifb.compiladores;

import br.edu.ifb.compiladores.lexico.Lexico;
import br.edu.ifb.compiladores.sintatico.Sintatico;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Testa {

    private static void testeSintatico(String file){
        try {
            Sintatico sintatico = new Sintatico(new Lexico(new FileReader(file)));
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
        testeSintatico(path);
    }
}
