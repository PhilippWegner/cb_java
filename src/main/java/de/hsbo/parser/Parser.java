package de.hsbo.parser;

import java.util.List;

import de.hsbo.lexer.TYPE;
import de.hsbo.lexer.Token;

public class Parser {
    private List<Token> tokens;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public double parse() {
        double result = parseExpression();
        return result;
    }

    private double parseExpression() {
        double result = parseTerm();

        while (position < tokens.size() && (tokens.get(position).type == TYPE.PLUS || tokens.get(position).type == TYPE.MINUS)) {
            Token token = tokens.get(position);
            if (token.type == TYPE.PLUS) {
                position++;
                result += parseTerm();
            } else if (token.type == TYPE.MINUS) {
                position++;
                result -= parseTerm();
            }
        }
        return result;
    }

    private double parseTerm() {
        double result = parseFactor();

        while (position < tokens.size() && (tokens.get(position).type == TYPE.MULTIPLY || tokens.get(position).type == TYPE.DIVIDE)) {
            Token token = tokens.get(position);
            if (token.type == TYPE.MULTIPLY) {
                position++;
                result *= parseFactor();
            } else if (token.type == TYPE.DIVIDE) {
                position++;
                result /= parseFactor();
            }
        }
        return result;
    }

    private double parseFactor() {
        Token token = tokens.get(position);
        if (token.type == TYPE.INTEGER || token.type == TYPE.FLOAT) {
            position++;
            return Double.parseDouble(token.value);
        } else if (token.type == TYPE.LEFT_PAREN) {
            position++;
            double result = parseExpression();
            if (tokens.get(position).type != TYPE.RIGHT_PAREN) {
                throw new IllegalArgumentException("Missing closing parenthesis at position " + position);
            }
            position++;
            return result;
        } else if (token.type == TYPE.MINUS) {
            position++;
            return -parseFactor();
        } else {
            throw new IllegalArgumentException("Unexpected token at position " + position);
        }
    }
}
