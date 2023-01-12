package de.hsbo.lexer;

public enum TYPE {
    // Keywords
    
    // Special characters
    EOF, INVALID,
    // Operators
    PLUS, MINUS, MULTIPLY, DIVIDE, 
    // Delimiters
    LEFT_PAREN, RIGHT_PAREN,
    // Literals
    INTEGER, FLOAT, STRING;

}
