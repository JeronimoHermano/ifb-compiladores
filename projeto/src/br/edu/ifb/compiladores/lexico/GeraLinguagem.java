package br.edu.ifb.compiladores.lexico;

import java.io.File;
import java.nio.file.Paths;

public class GeraLinguagem {

    public static void main(String[] args) {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/src/br/edu/ifb/compiladores/";

        String file = rootPath + subPath + "projeto.flex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);
    }

}
