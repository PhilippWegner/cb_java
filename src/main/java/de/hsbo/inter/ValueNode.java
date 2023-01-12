package de.hsbo.inter;

import de.hsbo.lexer.Token;

// ValueNode consists value-node
// overrides the evaluate() function
// returns value of value-node

public class ValueNode extends Node {
    private Token value;

    public ValueNode(Token value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return Double.parseDouble(value.value);
    }
}





