package com.example.spylang;

public class SpyParser {

    private final SpyScanner scanner;
    private Token currentToken;

    public SpyParser(String source) {
        this.scanner = new SpyScanner(source);
        this.currentToken = scanner.nextToken();
    }

    public boolean parse() throws Exception {
        while (currentToken != null) {
            if (currentToken.getType() == TokenType.KEYWORD) {
                handleKeyword();
            } else {
                throw new Exception("Unexpected token: " + currentToken.getValue());
            }
        }
        return true;
    }

    private void handleKeyword() throws Exception {
        String keyword = (String) currentToken.getValue();
        consumeToken();

        // Check for valid following tokens
        if (currentToken != null) {
            TokenType nextType = currentToken.getType();
            if (nextType == TokenType.IDENTIFIER || nextType == TokenType.FLOAT || nextType == TokenType.INTEGER) {
                System.out.println("Valid keyword followed by: " + nextType);
                consumeToken();
            } else if (nextType == TokenType.KEYWORD) {
                System.out.println("Valid keyword followed by another keyword: " + currentToken.getValue());
                consumeToken();
            } else {
                throw new Exception("Unexpected token after keyword: " + currentToken.getValue());
            }
        }
    }

    private void consumeToken() {
        currentToken = scanner.nextToken();
    }
}

/* grammar as a key used here:

    Rules: -keywords followed by only one identifier, int, float are allowed
           -keywords followed by nothing are allowed when used at the end of spy code string.
            they should be even in count if used in between spy code sentence.
            (but keyword keyword identifier/float/int syntax is not allowed)
           -if the code is valid it'll be converted to morse code.

 */
