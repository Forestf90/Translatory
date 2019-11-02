package com.translatory;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private List<Token> tokenList = new ArrayList<>();
    private int currentIndex = 0;

    public boolean analyse(String line){

        while(currentIndex < line.length()){

            if(b(line)){
                if(!tokenList.isEmpty() && tokenList.get(tokenList.size()-1).getType().equals(TokenType.whiteSpace)){
                    Token last =tokenList.get(tokenList.size()-1);
                    last.setValue(last.getValue()+line.charAt(currentIndex));
                    currentIndex++;
                }
                else{
                    tokenList.add(new Token(TokenType.whiteSpace ,currentIndex, String.valueOf(line.charAt(currentIndex))));
                    currentIndex++;
                }

            }
            else if(o(line)){
                tokenList.add(new Token(TokenType.operator ,currentIndex, String.valueOf(line.charAt(currentIndex))));
                currentIndex++;
            }
            else if(n(line)){
                tokenList.add(new Token(TokenType.bracket ,currentIndex, String.valueOf(line.charAt(currentIndex))));
                currentIndex++;
            }
            else currentIndex++;
        }

        displayTokens();
        return true;
    }

    public void displayTokens(){

        for(Token t: tokenList){
            System.out.println(t.getType()+" '"+t.getValue()+ "'  index:"+t.getIndex());
        }
    }
    private boolean b(String line){

        //System.out.println(line.toCharArray()[currentIndex]);
        return line.charAt(currentIndex) == ' ';
    }

    private boolean o(String line){

        return "+-*/".contains(""+line.charAt(currentIndex));
    }

    private boolean n(String line){

        return ")(".contains(""+line.charAt(currentIndex));
    }

}
