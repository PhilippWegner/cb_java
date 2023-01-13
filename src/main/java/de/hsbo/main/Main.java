package de.hsbo.main;

import java.io.FileNotFoundException;
import java.util.List;

import de.hsbo.inter.IntermediateCodeGenerator;
import de.hsbo.inter.Node;
import de.hsbo.lexer.Lexer;
import de.hsbo.lexer.Token;
import de.hsbo.parser.Parser;

public class Main {

    public static int tempCounter = 0;

    public static void main(String[] args) throws FileNotFoundException {
        // path to example-code.dmvp file
        // String path = "example-code.dmvp";
        String path = args[0];
        // check if file exists
        if (!CodeReader.checkFileExists(path)) {
            System.out.println("File does not exist!");
            return;
        }
        // read code from file
        String code = CodeReader.readCodeFromFileAString(path);
        // print code
        System.out.println("Code:");
        System.out.println(code);

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create lexer
        System.out.println("Lexer tokens:");
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        System.out.println(tokens);

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create parser
        Parser parser = new Parser(tokens);
        Node parseTree = parser.parse();
        System.out.println("Parser tokens:");
        System.out.println(parseTree);

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create node printer
        System.out.println("Create Syntax Tree:");
        NodePrinter nodePrinter = new NodePrinter(parseTree);
        nodePrinter.print();

        // ------
        System.out.println("-----------------");
        System.out.println();

        // create intermediate code generator
        System.out.println("Create Intermediate Code:");
        IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator(parseTree);
        List<String> intermediateCode = intermediateCodeGenerator.generate();
        intermediateCodeGenerator.print();

        // ------
        System.out.println("-----------------");
        System.out.println();

        double result = parseTree.evaluate();
        System.out.println("Mathematical Result: " + result);

        // ------
        System.out.println("-----------------");
        System.out.println();

        
    }
}
