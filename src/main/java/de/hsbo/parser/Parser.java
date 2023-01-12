package de.hsbo.parser;

import java.util.List;

import de.hsbo.lexer.TYPE;
import de.hsbo.lexer.Token;
import de.hsbo.inter.BinaryNode;
import de.hsbo.inter.Node;
import de.hsbo.inter.UnaryNode;
import de.hsbo.inter.ValueNode;

public class Parser {
    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {
        return expression();
    }

    private Node expression() {
        Node left = term();
        while (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = previous();
            Node right = term();
            left = new BinaryNode(left, operator, right);
        }
        return left;
    }

    private Node term() {
        Node left = factor();
        while (match(TYPE.MULTIPLY, TYPE.DIVIDE)) {
            Token operator = previous();
            Node right = factor();
            left = new BinaryNode(left, operator, right);
        }
        return left;
    }

    private Node factor() {
        if (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = previous();
            Node right = factor();
            return new UnaryNode(operator, right);
        } else if (match(TYPE.INTEGER, TYPE.FLOAT)) {
            Token value = previous();
            return new ValueNode(value);
        } else if (match(TYPE.LEFT_PAREN)) {
            Node expression = this.expression();
            consume(TYPE.RIGHT_PAREN, "Expect ')' after expression.");
            return expression;
        }
        throw new RuntimeException("Expect expression.");
    }

    private boolean match(TYPE... types) {
        for (TYPE type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TYPE type, String message) {
        if (check(type))
            return advance();
        throw new RuntimeException(message);
    }

    private boolean check(TYPE type) {
        if (isAtEnd())
            return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd())
            current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == TYPE.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}