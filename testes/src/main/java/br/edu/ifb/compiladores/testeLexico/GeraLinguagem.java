package br.edu.ifb.compiladores.testeLexico;

import java.io.File;
import java.nio.file.Paths;

public class GeraLinguagem {
    public static void main(String[] args) {

        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/main/java/br/edu/ifb/compiladores/testeLexico/";

        String file = rootPath + subPath + "linguagem.lex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);

    }
}
