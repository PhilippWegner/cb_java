package de.hsbo.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String rawCode;
    private int position;

    public Lexer(String rawCode) {
        this.rawCode = rawCode;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < rawCode.length()) {
            char currentChar = rawCode.charAt(position);
            if (Character.isDigit(currentChar)) {
                // check if digits are Integer or Float
                tokens.add(tokenizeNumber());
            } else if (currentChar == '+') {
                tokens.add(new Token(TYPE.PLUS));
                position++;
            } else if (currentChar == '-') {
                tokens.add(new Token(TYPE.MINUS));
                position++;
            } else if (currentChar == '*') {
                tokens.add(new Token(TYPE.MULTIPLY));
                position++;
            } else if (currentChar == '/') {
                tokens.add(new Token(TYPE.DIVIDE));
                position++;
            } else if (currentChar == '(') {
                tokens.add(new Token(TYPE.LEFT_PAREN));
                position++;
            } else if (currentChar == ')') {
                tokens.add(new Token(TYPE.RIGHT_PAREN));
                position++;
            } else if (Character.isWhitespace(currentChar)) {
                position++;
            } else {
                throw new IllegalArgumentException("Invalid character '" + currentChar + "' at position " + position);
            }
        }
        tokens.add(new Token(TYPE.EOF));
        return tokens;
    }

    // Tokenize numbers with dotCounter
    private Token tokenizeNumber() {
        int dotCounter = 0;
        String numberAsString = "";

        // 221.5
        // 2 => 22; 2, 2 => 22; 22, 1 => 221; 221, . => 221.; 221., 5 => 221.5
        // ==> 221.5
        
        while (position < rawCode.length() && (Character.isDigit(rawCode.charAt(position)) || rawCode.charAt(position) == '.')) {
            if (rawCode.charAt(position) == '.') {
                dotCounter++;
            }
            numberAsString += rawCode.charAt(position);
            position++;
        }
        if (dotCounter == 0) {
            return new Token(TYPE.INTEGER, numberAsString.toString());
        } else if (dotCounter == 1) {
            return new Token(TYPE.FLOAT, numberAsString.toString());
        } else {
            throw new IllegalArgumentException("Invalid number at position " + position);
        }
    }


}
