package com.translatory;

public class Main {

    public static void main(String[] args) {


        Lexer lexer = new Lexer();
        lexer.analyse("(5 *   4) - 9");
    }
}
