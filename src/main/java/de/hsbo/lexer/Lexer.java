package de.hsbo.lexer;

import java.util.ArrayList;

public class Lexer {
    private String sourcecode;
    private int sourcecodeLength;

    private int currentPosition;
    private char currentCharacter;

    private Token currentToken;

    private ArrayList<Token> tokenList = new ArrayList<Token>();

    public Lexer(String sourcecode) {
        this.sourcecode = sourcecode;
        this.sourcecodeLength = sourcecode.length();
        this.currentPosition = 0;
    }

    public void run() {
        this.nextToken();
    }

    private void nextToken() {
        while (this.currentPosition < this.sourcecodeLength) {
            // read character at current position
            this.currentCharacter = this.sourcecode.charAt(this.currentPosition);

            if (this.currentCharacter == ' ' || this.currentCharacter == '\t') {
                // skip
                this.movePositionForward();
                continue;
            } else if (this.currentCharacter == '(') {
                this.currentToken = new Token(TOKENTYPE.OPEN_PARENTHESIS, "(");
                this.movePositionForward();
            } else if (this.currentCharacter == ')') {
                this.currentToken = new Token(TOKENTYPE.CLOSE_PARENTHESIS, ")");
                this.movePositionForward();
            } else if (this.currentCharacter == '+') {
                this.currentToken = new Token(TOKENTYPE.ADD, "+");
                this.movePositionForward();
            } else if (this.currentCharacter == '-') {
                this.currentToken = new Token(TOKENTYPE.SUBSTRACT, "-");
                this.movePositionForward();
            } else if (this.currentCharacter == '*') {
                this.currentToken = new Token(TOKENTYPE.MULTIPLY, "*");
                this.movePositionForward();
            } else if (this.currentCharacter == '/') {
                this.currentToken = new Token(TOKENTYPE.DIVIDE, "/");
                this.movePositionForward();
            } else if (this.currentCharacter == '=') {
                this.currentToken = new Token(TOKENTYPE.DECLARE, "=");
                this.movePositionForward();
            } else if (this.currentCharacter == ',') {
                this.currentToken = new Token(TOKENTYPE.COMMA, ",");
                this.movePositionForward();
            } else if (this.currentCharacter == '\n') {
                this.currentToken = new Token(TOKENTYPE.NEWLINE, "\n");
                this.movePositionForward();
            } else if (Character.isDigit(this.currentCharacter)) {
                String valueAsString = this.getNumberValue();
                TOKENTYPE tokentype = TOKENTYPE.NUMBER;
                this.currentToken = new Token(tokentype, valueAsString);
            } else if (Character.isLetter(this.currentCharacter)) {
                String valueAsString = this.getLetterValue();
                TOKENTYPE tokentype = this.getTokenTypeEnum(valueAsString);
                this.currentToken = new Token(tokentype, valueAsString);
            } else {
                this.currentToken = new Token(TOKENTYPE.INVALID, this.currentCharacter + "");
                this.movePositionForward();
            }
            // add currentToken to tokenList
            this.tokenList.add(this.currentToken);
        }
    }

    private void movePositionForward() {
        this.currentPosition++;
    }

    private String getNumberValue() {
        StringBuilder valueAsStringBuilder = new StringBuilder();
        while (Character.isDigit(this.currentCharacter)) {
            valueAsStringBuilder.append(this.currentCharacter);
            this.movePositionForward();
            if (this.currentPosition >= this.sourcecodeLength) {
                break;
            }
            this.currentCharacter = this.sourcecode.charAt(this.currentPosition);
        }
        return valueAsStringBuilder.toString();
    }

    // merge followed Letters to String
    private String getLetterValue() {
        StringBuilder valueAsStringBuilder = new StringBuilder();
        // Identifiers can contain digits too!
        while (Character.isLetterOrDigit(this.currentCharacter)) {
            valueAsStringBuilder.append(this.currentCharacter);
            this.movePositionForward();
            if (this.currentPosition >= this.sourcecodeLength) {
                break;
            }
            this.currentCharacter = this.sourcecode.charAt(this.currentPosition);
        }
        return valueAsStringBuilder.toString();
    }

    // check if String Value is a keyword and return new TOKENTYPE
    private TOKENTYPE getTokenTypeEnum(String valueAsString) {
        TOKENTYPE tokentype = TOKENTYPE.IDENTIFIER;
        if (valueAsString.equalsIgnoreCase("BEGIN")) {
            tokentype = TOKENTYPE.BEGIN;
        } else if (valueAsString.equalsIgnoreCase("END")) {
            tokentype = TOKENTYPE.END;
        } else if (valueAsString.equalsIgnoreCase("DISPLAY")) {
            tokentype = TOKENTYPE.DISPLAY;
        } else if (valueAsString.equalsIgnoreCase("FUNCTION")) {
            tokentype = TOKENTYPE.FUNCTION;
        }
        return tokentype;
    }

    public Token getCurrentToken() {
        return this.currentToken;
    }

    public ArrayList<Token> getTokenList() {
        return this.tokenList;
    }
}
