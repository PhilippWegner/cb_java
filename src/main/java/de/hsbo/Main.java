package de.hsbo;

import java.util.ArrayList;

import de.hsbo.analysis.lexical.Lexer;
import de.hsbo.analysis.syntactial.Parser;
import de.hsbo.helper.Token;
import de.hsbo.tools.SourcecodeReader;

public class Main {
    public static void main(String[] args) {
        // === 0. SOURCECODE ===
        // get Main resources path as string
        String sourcecodeFilePath = "src/main/resources/de/hsbo/code-example.dmvp";
        // read sourcecode file
        String sourcecode = SourcecodeReader.getSourcecodeFileAsString(sourcecodeFilePath);
        // print sourcecode to console
        System.out.println("Sourcecode:");
        System.out.println("----------------------------");
        System.out.println(sourcecode);
        System.out.println();
        System.out.println("============================");
        System.out.println();

        // === 1. Lexical Analysis (Scanning) ===
        // create lexer and tokenize sourcecode
        Lexer lexer = new Lexer(sourcecode);
        // run lexer through sourcecode
        lexer.run();
        // save all tokens in ArrayList
        ArrayList<Token> tokenArrayList = lexer.getTokenList();
        // print all tokens to console
        System.out.println("Lexical Analysis (Scanning):");
        System.out.println("----------------------------");
        System.out.println(tokenArrayList.toString());
        System.out.println();
        System.out.println("============================");
        System.out.println();

        // === 2. Syntactial Analysis (Parsing) ===
        // create parser and parse tokens
        Parser parser = new Parser(tokenArrayList);
        
    }
}
