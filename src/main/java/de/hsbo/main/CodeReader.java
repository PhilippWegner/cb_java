package de.hsbo.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeReader {
    public static String readCodeFromFileAString(String path) throws FileNotFoundException {
        // create emtpy string code
        String code = "";
        // read file line by line and append line to code
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            code += scanner.nextLine() + "\n";
        }
        scanner.close();

        // return code
        return code;
    }
}
