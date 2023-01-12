package de.hsbo.main;

import java.io.FileNotFoundException;
import java.util.List;

import de.hsbo.inter.Node;
import de.hsbo.lexer.Lexer;
import de.hsbo.lexer.Token;
import de.hsbo.parser.Parser;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // path to example-code.dmvp file
        String path = "example-code.dmvp";
        // read code from file
        String code = CodeReader.readCodeFromFileAString(path);
        // print code
        System.out.println("Code:");
        System.out.println(code);

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create lexer
        System.out.println("Create lexer");
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        System.out.println(tokens);

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create parser
        System.out.println("Create parser");
        Parser parser = new Parser(tokens);
        Node parseTree = parser.parse();
        double result = parseTree.evaluate();
        System.out.println("Result: " + result);
    
    

    }
}
