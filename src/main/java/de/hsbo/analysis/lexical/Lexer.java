package de.hsbo.analysis.lexical;

import java.util.ArrayList;

import de.hsbo.helper.TOKENTYPE;
import de.hsbo.helper.Token;

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

            if (Character.isWhitespace(this.currentCharacter)) {
                // different approach with continue!
                this.currentToken = new Token(TOKENTYPE.WHITESPACE, null);
                this.movePositionForward();
                // continue;
                // '(' as char for comparing
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

            // remove Whitespaces from ArrayList
            this.removeWhitespaceFromTokenList();
        }
    }

    private void movePositionForward() {
        this.currentPosition++;
    }

    private void removeWhitespaceFromTokenList() {
        for (int i = 0; i < this.tokenList.size(); i++) {
            if (this.tokenList.get(i).getTokentype() == TOKENTYPE.WHITESPACE) {
                this.tokenList.remove(i);
            }
        }
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
        while (Character.isLetter(this.currentCharacter)) {
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
        TOKENTYPE tokentype = TOKENTYPE.LETTER;
        if (valueAsString.equalsIgnoreCase("START")) {
            tokentype = TOKENTYPE.START;
        } else if (valueAsString.equalsIgnoreCase("END")) {
            tokentype = TOKENTYPE.END;
        } else if (valueAsString.equalsIgnoreCase("DISPLAY")) {
            tokentype = TOKENTYPE.DISPLAY;
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
