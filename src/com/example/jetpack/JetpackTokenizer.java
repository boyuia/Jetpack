package com.example.jetpack;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JetpackTokenizer {
    private String input;
    private List<Token> tokens = new ArrayList<>();

    // Regex pattern to match tokens
    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
            "\\s*(?:(?<LBRACE>\\{)|(?<RBRACE>\\})|(?<COLON>:)|(?<SEMICOLON>;)|" +
                    "(?<LBRACKET>\\[)|(?<RBRACKET>\\])|(?<STRING>\"[^\"]*\")|(?<NUMBER>\\d+)|" +
                    "(?<BOOLEAN>true|false)|(?<IDENTIFIER>[a-zA-Z_][a-zA-Z_0-9]*)|(?<COMMA>,))"
    );

    public JetpackTokenizer(String input) {
        this.input = input;
    }

    public static List<Token> tokenize(String code) {
        Matcher matcher = TOKEN_PATTERNS.matcher(input);
        while (matcher.find()) {
            if (matcher.group("LBRACE") != null) {
                tokens.add(new Token(TokenType.LBRACE, "{"));
            } else if (matcher.group("RBRACE") != null) {
                tokens.add(new Token(TokenType.RBRACE, "}"));
            } else if (matcher.group("COLON") != null) {
                tokens.add(new Token(TokenType.COLON, ":"));
            } else if (matcher.group("SEMICOLON") != null) {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
            } else if (matcher.group("LBRACKET") != null) {
                tokens.add(new Token(TokenType.LBRACKET, "["));
            } else if (matcher.group("RBRACKET") != null) {
                tokens.add(new Token(TokenType.RBRACKET, "]"));
            } else if (matcher.group("STRING") != null) {
                tokens.add(new Token(TokenType.STRING, matcher.group("STRING")));
            } else if (matcher.group("NUMBER") != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group("NUMBER")));
            } else if (matcher.group("BOOLEAN") != null) {
                tokens.add(new Token(TokenType.BOOLEAN, matcher.group("BOOLEAN")));
            } else if (matcher.group("IDENTIFIER") != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group("IDENTIFIER")));
            } else if (matcher.group("COMMA") != null) {
                tokens.add(new Token(TokenType.COMMA, ","));
            }
        }
        return tokens;
    }
}
