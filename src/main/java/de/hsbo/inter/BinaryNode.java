package de.hsbo.inter;

import de.hsbo.lexer.Token;

// BinaryNode consists left-node, right-node and binary-operator-node 
// overrides the evaluate() function 
// performs operation of operator type

// ==> 1 + 2
//
//        +
//      /   \
//     1     2

// ==> 2 * 3 + 4
//
//        +
//       / \
//      *   4
//     / \
//    2   3

// ==> 1 + 2 * 3
// 
//        +
//       / \
//      1   *
//         / \
//        2   3

public class BinaryNode extends Node {
    public Node left;
    public Token operator;
    public Node right;

    public BinaryNode(Node left, Token operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();

        switch (operator.type) {
            case PLUS:
                return leftValue + rightValue;
            case MINUS:
                return leftValue - rightValue;
            case MULTIPLY:
                return leftValue * rightValue;
            case DIVIDE:
                return leftValue / rightValue;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                "}";
    }
}
