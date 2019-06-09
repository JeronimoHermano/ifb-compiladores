package br.edu.ifb.compiladores.lexico;

import java.io.File;
import java.nio.file.Paths;

/***
 *  Classe utilizada para gerar apenas o analisador léxico.
 *
 *
 * java -jar /home/johni/Projects/compiler/vendor/jflex-1.6.1.jar Lexer.lex
 *
 * java -jar /media/jeronimo/DADOS/Documentos/Git/ifb-compiladores/java-cup-11a.jar -interface -parser Sintatico -symbols Sym projeto.cup
 *
 */
public class GeraLexico {

    public static void main(String[] args) {
        String rootPath = Paths.get("").toAbsolutePath().toString() + "/src/br/edu/ifb/compiladores/";

        String fileName = "lexico/projeto.flex";

        jflex.Main.generate(new File(rootPath + fileName));
    }

}
