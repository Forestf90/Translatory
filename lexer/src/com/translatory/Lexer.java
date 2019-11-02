package com.translatory;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private List<Token> tokenList = new ArrayList<>();
    private int currentIndex = 0;

    public boolean analyse(String line) {

        while (currentIndex < line.length()) {

            if (b(line)) {
                if (!tokenList.isEmpty() && tokenList.get(tokenList.size() - 1).getType().equals(TokenType.whiteSpace)) {
                    Token last = tokenList.get(tokenList.size() - 1);
                    last.setValue(last.getValue() + line.charAt(currentIndex));
                    currentIndex++;
                } else {
                    tokenList.add(new Token(TokenType.whiteSpace, currentIndex, String.valueOf(line.charAt(currentIndex))));
                    currentIndex++;
                }

            } else if (o(line)) {
                tokenList.add(new Token(TokenType.operator, currentIndex, String.valueOf(line.charAt(currentIndex))));
                currentIndex++;
            } else if (n(line)) {
                tokenList.add(new Token(TokenType.bracket, currentIndex, String.valueOf(line.charAt(currentIndex))));
                currentIndex++;
            } else if (l(line)) {
                if (!tokenList.isEmpty()) {
                    Token last = tokenList.get(tokenList.size() - 1);
                    if (last.getValue().equals("-")) {
                        last.setValue(last.getValue() + line.charAt(currentIndex));
                        last.setType(TokenType.number);
                        currentIndex++;
                    } else if (last.getValue().equals("0")) {
                        System.out.println("Unexpected character at " + currentIndex + " index");
                        System.out.println("Multi-digit number cannot start with 0");
                        return false;
                    } else if (last.getType().equals(TokenType.number)) {
                        last.setValue(last.getValue() + line.charAt(currentIndex));
                        currentIndex++;
                    } else {
                        tokenList.add(new Token(TokenType.number, currentIndex, String.valueOf(line.charAt(currentIndex))));
                        currentIndex++;
                    }
                }
            } else {
                System.out.println("Unexpected character at " + currentIndex + " index");
                return false;
            }
        }

        displayTokens();
        return true;
    }

    public void displayTokens() {

        for (Token t : tokenList) {
            System.out.println(t.getType() + " '" + t.getValue() + "'  index:" + t.getIndex());
        }
    }

    private boolean b(String line) {
        return line.charAt(currentIndex) == ' ';
    }

    private boolean o(String line) {
        return "+-*/".contains("" + line.charAt(currentIndex));
    }

    private boolean n(String line) {

        return ")(".contains("" + line.charAt(currentIndex));
    }

    private boolean l(String line) {

        return "0123456789".contains("" + line.charAt(currentIndex));
    }
}

