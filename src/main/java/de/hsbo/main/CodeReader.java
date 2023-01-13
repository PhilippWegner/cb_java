package de.hsbo.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeReader {
    public static String readCodeFromFileAString(String path) throws FileNotFoundException {
        // read file line by line and append line to code
        Scanner scanner = new Scanner(new File(path));
        // read all lines of file to list of strings
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();

        // join all lines to one string
        String code = String.join("\n", lines);
        


        // return code
        return code;
    }

    public static boolean checkFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
