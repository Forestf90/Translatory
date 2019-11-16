package com.translatory;

public class Main {

    public static void main(String[] args) {

        Lexer lexer = new Lexer();
        boolean lexerResult = lexer.analyse("(7+(8-5) +6)*3");
        if(lexerResult){
            System.out.println("Lexer analyse success");
            Parser parser = new Parser(lexer);
            try{
                parser.analise();
            }catch (Exception e){
                System.err.print(e);
            }
        }
    }
}
