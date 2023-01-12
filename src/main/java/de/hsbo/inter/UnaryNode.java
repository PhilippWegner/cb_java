package de.hsbo.inter;

import de.hsbo.lexer.Token;
import de.hsbo.main.Main;

// UnaryNode consists right-node and unary-operator-node
// overrides the evaluate() function
// performs operation of operator type
// (for negative Numbers)

// ==> -2
//
//          -
//           \
//            2

// ==> -2 * 3
//
//          *
//         / \
//        -   3
//         \
//          2

public class UnaryNode extends Node {
    private Token operator;
    private Node right;

    public UnaryNode(Token operator, Node right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double rightValue = right.evaluate();

        switch (operator.type) {
            case PLUS:
                return rightValue;
            case MINUS:
                return -rightValue;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String toString() {
        return "UnaryNode{" +
                "operator=" + operator +
                ", right=" + right +
                "}";
    }
}