package de.hsbo.lexer;

public class Token {
    private TOKENTYPE tokentype;
    private String value;

    public Token(TOKENTYPE tokentype, String value) {
        this.tokentype = tokentype;
        this.value = value;
    }

    public TOKENTYPE getTokentype() {
        return this.tokentype;
    }

    public void setTokentype(TOKENTYPE tokentype) {
        this.tokentype = tokentype;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        if(this.tokentype == TOKENTYPE.NUMBER | this.tokentype == TOKENTYPE.IDENTIFIER) {
            return this.tokentype + "(" + this.value + ")";
        }
        return this.tokentype + "";
    }
}
