package com.example.spylang;


import java.util.Scanner;

public class SpyScanner {

    private final Scanner scanner;

    public SpyScanner(String source) {
        this.scanner = new Scanner(source);
    }

    public Token nextToken() {
        while (scanner.hasNext()) {
            String text = scanner.next();

            if (isKeyword(text)) {
                return new Token(TokenType.KEYWORD, text);

            } else if (text.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                return new Token(TokenType.IDENTIFIER, text);

            } else if (text.matches("\\d+")) {
                return new Token(TokenType.INTEGER, Integer.parseInt(text));

            } else if (text.matches("\\d*\\.\\d+")) {
                return new Token(TokenType.FLOAT, Float.parseFloat(text));

            } else if (text.startsWith("\"")) {
                StringBuilder stringValue = new StringBuilder();
                while (scanner.hasNext()) {
                    text = scanner.next();
                    if (text.endsWith("\"")) {
                        stringValue.append(text.substring(0, text.length() - 1));
                        break;
                    }
                    stringValue.append(text).append(" ");
                }
                return new Token(TokenType.STRING, stringValue.toString());

            } else {
                // Skip unrecognized characters
                System.out.println("Skipping unrecognized character: " + text);
            }
        }
        return null;
    }

    private boolean isKeyword(String text) {
        return text.equals("secret") ||
                text.equals("mission") ||
                text.equals("infiltrate") ||
                text.equals("extract") ||
                text.equals("report") ||
                text.equals("message") ||
                text.equals("charlie") ||
                text.equals("data") ||
                text.equals("attack") ||
                text.equals("destroy") ||
                text.equals("alpha") ||
                text.equals("beta") ||
                text.equals("book") ||
                text.equals("library");
    }
}
