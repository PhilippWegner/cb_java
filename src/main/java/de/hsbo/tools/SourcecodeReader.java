package de.hsbo.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SourcecodeReader {
    public static String getSourcecodeFileAsString(String sourcecodeFilePath) {
        String sourcecode = "";
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(sourcecodeFilePath));
            while(scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + "\n");
            }
            scanner.close();
            sourcecode = sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + sourcecodeFilePath);
        }
        return sourcecode;
    }
}
