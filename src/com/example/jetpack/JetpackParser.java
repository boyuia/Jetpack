package com.example.jetpack;

import java.util.ArrayList;
import java.util.List;

// Assuming Element class exists with a constructor and addProperty method
public class JetpackParser {
    private List<Token> tokens;
    private int currentTokenIndex = 0;

    public JetpackParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Parses an element, assuming it has a name and properties
    public Element parseElement() {
        Token nameToken = consume(TokenType.IDENTIFIER);
        String elementName = nameToken.getValue();
        consume(TokenType.LBRACE);

        Element element = new Element(elementName);

        while (!match(TokenType.RBRACE)) {
            Token propertyNameToken = consume(TokenType.IDENTIFIER);
            String propertyName = propertyNameToken.getValue();
            consume(TokenType.COLON);

            Object propertyValue = parseValue();
            element.addProperty(propertyName, propertyValue);

            // Ensure there is a SEMICOLON or end of element
            if (!match(TokenType.RBRACE)) {
                if (match(TokenType.SEMICOLON)) {
                    consume(TokenType.SEMICOLON);
                } else {
                    throw new RuntimeException("Expected SEMICOLON or RBRACE but found " + tokens.get(currentTokenIndex).getType());
                }
            }
        }

        consume(TokenType.RBRACE);
        return element;
    }

    // Parses a value which could be a String, Number, Boolean, Array, or Element
    private Object parseValue() {
        if (match(TokenType.STRING)) {
            return consume(TokenType.STRING).getValue();
        } else if (match(TokenType.NUMBER)) {
            return Integer.parseInt(consume(TokenType.NUMBER).getValue());
        } else if (match(TokenType.BOOLEAN)) {
            return Boolean.parseBoolean(consume(TokenType.BOOLEAN).getValue());
        } else if (match(TokenType.LBRACKET)) {
            return parseArray();
        } else if (match(TokenType.IDENTIFIER)) {
            return parseElement(); // Assumes parseElement() returns Element
        }
        throw new RuntimeException("Unexpected value type at token index " + currentTokenIndex);
    }

    // Parses an array of values
    private List<Object> parseArray() {
        List<Object> array = new ArrayList<>();
        consume(TokenType.LBRACKET);

        while (!match(TokenType.RBRACKET)) {
            array.add(parseValue());

            // Check if next token is the end of the array or a comma
            if (match(TokenType.RBRACKET)) break;
            if (match(TokenType.COMMA)) {
                consume(TokenType.COMMA);
            } else {
                throw new RuntimeException("Expected COMMA or RBRACKET but found " + tokens.get(currentTokenIndex).getType());
            }
        }

        consume(TokenType.RBRACKET);
        return array;
    }

    // Consumes the current token and expects it to match the expected type
    private Token consume(TokenType expectedType) {
        if (currentTokenIndex >= tokens.size()) {
            throw new RuntimeException("Unexpected end of input");
        }
        Token token = tokens.get(currentTokenIndex);
        if (token.getType() != expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but found " + token.getType() + " with value " + token.getValue());
        }
        currentTokenIndex++;
        return token;
    }

    // Checks if the current token matches the expected type
    private boolean match(TokenType expectedType) {
        if (currentTokenIndex >= tokens.size()) {
            return false;
        }
        return tokens.get(currentTokenIndex).getType() == expectedType;
    }
}
