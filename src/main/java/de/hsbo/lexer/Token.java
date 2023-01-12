package de.hsbo.lexer;

public class Token {
    public final TYPE type;
    public final String value;


    public Token(TYPE type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TYPE type) {
        this.type = type;
        this.value = null;
    }

    public String toString() {
        if (value == null) {
            return type.toString();
        }
        return type.toString() + "(" + value + ")";
    }
}
